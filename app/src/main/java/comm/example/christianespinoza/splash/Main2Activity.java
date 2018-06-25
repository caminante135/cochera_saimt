package comm.example.christianespinoza.splash;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button regresar, bfecha, bhora;
    EditText efecha, ehora;
    private int dia,mes,ano,hora,minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        regresar = (Button)findViewById(R.id.inicio);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(regresar);
            }
        });


        bfecha=(Button)findViewById(R.id.button_fecha);
        bhora=(Button)findViewById(R.id.button_hora);
        efecha=(EditText)findViewById(R.id.editFecha);
        ehora=(EditText)findViewById(R.id.editHora);
        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v==bfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    efecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
            ,dia,mes,ano);
            datePickerDialog.show();
        }
        if(v==bhora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }
}
