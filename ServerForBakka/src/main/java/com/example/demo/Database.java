package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String URL = "jdbc:postgresql://localhost:5432/study";
    private final String USER = "root";
    private final String PASSWORD = "46q11146q";

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    ArrayList<Employee> employeeList = new ArrayList<>();


    public String getAllAsJson() throws SQLException {

        //Подключаемся к базе данных
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/study", "root", "46q11146q");
        statement = connection.createStatement();

        //Отправляем запрос базе данных и сохраняем результаты как список Employee объектов (список сотрудников)
        resultSet = statement.executeQuery("Select * from employes where deleted = false order by secondname");
        while (resultSet.next()) {
            employeeList.add(new Employee(resultSet));
        }

        //Переводим список сотрудников в строку формата Json
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String result = gson.toJson(employeeList);

        //Закрываем ресурсы
        closeResources();

        return result;
    }


    public void putDataIntoDatabase(String data) throws SQLException {

        //Переводим данные из формата Json в объект типа Employee
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Employee employee = gson.fromJson(data, new TypeToken<Employee>() {
        }.getType());
        System.out.println("Данные в таблице обновляются: " + employee.isDeleted());

        //Подключаемся к базе данных
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/study", "root", "46q11146q");
        statement = connection.createStatement();


        //Если таблице базы данных есть работник с тем же id, что мы получили от клиента, заменяем данные в таблице на новые:
        if (getIdArray(statement).contains(employee.id)) {
            updateSQLField(statement, employee);
        }
        //Если совпадения id отсутствуют , добавляем нового сотрудника в базу данных
        else addSQLField(statement, employee);

        //Закрываем ресурсы
        closeResources();

    }

    private void addSQLField(Statement statement, Employee employee) throws SQLException {
        statement.executeUpdate("INSERT INTO employes (secondname, name, patronymic,age,employdate,position,deleted) VALUES (" +
                "'"  + employee.getSecondname() + "'" +
                ",'" + employee.getName() + "'" +
                ",'" + employee.getPatronymic() + "'" +
                ","  + employee.getAge() +
                ",'" + employee.getEmploydate() + "'" +
                ",'" + employee.getPosition() + "'" +
                ",false)");
    }


    private void updateSQLField(Statement statement, Employee employee) throws SQLException {

        statement.executeUpdate("UPDATE employes SET"
                + " secondname = " + "'" + employee.getSecondname() + "'"
                + ", name = " + "'" + employee.getName() + "'"
                + ", patronymic = " + "'" + employee.getPatronymic() + "'"
                + ", age = " + "'" + employee.getAge() + "'"
                + ", employdate = " + "'" + employee.getEmploydate() + "'"
                + ", position = " + "'" + employee.getPosition() + "'"
                + ", deleted = " + "'" + employee.isDeleted() + "'"
                + "WHERE id = " + "'" + employee.id + "'");
    }

    private ArrayList<Integer> getIdArray(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("Select * from employes where deleted = false");
        ArrayList<Integer> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getInt("id"));
        }
        return list;
    }

    private void closeResources() {
        //Закрываем ресурсы
        try {
            connection.close();
            statement.close();
            resultSet.close();
            employeeList.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
