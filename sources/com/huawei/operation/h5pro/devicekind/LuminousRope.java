package com.huawei.operation.h5pro.devicekind;

import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.operation.h5pro.ble.BleOperatorCompactImpl;
import health.compact.a.Services;

/* loaded from: classes9.dex */
public class LuminousRope {
    private static final int AEROBIC_ADVANCE_THRESHOLD_POSITION = 1;
    private static final int AEROBIC_BASE_THRESHOLD_POSITION = 0;
    private static final int AEROBIC_THRESHOLD_POSITION = 3;
    private static final int ANAEROBIC_ADVANCE_THRESHOLD_POSITION = 4;
    private static final int ANAEROBIC_BASE_THRESHOLD_POSITION = 3;
    private static final int ANAEROBIC_THRESHOLD_POSITION = 4;
    private static final int FAT_BURN_THRESHOLD_POSITION = 2;
    private static final int FITNESS_THRESHOLD_POSITION = 0;
    private static final int LACTIC_ACID_THRESHOLD_POSITION = 2;
    private static final int MAXIMUM_MAX_THRESHOLD_POSITION = 5;
    private static final int MAX_THRESHOLD_POSITION = 5;
    private static final String TAG = "LuminousRope";
    private static final String TYPE_MAXIMUM_HEART_RATE = "0";
    private static final String TYPE_RESERVED_HEART_RATE = "1";
    private static final int WARM_UP_THRESHOLD_POSITION = 1;
    private BleOperatorCompactImpl mBleOperatorCompactImpl;

    public LuminousRope(BleOperatorCompactImpl bleOperatorCompactImpl) {
        this.mBleOperatorCompactImpl = bleOperatorCompactImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008d A[Catch: JSONException -> 0x00c2, TryCatch #0 {JSONException -> 0x00c2, blocks: (B:12:0x002a, B:21:0x0058, B:22:0x00b7, B:26:0x0062, B:27:0x008d, B:28:0x003c, B:31:0x0046), top: B:11:0x002a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void requestHeartRateInterval(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            com.huawei.operation.h5pro.ble.BleOperatorCompactImpl r0 = r11.mBleOperatorCompactImpl
            java.lang.String r1 = "LuminousRope"
            if (r0 != 0) goto L10
            java.lang.String r12 = "bleOperator is null"
            java.lang.Object[] r12 = new java.lang.Object[]{r12}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r12)
            return
        L10:
            r0 = 4
            com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig r2 = r11.requestHeartRateInterval(r0)
            r3 = 1
            if (r2 != 0) goto L24
            com.huawei.operation.h5pro.ble.BleOperatorCompactImpl r12 = r11.mBleOperatorCompactImpl
            java.lang.String r0 = ""
            java.lang.String r1 = java.lang.String.valueOf(r3)
            r12.requestHeartRateInterval(r0, r1, r13)
            return
        L24:
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            r5 = 0
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch: org.json.JSONException -> Lc2
            r6.<init>()     // Catch: org.json.JSONException -> Lc2
            int r7 = r12.hashCode()     // Catch: org.json.JSONException -> Lc2
            r8 = 48
            if (r7 == r8) goto L46
            r8 = 49
            if (r7 == r8) goto L3c
            goto L50
        L3c:
            java.lang.String r7 = "1"
            boolean r7 = r12.equals(r7)     // Catch: org.json.JSONException -> Lc2
            if (r7 == 0) goto L50
            r7 = r5
            goto L51
        L46:
            java.lang.String r7 = "0"
            boolean r7 = r12.equals(r7)     // Catch: org.json.JSONException -> Lc2
            if (r7 == 0) goto L50
            r7 = r3
            goto L51
        L50:
            r7 = -1
        L51:
            r8 = 5
            r9 = 3
            r10 = 2
            if (r7 == 0) goto L8d
            if (r7 == r3) goto L62
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: org.json.JSONException -> Lc2
            java.lang.String r2 = "requestHeartRateInterval other type"
            r0[r5] = r2     // Catch: org.json.JSONException -> Lc2
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)     // Catch: org.json.JSONException -> Lc2
            goto Lb7
        L62:
            int r7 = r2.getFitnessThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r5, r7)     // Catch: org.json.JSONException -> Lc2
            int r7 = r2.getWarmUpThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r3, r7)     // Catch: org.json.JSONException -> Lc2
            int r3 = r2.getFatBurnThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r10, r3)     // Catch: org.json.JSONException -> Lc2
            int r3 = r2.getAerobicThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r9, r3)     // Catch: org.json.JSONException -> Lc2
            int r3 = r2.getAnaerobicThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r0, r3)     // Catch: org.json.JSONException -> Lc2
            int r0 = r2.getMaxThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r8, r0)     // Catch: org.json.JSONException -> Lc2
            goto Lb7
        L8d:
            int r7 = r2.getAerobicBaseThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r5, r7)     // Catch: org.json.JSONException -> Lc2
            int r7 = r2.getAerobicAdvanceThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r3, r7)     // Catch: org.json.JSONException -> Lc2
            int r3 = r2.getLacticAcidThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r10, r3)     // Catch: org.json.JSONException -> Lc2
            int r3 = r2.getAnaerobicBaseThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r9, r3)     // Catch: org.json.JSONException -> Lc2
            int r3 = r2.getAnaerobicAdvanceThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r0, r3)     // Catch: org.json.JSONException -> Lc2
            int r0 = r2.getMaxThreshold()     // Catch: org.json.JSONException -> Lc2
            r6.put(r8, r0)     // Catch: org.json.JSONException -> Lc2
        Lb7:
            java.lang.String r0 = "type"
            r4.put(r0, r12)     // Catch: org.json.JSONException -> Lc2
            java.lang.String r12 = "interval"
            r4.put(r12, r6)     // Catch: org.json.JSONException -> Lc2
            goto Lcb
        Lc2:
            java.lang.String r12 = "requestHeartRateInterval fail JSONException"
            java.lang.Object[] r12 = new java.lang.Object[]{r12}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r12)
        Lcb:
            com.huawei.operation.h5pro.ble.BleOperatorCompactImpl r12 = r11.mBleOperatorCompactImpl
            java.lang.String r0 = r4.toString()
            java.lang.String r1 = java.lang.String.valueOf(r5)
            r12.requestHeartRateInterval(r0, r1, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.devicekind.LuminousRope.requestHeartRateInterval(java.lang.String, java.lang.String):void");
    }

    private HeartRateThresholdConfig requestHeartRateInterval(int i) {
        HeartRateZoneMgr heartRateZoneMgrByCache = ((HealthDataMgrApi) Services.c("HWHealthDataMgr", HealthDataMgrApi.class)).getHeartRateZoneMgrByCache();
        if (heartRateZoneMgrByCache == null) {
            return null;
        }
        return heartRateZoneMgrByCache.getPostureType(i);
    }
}
