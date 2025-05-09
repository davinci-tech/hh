package com.huawei.hihealthservice.hihealthkit;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.services.core.AMapException;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthAggregateQuery;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiHealthDataQuery;
import com.huawei.hihealth.HiHealthDataQueryOption;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.HiHealthUserPermission;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IBaseCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.TrendQuery;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.listener.HealthTrendListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import defpackage.Cint;
import defpackage.ify;
import defpackage.iip;
import defpackage.ijg;
import defpackage.ijm;
import defpackage.ijy;
import defpackage.ikf;
import defpackage.ima;
import defpackage.iox;
import defpackage.ioz;
import defpackage.ipa;
import defpackage.ipc;
import defpackage.iqt;
import defpackage.iqy;
import defpackage.iqz;
import defpackage.ira;
import defpackage.irc;
import defpackage.koq;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class HiHealthKitQueryHelper {
    private static final Map<Integer, String> e;

    /* renamed from: a, reason: collision with root package name */
    private ima f4180a;
    private String b;
    private Context c;
    private String d;

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(40002, "step");
        hashMap.put(40003, "carior");
        hashMap.put(40004, "distance");
    }

    HiHealthKitQueryHelper(Context context, String str) {
        this(context, str, null);
    }

    HiHealthKitQueryHelper(Context context, String str, String str2) {
        this.c = context.getApplicationContext();
        this.f4180a = ima.a();
        this.d = str;
        this.b = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData) {
        Map map = hiHealthKitData.getMap();
        map.put(44209, Float.valueOf(hiHealthData.getFloat("stat_core_sleep_duration_sum") + hiHealthData.getFloat("stat_core_sleep_noon_duration")));
        hiHealthKitData.setMap(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData) {
        hiHealthKitData.getMap().put(2028, Double.valueOf(ira.d(hiHealthData)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HiHealthKitData b(HiHealthData hiHealthData, InnerAggregateOption innerAggregateOption, Cint cint) {
        i resolveOption = innerAggregateOption.getResolveOption();
        HiHealthKitData hiHealthKitData = new HiHealthKitData();
        b(hiHealthData, hiHealthKitData, cint.e());
        hiHealthKitData.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
        hiHealthKitData.setMap(c(hiHealthData, resolveOption));
        hiHealthKitData.setType(resolveOption.d);
        hiHealthKitData.putString("metadata", hiHealthData.getMetaData());
        hiHealthKitData.putLong("update_time", hiHealthData.getModifiedTime());
        return hiHealthKitData;
    }

    private static HashMap c(HiHealthData hiHealthData, i iVar) {
        int[] iArr = iVar.f4193a;
        String[] strArr = iVar.e;
        int[] iArr2 = iVar.b;
        HashMap hashMap = new HashMap(16);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr2[i2];
            if (i3 == 0) {
                hashMap.put(Integer.valueOf(iArr[i2]), Integer.valueOf(hiHealthData.getInt(strArr[i2])));
            } else if (i3 == 1) {
                hashMap.put(Integer.valueOf(iArr[i2]), Long.valueOf(hiHealthData.getLong(strArr[i2])));
            } else if (i3 == 2) {
                hashMap.put(Integer.valueOf(iArr[i2]), Float.valueOf(hiHealthData.getFloat(strArr[i2])));
            } else if (i3 == 3) {
                hashMap.put(Integer.valueOf(iArr[i2]), Double.valueOf(hiHealthData.getDouble(strArr[i2])));
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(IDataReadResultListener iDataReadResultListener, int i2, List list, int i3, boolean z) {
        try {
            if (z) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(Integer.valueOf(list.size()));
                iDataReadResultListener.onResult(arrayList, i2, i3);
            } else {
                iDataReadResultListener.onResult(list, i2, i3);
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("R_HiHlthKitQryHlp", "notifyDataReadResult RemoteException", LogAnonymous.b((Throwable) e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData, boolean z) {
        String string = hiHealthData.getString("device_uniquecode");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String string2 = hiHealthData.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        String string3 = hiHealthData.getString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL);
        String string4 = hiHealthData.getString("trackdata_deviceType");
        if (!z) {
            hiHealthKitData.putString("device_uniquecode", "***");
        } else {
            hiHealthKitData.putString("device_uniquecode", string);
        }
        hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, string2);
        hiHealthKitData.putString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, string3);
        hiHealthKitData.putString("deviceType", e(string4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(HiHealthData hiHealthData, HiHealthKitData hiHealthKitData) {
        if (ipc.c(hiHealthData.getType())) {
            hiHealthKitData.setDoubleValue(Double.valueOf(hiHealthData.getValue()));
        } else if (ipc.d(hiHealthData.getType())) {
            hiHealthKitData.putString("metadata", hiHealthData.getMetaData());
        } else {
            hiHealthKitData.setValue(hiHealthData.getIntValue());
        }
    }

    protected void d(int i2, IDataOperateListener iDataOperateListener) throws RemoteException {
        int a2 = iip.b().a(this.d);
        LogUtil.a("HiHlthKitQryHlp", "getDataAuthStatus appId:", Integer.valueOf(a2));
        HiHealthUserPermission e2 = ijm.e(this.c).e(a2, i2);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(e2.getAllowWrite()));
        iDataOperateListener.onResult(0, arrayList);
    }

    protected void c(int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        int a2 = iip.b().a(this.d);
        LogUtil.a("HiHlthKitQryHlp", "getDataAuthStatusEx appId:", Integer.valueOf(a2));
        List<HiHealthUserPermission> b2 = ijm.e(this.c).b(a2);
        int[] iArr3 = new int[iArr == null ? 0 : iArr.length];
        int[] iArr4 = new int[iArr2 == null ? 0 : iArr2.length];
        for (int i2 = 0; iArr != null && i2 < iArr.length; i2++) {
            Iterator<HiHealthUserPermission> it = b2.iterator();
            while (true) {
                if (it.hasNext()) {
                    HiHealthUserPermission next = it.next();
                    if (iArr[i2] == next.getScopeId()) {
                        iArr3[i2] = next.getAllowWrite();
                        break;
                    }
                }
            }
        }
        for (int i3 = 0; iArr2 != null && i3 < iArr2.length; i3++) {
            Iterator<HiHealthUserPermission> it2 = b2.iterator();
            while (true) {
                if (it2.hasNext()) {
                    HiHealthUserPermission next2 = it2.next();
                    if (iArr2[i3] == next2.getScopeId()) {
                        iArr4[i3] = next2.getAllowRead();
                        break;
                    }
                }
            }
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("writeTypes", iArr3);
        hashMap.put("readTypes", iArr4);
        ReleaseLogUtil.b("R_HiHlthKitQryHlp", "getDataAuthStatusEx: ", HiJsonUtil.e(hashMap));
        iBaseCallback.onResult(0, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ICommonListener iCommonListener, int i2) throws RemoteException {
        if (i2 < 0 && i2 != -1) {
            iCommonListener.onFailure(4, null);
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(i2));
        iCommonListener.onSuccess(0, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ICommonListener iCommonListener, float f2) throws RemoteException {
        ArrayList arrayList = new ArrayList(1);
        if (f2 < 0.0f && f2 != -100.0f) {
            iCommonListener.onFailure(4, null);
            return;
        }
        if (f2 == -100.0f) {
            arrayList.add(Float.valueOf(0.0f));
        } else {
            arrayList.add(Float.valueOf(f2));
        }
        iCommonListener.onSuccess(0, arrayList);
    }

    void c(final irc ircVar, final ICommonListener iCommonListener, final String str) throws RemoteException {
        if (iCommonListener == null || str == null) {
            if (iCommonListener != null) {
                iCommonListener.onFailure(3, null);
                e(ircVar, null, 3, null);
            }
            LogUtil.h("HiHlthKitQryHlp", "param is null");
            return;
        }
        ify.e().c((ICommonListener) new ICommonListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.2
            @Override // com.huawei.hihealth.ICommonListener
            public void onSuccess(int i2, List list) throws RemoteException {
                if (koq.b(list)) {
                    LogUtil.h("HiHlthKitQryHlp", "userInfo is null");
                    return;
                }
                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                int c2 = HiHealthKitQueryHelper.this.c(hiUserInfo, str);
                if ("getWeight".equals(str)) {
                    HiHealthKitQueryHelper.this.e(iCommonListener, hiUserInfo != null ? hiUserInfo.getWeight() : 0.0f);
                } else {
                    HiHealthKitQueryHelper.this.e(iCommonListener, c2);
                }
                HiHealthKitQueryHelper.this.e(ircVar, str, 0, null);
                LogUtil.a("HiHlthKitQryHlp", "HiHealthApiManager getUserInfo onSuccess errorCode = ", 0);
            }

            @Override // com.huawei.hihealth.ICommonListener
            public void onFailure(int i2, List list) throws RemoteException {
                int b2 = iox.b(i2);
                iCommonListener.onFailure(b2, list);
                HiHealthKitQueryHelper.this.e(ircVar, str, b2, null);
                if (list != null && list.size() > 0) {
                    LogUtil.a("HiHlthKitQryHlp", "fetchUserData fail, errMsg = ", list.get(0));
                }
                LogUtil.a("HiHlthKitQryHlp", "HiHealthApiManager getUserInfo onSuccess errorCode = ", Integer.valueOf(i2));
            }
        }, false);
    }

    protected void d(HiHealthAggregateQuery hiHealthAggregateQuery, IDataReadResultListener iDataReadResultListener, Cint cint) {
        if (hiHealthAggregateQuery.getSampleType() != 10009) {
            return;
        }
        a(new IrregularHeartBeatQueryOption(hiHealthAggregateQuery), iDataReadResultListener, cint);
    }

    protected void c(HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener, Cint cint) throws RemoteException {
        if (ipa.a(hiHealthDataQuery.getSampleType())) {
            e(iDataReadResultListener, hiHealthDataQuery.getSampleType(), cint);
        }
        if (hiHealthDataQuery.getSampleType() == 44000) {
            a(d(hiHealthDataQuery, false), iDataReadResultListener, cint);
            return;
        }
        if (hiHealthDataQuery.getSampleType() == 15002) {
            LogUtil.a("HiHlthKitQryHlp", "enter query goal step");
            a(iDataReadResultListener, 2, cint);
            return;
        }
        LogUtil.a("HiHlthKitQryHlp", "getHiHealthData");
        switch (AnonymousClass10.f4182a[HiHealthDataType.e(hiHealthDataQuery.getSampleType()).ordinal()]) {
            case 1:
            case 2:
            case 3:
                d(hiHealthDataQuery, iDataReadResultListener, cint);
                break;
            case 4:
                if (hiHealthDataQuery.getSampleType() == 10002) {
                    a(hiHealthDataQuery, iDataReadResultListener, cint);
                    break;
                } else {
                    a(d(hiHealthDataQuery, false), iDataReadResultListener, cint);
                    break;
                }
            case 5:
                b(hiHealthDataQuery, iDataReadResultListener, cint);
                break;
            case 6:
                e(hiHealthDataQuery, iDataReadResultListener, cint);
                break;
            default:
                LogUtil.h("HiHlthKitQryHlp", "invalid sample type");
                iDataReadResultListener.onResult(null, 2, 2);
                break;
        }
    }

    /* renamed from: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4182a;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            f4182a = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4182a[HiHealthDataType.Category.STAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4182a[HiHealthDataType.Category.BUSINESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4182a[HiHealthDataType.Category.SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4182a[HiHealthDataType.Category.SEQUENCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4182a[HiHealthDataType.Category.SESSION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void a(IDataReadResultListener iDataReadResultListener, int i2, Cint cint) {
        int j = this.f4180a.j();
        irc ircVar = new irc();
        if (j <= 0) {
            LogUtil.h("HiHlthKitQryHlp", "queryConfig who <= 0");
            e(iDataReadResultListener, 4, Collections.emptyList(), 2, false);
            d(ircVar, cint.b(), 4, cint.d());
            return;
        }
        List<HiGoalInfo> c2 = ijg.d().c(j, i2);
        if (koq.b(c2)) {
            e(iDataReadResultListener, 0, Collections.EMPTY_LIST, 2, false);
            d(ircVar, cint.b(), 0, cint.d());
        } else {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(e(c2, i2));
            e(iDataReadResultListener, 0, (List) arrayList, 2, false);
            d(ircVar, cint.b(), 0, cint.d());
        }
    }

    private HiHealthKitData e(List<HiGoalInfo> list, int i2) {
        int size = list.size();
        HiHealthKitData hiHealthKitData = new HiHealthKitData();
        hiHealthKitData.setType(i2);
        HashMap hashMap = new HashMap(size);
        for (HiGoalInfo hiGoalInfo : list) {
            hashMap.put(2901, Double.valueOf(hiGoalInfo.getGoalValue()));
            hashMap.put(2903, Integer.valueOf(hiGoalInfo.getDealLine()));
            hashMap.put(2902, Integer.valueOf(hiGoalInfo.getGoalUnit()));
            hashMap.put(2904, Integer.valueOf(hiGoalInfo.getTargetType()));
        }
        hiHealthKitData.setMap(hashMap);
        return hiHealthKitData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(HiUserInfo hiUserInfo, String str) {
        char c2;
        int birthday;
        if (hiUserInfo == null) {
            return 0;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1147691021) {
            if (str.equals("getBirthday")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 446502327) {
            if (hashCode == 474985501 && str.equals("getHeight")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("getGender")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 != 0) {
            if (c2 != 1) {
                if (c2 == 2) {
                    return hiUserInfo.getHeight();
                }
                LogUtil.b("HiHlthKitQryHlp", "getBasicUserFeatyre unrecognized dataType");
                return 0;
            }
            if (hiUserInfo.getGender() == -100) {
                return -1;
            }
            birthday = hiUserInfo.getGender();
        } else {
            if (hiUserInfo.getBirthday() == -100) {
                return 0;
            }
            birthday = hiUserInfo.getBirthday();
        }
        return birthday;
    }

    private void a(final HiHealthDataQuery hiHealthDataQuery, final IDataReadResultListener iDataReadResultListener, final Cint cint) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final IDataReadResultListener.Stub stub = new IDataReadResultListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.5
            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i2, int i3) throws RemoteException {
                arrayList2.addAll(list);
                if (i3 == 2) {
                    int i4 = 0;
                    for (List list2 : arrayList) {
                        if (i4 == arrayList.size() - 1) {
                            iDataReadResultListener.onResult(HiHealthKitQueryHelper.this.d((List<HiHealthKitData>) list2, (List<HiHealthKitData>) arrayList2), i2, 2);
                        } else {
                            iDataReadResultListener.onResult(HiHealthKitQueryHelper.this.d((List<HiHealthKitData>) list2, (List<HiHealthKitData>) arrayList2), i2, 0);
                        }
                        i4++;
                    }
                }
            }
        };
        a(d(hiHealthDataQuery, true), new IDataReadResultListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.1
            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i2, int i3) throws RemoteException {
                if (cint.a()) {
                    iDataReadResultListener.onResult(list, i2, i3);
                    return;
                }
                arrayList.add(list);
                if (i3 == 2) {
                    if (i2 == 0) {
                        HiHealthKitQueryHelper hiHealthKitQueryHelper = HiHealthKitQueryHelper.this;
                        hiHealthKitQueryHelper.a(hiHealthKitQueryHelper.d(hiHealthDataQuery, false), stub, cint);
                    } else {
                        iDataReadResultListener.onResult(list, i2, i3);
                    }
                }
            }
        }, cint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthKitData> d(List<HiHealthKitData> list, List<HiHealthKitData> list2) {
        if (list2 != null && list != null) {
            for (HiHealthKitData hiHealthKitData : list) {
                Map map = hiHealthKitData.getMap();
                Iterator<HiHealthKitData> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    HiHealthKitData next = it.next();
                    if (hiHealthKitData.getStartTime() == next.getStartTime() && hiHealthKitData.getEndTime() == next.getEndTime()) {
                        map.putAll(next.getMap());
                        break;
                    }
                }
                if (Double.valueOf(String.valueOf(map.get(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value())))).doubleValue() > 0.0d) {
                    map.put(2018, map.get(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value())));
                }
                map.remove(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()));
            }
        }
        return list;
    }

    private void e(IDataReadResultListener iDataReadResultListener, int i2, Cint cint) {
        int j = this.f4180a.j();
        irc ircVar = new irc();
        if (j <= 0) {
            LogUtil.h("HiHlthKitQryHlp", "queryConfig who <= 0");
            e(iDataReadResultListener, 4, Collections.emptyList(), 2, false);
            e(ircVar, cint.b(), 4, cint.d());
            return;
        }
        HiUserPreference a2 = ijy.c(this.c).a(j, ipa.c(i2));
        if (a2 == null) {
            e(iDataReadResultListener, 0, Collections.EMPTY_LIST, 2, false);
            e(ircVar, cint.b(), 0, cint.d());
        } else {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(c(a2, i2));
            e(iDataReadResultListener, 0, (List) arrayList, 2, false);
            e(ircVar, cint.b(), 0, cint.d());
        }
    }

    private HiHealthKitData c(HiUserPreference hiUserPreference, int i2) {
        HiHealthKitData hiHealthKitData = new HiHealthKitData();
        hiHealthKitData.setType(i2);
        hiHealthKitData.setTimeInterval(hiUserPreference.getModifiedTime(), hiUserPreference.getModifiedTime());
        HashMap hashMap = new HashMap(1);
        hashMap.put(Integer.valueOf(i2), hiUserPreference.getValue());
        hiHealthKitData.setMap(hashMap);
        hiHealthKitData.putLong("update_time", hiUserPreference.getModifiedTime());
        return hiHealthKitData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InnerAggregateOption d(HiHealthDataQuery hiHealthDataQuery, boolean z) {
        if (HiHealthDataType.m(hiHealthDataQuery.getSampleType())) {
            return new MenstrualQueryOption(hiHealthDataQuery);
        }
        if (hiHealthDataQuery.getSampleType() == DicDataTypeUtil.DataType.OSA_SET.value()) {
            return new OsaQueryOption(hiHealthDataQuery);
        }
        int sampleType = hiHealthDataQuery.getSampleType();
        if (sampleType == 10001) {
            return new BloodSugarQueryOption(hiHealthDataQuery);
        }
        if (sampleType == 10002) {
            return new BloodPressureQueryOption(hiHealthDataQuery, z);
        }
        if (sampleType != 44000) {
            switch (sampleType) {
                case 10006:
                    return new WeightSetQueryOption(hiHealthDataQuery);
                case 10007:
                    return new CoreSleepQueryOption(hiHealthDataQuery);
                case 10008:
                    return new HeartRateQueryOption(hiHealthDataQuery);
                case 10009:
                    return new IrregularHeartBeatQueryOption(hiHealthDataQuery);
                default:
                    return null;
            }
        }
        return new SleepQueryOption(hiHealthDataQuery);
    }

    private void b(HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener, Cint cint) {
        IDataReadResultListener aVar;
        int sampleType = hiHealthDataQuery.getSampleType();
        if (sampleType == 30029) {
            hiHealthDataQuery.setSampleType(30002);
        }
        InnerReadOption innerReadOption = new InnerReadOption(hiHealthDataQuery);
        if (HiHealthDataType.q(sampleType)) {
            aVar = new f(iDataReadResultListener, hiHealthDataQuery, cint);
        } else if (HiHealthDataType.n(sampleType)) {
            aVar = new a(iDataReadResultListener, cint, hiHealthDataQuery);
        } else {
            LogUtil.b("HiHlthKitQryHlp", "querySequenceData, unknown type");
            return;
        }
        try {
            c(innerReadOption, aVar, cint);
        } catch (RemoteException e2) {
            ReleaseLogUtil.b("R_HiHlthKitQryHlp", "queryPointData Remote Exception", LogAnonymous.b((Throwable) e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(InnerAggregateOption innerAggregateOption, IDataReadResultListener iDataReadResultListener, Cint cint) {
        try {
            d(innerAggregateOption, new c(iDataReadResultListener, innerAggregateOption, cint), cint);
        } catch (RemoteException e2) {
            ReleaseLogUtil.b("R_HiHlthKitQryHlp", "RemoteException", LogAnonymous.b((Throwable) e2));
        }
    }

    private void d(HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener, Cint cint) {
        try {
            c(new InnerReadOption(hiHealthDataQuery), new d(iDataReadResultListener, cint, hiHealthDataQuery), cint);
        } catch (RemoteException e2) {
            ReleaseLogUtil.b("R_HiHlthKitQryHlp", "queryPointData Remote Exception", LogAnonymous.b((Throwable) e2));
        }
    }

    private void e(HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener, Cint cint) {
        InnerReadOption innerReadOption = new InnerReadOption(hiHealthDataQuery);
        HiDataReadResultListener eVar = new e(iDataReadResultListener, cint);
        if (hiHealthDataQuery.getSampleType() == 22104) {
            eVar = new h(iDataReadResultListener, cint);
        }
        HiHealthManager.d(this.c).readHiHealthData(innerReadOption, eVar);
    }

    private void c(HiDataReadOption hiDataReadOption, final IDataReadResultListener iDataReadResultListener, final Cint cint) throws RemoteException {
        if (iDataReadResultListener == null) {
            LogUtil.a("HiHlthKitQryHlp", "listener is null!");
            return;
        }
        LogUtil.a("HiHlthKitQryHlp", "readHiHealthData");
        final irc ircVar = new irc();
        ify.e().e(hiDataReadOption, new IDataReadResultListener() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.4
            @Override // android.os.IInterface
            public IBinder asBinder() {
                return null;
            }

            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i2, int i3) throws RemoteException {
                LogUtil.a("HiHlthKitQryHlp", "HiHealthApiManager readHiHealthData errorCode = ", Integer.valueOf(i2));
                int b2 = iox.b(i2);
                if (i3 == 2) {
                    HiHealthKitQueryHelper.this.e(ircVar, cint.b(), b2, cint.d());
                }
                iDataReadResultListener.onResult(list, b2, i3);
            }
        }, false);
    }

    private void d(HiAggregateOption hiAggregateOption, final IAggregateListener iAggregateListener, final Cint cint) throws RemoteException {
        if (iAggregateListener == null) {
            LogUtil.a("HiHlthKitQryHlp", "listener is null!");
        } else {
            final irc ircVar = new irc();
            ify.e().e(hiAggregateOption, new IAggregateListener() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.3
                @Override // android.os.IInterface
                public IBinder asBinder() {
                    return null;
                }

                @Override // com.huawei.hihealth.IAggregateListener
                public void onResult(List list, int i2, int i3) throws RemoteException {
                    LogUtil.a("HiHlthKitQryHlp", "HiHealthApiManager aggregateHiHealthData errorCode = ", Integer.valueOf(i2));
                    int b2 = iox.b(i2);
                    if (i3 == 2) {
                        HiHealthKitQueryHelper.this.e(ircVar, cint.b(), b2, cint.d());
                    }
                    iAggregateListener.onResult(list, b2, i3);
                }
            }, false);
        }
    }

    private void d(irc ircVar, String str, int i2, String str2) {
        if (iqt.b()) {
            return;
        }
        e(ircVar, str, i2, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(irc ircVar, String str, int i2, String str2) {
        ircVar.c(this.c, new iqy(str, i2, this.d, this.b, str2));
    }

    static class MenstrualQueryOption extends InnerAggregateOption {
        MenstrualQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            this.type = new int[]{hiHealthDataQuery.getSampleType()};
            this.constantsKey = new String[]{"menstrual"};
            int[] d = HiHealthDataType.d(10010);
            String[] b = HiHealthDataKey.b(10010);
            int[] iArr = new int[d.length];
            Arrays.fill(iArr, 0);
            super.setResolveOption(new i(d, b, iArr, 10010));
            super.setAggregateType(1);
            super.setGroupUnitType(0);
        }
    }

    static class IrregularHeartBeatQueryOption extends InnerAggregateOption {
        IrregularHeartBeatQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            this.type = new int[]{AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR};
            this.constantsKey = new String[]{"ppg"};
            int[] iArr = new int[this.type.length];
            Arrays.fill(iArr, 0);
            super.setResolveOption(new i(this.type, this.constantsKey, iArr, AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR));
        }

        IrregularHeartBeatQueryOption(HiHealthAggregateQuery hiHealthAggregateQuery) {
            super(hiHealthAggregateQuery);
            this.type = HiHealthDataType.g();
            this.constantsKey = HiHealthDataKey.c();
            int[] iArr = new int[this.type.length];
            Arrays.fill(iArr, 0);
            super.setResolveOption(new i(this.type, this.constantsKey, iArr, 10009));
        }
    }

    static class OsaQueryOption extends InnerAggregateOption {
        OsaQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            this.type = new int[]{DicDataTypeUtil.DataType.OSA_LEVEL.value(), DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()};
            this.constantsKey = new String[]{"osaLevel", "osaAvgCntPerHour"};
            int[] iArr = new int[this.type.length];
            iArr[0] = 0;
            iArr[1] = 3;
            super.setResolveOption(new i(this.type, this.constantsKey, iArr, DicDataTypeUtil.DataType.OSA_SET.value()));
            super.setGroupUnitType(0);
            super.setAggregateType(4);
            super.setReadType(0);
        }
    }

    static class WeightSetQueryOption extends InnerAggregateOption {
        WeightSetQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            this.type = new int[]{10006};
            this.constantsKey = new String[]{BleConstants.WEIGHT_KEY};
            int[] iArr = {2004, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, Constants.START_TO_MAIN_ACTIVITY, 2033, 2053, 2076, 2077, 2078, 2079, 2080, 2081, 2083, 2084, 2085, 2086, 2087, 2088};
            String[] strArr = {"bodyWeight", BleConstants.BMI, BleConstants.MUSCLE_MASS, BleConstants.BASAL_METABOLISM, BleConstants.MOISTURE, BleConstants.VISCERAL_FAT_LEVEL, BleConstants.BONE_SALT, "protein", BleConstants.BODY_SCORE, BleConstants.BODY_AGE, BleConstants.BODY_FAT_RATE, BleConstants.IMPEDANCE, BleConstants.MOISTURE_RATE, "skeletalMusclelMass", "lhrhImpedance", "lhlfImpedance", "lhrfImpedance", "rhlfImpedance", "rhrfImpedance", "lfrfImpedance", "lhrhHfImpedance", "lhlfHfImpedance", "lhrfHfImpedance", "rhlfHfImpedance", "rhrfHfImpedance", "lfrfHfImpedance"};
            int[] iArr2 = new int[26];
            Arrays.fill(iArr2, 3);
            super.setResolveOption(new i(iArr, strArr, iArr2, 10006));
            super.setAggregateType(1);
            super.setGroupUnitType(0);
        }
    }

    static class HeartRateQueryOption extends InnerAggregateOption {
        HeartRateQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            this.type = new int[]{46016, 46017, 46018, 47052, 47053, 47002, 47003};
            this.constantsKey = new String[]{"maxHeartRate", "minHeartRate", "avgRestingHeartRate", "heart_rate_bradycardia_min", "heart_rate_bradycardia_max", "heart_rate_rise_min", "heart_rate_rise_max"};
            int[] iArr = new int[this.type.length];
            Arrays.fill(iArr, 2);
            super.setResolveOption(new i(this.type, this.constantsKey, iArr, 10008));
            super.setAggregateType(1);
            super.setGroupUnitType(0);
            super.setReadType(0);
        }
    }

    static class BloodSugarQueryOption extends InnerAggregateOption {
        BloodSugarQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            this.type = HiHealthDataType.d(10001);
            this.constantsKey = new String[this.type.length];
            for (int i = 0; i < this.type.length; i++) {
                this.constantsKey[i] = HiHealthDataKey.c(this.type[i]);
            }
            int[] iArr = new int[this.type.length];
            Arrays.fill(iArr, 3);
            super.setResolveOption(new i(this.type, this.constantsKey, iArr, 10001));
            super.setAggregateType(1);
            super.setGroupUnitType(0);
            super.setReadType(0);
        }
    }

    static class BloodPressureQueryOption extends InnerAggregateOption {
        BloodPressureQueryOption(HiHealthDataQuery hiHealthDataQuery, boolean z) {
            super(hiHealthDataQuery);
            if (z) {
                this.type = new int[]{2006, 2007, DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()};
                this.constantsKey = new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC", "sphygmus_new"};
            } else {
                this.type = new int[]{2018};
                this.constantsKey = new String[]{BleConstants.BLOODPRESSURE_SPHYGMUS};
            }
            int[] iArr = new int[this.type.length];
            Arrays.fill(iArr, 3);
            super.setResolveOption(new i(this.type, this.constantsKey, iArr, 10002));
            super.setAggregateType(1);
            super.setGroupUnitType(0);
            super.setReadType(0);
        }
    }

    static class CoreSleepQueryOption extends InnerAggregateOption {
        CoreSleepQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            this.type = HiHealthDataType.a();
            this.constantsKey = HiHealthDataKey.b();
            int[] iArr = {44101, 44102, 44103, 44105, 44106, 44107, 44108, 44110, 44207, 44104, 44109, 44206, 44204, 44208, 44201, 44202, 44203};
            String[] strArr = {"stat_core_sleep_dream_duration", "stat_core_sleep_deep_duration", "stat_core_sleep_shallow_duration", "stat_core_sleep_duration_sum", "stat_core_sleep_deep_part_count", "stat_core_sleep_wake_count", "stat_core_sleep_noon_duration", "stat_core_sleep_device_category", "stat_out_core_sleep_efficiency", "stat_core_sleep_wake_duration", "stat_core_sleep_bed_duration", "stat_out_core_sleep_valid_data", "stat_out_core_sleep_latency", "stat_out_core_sleep_snore_freq", "stat_out_core_sleep_fall_time", "stat_out_core_sleep_wake_up_time", "stat_out_core_sleep_score"};
            int[] iArr2 = new int[17];
            Arrays.fill(iArr2, 2);
            for (int i = 14; i < 17; i++) {
                iArr2[i] = 1;
            }
            super.setResolveOption(new i(iArr, strArr, iArr2, 10007));
            super.setGroupUnitType(3);
            super.setAggregateType(1);
            super.setGroupUnitSize(1);
            super.setReadType(0);
        }
    }

    static class SleepQueryOption extends InnerAggregateOption {
        SleepQueryOption(HiHealthDataQuery hiHealthDataQuery) {
            super(hiHealthDataQuery);
            super.setStartTime(hiHealthDataQuery.getStartTime());
            super.setEndTime(hiHealthDataQuery.getEndTime());
            this.type = new int[]{44006, 44007, 44004, 44001, 44002, 44003, 44005};
            this.constantsKey = new String[]{"mStartTime", "mEndTime", "totalDuration", "deepDuration", "lightDuration", "awakeDuration", "awakeTimes"};
            int[] iArr = {44006, 44007, 44004, 44001, 44002, 44003, 44005};
            String[] strArr = {"mStartTime", "mEndTime", "totalDuration", "deepDuration", "lightDuration", "awakeDuration", "awakeTimes"};
            int[] iArr2 = new int[7];
            Arrays.fill(iArr2, 2);
            for (int i = 0; i <= 1; i++) {
                iArr2[i] = 1;
            }
            super.setResolveOption(new i(iArr, strArr, iArr2, 44000));
            super.setGroupUnitType(3);
            super.setAggregateType(1);
            super.setGroupUnitSize(1);
            super.setReadType(0);
        }
    }

    static class InnerAggregateOption extends HiAggregateOption {
        i resolveOption;

        InnerAggregateOption(HiHealthDataQuery hiHealthDataQuery) {
            super.setTimeInterval(hiHealthDataQuery.getStartTime(), hiHealthDataQuery.getEndTime());
            HiHealthDataQueryOption hiHealthDataQueryOption = hiHealthDataQuery.getHiHealthDataQueryOption();
            if (hiHealthDataQueryOption != null) {
                super.setSortOrder(hiHealthDataQueryOption.getOrder());
                super.setAnchor(hiHealthDataQueryOption.getOffset());
                super.setCount(hiHealthDataQueryOption.getLimit());
            }
        }

        InnerAggregateOption(HiHealthAggregateQuery hiHealthAggregateQuery) {
            super.setTimeInterval(hiHealthAggregateQuery.getStartTime(), hiHealthAggregateQuery.getEndTime());
            super.setAggregateType(hiHealthAggregateQuery.getAggregateType());
            super.setGroupUnitSize(hiHealthAggregateQuery.getGroupUnitSize());
            super.setGroupUnitType(hiHealthAggregateQuery.getGroupUnitType());
            super.setSortOrder(hiHealthAggregateQuery.getOrder());
            super.setAnchor(hiHealthAggregateQuery.getOffset());
            super.setCount(hiHealthAggregateQuery.getLimit());
            super.setDeviceUuid(hiHealthAggregateQuery.getDeviceUuid());
        }

        i getResolveOption() {
            return this.resolveOption;
        }

        void setResolveOption(i iVar) {
            this.resolveOption = iVar;
        }
    }

    static class InnerReadOption extends HiDataReadOption {
        InnerReadOption(HiHealthDataQuery hiHealthDataQuery) {
            super.setType(HiHealthKitQueryHelper.c(hiHealthDataQuery.getSampleType()));
            super.setStartTime(hiHealthDataQuery.getStartTime());
            super.setEndTime(hiHealthDataQuery.getEndTime());
            HiHealthDataQueryOption hiHealthDataQueryOption = hiHealthDataQuery.getHiHealthDataQueryOption();
            if (hiHealthDataQueryOption != null) {
                super.setSortOrder(hiHealthDataQueryOption.getOrder());
                super.setAnchor(hiHealthDataQueryOption.getOffset());
                super.setCount(hiHealthDataQueryOption.getLimit());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] c(int i2) {
        return i2 == HiHealthDataType.b ? new int[]{HiHealthDataType.b, 2104} : new int[]{i2};
    }

    static class i {

        /* renamed from: a, reason: collision with root package name */
        int[] f4193a;
        int[] b;
        int d;
        String[] e;

        i(int[] iArr, String[] strArr, int[] iArr2, int i) {
            this.f4193a = iArr;
            this.e = strArr;
            this.b = iArr2;
            this.d = i;
        }
    }

    static class c extends IAggregateListener.Stub {

        /* renamed from: a, reason: collision with root package name */
        IDataReadResultListener f4189a;
        InnerAggregateOption c;
        Cint d;
        List e = new ArrayList(10);
        List b = new ArrayList(10);

        c(IDataReadResultListener iDataReadResultListener, InnerAggregateOption innerAggregateOption, Cint cint) {
            this.f4189a = iDataReadResultListener;
            this.c = innerAggregateOption;
            this.d = cint;
        }

        @Override // com.huawei.hihealth.IAggregateListener
        public void onResult(List list, int i, int i2) throws RemoteException {
            InnerAggregateOption innerAggregateOption;
            if (this.f4189a == null || (innerAggregateOption = this.c) == null || innerAggregateOption.getResolveOption() == null) {
                LogUtil.b("HiHlthKitQryHlp", "SetDataReadResultProxy, null param");
            } else if (iqz.c(this.d.c()) <= 2) {
                e(list, i, i2);
            } else {
                d(list, i, i2);
            }
        }

        private void d(List list, int i, int i2) {
            if (list == null) {
                LogUtil.a("HiHlthKitQryHlp", "SetDataReadResultProxy, return null errorCode: ", Integer.valueOf(i));
                HiHealthKitQueryHelper.e(this.f4189a, i, Collections.EMPTY_LIST, i2, this.d.a());
            } else {
                ArrayList arrayList = new ArrayList();
                d(list, arrayList);
                HiHealthKitQueryHelper.e(this.f4189a, i, arrayList, i2, this.d.a());
            }
        }

        private void d(List list, List list2) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthData hiHealthData = (HiHealthData) it.next();
                HiHealthKitData b = HiHealthKitQueryHelper.b(hiHealthData, this.c, this.d);
                if (this.c.getResolveOption().d == 10007) {
                    HiHealthKitQueryHelper.b(hiHealthData, b);
                }
                if (this.c.getResolveOption().d == 10006) {
                    HiHealthKitQueryHelper.a(hiHealthData, b);
                }
                list2.add(b);
            }
            LogUtil.a("HiHlthKitQryHlp", "SetDataReadResultProxy, return type: ", Integer.valueOf(this.c.getResolveOption().d), " dataSize: ", Integer.valueOf(list2.size()));
        }

        private void e(List list, int i, int i2) throws RemoteException {
            if (HiDivideUtil.c(list, i2, this.e)) {
                List list2 = this.e;
                if (list2 == null) {
                    LogUtil.a("HiHlthKitQryHlp", "SetDataReadResultProxy, return null errorCode: ", Integer.valueOf(i));
                    this.f4189a.onResult(null, i, i2);
                    return;
                }
                d(list2, this.b);
                if (list != null || i == 0 || i2 != 2) {
                    HiHealthKitQueryHelper.e(this.f4189a, i, this.b, i2, this.d.a());
                    return;
                }
                try {
                    this.f4189a.onResult(null, i, i2);
                } catch (RemoteException e) {
                    ReleaseLogUtil.c("R_HiHlthKitQryHlp", "SetDataReadResultProxy RemoteException", LogAnonymous.b((Throwable) e));
                }
            }
        }
    }

    static class f extends IDataReadResultListener.Stub {

        /* renamed from: a, reason: collision with root package name */
        int f4191a = 0;
        int b;
        IDataReadResultListener d;
        Cint e;

        f(IDataReadResultListener iDataReadResultListener, HiHealthDataQuery hiHealthDataQuery, Cint cint) {
            this.d = iDataReadResultListener;
            this.b = hiHealthDataQuery.getSampleType();
            this.e = cint;
        }

        @Override // com.huawei.hihealth.IDataReadResultListener
        public void onResult(List list, int i, int i2) {
            LogUtil.a("HiHlthKitQryHlp", "enter queryMetaData onResult");
            if (list != null) {
                ArrayList arrayList = new ArrayList(10);
                LogUtil.a("HiHlthKitQryHlp", "trackMetaData size =", Integer.valueOf(list.size()));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(c((HiHealthData) it.next()));
                }
                this.f4191a += list.size();
                e(i, i2, arrayList);
                return;
            }
            b(i, i2);
        }

        private void b(int i, int i2) {
            LogUtil.a("HiHlthKitQryHlp", "queryMetaData datas == null");
            try {
                if (iqz.c(this.e.c()) > 2) {
                    this.d.onResult(null, i, i2);
                    return;
                }
                if (i2 == 2) {
                    if (i != 0) {
                        this.d.onResult(null, i, i2);
                    } else if (this.e.a()) {
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(Integer.valueOf(this.f4191a));
                        this.d.onResult(arrayList, i, i2);
                    }
                }
            } catch (RemoteException e) {
                ReleaseLogUtil.c("R_HiHlthKitQryHlp", "TrackMetaDataReadResultProxy RemoteException", LogAnonymous.b((Throwable) e));
            }
        }

        private void e(int i, int i2, List list) {
            if (!this.e.a()) {
                HiHealthKitQueryHelper.e(this.d, i, list, i2, this.e.a());
            } else if (iqz.c(this.e.c()) > 2) {
                HiHealthKitQueryHelper.e(this.d, i, list, i2, this.e.a());
            }
        }

        private HiHealthKitData c(HiHealthData hiHealthData) {
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            HiHealthKitQueryHelper.b(hiHealthData, hiHealthKitData, this.e.e());
            hiHealthKitData.setType(hiHealthData.getType());
            hiHealthKitData.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
            hiHealthKitData.putLong("update_time", hiHealthData.getModifiedTime());
            HashMap hashMap = new HashMap(16);
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
            if (hiTrackMetaData == null) {
                return hiHealthKitData;
            }
            LogUtil.a("HiHlthKitQryHlp", "resolveTrackMetaData: sampleType = ", Integer.valueOf(this.b));
            if (this.b == 30002 && hiTrackMetaData.getSportType() == 283) {
                hiHealthKitData.setType(30029);
                e(hashMap, hiTrackMetaData);
                hiHealthKitData.setMap(hashMap);
                return hiHealthKitData;
            }
            hashMap.put("total_distance", Integer.valueOf(hiTrackMetaData.getTotalDistance()));
            hashMap.put("total_time", Long.valueOf(hiTrackMetaData.getTotalTime()));
            hashMap.put("total_calories", Integer.valueOf(hiTrackMetaData.getTotalCalories()));
            hashMap.put("average_heart_rate", Integer.valueOf(hiTrackMetaData.getAvgHeartRate()));
            hashMap.put("date", Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
            hashMap.put("average_pace", Float.valueOf(hiTrackMetaData.getAvgPace()));
            hashMap.put("data_source", Integer.valueOf(ioz.a(hiTrackMetaData.getSportDataSource())));
            if (hiTrackMetaData.getTotalTime() <= 0) {
                hashMap.put("average_speed", Double.valueOf(0.0d));
            } else {
                hashMap.put("average_speed", Double.valueOf(hiTrackMetaData.getTotalDistance() / hiTrackMetaData.getTotalTime()));
            }
            if (this.b != 30007) {
                hashMap.put("average_step_rate", Integer.valueOf(hiTrackMetaData.getAvgStepRate()));
                hashMap.put("step", Integer.valueOf(hiTrackMetaData.getTotalSteps()));
                hashMap.put("total_altitude", Float.valueOf(hiTrackMetaData.getCreepingWave()));
                hashMap.put("total_descent", Float.valueOf(hiTrackMetaData.getTotalDescent()));
                if (hiTrackMetaData.getTotalSteps() <= 0) {
                    hashMap.put("step_distance", Double.valueOf(0.0d));
                } else {
                    hashMap.put("step_distance", Double.valueOf(hiTrackMetaData.getTotalDistance() / hiTrackMetaData.getTotalSteps()));
                }
            }
            hiHealthKitData.setMap(hashMap);
            return hiHealthKitData;
        }

        private void e(Map map, HiTrackMetaData hiTrackMetaData) {
            LogUtil.a("HiHlthKitQryHlp", "parseRopeSkippingMetaData");
            map.put(BleConstants.SPORT_TYPE, Integer.valueOf(hiTrackMetaData.getSportType()));
            map.put("totalTime", Long.valueOf(hiTrackMetaData.getTotalTime()));
            map.put(BleConstants.TOTAL_CALORIES, Integer.valueOf(hiTrackMetaData.getTotalCalories()));
            Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
            map.put("skipNum", extendTrackMap.get("skipNum"));
            map.put("skipSpeed", extendTrackMap.get("skipSpeed"));
            map.put("stumblingRope", extendTrackMap.get("stumblingRope"));
            map.put("maxSkippingTimes", extendTrackMap.get("maxSkippingTimes"));
        }
    }

    static class a extends IDataReadResultListener.Stub {

        /* renamed from: a, reason: collision with root package name */
        long f4188a;
        IDataReadResultListener b;
        Cint c;
        final List<List> d = new ArrayList(10);
        boolean e;
        int g;

        a(IDataReadResultListener iDataReadResultListener, Cint cint, HiHealthDataQuery hiHealthDataQuery) {
            this.b = iDataReadResultListener;
            this.g = hiHealthDataQuery.getSampleType();
            this.c = cint;
            this.e = hiHealthDataQuery.getSampleType() != 31001;
        }

        @Override // com.huawei.hihealth.IDataReadResultListener
        public void onResult(List list, int i, int i2) {
            LogUtil.a("HiHlthKitQryHlp", "SequenceDataReadResultProxy onResult errorCode:", Integer.valueOf(i), " resultType:", Integer.valueOf(i2));
            if (iqz.c(this.c.c()) <= 2) {
                a(list, i, i2);
            } else {
                d(list, i, i2);
            }
        }

        private void d(List list, int i, int i2) {
            if (list != null && list.size() > 0) {
                List<HiHealthKitData> c = c(list);
                d(c);
                LogUtil.a("HiHlthKitQryHlp", "SequenceDataReadResultProxy return sequenceData size:", Integer.valueOf(c.size()));
                HiHealthKitQueryHelper.e(this.b, 0, c, i2, this.c.a());
                return;
            }
            LogUtil.b("HiHlthKitQryHlp", "SequenceDataReadResultProxy return empty list, errCode:", Integer.valueOf(i));
            HiHealthKitQueryHelper.e(this.b, i, Collections.EMPTY_LIST, i2, this.c.a());
        }

        private List<HiHealthKitData> c(List list) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthData hiHealthData = (HiHealthData) it.next();
                this.f4188a += hiHealthData.getSequenceData() == null ? 0L : hiHealthData.getSequenceData().length();
                arrayList.add(c(hiHealthData));
            }
            return arrayList;
        }

        private void a(List list, int i, int i2) {
            if (a(list, i2)) {
                if (this.d.size() > 0) {
                    if (this.e) {
                        List<HiHealthKitData> c = c();
                        d(c);
                        LogUtil.a("HiHlthKitQryHlp", "SequenceDataReadResultProxy return metaData size:", Integer.valueOf(c.size()));
                        HiHealthKitQueryHelper.e(this.b, 0, c, 1, this.c.a());
                        return;
                    }
                    e();
                    for (List list2 : this.d) {
                        d(list2);
                        LogUtil.a("HiHlthKitQryHlp", "SequenceDataReadResultProxy return sequenceData size:", Integer.valueOf(list2.size()));
                        HiHealthKitQueryHelper.e(this.b, 0, list2, 0, this.c.a());
                    }
                    LogUtil.a("HiHlthKitQryHlp", "SequenceDataReadResultProxy return sequenceData over");
                    HiHealthKitQueryHelper.e(this.b, 0, new ArrayList(0), 1, this.c.a());
                    return;
                }
                if (i == this.g) {
                    LogUtil.a("HiHlthKitQryHlp", "SequenceDataReadResultProxy return empty list, errorCode 0");
                    HiHealthKitQueryHelper.e(this.b, 0, new ArrayList(0), 1, this.c.a());
                } else {
                    LogUtil.b("HiHlthKitQryHlp", "SequenceDataReadResultProxy return empty list, errCode:", Integer.valueOf(i));
                    HiHealthKitQueryHelper.e(this.b, i, new ArrayList(0), 1, this.c.a());
                }
            }
        }

        private void e() {
            LogUtil.a("HiHlthKitQryHlp", "enter packageSequenceDivideData, and allData size = ", Integer.valueOf(this.d.size()));
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<List> it = this.d.iterator();
            while (it.hasNext()) {
                Map map = a(it.next()).getMap();
                if (map.containsKey("detail_data")) {
                    stringBuffer.append(map.get("detail_data"));
                }
                if (map.get("meta_data") == null) {
                    it.remove();
                } else {
                    map.remove("detail_data");
                    LogUtil.a("HiHlthKitQryHlp", "detail data size = ", Integer.valueOf(stringBuffer.length()));
                    map.put("detail_data", stringBuffer.toString());
                }
            }
        }

        private HiHealthKitData a(List list) {
            if (!HiCommonUtil.d(list)) {
                Object obj = list.get(0);
                if (obj instanceof HiHealthKitData) {
                    return (HiHealthKitData) obj;
                }
            }
            LogUtil.a("HiHlthKitQryHlp", "List is null or class cast exception");
            return new HiHealthKitData();
        }

        private List<HiHealthKitData> c() {
            ArrayList arrayList = new ArrayList(10);
            Iterator<List> it = this.d.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next());
            }
            return arrayList;
        }

        private HiHealthKitData c(HiHealthData hiHealthData) {
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            HiHealthKitQueryHelper.b(hiHealthData, hiHealthKitData, this.c.e());
            hiHealthKitData.setType(hiHealthData.getType());
            hiHealthKitData.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
            hiHealthKitData.putLong("update_time", hiHealthData.getModifiedTime());
            HashMap hashMap = new HashMap(16);
            hashMap.put("meta_data", hiHealthData.getMetaData());
            if (hiHealthData.getSequenceData() != null) {
                hashMap.put("detail_data", hiHealthData.getSequenceData());
            }
            hashMap.put("simple_data", hiHealthData.getSimpleData());
            String string = hiHealthData.getString("interpret_data");
            if (!TextUtils.isEmpty(string)) {
                hashMap.put("interpret_data", string);
            }
            hashMap.put("data_id", String.valueOf(hiHealthData.getDataId()));
            hiHealthKitData.setMap(hashMap);
            return hiHealthKitData;
        }

        private void d(List<HiHealthKitData> list) {
            LogUtil.a("HiHlthKitQryHlp", "SequenceDataReadResultProxy updateSizeInfo available:", Long.valueOf(this.f4188a));
            Iterator<HiHealthKitData> it = list.iterator();
            while (it.hasNext()) {
                it.next().putLong("size", this.f4188a);
            }
        }

        private boolean a(List list, int i) {
            if (list != null && !list.isEmpty()) {
                ArrayList arrayList = new ArrayList(list.size());
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    HiHealthData hiHealthData = (HiHealthData) it.next();
                    this.f4188a += hiHealthData.getSequenceData() == null ? 0L : hiHealthData.getSequenceData().length();
                    arrayList.add(c(hiHealthData));
                }
                this.d.add(arrayList);
            }
            return i == 2;
        }
    }

    static class d extends IDataReadResultListener.Stub {

        /* renamed from: a, reason: collision with root package name */
        String f4190a;
        Cint b;
        boolean d;
        boolean e;
        IDataReadResultListener i;
        int j;
        HiHealthKitData m;
        int o = 0;
        int g = 0;
        int h = 0;
        int f = 0;
        boolean c = true;

        d(IDataReadResultListener iDataReadResultListener, Cint cint, HiHealthDataQuery hiHealthDataQuery) {
            this.i = iDataReadResultListener;
            this.b = cint;
            this.j = hiHealthDataQuery.getSampleType();
            c(hiHealthDataQuery);
        }

        private void c(HiHealthDataQuery hiHealthDataQuery) {
            HiHealthDataQueryOption hiHealthDataQueryOption = hiHealthDataQuery.getHiHealthDataQueryOption();
            if (hiHealthDataQueryOption != null) {
                this.g = hiHealthDataQueryOption.getOrder();
                this.h = hiHealthDataQueryOption.getOffset();
                this.f = hiHealthDataQueryOption.getLimit();
            }
            long t = HiDateUtil.t(System.currentTimeMillis());
            this.d = hiHealthDataQuery.getEndTime() >= t && HiHealthKitQueryHelper.e.containsKey(Integer.valueOf(hiHealthDataQuery.getSampleType()));
            this.e = hiHealthDataQuery.getStartTime() >= t;
            if (this.d) {
                String str = (String) HiHealthKitQueryHelper.e.get(Integer.valueOf(hiHealthDataQuery.getSampleType()));
                this.f4190a = str;
                this.m = HiHealthKitQueryHelper.b(str);
                if (this.e) {
                    LogUtil.a("HiHlthKitQryHlp", "query sport data only today");
                    return;
                }
                hiHealthDataQuery.setEndTime(t - 1);
                if (this.h <= 0 || this.f <= 0 || this.g != 1) {
                    return;
                }
                HiHealthKitData hiHealthKitData = this.m;
                if (hiHealthKitData != null && hiHealthKitData.getIntValue() > 0) {
                    hiHealthDataQueryOption.setOffset(this.h - 1);
                }
                this.d = false;
            }
        }

        @Override // com.huawei.hihealth.IDataReadResultListener
        public void onResult(List list, int i, int i2) {
            LogUtil.a("HiHlthKitQryHlp", "PointDataReadResultProxy, onResult");
            if (list != null) {
                ArrayList arrayList = new ArrayList(10);
                LogUtil.a("HiHlthKitQryHlp", "PointDataReadResultProxy, size =", Integer.valueOf(list.size()));
                for (Object obj : list) {
                    if (obj != null) {
                        arrayList.add(b((HiHealthData) obj));
                    } else {
                        LogUtil.b("HiHlthKitQryHlp", "PointDataReadResultProxy, pointData obj null");
                    }
                }
                a(i, i2, arrayList);
                return;
            }
            c(i, i2);
        }

        private HiHealthKitData b(HiHealthData hiHealthData) {
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            if (this.j == HiHealthDataType.b) {
                hiHealthKitData.setType(this.j);
            } else {
                hiHealthKitData.setType(hiHealthData.getType());
            }
            HiHealthKitQueryHelper.b(hiHealthData, hiHealthKitData, this.b.e());
            HiHealthKitQueryHelper.i(hiHealthData, hiHealthKitData);
            hiHealthKitData.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
            hiHealthKitData.putLong("update_time", hiHealthData.getModifiedTime());
            return hiHealthKitData;
        }

        private void c(int i, int i2) {
            LogUtil.a("HiHlthKitQryHlp", "PointDataReadResultProxy, datas == null");
            try {
                if (iqz.c(this.b.c()) > 2) {
                    this.i.onResult(null, i, i2);
                    return;
                }
                if (i2 == 2) {
                    if (i != 0) {
                        this.i.onResult(null, i, i2);
                    } else if (this.b.a()) {
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(Integer.valueOf(this.o));
                        this.i.onResult(arrayList, i, i2);
                    }
                }
            } catch (RemoteException e) {
                ReleaseLogUtil.c("R_HiHlthKitQryHlp", "PointDataReadResultProxy RemoteException", LogAnonymous.b((Throwable) e));
            }
        }

        private void a(int i, int i2, List list) {
            this.o += list.size();
            a(i2, list);
            if (!this.b.a()) {
                HiHealthKitQueryHelper.e(this.i, i, list, i2, this.b.a());
            } else if (iqz.c(this.b.c()) > 2) {
                HiHealthKitQueryHelper.e(this.i, i, list, i2, this.b.a());
            }
        }

        private void a(int i, List list) {
            if (this.d) {
                HiHealthKitData hiHealthKitData = this.m;
                if (hiHealthKitData == null || hiHealthKitData.getIntValue() <= 0) {
                    LogUtil.h("HiHlthKitQryHlp", "today sport data is invalid");
                    return;
                }
                LogUtil.a("HiHlthKitQryHlp", "start deal today sport data");
                if (!this.e) {
                    if (this.g == 0 && i == 1) {
                        LogUtil.a("HiHlthKitQryHlp", "dealTodaySportData isAddEnd");
                        list.add(this.m);
                        this.o++;
                    }
                    if (this.g == 1 && this.c) {
                        this.c = false;
                        LogUtil.a("HiHlthKitQryHlp", "dealTodaySportData isAddStart");
                        list.add(0, this.m);
                        this.o++;
                    }
                    int i2 = this.f;
                    if (i2 <= 0 || this.o <= i2) {
                        return;
                    }
                    LogUtil.a("HiHlthKitQryHlp", "dealTodaySportData removeLast");
                    list.remove(list.size() - 1);
                    this.o--;
                    return;
                }
                if (list.isEmpty()) {
                    this.o = 1;
                }
                list.clear();
                list.add(this.m);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HiHealthKitData b(final String str) {
        final g gVar = new g();
        final b bVar = new b();
        final HealthOpenSDK healthOpenSDK = new HealthOpenSDK();
        long t = HiDateUtil.t(System.currentTimeMillis());
        final HiHealthKitData hiHealthKitData = new HiHealthKitData();
        hiHealthKitData.setStartTime(t);
        hiHealthKitData.setEndTime(t);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        healthOpenSDK.initSDK(BaseApplication.getContext(), new IExecuteResult() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.7
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                LogUtil.a("HiHlthKitQryHlp", "initSDK Success");
                HiHealthKitQueryHelper.b(HiHealthKitData.this, gVar, bVar, healthOpenSDK, str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
                LogUtil.b("HiHlthKitQryHlp", "initSDK failed");
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
                LogUtil.b("HiHlthKitQryHlp", "initSDK exception");
                countDownLatch.countDown();
            }
        }, "Health Kit");
        try {
            LogUtil.a("HiHlthKitQryHlp", "query today sport data count down result : ", Boolean.valueOf(countDownLatch.await(3000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("R_HiHlthKitQryHlp", "query today sport data process is interruputed", LogAnonymous.b((Throwable) e2));
        }
        healthOpenSDK.destorySDK();
        return hiHealthKitData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final HiHealthKitData hiHealthKitData, g gVar, b bVar, final HealthOpenSDK healthOpenSDK, final String str) {
        healthOpenSDK.b(gVar, bVar);
        LogUtil.a("HiHlthKitQryHlp", "register stepCallback success");
        healthOpenSDK.d(new IExecuteResult() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.6
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                if (obj == null) {
                    LogUtil.h("HiHlthKitQryHlp", "step notification result is null");
                } else {
                    HealthOpenSDK.this.b(new IExecuteResult() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitQueryHelper.6.3
                        @Override // com.huawei.hihealth.motion.IExecuteResult
                        public void onSuccess(Object obj2) {
                            if (obj2 instanceof Bundle) {
                                int i2 = ((Bundle) obj2).getInt(str, -1);
                                LogUtil.a("HiHlthKitQryHlp", "get today ", str, " success");
                                if (i2 > -1) {
                                    hiHealthKitData.setValue(i2);
                                    return;
                                } else {
                                    LogUtil.h("HiHlthKitQryHlp", "bundle is not containsKey ", str);
                                    return;
                                }
                            }
                            LogUtil.h("HiHlthKitQryHlp", "get sport data success, but object is not instance of bundle", obj2);
                        }

                        @Override // com.huawei.hihealth.motion.IExecuteResult
                        public void onFailed(Object obj2) {
                            LogUtil.b("HiHlthKitQryHlp", "failed to get today sport data", obj2);
                        }

                        @Override // com.huawei.hihealth.motion.IExecuteResult
                        public void onServiceException(Object obj2) {
                            LogUtil.b("HiHlthKitQryHlp", "service error when get today sport data", obj2);
                        }
                    });
                }
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
                LogUtil.b("HiHlthKitQryHlp", "get steps notification state failed", obj);
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
                LogUtil.b("HiHlthKitQryHlp", "steps notification service error", obj);
            }
        });
    }

    static class b implements IExecuteResult {
        private b() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("HiHlthKitQryHlp", "onSuccess");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.b("HiHlthKitQryHlp", "onFailed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.b("HiHlthKitQryHlp", "onServiceException");
        }
    }

    static class g implements ICommonReport {
        @Override // com.huawei.hihealth.motion.ICommonReport
        public void report(Bundle bundle) {
        }

        private g() {
        }
    }

    class h implements HiDataReadResultListener {
        IDataReadResultListener b;
        Cint c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        h(IDataReadResultListener iDataReadResultListener, Cint cint) {
            this.b = iDataReadResultListener;
            this.c = cint;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            irc ircVar = new irc();
            int b = iox.b(i);
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray == null || sparseArray.size() <= 0) {
                HiHealthKitQueryHelper.e(this.b, 0, (List) null, 2, false);
                HiHealthKitQueryHelper.this.e(ircVar, this.c.b(), b, this.c.d());
            } else {
                d((List) sparseArray.get(22104), ircVar, b);
            }
        }

        private void d(List<HiHealthData> list, irc ircVar, int i) {
            if (koq.b(list)) {
                HiHealthKitQueryHelper.e(this.b, i, Collections.EMPTY_LIST, 2, false);
                HiHealthKitQueryHelper.this.e(ircVar, this.c.b(), i, this.c.d());
                return;
            }
            ArrayList arrayList = new ArrayList(10);
            long startTime = list.get(0).getStartTime();
            long endTime = list.get(0).getEndTime();
            for (HiHealthData hiHealthData : list) {
                long startTime2 = hiHealthData.getStartTime();
                long endTime2 = hiHealthData.getEndTime();
                if (startTime - endTime2 > 60000) {
                    HiHealthKitData hiHealthKitData = new HiHealthKitData();
                    hiHealthKitData.setStartTime(startTime);
                    hiHealthKitData.setEndTime(endTime);
                    arrayList.add(hiHealthKitData);
                    endTime = endTime2;
                }
                startTime = startTime2;
            }
            HiHealthData hiHealthData2 = list.get(list.size() - 1);
            HiHealthKitData hiHealthKitData2 = new HiHealthKitData();
            hiHealthKitData2.setStartTime(hiHealthData2.getStartTime());
            hiHealthKitData2.setEndTime(endTime);
            arrayList.add(hiHealthKitData2);
            HiHealthKitQueryHelper.e(this.b, i, (List) arrayList, 2, false);
            HiHealthKitQueryHelper.this.e(ircVar, this.c.b(), i, this.c.d());
        }
    }

    class e implements HiDataReadResultListener {
        IDataReadResultListener d;
        Cint e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        e(IDataReadResultListener iDataReadResultListener, Cint cint) {
            this.d = iDataReadResultListener;
            this.e = cint;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            irc ircVar = new irc();
            int b = iox.b(i);
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray == null || sparseArray.size() <= 0) {
                HiHealthKitQueryHelper.e(this.d, 0, (List) null, 2, false);
                HiHealthKitQueryHelper.this.e(ircVar, this.e.b(), b, this.e.d());
                LogUtil.h("HiHlthKitQryHlp", "map is null or size=0");
                return;
            }
            ArrayList arrayList = new ArrayList(10);
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                if (sparseArray.get(sparseArray.keyAt(i3)) instanceof List) {
                    for (HiHealthData hiHealthData : (List) sparseArray.get(sparseArray.keyAt(i3))) {
                        HiHealthKitData hiHealthKitData = new HiHealthKitData();
                        hiHealthKitData.setStartTime(hiHealthData.getStartTime());
                        hiHealthKitData.setEndTime(hiHealthData.getEndTime());
                        hiHealthKitData.putLong("update_time", hiHealthData.getModifiedTime());
                        hiHealthKitData.setType(hiHealthData.getType());
                        HiHealthKitQueryHelper.b(hiHealthData, hiHealthKitData, this.e.e());
                        arrayList.add(hiHealthKitData);
                    }
                } else {
                    LogUtil.h("HiHlthKitQryHlp", "map value is not List<?> type");
                }
            }
            if (iqz.c(this.e.c()) <= 3) {
                HiHealthKitQueryHelper.e(this.d, 0, arrayList, 2, this.e.a());
                return;
            }
            try {
                HiDivideUtil.d(i, arrayList, this.d);
                HiHealthKitQueryHelper.e(this.d, 0, (List) null, 2, this.e.a());
            } catch (RemoteException e) {
                ReleaseLogUtil.c("R_HiHlthKitQryHlp", "RemoteException: ", e.getMessage(), " in SessionProxy");
            }
        }
    }

    public static String e(String str) {
        List<ProductMapInfo> b2 = ProductMap.b("deviceId", str);
        return koq.c(b2) ? b2.get(0).e() : "";
    }

    protected void e(TrendQuery trendQuery, int i2, final IDataReadResultListener iDataReadResultListener) {
        ReleaseLogUtil.b("R_HiHlthKitQryHlp", "enter queryTrends");
        List<String> c2 = ira.c(trendQuery.getDataTypes());
        int[] trendPeriods = trendQuery.getTrendPeriods();
        String recordDay = trendQuery.getRecordDay();
        if (TextUtils.isEmpty(recordDay)) {
            ikf.a(c2, trendPeriods, i2, new HealthTrendListener() { // from class: inn
                @Override // com.huawei.hihealth.data.listener.HealthTrendListener
                public final void onResult(int i3, List list) {
                    HiHealthKitQueryHelper.a(IDataReadResultListener.this, i3, list);
                }
            }, trendQuery.getIsForce());
        } else {
            ikf.c(c2, trendPeriods, i2, new HealthTrendListener() { // from class: inm
                @Override // com.huawei.hihealth.data.listener.HealthTrendListener
                public final void onResult(int i3, List list) {
                    HiHealthKitQueryHelper.b(IDataReadResultListener.this, i3, list);
                }
            }, recordDay);
        }
    }

    public static /* synthetic */ void a(IDataReadResultListener iDataReadResultListener, int i2, List list) {
        ReleaseLogUtil.b("R_HiHlthKitQryHlp", "queryTrends resultCode: ", Integer.valueOf(i2), ", data size = ", Integer.valueOf(list.size()));
        e(iDataReadResultListener, i2, list, 2, false);
    }

    public static /* synthetic */ void b(IDataReadResultListener iDataReadResultListener, int i2, List list) {
        ReleaseLogUtil.b("R_HiHlthKitQryHlp", "queryTrends resultCode: ", Integer.valueOf(i2), ", data size = ", Integer.valueOf(list.size()));
        e(iDataReadResultListener, i2, list, 2, false);
    }
}
