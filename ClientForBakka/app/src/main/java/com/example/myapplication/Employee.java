package com.example.myapplication;

import android.content.Intent;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
        int id;
        String secondname;
        String name;
        String patronymic;
        int age;
        Date employdate;
        String position;
        boolean isDeleted;

        public Employee(){

        }

        public Employee(Intent intent){
            this.secondname = intent.getStringExtra("secondname");
            this.name = intent.getStringExtra("name");
            this.patronymic = intent.getStringExtra("patronymic");
            this.age = Integer.parseInt(intent.getStringExtra("age"));
            Log.i("asd", "Employee: " + this.age );
            try {
                this.employdate = new SimpleDateFormat("yyyy-MM-dd").parse(intent.getStringExtra("employdate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.id = intent.getIntExtra("id",-1);
            this.position = intent.getStringExtra("position");
            this.isDeleted = false;
        }

        public static Employee GetTestEmployee(){
                Employee e = new Employee();
                e.id = -1;
                e.secondname = "Толденко";
                e.name = "Алевтина";
                e.patronymic = "Тестовна";
                e.age = 24;
                e.position = "PR manager";
                e.isDeleted = false;
                try {
                        e.employdate = new SimpleDateFormat("yyyy-MM-dd").parse("1994-07-17");
                } catch (ParseException e1) {
                        e1.printStackTrace();
                }
                return e;
        }

        public void delete(){this.isDeleted = true;}

        public void setDeleted(boolean bool){this.isDeleted = bool;}

        public boolean isDeleted(){
                return isDeleted;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getSecondname() {
                return secondname;
        }

        public void setSecondname(String secondname) {
                this.secondname = secondname;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPatronymic() {
                return patronymic;
        }

        public void setPatronymic(String patronymic) {
                this.patronymic = patronymic;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public Date getEmploydate() {
                return employdate;
        }

        public void setEmploydate(Date employdate) {
                this.employdate = employdate;
        }

        public String getPosition() {
                return position;
        }

        public void setPosition(String position) {
                this.position = position;
        }
}
