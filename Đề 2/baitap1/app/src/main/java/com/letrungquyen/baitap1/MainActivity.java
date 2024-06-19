package com.letrungquyen.baitap1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.letrungquyen.baitap1.databinding.ActivityMainBinding;
import androidx.appcompat.app.AppCompatActivity;




import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    int percent;
    int count;
    int flag;
    Random random = new Random();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.container.removeAllViews();
                execBackGroundThread();
            }
        });
    }

    private void execBackGroundThread() {
        binding.container.removeAllViews();
        int numberOfViews = Integer.parseInt(binding.edtViewNumber.getText().toString());
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= numberOfViews; i++) {
                    final int index = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (index % 2 != 0) { // Odd rows
                                EditText editText = new EditText(MainActivity.this);
                                editText.setLayoutParams(new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                ));
                                editText.setText(String.valueOf(random.nextInt(101))); // Random number between 0 and 100
                                binding.container.addView(editText);
                            } else { // Even rows
                                Button button = new Button(MainActivity.this);
                                button.setLayoutParams(new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                ));
                                button.setText(String.valueOf(random.nextInt(101))); // Random number between 0 and 100
                                binding.container.addView(button);
                            }
                        }
                    });

                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }
}
