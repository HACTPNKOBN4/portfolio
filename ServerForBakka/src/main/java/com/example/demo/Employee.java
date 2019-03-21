package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Employee{
    //Названия колонок в базе данных
    int id;
    String secondname;
    String name;
    String patronymic;
    int age;
    Date employdate;
    String position;
    boolean isDeleted;

    //конструктор , заполняющий поля объекта информацией из базы данных
    public Employee(ResultSet rs) throws SQLException {
        this.id =  rs.getInt("id");
        this.secondname = rs.getString("secondname");
        this.name = rs.getString("name");
        this.patronymic =  rs.getString("patronymic");
        this.age =  rs.getInt("age");
        try{
            this.employdate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("employdate"));
        } catch (ParseException pe){
            pe.printStackTrace();
        }
        this.position = rs.getString("position");
        this.isDeleted = rs.getBoolean("deleted");
    }

    public int getId() {
        return id;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public Date getEmploydate() {
        return employdate;
    }

    public String getPosition() {
        return position;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}