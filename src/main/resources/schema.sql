-- schema.sql
CREATE TABLE project (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    budget DOUBLE
);

CREATE TABLE researcher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    specialization varchar(255)
);

CREATE TABLE researcher_project (
    projectId INT,
    researcherId INT,
    PRIMARY KEY (projectId, researcherId),
    FOREIGN KEY (projectId) REFERENCES project(id),
    FOREIGN KEY (researcherId) REFERENCES researcher(id)
);


