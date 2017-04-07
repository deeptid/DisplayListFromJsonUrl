package com.deepti.saltsideassignment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deepti on 07/04/17.
 */
public class JsonListFragment extends Fragment {
    private static String TAG = JsonListFragment.class.getSimpleName();
    private String jsonString;
    private JsonArray jsonArray;
    static final String KEY_TITLE = "title";
    static final String KEY_DESC = "desc";
    static final String KEY_IMAGE_URL = "url";
    private long mLastClickTime = 0;
    private ArrayList<HashMap<String, String>> mapListJson = new ArrayList<HashMap<String, String>>();;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle =  this.getArguments();
        jsonString = bundle.getString("JsonArray");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ScrollView layout = (ScrollView) inflater.inflate(R.layout.json_list_fragment, container, false);
        TextView title = (TextView) layout.findViewById(R.id.my_json_title);
        ListView listJson= (ListView) layout.findViewById(R.id.list_json);
        mapListJson = new ArrayList<HashMap<String, String>>();

        if(jsonString != null) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(jsonString);
            jsonArray = jsonElement.getAsJsonArray();
        }

        if(jsonArray != null && !jsonArray.isJsonNull()){
            for (JsonElement e : jsonArray){
                JsonObject obj = e.getAsJsonObject();
                String titleValue = obj.get("title").getAsString();
                String descriptionValue = obj.get("description").getAsString();
                String imageValue = obj.get("image").getAsString();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_TITLE, titleValue);
                map.put(KEY_DESC, descriptionValue);
                map.put(KEY_IMAGE_URL, imageValue);
                mapListJson.add(map);
            }
        }


        if (mapListJson.size() > 0) {
            Log.d(TAG, "mapList:" + mapListJson);
            JsonListAdapter adapterJson = new JsonListAdapter(getActivity(), mapListJson);
            listJson.setAdapter(adapterJson);
        }

        listJson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick");
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                performAction(position);
            }
        });

        return layout;
    }

    private void performAction(int position) {
        Bundle bundle =  new Bundle();
        HashMap<String, String> map = mapListJson.get(position);
        bundle.putString(KEY_TITLE, map.get(KEY_TITLE));
        bundle.putString(KEY_DESC, map.get(KEY_DESC));
        bundle.putString(KEY_IMAGE_URL, map.get(KEY_IMAGE_URL));
        DetailPageFragment newFragment = new DetailPageFragment();
        String tag = DetailPageFragment.class.getSimpleName();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        newFragment.setArguments(bundle);
        ft.add(R.id.full_screen_list, newFragment, tag);
        ft.addToBackStack(tag);
        ft.commit();

    }


}
