package com.example.manga;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin,btnRegister;
    private EditText userNameInput,passwordInput;
    private TextView userNameError,passwordError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.loginButton);
        btnRegister = findViewById(R.id.registerButton);
        userNameInput = findViewById(R.id.etuserNm);
        passwordInput = findViewById(R.id.etPass);
        userNameError = findViewById(R.id.usrErrMainWindow);
        passwordError = findViewById(R.id.pasErrMainWindow);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegistrationWindowActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String userNameSet = String.valueOf(userNameInput.getText());
                String passwordSet = String.valueOf(passwordInput.getText());

                if (userNameSet.isEmpty())
                {
                    userNameError.setVisibility(View.VISIBLE);
                    userNameError.setText("UserName:Empty input.");
                    return;
                }else userNameError.setVisibility(View.GONE);

                if(passwordSet.isEmpty())
                {
                    passwordError.setVisibility(View.VISIBLE);
                    passwordError.setText("Password:Empty input.");
                    return;
                }else passwordError.setVisibility(View.GONE);

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                if(!databaseHelper.loginUser(userNameSet))
                {
                    userNameError.setVisibility(View.VISIBLE);
                    userNameError.setText("UserName:Incorrect username.");
                    return;
                }else userNameError.setVisibility(View.GONE);

                if(!databaseHelper.loginPassword(passwordSet))
                {
                    passwordError.setVisibility(View.VISIBLE);
                    passwordError.setText("Password:Incorrect password.");
                    return;
                }else passwordError.setVisibility(View.GONE);

                Intent i = new Intent(MainActivity.this,UserPage.class);
                i.putExtra("input_text", userNameSet); // Passing userName text
                startActivity(i);
            }
        });

    }

}