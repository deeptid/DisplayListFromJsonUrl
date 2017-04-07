package com.deepti.saltsideassignment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deepti on 07/04/17.
 */
public class JsonListAdapter extends BaseAdapter {
    private final Activity activity;
    private final ArrayList<HashMap<String, String>> data;
    private LayoutInflater inflater = null;
    static final String KEY_TITLE = "title";
    static final String KEY_DESC = "desc";
    static final String KEY_IMAGE_URL = "url";

    public JsonListAdapter(Activity activity, ArrayList<HashMap<String, String>> mapListJson) {
        this.data = mapListJson;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (view == null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView) vi.findViewById(R.id.list_title);
        TextView desc = (TextView) vi.findViewById(R.id.description);
        HashMap<String, String> map = new HashMap<String, String>();
        map = data.get(i);
        title.setText(map.get(KEY_TITLE));
        desc.setText(map.get(KEY_DESC));
        return vi;
    }
}
