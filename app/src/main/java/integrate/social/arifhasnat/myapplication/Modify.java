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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Modify extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    public EditText email,password;
    public Button registration;
    String emailString;
    String passwordString;

    Button resetPass,changePass,changeEmail,deleteUser,logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editTextEmailModify);
        password = (EditText) findViewById(R.id.editPassword);
        registration = (Button) findViewById(R.id.buttonRegiste);
        resetPass= (Button) findViewById(R.id.buttonResetPassword);
        changePass = (Button) findViewById(R.id.buttonChangePassword);
        changeEmail = (Button) findViewById(R.id.buttonChangeEmail);
        deleteUser = (Button) findViewById(R.id.buttonDeleteUser);
        logOut = (Button) findViewById(R.id.buttonLogOut);

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPass();
            }
        });


        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changePass();
            }
        });
       changeEmail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               changeEmil();

           }
       });


        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteUser();

                Intent intent = new Intent(Modify.this,MainActivity.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();

            }
        });
    }


    public void deleteUser(){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Modify.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Modify.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public  void changeEmil(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(email.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Modify.this, "Email address is updated.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Modify.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void changePass(){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(email.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Modify.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Modify.this, "Failed to update password!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    public void resetPass(){

        firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Modify.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Modify.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }

}
