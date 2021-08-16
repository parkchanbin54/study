package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class ListViewActivity extends Fragment {
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,Bundle
            savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.listview, container, false);

        String[] names = {"Monday","Tuesday","wednesday","Thursday","Friday"};
        ListView listView = (ListView) rootView.findViewById(R.id.listview1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
        return rootView;
    }
}
