
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
  `commerce_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
    FOREIGN KEY (`order_id`)
    REFERENCES `marketapp`.`shopping_cart` (`id`),
    FOREIGN KEY (`product_id`)
    REFERENCES `marketapp`.`product` (`id`));
