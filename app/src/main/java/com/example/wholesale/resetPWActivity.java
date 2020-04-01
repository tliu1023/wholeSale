package com.example.wholesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class resetPWActivity extends AppCompatActivity {
    private Button button_resetpw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpw);

        button_resetpw = findViewById(R.id.resetpw_button_password);
        button_resetpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resetPWActivity.this, forgetPwActivity.class);
                resetPWActivity.this.finish();
                resetPWActivity.this.startActivity(intent);
            }
        });
    }

//    private void resetPassword() {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View temp = inflater.inflate(R.layout.layout_resetpw, null);
//
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//
//        PopupWindow popup_resetpw = new PopupWindow(temp, width, height, true);
//        View view_resetpw = findViewById(R.id.view_resetpw);
//        popup_resetpw.showAtLocation(view_resetpw, Gravity.CENTER, 0, 0);
//    }
}
