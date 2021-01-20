package com.example.shaperecogition;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    GestureLibrary lib = null;
   TextView  txtResult =null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult=(TextView)findViewById(R.id.txtresult);
        lib=GestureLibraries.fromRawResource(this,R.raw.gestures);
        if(!lib.load())
            finish();
        GestureOverlayView gesture=(GestureOverlayView)findViewById(R.id.gesture);
       gesture.addOnGesturePerformedListener(new OnGesturePerformedListener() {
           @Override
           public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
               ArrayList<Prediction> predictionArrayList=lib.recognize(gesture);
                   if (predictionArrayList.size() > 0 && predictionArrayList.get(0).score > 1.0) {
                       String action = predictionArrayList.get(0).name;
                       txtResult.setText(action);
                   }
           }
       });
    }
}