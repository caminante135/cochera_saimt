package comm.example.christianespinoza.splash;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.app.ProgressDialog;
import android.widget.ImageView;


public class BienvenidoActivity extends AppCompatActivity {

    public  static final String user="names";
    private Button reserva, subiriamgen;
    TextView txtUsario;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private static final int GALLERY_INTENT = 1;
    private ImageView mImageview;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);


        reserva = (Button)findViewById(R.id.ir_reservas);
        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reserva = new Intent(BienvenidoActivity.this,MainActivity.class);
                startActivity(reserva);
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        subiriamgen = (Button) findViewById(R.id.btn_subir);
        mImageview= (ImageView) findViewById(R.id.SubirImagen);
        mProgressDialog = new ProgressDialog(this);
        subiriamgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });

        txtUsario = (TextView)findViewById(R.id.textUser);
        String user = getIntent().getStringExtra("names");
        txtUsario.setText("¡ Bienvenido " + user + "! " );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setTitle("Subiendo...");
            mProgressDialog.setMessage("Subiendo Foto a FIREBASE");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            Uri uri = data.getData();
            StorageReference filepath = storageReference.child("fotos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgressDialog.dismiss();
                    Uri descargarfoto = taskSnapshot.getDownloadUrl();

                    Glide.with(BienvenidoActivity.this)
                            .load(descargarfoto)
                            .fitCenter()
                            .centerCrop()
                            .into(mImageview);

                    Toast.makeText(BienvenidoActivity.this,"Se subio exitosamente la foto",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void signOut(View view  ) {
        firebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,RegistrarActivity.class));

    }


}
