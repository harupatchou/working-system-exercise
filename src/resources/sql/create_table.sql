DROP TABLE working_all;
DROP TABLE overtime;
DROP TABLE working_day;
DROP TABLE week;
DROP TABLE employee;
DROP TABLE attendance_time;
DROP TABLE attendance_status;
DROP TABLE working_time;
DROP TABLE working_type;
DROP TABLE labor_system;
DROP TABLE company;

CREATE TABLE company
(
  id integer,
  company_name text NOT NULL,
  master_id integer NOT NULL,
  CONSTRAINT company_pkc PRIMARY KEY (id)
);

CREATE TABLE labor_system
(
  id serial,
  labor_system_name text NOT NULL,
  CONSTRAINT labor_system_pkc PRIMARY KEY (id)
);

CREATE TABLE working_type
(
  id integer,
  working_name text NOT NULL,
  labor_system_id integer NOT NULL,
  company_id integer NOT NULL,
  CONSTRAINT working_type_pkc PRIMARY KEY (id),
  FOREIGN KEY (labor_system_id) REFERENCES labor_system(id) ON UPDATE CASCADE,
  FOREIGN KEY (company_id) REFERENCES company(id) ON UPDATE CASCADE
);

CREATE TABLE working_time
(
  id integer,
  working_time real,
  carryover_time text,
  labor_system_id integer NOT NULL,
  CONSTRAINT working_time_pkc PRIMARY KEY (id),
  FOREIGN KEY (labor_system_id) REFERENCES labor_system(id) ON UPDATE CASCADE
);

CREATE TABLE attendance_time
(
  id serial,
  start_time text NOT NULL,
  end_time text NOT NULL,
  core_time_start text,
  core_time_end text,
  labor_system_id integer NOT NULL,
  company_id integer NOT NULL,
  CONSTRAINT attendance_time_pkc PRIMARY KEY (id),
  FOREIGN KEY (labor_system_id) REFERENCES labor_system(id) ON UPDATE CASCADE,
  FOREIGN KEY (company_id) REFERENCES company(id) ON UPDATE CASCADE
);

CREATE TABLE attendance_status
(
  id serial,
  status_name text NOT NULL,
  CONSTRAINT attendance_status_pkc PRIMARY KEY (id)
);

CREATE TABLE employee
(
  id integer,
  first_name text NOT NULL,
  last_name text NOT NULL,
  password text NOT NULL,
  company_id integer,
  working_type_id integer,
  CONSTRAINT employee_pkc PRIMARY KEY (id),
  FOREIGN KEY (company_id) REFERENCES company(id) ON UPDATE CASCADE,
  FOREIGN KEY (working_type_id) REFERENCES working_type(id) ON UPDATE CASCADE
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
  break_time_start text,
  break_time_end text,
  nap_time text,
  employee_id integer NOT NULL,
  legal_flag integer NOT NULL,
  attendance_status integer,
  CONSTRAINT working_day_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id) ON UPDATE CASCADE,
  FOREIGN KEY (attendance_status) REFERENCES attendance_status(id) ON UPDATE CASCADE
);

CREATE TABLE overtime
(
  id serial,
  legal_overtime text,
  statutory_overtime text,
  night_overtime text,
  statutory_night_overtime text,
  daily_id integer NOT NULL,
  CONSTRAINT overtime_pkc PRIMARY KEY (id),
  FOREIGN KEY (daily_id) REFERENCES working_day(id) ON UPDATE CASCADE
);

CREATE TABLE working_all
(
  id serial,
  date DATE NOT NULL,
  week integer NOT NULL,
  working_time_all text,
  legal_overtime_all text,
  statutory_overtime_all text,
  night_time_all text,
  night_overtime_all text,
  late_time_all text,
  day_status text,
  employee_id integer NOT NULL,
  CONSTRAINT working_all_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id) ON UPDATE CASCADE
);