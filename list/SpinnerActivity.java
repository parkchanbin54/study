package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SpinnerActivity extends Fragment {
    TextView textView;
    String[] items = {"mike", "angel", "crow", "john", "ginnie", "sally", "cohen", "rice"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.spinner, container, false);
        Spinner spinnerView = (Spinner) rootView.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerView.setAdapter(adapter);
        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                textView.setText(items[position]);
            }
            @Override
            public void onNothingSelected(AdapterView adapterView){
                textView.setText("");
            }
        });
        return rootView;
    }
}
