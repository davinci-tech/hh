package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;

/* loaded from: classes5.dex */
public class jim {
    private int b = 1;

    public long d(jhg jhgVar) {
        try {
            if (jhgVar == null) {
                LogUtil.h("SyncOptimization", "getLastSyncSecondStageTime fitnessManager is null");
                return 0L;
            }
            String sharedPreference = jhgVar.getSharedPreference("sync_sample_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + jpp.d());
            if (TextUtils.isEmpty(sharedPreference)) {
                return -1L;
            }
            return Long.parseLong(sharedPreference, 10);
        } catch (NumberFormatException e) {
            LogUtil.b("SyncOptimization", "getLastSyncSecondStageTime Exception : ", ExceptionUtils.d(e));
            return 0L;
        }
    }

    public void a(jhg jhgVar, long j) {
        if (jhgVar == null) {
            LogUtil.h("SyncOptimization", "setLastSyncSecondStageTime fitnessManager is null");
            return;
        }
        jhgVar.setSharedPreference("sync_sample_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + jpp.d(), j + "", null);
    }

    public long e(jhg jhgVar) {
        try {
            if (jhgVar == null) {
                LogUtil.h("SyncOptimization", "getLastSyncStatusSecondStageTime fitnessManager is null");
                return 0L;
            }
            String sharedPreference = jhgVar.getSharedPreference("sync_status_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + jpp.d());
            if (TextUtils.isEmpty(sharedPreference)) {
                return -1L;
            }
            return Long.parseLong(sharedPreference, 10);
        } catch (NumberFormatException e) {
            LogUtil.b("SyncOptimization", "getLastSyncSecondStageTime Exception : ", ExceptionUtils.d(e));
            return 0L;
        }
    }

    public void b(jhg jhgVar, long j) {
        if (jhgVar == null) {
            LogUtil.h("SyncOptimization", "setLastSyncStatusSecondStageTime fitnessManager is null");
            return;
        }
        jhgVar.setSharedPreference("sync_status_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + jpp.d(), j + "", null);
    }

    public void d(int i) {
        this.b = i;
    }

    public int b() {
        return this.b;
    }
}
