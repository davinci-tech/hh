package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.basichealthmodel.callback.CloverTaskStatusOnClickListener;
import com.huawei.basichealthmodel.ui.view.CloverTaskStatusView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ucd.cloveranimation.AddFrameListener;
import com.huawei.ucd.cloveranimation.CloverView;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.Clover;
import defpackage.azi;
import defpackage.koq;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsk;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public class CloverTaskStatusView extends LinearLayout implements View.OnClickListener, CloverView.OnPetalClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Clover f1922a;
    private ImageView b;
    private ConstraintLayout c;
    private CloverTaskStatusOnClickListener d;
    private Context e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private ImageView i;
    private List<HealthLifeBean> j;
    private HealthTextView k;
    private ImageView l;
    private ImageView m;
    private HealthTextView n;
    private HealthTextView o;
    private ViewStub p;
    private int q;
    private View r;
    private int s;

    public CloverTaskStatusView(Context context) {
        super(context);
        e();
    }

    public CloverTaskStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public CloverTaskStatusView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    private void e() {
        Context e = BaseApplication.e();
        this.e = e;
        inflate(e, R.layout.view_stub_task_status, this);
        if (this.p == null) {
            this.p = (ViewStub) findViewById(R.id.view_stub_task_status);
        }
    }

    private void c() {
        if (this.p.getParent() != null) {
            try {
                this.r = this.p.inflate();
                ReleaseLogUtil.b("R_HealthLife_TaskStatusView", "init inflate");
            } catch (Resources.NotFoundException | InflateException | IllegalArgumentException | IllegalStateException e) {
                ReleaseLogUtil.c("R_HealthLife_TaskStatusView", "init exception ", ExceptionUtils.d(e));
            }
        }
        if (this.r == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "init mView is null");
            return;
        }
        if (this.b == null) {
            ImageView imageView = (ImageView) findViewById(R.id.view_task_status_kaka);
            this.b = imageView;
            imageView.setOnClickListener(this);
        }
        if (this.f1922a == null) {
            Clover clover = (Clover) findViewById(R.id.view_task_status_clover);
            this.f1922a = clover;
            clover.setCloverWithIcon(0.0f, 0.0f, 0.0f);
            this.f1922a.setAddFrameListener(new AddFrameListener() { // from class: ayz
                @Override // com.huawei.ucd.cloveranimation.AddFrameListener
                public final void onFinished() {
                    LogUtil.a("HealthLife_TaskStatusView", "init onFinished");
                }
            });
        }
        if (this.c == null) {
            this.c = (ConstraintLayout) findViewById(R.id.view_task_status_layout);
        }
        if (this.o == null) {
            this.o = (HealthTextView) findViewById(R.id.view_task_status_sport);
        }
        if (this.k == null) {
            this.k = (HealthTextView) findViewById(R.id.view_task_status_sport_value);
        }
        if (this.g == null) {
            this.g = (HealthTextView) findViewById(R.id.view_task_status_mood);
        }
        if (this.f == null) {
            this.f = (HealthTextView) findViewById(R.id.view_task_status_mood_value);
        }
        if (this.h == null) {
            this.h = (HealthTextView) findViewById(R.id.view_task_status_slumber);
        }
        if (this.n == null) {
            this.n = (HealthTextView) findViewById(R.id.view_task_status_slumber_value);
        }
        i();
        f();
        this.f1922a.setPetalClickListener(this);
    }

    private void f() {
        int i;
        if (this.i == null) {
            this.i = (ImageView) findViewById(R.id.view_task_status_mood_arrow);
        }
        if (this.l == null) {
            this.l = (ImageView) findViewById(R.id.view_task_status_slumber_arrow);
        }
        if (this.m == null) {
            this.m = (ImageView) findViewById(R.id.view_task_status_sport_arrow);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.view_task_status_slumber_layout);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(this);
        }
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.view_task_status_sport_layout);
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(this);
        }
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.view_task_status_mood_layout);
        if (linearLayout3 != null) {
            linearLayout3.setOnClickListener(this);
        }
        boolean g = g();
        this.i.setVisibility(g ? 0 : 8);
        this.l.setVisibility(g ? 0 : 8);
        this.m.setVisibility(g ? 0 : 8);
        if (g) {
            if (nrt.a(this.e)) {
                i = R$drawable.ic_right_grey_update_dark;
            } else {
                i = R$drawable.ic_right_grey_update;
            }
            Drawable cKn_ = LanguageUtil.bc(this.e) ? nrz.cKn_(this.e, i) : ContextCompat.getDrawable(this.e, i);
            this.i.setImageDrawable(cKn_);
            this.l.setImageDrawable(cKn_);
            this.m.setImageDrawable(cKn_);
            int i2 = this.q;
            if (i2 <= 0) {
                LogUtil.h("HealthLife_TaskStatusView", "setArrow mWidth ", Integer.valueOf(i2));
                return;
            }
            int b = (int) azi.b((this.q - nsf.a(R.dimen._2131362935_res_0x7f0a0477)) - nsf.a(R.dimen._2131363122_res_0x7f0a0532), 0);
            this.f.setMaxWidth(b);
            this.k.setMaxWidth(b);
            this.n.setMaxWidth(b);
        }
    }

    private boolean g() {
        HealthLifeBean healthLifeBean;
        return koq.b(this.j) || (healthLifeBean = this.j.get(0)) == null || this.s == healthLifeBean.getRecordDay();
    }

    private void e(boolean z) {
        Clover clover;
        c();
        View view = this.r;
        if (view == null || (clover = this.f1922a) == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "initClover mView ", view, " mClover ", this.f1922a, " isAllFull ", Boolean.valueOf(z));
            return;
        }
        if (clover.getVisibility() != 0) {
            this.f1922a.setVisibility(0);
        }
        this.f1922a.d();
        this.f1922a.setPlayAllFrameAnimation(true);
        this.f1922a.setAllFull(z);
    }

    private void i() {
        int i = this.q;
        if (i <= 0) {
            LogUtil.h("HealthLife_TaskStatusView", "setLayoutParams mWidth ", Integer.valueOf(i));
        } else {
            h();
        }
    }

    private void h() {
        if (LanguageUtil.v(this.e)) {
            this.o.setTextSize(1, 9.0f);
            this.k.setTextSize(1, 9.0f);
            this.g.setTextSize(1, 9.0f);
            this.f.setTextSize(1, 9.0f);
            this.h.setTextSize(1, 9.0f);
            this.n.setTextSize(1, 9.0f);
        }
    }

    private Drawable getLastDrawable() {
        return ContextCompat.getDrawable(this.e, R.drawable._2131430607_res_0x7f0b0ccf);
    }

    private void b(HealthTextView healthTextView, CharSequence charSequence, int i) {
        if (TextUtils.isEmpty(charSequence)) {
            LogUtil.h("HealthLife_TaskStatusView", "setText charSequence is empty type = ", Integer.valueOf(i));
            this.o.setVisibility(8);
            this.g.setVisibility(8);
            this.h.setVisibility(8);
            this.k.setVisibility(8);
            this.f.setVisibility(8);
            this.n.setVisibility(8);
            return;
        }
        healthTextView.setText(charSequence);
    }

    private void c(HealthTextView healthTextView, CharSequence charSequence, int i) {
        b(healthTextView, charSequence, i);
        if (LanguageUtil.h(this.e)) {
            healthTextView.setTypeface(nsk.cKN_());
        }
    }

    public void setKakaVisibility(int i) {
        ImageView imageView;
        c();
        View view = this.r;
        if (view == null || (imageView = this.b) == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "setKakaVisibility mView ", view, " mKaka ", this.b, " visibility ", Integer.valueOf(i));
            return;
        }
        imageView.setImageDrawable(getLastDrawable());
        if (this.b.getVisibility() == 0) {
            if (i != 0) {
                this.b.setVisibility(4);
            }
        } else if (i == 0) {
            this.b.setVisibility(0);
        }
    }

    public void setCloverWithIcon(float f, float f2, float f3, boolean z) {
        Clover clover;
        e(z);
        View view = this.r;
        if (view == null || (clover = this.f1922a) == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "setCloverWithIcon mView ", view, " mClover ", this.f1922a, " top ", Float.valueOf(f), " left ", Float.valueOf(f2), " right ", Float.valueOf(f3), " isAllFull ", Boolean.valueOf(z));
        } else {
            clover.setCloverWithIcon(f, f2, f3);
        }
    }

    public void setCloverRunAnimation(float f, float f2, float f3, boolean z, boolean z2) {
        Clover clover;
        e(z);
        View view = this.r;
        if (view == null || (clover = this.f1922a) == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "setCloverRunAnimation mView ", view, " mClover ", this.f1922a, " top ", Float.valueOf(f), " left ", Float.valueOf(f2), " right ", Float.valueOf(f3), " isAllFull ", Boolean.valueOf(z), " isLastBasicTask ", Boolean.valueOf(z2));
            return;
        }
        clover.setCloverRunAnimator(f, f2, f3);
        if (z) {
            if (z2) {
                this.f1922a.postDelayed(new Runnable() { // from class: azd
                    @Override // java.lang.Runnable
                    public final void run() {
                        CloverTaskStatusView.this.d();
                    }
                }, 3000L);
            } else {
                this.f1922a.a();
            }
        }
    }

    public /* synthetic */ void d() {
        this.f1922a.a();
    }

    public void setCloverRunGrowAnimation(float f, float f2, float f3, boolean z) {
        Clover clover;
        e(z);
        View view = this.r;
        if (view == null || (clover = this.f1922a) == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "setCloverRunGrowAnimation mView ", view, " mClover ", this.f1922a, " top ", Float.valueOf(f), " left ", Float.valueOf(f2), " right ", Float.valueOf(f3), " isAllFull ", Boolean.valueOf(z));
        } else {
            clover.setCloverRunGrowAnimator(f, f2, f3);
        }
    }

    public void setTaskStatus(SparseArray<CharSequence> sparseArray, SparseArray<CharSequence> sparseArray2, List<HealthLifeBean> list, int i) {
        HealthTextView healthTextView;
        if (sparseArray == null || sparseArray2 == null || koq.b(list)) {
            LogUtil.h("HealthLife_TaskStatusView", "setTaskStatus titleSparseArray is null or valueSparseArray is null");
            return;
        }
        this.j = list;
        this.s = i;
        c();
        View view = this.r;
        if (view == null || this.c == null || (healthTextView = this.o) == null || this.g == null || this.h == null || this.k == null || this.f == null || this.n == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "setTaskStatus mView ", view, " mLayout ", this.c, " mSport ", this.o, " mMood ", this.g, " mSlumber ", this.h, " mSportValue ", this.k, " mMoodValue ", this.f, " mSlumberValue ", this.n);
            return;
        }
        b(healthTextView, sparseArray.get(1), 1);
        b(this.g, sparseArray.get(2), 2);
        b(this.h, sparseArray.get(3), 3);
        c(this.k, sparseArray2.get(1), 1);
        c(this.f, sparseArray2.get(2), 2);
        c(this.n, sparseArray2.get(3), 3);
        if (this.c.getVisibility() != 0) {
            this.c.setVisibility(0);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(300L);
            this.c.startAnimation(alphaAnimation);
        }
        i();
    }

    public void setWidth(int i) {
        this.q = i;
    }

    public void b() {
        c();
    }

    public void setAnimationListener(AddFrameListener addFrameListener) {
        Clover clover;
        c();
        View view = this.r;
        if (view == null || (clover = this.f1922a) == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskStatusView", "setAnimationListener mView ", view, " mClover ", this.f1922a, " listener ", addFrameListener);
        } else {
            clover.setAnimEndListener(addFrameListener);
        }
    }

    public void setCloverTaskStatusOnClickListener(CloverTaskStatusOnClickListener cloverTaskStatusOnClickListener) {
        this.d = cloverTaskStatusOnClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("HealthLife_TaskStatusView", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.d == null) {
            LogUtil.h("HealthLife_TaskStatusView", "onClick mCloverTaskStatusOnClickListener is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.view_task_status_kaka) {
            this.d.onCloverTaskStatusKakaClick();
        }
        if (id == R.id.view_task_status_slumber_layout) {
            this.d.onBasicTaskClick(6);
        }
        if (id == R.id.view_task_status_sport_layout) {
            this.d.onBasicTaskClick(azi.d(this.j, 1));
        }
        if (id == R.id.view_task_status_mood_layout) {
            this.d.onBasicTaskClick(5);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView.OnPetalClickListener
    public void onTopClick() {
        CloverTaskStatusOnClickListener cloverTaskStatusOnClickListener = this.d;
        if (cloverTaskStatusOnClickListener != null) {
            cloverTaskStatusOnClickListener.onBasicTaskClick(azi.d(this.j, 1));
        }
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView.OnPetalClickListener
    public void onLeftClick() {
        CloverTaskStatusOnClickListener cloverTaskStatusOnClickListener = this.d;
        if (cloverTaskStatusOnClickListener != null) {
            cloverTaskStatusOnClickListener.onBasicTaskClick(6);
        }
    }

    @Override // com.huawei.ucd.cloveranimation.CloverView.OnPetalClickListener
    public void onRightClick() {
        CloverTaskStatusOnClickListener cloverTaskStatusOnClickListener = this.d;
        if (cloverTaskStatusOnClickListener != null) {
            cloverTaskStatusOnClickListener.onBasicTaskClick(5);
        }
    }
}
