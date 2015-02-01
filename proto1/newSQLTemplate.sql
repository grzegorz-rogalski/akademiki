-- MySQL Script generated by MySQL Workbench
-- 01/12/15 23:11:08
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Stuff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Stuff` (
  `idStuff` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Status` VARCHAR(45) NULL,
  PRIMARY KEY (`idStuff`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Room` (
  `idRoom` INT NOT NULL,
  `Capacity` INT NOT NULL,
  `isFree` TINYINT(1) NULL,
  `isResidential` TINYINT(1) NULL,
  `Stuff_idStuff` INT NOT NULL,
  PRIMARY KEY (`idRoom`),
  INDEX `fk_Room_Stuff1_idx` (`Stuff_idStuff` ASC),
  CONSTRAINT `fk_Room_Stuff1`
    FOREIGN KEY (`Stuff_idStuff`)
    REFERENCES `mydb`.`Stuff` (`idStuff`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`People`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`People` (
  `idPeople` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Nick` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  `Room_idRoom` INT NOT NULL,
  PRIMARY KEY (`idPeople`),
  INDEX `fk_People_Room_idx` (`Room_idRoom` ASC),
  CONSTRAINT `fk_People_Room`
    FOREIGN KEY (`Room_idRoom`)
    REFERENCES `mydb`.`Room` (`idRoom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Event` (
  `idEvent` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Place` VARCHAR(45) NULL,
  `StartDate` DATE NULL,
  `EndDate` DATE NULL,
  `Description` VARCHAR(45) NULL,
  `People_idPeople` INT NOT NULL,
  PRIMARY KEY (`idEvent`),
  INDEX `fk_Event_People1_idx` (`People_idPeople` ASC),
  CONSTRAINT `fk_Event_People1`
    FOREIGN KEY (`People_idPeople`)
    REFERENCES `mydb`.`People` (`idPeople`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Council`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Council` (
  `idCouncil` INT NOT NULL,
  `People_idPeople` INT NOT NULL,
  PRIMARY KEY (`idCouncil`),
  INDEX `fk_Council_People1_idx` (`People_idPeople` ASC),
  CONSTRAINT `fk_Council_People1`
    FOREIGN KEY (`People_idPeople`)
    REFERENCES `mydb`.`People` (`idPeople`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Akademik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Akademik` (
  `idAkademik` INT NOT NULL,
  `People_idPeople` INT NOT NULL,
  `Council_idCouncil` INT NOT NULL,
  `Room_idRoom` INT NOT NULL,
  PRIMARY KEY (`idAkademik`),
  INDEX `fk_Akademik_People1_idx` (`People_idPeople` ASC),
  INDEX `fk_Akademik_Council1_idx` (`Council_idCouncil` ASC),
  INDEX `fk_Akademik_Room1_idx` (`Room_idRoom` ASC),
  CONSTRAINT `fk_Akademik_People1`
    FOREIGN KEY (`People_idPeople`)
    REFERENCES `mydb`.`People` (`idPeople`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Akademik_Council1`
    FOREIGN KEY (`Council_idCouncil`)
    REFERENCES `mydb`.`Council` (`idCouncil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Akademik_Room1`
    FOREIGN KEY (`Room_idRoom`)
    REFERENCES `mydb`.`Room` (`idRoom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

