import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { ValidationPipe } from '@nestjs/common';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // Activation de la validation globale
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true,
      transform: true,
    }),
  );

  // Configuration du préfixe global /api pour toutes les routes sauf /doc et /health
  app.setGlobalPrefix('api', {
    exclude: ['health', 'doc'],
  });

  // Configuration de Swagger sur /doc
  const config = new DocumentBuilder()
    .setTitle('CineTrack API')
    .setDescription('API de gestion de films et séries')
    .setVersion('1.0')
    .addTag('auth')
    .addTag('users')
    .addTag('reviews')
    .addTag('lists')
    .addTag('health')
    .addBearerAuth()
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('doc', app, document);

  await app.listen(process.env.PORT ?? 3000);
}
bootstrap().catch((err) => {
  console.error("Erreur lors du démarrage de l'application :", err);
});
