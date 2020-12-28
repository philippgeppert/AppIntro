package com.geppert.appintromaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geppert.appintro.IntroBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> heading = new ArrayList<>();
        heading.add("Title One");
        heading.add("Title Two");
        heading.add("Title Three");
        heading.add("Title Four");

        List<String> description = new ArrayList<>();
        description.add("Description One");
        description.add("Description Two");
        description.add("Description Three");
        description.add("Description Four");

        List<Integer> drawables = new ArrayList<>();
        drawables.add(R.drawable.ic_launcher_foreground);
        drawables.add(R.drawable.ic_launcher_foreground);
        drawables.add(R.drawable.ic_launcher_foreground);
        drawables.add(R.drawable.ic_launcher_foreground);


        IntroBuilder introBuilder = new IntroBuilder();
        introBuilder.setHeadings(heading);
        introBuilder.setDescriptions(description);
        introBuilder.setDrawables(drawables);
        introBuilder.nextActivity(StartActivity.class);
        introBuilder.start(this);
    }
}