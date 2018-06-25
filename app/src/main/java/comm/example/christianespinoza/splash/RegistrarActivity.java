package comm.example.christianespinoza.splash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseUser;


public class RegistrarActivity extends AppCompatActivity {

    private EditText Textemail;
    private EditText Textcontraseña;
    private Button botonregistrar, botonlogin;
    private ProgressDialog progressDialog;

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    //un objeto firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        firebaseAuth = FirebaseAuth.getInstance();

        Textemail = (EditText) findViewById(R.id.txtUsuario);
        Textcontraseña = (EditText) findViewById(R.id.txtContraseña);
        //botonregistrar = (Button) findViewById(R.id.btnRegistrar);
        //botonlogin = (Button) findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);

        //conectamos el boton
        //botonregistrar.setOnClickListener(this);
        //botonlogin.setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {
            Toast.makeText(getApplicationContext(),R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
            Toast.makeText(getApplicationContext(),R.string.error_login,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainScreen() {
        Intent intent = new Intent(this,SplashActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public   void registrarUsuario(View view){

        String email = Textemail.getText().toString().trim();
        String password = Textcontraseña.getText().toString().trim();

        //verificar que las cajas ded texto no esten vacioas
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe Ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrarActivity.this,"Se ha registrado el usuario con el email: "+ Textemail.getText(),Toast.LENGTH_LONG).show();
                        }
                        else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(RegistrarActivity.this,"El usuario ya Existe",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegistrarActivity.this,"No se pudo registrar el usuario",Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    public  void loguearUsuario(View view){
        final String email = Textemail.getText().toString().trim();
        String password = Textcontraseña.getText().toString().trim();

        //verificar que las cajas ded texto no esten vacioas
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe Ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();
        // loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            int pos = email.indexOf("@");
                            String user = email.substring(0,pos);
                            Toast.makeText(RegistrarActivity.this,"Bienvenido: "+ Textemail.getText(),Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(),BienvenidoActivity.class);
                            intencion.putExtra(BienvenidoActivity.user, user);
                            startActivity(intencion);
                        }
                        else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(RegistrarActivity.this,"el usuario no existe",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegistrarActivity.this,"error contraseña",Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }



    //public void Registrar(View view) {
        //registrarUsuario();
    //}



}


