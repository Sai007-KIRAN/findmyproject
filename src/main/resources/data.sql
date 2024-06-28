-- project
INSERT INTO project (name, budget) VALUES ('Project Alpha', 50000.00);
INSERT INTO project (name, budget) VALUES ('Project Beta', 100000.00);
INSERT INTO project (name, budget) VALUES ('Project Gamma', 150000.00);
INSERT INTO project (name, budget) VALUES ('Project Delta', 75000.00);

--researcher
INSERT INTO researcher (name, specialization) VALUES ('Marie Curie', 'Radioactivity');
INSERT INTO researcher (name, specialization) VALUES ('Albert Einstein', 'Relativity');
INSERT INTO researcher (name, specialization) VALUES ('Isaac Newton', 'Classical Mechanics');
INSERT INTO researcher (name, specialization) VALUES ('Niels Bohr', 'Quantum Mechanics');

--researcher_project
INSERT INTO researcher_project (projectId, researcherId) VALUES (1, 1);
INSERT INTO researcher_project (projectId, researcherId) VALUES (2, 1);
INSERT INTO researcher_project (projectId, researcherId) VALUES (2, 2);
INSERT INTO researcher_project (projectId, researcherId) VALUES (3, 3);
INSERT INTO researcher_project (projectId, researcherId) VALUES (4, 3);
INSERT INTO researcher_project (projectId, researcherId) VALUES (4, 4);