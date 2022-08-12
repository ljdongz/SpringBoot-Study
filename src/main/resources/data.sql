INSERT INTO article (id, title, content) VALUES (1, 'Hello World 1', 'This is a test article 1');
INSERT INTO article (id, title, content) VALUES (2, 'Hello World 2', 'This is a test article 2');
INSERT INTO article (id, title, content) VALUES (3, 'Hello World 3', 'This is a test article 3');

-- article 더미 데이터
INSERT INTO article (id, title, content) VALUES (4, '최애 음식은?', '댓글 ㄱ');
INSERT INTO article (id, title, content) VALUES (5, '최애 영화는?', '댓글 ㄱ');
INSERT INTO article (id, title, content) VALUES (6, '취미는?', '댓글 ㄱ');

-- comment 더미 데이터
---- 4번 게시글의 댓글
INSERT INTO comment (id, article_id, nickname, body) VALUES (1, 4, 'Park', '치킨');
INSERT INTO comment (id, article_id, nickname, body) VALUES (2, 4, 'Lee', '피자');
INSERT INTO comment (id, article_id, nickname, body) VALUES (3, 4, 'Kim', '스파게티');
---- 5번 게시글의 댓글
INSERT INTO comment (id, article_id, nickname, body) VALUES (4, 5, 'Park', '아이언맨');
INSERT INTO comment (id, article_id, nickname, body) VALUES (5, 5, 'Lee', '스파이더맨');
INSERT INTO comment (id, article_id, nickname, body) VALUES (6, 5, 'Kim', '어벤져스');
---- 6번 게시글의 댓글
INSERT INTO comment (id, article_id, nickname, body) VALUES (7, 6, 'Park', '축구');
INSERT INTO comment (id, article_id, nickname, body) VALUES (8, 6, 'Lee', '풋살');
INSERT INTO comment (id, article_id, nickname, body) VALUES (9, 6, 'Kim', '코딩');