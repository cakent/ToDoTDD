package com.example.ckent.todotdd;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewActivity extends Activity {
    private RecyclerView rv=null;

    public void setAdapter(RecyclerView.Adapter adapter){
        getRecyclerView().setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager){
        getRecyclerView().setLayoutManager(manager);
    }

    public RecyclerView getRecyclerView(){
        if (rv==null) {
            rv=new RecyclerView(this);
            rv.setHasFixedSize(true);
            setContentView(rv);
        }
        return(rv);
    }

}
