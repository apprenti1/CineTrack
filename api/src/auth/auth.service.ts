import {
  Injectable,
  UnauthorizedException,
  ConflictException,
} from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { PrismaService } from '../prisma/prisma.service';
import { RegisterDto } from './dto/register.dto';
import { LoginDto } from './dto/login.dto';
import * as bcrypt from 'bcrypt';

@Injectable()
export class AuthService {
  constructor(
    private prisma: PrismaService,
    private jwtService: JwtService,
  ) {}

  async register(registerDto: RegisterDto) {
    // Vérifier si l'utilisateur existe déjà
    const existingUser = await this.prisma.user.findFirst({
      where: {
        OR: [{ email: registerDto.email }, { pseudo: registerDto.pseudo }],
      },
    });

    if (existingUser) {
      if (existingUser.email === registerDto.email) {
        throw new ConflictException('Cet email est déjà utilisé');
      }
      if (existingUser.pseudo === registerDto.pseudo) {
        throw new ConflictException('Ce pseudo est déjà utilisé');
      }
    }

    // Hasher le mot de passe
    const hashedPassword = await bcrypt.hash(registerDto.password, 10);

    // Créer l'utilisateur
    const user = await this.prisma.user.create({
      data: {
        pseudo: registerDto.pseudo,
        email: registerDto.email,
        password: hashedPassword,
      },
      select: {
        id: true,
        pseudo: true,
        email: true,
        watchlist: true,
        likes: true,
        watched: true,
        createdAt: true,
        updatedAt: true,
      },
    });

    // Générer le token JWT
    const payload = { sub: user.id, pseudo: user.pseudo };
    const token = this.jwtService.sign(payload);

    return {
      user: {
        id: user.id,
        pseudo: user.pseudo,
        email: user.email,
        watchlist: user.watchlist,
        likes: user.likes,
        watched: user.watched,
        createdAt: user.createdAt,
        updatedAt: user.updatedAt,
      },
      access_token: token,
    };
  }

  async login(loginDto: LoginDto) {
    // Trouver l'utilisateur
    const user = await this.prisma.user.findUnique({
      where: { pseudo: loginDto.pseudo },
    });

    if (!user) {
      throw new UnauthorizedException('Identifiants incorrects');
    }

    // Vérifier le mot de passe
    const isPasswordValid = await bcrypt.compare(
      loginDto.password,
      user.password,
    );

    if (!isPasswordValid) {
      throw new UnauthorizedException('Identifiants incorrects');
    }

    // Générer le token JWT
    const payload = { sub: user.id, pseudo: user.pseudo };
    const token = this.jwtService.sign(payload);

    return {
      user: {
        id: user.id,
        pseudo: user.pseudo,
        email: user.email,
        watchlist: user.watchlist,
        likes: user.likes,
        watched: user.watched,
        createdAt: user.createdAt,
        updatedAt: user.updatedAt,
      },
      access_token: token,
    };
  }
}
