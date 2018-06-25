package comm.example.christianespinoza.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private  Button horas, dias, regresar;
    private Spinner spinner1,sppiner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regresar =(Button)findViewById(R.id.regresar_login) ;
        horas = (Button)findViewById(R.id.porHoras);
        dias = (Button)findViewById(R.id.porDias);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(MainActivity.this,BienvenidoActivity.class);
                startActivity(regresar);
            }
        });

        horas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent horas = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(horas);
            }
        });
        dias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dias = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(dias);
            }
        });
        spinner1 = (Spinner) findViewById(R.id.spinner);
        sppiner2 = (Spinner) findViewById(R.id.spinner3);

        String [] opciones = {"Piso 1", "Piso 2","Piso 3"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        spinner1.setAdapter(adapter);

        String [] opciones2 = {"Espacio 1", "Espacio 2","Espacio 3", "Espacio 4", "Espacio 5", "Espacio 6", "Espacio 7", "Espacio 8", "Espacio 9", "Espacio 10"};

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones2);
        sppiner2.setAdapter(adapter2);
    }
}
