package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;

/* loaded from: classes6.dex */
public class nny {
    private HwHealthBaseBarLineChart c;
    private boolean e = false;
    private Handler b = new Handler(Looper.getMainLooper()) { // from class: nny.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("RingAnimationMgr", "handleMessage msg is null.");
            } else if (message.what == 1) {
                nny.this.c();
            } else {
                super.handleMessage(message);
            }
        }
    };

    public nny(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        this.c = hwHealthBaseBarLineChart;
    }

    public boolean a() {
        return this.e;
    }

    public void e(long j) {
        this.b.sendMessageDelayed(this.b.obtainMessage(1), j);
    }

    public void e() {
        this.b.removeMessages(1);
        if (c()) {
            e(500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        boolean z = !this.c.isDataReady();
        if (this.e != z) {
            this.e = z;
            this.c.invalidateForce();
        }
        return this.e;
    }
}
