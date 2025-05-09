package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.main.stories.guidepage.data.GuideResource;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes6.dex */
public class qbg {
    public static HashMap<String, String> b() {
        Context context = BaseApplication.getContext();
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            try {
                hashMap.put("token", URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                LogUtil.b("GuideCommonUtil", "token encode Exception ", e.toString());
            }
        }
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("siteId", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        hashMap.put("appId", BaseApplication.getAppPackage());
        String deviceId = LoginInit.getInstance(context).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", String.valueOf(2));
        hashMap.put("language", mtj.e(null));
        hashMap.put("ts", String.valueOf(pfh.g()));
        hashMap.put("timeZone", HiDateUtil.d((String) null).replace(Marker.ANY_NON_NULL_MARKER, "%2B"));
        hashMap.put("countryCode", LoginInit.getInstance(context).getAccountInfo(1010));
        hashMap.put("phoneType", PhoneInfoUtils.getPhoneType());
        return hashMap;
    }

    public static HashMap<String, String> b(String str) {
        Context context = BaseApplication.getContext();
        if (TextUtils.isEmpty(str)) {
            str = CommonUtil.c(context);
        }
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("x-huid", LoginInit.getInstance(context).getAccountInfo(1011));
        hashMap.put("x-version", str);
        return hashMap;
    }

    public static String a(String str) {
        String str2;
        try {
            str2 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "guidevideo" + File.separator;
        } catch (IOException unused) {
            LogUtil.b("GuideCommonUtil", "IOException");
            str2 = null;
        }
        return str2 + c(str);
    }

    private static String c(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1) : "";
    }

    public static void e(List<GuideResource> list) {
        LogUtil.a("GuideCommonUtil", "enter saveGuidePageData");
        if (koq.b(list)) {
            LogUtil.h("GuideCommonUtil", "guideResources is Empty");
        } else {
            SharedPreferenceManager.e(BaseApplication.getContext(), "guide_page_caching_data", "catching_data", new Gson().toJson(list), (StorageParams) null);
        }
    }

    public static List<GuideResource> a() {
        LogUtil.a("GuideCommonUtil", "enter getGuidePageData");
        ArrayList arrayList = new ArrayList();
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "guide_page_caching_data", "catching_data");
        if (TextUtils.isEmpty(b)) {
            return arrayList;
        }
        try {
            return (List) new Gson().fromJson(b, new TypeToken<ArrayList<GuideResource>>() { // from class: qbg.1
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("GuideCommonUtil", "JsonSyntaxException ");
            return arrayList;
        }
    }

    public static boolean d() {
        long j;
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "guide_page_caching_data", accountInfo + "catching_data_version");
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        try {
            j = Long.parseLong(SharedPreferenceManager.b(BaseApplication.getContext(), "guide_page_caching_data", accountInfo + "catching_data_expire_time"));
        } catch (NumberFormatException unused) {
            LogUtil.b("GuideCommonUtil", "NumberFormatException");
            j = 0;
        }
        if (j < System.currentTimeMillis()) {
            LogUtil.a("GuideCommonUtil", "time is expired");
            c("", "");
            qbd.a("");
            return false;
        }
        String e = e(CommonUtil.e(BaseApplication.getContext()));
        LogUtil.a("GuideCommonUtil", "cachedVersion:", b, " appVersion:", e);
        return CommonUtil.d(b, e) == 0;
    }

    public static void c(String str, String str2) {
        String e = e(str);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        SharedPreferenceManager.e(BaseApplication.getContext(), "guide_page_caching_data", accountInfo + "catching_data_version", e, (StorageParams) null);
        SharedPreferenceManager.e(BaseApplication.getContext(), "guide_page_caching_data", accountInfo + "catching_data_expire_time", str2, (StorageParams) null);
    }

    public static String e(String str) {
        int indexOf;
        return (TextUtils.isEmpty(str) || (indexOf = str.indexOf(Constants.LINK)) <= 0) ? str : str.substring(0, indexOf);
    }
}
