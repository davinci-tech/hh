package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.section.listener.OnCalendarSelectDataCallback;
import com.huawei.health.knit.section.view.SectionActiveRecordCalendar;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import defpackage.edr;
import defpackage.efa;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nkw;
import defpackage.nrh;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionActiveRecordCalendar extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthCalendarView f2662a;
    private HealthTextView b;
    private ImageView c;
    private Context d;
    private int e;
    private View i;
    private ImageView j;

    private int e(float f) {
        return (int) (f * 1000.0f);
    }

    public SectionActiveRecordCalendar(Context context) {
        super(context);
    }

    public SectionActiveRecordCalendar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionActiveRecordCalendar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        if (this.i == null) {
            this.d = context;
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_active_record_calendar, (ViewGroup) this, false);
            this.i = inflate;
            HealthCalendarView healthCalendarView = (HealthCalendarView) inflate.findViewById(R.id.hcv_active_record_calendar);
            this.f2662a = healthCalendarView;
            healthCalendarView.setModeOnlyWeekView();
            this.f2662a.b(true);
            this.f2662a.setIsSelectFutureDate(false);
            this.f2662a.setIsSetGrayFutureDate(true);
            this.f2662a.setSelectGrayDate(false);
            this.f2662a.setWeekStart(2);
            this.f2662a.setMarkerViewClickable(true);
            this.j = (ImageView) this.i.findViewById(R.id.active_record_arrow_left);
            this.b = (HealthTextView) this.i.findViewById(R.id.tv_active_record_date);
            if (Utils.o()) {
                this.b.setCompoundDrawables(null, null, null, null);
            }
            this.c = (ImageView) this.i.findViewById(R.id.active_record_arrow_right);
            c();
        }
        d();
        return this.i;
    }

    private void c() {
        HealthCalendarView healthCalendarView = this.f2662a;
        if (healthCalendarView != null) {
            b(healthCalendarView.getCurrentWeekCalendars());
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionActiveRecordCalendar", " enter bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionActiveRecordCalendar", "no need to bind");
            return;
        }
        setOnCalendarSelectDataCallback(hashMap);
        setDialogCalendarClickCallBack(hashMap);
        b(hashMap);
        c(hashMap);
        setCurWeekThreeCircle(hashMap);
        setWeekChangeCallback(hashMap);
        nsy.cMs_(this.b, nru.b(hashMap, "BAR_CHART_DATE_TEXT", ""), true);
        this.e = nru.d((Map) hashMap, "IS_DATA_TYPE_DAY", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStartCalendarString() {
        return UnitUtil.a(new Date(1530374400000L), 20);
    }

    private void c(HashMap<String, Object> hashMap) {
        Object obj = hashMap.get("CALENDAR_START_DAY");
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            LogUtil.a("SectionActiveRecordCalendar", "updateCalendarStartTime ", Long.valueOf(longValue));
            this.f2662a.setRangeGray(null, new HealthCalendar(longValue - 86400000));
        }
    }

    private void setCurWeekThreeCircle(HashMap<String, Object> hashMap) {
        long j;
        Object obj = hashMap.get("BAR_DATA_INFOS");
        Object obj2 = hashMap.get("BAR_CHART_CURRENT_DAY");
        List arrayList = new ArrayList(16);
        Object obj3 = hashMap.get("CALENDAR_START_DAY");
        if (koq.e(obj, edr.class)) {
            arrayList = (List) obj;
        }
        if (obj3 instanceof Long) {
            j = ((Long) obj3).longValue();
            LogUtil.a("SectionActiveRecordCalendar", "updateCalendarStartTime ", Long.valueOf(j));
        } else {
            j = 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (obj2 instanceof Long) {
            currentTimeMillis = ((Long) obj2).longValue();
        }
        long c = jdl.c(currentTimeMillis, 2, 0);
        Map<String, HealthCalendar> hashMap2 = new HashMap<>(16);
        for (int i = 0; i < arrayList.size(); i++) {
            ThreeCircleView threeCircleView = new ThreeCircleView(BaseApplication.e(), 1);
            ait_(threeCircleView);
            HealthMark healthMark = new HealthMark();
            healthMark.d(nsn.c(this.d, 2.0f));
            HealthCalendar healthCalendar = new HealthCalendar();
            healthCalendar.setTime(new Date(jdl.d(c, i)));
            edr edrVar = (edr) arrayList.get(i);
            if (b(healthCalendar) || d(j, healthCalendar)) {
                LogUtil.a("SectionActiveRecordCalendar", "update gray circle");
                setFutureCircleGray(threeCircleView);
                aiu_(hashMap2, healthCalendar, threeCircleView, healthMark);
            } else {
                a(threeCircleView, edrVar);
                aiu_(hashMap2, healthCalendar, threeCircleView, healthMark);
            }
        }
        this.f2662a.b(hashMap2);
    }

    private void ait_(View view) {
        int c = nsn.c(BaseApplication.e(), 30.0f);
        view.setLayoutParams(new FrameLayout.LayoutParams(c, c));
    }

    private void aiu_(Map<String, HealthCalendar> map, HealthCalendar healthCalendar, View view, HealthMark healthMark) {
        healthMark.cxA_(view);
        healthCalendar.addMark(healthMark);
        map.put(healthCalendar.toString(), healthCalendar);
    }

    private boolean b(HealthCalendar healthCalendar) {
        return HiDateUtil.t(System.currentTimeMillis()) < nkw.d(healthCalendar);
    }

    private boolean d(long j, HealthCalendar healthCalendar) {
        return nkw.d(healthCalendar) < nkw.d(new HealthCalendar(j));
    }

    private void setFutureCircleGray(ThreeCircleView threeCircleView) {
        efa.b(efa.f11993a, threeCircleView);
    }

    private void setDialogCalendarClickCallBack(HashMap<String, Object> hashMap) {
        if (hashMap.get("CLICK_EVENT_LISTENER") instanceof View.OnClickListener) {
            View.OnClickListener onClickListener = (View.OnClickListener) hashMap.get("CLICK_EVENT_LISTENER");
            HealthTextView healthTextView = this.b;
            if (healthTextView != null) {
                healthTextView.setOnClickListener(onClickListener);
            }
            c();
        }
    }

    private void a(ThreeCircleView threeCircleView, edr edrVar) {
        efa.b(efa.d(edrVar.af()), threeCircleView);
        if (edrVar.af()) {
            threeCircleView.c("firstCircle", e(edrVar.e()), e(c(edrVar.c(), edrVar.e())));
        } else {
            threeCircleView.c("firstCircle", edrVar.y(), c(edrVar.p(), edrVar.y()));
        }
        threeCircleView.c("secondCircle", edrVar.i(), c(edrVar.f(), edrVar.i()));
        threeCircleView.c("thirdCircle", edrVar.s(), c(edrVar.q(), edrVar.s()));
    }

    private int c(int i, float f) {
        if (Float.compare(f, 0.0f) == 0) {
            return 0;
        }
        return i;
    }

    private void setWeekChangeCallback(HashMap<String, Object> hashMap) {
        Object obj = hashMap.get("CALENDAR_START_DAY");
        if (obj instanceof Long) {
            final long longValue = ((Long) obj).longValue();
            final HealthCalendar healthCalendar = new HealthCalendar(longValue);
            if (hashMap.get("BAR_CHART_CALENDAR_CLICK_EVENT") instanceof OnCalendarSelectDataCallback) {
                final OnCalendarSelectDataCallback onCalendarSelectDataCallback = (OnCalendarSelectDataCallback) hashMap.get("BAR_CHART_CALENDAR_CLICK_EVENT");
                HealthCalendarView healthCalendarView = this.f2662a;
                if (healthCalendarView == null) {
                    return;
                }
                healthCalendarView.setOnWeekChangeListener(new HealthCalendarView.OnWeekChangeListener() { // from class: egd
                    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnWeekChangeListener
                    public final void onWeekChange(List list) {
                        SectionActiveRecordCalendar.this.c(longValue, onCalendarSelectDataCallback, healthCalendar, list);
                    }
                });
            }
        }
    }

    public /* synthetic */ void c(long j, OnCalendarSelectDataCallback onCalendarSelectDataCallback, HealthCalendar healthCalendar, List list) {
        Object[] objArr = new Object[2];
        objArr[0] = "onWeekChange enter ";
        objArr[1] = list == null ? null : Integer.valueOf(list.size());
        LogUtil.a("SectionActiveRecordCalendar", objArr);
        int i = this.e;
        int i2 = i != 0 ? i - 1 : 0;
        if (koq.d(list, i2)) {
            if (((HealthCalendar) list.get(i2)).transformCalendar().getTimeInMillis() < j) {
                onCalendarSelectDataCallback.onCalendarSelect(healthCalendar, true);
            } else {
                onCalendarSelectDataCallback.onCalendarSelect((HealthCalendar) list.get(i2), true);
            }
        }
        b((List<HealthCalendar>) list);
    }

    private void b(List<HealthCalendar> list) {
        if (koq.b(list)) {
            return;
        }
        HealthCalendar healthCalendar = list.get(0);
        HealthCalendar healthCalendar2 = list.get(list.size() - 1);
        long d = nkw.d(healthCalendar);
        long c = nkw.c(healthCalendar2);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= d && currentTimeMillis <= c) {
            this.c.setVisibility(4);
            this.f2662a.setForbiddenSwipeNextWeek(true);
        } else {
            this.c.setVisibility(0);
            this.f2662a.setForbiddenSwipeNextWeek(false);
        }
    }

    private void setOnCalendarSelectDataCallback(HashMap<String, Object> hashMap) {
        if (hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK") instanceof OnCalendarSelectDataCallback) {
            final OnCalendarSelectDataCallback onCalendarSelectDataCallback = (OnCalendarSelectDataCallback) hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK");
            HealthCalendarView healthCalendarView = this.f2662a;
            if (healthCalendarView != null) {
                healthCalendarView.setOnCalendarSelectListener(new HealthCalendarView.OnCalendarSelectListener() { // from class: com.huawei.health.knit.section.view.SectionActiveRecordCalendar.5
                    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
                    public void onCalendarOutOfRange(HealthCalendar healthCalendar) {
                        if (Utils.o()) {
                            return;
                        }
                        nrh.d(BaseApplication.e(), BaseApplication.e().getString(R$string.IDS_hw_health_active_record_out_of_bound_tips, SectionActiveRecordCalendar.this.getStartCalendarString()));
                    }

                    @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
                    public void onCalendarSelect(HealthCalendar healthCalendar, boolean z) {
                        if (SectionActiveRecordCalendar.this.getKnitFragment() == null) {
                            LogUtil.b("SectionActiveRecordCalendar", "KnitFragment is null in onCalendarSelect");
                            return;
                        }
                        FragmentActivity activity = SectionActiveRecordCalendar.this.getKnitFragment().getActivity();
                        if (activity == null) {
                            LogUtil.b("SectionActiveRecordCalendar", "activity is null in onCalendarSelect");
                            return;
                        }
                        onCalendarSelectDataCallback.onCalendarSelect(healthCalendar, z);
                        if (z) {
                            SectionActiveRecordCalendar.this.setBiCollect(2);
                        }
                        CustomTitleBar customTitleBar = (CustomTitleBar) activity.findViewById(R.id.knit_health_detail_title_bar);
                        if (customTitleBar == null) {
                            LogUtil.h("SectionActiveRecordCalendar", "CustomTitleBar is null in onCalendarSelect");
                        } else if (HiDateUtil.c(healthCalendar.transformCalendar().getTimeInMillis()) > HiDateUtil.c(System.currentTimeMillis())) {
                            customTitleBar.setRightSoftkeyVisibility(8);
                        } else {
                            customTitleBar.setRightSoftkeyVisibility(0);
                        }
                    }
                });
            }
        }
    }

    private void b(HashMap<String, Object> hashMap) {
        Object obj = hashMap.get("BAR_COMMON_CALENDER_EVENT");
        if (obj instanceof HealthCalendar) {
            this.f2662a.a((HealthCalendar) obj);
        }
        Object obj2 = hashMap.get("CALENDAR_START_DAY");
        long longValue = obj2 instanceof Long ? ((Long) obj2).longValue() : 1530374400000L;
        Object obj3 = hashMap.get("BAR_CHART_CURRENT_DAY");
        long currentTimeMillis = System.currentTimeMillis();
        if (obj3 instanceof Long) {
            currentTimeMillis = ((Long) obj3).longValue();
        }
        if (HiDateUtil.p(currentTimeMillis)[0] == HiDateUtil.p(longValue)[0]) {
            this.j.setVisibility(4);
            this.f2662a.setForbiddenSwipePreWeek(true);
        } else {
            this.j.setVisibility(0);
            this.f2662a.setForbiddenSwipePreWeek(false);
        }
    }

    private void d() {
        if (LanguageUtil.bc(this.d)) {
            this.j.setBackground(ContextCompat.getDrawable(this.d, R.drawable._2131427831_res_0x7f0b01f7));
            this.c.setBackground(ContextCompat.getDrawable(this.d, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.j.setBackground(ContextCompat.getDrawable(this.d, R.drawable._2131427825_res_0x7f0b01f1));
            this.c.setBackground(ContextCompat.getDrawable(this.d, R.drawable._2131427831_res_0x7f0b01f7));
        }
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionActiveRecordCalendar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SectionActiveRecordCalendar.this.f2662a.e(true);
                SectionActiveRecordCalendar.this.setBiCollect(1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionActiveRecordCalendar.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SectionActiveRecordCalendar.this.f2662a.c(true);
                SectionActiveRecordCalendar.this.setBiCollect(1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBiCollect(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("switchType", Integer.valueOf(i));
        ixx.d().d(this.d, AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040107.value(), hashMap, 0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionActiveRecordCalendar";
    }
}
