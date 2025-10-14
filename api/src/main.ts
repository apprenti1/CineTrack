import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // Configuration du préfixe global /api pour toutes les routes sauf /doc et /health
  app.setGlobalPrefix('api', {
    exclude: ['health', 'doc'],
  });

  // Configuration de Swagger sur /doc
  const config = new DocumentBuilder()
    .setTitle('CineTrack API')
    .setDescription('API de gestion de films et séries')
    .setVersion('1.0')
    .addTag('cinetrack')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('doc', app, document);

  await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
