import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeServlet extends HttpServlet {

    Connection connection;
    Statement statement;
    ResultSet resultSet;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("АППЛЕТ ЗАПУЩЕН И ПОЛУЧИЛ ЗАПРОС!");
        ArrayList<Employee> employeeList = new ArrayList<>();

        try {
            //подключаемся к базе данных, получаем строки с работниками
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/study", "root", "46q11146q") ;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from employes");

            while(resultSet.next()){
               employeeList.add(new Employee(resultSet));
            }

            //переводим список работников в формат json
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String jsonMessage = gson.toJson(employeeList);

            System.out.println(jsonMessage);
            resp.getWriter().print(jsonMessage);

        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello World!");
    }

    //класс работника. Такой же класс есть на стороне клиента. Информация о рабочих передаётся путём сериализации объектов этого класса в GSON и десериализации у клиента.
    class Employee{
        //Названия колонок в базе данных
        int id;
        String secondname;
        String name;
        String patronymic;
        int age;
        Date employdate;
        String position;

        //конструктор , заполняющий поля объекта информацией из базы данных
        public Employee(ResultSet rs) throws SQLException{
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
        }
    }
}