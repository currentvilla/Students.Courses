INSERT INTO students (full_name, furigana, nickname, email, area, age, gender, remark, is_deleted) VALUES
('田中 太郎', 'タナカ タロウ', 'たろちゃん', 'taro.tanaka@example.com', '東京都', 28, 'male', 'プログラミング初心者', false),
('佐藤 花子', 'サトウ ハナコ', 'はなちゃん', 'hanako.sato@example.com', '大阪府', 24, 'female', NULL, false),
('鈴木 一郎', 'スズキ イチロウ', NULL, 'ichiro.suzuki@example.com', '神奈川県', 35, 'male', '転職希望', false),
('山田 美咲', 'ヤマダ ミサキ', 'みさ', 'misaki.yamada@example.com', '福岡県', 22, 'female', '大学在学中', false),
('中村 健太', 'ナカムラ ケンタ', 'けんちゃん', 'kenta.nakamura@example.com', '北海道', 31, 'male', NULL, false);

INSERT INTO students_courses (id, course_name, start_date, expected_end_date, created_at, updated_at, student_id) VALUES
('SC-001', 'Javaフルコース', '2024-04-01 09:00:00', '2024-09-30 18:00:00', '2024-03-15 10:00:00', '2024-03-15 10:00:00', 1),
('SC-002', 'Webデザインコース', '2024-05-01 09:00:00', '2024-08-31 18:00:00', '2024-04-20 11:00:00', '2024-04-20 11:00:00', 2),
('SC-003', 'Pythonデータ分析コース', '2024-03-01 09:00:00', '2024-08-31 18:00:00', '2024-02-10 09:30:00', '2024-06-01 14:00:00', 3),
('SC-004', 'フロントエンドコース', '2024-06-01 09:00:00', '2024-11-30 18:00:00', '2024-05-10 13:00:00', '2024-05-10 13:00:00', 4),
('SC-005', 'AWSクラウドコース', '2023-10-01 09:00:00', '2024-03-31 18:00:00', '2023-09-01 10:00:00', '2024-03-31 18:00:00', 5);

