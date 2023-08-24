CREATE TABLE IF NOT EXISTS test (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    test_title VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS questions (
    id BIGINT PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS test_questions (
    id BIGINT PRIMARY KEY,
    test_id BIGINT,
    question_id BIGINT,
    FOREIGN KEY (test_id) REFERENCES test(id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

