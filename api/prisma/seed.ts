/* eslint-disable @typescript-eslint/no-unsafe-assignment */
/* eslint-disable @typescript-eslint/no-unsafe-member-access */
/* eslint-disable @typescript-eslint/no-unsafe-return */
import { PrismaClient } from '@prisma/client';
import fs from 'fs';
import * as bcrypt from 'bcrypt';

const prisma = new PrismaClient();

function loadJSON(path: string) {
  return JSON.parse(fs.readFileSync(path, 'utf8'));
}

async function main() {
  // --- Seed Users ---
  const usersData = loadJSON('prisma/seed/users.json');
  const users: Array<{
    id: string;
    pseudo: string;
    email: string;
    password: string;
    watchlist: number[];
    likes: number[];
    createdAt: Date;
    updatedAt: Date;
  }> = [];
  for (const user of usersData) {
    const created = await prisma.user.upsert({
      where: { pseudo: user.pseudo },
      update: {},
      create: {
        ...user,
        password: await bcrypt.hash(String(user.password), 10),
      },
    });

    users.push(created);
  }

  // --- Seed Reviews ---
  const reviewsData = loadJSON('prisma/seed/reviews.json');
  for (let i = 0; i < reviewsData.length; i++) {
    const userId = users[i % users.length].id;
    const filmId = reviewsData[i].filmId as number;
    await prisma.review.upsert({
      where: { userId_filmId: { userId, filmId } },
      update: {},
      create: {
        ...reviewsData[i],
        userId,
        filmId,
      },
    });
  }

  // --- Seed Lists ---
  const listsData = loadJSON('prisma/seed/lists.json');
  for (let i = 0; i < listsData.length; i++) {
    await prisma.list.create({
      data: {
        ...listsData[i],
        userId: users[i % users.length].id,
      },
    });
  }

  console.log('Seed completed!');
}

main()
  .then(() => prisma.$disconnect())
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });
