/*
Finally able to get room working, needed to put everything on background thread. Still need to make it all clickable, and launch the details screen.
 */

package com.example.ckent.todotdd;

public class TaskListActivity extends SingleFragmentActivity {

    @Override
    protected TaskListFragment createFragment(){
        return new TaskListFragment();
    }

}
