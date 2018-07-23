package com.example.ckent.todotdd;

import android.content.Context;
import android.content.Intent;

public class TaskActivity extends SingleFragmentActivity {

    public static final String EXTRA_MESSAGE="msg";
    public static Intent newIntent(Context packageContext, String ID){
        Intent intent = new Intent(packageContext,TaskActivity.class);
        intent.putExtra(EXTRA_MESSAGE,ID);

        return intent;
    }

    @Override
    protected TaskFragment createFragment(){
        String ID = getIntent().getStringExtra(EXTRA_MESSAGE);

        TaskFragment taskfragment = TaskFragment.newInstance(ID);
        return taskfragment;
    }
}
