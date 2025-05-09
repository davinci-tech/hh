package defpackage;

import android.content.res.Configuration;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kkj {
    public static void d(final DeviceInfo deviceInfo) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kkj.5
            @Override // java.lang.Runnable
            public void run() {
                kkj.c(DeviceInfo.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(DeviceInfo deviceInfo) {
        HwDeviceMgrInterface b = jsz.b(BaseApplication.getContext());
        List<DeviceInfo> c = c();
        if (c.size() == 0) {
            LogUtil.h("LanguageUtil", "connected device list is null,can not change any language");
            return;
        }
        Map<String, DeviceCapability> queryDeviceCapability = b.queryDeviceCapability(3, "", "LanguageUtil");
        for (DeviceInfo deviceInfo2 : c) {
            if (deviceInfo2 != null && !TextUtils.isEmpty(deviceInfo2.getDeviceIdentify()) && (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || deviceInfo2.getDeviceIdentify().contains(deviceInfo.getDeviceIdentify()))) {
                DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo2.getDeviceIdentify());
                if (deviceCapability != null && deviceCapability.isLanguage()) {
                    d(deviceInfo2, b);
                }
            }
        }
    }

    private static List<DeviceInfo> c() {
        ArrayList arrayList = new ArrayList(16);
        for (DeviceInfo deviceInfo : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "LanguageUtil")) {
            if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
                arrayList.add(deviceInfo);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void d(DeviceInfo deviceInfo, HwDeviceMgrInterface hwDeviceMgrInterface) {
        int i;
        boolean z;
        int length;
        int length2;
        String a2 = a(deviceInfo);
        byte[] a3 = cvx.a(cvx.c(a2));
        String ae = CommonUtil.ae();
        byte[] a4 = cvx.a(cvx.c(ae));
        LogUtil.a("LanguageUtil", "current language script: ", ae);
        boolean z2 = false;
        if (deviceInfo.getProductType() == 7 || deviceInfo.getProductType() == 14) {
            i = 0;
            z = false;
        } else {
            boolean b = UnitUtil.b();
            LogUtil.a("LanguageUtil", "Need set unit info unit info:", Integer.valueOf(b ? 1 : 0));
            z = true;
            i = b;
        }
        if (cwi.c(deviceInfo, 57) && ae.length() > 0) {
            z2 = true;
        }
        LogUtil.a("LanguageUtil", "isSupportScript: ", Boolean.valueOf(z2));
        if (z) {
            length = a3.length + 5;
            if (z2) {
                length2 = a4.length;
                length += length2 + 2;
            }
        } else {
            length = a3.length + 2;
            if (z2) {
                length2 = a4.length;
                length += length2 + 2;
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.put((byte) 1);
        allocate.put((byte) a3.length);
        allocate.put(a3);
        if (z) {
            allocate.put((byte) 2);
            allocate.put((byte) 1);
            allocate.put((byte) i);
        }
        if (z2) {
            allocate.put((byte) 3);
            allocate.put((byte) a4.length);
            allocate.put(a4);
        }
        a(deviceInfo, hwDeviceMgrInterface, a2, allocate);
    }

    public static String a(DeviceInfo deviceInfo) {
        if (cwi.c(deviceInfo, 57)) {
            Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
            return configuration.locale.getLanguage() + Constants.LINK + configuration.locale.getCountry();
        }
        return CommonUtil.u();
    }

    private static void a(DeviceInfo deviceInfo, HwDeviceMgrInterface hwDeviceMgrInterface, String str, ByteBuffer byteBuffer) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(12);
        deviceCommand.setCommandID(1);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        hwDeviceMgrInterface.sendDeviceData(deviceCommand);
        LogUtil.a("LanguageUtil", "sendCommand2Device language: ", str, " Command:", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
    }
}
