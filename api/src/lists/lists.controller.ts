import { Controller, Get, Post, Put, Delete, Body, Param, UseGuards, Request } from '@nestjs/common';
import { ApiTags, ApiOperation, ApiResponse, ApiBearerAuth } from '@nestjs/swagger';
import { ListsService } from './lists.service';
import { CreateListDto } from './dto/create-list.dto';
import { UpdateListDto } from './dto/update-list.dto';
import { ManageFilmsDto } from './dto/manage-films.dto';
import { JwtAuthGuard } from '../auth/jwt-auth.guard';

@ApiTags('lists')
@Controller('lists')
export class ListsController {
  constructor(private readonly listsService: ListsService) {}

  @Post()
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Créer une nouvelle liste' })
  @ApiResponse({ status: 201, description: 'Liste créée avec succès' })
  @ApiResponse({ status: 401, description: 'Non autorisé' })
  async create(@Request() req, @Body() createListDto: CreateListDto) {
    return this.listsService.create(req.user.id, createListDto);
  }

  @Get('my-lists')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Obtenir toutes mes listes' })
  @ApiResponse({ status: 200, description: 'Listes récupérées avec succès' })
  async findMyLists(@Request() req) {
    return this.listsService.findByUser(req.user.id);
  }

  @Get(':id')
  @ApiOperation({ summary: 'Obtenir une liste par son ID' })
  @ApiResponse({ status: 200, description: 'Liste récupérée avec succès' })
  @ApiResponse({ status: 404, description: 'Liste non trouvée' })
  async findOne(@Param('id') id: string) {
    return this.listsService.findOne(id);
  }

  @Put(':id')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Modifier une liste' })
  @ApiResponse({ status: 200, description: 'Liste modifiée avec succès' })
  @ApiResponse({ status: 403, description: 'Vous ne pouvez modifier que vos propres listes' })
  @ApiResponse({ status: 404, description: 'Liste non trouvée' })
  async update(@Param('id') id: string, @Request() req, @Body() updateListDto: UpdateListDto) {
    return this.listsService.update(id, req.user.id, updateListDto);
  }

  @Delete(':id')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Supprimer une liste' })
  @ApiResponse({ status: 200, description: 'Liste supprimée avec succès' })
  @ApiResponse({ status: 403, description: 'Vous ne pouvez supprimer que vos propres listes' })
  @ApiResponse({ status: 404, description: 'Liste non trouvée' })
  async remove(@Param('id') id: string, @Request() req) {
    return this.listsService.remove(id, req.user.id);
  }

  @Post(':id/films')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Ajouter un film à une liste' })
  @ApiResponse({ status: 200, description: 'Film ajouté à la liste' })
  @ApiResponse({ status: 403, description: 'Vous ne pouvez modifier que vos propres listes' })
  @ApiResponse({ status: 404, description: 'Liste non trouvée' })
  async addFilm(@Param('id') id: string, @Request() req, @Body() dto: ManageFilmsDto) {
    return this.listsService.addFilm(id, req.user.id, dto.filmId);
  }

  @Delete(':id/films/:filmId')
  @UseGuards(JwtAuthGuard)
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Retirer un film d\'une liste' })
  @ApiResponse({ status: 200, description: 'Film retiré de la liste' })
  @ApiResponse({ status: 403, description: 'Vous ne pouvez modifier que vos propres listes' })
  @ApiResponse({ status: 404, description: 'Liste non trouvée' })
  async removeFilm(@Param('id') id: string, @Request() req, @Param('filmId') filmId: string) {
    return this.listsService.removeFilm(id, req.user.id, parseInt(filmId));
  }
}
