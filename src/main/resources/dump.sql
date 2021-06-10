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
-- ENGINE = InnoDB
-- AUTO_INCREMENT = 4
-- DEFAULT CHARACTER SET = utf8mb4
-- COLLATE = utf8mb4_0900_ai_ci;


-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;