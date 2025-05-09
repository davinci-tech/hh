package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public class nuj {

    /* renamed from: a, reason: collision with root package name */
    private static String f15504a;
    private static String c;
    private static String e;
    private static final String[] d = {"wifiaren", "wifira", "Wifia", "Wi-Fi", "wifi"};
    private static ArrayList<String> b = new ArrayList<>(10);
    private static ArrayList<String> g = new ArrayList<>(10);
    private static ArrayList<String> i = new ArrayList<>(10);

    private static String d() {
        try {
            File filesDir = BaseApplication.getContext().getFilesDir();
            return filesDir == null ? "" : filesDir.getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("DeclarationUtil", "getSourcePath catch IOException");
            return "";
        }
    }

    public static nzf e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("DeclarationUtil", "getDeclaration featureId or emuiVersion is null or empty");
            return new nzf();
        }
        try {
            nys c2 = nys.c();
            nyx c3 = c2.c(e(str), str2);
            if (c3 == null) {
                LogUtil.h("DeclarationUtil", "getDeclaration featureEntity is null");
                return new nzf();
            }
            return c2.d(c3.c(), c(str, c3.e()));
        } catch (IOException unused) {
            LogUtil.b("DeclarationUtil", "getDeclaration catch IOException");
            return new nzf();
        } catch (XmlPullParserException unused2) {
            LogUtil.b("DeclarationUtil", "getDeclaration catch XmlPullParserException");
            return new nzf();
        }
    }

    public static void b(List<nyu> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeclarationUtil", "sortDeclarationByIndex is null");
        } else {
            Collections.sort(list, new Comparator<nyu>() { // from class: nuj.2
                @Override // java.util.Comparator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public int compare(nyu nyuVar, nyu nyuVar2) {
                    return nyuVar.a() - nyuVar2.a();
                }
            });
        }
    }

    public static void a(final Context context, String str) {
        if (context == null) {
            LogUtil.h("DeclarationUtil", "openBrowser: context is null. ", str);
            return;
        }
        if (!d(str)) {
            LogUtil.h("DeclarationUtil", "openBrowser: url is invalid. url: ", str);
            return;
        }
        final Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str).normalizeScheme());
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null && intent.resolveActivity(packageManager) != null) {
            try {
                ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
                if (resolveActivity != null && resolveActivity.activityInfo != null) {
                    final ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
                    intent.setComponent(componentName);
                    if (context instanceof Activity) {
                        ((Activity) context).runOnUiThread(new Runnable() { // from class: nuj.3
                            @Override // java.lang.Runnable
                            public void run() {
                                Intent intent2 = intent;
                                String packageName = componentName.getPackageName();
                                Context context2 = context;
                                nsn.cLM_(intent2, packageName, context2, context2.getString(R.string._2130847432_res_0x7f0226c8));
                            }
                        });
                    }
                    LogUtil.a("DeclarationUtil", "openBrowser by:", resolveActivity.activityInfo.packageName);
                    return;
                }
                LogUtil.h("DeclarationUtil", "openBrowser resolveInfo or activityInfo is null");
                nrh.b(context, R.string._2130843955_res_0x7f021933);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("DeclarationUtil", "openBrowser: ActivityNotFoundException occurred");
                nrh.b(context, R.string._2130843955_res_0x7f021933);
                return;
            }
        }
        LogUtil.h("DeclarationUtil", "openBrowser: have no browser.");
        nrh.b(context, R.string._2130843955_res_0x7f021933);
    }

    private static String e(String str) {
        return f15504a + File.separator + str;
    }

    private static String c(String str, String str2) {
        String str3 = "_" + mtj.e(null);
        String str4 = c + File.separator + str + File.separator + str2 + str3 + WatchFaceConstant.XML_SUFFIX;
        if (new File(str4).exists()) {
            return str4;
        }
        String str5 = c + File.separator + str + File.separator + str + "_en.xml";
        LogUtil.a("DeclarationUtil", "getLanguageDir language file not exist. ", "language: ", str3);
        return str5;
    }

    private static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeclarationUtil", "isUrlValid: url is null or empty.");
            return false;
        }
        if (Pattern.compile("[<>]").matcher(Normalizer.normalize(str, Normalizer.Form.NFKC)).find()) {
            LogUtil.a("DeclarationUtil", "isUrlValid: url is illegal.");
            return false;
        }
        LogUtil.a("DeclarationUtil", "isUrlValid: url is correct.");
        return str.contains("huawei.com");
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeclarationUtil", "str is empty");
            return str;
        }
        if (!Utils.o()) {
            int i2 = 0;
            while (true) {
                String[] strArr = d;
                if (i2 >= strArr.length) {
                    break;
                }
                String lowerCase = str.toLowerCase();
                String lowerCase2 = strArr[i2].toLowerCase();
                if (lowerCase.contains(lowerCase2)) {
                    int indexOf = lowerCase.indexOf(lowerCase2);
                    str = str.replaceAll(str.substring(indexOf, lowerCase2.length() + indexOf), "WLAN");
                }
                i2++;
            }
        }
        return str;
    }

    private static nyz c() throws IOException {
        nyz nyzVar = new nyz();
        String e2 = mrx.e(new File(e));
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("DeclarationUtil", "configJsonText is null");
            return nyzVar;
        }
        try {
            String jSONObject = new JSONObject(CommonUtil.z(e2)).toString();
            LogUtil.a("DeclarationUtil", "jsonObjectStr:", jSONObject);
            return (nyz) new Gson().fromJson(jSONObject, new TypeToken<nyz>() { // from class: nuj.4
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("DeclarationUtil", "loadConfigJson JsonSyntaxException");
            return nyzVar;
        } catch (JSONException unused2) {
            LogUtil.b("DeclarationUtil", "loadConfigJson - JSONException");
            return nyzVar;
        }
    }

    public static void d(int i2, String str) {
        char c2;
        b(str);
        LogUtil.a("DeclarationUtil", "getAllFeatureId: ", f15504a, "  ", c, " ", e);
        try {
            b.clear();
            g.clear();
            i.clear();
            nyz c3 = c();
            if (c3 == null) {
                LogUtil.a("DeclarationUtil", "getAllFeatureId FeatureIdJson is empty");
                return;
            }
            List<nyy> e2 = c3.e();
            if (e2 != null) {
                for (nyy nyyVar : e2) {
                    if (nyyVar != null) {
                        String e3 = nyyVar.e();
                        switch (e3.hashCode()) {
                            case 48:
                                if (e3.equals("0")) {
                                    c2 = 0;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 49:
                                if (e3.equals("1")) {
                                    c2 = 1;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 50:
                                if (e3.equals("2")) {
                                    c2 = 2;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            default:
                                c2 = 65535;
                                break;
                        }
                        if (c2 != 0) {
                            if (c2 != 1) {
                                if (c2 == 2) {
                                    d(nyyVar);
                                } else {
                                    LogUtil.a("DeclarationUtil", "getAllFeatureId the default branch");
                                }
                            } else if (i2 == 2 || i2 == 3) {
                                d(nyyVar);
                            }
                        } else if (i2 == 0 || i2 == 1) {
                            d(nyyVar);
                        }
                    }
                }
            }
        } catch (IOException unused) {
            LogUtil.a("DeclarationUtil", "getAllFeatureId catch IOException");
        }
    }

    private static void b(String str) {
        String str2 = d() + File.separator + "plugins" + File.separator + str + File.separator + str + File.separator + "declaration";
        LogUtil.a("DeclarationUtil", "initPath: ", str2);
        f15504a = str2 + File.separator + NetworkService.Constants.CONFIG_SERVICE;
        c = str2 + File.separator + "language";
        e = str2 + File.separator + "featureIds.json";
    }

    private static void d(nyy nyyVar) {
        if (nyyVar.b().equals("0")) {
            b.add(nyyVar.a());
        }
        if (nyyVar.b().equals("1")) {
            g.add(nyyVar.a());
        }
        if ("1".equals(nyyVar.d())) {
            i.add(nyyVar.a());
        }
    }

    public static String[] e() {
        return g.size() > 0 ? (String[]) g.toArray(new String[0]) : new String[0];
    }

    public static String[] a() {
        return b.size() > 0 ? (String[]) b.toArray(new String[0]) : new String[0];
    }

    public static String[] b() {
        return i.size() > 0 ? (String[]) i.toArray(new String[0]) : new String[0];
    }

    public static void a(String str, int i2, int i3) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Result", 2);
        hashMap.put("deviceType", "HDK_WEAR");
        hashMap.put("Action", Integer.valueOf(i2));
        hashMap.put("Page", Integer.valueOf(i3));
        hashMap.put("deviceName", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.UNAUTHORIZED_DEVICE_PAIRING_FAILURE.value(), hashMap, 0);
    }
}
