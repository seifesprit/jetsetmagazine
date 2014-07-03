DROP TABLE IF EXISTS `actuality`;
CREATE TABLE `actuality` (
  `id_auto` int(8) NOT NULL AUTO_INCREMENT,
  `titre` varchar(20) DEFAULT NULL,
  `description` text,
  `date_ajout` datetime DEFAULT NULL,
  `date_redaction` datetime DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `valide` tinyint(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL,
  `id_leader` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_auto`),
  KEY `id_category` (`id_category`)
);
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id_auto` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_auto`)
);
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id_actuality` int(8) NOT NULL,
  `type` varchar(10) NOT NULL,
  `comment` text NOT NULL,
  `id_subscriber` int(11) NOT NULL,
  PRIMARY KEY (`id_actuality`,`id_subscriber`),
  KEY `id_subscriber` (`id_subscriber`)
);
DROP TABLE IF EXISTS `subscriber`;
CREATE TABLE `subscriber` (
  `id_auto` int(8) NOT NULL AUTO_INCREMENT,
  `nom_prenom` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_auto`)
);
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_auto` int(8) NOT NULL AUTO_INCREMENT,
  `nom_prenom` varchar(100) DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `id_category` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_auto`)
);
