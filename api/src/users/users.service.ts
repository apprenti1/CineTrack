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
        watched: true,
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
      return this.getProfile(userId);
    }

    const updatedUser = await this.prisma.user.update({
      where: { id: userId },
      data: {
        watchlist: {
          push: filmId,
        },
      },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        watched: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    return updatedUser;
  }

  async removeFromWatchlist(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    const updatedWatchlist = user.watchlist.filter((id) => id !== filmId);

    const updatedUser = await this.prisma.user.update({
      where: { id: userId },
      data: {
        watchlist: updatedWatchlist,
      },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        watched: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    return updatedUser;
  }

  async addToLikes(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    if (user.likes.includes(filmId)) {
      return this.getProfile(userId);
    }

    const updatedUser = await this.prisma.user.update({
      where: { id: userId },
      data: {
        likes: {
          push: filmId,
        },
      },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        watched: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    return updatedUser;
  }

  async removeFromLikes(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    const updatedLikes = user.likes.filter((id) => id !== filmId);

    const updatedUser = await this.prisma.user.update({
      where: { id: userId },
      data: {
        likes: updatedLikes,
      },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        watched: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    return updatedUser;
  }

  async addToWatched(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    if (user.watched.includes(filmId)) {
      return this.getProfile(userId);
    }

    const updatedUser = await this.prisma.user.update({
      where: { id: userId },
      data: {
        watched: {
          push: filmId,
        },
      },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        watched: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    return updatedUser;
  }

  async removeFromWatched(userId: string, filmId: number) {
    const user = await this.prisma.user.findUnique({
      where: { id: userId },
    });

    if (!user) {
      throw new NotFoundException('Utilisateur non trouvé');
    }

    const updatedWatched = user.watched.filter((id) => id !== filmId);

    const updatedUser = await this.prisma.user.update({
      where: { id: userId },
      data: {
        watched: updatedWatched,
      },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        watched: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    return updatedUser;
  }
}
