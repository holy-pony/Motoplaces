package ru.nitrobubbles.motoplaces.support.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by konstantinaksenov on 15.10.15.
 */
public class AnimationSupport {
    public static void fadeIn(View v){
        if(v.getVisibility() == View.GONE){

            AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setFillEnabled(true);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    v.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(alphaAnimation);
        }
    }

    public static void fadeOut(View v){
        if(v.getVisibility() == View.VISIBLE) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            alphaAnimation.setFillEnabled(true);
            v.startAnimation(alphaAnimation);
        }
    }
}
