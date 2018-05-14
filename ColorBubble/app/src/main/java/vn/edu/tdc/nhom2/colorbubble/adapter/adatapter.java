package vn.edu.tdc.nhom2.colorbubble.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdc.nhom2.colorbubble.Model.Score;
import vn.edu.tdc.nhom2.colorbubble.R;


/**
 * Created by Vinh on 28-Apr-18.
 */

public class adatapter extends PagerAdapter {
    Context context;
    ArrayList<Score> ar;

    public adatapter(Context context, ArrayList<Score> _ar) {
        ar = _ar;
        this.context = context;
    }

    public int getCount() {
        return ar.size();
    }

    public Object instantiateItem(ViewGroup container, int position) {

        Score chuDe = ar.get(position);
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_viewpager, container, false);

        TextView rank = (TextView) itemView.findViewById(R.id.rank);
        TextView name = (TextView) itemView.findViewById(R.id.name);
        TextView score = (TextView) itemView.findViewById(R.id.score);
        ImageView image = (ImageView) itemView.findViewById(R.id.image);
        rank.setText(position + 1 + ".");
        name.setText(chuDe.getName());
        score.setText(chuDe.getScore()+"");
        if((chuDe.getHinh().equals("a"))){
            image.setImageResource(R.drawable.ic_gooogleplay);
        }
        else image.setImageBitmap(covertToBitmap(chuDe.getHinh()));
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
    private Bitmap covertToBitmap(String string1) {
        byte[] decodedString = Base64.decode(string1, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }
}