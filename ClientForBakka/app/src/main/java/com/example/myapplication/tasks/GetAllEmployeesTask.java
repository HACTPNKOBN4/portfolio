package com.example.myapplication.tasks;

import android.os.AsyncTask;

import com.example.myapplication.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.myapplication.activities.MainMenu.SERVER_URL;

public class GetAllEmployeesTask extends AsyncTask<Void,Void,ArrayList<Employee>> {


    public ArrayList<Employee> connect() throws IOException{

        //создаём подключение
        URL url = new URL(SERVER_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //настраиваем подключение
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);

        //подключаемся
        connection.connect();

        //Получаем данные (в фотмате Json) из подключения и сохраняем его в строку
        InputStream in = new BufferedInputStream(connection.getInputStream());
        String result = IOUtils.toString(in, Charset.forName("UTF-8"));

        //Переводим данные из формата Json в список объектов типа Employee
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson= gsonBuilder.create();
        ArrayList<Employee> employeeList;
        employeeList = gson.fromJson(result,new TypeToken<ArrayList<Employee>>(){}.getType());



        return employeeList;
    }

    @Override
    protected ArrayList<Employee> doInBackground(Void... voids) {
        ArrayList<Employee> employeeList = null;
        try{
            employeeList = connect();
        } catch (IOException e){
            e.printStackTrace();
        }
        return employeeList;
    }

    public static ArrayList<Employee> getData(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        GetAllEmployeesTask query = new GetAllEmployeesTask();
        query.execute();
        try {
           employeeList = query.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
