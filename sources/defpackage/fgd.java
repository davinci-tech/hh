package defpackage;

import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class fgd {
    public static int b(int i) {
        HiUserPreference userPreference;
        if (i < 1 || i > 4) {
            i = 1;
        }
        int i2 = 0;
        if (i == 1 && (userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_HeartRate_Classify_Method")) != null) {
            i2 = CommonUtil.e(userPreference.getValue(), 0);
        }
        HeartZoneConf a2 = a(i);
        return a2 != null ? a2.getClassifyMethod() : i2;
    }

    public static List<String> c(int i) {
        ArrayList arrayList = new ArrayList(10);
        if (i == 1) {
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_aerobicBase_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_aerobicAdvance_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_lacticAcid_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_anaerobicBase_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_anaerobicAdvance_threshold));
        } else if (i == 3) {
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_lactate_subHeader_top8));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_aerobicBase_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_aerobicAdvance_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_hrr_lacticAcid_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_lactate_subHeader_top7));
        } else {
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_warmup_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_fatburn_threshold_string));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_aerobic_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_anaerobic_threshold));
            arrayList.add(BaseApplication.getContext().getString(R$string.IDS_rate_zone_maximum_threshold));
        }
        return arrayList;
    }

    public static HeartZoneConf e(int i, int i2) {
        HeartZoneConf heartZoneConf;
        if (i < 1 || i > 4) {
            i = 1;
        }
        if (i == 1) {
            heartZoneConf = a();
            LogUtil.a("Track_HeartRateConfigUtils", "get standing posture data");
        } else {
            heartZoneConf = null;
        }
        HeartZoneConf a2 = a(i);
        if (a2 != null) {
            LogUtil.a("Track_HeartRateConfigUtils", "get multiple heart rate data ", Integer.valueOf(a2.getClassifyMethod()));
            heartZoneConf = a2;
        }
        if (heartZoneConf != null) {
            return heartZoneConf;
        }
        HeartZoneConf heartZoneConf2 = new HeartZoneConf(i2);
        LogUtil.a("Track_HeartRateConfigUtils", "no heart rate data ", Integer.valueOf(heartZoneConf2.getMaxThreshold()));
        return heartZoneConf2;
    }

    public static HeartZoneConf c(int i, int i2, int i3) {
        HeartZoneConf e = e(i, i2);
        LogUtil.a("Track_HeartRateConfigUtils", "get Classify Method ", Integer.valueOf(e.getClassifyMethod()));
        e.setClassifyMethod(i3);
        return e;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf a() {
        /*
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r0 = com.huawei.hihealth.api.HiHealthManager.d(r0)
            java.lang.String r1 = "custom.UserPreference_HeartZone_Config"
            com.huawei.hihealth.HiUserPreference r0 = r0.getUserPreference(r1)
            r1 = 0
            r2 = 2
            java.lang.String r3 = ","
            r4 = 1
            if (r0 == 0) goto L36
            java.lang.String r5 = r0.getValue()
            if (r5 == 0) goto L36
            java.lang.String r0 = r0.getValue()
            java.lang.String[] r0 = r0.split(r3)
            int r5 = r0.length
            if (r5 != r2) goto L36
            com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf r5 = new com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf
            r5.<init>()
            r6 = r0[r1]
            r5.setHRZoneConf(r6)
            r0 = r0[r4]
            r5.setThreshold(r0)
            goto L37
        L36:
            r5 = 0
        L37:
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r0 = com.huawei.hihealth.api.HiHealthManager.d(r0)
            java.lang.String r6 = "custom.UserPreference_HRRHeartZone_Config"
            com.huawei.hihealth.HiUserPreference r0 = r0.getUserPreference(r6)
            if (r0 == 0) goto L6a
            java.lang.String r6 = r0.getValue()
            if (r6 == 0) goto L6a
            java.lang.String r0 = r0.getValue()
            java.lang.String[] r0 = r0.split(r3)
            int r3 = r0.length
            if (r3 != r2) goto L6a
            if (r5 != 0) goto L60
            com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf r2 = new com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf
            r2.<init>()
            r5 = r2
        L60:
            r1 = r0[r1]
            r5.setHRRHRZoneConf(r1)
            r0 = r0[r4]
            r5.setHrrThreshold(r0)
        L6a:
            if (r5 == 0) goto L73
            int r0 = b(r4)
            r5.setClassifyMethod(r0)
        L73:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fgd.a():com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf");
    }

    public static HeartZoneConf a(int i) {
        HeartRateZoneMgr heartRateZoneMgr;
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi != null) {
            LogUtil.h("Track_HeartRateConfigUtils", "getHeartRateConfigByNewDb healthDataMgrApi not null");
            heartRateZoneMgr = healthDataMgrApi.getHeartRateZoneMgrByCache();
        } else {
            heartRateZoneMgr = null;
        }
        HeartZoneConf heartZoneConf = new HeartZoneConf();
        if (heartRateZoneMgr == null) {
            LogUtil.b("Track_HeartRateConfigUtils", "getPostureHeartZoneConf object != null");
            return heartZoneConf;
        }
        if (i < 1 || i > 4) {
            i = 1;
        }
        HeartRateThresholdConfig postureType = heartRateZoneMgr.getPostureType(i);
        if (postureType == null) {
            LogUtil.b("Track_HeartRateConfigUtils", "temp == null");
            return heartZoneConf;
        }
        d(heartZoneConf, postureType);
        heartZoneConf.setAnaerobicThreshold(postureType.getAnaerobicThreshold());
        heartZoneConf.setAerobicThreshold(postureType.getAerobicThreshold());
        heartZoneConf.setFatBurnThreshold(postureType.getFatBurnThreshold());
        heartZoneConf.setWarmUpThreshold(postureType.getWarmUpThreshold());
        heartZoneConf.setFitnessThreshold(postureType.getFitnessThreshold());
        heartZoneConf.setAnaerobicAdvanceThreshold(postureType.getAnaerobicAdvanceThreshold());
        heartZoneConf.setAnaerobicBaseThreshold(postureType.getAnaerobicBaseThreshold());
        heartZoneConf.setLacticAcidThreshold(postureType.getLacticAcidThreshold());
        heartZoneConf.setAerobicAdvanceThreshold(postureType.getAerobicAdvanceThreshold());
        heartZoneConf.setAerobicBaseThreshold(postureType.getAerobicBaseThreshold());
        heartZoneConf.setLactateThresholdHeartRate(postureType.getLactateThresholdHeartRate());
        heartZoneConf.setLthrAnaerobicInterval(postureType.getLthrAnaerobicInterval());
        heartZoneConf.setLthrLactateThreshold(postureType.getLthrLactateThreshold());
        heartZoneConf.setLthrAerobicHighZone(postureType.getLthrAerobicHighZone());
        heartZoneConf.setLthrAerobicLowZone(postureType.getLthrAerobicLowZone());
        heartZoneConf.setLthrRecoveryInterval(postureType.getLthrRecoveryInterval());
        LogUtil.a("Track_HeartRateConfigUtils", "getClassifyMethod = ", Integer.valueOf(postureType.getClassifyMethod()));
        return heartZoneConf;
    }

    private static void d(HeartZoneConf heartZoneConf, HeartRateThresholdConfig heartRateThresholdConfig) {
        heartZoneConf.setClassifyMethod(heartRateThresholdConfig.getClassifyMethod());
        heartZoneConf.setMaxThreshold(heartRateThresholdConfig.getMaxThreshold());
        heartZoneConf.setRestHeartRate(heartRateThresholdConfig.getRestHeartRate());
        heartZoneConf.setWarningEnble(heartRateThresholdConfig.getWarningEnable());
        heartZoneConf.setWarningEnbleHRR(heartRateThresholdConfig.getWarningEnable());
        heartZoneConf.setWarningLimitHR(heartRateThresholdConfig.getWarningLimit());
        heartZoneConf.setWarningLimitHRHRR(heartRateThresholdConfig.getWarningLimit());
    }
}
