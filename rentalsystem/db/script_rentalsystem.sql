DROP SCHEMA IF EXISTS `rentalsystem`;
CREATE SCHEMA IF NOT EXISTS `rentalsystem` DEFAULT CHARACTER SET utf8;
USE `rentalsystem`;

DROP TABLE IF EXISTS `rentalsystem`.`type`;
CREATE TABLE `rentalsystem`.`type` (
  `type_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  `days` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `rentalsystem`.`game`;
CREATE TABLE `rentalsystem`.`game` (
  `game_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type_id` bigint(11) NOT NULL,
  FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`),
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `rentalsystem`.`user`;
CREATE TABLE `rentalsystem`.`user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `age` int(3) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `rentalsystem`.`game_user`;
CREATE TABLE `rentalsystem`.`game_user` (
    `id` bigint(11) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(11) NOT NULL,
    `game_id` bigint(11) NOT NULL,
    `rental_start_date` date NOT NULL,
    `rental_end_date` date NOT NULL,
	`points` int(11) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (`id`)
);

insert into `rentalsystem`.`user` values(1, 'Daniel', 'Vasquez', 24);
insert into `rentalsystem`.`user` values(2, 'Julio', 'Medina', 20);
insert into `rentalsystem`.`user` values(3, 'Monica', 'Hurtado', 41);

insert into `rentalsystem`.`type` values(1, 'New releases', 0, 4);
insert into `rentalsystem`.`type` values(2, 'Standard games', 3, 3);
insert into `rentalsystem`.`type` values(3, 'Classic games', 5, 3);

insert into `rentalsystem`.`game` values(1, 'No Man’s Sky', 1);
insert into `rentalsystem`.`game` values(2, 'Resident Evil 6', 2);
insert into `rentalsystem`.`game` values(3, 'Fallout 4', 2);
insert into `rentalsystem`.`game` values(4, 'Fallout 3', 3);
insert into `rentalsystem`.`game` values(5, 'GTA', 2);

insert into `rentalsystem`.`game_user` values(1, 1, 1, '2019-09-16', '2019-09-17', 2);
insert into `rentalsystem`.`game_user` values(2, 2, 2, '2019-09-16', '2019-09-21', 1);
insert into `rentalsystem`.`game_user` values(3, 1, 3, '2019-09-16', '2019-09-18', 1);
insert into `rentalsystem`.`game_user` values(4, 2, 4, '2019-09-16', '2019-09-23', 1);

commit;
