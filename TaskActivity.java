package com.example.ckent.todotdd;

import android.content.Context;
import android.content.Intent;

public class TaskActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext,TaskActivity.class);


        return intent;
    }

    @Override
    protected TaskFragment createFragment(){
        return new TaskFragment();
    }
}
