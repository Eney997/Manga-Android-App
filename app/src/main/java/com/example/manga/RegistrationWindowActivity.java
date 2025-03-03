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

public class RegistrationWindowActivity extends AppCompatActivity {

    private Button btnSubmit,btnGoMain;
    private TextView nameError,lastnameError,userNameError,passwordError;
    private EditText nameInput,lastNameInput,userNameInput,passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_window);

        btnSubmit = findViewById(R.id.regSubmitButton);
        btnGoMain = findViewById(R.id.regGoBackButton);
        nameError = findViewById(R.id.nameError);
        lastnameError = findViewById(R.id.lastNameError);
        userNameError = findViewById(R.id.userNameError);
        passwordError = findViewById(R.id.passwordError);
        //et
        nameInput = findViewById(R.id.nameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);

        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationWindowActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String getNameV = String.valueOf(nameInput.getText());
                String getLastNameV = String.valueOf(lastNameInput.getText());
                String getUsrNameV = String.valueOf(userNameInput.getText());
                String getPasswordV = String.valueOf(passwordInput.getText());

                //----------------------------------------------------------------forbid space start
                if(forbidSpace(getNameV))
                {
                    nameError.setVisibility(View.VISIBLE);
                    nameError.setText("Name:Space detected.");
                    return;
                }
                else nameError.setVisibility(View.GONE);

                if(forbidSpace(getLastNameV))
                {
                    lastnameError.setVisibility(View.VISIBLE);
                    lastnameError.setText("LastName:Space detected.");
                    return;
                }
                else lastnameError.setVisibility(View.GONE);

                if(forbidSpace(getUsrNameV))
                {
                    userNameError.setVisibility(View.VISIBLE);
                    userNameError.setText("UserName:Space detected.");
                    return;
                }
                else userNameError.setVisibility(View.GONE);


                if(forbidSpace(getPasswordV))
                {
                    passwordError.setVisibility(View.VISIBLE);
                    passwordError.setText("Password:Space detected.");
                    return;
                }
                else passwordError.setVisibility(View.GONE);

                //------------------------------------------------------------------forbid space end

                //---------------------------------------------------------if inputs are empty start
                if(getNameV.isEmpty())
                {
                    nameError.setVisibility(View.VISIBLE);
                    nameError.setText("Name:Empty input.");
                    return;
                }
                else nameError.setVisibility(View.GONE);

                if(getLastNameV.isEmpty())
                {
                    lastnameError.setVisibility(View.VISIBLE);
                    lastnameError.setText("LastName:Empty input.");
                    return;
                }
                else lastnameError.setVisibility(View.GONE);

                if(getUsrNameV.isEmpty())
                {
                    userNameError.setVisibility(View.VISIBLE);
                    userNameError.setText("UserName:Empty input.");
                    return;
                }
                else userNameError.setVisibility(View.GONE);


                if(getPasswordV.isEmpty())
                {
                    passwordError.setVisibility(View.VISIBLE);
                    passwordError.setText("Password:Empty input.");
                    return;
                }
                else passwordError.setVisibility(View.GONE);

                //----------------------------------------------------------if inputs are empty Ends

                //--------------------name and lastname contains only letters and nothing else start
                if(containsOnlyLetters(getNameV))
                {
                    nameError.setVisibility(View.VISIBLE);
                    nameError.setText("Name:Use only letters please.");
                    return;
                }
                else nameError.setVisibility(View.GONE);

                if(containsOnlyLetters(getLastNameV))
                {
                    lastnameError.setVisibility(View.VISIBLE);
                    lastnameError.setText("LastName:Use only letters please.");
                    return;
                }
                else lastnameError.setVisibility(View.GONE);
                //---------------------name and lastname contains only letters and nothing else Ends

                //--------checks if first letter is upcast or not in name and lastName section start
                if(capitalLetterFirst(getNameV))
                {
                    nameError.setVisibility(View.VISIBLE);
                    nameError.setText("Name:First letter should be uppercase.");
                    return;
                }else nameError.setVisibility(View.GONE);

                if(capitalLetterFirst(getLastNameV))
                {
                    lastnameError.setVisibility(View.VISIBLE);
                    lastnameError.setText("LastName:First letter should be uppercase.");
                    return;
                }else lastnameError.setVisibility(View.GONE);

                //---------checks if first letter is upcast or not in name and lastName section ends

                //---------------------------------------------forbid uppercase after 1 letter start
                if(fullStringCantBeCapital(getNameV))
                {
                    nameError.setVisibility(View.VISIBLE);
                    nameError.setText("Name:Only first letter should be uppercase.");
                    return;
                }else nameError.setVisibility(View.GONE);

                if(fullStringCantBeCapital(getLastNameV))
                {
                    lastnameError.setVisibility(View.VISIBLE);
                    lastnameError.setText("LastName:Only first letter should be uppercase.");
                    return;
                }else lastnameError.setVisibility(View.GONE);

                //-----------------------------------------------forbid uppercase after 1 letter end


                //--------------------------------userName input allows only letters and numbers start
                if(userCanWriteOnlyNumAndLet(getUsrNameV))
                {
                    userNameError.setVisibility(View.VISIBLE);
                    userNameError.setText("UserName:Use only numbers and letters.");
                    return;
                }
                else userNameError.setVisibility(View.GONE);
                //----------------------------------userName input allows only letters and numbers end

                //------------------------length of username and password inputs are minimum 6 start
                if(minimumLengthForUserName(getUsrNameV))
                {
                    userNameError.setVisibility(View.VISIBLE);
                    userNameError.setText("UserName:Minimum 6 character.");
                    return;
                }
                else userNameError.setVisibility(View.GONE);

                if(minimumLengthForUserName(getPasswordV))
                {
                    passwordError.setVisibility(View.VISIBLE);
                    passwordError.setText("Password:Minimum 6 character.");
                    return;
                }
                else passwordError.setVisibility(View.GONE);
                //--------------------------length of username and password inputs are minimum 6 end

                //----------------------password must : letters,numbers and special characters start
                if(passwordMust(getPasswordV))
                {
                    passwordError.setVisibility(View.VISIBLE);
                    passwordError.setText("Password:Use letter,number and characters.");
                    return;
                }
                else passwordError.setVisibility(View.GONE);
                //------------------------password must : letters,numbers and special characters end

                //-----------------------------------------------------------------SQLite con start
                //if username exists
                DatabaseHelper databaseHelper = new DatabaseHelper(RegistrationWindowActivity.this);

                if (databaseHelper.isUsernameTaken(getUsrNameV)) {
                    userNameError.setVisibility(View.VISIBLE);
                    userNameError.setText("UserName:Username already exists.");
                    return;
                }else userNameError.setVisibility(View.GONE);

                databaseHelper.addInformation(getNameV,getLastNameV,getUsrNameV,getPasswordV);
                //-------------------------------------------------------------------SQLite con end

                //make inputs empty after registration
                nameInput.setText("");
                lastNameInput.setText("");
                userNameInput.setText("");
                passwordInput.setText("");
            }
        });

    }

    //only letters
    public static boolean containsOnlyLetters(String str) {
        // run in characters of the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122))) {
                return true; // Return 1 if num found
            }
        }
        return false; // Return 0 if all good
    }

    //--------------------------CHECKS IF FIRS LETTER IS UPPERCASE OR NOT IN NAME AND LASTNAME INPUT
    public static boolean capitalLetterFirst(String str)
    {
        char firstChar = str.charAt(0);
        return firstChar < 65 || firstChar > 90;
    };

    //forbid uppercase after 1 letter
    public static boolean fullStringCantBeCapital(String str)
    {
        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar >= 65 && currentChar <= 90) {
                return true;
            }
        }
        return false;
    }
    //-----------------------------------------------------------DOESN'T ALLOWS SPACE IN EVERY INPUT
    public static boolean forbidSpace(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar == 32) {
                return true;
            }
        }
        return false;
    }

    //---------------------------------IN USERNAME INPUT ALLOWS TO BE ENTERED ONLY NUMBER AND LETTER
    public static boolean userCanWriteOnlyNumAndLet(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if (!((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122 || (ch) >= 48 && ch <= 57)))
            {
                return true;
            }
        }
        return false;
    }

    //-------------------------------------------------------------------------------COUNT LETTERS 6
    public static boolean minimumLengthForUserName(String str)
    {
        int minimum = 6;
        int currentStrLen = str.length();
        for(int i = 0;i<currentStrLen;i++)
        {
            if(currentStrLen < minimum)
            {
                return true;
            }
        }
        return false;
    }

    //---------------------------------------------PASSWORD MUST CONTAIN NUMBER LETTER AND CHARACTER
    public static boolean passwordMust(String str) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }
        //If all conditions are met, return 1 (valid)
        return !hasLetter || !hasDigit || !hasSpecialChar;
        // Otherwise, return 0 (invalid)
    }
}