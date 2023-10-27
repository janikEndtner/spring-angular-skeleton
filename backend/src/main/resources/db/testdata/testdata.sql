INSERT INTO user (id, email, password) VALUES
                            (1, 'test@test.com', '$2a$12$oCayvX2WHm8L9v3SWKEHNeK2OsyqpzfsCrPPMmM7xHhjxJdseednC'),
                            (2, 'next@test.com', '$2a$12$oCayvX2WHm8L9v3SWKEHNeK2OsyqpzfsCrPPMmM7xHhjxJdseednC'),
                            (3, 'admin@example.com', '$2a$12$oCayvX2WHm8L9v3SWKEHNeK2OsyqpzfsCrPPMmM7xHhjxJdseednC');

INSERT INTO user_role (id, user_id, role) VALUES
                                              (1, 1, 'USER'),
                                              (2, 1, 'ADMIN'),
                                              (3, 2, 'USER'),
                                              (4, 3, 'ADMIN'),
                                              (5, 3, 'USER');
