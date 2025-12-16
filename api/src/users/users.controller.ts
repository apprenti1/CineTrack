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
import { UsersService } from './users.service';
import { UpdateWatchlistDto } from './dto/update-watchlist.dto';
import { UpdateProfileDto } from './dto/update-profile.dto';
import { JwtAuthGuard } from '../auth/jwt-auth.guard';

@ApiTags('users')
@Controller('users')
@UseGuards(JwtAuthGuard)
@ApiBearerAuth()
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get('profile')
  @ApiOperation({ summary: "Obtenir le profil de l'utilisateur connecté" })
  @ApiResponse({ status: 200, description: 'Profil récupéré avec succès' })
  @ApiResponse({ status: 401, description: 'Non autorisé' })
  async getProfile(@Request() req) {
    return this.usersService.getProfile(req.user.id as string);
  }

  @Put('profile')
  @ApiOperation({ summary: "Mettre à jour le profil de l'utilisateur" })
  @ApiResponse({ status: 200, description: 'Profil mis à jour avec succès' })
  @ApiResponse({ status: 401, description: 'Non autorisé' })
  async updateProfile(@Request() req, @Body() dto: UpdateProfileDto) {
    return this.usersService.updateProfile(req.user.id as string, dto);
  }

  @Post('watchlist')
  @ApiOperation({ summary: 'Ajouter un film à la watchlist' })
  @ApiResponse({ status: 200, description: 'Film ajouté à la watchlist' })
  async addToWatchlist(@Request() req, @Body() dto: UpdateWatchlistDto) {
    return this.usersService.addToWatchlist(req.user.id as string, dto.filmId);
  }

  @Delete('watchlist/:filmId')
  @ApiOperation({ summary: 'Retirer un film de la watchlist' })
  @ApiResponse({ status: 200, description: 'Film retiré de la watchlist' })
  async removeFromWatchlist(@Request() req, @Param('filmId') filmId: string) {
    return this.usersService.removeFromWatchlist(
      req.user.id as string,
      parseInt(filmId),
    );
  }

  @Post('likes')
  @ApiOperation({ summary: 'Ajouter un film aux likes' })
  @ApiResponse({ status: 200, description: 'Film ajouté aux likes' })
  async addToLikes(@Request() req, @Body() dto: UpdateWatchlistDto) {
    return this.usersService.addToLikes(req.user.id as string, dto.filmId);
  }

  @Delete('likes/:filmId')
  @ApiOperation({ summary: 'Retirer un film des likes' })
  @ApiResponse({ status: 200, description: 'Film retiré des likes' })
  async removeFromLikes(@Request() req, @Param('filmId') filmId: string) {
    return this.usersService.removeFromLikes(
      req.user.id as string,
      parseInt(filmId),
    );
  }

  @Post('watched')
  @ApiOperation({ summary: 'Ajouter un film aux films vus' })
  @ApiResponse({ status: 200, description: 'Film ajouté aux films vus' })
  async addToWatched(@Request() req, @Body() dto: UpdateWatchlistDto) {
    return this.usersService.addToWatched(req.user.id as string, dto.filmId);
  }

  @Delete('watched/:filmId')
  @ApiOperation({ summary: 'Retirer un film des films vus' })
  @ApiResponse({ status: 200, description: 'Film retiré des films vus' })
  async removeFromWatched(@Request() req, @Param('filmId') filmId: string) {
    return this.usersService.removeFromWatched(
      req.user.id as string,
      parseInt(filmId),
    );
  }
}
