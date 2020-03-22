package com.example.wholesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

        private SQLiteDatabase db;
        private EditText edittext_username;
        private EditText edittext_password;
        private Button button_signin;
        private Button button_signup;
        private CheckBox checkbox_signin;
        private CheckBox checkbox_signup;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            edittext_username = findViewById(R.id.login_edittext_username);
            edittext_password = findViewById(R.id.login_edittext_password);
            button_signin = findViewById(R.id.login_button_signin);
            button_signup = findViewById(R.id.login_button_signup);
            checkbox_signin = findViewById(R.id.checkbox_signin);
            checkbox_signup = findViewById(R.id.checkbox_signup);

            // check if the table exist, or create the table
            db = openOrCreateDatabase("WholeSaleDB",MODE_PRIVATE,null);
            Cursor initCursor = db.rawQuery( "SELECT * FROM sqlite_master WHERE type='table' AND name='login';",
                    null);
            if (initCursor.getCount() == 0) {
                System.out.println("Initializing tables");
                clearDB();
                initDB();
            }
            initCursor.close();

            // login
            button_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputName = edittext_username.getText().toString();
                    String inputPassword = edittext_password.getText().toString();
                    if(inputName.trim().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                        edittext_username.setBackgroundColor(Color.argb(60, 255, 0, 0));
                    }else if(inputPassword.trim().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        edittext_password.setBackgroundColor(Color.argb(60, 255, 0, 0));
                    }else{
                        String findNameStr = "SELECT password FROM login WHERE username='" + inputName + "';";
                        Cursor loginCursor = db.rawQuery(findNameStr, null);
                        if(loginCursor.getCount() == 0){
                            // doesn't find user
                            Toast.makeText(LoginActivity.this, "Username does not exist!", Toast.LENGTH_SHORT).show();
                            edittext_username.setText("");
                        }else{
                            String groundtruth = "";
                            if(loginCursor.moveToNext()){
                                groundtruth = loginCursor.getString(0);
                                System.out.println("groundtruth: " + groundtruth);
                            }else{
                                Toast.makeText(LoginActivity.this, "unknown Error", Toast.LENGTH_SHORT).show();
                            }
                            if(!groundtruth.equals(inputPassword)){
                                // doesn't match password
                                Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }else{
                                // match password
                                // updateUserInfo(groundtruth);
                                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(mainIntent);
                                LoginActivity.this.finish();
                            }
                        }
                    }

                }
            });

            button_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearDB();
                    initDB();
                }
            });
        }

    private void clearDB() {
        String dropLoginStr = "DROP TABLE IF EXISTS login;";
        //String dropUserStr = "DROP TABLE IF EXISTS user;";
        //String dropLoginStr = "DROP TABLE IF EXISTS login;";
        db.execSQL(dropLoginStr);
        System.out.println("Delete login table!");
    }

    /* set up the database*/
    private void initDB() {
        String loginStr = "CREATE TABLE login (" +
                "username TEXT, password TEXT, savename INTEGRE, autologin INTEGER, times INTEGER, " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" + ");";
        db.execSQL(loginStr);
        String insertStr = "INSERT INTO login VALUES "
                + "('forethought','Yitaipu8585#', 1, 1, 0, NULL),"
                + "('weepy','0404yyh',0, 0, 0, NULL)"
                + ";";
        db.execSQL(insertStr);
        System.out.println("Create login table!");
    }
}
