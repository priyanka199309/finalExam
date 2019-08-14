package com.example.finalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText USER,PASS;
    Button LOGIN;
    FirebaseAuth firebaseAuth;
//   public static String EMAIL_New="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        USER=findViewById(R.id.editTextEMAIL);
        PASS=findViewById(R.id.editText4PASS);
        LOGIN=findViewById(R.id.buttonLogin2);

        firebaseAuth=FirebaseAuth.getInstance();
        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInUser();
            }
        });
    }

    private void SignInUser() {
        final String email1 = USER.getText().toString().trim();
        final String password1 = PASS.getText().toString().trim();


        if (email1.isEmpty()) {
            USER.setError(getString(R.string.input_error_email));

            USER.requestFocus();
            return;

//            Toast.makeText(this, "enter Valid email", Toast.LENGTH_SHORT).show();
        }
        if (password1.isEmpty()) {
            PASS.setError(getString(R.string.input_error_password));
            PASS.requestFocus();
            return;

//            Toast.makeText(this, "enter Password", Toast.LENGTH_SHORT).show();

        }


        firebaseAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(Login.this, "user logged in successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(Login.this,Emailsend.class);
//                    intent.putExtra(EMAIL_New,email1);
//
//                    startActivity(intent);

                       startActivity(new Intent(Login.this, Emailsend.class));


//                Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                else {
                    Toast.makeText(Login.this, "Please enter Valid userName and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
