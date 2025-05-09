package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import android.util.ArrayMap;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.iiz;
import defpackage.ijd;
import defpackage.ikr;
import defpackage.iks;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;

/* loaded from: classes7.dex */
public class HiTrackStat extends HiStatCommon {
    private iks aa;
    private TimeZone ab;
    private int ac;
    private int ad;
    private int ae;
    private double af;
    private double ag;
    private double ah;
    private int ai;
    private double aj;
    private List<String> ak;
    private int al;
    private Map<String, List<String>> am;
    private int an;
    private List<String> ao;
    private List<String> ap;
    private List<String> aq;
    private List<String> ar;
    private List<String> as;
    private List<String> av;
    private List<String> f;
    private double h;
    private List<String> j;
    private double k;
    private int l;
    private int m;
    private int n;
    private iiz o;
    private float p;
    private int q;
    private int r;
    private int s;
    private double t;
    private double u;
    private int v;
    private int w;
    private int x;
    private int y;
    private ikr z;
    private static final Integer[] g = {42051, 42052, 42053, 42054, 42055, 42056, 42057, 42058, 42059, 42060, 42061};
    private static final Integer[] e = {42101, 42102, 42103, 42104, 42105, 42106, 42107, 42108, 42109, 42110, 42111};

    /* renamed from: a, reason: collision with root package name */
    private static final Integer[] f4213a = {42151, 42152, 42153, 42154, 42155, 42156, 42157, 42158, 42159, 42160, 42161};
    private static final Integer[] b = {42201, 42202, 42203, 42204, 42205, 42206, 42207, 42208, 42209, 42210, 42211};
    private static final Integer[] i = {42251, 42252, 42253, 42254, 42255, 42256, 42257, 42258, 42259, 42260, 42261};
    private static final Integer[] c = {42351, 42352, 42353, 42354, 42355, 42356, 42357, 42358, 42359, 42360, 42361};
    private static final Integer[] d = {42401, 42402, 42403, 42404, 42405, 42406, 42407, 42408, 42409, 42410, 42411};

