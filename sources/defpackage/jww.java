package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class jww {
    public static void d(long j) {
        StorageParams storageParams = new StorageParams();
        LogUtil.a("FitnessManagerAw70SharePreference", "setLastStatusTime: lastTimeStamp: ", Long.valueOf(j));
        SharedPreferenceManager.e(BaseApplication.getContext(), "100007", "kStorage_FitnessMgr_Long_LastStatusTimeStamp", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "|" + jxi.d() + "|" + Long.toString(j), storageParams);
    }

    public static long d() {
        long j;
        int parseInt;
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "100007", "kStorage_FitnessMgr_Long_LastStatusTimeStamp");
        if (!TextUtils.isEmpty(b)) {
            String[] split = b.split("\\|");
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            try {
                if (split.length == 3 && split[0].equals(accountInfo) && split[1].equals(jxi.d())) {
                    parseInt = Integer.parseInt(split[2]);
                } else if (split.length == 2 && split[0].equals(jxi.d())) {
                    parseInt = Integer.parseInt(split[1]);
                } else {
                    LogUtil.h("FitnessManagerAw70SharePreference", "getLastStatusTime(): do nothing.");
                }
                j = parseInt;
            } catch (NumberFormatException e) {
                LogUtil.b("FitnessManagerAw70SharePreference", "getLastStatusTime Exception : ", ExceptionUtils.d(e));
            }
            LogUtil.a("FitnessManagerAw70SharePreference", "getLastStatusTime: lastTimeStamp: ", Long.valueOf(j));
            return j;
        }
        j = 0;
        LogUtil.a("FitnessManagerAw70SharePreference", "getLastStatusTime: lastTimeStamp: ", Long.valueOf(j));
        return j;
    }
}
