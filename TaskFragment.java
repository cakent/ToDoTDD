package com.example.ckent.todotdd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TaskFragment extends Fragment {

    private static final String KEY_ID="identification";

    static TaskFragment newInstance(String ID){
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(KEY_ID,ID);

        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.mainfragment_layout,container,false);
        EditText taskTitle = (EditText) v.findViewById(R.id.taskTitle);
        EditText description = (EditText) v.findViewById(R.id.taskDescription);


        return v;
    }


    }

