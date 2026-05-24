-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2026 at 11:55 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pharmacy_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `medicines`
--

CREATE TABLE `medicines` (
  `medicine_id` int(11) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `medicine_type` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity_in_stock` int(11) DEFAULT NULL,
  `reorder_level` int(11) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `medicines`
--

INSERT INTO `medicines` (`medicine_id`, `name`, `company`, `medicine_type`, `price`, `quantity_in_stock`, `reorder_level`, `expiry_date`, `supplier_id`) VALUES
(2, 'CorenzaC', 'Ceema', 'Tablet', 100.00, 4, 5, '2026-03-31', 1),
(3, 'Grandpa', 'Ceema', 'Tablet', 100.00, 25, 15, '2027-04-07', 1),
(5, 'Silpayne', 'Aspen', 'Syrup', 57.50, 90, 20, '2029-04-06', 1),
(6, 'Benylin', 'Adcock', 'Tablet', 90.00, 213, 30, '2028-06-05', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sale_id` int(11) NOT NULL,
  `sale_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_amount` decimal(10,2) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sale_id`, `sale_date`, `total_amount`, `user_id`) VALUES
(6, '2026-03-24 10:02:13', 1500.00, 2),
(7, '2026-03-27 17:13:31', 8500.00, 2),
(30, '2026-04-04 20:25:53', 900.00, 2),
(31, '2026-04-05 08:26:02', 700.00, 2),
(32, '2026-04-05 08:27:42', 300.00, 2),
(33, '2026-04-05 08:38:01', 1200.00, 2),
(34, '2026-04-05 08:38:56', 2600.00, 2),
(35, '2026-04-05 11:38:46', 600.00, 2),
(36, '2026-04-05 11:39:01', 1400.00, 2),
(37, '2026-04-05 11:40:15', 300.00, 2),
(38, '2026-04-05 11:54:31', 885.00, 2),
(39, '2026-04-05 11:55:07', 2407.50, 2),
(40, '2026-04-05 11:55:15', 2407.50, 2),
(41, '2026-04-05 11:57:29', 115.00, 2),
(42, '2026-04-05 11:57:53', 1425.00, 2),
(43, '2026-04-05 12:43:37', 450.00, 2),
(44, '2026-04-05 12:44:25', 360.00, 2),
(45, '2026-04-05 12:44:38', 1180.00, 2);

-- --------------------------------------------------------

--
-- Table structure for table `sale_items`
--

CREATE TABLE `sale_items` (
  `sales_item_id` int(11) NOT NULL,
  `sale_id` int(11) DEFAULT NULL,
  `medicine_id` int(11) DEFAULT NULL,
  `quantity_sold` int(11) DEFAULT NULL,
  `price_at_sale` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sale_items`
--

INSERT INTO `sale_items` (`sales_item_id`, `sale_id`, `medicine_id`, `quantity_sold`, `price_at_sale`) VALUES
(16, 30, 3, 9, 100.00),
(17, 31, 3, 7, 100.00),
(18, 32, 2, 3, 100.00),
(19, 33, 3, 6, 100.00),
(20, 34, 3, 5, 100.00),
(21, 35, 2, 6, 100.00),
(22, 36, 2, 2, 100.00),
(23, 37, 2, 3, 100.00),
(24, 38, 6, 6, 90.00),
(25, 39, 6, 9, 90.00),
(26, 40, 6, 9, 90.00),
(27, 41, 5, 2, 57.50),
(28, 42, 6, 4, 90.00),
(29, 43, 6, 5, 90.00),
(30, 44, 6, 4, 90.00),
(31, 45, 5, 8, 57.50);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `supplier_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `contact_person` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`supplier_id`, `name`, `contact_person`, `phone`, `email`, `address`) VALUES
(1, 'Zeema', 'Zweli', '0748623548', 'zweli@email.com', 'Sunnighill'),
(2, 'eCreete', 'Kgomosto Mashego', '07485624', 'kg@email.com', 'Sandton');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('Admin','Cashier') NOT NULL DEFAULT 'Cashier',
  `full_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`, `full_name`) VALUES
(1, 'admin', 'admin123', 'Admin', 'Zweli Mashego'),
(2, 'cashier', 'cashier123', 'Cashier', 'Cecil Mashego');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `medicines`
--
ALTER TABLE `medicines`
  ADD PRIMARY KEY (`medicine_id`),
  ADD KEY `fk_supplier` (`supplier_id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sale_id`),
  ADD KEY `fk_users` (`user_id`);

--
-- Indexes for table `sale_items`
--
ALTER TABLE `sale_items`
  ADD PRIMARY KEY (`sales_item_id`),
  ADD KEY `fk_sales` (`sale_id`),
  ADD KEY `fk_medicines` (`medicine_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `medicines`
--
ALTER TABLE `medicines`
  MODIFY `medicine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `sale_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `sale_items`
--
ALTER TABLE `sale_items`
  MODIFY `sales_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplier_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `medicines`
--
ALTER TABLE `medicines`
  ADD CONSTRAINT `fk_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `fk_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sale_items`
--
ALTER TABLE `sale_items`
  ADD CONSTRAINT `fk_medicines` FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`medicine_id`),
  ADD CONSTRAINT `fk_sales` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`sale_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
