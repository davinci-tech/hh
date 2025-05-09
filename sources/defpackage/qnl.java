package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import com.huawei.ui.main.stories.health.sleep.SleepDayBarChartProvider;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class qnl {
    private static final Object b = new Object();
    private static final int[] c = {22101, 22102, 22103, 22104, 22105};
    private static final int[] j = {22101, 22102, 22103, 22104, 22105, 22001, 22002, 22003};
    private static final String[] d = {"core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_deep_key", "core_sleep_wake_key", "core_sleep_day_sleep_time_key"};
    private static final String[] h = {"core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_deep_key", "core_sleep_wake_key", "core_sleep_day_sleep_time_key", "sleep_deep_key", "sleep_shallow_key", "sleep_wake_key"};
    private static final int[] f = {44203, 44106, 44008, 44201, 44009, 44202, 44107};
    private static final int[] e = {DicDataTypeUtil.DataType.BED_TIME.value(), DicDataTypeUtil.DataType.RISING_TIME.value(), DicDataTypeUtil.DataType.SLEEP_SCORE.value(), DicDataTypeUtil.DataType.WAKE_COUNT.value(), DicDataTypeUtil.DataType.SLEEP_END_REASON.value(), DicDataTypeUtil.DataType.WAKE_UP_FEELING.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_LATENCY.value()};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f16495a = {DicDataTypeUtil.DataType.SLEEP_SCORE.value()};

    public static int a(long j2, long j3, long j4, long j5) {
        boolean z = j2 == 0 || j2 < j4;
        boolean z2 = j3 == 0 || j3 > j5;
        if (z && z2) {
            return 3;
        }
        if (z) {
            return 2;
        }
        return z2 ? 1 : 0;
    }

    public static int e(List<String> list, List<String> list2, List<String> list3) {
        if (koq.c(list) && koq.c(list2) && koq.c(list3)) {
            LogUtil.a("SleepUtil", "judgePriority wear pillow phone");
            return 1;
        }
        if (koq.c(list) && koq.c(list2) && koq.b(list3)) {
            LogUtil.a("SleepUtil", "judgePriority wear pillow");
            return 2;
        }
        if (koq.c(list) && koq.c(list3)) {
            LogUtil.a("SleepUtil", "judgePriority wear phone");
            return 3;
        }
        if (koq.c(list2) && koq.c(list3)) {
            LogUtil.a("SleepUtil", "judgePriority pillow phone");
            return 4;
        }
        LogUtil.a("SleepUtil", "judgePriority only");
        return 0;
    }

    private static qgr e(HiHealthData hiHealthData, int i) {
        boolean z;
        if (hiHealthData == null) {
            LogUtil.h("SleepUtil", "getJudgeOfSleepData summary is null");
            return null;
        }
        int i2 = hiHealthData.getInt("core_sleep_deep_key");
        int i3 = hiHealthData.getInt("core_sleep_shallow_key");
        int i4 = hiHealthData.getInt("core_sleep_wake_dream_key");
        int i5 = hiHealthData.getInt("core_sleep_day_sleep_time_key");
        int i6 = hiHealthData.getInt("sleep_deep_key");
        int i7 = hiHealthData.getInt("sleep_shallow_key");
        boolean z2 = i3 > 0 || i2 > 0 || i4 > 0;
        if (i != 1 || z2 || i5 > 0) {
            z = true;
        } else {
            i3 = i7;
            i2 = i6;
            z = false;
        }
        return new qgr(z2, i5 == ((i2 + i5) + i3) + i4 && i5 != 0, z, (i2 == 0 && i3 == 0) && (i4 == 0 && i5 == 0), i3 > 0 && i2 == 0 && i4 == 0 && i != 3);
    }

    public static Object[] d(HiHealthData hiHealthData, HiHealthData hiHealthData2, int i, int i2, HiHealthData hiHealthData3, List<String> list, List<String> list2, List<String> list3) {
        LogUtil.a("SleepUtil", "judgeDataForTwoDevice");
        Object[] objArr = new Object[3];
        qgr e2 = e(hiHealthData, i);
        qgr e3 = e(hiHealthData2, i2);
        if (e2 == null || e3 == null) {
            LogUtil.h("SleepUtil", "judgeDataForTwoDevice judge ", e2, " judge2 ", e3);
            return objArr;
        }
        if (e2.b() && e3.b()) {
            LogUtil.a("SleepUtil", "judgeDataForTwoDevice DATA_PLATFORMS_TYPE_NO_DATA_CODE");
            nrq.a().e(5001);
        }
        boolean d2 = e2.d();
        int i3 = FitnessStatusCodes.UNKNOWN_AUTH_ERROR;
        if (d2 && e2.a()) {
            if (e2.e()) {
                LogUtil.a("SleepUtil", "judgeDataForTwoDevice DATA_PLATFORMS_TYPE_INSUFFICIENT_HEART_RATE_CODE");
                nrq.a().e(5002);
            } else {
                LogUtil.a("SleepUtil", "judgeDataForTwoDevice DATA_PLATFORMS_TYPE_CORE_SLEEP_CODE");
                nrq.a().e(FitnessStatusCodes.UNKNOWN_AUTH_ERROR);
            }
            if (i == 1) {
                objArr[0] = hiHealthData;
                objArr[1] = 2;
            } else if (i != 3) {
                LogUtil.h("SleepUtil", "judgeDataForTwoDevice type ", Integer.valueOf(i));
            } else {
                objArr[0] = hiHealthData;
                objArr[1] = 3;
            }
        }
        if (!e2.d() && e3.d()) {
            LogUtil.a("SleepUtil", "judgeDataForTwoDevice type2 ", Integer.valueOf(i2));
            nrq a2 = nrq.a();
            if (i2 == 2) {
                i3 = FitnessStatusCodes.EQUIVALENT_SESSION_ENDED;
            }
            a2.e(i3);
            if (i2 == 2) {
                objArr[0] = hiHealthData2;
                objArr[1] = 1;
            } else if (i2 == 3) {
                objArr[0] = hiHealthData2;
                objArr[1] = 3;
            } else {
                LogUtil.h("SleepUtil", "judgeDataForTwoDevice type2 else");
            }
        }
        if (!e2.d() && !e3.d()) {
            if (!e2.a()) {
                LogUtil.a("SleepUtil", "judgeDataForTwoDevice DATA_PLATFORMS_TYPE_COMMON_SLEEP_CODE");
                nrq.a().e(FitnessStatusCodes.APP_MISMATCH);
                objArr[0] = hiHealthData3;
                objArr[1] = 2;
            } else {
                c(hiHealthData, hiHealthData2, i, i2, objArr, e2, e3);
            }
        }
        if (((Integer) objArr[1]).intValue() == 1) {
            objArr[2] = list3.get(list3.size() - 1);
        } else if (((Integer) objArr[1]).intValue() == 3) {
            objArr[2] = list2.get(list2.size() - 1);
        } else {
            objArr[2] = list.get(list.size() - 1);
        }
        return objArr;
    }

    private static void c(HiHealthData hiHealthData, HiHealthData hiHealthData2, int i, int i2, Object[] objArr, qgr qgrVar, qgr qgrVar2) {
        if (qgrVar == null || qgrVar2 == null) {
            LogUtil.h("SleepUtil", "judgeOnlyNoon judge ", qgrVar, " judge2 ", qgrVar2);
            return;
        }
        if (qgrVar.c()) {
            LogUtil.a("SleepUtil", "judgeOnlyNoon DATA_PLATFORMS_TYPE_ONLY_NOON_CODE");
            nrq.a().e(FitnessStatusCodes.DATA_TYPE_NOT_FOUND);
            if (i == 1) {
                objArr[0] = hiHealthData;
                objArr[1] = 2;
                return;
            } else if (i == 3) {
                objArr[0] = hiHealthData;
                objArr[1] = 3;
                return;
            } else {
                LogUtil.h("SleepUtil", "judgeOnlyNoon type ", Integer.valueOf(i));
                return;
            }
        }
        if (qgrVar2.c()) {
            LogUtil.a("SleepUtil", "judgeOnlyNoon DATA_PLATFORMS_TYPE_PHONE_ONLY_NOON_CODE");
            nrq.a().e(FitnessStatusCodes.TRANSIENT_ERROR);
            if (i2 == 2) {
                objArr[0] = hiHealthData2;
                objArr[1] = 1;
                return;
            } else if (i2 == 3) {
                objArr[0] = hiHealthData2;
                objArr[1] = 3;
                return;
            } else {
                LogUtil.h("SleepUtil", "judgeOnlyNoon type2 ", Integer.valueOf(i2));
                return;
            }
        }
        LogUtil.h("SleepUtil", "judgeOnlyNoon else");
    }

    public static Object[] c(HiHealthData hiHealthData, HiHealthData hiHealthData2, HiHealthData hiHealthData3, List<String> list, List<String> list2, List<String> list3) {
        LogUtil.a("SleepUtil", "judgeDataForThirdDevice");
        Object[] objArr = new Object[3];
        qgr e2 = e(hiHealthData, 1);
        qgr e3 = e(hiHealthData2, 3);
        qgr e4 = e(hiHealthData3, 2);
        if (e2 == null || e3 == null || e4 == null) {
            LogUtil.h("SleepUtil", "judgeDataForThirdDevice judgeWear ", e2, " judgePillow ", e3, " judgePhone ", e4);
            return objArr;
        }
        if (e2.b() && e3.b() && e4.b()) {
            LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_NO_DATA_CODE");
            nrq.a().e(5001);
        }
        if (e2.d() && e2.a()) {
            if (e2.e()) {
                LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_INSUFFICIENT_HEART_RATE_CODE");
                nrq.a().e(5002);
            } else {
                LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_CORE_SLEEP_CODE");
                nrq.a().e(FitnessStatusCodes.UNKNOWN_AUTH_ERROR);
            }
            objArr[0] = hiHealthData;
            objArr[1] = 2;
        }
        if (!e2.d() && e3.d()) {
            LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_CORE_SLEEP_CODE");
            nrq.a().e(FitnessStatusCodes.UNKNOWN_AUTH_ERROR);
            objArr[0] = hiHealthData2;
            objArr[1] = 3;
            return objArr;
        }
        if (!e2.d() && !e3.d() && e4.d()) {
            LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_PHONE_SLEEP_CODE");
            nrq.a().e(FitnessStatusCodes.EQUIVALENT_SESSION_ENDED);
            objArr[0] = hiHealthData3;
            objArr[1] = 1;
        }
        if (!e2.d() && !e3.d() && !e4.d()) {
            if (!e2.a()) {
                LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_COMMON_SLEEP_CODE");
                nrq.a().e(FitnessStatusCodes.APP_MISMATCH);
                objArr[0] = hiHealthData;
                objArr[1] = 2;
            } else if (e2.c()) {
                LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_ONLY_NOON_CODE");
                nrq.a().e(FitnessStatusCodes.DATA_TYPE_NOT_FOUND);
                objArr[0] = hiHealthData;
                objArr[1] = 2;
            } else if (e3.c()) {
                LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_ONLY_NOON_CODE");
                nrq.a().e(FitnessStatusCodes.DATA_TYPE_NOT_FOUND);
                objArr[0] = hiHealthData2;
                objArr[1] = 3;
            } else if (e4.c()) {
                LogUtil.a("SleepUtil", "judgeDataForThirdDevice DATA_PLATFORMS_TYPE_PHONE_ONLY_NOON_CODE");
                nrq.a().e(FitnessStatusCodes.TRANSIENT_ERROR);
                objArr[0] = hiHealthData3;
                objArr[1] = 1;
            } else {
                LogUtil.a("SleepUtil", "judgeDataForThirdDevice else");
            }
        }
        if (((Integer) objArr[1]).intValue() == 1) {
            if (koq.c(list3)) {
                objArr[2] = list3.get(list3.size() - 1);
            }
        } else if (((Integer) objArr[1]).intValue() == 3) {
            if (koq.c(list2)) {
                objArr[2] = list2.get(list2.size() - 1);
            }
        } else if (koq.c(list)) {
            objArr[2] = list.get(list.size() - 1);
        }
        return objArr;
    }

    public static void b(long j2, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, HiDataClientListener hiDataClientListener) {
        HiHealthManager.d(BaseApplication.e()).fetchDataSourceByType(22100, new HiTimeInterval(j2 * 1000, ((((FitnessDataQueryDefine.a(j2, fitnessQueryId) * FitnessDataQueryDefine.a(fitnessQueryId)) * 60) + j2) * 1000) - 1), hiDataClientListener);
    }

    public static ArrayList<pwb> d(List<HiHealthClient> list, List<HiHealthClient> list2) {
        ArrayList<pwb> arrayList = new ArrayList<>();
        if (list != null) {
            for (HiHealthClient hiHealthClient : list) {
                if (hiHealthClient.getHiDeviceInfo() != null) {
                    LogUtil.a("SleepUtil", "core NAME ", LogAnonymous.e(hiHealthClient.getHiDeviceInfo().getDeviceName()), " Model ", hiHealthClient.getHiDeviceInfo().getModel(), " DeviceType ", Integer.valueOf(hiHealthClient.getHiDeviceInfo().getDeviceType()), " uuid ", hiHealthClient.getHiDeviceInfo().getDeviceUniqueCode());
                }
                pwb pwbVar = new pwb();
                if (hiHealthClient.getHiDeviceInfo() != null) {
                    int deviceType = hiHealthClient.getHiDeviceInfo().getDeviceType();
                    String deviceName = hiHealthClient.getHiDeviceInfo().getDeviceName();
                    String prodId = hiHealthClient.getHiDeviceInfo().getProdId();
                    String model = hiHealthClient.getHiDeviceInfo().getModel();
                    String deviceUniqueCode = hiHealthClient.getHiDeviceInfo().getDeviceUniqueCode();
                    if (TextUtils.isEmpty(model)) {
                        model = hiHealthClient.getHiDeviceInfo().getManufacturer();
                    }
                    if (deviceType == 30) {
                        deviceType = 32;
                    }
                    if (deviceType != 32 || !TextUtils.isEmpty(model)) {
                        if (deviceType == 51) {
                            deviceType = 23;
                        }
                        Iterator<pwb> it = arrayList.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                pwb next = it.next();
                                if (next.c() != deviceType || !c(next.e(), deviceUniqueCode)) {
                                }
                            } else if (pwbVar.c() < 10000) {
                                pwbVar.a(deviceType);
                                pwbVar.c(prodId);
                                pwbVar.b(deviceName);
                                pwbVar.d(model);
                                pwbVar.a(deviceUniqueCode);
                                if (deviceType != 32 && deviceType != 506) {
                                    pwbVar.b((Object) true);
                                }
                                arrayList.add(pwbVar);
                            }
                        }
                    }
                }
            }
        }
        if (list2 != null) {
            for (HiHealthClient hiHealthClient2 : list2) {
                if (hiHealthClient2.getHiDeviceInfo() != null) {
                    pwb pwbVar2 = new pwb();
                    LogUtil.a("SleepUtil", "normal NAME ", LogAnonymous.e(hiHealthClient2.getHiDeviceInfo().getDeviceName()), " Model ", hiHealthClient2.getHiDeviceInfo().getModel(), " DeviceType ", Integer.valueOf(hiHealthClient2.getHiDeviceInfo().getDeviceType()), " uuid ", hiHealthClient2.getHiDeviceInfo().getDeviceUniqueCode());
                    int deviceType2 = hiHealthClient2.getHiDeviceInfo().getDeviceType();
                    String deviceName2 = hiHealthClient2.getHiDeviceInfo().getDeviceName();
                    String prodId2 = hiHealthClient2.getHiDeviceInfo().getProdId();
                    String model2 = hiHealthClient2.getHiDeviceInfo().getModel();
                    String deviceUniqueCode2 = hiHealthClient2.getHiDeviceInfo().getDeviceUniqueCode();
                    pwbVar2.a(deviceType2);
                    pwbVar2.c(prodId2);
                    pwbVar2.b(deviceName2);
                    pwbVar2.d(model2);
                    pwbVar2.a(deviceUniqueCode2);
                    pwbVar2.b((Object) false);
                    arrayList.add(pwbVar2);
                }
            }
        }
        return arrayList;
    }

    private static boolean c(String str, String str2) {
        return str.length() < str2.length() ? str2.contains(str) : str.contains(str2);
    }

    public static void b(int i, List<String> list, long j2, long j3, HiAggregateListener hiAggregateListener) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        if (koq.c(list)) {
            hiAggregateOption.setDeviceUuid(list.get(list.size() - 1));
        }
        if (i == 2) {
            hiAggregateOption.setType(c);
            hiAggregateOption.setConstantsKey(d);
        } else {
            hiAggregateOption.setType(j);
            hiAggregateOption.setConstantsKey(h);
        }
        hiAggregateOption.setReadType(2);
        hiAggregateOption.setStartTime(j2);
        hiAggregateOption.setEndTime(j3);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setGroupUnitType(FitnessDataQueryDefine.c(FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC));
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthDataPro(HiDataAggregateProOption.builder().c(hiAggregateOption).c(BaseApplication.d()).c(), hiAggregateListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0292  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object[] d(com.huawei.hihealth.HiHealthData r31, com.huawei.hihealth.HiHealthData r32, java.util.List<java.lang.String> r33, java.util.List<java.lang.String> r34) {
        /*
            Method dump skipped, instructions count: 692
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qnl.d(com.huawei.hihealth.HiHealthData, com.huawei.hihealth.HiHealthData, java.util.List, java.util.List):java.lang.Object[]");
    }

    public static void e(long j2, long j3, int i, HiDataReadResultListener hiDataReadResultListener) {
        int[] iArr;
        LogUtil.a("SleepUtil", "getData startTime ", Long.valueOf(j2), " endTime ", Long.valueOf(j3));
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (i == 3) {
            iArr = e;
        } else {
            iArr = i != 4 ? null : f16495a;
        }
        if (iArr != null) {
            hiDataReadOption.setType(iArr);
        }
        hiDataReadOption.setSortOrder(0);
        hiDataReadOption.setTimeInterval(j2, j3);
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(hiDataReadOption, hiDataReadResultListener);
    }

    public static HiDataReadOption b(String str, int i, long j2, long j3, int[] iArr) {
        int[] iArr2;
        LogUtil.a("SleepUtil", "enter getOption");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (str != null && !str.equals("")) {
            hiDataReadOption.setDeviceUuid(str);
        }
        if (i == 3) {
            iArr2 = e;
        } else {
            iArr2 = i != 4 ? null : f16495a;
        }
        if (iArr2 != null) {
            hiDataReadOption.setType(iArr2);
        } else if (iArr != null) {
            hiDataReadOption.setType(iArr);
        } else {
            LogUtil.a("SleepUtil", "no types");
        }
        hiDataReadOption.setSortOrder(0);
        hiDataReadOption.setTimeInterval(j2, j3);
        hiDataReadOption.setReadType(2);
        return hiDataReadOption;
    }

    public static HiDataReadOption d(long j2, int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long e2 = jec.e(new Date(j2)) * 1000;
        hiDataReadOption.setTimeInterval(e2, ((e2 / 1000) + k.b.m) * 1000);
        hiDataReadOption.setType(new int[]{22100});
        hiDataReadOption.setCount(i);
        hiDataReadOption.setSortOrder(0);
        return hiDataReadOption;
    }

    public static List<HiDataReadProOption> a(List<HiDataReadOption> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<HiDataReadOption> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(HiDataReadProOption.builder().e(it.next()).e());
        }
        return arrayList;
    }

    public static void e(List<HiDataReadProOption> list, HiDataReadResultListener hiDataReadResultListener) {
        LogUtil.a("SleepUtil", "getDataList, list size=", Integer.valueOf(list.size()));
        HiHealthManager.d(BaseApplication.e()).readHiHealthDataEx(list, hiDataReadResultListener);
    }

    public static void c(List<HiHealthClient> list) {
        HiDeviceInfo hiDeviceInfo;
        SleepDayBarChartProvider.e.clear();
        SleepDayBarChartProvider.f10225a.clear();
        SleepDayBarChartProvider.b.clear();
        if (koq.c(list)) {
            for (HiHealthClient hiHealthClient : list) {
                if (hiHealthClient != null && (hiDeviceInfo = hiHealthClient.getHiDeviceInfo()) != null) {
                    int deviceType = hiDeviceInfo.getDeviceType();
                    String deviceUniqueCode = hiDeviceInfo.getDeviceUniqueCode();
                    if (deviceType == 506 && !SleepDayBarChartProvider.f10225a.contains(deviceUniqueCode)) {
                        SleepDayBarChartProvider.f10225a.add(deviceUniqueCode);
                    } else if (deviceType == 32 && !SleepDayBarChartProvider.e.contains(deviceUniqueCode)) {
                        SleepDayBarChartProvider.e.add(deviceUniqueCode);
                    } else if (!SleepDayBarChartProvider.b.contains(deviceUniqueCode)) {
                        SleepDayBarChartProvider.b.add(deviceUniqueCode);
                    }
                }
            }
        }
    }

    public static void d(int i) {
        String str = Integer.toString(i / 60) + " ";
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(36, true), 0, str.length(), 17);
        String str2 = BaseApplication.e().getString(R$string.IDS_messagecenter_time_hour_value) + " ";
        SpannableString spannableString2 = new SpannableString(str2);
        spannableString2.setSpan(new AbsoluteSizeSpan(16, true), 0, str2.length(), 17);
        String str3 = Integer.toString(i % 60) + " ";
        SpannableString spannableString3 = new SpannableString(str3);
        spannableString3.setSpan(new AbsoluteSizeSpan(36, true), 0, str3.length(), 17);
        String string = BaseApplication.e().getString(R$string.IDS_hw_show_main_home_page_minutes);
        SpannableString spannableString4 = new SpannableString(string);
        spannableString4.setSpan(new AbsoluteSizeSpan(16, true), 0, string.length(), 17);
        new SpannableStringBuilder().append((CharSequence) spannableString).append((CharSequence) spannableString2).append((CharSequence) spannableString3).append((CharSequence) spannableString4);
    }

    public static void c(SleepDayBarChartProvider sleepDayBarChartProvider) {
        if (!eil.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
            LogUtil.a("SleepUtil", "RecommendSwitch is close.");
            return;
        }
        Context e2 = sleepDayBarChartProvider.e();
        if (e2 == null || SleepDayBarChartProvider.c == null) {
            LogUtil.h("SleepUtil", "context is null or sleepDayBarChartProvider.mInteractor is null.");
            return;
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("sleep_score", Integer.valueOf(sleepDayBarChartProvider.d.j().ar()));
            marketingApi.triggerMarketingResourceEvent(new MarketingOption.Builder().setContext(e2).setPageId(1).setTypeId(300).setTriggerEventParams(hashMap).build());
        }
    }

    public static void c(HashMap<String, Object> hashMap, SleepDayBarChartProvider sleepDayBarChartProvider) {
        LogUtil.a("SleepUtil", "parseParams");
        synchronized (b) {
            if (sleepDayBarChartProvider != null) {
                hashMap.put("SLEEP_DAY_BAR_CHART_DEVICE_ID", Integer.valueOf(sleepDayBarChartProvider.d()));
            }
            hashMap.putAll(sleepDayBarChartProvider.i);
            if (sleepDayBarChartProvider.i != null && sleepDayBarChartProvider.d != null) {
                d(sleepDayBarChartProvider.i, "BAR_CHART_PROCESS_TEXT_VISIBILITY", Integer.MAX_VALUE);
                sleepDayBarChartProvider.d.b(sleepDayBarChartProvider.i);
            }
        }
    }

    public static void e(final Map<String, Object> map, Context context, final fdp fdpVar, final SectionBean sectionBean) {
        LogUtil.a("SleepUtil", "setCoreSleepGoToEcgTips");
        d(map, "ECG_TEXT_TRY_TEXT", context.getText(R$string.IDS_heart_rate_ecg_go_Measure));
        d(map, "ECG_TEXT_TRY_CLICK_EVENT", new OnClickSectionListener() { // from class: qnl.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                LogUtil.a("SleepUtil", "subView: " + str);
                if (str.equals("ECG_TEXT_TRY_CLICK_EVENT")) {
                    pxz.e(pxz.j() ? 2 : 1);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        d(map, "TEXT_CANCEL_CLICK", new View.OnClickListener() { // from class: qnl.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                qnl.d(map, "ECG_TIPS_LAYOUT_VISIBILITY", 8);
                fdpVar.b(map);
                sectionBean.e(fdpVar);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (!b()) {
            d(map, "ECG_TEXT_CONTENT_TEXT", context.getText(R$string.IDS_heart_rate_ecg_sleep_tips));
            d(map, "ECG_TIPS_LAYOUT_VISIBILITY", 0);
        }
        fdpVar.b(map);
    }

    private static boolean b() {
        long b2 = SharedPreferenceManager.b(String.valueOf(10000), "ECG_TIPS_LAYOUT_VISIBILITY", 0L);
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        LogUtil.a("SleepUtil", "saveTime: ", Long.valueOf(b2), " currentTime: ", Long.valueOf(timeInMillis));
        if (jdl.d(b2, timeInMillis)) {
            return true;
        }
        SharedPreferenceManager.e(String.valueOf(10000), "ECG_TIPS_LAYOUT_VISIBILITY", timeInMillis);
        return false;
    }

    public static void e(Map<String, Object> map, final Context context, fdp fdpVar) {
        LogUtil.a("SleepUtil", "setCoreSleepBtnTips");
        d(map, "CORE_SLEEP_BTN_TIPS", new IBaseResponseCallback() { // from class: qnl.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("SleepUtil", "bindParamsToView onResponse");
                if (!(obj instanceof RelativeLayout)) {
                    LogUtil.b("SleepUtil", "objData type is invalid");
                    return;
                }
                String string = context.getResources().getString(R$string.IDS_core_sleep_switch_dialog_note);
                nlg.cxS_(context, new IBaseResponseCallback() { // from class: qnl.4.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj2) {
                        LogUtil.a("SleepUtil", "onResponse");
                        if (i2 == 0) {
                            Intent intent = new Intent();
                            intent.setFlags(268435456);
                            intent.setPackage(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage());
                            intent.putExtra("core_sleep_active_open_control_btn", true);
                            intent.setClassName(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity");
                            gnm.aPB_(context, intent);
                        }
                    }
                }, (RelativeLayout) obj, string, new String[]{"core_sleep_btn_tips_do_not_remind_again", "core_sleep_btn_tips_intervals", "core_sleep_btn_tips_cnt", "core_sleep_btn_tips_if_show"});
            }
        });
        fdpVar.b(map);
    }

    public static void b(int i) {
        if (i == 3) {
            LogUtil.a("SleepUtil", "6、Data platform gets phone normal");
            nrq.a().e(FitnessStatusCodes.EQUIVALENT_SESSION_ENDED);
        } else if (i == 4) {
            LogUtil.a("SleepUtil", "8、Data platform gets pillow only normal");
            nrq.a().e(FitnessStatusCodes.API_EXCEPTION);
        } else {
            LogUtil.a("SleepUtil", "2、Data platform gets insufficient heart rate effectiveness");
            nrq.a().e(5002);
        }
    }

    public static void c(int i) {
        if (i == 3) {
            LogUtil.a("SleepUtil", "7、Data platform gets phone only noon");
            nrq.a().e(FitnessStatusCodes.TRANSIENT_ERROR);
        } else if (i == 4) {
            LogUtil.a("SleepUtil", "9、Data platform gets pillow only noon");
            nrq.a().e(FitnessStatusCodes.APP_NOT_FIT_ENABLED);
        } else {
            LogUtil.a("SleepUtil", "3、Data platform gets to only sporadic naps");
            nrq.a().e(FitnessStatusCodes.DATA_TYPE_NOT_FOUND);
        }
    }

    public static void a(int i) {
        if (i == 3) {
            LogUtil.a("SleepUtil", "6、Data platform gets phone only normal");
            nrq.a().e(FitnessStatusCodes.EQUIVALENT_SESSION_ENDED);
        } else if (i == 4) {
            LogUtil.a("SleepUtil", "8、Data platform gets pillow only normal");
            nrq.a().e(FitnessStatusCodes.API_EXCEPTION);
        } else {
            LogUtil.a("SleepUtil", "4、Data platform gets to normal scientific sleep");
            nrq.a().e(FitnessStatusCodes.UNKNOWN_AUTH_ERROR);
        }
    }

    public static void dGt_(boolean z, BroadcastReceiver broadcastReceiver, Context context, BroadcastReceiver broadcastReceiver2, BroadcastReceiver broadcastReceiver3) {
        LogUtil.a("SleepUtil", "unRegisterBroadcast !!!");
        if (z) {
            if (broadcastReceiver != null) {
                try {
                    BroadcastManagerUtil.bFJ_(context, broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                    LogUtil.a("SleepUtil", "unRegisterBroadcast exception!!!");
                    return;
                }
            }
            if (broadcastReceiver2 != null) {
                context.unregisterReceiver(broadcastReceiver2);
            }
            if (broadcastReceiver3 != null) {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver3);
            }
        }
    }

    public static void dGr_(Context context, final Handler handler) {
        LogUtil.a("SleepUtil", "initSwitchSetting");
        boolean isLogined = LoginInit.getInstance(context).getIsLogined();
        LogUtil.a("SleepUtil", "initSwitchSetting ", "isLogin = ", Boolean.valueOf(isLogined));
        if (isLogined && scn.a()) {
            LogUtil.a("SleepUtil", "hasCoreSleepDeviceConnected: true");
            jqi.a().getSwitchSetting("core_sleep_button", new IBaseResponseCallback() { // from class: qnl.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("SleepUtil", "getWearCommonSetting, coreSleep btn err_code = ", Integer.valueOf(i));
                    if (i == -1) {
                        handler.sendEmptyMessage(SleepBaseBarLineChartProvider.SHOW_RECOMMEND_SERVICE_UI);
                        return;
                    }
                    if (i == 0 && (obj instanceof String)) {
                        String str = (String) obj;
                        LogUtil.a("SleepUtil", "coreSleep btn info = ", str);
                        if ("0".equals(str)) {
                            handler.sendEmptyMessage(SleepBaseBarLineChartProvider.SHOW_RECOMMEND_SERVICE_UI);
                            return;
                        }
                        return;
                    }
                    LogUtil.a("SleepUtil", "errCode = ", Integer.valueOf(i));
                }
            });
        } else {
            LogUtil.b("SleepUtil", "currentDeviceInfo is null!");
        }
    }

    public static void d(Context context, Map<String, Object> map) {
        if (context == null) {
            ReleaseLogUtil.d("SleepUtil", "isRtlImage obj is null");
            return;
        }
        if (LanguageUtil.bc(context)) {
            BitmapDrawable cKn_ = nrz.cKn_(context, R.drawable._2131430120_res_0x7f0b0ae8);
            if (cKn_ == null) {
                d(map, "BAR_CHART_HELP_ICON", context.getDrawable(R.drawable._2131430120_res_0x7f0b0ae8));
                return;
            } else {
                d(map, "BAR_CHART_HELP_ICON", cKn_);
                return;
            }
        }
        d(map, "BAR_CHART_HELP_ICON", context.getDrawable(R.drawable._2131430120_res_0x7f0b0ae8));
    }

    public static int dGp_(SparseArray<Object> sparseArray, int i, long j2, long j3) {
        int i2 = -1;
        if (!(sparseArray.get(i) instanceof List)) {
            return -1;
        }
        for (HiHealthData hiHealthData : (List) sparseArray.get(i)) {
            LogUtil.a("SleepUtil", "scoreData: ", hiHealthData.toString());
            if (hiHealthData.getStartTime() >= j2 && hiHealthData.getStartTime() <= j3) {
                i2 = (int) hiHealthData.getValue();
            }
        }
        return i2;
    }

    public static long dGo_(SparseArray<Object> sparseArray, int i, long j2, long j3) {
        long j4;
        if (!(sparseArray.get(i) instanceof List)) {
            return 0L;
        }
        Iterator it = ((List) sparseArray.get(i)).iterator();
        long j5 = 0;
        while (true) {
            if (!it.hasNext()) {
                j4 = 0;
                break;
            }
            HiHealthData hiHealthData = (HiHealthData) it.next();
            if (hiHealthData.getStartTime() >= j2 && hiHealthData.getStartTime() <= j3) {
                j5 = (long) hiHealthData.getValue();
            } else if (hiHealthData.getStartTime() > j3 && j5 == 0) {
                j4 = (long) hiHealthData.getValue();
                break;
            }
        }
        return j5 == 0 ? j4 : j5;
    }

    public static String dGq_(SparseArray<Object> sparseArray, int i) {
        String d2 = HiDateUtil.d((String) null);
        if (!(sparseArray.get(i) instanceof List)) {
            return d2;
        }
        for (HiHealthData hiHealthData : (List) sparseArray.get(i)) {
            if (hiHealthData != null && !TextUtils.isEmpty(hiHealthData.getTimeZone())) {
                return hiHealthData.getTimeZone();
            }
        }
        return d2;
    }

    public static int dGn_(SparseArray<Object> sparseArray, int i, long j2, long j3) {
        int i2 = 0;
        if (!(sparseArray.get(i) instanceof List)) {
            return 0;
        }
        Iterator it = ((List) sparseArray.get(i)).iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HiHealthData hiHealthData = (HiHealthData) it.next();
            if (hiHealthData.getStartTime() >= j2 && hiHealthData.getStartTime() <= j3) {
                i3 = (int) hiHealthData.getValue();
            } else if (hiHealthData.getStartTime() > j3 && i3 == 0) {
                i2 = (int) hiHealthData.getValue();
                break;
            }
        }
        return i3 == 0 ? i2 : i3;
    }

    public static void dGs_(int i, Date date, Handler handler, boolean z) {
        if (jec.n(date) == jec.n(jec.e())) {
            Message obtain = Message.obtain();
            obtain.arg1 = i;
            if (i >= 100) {
                obtain.what = 24;
                handler.removeMessages(obtain.what);
                handler.sendMessageDelayed(obtain, 1000L);
            } else if (z) {
                obtain.what = 23;
                handler.sendMessage(obtain);
            }
        }
    }

    public static void d(Map<String, Object> map, String str, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        map.put(str, obj);
    }

    public static void b(Map<String, Object> map, String str) {
        if (map != null) {
            map.remove(str);
        }
    }
}
