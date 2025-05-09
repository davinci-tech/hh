package com.huawei.watchface.mvp.model.helper;

import android.text.TextUtils;
import android.webkit.URLUtil;
import android.webkit.WebView;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.secure.android.common.webview.UriUtil;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.at;
import com.huawei.watchface.dg;
import com.huawei.watchface.dx;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.n;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.HwSfpPolicyManagerHelper;
import com.huawei.watchface.utils.WebViewUtils;
import defpackage.mcq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class JsSafeUrlSystemParamManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile JsSafeUrlSystemParamManager f11079a;
    private static String j;
    private static List<String> k = new CopyOnWriteArrayList();
    private static List<String> l = new CopyOnWriteArrayList();
    private String g;
    private List<String> b = new ArrayList();
    private List<String> c = new ArrayList();
    private Map<String, List<String>> d = new HashMap();
    private volatile String e = "";
    private boolean f = true;
    private String h = "1";
    private String i = "";

    public interface a {
        void onGetFinished();
    }

    private JsSafeUrlSystemParamManager() {
        h();
    }

    public static JsSafeUrlSystemParamManager getInstance() {
        if (f11079a == null) {
            synchronized (JsSafeUrlSystemParamManager.class) {
                if (f11079a == null) {
                    f11079a = new JsSafeUrlSystemParamManager();
                }
            }
        }
        return f11079a;
    }

    private void h() {
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                JsSafeUrlSystemParamManager.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        try {
            this.h = NewSystemParamManager.getSystemParam("client_watchface_release_log_switches", "1");
            this.i = NewSystemParamManager.getSystemParam("client_watchface_release_url_config", "");
            HwLog.i("JsSafeUrlSystemParamManager", "release log switches :" + this.h + ",releaseUrlConfig:" + this.i);
            if (Environment.getApplicationContext() != null) {
                HwWatchFaceApi.getInstance(Environment.getApplicationContext()).setReleaseLogSwitches(TextUtils.equals(this.h, "1"));
            }
            String systemParam = NewSystemParamManager.getSystemParam("client_watchface_h5_read_dir_white_list", "{\"whitePathList\":[\"/data/data/com.huawei.health/files/watchfacePhoto/\",\"/data/data/com.huawei.health/files/watchfaceVideo/\",\"/data/data/com.huawei.health/files/14/\",\"/data/data/com.huawei.health/files/16/\"],\"whiteFileTypeList\":[\".png\",\".jpg\",\".bmp\",\".mp4\"]}");
            FlavorConfig.safeHwLog("JsSafeUrlSystemParamManager", "h5LoadPathConfig:" + systemParam);
            JSONObject jSONObject = new JSONObject(systemParam);
            JSONArray optJSONArray = jSONObject.optJSONArray("whitePathList");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("whiteFileTypeList");
            for (int i = 0; i < optJSONArray.length(); i++) {
                d(optJSONArray.getString(i));
            }
            a(k);
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                if (!TextUtils.isEmpty(optJSONArray2.getString(i2)) && !l.contains(optJSONArray2.getString(i2))) {
                    l.add(optJSONArray2.getString(i2));
                }
            }
            FlavorConfig.safeHwLog("JsSafeUrlSystemParamManager", "mFilePermissionUrls:" + GsonHelper.toJson(k));
            a();
        } catch (JSONException e) {
            HwLog.e("JsSafeUrlSystemParamManager", "h5LoadPathConfig JSONException" + HwLog.printException((Exception) e));
        } catch (Exception e2) {
            HwLog.e("JsSafeUrlSystemParamManager", "h5LoadPathConfig Exception" + HwLog.printException(e2));
        }
    }

    public void a() {
        HwLog.i("JsSafeUrlSystemParamManager", "initSystemParamCache() enter.");
        Set<String> b = NewSystemParamManager.b("client_safe_url_watchface");
        HwLog.i("JsSafeUrlSystemParamManager", "watchfaceSafeUrls size:" + ArrayUtils.a(b));
        if (!ArrayUtils.isEmpty(b)) {
            for (String str : b) {
                if (!this.b.contains(str)) {
                    this.b.add(str);
                }
            }
            HwLog.i("JsSafeUrlSystemParamManager", "watchfaceSafeUrls get cache size:" + ArrayUtils.a(this.b));
        }
        Map<String, List<String>> a2 = a(NewSystemParamManager.b("client_safe_url_watchface_sensitive_v2"));
        HwLog.i("JsSafeUrlSystemParamManager", "watchFaceSensitiveUrls size:" + ArrayUtils.b(a2));
        if (dg.isEmpty(a2)) {
            return;
        }
        this.d.putAll(a2);
        HwLog.i("JsSafeUrlSystemParamManager", "watchfaceSafeUrls get cache size:" + ArrayUtils.b(this.d));
    }

    public void a(a aVar) {
        if (!ArrayUtils.isEmpty(e())) {
            HwLog.i("JsSafeUrlSystemParamManager", "getSystemParam safeUrls !isEmpty");
            if (aVar != null) {
                aVar.onGetFinished();
                return;
            }
            return;
        }
        NewSystemParamManager.a();
        b(aVar);
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        HwLog.d("JsSafeUrlSystemParamManager", "setWebViewUrlCache:" + z);
        this.f = z;
    }

    public void b() {
        HwLog.i("JsSafeUrlSystemParamManager", "getWatchTypeSystemParamForNet() enter.");
        NewSystemParamManager.a();
        b((a) null);
    }

    public String c() {
        return this.i;
    }

    public void d() {
        if (Environment.getApplicationContext() == null) {
            HwLog.i("JsSafeUrlSystemParamManager", "getPhoneTypeSystemParamForNet() isOversea.");
            return;
        }
        if (!TextUtils.equals("CN", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode()) || HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            HwLog.i("JsSafeUrlSystemParamManager", "getPhoneTypeSystemParamForNet() isOversea.");
        } else if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isBetaVersion()) {
            HwLog.i("JsSafeUrlSystemParamManager", "getPhoneTypeSystemParamForNet() isBetaVersion.");
        } else {
            NewSystemParamManager.b();
        }
    }

    private void b(final a aVar) {
        NewSystemParamManager.a(new NewSystemParamManager.a() { // from class: com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager.1
            @Override // com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager.a
            public void a(int i) {
                HwLog.i("JsSafeUrlSystemParamManager", "getSystemParam() onParamPrepared enter.");
                synchronized (JsSafeUrlSystemParamManager.class) {
                    JsSafeUrlSystemParamManager.this.g();
                }
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.onGetFinished();
                }
            }
        });
    }

    public List<String> e() {
        return this.b;
    }

    public String f() {
        this.g = NewSystemParamManager.getSystemParam("client_grey_watchface_site", "");
        HwLog.i("JsSafeUrlSystemParamManager", "greyWatchface:" + this.g);
        return this.g;
    }

    public void g() {
        HwLog.i("JsSafeUrlSystemParamManager", "parseSystemParam() enter.");
        List<String> c = NewSystemParamManager.c("client_safe_url_watchface");
        if (!ArrayUtils.isEmpty(c)) {
            for (String str : c) {
                if (!this.b.contains(str)) {
                    this.b.add(str);
                }
            }
        }
        Map<String, List<String>> b = b(NewSystemParamManager.c("client_safe_url_watchface_sensitive_v2"));
        if (!dg.isEmpty(b)) {
            this.d.putAll(b);
        }
        n.a(Environment.getApplicationContext()).a(NewSystemParamManager.c("client_auth_error_params"));
        List<String> c2 = NewSystemParamManager.c("client_watchface_nodark_url");
        if (ArrayUtils.isEmpty(c2)) {
            return;
        }
        this.c.clear();
        this.c.addAll(c2);
    }

    public static void a(List<String> list) {
        String i = i();
        if (TextUtils.isEmpty(i)) {
            HwLog.e("JsSafeUrlSystemParamManager", "unifyPath -- empty fileDirPath");
            return;
        }
        if (ArrayUtils.isEmpty(list)) {
            HwLog.e("JsSafeUrlSystemParamManager", "unifyPath -- empty mFilePermissionUrls");
            return;
        }
        FlavorConfig.safeHwLog("JsSafeUrlSystemParamManager", "unifyPath fileDirPath:" + i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = list.get(i2);
            if (str.startsWith("/data/data/") && !i.startsWith("/data/data/")) {
                String substring = SafeString.substring(str, 11);
                String substring2 = SafeString.substring(i, 0, i.indexOf(SafeString.substring(substring, 0, substring.indexOf("/"))));
                if (!mcq.a(substring2)) {
                    list.add(substring2 + substring);
                }
            }
        }
    }

    private static String i() {
        if (TextUtils.isEmpty(j)) {
            try {
                j = HwSfpPolicyManagerHelper.getFileDataUserPath();
            } catch (Exception e) {
                HwLog.e("JsSafeUrlSystemParamManager", "getFileDirPath Exception " + HwLog.printException(e));
            }
        }
        return j;
    }

    private Map<String, List<String>> b(List<String> list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            HwLog.e("JsSafeUrlSystemParamManager", "empty list for sensitive urls");
            return hashMap;
        }
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                JSONObject jSONObject = new JSONObject(it.next());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    List<String> a2 = dx.a(jSONObject.getJSONArray(next));
                    List list2 = (List) hashMap.get(next);
                    if (ArrayUtils.isEmpty(list2)) {
                        hashMap.put(next, a2);
                    } else if (a2 != null) {
                        list2.addAll(a2);
                    }
                }
            }
        } catch (JSONException e) {
            HwLog.i("JsSafeUrlSystemParamManager", "parseSystemParamObject Exception: " + HwLog.printException((Exception) e));
        }
        return hashMap;
    }

    private Map<String, List<String>> a(Set<String> set) {
        HashMap hashMap = new HashMap();
        if (set == null) {
            HwLog.e("JsSafeUrlSystemParamManager", "empty list for sensitive urls");
            return hashMap;
        }
        try {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                JSONObject jSONObject = new JSONObject(it.next());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    List<String> a2 = dx.a(jSONObject.getJSONArray(next));
                    List list = (List) hashMap.get(next);
                    if (ArrayUtils.isEmpty(list)) {
                        hashMap.put(next, a2);
                    } else if (a2 != null) {
                        list.addAll(a2);
                    }
                }
            }
        } catch (JSONException e) {
            HwLog.i("JsSafeUrlSystemParamManager", "parseSystemParamObject Exception: " + HwLog.printException((Exception) e));
        }
        return hashMap;
    }

    public boolean isInURLWhiteList(WebView webView) {
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isTestVersion()) {
            return true;
        }
        if (webView != null) {
            return a(webView, this.b, true);
        }
        return false;
    }

    public boolean b(String str) {
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isTestVersion()) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return b(str, this.c);
    }

    public boolean isInSensitiveURLWhiteList(WebView webView, String str) {
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isTestVersion()) {
            return true;
        }
        if (webView == null) {
            return false;
        }
        List<String> list = this.d.get(str);
        if (!ArrayUtils.isEmpty(list)) {
            return a(webView, list, this.f);
        }
        return a(webView, this.b, true);
    }

    private boolean a(WebView webView, List<String> list, boolean z) {
        String a2;
        if (z && !TextUtils.isEmpty(this.e)) {
            a2 = this.e;
        } else {
            a2 = WebViewUtils.a(webView);
        }
        boolean b = b(a2, list);
        if (!b) {
            FlavorConfig.safeHwLog("JsSafeUrlSystemParamManager", "isInURLWhiteList -- false: " + UriUtil.getHostByURI(a2));
            HwLog.e("JsSafeUrlSystemParamManager", "isInURLWhiteList -- false");
        }
        return b;
    }

    private boolean b(String str, List<String> list) {
        if (!URLUtil.isHttpsUrl(str)) {
            HwLog.i("JsSafeUrlSystemParamManager", "isSafeUrl() targetUrl is not https url");
            return false;
        }
        return at.b(str, list);
    }

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && a(str, k) && c(str, l);
    }

    private static boolean c(String str, List<String> list) {
        try {
            String substring = SafeString.substring(str, str.lastIndexOf("."));
            if (!TextUtils.isEmpty(substring)) {
                substring = substring.toLowerCase(Locale.ENGLISH);
            }
            HwLog.i("JsSafeUrlSystemParamManager", "isPathAndTypeInWhiteList fileSuffix：" + substring);
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(substring, it.next())) {
                    return true;
                }
            }
        } catch (Exception unused) {
            HwLog.e("JsSafeUrlSystemParamManager", "isPathAndTypeInWhiteList() exception");
        }
        HwLog.i("JsSafeUrlSystemParamManager", "isFileTypeInWhiteList | targetPath not in whiteList");
        return false;
    }

    public static boolean a(String str, List<String> list) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("JsSafeUrlSystemParamManager", "isPathInWhiteList | targetPath is empty");
            return false;
        }
        if (ArrayUtils.isEmpty(list)) {
            HwLog.e("JsSafeUrlSystemParamManager", "isPathInWhiteList | whiteList is empty");
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        HwLog.i("JsSafeUrlSystemParamManager", "isPathInWhiteList | targetPath not in whiteList");
        return false;
    }

    private static void d(String str) {
        if (TextUtils.isEmpty(str) || k.contains(str)) {
            return;
        }
        k.add(str);
    }
}
