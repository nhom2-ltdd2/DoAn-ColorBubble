package vn.edu.tdc.myapplication;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;
        import android.widget.ImageView;

public class ImageActivity extends Activity {

    ImageView AnimationImage;
    Button AnimationButton;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);

        AnimationButton = (Button)findViewById(R.id.button1);
        AnimationImage = (ImageView)findViewById(R.id.imageView1);

        animation = AnimationUtils.loadAnimation(ImageActivity.this,R.layout.rotation_animation);

        AnimationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AnimationImage.startAnimation(animation);

            }
        });

    }
}
