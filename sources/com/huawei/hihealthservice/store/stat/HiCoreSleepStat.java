package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiNoonSleepInfo;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.unite.ProfessionalSleep;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.iip;
import defpackage.iis;
import defpackage.iix;
import defpackage.ijf;
import defpackage.ijj;
import defpackage.ikr;
import defpackage.iks;
import defpackage.ivq;
import defpackage.ivu;
import defpackage.iwg;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HiCoreSleepStat extends HiStatCommon {

    /* renamed from: a, reason: collision with root package name */
    private Context f4209a;
    private iix b;
    private iks c;
    private int d;
    private ikr e;
    private int g;

    public HiCoreSleepStat(Context context) {
        super(context);
        this.f4209a = context;
        this.b = iix.a(context);
        this.e = ikr.b(this.f4209a);
        this.c = iks.e();
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.g = hiHealthData.getUserId();
        this.d = hiHealthData.getSyncStatus();
        igo igoVar = new igo();
        igoVar.d(HiDateUtil.r(hiHealthData.getStartTime()));
        igoVar.j(this.g);
        igoVar.g(0);
        igoVar.c(hiHealthData.getType());
        ReleaseLogUtil.e("Debug_HiCoreSleepStat", "stat() coreSleepData ", Integer.valueOf(igoVar.e()), " mSyncStatus ", Integer.valueOf(this.d));
        boolean e = e(HiDateUtil.l(hiHealthData.getStartTime()), HiDateUtil.o(hiHealthData.getStartTime()), igoVar, this.g);
        LogUtil.c("Debug_HiCoreSleepStat", "stat() coreSleepTotalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return e;
    }

    private boolean e(long j, long j2, igo igoVar, int i) {
        int e = this.e.e(0, i, 0);
        igoVar.b(e);
        if (e <= 0) {
            LogUtil.h("Debug_HiCoreSleepStat", "statSleepDataByUser() statClient <= 0, userID = ", Integer.valueOf(i));
            return false;
        }
        List<Integer> a2 = this.c.a(i);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("Debug_HiCoreSleepStat", "statSleepDataByUser() statClients is null, userID = ", Integer.valueOf(i));
            return false;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setSortOrder(0);
        return d(igoVar, this.b.e(hiDataReadOption, a2, 22100, 22199), a2);
    }

    private boolean d(igo igoVar, List<HiHealthData> list, List<Integer> list2) {
        String str;
        if (HiCommonUtil.d(list)) {
            if (this.d != 2) {
                return true;
            }
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "statCoreSleepData deleteAllStatDatas");
            a(igoVar);
            return iwg.a(igoVar, HiHealthDataType.a());
        }
        ReleaseLogUtil.e("Debug_HiCoreSleepStat", "statCoreSleepData sleepDatas size = ", Integer.valueOf(list.size()));
        int a2 = a(igoVar, list);
        boolean z = a2 == Integer.parseInt("06D", 16) || a2 == Integer.parseInt("06E", 16);
        ArrayList arrayList = new ArrayList(10);
        int size = list.size();
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        int i2 = 0;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        double d6 = 0.0d;
        double d7 = 0.0d;
        boolean z2 = false;
        boolean z3 = false;
        double d8 = 0.0d;
        while (i < size) {
            if (list.get(i).getType() == 22103) {
                d += 1.0d;
            } else if (list.get(i).getType() == 22101) {
                d2 += 1.0d;
            } else if (list.get(i).getType() == 22102) {
                d4 += 1.0d;
            } else if (list.get(i).getType() == 22104) {
                if (!z) {
                    d3 += 1.0d;
                }
            } else if (list.get(i).getType() == 22105) {
                d6 += 1.0d;
                arrayList.add(list.get(i));
            } else if (list.get(i).getType() == 22106) {
                d7 += 1.0d;
            } else if (list.get(i).getType() == 22107) {
                d5 += 1.0d;
            } else {
                ReleaseLogUtil.e("Debug_HiCoreSleepStat", "sleepDatas type error = ", Integer.valueOf(list.get(i).getType()));
            }
            if (!z2 && list.get(i).getType() != 22104) {
                z3 = true;
            }
            if (z3 && list.get(i).getType() == 22104) {
                d8 += 1.0d;
                z2 = true;
            }
            if (z3 && z2 && list.get(i).getType() != 22104) {
                i2++;
                if (z) {
                    d3 += d8;
                    d8 = 0.0d;
                }
                z2 = false;
            }
            int i3 = i + 1;
            if (i3 < size && list.get(i).getEndTime() != list.get(i3).getStartTime()) {
                z2 = false;
                z3 = false;
                d8 = 0.0d;
            }
            i = i3;
        }
        Map<String, Long> e = ivq.e(list);
        long b = CommonUtil.b(String.valueOf(e.get("core_sleep_start_time_key")), 0L);
        long b2 = CommonUtil.b(String.valueOf(e.get("core_sleep_end_time_key")), 0L);
        long b3 = CommonUtil.b(String.valueOf(e.get("core_sleep_start_wake_key")), LocationRequestCompat.PASSIVE_INTERVAL);
        long b4 = CommonUtil.b(String.valueOf(e.get("core_sleep_end_wake_key")), 0L);
        c(igoVar, d, d2, d4, d + d2 + d4 + d5);
        e(igoVar, d3, d6, d7 + d5);
        d(igoVar, b, b2, b3, b4);
        c(igoVar, arrayList, d6, list2);
        if (i2 > 0) {
            d(igoVar, i2, 44107, 16);
            str = "Debug_HiCoreSleepStat";
        } else {
            str = "Debug_HiCoreSleepStat";
            ReleaseLogUtil.e(str, "delete sleep wakeCount 44107");
            iwg.a(igoVar, new int[]{44107});
        }
        e(igoVar, list.get(0), list2, list.size() - d6);
        if (z || this.d != 2) {
            return true;
        }
        ReleaseLogUtil.e(str, "delete sleep WearDevice");
        iwg.a(igoVar, new int[]{44203, 44106, 44206, 44208});
        return true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(28:38|(3:139|140|(1:142))|40|(3:132|133|(25:135|136|43|44|(3:121|122|(2:124|125))|46|(5:109|110|111|(1:113)(1:117)|(1:115)(1:116))(1:48)|49|50|(3:101|102|(1:104))|52|(3:54|55|(2:98|99))(1:100)|57|(1:61)|62|(2:66|67)|68|69|70|(4:81|82|83|(2:85|86))(1:72)|73|74|75|77|34))|42|43|44|(0)|46|(0)(0)|49|50|(0)|52|(0)(0)|57|(2:59|61)|62|(3:64|66|67)|68|69|70|(0)(0)|73|74|75|77|34) */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0235, code lost:
    
        r7 = r0;
        r36 = r3;
        r37 = r4;
        r3 = r16;
        r4 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x023f, code lost:
    
        r7 = r0;
        r36 = r3;
        r37 = r4;
        r14 = r10;
        r3 = r16;
        r4 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0231, code lost:
    
        r4 = r38;
        r7 = r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0183 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0169 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01bb A[Catch: JSONException -> 0x01b0, TRY_ENTER, TRY_LEAVE, TryCatch #14 {JSONException -> 0x01b0, blocks: (B:102:0x01a5, B:104:0x01ab, B:54:0x01bb), top: B:101:0x01a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x020f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(defpackage.igo r39, com.huawei.hihealth.HiHealthData r40, java.util.List<java.lang.Integer> r41, double r42) {
        /*
            Method dump skipped, instructions count: 877
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.store.stat.HiCoreSleepStat.e(igo, com.huawei.hihealth.HiHealthData, java.util.List, double):void");
    }

    private void d(JSONObject jSONObject, ProfessionalSleep professionalSleep) throws JSONException {
        if (jSONObject.has("avgHeartrate")) {
            professionalSleep.setLastAvgHeartrate(Integer.valueOf(jSONObject.getInt("avgHeartrate")));
        }
        if (jSONObject.has("minHeartrateBaseline")) {
            professionalSleep.setLastMinHeartrateBaseline(Integer.valueOf(jSONObject.getInt("minHeartrateBaseline")));
        }
        if (jSONObject.has("maxHeartrateBaseline")) {
            professionalSleep.setLastMaxHeartrateBaseline(Integer.valueOf(jSONObject.getInt("maxHeartrateBaseline")));
        }
        if (jSONObject.has("heartrateDayToBaseline")) {
            professionalSleep.setLastHeartrateDayToBaseline(Integer.valueOf(jSONObject.getInt("heartrateDayToBaseline")));
        }
        if (jSONObject.has("avgOxygenSaturation")) {
            professionalSleep.setLastAvgSpO2(Integer.valueOf(jSONObject.getInt("avgOxygenSaturation")));
        }
        if (jSONObject.has("minOxygenSaturationBaseline")) {
            professionalSleep.setLastMinSpO2Baseline(Integer.valueOf(jSONObject.getInt("minOxygenSaturationBaseline")));
        }
        if (jSONObject.has("maxOxygenSaturationBaseline")) {
            professionalSleep.setLastMaxSpO2Baseline(Integer.valueOf(jSONObject.getInt("maxOxygenSaturationBaseline")));
        }
        if (jSONObject.has("oxygenSaturationDayToBaseline")) {
            professionalSleep.setLastSpO2DayToBaseline(Integer.valueOf(jSONObject.getInt("oxygenSaturationDayToBaseline")));
        }
        if (jSONObject.has("avgBreathrate")) {
            professionalSleep.setLastAvgBreathrate(Integer.valueOf(jSONObject.getInt("avgBreathrate")));
        }
        if (jSONObject.has("minBreathrateBaseline")) {
            professionalSleep.setLastMinBreathrateBaseline(Integer.valueOf(jSONObject.getInt("minBreathrateBaseline")));
        }
        if (jSONObject.has("maxBreathrateBaseline")) {
            professionalSleep.setLastMaxBreathrateBaseline(Integer.valueOf(jSONObject.getInt("maxBreathrateBaseline")));
        }
        if (jSONObject.has("breathrateDayToBaseline")) {
            professionalSleep.setLastBreathrateDayToBaseline(Integer.valueOf(jSONObject.getInt("breathrateDayToBaseline")));
        }
        if (jSONObject.has("avgHrv")) {
            professionalSleep.setLastAvgHrv(Integer.valueOf(jSONObject.getInt("avgHrv")));
        }
        if (jSONObject.has("minHrvBaseline")) {
            professionalSleep.setLastMinHrvBaseline(Integer.valueOf(jSONObject.getInt("minHrvBaseline")));
        }
        if (jSONObject.has("maxHrvBaseline")) {
            professionalSleep.setLastMaxHrvBaseline(Integer.valueOf(jSONObject.getInt("maxHrvBaseline")));
        }
        if (jSONObject.has("hrvDayToBaseline")) {
            professionalSleep.setLastHrvDayToBaseline(Integer.valueOf(jSONObject.getInt("hrvDayToBaseline")));
        }
    }

    private void d(igo igoVar, ProfessionalSleep professionalSleep) {
        d(igoVar, professionalSleep.getLastAvgHeartrate(), 44219, 8);
        d(igoVar, professionalSleep.getLastMinHeartrateBaseline(), 44220, 8);
        d(igoVar, professionalSleep.getLastMaxHeartrateBaseline(), 44221, 8);
        d(igoVar, professionalSleep.getLastHeartrateDayToBaseline(), 44222, 0);
        d(igoVar, professionalSleep.getLastAvgSpO2(), 44223, 0);
        d(igoVar, professionalSleep.getLastMinSpO2Baseline(), 44224, 0);
        d(igoVar, professionalSleep.getLastMaxSpO2Baseline(), 44225, 0);
        d(igoVar, professionalSleep.getLastSpO2DayToBaseline(), 44226, 0);
        d(igoVar, professionalSleep.getLastAvgBreathrate(), 44227, 0);
        d(igoVar, professionalSleep.getLastMinBreathrateBaseline(), 44228, 0);
        d(igoVar, professionalSleep.getLastMaxBreathrateBaseline(), 44229, 0);
        d(igoVar, professionalSleep.getLastBreathrateDayToBaseline(), 44230, 0);
        d(igoVar, professionalSleep.getLastAvgHrv(), 44231, 0);
        d(igoVar, professionalSleep.getLastMinHrvBaseline(), 44232, 0);
        d(igoVar, professionalSleep.getLastMaxHrvBaseline(), 44233, 0);
        d(igoVar, professionalSleep.getLastHrvDayToBaseline(), 44234, 0);
    }

    private void d(igo igoVar, Integer num, int i, int i2) {
        if (num != null) {
            d(igoVar, num.intValue(), i, i2);
        }
    }

    private void e(igo igoVar, int i, double d, double d2) {
        ReleaseLogUtil.e("Debug_HiCoreSleepStat", "min ", Integer.valueOf(i), Double.valueOf(d), Double.valueOf(d2));
        if (i != Integer.MAX_VALUE) {
            d(igoVar, i, 44209, 0);
        }
        if (d != Double.MAX_VALUE) {
            d(igoVar, d, 44211, 0);
        }
        if (d2 != Double.MAX_VALUE) {
            d(igoVar, d2, 44213, 0);
        }
    }

    private void c(igo igoVar, int i, double d, double d2) {
        ReleaseLogUtil.e("Debug_HiCoreSleepStat", "max ", Integer.valueOf(i), Double.valueOf(d), Double.valueOf(d2));
        d(igoVar, i, 44210, 0);
        d(igoVar, d, 44212, 0);
        d(igoVar, d2, 44214, 0);
    }

    private void e(igo igoVar, double d, double d2, double d3) {
        if (d > 0.0d) {
            d(igoVar, d, 44104, 15);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep wakeDuration 44104");
            iwg.a(igoVar, new int[]{44104});
        }
        if (d2 > 0.0d) {
            d(igoVar, d2, 44108, 15);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep noonDuration 44108");
            iwg.a(igoVar, new int[]{44108});
        }
        if (d3 > 0.0d) {
            d(igoVar, d3, 44109, 15);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep bedDuration 44109");
            iwg.a(igoVar, new int[]{44109});
        }
    }

    private void d(igo igoVar, long j, long j2, long j3, long j4) {
        if (j > 0) {
            d(igoVar, j, 44201, 5);
        }
        if (j2 > 0) {
            d(igoVar, j2, 44202, 5);
        }
        if (j > j3) {
            d(igoVar, ((j - j3) / 1000) / 60, 44218, 0);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep new latency 44218");
            iwg.a(igoVar, new int[]{44218});
        }
        if (j > j3) {
            d(igoVar, j3, 44205, 5);
        } else {
            d(igoVar, j, 44205, 5);
        }
        if (j4 > j2) {
            d(igoVar, j4, 44216, 5);
        } else {
            d(igoVar, j2, 44216, 5);
        }
    }

    private void c(igo igoVar, double d, double d2, double d3, double d4) {
        if (d3 > 0.0d) {
            d(igoVar, d3, 44101, 15);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep dreamDuration 44101");
            iwg.a(igoVar, new int[]{44101});
        }
        if (d > 0.0d) {
            d(igoVar, d, 44102, 15);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep deep duration 44102");
            iwg.a(igoVar, new int[]{44102});
        }
        if (d2 > 0.0d) {
            d(igoVar, d2, 44103, 15);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep shallowDuration 44103");
            iwg.a(igoVar, new int[]{44103});
        }
        if (d4 > 0.0d) {
            d(igoVar, d4, 44105, 15);
        } else {
            ReleaseLogUtil.e("Debug_HiCoreSleepStat", "delete sleep totalDuration 44105");
            iwg.a(igoVar, new int[]{44105});
        }
    }

    private boolean d(igo igoVar, double d, int i, int i2) {
        igoVar.a(d);
        igoVar.d(i);
        igoVar.h(i2);
        return this.mDataStatManager.e(igoVar, true, igoVar.f() < 50);
    }

    private void c(igo igoVar, List<HiHealthData> list, double d, List<Integer> list2) {
        List<HiNoonSleepInfo.TimeInterval> a2 = ivq.a(list);
        if (HiCommonUtil.d(a2)) {
            a(igoVar);
            return;
        }
        HiNoonSleepInfo hiNoonSleepInfo = new HiNoonSleepInfo();
        hiNoonSleepInfo.e(a2);
        hiNoonSleepInfo.d(d);
        long r = HiDateUtil.r(list.get(0).getStartTime());
        List<HiHealthData> a3 = ivu.d(this.f4209a, DicDataTypeUtil.DataType.NOON_SLEEP_INFO.value()).a(r, r, DicDataTypeUtil.DataType.NOON_SLEEP_INFO.value(), list2);
        int i = (HiCommonUtil.d(a3) || !a3.get(0).getMetaData().equals(HiJsonUtil.e(hiNoonSleepInfo))) ? 0 : 1;
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(DicDataTypeUtil.DataType.NOON_SLEEP_INFO.value());
        hiHealthData.setStartTime(r);
        hiHealthData.setEndTime(r);
        hiHealthData.setMetaData(HiJsonUtil.e(hiNoonSleepInfo));
        hiHealthData.setSyncStatus(i);
        ijj.a(this.f4209a).a(hiHealthData, iis.d().b(this.g, ijf.d(this.f4209a).a("-1"), iip.b().a(this.f4209a.getPackageName())), 0);
    }

    private void a(igo igoVar) {
        int b = iis.d().b(this.g, ijf.d(this.f4209a).a("-1"), iip.b().a(this.f4209a.getPackageName()));
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(b));
        List<HiHealthData> e = ijj.a(this.f4209a).e(HiDateUtil.a(igoVar.e()), HiDateUtil.a(igoVar.e()), new int[]{DicDataTypeUtil.DataType.NOON_SLEEP_INFO.value()}, arrayList);
        if (HiCommonUtil.d(e)) {
            return;
        }
        HiHealthData hiHealthData = e.get(0);
        if (hiHealthData.getSyncStatus() == 0) {
            ijj.a(this.f4209a).a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), hiHealthData.getClientId());
        } else {
            ijj.a(this.f4209a).d(hiHealthData.getDataId(), 2, 2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(defpackage.igo r10, java.util.List<com.huawei.hihealth.HiHealthData> r11) {
        /*
            r9 = this;
            r0 = 0
            java.lang.Object r11 = r11.get(r0)
            com.huawei.hihealth.HiHealthData r11 = (com.huawei.hihealth.HiHealthData) r11
            iis r1 = defpackage.iis.d()
            int r11 = r11.getClientId()
            int r11 = r1.a(r11)
            android.content.Context r1 = r9.f4209a
            ijf r1 = defpackage.ijf.d(r1)
            com.huawei.hihealth.HiDeviceInfo r11 = r1.a(r11)
            java.lang.String r1 = "Debug_HiCoreSleepStat"
            r2 = -1
            if (r11 != 0) goto L2c
            java.lang.String r10 = "deviceInfo is null!"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r10)
            return r2
        L2c:
            int r11 = r11.getDeviceType()
            if (r11 == 0) goto L4d
            java.lang.String r3 = "deviceId"
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.util.List r11 = com.huawei.hihealth.dictionary.constants.ProductMap.b(r3, r11)
            boolean r3 = health.compact.a.HiCommonUtil.d(r11)
            if (r3 != 0) goto L4d
            java.lang.Object r11 = r11.get(r0)
            com.huawei.hihealth.dictionary.constants.ProductMapInfo r11 = (com.huawei.hihealth.dictionary.constants.ProductMapInfo) r11
            java.lang.String r11 = r11.e()
            goto L4e
        L4d:
            r11 = 0
        L4e:
            r0 = 16
            int r11 = health.compact.a.CommonUtil.a(r11, r0)
            if (r11 != r2) goto L60
            java.lang.String r10 = "categoryValue is invalid"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r10)
            return r2
        L60:
            double r5 = (double) r11
            r7 = 44110(0xac4e, float:6.1811E-41)
            r8 = 0
            r3 = r9
            r4 = r10
            r3.d(r4, r5, r7, r8)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.store.stat.HiCoreSleepStat.a(igo, java.util.List):int");
    }
}
