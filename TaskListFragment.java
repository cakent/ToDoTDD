package com.example.ckent.todotdd;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TaskListFragment extends Fragment {
    private AsyncTask task = null;
    private final String TAG = "taggytagtag";
    private RecyclerView taskRecyclerView;
    List<Tasks> items=null;
    private IconicAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragmenttasklist,container,false);
        taskRecyclerView = (RecyclerView) v.findViewById(R.id.taskList_recycler_view);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setHasOptionsMenu(true);


        if(items==null){
            if(task==null){
                task= new SelectAllTask(getContext()).execute();
                if(task.getStatus()== AsyncTask.Status.RUNNING){
                    Log.i(TAG,"It's running");
                }
            }
        }else{
            setAdapter();
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.listscreenactions,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newItem:
                Intent intent = new Intent(this.getContext(),AddTaskActivity.class);

                this.startActivity(intent);

                return (true);}
        return(super.onOptionsItemSelected(item));
    }




    private void setAdapter() {
           taskRecyclerView.setAdapter(new IconicAdapter(items,getLayoutInflater()));
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


    static class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title=null;
        TextView description=null;
        String iD=null;



        RowHolder(View row) {
            super(row);

            title=(TextView)row.findViewById(R.id.label);
            description=(TextView)row.findViewById(R.id.size);


            row.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), TaskDetailActivity.class);
            intent.putExtra(TaskDetailActivity.EXTRA_MESSAGE,iD);
            v.getContext().startActivity(intent);
        }

        void bindModel(Tasks item) {
            title.setText(item.title);
            description.setText(item.desc);
            iD = item.id;



        }
    }


    private class SelectAllTask extends AsyncTask<Void, Void, List<Tasks>>{

        private final Context app;

        SelectAllTask(Context ctxt){
            this.app=getContext();
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
