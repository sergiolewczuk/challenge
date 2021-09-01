INSERT INTO `genres` (`image`, `name`) VALUES ('', 'Comedy');
INSERT INTO `genres` (`image`, `name`) VALUES ('', 'Ficción');

INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (10, '', '', 'Batman', 50);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (26, '', '', 'Thor', 45);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (45, '', '', 'IronMan', 34);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (56, '', '', 'Hulk', 120);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (41, '', '', 'Tormenta', 79);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (23, '', '', 'Professor X', 98);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (21, '', '', 'Daniel', 21);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (21, '', '', 'Spiderman', 21);
INSERT INTO `characters` (`age`, `history`, `image`, `name`, `weight`) VALUES (21, '', '', 'Superman', 21);

INSERT INTO `movies` (`create_at`, `image`, `score`, `title`, `id_genre`) VALUES ('2021-08-24', '', 3, 'SpiderMan', 1);
INSERT INTO `movies` (`create_at`, `image`, `score`, `title`, `id_genre`) VALUES ('2021-08-24', '', 5, 'SuperMan', 2);
INSERT INTO `movies` (`create_at`, `image`, `score`, `title`, `id_genre`) VALUES ('2021-08-24', '', 2, 'Batman', 1);
INSERT INTO `movies` (`create_at`, `image`, `score`, `title`, `id_genre`) VALUES ('2021-08-24', '', 4, 'Iron Man', 2);

INSERT INTO `characters_movies` (`characters_id`, `movies_id`) VALUES (9, 2);
INSERT INTO `characters_movies` (`characters_id`, `movies_id`) VALUES (8, 1);
INSERT INTO `characters_movies` (`characters_id`, `movies_id`) VALUES (3, 3);
INSERT INTO `characters_movies` (`characters_id`, `movies_id`) VALUES (1, 3);