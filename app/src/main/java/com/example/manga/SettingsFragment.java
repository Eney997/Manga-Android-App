package com.example.manga;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    private ImageView btnLongOut;
    private TextView userName;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Find the button by ID
        btnLongOut = view.findViewById(R.id.exitAcc);
        userName = view.findViewById(R.id.usernameSaver);

        // Retrieve the username from Bundle
        Bundle args = getArguments();
        String receivedText = (args != null) ? args.getString("input_text") : "Guest"; // Default "Guest" if null

        // Display username in TextView
        userName.setText(receivedText);

        btnLongOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                // optionally, finish the current activity if you don't want to keep the settings screen
                getActivity().finish();
            }
        });
        return view;
    }
}
