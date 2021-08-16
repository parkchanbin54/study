package com.example.myapplication;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
    public class MainActivity extends AppCompatActivity {
        ListViewActivity listviewFragment;
        SpinnerActivity spinnerFragment;
        @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            Button button = (Button)findViewById(R.id.button1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity activity = (MainActivity) listviewFragment.getActivity();
                    activity.onFragmentChanged(1);
                }
            });
            Button button2 = (Button)findViewById(R.id.button2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity activity = (MainActivity) spinnerFragment.getActivity();
                    activity.onFragmentChanged(0);
                }
            });
            listviewFragment = new ListViewActivity();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, listviewFragment).commit();
        spinnerFragment = new SpinnerActivity();
    }
    public void onFragmentChanged(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, spinnerFragment).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, listviewFragment).commit();
        }

}}