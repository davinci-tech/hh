package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class lau {
    private static final Object c = new Object();
    private static volatile lau d;
    private boolean b;
    private lag e;

    private lau() {
    }

    public static lau d() {
        lau lauVar;
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    d = new lau();
                }
                lauVar = d;
            }
            return lauVar;
        }
        return d;
    }

    public void f() {
        ReleaseLogUtil.e("R_EcologyDevice_FitnessDeviceManager", "initBackground()");
        if (this.e instanceof laa) {
            return;
        }
        laa laaVar = new laa();
        this.e = laaVar;
        laaVar.i();
    }

    public void c() {
        LogUtil.c("FitnessDeviceManager", "init()");
        lag lagVar = this.e;
        if (lagVar == null || (lagVar instanceof laa)) {
            lag lagVar2 = new lag();
            this.e = lagVar2;
            lagVar2.i();
        }
    }

    public void e(String str, MessageOrStateCallback messageOrStateCallback) {
        if (this.e != null) {
            LogUtil.c("FitnessDeviceManager", "callbackKey=" + str);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
            lag lagVar = new lag();
            this.e = lagVar;
            lagVar.i();
        }
        this.e.d(str, messageOrStateCallback);
    }

    public void c(boolean z) {
        ReleaseLogUtil.e("R_EcologyDevice_FitnessDeviceManager", AwarenessRequest.MessageType.DISCONNECT);
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.disconnect(z);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void d(boolean z, String str) {
        if (this.e != null && !TextUtils.isEmpty(str)) {
            this.e.connectByMac(z, str);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public boolean h() {
        lag lagVar = this.e;
        if (lagVar != null) {
            return lagVar.reConnect();
        }
        LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        return false;
    }

    public void a(boolean z, String str) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.connectByName(z, str);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void e(String str, String str2) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.a(str, str2);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void m() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.m();
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public DeviceInformation e() {
        lag lagVar = this.e;
        DeviceInformation f = lagVar != null ? lagVar.f() : null;
        return f == null ? new DeviceInformation() : f;
    }

    public void k() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.l();
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void n() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.o();
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void a() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.cancelPairing();
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void e(byte[] bArr) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.sendByteToEquip(bArr);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void a(int[] iArr) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.e(iArr);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void a(Map<String, String> map) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.b(map);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void d(int i, int[] iArr) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.e(i, iArr);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    @Deprecated
    public void b(int i, int[] iArr) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.setFitnessMachineControl(i, iArr);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void b(int i, int i2, int[] iArr) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.setFitnessMachineControl(i, i2, iArr);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public boolean e(int i, int i2) {
        lag lagVar = this.e;
        if (lagVar != null) {
            return lagVar.b(i, i2);
        }
        LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        return false;
    }

    public void d(int i) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.setHeartRateFromWearable(i);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void l() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.setRealStartWorkout();
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public String b() {
        lag lagVar = this.e;
        if (lagVar != null) {
            return lagVar.getCurrentMacAddress();
        }
        LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        return "";
    }

    public void b(String str, boolean z) {
        if (this.e != null) {
            LogUtil.a("FitnessDeviceManager", "releaseResource, callbackKey = ", str);
            this.e.removeMessageOrStateCallback(str, z);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void o() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.releaseResource();
            this.e = null;
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void q() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.stopThreadsInCsafeController();
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void d(boolean z) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.setHasExperiencedStateOfStartForFtmp(z);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void c(String str) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.setDeviceType(str);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public void a(boolean z) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.b(z);
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public boolean g() {
        lag lagVar = this.e;
        if (lagVar != null) {
            return lagVar.k();
        }
        LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        return false;
    }

    public void p() {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.s();
        } else {
            LogUtil.b("FitnessDeviceManager", "mFitnessClient is null");
        }
    }

    public boolean j() {
        lag lagVar = this.e;
        if (lagVar != null) {
            return lagVar.j();
        }
        return false;
    }

    public void e(String str) {
        lag lagVar = this.e;
        if (lagVar != null) {
            lagVar.b(str);
        }
    }

    public boolean i() {
        return this.b;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean bVg_(Bundle bundle) {
        lag lagVar = this.e;
        if (lagVar != null) {
            return lagVar.bUm_(bundle);
        }
        LogUtil.h("FitnessDeviceManager", "mFitnessClient is null");
        return false;
    }

    public boolean bVh_(Bundle bundle) {
        lag lagVar = this.e;
        if (lagVar != null) {
            return lagVar.bUn_(bundle);
        }
        LogUtil.h("FitnessDeviceManager", "mFitnessClient is null");
        return false;
    }
}
