package com.example.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Fragments.DeleteDialogFragment;
import com.example.myapplication.activities.Autorization;
import com.example.myapplication.activities.MainMenu;
import com.example.myapplication.activities.Redactor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Employee> employeeList = new ArrayList<>();


    /*В классе холдера реализуем интерфейс OnClickListener и OnLongClickListener,
    чтобы обрабатывать нажатия по элементам списка, имея доступ к данным конкретного работника*/
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewForRecycle);
            textView.setOnClickListener(this);
            textView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Employee employee = employeeList.get(getLayoutPosition());
            MainMenu mainMenu =(MainMenu) itemView.getContext();

            Intent intent = new Intent(mainMenu, Redactor.class);
            intent.putExtra("id",employee.getId());
            intent.putExtra("secondname",employee.getSecondname());
            intent.putExtra("name",employee.getName());
            intent.putExtra("patronymic",employee.getPatronymic());
            intent.putExtra("position",employee.getPosition());
            intent.putExtra("age",new String(employee.getAge()+""));
            intent.putExtra("employdate",new SimpleDateFormat("yyyy-MM-dd").format(employee.employdate));
            intent.putExtra("action","redacting");

            ((MainMenu) itemView.getContext()).startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            MainMenu mainMenu = (MainMenu) itemView.getContext();
            Log.i("sdfasdf", "onLongClick: Длинный клик");

            DeleteDialogFragment fragment = new DeleteDialogFragment();
            fragment.setData(employeeList.get(getAdapterPosition()));
            fragment.show(mainMenu.getSupportFragmentManager(),"asdf");
            return true;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String secondName = employeeList.get(position).secondname;
        String name = employeeList.get(position).name;
        String patronymic = employeeList.get(position).patronymic;
        holder.textView.setText((position+1) + " " + secondName + " " + name + " " + patronymic);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void setItems(ArrayList<Employee> e){
        employeeList.addAll(e);
        notifyDataSetChanged();
    }

    public void clear(){
        employeeList.clear();
        notifyDataSetChanged();
    }

    public void update(){
        notifyDataSetChanged();
    }
}
