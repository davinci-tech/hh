package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.OnCalendarSelectDataCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.threecircle.ThreeCircleConfigUtil;
import com.huawei.threecircle.api.ThreeCircleApi;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordCalendarProvider;
import com.huawei.ui.main.stories.fitness.activity.active.writehelper.ThreeCircleDataManager;
import defpackage.dpg;
import defpackage.edr;
import defpackage.ixx;
import defpackage.jct;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nhj;
import defpackage.nip;
import defpackage.nsn;
import defpackage.pit;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ActiveRecordCalendarProvider extends MajorProvider<edr> {

    /* renamed from: a, reason: collision with root package name */
    private c f9727a;
    private edr b;
    private int c;
    private String d;
    private SectionBean f;
    private long[] g;
    private boolean h;
    private edr k;
    private BroadcastReceiver n;
    private BroadcastReceiver o;
    private HealthCalendar e = new HealthCalendar(System.currentTimeMillis());
    private long j = System.currentTimeMillis();
    private List<edr> m = new CopyOnWriteArrayList();
    private List<edr> l = new CopyOnWriteArrayList();
    private List<edr> i = new CopyOnWriteArrayList();

    private float a(float f) {
        return f / 1000.0f;
    }

    private int a(int i, int i2) {
        return i2 > 0 ? i + 1 : i;
    }

    private float e(float f) {
        return f / 10.0f;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.c("SCUI_ActiveRecordCalendarProvider", "enter loadData");
        this.c = dpg.a(this.j);
        this.f = sectionBean;
        this.b = new edr();
        long[] e = jdl.e(this.j, TimeZone.getDefault(), 2, 0);
        this.g = e;
        this.b.e(e);
        this.f.e(this.b);
        long[] jArr = this.g;
        b(jArr[0], jArr[1]);
    }

    private void c(final CountDownLatch countDownLatch) {
        pit.a().c(new IBaseResponseCallback() { // from class: pic
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ActiveRecordCalendarProvider.this.d(countDownLatch, i, obj);
            }
        });
    }

    public /* synthetic */ void d(CountDownLatch countDownLatch, int i, Object obj) {
        if (!(obj instanceof HashMap)) {
            LogUtil.a("SCUI_ActiveRecordCalendarProvider", "queryActiveGoal objData is not instanceof HashMap");
            countDownLatch.countDown();
            return;
        }
        HashMap hashMap = (HashMap) obj;
        int e = nip.e(hashMap, "900200008", 25);
        int e2 = nip.e(hashMap, "900200009", 12);
        int e3 = nip.e(hashMap, "900200007", 270000);
        LogUtil.c("SCUI_ActiveRecordCalendarProvider", "loadData intensityGoalValue ", Integer.valueOf(e), " standGoalValue ", Integer.valueOf(e2), " calorieGoalValue ", Integer.valueOf(e3));
        this.k.c(e);
        this.k.g(e2);
        this.k.e(c(e3));
        countDownLatch.countDown();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.f9727a = new c(this);
        Bundle extra = getExtra();
        this.b = new edr();
        edr edrVar = new edr();
        this.k = edrVar;
        edrVar.a(HiDateUtil.t(System.currentTimeMillis()));
        this.k.n(extra.getInt("current_total_step"));
        this.k.b(extra.getInt("current_total_intensity"));
        this.k.m(Math.min(extra.getInt("current_total_active"), 24));
        this.k.e(a(extra.getInt("current_total_distance")));
        this.k.d(extra.getInt("current_total_calorie") / 1000.0f);
        this.k.a(e(extra.getInt("current_total_floor")));
        this.k.l(extra.getInt("cur_step_goal"));
        this.k.e(c(extra.getInt("cur_calorie_goal")));
        this.k.c(extra.getInt("cur_intensity_goal"));
        this.k.g(extra.getInt("cur_active_goal"));
        this.k.b(jct.b());
        this.h = extra.getBoolean("is_open_calendar");
        j();
        c(nhj.c());
        g();
        ThreadPoolManager.d().execute(new Runnable() { // from class: phz
            @Override // java.lang.Runnable
            public final void run() {
                ActiveRecordCalendarProvider.b();
            }
        });
        nhj.l();
    }

    public static /* synthetic */ void b() {
        ThreeCircleDataManager.a().d();
        ((ThreeCircleApi) Services.c("DailyActivity", ThreeCircleApi.class)).checkUpdateForThreeCircle();
    }

    private void g() {
        if (this.o == null) {
            this.o = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordCalendarProvider.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent == null) {
                        ReleaseLogUtil.a("SCUI_ActiveRecordCalendarProvider", "registerThreeGoalBroadcastReceiver onReceive intent is null");
                        return;
                    }
                    String action = intent.getAction();
                    ReleaseLogUtil.b("SCUI_ActiveRecordCalendarProvider", "registerThreeGoalBroadcastReceiver action", action);
                    if ("UPDATE_THREE_GOAL".equals(action) && ActiveRecordCalendarProvider.this.f != null && ThreeCircleConfigUtil.d()) {
                        ReleaseLogUtil.b("SCUI_ActiveRecordCalendarProvider", "loadData by save guide goal");
                        ActiveRecordCalendarProvider.this.e(BaseApplication.getContext(), ActiveRecordCalendarProvider.this.f);
                    }
                }
            };
            BroadcastManagerUtil.bFE_(BaseApplication.getContext(), this.o, new IntentFilter("UPDATE_THREE_GOAL"));
        }
    }

    private void c(final List<Integer> list) {
        if (this.n == null) {
            this.n = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordCalendarProvider.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent == null) {
                        ReleaseLogUtil.a("SCUI_ActiveRecordCalendarProvider", "registerSyncStatusBroadcastReceiver onReceive intent is null");
                        return;
                    }
                    String action = intent.getAction();
                    if (!"com.huawei.hihealth.action_sync_data_result".equals(action)) {
                        ReleaseLogUtil.a("SCUI_ActiveRecordCalendarProvider", "registerSyncStatusBroadcastReceiver onReceive action ", action);
                        return;
                    }
                    boolean booleanExtra = intent.getBooleanExtra("sync_data_result_success", true);
                    String stringExtra = intent.getStringExtra("sync_data_result_type");
                    boolean e = nhj.e((List<Integer>) list, stringExtra);
                    ReleaseLogUtil.b("SCUI_ActiveRecordCalendarProvider", "registerSyncStatusBroadcastReceiver onReceive isSyncSleepSummaryType ", Boolean.valueOf(e), " syncDataResultType ", stringExtra, " syncDataTypeList ", list, " isSyncSuccess ", Boolean.valueOf(booleanExtra));
                    if (e) {
                        ObserverManagerUtil.c("ACTIVE_RECORD_SUMMARY_SYNC_STATUS", Boolean.valueOf(booleanExtra));
                    }
                }
            };
            BroadcastManagerUtil.bFE_(BaseApplication.getContext(), this.n, new IntentFilter("com.huawei.hihealth.action_sync_data_result"));
        }
    }

    private int c(float f) {
        return Math.round(f / 1000.0f);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, edr edrVar) {
        LogUtil.c("SCUI_ActiveRecordCalendarProvider", "parseParams enter ");
        a(hashMap);
        e(hashMap);
        b(hashMap);
        String str = this.d;
        if (str == null) {
            str = b(System.currentTimeMillis());
        }
        hashMap.put("BAR_CHART_DATE_TEXT", str);
        hashMap.put("BAR_COMMON_CALENDER_EVENT", this.e);
        hashMap.put("BAR_DATA_INFOS", this.l);
        hashMap.put("BAR_CHART_CURRENT_DAY", Long.valueOf(this.j));
        hashMap.put("CALENDAR_START_DAY", Long.valueOf(Utils.o() ? ThreeCircleDataManager.a().b() : 1530374400000L));
        int i = this.c;
        hashMap.put("IS_DATA_TYPE_DAY", Integer.valueOf(i == 1 ? 7 : i - 1));
        if (this.h) {
            this.h = false;
            a();
        }
    }

    private void j() {
        ObserverManagerUtil.d(new Observer() { // from class: pib
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str, Object[] objArr) {
                ActiveRecordCalendarProvider.this.d(str, objArr);
            }
        }, "observer_refresh_active_record_provider");
    }

    public /* synthetic */ void d(String str, Object[] objArr) {
        if (this.f != null) {
            e(BaseApplication.getContext(), this.f);
        }
    }

    private void a(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_MARK_CHANGE_CALL_BACK", new OnCalendarSelectDataCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordCalendarProvider.3
            @Override // com.huawei.health.knit.section.listener.OnCalendarSelectDataCallback
            public void onCalendarSelect(HealthCalendar healthCalendar, boolean z) {
                ActiveRecordCalendarProvider.this.e = healthCalendar;
                ActiveRecordCalendarProvider activeRecordCalendarProvider = ActiveRecordCalendarProvider.this;
                activeRecordCalendarProvider.c = activeRecordCalendarProvider.e.getWeek();
                if (z) {
                    ActiveRecordCalendarProvider.this.l();
                }
            }
        });
    }

    private void b(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_CHART_CALENDAR_CLICK_EVENT", new OnCalendarSelectDataCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordCalendarProvider.1
            @Override // com.huawei.health.knit.section.listener.OnCalendarSelectDataCallback
            public void onCalendarSelect(HealthCalendar healthCalendar, boolean z) {
                ActiveRecordCalendarProvider.this.e = healthCalendar;
                ActiveRecordCalendarProvider activeRecordCalendarProvider = ActiveRecordCalendarProvider.this;
                activeRecordCalendarProvider.c = activeRecordCalendarProvider.e.getWeek();
                if (z) {
                    ActiveRecordCalendarProvider.this.l();
                }
            }
        });
    }

    private void e(HashMap<String, Object> hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new View.OnClickListener() { // from class: phx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRecordCalendarProvider.this.dpN_(view);
            }
        });
    }

    public /* synthetic */ void dpN_(View view) {
        if (Utils.o()) {
            LogUtil.c("SCUI_ActiveRecordCalendarProvider", "oversea not show");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.c("SCUI_ActiveRecordCalendarProvider", "setDialogCalendarClickListener up");
        if (nsn.cLk_(view)) {
            LogUtil.c("SCUI_ActiveRecordCalendarProvider", "isFastClick not show");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("calendar", this.e);
        bundle.putParcelable("markDateTrigger", new ActiveDataMarkerTrigger());
        bundle.putInt("lastExtraSpace", BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
        bundle.putInt("extraSpace", 50);
        bundle.putInt("selectedMarkerSize", BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d));
        bundle.putInt("itemVerticalSpace", BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516));
        bundle.putBoolean("isMarkerClickable", true);
        HealthCalendarActivity.cxj_(BaseApplication.getActivity(), bundle, this.f9727a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        long timeInMillis = this.e.transformCalendar().getTimeInMillis();
        this.d = b(timeInMillis);
        SectionBean sectionBean = this.f;
        sectionBean.e(sectionBean.e());
        a(timeInMillis);
    }

    private String b(long j) {
        return UnitUtil.a(new Date(j), 22);
    }

    private void a(long j) {
        this.j = j;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        if (jdl.ac(this.j)) {
            c(countDownLatch);
            try {
                countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
                m();
            } catch (InterruptedException unused) {
                LogUtil.e("SCUI_ActiveRecordCalendarProvider", "interrupted while waiting for queryActiveGoal");
            }
        }
        long[] jArr = this.g;
        if (j >= jArr[0] && j <= jArr[1]) {
            LogUtil.a("SCUI_ActiveRecordCalendarProvider", "queryData the week data is already response");
            e();
        } else {
            long[] e = jdl.e(j, TimeZone.getDefault(), 2, 0);
            this.g = e;
            b(e[0], e[1]);
        }
    }

    private void b(final long j, long j2) {
        d();
        long c2 = jdl.c(j, 2, -1);
        this.l.addAll(e(j));
        this.i.addAll(e(c2));
        ThreeCircleDataManager.a().a(jdl.t(c2), jdl.e(j2), new ResponseCallback() { // from class: pia
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ActiveRecordCalendarProvider.this.a(j, i, (List) obj);
            }
        });
        a(j, (List<HiHealthData>) null);
    }

    public /* synthetic */ void a(long j, int i, List list) {
        a(j, (List<HiHealthData>) list);
    }

    private void e(long j, List<HiHealthData> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("SCUI_ActiveRecordCalendarProvider", "queryWeekData objData is empty");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                ReleaseLogUtil.b("SCUI_ActiveRecordCalendarProvider", "hiHealthData is null");
            } else {
                ReleaseLogUtil.b("SCUI_ActiveRecordCalendarProvider", "hiHealthData to String", hiHealthData.toString());
                if (jdl.f(j, hiHealthData.getStartTime())) {
                    d(hiHealthData, this.l);
                } else {
                    d(hiHealthData, this.i);
                }
            }
        }
    }

    private void d(HiHealthData hiHealthData, List<edr> list) {
        int i = hiHealthData.getInt("durationGoalValue");
        int i2 = hiHealthData.getInt("durationUserValue");
        int i3 = hiHealthData.getInt("activeGoalValue");
        int i4 = hiHealthData.getInt("activeUserValue");
        int i5 = hiHealthData.getInt("stepGoalValue");
        int i6 = hiHealthData.getInt("stepUserValue");
        int c2 = c(hiHealthData.getInt("calorieGoalValue"));
        boolean z = hiHealthData.getInt("calorieIsRingNew") == 1;
        float a2 = a(hiHealthData.getFloat("sport_distance_sum"));
        float f = hiHealthData.getFloat("sport_calorie_sum") / 1000.0f;
        float e = e(hiHealthData.getFloat("sport_altitude_offset_sum"));
        for (edr edrVar : list) {
            if (HiDateUtil.t(hiHealthData.getStartTime()) == HiDateUtil.t(edrVar.t())) {
                edrVar.c(i);
                edrVar.b(i2);
                edrVar.g(i3);
                edrVar.m(Math.min(i4, 24));
                edrVar.l(i5);
                edrVar.e(c2);
                edrVar.n(i6);
                edrVar.b(jct.b());
                edrVar.e(a2);
                edrVar.d(f);
                edrVar.a(e);
                edrVar.d(z);
                return;
            }
        }
    }

    private List<edr> e(long j) {
        ArrayList arrayList = new ArrayList(7);
        for (int i = 0; i < 7; i++) {
            edr edrVar = new edr();
            edrVar.a(jdl.d(j, i));
            edrVar.c(jdl.c(j, i));
            edrVar.d(true);
            arrayList.add(edrVar);
        }
        return arrayList;
    }

    private void d() {
        if (koq.c(this.m)) {
            this.m.clear();
        }
        if (koq.c(this.l)) {
            this.l.clear();
        }
        if (koq.c(this.i)) {
            this.i.clear();
        }
    }

    private void f() {
        for (int i = 0; i < this.l.size(); i++) {
            e(this.l.get(i));
        }
    }

    private void e(edr edrVar) {
        if (edrVar.p() == 0) {
            edrVar.l(this.k.p());
        }
        if (edrVar.f() == 0) {
            edrVar.c(this.k.f());
        }
        if (edrVar.q() == 0) {
            edrVar.g(this.k.q());
        }
        if (edrVar.c() == 0) {
            edrVar.e(this.k.c());
        }
    }

    private void i() {
        for (edr edrVar : this.l) {
            edrVar.p(b(this.l, 0));
            edrVar.j(b(this.i, 0));
            edrVar.s(b(this.l, 1));
            edrVar.h(b(this.i, 1));
            edrVar.r(b(this.l, 3));
            edrVar.i(b(this.i, 3));
            edrVar.q(Math.min(a(this.m, edrVar), 24));
            edrVar.f(Math.min(b(this.m, edrVar), 24));
            edrVar.d(b(this.i, 2));
            edrVar.t(this.k.y());
            edrVar.o(this.k.i());
            edrVar.k(this.k.e());
            edrVar.b(this.k.h());
            edrVar.c(this.k.g());
            edrVar.c(d(this.l, 3));
            edrVar.a(d(this.l, 2));
            edrVar.d(d(this.l, 1));
            edrVar.a(b(this.l));
        }
    }

    private int b(List<edr> list) {
        int i = 0;
        for (edr edrVar : list) {
            if (edrVar.s() >= edrVar.q()) {
                i++;
            }
        }
        return i;
    }

    private void e() {
        LogUtil.c("SCUI_ActiveRecordCalendarProvider", "enter notifyMinorProvidersDataChange");
        for (edr edrVar : this.l) {
            if (jdl.d(this.j, edrVar.t())) {
                LogUtil.c("SCUI_ActiveRecordCalendarProvider", "notifyMinorProviders data mDayTimeStamp is ", Long.valueOf(this.j));
                edrVar.d(this.j);
                notifyMinorProviders(edrVar);
                SectionBean sectionBean = this.f;
                if (sectionBean != null) {
                    sectionBean.e(edrVar);
                    return;
                }
                return;
            }
        }
    }

    private int b(List<edr> list, int i) {
        int y;
        float f;
        if (koq.b(list)) {
            LogUtil.a("SCUI_ActiveRecordCalendarProvider", "getTrendAverageData list is empty");
            return 0;
        }
        float f2 = 0.0f;
        int i2 = 0;
        for (edr edrVar : list) {
            if (edrVar != null) {
                if (i == 0) {
                    i2 = a(i2, edrVar.y());
                    y = edrVar.y();
                } else if (i == 1) {
                    i2 = a(i2, edrVar.i());
                    y = edrVar.i();
                } else if (i == 3) {
                    i2 = a(i2, edrVar.e());
                    f = edrVar.d();
                    f2 += f;
                } else if (i == 2) {
                    i2 = a(i2, edrVar.s());
                    y = edrVar.s();
                }
                f = y;
                f2 += f;
            }
        }
        return (int) UnitUtil.a(i2 == 0 ? 0.0d : f2 / i2, 0);
    }

    private Map<Long, Integer> d(List<edr> list, int i) {
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < list.size(); i2++) {
            edr edrVar = list.get(i2);
            if (edrVar != null) {
                if (i == 3) {
                    hashMap.put(Long.valueOf(edrVar.t()), Integer.valueOf(edrVar.e()));
                } else if (i == 1) {
                    hashMap.put(Long.valueOf(edrVar.t()), Integer.valueOf(edrVar.i()));
                } else if (i == 2) {
                    hashMap.put(Long.valueOf(edrVar.t()), Integer.valueOf(edrVar.s()));
                }
            }
        }
        return hashMap;
    }

    private int b(List<edr> list, edr edrVar) {
        long t = jdl.t(edrVar.t());
        for (edr edrVar2 : list) {
            if (edrVar2 != null && jdl.e(t, jdl.t(edrVar2.t())) == 2) {
                return edrVar2.s();
            }
        }
        return 0;
    }

    private int a(List<edr> list, edr edrVar) {
        long t = jdl.t(edrVar.t());
        for (edr edrVar2 : list) {
            if (edrVar2 != null && t == jdl.t(edrVar2.t())) {
                return edrVar2.s();
            }
        }
        return 0;
    }

    private void c() {
        long[] jArr = this.g;
        if (jArr != null && jArr.length == 2 && jArr[0] == jdl.t(System.currentTimeMillis())) {
            for (edr edrVar : this.l) {
                edrVar.p(b(this.l, 0));
                edrVar.s(b(this.l, 1));
                edrVar.r(b(this.l, 3));
            }
        }
    }

    private void m() {
        for (edr edrVar : this.l) {
            if (jdl.ac(edrVar.t())) {
                edrVar.b(c(edrVar.i(), this.k.i()));
                edrVar.m(Math.min(c(edrVar.s(), this.k.s()), 24));
                edrVar.n(c(edrVar.y(), this.k.y()));
                edrVar.b(edrVar.m() || this.k.m());
                edrVar.e(e(edrVar.h(), this.k.h()));
                edrVar.a(e(edrVar.g(), this.k.g()));
                edrVar.d(e(edrVar.d(), this.k.d()));
                edrVar.d(true);
                return;
            }
        }
    }

    private float e(float f, float f2) {
        return Math.max(0.0f, Math.max(f, f2));
    }

    private int c(int i, int i2) {
        return Math.max(0, Math.max(i, i2));
    }

    private void a(final long j, final List<HiHealthData> list) {
        HandlerExecutor.e(new Runnable() { // from class: phy
            @Override // java.lang.Runnable
            public final void run() {
                ActiveRecordCalendarProvider.this.d(j, list);
            }
        });
    }

    public /* synthetic */ void d(long j, List list) {
        e(j, (List<HiHealthData>) list);
        this.m.addAll(this.l);
        this.m.addAll(this.i);
        f();
        m();
        i();
        c();
        e();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        if (this.n != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.getContext(), this.n);
            this.n = null;
        }
        if (this.o != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.getContext(), this.o);
            this.o = null;
        }
        ObserverManagerUtil.e("observer_refresh_active_record_provider");
        ThreeCircleDataManager.a().clearData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("switchType", 3);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040107.value(), hashMap, 0);
    }

    static class c implements CommonUiBaseResponse {
        private WeakReference<ActiveRecordCalendarProvider> b;

        c(ActiveRecordCalendarProvider activeRecordCalendarProvider) {
            this.b = new WeakReference<>(activeRecordCalendarProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            ActiveRecordCalendarProvider activeRecordCalendarProvider = this.b.get();
            if (activeRecordCalendarProvider == null) {
                LogUtil.a("SCUI_ActiveRecordCalendarProvider", "onResponse: provider is null");
            } else if (i == 1 && (obj instanceof HealthCalendar)) {
                activeRecordCalendarProvider.e = (HealthCalendar) obj;
                activeRecordCalendarProvider.l();
                activeRecordCalendarProvider.h();
            }
        }
    }
}
