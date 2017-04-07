package com.deepti.saltsideassignment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by deepti on 07/04/17.
 */

public class DetailPageFragment extends Fragment {
    private static String TAG = DetailPageFragment.class.getSimpleName();
    static final String KEY_TITLE = "title";
    static final String KEY_DESC = "desc";
    static final String KEY_IMAGE_URL = "url";
    private String title;
    private String description;
    private String url;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle =  this.getArguments();
        title = bundle.getString(KEY_TITLE);
        description = bundle.getString(KEY_DESC);
        url = bundle.getString(KEY_IMAGE_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.detail_page, container, false);
        TextView titleTV = (TextView) layout.findViewById(R.id.detail_page_title);
        TextView descTV = (TextView) layout.findViewById(R.id.detail_page_desc);

        ImageView img = (ImageView) layout.findViewById(R.id.iv_large_banner);
        titleTV.setText(title);
        descTV.setText(description);

        Glide.with(getActivity())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .crossFade()
                .into(img);
        return layout;
    }
}
