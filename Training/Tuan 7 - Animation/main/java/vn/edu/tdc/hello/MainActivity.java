package vn.edu.tdc.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnfire;
    ImageView imgtom,imgbull,imgjer;
    Animation anim_moveright,anim_blink,anim_rotate,anim_topdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnfire = (Button) findViewById(R.id.btn_fire);
        imgtom = (ImageView) findViewById(R.id.img_tom);
        imgbull = (ImageView) findViewById(R.id.img_bullet);
        imgjer = (ImageView) findViewById(R.id.img_jerry);
        anim_moveright = AnimationUtils.loadAnimation(this,R.anim.anim_fadein);
        anim_rotate = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        anim_blink = AnimationUtils.loadAnimation(this,R.anim.anim_blink);
        anim_topdown = AnimationUtils.loadAnimation(this,R.anim.anim_topdown);
        fireClick();
    }

    public void fireClick()
    {
        btnfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnfire)
                {
                    imgjer.startAnimation(anim_topdown);
                    imgbull.startAnimation(anim_moveright);
                    imgjer.startAnimation(anim_rotate);
                    imgtom.startAnimation(anim_blink);
                }
            }
        });
    }
}
