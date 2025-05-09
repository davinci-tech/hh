package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class jgz {
    public static void d(jhg jhgVar, long j) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrSharePreference", "setLastStatusTime fitnessManager is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "setLastStatusTime lastTimestamp:", Long.valueOf(j));
        jhgVar.setSharedPreference("kStorage_FitnessMgr_Long_LastStatusTimeStamp", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "|" + jpp.d() + "|" + Long.toString(j), storageParams);
    }

    public static long e(jhg jhgVar) {
        int e;
        long j = 0;
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrSharePreference", "getLastStatusTime fitnessManager is null");
            return 0L;
        }
        String sharedPreference = jhgVar.getSharedPreference("kStorage_FitnessMgr_Long_LastStatusTimeStamp");
        if (sharedPreference != null && !TextUtils.isEmpty(sharedPreference)) {
            String[] split = sharedPreference.split("\\|");
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (split.length == 3 && split[0].equals(accountInfo) && split[1].equals(jpp.d())) {
                e = CommonUtil.e(split[2], 0);
            } else if (split.length == 2 && split[0].equals(jpp.d())) {
                e = CommonUtil.e(split[1], 0);
            } else {
                LogUtil.h("FitnessMgrSharePreference", "lastStatusTime is 0");
            }
            j = e;
        }
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "getLastStatusTime lastStatusTime:", Long.valueOf(j));
        return j;
    }

    public static boolean b(jhg jhgVar) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrSharePreference", "getReverseDataSyncEnable fitnessManager is null");
            return false;
        }
        LogUtil.a("FitnessMgrSharePreference", "getReverseDataSyncEnable reverseString:", jhgVar.getSharedPreference("kStorage_FitnessMgr_Boolean_ReverseSyncEnable"));
        return false;
    }

    public static void b(Context context, int i, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null) {
            LogUtil.h("FitnessMgrSharePreference", "setCoreSleepButtonEnable context is null");
            return;
        }
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "setCoreSleepButtonEnable enable:", Integer.valueOf(i));
        String num = Integer.toString(i);
        jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setCoreSleepButtonEnable switchSettingManager is null");
        } else {
            a2.setSwitchSetting("core_sleep_button", num, iBaseResponseCallback);
            jqp.a(num);
        }
    }

    public static void a(Context context, int i, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null) {
            LogUtil.h("FitnessMgrSharePreference", "setHeartRateButtonEnable context is null");
            return;
        }
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "setHeartRateButtonEnable:", Integer.valueOf(i));
        jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setHeartRateButtonEnable switchSettingManager is null");
            return;
        }
        String num = Integer.toString(i);
        if (TextUtils.isEmpty(num)) {
            return;
        }
        a2.setSwitchSetting("heart_rate_button", num, iBaseResponseCallback);
    }

    public static long a(jhg jhgVar) {
        long j = 0;
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrSharePreference", "getLastDesTime fitnessManager is null");
            return 0L;
        }
        String sharedPreference = jhgVar.getSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp");
        if (sharedPreference != null && !TextUtils.isEmpty(sharedPreference)) {
            j = c(0L, sharedPreference.split("\\|"));
        }
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "getLastDesTime desTimestamp:", Long.valueOf(j));
        return j;
    }

    public static void c(jhg jhgVar, long j) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrSharePreference", "setLastDesTime fitnessManager is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "setLastDesTime lastTimestamp:", Long.valueOf(j));
        jhgVar.setSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "|" + jpp.d() + "|" + Long.toString(j), storageParams);
    }

    public static long c(jhg jhgVar) {
        long j = 0;
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrSharePreference", "getLastSecondDesTime fitnessManager is null");
            return 0L;
        }
        String sharedPreference = jhgVar.getSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp2");
        if (sharedPreference != null && !TextUtils.isEmpty(sharedPreference)) {
            j = c(0L, sharedPreference.split("\\|"));
        }
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "getLastSecondDesTime desTimestamp:", Long.valueOf(j));
        return j;
    }

    private static long c(long j, String[] strArr) {
        return (strArr.length == 3 && strArr[0].equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)) && strArr[1].equals(jpp.d())) ? CommonUtil.e(strArr[2], 0) : j;
    }

    public static void e(jhg jhgVar, long j) {
        if (jhgVar == null) {
            LogUtil.h("FitnessMgrSharePreference", "setLastSecondDesTime fitnessManager is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        LogUtil.a("05", 1, "FitnessMgrSharePreference", "setLastSecondDesTime lastTimestamp:", Long.valueOf(j));
        jhgVar.setSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp2", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "|" + jpp.d() + "|" + Long.toString(j), storageParams);
    }

    public static void c(int i) {
        jqy.d(jre.a("FitnessMgrSharePreference"), "continuous_temp_monitoring", Integer.toString(i));
    }

    public static void a(int i, int i2) {
        jqy.d(jre.a("FitnessMgrSharePreference"), "temperature_upper_remind", Float.toString(i2 / 10.0f));
    }

    public static void d(int i, int i2) {
        jqy.d(jre.a("FitnessMgrSharePreference"), "temperature_lower_remind", Float.toString(i2 / 10.0f));
    }

    public static void d(final int i) {
        final jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setContinueHeartRateStatus switchSettingManager is null");
        } else {
            a2.setSwitchSetting("continue_heart_rate", Integer.toString(i), new IBaseResponseCallback() { // from class: jgz.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 != 0) {
                        LogUtil.h("FitnessMgrSharePreference", "setContinueHeartRateStatus continue errorCode:", Integer.valueOf(i2));
                    } else {
                        jqi.this.setSwitchSetting("heart_rate_mode", Integer.toString(0), new IBaseResponseCallback() { // from class: jgz.2.3
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i3, Object obj2) {
                                LogUtil.a("FitnessMgrSharePreference", "setContinueHeartRateStatus errorCode :", Integer.valueOf(i3));
                                if (i3 == 0) {
                                    jho.b(i);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public static void a(final int i) {
        final jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setCycleHeartRateStatus switchSettingManager is null");
        } else {
            a2.setSwitchSetting("continue_heart_rate", Integer.toString(i), new IBaseResponseCallback() { // from class: jgz.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 != 0) {
                        LogUtil.h("FitnessMgrSharePreference", "setCycleHeartRateStatus continue errorCode:", Integer.valueOf(i2));
                    } else {
                        jqi.this.setSwitchSetting("heart_rate_mode", Integer.toString(1), new IBaseResponseCallback() { // from class: jgz.4.3
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i3, Object obj2) {
                                LogUtil.a("FitnessMgrSharePreference", "setCycleHeartRateStatus errorCode :", Integer.valueOf(i3));
                                if (i3 == 0) {
                                    jho.a(i);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public static void c(final int i, int i2) {
        jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setBloodOxygenRemind switchSettingManager is null");
            return;
        }
        final int min = Math.min(i2, 90);
        a2.setSwitchSetting("custom.blood.oxygen.remind", min + "", new IBaseResponseCallback() { // from class: jgz.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (i3 == 0) {
                    jho.c(i, min);
                }
            }
        });
    }

    public static void e(final int i, final int i2) {
        jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setHeartRateRaiseRemind switchSettingManager is null");
            return;
        }
        a2.setSwitchSetting("custom.heart_rate_raise_remind", i2 + "", new IBaseResponseCallback() { // from class: jgz.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (i3 == 0) {
                    LogUtil.a("FitnessMgrSharePreference", "heartRateRaiseRemind setSwitchSetting success.");
                    jho.b(i, i2);
                }
            }
        });
    }

    public static void b(final int i, final int i2) {
        jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setHeartRateDownRemind switchSettingManager is null");
            return;
        }
        a2.setSwitchSetting("custom.heart_rate_down_remind", i2 + "", new IBaseResponseCallback() { // from class: jgz.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (i3 == 0) {
                    LogUtil.a("FitnessMgrSharePreference", "heartRateDownRemind setSwitchSetting success.");
                    jho.a(i, i2);
                }
            }
        });
    }

    public static void b(final int i) {
        jqi a2 = jqi.a();
        if (a2 == null) {
            LogUtil.h("FitnessMgrSharePreference", "setBloodOxygenSwitch switchSettingManager is null");
            return;
        }
        a2.setSwitchSetting("custom.blood.oxygen.switch", i + "", new IBaseResponseCallback() { // from class: jgz.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0) {
                    jho.c(i);
                }
            }
        });
    }
}
