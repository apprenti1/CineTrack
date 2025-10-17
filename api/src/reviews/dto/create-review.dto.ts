import { ApiProperty } from '@nestjs/swagger';
import {
  IsInt,
  IsNotEmpty,
  IsNumber,
  IsString,
  Max,
  Min,
} from 'class-validator';

export class CreateReviewDto {
  @ApiProperty({ example: 550, description: 'ID du film TMDB' })
  @IsInt()
  @IsNotEmpty()
  filmId: number;

  @ApiProperty({
    example: 4.5,
    description: 'Note entre 0 et 5',
    minimum: 0,
    maximum: 5,
  })
  @IsNumber()
  @Min(0)
  @Max(5)
  @IsNotEmpty()
  rating: number;

  @ApiProperty({
    example: 'Excellent film !',
    description: 'Commentaire sur le film',
  })
  @IsString()
  @IsNotEmpty()
  comment: string;
}
