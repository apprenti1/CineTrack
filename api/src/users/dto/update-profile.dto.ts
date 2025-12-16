import { ApiProperty } from '@nestjs/swagger';
import { IsEmail, IsOptional, IsString } from 'class-validator';

export class UpdateProfileDto {
  @ApiProperty({ description: 'Le nouveau pseudo de l\'utilisateur', required: false })
  @IsOptional()
  @IsString()
  pseudo?: string;

  @ApiProperty({ description: 'Le nouvel email de l\'utilisateur', required: false })
  @IsOptional()
  @IsEmail()
  email?: string;
}
