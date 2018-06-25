package comm.example.christianespinoza.splash;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {


    Button regrear2, bfecha1, bfecha2;
    EditText efecha1, efecha2;
    private int dia,mes,ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        regrear2 = (Button)findViewById(R.id.inicio2);
        regrear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar2 = new Intent(Main3Activity.this,MainActivity.class);
                startActivity(regresar2);
            }
        });

        bfecha1=(Button)findViewById(R.id.button_fecha1);
        bfecha2=(Button)findViewById(R.id.button_fecha2);
        efecha1=(EditText)findViewById(R.id.editFecha1);
        efecha2=(EditText)findViewById(R.id.editFecha2);
        bfecha1.setOnClickListener(this);
        bfecha2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==bfecha1){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efecha1.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }

        if(v==bfecha2){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efecha2.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
    }
}
