package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class f extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private Animation f8059a;
    private Animation b;
    private Animation c;
    private Animation d;
    private ImageView e;
    private ImageView f;

    public void a() {
        a(this.b);
        a(this.f8059a);
        a(this.d);
        a(this.c);
        setVisibility(8);
    }

    private void b(Context context) {
        this.f8059a = AnimationUtils.loadAnimation(context, R.anim._2130772026_res_0x7f01003a);
        this.b = AnimationUtils.loadAnimation(context, R.anim._2130772027_res_0x7f01003b);
        this.f8059a.setDuration(400L);
        this.b.setDuration(400L);
        this.f8059a.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.openalliance.ad.views.f.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (f.this.e != null) {
                    f.this.e.startAnimation(f.this.b);
                }
            }
        });
        this.b.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.openalliance.ad.views.f.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (f.this.f != null) {
                    f.this.f.startAnimation(f.this.c);
                }
            }
        });
        this.c = AnimationUtils.loadAnimation(context, R.anim._2130772021_res_0x7f010035);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim._2130772022_res_0x7f010036);
        this.d = loadAnimation;
        loadAnimation.setDuration(400L);
        this.c.setDuration(400L);
        this.c.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.openalliance.ad.views.f.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                if (f.this.f != null) {
                    f.this.f.setVisibility(0);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (f.this.f != null) {
                    f.this.f.startAnimation(f.this.d);
                }
                if (f.this.e != null) {
                    f.this.e.startAnimation(f.this.f8059a);
                }
            }
        });
        this.d.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.openalliance.ad.views.f.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (f.this.f != null) {
                    f.this.f.setVisibility(4);
                }
            }
        });
    }

    private void a(Animation animation) {
        if (animation != null) {
            animation.cancel();
        }
    }

    private void a(Context context) {
        ho.b("MaskingView", "init");
        inflate(context, R.layout.pps_masking_view, this);
        this.e = (ImageView) findViewById(R.id.hiad_click_hand);
        this.f = (ImageView) findViewById(R.id.hiad_click_arc);
        b(context);
        this.e.startAnimation(this.b);
    }

    public f(Context context) {
        super(context);
        a(context);
    }
}
