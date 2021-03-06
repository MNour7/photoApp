-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 20 jan. 2019 à 22:36
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `photoshop`
--

-- --------------------------------------------------------

--
-- Structure de la table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE IF NOT EXISTS `app_user` (
  `app_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` text COLLATE utf8_unicode_ci,
  `school_school_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`app_user_id`),
  KEY `FK3kfol3bd24algpkt860l23xoj` (`school_school_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `app_user`
--

INSERT INTO `app_user` (`app_user_id`, `firstname`, `lastname`, `email`, `password`, `school_school_id`) VALUES
(1, 'Davy', 'UNG', 'udavy', '$2a$10$yfJmmvWTsixTQ27k/WSGU.UxKiq0H2gXZoV0Qb2PeFf1FEPSE7666', NULL),
(2, 'Aboubacar', 'KADRI', 'kaboubacar', '$2a$10$yfJmmvWTsixTQ27k/WSGU.UxKiq0H2gXZoV0Qb2PeFf1FEPSE7666', NULL),
(3, 'Mahamadou', 'NOUR', 'mnour', '$2a$10$yfJmmvWTsixTQ27k/WSGU.UxKiq0H2gXZoV0Qb2PeFf1FEPSE7666', NULL),
(4, 'Sacha', 'COLOMA', 'csacha', '$2a$10$yfJmmvWTsixTQ27k/WSGU.UxKiq0H2gXZoV0Qb2PeFf1FEPSE7666', NULL),
(5, 'Solo', 'NIANFO', 'nsolo', '$2a$10$BTJr1eSEFKdGvBISm38nPuxiEDobqDaQBHc/k0ImliU8/oq9Zb2RS', NULL),
(7, 'Nourdati', 'DJAOUHAR', 'dnour', '$2a$10$sKZTBbckg.GlhrcjM/.jDOVh2EdLRxY1NOcp/5kYrj4s.jEeiJABy', NULL),
(12, 'Yacine', 'Mokarani', 'myacine', '$2a$10$RfpQ3iEQRVaFwaaL.dq89uCar4DZHS7Z17T5jw7y09X8zYwwS3Gf.', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `child`
--

DROP TABLE IF EXISTS `child`;
CREATE TABLE IF NOT EXISTS `child` (
  `child_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`child_id`),
  KEY `FKem0ikl8nv4122btpk0disc3sg` (`parent_id`),
  KEY `FKq507ylm14x4x4ksjcl7qjm8nm` (`class_id`),
  KEY `FKidgnlru9cmo5ygdcynqcyqccl` (`school_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `child`
--

INSERT INTO `child` (`child_id`, `firstname`, `lastname`, `class_id`, `parent_id`, `school_id`) VALUES
(1, 'Kevin', 'COLOMA', 1, 4, 1),
(2, 'Moussa', 'NIANFO', 1, 5, 1),
(3, 'Emilie', 'COLOMA', 2, 4, 1),
(4, 'Fati', 'NIANFO', 4, 5, 1),
(5, 'Alpha', 'NIANFO', 5, 5, 1),
(6, 'Lea', 'COLOMA', 10, 4, 2),
(8, 'Salim', 'DJAOUHAR', 1, 7, 1),
(14, 'Ahmed', 'MOKRANI', 1, 12, 1);

-- --------------------------------------------------------

--
-- Structure de la table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `FK96x0psi3ip7rge1n5ubwbhe3y` (`school_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `class`
--

INSERT INTO `class` (`class_id`, `name`, `school_id`) VALUES
(1, 'CP', 1),
(2, 'CE1', 1),
(3, 'CE2', 1),
(4, 'CM1', 1),
(5, 'CM2', 1),
(6, 'CP', 2),
(7, 'CE1', 2),
(8, 'CE2', 2),
(9, 'CM1', 2),
(10, 'CM2', 2),
(15, 'CI', 1);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(17),
(17),
(17),
(17),
(17),
(17),
(17),
(17);

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

DROP TABLE IF EXISTS `photo`;
CREATE TABLE IF NOT EXISTS `photo` (
  `photo_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_take` date DEFAULT NULL,
  `type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `child_id` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` tinyblob,
  PRIMARY KEY (`photo_id`),
  KEY `FK35hwl7xxgfcvep9g5boysyc0q` (`author_id`),
  KEY `FKc87peaci4l7eb8k08soaretre` (`child_id`),
  KEY `FK2pylbbqepmki54jh1hxgr3xb8` (`class_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `photo`
--

INSERT INTO `photo` (`photo_id`, `name`, `date_take`, `type`, `class_id`, `child_id`, `author_id`, `path`, `image`) VALUES
(6, 'boxe', '2019-01-19', 'solo', NULL, 1, 1, '52c125b9-fc4d-4f89-8606-9e645a5201c6.jpg', NULL),
(3, 'process odk', '2019-01-19', 'solo', NULL, 2, 1, '25a521dc-b32b-44bd-91d7-3e64d3c88c4f.jpg', NULL),
(4, 'Hegeprine', '2019-01-19', 'class', 10, NULL, 1, '55bdb78a-7e3d-4bf5-ba63-ff574142711e.jpg', NULL),
(5, 'foot_man', '2019-01-19', 'class', 1, NULL, 1, 'f254e4ca-fb51-4c54-9e6e-906f7efa53f6.jpg', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `photo_order`
--

DROP TABLE IF EXISTS `photo_order`;
CREATE TABLE IF NOT EXISTS `photo_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKs39rkgk7ggj4of9svaw1wlaql` (`parent_id`),
  KEY `FKpanxssijv2d8k7r9vilq3jyyc` (`photo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `photo_order`
--

INSERT INTO `photo_order` (`order_id`, `photo_id`, `parent_id`, `quantity`, `order_date`) VALUES
(9, NULL, 5, 1, '2019-01-20'),
(10, NULL, 5, 1, '2019-01-20'),
(11, 5, 5, 1, '2019-01-20'),
(16, 5, 5, 1, '2019-01-20');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `tile` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`role_id`, `tile`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_PHOTO'),
(3, 'ROLE_PARENT');

-- --------------------------------------------------------

--
-- Structure de la table `school`
--

DROP TABLE IF EXISTS `school`;
CREATE TABLE IF NOT EXISTS `school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_price` double DEFAULT NULL,
  `solo_price` double DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`school_id`),
  KEY `FKomloom55jodku6yl317snx8x7` (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `school`
--

INSERT INTO `school` (`school_id`, `name`, `group_price`, `solo_price`, `admin_id`) VALUES
(1, 'Metare', 8, 10, 2),
(2, 'Cotonne', 7, 9, 3),
(3, 'Soleil', 5, 2, 4);

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `app_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `FKj16wg2x08hwytvgys4y9idf4b` (`app_user_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `role_id`, `app_user_id`) VALUES
(1, 2, 1),
(2, 1, 2),
(3, 1, 3),
(4, 3, 4),
(5, 3, 5),
(13, 3, 12);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
