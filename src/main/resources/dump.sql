
CREATE SCHEMA IF NOT EXISTS `marketapp` ;
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
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `marketapp`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`customer` (
  `user_id` INT NOT NULL auto_increment,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `cellphone` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(50) NOT NULL,
  `payment_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`),
	FOREIGN KEY (`user_id`) REFERENCES `marketapp`.`user` (`id`));

-- -----------------------------------------------------
-- Table `marketapp`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`address` (
  `id` INT NOT NULL auto_increment,
  `customer_id` INT NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `crossing` VARCHAR(255) NOT NULL,
  `suburb` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
	FOREIGN KEY (`customer_id`) REFERENCES `marketapp`.`customer` (`user_id`));


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
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`));

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
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`));


-- -----------------------------------------------------
-- Table `marketapp`.`commerce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`commerce` (
  `id` INT NOT NULL auto_increment,
  `salesman_id` INT NOT NULL,
  `commercial_name` VARCHAR(255) NOT NULL,
  `rfc` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `logo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`salesman_id`) REFERENCES `marketapp`.`salesman` (`user_id`));


-- -----------------------------------------------------
-- Table `marketapp`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`product` (
  `id` INT NOT NULL auto_increment,
  `commerce_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `stock` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`commerce_id`)
    REFERENCES `marketapp`.`commerce` (`id`)
   );



-- -----------------------------------------------------
-- Table `marketapp`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`shopping_cart` (
  `id` INT NOT NULL auto_increment,
  `deliveryman_id` INT NULL,
  `customer_id` INT NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `order_date` DATETIME NULL,
  `delivered_date` DATETIME NULL,
  `total` DOUBLE NOT NULL,
  `payment_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`deliveryman_id`)
    REFERENCES `marketapp`.`deliveryman` (`user_id`),
    FOREIGN KEY (`customer_id`)
    REFERENCES `marketapp`.`customer` (`user_id`));


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
    FOREIGN KEY (`order_id`)
    REFERENCES `marketapp`.`shopping_cart` (`id`),
    FOREIGN KEY (`product_id`)
    REFERENCES `marketapp`.`product` (`id`));



-- ============================================================================================================
--                                       BASE 2
-- ============================================================================================================

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
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`customer` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `cellphone` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(50) NOT NULL,
  `payment_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `customer_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `crossing` VARCHAR(255) NOT NULL,
  `suburb` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `customer_id` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `address_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `marketapp`.`customer` (`user_id`))
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
  CONSTRAINT `salesman_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`commerce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`commerce` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `salesman_id` INT NOT NULL,
  `commercial_name` VARCHAR(255) NOT NULL,
  `rfc` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `logo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `salesman_id` (`salesman_id` ASC) VISIBLE,
  CONSTRAINT `commerce_ibfk_1`
    FOREIGN KEY (`salesman_id`)
    REFERENCES `marketapp`.`salesman` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
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
  CONSTRAINT `deliveryman_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketapp`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`shopping_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`shopping_cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `deliveryman_id` INT NULL DEFAULT NULL,
  `customer_id` INT NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `order_date` DATETIME NULL DEFAULT NULL,
  `delivered_date` DATETIME NULL DEFAULT NULL,
  `total` DOUBLE NOT NULL,
  `payment_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `deliveryman_id` (`deliveryman_id` ASC) VISIBLE,
  INDEX `customer_id` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `shopping_cart_ibfk_1`
    FOREIGN KEY (`deliveryman_id`)
    REFERENCES `marketapp`.`deliveryman` (`user_id`),
  CONSTRAINT `shopping_cart_ibfk_2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `marketapp`.`customer` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `commerce_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `stock` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `commerce_id` (`commerce_id` ASC) VISIBLE,
  CONSTRAINT `product_ibfk_1`
    FOREIGN KEY (`commerce_id`)
    REFERENCES `marketapp`.`commerce` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketapp`.`order_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketapp`.`order_detail` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `subtotal` DOUBLE NOT NULL,
  `finished` TINYINT NOT NULL,
  `commerce_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `product_id` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_detail_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `marketapp`.`shopping_cart` (`id`),
  CONSTRAINT `order_detail_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `marketapp`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
