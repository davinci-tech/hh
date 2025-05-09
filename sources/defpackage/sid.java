package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback;
import com.huawei.ui.thirdpartservice.syncdata.wechatdevice.SdkManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class sid {
    public static void c(final ICloudOperationResult<Boolean> iCloudOperationResult) {
        jdx.b(new Runnable() { // from class: sic
            @Override // java.lang.Runnable
            public final void run() {
                new shh().e(ICloudOperationResult.this);
            }
        });
    }

    public static void b(final ICloudOperationResult<List<String>> iCloudOperationResult) {
        jdx.b(new Runnable() { // from class: shz
            @Override // java.lang.Runnable
            public final void run() {
                new shk().a(ICloudOperationResult.this);
            }
        });
    }

    public static void b(final DeviceInfo deviceInfo, final ICloudOperationResult<String> iCloudOperationResult) {
        jdx.b(new Runnable() { // from class: sia
            @Override // java.lang.Runnable
            public final void run() {
                new sho().c(DeviceInfo.this, iCloudOperationResult);
            }
        });
    }

    public static void d(final SupportDeviceResultCallback supportDeviceResultCallback) {
        jdx.b(new Runnable() { // from class: sif
            @Override // java.lang.Runnable
            public final void run() {
                new shf().c(SupportDeviceResultCallback.this);
            }
        });
    }

    public static void a(Map<String, String> map, ICloudOperationResult<String> iCloudOperationResult) {
        new she().a(map, iCloudOperationResult);
    }

    public static void a(Context context, String str, SdkManager.launchWechatCallBack launchwechatcallback) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        new SdkManager().e(context, arrayList, launchwechatcallback);
    }

    public static Bitmap dXU_(String str) {
        shf shfVar = new shf();
        List<DeviceInfo> c = shfVar.c();
        if (koq.b(c)) {
            return null;
        }
        for (DeviceInfo deviceInfo : c) {
            if (str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                return shfVar.dXO_(deviceInfo);
            }
        }
        return null;
    }

    public static List<DeviceInfo> b() {
        return new shf().a();
    }

    public static List<DeviceInfo> a() {
        return new shf().c();
    }

    public static void a(ICloudOperationResult<List<DeviceInfo>> iCloudOperationResult) {
        new shf().b(iCloudOperationResult);
    }

    public static List<sjy> a(List<DeviceInfo> list) {
        return new shf().c(list);
    }
}
