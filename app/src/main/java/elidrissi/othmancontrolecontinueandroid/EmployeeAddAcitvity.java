package elidrissi.othmancontrolecontinueandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import elidrissi.othmancontrolecontinueandroid.entity.Employee;
import elidrissi.othmancontrolecontinueandroid.entity.Service;
import elidrissi.othmancontrolecontinueandroid.utility.ApiCalls;
import elidrissi.othmancontrolecontinueandroid.utility.JSONFactory;
import elidrissi.othmancontrolecontinueandroid.utility.JSONParsing;

public class EmployeeAddAcitvity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private EditText nom;
    private EditText prenom;
    private Spinner spinner;
    private Button create;



    private List<Service> services;

    private List<String> service_nom;

    private Service currentService;

    private ArrayAdapter<String> servivceAdapter;

    RequestQueue requestQueue;

    String post_url="http://10.0.2.2:8087/api/employees/create";

    String service_get_url="http://10.0.2.2:8087/api/services/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_acitvity);
        nom=findViewById(R.id.enter_nom);
        prenom=findViewById(R.id.enter_prenom);
        create=findViewById(R.id.create);
        spinner=findViewById(R.id.set_service);
        services=new ArrayList<>();
        service_nom=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        spinner.setOnItemSelectedListener(this);
        getAllSerices();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom_txt=nom.getText().toString();
                String prenom_txt=prenom.getText().toString();
                Service service=currentService;
                Employee employee=new Employee();
                employee.setNom(nom_txt);
                employee.setPrenom(prenom_txt);
                employee.setService(service);

                try {
                    JSONObject object= JSONFactory.getJSONObject(employee);

                    ApiCalls.makeJsonObjectRequest(post_url, Request.Method.POST, object, requestQueue, new ApiCalls.JsonObjectResponseHandler() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            finish();
                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private void getAllSerices() {

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

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}