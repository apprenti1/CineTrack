import { Injectable, NotFoundException } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';

@Injectable()
export class UsersService {
  constructor(private prisma: PrismaService) {}

  async getProfile(userId: string) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    return user;
  }

  async addToWatchlist(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    if (user.watchlist.includes(filmId)) {
      return { message: 'Film déjà dans la watchlist' };
    }

    await this.prisma.user.update({
      where: { id: userId },
      data: {
        watchlist: {
          push: filmId,
        },
      },
    });

    return { message: 'Film ajouté à la watchlist' };
  }

  async removeFromWatchlist(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    const updatedWatchlist = user.watchlist.filter((id) => id !== filmId);

    await this.prisma.user.update({
      where: { id: userId },
      data: {
        watchlist: updatedWatchlist,
      },
    });

    return { message: 'Film retiré de la watchlist' };
  }

  async addToLikes(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    if (user.likes.includes(filmId)) {
      return { message: 'Film déjà dans les likes' };
    }

    await this.prisma.user.update({
      where: { id: userId },
      data: {
        likes: {
          push: filmId,
        },
      },
    });

    return { message: 'Film ajouté aux likes' };
  }

  async removeFromLikes(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    const updatedLikes = user.likes.filter((id) => id !== filmId);

    await this.prisma.user.update({
      where: { id: userId },
      data: {
        likes: updatedLikes,
      },
    });

    return { message: 'Film retiré des likes' };
  }
}
