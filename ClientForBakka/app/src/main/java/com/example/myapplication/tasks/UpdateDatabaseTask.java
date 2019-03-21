package com.example.myapplication.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.myapplication.activities.MainMenu.SERVER_URL;

public class UpdateDatabaseTask extends AsyncTask<Employee,Void,Void> {
    static  UpdateDatabaseTask task =null;



    public void send(Employee employee) throws IOException{

        //переводим данные о работнике в GSON
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String messageToSend =  gson.toJson(employee);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),messageToSend);
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(requestBody)
                .build();
        Response resp = client.newCall(request).execute();


        Log.i("send", "send: Информация отправлена!  "+employee.getName() + employee.isDeleted());
    }

    @Override
    protected Void doInBackground(Employee... employees) {
        try {
            for(Employee e: employees){
                send(e) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(Employee e){
        task = new UpdateDatabaseTask();
        task.execute(new Employee[]{e});
        task=null;
    }
}
