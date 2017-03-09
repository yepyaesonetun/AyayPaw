package com.immortal.ayaypaw;

/**
 * Created by ACER on 20/12/2015.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
  * Created by hp1 on 21-01-2015.
  */
public class At2 extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.at2,container,false);
        ImageView imgvTeam = (ImageView) v.findViewById(R.id.imageViewTeamPoster);
        imgvTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamFbUrl = "https://m.facebook.com/immortaldevelopment/";
                Intent iTUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(teamFbUrl));
                startActivity(iTUrl);
            }
        });
        return v;
    }
}