package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.jec;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class StartTrainDateFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f3341a;
    private HealthColumnLinearLayout c;
    private Context e;
    private RunPlanCreateActivity.OnNextPageListener f;
    private ImageView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthProgressBar l;
    private long j = System.currentTimeMillis();
    private List<View> d = new ArrayList();
    private List<View> b = new ArrayList();

    public static StartTrainDateFragment d() {
        return new StartTrainDateFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_start_train_date, viewGroup, false);
        aKG_(inflate);
        return inflate;
    }

    private void aKG_(View view) {
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.f3341a = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.l = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.g = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        healthTextView.setText(getString(R.string._2130848570_res_0x7f022b3a));
        this.d.add(healthTextView);
        this.c = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        this.h = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary);
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.sub_header_train_date);
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this.e, R.color._2131296971_res_0x7f0902cb));
        this.d.add(healthSubHeader);
        this.b.add(healthSubHeader);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.card_view_start_train_date);
        this.d.add(healthCardView);
        this.b.add(healthCardView);
        BaseActivity.setViewSafeRegion(true, healthSubHeader, healthCardView);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.start_train_time);
        this.i = healthTextView2;
        healthTextView2.setText(c());
        HealthCalendarView healthCalendarView = (HealthCalendarView) view.findViewById(R.id.start_train_calendar_view);
        healthCalendarView.b(true);
        HealthCalendar healthCalendar = new HealthCalendar();
        Date e = jec.e();
        healthCalendar.setTime(e);
        HealthCalendar healthCalendar2 = new HealthCalendar();
        healthCalendar2.setTime(new Date(e.getTime() + 518400000));
        d(healthCalendarView, healthCalendar, healthCalendar2);
        gdk.b(true, this.d);
        gdr.aLy_(this.e, 45, this.l, this.g);
    }

    private void d(HealthCalendarView healthCalendarView, HealthCalendar healthCalendar, HealthCalendar healthCalendar2) {
        healthCalendarView.setRange(healthCalendar, healthCalendar2, false);
        healthCalendarView.d(false);
        healthCalendarView.setMonthViewIsScroll(false);
        healthCalendarView.setOnCalendarSelectListener(new HealthCalendarView.OnCalendarSelectListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.StartTrainDateFragment.1
            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarOutOfRange(HealthCalendar healthCalendar3) {
            }

            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarSelect(HealthCalendar healthCalendar3, boolean z) {
                FragmentActivity activity = StartTrainDateFragment.this.getActivity();
                if (activity != null && !nsn.a(500)) {
                    if (StartTrainDateFragment.this.f == null) {
                        activity.finish();
                        return;
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(healthCalendar3.getYear(), healthCalendar3.getMonth() - 1, healthCalendar3.getDay());
                    if (calendar.get(6) != Calendar.getInstance().get(6)) {
                        LogUtil.a("Suggestion_StartTrainDateFragment", "plan start date:", calendar.getTime().toString());
                        calendar.set(11, 0);
                        calendar.set(12, 0);
                        calendar.set(13, 0);
                    }
                    StartTrainDateFragment.this.j = calendar.getTimeInMillis();
                    StartTrainDateFragment.this.i.setText(StartTrainDateFragment.this.c());
                    StartTrainDateFragment.this.f.backLock();
                    gdk.b(false, StartTrainDateFragment.this.b);
                    gdk.aLq_(StartTrainDateFragment.this.f3341a, StartTrainDateFragment.this.c, StartTrainDateFragment.this.f, true);
                    return;
                }
                LogUtil.h("Suggestion_StartTrainDateFragment", "calendarView onCalendarSelect activity is null or click too fast.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        Date date = new Date(this.j);
        String a2 = UnitUtil.a(date, 20);
        if (jec.ab(date)) {
            this.h.setText(this.e.getString(R.string._2130841407_res_0x7f020f3f));
            return ffy.d(this.e, R.string._2130848718_res_0x7f022bce, a2, this.h.getText());
        }
        if (jec.b(date, new Date(jec.e().getTime() + 86400000))) {
            this.h.setText(this.e.getString(R.string._2130848569_res_0x7f022b39));
            return ffy.d(this.e, R.string._2130848718_res_0x7f022bce, a2, this.h.getText());
        }
        this.h.setText(DateFormat.format("yyyy-MM-dd", date));
        return a2;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_StartTrainDateFragment", "isHidden fragment.");
        } else {
            this.f3341a.setVisibility(0);
            gdk.b(true, this.d);
        }
    }

    public long b() {
        return this.j;
    }

    public void c(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.f = onNextPageListener;
    }
}
