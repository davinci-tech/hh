package com.huawei.openalliance.ad.views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.gq;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;

/* loaded from: classes5.dex */
public class PPSSplashProView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f7970a;
    private ScanningRelativeLayout b;
    private int c;
    private TextView d;
    private ImageView e;
    private boolean f;
    private int g;
    private RoundLinearLayout h;
    private AnimatorSet i;
    private AnimatorSet j;
    private AnimatorSet k;
    private AnimatorSet l;
    private AnimatorSet m;
    private AnimatorSet n;

    public void setOrientation(int i) {
        this.g = i;
    }

    public void setMode(int i) {
        this.c = i;
    }

    public void setDesc(String str) {
        if (this.d != null) {
            if (TextUtils.isEmpty(str)) {
                this.d.setText(R.string._2130851159_res_0x7f023557);
            } else {
                this.d.setText(str);
            }
        }
    }

    public int getMode() {
        return this.c;
    }

    public void a(boolean z, int i) {
        this.f = z;
        if (this.b != null && i == 0) {
            RoundLinearLayout roundLinearLayout = this.h;
            if (roundLinearLayout != null) {
                roundLinearLayout.setBackground(getResources().getDrawable(R.drawable._2131428588_res_0x7f0b04ec));
                this.h.setAlpha(0.0f);
            }
            this.b.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashProView.2
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    if (i4 - i2 <= 0 || i5 - i3 <= 0 || i6 != 0 || i8 != 0) {
                        return;
                    }
                    PPSSplashProView.this.d();
                }
            });
        }
        c();
    }

    public void a() {
        ScanningRelativeLayout scanningRelativeLayout = this.b;
        if (scanningRelativeLayout != null) {
            scanningRelativeLayout.a();
        }
        AnimatorSet animatorSet = this.j;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.j = null;
        }
        AnimatorSet animatorSet2 = this.i;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
            this.i = null;
        }
        AnimatorSet animatorSet3 = this.k;
        if (animatorSet3 != null) {
            animatorSet3.cancel();
            this.k = null;
        }
        AnimatorSet animatorSet4 = this.l;
        if (animatorSet4 != null) {
            animatorSet4.cancel();
            this.l = null;
        }
        AnimatorSet animatorSet5 = this.m;
        if (animatorSet5 != null) {
            animatorSet5.cancel();
            this.m = null;
        }
        AnimatorSet animatorSet6 = this.n;
        if (animatorSet6 != null) {
            animatorSet6.cancel();
            this.n = null;
        }
    }

    private void e() {
        this.k = new AnimatorSet();
        this.l = new AnimatorSet();
        this.m = new AnimatorSet();
        this.n = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, "scaleX", 1.0f, 1.225f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.e, "scaleY", 1.0f, 1.225f);
        ofFloat.setDuration(350L);
        ofFloat2.setDuration(350L);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.e, "scaleX", 1.225f, 1.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.e, "scaleY", 1.225f, 1.0f);
        ofFloat3.setDuration(500L);
        ofFloat4.setDuration(500L);
        this.m.playSequentially(ofFloat, ofFloat3);
        this.n.playSequentially(ofFloat2, ofFloat4);
        this.m.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
        this.n.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.e, "scaleX", 1.0f, 0.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.e, "scaleY", 1.0f, 0.0f);
        ofFloat5.setDuration(0L);
        ofFloat6.setDuration(0L);
        ofFloat5.addListener(new Animator.AnimatorListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashProView.5
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                try {
                    PPSSplashProView.this.e.setVisibility(0);
                } catch (Throwable th) {
                    ho.c("PPSSplashProView", "arrowImage set visible err: %s", th.getClass().getSimpleName());
                }
            }
        });
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.e, "scaleX", 0.0f, 1.225f);
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this.e, "scaleY", 0.0f, 1.225f);
        ofFloat7.setDuration(400L);
        ofFloat8.setDuration(400L);
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(this.e, "scaleX", 1.225f, 0.989f);
        ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(this.e, "scaleY", 1.225f, 0.989f);
        ofFloat9.setDuration(500L);
        ofFloat10.setDuration(500L);
        ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(this.e, "scaleX", 0.989f, 1.0f);
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(this.e, "scaleY", 0.989f, 1.0f);
        ofFloat11.setDuration(350L);
        ofFloat12.setDuration(350L);
        this.k.playSequentially(ofFloat5, ofFloat7, ofFloat9, ofFloat11);
        this.l.playSequentially(ofFloat6, ofFloat8, ofFloat10, ofFloat12);
        this.k.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
        this.l.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
        this.k.addListener(new Animator.AnimatorListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashProView.6
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashProView.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PPSSplashProView.this.m == null || PPSSplashProView.this.n == null) {
                            return;
                        }
                        PPSSplashProView.this.m.start();
                        PPSSplashProView.this.n.start();
                    }
                }, 450L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ho.b("PPSSplashProView", "startAnimators");
        try {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.h, "alpha", 0.0f, 1.0f);
            ofFloat.setDuration(300L);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.b, "scaleX", 0.85f, 1.0f);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.b, "scaleY", 0.85f, 1.0f);
            ofFloat2.setDuration(300L);
            ofFloat3.setDuration(300L);
            AnimatorSet animatorSet = new AnimatorSet();
            this.i = animatorSet;
            animatorSet.playTogether(ofFloat2, ofFloat3, ofFloat);
            this.i.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
            this.i.addListener(new Animator.AnimatorListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashProView.3
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    try {
                        PPSSplashProView.this.b.c();
                    } catch (Throwable th) {
                        ho.c("PPSSplashProView", "prepare err: %s", th.getClass().getSimpleName());
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ho.b("PPSSplashProView", "upAndAlphaSet onAnimationEnd");
                    try {
                        PPSSplashProView.this.b.d();
                        if (PPSSplashProView.this.k == null || PPSSplashProView.this.l == null) {
                            return;
                        }
                        PPSSplashProView.this.k.start();
                        PPSSplashProView.this.l.start();
                    } catch (Throwable th) {
                        ho.c("PPSSplashProView", "scale err: %s", th.getClass().getSimpleName());
                    }
                }
            });
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.b, "scaleX", 1.0f, 0.85f);
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.b, "scaleY", 1.0f, 0.85f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.j = animatorSet2;
            animatorSet2.setDuration(0L);
            this.j.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
            this.j.playTogether(ofFloat4, ofFloat5);
            this.j.addListener(new Animator.AnimatorListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashProView.4
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ho.b("PPSSplashProView", "scaleAnimationDown onAnimationEnd");
                    try {
                        PPSSplashProView.this.setVisibility(0);
                        if (PPSSplashProView.this.i != null) {
                            PPSSplashProView.this.i.start();
                        }
                    } catch (Throwable th) {
                        ho.c("PPSSplashProView", "up and alpha err: %s", th.getClass().getSimpleName());
                    }
                }
            });
            this.e.setVisibility(4);
            e();
            this.j.start();
        } catch (Throwable th) {
            ho.c("PPSSplashProView", "anim error: %s", th.getClass().getSimpleName());
            RoundLinearLayout roundLinearLayout = this.h;
            if (roundLinearLayout != null) {
                roundLinearLayout.setBackground(getResources().getDrawable(R.drawable._2131428588_res_0x7f0b04ec));
            }
            setVisibility(0);
        }
    }

    private void c() {
        ho.b("PPSSplashProView", "showLogo:" + this.f + ",orientation:" + this.g);
        b();
    }

    private void b() {
        TextView textView;
        float O;
        Context applicationContext = getContext().getApplicationContext();
        gc b = fh.b(applicationContext);
        int a2 = ao.a(applicationContext, b.N());
        int P = b.P();
        int a3 = ao.a(applicationContext, b.O());
        final int a4 = ao.a(applicationContext, b.a(applicationContext));
        this.b.setRadius(P);
        this.h.setRectCornerRadius(ao.a(applicationContext, P));
        this.b.setMinimumHeight(a2);
        int i = 2;
        if (ao.n(getContext())) {
            textView = this.d;
            O = b.O() * 2;
            i = 1;
        } else {
            textView = this.d;
            O = b.O();
        }
        textView.setTextSize(i, O);
        this.d.setMinimumHeight(a2);
        ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
        layoutParams.height = a3;
        layoutParams.width = a3;
        this.e.setLayoutParams(layoutParams);
        this.f7970a.post(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashProView.1
            @Override // java.lang.Runnable
            public void run() {
                ViewGroup.LayoutParams layoutParams2 = PPSSplashProView.this.getLayoutParams();
                if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
                    layoutParams3.bottomMargin = (PPSSplashProView.this.f || PPSSplashProView.this.g != 1) ? a4 : a4 + dd.f(PPSSplashProView.this.getContext());
                    PPSSplashProView.this.setLayoutParams(layoutParams3);
                }
            }
        });
    }

    private void a(Context context) {
        String str;
        ho.b("PPSSplashProView", "init");
        try {
            View inflate = inflate(context, R.layout.hiad_layout_splash_pro, this);
            this.f7970a = inflate;
            this.b = (ScanningRelativeLayout) inflate.findViewById(R.id.hiad_pro_layout);
            this.h = (RoundLinearLayout) this.f7970a.findViewById(R.id.hiad_pro_desc_layout);
            this.b.setBackground(getResources().getDrawable(R.drawable._2131428587_res_0x7f0b04eb));
            this.d = (TextView) this.f7970a.findViewById(R.id.hiad_pro_desc);
            this.e = (ImageView) this.f7970a.findViewById(R.id.hiad_pro_arrow);
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("PPSSplashProView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("PPSSplashProView", str);
        }
    }

    public PPSSplashProView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 2;
        this.g = 1;
        a(context);
    }

    public PPSSplashProView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 2;
        this.g = 1;
        a(context);
    }

    public PPSSplashProView(Context context) {
        super(context);
        this.c = 2;
        this.g = 1;
        a(context);
    }
}
