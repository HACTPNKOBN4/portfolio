package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Employee;
import com.example.myapplication.R;
import com.example.myapplication.tasks.UpdateDatabaseTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Redactor extends AppCompatActivity {

    TextView title;
    Button button;
    EditText secondname, name, patronymic, position, age, employdate;

    String action;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redactor);

        Intent intent = getIntent();
        action = intent.getStringExtra("action");
        AttacheElements();

        if (action.equals("redacting")) {
            employee = new Employee(intent);

            title.setText("Данные по струднику");
            button.setText("Отправить изменения на сервер");

            secondname.setText(employee.getSecondname());
            name.setText(employee.getName());
            patronymic.setText(employee.getPatronymic());
            position.setText(employee.getPosition());
            age.setText(new String(employee.getAge() + ""));
            employdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(employee.getEmploydate()));
        } else if(action.equals("creation")){
            employee = new Employee();
            employee.setId(-1);

            title.setText("Введите данные");
            button.setText("Добавить сотрудника в базу");
        }
    }

    private void AttacheElements() {
        secondname   = findViewById(R.id.secondnameRedactor);
        name        = findViewById(R.id.nameRedactor);
        patronymic  = findViewById(R.id.patronymicRedactor);
        position    = findViewById(R.id.positionRedactor);
        age         = findViewById(R.id.ageRedactor);
        employdate  = findViewById(R.id.employDateRedactor);

        title = findViewById(R.id.titleRedactor);
        button = findViewById(R.id.buttonRedactor);
    }

    public void buttonClicked(View view) {
        if(isAnythingNull()){
            //поставь предупреждение о пустом поле
            return;
        }
        Employee employee = createEmployeeFromEditText();
        if (action.equals("redacting")) {
            employee.setId(this.employee.getId());
        } else {
            employee.setId(-1);
        }

        UpdateDatabaseTask.update(employee);

    }

    private boolean isAnythingNull(){
        return !(!secondname.getText().toString().equals("")&&
                (!name.getText().toString().equals(""))&&
                (!patronymic.getText().toString().equals(""))&&
                (!position.getText().toString().equals(""))&&
                (!age.getText().toString().equals(""))&&
                (!employdate.getText().toString().equals("")));
    }

    private Employee createEmployeeFromEditText(){
        Employee employee = new Employee();
        employee.setId(this.employee.getId());

        employee.setSecondname(secondname.getText().toString());
        employee.setName(name.getText().toString());
        employee.setPatronymic(patronymic.getText().toString());
        employee.setPosition(position.getText().toString());
        employee.setAge(Integer.parseInt(age.getText().toString()));
        try {
            employee.setEmploydate(new SimpleDateFormat("YYYY-MM-DD").parse(
                    employdate.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employee.setDeleted(false);
        return employee;
    }
}
