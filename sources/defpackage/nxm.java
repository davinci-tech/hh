package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes8.dex */
public class nxm extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private int f15543a = 0;

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i == 1) {
            ReleaseLogUtil.e("Dfx_AppEventDeleteHandler", "handleMessage DELETE_APP_EVENT_MESSAGE_FIRST");
            removeMessages(1);
            sendEmptyMessageDelayed(2, 180000L);
            return;
        }
        if (i == 2) {
            int i2 = this.f15543a + 1;
            this.f15543a = i2;
            ReleaseLogUtil.e("Dfx_AppEventDeleteHandler", "handleMessage DELETE_APP_EVENT_MESSAGE count: ", Integer.valueOf(i2));
            if (!bls.c(BaseApplication.e(), "ProblemSuggestActivity")) {
                ReleaseLogUtil.e("Dfx_AppEventDeleteHandler", "handleMessage not isTopActivity");
                e();
                return;
            }
            ReleaseLogUtil.e("Dfx_AppEventDeleteHandler", "handleMessage isTopActivity");
            if (this.f15543a == 3) {
                e();
                return;
            } else {
                sendEmptyMessageDelayed(2, 180000L);
                return;
            }
        }
        LogUtil.d("AppEventDeleteHandler", "handleMessage default");
    }

    private void e() {
        ReleaseLogUtil.e("Dfx_AppEventDeleteHandler", "handleMessage deleteDeal");
        removeMessages(2);
        bls.a();
        this.f15543a = 0;
    }
}
