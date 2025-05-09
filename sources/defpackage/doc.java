package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class doc {
    public static HashMap<String, String> b(Context context) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        if (!LoginInit.getInstance(context).isBrowseMode()) {
            hashMap.put("x-huid", LoginInit.getInstance(context).getAccountInfo(1011));
        }
        hashMap.put("x-version", CommonUtil.c(context));
        return hashMap;
    }

    public static HashMap<String, String> a(Context context) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("phoneType", PhoneInfoUtils.getHuaweiManufaturerOrEmui());
        String deviceId = LoginInit.getInstance(context).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("bindDeviceType", String.valueOf(CommonUtil.h(context)));
        hashMap.put("wearType", "");
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", String.valueOf(1));
        hashMap.put("countryCode", LoginInit.getInstance(context).getAccountInfo(1010));
        hashMap.put("language", mtj.e(null));
        hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            try {
                hashMap.put("token", URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                LogUtil.b("PostUtil", "token encode Exception ", e.toString());
            }
        }
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("upDeviceType", LoginInit.getInstance(context).getDeviceType());
        if (LoginInit.getInstance(context).isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", BaseApplication.getAppPackage());
        }
        hashMap.put("siteId", LoginInit.getInstance(context).getAccountInfo(1009));
        hashMap.put("manufacturer", Build.MANUFACTURER);
        return hashMap;
    }
}
