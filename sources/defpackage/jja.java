package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$array;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jja {
    private jja() {
    }

    public int a(Context context) {
        if (!CommonUtil.aa(context)) {
            LogUtil.c("AppMarketUtil", "getNetworkStatus not connect");
        } else {
            if (CommonUtil.ah(context)) {
                return 1;
            }
            if (e(context)) {
                return 0;
            }
            LogUtil.h("AppMarketUtil", "unknown network type");
        }
        return 2;
    }

    private boolean e(Context context) {
        return CommonUtil.l(context) == 1;
    }

    public static boolean a() {
        DeviceInfo a2 = jpt.a("AppMarketUtil");
        if (a2 == null) {
            LogUtil.h("AppMarketUtil", "isDeviceVersionNotSupport, deviceInfo is null");
            return false;
        }
        String a3 = a(a2);
        if (TextUtils.isEmpty(a3)) {
            LogUtil.h("AppMarketUtil", "isDeviceVersionNotSupport, strongGuide is empty, so support");
            return false;
        }
        String softVersion = a2.getSoftVersion();
        LogUtil.a("AppMarketUtil", "strongGuide :", a3, ", softVersion:", softVersion);
        int indexOf = a3.indexOf(AwarenessConstants.SECOND_ACTION_SPLITE_TAG);
        if (indexOf != -1) {
            a3 = a3.substring(indexOf + 1).trim();
        }
        String[] split = a3.split("\\.");
        String[] split2 = softVersion.split("\\.");
        if (split.length != split2.length) {
            LogUtil.h("AppMarketUtil", "isDeviceVersionNotSupport, version length not equals");
            return false;
        }
        return c(split, split2);
    }

    private static String a(DeviceInfo deviceInfo) {
        JSONObject d = cwc.d(deviceInfo.getHiLinkDeviceId());
        if (d == null) {
            LogUtil.h("AppMarketUtil", "getStrongGuideConfig aiTipsProduct is null");
            return "";
        }
        if (d.has("strong_guide")) {
            try {
                String string = d.getString("strong_guide");
                LogUtil.a("AppMarketUtil", "getStrongGuideConfig strongGuide:", string);
                return string;
            } catch (JSONException unused) {
                LogUtil.b("AppMarketUtil", "getStrongGuideConfig, JSONException");
                return "";
            }
        }
        LogUtil.h("AppMarketUtil", "getStrongGuideConfig aiTipsProduct not has strong_guide");
        return "";
    }

    private static boolean c(String[] strArr, String[] strArr2) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String b = b(strArr[i]);
            String b2 = b(strArr2[i]);
            if (TextUtils.isEmpty(b) || TextUtils.isEmpty(b2)) {
                LogUtil.h("AppMarketUtil", "minVersion or currentVersion is illegal");
                break;
            }
            int h = CommonUtil.h(b);
            int h2 = CommonUtil.h(b2);
            if (h2 < h) {
                LogUtil.c("AppMarketUtil", "index ", Integer.valueOf(i), " soft version less than min version");
                return true;
            }
            if (h2 > h) {
                LogUtil.c("AppMarketUtil", "index ", Integer.valueOf(i), " soft version more than min version");
                return false;
            }
            LogUtil.c("AppMarketUtil", "index ", Integer.valueOf(i), " soft version equals min version");
        }
        return false;
    }

    private static String b(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return str.substring(0, i);
            }
        }
        return str;
    }

    public void a(DeviceCapability deviceCapability, IBaseResponseCallback iBaseResponseCallback) {
        if (deviceCapability == null) {
            LogUtil.h("AppMarketUtil", "showAppMarket, deviceCapability is null");
            iBaseResponseCallback.d(0, null);
            return;
        }
        if (deviceCapability.isSupportMarketFace() && !Utils.o()) {
            LogUtil.a("AppMarketUtil", "showAppMarket, deviceCapability isSupportMarketFace and not oversea");
            iBaseResponseCallback.d(1, null);
            return;
        }
        if (c()) {
            LogUtil.h("AppMarketUtil", "showAppMarket, not login");
            iBaseResponseCallback.d(0, null);
        } else if (c(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010))) {
            LogUtil.h("AppMarketUtil", "countryCode is not support");
            iBaseResponseCallback.d(0, null);
        } else if (!deviceCapability.isSupportMarketParams()) {
            LogUtil.h("AppMarketUtil", "showAppMarket, deviceCapability is not support MarketParams");
            iBaseResponseCallback.d(0, null);
        } else {
            LogUtil.a("AppMarketUtil", "showAppMarket, deviceCapability isSupportMarketParams");
            iBaseResponseCallback.d(1, null);
        }
    }

    private boolean c() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo) || "0".equals(accountInfo)) {
            return true;
        }
        LogUtil.a("AppMarketUtil", "isNotLogin, userId is normal value, so has login");
        return false;
    }

    public SpannableString bHu_(ClickableSpan clickableSpan) {
        String string = BaseApplication.getContext().getString(R$string.IDS_application_market_internet);
        String string2 = BaseApplication.getContext().getString(R$string.IDS_application_market_storage);
        String string3 = BaseApplication.getContext().getString(R$string.IDS_application_market_user_agreement);
        String string4 = BaseApplication.getContext().getString(R$string.IDS_application_market_privacy_content, string, string2, string3);
        if (nsn.ae(BaseApplication.getContext())) {
            string4 = BaseApplication.getContext().getString(R$string.IDS_app_market_pad_privacy, string, string2, string3);
        }
        SpannableString spannableString = new SpannableString(string4);
        int indexOf = spannableString.toString().indexOf(string);
        if (indexOf != -1) {
            spannableString.setSpan(new StyleSpan(1), indexOf, string.length() + indexOf, 17);
        }
        int indexOf2 = spannableString.toString().indexOf(string2);
        if (indexOf2 != -1) {
            spannableString.setSpan(new StyleSpan(1), indexOf2, string2.length() + indexOf2, 17);
        }
        int indexOf3 = spannableString.toString().indexOf(string3);
        if (indexOf3 != -1) {
            spannableString.setSpan(Integer.valueOf(BaseApplication.getContext().getResources().getColor(R$color.common_colorAccent_pressed)), indexOf3, string3.length() + indexOf3, 33);
            spannableString.setSpan(clickableSpan, indexOf3, string3.length() + indexOf3, 17);
        }
        return spannableString;
    }

    private boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AppMarketUtil", "countryCode is empty");
            return true;
        }
        for (String str2 : BaseApplication.getContext().getResources().getStringArray(R$array.not_support_app_market_code)) {
            if (TextUtils.equals(str2, str)) {
                return true;
            }
        }
        return false;
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        if (Utils.l()) {
            LogUtil.a("AppMarketUtil", "is oversea and no cloud");
            if (!SharedPreferenceManager.b(BaseApplication.getContext())) {
                a(iBaseResponseCallback, 0);
                return;
            } else {
                a(iBaseResponseCallback, 1);
                return;
            }
        }
        jqi.a().getSwitchSetting("app_market_privacy_service_status", new IBaseResponseCallback() { // from class: jja.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_AppMarketUtil", "showAppMarketView errorCode :", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    String str = (String) obj;
                    ReleaseLogUtil.e("DEVMGR_AppMarketUtil", "appmarket_service_status :", str);
                    if ("2".equals(str)) {
                        SharedPreferenceManager.a(BaseApplication.getContext(), false);
                        jja.this.a(iBaseResponseCallback, 0);
                        return;
                    } else {
                        SharedPreferenceManager.a(BaseApplication.getContext(), true);
                        jja.this.a(iBaseResponseCallback, 1);
                        return;
                    }
                }
                if (!SharedPreferenceManager.b(BaseApplication.getContext())) {
                    jja.this.a(iBaseResponseCallback, 0);
                } else {
                    jja.this.a(iBaseResponseCallback, 1);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IBaseResponseCallback iBaseResponseCallback, int i) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
        }
    }

    public static jja b() {
        return e.b;
    }

    static class e {
        private static final jja b = new jja();
    }

    public static String a(String str) {
        return BaseApplication.getAppPackage() + "." + str;
    }

    public static String e(String str) {
        return str + "_deviceinfo";
    }

    public static void bHt_(Context context, Intent intent, String str) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a("AppMarketUtil", str, ": ActivityNotFoundException");
        }
    }

    public static String e() {
        return CompileParameterUtil.c("CONTENT_CENTER_TEST");
    }

    public static String d(String str) {
        return (!TextUtils.isEmpty(str) && str.length() > 3) ? str.substring(str.length() - 3) : str;
    }
}
