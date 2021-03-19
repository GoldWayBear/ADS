-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- Link to schema: https://app.quickdatabasediagrams.com/#/d/w8SAKX
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.


CREATE TABLE "user" (
    "userId" SERIAL   NOT NULL,
    "profilePic" BYTEA   NOT NULL,
    "firstName" VARCHAR   NOT NULL,
    "lastName" VARCHAR   NOT NULL,
    "gender" VARCHAR   NOT NULL,
    "email" VARCHAR   NOT NULL,
    "phone" LONG   NOT NULL,
    "address" INT   NOT NULL,
    "dob" DATE   NOT NULL,
    "roleId" INT   NOT NULL,
    CONSTRAINT "pk_user" PRIMARY KEY (
        "userId"
     )
);

CREATE TABLE "address" (
    "addressId" INT   NOT NULL,
    "street" VARCHAR   NOT NULL,
    "city" VARCHAR   NOT NULL,
    "state" VARCHAR   NOT NULL,
    "zipcode" LONG   NOT NULL,
    "apt" VARCHAR   NOT NULL,
    CONSTRAINT "pk_address" PRIMARY KEY (
        "addressId"
     )
);

CREATE TABLE "bill" (
    "invoiceId" SERIAL   NOT NULL,
    "userId" INT   NOT NULL,
    "total" NUMERIC   NOT NULL,
    "date" DATE   NOT NULL,
    "dueDate" DATE   NOT NULL,
    CONSTRAINT "pk_bill" PRIMARY KEY (
        "invoiceId"
     )
);

CREATE TABLE "login" (
    "userId" INT   NOT NULL,
    "username" VARCHAR   NOT NULL,
    "password" VARCHAR   NOT NULL,
    CONSTRAINT "pk_login" PRIMARY KEY (
        "userId"
     )
);

CREATE TABLE "appointment" (
    "appointmentId" SERIAL   NOT NULL,
    "date_time" DATETIME   NOT NULL,
    "patient" INT   NOT NULL,
    "doctor" INT   NOT NULL,
    "status" VARCHAR   NOT NULL,
    CONSTRAINT "pk_appointment" PRIMARY KEY (
        "appointmentId"
     )
);

CREATE TABLE "patient_doctor" (
    "patientId" INT   NOT NULL,
    "doctorId" INT   NOT NULL,
    CONSTRAINT "pk_patient_doctor" PRIMARY KEY (
        "patientId","doctorId"
     )
);

CREATE TABLE "role" (
    "roleId" INT   NOT NULL,
    "role" VARCHAR   NOT NULL,
    CONSTRAINT "pk_role" PRIMARY KEY (
        "roleId"
     )
);

ALTER TABLE "user" ADD CONSTRAINT "fk_user_address" FOREIGN KEY("address")
REFERENCES "address" ("addressId");

ALTER TABLE "user" ADD CONSTRAINT "fk_user_roleId" FOREIGN KEY("roleId")
REFERENCES "role" ("roleId");

ALTER TABLE "bill" ADD CONSTRAINT "fk_bill_userId" FOREIGN KEY("userId")
REFERENCES "user" ("userId");

ALTER TABLE "login" ADD CONSTRAINT "fk_login_userId" FOREIGN KEY("userId")
REFERENCES "user" ("userId");

ALTER TABLE "appointment" ADD CONSTRAINT "fk_appointment_patient" FOREIGN KEY("patient")
REFERENCES "user" ("userId");

ALTER TABLE "appointment" ADD CONSTRAINT "fk_appointment_doctor" FOREIGN KEY("doctor")
REFERENCES "user" ("userId");

ALTER TABLE "patient_doctor" ADD CONSTRAINT "fk_patient_doctor_patientId" FOREIGN KEY("patientId")
REFERENCES "user" ("userId");

ALTER TABLE "patient_doctor" ADD CONSTRAINT "fk_patient_doctor_doctorId" FOREIGN KEY("doctorId")
REFERENCES "user" ("userId");

