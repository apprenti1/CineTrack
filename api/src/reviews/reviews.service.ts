import { Injectable, NotFoundException, ForbiddenException, ConflictException } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import { CreateReviewDto } from './dto/create-review.dto';
import { UpdateReviewDto } from './dto/update-review.dto';

@Injectable()
export class ReviewsService {
  constructor(private prisma: PrismaService) {}

  async create(userId: string, createReviewDto: CreateReviewDto) {
    // Vérifier si l'utilisateur a déjà laissé un avis pour ce film
    const existingReview = await this.prisma.review.findUnique({
      where: {
        userId_filmId: {
          userId,
          filmId: createReviewDto.filmId,
        },
      },
    });

    if (existingReview) {
      throw new ConflictException('Vous avez déjà laissé un avis pour ce film');
    }

    return this.prisma.review.create({
      data: {
        userId,
        filmId: createReviewDto.filmId,
        rating: createReviewDto.rating,
        comment: createReviewDto.comment,
      },
      include: {
        user: {
          select: {
            id: true,
            pseudo: true,
          },
        },
      },
    });
  }

  async findByFilm(filmId: number) {
    return this.prisma.review.findMany({
      where: { filmId },
      include: {
        user: {
          select: {
            id: true,
            pseudo: true,
          },
        },
      },
      orderBy: {
        createdAt: 'desc',
      },
    });
  }

  async findByUser(userId: string) {
    return this.prisma.review.findMany({
      where: { userId },
      orderBy: {
        createdAt: 'desc',
      },
    });
  }

  async update(reviewId: string, userId: string, updateReviewDto: UpdateReviewDto) {
    const review = await this.prisma.review.findUnique({
      where: { id: reviewId },
    });

    if (!review) {
      throw new NotFoundException('Avis non trouvé');
    }

    if (review.userId !== userId) {
      throw new ForbiddenException('Vous ne pouvez modifier que vos propres avis');
    }

    return this.prisma.review.update({
      where: { id: reviewId },
      data: updateReviewDto,
      include: {
        user: {
          select: {
            id: true,
            pseudo: true,
          },
        },
      },
    });
  }

  async remove(reviewId: string, userId: string) {
    const review = await this.prisma.review.findUnique({
      where: { id: reviewId },
    });

    if (!review) {
      throw new NotFoundException('Avis non trouvé');
    }

    if (review.userId !== userId) {
      throw new ForbiddenException('Vous ne pouvez supprimer que vos propres avis');
    }

    await this.prisma.review.delete({
      where: { id: reviewId },
    });

    return { message: 'Avis supprimé avec succès' };
  }
}
