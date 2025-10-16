import { ApiProperty } from '@nestjs/swagger';
import { IsInt, IsNotEmpty } from 'class-validator';

export class ManageFilmsDto {
  @ApiProperty({ example: 550, description: 'ID du film TMDB' })
  @IsInt()
  @IsNotEmpty()
  filmId: number;
}
