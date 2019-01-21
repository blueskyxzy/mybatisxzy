CREATE TABLE `user` (
  `id` varchar(64) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `test`.`user` (`id`, `password`, `name`) VALUES ('1', '123456', 'xzy');