package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class jwr {
    public static void b(jwy jwyVar, long j) {
        if (jwyVar == null) {
            LogUtil.h("BasicMgrSharePreference", "setLastStatusTime fitnessManager is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        LogUtil.a("05", 1, "BasicMgrSharePreference", "setLastStatusTime lastTimestamp:", Long.valueOf(j));
        jwyVar.setSharedPreference("kStorage_FitnessMgr_Long_LastStatusTimeStamp", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "|" + keg.e() + "|" + j, storageParams);
    }

    public static long d(jwy jwyVar) {
        int e;
        long j = 0;
        if (jwyVar == null) {
            LogUtil.h("BasicMgrSharePreference", "getLastStatusTime fitnessManager is null");
            return 0L;
        }
        String sharedPreference = jwyVar.getSharedPreference("kStorage_FitnessMgr_Long_LastStatusTimeStamp");
        if (sharedPreference != null && !TextUtils.isEmpty(sharedPreference)) {
            String[] split = sharedPreference.split("\\|");
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (split.length == 3 && split[0].equals(accountInfo) && split[1].equals(keg.e())) {
                e = CommonUtil.e(split[2], 0);
            } else if (split.length == 2 && split[0].equals(keg.e())) {
                e = CommonUtil.e(split[1], 0);
            } else {
                LogUtil.h("BasicMgrSharePreference", "lastStatusTime is 0");
            }
            j = e;
        }
        LogUtil.a("05", 1, "BasicMgrSharePreference", "getLastStatusTime lastStatusTime:", Long.valueOf(j));
        return j;
    }

    public static long c(jwy jwyVar) {
        long j = 0;
        if (jwyVar == null) {
            LogUtil.h("BasicMgrSharePreference", "getLastDesTime fitnessManager is null");
            return 0L;
        }
        String sharedPreference = jwyVar.getSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp");
        if (sharedPreference != null && !TextUtils.isEmpty(sharedPreference)) {
            j = c(0L, sharedPreference.split("\\|"));
        }
        LogUtil.a("05", 1, "BasicMgrSharePreference", "getLastDesTime desTimestamp:", Long.valueOf(j));
        return j;
    }

    public static void d(jwy jwyVar, long j) {
        if (jwyVar == null) {
            LogUtil.h("BasicMgrSharePreference", "setLastDesTime fitnessManager is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        LogUtil.a("05", 1, "BasicMgrSharePreference", "setLastDesTime lastTimestamp:", Long.valueOf(j));
        jwyVar.setSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "|" + keg.e() + "|" + j, storageParams);
    }

    public static long e(jwy jwyVar) {
        long j = 0;
        if (jwyVar == null) {
            LogUtil.h("BasicMgrSharePreference", "getLastSecondDesTime fitnessManager is null");
            return 0L;
        }
        String sharedPreference = jwyVar.getSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp2");
        if (sharedPreference != null && !TextUtils.isEmpty(sharedPreference)) {
            j = c(0L, sharedPreference.split("\\|"));
        }
        LogUtil.a("05", 1, "BasicMgrSharePreference", "getLastSecondDesTime desTimestamp:", Long.valueOf(j));
        return j;
    }

    private static long c(long j, String[] strArr) {
        return (strArr.length == 3 && strArr[0].equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)) && strArr[1].equals(keg.e())) ? CommonUtil.e(strArr[2], 0) : j;
    }

    public static void e(jwy jwyVar, long j) {
        if (jwyVar == null) {
            LogUtil.h("BasicMgrSharePreference", "setLastSecondDesTime fitnessManager is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        LogUtil.a("05", 1, "BasicMgrSharePreference", "setLastSecondDesTime lastTimestamp:", Long.valueOf(j));
        jwyVar.setSharedPreference("kStorage_FitnessMgr_Long_LastDesTimeStamp2", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "|" + keg.e() + "|" + j, storageParams);
    }
}
