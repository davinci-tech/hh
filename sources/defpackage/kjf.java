package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcloudmodel.hwwear.hag.model.tide.HagTide;
import com.huawei.hwcloudmodel.hwwear.hag.model.tide.HagTideStationBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kjf {
    private static String b(jbz jbzVar) {
        if (jbzVar == null || jbzVar.b() == null) {
            LogUtil.h("FutureTidePushUtils", "makeTideCommand() weather is null or HagTideDataBean is null");
            return "";
        }
        String str = "" + cvx.e(129);
        List<HagTideStationBean> tideStations = jbzVar.b().getTideStations();
        if (tideStations != null && !tideStations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tideStations.size(); i++) {
                HagTideStationBean hagTideStationBean = tideStations.get(i);
                if (hagTideStationBean == null) {
                    LogUtil.h("FutureTidePushUtils", "makeTideCommand() tideStationBean is null");
                } else {
                    sb.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS));
                    String e = e(hagTideStationBean);
                    sb.append(cvx.d(e.length() / 2));
                    sb.append(e);
                }
            }
            return str + cvx.d(sb.toString().length() / 2) + sb.toString();
        }
        String str2 = str + cvx.e(0);
        jrb.d("FutureTidePushUtils", "getTideStations is null,please check Weather CP");
        return str2;
    }

    private static String e(HagTideStationBean hagTideStationBean) {
        String str;
        List<HagTide> hagTides = hagTideStationBean.getHagTides();
        if (hagTides == null || hagTides.isEmpty()) {
            jrb.d("FutureTidePushUtils", "Tides for given station " + hagTideStationBean.getName() + " not exist,please check Weather CP");
            str = "00";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hagTides.size(); i++) {
                if (hagTides.get(i) == null) {
                    LogUtil.h("FutureTidePushUtils", "parseHagTideStation() hagTide is null");
                } else {
                    sb.append(cvx.e(OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL));
                    String a2 = a(hagTides.get(i));
                    sb.append(cvx.d(a2.length() / 2));
                    sb.append(a2);
                }
            }
            str = "" + cvx.d(sb.toString().length() / 2) + sb.toString();
        }
        String name = hagTideStationBean.getName();
        if (!TextUtils.isEmpty(name)) {
            String c = cvx.c(name);
            if (c.length() > 120) {
                c = c.substring(0, 120);
            }
            str = str + cvx.e(3) + cvx.d(c.length() / 2) + c;
        } else {
            jrb.d("FutureTidePushUtils", "get TideStationName is null, please check Weather CP");
        }
        if (TextUtils.isEmpty(str)) {
            str = cvx.e(0);
        }
        return cvx.e(UserInfomation.WEIGHT_DEFAULT_ENGLISH) + str;
    }

    private static String a(HagTide hagTide) {
        String str = "";
        if (hagTide == null) {
            return "";
        }
        if (hagTide.getTideTime() != 0) {
            String b = cvx.b(hagTide.getTideTime());
            str = "" + (cvx.e(6) + cvx.e(b.length() / 2) + b);
        }
        if (hagTide.getTideType() != 0) {
            String e = cvx.e(hagTide.getTideType());
            str = str + (cvx.e(7) + cvx.e(e.length() / 2) + e);
        }
        if (hagTide.getTideHeight() == -32768) {
            return str;
        }
        String a2 = cvx.a(hagTide.getTideHeight());
        return str + (cvx.e(8) + cvx.e(a2.length() / 2) + a2);
    }

    public static void e(jbz jbzVar, DeviceInfo deviceInfo) {
        LogUtil.a("FutureTidePushUtils", "pushTideToDevice()");
        if (!c()) {
            LogUtil.h("FutureTidePushUtils", "pushTideToDevice() has not Capability");
            return;
        }
        String b = b(jbzVar);
        if (!TextUtils.isEmpty(b)) {
            LogUtil.a("FutureTidePushUtils", "pushTideToDevice(), command :", b);
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(15);
            deviceCommand.setCommandID(11);
            deviceCommand.setDataContent(cvx.a(b));
            deviceCommand.setDataLen(cvx.a(b).length);
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
            jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
            return;
        }
        LogUtil.h("FutureTidePushUtils", "pushTideToDevice() command is empty");
    }

    public static boolean c() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", "FutureTidePushUtils");
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        if (deviceCapability == null) {
            LogUtil.h("FutureTidePushUtils", "isSupportTideCapability() Capability is null");
            return false;
        }
        if (deviceCapability.isSupportTide()) {
            return true;
        }
        LogUtil.h("FutureTidePushUtils", "isSupportTideCapability() has not Capability");
        return false;
    }

    public static void e(Map<String, Object> map, String str) {
        if (map == null || map.isEmpty() || TextUtils.isEmpty(str)) {
            return;
        }
        ixx.d().d(BaseApplication.getContext(), str, map, 0);
    }
}
