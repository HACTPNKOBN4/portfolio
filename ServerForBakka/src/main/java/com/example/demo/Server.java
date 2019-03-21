package com.example.demo;

import org.springframework.web.HttpRequestHandler;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@EnableAutoConfiguration
public class Server implements HttpRequestHandler {

    Database db = new Database();

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }


    @RequestMapping(value = "/", method = GET)
    String returnEmployes() throws SQLException {
        System.out.println("Данные отправлены");
        return db.getAllAsJson();
    }


    @RequestMapping(value = "/", method = POST)
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = request.getReader().readLine();
        try {
            db.putDataIntoDatabase(jsonString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(request.getReader().readLine());

        System.out.println("Данные приняты");
        response.getWriter().print("test прошел успешно!");
    }
}
