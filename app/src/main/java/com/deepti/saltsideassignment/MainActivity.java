package com.deepti.saltsideassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonArray;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private static String DEFAULTURL = "https://gist.githubusercontent.com/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json";
    private Button enterFetchBtn;
    private JsonArray jsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterFetchBtn = (Button) findViewById(R.id.btn_fetch);
        String sURL = DEFAULTURL;
        try {
            jsonArray = new DownloadJson().execute(sURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final Bundle bundle =  new Bundle();
        bundle.putString("JsonArray", jsonArray.toString());
        enterFetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonListFragment newFragment = new JsonListFragment();
                String tag = JsonListFragment.class.getSimpleName();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                newFragment.setArguments(bundle);
                ft.add(R.id.full_screen, newFragment, tag);
                ft.addToBackStack(tag);
                ft.commit();
            }
        });
    }
}

