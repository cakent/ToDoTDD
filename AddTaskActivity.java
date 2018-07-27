package com.example.ckent.todotdd;

import android.content.Context;
import android.content.Intent;

public class AddTaskActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext,AddTaskActivity.class);

        return intent;
    }

    @Override
    protected AddTaskFragment createFragment(){

        return new AddTaskFragment();

    }
}
