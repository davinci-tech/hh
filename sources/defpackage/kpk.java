package defpackage;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.StudentHeartRateZoneMgr;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogUtil;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class kpk {
    private StudentHeartRateZoneMgr e = new StudentHeartRateZoneMgr(15);

    public kpk() {
        b();
    }

    public StudentHeartRateZoneMgr c() {
        return this.e;
    }

    public final void b() {
        String studentHeartData = this.e.getStudentHeartData();
        LogUtil.c("Heart_StudentDataManager", "updateStudentHeartDataFromSp data", studentHeartData);
        if (TextUtils.isEmpty(studentHeartData)) {
            LogUtil.e("Heart_StudentDataManager", "db data is null");
            return;
        }
        HeartRateThresholdConfig heartRateThresholdConfig = (HeartRateThresholdConfig) gvv.a(studentHeartData, new TypeToken<HeartRateThresholdConfig>() { // from class: kpk.3
        });
        if (heartRateThresholdConfig == null) {
            LogUtil.e("Heart_StudentDataManager", "fromJson data is null");
            return;
        }
        this.e.setHeartRateConfig(heartRateThresholdConfig.getWarningEnable(), heartRateThresholdConfig.getWarningLimit(), heartRateThresholdConfig.getClassifyMethod(), heartRateThresholdConfig.getMaxThreshold(), heartRateThresholdConfig.getRestHeartRate());
        this.e.setMaxHeartRateThreshold(heartRateThresholdConfig.getAnaerobicThreshold(), heartRateThresholdConfig.getAerobicThreshold(), heartRateThresholdConfig.getFatBurnThreshold(), heartRateThresholdConfig.getWarmUpThreshold(), heartRateThresholdConfig.getFitnessThreshold());
        this.e.setHrrHeartRateZoneThreshold(heartRateThresholdConfig.getAnaerobicAdvanceThreshold(), heartRateThresholdConfig.getAnaerobicBaseThreshold(), heartRateThresholdConfig.getLacticAcidThreshold(), heartRateThresholdConfig.getAerobicAdvanceThreshold(), heartRateThresholdConfig.getAerobicBaseThreshold());
        this.e.getStudentHeartRateThresholdData().setOldMaxThreshold(heartRateThresholdConfig.getOldMaxThreshold());
        this.e.getStudentHeartRateThresholdData().getHeartZoneStateConfig().setIsSetWarningLimit(heartRateThresholdConfig.getHeartZoneStateConfig().getIsSetWarningLimit());
        this.e.getStudentHeartRateThresholdData().getHeartZoneStateConfig().setIsSetMaxHeart(heartRateThresholdConfig.getHeartZoneStateConfig().getIsSetMaxHeart());
        LogUtil.c("Heart_StudentDataManager", "updateStudentHeartDataFromSp mStudentHeartRateZoneMgr", this.e.getStudentHeartRateThresholdData().toString());
    }

    public void c(int i) {
        boolean isSetMaxHeart = this.e.getStudentHeartRateThresholdData().getHeartZoneStateConfig().getIsSetMaxHeart();
        LogUtil.c("Heart_StudentDataManager", "is StudentMaxHeart", Boolean.valueOf(isSetMaxHeart));
        if (isSetMaxHeart) {
            return;
        }
        if (i == 0) {
            LogUtil.a("Heart_StudentDataManager", "StudentMaxHeart age error");
            return;
        }
        int i2 = 220 - i;
        this.e.getStudentHeartRateThresholdData().setOldMaxThreshold(this.e.getStudentHeartRateThresholdData().getMaxThreshold());
        if (!this.e.getStudentWarningLimitStatus()) {
            this.e.getStudentHeartRateThresholdData().setWarningLimit(i2);
        }
        LogUtil.c("Heart_StudentDataManager", "onStudentAgeChanged maxHeart", Integer.valueOf(i2));
        this.e.getStudentHeartRateThresholdData().setMaxThreshold(i2);
        this.e.setStudentHeartDataToSp();
    }

    public void d() {
        if (this.e.getStudentHeartRateThresholdData().getClassifyMethod() == 1) {
            this.e.getStudentHeartRateThresholdData().setRestHeartRate(this.e.getStudentHeartRateThresholdData().getRestHeartRateDefault());
        }
        this.e.getStudentHeartRateThresholdData().resetHeartZoneConf(e());
    }

    public int e() {
        String d = KeyValDbManager.b(BaseApplication.getContext()).d("family_mode_student_age_key", new StorageParams(1));
        if (CommonUtil.e(d, 15) == 0) {
            return 15;
        }
        return CommonUtil.e(d, 15);
    }

    public void d(int i) {
        if (i == 0) {
            LogUtil.a("Heart_StudentDataManager", "StudentMaxHeart age error");
            i = 15;
        }
        StudentHeartRateZoneMgr studentHeartRateZoneMgr = new StudentHeartRateZoneMgr(i);
        this.e = studentHeartRateZoneMgr;
        LogUtil.c("Heart_StudentDataManager", "resetHeartZoneData mStudentHeartRateZoneMgr=", studentHeartRateZoneMgr.getStudentHeartRateThresholdData().toString());
    }
}
