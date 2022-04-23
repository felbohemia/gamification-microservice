
--DROP TABLE IF EXISTS score_card;
--IF NOT EXISTS

CREATE TABLE IF NOT EXISTS score_card
(
  card_id int not null primary key AUTO_INCREMENT,
  user_id varchar(100) not null,
  score int not null,
  time_created DateTime
);

--DROP TABLE IF EXISTS badge_card;
CREATE TABLE IF NOT EXISTS badge_card
(
  badge_id int not null primary key AUTO_INCREMENT,
  user_id varchar(100) not null,
  badge varchar(15) not null,
  time_earned DateTime
);
