package com.example.myapplication.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.myapplication.Employee;
import com.example.myapplication.activities.MainMenu;
import com.example.myapplication.tasks.UpdateDatabaseTask;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeleteDialogFragment  extends DialogFragment {

    private Employee employee = null;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Удалить работника  из базы данных?").setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!(employee==null)) {
                    Log.i("TAG", "onClick: КНОПКА КАЖААСЬ , УДАЛЕНИЕ ПРОШЛО!");
                    employee.delete();
                    UpdateDatabaseTask task = new UpdateDatabaseTask();
                    Log.i("asd", "onClick: "+employee.isDeleted());
                    task.execute(new Employee[]{employee});
                    ((MainMenu)getActivity()).refresh(new View(getContext()));
                }
            }
        });
        return builder.create();
    }

    public void setData(Employee employee){
        this.employee=employee;
    }
}
