package com.example.myapplication.activities;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.myapplication.Fragments.DeleteDialogFragment;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.tasks.GetAllEmployeesTask;
import com.example.myapplication.Employee;
import com.example.myapplication.R;
import com.example.myapplication.tasks.UpdateDatabaseTask;

import java.util.ArrayList;

public class MainMenu extends FragmentActivity {

    public final static String SERVER_URL = "http:/192.168.56.1:8090/";
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<Employee> employeeArrayList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        employeeArrayList = GetAllEmployeesTask.getData();
        setContentView(R.layout.activity_main_menu);

        //Создаём и настраиваем RecycleView
        recyclerView = findViewById(R.id.RecycleView);
        myAdapter = new MyAdapter();
        myAdapter.setItems(employeeArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    public void refresh(View view) {
        ArrayList<Employee> employeeList = GetAllEmployeesTask.getData();
        myAdapter.clear();
        myAdapter.setItems(employeeList);
    }

    public void addEmployee(View view){
        Intent intent = new Intent(MainMenu.this, Redactor.class);
        intent.putExtra("action","creation");
        this.startActivity(intent);
    }

}
