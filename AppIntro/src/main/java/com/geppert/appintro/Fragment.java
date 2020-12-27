/*
 * *
 *  * Created by Philipp Geppert on 12/27/20 12:09 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/27/20 12:09 PM
 *
 */

package com.geppert.appintro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Fragment extends androidx.fragment.app.Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_slide, container, false);
    }
}
