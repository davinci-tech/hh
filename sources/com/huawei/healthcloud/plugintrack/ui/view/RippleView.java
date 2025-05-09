package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class RippleView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private float f3786a;
    private AnimationSet[] b;
    private float c;
    private final Handler d;
    private ImageView e;
    private ImageView[] f;
    private int h;
    private int i;
    private int j;

    public RippleView(Context context) {
        super(context);
        this.h = 400;
        this.b = new AnimationSet[3];
        this.f = new ImageView[3];
        this.f3786a = 80.0f;
        this.c = 80.0f;
        this.d = new a(this);
        c(context);
    }

    public RippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 400;
        this.b = new AnimationSet[3];
        this.f = new ImageView[3];
        this.f3786a = 80.0f;
        this.c = 80.0f;
        this.d = new a(this);
        biE_(context, attributeSet);
        c(context);
    }

    private void biE_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100628_res_0x7f0603d4, R.attr._2131100629_res_0x7f0603d5, R.attr._2131101015_res_0x7f060557, R.attr._2131101017_res_0x7f060559, R.attr._2131101100_res_0x7f0605ac});
        this.h = obtainStyledAttributes.getInt(4, 400);
        this.f3786a = obtainStyledAttributes.getDimension(1, 80.0f);
        this.c = obtainStyledAttributes.getDimension(0, 80.0f);
        this.j = obtainStyledAttributes.getResourceId(2, R.drawable._2131431271_res_0x7f0b0f67);
        this.i = obtainStyledAttributes.getResourceId(3, R.drawable._2131431270_res_0x7f0b0f66);
        obtainStyledAttributes.recycle();
    }

    private void c(Context context) {
        setLayout(context);
        for (int i = 0; i < this.f.length; i++) {
            this.b[i] = biF_();
        }
    }

    private void setLayout(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) this.f3786a, (int) this.c);
        layoutParams.addRule(13, -1);
        for (int i = 0; i < 3; i++) {
            this.f[i] = new ImageView(context);
            this.f[i].setImageResource(this.j);
            addView(this.f[i], layoutParams);
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) this.f3786a, (int) this.c);
        layoutParams2.addRule(13, -1);
        ImageView imageView = new ImageView(context);
        this.e = imageView;
        imageView.setImageResource(this.i);
        addView(this.e, layoutParams2);
    }

    private AnimationSet biF_() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(this.h * 3);
        scaleAnimation.setRepeatCount(-1);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
        alphaAnimation.setDuration(this.h * 3);
        alphaAnimation.setRepeatCount(-1);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public void a() {
        AnimationSet animationSet;
        ImageView imageView = this.f[0];
        if (imageView == null || (animationSet = this.b[0]) == null) {
            LogUtil.h("Track_RippleView", "startWaveAnimation null");
            return;
        }
        imageView.startAnimation(animationSet);
        this.d.sendEmptyMessageDelayed(1, this.h);
        this.d.sendEmptyMessageDelayed(2, this.h * 2);
    }

    public void d() {
        for (ImageView imageView : this.f) {
            if (imageView != null) {
                imageView.clearAnimation();
            }
        }
    }

    public int getShowAnimTime() {
        return this.h;
    }

    public void setShowAnimTime(int i) {
        this.h = i;
    }

    /* loaded from: classes8.dex */
    static class a extends BaseHandler<RippleView> {
        a(RippleView rippleView) {
            super(rippleView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: biG_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(RippleView rippleView, Message message) {
            int i = message.what;
            if (i == 1) {
                rippleView.f[1].startAnimation(rippleView.b[1]);
            } else {
                if (i != 2) {
                    return;
                }
                rippleView.f[1].startAnimation(rippleView.b[2]);
            }
        }
    }
}
