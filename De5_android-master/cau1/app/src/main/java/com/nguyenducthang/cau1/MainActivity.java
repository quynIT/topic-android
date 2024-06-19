package com.nguyenducthang.cau1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenducthang.cau1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;

    int percent;
    int randNumb;
    int count;
    int flag;
    Random random = new Random();
    Handler handler = new Handler();

    //Main/UI - Thread
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            binding.txtPercent.setText(percent + " %");
            binding.pbPercent.setProgress(percent);

            LinearLayout.LayoutParams params_1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150, 20);
            LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150, 10);
            params_1.setMargins(5, 10, 5, 10);
            params_2.setMargins(5, 10, 5, 10);
            LinearLayout.LayoutParams ln_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            linearLayout.setLayoutParams(ln_params);
            linearLayout.setWeightSum(30);
            Log.i("Hello", String.valueOf(count));
            for (int j = 1; j <= 2; ++j) {
                Button button = new Button(MainActivity.this);
                if (flag % 2 == 0) {
                    if (j % 2 == 0) {
                        button.setBackgroundColor(Color.rgb(255, 0, 0));
                        button.setLayoutParams(params_1);
                    } else {
                        button.setBackgroundColor(Color.rgb(0, 255, 0));
                        button.setLayoutParams(params_2);
                    }
                } else {
                    if (j % 2 == 0) {
                        button.setBackgroundColor(Color.rgb(255, 0, 0));
                        button.setLayoutParams(params_2);
                    } else {
                        button.setBackgroundColor(Color.rgb(0, 255, 0));
                        button.setLayoutParams(params_1);
                    }
                }

                //button.setLayoutParams(params);
                button.setText(String.valueOf(randNumb));
                button.setTextSize(22);
                button.setTextColor(Color.WHITE);
                linearLayout.addView(button);
            }
            binding.container.addView(linearLayout);

            if (percent == 100) {
                binding.txtPercent.setText("DONE!");
            }
        };

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
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
        //
        binding.container.removeAllViews();
        int numberOfViews = Integer.parseInt(binding.edtViewNumber.getText().toString());
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =1;i<= numberOfViews; i++){
                    ++count;
                    percent = i * 100/numberOfViews;
                    randNumb = random.nextInt(100);
                    if(count % 2==0 || i == numberOfViews){
                        flag ++;
                        handler.post(foregroundThread);
                    }
                    SystemClock.sleep(100);
                }

            }
        });
        backgroundThread.start();
    }
   // @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//    }
}