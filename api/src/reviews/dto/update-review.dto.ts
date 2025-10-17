import { ApiProperty } from '@nestjs/swagger';
import { IsNumber, IsOptional, IsString, Max, Min } from 'class-validator';

export class UpdateReviewDto {
  @ApiProperty({
    example: 4.5,
    description: 'Note entre 0 et 5',
    minimum: 0,
    maximum: 5,
    required: false,
  })
  @IsNumber()
  @Min(0)
  @Max(5)
  @IsOptional()
  rating?: number;

  @ApiProperty({
    example: 'Commentaire mis à jour',
    description: 'Commentaire sur le film',
    required: false,
  })
  @IsString()
  @IsOptional()
  comment?: string;
}
