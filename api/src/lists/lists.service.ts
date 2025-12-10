import {
  Injectable,
  NotFoundException,
  ForbiddenException,
} from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import { CreateListDto } from './dto/create-list.dto';
import { UpdateListDto } from './dto/update-list.dto';

@Injectable()
export class ListsService {
  constructor(private prisma: PrismaService) {}

  async create(userId: string, createListDto: CreateListDto) {
    return this.prisma.list.create({
      data: {
        userId,
        name: createListDto.name,
      },
    });
  }

  async findByUser(userId: string) {
    return this.prisma.list.findMany({
      where: { userId },
      orderBy: {
        createdAt: 'desc',
      },
    });
  }

  async findOne(listId: string) {
    const list = await this.prisma.list.findUnique({
      where: { id: listId },
      include: {
        user: {
          select: {
            id: true,
            pseudo: true,
          },
        },
      },
    });

    if (!list) {
      throw new NotFoundException('Liste non trouvée');
    }

    return list;
  }

  async findAll() {
    const lists = await this.prisma.list.findMany({
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

    if (!lists || lists.length === 0) {
      throw new NotFoundException('Aucune liste trouvée');
    }

    return lists;
  }

  async update(listId: string, userId: string, updateListDto: UpdateListDto) {
    const list = await this.prisma.list.findUnique({
      where: { id: listId },
    });

    if (!list) {
      throw new NotFoundException('Liste non trouvée');
    }

    if (list.userId !== userId) {
      throw new ForbiddenException(
        'Vous ne pouvez modifier que vos propres listes',
      );
    }

    return this.prisma.list.update({
      where: { id: listId },
      data: updateListDto,
    });
  }

  async remove(listId: string, userId: string) {
    const list = await this.prisma.list.findUnique({
      where: { id: listId },
    });

    if (!list) {
      throw new NotFoundException('Liste non trouvée');
    }

    if (list.userId !== userId) {
      throw new ForbiddenException(
        'Vous ne pouvez supprimer que vos propres listes',
      );
    }

    await this.prisma.list.delete({
      where: { id: listId },
    });

    return { message: 'Liste supprimée avec succès' };
  }

  async addFilm(listId: string, userId: string, filmId: number) {
    const list = await this.prisma.list.findUnique({
      where: { id: listId },
    });

    if (!list) {
      throw new NotFoundException('Liste non trouvée');
    }

    if (list.userId !== userId) {
      throw new ForbiddenException(
        'Vous ne pouvez modifier que vos propres listes',
      );
    }

    if (list.filmIds.includes(filmId)) {
      return { message: 'Film déjà dans la liste' };
    }

    await this.prisma.list.update({
      where: { id: listId },
      data: {
        filmIds: {
          push: filmId,
        },
      },
    });

    return { message: 'Film ajouté à la liste' };
  }

  async removeFilm(listId: string, userId: string, filmId: number) {
    const list = await this.prisma.list.findUnique({
      where: { id: listId },
    });

    if (!list) {
      throw new NotFoundException('Liste non trouvée');
    }

    if (list.userId !== userId) {
      throw new ForbiddenException(
        'Vous ne pouvez modifier que vos propres listes',
      );
    }

    const updatedFilmIds = list.filmIds.filter((id) => id !== filmId);

    await this.prisma.list.update({
      where: { id: listId },
      data: {
        filmIds: updatedFilmIds,
      },
    });

    return { message: 'Film retiré de la liste' };
  }
}
