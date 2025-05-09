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
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.datepicker.HealthDatePicker;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.jdl;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/* loaded from: classes4.dex */
public class SetCompeteDateFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3340a;
    private HealthDatePicker b;
    private Context c;
    private HealthButton d;
    private Calendar e;
    private HealthColumnLinearLayout f;
    private RelativeLayout g;
    private ImageView k;
    private RunPlanCreateActivity.OnNextPageListener l;
    private HealthTextView m;
    private long n;
    private HealthProgressBar o;
    private List<View> j = new ArrayList();
    private List<View> h = new ArrayList();
    private Handler i = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.SetCompeteDateFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                SetCompeteDateFragment.this.b();
            }
        }
    };

    public static SetCompeteDateFragment d() {
        return new SetCompeteDateFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_competition_date, viewGroup, false);
        aKE_(inflate);
        return inflate;
    }

    private void aKE_(View view) {
        if (view == null) {
            LogUtil.b("Suggestion_CompetitionDateFragment", "initView view is null.");
            return;
        }
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.g = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.o = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.k = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        healthTextView.setText(getString(R.string._2130848572_res_0x7f022b3c));
        this.j.add(healthTextView);
        this.f = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        this.m = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary);
        HealthTextView healthTextView2 = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_comments);
        this.f3340a = healthTextView2;
        healthTextView2.setVisibility(0);
        this.j.add(this.f3340a);
        this.h.add(this.f3340a);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.competition_date_select_layout);
        this.j.add(healthCardView);
        this.h.add(healthCardView);
        BaseActivity.setViewSafeRegion(true, this.f3340a, healthCardView);
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.sug_btn_next);
        this.d = healthButton;
        this.j.add(healthButton);
        this.h.add(this.d);
        this.b = (HealthDatePicker) view.findViewById(R.id.event_date_picker);
        b();
        gdk.b(true, this.j);
        gdr.aLy_(this.c, 80, this.o, this.k);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_CompetitionDateFragment", "isHidden fragment.");
        } else {
            this.g.setVisibility(0);
            gdk.b(true, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(this.n);
        gregorianCalendar.add(5, 28);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.setTimeInMillis(this.n);
        gregorianCalendar2.add(5, 112);
        this.f3340a.setText(ffy.d(BaseApplication.e(), R.string._2130848622_res_0x7f022b6e, UnitUtil.a(gregorianCalendar.getTime(), 20), UnitUtil.a(gregorianCalendar2.getTime(), 20)));
        this.b.setmIsSupportLunarSwitch(false);
        this.b.setmIsWestern(true);
        this.b.a(gregorianCalendar, gregorianCalendar2);
        this.b.a(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5));
        this.e = Calendar.getInstance();
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.SetCompeteDateFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FragmentActivity activity = SetCompeteDateFragment.this.getActivity();
                if (activity != null && !nsn.a(500)) {
                    if (SetCompeteDateFragment.this.l != null) {
                        SetCompeteDateFragment.this.l.backLock();
                        gdk.b(false, SetCompeteDateFragment.this.h);
                        SetCompeteDateFragment.this.e();
                        gdk.aLq_(SetCompeteDateFragment.this.g, SetCompeteDateFragment.this.f, SetCompeteDateFragment.this.l, true);
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    activity.finish();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("Suggestion_CompetitionDateFragment", "mBtnNext onClick activity is null or click too fast.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.m.setText(DateFormat.format("yyyy-MM-dd", c()));
    }

    public void a() {
        this.i.sendEmptyMessage(1);
    }

    public void c(long j) {
        this.n = j;
    }

    public long c() {
        Calendar calendar = this.e;
        if (calendar == null) {
            return 0L;
        }
        calendar.set(this.b.getYear(), this.b.getMonth() - 1, this.b.getDayOfMonth());
        return jdl.e(this.e.getTimeInMillis());
    }

    public void d(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.l = onNextPageListener;
    }
}
