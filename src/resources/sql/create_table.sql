CREATE TABLE workingtype
(
  id integer,
  working_name text NOT NULL,
  working_time text,
  CONSTRAINT workingtype_pkc PRIMARY KEY (id)
);

CREATE TABLE company
(
  id integer,
  company_name text NOT NULL,
  workingtype_id integer NOT NULL,
  CONSTRAINT company_pkc PRIMARY KEY (id),
  FOREIGN KEY (workingtype_id) REFERENCES workingtype(id)
);

CREATE TABLE employee
(
  id integer,
  first_name text NOT NULL,
  last_name text NOT NULL,
  password text NOT NULL,
  company_id integer,
  CONSTRAINT employee_pkc PRIMARY KEY (id),
  FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE working_day
(
  id integer,
  date DATE NOT NULL,
  attendance_time text,
  leave_time text,
  break_time text,
  nap_time text,
  employee_id integer NOT NULL,
  CONSTRAINT working_day_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);

CREATE TABLE overtime
(
  id integer,
  overtime text,
  daily_id integer NOT NULL,
  CONSTRAINT overtime_pkc PRIMARY KEY (id),
  FOREIGN KEY (daily_id) REFERENCES working_day(id)
);

CREATE TABLE working_all
(
  id integer,
  day integer NOT NULL,
  month integer NOT NULL,
  year integer NOT NULL,
  week text NOT NULL,
  working_time_all text,
  overtime_all text,
  night_time_all text,
  night_overtime_all text,
  late_time_all text,
  employee_id integer NOT NULL,
  CONSTRAINT working_all_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);