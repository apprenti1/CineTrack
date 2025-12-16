-- AlterTable
ALTER TABLE "users" ADD COLUMN     "watched" INTEGER[] DEFAULT ARRAY[]::INTEGER[];
