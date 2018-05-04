package vn.edu.tdc.nhom2.colorbubble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Vinh on 21-Apr-18.
 */

public class frg_top_gamover extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frg_top_gameover, container, false);
        TextView score = (TextView)view.findViewById(R.id.score);
//        String strtext = "Your Score : "+ getArguments().getInt(" Score ")+"";
        score.setText("1");
        return view;
    }
}
