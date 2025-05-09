package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.SportIntensityUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class sqi {
    private static mxf b;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0016 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0019 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001c A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int e(int r1) {
        /*
            r0 = 273(0x111, float:3.83E-43)
            if (r1 == r0) goto L22
            r0 = 274(0x112, float:3.84E-43)
            if (r1 == r0) goto L22
            r0 = 281(0x119, float:3.94E-43)
            if (r1 == r0) goto L1f
            switch(r1) {
                case 257: goto L1f;
                case 258: goto L1c;
                case 259: goto L19;
                case 260: goto L13;
                case 261: goto L13;
                case 262: goto L16;
                default: goto Lf;
            }
        Lf:
            switch(r1) {
                case 264: goto L1c;
                case 265: goto L19;
                case 266: goto L16;
                default: goto L12;
            }
        L12:
            goto L22
        L13:
            r1 = 20004(0x4e24, float:2.8032E-41)
            goto L24
        L16:
            r1 = 20007(0x4e27, float:2.8036E-41)
            goto L24
        L19:
            r1 = 20005(0x4e25, float:2.8033E-41)
            goto L24
        L1c:
            r1 = 20003(0x4e23, float:2.803E-41)
            goto L24
        L1f:
            r1 = 20002(0x4e22, float:2.8029E-41)
            goto L24
        L22:
            r1 = 20001(0x4e21, float:2.8027E-41)
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sqi.e(int):int");
    }

    public static void e(Context context, MotionPathSimplify motionPathSimplify, String str, HiDataInsertOption hiDataInsertOption) {
        String androidId;
        if (context == null || motionPathSimplify == null || hiDataInsertOption == null) {
            LogUtil.h("Track_PluginHealthTrackUtil", "convertTrackDataToHiData context == null || simplifyData == null|| option == null");
            return;
        }
        HiTrackMetaData hiTrackMetaData = new HiTrackMetaData();
        d(motionPathSimplify, hiTrackMetaData);
        if (motionPathSimplify.requestMapType() == 1) {
            hiTrackMetaData.setVendor("AMAP");
        } else if (motionPathSimplify.requestMapType() == 2) {
            hiTrackMetaData.setVendor("GOOGLE");
        } else if (motionPathSimplify.requestMapType() == 3) {
            hiTrackMetaData.setVendor(Constants.HMS);
        } else {
            LogUtil.c("Track_PluginHealthTrackUtil", "should not enter this branch,do not set");
        }
        hiTrackMetaData.setCoordinate(motionPathSimplify.requestMapCoordinate());
        hiTrackMetaData.setIsNewCoordinate(motionPathSimplify.isNewCoordinate());
        hiTrackMetaData.setTotalDescent(motionPathSimplify.requestTotalDescent());
        hiTrackMetaData.setMaxAlti(motionPathSimplify.requestMaxAltitude());
        hiTrackMetaData.setMinAlti(motionPathSimplify.requestMinAltitude());
        a(motionPathSimplify, hiTrackMetaData);
        e(motionPathSimplify, hiTrackMetaData);
        hiTrackMetaData.setChildSportItems(motionPathSimplify.requestChildSportItems());
        hiTrackMetaData.setFatherSportItem(motionPathSimplify.requestFatherSportItem());
        hiTrackMetaData.setMaxSpo2(motionPathSimplify.requestMaxSpo2());
        hiTrackMetaData.setMinSpo2(motionPathSimplify.requestMinSpo2());
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setStartTime(motionPathSimplify.requestStartTime());
        hiHealthData.setEndTime(motionPathSimplify.requestEndTime());
        hiHealthData.setType(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
        hiHealthData.setSequenceFileUrl(str);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        hiHealthData.setMetaData(gsonBuilder.create().toJson(hiTrackMetaData, HiTrackMetaData.class));
        if (!TextUtils.isEmpty(motionPathSimplify.requestDeviceUuid())) {
            androidId = motionPathSimplify.requestDeviceUuid();
        } else {
            androidId = FoundationCommonUtil.getAndroidId(context);
        }
        hiHealthData.setDeviceUuid(androidId);
        hiDataInsertOption.addData(hiHealthData);
    }

    private static void d(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        hiTrackMetaData.setAvgStepRate(motionPathSimplify.requestAvgStepRate());
        hiTrackMetaData.setAvgHeartRate(motionPathSimplify.requestAvgHeartRate());
        hiTrackMetaData.setAvgPace(motionPathSimplify.requestAvgPace());
        hiTrackMetaData.setBestPace(motionPathSimplify.requestBestPace());
        hiTrackMetaData.setPaceMap(motionPathSimplify.requestPaceMap());
        hiTrackMetaData.setBritishPaceMap(motionPathSimplify.requestBritishPaceMap());
        hiTrackMetaData.setPartTimeMap(motionPathSimplify.requestPartTimeMap());
        hiTrackMetaData.setBritishPartTimeMap(motionPathSimplify.requestBritishPartTimeMap());
        hiTrackMetaData.setBestStepRate(motionPathSimplify.requestBestStepRate());
        hiTrackMetaData.setMaxHeartRate(motionPathSimplify.requestMaxHeartRate());
        b(motionPathSimplify, hiTrackMetaData);
        hiTrackMetaData.setTotalSteps(motionPathSimplify.requestTotalSteps());
        hiTrackMetaData.setWearSportData(motionPathSimplify.requestSportData());
        hiTrackMetaData.setExtendTrackDataMap(motionPathSimplify.requestExtendDataMap());
        hiTrackMetaData.setCreepingWave(motionPathSimplify.requestCreepingWave());
        hiTrackMetaData.setMinHeartRate(motionPathSimplify.requestMinHeartRate());
        hiTrackMetaData.setCreepingWave(motionPathSimplify.requestCreepingWave());
        hiTrackMetaData.setRuncourseId(motionPathSimplify.requestRunCourseId());
    }

    private static void b(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        hiTrackMetaData.setSportId(motionPathSimplify.requestSportId());
        hiTrackMetaData.setSportType(motionPathSimplify.requestSportType());
        hiTrackMetaData.setTotalCalories(motionPathSimplify.requestTotalCalories());
        hiTrackMetaData.setTotalDistance(motionPathSimplify.requestTotalDistance());
        hiTrackMetaData.setTotalTime(motionPathSimplify.requestTotalTime());
        hiTrackMetaData.setTrackType(motionPathSimplify.requestTrackType());
        hiTrackMetaData.setAbnormalTrack(motionPathSimplify.requestAbnormalTrack());
        hiTrackMetaData.setIsFreeMotion(motionPathSimplify.isFreeMotion());
        hiTrackMetaData.setSportDataSource(motionPathSimplify.requestSportDataSource());
        hiTrackMetaData.setChiefSportDataType(motionPathSimplify.requestChiefSportDataType());
        hiTrackMetaData.setHasTrackPoint(motionPathSimplify.hasTrackPoint());
        hiTrackMetaData.setDuplicated(motionPathSimplify.requestDuplicated());
        hiTrackMetaData.setHeartrateZoneType(motionPathSimplify.requestHeartRateZoneType());
    }

    private static void e(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        hiTrackMetaData.saveAverageHangTime(motionPathSimplify.requestAverageHangTime());
        hiTrackMetaData.saveGroundHangTimeRate(motionPathSimplify.requestGroundHangTimeRate());
        hiTrackMetaData.setAvgGroundContactTime(motionPathSimplify.requestAvgGroundContactTime());
        hiTrackMetaData.setAvgGroundImpactAcceleration(motionPathSimplify.requestAvgGroundImpactAcceleration());
        hiTrackMetaData.setAvgEversionExcursion(motionPathSimplify.requestAvgEversionExcursion());
        hiTrackMetaData.setAvgSwingAngle(motionPathSimplify.requestAvgSwingAngle());
        hiTrackMetaData.setAvgForeFootStrikePattern(motionPathSimplify.requestAvgForeFootStrikePattern());
        hiTrackMetaData.setAvgWholeFootStrikePattern(motionPathSimplify.requestAvgWholeFootStrikePattern());
        hiTrackMetaData.setAvgHindFootStrikePattern(motionPathSimplify.requestAvgHindFootStrikePattern());
    }

    public static void e(Context context, MotionPathSimplify motionPathSimplify, MotionPath motionPath, HiDataInsertOption hiDataInsertOption) {
        if (context == null || motionPathSimplify == null || motionPath == null || hiDataInsertOption == null) {
            LogUtil.h("Track_PluginHealthTrackUtil", "convertHealthTrackDataToHiData context == null", " || simplifyData == null || motionData == null || option == null");
            return;
        }
        HiTrackMetaData hiTrackMetaData = new HiTrackMetaData();
        c(motionPathSimplify, hiTrackMetaData);
        if (motionPathSimplify.requestMapType() == 1) {
            hiTrackMetaData.setVendor("AMAP");
        } else if (motionPathSimplify.requestMapType() == 2) {
            hiTrackMetaData.setVendor("GOOGLE");
        } else if (motionPathSimplify.requestMapType() == 3) {
            hiTrackMetaData.setVendor(Constants.HMS);
        } else {
            LogUtil.c("Track_PluginHealthTrackUtil", "should not enter this branch,do not set");
        }
        hiTrackMetaData.setCoordinate(motionPathSimplify.requestMapCoordinate());
        hiTrackMetaData.setIsNewCoordinate(motionPathSimplify.isNewCoordinate());
        hiTrackMetaData.setCreepingWave(motionPathSimplify.requestCreepingWave());
        a(motionPathSimplify, hiTrackMetaData);
        hiTrackMetaData.setTotalDescent(motionPathSimplify.requestTotalDescent());
        hiTrackMetaData.setMaxAlti(motionPathSimplify.requestMaxAltitude());
        hiTrackMetaData.setMinAlti(motionPathSimplify.requestMinAltitude());
        e(motionPathSimplify, hiTrackMetaData);
        hiTrackMetaData.setChildSportItems(motionPathSimplify.requestChildSportItems());
        hiTrackMetaData.setFatherSportItem(motionPathSimplify.requestFatherSportItem());
        hiTrackMetaData.setMaxSpo2(motionPathSimplify.requestMaxSpo2());
        hiTrackMetaData.setMinSpo2(motionPathSimplify.requestMinSpo2());
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setStartTime(motionPathSimplify.requestStartTime());
        hiHealthData.setEndTime(motionPathSimplify.requestEndTime());
        hiHealthData.setType(30001);
        hiHealthData.setSequenceData(motionPath.toString());
        hiHealthData.setMetaData(new Gson().toJson(hiTrackMetaData, HiTrackMetaData.class));
        hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
        hiDataInsertOption.addData(hiHealthData);
    }

    private static void c(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        b(motionPathSimplify, hiTrackMetaData);
        hiTrackMetaData.setAvgStepRate(motionPathSimplify.requestAvgStepRate());
        hiTrackMetaData.setAvgHeartRate(motionPathSimplify.requestAvgHeartRate());
        hiTrackMetaData.setPaceMap(motionPathSimplify.requestPaceMap());
        hiTrackMetaData.setBritishPaceMap(motionPathSimplify.requestBritishPaceMap());
        hiTrackMetaData.setPartTimeMap(motionPathSimplify.requestPartTimeMap());
        hiTrackMetaData.setBritishPartTimeMap(motionPathSimplify.requestBritishPartTimeMap());
        hiTrackMetaData.setAvgPace(motionPathSimplify.requestAvgPace());
        hiTrackMetaData.setBestPace(motionPathSimplify.requestBestPace());
        hiTrackMetaData.setBestStepRate(motionPathSimplify.requestBestStepRate());
        hiTrackMetaData.setMaxHeartRate(motionPathSimplify.requestMaxHeartRate());
        hiTrackMetaData.setMinHeartRate(motionPathSimplify.requestMinHeartRate());
        hiTrackMetaData.setTotalSteps(motionPathSimplify.requestTotalSteps());
        hiTrackMetaData.setWearSportData(motionPathSimplify.requestSportData());
        hiTrackMetaData.setExtendTrackDataMap(motionPathSimplify.requestExtendDataMap());
        hiTrackMetaData.setCreepingWave(motionPathSimplify.requestCreepingWave());
    }

    private static void a(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        hiTrackMetaData.setSwolfBase(motionPathSimplify.requestSwolfBase());
        hiTrackMetaData.setBritishSwolfBase(motionPathSimplify.requestBritishSwolfBase());
        hiTrackMetaData.setSwimSegments(motionPathSimplify.requestSwimSegments());
        hiTrackMetaData.setBritishSwimSegments(motionPathSimplify.requestBritishSwimSegments());
    }

    public static HiSyncOption a() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        return hiSyncOption;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0086, code lost:
    
        if (r3 != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hihealth.HiDataInsertOption e(android.content.Context r10, java.util.List<defpackage.gyc> r11, int r12) {
        /*
            java.lang.String r0 = "Track_PluginHealthTrackUtil"
            if (r10 == 0) goto Lde
            if (r11 != 0) goto L8
            goto Lde
        L8:
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 16
            r1.<init>(r2)
            java.lang.String r10 = com.huawei.utils.FoundationCommonUtil.getAndroidId(r10)
            int r12 = e(r12)
            java.util.Iterator r11 = r11.iterator()
        L1b:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto Ld8
            java.lang.Object r2 = r11.next()
            gyc r2 = (defpackage.gyc) r2
            if (r2 != 0) goto L2a
            goto L1b
        L2a:
            float r3 = r2.d()
            float r3 = java.lang.Math.abs(r3)
            r4 = 981668463(0x3a83126f, float:0.001)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            r5 = 0
            if (r3 <= 0) goto L5a
            com.huawei.hihealth.HiHealthData r3 = new com.huawei.hihealth.HiHealthData
            r6 = 3
            r3.<init>(r6)
            long r6 = r2.c()
            long r8 = r2.e()
            r3.setTimeInterval(r6, r8)
            float r6 = r2.d()
            r3.setValue(r6)
            r3.setDeviceUuid(r10)
            r1.add(r3)
            r3 = 1
            goto L5b
        L5a:
            r3 = r5
        L5b:
            float r6 = r2.a()
            float r6 = java.lang.Math.abs(r6)
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 <= 0) goto L86
            com.huawei.hihealth.HiHealthData r3 = new com.huawei.hihealth.HiHealthData
            r4 = 4
            r3.<init>(r4)
            long r6 = r2.c()
            long r8 = r2.e()
            r3.setTimeInterval(r6, r8)
            float r4 = r2.a()
            r3.setValue(r4)
            r3.setDeviceUuid(r10)
            r1.add(r3)
            goto L88
        L86:
            if (r3 == 0) goto La1
        L88:
            com.huawei.hihealth.HiHealthData r3 = new com.huawei.hihealth.HiHealthData
            r3.<init>(r12)
            long r6 = r2.c()
            long r8 = r2.e()
            r3.setTimeInterval(r6, r8)
            r3.setValue(r5)
            r3.setDeviceUuid(r10)
            r1.add(r3)
        La1:
            boolean r3 = c(r12, r2)
            if (r3 == 0) goto L1b
            com.huawei.hihealth.HiHealthData r3 = new com.huawei.hihealth.HiHealthData
            r4 = 7
            r3.<init>(r4)
            long r4 = r2.c()
            long r6 = r2.e()
            r3.setTimeInterval(r4, r6)
            long r4 = r2.c()
            java.lang.Long r2 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = "buildTrackPointDataToHiData getStartTime "
            java.lang.Object[] r2 = new java.lang.Object[]{r4, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            int r2 = c(r12)
            r3.setValue(r2)
            r3.setDeviceUuid(r10)
            r1.add(r3)
            goto L1b
        Ld8:
            com.huawei.hihealth.HiDataInsertOption r10 = new com.huawei.hihealth.HiDataInsertOption
            r10.<init>(r1)
            return r10
        Lde:
            java.lang.String r10 = "buildTrackPointDataToHiData context == null || list == null"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r10)
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sqi.e(android.content.Context, java.util.List, int):com.huawei.hihealth.HiDataInsertOption");
    }

    private static int c(int i) {
        int i2 = i != 20002 ? i != 20003 ? i != 20005 ? 8 : 3 : 2 : 1;
        if (i2 == 8) {
            LogUtil.b("Track_PluginHealthTrackUtil", "unknown high intensity");
        }
        return i2;
    }

    public static HiDataInsertOption c(Context context, List<gxn> list) {
        String androidId = FoundationCommonUtil.getAndroidId(context);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        ArrayList arrayList = new ArrayList();
        for (gxn gxnVar : list) {
            if (gxnVar != null) {
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setTimeInterval(gxnVar.a(), gxnVar.b());
                hiHealthData.setValue(8);
                hiHealthData.setType(7);
                hiHealthData.setDeviceUuid(androidId);
                arrayList.add(hiHealthData);
            }
        }
        hiDataInsertOption.setDatas(arrayList);
        return hiDataInsertOption;
    }

    public static HiDataInsertOption a(Context context, List<gyc> list) {
        if (context == null || list == null) {
            LogUtil.h("Track_PluginHealthTrackUtil", "buildTreadmillStepPointDataToHiData context == null || list == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(16);
        String androidId = FoundationCommonUtil.getAndroidId(context);
        for (gyc gycVar : list) {
            if (gycVar != null && gycVar.h() > 0) {
                HiHealthData hiHealthData = new HiHealthData(2);
                hiHealthData.setTimeInterval(gycVar.c(), gycVar.e());
                hiHealthData.setValue(gycVar.h());
                hiHealthData.setDeviceUuid(androidId);
                arrayList.add(hiHealthData);
                HiHealthData hiHealthData2 = new HiHealthData(20003);
                hiHealthData2.setTimeInterval(gycVar.c(), gycVar.e());
                hiHealthData2.setValue(0);
                hiHealthData2.setDeviceUuid(androidId);
                arrayList.add(hiHealthData2);
            }
        }
        return new HiDataInsertOption(arrayList);
    }

    private static boolean c(int i, gyc gycVar) {
        if (i != 20002 && i != 20003 && i != 20005) {
            return false;
        }
        if (b == null) {
            b = SportIntensityUtil.b();
        }
        int n = b.n();
        if (i == 20005) {
            n = b.i();
        }
        float f = (n * 100.0f) / 60.0f;
        LogUtil.a("Track_PluginHealthTrackUtil", "buildTrackPointDataToHiData speed ", Float.valueOf(gycVar.b()), " speedThreshold ", Float.valueOf(f));
        return Float.compare(gycVar.b(), f) >= 0;
    }
}
