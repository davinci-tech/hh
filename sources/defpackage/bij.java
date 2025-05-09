package defpackage;

import com.huawei.devicesdk.connect.retry.ExecuteActionInterface;
import com.huawei.devicesdk.connect.retry.RetryCallbackInterface;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bij {
    private int b;
    private int d = 0;

    public bij(int i) {
        this.b = Math.max(i, 0);
    }

    public void d(ExecuteActionInterface executeActionInterface, RetryCallbackInterface retryCallbackInterface, boolean z) {
        if (executeActionInterface == null || retryCallbackInterface == null) {
            LogUtil.e("ActionRetryExecutor", "action or retryCallback is null");
        } else if (z) {
            c(executeActionInterface, retryCallbackInterface);
        } else {
            b(executeActionInterface, retryCallbackInterface);
        }
    }

    private void c(ExecuteActionInterface executeActionInterface, RetryCallbackInterface retryCallbackInterface) {
        String actionName = executeActionInterface.getActionName();
        if (this.d <= this.b) {
            a(executeActionInterface, retryCallbackInterface, actionName);
            return;
        }
        LogUtil.e("ActionRetryExecutor", "action  ", actionName, " execute failed.");
        d();
        retryCallbackInterface.doFailureAction();
    }

    private void b(ExecuteActionInterface executeActionInterface, RetryCallbackInterface retryCallbackInterface) {
        String actionName = executeActionInterface.getActionName();
        while (this.d <= this.b) {
            if (a(executeActionInterface, retryCallbackInterface, actionName)) {
                return;
            }
        }
        LogUtil.e("ActionRetryExecutor", "action  ", actionName, " execute failed.");
        d();
        retryCallbackInterface.doFailureAction();
    }

    private boolean a(ExecuteActionInterface executeActionInterface, RetryCallbackInterface retryCallbackInterface, String str) {
        if (executeActionInterface.execute()) {
            d();
            LogUtil.c("ActionRetryExecutor", "action ", str, " execute success.");
            retryCallbackInterface.doSuccessAction();
            return true;
        }
        LogUtil.a("ActionRetryExecutor", "action ", str, " execute failed. start to retry. currentRetry:", Integer.valueOf(this.d));
        retryCallbackInterface.doRetryAction(this.d);
        this.d++;
        return false;
    }

    public void d() {
        this.d = 0;
    }
}
