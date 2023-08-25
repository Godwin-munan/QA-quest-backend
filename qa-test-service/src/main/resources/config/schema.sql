
CREATE TABLE IF NOT EXISTS test (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS test_questions (
    id BIGINT PRIMARY KEY,
    test_id BIGINT,
    question_id BIGINT,
    FOREIGN KEY (test_id) REFERENCES test(id)
);

