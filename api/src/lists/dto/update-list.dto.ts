import { ApiProperty } from '@nestjs/swagger';
import { IsOptional, IsString } from 'class-validator';

export class UpdateListDto {
  @ApiProperty({ example: 'Nouveau nom de liste', description: 'Nom de la liste', required: false })
  @IsString()
  @IsOptional()
  name?: string;
}
