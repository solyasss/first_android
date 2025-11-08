package com.example.first;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private int appLaunchCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setBackgroundColor(Color.parseColor("#F8BBD0"));
        mainLayout.setGravity(Gravity.CENTER);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TextView title = new TextView(this);
        title.setText("Messenger");
        title.setTextSize(28);
        title.setTextColor(Color.parseColor("#AD1457"));
        mainLayout.addView(title);

        setContentView(mainLayout);

        if (appLaunchCount >= 10) {
            showRatingDialog();
        }
    }

    private void showRatingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🌸 Tell us what you think");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(60, 40, 60, 20);
        layout.setBackgroundColor(Color.parseColor("#F8BBD0"));
        layout.setGravity(Gravity.CENTER);

        TextView message = new TextView(this);
        message.setText("How would you rate your experience?");
        message.setTextColor(Color.parseColor("#4A148C"));
        message.setTextSize(18);
        message.setPadding(0, 0, 0, 30);
        layout.addView(message);

        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(1);
        ratingBar.setRating(0);
        ratingBar.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(ratingBar);

        builder.setView(layout);

        builder.setPositiveButton("Submit", (dialog, which) -> {
            int rating = (int) ratingBar.getRating();
            if (rating >= 4) {
                showPlayStoreDialog();
            } else if (rating > 0) {
                showFeedbackDialog();
            } else {
                Toast.makeText(this, "Please select stars 🌟", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Later", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showFeedbackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("💌 We’re sorry to hear that");
        builder.setMessage("Would you like to share what we can improve?");
        builder.setPositiveButton("Yes", (dialog, which) ->
                Toast.makeText(this, "Thanks for your feedback! 💖", Toast.LENGTH_LONG).show());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showPlayStoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🌟 Thank you!");
        builder.setMessage("Would you like to rate us on Google Play?");
        builder.setPositiveButton("Go to Play Store", (dialog, which) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.example.first"));
            startActivity(intent);
        });
        builder.setNegativeButton("Maybe later", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
