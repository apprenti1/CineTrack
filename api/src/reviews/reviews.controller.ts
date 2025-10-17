/* eslint-disable @typescript-eslint/no-unsafe-member-access */
import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Body,
  Param,
  UseGuards,
  Request,
} from '@nestjs/common';
import {
  ApiTags,
  ApiOperation,
  ApiResponse,
  ApiBearerAuth,
} from '@nestjs/swagger';
import { ReviewsService } from './reviews.service';
import { CreateReviewDto } from './dto/create-review.dto';
import { UpdateReviewDto } from './dto/update-review.dto';
import { JwtAuthGuard } from '../auth/jwt-auth.guard';

@ApiTags('reviews')
@Controller('reviews')
export class ReviewsController {
  constructor(private readonly reviewsService: ReviewsService) {}

  @Post()
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Créer un avis sur un film' })
  @ApiResponse({ status: 201, description: 'Avis créé avec succès' })
  @ApiResponse({
    status: 409,
    description: 'Vous avez déjà laissé un avis pour ce film',
  })
  @ApiResponse({ status: 401, description: 'Non autorisé' })
  async create(@Request() req, @Body() createReviewDto: CreateReviewDto) {
    return this.reviewsService.create(req.user.id as string, createReviewDto);
  }

  @Get('film/:filmId')
  @ApiOperation({ summary: "Obtenir tous les avis d'un film" })
  @ApiResponse({
    status: 200,
    description: 'Liste des avis récupérée avec succès',
  })
  async findByFilm(@Param('filmId') filmId: string) {
    return this.reviewsService.findByFilm(parseInt(filmId));
  }

  @Get('user/:userId')
  @ApiOperation({ summary: "Obtenir tous les avis d'un utilisateur" })
  @ApiResponse({
    status: 200,
    description: 'Liste des avis récupérée avec succès',
  })
  async findByUser(@Param('userId') userId: string) {
    return this.reviewsService.findByUser(userId);
  }

  @Put(':id')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Modifier son avis' })
  @ApiResponse({ status: 200, description: 'Avis modifié avec succès' })
  @ApiResponse({
    status: 403,
    description: 'Vous ne pouvez modifier que vos propres avis',
  })
  @ApiResponse({ status: 404, description: 'Avis non trouvé' })
  async update(
    @Param('id') id: string,
    @Request() req,
    @Body() updateReviewDto: UpdateReviewDto,
  ) {
    return this.reviewsService.update(
      id,
      req.user.id as string,
      updateReviewDto,
    );
  }

  @Delete(':id')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Supprimer son avis' })
  @ApiResponse({ status: 200, description: 'Avis supprimé avec succès' })
  @ApiResponse({
    status: 403,
    description: 'Vous ne pouvez supprimer que vos propres avis',
  })
  @ApiResponse({ status: 404, description: 'Avis non trouvé' })
  async remove(@Param('id') id: string, @Request() req) {
    return this.reviewsService.remove(id, req.user.id);
  }
}
