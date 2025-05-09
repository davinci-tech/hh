package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes.dex */
public class kfp implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f14351a = false;
    private static kfp c;
    private static final Object e = new Object();
    private Context b;
    private cwl d = new cwl();

    private kfp(Context context) {
        this.b = context;
    }

    public static kfp d(Context context) {
        kfp kfpVar;
        synchronized (e) {
            if (c == null && context != null) {
                LogUtil.a("HwWakeAppManager", "getInstance() context =", context);
                c = new kfp(context);
            }
            kfpVar = c;
        }
        return kfpVar;
    }

    public void d(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.c("HwWakeAppManager", "handleOpenApp data =", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("HwWakeAppManager", "handleOpenApp messageHex is error");
            return;
        }
        try {
            List<cwd> e2 = this.d.a(d.substring(4)).e();
            if (e2 != null && !e2.isEmpty()) {
                kfr kfrVar = new kfr();
                for (cwd cwdVar : e2) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    if (a2 == 1) {
                        kfrVar.b(cvx.e(cwdVar.c()));
                    } else if (a2 == 2) {
                        kfrVar.e(cvx.e(cwdVar.c()));
                    } else {
                        LogUtil.h("HwWakeAppManager", "handleOpenApp unknown tlv type");
                    }
                }
                a(kfrVar);
                return;
            }
            LogUtil.h("HwWakeAppManager", "handleOpenApp tlv is error");
        } catch (cwg unused) {
            LogUtil.b("HwWakeAppManager", "COMMAND_ID_GET_DATE TlvException");
        }
    }

    private void a(kfr kfrVar) {
        try {
        } catch (Exception unused) {
            LogUtil.b("HwWakeAppManager", "openApp startActivity Exception");
        }
        if (!"com.huawei.bone".equals(kfrVar.d()) && !kfrVar.d().equals(this.b.getPackageName())) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName(kfrVar.d(), kfrVar.b()));
            this.b.startActivity(intent);
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(1);
            deviceCommand.setCommandID(23);
            ByteBuffer allocate = ByteBuffer.allocate(6);
            allocate.put(Byte.MAX_VALUE);
            allocate.put((byte) 4);
            allocate.put(cvx.a(cvx.b(100000L)));
            deviceCommand.setDataLen(allocate.array().length);
            deviceCommand.setDataContent(allocate.array());
            jsz.b(this.b).sendDeviceData(deviceCommand);
        }
        String b = kfrVar.b();
        if ("com.huawei.sim.esim.view.EsimActivationActivity".equals(b)) {
            b = "com.huawei.sim.esim.view.WirelessManagerActivity";
        }
        Bundle bundle = new Bundle();
        Intent intent2 = new Intent();
        intent2.putExtra("health_activity_id", b);
        intent2.setClassName(this.b, "com.huawei.health.StartHealthActivity");
        intent2.putExtras(bundle);
        intent2.setFlags(268435456);
        f14351a = true;
        this.b.startActivity(intent2);
        DeviceCommand deviceCommand2 = new DeviceCommand();
        deviceCommand2.setServiceID(1);
        deviceCommand2.setCommandID(23);
        ByteBuffer allocate2 = ByteBuffer.allocate(6);
        allocate2.put(Byte.MAX_VALUE);
        allocate2.put((byte) 4);
        allocate2.put(cvx.a(cvx.b(100000L)));
        deviceCommand2.setDataLen(allocate2.array().length);
        deviceCommand2.setDataContent(allocate2.array());
        jsz.b(this.b).sendDeviceData(deviceCommand2);
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        d(bArr);
    }
}
