package com.huawei.hwadpaterhealthmgr;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.algorithm.api.BreathTrainApi;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.agreement.AgrHttp;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ILoginCallback;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.adapter.SportData;
import com.huawei.operation.adapter.SportSummary;
import com.huawei.operation.adapter.SportsStatisticsCallback;
import com.huawei.operation.beans.WebViewConfig;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.Utils;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.openservice.db.control.OpenServiceControl;
import com.huawei.ui.openservice.db.model.OpenService;
import com.huawei.ui.openservice.db.model.UserServiceAuth;
import defpackage.bzb;
import defpackage.ceo;
import defpackage.cex;
import defpackage.cjx;
import defpackage.cos;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.ddh;
import defpackage.dks;
import defpackage.fdu;
import defpackage.gmz;
import defpackage.iwz;
import defpackage.jct;
import defpackage.jfq;
import defpackage.jiw;
import defpackage.kcy;
import defpackage.koq;
import defpackage.kxc;
import defpackage.mcv;
import defpackage.sqf;
import defpackage.sqg;
import defpackage.sqk;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PluginOperationAdapterImpl implements PluginOperationAdapter {
    private static volatile PluginOperationAdapterImpl d;
    private static gmz e;

    /* renamed from: a, reason: collision with root package name */
    private Context f6171a;
    private IBaseResponseCallback b;
    private IBaseResponseCallback c;
    private PluginOperation f;
    private Map<Integer, Integer> g;
    private HealthOpenSDK j;
    private JSONArray l;
    private Map<String, Integer> n;
    private List<SportSummary> o;
    private int r = 0;
    private int t = 0;
    private int q = 0;
    private int p = 0;
    private int k = 0;
    private long s = 0;
    private long m = 0;
    private long i = 0;
    private JSONObject h = null;
    private String w = null;
    private String u = null;

    private PluginOperationAdapterImpl(Context context) {
        this.f = null;
        if (context != null) {
            this.f6171a = context.getApplicationContext();
        } else {
            this.f6171a = BaseApplication.getContext();
        }
        this.f = PluginOperation.getInstance(this.f6171a);
        this.g = new HashMap();
        this.n = new HashMap();
        b();
    }

    public static PluginOperationAdapterImpl getInstance(Context context) {
        if (d == null) {
            synchronized (PluginOperationAdapterImpl.class) {
                if (d == null) {
                    d = new PluginOperationAdapterImpl(context);
                    e = gmz.d();
                }
            }
        }
        return d;
    }

    private void b() {
        this.g.put(1, 258);
        this.g.put(2, 257);
        this.g.put(3, 259);
        this.n.put("km", 1);
        this.n.put("s", 0);
        this.n.put("cal", 2);
    }

    public static void destroy() {
        d = null;
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public Map<String, String> getInfo(String[] strArr) {
        return sqf.e(strArr);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getHealthData(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        sqk.a(j, j2, i, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getAnnualReport(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_PluginOperationAdapterImpl", "getAnnualReport year = ", Integer.valueOf(i));
        mcv.d(this.f6171a).d(this.f6171a, i, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getHealthStat(String str, String str2, int i, IBaseResponseCallback iBaseResponseCallback) {
        sqk.b(str, str2, i, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public SportData getSportData() {
        a();
        SportData.Data data = new SportData.Data();
        data.configType(1);
        data.configValue(String.valueOf(this.r));
        ArrayList arrayList = new ArrayList();
        arrayList.add(data);
        SportData.Data data2 = new SportData.Data();
        data2.configType(2);
        data2.configValue(String.valueOf(this.q));
        arrayList.add(data2);
        SportData.Data data3 = new SportData.Data();
        data3.configType(3);
        data3.configValue(String.valueOf(this.t));
        arrayList.add(data3);
        e();
        LogUtil.a("Opera_PluginOperationAdapterImpl", "run = ", Integer.valueOf(this.p), "; ride = ", Integer.valueOf(this.k), "; run duration = ", Long.valueOf(this.s));
        SportData.Data data4 = new SportData.Data();
        data4.configType(4);
        data4.configValue(String.valueOf(this.p));
        arrayList.add(data4);
        SportData.Data data5 = new SportData.Data();
        data5.configType(5);
        data5.configValue(String.valueOf(this.k));
        arrayList.add(data5);
        SportData.Data data6 = new SportData.Data();
        data6.configType(6);
        data6.configValue(String.valueOf(this.s));
        arrayList.add(data6);
        SportData sportData = new SportData();
        sportData.configData(arrayList);
        return sportData;
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getSportData(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_PluginOperationAdapterImpl", "startDay = ", str, ",endDay = ", str2);
        this.h = new JSONObject();
        this.o = new ArrayList();
        this.l = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = simpleDateFormat.parse(str);
            Date parse2 = simpleDateFormat.parse(str2);
            this.m = sqg.e(parse);
            this.i = sqg.a(parse2);
            try {
                int c = HiDateUtil.c(str, str2, "yyyyMMdd");
                LogUtil.a("Opera_PluginOperationAdapterImpl", "dayCounts = ", Integer.valueOf(c));
                if (c < 1 || c > 10) {
                    LogUtil.b("Opera_PluginOperationAdapterImpl", "dayCounts is illegal !!");
                    iBaseResponseCallback.d(1001, null);
                    return;
                }
                for (int i = 0; i <= c - 1; i++) {
                    int c2 = HiDateUtil.c(this.m, -i);
                    SportSummary sportSummary = new SportSummary();
                    sportSummary.configDate(c2);
                    sportSummary.configSportType(0);
                    SportSummary.SportUnit sportUnit = new SportSummary.SportUnit();
                    sportUnit.configSteps(0);
                    sportUnit.configDistance(0);
                    sportUnit.configCalorie(0);
                    sportSummary.configSportUnit(sportUnit);
                    this.o.add(sportSummary);
                }
                b(iBaseResponseCallback);
            } catch (ParseException e2) {
                LogUtil.b("Opera_PluginOperationAdapterImpl", "parse date exception:", e2.getMessage());
                iBaseResponseCallback.d(1001, null);
            }
        } catch (ParseException e3) {
            LogUtil.b("Opera_PluginOperationAdapterImpl", "getSportData dateFormat exception: ", e3.getMessage());
            iBaseResponseCallback.d(1001, null);
        }
    }

    private void a() {
        this.r = 0;
        this.t = 0;
        this.q = 0;
        if (this.j == null) {
            this.j = iwz.b();
        }
        this.j.b(new IExecuteResult() { // from class: com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl.4
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                LogUtil.a("Opera_PluginOperationAdapterImpl", "get today steps success");
                if (obj == null) {
                    LogUtil.h("Opera_PluginOperationAdapterImpl", "get today steps success but boj==null");
                    return;
                }
                if (obj instanceof Bundle) {
                    Bundle bundle = (Bundle) obj;
                    PluginOperationAdapterImpl.this.r = bundle.getInt("step");
                    PluginOperationAdapterImpl.this.t = bundle.getInt("carior");
                    PluginOperationAdapterImpl.this.q = bundle.getInt("distance");
                }
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
                LogUtil.b("Opera_PluginOperationAdapterImpl", "OpenSDK IExecuteResult onFailed!");
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
                LogUtil.b("Opera_PluginOperationAdapterImpl", "OpenSDK IExecuteResult onServiceException!");
            }
        });
    }

    private void e() {
        long currentTimeMillis = System.currentTimeMillis();
        long e2 = sqg.e(new Date(currentTimeMillis));
        this.p = 0;
        this.k = 0;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(e2, currentTimeMillis);
        hiDataReadOption.setType(new int[]{30002});
        HiHealthManager.d(this.f6171a).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                PluginOperationAdapterImpl.this.d(obj);
            }
        });
        try {
            Thread.sleep(500L);
        } catch (InterruptedException unused) {
            LogUtil.a("Opera_PluginOperationAdapterImpl", "thread waiting fail!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        List list;
        long j;
        int i;
        if (obj == null) {
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() > 0 && (list = (List) sparseArray.get(30002)) != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                try {
                    HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiTrackMetaData.class);
                    int sportType = hiTrackMetaData.getSportType();
                    if (hiTrackMetaData.getAbnormalTrack() != 0 || hiTrackMetaData.getSportDataSource() == 2) {
                        j = 0;
                        i = 0;
                    } else {
                        j = hiTrackMetaData.getTotalTime();
                        i = hiTrackMetaData.getTotalDistance();
                    }
                    if (sportType == 258) {
                        arrayList.add(Integer.valueOf(i));
                        arrayList3.add(Long.valueOf(j));
                    } else if (sportType == 264) {
                        arrayList.add(Integer.valueOf(i));
                        arrayList3.add(Long.valueOf(j));
                    } else if (sportType == 259) {
                        arrayList2.add(Integer.valueOf(i));
                    } else {
                        LogUtil.h("Opera_PluginOperationAdapterImpl", "no branch!");
                    }
                } catch (JsonSyntaxException unused) {
                    LogUtil.h("Opera_PluginOperationAdapterImpl", "trackMetaData is error");
                }
            }
            c(arrayList3, arrayList, arrayList2);
        }
    }

    private void c(List<Long> list, List<Integer> list2, List<Integer> list3) {
        if (list.size() > 0) {
            this.s = ((Long) Collections.max(list)).longValue();
        }
        if (list2.size() > 0) {
            this.p = ((Integer) Collections.max(list2)).intValue();
        }
        if (list3.size() > 0) {
            this.k = ((Integer) Collections.max(list3)).intValue();
        }
    }

    private void b(final IBaseResponseCallback iBaseResponseCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(this.m);
        hiAggregateOption.setEndTime(this.i);
        hiAggregateOption.setType(new int[]{40002, 40003, 40004, 42003, Constants.REBACK_MARKET_RESULT_CODE});
        hiAggregateOption.setConstantsKey(new String[]{ParsedFieldTag.STEP_SUM, "calorie_sum", "distance_sum", "track_distance_sum", "track_calories_sum"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        HiHealthNativeApi.a(this.f6171a).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                PluginOperationAdapterImpl.this.a(list, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        int max;
        int i;
        int i2;
        if (list == null || list.isEmpty()) {
            LogUtil.a("Opera_PluginOperationAdapterImpl", "get step distances calories HiAggregateListener onResult data is null");
            e(iBaseResponseCallback);
            return;
        }
        int c = HiDateUtil.c(System.currentTimeMillis());
        for (SportSummary sportSummary : this.o) {
            SportSummary.SportUnit sportUnit = sportSummary.getSportUnit();
            for (HiHealthData hiHealthData : list) {
                int c2 = HiDateUtil.c(hiHealthData.getStartTime());
                if (c2 == sportSummary.getDate()) {
                    LogUtil.c("Opera_PluginOperationAdapterImpl", "getStepSportData date = ", Integer.valueOf(c2));
                    if (c2 == c) {
                        a();
                        i = this.r;
                        i2 = this.t;
                        max = this.q;
                    } else {
                        int i3 = hiHealthData.getInt(ParsedFieldTag.STEP_SUM);
                        int i4 = hiHealthData.getInt("calorie_sum");
                        int i5 = hiHealthData.getInt("distance_sum");
                        int i6 = hiHealthData.getInt("track_calories_sum");
                        int i7 = hiHealthData.getInt("track_distance_sum");
                        int max2 = Math.max(i4, i6);
                        max = Math.max(i5, i7);
                        i = i3;
                        i2 = max2;
                    }
                    sportUnit.configSteps(i);
                    sportUnit.configCalorie(i2);
                    sportUnit.configDistance(max);
                    sportSummary.configSportUnit(sportUnit);
                }
            }
        }
        e(iBaseResponseCallback);
    }

    private void e(IBaseResponseCallback iBaseResponseCallback) {
        Iterator<SportSummary> it = this.o.iterator();
        while (it.hasNext()) {
            try {
                this.l.put(new JSONObject(it.next().toString()));
            } catch (JSONException e2) {
                LogUtil.b("Opera_PluginOperationAdapterImpl", "responseSportData sportSummary exception ", e2.getMessage());
                iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, this.h);
                return;
            }
        }
        try {
            this.h.put(JsUtil.SUMMARIES_KEY, this.l);
            iBaseResponseCallback.d(0, this.h);
        } catch (JSONException e3) {
            LogUtil.b("Opera_PluginOperationAdapterImpl", "responseSportData sportDataJson exception ", e3.getMessage());
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, this.h);
        }
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getMotionPathData(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Opera_PluginOperationAdapterImpl", "mStartTime = ", Long.valueOf(j), ", mEndTime = ", Long.valueOf(j2));
        long j3 = j2 - j;
        double d2 = (j3 * 1.0d) / 8.64E7d;
        LogUtil.c("Opera_PluginOperationAdapterImpl", "dayCounts = ", Double.valueOf(d2));
        if (j3 < 0 || d2 > 3.0d) {
            iBaseResponseCallback.d(1001, null);
        } else {
            sqg.b(j, j2, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public int bindDevice(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str5)) {
            LogUtil.h("Opera_PluginOperationAdapterImpl", "bindDevice uniqueId is null ");
            return 2;
        }
        if (ceo.d().h(str5)) {
            return -1;
        }
        return !cjx.e().b(str, str2, new cex(str4, str, str5, str3), null) ? 1 : 0;
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public boolean unbindDevice(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("Opera_PluginOperationAdapterImpl", "unbindDevice context  is null");
            return false;
        }
        dcz d2 = ResourceManager.e().d(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        if (d2 == null) {
            return false;
        }
        if (d2.n() != null) {
            contentValues.put("name", dcx.d(str, d2.n().b()));
        }
        if (d2.l() != null) {
            contentValues.put("deviceType", d2.l().name());
        }
        if (d2.x() != null) {
            contentValues.put(Constants.KEY_BLE_SCAN_MODE, Integer.valueOf(d2.x().c()));
        }
        if (d2.s() != null) {
            contentValues.put("kitUuid", d2.s());
        }
        return cjx.e().Gv_(context, contentValues, str);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getCourseRecommend(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback) {
        sqk.a(str, str2, str3, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getUserInfo(IBaseResponseCallback iBaseResponseCallback) {
        sqk.a(iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void startAchieveAwardShare(Context context, String str, String str2) {
        mcv d2 = mcv.d(context);
        d2.setAdapter(new PluginAchieveAdapterImpl());
        d2.b(context, str, str2);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void jumpAchieveMedalById(Context context, String str) {
        mcv d2 = mcv.d(context);
        d2.setAdapter(new PluginAchieveAdapterImpl());
        d2.c(context, str, "3");
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void jumpAchieveKakaPage(Context context) {
        mcv d2 = mcv.d(context);
        d2.setAdapter(new PluginAchieveAdapterImpl());
        d2.b(context);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void jumpMyAwardPage(Context context) {
        mcv d2 = mcv.d(context);
        d2.setAdapter(new PluginAchieveAdapterImpl());
        d2.m(context);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void share(Context context, fdu fduVar, IBaseResponseCallback iBaseResponseCallback) {
        sqf.c(context, fduVar, iBaseResponseCallback, false);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void startGpsTrackPage(Context context, int i, String str, float f) {
        sqf.b(context, this.g.get(Integer.valueOf(i)).intValue(), this.n.get(str).intValue(), f);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void startFitnessPage(Context context, String str, String str2) {
        sqf.b(context, str, str2);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void startFitnessList() {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(this.f6171a);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public boolean checkCurrentUrlAuth(String str, String str2) {
        return OpenServiceControl.getInstance(this.f6171a).checkUrlAuth(str, str2);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public List<String> queryUrlList(String str) {
        OpenServiceControl.getInstance(this.f6171a);
        return OpenServiceControl.queryUrlList(str);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public WebViewConfig queryWebViewConfig() {
        return sqk.d();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void stressControl(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        kxc.e().b(i, i2, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void relaxControl(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        kxc.e().d(i, i2, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void gameControl(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        kxc.e().d(i, i2, i3, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void calibrationControl(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        kxc.e().a(i, i2, i3, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void checkConnected(IBaseResponseCallback iBaseResponseCallback) {
        kxc.e().d(iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void resetCalibration(IBaseResponseCallback iBaseResponseCallback) {
        kxc.e().b(iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void checkCalibration(IBaseResponseCallback iBaseResponseCallback) {
        kxc.e().a(iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void stressAbort(IBaseResponseCallback iBaseResponseCallback, boolean z) {
        kxc.e().a(iBaseResponseCallback, z);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public boolean checkWhiteUrl(String str) {
        return OpenServiceControl.getInstance(this.f6171a).checkWhiteUrl(str);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void setAchieveEvent(String str, Map<String, Object> map) {
        LogUtil.c("Opera_PluginOperationAdapterImpl", "setAchieveEvent key", str, " map = ", map);
        mcv.d(this.f6171a).c(this.f6171a.getApplicationContext(), str, map);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getPersonalPrivacySettingValue(int i) {
        if (i == 6) {
            return e.c(7);
        }
        return e.c(i);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void go2PersonalPrivacySettingsActivity() {
        Intent intent = new Intent();
        intent.setClassName("com.huawei.health", "com.huawei.ui.main.stories.me.activity.PrivacyCenterActivity");
        intent.setFlags(268435456);
        this.f6171a.startActivity(intent);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void breatheControl(int i, int i2, int i3, int i4, IBaseResponseCallback iBaseResponseCallback) {
        bzb bzbVar = new bzb();
        bzbVar.d(i);
        bzbVar.b(i2);
        bzbVar.c(i3);
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.a("BreathTrainService", BreathTrainApi.class);
        if (breathTrainApi == null) {
            LogUtil.h("Opera_PluginOperationAdapterImpl", "breathTrainApi is null");
        } else {
            breathTrainApi.breathControl(bzbVar, i4, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public int getJanusDeviceConnect() {
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.a("BreathTrainService", BreathTrainApi.class);
        if (breathTrainApi == null) {
            LogUtil.h("Opera_PluginOperationAdapterImpl", "brath train api is null");
            return 0;
        }
        return breathTrainApi.getJanusDeviceConnect();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public int canFinish(Activity activity) {
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.a("BreathTrainService", BreathTrainApi.class);
        if (breathTrainApi == null) {
            LogUtil.h("Opera_PluginOperationAdapterImpl", "brath train api is null");
            return 3;
        }
        return breathTrainApi.canFinish(activity);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getServiceIdByUrl(String str) {
        LogUtil.c("Opera_PluginOperationAdapterImpl", "getServiceIdByUrl url = " + str);
        return OpenServiceControl.getInstance(this.f6171a).getServiceIdByUrl(str);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String queryServiceNameById(String str) {
        OpenService queryServiceByID = OpenServiceControl.getInstance(this.f6171a).queryServiceByID(str);
        return queryServiceByID != null ? queryServiceByID.getProductName() : "";
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public int getAuthType(String str, String str2) {
        UserServiceAuth queryServiceAuth = OpenServiceControl.getInstance(this.f6171a).queryServiceAuth(str, str2);
        if (queryServiceAuth == null || queryServiceAuth.fetchAuthType() == 0) {
            return 0;
        }
        return queryServiceAuth.fetchAuthType() == 1 ? 1 : 2;
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void showServiceTips(Context context, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback) {
        if (hashMap == null || iBaseResponseCallback == null) {
            return;
        }
        LogUtil.c("Opera_PluginOperationAdapterImpl", "showServiceTips dialogType = ", hashMap.get("dialogType"), ", serviceName = ", hashMap.get(JsUtil.SERVICE_NAME));
        UserServiceAuth userServiceAuth = new UserServiceAuth();
        userServiceAuth.configHuid(hashMap.get("huid"));
        userServiceAuth.configServiceID(hashMap.get("serviceId"));
        userServiceAuth.configAuthType(1);
        OpenServiceControl.getInstance(context).insertOrUpdateUserAuth(userServiceAuth);
        iBaseResponseCallback.d(1, "0");
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public List<Map<String, Object>> getRecordsByDateRange(String str, String str2) {
        Date stringToDate = Utils.stringToDate(str);
        Date stringToDate2 = Utils.stringToDate(str2);
        if (stringToDate == null || stringToDate2 == null) {
            LogUtil.h("Opera_PluginOperationAdapterImpl", "startDate == null || endDate == null");
            return null;
        }
        LogUtil.a("Opera_PluginOperationAdapterImpl", "getRecordsByDateRange startDate = ", stringToDate, ",endDate = ", stringToDate2);
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion != null) {
            return pluginSuggestion.getRecordsByDateRange(stringToDate, stringToDate2);
        }
        return null;
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getFitnessSumData(long j, long j2, String str, SportsStatisticsCallback sportsStatisticsCallback) {
        sqk.c(j, j2, str, sportsStatisticsCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getWorkoutRecordByTimeAndId(long j, long j2, String str, final String str2, final SportsStatisticsCallback sportsStatisticsCallback) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(":");
            String str3 = split[0];
            String str4 = split.length > 0 ? split[1] : "";
            RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
            if (recordApi == null) {
                LogUtil.h("Opera_PluginOperationAdapterImpl", "getWorkoutRecordByTimeAndId recordApi is null.");
                return;
            } else {
                recordApi.acquireExerciseRecordByTimeAndId(j, j2, str3, str4, new ResultCallback() { // from class: ixe
                    @Override // com.huawei.health.suggestion.ResultCallback
                    public final void onResult(int i, Object obj) {
                        PluginOperationAdapterImpl.d(str2, sportsStatisticsCallback, i, obj);
                    }
                });
                return;
            }
        }
        LogUtil.h("Opera_PluginOperationAdapterImpl", "workOutStr is empty");
    }

    public static /* synthetic */ void d(String str, SportsStatisticsCallback sportsStatisticsCallback, int i, Object obj) {
        LogUtil.a("Opera_PluginOperationAdapterImpl", "onResponse returnCode", Integer.valueOf(i));
        if (str == null) {
            return;
        }
        if (obj != null) {
            sportsStatisticsCallback.callSportSumDataJsFunction(i, obj, str);
        } else {
            sportsStatisticsCallback.callSportSumDataJsFunction(i, "", str);
        }
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void uploadSleepQuestionnaireResults(long j, String str, IBaseResponseCallback iBaseResponseCallback) {
        sqk.b(j, str, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void querySleepQuestionnaireResults(long j, IBaseResponseCallback iBaseResponseCallback) {
        sqk.c(j, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void deleteSleepQuestionnaireResult(long j, IBaseResponseCallback iBaseResponseCallback) {
        sqk.a(j, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void vmallAgrSign(IBaseResponseCallback iBaseResponseCallback) {
        this.b = iBaseResponseCallback;
        if (this.w != null) {
            c();
        } else {
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        String url = GRSManager.a(this.f6171a).getUrl("agreementservice");
        String accountInfo = LoginInit.getInstance(this.f6171a).getAccountInfo(1015);
        String b = SharedPreferenceManager.b(this.f6171a, Integer.toString(10000), "agr_first_sign_language");
        ArrayList arrayList = new ArrayList();
        arrayList.add(134);
        arrayList.add(10023);
        AgrHttp agrHttp = new AgrHttp();
        LogUtil.a("Opera_PluginOperationAdapterImpl", "First sign country ", "CN", " language ", b);
        agrHttp.signHttpReq(accountInfo, url, true, arrayList, "CN", b + "_CN", new HttpResCallBack() { // from class: com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl.3
            @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
            public void onFinished(int i, String str) {
                if (PluginOperationAdapterImpl.this.b != null) {
                    SharedPreferenceManager.e(PluginOperationAdapterImpl.this.f6171a, "socialsharedpreference", Constants.IS_SIGN_VMALL_ARG, Boolean.toString(true), (StorageParams) null);
                    PluginOperationAdapterImpl.this.b.d(i, str);
                    LogUtil.a("Opera_PluginOperationAdapterImpl", "First sign resCode ", Integer.valueOf(i), " result ", str);
                    PluginOperationAdapterImpl.this.b = null;
                }
            }
        });
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void vmallAgrQuery(IBaseResponseCallback iBaseResponseCallback) {
        String b = SharedPreferenceManager.b(this.f6171a, "socialsharedpreference", Constants.IS_SIGN_VMALL_ARG);
        LogUtil.a("Opera_PluginOperationAdapterImpl", "isSignVmallArg = ", b);
        if ("false".equals(b)) {
            iBaseResponseCallback.d(200, "false");
        } else {
            j();
            this.c = iBaseResponseCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.w = LoginInit.getInstance(this.f6171a).getAccountInfo(1015);
        this.u = GRSManager.a(BaseApplication.getContext()).getUrl("agreementservice");
        new AgrHttp().queryHttpReq(this.w, this.u, true, new HttpResCallBack() { // from class: com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl.5
            @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
            public void onFinished(int i, String str) {
                if (PluginOperationAdapterImpl.this.c != null) {
                    PluginOperationAdapterImpl.this.c.d(i, str);
                    LogUtil.a("Opera_PluginOperationAdapterImpl", "First sign resCode ", Integer.valueOf(i), " result ", str);
                    PluginOperationAdapterImpl.this.c = null;
                }
            }
        });
    }

    private void j() {
        LoginInit.getInstance(this.f6171a).refreshAccessToken(new ILoginCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl.7
            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginSuccess(Object obj) {
                if (PluginOperationAdapterImpl.this.c != null) {
                    PluginOperationAdapterImpl.this.d();
                }
                if (PluginOperationAdapterImpl.this.b != null) {
                    PluginOperationAdapterImpl.this.c();
                }
            }

            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginFailed(Object obj) {
                LogUtil.h("Opera_PluginOperationAdapterImpl", "obtainToken failed = ", obj);
            }
        });
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getLocale() {
        return HwWatchFaceUtil.b().d();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void setSendOpenId(String str) {
        kcy.e(BaseApplication.getContext()).e(str, new IBaseResponseCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                Object[] objArr = new Object[4];
                objArr[0] = "setEcgMeasureAuthAccountCommand onResponse errCode:";
                objArr[1] = Integer.valueOf(i);
                objArr[2] = ",objData:";
                objArr[3] = obj == null ? "objData is null" : obj.toString();
                LogUtil.a("HWRRiServiceMgr", objArr);
            }
        });
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public boolean isSupportStepCounter(Context context) {
        if (jct.d(context)) {
            return true;
        }
        List<DeviceInfo> b = jfq.c().b(HwGetDevicesMode.ALL_DEVICES, (HwGetDevicesParameter) null, "Opera_PluginOperationAdapterImpl");
        return b != null && b.size() > 0;
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getUserLabels() {
        List<String> allLabels = ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).getAllLabels(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        return !koq.b(allLabels) ? allLabels.toString() : "";
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void setAppMarketParameter(int i, Object obj) {
        jiw.a().a(i, obj);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getAppMarketLocal() {
        return LanguageUtil.e();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public int getNetworkStatus() {
        return jiw.a().d(BaseApplication.getContext());
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getBondedDeviceAddress(String str) {
        HealthDevice a2;
        return (str == null || (a2 = cjx.e().a(str)) == null) ? "" : a2.getAddress();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public ArrayList<ContentValues> getBondedDeviceByProductId(String str) {
        if (str != null) {
            return cjx.e().b(str);
        }
        return new ArrayList<>();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void saveMeasureData(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        sqk.c(str, str2, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getContentCenterTest() {
        return jiw.a().d();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public boolean isBindDevice() {
        return sqk.a();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void getHealthDataStatistics(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        sqg.a(j, j2, i, iBaseResponseCallback);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public boolean unbindHaveBindingDevice(Context context, ContentValues contentValues, String str) {
        return cjx.e().Gv_(context, contentValues, str);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public boolean isRopeDevice(String str) {
        return dks.g(str);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void checkUserWeightAndShowDialog(Context context, Object obj) {
        dks.b(context, obj);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getCurrentUserUuid() {
        return sqk.c();
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public void createHpkInstalled(String str, String str2) {
        ddh.c().a(str, str2);
    }

    @Override // com.huawei.operation.adapter.PluginOperationAdapter
    public String getProductDir() {
        return cos.e;
    }
}
