/*
SQLyog Community Edition- MySQL GUI v6.15
MySQL - 5.1.42-community : Database - sispharma
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `sispharma`;

USE `sispharma`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `entradas` */

DROP TABLE IF EXISTS `entradas`;

CREATE TABLE `entradas` (
  `chave_entradas` int(11) NOT NULL AUTO_INCREMENT,
  `chave_produtos` int(11) NOT NULL,
  `chave_usuarios` int(11) NOT NULL,
  `data` varchar(30) DEFAULT NULL,
  `qtd_entrada` double DEFAULT NULL,
  PRIMARY KEY (`chave_entradas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `entradas` */

/*Table structure for table `itens_venda` */

DROP TABLE IF EXISTS `itens_venda`;

CREATE TABLE `itens_venda` (
  `chave_itens_venda` int(11) NOT NULL AUTO_INCREMENT,
  `chave_vendas` int(11) NOT NULL,
  `chave_produtos` int(11) NOT NULL,
  `qtd_itens` double DEFAULT NULL,
  PRIMARY KEY (`chave_itens_venda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `itens_venda` */

/*Table structure for table `laboratorios` */

DROP TABLE IF EXISTS `laboratorios`;

CREATE TABLE `laboratorios` (
  `chave_lab` int(11) NOT NULL AUTO_INCREMENT,
  `nome_lab` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`chave_lab`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `laboratorios` */

/*Table structure for table `produtos` */

DROP TABLE IF EXISTS `produtos`;

CREATE TABLE `produtos` (
  `chave_produtos` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` int(11) DEFAULT NULL,
  `codigo_barra` varchar(15) DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `laboratorio` int(11) DEFAULT NULL,
  `unidade` varchar(10) DEFAULT NULL,
  `qtd_produtos` double DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `validade` varchar(10) DEFAULT NULL,
  `qtd_minima` double DEFAULT NULL,
  PRIMARY KEY (`chave_produtos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `produtos` */

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `chave_usuarios` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `login` varchar(5) DEFAULT NULL,
  `senha` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`chave_usuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `usuarios` */

insert  into `usuarios`(`chave_usuarios`,`nome`,`tipo`,`login`,`senha`) values (1,'ADMIN',1,'admin','admin');

/*Table structure for table `vendas` */

DROP TABLE IF EXISTS `vendas`;

CREATE TABLE `vendas` (
  `chave_vendas` int(11) NOT NULL AUTO_INCREMENT,
  `chave_usuarios` int(11) NOT NULL,
  `data` varchar(30) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `valor_pago` double DEFAULT NULL,
  `tipo_venda` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`chave_vendas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vendas` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