    public HiTrackStat(Context context) {
        super(context);
        this.al = 0;
        this.y = 0;
        this.q = 0;
        this.w = 0;
        this.v = 0;
        this.s = 0;
        this.af = 0.0d;
        this.aj = 0.0d;
        this.ag = 0.0d;
        this.ah = 0.0d;
        this.ae = 0;
        this.ai = 0;
        this.k = 0.0d;
        this.h = 0.0d;
        this.r = 0;
        this.t = 0.0d;
        this.p = 0.0f;
        this.m = 0;
        this.x = 30001;
        this.n = 0;
        this.u = 0.0d;
        this.l = 0;
        this.ar = new ArrayList(10);
        this.as = new ArrayList(10);
        this.ak = new ArrayList(10);
        this.aq = new ArrayList(10);
        this.ao = new ArrayList(10);
        this.av = new ArrayList(10);
        this.j = new ArrayList(10);
        this.ap = new ArrayList(10);
        this.f = new ArrayList(10);
        this.am = new HashMap(16);
        this.o = iiz.a(context);
        this.z = ikr.b(this.mContext);
        this.aa = iks.e();
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.an = hiHealthData.getUserId();
        this.ab = TimeZone.getDefault();
        long e2 = HiDateUtil.e(hiHealthData.getStartTime(), this.ab);
        long d2 = HiDateUtil.d(hiHealthData.getStartTime(), this.ab, -1);
        long b2 = HiDateUtil.b(hiHealthData.getStartTime(), this.ab);
        this.ac = HiDateUtil.a(e2, this.ab);
        ReleaseLogUtil.e("HiH_HiTrackStat", "stat() trackData", Long.valueOf(e2), " tz ", this.ab);
        int type = hiHealthData.getType();
        List<Integer> a2 = this.aa.a(this.an);
        if (a2 == null || a2.isEmpty()) {
            ReleaseLogUtil.d("HiH_HiTrackStat", "stat() clientIds is null");
            return false;
        }
        if (e2 >= HiDateUtil.e(System.currentTimeMillis(), this.ab) - 31104000000L) {
            try {
                HiDicHealthDataStat.a(this.mContext, this.o.d(a2, d2, b2, 30001));
            } catch (JSONException e3) {
                ReleaseLogUtil.d("HiH_HiTrackStat", "statDayTrack eliminateDuplicateMetaData JSONException, e is ", e3.getMessage());
                return false;
            }
        }
        List<String> a3 = this.o.a(a2, e2, b2, type);
        this.ad = this.z.e(0, this.an, 0);
        Map<String, List<String>> d3 = d(a3);
        d(a3, hiHealthData, HiDicHealthDataStat.e);
        List<igo> d4 = ijd.c(this.mContext).d(this.ac, this.ad, CommonUtil.b(d()));
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        if (!HiCommonUtil.d(d4)) {
            Iterator<igo> it = d4.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().f()));
            }
        }
        a(d3.get("walk_type_datas"), hiHealthData, g, arrayList);
        a(d3.get("run_type_datas"), hiHealthData, e, arrayList);
        a(d3.get("ride_type_datas"), hiHealthData, f4213a, arrayList);
        a(d3.get("swim_type_datas"), hiHealthData, b, arrayList);
        a(d3.get("triathlon_type_datas"), hiHealthData, i, arrayList);
        a(d3.get("ski_type_datas"), hiHealthData, c, arrayList);
        a(d3.get("golf_type_datas"), hiHealthData, d, arrayList);
        ArrayList arrayList2 = new ArrayList(10);
        if (this.am.size() > 0) {
            LogUtil.c("HiH_HiTrackStat", "statDayTrack for userDefinedSequenceData");
            for (Map.Entry<String, List<String>> entry : this.am.entrySet()) {
                d(entry.getValue(), hiHealthData, HiDicHealthDataStat.f4210a);
                arrayList2.add(entry.getKey());
            }
        }
        this.mDataStatManager.b(this.ac, arrayList2, this.ad);
        d(d3.get("basketball_type_datas"), hiHealthData);
        d(this.ac, 43500, hiHealthData.getSyncStatus(), d3.get("wear_device_type_datas") != null ? d3.get("wear_device_type_datas").size() : 0, 16);
        LogUtil.c("HiH_HiTrackStat", "stat() mTotalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return true;
    }

    private ArrayList<Integer> d() {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(g));
        arrayList.addAll(Arrays.asList(e));
        arrayList.addAll(Arrays.asList(f4213a));
        arrayList.addAll(Arrays.asList(b));
        arrayList.addAll(Arrays.asList(i));
        arrayList.addAll(Arrays.asList(c));
        arrayList.addAll(Arrays.asList(d));
        return arrayList;
    }

    private void a(List<String> list, HiHealthData hiHealthData, Integer[] numArr, ArrayList<Integer> arrayList) {
        if (!HiCommonUtil.d(list)) {
            d(list, hiHealthData, numArr);
            return;
        }
        ArrayList<Integer> arrayList2 = new ArrayList(Arrays.asList(numArr));
        arrayList2.retainAll(arrayList);
        if (arrayList2.size() > 0) {
            ReleaseLogUtil.e("HiH_HiTrackStat", "intersection is ", arrayList2);
            for (Integer num : arrayList2) {
                igo igoVar = new igo();
                igoVar.d(num.intValue());
                igoVar.b(this.ad);
                igoVar.e(this.ac);
                this.mDataStatManager.b(igoVar);
            }
        }
    }

    private void d(List<String> list, HiHealthData hiHealthData) {
        List<String> list2;
        if (this.ad <= 0) {
            LogUtil.h("HiH_HiTrackStat", "statDayBasketballTrack userClient <= 0");
            return;
        }
        if (list == null) {
            LogUtil.c("HiH_HiTrackStat", "statDayBasketballTrack sequenceMetaDatas is null");
            list2 = new ArrayList<>(10);
        } else {
            list2 = list;
        }
        LogUtil.c("HiH_HiTrackStat", "statDayBasketballTrack sequenceMetaDatas = ", Integer.valueOf(list2.size()), ",mStatClient = ", Integer.valueOf(this.ad));
        Iterator<String> it = list2.iterator();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        while (it.hasNext()) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(it.next(), HiTrackMetaData.class);
            i3++;
            if (hiTrackMetaData.getAbnormalTrack() != 0 || hiTrackMetaData.getDuplicated() != 0) {
                LogUtil.h("HiH_HiTrackStat", "statDayBasketballTrack is abnormalTrack or duplicated");
                i9++;
            } else {
                i2 += hiTrackMetaData.getTotalSteps();
                d3 += hiTrackMetaData.getTotalCalories();
                d2 += hiTrackMetaData.getTotalDistance();
                d4 += hiTrackMetaData.getTotalTime();
                Map<String, Integer> wearSportData = hiTrackMetaData.getWearSportData();
                if (wearSportData != null) {
                    if (wearSportData.containsKey("active_time")) {
                        i4 += wearSportData.get("active_time").intValue();
                    }
                    if (wearSportData.containsKey("jump_times")) {
                        i5 += wearSportData.get("jump_times").intValue();
                    }
                    if (wearSportData.containsKey("max_duration_of_passage") && wearSportData.get("max_duration_of_passage").intValue() > i6) {
                        i6 = wearSportData.get("max_duration_of_passage").intValue();
                    }
                    if (wearSportData.containsKey("max_jump_height") && wearSportData.get("max_jump_height").intValue() > i7) {
                        i7 = wearSportData.get("max_jump_height").intValue();
                    }
                    if (wearSportData.containsKey("max_spriting_speed") && wearSportData.get("max_spriting_speed").intValue() > i8) {
                        i8 = wearSportData.get("max_spriting_speed").intValue();
                    }
                }
            }
        }
        int syncStatus = hiHealthData.getSyncStatus();
        d(this.ac, 42310, syncStatus, i2, 1);
        d(this.ac, 42301, syncStatus, d2, 2);
        d(this.ac, 42302, syncStatus, d3, 3);
        d(this.ac, 42303, syncStatus, d4, 13);
        d(this.ac, 42304, syncStatus, i3, 16);
        d(this.ac, 42305, syncStatus, i4, 13);
        d(this.ac, 42306, syncStatus, i5, 16);
        d(this.ac, 42307, syncStatus, i6, 13);
        d(this.ac, 42308, syncStatus, i7, 2);
        d(this.ac, 42309, syncStatus, i8, 14);
        d(this.ac, 42311, syncStatus, i9, 16);
    }

    private void d(List<String> list, HiHealthData hiHealthData, Integer[] numArr) {
        boolean equals = Arrays.equals(HiDicHealthDataStat.e, numArr);
        if (this.ad <= 0) {
            LogUtil.h("HiH_HiTrackStat", "statDayTrack userClient <= 0");
            return;
        }
        if (list == null) {
            LogUtil.c("HiH_HiTrackStat", "statDayTrack sequenceMetaDatas is null");
            list = new ArrayList<>(10);
        }
        LogUtil.c("HiH_HiTrackStat", "statDayTrack sequenceMetaDatas = ", Integer.valueOf(list.size()), ",mStatClient = ", Integer.valueOf(this.ad));
        a();
        d(list, equals);
        c(hiHealthData, numArr, equals);
    }

    private void c(HiHealthData hiHealthData, Integer[] numArr, boolean z) {
        int syncStatus = hiHealthData.getSyncStatus();
        d(this.ac, numArr[0].intValue(), syncStatus, this.ah, 1);
        d(this.ac, numArr[1].intValue(), syncStatus, this.af, 2);
        d(this.ac, numArr[2].intValue(), syncStatus, this.ag, 3);
        d(this.ac, numArr[3].intValue(), syncStatus, this.aj, 13);
        d(this.ac, numArr[4].intValue(), syncStatus, this.ae, 16);
        d(this.ac, numArr[5].intValue(), syncStatus, this.ai, 16);
        d(this.ac, numArr[7].intValue(), syncStatus, this.k, 2);
        int i2 = this.r;
        if (i2 == 0 || (i2 >= 21 && i2 <= 88)) {
            d(this.ac, numArr[8].intValue(), syncStatus, this.r, 0);
            if (z) {
                int i3 = this.r;
                if (i3 == 0) {
                    e();
                } else {
                    a(this.ac, 42007, syncStatus, i3, 0);
                }
            }
        }
        d(this.ac, numArr[9].intValue(), syncStatus, this.h, 4);
        if (this.x == 283) {
            d(this.ac, 12, syncStatus, this.m, 16);
        }
        if (this.x == 274) {
            d(this.ac, 25, syncStatus, this.u, 13);
        }
        int i4 = this.x;
        if (i4 == 258 || i4 == 264 || i4 == 280) {
            d(this.ac, 42112, syncStatus, this.n, 8);
            d(this.ac, 42113, syncStatus, this.l, 13);
        }
        if (z) {
            c(this.t, this.ac, syncStatus);
        }
    }

    private void d(List<String> list, boolean z) {
        double parseDouble;
        int intValue;
        int i2;
        Iterator<String> it = list.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(it.next(), HiTrackMetaData.class);
            if (z && 512 == hiTrackMetaData.getSportType()) {
                LogUtil.c("HiH_HiTrackStat", "statDayTrack TRIATHLON don't stat in total");
            } else {
                this.x = hiTrackMetaData.getSportType();
                this.ae++;
                if (hiTrackMetaData.getAbnormalTrack() == 0 && (((i2 = this.x) == 258 || i2 == 264 || i2 == 280) && !z2)) {
                    if (hiTrackMetaData.getExtendTrackMap().containsKey("lthrHr")) {
                        this.n = CommonUtil.h(hiTrackMetaData.getExtendTrackMap().get("lthrHr"));
                    }
                    if (hiTrackMetaData.getExtendTrackMap().containsKey("lthrPace")) {
                        this.l = CommonUtil.h(hiTrackMetaData.getExtendTrackMap().get("lthrPace"));
                    }
                    if (this.n != 0 || this.l != 0) {
                        z2 = true;
                    }
                }
                if (hiTrackMetaData.getAbnormalTrack() != 0 || hiTrackMetaData.getDuplicated() != 0) {
                    LogUtil.h("HiH_HiTrackStat", "statDayTrack is abnormalTrack or duplicated");
                    this.ai++;
                } else {
                    this.ag += hiTrackMetaData.getTotalCalories();
                    this.ah += hiTrackMetaData.getTotalSteps();
                    this.af += hiTrackMetaData.getTotalDistance();
                    this.aj += hiTrackMetaData.getTotalTime();
                    float avgPace = hiTrackMetaData.getAvgPace();
                    this.p = avgPace;
                    this.t += avgPace;
                    this.h += hiTrackMetaData.getCreepingWave();
                    Map<String, Integer> wearSportData = hiTrackMetaData.getWearSportData();
                    if (wearSportData != null && wearSportData.containsKey("max_met") && (intValue = ((int) (wearSportData.get("max_met").intValue() * 3.5f)) / 65536) > this.r) {
                        this.r = intValue;
                    }
                    double totalDistance = hiTrackMetaData.getTotalDistance();
                    double d2 = this.k;
                    if (totalDistance > d2) {
                        d2 = hiTrackMetaData.getTotalDistance();
                    }
                    this.k = d2;
                    e(hiTrackMetaData);
                    if (hiTrackMetaData.getSportType() == 274) {
                        String str = hiTrackMetaData.getExtendTrackMap().get("rowMachinePowerModeTime");
                        if (str == null) {
                            parseDouble = 0.0d;
                        } else {
                            try {
                                parseDouble = Double.parseDouble(str);
                            } catch (NumberFormatException unused) {
                                ReleaseLogUtil.c("HiH_HiTrackStat", "get RowMachinePowerModeTime NumberFormatException, time is ", str);
                            }
                        }
                        this.u += parseDouble;
                    }
                }
            }
        }
    }

    private void e(HiTrackMetaData hiTrackMetaData) {
        int parseInt;
        if (hiTrackMetaData.getSportType() == 283) {
            String str = hiTrackMetaData.getExtendTrackMap().get("skipNum");
            if (str == null) {
                parseInt = 0;
            } else {
                try {
                    parseInt = Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                    ReleaseLogUtil.c("HiH_HiTrackStat", "get JumpTimes NumberFormatException, time is ", str);
                    return;
                }
            }
            this.m += parseInt;
        }
    }

    private void a() {
        this.af = 0.0d;
        this.aj = 0.0d;
        this.ag = 0.0d;
        this.ah = 0.0d;
        this.ae = 0;
        this.ai = 0;
        this.k = 0.0d;
        this.h = 0.0d;
        this.r = 0;
        this.t = 0.0d;
        this.p = 0.0f;
        this.m = 0;
        this.n = 0;
        this.l = 0;
        this.u = 0.0d;
        this.x = 30001;
    }

    private void c(double d2, int i2, int i3) {
        d(i2, 42004, i3, d2, 14);
        d(i2, 43800, i3, this.v | this.al | this.y | this.q | this.w | this.s, 0);
        if (i2 == HiDateUtil.a(System.currentTimeMillis(), this.ab)) {
            LogUtil.c("HiH_HiTrackStat", "statDayTrack today track Stat changed ! ");
            HiBroadcastUtil.d(this.mContext, 2);
        }
    }

    private Map<String, List<String>> d(List<String> list) {
        this.al = 0;
        this.y = 0;
        this.q = 0;
        this.w = 0;
        this.v = 0;
        this.s = 0;
        ArrayMap arrayMap = new ArrayMap();
        if (HiCommonUtil.d(list)) {
            LogUtil.h("HiH_HiTrackStat", "getSportTypeSequenceData sequenceMetaDatas is null");
            this.am.clear();
            return arrayMap;
        }
        b(list, arrayMap);
        return arrayMap;
    }

    private void b(List<String> list, Map<String, List<String>> map) {
        c();
        for (String str : list) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(str, HiTrackMetaData.class);
            if (d(hiTrackMetaData)) {
                this.av.add(str);
            }
            int sportType = hiTrackMetaData.getSportType();
            if (sportType != 262) {
                if (sportType == 264) {
                    this.as.add(str);
                    this.w = 2;
                } else if (sportType != 266) {
                    switch (sportType) {
                        case 257:
                            break;
                        case 258:
                            this.as.add(str);
                            this.y = 8;
                            continue;
                        case 259:
                            this.ak.add(str);
                            this.q = 4;
                            continue;
                        default:
                            switch (sportType) {
                                case OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE /* 280 */:
                                    this.as.add(str);
                                    this.v = 32;
                                    continue;
                                case 281:
                                case 282:
                                    break;
                                default:
                                    c(sportType, str);
                                    continue;
                            }
                    }
                    this.ar.add(str);
                    this.al = 16;
                }
            }
            this.aq.add(str);
            this.s = 1;
        }
        a(map);
    }

    private void c(int i2, String str) {
        if (i2 == 271) {
            this.j.add(str);
        }
        if (i2 == 512) {
            this.ao.add(str);
            this.s = 1;
            return;
        }
        switch (i2) {
            case 217:
            case 218:
            case 219:
                this.ap.add(str);
                break;
            case HeartRateThresholdConfig.HEART_RATE_LIMIT /* 220 */:
                this.f.add(str);
                break;
            default:
                d(i2, str);
                this.s = 1;
                break;
        }
    }

    private void d(int i2, String str) {
        List<String> list = this.am.get(String.valueOf(i2));
        if (HiCommonUtil.d(list)) {
            list = new ArrayList<>(10);
        }
        list.add(str);
        this.am.put(String.valueOf(i2), list);
    }

    private void a(Map<String, List<String>> map) {
        map.put("walk_type_datas", this.ar);
        map.put("run_type_datas", this.as);
        map.put("ride_type_datas", this.ak);
        map.put("swim_type_datas", this.aq);
        map.put("triathlon_type_datas", this.ao);
        map.put("wear_device_type_datas", this.av);
        map.put("basketball_type_datas", this.j);
        map.put("ski_type_datas", this.ap);
        map.put("golf_type_datas", this.f);
    }

    private void c() {
        this.ar.clear();
        this.as.clear();
        this.ak.clear();
        this.aq.clear();
        this.ao.clear();
        this.av.clear();
        this.j.clear();
        this.f.clear();
        this.ap.clear();
        this.am.clear();
    }

    private boolean d(HiTrackMetaData hiTrackMetaData) {
        int trackType = hiTrackMetaData.getTrackType();
        return trackType == 3 || trackType == 4 || trackType == 5 || trackType == 6;
    }

    private boolean d(int i2, int i3, int i4, double d2, int i5) {
        igo e2 = e(i2, i3, i4, d2, i5);
        if (i3 < 50) {
            e2.c(this.x);
        }
        if (d2 <= 0.0d) {
            LogUtil.c("HiH_HiTrackStat", "saveOneTrackStat delete old stat , because new stat is zero ! statDate = ", Integer.valueOf(i2), ",mStatClient = ", Integer.valueOf(this.ad), ", statType = ", Integer.valueOf(i3));
            return this.mDataStatManager.b(e2);
        }
        return this.mDataStatManager.a(e2);
    }

    private igo e(int i2, int i3, int i4, double d2, int i5) {
        igo igoVar = new igo();
        igoVar.e(i2);
        igoVar.c(30001);
        igoVar.b(this.ad);
        igoVar.j(this.an);
        igoVar.g(i4);
        igoVar.h(i5);
        igoVar.d(i3);
        igoVar.a(d2);
        return igoVar;
    }

    private boolean a(int i2, int i3, int i4, double d2, int i5) {
        return this.mDataStatManager.c(e(i2, i3, i4, d2, i5));
    }

    private void e() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, currentTimeMillis);
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        List<HiHealthData> d2 = this.mDataStatManager.d(hiDataReadOption, 42006, this.ad, null);
        if (d2 == null || d2.isEmpty()) {
            LogUtil.h("HiH_HiTrackStat", "getLastVo2max vo2maxListSport is null");
        } else {
            a(HiDateUtil.a(d2.get(0).getStartTime(), this.ab), 42007, 1, d2.get(0).getIntValue(), 0);
        }
    }

    public boolean b(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.an = hiHealthData.getUserId();
        long e2 = HiDateUtil.e(hiHealthData.getStartTime(), this.ab);
        long b2 = HiDateUtil.b(hiHealthData.getStartTime(), this.ab);
        int type = hiHealthData.getType();
        List<Integer> a2 = this.aa.a(this.an);
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HiH_HiTrackStat", "statOldData() clientIds is null");
            return false;
        }
        List<String> a3 = this.o.a(a2, e2, b2, type);
        this.ad = this.z.e(0, this.an, 0);
        e(a3, hiHealthData);
        LogUtil.a("HiH_HiTrackStat", "statOldData() mTotalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return true;
    }

    private void e(List<String> list, HiHealthData hiHealthData) {
        int intValue;
        if (this.ad <= 0) {
            LogUtil.h("HiH_HiTrackStat", "statDayTrack userClient <= 0");
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("HiH_HiTrackStat", "statDayTrack sequenceMetaDatas is null");
            return;
        }
        LogUtil.c("HiH_HiTrackStat", "statDayTrack sequenceMetaDatas = ", Integer.valueOf(list.size()), ",mStatClient = ", Integer.valueOf(this.ad));
        Iterator<String> it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Map<String, Integer> wearSportData = ((HiTrackMetaData) HiJsonUtil.e(it.next(), HiTrackMetaData.class)).getWearSportData();
            if (wearSportData != null && wearSportData.containsKey("max_met") && (intValue = ((int) (wearSportData.get("max_met").intValue() * 3.5f)) / 65536) > i2) {
                i2 = intValue;
            }
        }
        int syncStatus = hiHealthData.getSyncStatus();
        if (21 > i2 || i2 > 88) {
            return;
        }
        double d2 = i2;
        d(this.ac, 42006, syncStatus, d2, 0);
        a(this.ac, 42007, syncStatus, d2, 0);
    }
}
