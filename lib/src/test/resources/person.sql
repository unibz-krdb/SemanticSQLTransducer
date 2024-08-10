CREATE TABLE ssqlt_test._person
    (
      ssn VARCHAR(100) NOT NULL,
      phone VARCHAR(100) NOT NULL,
      manager VARCHAR(100),
      title VARCHAR(100),
      city VARCHAR(100) NOT NULL,
      country VARCHAR(100) NOT NULL,
      mayor VARCHAR(100) NOT NULL
    );

    ALTER TABLE ssqlt_test._person ADD PRIMARY KEY (ssn,phone);
