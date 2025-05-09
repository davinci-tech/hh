package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class kcj extends Handler {
    private WeakReference<kcg> e;

    kcj(kcg kcgVar) {
        this.e = new WeakReference<>(kcgVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message == null) {
            LogUtil.h("HwGpsMgrHandler", "message is null");
            return;
        }
        super.handleMessage(message);
        kcg kcgVar = this.e.get();
        if (kcgVar == null) {
            LogUtil.h("HwGpsMgrHandler", "hwGpsLocationManager is null");
            return;
        }
        LogUtil.a("HwGpsMgrHandler", "handleMessage message ", Integer.valueOf(message.what));
        int i = message.what;
        if (i == 1) {
            kcgVar.i();
            return;
        }
        if (i == 2) {
            if (message.obj instanceof DeviceInfo) {
                kcgVar.d((DeviceInfo) message.obj);
                return;
            }
            return;
        }
        if (i != 3) {
            if (i == 4) {
                kcgVar.f();
                kcgVar.e();
                return;
            } else {
                LogUtil.h("HwGpsMgrHandler", "handleMessage default");
                return;
            }
        }
        int i2 = message.arg1;
        if (i2 == 1) {
            kce.b().d();
        } else if (i2 == 0) {
            kce.b().c();
        } else {
            LogUtil.h("HwGpsMgrHandler", "handleMessage() unknown status");
        }
    }
}
