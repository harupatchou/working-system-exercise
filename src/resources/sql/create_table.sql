CREATE TABLE workingtype
(
  id integer,
  working_name character varying(20) NOT NULL,
  working_time integer,
  CONSTRAINT workingtype_pkc PRIMARY KEY (id)
);

CREATE TABLE company
(
  id integer,
  company_name character varying(20) NOT NULL,
  workingtype_id integer NOT NULL,
  CONSTRAINT company_pkc PRIMARY KEY (id),
  FOREIGN KEY (workingtype_id) REFERENCES workingtype(id)
);

CREATE TABLE employee
(
  id integer,
  first_name character varying(10) NOT NULL,
  last_name character varying(10) NOT NULL,   
  password character varying(20) NOT NULL,
  company_id integer,
  CONSTRAINT employee_pkc PRIMARY KEY (id),
  FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE working_day
(
  id integer,
  date DATE NOT NULL,
  attendance_time integer,
  leave_time integer,
  break_time integer,
  nap_time integer,
  employee_id integer NOT NULL,
  CONSTRAINT working_day_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);

CREATE TABLE overtime
(
  id integer,
  overtime integer,
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
  week character varying(8) NOT NULL,
  working_time_all integer,
  overtime_all integer,
  night_time_all integer,
  night_overtime_all integer,
  late_time_all integer,
  employee_id integer NOT NULL,
  CONSTRAINT working_all_pkc PRIMARY KEY (id),
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);