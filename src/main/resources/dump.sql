-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema marketapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema marketapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `marketapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `marketapp` ;

-- -----------------------------------------------------
-- Table `marketapp`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`deliveryman`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`deliveryman` (
  `user_id` INT NOT NULL,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `cellphone` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(50) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `vehicle` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`salesman`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`salesman` (
  `user_id` INT NOT NULL,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `cellphone` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(45) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `id`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
