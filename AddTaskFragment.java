package com.example.ckent.todotdd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskFragment extends Fragment {

    String title = null;
    String desc = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.addtask_fragment,container,false);
        final EditText titleText =(EditText) v.findViewById(R.id.titleEditText);
        final EditText detailText = (EditText) v.findViewById(R.id.detailEditText);
        Button save =(Button) v.findViewById(R.id.addTaskButton);
        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title=titleText.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        detailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                desc = detailText.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasks task = new Tasks(title,desc);
            }
        });

        return v;
    }
}
