INSERT INTO users (id, created_at, created_by, updated_at, updated_by,
                   email, name, password, phone, role)
VALUES (1, '2025-08-16 14:44:26.470657', 'anonymousUser', NULL, NULL,
        'admin@example.com', 'Example Admin', '{bcrypt}$2a$10$KH53YWRzHb4qbV9pTW9Pne/wjfRgIzOJNJ08sBopIxHvyYhGxoXYO',
        NULL, 'ADMIN'),

       (2, '2025-08-16 14:45:42.813448', 'anonymousUser', NULL, NULL,
        'user@example.com', 'Example User', '{bcrypt}$2a$10$tDMHnRO7zyXKvhgOrZ8/Me8UCZdeSJ88O/9m83qtbocdjr/B9fqti',
        NULL, 'USER');


INSERT INTO category (name, description)
VALUES ('Science Fiction', 'Books about futuristic science, technology, and space exploration.'),
       ('Fantasy', 'Magical worlds, wizards, dragons, and mythical creatures.'),
       ('Mystery', 'Whodunits, detective stories, and thrillers.'),
       ('Romance', 'Love stories and relationships.'),
       ('History', 'Books about past events and human civilizations.'),
       ('Biography', 'Life stories of famous people.'),
       ('Self-Help', 'Guides for personal development and growth.'),
       ('Technology', 'Books about modern innovations and gadgets.'),
       ('Philosophy', 'Books on human thought, ethics, and existence.'),
       ('Horror', 'Scary stories with supernatural or psychological themes.');

INSERT INTO author (name, bio, created_at, updated_at, created_by, updated_by)
VALUES ('Isaac Asimov', 'Prolific science fiction author and biochemist.', NOW(), NOW(), 'system', 'system'),
       ('J.K. Rowling', 'British author known for the Harry Potter series.', NOW(), NOW(), 'system', 'system'),
       ('Agatha Christie', 'Queen of crime fiction and mystery novels.', NOW(), NOW(), 'system', 'system'),
       ('Nicholas Sparks', 'American novelist known for romantic dramas.', NOW(), NOW(), 'system', 'system'),
       ('Yuval Noah Harari', 'Historian and author of Sapiens.', NOW(), NOW(), 'system', 'system'),
       ('Walter Isaacson', 'Biographer of Steve Jobs, Einstein, and others.', NOW(), NOW(), 'system', 'system'),
       ('Dale Carnegie', 'Author and lecturer on self-improvement.', NOW(), NOW(), 'system', 'system'),
       ('Hamish McKenzie', 'Author of books on Tesla and innovation.', NOW(), NOW(), 'system', 'system'),
       ('Plato', 'Ancient Greek philosopher and student of Socrates.', NOW(), NOW(), 'system', 'system'),
       ('Stephen King', 'Master of horror, supernatural fiction, and suspense.', NOW(), NOW(), 'system', 'system');

INSERT INTO book (title, description, publisher, publication_date,
                  category_id, author_id,
                  book_file_url, book_file_url_public_id,
                  cover_image_url, cover_image_public_id)
VALUES ('Foundation', 'Epic sci-fi saga about the fall of the Galactic Empire.', 'Random House', '1951-06-01', 1, 1,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-1',
        'https://covers.openlibrary.org/b/id/7222246-L.jpg', 'cover-1'),

       ('Harry Potter and the Philosopher''s Stone', 'The boy who lived begins his magical journey.', 'Bloomsbury',
        '1997-06-26', 2, 2,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-2',
        'https://covers.openlibrary.org/b/id/7984916-L.jpg', 'cover-2'),

       ('Murder on the Orient Express', 'Detective Hercule Poirot solves a murder mystery on a train.',
        'Collins Crime Club', '1934-01-01', 3, 3,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-3',
        'https://covers.openlibrary.org/b/id/8231856-L.jpg', 'cover-3'),

       ('The Notebook', 'A timeless love story between Noah and Allie.', 'Warner Books', '1996-10-01', 4, 4,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-4',
        'https://covers.openlibrary.org/b/id/240726-L.jpg', 'cover-4'),

       ('Sapiens: A Brief History of Humankind', 'Journey of Homo sapiens from prehistoric to modern age.', 'Harper',
        '2011-09-04', 5, 5,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-5',
        'https://covers.openlibrary.org/b/id/8378429-L.jpg', 'cover-5'),

       ('Steve Jobs', 'Biography of Apple co-founder Steve Jobs.', 'Simon & Schuster', '2011-10-24', 6, 6,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-6',
        'https://covers.openlibrary.org/b/id/7275431-L.jpg', 'cover-6'),

       ('How to Win Friends and Influence People', 'Classic self-help guide on communication and relationships.',
        'Simon & Schuster', '1936-10-01', 7, 7,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-7',
        'https://covers.openlibrary.org/b/id/8226191-L.jpg', 'cover-7'),

       ('Tesla Revolution', 'Innovation and disruption in the electric car industry.', 'Penguin', '2018-01-15', 8, 8,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-8',
        'https://covers.openlibrary.org/b/id/10594727-L.jpg', 'cover-8'),

       ('The Republic', 'Philosophical dialogue by Plato on justice and order.', 'Oxford Classics', '0380-01-01', 9, 9,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-9',
        'https://covers.openlibrary.org/b/id/8235116-L.jpg', 'cover-9'),

       ('It', 'Horror novel about a shapeshifting evil in Derry, Maine.', 'Viking Press', '1986-09-15', 10, 10,
        'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf', 'book-file-10',
        'https://covers.openlibrary.org/b/id/8225631-L.jpg', 'cover-10');
