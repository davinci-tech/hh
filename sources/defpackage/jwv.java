package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;

/* loaded from: classes5.dex */
public class jwv {
    private int c = 1;

    public long a(jwy jwyVar) {
        try {
            if (jwyVar == null) {
                LogUtil.h("SyncOptimization", "getLastSyncSecondStageTime fitnessManager is null");
                return 0L;
            }
            String sharedPreference = jwyVar.getSharedPreference("sync_sample_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + keg.e());
            if (TextUtils.isEmpty(sharedPreference)) {
                return -1L;
            }
            return Long.parseLong(sharedPreference, 10);
        } catch (NumberFormatException e) {
            LogUtil.b("SyncOptimization", "getLastSyncSecondStageTime Exception : ", ExceptionUtils.d(e));
            return 0L;
        }
    }

    public void b(jwy jwyVar, long j) {
        if (jwyVar == null) {
            LogUtil.h("SyncOptimization", "setLastSyncSecondStageTime fitnessManager is null");
            return;
        }
        jwyVar.setSharedPreference("sync_sample_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + keg.e(), j + "", null);
    }

    public long b(jwy jwyVar) {
        try {
            if (jwyVar == null) {
                LogUtil.h("SyncOptimization", "getLastSyncStatusSecondStageTime fitnessManager is null");
                return 0L;
            }
            String sharedPreference = jwyVar.getSharedPreference("sync_status_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + keg.e());
            if (TextUtils.isEmpty(sharedPreference)) {
                return -1L;
            }
            return Long.parseLong(sharedPreference, 10);
        } catch (NumberFormatException e) {
            LogUtil.b("SyncOptimization", "getLastSyncSecondStageTime Exception : ", ExceptionUtils.d(e));
            return 0L;
        }
    }

    public void a(jwy jwyVar, long j) {
        if (jwyVar == null) {
            LogUtil.h("SyncOptimization", "setLastSyncStatusSecondStageTime fitnessManager is null");
            return;
        }
        jwyVar.setSharedPreference("sync_status_stage_2_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + keg.e(), j + "", null);
    }

    public void d(int i) {
        this.c = i;
    }

    public int c() {
        return this.c;
    }
}
