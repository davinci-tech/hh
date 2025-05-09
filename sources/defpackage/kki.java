package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class kki {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14431a = {"android.permission.SEND_SMS"};

    public static void c(byte[] bArr, DeviceInfo deviceInfo) {
        int a2 = a(bArr);
        if (a2 == 0) {
            return;
        }
        int i = ((a2 & 1) == 1 && jdi.c(BaseApplication.getContext(), f14431a)) ? 1 : 0;
        LogUtil.a("PermissionQueryUtils", "parseQueryPermissionStatus queryType: ", Integer.valueOf(a2), " result: ", Integer.valueOf(i));
        c(a2, i, deviceInfo);
    }

    private static void c(int i, int i2, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(56);
        byte[] d = new bms().h(1, i).h(2, i2).d();
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private static int a(byte[] bArr) {
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() <= 4) {
            LogUtil.h("PermissionQueryUtils", "parseCommandPackage resultDataHex is empty");
            return 0;
        }
        try {
            List<cwd> e = new cwl().a(d.substring(4, d.length())).e();
            if (e == null || e.isEmpty()) {
                LogUtil.h("PermissionQueryUtils", "parseCommandPackage tlvList is empty");
                return 0;
            }
            cwd cwdVar = e.get(0);
            if (cwdVar == null) {
                LogUtil.h("PermissionQueryUtils", "parseCommandPackage tlv is null");
                return 0;
            }
            if (CommonUtil.a(cwdVar.e(), 16) != 1) {
                LogUtil.h("PermissionQueryUtils", "parseCommandPackage type is invalid");
                return 0;
            }
            return CommonUtil.a(cwdVar.c(), 16);
        } catch (cwg unused) {
            LogUtil.b("PermissionQueryUtils", "parseCommandPackage TlvException");
            return 0;
        }
    }
}
