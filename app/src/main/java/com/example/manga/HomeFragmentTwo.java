package com.example.manga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeFragmentTwo extends AppCompatActivity {

    private ImageView imgGoback;
    private String name,description;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment_two);

        // Retrieve data from intent
        name = getIntent().getStringExtra("Name");
        image = getIntent().getIntExtra("Image", 0);
        description = getIntent().getStringExtra("Description");

        // Find views
        TextView nameTextView = findViewById(R.id.textViewCp);
        ImageView imageView = findViewById(R.id.imageViewCp);
        TextView descriptionTextView = findViewById(R.id.textViewCp2);

        // Set data
        nameTextView.setText(name);
        imageView.setImageResource(image);
        descriptionTextView.setText(description);

        imgGoback = findViewById(R.id.gotomainrecview);

        imgGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
