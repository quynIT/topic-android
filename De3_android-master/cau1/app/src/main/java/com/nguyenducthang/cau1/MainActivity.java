package com.nguyenducthang.cau1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenducthang.cau1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    int percent;
    int count;
    int flag;
    List<Integer> digits = new ArrayList<>();
    Handler handler = new Handler();
    Random random = new Random();

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
        // Lấy và phân tách các chữ số trong số điện thoại
        String phoneNumber = binding.edtViewNumber.getText().toString();
        digits.clear();
        for (char ch : phoneNumber.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.add(Character.getNumericValue(ch));
            }
        }

        int numberOfViews = digits.size();
        count = 0;
        percent = 0;
        flag = 0;

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= numberOfViews; i++) {
                    ++count;
                    percent = i * 100 / numberOfViews;
                    if (count % 2 == 0 || i == numberOfViews) {
                        flag++;
                        handler.post(foregroundThread);
                    }
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }

    //Main/UI - Thread
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            binding.txtPercent.setText(percent + " %");
            binding.pbPercent.setProgress(percent);

            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            buttonParams.setMargins(5, 10, 5, 10);

            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setWeightSum(3);

            for (int j = 0; j < 3; ++j) {
                Button button = new Button(MainActivity.this);
                button.setLayoutParams(buttonParams);
                button.setBackgroundColor(Color.rgb(0, 255, 0));
                button.setText(String.valueOf(digits.get(random.nextInt(digits.size())))); // Random digit from the input list
                button.setTextSize(22);
                button.setTextColor(Color.WHITE);
                linearLayout.addView(button);
            }

            binding.container.addView(linearLayout);

            if (percent == 100) {
                binding.txtPercent.setText("DONE!");
            }
        }
    };
}
