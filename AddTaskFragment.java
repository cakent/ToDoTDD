package com.example.ckent.todotdd;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class AddTaskFragment extends Fragment {
    public Tasks item = new Tasks("","");
    private AsyncTask task = null;
    Calendar dateAndTime=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d =new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            item.setDueDate(dateAndTime.getTime());

        } };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.addtask_fragment,container,false);
        final  EditText titleText =(EditText) v.findViewById(R.id.titleEditText);
        final  EditText detailText = (EditText) v.findViewById(R.id.detailEditText);
        final EditText datePicker = (EditText) v.findViewById(R.id.datePicker);

        Button save =(Button) v.findViewById(R.id.addTaskButton);



        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setTitle(titleText.getText().toString());
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
                item.setDesc(detailText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();

                datePicker.setText(item.getDueDate().toString());}



        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task == null) {
                    task = new addToList().execute();
                }
            }
        });

        return v;
    }

    private class addToList extends AsyncTask<Void, Void, Tasks>{
        @Override
        protected Tasks doInBackground(Void... params){
            TaskStore store = TaskDatabase.get(getContext()).taskStore();

            store.insert(item);

            return null;
        }

        protected void onPostExecute(Tasks tasks) {
            Intent intent = new Intent(getActivity(),TaskListActivity.class);

           getActivity().startActivity(intent);




        }
    }
}
