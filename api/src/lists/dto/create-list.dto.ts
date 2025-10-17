import { ApiProperty } from '@nestjs/swagger';
import { IsNotEmpty, IsString } from 'class-validator';

export class CreateListDto {
  @ApiProperty({
    example: 'Mes films préférés',
    description: 'Nom de la liste',
  })
  @IsString()
  @IsNotEmpty()
  name: string;
}
