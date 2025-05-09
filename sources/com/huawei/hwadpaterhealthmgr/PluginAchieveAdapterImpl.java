package com.huawei.hwadpaterhealthmgr;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.routes.AppRoute$$Info$$HuaweiHealth;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.sport.model.AnnualWorkoutRecord;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.jsmodules.MenstrualModule;
import com.huawei.operation.operationactivity.OperationActivityCallback;
import com.huawei.operation.operationactivity.OperationActivityPuller;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.TrackData;
import com.huawei.pluginachievement.manager.service.OnceMovementReceiver;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.ui.openservice.db.control.OpenServiceControl;
import com.huawei.ui.openservice.db.model.OpenService;
import com.huawei.ui.openservice.db.model.UserServiceAuth;
import defpackage.bzs;
import defpackage.cjx;
import defpackage.dks;
import defpackage.gso;
import defpackage.iwz;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.koq;
import defpackage.kor;
import defpackage.kpt;
import defpackage.kvm;
import defpackage.kvo;
import defpackage.kwx;
import defpackage.kwy;
import defpackage.mcv;
import defpackage.mht;
import defpackage.mlg;
import defpackage.mmp;
import defpackage.mod;
import defpackage.mpf;
import defpackage.sqe;
import defpackage.sqf;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HwEncryptUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class PluginAchieveAdapterImpl implements PluginAchieveAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static int f6155a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static int g;
    private static int h;
    private static int i;

    private int[] e() {
        return new int[]{Constants.REBACK_MARKET_ENTRANCE, 40003, 42005, 42008, 40022, 42152, 42154, 40023, 42155, 42156, 42160, 42202, 42204, 42205, 42206, 40024, 40021, 42052, 42054, 42055, 42056};
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public Map<String, String> getInfo(String[] strArr) {
        return sqf.e(strArr);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void startWebActivityForResult(Context context, String str) {
        sqf.c(context, str);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public String generateMessage(MessageObject messageObject) {
        return sqe.d(messageObject);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void deleteMessage(String str) {
        LogUtil.c("PluginAchieveAdapterImpl", "msgId=" + str);
        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).deleteMessage(str);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getTotalCalorie(AchieveCallback achieveCallback, Context context) {
        LogUtil.a("PluginAchieveAdapterImpl", "getTotalCalorie enter.");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        a(countDownLatch);
        try {
            countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PluginAchieveAdapterImpl", "getTotalCalorie.await(): catch a InterruptedException");
        }
        LogUtil.a("PluginAchieveAdapterImpl", "getTotalCalorie toReadCalorieData.");
        sqe.b(achieveCallback);
    }

    private void a(final CountDownLatch countDownLatch) {
        iwz.b().a(new IFlushResult() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.3
            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onSuccess(Bundle bundle) {
                LogUtil.a("PluginAchieveAdapterImpl", "openSdkSingletonInit flush data onSuccess.");
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onFailed(Bundle bundle) {
                LogUtil.h("PluginAchieveAdapterImpl", "openSdkSingletonInit flush data from stepMoudle to account studio is failed!");
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.motion.IFlushResult
            public void onServiceException(Bundle bundle) {
                LogUtil.h("PluginAchieveAdapterImpl", "openSdkSingletonInit flush data onServiceException!");
                countDownLatch.countDown();
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public int getTotalFitDuration(Date date, Date date2) {
        LogUtil.a("PluginAchieveAdapterImpl", "startDate=", date, "endDate=", date2);
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        List<Map<String, Object>> recordsByDateRange = pluginSuggestion != null ? pluginSuggestion.getRecordsByDateRange(date, date2) : null;
        int i2 = 0;
        if (recordsByDateRange != null) {
            for (Map<String, Object> map : recordsByDateRange) {
                if (map.get("totalduration") != null) {
                    try {
                        i2 += Integer.parseInt(String.valueOf(map.get("totalduration")));
                    } catch (NumberFormatException unused) {
                        LogUtil.b("PluginAchieveAdapterImpl", "getTotalFitDuration NumberFormatException");
                    }
                }
            }
        }
        return i2;
    }

    private void a(final AchieveCallback achieveCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        String[] strArr = {ParsedFieldTag.STEP_SUM};
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setType(new int[]{40002});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setTimeRange(HiDateUtil.t(System.currentTimeMillis()), HiDateUtil.f(System.currentTimeMillis()));
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.14
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (!koq.b(list)) {
                    int unused = PluginAchieveAdapterImpl.i = list.get(0).getInt(ParsedFieldTag.STEP_SUM);
                    AchieveCallback achieveCallback2 = achieveCallback;
                    if (achieveCallback2 != null) {
                        achieveCallback2.onResponse(i2, Integer.valueOf(PluginAchieveAdapterImpl.i));
                        return;
                    }
                    return;
                }
                LogUtil.h("PluginAchieveAdapterImpl", " readAggregateData null");
                AchieveCallback achieveCallback3 = achieveCallback;
                if (achieveCallback3 != null) {
                    achieveCallback3.onResponse(i2, 0);
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void toReadAggregateData(final AchieveCallback achieveCallback, Context context, long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        String[] strArr = {ParsedFieldTag.STEP_SUM, ParsedFieldTag.CALORIES_SUM};
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setType(new int[]{40002, 40003});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setTimeRange(j, j2);
        HiHealthManager.d(context).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.22
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (koq.b(list)) {
                    achieveCallback.onResponse(100001, null);
                } else {
                    achieveCallback.onResponse(0, list);
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getTrackSumDistanceData(Context context, long j, long j2, int i2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", " getTrackSumDistanceData ");
        ArrayList arrayList = new ArrayList();
        arrayList.add(sqe.c(j, j2, i2));
        HiHealthManager.d(context).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.24
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i3, int i4) {
                if (sparseArray == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "getTrackSumDistanceData data is null");
                    AchieveCallback achieveCallback2 = achieveCallback;
                    if (achieveCallback2 != null) {
                        achieveCallback2.onResponse(i3, null);
                        return;
                    }
                    return;
                }
                if (achieveCallback != null) {
                    achieveCallback.onResponse(0, sparseArray.get(0));
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getSumData(Context context, long j, long j2, int i2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", " getSumData ");
        ArrayList arrayList = new ArrayList();
        arrayList.add(sqe.a(j, j2, i2));
        HiHealthManager.d(context).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.25
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i3, int i4) {
                if (sparseArray == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "getSumData data is null");
                    AchieveCallback achieveCallback2 = achieveCallback;
                    if (achieveCallback2 != null) {
                        achieveCallback2.onResponse(i3, null);
                        return;
                    }
                    return;
                }
                if (achieveCallback != null) {
                    achieveCallback.onResponse(0, sparseArray.get(0));
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getWeightData(Context context, long j, long j2, AchieveCallback achieveCallback) {
        sqe.a(context, j, j2, achieveCallback);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getTrackListData(Context context, long j, long j2, final int i2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", " getTrackListData ");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{sqe.d(i2)});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.21
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                if (obj == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "getTrackSumList return data is null.");
                    achieveCallback.onResponse(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PluginAchieveAdapterImpl", "getTrackSumList return map is empty.");
                    achieveCallback.onResponse(-1, null);
                } else {
                    achieveCallback.onResponse(0, sparseArray.get(sqe.d(i2)));
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void fetchSequenceDataBySportType(Context context, long j, long j2, int i2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", " fetchSequenceDataBySportType ");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(i2 != 0 ? sqe.b(i2) : null);
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(context).fetchSequenceDataBySportType(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.23
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                if (achieveCallback == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "fetchSequenceDataBySportType achieveCallback is null.");
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("PluginAchieveAdapterImpl", "fetchSequenceDataBySportType return data is error.");
                    achieveCallback.onResponse(i3, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PluginAchieveAdapterImpl", "fetchSequenceDataBySportType return map is empty.");
                    achieveCallback.onResponse(i3, null);
                } else {
                    achieveCallback.onResponse(0, sparseArray.get(i3));
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getSumYearData(Context context, long j, long j2, int i2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", " getSumYearData ");
        if (i2 == 22) {
            e(context, j, HiDateUtil.f(System.currentTimeMillis()), achieveCallback);
        } else if (i2 == 23) {
            e(context, j, j2, achieveCallback);
        } else {
            HiHealthManager.d(context).aggregateHiHealthData(sqe.d(j, j2, i2), new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.30
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i3, int i4) {
                    if (achieveCallback != null) {
                        LogUtil.a("PluginAchieveAdapterImpl", "getSumYearData success");
                        AchieveCallback achieveCallback2 = achieveCallback;
                        if (list == null) {
                            list = Collections.EMPTY_LIST;
                        }
                        achieveCallback2.onResponse(0, list);
                    }
                }
            });
        }
    }

    private void e(Context context, long j, long j2, final AchieveCallback achieveCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{40002, 40003, 40004});
        try {
            HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.26
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i2, Object obj, int i3, int i4) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i2, int i3) {
                    if (achieveCallback != null) {
                        LogUtil.a("PluginAchieveAdapterImpl", "getSumYearData success");
                        AchieveCallback achieveCallback2 = achieveCallback;
                        if (obj == null) {
                            obj = Collections.EMPTY_LIST;
                        }
                        achieveCallback2.onResponse(0, obj);
                    }
                }
            });
        } catch (Exception unused) {
            if (achieveCallback != null) {
                achieveCallback.onResponse(0, Collections.EMPTY_LIST);
            }
            LogUtil.b("PluginAchieveAdapterImpl", "requestTotalDetailData Exception");
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getReportStatistics(Context context, long j, long j2, int i2, final AchieveCallback achieveCallback) {
        HiAggregateOption c2;
        ArrayList arrayList = new ArrayList();
        if (i2 == 1) {
            c2 = c(j, j2);
        } else {
            if (i2 != 4) {
                if (achieveCallback != null) {
                    achieveCallback.onResponse(100007, null);
                    return;
                }
                return;
            }
            c2 = d(j, j2);
        }
        arrayList.add(c2);
        HiHealthManager.d(context).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i3, int i4) {
                if (achieveCallback != null) {
                    if (sparseArray == null) {
                        LogUtil.h("PluginAchieveAdapterImpl", "aggregateHiHealthDataEx data is null");
                        achieveCallback.onResponse(i3, null);
                    } else {
                        achieveCallback.onResponse(0, sparseArray.get(0));
                    }
                }
            }
        });
    }

    private HiAggregateOption c(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setReadType(0);
        int[] e2 = e();
        hiAggregateOption.setConstantsKey(b());
        hiAggregateOption.setType(e2);
        return hiAggregateOption;
    }

    private String[] b() {
        return new String[]{"Track_Duration_Sum", "sport_calorie_sum", "Track_Count_Sum", "Track_Abnormal_Count_Sum", "sport_run_calorie_sum", "Track_Ride_Distance_Sum", "Track_Ride_Duration_Sum", "sport_cycle_calorie_sum", "Track_Ride_Count_Sum", "Track_Ride_Abnormal_Count_Sum", "Track_Ride_CreepingWave", "Track_Swim_Distance_Sum", "Track_Swim_Duration_Sum", "Track_Swim_Count_Sum", "Track_Swim_Abnormal_Count_Sum", "sport_climb_calorie_sum", "sport_walk_calorie_sum", "Track_Walk_Distance_Sum", "Track_Walk_Duration_Sum", "Track_Walk_Count_Sum", "Track_Walk_Abnormal_Count_Sum"};
    }

    private HiAggregateOption d(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setConstantsKey(new String[]{"Track_Ride_Longest_Distance", "Track_Swim_Longest_Distance"});
        hiAggregateOption.setType(new int[]{42158, 42208});
        return hiAggregateOption;
    }

    public static void c(int i2, int i3, int i4, int i5) {
        if (kwx.c()) {
            LogUtil.h("PluginAchieveAdapterImpl", "sendThreeCircleChangeData app is sportOrFitting!");
            return;
        }
        if (i2 < c || i4 < e) {
            b = 0;
            h = 0;
        }
        if (a(i2, i3, i4, i5)) {
            LogUtil.a("PluginAchieveAdapterImpl", "threeCircle cd:", Integer.valueOf(i2), " cg:", Integer.valueOf(i3), " wd:", Integer.valueOf(i4), " wg:", Integer.valueOf(i5));
            if (i2 < i3) {
                b = 0;
            }
            if (i4 < i5) {
                h = 0;
            }
            if (i3 > 0 && i2 >= i3) {
                c(i2, i3);
            }
            if (i5 <= 0 || i4 < i5) {
                return;
            }
            e(i4, i5);
        }
    }

    private static boolean a(int i2, int i3, int i4, int i5) {
        boolean z = (i2 == c && i4 == e && i3 == f6155a && i5 == d) ? false : true;
        if (z) {
            c = i2;
            e = i4;
            f6155a = i3;
            d = i5;
        }
        return z;
    }

    public static void c(int i2, int i3) {
        if (i3 <= 0) {
            return;
        }
        int i4 = i2 / i3;
        LogUtil.a("PluginAchieveAdapterImpl", "sendThreeCircleChangeData caloriesData:", Integer.valueOf(i2), " caloriesGoal:", Integer.valueOf(i3), " ratio:", Integer.valueOf(i4), " mCalRatio:", Integer.valueOf(b));
        if (i4 != b) {
            b = i4;
            OnceMovementReceiver.cgr_(bCJ_("D1", i4, System.currentTimeMillis()));
        }
    }

    public static void e(int i2, int i3) {
        if (i3 <= 0) {
            return;
        }
        int i4 = i2 / i3;
        LogUtil.a("PluginAchieveAdapterImpl", "sendThreeCircleChangeData workoutData:", Integer.valueOf(i2), " workoutGoal:", Integer.valueOf(i3), " ratio:", Integer.valueOf(i4), " mWorkoutRatio:", Integer.valueOf(h));
        if (i4 != h) {
            h = i4;
            OnceMovementReceiver.cgr_(bCJ_("D2", i4, System.currentTimeMillis()));
        }
    }

    private static Bundle bCJ_(String str, int i2, long j) {
        Bundle bundle = new Bundle();
        bundle.putString("dataType", str);
        bundle.putInt("dataLevel", i2);
        bundle.putLong("timestamp", j);
        LogUtil.a("PluginAchieveAdapterImpl", "getThreeCircleBundle type:", str, " ratio:", Integer.valueOf(i2), " currentTime:", Long.valueOf(j));
        return bundle;
    }

    public static void a(int i2) {
        if (i2 < g) {
            g = 0;
        }
        if (i2 - g >= 100) {
            LogUtil.a("PluginAchieveAdapterImpl", "steps =" + i2);
            HashMap hashMap = new HashMap();
            hashMap.put("step", Integer.valueOf(i2));
            g = i2;
            if (i2 > 500) {
                LogUtil.a("PluginAchieveAdapterImpl", "setCurrentSteps kaka task steps = ", Integer.valueOf(i2));
                mcv.d(BaseApplication.getContext()).c(BaseApplication.getContext(), String.valueOf(KakaConstants.TASK_STEPS_REACH_THREE_THOUSAND), hashMap);
            }
        }
        i = i2;
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getTotalSteps(AchieveCallback achieveCallback) {
        a(achieveCallback);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoAllDeviceBinding(Context context) {
        LogUtil.a("PluginAchieveAdapterImpl", "gotoAllDeviceBinding isSwitchPage true");
        dks.e(context, (String) null);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void startMyTargetActivity(Context context) {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), "com.huawei.ui.main.stories.me.activity.MyTargetActivity");
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PluginAchieveAdapterImpl", "startMyTargetActivity: ActivityNotFoundException");
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void startInputWeight(Context context) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setPackage(context.getPackageName());
        intent.setClassName(context.getPackageName(), "com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity");
        context.startActivity(intent);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoBondDevice(Context context, String str) {
        dks.e(context, str);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoMeasureDeivice(Context context, String str) {
        if (getBondResult(str)) {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
            intent.putExtra("kind", str);
            intent.putExtra("view", "MeasureDevice");
            context.startActivity(intent);
            return;
        }
        gotoBondDevice(context, str);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public boolean getBondResult(String str) {
        return cjx.e().j(str);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoFitness() {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(BaseApplication.getContext());
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoTrainDetailActivity(String str, String str2) {
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId(str2);
        workoutRecord.saveWorkoutName(str);
        workoutRecord.savePlanId("");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(workoutRecord);
        mod.c(BaseApplication.getContext(), new mmp(str2), arrayList);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoFitnessRecord() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, 10001);
        Context context = BaseApplication.getContext();
        ixx.d().d(context, AnalyticsValue.HEALTH_HOME_GPS_HISTORY_2010015.value(), hashMap, 0);
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), Constants.SPORT_HISTORY);
        intent.setFlags(268435456);
        intent.putExtra(BleConstants.SPORT_TYPE, 10001);
        context.startActivity(intent);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoShumianService(Context context, final String str) {
        final OpenServiceControl openServiceControl = OpenServiceControl.getInstance(context);
        final OpenService queryServiceByID = openServiceControl.queryServiceByID(KakaConstants.DECOMPRESSI0N);
        if (queryServiceByID == null) {
            LogUtil.h("PluginAchieveAdapterImpl", "gotoShumianService openService is null");
            return;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.2
            @Override // java.lang.Runnable
            public void run() {
                UserServiceAuth queryServiceAuth = openServiceControl.queryServiceAuth(str, queryServiceByID.getServiceID());
                if (queryServiceAuth == null || queryServiceAuth.fetchAuthType() == 0) {
                    PluginAchieveAdapterImpl.this.d(openServiceControl, queryServiceByID.getServiceID(), str);
                }
            }
        });
        d(queryServiceByID, context);
        newSingleThreadExecutor.shutdown();
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoSleepDetailPage(Context context) {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/basicHealth?healthType=1"));
        intent.setPackage(context.getPackageName());
        jdw.bGh_(intent, context);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoBubuBaoService(Context context, final String str) {
        final OpenServiceControl openServiceControl = OpenServiceControl.getInstance(context);
        final OpenService queryServiceByID = openServiceControl.queryServiceByID(KakaConstants.DECOMPRESSI0N_ABBUBUBAO);
        if (queryServiceByID == null) {
            LogUtil.h("PluginAchieveAdapterImpl", "gotoShumianService openService is null");
            return;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.4
            @Override // java.lang.Runnable
            public void run() {
                UserServiceAuth queryServiceAuth = openServiceControl.queryServiceAuth(str, queryServiceByID.getServiceID());
                if (queryServiceAuth == null || queryServiceAuth.fetchAuthType() == 0) {
                    PluginAchieveAdapterImpl.this.d(openServiceControl, queryServiceByID.getServiceID(), str);
                }
            }
        });
        d(queryServiceByID, context);
        newSingleThreadExecutor.shutdown();
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoSecIBaoService(Context context, final String str) {
        final OpenServiceControl openServiceControl = OpenServiceControl.getInstance(context);
        final OpenService queryServiceByID = openServiceControl.queryServiceByID(KakaConstants.DECOMPRESSI0N_DONGBAO);
        if (queryServiceByID == null) {
            LogUtil.h("PluginAchieveAdapterImpl", "gotoSecIBaoService openService is null");
            return;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.1
            @Override // java.lang.Runnable
            public void run() {
                UserServiceAuth queryServiceAuth = openServiceControl.queryServiceAuth(str, queryServiceByID.getServiceID());
                if (queryServiceAuth == null || queryServiceAuth.fetchAuthType() == 0) {
                    PluginAchieveAdapterImpl.this.d(openServiceControl, queryServiceByID.getServiceID(), str);
                }
            }
        });
        d(queryServiceByID, context);
        newSingleThreadExecutor.shutdown();
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoRunRecord(Context context) {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), Constants.SPORT_HISTORY);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PluginAchieveAdapterImpl", "gotoRunRecord Exception");
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoStepRecord(Context context) {
        try {
            Intent intent = new Intent();
            intent.putExtra("today_current_steps_total", i);
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), PersonalData.CLASS_NAME_PERSONAL_HISTORY_BEST);
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PluginAchieveAdapterImpl", "gotoStepRecord Exception");
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoWeChatActivity(Context context) {
        Intent launchIntentForPackage;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (launchIntentForPackage = packageManager.getLaunchIntentForPackage(BaseApplication.getAppPackage())) == null) {
            return;
        }
        launchIntentForPackage.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.thirdpartservice.activity.ThirdPartServiceActivity"));
        launchIntentForPackage.addFlags(268435456);
        context.startActivity(launchIntentForPackage);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public String getEncryptData(String str, Context context) {
        try {
            return HwEncryptUtil.c(context).b(2, str);
        } catch (Exception unused) {
            LogUtil.b("PluginAchieveAdapterImpl", "getEncryptData Exception");
            return "default";
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public String decryptData(String str, Context context) {
        try {
            return HwEncryptUtil.c(context).a(2, str);
        } catch (Exception unused) {
            LogUtil.b("PluginAchieveAdapterImpl", "decryptData Exception");
            return "default";
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoTrackDetailData(Context context, long j, long j2) {
        if (j2 < j || j2 == 1 + j) {
            a(context, j, j2);
        } else {
            b(context, j, j2);
        }
    }

    public void a(final Context context, final long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{30002});
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.9
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                if (obj == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackMetadata data is null");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackMetadata map size is 0");
                    return;
                }
                List list = (List) sparseArray.get(30002);
                if (koq.b(list)) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackMetadata list is null");
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) list.get(0);
                if (hiHealthData == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackMetadata hiHealthData is null");
                    return;
                }
                long endTime = hiHealthData.getEndTime();
                LogUtil.a("PluginAchieveAdapterImpl", "hiHealthData=", hiHealthData.toString(), "endTime is", Long.valueOf(endTime));
                PluginAchieveAdapterImpl.this.b(context, j, endTime);
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void readBestStepDayOfYear(Context context, long j, long j2, final AchieveCallback achieveCallback) {
        readStepDataAsDayUnit(context, j, j2, 3, new AchieveCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.10
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "readBestStepDayOfYear obj is null");
                    achieveCallback.onResponse(-1, null);
                } else {
                    AchieveCallback achieveCallback2 = achieveCallback;
                    if (achieveCallback2 != null) {
                        achieveCallback2.onResponse(0, obj);
                    }
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void readEveryMonthStepsOfYear(Context context, long j, long j2, final AchieveCallback achieveCallback) {
        readStepDataAsDayUnit(context, j, j2, 5, new AchieveCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.7
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "readEveryMonthStepsOfYear obj is null");
                    achieveCallback.onResponse(-1, null);
                } else {
                    AchieveCallback achieveCallback2 = achieveCallback;
                    if (achieveCallback2 != null) {
                        achieveCallback2.onResponse(0, obj);
                    }
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void readStepDataAsDayUnit(Context context, long j, long j2, final int i2, final AchieveCallback achieveCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(1);
        int i3 = i2 == 6 ? 5 : i2;
        String[] a2 = sqe.a(40002);
        int[] c2 = sqe.c(40002);
        hiAggregateOption.setConstantsKey(a2);
        hiAggregateOption.setType(c2);
        hiAggregateOption.setGroupUnitType(i3);
        hiAggregateOption.setReadType(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        HiHealthManager.d(context).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.6
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i4, int i5) {
                if (sparseArray == null) {
                    achieveCallback.onResponse(-1, null);
                    return;
                }
                if (sparseArray.size() == 0) {
                    LogUtil.h("PluginAchieveAdapterImpl", "readStepDataAsDayUnit the data is null");
                    achieveCallback.onResponse(-1, null);
                    return;
                }
                List<HiHealthData> list = sparseArray.get(0);
                b bVar = new b();
                if (i2 == 3 && !koq.b(list)) {
                    achieveCallback.onResponse(0, sqe.c(list, bVar));
                } else if (i2 == 5) {
                    achieveCallback.onResponse(0, list);
                } else {
                    LogUtil.h("PluginAchieveAdapterImpl", "hiGroupUnitType not match");
                }
            }
        });
    }

    public static class b implements Serializable, Comparator<HiHealthData> {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return mlg.e(hiHealthData.getFloat("sum"), hiHealthData2.getFloat("sum"));
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getTrackDetailDataByTimestamp(Context context, long j, long j2, AchieveCallback achieveCallback) {
        if (j2 < j || j2 == 1 + j) {
            sqe.c(context, j, j2, achieveCallback);
        } else {
            sqe.b(context, j, j2, achieveCallback);
        }
    }

    public void b(Context context, long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.8
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                if (obj == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackDetail data is null");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackDetail map size is 0");
                    return;
                }
                List list = (List) sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
                if (koq.b(list)) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackDetail list is null");
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) list.get(0);
                if (hiHealthData == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "gotoTrackDetail hiHealthData is null");
                    return;
                }
                MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
                String a2 = kpt.a(hiHealthData, motionPathSimplify);
                motionPathSimplify.saveDeviceType(hiHealthData.getInt("trackdata_deviceType"));
                motionPathSimplify.saveProductId(hiHealthData.getString("device_prodid"));
                if (a2 == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "fileUrl is null");
                    return;
                }
                LogUtil.a("PluginAchieveAdapterImpl", "gotoTrackDetailData is success!");
                gso.e().init(BaseApplication.getContext());
                gso.e().e(a2, motionPathSimplify);
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public String getExchangeUrl(Context context) {
        return sqf.d(context);
    }

    private void d(OpenService openService, Context context) {
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("url", openService.getServiceUrl());
        intent.putExtra("EXTRA_BI_ID", openService.getServiceID());
        intent.putExtra("EXTRA_BI_NAME", openService.getProductName());
        intent.putExtra("EXTRA_BI_SOURCE", AppRoute$$Info$$HuaweiHealth.M_12);
        intent.putExtra("type", Constants.OPEN_SERVICE_TYPE);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(OpenServiceControl openServiceControl, String str, String str2) {
        UserServiceAuth userServiceAuth = new UserServiceAuth();
        userServiceAuth.configHuid(str2);
        userServiceAuth.configAuthType(1);
        userServiceAuth.configServiceID(str);
        openServiceControl.insertOrUpdateUserAuth(userServiceAuth);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getAnnualSleepReport(Context context, long j, long j2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", "getAnnualSleepReport with startTime = ", Long.valueOf(j), "endTime=", Long.valueOf(j2));
        HiAggregateOption a2 = sqe.a();
        a2.setStartTime(j);
        a2.setEndTime(j2);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(a2, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.11
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (list == null) {
                    achieveCallback.onResponse(100001, null);
                } else if (list.isEmpty()) {
                    achieveCallback.onResponse(100001, null);
                } else {
                    LogUtil.a("PluginAchieveAdapterImpl", "getAnnualSleepReport success");
                    achieveCallback.onResponse(i2, sqe.a(list));
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                achieveCallback.onResponse(100001, null);
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getLatestSleepDatas(long j, long j2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", "start getLatestSleepDatas() ");
        HiAggregateOption a2 = sqe.a();
        a2.setStartTime(j);
        a2.setEndTime(j2);
        a2.setSortOrder(1);
        a2.setCount(1);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(a2, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.15
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                AchieveCallback achieveCallback2 = achieveCallback;
                if (achieveCallback2 != null) {
                    achieveCallback2.onResponse(i2, list);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                LogUtil.a("PluginAchieveAdapterImpl", "getLatestSleepDatas onResultIntent intentType = ", Integer.valueOf(i2));
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getAnnualFitnessReport(Context context, final long j, final long j2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", "getAnnualFitnessReport with startTime =" + j + "enTime:" + j2);
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("PluginAchieveAdapterImpl", "getAllWorkoutRecord recordApi is null.");
        } else {
            recordApi.acquireDetailFitnessRecords(new kwy.a().a(0L).e(System.currentTimeMillis()).d(), new IBaseResponseCallback() { // from class: iwx
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    PluginAchieveAdapterImpl.e(AchieveCallback.this, j, j2, i2, obj);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.huawei.pluginachievement.impl.AchieveCallback] */
    public static /* synthetic */ void e(AchieveCallback achieveCallback, long j, long j2, int i2, Object obj) {
        if (achieveCallback == 0) {
            return;
        }
        if (obj != null) {
            ArrayList<WorkoutRecord> arrayList = new ArrayList();
            try {
                arrayList = (List) obj;
            } catch (ClassCastException unused) {
                LogUtil.b("PluginAchieveAdapterImpl", "getAnnualFitnessReport data ClassCastException");
            }
            ArrayList arrayList2 = new ArrayList();
            for (WorkoutRecord workoutRecord : arrayList) {
                if (workoutRecord.acquireExerciseTime() >= j && workoutRecord.acquireExerciseTime() <= j2) {
                    AnnualReportFitnessRaw annualReportFitnessRaw = new AnnualReportFitnessRaw();
                    annualReportFitnessRaw.saveWorkoutName(workoutRecord.acquireWorkoutName());
                    annualReportFitnessRaw.saveWorkoutId(workoutRecord.acquireWorkoutId());
                    annualReportFitnessRaw.saveDuring(workoutRecord.getDuration());
                    annualReportFitnessRaw.saveExerciseTime(workoutRecord.acquireExerciseTime());
                    annualReportFitnessRaw.saveWorkoutDate(workoutRecord.acquireWorkoutDate());
                    arrayList2.add(annualReportFitnessRaw);
                }
            }
            achieveCallback.onResponse(0, arrayList2);
            return;
        }
        achieveCallback.onResponse(i2, null);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getAnnualFitnessRecord(Context context, final long j, final long j2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", "getAnnualFitnessReport with startTime =", Long.valueOf(j), "enTime:", Long.valueOf(j2));
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("PluginAchieveAdapterImpl", "getAllAnnualWorkoutRecord recordApi is null.");
        } else {
            recordApi.acquireAnnualExerciseRecordByAll(new ResultCallback() { // from class: ixd
                @Override // com.huawei.health.suggestion.ResultCallback
                public final void onResult(int i2, Object obj) {
                    PluginAchieveAdapterImpl.a(AchieveCallback.this, j, j2, i2, obj);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.huawei.pluginachievement.impl.AchieveCallback] */
    public static /* synthetic */ void a(AchieveCallback achieveCallback, long j, long j2, int i2, Object obj) {
        if (achieveCallback == 0) {
            LogUtil.h("PluginAchieveAdapterImpl", "getAnnualFitnessRecord achieveCallback is empty");
            return;
        }
        if (!(obj instanceof List)) {
            LogUtil.h("PluginAchieveAdapterImpl", "getAnnualFitnessRecord: !(data instanceof List)");
            achieveCallback.onResponse(i2, null);
            return;
        }
        ArrayList<AnnualWorkoutRecord> arrayList = new ArrayList();
        try {
            arrayList = (List) obj;
        } catch (ClassCastException unused) {
            LogUtil.b("PluginAchieveAdapterImpl", "getAnnualFitnessReport data ClassCastException");
            achieveCallback.onResponse(i2, null);
        }
        ArrayList arrayList2 = new ArrayList();
        for (AnnualWorkoutRecord annualWorkoutRecord : arrayList) {
            if (annualWorkoutRecord.acquireExerciseTime() >= j && annualWorkoutRecord.acquireExerciseTime() <= j2) {
                AnnualReportFitnessRaw annualReportFitnessRaw = new AnnualReportFitnessRaw();
                annualReportFitnessRaw.saveWorkoutName(annualWorkoutRecord.acquireWorkoutName());
                annualReportFitnessRaw.saveDuring(annualWorkoutRecord.getDuration());
                annualReportFitnessRaw.saveExerciseTime(annualWorkoutRecord.acquireExerciseTime());
                annualReportFitnessRaw.saveWorkoutDate(annualWorkoutRecord.acquireWorkoutDate());
                annualReportFitnessRaw.setPrimaryClassify(annualWorkoutRecord.getPrimaryClassify());
                annualReportFitnessRaw.setSecondClassify(annualWorkoutRecord.getSecondClassify());
                annualReportFitnessRaw.saveCalorie(annualWorkoutRecord.acquireActualCalorie());
                annualReportFitnessRaw.saveWorkoutId(annualWorkoutRecord.acquireWorkoutId());
                annualReportFitnessRaw.saveVersion(annualWorkoutRecord.acquireVersion());
                arrayList2.add(annualReportFitnessRaw);
            }
        }
        achieveCallback.onResponse(0, arrayList2);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void readSingleTrackData(Context context, long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        LogUtil.a("PluginAchieveAdapterImpl", "readSingleTrackData 1km!");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{30002});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setTimeInterval(HiDateUtil.t(j), HiDateUtil.f(j2));
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.13
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.a("PluginAchieveAdapterImpl", "achieve 1km kaka onResult enter");
                if (i2 != 0 || obj == null) {
                    LogUtil.h("PluginAchieveAdapterImpl", "achieve 1km kaka fail! errorcode = ", Integer.valueOf(i2), "&& anchor is ", Integer.valueOf(i3));
                    iBaseResponseCallback.d(i2, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                Object obj2 = sparseArray.get(30002);
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PluginAchieveAdapterImpl", "achieve 1km kaka trackdata from DataStudio is none");
                    iBaseResponseCallback.d(i2, null);
                } else {
                    List list = (List) obj2;
                    LogUtil.a("PluginAchieveAdapterImpl", "achieve 1km kaka obtainFrom DataStudio Achieve size is ", Integer.valueOf(list.size()));
                    iBaseResponseCallback.d(i2, list);
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void sendTrackData(Context context, ArrayList<TrackData> arrayList) {
        LogUtil.a("PluginAchieveAdapterImpl", "sendTrackData maxSingleTrack.size == ", Integer.valueOf(arrayList.size()));
        OnceMovementReceiver.e(context, arrayList);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getFitSportSumDatas(int i2, AchieveCallback achieveCallback) {
        b(i2, 6, achieveCallback);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getOtherFitSportSumData(int i2, AchieveCallback achieveCallback) {
        d(i2, 6, achieveCallback);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getFitSportEverydayRecords(int i2, AchieveCallback achieveCallback) {
        b(i2, 3, achieveCallback);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getOtherFitSportEverydayRecords(int i2, AchieveCallback achieveCallback) {
        d(i2, 3, achieveCallback);
    }

    private void b(int i2, int i3, final AchieveCallback achieveCallback) {
        long b2 = mht.b(i2, true);
        long b3 = mht.b(i2, false);
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setGroupUnitType(i3);
        hiSportStatDataAggregateOption.setReadType(0);
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"duration_sum", "count_sum"});
        hiSportStatDataAggregateOption.setType(new int[]{4, 5});
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setTimeRange(b2, b3);
        HiHealthManager.d(BaseApplication.getContext()).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.12
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i4, List<HiHealthData> list, int i5, int i6) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i4, int i5) {
                AchieveCallback achieveCallback2 = achieveCallback;
                if (achieveCallback2 != null) {
                    achieveCallback2.onResponse(i4, list);
                } else {
                    LogUtil.h("PluginAchieveAdapterImpl", "getNewSportData achieveCallback is empty");
                }
            }
        });
    }

    private void d(int i2, int i3, final AchieveCallback achieveCallback) {
        long b2 = mht.b(i2, true);
        long b3 = mht.b(i2, false);
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(b2);
        hiAggregateOption.setEndTime(b3);
        hiAggregateOption.setType(new int[]{42204, 42205, 42303, 42304, 42354, 42355, 42404, 42405});
        hiAggregateOption.setConstantsKey(new String[]{"swim_duration_sum", "swim_count_sum", "basketball_duration_sum", "basketball_count_sum", "ski_duration_sum", "ski_count_sum", "golf_duration_sum", "golf_count_sum"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(i3);
        hiAggregateOption.setSortOrder(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.19
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i4, List<HiHealthData> list, int i5, int i6) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i4, int i5) {
                AchieveCallback achieveCallback2 = achieveCallback;
                if (achieveCallback2 != null) {
                    achieveCallback2.onResponse(i4, list);
                } else {
                    LogUtil.h("PluginAchieveAdapterImpl", "getOldSportData achieveCallback is empty");
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getAnnualActivityReport(Context context, int i2, final AchieveCallback achieveCallback) {
        OperationActivityPuller.getOperationActivity(new kvo(1, null, 4), new OperationActivityCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.20
            @Override // com.huawei.operation.operationactivity.OperationActivityCallback
            public void onResponse(int i3, List<kvm> list) {
                if (i3 == 0 && koq.c(list)) {
                    achieveCallback.onResponse(0, list);
                } else {
                    achieveCallback.onResponse(i3, null);
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getAnnualBloodSugarRecordData(int i2, final AchieveCallback achieveCallback) {
        kor.a().d(BaseApplication.getContext(), mht.b(i2, true), mht.b(i2, false), 0, new IBaseResponseCallback() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.18
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                AchieveCallback achieveCallback2 = achieveCallback;
                if (achieveCallback2 != null) {
                    achieveCallback2.onResponse(i3, obj);
                } else {
                    LogUtil.h("PluginAchieveAdapterImpl", "getAnnualBloodSugarRecordData achieveCallback is empty");
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getAnnualBloodPressureRecordData(int i2, final AchieveCallback achieveCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long b2 = mht.b(i2, true);
        long b3 = mht.b(i2, false);
        hiAggregateOption.setStartTime(b2);
        hiAggregateOption.setEndTime(b3);
        hiAggregateOption.setType(new int[]{2006, 2007});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.16
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                AchieveCallback achieveCallback2 = achieveCallback;
                if (achieveCallback2 != null) {
                    achieveCallback2.onResponse(i3, list);
                } else {
                    LogUtil.h("PluginAchieveAdapterImpl", "getAnnualBloodPressureRecordData achieveCallback is empty");
                }
            }
        });
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getLatestMesureDatas(long j, long j2, int i2, AchieveCallback achieveCallback) {
        sqe.d(j, j2, i2, achieveCallback);
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void gotoFitnessAllCourses() {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(BaseApplication.getContext());
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void goToPhysiologicalCycle(Context context) {
        a();
    }

    private void a() {
        mpf.e().launchActivity(BaseApplication.getContext(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: ixf
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return PluginAchieveAdapterImpl.this.bCK_(context, intent);
            }
        });
    }

    public /* synthetic */ boolean bCK_(Context context, Intent intent) {
        LogUtil.a("PluginAchieveAdapterImpl", "InstallCallback");
        f();
        return false;
    }

    private void f() {
        StringBuilder sb = new StringBuilder("#/?from=1");
        if (d() == 1) {
            sb.append("&isActived=true");
        } else {
            sb.append("&isActived=false");
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(sb.toString());
        bzs.e().initH5Pro();
        builder.setImmerse().showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true).enableOnPauseCallback().enableOnResumeCallback().setNeedSoftInputAdapter();
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        builder.addCustomizeJsModule("menstrual", MenstrualModule.class);
        bzs.e().loadH5ProApp(BaseApplication.getContext(), "com.huawei.health.h5.cycle-calendar", builder);
    }

    private int d() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("com.huawei.health.mc");
        if (userPreference == null || HiCommonUtil.b(userPreference.getValue())) {
            LogUtil.h("PluginAchieveAdapterImpl", "MENSTRUAL_CONFIG_KEY is null!");
            return 0;
        }
        try {
            return new JSONObject(userPreference.getValue()).getInt("masterSwitch");
        } catch (JSONException unused) {
            ReleaseLogUtil.c("PluginAchieveAdapterImpl", "getMenstrualActiveState JSONException");
            return 0;
        }
    }

    @Override // com.huawei.pluginachievement.PluginAchieveAdapter
    public void getSleepDatas(long j, long j2, final AchieveCallback achieveCallback) {
        LogUtil.a("PluginAchieveAdapterImpl", "start getSleepDatas() ");
        HiAggregateOption a2 = sqe.a();
        a2.setStartTime(j);
        a2.setEndTime(j2);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(a2, new HiAggregateListener() { // from class: com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl.17
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                AchieveCallback achieveCallback2 = achieveCallback;
                if (achieveCallback2 != null) {
                    achieveCallback2.onResponse(i2, list);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                LogUtil.a("PluginAchieveAdapterImpl", "getSleepDatas onResultIntent intentType = ", Integer.valueOf(i2));
            }
        });
    }
}
