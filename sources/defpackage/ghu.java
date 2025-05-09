package defpackage;

import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes8.dex */
public class ghu {
    public static int a() {
        return R.animator._2131034181_res_0x7f050045;
    }

    public static int d() {
        return R.animator._2131034180_res_0x7f050044;
    }

    public static AnimationSet aMU_() {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 0, nsn.c(BaseApplication.getContext(), 30.0f), 0, 0.0f);
        translateAnimation.setDuration(250L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(250L);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static AnimationSet aMV_() {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 0, 0.0f, 0, nsn.c(BaseApplication.getContext(), -30.0f));
        translateAnimation.setDuration(250L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(250L);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static AnimationSet aMS_() {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 0, nsn.c(BaseApplication.getContext(), -30.0f), 0, 0.0f);
        translateAnimation.setDuration(250L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(250L);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static AnimationSet aMT_() {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 0, 0.0f, 0, nsn.c(BaseApplication.getContext(), 30.0f));
        translateAnimation.setDuration(250L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(250L);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }
}
