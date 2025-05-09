package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.util.ArrayUtils;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HealthDataStatPolicy;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictStat;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.TrackSimilarityIdentifier;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import defpackage.ifl;
import defpackage.igo;
import defpackage.iiz;
import defpackage.ijd;
import defpackage.ijj;
import defpackage.ijt;
import defpackage.ikr;
import defpackage.iks;
import defpackage.ivu;
import defpackage.iwg;
import defpackage.iwp;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HiDicHealthDataStat {
    private static final Map<String, Integer> c;
    private Context b;
    private List<Integer> d;
    private int f;
    private HiHealthDictManager g;
    private ijt h;
    private ijj i;
    private iiz j;
    private long k;
    private long l;
    private int m;
    private long n;
    private int o;
    private TimeZone p;
    private int r;
    private int t;
    public static final Integer[] e = {42011, 42003, Integer.valueOf(Constants.REBACK_MARKET_RESULT_CODE), Integer.valueOf(Constants.REBACK_MARKET_ENTRANCE), 42005, 42008, 42009, 42010, 42006, 42012, 42013};

    /* renamed from: a, reason: collision with root package name */
    public static final Integer[] f4210a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 25, 24, 22, 21, 26};

    private boolean a(int i) {
        return i == 3 || i == 4 || i == 5 || i == 6;
    }

    static {
        ArrayMap arrayMap = new ArrayMap(10);
        c = arrayMap;
        arrayMap.put("MAX", 4);
        arrayMap.put("MIN", 5);
        arrayMap.put(HealthDataStatPolicy.AVG, 3);
        arrayMap.put(HealthDataStatPolicy.SUM, 1);
        arrayMap.put(HealthDataStatPolicy.COUNT, 2);
        arrayMap.put(HealthDataStatPolicy.SD, 7);
    }

    public HiDicHealthDataStat(Context context) {
        this.b = context;
    }

    private boolean a(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("HiH_HiDicHealthDataStat", "healthData is null");
            return false;
        }
        this.r = hiHealthData.getUserId();
        int e2 = ikr.b(this.b).e(0, this.r, 0);
        this.o = e2;
        if (e2 <= 0) {
            LogUtil.h("HiH_HiDicHealthDataStat", "mStatClient <= 0");
            return false;
        }
        List<Integer> a2 = iks.e().a(this.r);
        this.d = a2;
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("HiH_HiDicHealthDataStat", "mClients is null or empty");
            return false;
        }
        this.p = TimeZone.getDefault();
        this.l = HiDateUtil.e(hiHealthData.getStartTime(), this.p);
        this.k = HiDateUtil.b(hiHealthData.getStartTime(), this.p);
        this.n = HiDateUtil.d(hiHealthData.getStartTime(), this.p, -1);
        this.f = HiDateUtil.a(this.l, this.p);
        this.t = hiHealthData.getType();
        this.m = hiHealthData.getSubType();
        this.h = ivu.b(this.b, this.t);
        this.i = ivu.d(this.b, this.t);
        this.j = iiz.a(this.b);
        this.g = HiHealthDictManager.d(this.b);
        return true;
    }

    public boolean c(HiHealthData hiHealthData) {
        if (!a(hiHealthData)) {
            LogUtil.h("HiH_HiDicHealthDataStat", "initalize failed!");
            return false;
        }
        ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "execute stat in type:", Integer.valueOf(hiHealthData.getType()), "startTime:", Long.valueOf(hiHealthData.getStartTime()));
        int i = AnonymousClass1.b[HiHealthDataType.e(hiHealthData.getType()).ordinal()];
        if (i == 1) {
            HiHealthDictField b = this.g.b(this.t);
            if (b == null) {
                LogUtil.h("HiH_HiDicHealthDataStat", "healthDictField is null, health type is ", Integer.valueOf(this.t));
                return false;
            }
            if (HiCommonUtil.d(b.d())) {
                LogUtil.h("HiH_HiDicHealthDataStat", "statPolicys is empty!");
                return false;
            }
            Iterator<HiHealthDictStat> it = b.d().iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        } else if (i == 2) {
            iiz iizVar = this.j;
            List<Integer> list = this.d;
            long j = this.l;
            long j2 = this.k;
            int i2 = this.t;
            int i3 = this.m;
            List<String> a2 = iizVar.a(list, j, j2, i2 - i3, i3);
            if (HiCommonUtil.d(a2)) {
                ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "sequenceMetaDatas is null or empty!");
                List<String> m = HiHealthDictManager.d(this.b).m(this.t);
                ArrayList arrayList = new ArrayList(Arrays.asList(f4210a));
                Iterator<String> it2 = m.iterator();
                while (it2.hasNext()) {
                    arrayList.add(Integer.valueOf(HiHealthDictManager.d(this.b).d(this.t, it2.next(), 0)));
                }
                igo igoVar = new igo();
                igoVar.c(this.m);
                igoVar.b(this.o);
                igoVar.e(this.f);
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    igoVar.d(((Integer) it3.next()).intValue());
                    ijd.c(this.b).b(igoVar);
                }
            }
            try {
                List<HiHealthDictField> j3 = this.g.j(hiHealthData.getType());
                if (HiCommonUtil.d(j3)) {
                    ReleaseLogUtil.d("HiH_HiDicHealthDataStat", "dictFields is null or empty!");
                    return false;
                }
                ArrayList arrayList2 = new ArrayList(10);
                for (String str : a2) {
                    if (str != null) {
                        JSONObject jSONObject = new JSONObject(str);
                        for (HiHealthDictField hiHealthDictField : j3) {
                            HashMap hashMap = new HashMap(1);
                            if (jSONObject.has(hiHealthDictField.a()) && HiCommonUtil.e(String.valueOf(jSONObject.get(hiHealthDictField.a())))) {
                                hashMap.put(hiHealthDictField.a(), Double.valueOf(jSONObject.getDouble(hiHealthDictField.a())));
                                arrayList2.add(hashMap);
                            }
                            if (jSONObject.has(BleConstants.EXTEND_TRACK_DATA_MAP)) {
                                JSONObject jSONObject2 = new JSONObject(jSONObject.getString(BleConstants.EXTEND_TRACK_DATA_MAP));
                                if (jSONObject2.has(hiHealthDictField.a()) && HiCommonUtil.e(String.valueOf(jSONObject2.get(hiHealthDictField.a())))) {
                                    hashMap.put(hiHealthDictField.a(), Double.valueOf(jSONObject2.getDouble(hiHealthDictField.a())));
                                    arrayList2.add(hashMap);
                                }
                            }
                        }
                    }
                }
                if (!HiCommonUtil.d(arrayList2)) {
                    for (HiHealthDictField hiHealthDictField2 : j3) {
                        if (hiHealthDictField2 != null && !HiCommonUtil.d(hiHealthDictField2.d())) {
                            Iterator<HiHealthDictStat> it4 = hiHealthDictField2.d().iterator();
                            while (it4.hasNext()) {
                                e(it4.next(), hiHealthDictField2, arrayList2);
                            }
                        }
                        ReleaseLogUtil.d("HiH_HiDicHealthDataStat", "dictField is null or statPolicies is empty!");
                    }
                }
                b(hiHealthData);
            } catch (JSONException e2) {
                ReleaseLogUtil.d("HiH_HiDicHealthDataStat", "stat JSONException!, e is ", e2.getMessage());
                return false;
            }
        } else {
            ReleaseLogUtil.d("HiH_HiDicHealthDataStat", "This type is not supported stat, type is ", Integer.valueOf(hiHealthData.getType()));
        }
        return true;
    }

    /* renamed from: com.huawei.hihealthservice.store.stat.HiDicHealthDataStat$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            b = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[HiHealthDataType.Category.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void b(HiHealthData hiHealthData) {
        if (hiHealthData.getSubType() == 0) {
            return;
        }
        try {
            if (this.l >= HiDateUtil.e(System.currentTimeMillis(), this.p) - 31104000000L) {
                a(this.b, this.j.d(this.d, this.n, this.k, 30001));
            }
            HashMap hashMap = new HashMap(16);
            List<String> a2 = this.j.a(this.d, this.l, this.k, 30001);
            int i = 0;
            for (String str : a2) {
                if (str != null) {
                    JSONObject jSONObject = new JSONObject(str);
                    if (a(CommonUtil.d(jSONObject, "trackType"))) {
                        i++;
                    }
                    int d = CommonUtil.d(jSONObject, BleConstants.SPORT_TYPE);
                    List list = (List) hashMap.get(String.valueOf(d));
                    if (HiCommonUtil.d(list)) {
                        list = new ArrayList(10);
                    }
                    list.add(str);
                    hashMap.put(String.valueOf(d), list);
                }
            }
            hiHealthData.setSyncStatus(0);
            b(hiHealthData, 30001, a2, e, true);
            if (i > 0) {
                c(HiDateUtil.a(hiHealthData.getStartTime(), this.p), 30001, 43500, i, hiHealthData.getSyncStatus(), 16);
            }
            if (hashMap.isEmpty()) {
                return;
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                HiHealthDictType d2 = this.g.d(hiHealthData.getType());
                if (d2 != null) {
                    b(hiHealthData, CommonUtils.h((String) entry.getKey()), (List) entry.getValue(), ArrayUtils.toWrapperArray(d2.a()), false);
                }
            }
        } catch (JSONException e2) {
            ReleaseLogUtil.d("HiH_HiDicHealthDataStat", "processSportSequenceStat JSONException!, e is ", e2.getMessage());
        }
    }

    private void b(HiHealthData hiHealthData, int i, List<String> list, Integer[] numArr, boolean z) {
        String str;
        Integer[] numArr2;
        String str2;
        char c2;
        int i2;
        int i3;
        int i4;
        int i5;
        String str3;
        String str4;
        int c3;
        try {
            Iterator<String> it = list.iterator();
            String str5 = "HiH_HiDicHealthDataStat";
            int i6 = 0;
            double d = 0.0d;
            double d2 = 0.0d;
            int i7 = 0;
            int i8 = 0;
            double d3 = 0.0d;
            double d4 = 0.0d;
            double d5 = 0.0d;
            double d6 = 0.0d;
            double d7 = 0.0d;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            double d11 = 0.0d;
            while (it.hasNext()) {
                try {
                    try {
                        Iterator<String> it2 = it;
                        JSONObject jSONObject = new JSONObject(it.next());
                        i7++;
                        try {
                            if (CommonUtil.d(jSONObject, "abnormalTrack") == 0 && CommonUtil.d(jSONObject, "mDuplicated") == 0) {
                                double c4 = d3 + CommonUtil.c(jSONObject, "totalSteps");
                                d2 = Math.max(CommonUtil.c(jSONObject, "mTotalDescent"), d2);
                                d4 += CommonUtil.c(jSONObject, BleConstants.TOTAL_DISTANCE);
                                d6 += CommonUtil.c(jSONObject, "totalTime");
                                d7 += CommonUtil.c(jSONObject, "creepingWave");
                                d8 += CommonUtil.c(jSONObject, "avgPace");
                                d5 += CommonUtil.c(jSONObject, BleConstants.TOTAL_CALORIES);
                                d = Math.max(CommonUtil.c(jSONObject, BleConstants.TOTAL_DISTANCE), d);
                                if (jSONObject.has(BleConstants.EXTEND_TRACK_DATA_MAP)) {
                                    JSONObject jSONObject2 = new JSONObject(jSONObject.getString(BleConstants.EXTEND_TRACK_DATA_MAP));
                                    d11 += CommonUtil.c(jSONObject2, "divingTime");
                                    d9 += CommonUtil.c(jSONObject2, "divingCount");
                                    d10 += CommonUtil.c(jSONObject2, "wayPointDistance");
                                }
                                if (jSONObject.has("wearSportData")) {
                                    JSONObject jSONObject3 = jSONObject.getJSONObject("wearSportData");
                                    if (jSONObject3.has("max_met") && (c3 = ((int) (CommonUtil.c(jSONObject3, "max_met") * 3.5d)) / 65536) > i6) {
                                        i6 = c3;
                                    }
                                }
                                str4 = str5;
                                d3 = c4;
                                str5 = str4;
                                it = it2;
                            }
                            ReleaseLogUtil.d(str4, "statDayTrack is abnormalTrack or duplicated");
                            i8++;
                            str5 = str4;
                            it = it2;
                        } catch (JSONException e2) {
                            e = e2;
                            str = str4;
                            ReleaseLogUtil.d(str, "statSportSequence JSONException!, e is ", e.getMessage());
                            return;
                        }
                        str4 = str5;
                    } catch (JSONException e3) {
                        e = e3;
                        str4 = str5;
                    }
                } catch (JSONException e4) {
                    e = e4;
                    str = str5;
                }
            }
            String str6 = str5;
            double d12 = d2;
            int a2 = HiDateUtil.a(hiHealthData.getStartTime(), this.p);
            int syncStatus = hiHealthData.getSyncStatus();
            if (z) {
                numArr2 = numArr;
                c(a2, i, numArr2[0].intValue(), d3, syncStatus, 1);
                c(a2, i, numArr2[1].intValue(), d4, syncStatus, 2);
                c(a2, i, numArr2[2].intValue(), d5, syncStatus, 3);
                c(a2, i, numArr2[3].intValue(), d6, syncStatus, 13);
                c(a2, i, numArr2[4].intValue(), i7, syncStatus, 16);
                c(a2, i, numArr2[5].intValue(), i8, syncStatus, 16);
                c(a2, i, numArr2[7].intValue(), d, syncStatus, 2);
                c(a2, i, numArr2[9].intValue(), d7, syncStatus, 4);
                str2 = str6;
                i2 = i6;
                c2 = '\b';
            } else {
                numArr2 = numArr;
                String str7 = str6;
                int i9 = i6;
                double d13 = d;
                char c5 = 2;
                int length = numArr2.length;
                int i10 = 0;
                while (i10 < length) {
                    int intValue = numArr2[i10].intValue();
                    if (intValue == 8) {
                        i3 = i10;
                        i4 = length;
                        i5 = i8;
                        str3 = str7;
                        c(a2, i, intValue, d13, syncStatus, 2);
                    } else if (intValue == 10) {
                        i3 = i10;
                        i4 = length;
                        i5 = i8;
                        str3 = str7;
                        c(a2, i, intValue, d7, syncStatus, 4);
                    } else if (intValue == 24) {
                        i3 = i10;
                        i4 = length;
                        i5 = i8;
                        str3 = str7;
                        c(a2, i, intValue, d9, syncStatus, 16);
                    } else if (intValue == 26) {
                        i3 = i10;
                        i4 = length;
                        i5 = i8;
                        str3 = str7;
                        c(a2, i, intValue, d10, syncStatus, 2);
                    } else if (intValue == 21) {
                        i3 = i10;
                        i4 = length;
                        i5 = i8;
                        str3 = str7;
                        c(a2, i, intValue, d11, syncStatus, 2);
                    } else if (intValue != 22) {
                        switch (intValue) {
                            case 1:
                                i3 = i10;
                                i4 = length;
                                i5 = i8;
                                c(a2, i, intValue, d3, syncStatus, 1);
                                break;
                            case 2:
                                i3 = i10;
                                i4 = length;
                                i5 = i8;
                                c(a2, i, intValue, d4, syncStatus, 2);
                                break;
                            case 3:
                                i3 = i10;
                                i4 = length;
                                i5 = i8;
                                c(a2, i, intValue, d5, syncStatus, 3);
                                break;
                            case 4:
                                i3 = i10;
                                i4 = length;
                                i5 = i8;
                                c(a2, i, intValue, d6, syncStatus, 13);
                                break;
                            case 5:
                                i3 = i10;
                                i4 = length;
                                i5 = i8;
                                c(a2, i, intValue, i7, syncStatus, 16);
                                break;
                            case 6:
                                i3 = i10;
                                i4 = length;
                                i5 = i8;
                                c(a2, i, intValue, i8, syncStatus, 16);
                                break;
                            default:
                                i3 = i10;
                                i4 = length;
                                i5 = i8;
                                String str8 = str7;
                                ReleaseLogUtil.e(str8, "common stat type is invalid!");
                                str3 = str8;
                                continue;
                        }
                        str3 = str7;
                    } else {
                        i3 = i10;
                        i4 = length;
                        i5 = i8;
                        str3 = str7;
                        c(a2, i, intValue, d12, syncStatus, 2);
                    }
                    i10 = i3 + 1;
                    str7 = str3;
                    length = i4;
                    c5 = 2;
                    i8 = i5;
                }
                str2 = str7;
                c2 = '\b';
                i2 = i9;
            }
            if (i2 == 0 || (i2 >= 21 && i2 <= 88)) {
                if (z) {
                    double d14 = i2;
                    c(a2, i, numArr2[c2].intValue(), d14, syncStatus, 0);
                    if (i2 == 0) {
                        e();
                    } else {
                        c(a2, 30001, 42007, d14, syncStatus, 0);
                    }
                } else if (Arrays.asList(numArr).contains(9)) {
                    c(a2, i, 9, i2, syncStatus, 0);
                }
            }
            if (z) {
                if (d8 > 0.0d) {
                    c(a2, 30001, 42004, d8, syncStatus, 14);
                }
                if (a2 == HiDateUtil.a(System.currentTimeMillis(), this.p)) {
                    LogUtil.c(str2, "statDayTrack today track Stat changed ! ");
                    HiBroadcastUtil.d(this.b, 2);
                }
            }
        } catch (JSONException e5) {
            e = e5;
            str = "HiH_HiDicHealthDataStat";
        }
    }

    private void e() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, currentTimeMillis);
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        List<HiHealthData> d = ijd.c(this.b).d(hiDataReadOption, 42006, this.o, null);
        if (d == null || d.isEmpty()) {
            LogUtil.h("HiH_HiDicHealthDataStat", "getLastVo2max vo2maxListSport is null");
        } else {
            c(HiDateUtil.a(d.get(0).getStartTime(), this.p), 30001, 42007, d.get(0).getIntValue(), d.get(0).getSyncStatus(), 0);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean a(HiHealthDictStat hiHealthDictStat) {
        char c2;
        double c3;
        String str;
        List<HiHealthData> b;
        double d;
        String[] strArr = {hiHealthDictStat.c()};
        HiDataReadOption b2 = b();
        String e2 = hiHealthDictStat.e();
        e2.hashCode();
        switch (e2.hashCode()) {
            case 2641:
                if (e2.equals(HealthDataStatPolicy.SD)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 65202:
                if (e2.equals(HealthDataStatPolicy.AVG)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 76100:
                if (e2.equals("MAX")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 76338:
                if (e2.equals("MIN")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 82475:
                if (e2.equals(HealthDataStatPolicy.SUM)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 2329238:
                if (e2.equals(HealthDataStatPolicy.LAST)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 64313583:
                if (e2.equals(HealthDataStatPolicy.COUNT)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                List<HiHealthData> b3 = this.i.b(b2, this.t, this.d, (ifl) null);
                List<HiHealthData> b4 = this.h.b(this.d, this.l, this.k, 3, this.t, new String[]{HealthDataStatPolicy.AVG}, new int[]{3}, 0);
                if (HiCommonUtil.d(b4) || HiCommonUtil.d(b3) || b3.size() <= 1) {
                    LogUtil.h("HiH_HiDicHealthDataStat", "statData is nullEmpty or currentDayDatas is less than or equal to one");
                    return iwg.d(this.f, this.o, this.t, new int[]{hiHealthDictStat.d()});
                }
                c3 = c(b3, b4);
                d = c3;
                return c(this.f, this.t, hiHealthDictStat.d(), d, 0, 0);
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
                int[] iArr = {c.get(hiHealthDictStat.e()).intValue()};
                if (hiHealthDictStat.a() != null) {
                    str = "HiH_HiDicHealthDataStat";
                    b = this.h.d(this.d, this.l, this.k, 3, this.t, strArr, iArr, 0, hiHealthDictStat.a().doubleValue());
                } else {
                    str = "HiH_HiDicHealthDataStat";
                    b = this.h.b(this.d, this.l, this.k, 3, this.t, strArr, iArr, 0);
                }
                if (HiCommonUtil.d(b)) {
                    ReleaseLogUtil.d(str, "statData is null");
                    if (iwp.f(hiHealthDictStat.d())) {
                        return true;
                    }
                    return iwg.d(this.f, this.o, this.t, new int[]{hiHealthDictStat.d()});
                }
                int size = b.size();
                if (size > 1 && (hiHealthDictStat.d() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT.value() || hiHealthDictStat.d() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT.value() || hiHealthDictStat.d() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT.value())) {
                    ReleaseLogUtil.e(str, "statPointOnePolicy detailDatas=", b);
                    double d2 = 0.0d;
                    for (int i = 0; i < size; i++) {
                        double d3 = b.get(i).getDouble(hiHealthDictStat.c());
                        if (d3 > d2) {
                            d2 = d3;
                        }
                    }
                    d = d2;
                    return c(this.f, this.t, hiHealthDictStat.d(), d, 0, 0);
                }
                c3 = b.get(0).getDouble(hiHealthDictStat.c());
                d = c3;
                return c(this.f, this.t, hiHealthDictStat.d(), d, 0, 0);
            case 5:
                b2.setCount(1);
                List<HiHealthData> b5 = this.i.b(b2, this.t, this.d, (ifl) null);
                if (HiCommonUtil.d(b5)) {
                    ReleaseLogUtil.d("HiH_HiDicHealthDataStat", "statData is null");
                    if (iwp.f(hiHealthDictStat.d())) {
                        return true;
                    }
                    return iwg.d(this.f, this.o, this.t, new int[]{hiHealthDictStat.d()});
                }
                c3 = b5.get(0).getValue();
                d = c3;
                return c(this.f, this.t, hiHealthDictStat.d(), d, 0, 0);
            default:
                LogUtil.h("HiH_HiDicHealthDataStat", "This statPolicy is not supported! statPolicy is ", hiHealthDictStat.e());
                return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(com.huawei.hihealth.dictionary.model.HiHealthDictStat r16, com.huawei.hihealth.dictionary.model.HiHealthDictField r17, java.util.List<java.util.Map<java.lang.String, java.lang.Double>> r18) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.store.stat.HiDicHealthDataStat.e(com.huawei.hihealth.dictionary.model.HiHealthDictStat, com.huawei.hihealth.dictionary.model.HiHealthDictField, java.util.List):boolean");
    }

    public static void a(Context context, List<HiHealthData> list) throws JSONException {
        if (HiCommonUtil.b(list)) {
            return;
        }
        ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "eliminateDumplicateMetaData duplication", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(10);
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            b(arrayList, linkedHashMap, it.next());
        }
        TrackSimilarityIdentifier trackSimilarityIdentifier = new TrackSimilarityIdentifier();
        List<TrackSimilarityIdentifier.TrackRecord> c2 = trackSimilarityIdentifier.c(arrayList, TrackSimilarityIdentifier.TrackRouteDateInfo.INVALID_DATE);
        List<TrackSimilarityIdentifier.TrackRecord> c3 = trackSimilarityIdentifier.c(arrayList, TrackSimilarityIdentifier.TrackRouteDateInfo.VALID_DATE);
        list.clear();
        if (!HiCommonUtil.b(c2)) {
            b(list, linkedHashMap, c2);
        }
        if (!HiCommonUtil.b(c3)) {
            Iterator<TrackSimilarityIdentifier.TrackRecord> it2 = c3.iterator();
            while (it2.hasNext()) {
                e(list, linkedHashMap, it2.next());
            }
        }
        ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "metadata change  sequenceMetaDatasBefore size  = ", Integer.valueOf(list.size()));
        for (HiHealthData hiHealthData : list) {
            iiz.a(context).d(hiHealthData, hiHealthData.getClientId());
        }
    }

    private static void b(List<HiHealthData> list, Map<TrackSimilarityIdentifier.TrackRecord, HiHealthData> map, List<TrackSimilarityIdentifier.TrackRecord> list2) {
        for (TrackSimilarityIdentifier.TrackRecord trackRecord : list2) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(map.get(trackRecord).getMetaData(), HiTrackMetaData.class);
            if (hiTrackMetaData.getDuplicated() != 1) {
                hiTrackMetaData.setDuplicated(1);
                map.get(trackRecord).setMetaData(HiJsonUtil.e(hiTrackMetaData));
                list.add(map.get(trackRecord));
            } else {
                ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "buildTrackMetaData duplication is 1");
            }
        }
    }

    private static void e(List<HiHealthData> list, Map<TrackSimilarityIdentifier.TrackRecord, HiHealthData> map, TrackSimilarityIdentifier.TrackRecord trackRecord) {
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(map.get(trackRecord).getMetaData(), HiTrackMetaData.class);
        if (hiTrackMetaData.getDuplicated() == 1) {
            hiTrackMetaData.setDuplicated(0);
            map.get(trackRecord).setMetaData(HiJsonUtil.e(hiTrackMetaData));
            list.add(map.get(trackRecord));
            return;
        }
        ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "TrackSimilarityIdentifier duplication is 0");
    }

    private static void b(List<TrackSimilarityIdentifier.TrackRecord> list, Map<TrackSimilarityIdentifier.TrackRecord, HiHealthData> map, HiHealthData hiHealthData) throws JSONException {
        JSONObject jSONObject = new JSONObject(hiHealthData.getMetaData());
        if (CommonUtil.d(jSONObject, "abnormalTrack") != 0 || CommonUtil.d(jSONObject, BleConstants.SPORT_TYPE) == 512 || CommonUtil.d(jSONObject, BleConstants.SPORT_TYPE) == DicDataTypeUtil.DataType.ADVENTURES_SPORT_TYPE.value()) {
            return;
        }
        if (CommonUtil.d(jSONObject, "sportDataSource") == 2) {
            ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "eliminateDumplicateMetaData user input source");
            return;
        }
        a aVar = new a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), CommonUtil.d(jSONObject, BleConstants.TOTAL_DISTANCE), CommonUtil.d(jSONObject, "trackType"));
        list.add(aVar);
        map.put(aVar, hiHealthData);
    }

    private HiDataReadOption b() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(this.l);
        hiDataReadOption.setEndTime(this.k);
        hiDataReadOption.setSortOrder(1);
        return hiDataReadOption;
    }

    private double c(List<HiHealthData> list, List<HiHealthData> list2) {
        double d = list2.get(0).getDouble(HealthDataStatPolicy.AVG);
        Iterator<HiHealthData> it = list.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            d2 += Math.pow(it.next().getValue() - d, 2.0d);
        }
        return Math.sqrt(d2 / (list.size() - 1));
    }

    private boolean c(int i, int i2, int i3, double d, int i4, int i5) {
        if (iwp.h(i3) && i != HiDateUtil.a(System.currentTimeMillis(), this.p) && d == 0.0d) {
            return true;
        }
        igo igoVar = new igo();
        igoVar.e(i);
        igoVar.j(this.r);
        igoVar.g(i4);
        igoVar.c(i2);
        igoVar.h(i5);
        igoVar.b(this.o);
        igoVar.a(d);
        igoVar.d(i3);
        return b(igoVar);
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0065: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:24:0x0065 */
    public boolean b(defpackage.igo r7) {
        /*
            r6 = this;
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            int r2 = r7.f()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            boolean r1 = defpackage.ivu.i(r1, r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            if (r1 != 0) goto L1c
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            int r2 = r7.f()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            boolean r1 = defpackage.ivu.e(r1, r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            goto L1d
        L1c:
            r1 = r0
        L1d:
            boolean r2 = r6.e(r7)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
            if (r1 == 0) goto L2e
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
            int r4 = r7.f()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
            defpackage.ivu.j(r3, r4)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
        L2e:
            if (r1 == 0) goto L3b
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            int r7 = r7.f()
            defpackage.ivu.c(r0, r7)
        L3b:
            return r2
        L3c:
            r2 = move-exception
            goto L43
        L3e:
            r1 = move-exception
            goto L68
        L40:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L43:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L64
            java.lang.String r4 = "insertOrUpdateStatData: "
            r3[r0] = r4     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = health.compact.a.LogAnonymous.b(r2)     // Catch: java.lang.Throwable -> L64
            r4 = 1
            r3[r4] = r2     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = "HiH_HiDicHealthDataStat"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r2, r3)     // Catch: java.lang.Throwable -> L64
            if (r1 == 0) goto L63
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            int r7 = r7.f()
            defpackage.ivu.c(r1, r7)
        L63:
            return r0
        L64:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L68:
            if (r0 == 0) goto L75
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            int r7 = r7.f()
            defpackage.ivu.c(r0, r7)
        L75:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.store.stat.HiDicHealthDataStat.b(igo):boolean");
    }

    private boolean e(igo igoVar) {
        List asList = Arrays.asList(e);
        List asList2 = Arrays.asList(f4210a);
        if ((asList.contains(Integer.valueOf(igoVar.f())) || asList2.contains(Integer.valueOf(igoVar.f()))) && igoVar.l() == 0.0d) {
            ReleaseLogUtil.e("HiH_HiDicHealthDataStat", "statValue is 0, statType is = ", Integer.valueOf(igoVar.f()));
            return iwg.d(this.f, this.o, igoVar.b(), new int[]{igoVar.f()});
        }
        return ivu.a(this.b, igoVar.f()).e(igoVar, true, this.g.e().contains(Integer.valueOf(igoVar.b())));
    }

    public static class a extends TrackSimilarityIdentifier.TrackRecord {
        private int b;
        long c;
        long d;
        private int e;

        a(long j, long j2, int i, int i2) {
            this.d = j;
            this.c = j2;
            this.b = i;
            this.e = i2;
        }

        @Override // com.huawei.hwcommonmodel.TrackSimilarityIdentifier.TrackRecord
        public long getStartTime() {
            return this.d;
        }

        @Override // com.huawei.hwcommonmodel.TrackSimilarityIdentifier.TrackRecord
        public long getEndTime() {
            return this.c;
        }

        @Override // com.huawei.hwcommonmodel.TrackSimilarityIdentifier.TrackRecord
        public float getDistance() {
            return this.b;
        }

        @Override // com.huawei.hwcommonmodel.TrackSimilarityIdentifier.TrackRecord
        public boolean isWatchData() {
            return this.e > 2;
        }
    }
}
