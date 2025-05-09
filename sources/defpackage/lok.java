package defpackage;

import android.content.Context;
import android.os.Message;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import defpackage.log;

/* loaded from: classes5.dex */
public class lok {

    /* renamed from: a, reason: collision with root package name */
    private static String f14811a = "HwMultiDeviceManager";
    private AuthParam c;
    private Context d;

    public void d(Context context) {
        if (context == null) {
            loq.c(f14811a, "HwMultiDeviceManager init failed, context is null");
        } else {
            a(context, null, null);
        }
    }

    public void a(Context context, String str, String str2) {
        d(context, null, str, str2);
    }

    public void d(Context context, AuthParam authParam, String str, String str2) {
        if (context == null) {
            loq.c(f14811a, "HwMultiDeviceManager init failed, context is null");
            return;
        }
        this.d = context;
        lod.e(context, authParam);
        lna.e().a(context);
        loe.a(str, str2);
        loj.d().e(context);
        AuthParam authParam2 = this.c;
        int authType = authParam2 != null ? authParam2.getAuthType() : 3;
        loq.c(f14811a, "init authType=" + authType);
        if ("1".equals(lod.a())) {
            if (authParam == null) {
                loq.c(f14811a, "init(), authParam is null");
                log.a().b(context);
            } else {
                this.c = authParam;
                log.a().b(context, authParam);
            }
        } else if (authParam == null) {
            loq.b(f14811a, "init(), es authParam is null");
            return;
        } else {
            this.c = authParam;
            loy.e().b(context, authParam);
        }
        loq.c(f14811a, "Start init HwMultiSIMSdk");
    }

    public void a() {
        log.a().e();
        loy.e().a();
        lna.e().h();
        lod.b();
        loq.c(f14811a, "Finish HwMultiSIMSdk exit");
    }

    public void bYi_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        if ("1".equals(lod.a())) {
            bYg_(absPrimaryDevice, absPairedDevice, message, null, 100);
        } else {
            loy.e().bYC_(absPrimaryDevice, absPairedDevice, message);
        }
    }

    public void bYh_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        loq.c(f14811a, "acquireStatusByPolling start.");
        loy.e().bYF_(absPrimaryDevice, absPairedDevice, message, 5);
    }

    public void bYj_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        loy.e().bYD_(absPrimaryDevice, absPairedDevice, message);
    }

    public void bYk_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        if ("1".equals(lod.a())) {
            bYg_(absPrimaryDevice, absPairedDevice, message, null, 102);
        } else {
            loy.e().bYE_(absPrimaryDevice, absPairedDevice, message);
        }
    }

    public boolean bYl_(Context context, AuthParam authParam, Message message) {
        return bYm_(context, authParam, null, null, message);
    }

    public boolean bYm_(Context context, AuthParam authParam, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message) {
        if ("1".equals(lod.a())) {
            return loe.bYf_(context, authParam, message);
        }
        return loy.e().bYG_(context, authParam, absPrimaryDevice, absPairedDevice, message);
    }

    private void bYg_(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Message message, Message message2, int i) {
        log.c h = log.a().h();
        lns lnsVar = new lns();
        if (absPrimaryDevice != null) {
            String d = d(absPrimaryDevice.getService());
            String primaryID = absPrimaryDevice.getPrimaryID();
            String b = b(absPrimaryDevice.getPrimaryIDType());
            lnsVar.b(primaryID);
            lnsVar.d(b);
            lnsVar.f(d);
            if (loq.b.booleanValue()) {
                loq.c(f14811a, "progressData.getPrimary():" + lnsVar.c());
                loq.c(f14811a, "progressData.primarytype:" + b);
            }
        }
        if (absPairedDevice != null) {
            lnsVar.e(absPairedDevice.getPairedID());
            lnsVar.h(b(absPairedDevice.getPairedIDType()));
            lnsVar.c(absPairedDevice.getPairedDeviceName());
            lnsVar.a(absPairedDevice.getDeviceID());
            lnsVar.e(absPairedDevice.getSecondaryDeviceId());
            if (loq.b.booleanValue()) {
                loq.c(f14811a, "SecondaryType:" + lnsVar.g());
                loq.c(f14811a, "SecondaryID:" + lnsVar.f());
            }
        }
        if (message != null) {
            lnsVar.bXQ_(message);
        }
        if (message2 != null) {
            lnsVar.bXP_(message2);
        }
        lnsVar.d(i);
        if (h != null) {
            h.sendMessage(h.obtainMessage(i, lnsVar));
        }
    }

    private String b(int i) {
        if (i == 0) {
            return "IMSI";
        }
        if (i == 1) {
            return "MSISDN";
        }
        if (i == 2) {
            return "ICCID";
        }
        if (i != 3) {
            return null;
        }
        return "EID";
    }

    private String d(int i) {
        return (i == 0 || i != 1) ? "Multi-SIM" : "eSIM Profile";
    }
}
