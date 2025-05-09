package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.KeyValDbManager;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jtj {

    /* renamed from: a, reason: collision with root package name */
    private final CountDownLatch f14068a = new CountDownLatch(1);
    private boolean d = false;

    public boolean b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        return jSONObject.has("identify");
    }

    public void d(UniteDevice uniteDevice, biz bizVar, JSONObject jSONObject) {
        if (bizVar == null) {
            return;
        }
        if (cvz.d() && cvz.c()) {
            bizVar.a(true);
        } else {
            bizVar.a(false);
        }
        bizVar.e(d(uniteDevice, jSONObject));
        if (jSONObject == null || !jSONObject.has("isConnectNewPhone")) {
            this.f14068a.countDown();
        } else if (!a(uniteDevice)) {
            this.f14068a.countDown();
        }
        if (this.f14068a.getCount() > 0) {
            try {
                this.f14068a.await(60000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                LogUtil.b("PreHandshakeCreator", "mCountDownLatch exception");
            }
        }
        bizVar.d(this.d);
    }

    private boolean d(UniteDevice uniteDevice, JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("isCompatibleDevice")) {
            return false;
        }
        int a2 = a(uniteDevice.getDeviceInfo().getDeviceName());
        jtd.b().d();
        return cvt.b(b(a2, uniteDevice.getIdentify(), jtd.b().d()));
    }

    private int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (upperCase.contains("AW70".toUpperCase(Locale.ENGLISH)) || upperCase.contains("HUAWEI Band 3e-".toUpperCase(Locale.ENGLISH))) {
            return 23;
        }
        return (upperCase.contains("HONOR AW70".toUpperCase(Locale.ENGLISH)) || upperCase.contains("honor Band 4R-".toUpperCase(Locale.ENGLISH))) ? 24 : -1;
    }

    private int b(int i, String str, List<DeviceInfo> list) {
        if (i != -1 || str == null) {
            return i;
        }
        for (DeviceInfo deviceInfo : list) {
            if (str.equals(deviceInfo.getDeviceIdentify())) {
                return deviceInfo.getProductType();
            }
        }
        return i;
    }

    private boolean a(UniteDevice uniteDevice) {
        if (uniteDevice == null || TextUtils.isEmpty(uniteDevice.getIdentify())) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pairing.NotifyActivity"));
            intent.addFlags(268435456);
            intent.addFlags(536870912);
            intent.putExtra("deviceName", uniteDevice.getIdentify());
            BaseApplication.getContext().startActivity(intent);
            return true;
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PreHandshakeCreator", "startUserConfirmDialog ActivityNotFoundException.");
            return true;
        }
    }

    public void c(boolean z) {
        this.d = !z;
        if (this.f14068a.getCount() > 0) {
            this.f14068a.countDown();
        }
    }

    public int b(String str) {
        String str2 = "pair_mode_key" + knl.a(str);
        LogUtil.a("PreHandshakeCreator", "getPairMode queryKey: ", str2);
        String e = KeyValDbManager.b(BaseApplication.getContext()).e(str2);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("PreHandshakeCreator", "getPairMode queryValue is empty.");
            return -1;
        }
        try {
            return Integer.parseInt(e);
        } catch (NumberFormatException unused) {
            LogUtil.b("PreHandshakeCreator", "getPairMode NumberFormatException.");
            return -1;
        }
    }
}
