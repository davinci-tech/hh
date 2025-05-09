package defpackage;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jvg implements DataReceiveCallback {
    private static volatile jvg d;
    private String e = "0";

    /* renamed from: a, reason: collision with root package name */
    private boolean f14118a = false;
    private ExtendHandler c = null;
    private a b = new a();

    private jvg() {
        c();
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.a("EarPhoneLogPreExportManager", "SyncMgsHandler handleMessage msg: ", Integer.valueOf(message.what));
            if (message.what == 1059) {
                LogUtil.a("EarPhoneLogPreExportManager", "get device earPhone log timeout");
                DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
                jvg.this.c.removeMessages(1059);
                jvg.this.e(false);
                if ("2".equals(jvg.this.e) || "3".equals(jvg.this.e)) {
                    jvl.b().c(1, deviceInfo, 0);
                }
                jvg.this.a(deviceInfo, 0, 1);
                return true;
            }
            LogUtil.h("EarPhoneLogPreExportManager", "SyncMgsHandler default");
            return false;
        }
    }

    private void d() {
        this.c = HandlerCenter.yt_(this.b, "EarPhoneLogPreExportManager");
    }

    public static jvg e() {
        if (d == null) {
            synchronized (jvg.class) {
                if (d == null) {
                    d = new jvg();
                }
            }
        }
        return d;
    }

    private void c() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.hiviewEarphone", this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo, int i, int i2) {
        LogUtil.a("EarPhoneLogPreExportManager", "notifyMessageResult enter");
        try {
            INoitifyPhoneServiceCallback b = jso.d().b("earphoneLogPre");
            if (b != null) {
                b.executeResponse("earphoneLogPre", i2, deviceInfo, i, "");
            } else {
                LogUtil.h("EarPhoneLogPreExportManager", "mCommonCallback is null");
            }
        } catch (RemoteException unused) {
            LogUtil.b("EarPhoneLogPreExportManager", "notifyMessageResult, exception");
        }
    }

    public void d(String str, DeviceInfo deviceInfo) {
        LogUtil.a("EarPhoneLogPreExportManager", "sendDeviceEvent enter");
        if (b()) {
            LogUtil.h("EarPhoneLogPreExportManager", "isExportingEvent is true");
            return;
        }
        e(true);
        LogUtil.a("EarPhoneLogPreExportManager", "sendDeviceEvent sendSampleEventCommand");
        cuk.b().sendSampleEventCommand(deviceInfo, a(), "EarPhoneLogPreExportManager");
        if (this.c == null) {
            d();
        }
        this.e = str;
        Message obtain = Message.obtain();
        obtain.what = 1059;
        obtain.obj = deviceInfo;
        this.c.sendMessage(obtain, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    private static cvp a() {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.hiviewEarphone");
        cvpVar.setWearPkgName("hiviewEarphone");
        cvpVar.setCommandId(2);
        cvpVar.a(800100006L);
        cvpVar.b(0);
        cvpVar.e(null);
        return cvpVar;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("EarPhoneLogPreExportManager", "onDataReceived: ", Integer.valueOf(i));
        if (cvrVar instanceof cvp) {
            cvp cvpVar = (cvp) cvrVar;
            if (cvrVar.getCommandId() != 2) {
                LogUtil.h("EarPhoneLogPreExportManager", "onDataReceived, message commandId: ", Integer.valueOf(cvrVar.getCommandId()));
                return;
            }
            if (cvpVar.e() != 800100006) {
                return;
            }
            String d2 = cvx.d(cvpVar.c());
            int e = e(d2);
            LogUtil.a("EarPhoneLogPreExportManager", "eventData: ", d2, " status: ", Integer.valueOf(e));
            if (this.c == null) {
                d();
            }
            this.c.removeMessages(1059);
            if (e == 100) {
                LogUtil.a("EarPhoneLogPreExportManager", "earPhone log collect complete");
                e(false);
            } else {
                Message obtain = Message.obtain();
                obtain.what = 1059;
                obtain.obj = deviceInfo;
                this.c.sendMessage(obtain, OpAnalyticsConstants.H5_LOADING_DELAY);
            }
            if ("2".equals(this.e) || "3".equals(this.e)) {
                jvl.b().c(0, deviceInfo, e);
            }
            a(deviceInfo, e, 0);
        }
    }

    public boolean b() {
        return this.f14118a;
    }

    public void e(boolean z) {
        this.f14118a = z;
    }

    private int e(String str) {
        List<cwd> e;
        int i = -1;
        try {
            e = new cwl().a(str).e();
        } catch (cwg unused) {
            LogUtil.b("EarPhoneLogPreExportManager", "parseOperateData is error");
        }
        if (e != null && e.size() != 0) {
            for (cwd cwdVar : e) {
                if (CommonUtil.w(cwdVar.e()) == 1) {
                    i = CommonUtil.w(cwdVar.c());
                } else {
                    LogUtil.a("EarPhoneLogPreExportManager", "other type");
                }
            }
            return i;
        }
        LogUtil.h("EarPhoneLogPreExportManager", "tlvList is empty");
        return -1;
    }
}
