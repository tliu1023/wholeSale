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
        private CheckBox checkbox_autologin;
        private CheckBox checkbox_remember;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            edittext_username = findViewById(R.id.login_edittext_username);
            edittext_password = findViewById(R.id.login_edittext_password);
            button_signin = findViewById(R.id.login_button_signin);
            button_signup = findViewById(R.id.login_button_signup);
            checkbox_autologin = findViewById(R.id.checkbox_autologin);
            checkbox_remember = findViewById(R.id.checkbox_remember);

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

            // last time log in
            String username = "";
            Boolean isSavename = false;
            Boolean isAutologin = false;
            db = openOrCreateDatabase("WholeSaleDB",MODE_PRIVATE,null);
            Cursor loginCursor = db.rawQuery( "SELECT * FROM lastlogin ;",
                    null);
            System.out.println("count:" + loginCursor.getCount());
            if (loginCursor.getCount() != 1) {
                System.out.println("initialize lastlogin table");
            }else{
                if(loginCursor.moveToNext()){
                    username = loginCursor.getString(0);
                    isSavename = loginCursor.getInt(1) == 1;
                    isAutologin = loginCursor.getInt(2) == 1;
                    System.out.println("isSavename:" + isSavename);
                    System.out.println("isAutologin:" + isAutologin);
                    if(isAutologin){
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        mainIntent.putExtra("username", username);
                        LoginActivity.this.startActivity(mainIntent);
                        LoginActivity.this.finish();
                    }else if(isSavename){
                        edittext_username.setText(username);
                        checkbox_remember.setChecked(true);
                    }
                }
            }
            loginCursor.close();

            // press signin button
//            final Boolean finalIssavename = isSavename;
//            final Boolean finalIsAutologin = isAutologin;
            button_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputName = edittext_username.getText().toString();
                    String inputPassword = edittext_password.getText().toString();
                    Boolean finalIssavename = checkbox_remember.isChecked();
                    Boolean finalIsAutologin = checkbox_autologin.isChecked();
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

                                // update last time login
                                String droplastloginStr = "DROP TABLE IF EXISTS lastlogin;";
                                db.execSQL(droplastloginStr);

                                String lastLoginStr = "CREATE TABLE lastlogin (" +
                                        "username TEXT, savename INTEGRE, autologin INTEGER, " +
                                        "_id INTEGER PRIMARY KEY AUTOINCREMENT" + ");";
                                db.execSQL(lastLoginStr);

                                String insertStr = "INSERT INTO lastlogin VALUES ('" +
                                        inputName + "', " +
                                        ( finalIssavename ? 1 : 0 ) + ", " +
                                        ( finalIsAutologin ? 1 : 0 ) + ", " +
                                        "NULL );";
                                db.execSQL(insertStr);

                                // start main activity
                                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                mainIntent.putExtra("username", groundtruth);
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
        String droplastloginStr = "DROP TABLE IF EXISTS lastlogin;";
        db.execSQL(droplastloginStr);
        System.out.println("Delete login table!");
    }

    /* set up the database*/
    private void initDB() {
        // user login db
        String loginStr = "CREATE TABLE login (" +
                "username TEXT PRIMARY KEY, password TEXT, times INTEGER" + ");";
        db.execSQL(loginStr);
        String insertStr = "INSERT INTO login VALUES "
                + "('forethought','Yitaipu8585#', 0),"
                + "('weepy','0404yyh', 0)"
                + ";";
        db.execSQL(insertStr);
        System.out.println("Create login table!");

        // last time login
        String lastLoginStr = "CREATE TABLE lastlogin (" +
                "username TEXT, savename INTEGRE, autologin INTEGER, " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" + ");";
        db.execSQL(lastLoginStr);
//        insertStr = "INSERT INTO login VALUES "
//                + "('forethought',1, 1, NULL)"
//                + ";";
//        db.execSQL(insertStr);
        System.out.println("Create last login table!");
    }
}