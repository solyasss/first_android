package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int index = 0;
    private String[] song_lyrics = {
            "Song: Walking down the old street, lights are fading low...",
            "Song: In my neighborhood, dreams still glow!",
            "Song: Faces change but the heart remains...",
            "Song: In my neighborhood, dreams still glow!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSong = findViewById(R.id.btnSong);

        btnSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast(song_lyrics[index]);
                index++;
                if (index >= song_lyrics.length) index = 0;
            }
        });
    }

    private void showCustomToast(String message) {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 200);

        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(30, 20, 30, 20);
        layout.setBackgroundResource(R.drawable.toast_bg);

        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.watermelon); // 🍉

        Button textView = new Button(getApplicationContext());
        textView.setText(message);
        textView.setTextColor(0xFFFFFFFF);
        textView.setBackgroundColor(0x00000000);
        textView.setTextSize(16f);

        layout.addView(img);
        layout.addView(textView);
        toast.setView(layout);
        toast.show();
    }
}
