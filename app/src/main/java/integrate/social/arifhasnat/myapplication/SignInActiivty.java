package integrate.social.arifhasnat.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActiivty extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    public EditText email,password;
    public Button registration;
    String emailString;
    String passwordString;

    Button resetPass,changePass,changeEmail,deleteUser,logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_actiivty);
        firebaseAuth = FirebaseAuth.getInstance();


        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editPassword);
        registration = (Button) findViewById(R.id.buttonRegiste);



        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();



            }
        });




    }

    private void signInUser(){

        emailString =email.getText().toString();
        passwordString = password.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        //checking if success



                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignInActiivty.this, "success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActiivty.this,Modify.class);
                            startActivity(intent);
                        }else{
                            //display some message here
                            Toast.makeText(SignInActiivty.this, "failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }








}
