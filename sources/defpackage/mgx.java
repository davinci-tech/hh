package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.TrackData;
import com.huawei.pluginachievement.manager.service.OnceMovementReceiver;
import com.huawei.pluginachievement.report.bean.AnnualReportStepResult;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import defpackage.mgx;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mgx {
    private static volatile mgx b;
    private static final Object d = new Object();
    private static final Integer[] e = {257, 258, 264, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE), 262, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM), 259};

    /* renamed from: a, reason: collision with root package name */
    private Context f14988a;
    private long g = 0;
    private long c = 0;

    private boolean a(int i, int i2, int i3) {
        return (i == 0 && i2 == 0 && i3 != 2) ? false : true;
    }

    public static int e(long j, long j2) {
        return (int) ((j2 - j) / 8.64E7d);
    }

    private mgx(Context context) {
        this.f14988a = context.getApplicationContext();
    }

    public static mgx e(Context context) {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new mgx(context);
                }
            }
        }
        return b;
    }

    public void c(Context context) {
        mlk.b(context);
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (mds.a()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mgy
                @Override // java.lang.Runnable
                public final void run() {
                    mgx.this.a();
                }
            });
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("timeZone", HiDateUtil.d((String) null));
        meh.c(BaseApplication.e()).a(24, hashMap);
    }

    public void a(Context context) {
        if (c(context, "generateAchieveMedalTime", 43200000L)) {
            return;
        }
        k(context);
        i(context);
    }

    private void i(final Context context) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: mgx.5
            @Override // java.lang.Runnable
            public void run() {
                mgx.e(context).b(context);
            }
        }, 3000L);
    }

    public void b(Context context) {
        h(context);
        f(context);
        e();
    }

    /* renamed from: mgx$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long t = HiDateUtil.t(mgx.this.d());
            long f = HiDateUtil.f(System.currentTimeMillis());
            LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "dealHistoryThreeCircleData startTime:", Long.valueOf(t), " endTime:", Long.valueOf(f));
            nix.c().c(t, f, new ResponseCallback() { // from class: mgz
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    mgx.AnonymousClass3.e(i, (List) obj);
                }
            });
        }

        static /* synthetic */ void e(int i, List list) {
            if (koq.b(list)) {
                LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "dealHistoryThreeCircleData dataList isEmpty!");
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthData hiHealthData = (HiHealthData) it.next();
                if (hiHealthData != null) {
                    mgx.d(hiHealthData);
                }
            }
        }
    }

    public void e() {
        ThreadPoolManager.d().execute(new AnonymousClass3());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(HiHealthData hiHealthData) {
        long startTime = hiHealthData.getStartTime();
        int i = hiHealthData.getInt("calorieGoalValue");
        int i2 = hiHealthData.getInt("calorieUserValue");
        int i3 = hiHealthData.getInt("durationGoalValue");
        int i4 = hiHealthData.getInt("durationUserValue");
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "putThreeCircleData ", Long.valueOf(hiHealthData.getDay()), " cd:", Integer.valueOf(i2), " cg:", Integer.valueOf(i), " wd:", Integer.valueOf(i4), " wg:", Integer.valueOf(i3));
        if (i > 0 && i2 >= i) {
            OnceMovementReceiver.cgr_(cgR_("D1", i2 / i, startTime, String.valueOf(hiHealthData.getDay())));
        }
        if (i3 <= 0 || i4 < i3) {
            return;
        }
        OnceMovementReceiver.cgr_(cgR_("D2", i4 / i3, startTime, String.valueOf(hiHealthData.getDay())));
    }

    private static Bundle cgR_(String str, int i, long j, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("dataType", str);
        bundle.putInt("dataLevel", i);
        bundle.putLong("timestamp", j);
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "getThreeCircleData day:", str2, " type:", str, " ratio:", Integer.valueOf(i), " startTime:", Long.valueOf(j));
        return bundle;
    }

    public void h(final Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mgx.4
            @Override // java.lang.Runnable
            public void run() {
                if (mgx.this.g == 0) {
                    mgx.this.g(context);
                }
                PluginAchieveAdapter adapter = mcv.d(context).getAdapter();
                if (adapter != null) {
                    LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "TOTAL_SWIM_TYPE takeEffectTime ", Long.valueOf(mgx.this.g));
                    long d2 = mle.d(false, System.currentTimeMillis());
                    if (mgx.this.g != 0) {
                        mgx mgxVar = mgx.this;
                        mgxVar.a(context, adapter, mgxVar.g, d2);
                        return;
                    }
                    return;
                }
                LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "dealHealthMedalDataGenerate achieveAdapter == null.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(Context context) {
        List<mcz> b2 = meh.c(context).b(9, new HashMap(2));
        if (koq.b(b2)) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "medalConfigInfos isEmpty!");
            return;
        }
        long j = 0;
        long j2 = 0;
        for (mcz mczVar : b2) {
            if (mczVar instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                String acquireMedalType = medalConfigInfo.acquireMedalType();
                String acquireTakeEffectTime = medalConfigInfo.acquireTakeEffectTime();
                if (!TextUtils.isEmpty(acquireTakeEffectTime)) {
                    if ("C4".equals(acquireMedalType)) {
                        j = mht.a(acquireTakeEffectTime);
                    }
                    if ("C5".equals(acquireMedalType)) {
                        j2 = mht.a(acquireTakeEffectTime);
                    }
                }
            }
        }
        if (j != 0) {
            this.g = j;
        }
        if (j2 != 0) {
            this.c = j2;
        }
    }

    public void f(final Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mgx.2
            @Override // java.lang.Runnable
            public void run() {
                if (mgx.this.c == 0) {
                    mgx.this.g(context);
                }
                PluginAchieveAdapter adapter = mcv.d(context).getAdapter();
                if (adapter != null) {
                    LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "TOTAL_SKIP_TYPE takeEffectTime ", Long.valueOf(mgx.this.c));
                    long d2 = mle.d(false, System.currentTimeMillis());
                    if (mgx.this.c != 0) {
                        mgx mgxVar = mgx.this;
                        mgxVar.d(context, adapter, mgxVar.c, d2);
                        return;
                    }
                    return;
                }
                LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "dealHealthMedalDataGenerate achieveAdapter == null.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final Context context, PluginAchieveAdapter pluginAchieveAdapter, long j, long j2) {
        pluginAchieveAdapter.fetchSequenceDataBySportType(context, j, j2, 283, new AchieveCallback() { // from class: mgx.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i, Object obj) {
                mgx.this.c(context, (Context) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void c(Context context, T t) {
        if (koq.e(t, HiHealthData.class)) {
            e(context, (List<HiHealthData>) t);
        }
    }

    private void e(Context context, List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "SkipRecords records from HiHealth is 0! ");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "SkipRecords size is ", Integer.valueOf(list.size()));
        Iterator<HiHealthData> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(it.next().getMetaData(), HiTrackMetaData.class);
                int abnormalTrack = hiTrackMetaData.getAbnormalTrack();
                int duplicated = hiTrackMetaData.getDuplicated();
                if (abnormalTrack == 0 && duplicated == 0) {
                    int sportDataSource = hiTrackMetaData.getSportDataSource();
                    if (sportDataSource == 2) {
                        LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "sportDataSource=", Integer.valueOf(sportDataSource));
                    } else {
                        i += e(hiTrackMetaData);
                    }
                }
            } catch (JsonSyntaxException unused) {
                LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "trackMetaData is error!");
            }
        }
        if (i > 0) {
            meh c = meh.c(context);
            LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "computeSkipTrackInfo skip sum ", Integer.valueOf(i));
            mek.d(context, i, c, "C5");
        }
    }

    private int e(HiTrackMetaData hiTrackMetaData) {
        String str = hiTrackMetaData.getExtendTrackMap().get("skipNum");
        if (str != null) {
            return CommonUtil.h(str);
        }
        return 0;
    }

    private void k(final Context context) {
        if (!Utils.o()) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "dealLocalMedalDataGenerate is !isOversea().");
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: mgx.9
                @Override // java.lang.Runnable
                public void run() {
                    mgx.e(context).o(context);
                }
            }, 3000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(Context context) {
        PluginAchieveAdapter adapter = mcv.d(context).getAdapter();
        if (adapter == null) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "dealLocalMedalDataGenerate achieveAdapter == null.");
            return;
        }
        long d2 = mle.d(false, System.currentTimeMillis());
        LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "dealLocalMedalDataGenerate startTime == ", 0L);
        e(context, adapter, 0L, d2);
    }

    public void d(Context context) {
        if (c(context, "generateAchieveTime", 43200000L)) {
            return;
        }
        mda.a(context);
    }

    private void e(Context context, PluginAchieveAdapter pluginAchieveAdapter, long j, long j2) {
        pluginAchieveAdapter.readBestStepDayOfYear(context, j, j2, new AchieveCallback() { // from class: mgx.7
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i, final Object obj) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: mgx.7.5
                    @Override // java.lang.Runnable
                    public void run() {
                        mgx.this.b((mgx) obj);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, PluginAchieveAdapter pluginAchieveAdapter, long j, long j2) {
        pluginAchieveAdapter.fetchSequenceDataBySportType(context, j, j2, 262, new AchieveCallback() { // from class: mgx.8
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i, Object obj) {
                mgx.this.a((mgx) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void a(T t) {
        if (t instanceof List) {
            try {
                d((List<HiHealthData>) t);
                return;
            } catch (ClassCastException unused) {
                LogUtil.b("PLGACHIEVE_AchieveLocalDataManager", "getTrackListBySwimType data ClassCastException");
                return;
            }
        }
        LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "getTrackListBySwimType data is not list");
    }

    private void d(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "computeSwimTrackInfo() classificationList is null");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "enter computeSwimTrackInfo");
        HiTrackMetaData hiTrackMetaData = null;
        int i = 0;
        for (HiHealthData hiHealthData : list) {
            try {
                hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.b("PLGACHIEVE_AchieveLocalDataManager", "JsonSyntaxException.");
            }
            if (hiTrackMetaData != null) {
                if (mhu.c(hiTrackMetaData)) {
                    LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "computeSwimTrackInfo trackDataIsInvalid");
                } else {
                    int totalDistance = hiTrackMetaData.getTotalDistance();
                    LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "computeSwimTrackInfo totalTrackDis ", Integer.valueOf(totalDistance), " timestamp ", Long.valueOf(hiHealthData.getStartTime()));
                    if (!mhu.b(hiTrackMetaData)) {
                        i += totalDistance;
                    }
                }
            }
        }
        if (i >= 1000) {
            meh c = meh.c(this.f14988a);
            double d2 = mlg.d(i);
            LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "computeSwimTrackInfo sumSwimDis(KM) ", Double.valueOf(d2));
            mek.d(this.f14988a, d2, c, "C4");
        }
    }

    public HiTrackMetaData b(String str) {
        try {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(str, HiTrackMetaData.class);
            if (!a(hiTrackMetaData.getAbnormalTrack(), hiTrackMetaData.getDuplicated(), hiTrackMetaData.getSportDataSource())) {
                return hiTrackMetaData;
            }
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "getHiTrackMetaData trackDataIsInvalid!");
            return null;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("PLGACHIEVE_AchieveLocalDataManager", "trackMetaData is error");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> void b(T t) {
        if (!(t instanceof AnnualReportStepResult)) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "dealMedalData Class NotFound!");
            return;
        }
        AnnualReportStepResult annualReportStepResult = (AnnualReportStepResult) t;
        meh c = meh.c(this.f14988a);
        int c2 = c(annualReportStepResult.getReachTimeList());
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "dealMedalData reachDays == ", Integer.valueOf(c2));
        if (c2 >= 7) {
            mek.d(this.f14988a, c2, c, "B");
        }
        int totalSteps = annualReportStepResult.getTotalSteps();
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "dealMedalData value == ", Integer.valueOf(totalSteps));
        if (totalSteps >= 10000) {
            mek.d(this.f14988a, totalSteps, c, TypeParams.SEARCH_CODE);
        }
    }

    public static int c(List<Long> list) {
        Collections.sort(list);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (i3 == 0) {
                i = 1;
                i2 = 1;
            } else {
                int i4 = i3 - 1;
                if (e(b(list.get(i4).longValue()), b(list.get(i3).longValue())) == 1) {
                    arrayList.add(list.get(i4));
                    i++;
                } else {
                    if (i > i2) {
                        arrayList2.clear();
                        arrayList.add(list.get(i4));
                        arrayList2.addAll(arrayList);
                    } else {
                        i = i2;
                    }
                    arrayList.clear();
                    i2 = i;
                    i = 1;
                }
            }
        }
        if (i <= i2) {
            i = i2;
        }
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "timeReachList == ", arrayList2.toString());
        return i;
    }

    private static long b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private boolean c(Context context, String str, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String b2 = mct.b(context, str);
        if (TextUtils.isEmpty(b2)) {
            mct.b(context, str, String.valueOf(elapsedRealtime));
            return false;
        }
        try {
            long parseLong = Long.parseLong(b2);
            if (Math.abs(elapsedRealtime - parseLong) <= j) {
                LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "isInterval ", str, " lastTime = ", Long.valueOf(parseLong), " currentTime = ", Long.valueOf(elapsedRealtime), " intervalTime < queryTime, return!");
                return true;
            }
            mct.b(context, str, String.valueOf(elapsedRealtime));
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveLocalDataManager", "isInterval parse exception ");
            return false;
        }
    }

    public void j(Context context) {
        PluginAchieveAdapter adapter = mcv.d(context).getAdapter();
        if (adapter == null) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "toReadSingleTrackData achieveAdapter == null.");
            return;
        }
        long b2 = b();
        LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "toReadSingleTrackData startTime ==  ", Long.valueOf(b2));
        adapter.readSingleTrackData(context, b2, System.currentTimeMillis(), new AnonymousClass10(new WeakReference(context)));
    }

    /* renamed from: mgx$10, reason: invalid class name */
    class AnonymousClass10 implements IBaseResponseCallback {
        final /* synthetic */ WeakReference e;

        AnonymousClass10(WeakReference weakReference) {
            this.e = weakReference;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, final Object obj) {
            ThreadPoolManager d = ThreadPoolManager.d();
            final WeakReference weakReference = this.e;
            d.execute(new Runnable() { // from class: mhb
                @Override // java.lang.Runnable
                public final void run() {
                    mgx.AnonymousClass10.this.e(weakReference, obj);
                }
            });
        }

        /* synthetic */ void e(WeakReference weakReference, Object obj) {
            Context context = (Context) GeneralUtil.getReferent(weakReference);
            if (context != null) {
                mgx.this.a(context, obj);
            } else {
                LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "onResponse: context is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void a(Context context, T t) {
        if (!koq.e(t, HiHealthData.class)) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "readSingleTrackData is null");
            return;
        }
        ArrayList<TrackData> b2 = b((List<HiHealthData>) t);
        if (koq.b(b2)) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "readSingleTrackData no record!");
        } else {
            LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "toReadSingleTrackData sendTrackData size= ", Integer.valueOf(b2.size()));
            OnceMovementReceiver.e(context, b2);
        }
    }

    private ArrayList<TrackData> b(List<HiHealthData> list) {
        ArrayList<TrackData> arrayList = new ArrayList<>(8);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "the size of records from HiHealth is 0! ");
            return arrayList;
        }
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "single records size is ", Integer.valueOf(list.size()));
        for (HiHealthData hiHealthData : list) {
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                int abnormalTrack = hiTrackMetaData.getAbnormalTrack();
                int duplicated = hiTrackMetaData.getDuplicated();
                if (abnormalTrack != 0 || duplicated != 0) {
                    LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "AbnormalTrack abnormalResult == ", Integer.valueOf(abnormalTrack), "duplicateResult=", Integer.valueOf(duplicated));
                } else {
                    int sportDataSource = hiTrackMetaData.getSportDataSource();
                    if (sportDataSource == 2 || sportDataSource == 3) {
                        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "sportDataSource=", Integer.valueOf(sportDataSource));
                    } else {
                        TrackData c = c(hiHealthData, hiTrackMetaData);
                        if (c != null) {
                            arrayList.add(c);
                            LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "whole data from hihealth studio is*", Float.valueOf(c.acquireDistance()), " && num*", Integer.valueOf(c.acquireTrackNum()), "&&", Integer.valueOf(hiTrackMetaData.getSportType()), " time ", Long.valueOf(hiHealthData.getStartTime()));
                        }
                    }
                }
            } catch (JsonSyntaxException unused) {
                LogUtil.h("PLGACHIEVE_AchieveLocalDataManager", "trackMetaData is error");
            }
        }
        return arrayList;
    }

    public TrackData c(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData) {
        String str;
        String str2;
        String str3;
        TrackData trackData = new TrackData();
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        if (extendTrackMap == null || extendTrackMap.size() == 0) {
            str = "";
            str2 = "";
            str3 = str2;
        } else {
            str = extendTrackMap.get("skipNum");
            str3 = extendTrackMap.get("maxSkippingTimes");
            str2 = extendTrackMap.get("skipSpeed");
        }
        int sportType = hiTrackMetaData.getSportType();
        int totalDistance = hiTrackMetaData.getTotalDistance();
        trackData.saveDistance(totalDistance * 1.0f);
        trackData.saveTrackTime(hiHealthData.getStartTime());
        trackData.saveType(sportType);
        trackData.saveEndTime(hiHealthData.getEndTime());
        trackData.savePaceMap(hiTrackMetaData.getPaceMap());
        trackData.savePartTimeMap(hiTrackMetaData.getPartTimeMap());
        trackData.saveBestPace(hiTrackMetaData.getBestPace());
        trackData.saveSportDataSource(hiTrackMetaData.getSportDataSource());
        if (sportType == 283) {
            trackData.saveTrackNum(mfg.b(str));
            trackData.setMaxSkippingTimes(mfg.b(str3));
            trackData.setSkipSpeed(mfg.b(str2));
            trackData.setTotalTime(hiTrackMetaData.getTotalTime());
            return trackData;
        }
        if (!e(sportType) || totalDistance <= 0) {
            return null;
        }
        LogUtil.a("PLGACHIEVE_AchieveLocalDataManager", "isValidType");
        return trackData;
    }

    private boolean e(int i) {
        return Arrays.asList(e).contains(Integer.valueOf(i));
    }

    private long b() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(6, -30);
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long d() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(6, -7);
        return calendar.getTimeInMillis();
    }
}
