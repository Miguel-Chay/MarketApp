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
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`customer` (
  `user_id` INT NOT NULL,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `cellphone` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(50) NOT NULL,
  `payment_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`address` (
  `id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `crossing` VARCHAR(255) NOT NULL,
  `suburb` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_id` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `marketapp`.`customer` (`user_id`))
ENGINE = InnoDB
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
  INDEX `fk_user_id` (`user_id` ASC) VISIBLE,
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
  INDEX `fk_user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `id`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`commerce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`commerce` (
  `id` INT NOT NULL,
  `salesman_id` INT NOT NULL,
  `commercial_name` VARCHAR(255) NOT NULL,
  `rfc` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `logo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_salesman_id` (`salesman_id` ASC) VISIBLE,
  CONSTRAINT `salesman_id`
    FOREIGN KEY (`salesman_id`)
    REFERENCES `marketapp`.`salesman` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketapp`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`product` (
  `id` INT NOT NULL,
  `commerce_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `stock` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_commerce_id` (`commerce_id` ASC) INVISIBLE,
  CONSTRAINT `commerce_id`
    FOREIGN KEY (`commerce_id`)
    REFERENCES `marketapp`.`commerce` (`salesman_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketapp`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`order` (
  `id` INT NOT NULL,
  `deliveryman_id` INT NULL,
  `customer_id` INT NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `order_date` DATETIME NULL,
  `delivered_date` DATETIME NULL,
  `total` DOUBLE NOT NULL,
  `payment_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `deliveryman_id` (`deliveryman_id` ASC) VISIBLE,
  INDEX `customer_id` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `deliveryman_id`
    FOREIGN KEY (`deliveryman_id`)
    REFERENCES `marketapp`.`deliveryman` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `marketapp`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketapp`.`order_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`order_detail` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `subtotal` DOUBLE NOT NULL,
  `finished` TINYINT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `fk_product_id` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `marketapp`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `marketapp`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
