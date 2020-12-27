/*
 * *
 *  * Created by Philipp Geppert on 12/27/20 12:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/27/20 12:32 PM
 *
 */

package com.geppert.appintro;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;
import java.util.List;

public class IntroBuilder implements Serializable {


    public static List<Integer> DrawableList;
    public static List<String> HeaderList;
    public static List<String> DescriptionList;
    public static Class nextActivity;

    public void nextActivity(Class activity) {
        nextActivity = activity;
    }

    public void setHeadings(List<String> headings) {
        HeaderList = headings;
    }

    public void setDescriptions(List<String> descriptions) {
        DescriptionList = descriptions;
    }

    public void setDrawables(List<Integer> drawables) {
        DrawableList = drawables;
    }

    public void start(Activity context) {
        context.startActivity(new Intent(context, WelcomeActivity.class));
        context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
