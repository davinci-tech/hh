package defpackage;

import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.core.app.ActivityCompat;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HarmonyBuild;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kit {
    public void d(byte[] bArr, DeviceInfo deviceInfo) {
        String d = cvx.d(bArr);
        String substring = d.substring(4, d.length());
        ArrayList arrayList = new ArrayList(16);
        boolean z = false;
        try {
            for (cwd cwdVar : new cwl().a(substring).e()) {
                if (Integer.parseInt(cwdVar.e(), 16) == 127) {
                    z = true;
                } else {
                    arrayList.add(Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
                }
            }
        } catch (cwg e) {
            LogUtil.b("HandsetInfo", "getHandsetInfo() error e = ", e.getMessage());
        } catch (NumberFormatException unused) {
            LogUtil.b("HandsetInfo", "getHandsetInfo() error NumberFormatException");
        }
        if (z) {
            return;
        }
        c(arrayList, deviceInfo);
    }

    private void c(List<Integer> list, DeviceInfo deviceInfo) {
        String str;
        LogUtil.a("HandsetInfo", "getHandsetInfo() in");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(16);
        kip kipVar = new kip();
        kipVar.a(cvx.c(Build.BOARD));
        kipVar.e(cvx.c(Build.BRAND));
        kipVar.g(cvx.c(Build.DEVICE));
        kipVar.r(cvx.c(Build.MODEL));
        kipVar.h(cvx.c(Build.HARDWARE));
        kipVar.q(cvx.c(Build.PRODUCT));
        kipVar.m(cvx.c(Build.MANUFACTURER));
        kipVar.p(cvx.c(Build.VERSION.RELEASE));
        kipVar.t(cvx.e(Build.VERSION.SDK_INT));
        a(kipVar);
        int s = kipVar.s();
        if (CommonUtil.bf()) {
            s = 26;
        }
        kipVar.i(cvx.e(s));
        kipVar.c(cvx.e(CommonUtil.d(BaseApplication.getContext())));
        c(kipVar);
        kipVar.f(kipVar.w());
        kipVar.j(kipVar.u());
        try {
            str = cvx.c(BaseApplication.getContext().getPackageManager().getPackageInfo(BaseApplication.getContext().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HandsetInfo", "getHandsetInfo() e NameNotFoundException");
            str = "";
        }
        kipVar.u(str);
        kipVar.d(cvx.e(2));
        kipVar.l(cvx.e(1));
        String d = d();
        kipVar.s(d);
        LogUtil.a("HandsetInfo", "phoneCapabilityByString: ", d);
        ByteBuffer allocate = ByteBuffer.allocate(kipVar.c(list));
        kipVar.a(list, allocate);
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HandsetInfo", "deviceCommand", deviceCommand);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private String d() {
        boolean z = ActivityCompat.checkSelfPermission(BaseApplication.getContext(), "android.permission.START_ACTIVITIES_FROM_BACKGROUND") == 0;
        LogUtil.a("HandsetInfo", "getPhoneCapability isStartActivityFromBackground: ", Boolean.valueOf(z));
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append(1);
        } else {
            sb.append(0);
        }
        String sb2 = sb.reverse().toString();
        int length = sb2.length();
        int i = length % 8 == 0 ? length / 8 : (length / 8) + 1;
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (int i2 = 0; i2 < i; i2++) {
            if (i != 1) {
                LogUtil.a("HandsetInfo", "getPhoneCapabilityByString phoneCapability more than eight digits.");
            } else {
                allocate.put((byte) Integer.parseInt(sb2, 2));
            }
        }
        return cvx.d(allocate.array());
    }

    private void a(kip kipVar) {
        kipVar.n(cvx.e(HarmonyBuild.e ? 1 : 0));
        kipVar.o(cvx.e(HarmonyBuild.c));
        kipVar.k(cvx.c(HarmonyBuild.b));
    }

    private void c(kip kipVar) {
        Object systemService = BaseApplication.getContext().getSystemService("phone");
        if (systemService instanceof TelephonyManager) {
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            kipVar.b(cvx.c(telephonyManager.getNetworkCountryIso()));
            LogUtil.a("HandsetInfo", "CountryCode", telephonyManager.getNetworkCountryIso());
        }
    }
}
