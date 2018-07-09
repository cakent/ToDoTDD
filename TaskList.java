/*
Finally able to get room working, needed to put everything on background thread. Still need to make it all clickable, and launch the details screen. 
 */

package com.example.ckent.todotdd;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TaskList extends RecyclerViewActivity {

    private AsyncTask task = null;
    private final String TAG = "taggytagtag";

    List<Tasks> items=null;



    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        setLayoutManager(new LinearLayoutManager(this));


        if(items==null){
            if(task==null){
                task= new SelectAllTask(this).execute();
                if(task.getStatus()== AsyncTask.Status.RUNNING){
                    Log.i(TAG,"It's running");
                }
            }
        }else{
            setAdapter();
        }

    }

    private void setAdapter() {
        setAdapter(new IconicAdapter(items,getLayoutInflater()));
    }
    class IconicAdapter extends RecyclerView.Adapter<RowHolder> {
        @Override
        public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return(new RowHolder(getLayoutInflater()
                    .inflate(R.layout.row, parent, false)));
        }

        private final List<Tasks> trips;
        private final LayoutInflater inflater;

        private IconicAdapter(List<Tasks> trips, LayoutInflater inflater) {
            this.trips=trips;
            this.inflater=inflater;
        }

        @Override
        public void onBindViewHolder(RowHolder holder, int position) {
            holder.bindModel(items.get(position));
        }

        @Override
        public int getItemCount() {

            return(items==null ? 0 : items.size());

        }
    }
    static class RowHolder extends RecyclerView.ViewHolder {
        TextView title=null;
        TextView description=null;



        RowHolder(View row) {
            super(row);

            title=(TextView)row.findViewById(R.id.label);
            description=(TextView)row.findViewById(R.id.size);



        }

        void bindModel(Tasks item) {
            title.setText(item.title);
            description.setText(item.desc);


        }
    }
    private class SelectAllTask extends AsyncTask<Void, Void, List<Tasks>>{

        private final Context app;

        SelectAllTask(Context ctxt){
          this.app=getApplicationContext();
        }

        @Override
        protected List<Tasks> doInBackground(Void... params){
            TaskStore store = TaskDatabase.get(app).taskStore();

            List<Tasks> results = store.selectAll();
            if (results==null || results.size()==0) {
                store.insert(new Tasks("Vacation!", "hello"),
                        new Tasks("Business ", "goodbye"));
                results=store.selectAll();
            }
            Log.i(TAG, "Doing stuff!!!!"+results.size());


            return(results);
        }
//Finally able to get all of this working
        protected void onPostExecute(List<Tasks> tasks) {
            Log.i(TAG,"post executed");
            items=tasks;
            task=null;
            setAdapter();


        }
    }




}
