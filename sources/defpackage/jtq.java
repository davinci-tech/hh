package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jtq {

    /* renamed from: a, reason: collision with root package name */
    private static jtq f14080a;
    private static final Object d = new Object();
    private Map<String, IBaseCommonCallback> b = new HashMap();

    private jtq() {
    }

    public static jtq e() {
        jtq jtqVar;
        synchronized (d) {
            if (f14080a == null) {
                f14080a = new jtq();
            }
            jtqVar = f14080a;
        }
        return jtqVar;
    }

    public void a(String str, IBaseCommonCallback iBaseCommonCallback) {
        if (TextUtils.isEmpty(str) || iBaseCommonCallback == null) {
            return;
        }
        this.b.put(str, iBaseCommonCallback);
    }

    private IBaseCommonCallback b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.b.get(str);
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str) && this.b.containsKey(str)) {
            this.b.remove(str);
            LogUtil.a("BatterySupport", "removeBatteryTaskCallback success.");
        }
    }

    public void e(DeviceInfo deviceInfo, byte[] bArr) {
        int c;
        LogUtil.a("BatterySupport", "handleWearKitBatteryValue enter");
        if (bArr == null || bArr.length <= 0) {
            LogUtil.b("BatterySupport", "handleWearKitBatteryValue() Received data is null");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.b("BatterySupport", "handleWearKitBatteryValue() Received deviceInfo is null");
            return;
        }
        if (bArr.length > 1 && bArr[0] == 1 && bArr[1] == 8) {
            if (bArr.length > 3 && bArr[2] == Byte.MAX_VALUE) {
                LogUtil.b("BatterySupport", "handleWearKitBatteryValue() get !V0 battery level timeout.");
                c = -1;
            } else {
                c = c(bArr);
            }
            LogUtil.a("BatterySupport", "handleWearKitBatteryValue battery value is: ", Integer.valueOf(c));
            d(c, deviceInfo);
        }
    }

    private int c(byte[] bArr) {
        try {
            String d2 = cvx.d(bArr);
            LogUtil.a("BatterySupport", "getBatteryValue() Received byte message is:", Arrays.toString(bArr), "getBatteryValue() Received message is:", d2);
            if (TextUtils.isEmpty(d2) || d2.length() <= 8) {
                return 0;
            }
            String substring = d2.substring(8);
            LogUtil.a("BatterySupport", "getBatteryValue() batteryString is:", substring);
            int w = CommonUtil.w(substring);
            int i = w >= 0 ? w : 0;
            if (i > 100) {
                return 100;
            }
            return i;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private void d(int i, DeviceInfo deviceInfo) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mDeviceName", deviceInfo.getDeviceName());
        jsonObject.addProperty("mProductType", Integer.valueOf(deviceInfo.getProductType()));
        jsonObject.addProperty("mBatteryValue", Integer.valueOf(i));
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);
        LogUtil.a("BatterySupport", "onResponseBatteryValue response is:", jsonArray.toString());
        try {
            try {
                IBaseCommonCallback b = b(deviceInfo.getDeviceIdentify());
                if (b != null) {
                    LogUtil.a("BatterySupport", "onResponseBatteryValue IBaseCommonCallback is not null");
                    b.onResponse(0, jsonArray.toString());
                }
            } catch (Exception unused) {
                LogUtil.b("BatterySupport", "onResponseBatteryValue RemoteException");
            }
        } finally {
            c(deviceInfo.getDeviceIdentify());
        }
    }
}
