package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class kox {

    /* renamed from: a, reason: collision with root package name */
    private static kox f14513a;
    private static final Object c = new Object();
    private kpk b = new kpk();
    private HeartRateZoneMgr d = new HeartRateZoneMgr(30);

    private kox() {
    }

    public static kox e() {
        kox koxVar;
        synchronized (c) {
            if (f14513a == null) {
                f14513a = new kox();
            }
            koxVar = f14513a;
        }
        return koxVar;
    }

    public kpk a() {
        return this.b;
    }

    private HeartRateThresholdConfig j() {
        return this.b.c().getStudentHeartRateThresholdData();
    }

    public HeartRateZoneMgr d() {
        return this.d;
    }

    public void c(boolean z, int i) {
        if (z) {
            a().d();
            return;
        }
        if (this.d.getPostureType(i).getClassifyMethod() == 1) {
            this.d.getPostureType(i).setRestHeartRate(this.d.getPostureType(i).getRestHeartRateDefault());
        }
        this.d.getPostureType(i).resetHeartZoneConf(kor.a().n().getAgeOrDefaultValue());
        if (i == 1) {
            kor.a().l();
        }
    }

    public int d(boolean z, int i) {
        if (z) {
            return a().c().getStudentHeartRateThresholdData().getRestHeartRate();
        }
        return this.d.getPostureType(i).getRestHeartRate();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf r7, com.huawei.up.model.UserInfomation r8) {
        /*
            r6 = this;
            java.lang.String r0 = " updateAllPostureData"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Heart_HeartRateDataManager"
            health.compact.a.LogUtil.c(r1, r0)
            com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr r0 = new com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr
            int r2 = r8.getAgeOrDefaultValue()
            r0.<init>(r2)
            r6.d = r0
            r6.e(r7)
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r0 = com.huawei.hihealth.api.HiHealthManager.d(r0)
            java.lang.String r2 = "userPreference_HeartRate_all_posture_data"
            com.huawei.hihealth.HiUserPreference r0 = r0.getUserPreference(r2)
            r2 = 2
            if (r0 == 0) goto L56
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.NumberFormatException -> L4c
            java.lang.String r4 = " allPostureData =  "
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.NumberFormatException -> L4c
            java.lang.String r4 = r0.getValue()     // Catch: java.lang.NumberFormatException -> L4c
            r5 = 1
            r3[r5] = r4     // Catch: java.lang.NumberFormatException -> L4c
            health.compact.a.LogUtil.c(r1, r3)     // Catch: java.lang.NumberFormatException -> L4c
            java.lang.String r0 = r0.getValue()     // Catch: java.lang.NumberFormatException -> L4c
            kox$5 r3 = new kox$5     // Catch: java.lang.NumberFormatException -> L4c
            r3.<init>()     // Catch: java.lang.NumberFormatException -> L4c
            java.lang.Object r0 = defpackage.gvv.a(r0, r3)     // Catch: java.lang.NumberFormatException -> L4c
            com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr r0 = (com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr) r0     // Catch: java.lang.NumberFormatException -> L4c
            goto L61
        L4c:
            java.lang.String r0 = " updateAllPostureData parse value fail "
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.e(r1, r0)
            goto L60
        L56:
            java.lang.String r0 = "userDate is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.a(r1, r0)
        L60:
            r0 = 0
        L61:
            boolean r3 = r6.e(r0)
            if (r3 == 0) goto L84
            com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr r3 = r6.d
            boolean r3 = r6.e(r3)
            if (r3 == 0) goto L84
            java.lang.String r8 = " set heart data"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.LogUtil.c(r1, r8)
            r6.e(r0, r2, r7)
            r8 = 3
            r6.e(r0, r8, r7)
            r8 = 4
            r6.e(r0, r8, r7)
            goto L9f
        L84:
            boolean r7 = r6.i()
            java.lang.String r0 = "allPostureData is null and Birthday data is "
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r7)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            health.compact.a.LogUtil.c(r1, r0)
            if (r7 == 0) goto L98
            return
        L98:
            int r7 = r8.getAgeOrDefaultValue()
            r6.d(r7)
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kox.b(com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf, com.huawei.up.model.UserInfomation):void");
    }

    private void d(int i) {
        if (this.d.getPostureType(2) == null) {
            LogUtil.e("Heart_HeartRateDataManager", "RIDING_POSTURE is null ");
            c(2, i);
        }
        if (this.d.getPostureType(3) == null) {
            LogUtil.e("Heart_HeartRateDataManager", "STROKES_POSTURE is null ");
            c(3, i);
        }
        if (this.d.getPostureType(4) == null) {
            LogUtil.e("Heart_HeartRateDataManager", "OTHER_POSTURE is null ");
            c(4, i);
        }
    }

    private void c(int i, int i2) {
        this.d.setHeartRateThreshold(i, new HeartRateThresholdConfig(i, i2));
    }

    private boolean i() {
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1006);
        if (!TextUtils.isEmpty(accountInfo) && !Integer.toString(-100).equals(accountInfo)) {
            return false;
        }
        HeartRateThresholdConfig heartRateThresholdConfig = new HeartRateThresholdConfig(1, 25);
        this.d.setHeartRateThreshold(1, heartRateThresholdConfig);
        this.d.setHeartRateThreshold(2, heartRateThresholdConfig);
        this.d.setHeartRateThreshold(3, heartRateThresholdConfig);
        this.d.setHeartRateThreshold(4, heartRateThresholdConfig);
        return true;
    }

    private boolean e(HeartRateZoneMgr heartRateZoneMgr) {
        if (heartRateZoneMgr == null) {
            LogUtil.a("Heart_HeartRateDataManager", "allPostureData == null");
            return false;
        }
        if (heartRateZoneMgr.getPostureType(2) != null && heartRateZoneMgr.getPostureType(3) != null && heartRateZoneMgr.getPostureType(4) != null) {
            return true;
        }
        LogUtil.a("Heart_HeartRateDataManager", "allPostureData.getPostureType == null");
        return false;
    }

    private void e(HeartRateZoneMgr heartRateZoneMgr, int i, HeartZoneConf heartZoneConf) {
        boolean z;
        boolean z2;
        int warningLimit = heartZoneConf.getHeartRateZoneMgr().getPostureType(i).getWarningLimit();
        HeartRateThresholdConfig.HeartZoneStateConfig heartZoneStateConfig = heartRateZoneMgr.getPostureType(i).getHeartZoneStateConfig();
        if (heartZoneStateConfig != null) {
            z = heartZoneStateConfig.getIsSetWarningLimit();
            z2 = heartZoneStateConfig.getIsSetMaxHeart();
        } else {
            z = false;
            z2 = false;
        }
        if (z) {
            warningLimit = heartRateZoneMgr.getPostureType(i).getWarningLimit();
        }
        int i2 = warningLimit;
        int maxThreshold = heartZoneConf.getHeartRateZoneMgr().getPostureType(i).getMaxThreshold();
        if (z2) {
            maxThreshold = heartRateZoneMgr.getPostureType(i).getMaxThreshold();
        }
        int i3 = maxThreshold;
        HeartRateThresholdConfig postureType = this.d.getPostureType(i);
        if (postureType != null) {
            postureType.setWarningLimit(i2);
            postureType.setMaxThreshold(i3);
            HeartRateThresholdConfig.HeartZoneStateConfig heartZoneStateConfig2 = postureType.getHeartZoneStateConfig();
            if (heartZoneStateConfig2 != null) {
                heartZoneStateConfig2.setIsSetMaxHeart(z2);
                heartZoneStateConfig2.setIsSetWarningLimit(z);
            }
        }
        this.d.setHeartRateConfig(i, heartRateZoneMgr.getPostureType(i).getWarningEnable(), i2, heartRateZoneMgr.getPostureType(i).getClassifyMethod(), i3, heartRateZoneMgr.getPostureType(i).getRestHeartRate());
        this.d.setMaxHeartRateThreshold(i, heartRateZoneMgr.getPostureType(i).getAnaerobicThreshold(), heartRateZoneMgr.getPostureType(i).getAerobicThreshold(), heartRateZoneMgr.getPostureType(i).getFatBurnThreshold(), heartRateZoneMgr.getPostureType(i).getWarmUpThreshold(), heartRateZoneMgr.getPostureType(i).getFitnessThreshold());
        this.d.setHrrHeartRateZoneThreshold(i, heartRateZoneMgr.getPostureType(i).getAnaerobicAdvanceThreshold(), heartRateZoneMgr.getPostureType(i).getAnaerobicBaseThreshold(), heartRateZoneMgr.getPostureType(i).getLacticAcidThreshold(), heartRateZoneMgr.getPostureType(i).getAerobicAdvanceThreshold(), heartRateZoneMgr.getPostureType(i).getAerobicBaseThreshold());
    }

    public void e(final boolean z, final int i) {
        jdx.b(new Runnable() { // from class: kox.3
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    kox.this.d.getPostureType(i).getHeartZoneStateConfig().setIsSetMaxHeart(true);
                    if (i != 1) {
                        return;
                    }
                    kox.this.g();
                    return;
                }
                kox.this.b.c().getStudentHeartRateThresholdData().getHeartZoneStateConfig().setIsSetMaxHeart(true);
            }
        });
    }

    public void g() {
        jdx.b(new Runnable() { // from class: kox.4
            @Override // java.lang.Runnable
            public void run() {
                HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).setUserPreference(new HiUserPreference("custom.UserPreference_HeartRate_Flag", "1"), true);
                LogUtil.c("Heart_HeartRateDataManager", "saveHeartRateMaxValueDataToDB finish.");
            }
        });
    }

    public void c(final int i) {
        jdx.b(new Runnable() { // from class: kox.1
            @Override // java.lang.Runnable
            public void run() {
                kox.this.d.getPostureType(i).getHeartZoneStateConfig().setIsSetWarningLimit(true);
                if (i != 1) {
                    return;
                }
                kox.this.c();
            }
        });
    }

    public void c() {
        jdx.b(new Runnable() { // from class: kox.2
            @Override // java.lang.Runnable
            public void run() {
                HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).setUserPreference(new HiUserPreference("custom.UserPreference_HeartRate_Limit_Flag", "1"), true);
                LogUtil.c("Heart_HeartRateDataManager", "saveHeartRateLimitValueDataToDB finish.");
            }
        });
    }

    public void f() {
        jdx.b(new Runnable() { // from class: kox.7
            @Override // java.lang.Runnable
            public void run() {
                kox.this.f(1);
                kox.this.f(2);
                kox.this.f(3);
                kox.this.f(4);
                String json = new Gson().toJson(kox.this.d);
                LogUtil.c("Heart_HeartRateDataManager", "writeAllPostureDataToDataBase", json);
                HiHealthManager.d(BaseApplication.e()).setUserPreference(new HiUserPreference("userPreference_HeartRate_all_posture_data", json), true);
                kor.a().b((HeartZoneConf) null);
            }
        });
    }

    public HeartRateZoneMgr b() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("userPreference_HeartRate_all_posture_data");
        if (userPreference != null && !TextUtils.isEmpty(userPreference.getValue())) {
            try {
                return (HeartRateZoneMgr) new Gson().fromJson(userPreference.getValue(), HeartRateZoneMgr.class);
            } catch (JsonSyntaxException | NumberFormatException unused) {
                LogUtil.e("Heart_HeartRateDataManager", " getHeartRateZoneMgrByCache exception");
                return null;
            }
        }
        LogUtil.a("Heart_HeartRateDataManager", "hiUserPreference null");
        return d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        this.d.getPostureType(i).setOldMaxThreshold(this.d.getPostureType(i).getMaxThreshold());
    }

    public void a(int i, int i2) {
        if (i == 1) {
            kor.a().e(i2);
        }
        this.d.getPostureType(i).setClassifyMethod(i2);
    }

    public void a(boolean z, int i, int i2) {
        if (z) {
            j().setRestHeartRate(i2);
            return;
        }
        if (i == 1) {
            kor.a().d(i2);
        }
        this.d.getPostureType(i).setRestHeartRate(i2);
    }

    public void b(boolean z, int i, boolean z2, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        LogUtil.c("Heart_HeartRateDataManager", "Enter setHeartRateZoneSettingInfo !", Boolean.valueOf(z));
        if (z) {
            j().setWarningEnable(z2);
            j().setWarningLimit(i2);
            j().setMaxThreshold(i3);
            j().setAnaerobicThreshold(i4);
            j().setAerobicThreshold(i5);
            j().setFatBurnThreshold(i6);
            j().setWarmUpThreshold(i7);
            j().setFitnessThreshold(i8);
            return;
        }
        new HeartZoneConf();
        this.d.getPostureType(i).setWarningEnable(z2);
        this.d.getPostureType(i).setWarningLimit(i2);
        this.d.getPostureType(i).setMaxThreshold(i3);
        this.d.getPostureType(i).setAnaerobicThreshold(i4);
        this.d.getPostureType(i).setAerobicThreshold(i5);
        this.d.getPostureType(i).setFatBurnThreshold(i6);
        this.d.getPostureType(i).setWarmUpThreshold(i7);
        this.d.getPostureType(i).setFitnessThreshold(i8);
        if (i != 1) {
            return;
        }
        a(this.d.getPostureType(i));
        LogUtil.c("Heart_HeartRateDataManager", "Leave setHeartRateZoneSettingInfo !");
    }

    public void a(HeartRateThresholdConfig heartRateThresholdConfig) {
        LogUtil.c("Heart_HeartRateDataManager", "Enter setHeartRateZoneSettingInfo !");
        HeartZoneConf heartZoneConf = new HeartZoneConf();
        if (heartRateThresholdConfig != null) {
            heartZoneConf.setWarningEnble(heartRateThresholdConfig.getWarningEnable());
            heartZoneConf.setWarningLimitHR(heartRateThresholdConfig.getWarningLimit());
            heartZoneConf.setWarningEnbleHRR(heartRateThresholdConfig.getWarningEnable());
            heartZoneConf.setWarningLimitHRHRR(heartRateThresholdConfig.getWarningLimit());
            heartZoneConf.setMaxThreshold(heartRateThresholdConfig.getMaxThreshold());
            heartZoneConf.setAnaerobicThreshold(heartRateThresholdConfig.getAnaerobicThreshold());
            heartZoneConf.setAerobicThreshold(heartRateThresholdConfig.getAerobicThreshold());
            heartZoneConf.setFatBurnThreshold(heartRateThresholdConfig.getFatBurnThreshold());
            heartZoneConf.setWarmUpThreshold(heartRateThresholdConfig.getWarmUpThreshold());
            heartZoneConf.setFitnessThreshold(heartRateThresholdConfig.getFitnessThreshold());
            LogUtil.c("Heart_HeartRateDataManager", "setHeartRateZoneSettingInfo mHeartZoneConfingInfo = ", heartZoneConf.toString());
            kor.a().c(heartZoneConf);
        }
        LogUtil.c("Heart_HeartRateDataManager", "Leave setHeartRateZoneSettingInfo !");
    }

    public void d(boolean z, int i, boolean z2, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        LogUtil.c("Heart_HeartRateDataManager", "Enter setHeartRateZoneSettingInfo !");
        if (z) {
            j().setWarningEnable(z2);
            j().setWarningLimit(i2);
            j().setMaxThreshold(i3);
            j().setAnaerobicAdvanceThreshold(i4);
            j().setAnaerobicBaseThreshold(i5);
            j().setLacticAcidThreshold(i6);
            j().setAerobicAdvanceThreshold(i7);
            j().setAerobicBaseThreshold(i8);
            return;
        }
        new HeartZoneConf();
        this.d.getPostureType(i).setWarningEnable(z2);
        this.d.getPostureType(i).setWarningLimit(i2);
        this.d.getPostureType(i).setMaxThreshold(i3);
        this.d.getPostureType(i).setAnaerobicAdvanceThreshold(i4);
        this.d.getPostureType(i).setAnaerobicBaseThreshold(i5);
        this.d.getPostureType(i).setLacticAcidThreshold(i6);
        this.d.getPostureType(i).setAerobicAdvanceThreshold(i7);
        this.d.getPostureType(i).setAerobicBaseThreshold(i8);
        if (i != 1) {
            return;
        }
        e(this.d.getPostureType(i));
        LogUtil.c("Heart_HeartRateDataManager", "Leave setHeartRateZoneSettingInfo !");
    }

    private void e(HeartRateThresholdConfig heartRateThresholdConfig) {
        LogUtil.c("Heart_HeartRateDataManager", "Enter setHeartRateZoneSettingInfo !");
        HeartZoneConf heartZoneConf = new HeartZoneConf();
        if (heartRateThresholdConfig != null) {
            heartZoneConf.setWarningEnbleHRR(heartRateThresholdConfig.getWarningEnable());
            heartZoneConf.setWarningLimitHRHRR(heartRateThresholdConfig.getWarningLimit());
            heartZoneConf.setWarningEnble(heartRateThresholdConfig.getWarningEnable());
            heartZoneConf.setWarningLimitHR(heartRateThresholdConfig.getWarningLimit());
            heartZoneConf.setHrrMaxThreshold(heartRateThresholdConfig.getMaxThreshold());
            heartZoneConf.setAnaerobicAdvanceThreshold(heartRateThresholdConfig.getAnaerobicAdvanceThreshold());
            heartZoneConf.setAnaerobicBaseThreshold(heartRateThresholdConfig.getAnaerobicBaseThreshold());
            heartZoneConf.setLacticAcidThreshold(heartRateThresholdConfig.getLacticAcidThreshold());
            heartZoneConf.setAerobicAdvanceThreshold(heartRateThresholdConfig.getAerobicAdvanceThreshold());
            heartZoneConf.setAerobicBaseThreshold(heartRateThresholdConfig.getAerobicBaseThreshold());
            LogUtil.c("Heart_HeartRateDataManager", "setHeartRateZoneSettingInfo mHeartZoneConfingInfo = ", heartZoneConf.toString());
            kor.a().a(heartZoneConf);
        }
        LogUtil.c("Heart_HeartRateDataManager", "Leave setHeartRateZoneSettingInfo !");
    }

    public int a(int i) {
        if (i != 1) {
            return b(i);
        }
        return kor.a().j();
    }

    public void e(int i) {
        this.d.getPostureType(1).setRestHeartRateDefault(i);
        this.d.getPostureType(2).setRestHeartRateDefault(i);
        this.d.getPostureType(3).setRestHeartRateDefault(i);
        this.d.getPostureType(4).setRestHeartRateDefault(i);
    }

    private void e(HeartZoneConf heartZoneConf) {
        this.d.setHeartRateConfig(1, heartZoneConf.isWarningEnble(), heartZoneConf.getWarningLimitHR(), heartZoneConf.getClassifyMethod(), heartZoneConf.getMaxThreshold(), heartZoneConf.getRestHeartRate());
        this.d.setMaxHeartRateThreshold(1, heartZoneConf.getAnaerobicThreshold(), heartZoneConf.getAerobicThreshold(), heartZoneConf.getFatBurnThreshold(), heartZoneConf.getWarmUpThreshold(), heartZoneConf.getFitnessThreshold());
        this.d.setHrrHeartRateZoneThreshold(1, heartZoneConf.getAnaerobicAdvanceThreshold(), heartZoneConf.getAnaerobicBaseThreshold(), heartZoneConf.getLacticAcidThreshold(), heartZoneConf.getAerobicAdvanceThreshold(), heartZoneConf.getAerobicBaseThreshold());
    }

    public void a(boolean z) {
        this.d.getPostureType(1).getHeartZoneStateConfig().setIsSetWarningLimit(z);
    }

    public void e(boolean z) {
        this.d.getPostureType(1).getHeartZoneStateConfig().setIsSetMaxHeart(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int b(int r5) {
        /*
            r4 = this;
            kor r0 = defpackage.kor.a()
            com.huawei.up.model.UserInfomation r0 = r0.n()
            com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig r1 = new com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig
            int r0 = r0.getAgeOrDefaultValue()
            r1.<init>(r5, r0)
            int r0 = r1.getMaxThreshold()
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r1 = com.huawei.hihealth.api.HiHealthManager.d(r1)
            java.lang.String r2 = "userPreference_HeartRate_all_posture_data"
            com.huawei.hihealth.HiUserPreference r1 = r1.getUserPreference(r2)
            java.lang.String r2 = "Heart_HeartRateDataManager"
            if (r1 == 0) goto L41
            java.lang.String r1 = r1.getValue()     // Catch: java.lang.NumberFormatException -> L38
            kox$8 r3 = new kox$8     // Catch: java.lang.NumberFormatException -> L38
            r3.<init>()     // Catch: java.lang.NumberFormatException -> L38
            java.lang.Object r1 = defpackage.gvv.a(r1, r3)     // Catch: java.lang.NumberFormatException -> L38
            com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr r1 = (com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr) r1     // Catch: java.lang.NumberFormatException -> L38
            goto L42
        L38:
            java.lang.String r1 = "getMaxHeartRateValue updateAllPostureData parse value fail "
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.LogUtil.e(r2, r1)
        L41:
            r1 = 0
        L42:
            if (r1 == 0) goto L59
            com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig r5 = r1.getPostureType(r5)
            int r0 = r5.getMaxThreshold()
            java.lang.String r5 = "getMaxHeartRateValue maxHeart "
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r1}
            health.compact.a.LogUtil.c(r2, r5)
        L59:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kox.b(int):int");
    }
}
