package com.nitesh.dubaiinterview.SpotsDialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;


import com.nitesh.dubaiinterview.CommonUtils;
import com.nitesh.dubaiinterview.R;

import java.security.SecureRandom;

public class SpotsInitializer {

    private static final int DELAY = 150;
    private static final int DURATION = 1500;
    Context context;
    private int size;
    private AnimatedView[] spots;
    private ProgressLayout spotProgress;
    private SecureRandom random;
    private String randomSpotColorsList[] = {
            "#FF5722", "#795548", "#9E9E9E", "#FFC107", "#009688", "#2196F3", "#3F51B5", "#673AB7", "#9C27B0", "#97B8BA", "#E91E63", "#F44336"};
    private int SPOT_SIZE;

    public SpotsInitializer(Context context, ProgressLayout spotProgress) {
        this.context = context;
        this.spotProgress = spotProgress;
        initProgress();
    }

    public SpotsInitializer(Context context, ProgressLayout spotProgress, int spotSize) {
        this.context = context;
        this.spotProgress = spotProgress;
        SPOT_SIZE = CommonUtils.dp2px(spotSize, context);
        initProgress();
    }

    private void initProgress() {
        random = new SecureRandom();
        size = spotProgress.getSpotsCount();

        spots = new AnimatedView[size];
        int size = SPOT_SIZE == 0 ? context.getResources().getDimensionPixelSize(R.dimen.underline_bottom_spacing) : SPOT_SIZE;
//        int progressWidth = getContext().getResources().getDimensionPixelSize(R.dimen.progress_width);
        int progressWidth = CommonUtils.getScreenHeight(context);

        for (int i = 0; i < spots.length; i++) {
            AnimatedView v = new AnimatedView(context);
//            v.setBackground(ContextCompat.getDrawable(context,R.drawable.drawable_spots));//#
//            v.setBackgroundColor(spotProgress.getSpotsColor()); //to take from attr in xml
//            v.setBackgroundColor(Color.parseColor(randomSpotColorsList[random.nextInt(randomSpotColorsList.length)]));

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
//            GradientDrawable bgShape = (GradientDrawable)spotProgress.getBackground();
//            bgShape.setColor(Color.parseColor(randomSpotColorsList[random.nextInt(randomSpotColorsList.length)]));
            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

//            v.setBackgroundColor(Integer.parseInt(randomSpotColorsList[random.nextInt(randomSpotColorsList.length)]));

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
            ShapeDrawable footerBackground = new ShapeDrawable(new OvalShape());

            footerBackground.getPaint().setColor(Color.parseColor(randomSpotColorsList[random.nextInt(randomSpotColorsList.length)]));
            footerBackground.getPaint().setStyle(Paint.Style.FILL);
            footerBackground.getPaint().setStrokeWidth(size);
            v.setBackground(footerBackground);
            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

            v.setTarget(progressWidth);
            v.setXFactor(-1f);
            spotProgress.addView(v, size, size);
            spots[i] = v;
        }
    }

    public Animator[] createAnimations() {
        Animator[] animators = new Animator[size];
        for (int i = 0; i < spots.length; i++) {
            Animator move = ObjectAnimator.ofFloat(spots[i], "xFactor", 0, 1);
            move.setDuration(DURATION);
            move.setInterpolator(new HesitateInterpolator());
            move.setStartDelay(DELAY * i);
            animators[i] = move;
        }
        return animators;
    }
}
