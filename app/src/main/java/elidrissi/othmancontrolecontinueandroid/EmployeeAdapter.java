package elidrissi.othmancontrolecontinueandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import elidrissi.othmancontrolecontinueandroid.entity.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder> {


    private List<Employee> employees;
    private Context context;
    private int layout;
    private LayoutInflater inflater;

    public EmployeeAdapter(List<Employee> employees, Context context, int layout) {
        this.employees = employees;
        this.context = context;
        this.layout = layout;
        this.inflater=LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View employee_item=this.inflater.inflate(this.layout,parent,false);
        EmployeeHolder holder=new EmployeeHolder(employee_item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee employee=this.employees.get(position);
        holder.populate(employee);
    }
    @Override
    public int getItemCount() {
        return this.employees.size();
    }
}
