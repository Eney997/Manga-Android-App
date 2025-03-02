package com.example.manga;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

public class SlideActivity extends AppCompatActivity {

    public static ViewPager viewPager;
    SlideViewPageAdapted adapted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        viewPager = findViewById(R.id.viewPager);
        adapted = new SlideViewPageAdapted(this);
        viewPager.setAdapter(adapted);

        if(isAlreadyOpen())
        {
            Intent i = new Intent(SlideActivity.this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }else
        {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = getSharedPreferences("slide",MODE_PRIVATE).edit();
            editor.putBoolean("slide",true);
            editor.apply();
        }

    }

    private boolean isAlreadyOpen()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("slide",MODE_PRIVATE);
        return sharedPreferences.getBoolean("slide",false);
    }

}