package com.example.finalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class MainActivity extends AppCompatActivity {


    EditText UserName,Password;
    Button Register;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserName=findViewById(R.id.editTextEmail);
        Password=findViewById(R.id.editTextPassword);
        Register=findViewById(R.id.buttonLogin);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               LoginUser();
            }
        });
    }

    private void LoginUser() {

        final String email = UserName.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        if (email.isEmpty()) {
            UserName.setError(getString(R.string.input_error_email));
            UserName.requestFocus();
            return;

//            Toast.makeText(MainActivity.this, "enter Valid email", Toast.LENGTH_SHORT).show();
        }
        if (password.isEmpty()) {

            Password.setError(getString(R.string.input_error_password));
            Password.requestFocus();
            return;


//            Toast.makeText(MainActivity.this, "enter Password", Toast.LENGTH_SHORT).show();

        }

        if (password.length() < 6) {
            Password.setError(getString(R.string.input_error_password_length));
            Password.requestFocus();
            return;

//            Toast.makeText(MainActivity.this, "enter Password greater than 6 digits", Toast.LENGTH_SHORT).show();

        }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "sucessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Login.class));

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }
        });




    }
}
