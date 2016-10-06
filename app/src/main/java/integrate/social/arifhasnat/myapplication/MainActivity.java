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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    public EditText email,password;
    public Button registration;
    String emailString;
    String passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editPassword);
        registration = (Button) findViewById(R.id.buttonRegister);


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

                /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(email.getText().toString().equals(user.getEmail().trim())) {
                    Toast.makeText(MainActivity.this, "Email is taken", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //registerUser();
                }*/

            }
        });




    }

    private void registerUser(){

        emailString =email.getText().toString();
        passwordString = password.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        //checking if success

                   /*     FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(email.getText().toString().equals(user.getEmail().trim())) {
                            Toast.makeText(MainActivity.this, "Email is taken", Toast.LENGTH_SHORT).show();
                            return;
                        }*/

                        if(task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this,SignInActiivty.class);
                            startActivity(intent);
                            //display some message here
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }else{
                            //display some message here
                            Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
