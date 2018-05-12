package vn.edu.tdc.nhom2.colorbubble.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;
import vn.edu.tdc.nhom2.colorbubble.R;

public class RankAdapter extends ArrayAdapter<Score> {

    AppCompatActivity context;
    int layout;
    ArrayList<Score> rankAdapters ;


    public RankAdapter(@NonNull AppCompatActivity context, int resource, ArrayList<Score> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.rankAdapters = objects;
    }
    public class ViewHolder{
        TextView txt_name;
        TextView txt_score;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_name = (TextView) convertView.findViewById(R.id.nameCustom);
            viewHolder.txt_score = (TextView) convertView.findViewById(R.id.scoreCustom);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt_name.setText(rankAdapters.get(position).getName());
        viewHolder.txt_score.setText(Integer.toString(rankAdapters.get(position).getScore()));
        return convertView;
    }
}
