package example.com.auto_kit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name,vehicle_no;
    TextView vehicle_info;
    String user_name="",veh_name="",veh_no="",average="",fuel_capacity="";
    String user_info;
    User user=new User();
    VehicleInfoInterpretor vehicleInfoInterpretor=new VehicleInfoInterpretor();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] vehicle_name={"Vehicles","Activa"};
        Spinner spinner=findViewById(R.id.vehicle_name);
        name=findViewById(R.id.name);
        vehicle_no=findViewById(R.id.vehicle_no);
        vehicle_info=findViewById(R.id.vehicle_info);
        submit=findViewById(R.id.submit);
        sharedPreferences=getSharedPreferences("flagdata", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vehicle_name);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vehicle_info.setText("");
                if(position!=0) {
                    veh_name = vehicle_name[position];
                    Vehicle vehicle = vehicleInfoInterpretor.getVehicle(veh_name);
                    average = vehicle.getAverage();
                    fuel_capacity = vehicle.getFuelCapacity();
                    vehicle_info.append(String.format("Comapany Average : " + average + "\n"));
                    vehicle_info.append(String.format("Fuel Capacity    : " + fuel_capacity + "\n"));
                }
                else {
                    vehicle_info.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name=name.getText().toString();
                veh_no=vehicle_no.getText().toString();
                if (!user_name.equals("")&&!veh_no.equals("")&&!veh_name.equals("")) {
                    user.setUserInfo(user_name, veh_no, veh_name, average, fuel_capacity);
                    user_info=user_name+","+veh_name+","+veh_no+","+fuel_capacity+","+average;
                    editor.putInt("flag", 2);
                    editor.putString("user_info",user_info);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(getBaseContext(),"Enter All Required Fields",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

