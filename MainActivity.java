package com.example.ckent.todotdd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext,MainActivity.class);


        return intent;
    }

    @Override
    protected mainFragment createFragment(){
        return new mainFragment();
    }
}
