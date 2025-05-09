package defpackage;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.List;

/* loaded from: classes5.dex */
public class jtm {
    public static boolean e(String str) {
        cwe a2 = a(str);
        if (a2 == null) {
            LogUtil.h("VariableHandshakeUtils", "checkResponseCode tlvFather is null");
            return false;
        }
        List<cwd> e = a2.e();
        if (e.size() <= 0) {
            return true;
        }
        try {
            return b(e, CommonUtil.w(e.get(0).e()));
        } catch (NumberFormatException unused) {
            LogUtil.a("VariableHandshakeUtils", "checkResponseCode NumberFormatException");
            return false;
        }
    }

    private static boolean b(List<cwd> list, int i) {
        if (i != 127) {
            return true;
        }
        try {
            int w = CommonUtil.w(list.get(0).c());
            if (w == 100000) {
                return true;
            }
            LogUtil.a("VariableHandshakeUtils", "error code = " + w);
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.a("VariableHandshakeUtils", "NumberFormatException");
            return false;
        }
    }

    public static cwe a(String str) {
        if (str == null) {
            LogUtil.h("VariableHandshakeUtils", "praseDataToTlvFormart dataStrInfo is null");
            return null;
        }
        try {
            return new cwl().a(str.substring(4, str.length()));
        } catch (cwg unused) {
            LogUtil.a("VariableHandshakeUtils", "tlv resolve exception.");
            return null;
        }
    }

    public static void a(UniteDevice uniteDevice, int i) {
        if (uniteDevice == null) {
            LogUtil.h("VariableHandshakeUtils", "device is null");
            return;
        }
        DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
        if (deviceInfo == null) {
            LogUtil.h("VariableHandshakeUtils", "sendConnectStateBroadcast deviceInfo is null");
            return;
        }
        deviceInfo.setDeviceConnectState(i);
        com.huawei.health.devicemgr.business.entity.DeviceInfo c = blc.c(uniteDevice);
        String e = jta.d().e(uniteDevice.getIdentify());
        c.setRelationship(e);
        Intent bJs_ = bJs_(c);
        LogUtil.h("VariableHandshakeUtils", "sendConnectStateBroadcast connectState is: ", Integer.valueOf(i), " deviceRelation: ", e);
        BaseApplication.getContext().sendOrderedBroadcast(bJs_, LocalBroadcast.c);
    }

    public static Intent bJs_(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.a("VariableHandshakeUtils", "Enter createDeviceStateIntent.");
        Intent intent = new Intent("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intent.setExtrasClassLoader(com.huawei.health.devicemgr.business.entity.DeviceInfo.class.getClassLoader());
        Bundle bundle = new Bundle();
        bundle.putParcelable("deviceinfo", deviceInfo);
        intent.putExtras(bundle);
        return intent;
    }

    public static boolean a(String str, String str2) {
        if (str == null || str2 == null || str2.length() < 4) {
            LogUtil.b("VariableHandshakeUtils", "isCurrentOobeCommand: parameter is invalid.");
            return false;
        }
        String substring = str2.substring(0, 4);
        LogUtil.a("VariableHandshakeUtils", "isCurrentOobeCommand: replyCommand is: ", substring);
        return str.equals(substring);
    }
}
