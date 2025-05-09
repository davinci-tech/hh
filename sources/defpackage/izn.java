package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbtsdk.btdatatype.datatype.OperationDeviceInfo;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class izn {
    private static final Object c = new Object();
    private static izn d;

    /* renamed from: a, reason: collision with root package name */
    private Map<String, OperationDeviceInfo> f13686a = new HashMap(16);

    public static izn c() {
        izn iznVar;
        synchronized (c) {
            if (d == null) {
                d = new izn();
            }
            iznVar = d;
        }
        return iznVar;
    }

    public OperationDeviceInfo e(String str) {
        if (TextUtils.isEmpty(str) || this.f13686a.get(str) == null) {
            return null;
        }
        return this.f13686a.get(str);
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            LogUtil.c("OpAnalyticsManager", "setDeviceInfoMap ProductType: ", Integer.valueOf(deviceInfo.getProductType()), " containsKey: ", Boolean.valueOf(this.f13686a.containsKey(deviceInfo.getDeviceIdentify())));
            if (this.f13686a.containsKey(deviceInfo.getDeviceIdentify())) {
                this.f13686a.get(deviceInfo.getDeviceIdentify()).setProductType(deviceInfo.getProductType());
                return;
            }
            OperationDeviceInfo operationDeviceInfo = new OperationDeviceInfo();
            operationDeviceInfo.setDeviceIdentify(deviceInfo.getDeviceIdentify());
            operationDeviceInfo.setProductType(deviceInfo.getProductType());
            this.f13686a.put(deviceInfo.getDeviceIdentify(), operationDeviceInfo);
        }
    }

    public void b(DeviceInfo deviceInfo, long j) {
        if (deviceInfo == null) {
            LogUtil.c("OpAnalyticsManager", "setStartConnectTime deviceInfo is null.");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || j <= 0) {
            return;
        }
        OperationDeviceInfo operationDeviceInfo = this.f13686a.get(deviceInfo.getDeviceIdentify());
        if (operationDeviceInfo == null) {
            LogUtil.c("OpAnalyticsManager", "setStartConnectTime operationDeviceInfo is null.");
        } else {
            operationDeviceInfo.setStartConnectTime(j);
            operationDeviceInfo.setDeviceBluetoothType(deviceInfo.getDeviceBluetoothType());
        }
    }

    public void a(String str, long j) {
        if (TextUtils.isEmpty(str) || j <= 0) {
            return;
        }
        OperationDeviceInfo operationDeviceInfo = this.f13686a.get(str);
        if (operationDeviceInfo == null) {
            LogUtil.c("OpAnalyticsManager", "setConnectFailedTime operationDeviceInfo is null.");
        } else {
            operationDeviceInfo.setEndConnectTime(j);
        }
    }

    public void a(String str, int i) {
        if (TextUtils.isEmpty(str) || i <= 0) {
            return;
        }
        OperationDeviceInfo operationDeviceInfo = this.f13686a.get(str);
        if (operationDeviceInfo == null) {
            LogUtil.c("OpAnalyticsManager", "setErrorCode operationDeviceInfo is null.");
        } else {
            operationDeviceInfo.setErrorCode(i);
        }
    }

    public void c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        OperationDeviceInfo operationDeviceInfo = this.f13686a.get(str);
        if (operationDeviceInfo == null) {
            LogUtil.c("OpAnalyticsManager", "setErrorCode operationDeviceInfo is null.");
        } else {
            operationDeviceInfo.setErrorMeathod(str2);
        }
    }
}
