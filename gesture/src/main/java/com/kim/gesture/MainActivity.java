package com.kim.gesture;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    @Bind(R.id.edit)
    EditText edit;
    @Bind(R.id.gestures)
    GestureOverlayView gesture;
    private GestureLibrary library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!library.load()) {
            finish();
        }
        gesture.addOnGesturePerformedListener(this);

    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        List<Prediction> gestures = library.recognize(gesture);

        int index = 0;
        double score = 0.0;
        String str = "";
        for (int i = 0; i < gestures.size(); i++) {
            Prediction result = gestures.get(i);
            if (result.score > score) {
                index = i;
                score = result.score;
            }
            str += gestures.get(i) + ":" + result.score + "\n";
        }
        edit.setText(edit.getText().toString().trim() + gestures.get(index).name);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}