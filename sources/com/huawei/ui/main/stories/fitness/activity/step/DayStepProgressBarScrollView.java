package com.huawei.ui.main.stories.fitness.activity.step;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import defpackage.jcf;
import defpackage.koq;
import defpackage.mdf;
import defpackage.nip;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.pvm;
import defpackage.sco;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class DayStepProgressBarScrollView extends RelativeLayout implements ScrollChartObserverTotalDataView.OnDataChange {

    /* renamed from: a, reason: collision with root package name */
    private HealthProgressBar f9888a;
    private final d b;
    private final Context c;
    private final Context d;
    private View e;
    private ImageView f;
    private volatile boolean g;
    private volatile boolean h;
    private LinearLayout i;
    private volatile boolean j;
    private PopupWindow k;
    private final Handler l;
    private int m;
    private float n;
    private int o;

    public DayStepProgressBarScrollView(Context context) {
        super(context);
        this.d = BaseApplication.e();
        this.k = null;
        d dVar = new d();
        this.b = dVar;
        this.m = 0;
        this.o = 0;
        this.l = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.h("Step_DayStepProgressBarScrollView", "message is null.");
                    return;
                }
                super.handleMessage(message);
                int i = message.what;
                if (i == 200) {
                    DayStepProgressBarScrollView.this.j();
                } else {
                    if (i != 201) {
                        return;
                    }
                    DayStepProgressBarScrollView.this.getKakaTaskList();
                }
            }
        };
        b(context);
        this.c = context;
        LogUtil.a("Step_DayStepProgressBarScrollView", "Step Goal fetch mGoalValue=", Integer.valueOf(dVar.e));
        i();
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.day_step_progress_layout, this);
        this.e = inflate.findViewById(R.id.day_progress_layout);
        this.f9888a = (HealthProgressBar) inflate.findViewById(R.id.day_step_progressbar);
        this.i = (LinearLayout) findViewById(R.id.kaka_list_layout);
        this.f = (ImageView) findViewById(R.id.kaka_bubble);
        o();
        this.f9888a.setMax(100);
        this.f9888a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (DayStepProgressBarScrollView.this.b.b == -1) {
                    DayStepProgressBarScrollView.this.b.b = DayStepProgressBarScrollView.this.f9888a.getWidth();
                    if (DayStepProgressBarScrollView.this.b.b != 0) {
                        DayStepProgressBarScrollView.this.m = Math.round((r0.f9888a.getHeight() * 100.0f) / DayStepProgressBarScrollView.this.b.b);
                        DayStepProgressBarScrollView.this.getKakaTaskList();
                    }
                }
            }
        });
        this.f9888a.setProgressDrawable(nsf.cKq_(R.drawable._2131431147_res_0x7f0b0eeb));
    }

    public void setKakaListLayoutVisibility(int i) {
        this.i.setVisibility(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getKakaTaskList() {
        sco.a(new a(this));
    }

    private void dua_(LinearLayout linearLayout, d dVar) {
        int b2;
        if (dVar.e == 0) {
            LogUtil.h("Step_DayStepProgressBarScrollView", "addView goalValue == 0");
            return;
        }
        Collections.sort(dVar.f9893a, new Comparator<mdf>() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.1
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(mdf mdfVar, mdf mdfVar2) {
                if (mdfVar == null || mdfVar2 == null) {
                    return 0;
                }
                return mdfVar.ad() - mdfVar2.ad();
            }
        });
        Iterator<mdf> it = dVar.f9893a.iterator();
        while (it.hasNext()) {
            mdf next = it.next();
            int ad = next.ad();
            int h = CommonUtil.h(next.p());
            if (h != 2 && next.ac() != 2) {
                ReleaseLogUtil.e("Step_DayStepProgressBarScrollView", "level ", Integer.valueOf(ad), "mGoalValue ", Integer.valueOf(dVar.e));
                if (ad > dVar.e) {
                    return;
                }
                int round = Math.round(((ad * dVar.b) * 1.0f) / dVar.e);
                if (round == dVar.b) {
                    round = (round - nsf.b(h == 1 ? R.dimen._2131362954_res_0x7f0a048a : R.dimen._2131362922_res_0x7f0a046a)) - ((Integer) BaseActivity.getSafeRegionWidth().second).intValue();
                    b2 = nsf.b(R.dimen._2131363003_res_0x7f0a04bb);
                } else {
                    b2 = nsf.b(R.dimen._2131363122_res_0x7f0a0532);
                }
                View dVR_ = sco.dVR_(round - b2, h);
                if (dVR_ instanceof ImageView) {
                    ImageView imageView = (ImageView) dVR_;
                    dtZ_(h, imageView, next);
                    linearLayout.addView(imageView);
                }
            }
        }
    }

    public void a() {
        this.f9888a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                DayStepProgressBarScrollView.this.b.b = DayStepProgressBarScrollView.this.f9888a.getWidth();
                if (DayStepProgressBarScrollView.this.b.b != 0) {
                    DayStepProgressBarScrollView.this.m = Math.round((r0.f9888a.getHeight() * 100.0f) / DayStepProgressBarScrollView.this.b.b);
                    DayStepProgressBarScrollView.this.getKakaTaskList();
                }
            }
        });
    }

    private void dtZ_(int i, final ImageView imageView, final mdf mdfVar) {
        if (i == 0) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DayStepProgressBarScrollView.this.duc_(imageView, mdfVar.ad(), mdfVar.n());
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else if (i == 1) {
            sco.dVS_(imageView.getDrawable());
            imageView.setOnClickListener(new e(this.c, mdfVar));
        } else {
            LogUtil.a("Step_DayStepProgressBarScrollView", "No event is listened on imageView.");
        }
    }

    static class e implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<Context> f9894a;
        private mdf b;

        e(Context context, mdf mdfVar) {
            this.f9894a = new WeakReference<>(context);
            this.b = mdfVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            mdf mdfVar;
            Context context = this.f9894a.get();
            if (context == null || (mdfVar = this.b) == null) {
                LogUtil.b("Step_DayStepProgressBarScrollView", "context == null || mTaskInfo == null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            int n = mdfVar.n();
            ReleaseLogUtil.e("Step_DayStepProgressBarScrollView", "addView award ", Integer.valueOf(n));
            if (context instanceof FitnessStepDetailActivity) {
                ((FitnessStepDetailActivity) context).a(n, this.b.h(), this.b.ac());
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b(float f) {
        LogUtil.a("Step_DayStepProgressBarScrollView", "tmpTotal ", Float.valueOf(f), "goalValue ", Integer.valueOf(this.b.e));
        Context context = this.c;
        if (context instanceof FitnessStepDetailActivity) {
            ((FitnessStepDetailActivity) context).d((int) this.n, this.b.e);
        }
        if (f >= this.b.e && this.b.e != 0) {
            pvm.b(this.c);
        } else {
            pvm.e(this.c, f, this.b.e);
        }
        pvm.e(this.c, this.b.e);
        if (this.b.e != 0 && f != 0.0f) {
            LogUtil.a("Step_DayStepProgressBarScrollView", "mCompletePercent ", Integer.valueOf(pvm.d(f, this.b.e)));
            this.l.sendEmptyMessage(200);
        } else {
            this.f9888a.setProgress(0);
            g();
        }
    }

    private void g() {
        jcf.bEB_(this.e, nsf.a(R.plurals._2130903528_res_0x7f0301e8, this.b.e, Integer.valueOf((int) this.n), Integer.valueOf(this.b.e), UnitUtil.e(this.o, 2, 0)), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        HandlerExecutor.e(new Runnable() { // from class: pvh
            @Override // java.lang.Runnable
            public final void run() {
                DayStepProgressBarScrollView.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        if (this.j && this.g) {
            b(this.n);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.OnDataChange
    public void onChange(float f) {
        this.o = 0;
        this.n = f;
        this.j = true;
        k();
    }

    public void e(int i) {
        this.i.removeAllViews();
        this.b.e = i;
        LogUtil.a("Step_DayStepProgressBarScrollView", "enter goal", Integer.valueOf(i));
        this.g = true;
        this.o = 0;
        k();
        getKakaTaskList();
    }

    private void i() {
        nip.d("900200006", new b(this));
    }

    public static class b implements IBaseResponseCallback {
        private final WeakReference<DayStepProgressBarScrollView> b;

        b(DayStepProgressBarScrollView dayStepProgressBarScrollView) {
            this.b = new WeakReference<>(dayStepProgressBarScrollView);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("Step_DayStepProgressBarScrollView", "GoalInfoCommonListener Enter,data is ", obj);
            DayStepProgressBarScrollView dayStepProgressBarScrollView = this.b.get();
            if (dayStepProgressBarScrollView == null) {
                ReleaseLogUtil.d("Step_DayStepProgressBarScrollView", "scrollView == null");
                return;
            }
            if (obj instanceof Integer) {
                LogUtil.h("Step_DayStepProgressBarScrollView", "data not instance Of integer");
                dayStepProgressBarScrollView.b.e = ((Integer) obj).intValue();
            } else {
                ReleaseLogUtil.c("Step_DayStepProgressBarScrollView", "target is not ready,use default");
            }
            LogUtil.a("Step_DayStepProgressBarScrollView", "mGoalValue is back ");
            dayStepProgressBarScrollView.g = true;
            dayStepProgressBarScrollView.k();
            dayStepProgressBarScrollView.m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        int i = this.b.e;
        int max = Math.max(pvm.d(this.n, i), this.m);
        float f = this.n;
        if (f <= 0.0f || i <= 0) {
            return;
        }
        float f2 = i;
        float min = Math.min(1.0f, (f * 1.0f) / f2);
        if (this.n >= f2) {
            this.o = 100;
            a(min, 1000);
        } else {
            this.f.setVisibility(4);
            this.o = max;
            this.f9888a.setProgress(max, true);
            g();
        }
    }

    public void a(final float f, final int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, f);
        this.f9888a.setOnSetProgressAnimationDurationListener(new HwProgressBar.OnSetProgressAnimationDurationListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.7
            @Override // com.huawei.uikit.hwprogressbar.widget.HwProgressBar.OnSetProgressAnimationDurationListener
            public long getAnimationDuration(float f2) {
                LogUtil.a("Step_DayStepProgressBarScrollView", "getAnimationDuration is ", Float.valueOf(f2));
                return i;
            }
        });
        this.f9888a.setProgress(this.o, true);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.9
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
                if (f >= 1.0f) {
                    DayStepProgressBarScrollView.this.f.setVisibility(0);
                    LogUtil.a("Step_DayStepProgressBarScrollView", "start animation from applyAlphaAnimator");
                    sco.dVS_(DayStepProgressBarScrollView.this.f.getDrawable());
                }
            }
        });
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setDuration(i);
        ofFloat.start();
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void duc_(ImageView imageView, int i, int i2) {
        if (i < ((int) this.n)) {
            LogUtil.h("Step_DayStepProgressBarScrollView", " mTotalValue greater than level");
            return;
        }
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.kaka_receive_popupwindow, (ViewGroup) null);
        boolean z = (((float) i) * 1.0f) / ((float) this.b.e) < 0.5f;
        dub_(inflate, i, i2, z);
        this.k = new PopupWindow(inflate, -2, -2);
        inflate.measure(0, 0);
        int measuredWidth = inflate.getMeasuredWidth();
        int measuredHeight = inflate.getMeasuredHeight();
        int[] iArr = new int[2];
        this.k.setOutsideTouchable(true);
        imageView.getLocationOnScreen(iArr);
        int b2 = (iArr[1] - measuredHeight) - nsf.b(R.dimen._2131362869_res_0x7f0a0435);
        int i3 = iArr[0];
        int b3 = nsf.b(R.dimen._2131363060_res_0x7f0a04f4);
        if (nsn.ac(this.d) && !LanguageUtil.bc(this.d)) {
            i3 = (int) (i3 - (nsn.n() * 0.5d));
        }
        if (LanguageUtil.bc(this.d)) {
            if (z) {
                this.k.showAtLocation(imageView, 0, (i3 - measuredWidth) + b3, b2);
            } else {
                this.k.showAtLocation(imageView, 0, (i3 + ((int) (imageView.getWidth() * 0.5f))) - b3, b2);
            }
        } else if (z) {
            this.k.showAtLocation(imageView, 0, (i3 + ((int) (imageView.getWidth() * 0.5f))) - b3, b2);
        } else {
            this.k.showAtLocation(imageView, 0, (i3 - measuredWidth) + b3, b2);
        }
        h();
    }

    private void dub_(View view, int i, int i2, boolean z) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.kaka_tip);
        healthTextView.setMaxWidth((int) (nsn.n() * 0.6f));
        int i3 = i - ((int) this.n);
        healthTextView.setText(nsf.b(R$string.IDS_step_kaka_receive, nsf.a(R.plurals._2130903205_res_0x7f0300a5, i3, Integer.valueOf(i3)).replace(String.valueOf(i3), UnitUtil.e(i3, 1, 0)), nsf.a(R.plurals._2130903212_res_0x7f0300ac, i2, Integer.valueOf(i2)).replace(String.valueOf(i2), UnitUtil.e(i2, 1, 0))));
        if (LanguageUtil.bc(this.d)) {
            if (z) {
                healthTextView.setBackgroundResource(R.drawable._2131430738_res_0x7f0b0d52);
                return;
            } else {
                healthTextView.setBackgroundResource(R.drawable._2131430737_res_0x7f0b0d51);
                return;
            }
        }
        if (z) {
            healthTextView.setBackgroundResource(R.drawable._2131430737_res_0x7f0b0d51);
        } else {
            healthTextView.setBackgroundResource(R.drawable._2131430738_res_0x7f0b0d52);
        }
    }

    private void h() {
        this.l.postDelayed(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.step.DayStepProgressBarScrollView.8
            @Override // java.lang.Runnable
            public void run() {
                if (DayStepProgressBarScrollView.this.k == null || !DayStepProgressBarScrollView.this.k.isShowing()) {
                    return;
                }
                DayStepProgressBarScrollView.this.k.dismiss();
                DayStepProgressBarScrollView.this.k = null;
            }
        }, 2000L);
    }

    public void b() {
        this.l.sendMessage(this.l.obtainMessage(201));
    }

    private void o() {
        int i;
        int i2;
        if (f()) {
            i = ((Integer) BaseActivity.getSafeRegionWidth().first).intValue();
            i2 = ((Integer) BaseActivity.getSafeRegionWidth().second).intValue();
        } else {
            i = 0;
            i2 = 0;
        }
        ViewGroup.LayoutParams layoutParams = this.f9888a.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.setMarginEnd(i2);
        }
        if (f()) {
            return;
        }
        ViewGroup.LayoutParams layoutParams2 = this.i.getLayoutParams();
        if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
            marginLayoutParams2.setMarginStart(i);
            marginLayoutParams2.setMarginEnd(i2);
        }
    }

    private boolean f() {
        return ((Integer) BaseActivity.getSafeRegionWidth().first).intValue() > 0 || ((Integer) BaseActivity.getSafeRegionWidth().second).intValue() > 0;
    }

    static class a implements ResponseCallback {
        private WeakReference<DayStepProgressBarScrollView> e;

        a(DayStepProgressBarScrollView dayStepProgressBarScrollView) {
            this.e = new WeakReference<>(dayStepProgressBarScrollView);
        }

        @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
        public void onResult(int i, Object obj) {
            WeakReference<DayStepProgressBarScrollView> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("Step_DayStepProgressBarScrollView", "KakaResponseCallback mActivityReference == null");
                return;
            }
            DayStepProgressBarScrollView dayStepProgressBarScrollView = weakReference.get();
            if (dayStepProgressBarScrollView == null) {
                LogUtil.h("Step_DayStepProgressBarScrollView", "KakaResponseCallback activity == null");
                return;
            }
            LogUtil.a("Step_DayStepProgressBarScrollView", "errCode ", Integer.valueOf(i));
            if (koq.e(obj, mdf.class)) {
                LogUtil.c("Step_DayStepProgressBarScrollView", "objData ", obj.toString());
                dayStepProgressBarScrollView.b.f9893a = (ArrayList) obj;
                dayStepProgressBarScrollView.h = true;
                dayStepProgressBarScrollView.m();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.h && this.g) {
            LogUtil.a("Step_DayStepProgressBarScrollView", "is all back, enter Kaka");
            e();
        }
    }

    private void e() {
        HandlerExecutor.e(new Runnable() { // from class: pvb
            @Override // java.lang.Runnable
            public final void run() {
                DayStepProgressBarScrollView.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        dua_(this.i, this.b);
    }

    public static class d {
        int b = -1;
        int e = 10000;

        /* renamed from: a, reason: collision with root package name */
        ArrayList<mdf> f9893a = new ArrayList<>(16);

        public String toString() {
            return "mProgressWidth is " + this.b + "mGoalValue is " + this.e + "mTaskListBeanArrayList size," + this.f9893a.size();
        }
    }
}
