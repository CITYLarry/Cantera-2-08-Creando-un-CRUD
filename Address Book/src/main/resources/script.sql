-- -----------------------------------------------------------
-- Create the required tables with the necessary relationships
-- -----------------------------------------------------------
-- Schema store
CREATE SCHEMA IF NOT EXISTS address_book DEFAULT CHARACTER SET utf8 ;
USE address_book;

-- Table address_book_list
CREATE TABLE IF NOT EXISTS address_book_list (
    ab_id INT NOT NULL AUTO_INCREMENT,
    ab_name VARCHAR(80) NOT NULL,
    ab_phone VARCHAR(45) NULL,
    ab_mail VARCHAR(120) NULL,
    ab_bday DATE NULL,
    ab_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ab_updated_at DATETIME NULL,
    ab_deleted_at DATETIME NULL,
    PRIMARY KEY (ab_id),
    UNIQUE INDEX ab_id_UNIQUE (ab_id ASC) VISIBLE,
    UNIQUE INDEX ab_phone_UNIQUE (ab_phone ASC) VISIBLE,
    UNIQUE INDEX ab_mail_UNIQUE (ab_mail ASC) VISIBLE
) ENGINE = InnoDB;
