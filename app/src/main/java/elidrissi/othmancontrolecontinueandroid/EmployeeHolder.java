package elidrissi.othmancontrolecontinueandroid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import elidrissi.othmancontrolecontinueandroid.entity.Employee;

public class EmployeeHolder extends RecyclerView.ViewHolder {

    private TextView nom;
    private TextView prenom;
    private TextView date;
    private TextView service;

    public EmployeeHolder(@NonNull View itemView) {
        super(itemView);
        nom=itemView.findViewById(R.id.employee_nom);
        prenom=itemView.findViewById(R.id.employee_prenom);
        date=itemView.findViewById(R.id.employee_date);
        service=itemView.findViewById(R.id.employee_service);
    }

    public void populate(Employee employee){
        nom.setText(employee.getNom());
        prenom.setText(employee.getPrenom());
        date.setText(employee.getDateNaissance());
        service.setText(employee.getService().getNom());
    }
}
