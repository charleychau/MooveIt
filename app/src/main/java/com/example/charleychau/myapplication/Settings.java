package com.example.charleychau.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    private ToggleButton musicToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        musicToggle = (ToggleButton) findViewById(R.id.music_toggle);

        musicToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked) {
                    //toggle enabled
                }
                else {
                    //toggle disabled
                }
            }
        });
    }
}

