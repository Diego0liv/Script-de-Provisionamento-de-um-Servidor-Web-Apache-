-- Configurações iniciais
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Criação do schema
CREATE SCHEMA IF NOT EXISTS `Apex_BD` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `Apex_BD`;

-- Tabela Perfil
CREATE TABLE IF NOT EXISTS `Perfil` (
  `id_perfil` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Usuario
CREATE TABLE IF NOT EXISTS `Usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL UNIQUE,
  `senha` VARCHAR(255) NOT NULL, -- aumentar tamanho para hash de senha
  `data_nascimento` DATE NOT NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  `perfil_id` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `idx_usuario_perfil` (`perfil_id`),
  CONSTRAINT `fk_usuario_perfil` FOREIGN KEY (`perfil_id`) REFERENCES `Perfil`(`id_perfil`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Menu
CREATE TABLE IF NOT EXISTS `Menu` (
  `id_menu` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `link` VARCHAR(100) NOT NULL,
  `icone` VARCHAR(45),
  `exibir` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Endereco
CREATE TABLE IF NOT EXISTS `Endereco` (
  `id_endereco` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45),
  `cidade` VARCHAR(45),
  `cep` VARCHAR(9) NOT NULL,
  `bairro` VARCHAR(100),
  `logradouro` VARCHAR(100),
  `uf` CHAR(2),
  `numero` VARCHAR(10),
  `complemento` VARCHAR(100),
  PRIMARY KEY (`id_endereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Fornecedor
CREATE TABLE IF NOT EXISTS `Fornecedor` (
  `id_fornecedor` INT NOT NULL AUTO_INCREMENT,
  `status` TINYINT NOT NULL DEFAULT 1,
  `razao_social` VARCHAR(100) NOT NULL,
  `nome_fantasia` VARCHAR(45) NOT NULL,
  `cnpj` VARCHAR(18) NOT NULL UNIQUE,
  `inscricao_estadual` VARCHAR(20),
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `telefone` VARCHAR(15) NOT NULL,
  `endereco_id` INT NOT NULL,
  PRIMARY KEY (`id_fornecedor`),
  INDEX `idx_fornecedor_endereco` (`endereco_id`),
  CONSTRAINT `fk_fornecedor_endereco` FOREIGN KEY (`endereco_id`) REFERENCES `Endereco`(`id_endereco`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Categoria
CREATE TABLE IF NOT EXISTS `Categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45),
  `status` TINYINT DEFAULT 1,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Produtos
CREATE TABLE IF NOT EXISTS `Produtos` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(60) NOT NULL,
  `custo` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `fornecedor_id` INT NOT NULL,
  `codigo_barras` VARCHAR(20),
  `marca` VARCHAR(45),
  `unidade_medida` VARCHAR(10),
  `data_aquisicao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `quantidade_estoque` INT NOT NULL DEFAULT 0,
  `estoque_minimo` INT NOT NULL DEFAULT 0,
  `valor_venda` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `status` TINYINT NOT NULL DEFAULT 1,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`id_produto`),
  INDEX `idx_produtos_fornecedor` (`fornecedor_id`),
  INDEX `idx_produtos_categoria` (`categoria_id`),
  CONSTRAINT `fk_produtos_fornecedor` FOREIGN KEY (`fornecedor_id`) REFERENCES `Fornecedor`(`id_fornecedor`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_produtos_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `Categoria`(`id_categoria`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Cliente
CREATE TABLE IF NOT EXISTS `Cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nome_razao` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `tipo_documento` TINYINT,
  `cpf_cnpj` CHAR(14) NOT NULL,
  `data_cadastro` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `status` TINYINT DEFAULT 1,
  PRIMARY KEY (`id_cliente`),
  UNIQUE INDEX `unq_cliente_documento` (`tipo_documento`, `cpf_cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Venda
CREATE TABLE IF NOT EXISTS `Venda` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  `status` TINYINT NOT NULL,
  `valor_total` DECIMAL(10,2) NOT NULL,
  `desconto` DECIMAL(10,2) DEFAULT 0.00,
  `observacao` VARCHAR(255),
  `data_venda` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_venda`),
  INDEX `idx_venda_usuario` (`usuario_id`),
  INDEX `idx_venda_cliente` (`cliente_id`),
  CONSTRAINT `fk_venda_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `Usuario`(`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_venda_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `Cliente`(`id_cliente`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela FormasPagamento
CREATE TABLE IF NOT EXISTS `Formas_Pagamento` (
  `id_forma_pagamento` INT NOT NULL AUTO_INCREMENT,
  `descricao` TEXT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `status` TINYINT NOT NULL DEFAULT 0,
  `tipo_pagamento` TINYINT NOT NULL,
  PRIMARY KEY (`id_forma_pagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Produto_Venda
CREATE TABLE IF NOT EXISTS `Produto_Venda` (
  `produto_id` INT NOT NULL,
  `venda_id` INT NOT NULL,
  `quantidade` INT NOT NULL,
  `preco_unitario` DECIMAL(10,2) NOT NULL,
  `preco_total` DECIMAL(10,2) AS (`quantidade` * `preco_unitario`) STORED,
  PRIMARY KEY (`produto_id`, `venda_id`),
  INDEX `idx_produto_venda_venda` (`venda_id`),
  CONSTRAINT `fk_produto_venda_produto` FOREIGN KEY (`produto_id`) REFERENCES `Produtos`(`id_produto`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_produto_venda_venda` FOREIGN KEY (`venda_id`) REFERENCES `Venda`(`id_venda`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Venda_FormasPagamento
CREATE TABLE IF NOT EXISTS `Venda_FormasPagamento` (
  `venda_id` INT NOT NULL,
  `forma_pagamento_id` INT NOT NULL,
  `valor_pago` DECIMAL(10,2),
  `numero_parcelas` TINYINT,
  PRIMARY KEY (`venda_id`, `forma_pagamento_id`),
  INDEX `idx_venda_forma_pagamento_forma` (`forma_pagamento_id`),
  CONSTRAINT `fk_venda_forma_venda` FOREIGN KEY (`venda_id`) REFERENCES `Venda`(`id_venda`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_venda_forma_pagamento` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `Formas_Pagamento`(`id_forma_pagamento`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Menu_Perfil
CREATE TABLE IF NOT EXISTS `Menu_Perfil` (
  `menu_id` INT NOT NULL,
  `perfil_id` INT NOT NULL,
  PRIMARY KEY (`menu_id`, `perfil_id`),
  INDEX `idx_menu_perfil_perfil` (`perfil_id`),
  INDEX `idx_menu_perfil_menu` (`menu_id`),
  CONSTRAINT `fk_menu_perfil_menu` FOREIGN KEY (`menu_id`) REFERENCES `Menu`(`id_menu`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_menu_perfil_perfil` FOREIGN KEY (`perfil_id`) REFERENCES `Perfil`(`id_perfil`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Cliente_Endereco
CREATE TABLE IF NOT EXISTS `Cliente_Endereco` (
  `cliente_id` INT NOT NULL,
  `endereco_id` INT NOT NULL,
  PRIMARY KEY (`cliente_id`, `endereco_id`),
  INDEX `idx_cliente_endereco_endereco` (`endereco_id`),
  INDEX `idx_cliente_endereco_cliente` (`cliente_id`),
  CONSTRAINT `fk_cliente_endereco_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `Cliente`(`id_cliente`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cliente_endereco_endereco` FOREIGN KEY (`endereco_id`) REFERENCES `Endereco`(`id_endereco`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Restaurar configurações
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;