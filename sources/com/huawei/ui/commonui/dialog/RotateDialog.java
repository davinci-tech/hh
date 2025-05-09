package com.huawei.ui.commonui.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.dialog.RotateDialog;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class RotateDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private String f8820a;
    private OnVisibleListenter b;
    private final Context c;
    private Drawable d;
    private ImageView e;
    private LinearLayout f;
    private String g;
    private ImageView h;
    private int i;
    private String j;
    private View l;
    private long m;
    private boolean n;

    public interface OnVisibleListenter {
        void onVisible(boolean z, View view);
    }

    public RotateDialog(Context context) {
        super(context, R.style.CustomDialog, 0);
        this.i = 0;
        this.m = -1L;
        this.c = context;
        setCancelable(false);
        b();
    }

    public void d(final String str) {
        LogUtil.a("RotateDialog", "loadBackground start");
        if (c() || TextUtils.isEmpty(str)) {
            LogUtil.h("RotateDialog", "loadBackground activity is destoried or imgUrl is empty");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: nls
                @Override // java.lang.Runnable
                public final void run() {
                    RotateDialog.this.c(str);
                }
            });
            LogUtil.a("RotateDialog", "loadBackground end");
        }
    }

    public /* synthetic */ void c(final String str) {
        if (c()) {
            LogUtil.h("RotateDialog", "loadBackground activity is destoried");
            return;
        }
        Drawable cIb_ = nrf.cIb_(BaseApplication.getContext(), str);
        Object[] objArr = new Object[2];
        objArr[0] = "loadBackground drawable is null ? ";
        objArr[1] = Boolean.valueOf(this.d == null);
        LogUtil.a("RotateDialog", objArr);
        czM_(cIb_);
        HandlerExecutor.e(new Runnable() { // from class: nlo
            @Override // java.lang.Runnable
            public final void run() {
                RotateDialog.this.a(str);
            }
        });
    }

    private boolean c() {
        Context context = this.c;
        return context == null || ((context instanceof Activity) && (((Activity) context).isFinishing() || ((Activity) this.c).isDestroyed()));
    }

    public void e(int i) {
        this.i = i;
    }

    public void e(String str) {
        this.j = str;
    }

    public void g(String str) {
        this.g = str;
    }

    public void b(String str) {
        this.f8820a = str;
    }

    public void d(boolean z) {
        this.n = z;
    }

    public void czO_(View.OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    private void b() {
        View inflate = ((LayoutInflater) this.c.getSystemService("layout_inflater")).inflate(R.layout.commonui_dialog_rotate, (ViewGroup) null);
        this.l = inflate;
        this.f = (LinearLayout) inflate.findViewById(R.id.rotate_card_dialog);
        this.h = (ImageView) this.l.findViewById(R.id.rotate_input_image);
        ImageView imageView = (ImageView) this.l.findViewById(R.id.rotate_card_cancel_dialog);
        this.e = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: nlr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RotateDialog.this.czN_(view);
            }
        });
        setContentView(this.l);
    }

    public /* synthetic */ void czN_(View view) {
        if (!TextUtils.isEmpty(this.j) && this.i != 0 && !TextUtils.isEmpty(this.g)) {
            e();
        }
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        HashMap hashMap = new HashMap();
        hashMap.put("resourceId", this.j);
        hashMap.put("resourcePositionId", Integer.valueOf(this.i));
        hashMap.put("resourceName", this.g);
        hashMap.put("event", 4);
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.m));
        hashMap.put("algId", this.f8820a);
        hashMap.put("smartRecommend", Boolean.valueOf(this.n));
        hashMap.put("pullOrder", 1);
        ixx.d().d(this.c.getApplicationContext(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        if (c() || isShowing()) {
            return;
        }
        super.show();
        LogUtil.a("MarketingResourceSetting", "dialog.setBackground: dialog showed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f, "rotationY", 280.0f, 360.0f);
        long j = i;
        ofFloat.setDuration(j);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f, "alpha", 0.1f, 1.0f);
        ofFloat2.setStartDelay(50L);
        ofFloat2.setDuration(j);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f, "scaleY", 0.3f, 1.0f);
        ofFloat3.setDuration(j);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f, "scaleX", 0.3f, 1.0f);
        ofFloat3.setDuration(j);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat4, ofFloat3);
        animatorSet.setStartDelay(500L);
        animatorSet.setInterpolator(new Interpolator() { // from class: com.huawei.ui.commonui.dialog.RotateDialog.5
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return (float) Math.sin((float) (f * 1.5707963267948966d));
            }
        });
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.huawei.ui.commonui.dialog.RotateDialog.2
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
                int width = RotateDialog.this.h.getWidth();
                int height = RotateDialog.this.h.getHeight();
                LogUtil.a("RotateDialog", "onAnimationEnd， imgWidth:", Integer.valueOf(width), " imgHeight:", Integer.valueOf(height));
                if (width > 0 && height > 0) {
                    RotateDialog.this.e.setVisibility(0);
                } else {
                    RotateDialog.this.dismiss();
                }
            }
        });
        animatorSet.start();
    }

    public void a(OnVisibleListenter onVisibleListenter) {
        this.b = onVisibleListenter;
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog
    public void configDialog() {
        super.configDialog();
        czM_(this.d);
    }

    private void czM_(Drawable drawable) {
        int i;
        int i2;
        LogUtil.a("RotateDialog", "showBitmapInDialog");
        if (c() || drawable == null) {
            LogUtil.h("RotateDialog", "showBitmapInDialog is destory or drawable is null");
            return;
        }
        this.d = drawable;
        int h = nsn.h(getContext());
        int f = nsn.f(getContext());
        LogUtil.a("RotateDialog", "showBitmapInDialog windowWidth=", Integer.valueOf(h), " windowHeight=", Integer.valueOf(f));
        ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
        layoutParams.height = f;
        layoutParams.width = h;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        LogUtil.a("RotateDialog", "showBitmapInDialog drawableW=", Integer.valueOf(intrinsicWidth), " drawableH=", Integer.valueOf(intrinsicHeight));
        int b = f - nsf.b(R.dimen._2131363052_res_0x7f0a04ec);
        if (nsn.ag(getContext())) {
            int r = nsn.r(getContext());
            LogUtil.a("RotateDialog", "showBitmapInDialog statusBarHeight = ", Integer.valueOf(r));
            b -= r;
        }
        int b2 = h - nsf.b(R.dimen._2131362973_res_0x7f0a049d);
        if (b2 * intrinsicHeight >= b * intrinsicWidth) {
            i2 = Math.min(nsn.c(getContext(), intrinsicHeight), b);
            i = (intrinsicWidth * i2) / intrinsicHeight;
        } else {
            int min = Math.min(nsn.c(getContext(), intrinsicWidth), b2);
            int i3 = (intrinsicHeight * min) / intrinsicWidth;
            i = min;
            i2 = i3;
        }
        LogUtil.a("RotateDialog", "showBitmapInDialog imgWidth=", Integer.valueOf(i), " imgHeight=", Integer.valueOf(i2));
        ViewGroup.LayoutParams layoutParams2 = this.h.getLayoutParams();
        layoutParams2.width = i;
        layoutParams2.height = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public void a(String str) {
        if (c()) {
            LogUtil.h("RotateDialog", "loadBackground activity is destoried");
        } else {
            nrf.cIF_(str, new RequestOptions(), new RequestListener<Drawable>() { // from class: com.huawei.ui.commonui.dialog.RotateDialog.3
                @Override // com.bumptech.glide.request.RequestListener
                /* renamed from: czP_, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    LogUtil.a("RotateDialog", "loadBackground onResourceReady");
                    RotateDialog.this.show();
                    RotateDialog.this.b(400);
                    RotateDialog.this.m = System.currentTimeMillis();
                    if (RotateDialog.this.b == null) {
                        return false;
                    }
                    RotateDialog.this.b.onVisible(true, RotateDialog.this.l);
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    LogUtil.h("RotateDialog", "loadBackground onLoadFailed, ", ExceptionUtils.d(glideException));
                    return false;
                }
            }, this.h, false);
        }
    }
}
