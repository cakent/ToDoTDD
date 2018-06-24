package com.example.ckent.todotdd;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class TaskList extends RecyclerViewActivity {
    Tasks task1= new Tasks("title1","description1");
    Tasks task2= new Tasks("title2","description2");

    Tasks[] items = {task1,task2};

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new IconicAdapter());
    }
    class IconicAdapter extends RecyclerView.Adapter<RowHolder> {
        @Override
        public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return(new RowHolder(getLayoutInflater()
                    .inflate(R.layout.row, parent, false)));
        }

        @Override
        public void onBindViewHolder(RowHolder holder, int position) {
            holder.bindModel(items[position]);
        }

        @Override
        public int getItemCount() {
            return(items.length);
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
}
