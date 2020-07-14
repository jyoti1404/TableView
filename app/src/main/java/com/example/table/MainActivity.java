package com.example.table;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.levitnudi.legacytableview.LegacyTableView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   //     loadJSONFromAsset(this);

        B b[] = new B[] {

                new B(getString(R.string.main_adapter), MainTable.class),
        };
        setListAdapter(new ArrayAdapter<B>(this, android.R.layout.simple_list_item_1, android.R.id.text1, b));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(this, ((B) l.getItemAtPosition(position)).class1));
    }

    private class B {
        private final String string;
        private final Class<? extends Activity> class1;

        B(String string, Class<? extends Activity> class1) {
            this.string = string;
            this.class1 = class1;
        }

        @Override
        public String toString() {
            return string;
        }
    }

}
