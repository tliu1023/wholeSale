package com.example.wholesale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class forgetPwActivity extends AppCompatActivity {
    private EditText edittext_username;
    private Button button_submit;
    private TextView textview_nonexist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pw);

        edittext_username = findViewById(R.id.forgetPw_edittext_username);
        button_submit = findViewById(R.id.forgetPw_button_submit);
        textview_nonexist = findViewById(R.id.forgetPw_textview_nonexist);

        final String inputName = edittext_username.getText().toString();
        edittext_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !inputName.trim().isEmpty()){
                    textview_nonexist.setVisibility(View.VISIBLE);
                }
                if(hasFocus){
                    textview_nonexist.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
