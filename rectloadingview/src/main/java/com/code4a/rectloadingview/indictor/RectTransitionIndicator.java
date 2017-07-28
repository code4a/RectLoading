package com.code4a.rectloadingview.indictor;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/18.
 */
public class RectTransitionIndicator extends Indicator {

    float[] translateX = new float[dotCount], translateY = new float[dotCount];
    float scaleFloat = 1.0f;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float rWidth = getWidth() / 2;
        float rHeight = getHeight() / 2;
        for (int i = 0; i < dotCount; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.scale(scaleFloat, scaleFloat);
            RectF rectF = new RectF(-rWidth / 2, -rHeight / 2, rWidth / 2, rHeight / 2);
            setColor(mColors[i]);
            canvas.drawRect(rectF, paint);
            canvas.restore();
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        animators.clear();
        float startX = getWidth() / 5;
        float startY = getHeight() / 5;
        for (int i = 0; i < dotCount; i++) {
            final int index = i;
            translateX[index] = startX;
            ValueAnimator translationXAnim = ValueAnimator.ofFloat(startX, getWidth() - startX, getWidth() - startX, startX, startX);
            if (i == 1) {
                translationXAnim = ValueAnimator.ofFloat(getWidth() - startX, startX, startX, getWidth() - startX, getWidth() - startX);
            } else if (i == 2) {
                translationXAnim = ValueAnimator.ofFloat(startX, startX, getWidth() - startX, getWidth() - startX, startX);
            } else if (i == 3) {
                translationXAnim = ValueAnimator.ofFloat(getWidth() - startX, getWidth() - startX, startX, startX, getWidth() - startX);
            }
            translationXAnim.setInterpolator(new LinearInterpolator());
            translationXAnim.setDuration(animationDuration);
            translationXAnim.setRepeatCount(-1);
            addUpdateListener(translationXAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateX[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            translateY[index] = startY;
            ValueAnimator translationYAnim = ValueAnimator.ofFloat(startY, startY, getHeight() - startY, getHeight() - startY, startY);
            if (i == 1) {
                translationYAnim = ValueAnimator.ofFloat(getHeight() - startY, getHeight() - startY, startY, startY, getHeight() - startY);
            } else if (i == 2) {
                translationYAnim = ValueAnimator.ofFloat(getHeight() - startY, startY, startY, getHeight() - startY, getHeight() - startY);
            } else if (i == 3) {
                translationYAnim = ValueAnimator.ofFloat(startY, getHeight() - startY, getHeight() - startY, startY, startY);
            }
            translationYAnim.setDuration(animationDuration);
            translationYAnim.setInterpolator(new LinearInterpolator());
            translationYAnim.setRepeatCount(-1);
            addUpdateListener(translationYAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateY[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });

            animators.add(translationXAnim);
            animators.add(translationYAnim);
        }
        return animators;
    }
}
