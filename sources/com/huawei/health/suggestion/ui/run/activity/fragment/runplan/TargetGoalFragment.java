package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TargetGoalFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.ui.commonui.timepicker.HealthHourSecondNumberPicker;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.gib;
import defpackage.jed;
import defpackage.mog;
import defpackage.nsn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class TargetGoalFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f3342a;
    private ImageView ad;
    private ImageView b;
    private int c;
    private HealthTextView d;
    private Context e;
    private int f;
    private ConstraintLayout h;
    private RelativeLayout k;
    private HealthSeekBar l;
    private HealthColumnLinearLayout m;
    private int n;
    private RelativeLayout o;
    private RunPlanCreateActivity.OnNextPageListener p;
    private int q;
    private HealthTextView r;
    private HealthHourSecondNumberPicker s;
    private int t;
    private HealthTextView u;
    private int v;
    private HealthTextView w;
    private HealthProgressBar z;
    private int y = 1;
    private List<View> i = new ArrayList();
    private List<View> j = new ArrayList();
    private HealthSeekBar.OnSeekBarChangeListener x = new HealthSeekBar.OnSeekBarChangeListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TargetGoalFragment.3
        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
        }

        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
        }

        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
            LogUtil.a("Suggestion_TargetGoalFragment", "onProgressChanged() value:", Integer.valueOf(i));
            TargetGoalFragment.this.a(TargetGoalFragment.this.q - i);
        }
    };
    private Handler g = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TargetGoalFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                TargetGoalFragment.this.d();
            }
        }
    };

    public static TargetGoalFragment e(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("planType", i);
        TargetGoalFragment targetGoalFragment = new TargetGoalFragment();
        targetGoalFragment.setArguments(bundle);
        return targetGoalFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.t = arguments.getInt("planType");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_target_achievement, viewGroup, false);
        aKJ_(inflate);
        c();
        return inflate;
    }

    private void aKJ_(View view) {
        if (view == null) {
            LogUtil.b("Suggestion_TargetGoalFragment", "initView view is null.");
            return;
        }
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.k = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.z = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.ad = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        a(healthTextView);
        this.i.add(healthTextView);
        this.m = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        this.r = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.target_achievement_layout);
        this.i.add(healthCardView);
        this.j.add(healthCardView);
        BaseActivity.setViewSafeRegion(true, healthCardView);
        this.o = (RelativeLayout) view.findViewById(R.id.layout_set_target_time);
        this.w = (HealthTextView) view.findViewById(R.id.target_time);
        this.u = (HealthTextView) view.findViewById(R.id.target_time_dec);
        this.l = (HealthSeekBar) view.findViewById(R.id.run_target_goal_seekbar);
        this.h = (ConstraintLayout) view.findViewById(R.id.forecast_goal_constraintLayout);
        this.b = (ImageView) view.findViewById(R.id.img_arrows_forecast_achieve);
        this.d = (HealthTextView) view.findViewById(R.id.current_forecast_goal_desc);
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.sug_btn_next);
        this.i.add(healthButton);
        this.j.add(healthButton);
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TargetGoalFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                FragmentActivity activity = TargetGoalFragment.this.getActivity();
                if (activity != null && !nsn.a(500)) {
                    if (TargetGoalFragment.this.p != null) {
                        TargetGoalFragment.this.p.backLock();
                        gdk.b(false, TargetGoalFragment.this.j);
                        TargetGoalFragment.this.j();
                        gdk.aLq_(TargetGoalFragment.this.k, TargetGoalFragment.this.m, TargetGoalFragment.this.p, true);
                        ViewClickInstrumentation.clickOnView(view2);
                        return;
                    }
                    activity.finish();
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                LogUtil.h("Suggestion_TargetGoalFragment", "btnNext onClick activity is null or click too fast.");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        gdk.b(true, this.i);
        gdr.aLy_(this.e, 90, this.z, this.ad);
    }

    private void c() {
        this.o.setOnClickListener(new View.OnClickListener() { // from class: gdc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TargetGoalFragment.this.aKL_(view);
            }
        });
    }

    public /* synthetic */ void aKL_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            g();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void g() {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.e);
        this.s = new HealthHourSecondNumberPicker(this.e, this.n, this.q, this.v);
        builder.d(R.string._2130844471_res_0x7f021b37).czg_(this.s).czc_(R.string._2130844419_res_0x7f021b03, new View.OnClickListener() { // from class: gcz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R.string._2130844420_res_0x7f021b04, new View.OnClickListener() { // from class: gda
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TargetGoalFragment.this.aKM_(view);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void aKM_(View view) {
        int selectedTime = this.s.getSelectedTime();
        a(selectedTime);
        this.l.setProgress(this.q - selectedTime);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_TargetGoalFragment", "isHidden fragment.");
        } else {
            this.k.setVisibility(0);
            gdk.b(true, this.i);
        }
    }

    private void a(HealthTextView healthTextView) {
        String b;
        LogUtil.a("Suggestion_TargetGoalFragment", "setTargetGoalAskQuestion mPlanType:", Integer.valueOf(this.t));
        int i = this.t;
        if (i == 3) {
            b = ffy.b(R.plurals._2130903108_res_0x7f030044, 5, jed.b(5.0d, 1, 0));
        } else if (i == 4) {
            b = ffy.b(R.plurals._2130903108_res_0x7f030044, 10, jed.b(10.0d, 1, 0));
        } else if (i == 5) {
            b = getResources().getString(R.string._2130841792_res_0x7f0210c0);
        } else {
            b = i != 6 ? "" : getResources().getString(R.string._2130841793_res_0x7f0210c1);
        }
        healthTextView.setText(String.format(Locale.ROOT, getString(R.string._2130848573_res_0x7f022b3d), b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.l.setMax(this.q - this.n);
        this.l.setTouchable(true);
        this.l.setProgress((int) ((this.q - this.n) * 0.38200003f));
        this.l.setOnSeekBarChangeListener(this.x);
        int i = this.q;
        this.v = ((int) ((i - r1) * 0.618f)) + this.n;
        this.d.setText(getResources().getString(R.string._2130848560_res_0x7f022b30, b(this.f3342a)));
        a(this.v);
        a();
    }

    private String b(int i) {
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "Hms"), Locale.getDefault()).format(new Date(gib.g(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.w.setText(b(i));
        this.v = i;
        if (i >= this.c) {
            this.y = 1;
            this.u.setText(getResources().getString(R.string._2130848561_res_0x7f022b31));
        } else if (i >= this.f) {
            this.y = 2;
            this.u.setText(getResources().getString(R.string._2130848562_res_0x7f022b32));
        } else {
            this.y = 3;
            this.u.setText(getResources().getString(R.string._2130848563_res_0x7f022b33));
        }
    }

    private void a() {
        ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
        if (!(layoutParams instanceof ConstraintLayout.LayoutParams)) {
            LogUtil.h("Suggestion_TargetGoalFragment", "params is not ConstraintLayout.LayoutParams.");
            return;
        }
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        if (this.q == this.n) {
            LogUtil.b("Suggestion_TargetGoalFragment", "mMinAchieve == mMaxAchieve");
            return;
        }
        final float f = ((r1 - this.f3342a) * 1.0f) / (r1 - r3);
        layoutParams2.horizontalBias = f;
        this.b.setLayoutParams(layoutParams2);
        this.h.post(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TargetGoalFragment.1
            @Override // java.lang.Runnable
            public void run() {
                int width = TargetGoalFragment.this.h.getWidth() - TargetGoalFragment.this.getResources().getDimensionPixelSize(R.dimen._2131362635_res_0x7f0a034b);
                float dimension = TargetGoalFragment.this.getResources().getDimension(R.dimen._2131362585_res_0x7f0a0319);
                float dimension2 = TargetGoalFragment.this.getResources().getDimension(R.dimen._2131362328_res_0x7f0a0218);
                float width2 = (TargetGoalFragment.this.d.getWidth() - TargetGoalFragment.this.b.getWidth()) / 2.0f;
                float width3 = width - TargetGoalFragment.this.b.getWidth();
                float f2 = f;
                float width4 = TargetGoalFragment.this.b.getWidth() / 2.0f;
                ViewGroup.LayoutParams layoutParams3 = TargetGoalFragment.this.d.getLayoutParams();
                if (!(layoutParams3 instanceof ConstraintLayout.LayoutParams)) {
                    LogUtil.h("Suggestion_TargetGoalFragment", "goalDescLayoutParams is not ConstraintLayout.LayoutParams.");
                    return;
                }
                ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
                float f3 = (((width3 - (dimension * 2.0f)) * f2) + width4) - width2;
                if (f3 > width - TargetGoalFragment.this.d.getWidth()) {
                    layoutParams4.setMarginStart((int) ((width - TargetGoalFragment.this.d.getWidth()) + dimension2));
                } else if (f3 <= 0.0f) {
                    LogUtil.a("Suggestion_TargetGoalFragment", "initForecastView status startMove - moveWidth <= 0");
                    layoutParams4.setMarginStart((int) dimension2);
                } else {
                    LogUtil.a("Suggestion_TargetGoalFragment", "initForecastView status startMove - moveWidth > 0");
                    layoutParams4.setMarginStart((int) (f3 + dimension2));
                }
                TargetGoalFragment.this.d.setLayoutParams(layoutParams4);
            }
        });
    }

    public void e(mog mogVar) {
        if (mogVar == null) {
            LogUtil.b("Suggestion_TargetGoalFragment", "initController update data is null.");
            return;
        }
        this.q = mogVar.a();
        this.n = mogVar.c();
        this.c = mogVar.e();
        this.f = mogVar.d();
        this.f3342a = mogVar.b();
        LogUtil.a("Suggestion_TargetGoalFragment", "TrainingPredictionBean", new Gson().toJson(mogVar));
        this.g.sendMessage(this.g.obtainMessage(0));
    }

    public int b() {
        return this.v;
    }

    public int e() {
        return this.y;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.r.setText(b(this.v));
    }

    public void a(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.p = onNextPageListener;
    }
}
