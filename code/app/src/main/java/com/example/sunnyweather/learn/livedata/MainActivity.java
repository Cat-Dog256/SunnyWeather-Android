package com.example.sunnyweather.learn.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sunnyweather.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private Button plusOneButton;
    private TextView textView;
    private Button clearButton;
    private Button getUserButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vm_sample);
        this.getLifecycle().addObserver(new MainObserver());

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        Integer countReserved = sharedPreferences.getInt("count_reserved",0);
        viewModel = ViewModelProviders.of(this,new MainViewModelFactory(countReserved))
                .get(MainViewModel.class);

//        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        plusOneButton = findViewById(R.id.plusOneButton);
        clearButton = findViewById(R.id.clearButton);
        getUserButton = findViewById(R.id.getUserButton);
        textView = findViewById(R.id.infoText);
        plusOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                viewModel.setCounter(viewModel.getCounter()+1);
                viewModel.plusOne();
//                refreshCounter();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.clear();
            }
        });

        getUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer userId = new Random().nextInt();
                Log.d("getUserButton",userId.toString());
                viewModel.getUserById(userId.toString());
            }
        });
//        refreshCounter();
        viewModel.getCounter().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(integer.toString());
            }
        });
        viewModel.userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                textView.setText(user.getFirstName() + " " + user.getLastName());
            }
        });
    }


    private void refreshCounter(){
//        textView.setText(viewModel.getCounter().toString());

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("count_reserved",viewModel.getCounter());
        editor.putInt("count_reserved",viewModel.getCounter().getValue());
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count_reserved",viewModel.getCounter().getValue());
        editor.commit();
    }
}
