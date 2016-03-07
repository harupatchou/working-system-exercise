CREATE TABLE company
(
  id integer,
  company_name text NOT NULL,
  CONSTRAINT company_pkc PRIMARY KEY (id)
);

CREATE TABLE labor_system
(
  id serial,
  labor_system_name text NOT NULL,
  CONSTRAINT labor_system_pkc PRIMARY KEY (id)
);

CREATE TABLE workingtype
(
  id integer,
  working_name text NOT NULL,
  labor_system_id integer NOT NULL,
  company_id integer NOT NULL,
  CONSTRAINT workingtype_pkc PRIMARY KEY (id),
  FOREIGN KEY (labor_system_id) REFERENCES labor_system(id),
  FOREIGN KEY (company_id) REFERENCES company(id)
);


CREATE TABLE employee
(
  id integer,
  first_name text NOT NULL,
  last_name text NOT NULL,
  password text NOT NULL,
  company_id integer,
  workingtype_id integer,
  CONSTRAINT employee_pkc PRIMARY KEY (id),
  FOREIGN KEY (company_id) REFERENCES company(id),
  FOREIGN KEY (workingtype_id) REFERENCES workingtype(id)
);

CREATE TABLE week
(
  id serial,
  week_name text NOT NULL,
  holiday_flag integer NOT NULL,
  CONSTRAINT week_pkc PRIMARY KEY (id)
);

CREATE TABLE working_day
(
  id serial,
  date DATE NOT NULL,
  week integer NOT NULL,
  attendance_time text,
  leave_time text,
  break_time text,
  nap_time text,
  employee_id integer NOT NULL,
  legal_flag integer NOT NULL,
  CONSTRAINT working_day_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);

CREATE TABLE overtime
(
  id serial,
  legal_overtime text,
  non_legal_overtime text,
  daily_id integer NOT NULL,
  CONSTRAINT overtime_pkc PRIMARY KEY (id),
  FOREIGN KEY (daily_id) REFERENCES working_day(id)
);

CREATE TABLE working_all
(
  id serial,
  day integer NOT NULL,
  month integer NOT NULL,
  year integer NOT NULL,
  week integer NOT NULL,
  working_time_all text,
  legal_overtime_all text,
  non_legal_overtime_all text,
  night_time_all text,
  night_overtime_all text,
  late_time_all text,
  legal_holiday_time_all text,
  statutory_holiday_time_all text,
  employee_id integer NOT NULL,
  CONSTRAINT working_all_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);