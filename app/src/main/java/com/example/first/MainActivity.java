package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button[][] buttons = new Button[3][3];
    private boolean playerX_turn = true;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        Button btnNewGame = findViewById(R.id.btn_new_game);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                Button btn = (Button) gridLayout.getChildAt(index);
                buttons[i][j] = btn;
                btn.setOnClickListener(this::onCellClick);
            }
        }

        btnNewGame.setOnClickListener(v -> resetGame());
    }

    private void onCellClick(View view) {
        Button button = (Button) view;

        if (!button.getText().toString().equals("") || gameOver) {
            return;
        }

        Animation scale = new ScaleAnimation(
                0.8f, 1f, 0.8f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        button.startAnimation(scale);

        if (playerX_turn) {
            button.setText("X");
            button.setTextColor(0xFFE91E63);
        } else {
            button.setText("O");
            button.setTextColor(0xFF9C27B0);
        }

        if (checkWinner()) {
            gameOver = true;
            Toast.makeText(this, (playerX_turn ? "X wins!" : "O wins!"), Toast.LENGTH_SHORT).show();
        } else {
            playerX_turn = !playerX_turn;
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkEqual(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (checkEqual(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        return checkEqual(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                checkEqual(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkEqual(Button b1, Button b2, Button b3)
    {
        String s1 = b1.getText().toString();
        String s2 = b2.getText().toString();
        String s3 = b3.getText().toString();
        return !s1.equals("") && s1.equals(s2) && s2.equals(s3);
    }

    private void resetGame() {
        for (Button[] row : buttons) {
            for (Button b : row) {
                b.setText("");
                b.setEnabled(true);
            }
        }
        playerX_turn = true;
        gameOver = false;
    }
}
