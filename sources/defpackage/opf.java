package defpackage;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterResultReceiver;
import com.huawei.haf.router.Guidepost;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes6.dex */
public class opf {
    private static String[] c;
    private static String[] e;
    private static final String[] d = {"https://url.cloud.huawei.com/1Lfn1eswP6?a=", "https://urldre.cloud.huawei.com/97J6mTfn5S?a=", "https://url.cloud.huawei.com/a1jJk451Uk", "https://urldre.cloud.huawei.com/a1mpQudURi", "http://contentcenter-drcn.platform.hicloud.com/cch5/health/qr/qr.html", "https://url.cloud.huawei.com/1xbRdNHELC"};
    private static final String[] b = {"https://api.995120.cn/static/hm505/single/gt2/help.html", "https://h5hosting-drcn.dbankcdn.cn/"};

    /* renamed from: a, reason: collision with root package name */
    private static String[] f15834a = null;

    public static boolean e(String str) {
        return d(str, d);
    }

    public static boolean c(String str) {
        if (e == null) {
            e = BaseApplication.e().getResources().getStringArray(R.array._2130968717_res_0x7f04008d);
        }
        return d(str, e);
    }

    public static boolean d(String str) {
        return d(str, b);
    }

    public static boolean a(String str) {
        if (c == null) {
            c = BaseApplication.e().getResources().getStringArray(R.array._2130968668_res_0x7f04005c);
        }
        return d(str, c);
    }

    private static boolean d(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.toUpperCase(Locale.ENGLISH).startsWith(str2.toUpperCase(Locale.ENGLISH))) {
                return true;
            }
        }
        return false;
    }

    public static String b(String str, String str2) {
        int length;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        String str3 = "&" + str + "=";
        int indexOf = str2.indexOf(str3);
        if (indexOf == -1 || (length = indexOf + str3.length()) >= str2.length()) {
            return "";
        }
        String substring = str2.substring(length);
        if (substring.startsWith("&")) {
            return "";
        }
        int indexOf2 = substring.indexOf("&");
        return indexOf2 == -1 ? substring : substring.substring(0, indexOf2);
    }

    public static void deU_(Activity activity) {
        nrh.b(BaseApplication.e(), R.string.IDS_device_wifi_my_qrcode_error_qrcode);
        if (activity == null) {
            LogUtil.h("QrCodeUtils", "linkError() activity is null");
        } else {
            activity.finish();
        }
    }

    public static void deV_(Activity activity) {
        nrh.b(BaseApplication.e(), R.string.IDS_device_wifi_my_qrcode_overdue);
        if (activity == null) {
            LogUtil.h("QrCodeUtils", "linkExpired() activity is null");
        } else {
            activity.finish();
        }
    }

    public static boolean c(Context context) {
        if (context == null) {
            LogUtil.h("QrCodeUtils", "isMainActivityJumpDirectly context is null");
            return false;
        }
        boolean equals = "not_jump_directly".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "SP_SCHEME_MAIN_ACTIVITY_JUMP_DIRECTLY"));
        if (!equals) {
            SharedPreferenceManager.e(context, Integer.toString(10000), "SP_SCHEME_MAIN_ACTIVITY_JUMP_DIRECTLY", "not_jump_directly", (StorageParams) null);
        }
        return equals && AuthorizationUtils.a(context);
    }

    public static void deT_(final Context context, Uri uri, String str) {
        String queryParameter = uri.getQueryParameter(BleConstants.KEY_PATH);
        if (TextUtils.isEmpty(queryParameter)) {
            LogUtil.h("QrCodeUtils", "handleDeepLink path == null");
            return;
        }
        int indexOf = str.indexOf(queryParameter);
        int length = queryParameter.length() + indexOf + 1;
        if (indexOf == -1 || str.length() <= length) {
            LogUtil.h("QrCodeUtils", "handleDeepLink queryParameter=", str);
            return;
        }
        final String str2 = "/" + queryParameter + "?" + str.substring(length);
        AppRouter.zi_(Uri.parse("huaweischeme://healthapp" + str2)).b(context, new AppRouterResultReceiver() { // from class: opf.1
            @Override // com.huawei.haf.router.AppRouterResultReceiver
            public void a(Guidepost guidepost, int i) {
                LogUtil.a("QrCodeUtils", "handleDeepLink onRouteResult, resultCode=", Integer.valueOf(i));
                if (i != 10) {
                    opf.b(context);
                }
            }

            @Override // com.huawei.haf.router.AppRouterResultReceiver, com.huawei.haf.router.NaviPluginCallback
            public void onPluginFound(Guidepost guidepost, String str3) {
                LogUtil.a("QrCodeUtils", "handleDeepLink onPluginFound, pluginName=", str3);
            }

            @Override // com.huawei.haf.router.AppRouterResultReceiver, com.huawei.haf.router.NaviPluginCallback
            public void onPluginLost(Guidepost guidepost, String str3) {
                LogUtil.a("QrCodeUtils", "handleDeepLink onPluginLost, pluginName=", str3);
                opf.b(context);
            }

            @Override // com.huawei.haf.router.AppRouterResultReceiver, com.huawei.haf.router.NaviPluginCallback
            public void onPluginNone(Guidepost guidepost) {
                LogUtil.a("QrCodeUtils", "handleDeepLink onPluginNone, pathQuery=", str2);
                opf.b(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public static boolean b(String str) {
        if (f15834a == null) {
            f15834a = BaseApplication.e().getResources().getStringArray(R.array._2130968701_res_0x7f04007d);
            String c2 = CompileParameterUtil.c("APP_DOWNLOAD_SPECIAL_QR_CODE_URL");
            if (!TextUtils.isEmpty(c2)) {
                String[] strArr = f15834a;
                String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length + 1);
                f15834a = strArr2;
                strArr2[strArr2.length - 1] = c2;
            }
        }
        return d(str, f15834a);
    }
}
