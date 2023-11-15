package elidrissi.othmancontrolecontinueandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import elidrissi.othmancontrolecontinueandroid.R;
import elidrissi.othmancontrolecontinueandroid.entity.Employee;
import elidrissi.othmancontrolecontinueandroid.entity.Service;
import elidrissi.othmancontrolecontinueandroid.utility.ApiCalls;
import elidrissi.othmancontrolecontinueandroid.utility.JSONParsing;

public class EmployeeParServiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Employee> employees;
    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;


    private List<Service> services;

    private List<String> service_nom;

    private Service currentService;

    private ArrayAdapter<String> servivceAdapter;



    String service_get_url="http://10.0.2.2:8087/api/services/all";
    String employee_get_url="http://10.0.2.2:8087/api/services/employees/";

    RequestQueue requestQueue;


    private Button search;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_par_service);
        employees=new ArrayList<>();
        services=new ArrayList<>();
        service_nom=new ArrayList<>();
        recyclerView=findViewById(R.id.employee_specilaite_list);
        search=findViewById(R.id.search);
        spinner=findViewById(R.id.list_service);
        adapter=new EmployeeAdapter(this.employees,this,R.layout.employee_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue= Volley.newRequestQueue(this);
        spinner.setOnItemSelectedListener(this);

        getAllService();

        search.setOnClickListener(v -> {

            employees.clear();
            String final_url=employee_get_url+currentService.getId();
            ApiCalls.makeJsonArrayRequest(final_url, Request.Method.GET, requestQueue, new ApiCalls.JsonArrayResponseHandler() {
                @Override
                public void onSuccess(JSONArray response) {

                    try {
                        List<Employee> employees_list=JSONParsing.toArrayOfJavaObjects(response,Employee.class);
                        employees.addAll(employees_list);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(VolleyError error) {

                }
            });

        });
    }

    private void getAllService() {

        ApiCalls.makeJsonArrayRequest(service_get_url, Request.Method.GET, this.requestQueue, new ApiCalls.JsonArrayResponseHandler() {
            @Override
            public void onSuccess(JSONArray response) {

                try {
                    List<Service> list_service= JSONParsing.toArrayOfJavaObjects(response, Service.class);
                    services.addAll(list_service);
                    for (Service service:services){
                        Log.d("List",service.toString());
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }finally {
                    populateSpinner();
                    if(services.size()!=0){
                        currentService=services.get(0);
                    }
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    private void populateSpinner() {
        for (Service service: services) {
            service_nom.add(service.getNom());
        }
        servivceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, service_nom);
        servivceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(servivceAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.currentService=this.services.get(position);
        Log.d("Current Object",currentService.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}