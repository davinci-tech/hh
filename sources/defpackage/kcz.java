package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kcz {
    public static void a(String str, DeviceInfo deviceInfo) {
        LogUtil.a("RriServiceSendCommandUtil", "setEcgMeasureAuthAccountCommand openId:", str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(35);
        deviceCommand.setCommandID(6);
        String c = cvx.c(str);
        String d = cvx.d(c.length() / 2);
        String e = cvx.e(1);
        StringBuilder sb = new StringBuilder(0);
        sb.append(e);
        sb.append(d);
        sb.append(c);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b(int i, DeviceInfo deviceInfo) {
        LogUtil.a("RriServiceSendCommandUtil", "getEcgDetailDataCommand index:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(35);
        deviceCommand.setCommandID(8);
        byte[] e = kcx.e(i);
        LogUtil.a("RriServiceSendCommandUtil", "getEcgDetailDataCommand data:", Arrays.toString(e));
        deviceCommand.setDataLen(e.length);
        deviceCommand.setDataContent(e);
        deviceCommand.setNeedAck(true);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void c(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(35);
        deviceCommand.setCommandID(2);
        String e = cvx.e(i);
        String d = cvx.d(1);
        String e2 = cvx.e(1);
        StringBuilder sb = new StringBuilder(0);
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("RriServiceSendCommandUtil", "setAtrialAutoMeasureCommand command data is ", cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b(int i, int i2, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(35);
        deviceCommand.setCommandID(3);
        String e = cvx.e(i);
        String e2 = cvx.e(i2);
        String d = cvx.d(1);
        String e3 = cvx.e(1);
        String d2 = cvx.d(1);
        String e4 = cvx.e(2);
        StringBuilder sb = new StringBuilder(0);
        sb.append(e3);
        sb.append(d);
        sb.append(e);
        sb.append(e4);
        sb.append(d2);
        sb.append(e2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("RriServiceSendCommandUtil", "setAtrialSingleMeasureCommand command data is ", cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void a(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(35);
        deviceCommand.setCommandID(4);
        String e = cvx.e(1);
        String d = cvx.d(1);
        String e2 = cvx.e(4);
        StringBuilder sb = new StringBuilder(0);
        sb.append(e2);
        sb.append(d);
        sb.append(e);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("RriServiceSendCommandUtil", "setClearAtrialDataCommand command data is ", cvx.d(deviceCommand.getDataContent()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void c() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceId(35);
        deviceCommand.setCommandId(9);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(0));
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("RriServiceSendCommandUtil", "getSwitchStatus command : ", sb.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void a(int i, String str) {
        if (str == null) {
            LogUtil.h("RriServiceSendCommandUtil", "packageName is null");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceId(35);
        deviceCommand.setCommandId(11);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        sb.append(cvx.e(2));
        String c = cvx.c(str);
        sb.append(cvx.d(c.length() / 2));
        sb.append(c);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("RriServiceSendCommandUtil", "5.35.11 command : ", sb.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void d(int i) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceId(35);
        deviceCommand.setCommandId(12);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(i));
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("RriServiceSendCommandUtil", "5.35.12 command : ", sb.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void d() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceId(35);
        deviceCommand.setCommandId(14);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(0));
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("RriServiceSendCommandUtil", "5.35.14 command : ", sb.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void e(JSONArray jSONArray, boolean z) throws JSONException {
        if (jSONArray == null) {
            LogUtil.h("RriServiceSendCommandUtil", "sendEcgBlockInfo json is null.");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceId(35);
        deviceCommand.setCommandId(15);
        int length = jSONArray.length();
        StringBuilder sb = new StringBuilder(16);
        String e = z ? cvx.e(1) : cvx.e(0);
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                StringBuilder sb2 = new StringBuilder(16);
                JSONObject jSONObject = (JSONObject) obj;
                String string = jSONObject.getString("version");
                int i2 = jSONObject.getInt("status");
                String b = b(jSONObject.getJSONArray("sku"));
                String c = cvx.c(string);
                String e2 = cvx.e(i2);
                sb2.append(cvx.e(3));
                sb2.append(cvx.d(c.length() / 2));
                sb2.append(c);
                sb2.append(cvx.e(4));
                sb2.append(cvx.d(e2.length() / 2));
                sb2.append(e2);
                sb2.append(b);
                sb2.append(cvx.e(8));
                sb2.append(cvx.d(e.length() / 2));
                sb2.append(e);
                sb2.insert(0, cvx.d(sb2.length() / 2)).insert(0, cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS));
                sb.append(sb2.toString());
            }
        }
        sb.insert(0, cvx.d(sb.length() / 2)).insert(0, cvx.e(129));
        deviceCommand.setDataLength(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void b() {
        LogUtil.a("RriServiceSendCommandUtil", "getEcgServiceIv");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(35);
        deviceCommand.setCommandID(17);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(0));
        sb.append(cvx.e(2));
        sb.append(cvx.e(0));
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        LogUtil.a("RriServiceSendCommandUtil", "getEcgIv sendDeviceData end.");
    }

    private static String b(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return "";
        }
        int length = jSONArray.length();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < length; i++) {
            String string = jSONArray.getString(i);
            if (TextUtils.isEmpty(string) || string.length() != 16) {
                LogUtil.h("RriServiceSendCommandUtil", "wrong sku : ", string);
            } else {
                StringBuilder sb2 = new StringBuilder(16);
                sb2.append(cvx.e(7));
                sb2.append(cvx.d(string.length() / 2));
                sb2.append(string);
                sb2.insert(0, cvx.d(sb2.length() / 2)).insert(0, cvx.e(134));
                sb.append((CharSequence) sb2);
            }
        }
        sb.insert(0, cvx.d(sb.length() / 2)).insert(0, cvx.e(OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL));
        LogUtil.a("RriServiceSendCommandUtil", "sku list : ", sb.toString());
        return sb.toString();
    }
}
