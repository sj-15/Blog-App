DROP TABLE IF EXISTS `posts`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `user_name` VARCHAR(45) DEFAULT NULL,
  `email` VARCHAR(45) DEFAULT NULL,
  `password` VARCHAR(45) DEFAULT NULL
);

INSERT INTO `users` (`user_id`, `user_name`, `email`, `password`) VALUES 
(1, 'Himalaya', 'himalaya.saxena@gmail.com', '12345'),
(2, 'Test User', 'test@gmail.com', '12345'),
(3, 'qwerty', 'qwerty@gmail.com', '12345');

CREATE TABLE `posts` (
  `post_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `post_title` VARCHAR(455) DEFAULT NULL,
  `post_body` TEXT,
  `published_by` INTEGER DEFAULT NULL,
  `created_on` DATETIME DEFAULT NULL,
  `updated_on` DATETIME DEFAULT NULL,
  `is_deleted` INTEGER DEFAULT NULL,
  FOREIGN KEY (`published_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
);

INSERT INTO `posts` (`post_id`, `post_title`, `post_body`, `published_by`, `created_on`, `updated_on`, `is_deleted`) VALUES 
(1, 'Post First Updated', 'Post body Updated', 1, '2020-04-14 21:04:06', '2020-04-17 15:04:46', 0),
(2, 'This is be body of my second blog post', 'My Second Post', 1, '2020-04-16 11:55:48', '2020-04-16 11:55:48', 1),
(3, 'titlee', 'bodydyyy', 1, '2020-04-16 11:56:11', '2020-04-17 15:31:56', 0),
(4, 'Title 34', 'Thhis is my 4th Blog body', 1, '2020-04-17 15:01:36', '2020-04-17 15:01:36', 1),
(5, 'This is blog title 5', 'This is blog body 5', 1, '2020-04-17 15:29:53', '2020-04-17 15:29:53', 1),
(6, 'Thats it', 'I am updated', 2, '2020-04-17 16:26:10', '2020-04-17 16:26:54', 1),
(7, 'title demo updated', 'demo body updated', 1, '2020-04-17 17:27:05', '2020-04-17 17:28:34', 0);
