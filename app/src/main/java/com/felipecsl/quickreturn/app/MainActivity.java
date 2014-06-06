package com.felipecsl.quickreturn.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.felipecsl.quickreturn.com.felipecsl.quickreturn.library.widget.QuickReturnAdapter;
import com.felipecsl.quickreturn.com.felipecsl.quickreturn.library.widget.QuickReturnListView;


public class MainActivity extends ActionBarActivity {

    private QuickReturnListView quickReturnListView;
    private QuickReturnAdapter quickReturnAdapter;
    private ArrayAdapter<String> adapter;
    private int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quickReturnListView = (QuickReturnListView) findViewById(R.id.listView);
        TextView quickReturnTarget = (TextView) findViewById(R.id.quickReturnTarget);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        addMoreItems(100);

        quickReturnAdapter = new QuickReturnAdapter(adapter);
        quickReturnListView.setAdapter(quickReturnAdapter);
        quickReturnListView.setQuickReturnView(quickReturnTarget);
    }

    private void addMoreItems(final int amount) {
        for (int i = 0; i < amount; i++)
            adapter.add("Item " + String.valueOf(offset + i));

        offset += amount;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_more) {
            addMoreItems(100);
        } else if (id == R.id.reset) {
            reset();
        } else if (id == R.id.animated) {
            quickReturnListView.setAnimatedTransition(!quickReturnListView.isAnimatedTransition());
            item.setTitle(quickReturnListView.isAnimatedTransition() ? "Disable animated transition" : "Enable animated transition");
            reset();
        }
        return true;
    }

    private void reset() {
        adapter.clear();
        offset = 0;
        addMoreItems(100);
    }
}
