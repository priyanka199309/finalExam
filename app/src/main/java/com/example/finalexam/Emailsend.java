package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Emailsend extends AppCompatActivity {

    EditText _email,subject,BODY;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailsend);
        _email=findViewById(R.id.editTextEmailSend);
        subject=findViewById(R.id.editTextSubject);
        BODY=findViewById(R.id.editTextBody);
        send=findViewById(R.id.buttonSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EmailSend();
            }
        });
    }

    private void EmailSend() {

       String emaill = _email.getText().toString().trim();
        final String SUBJECT = subject.getText().toString().trim();
        final String bodyy = BODY.getText().toString().trim();


        if (emaill.isEmpty()) {
            _email.setError(getString(R.string.input_error_email));

            _email.requestFocus();
            return;

//            Toast.makeText(this, "enter Valid email", Toast.LENGTH_SHORT).show();
        }
        if (SUBJECT.isEmpty()) {
            subject.setError(getString(R.string.input_error_subject));
            subject.requestFocus();
            return;

//            Toast.makeText(this, "enter Subject", Toast.LENGTH_SHORT).show();

        }
        if (bodyy.isEmpty()) {
            BODY.setError(getString(R.string.input_error_body));
            BODY.requestFocus();
            return;

//            Toast.makeText(this, "enter body", Toast.LENGTH_SHORT).show();

        }


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] { ""+emaill });
        intent.putExtra(Intent.EXTRA_SUBJECT, ""+SUBJECT);
       intent.putExtra(Intent.EXTRA_TEXT, ""+bodyy);
        startActivity(Intent.createChooser(intent, "Title of the chooser dialog"));


//
//
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { ""+emaill });
//        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { ""+SUBJECT });
////        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");


    }
}
