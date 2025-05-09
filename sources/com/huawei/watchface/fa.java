package com.huawei.watchface;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.flatbuffers.reflection.BaseType;
import com.google.gson.JsonArray;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.openalliance.ad.beans.parameter.App;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.inter.IHiAd;
import com.huawei.openalliance.ad.inter.NativeAdLoader;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.NativeAdListener;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.util.EncodeUtil;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.fc;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WebViewUtils;
import com.huawei.watchface.utils.callback.ILoginCallback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class fa {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11028a = "fa";
    private static volatile fa l;
    private static final HashMap<AppStatus, String> n;
    private WeakReference<Activity> b;
    private PPSNativeView c;
    private TextView d;
    private ImageView e;
    private AppDownloadButton f;
    private PPSNativeView g;
    private List<INativeAd> h;
    private WebView i;
    private Map<String, INativeAd> j = new HashMap();
    private List<String> k = new CopyOnWriteArrayList();
    private boolean m = false;
    private LinkedHashMap<String, String> o = new LinkedHashMap<>();

    public interface a {
        void a();
    }

    static {
        HashMap<AppStatus, String> hashMap = new HashMap<>();
        n = hashMap;
        hashMap.put(AppStatus.INSTALLED, "OPEN");
        hashMap.put(AppStatus.DOWNLOADING, "DOWNLOADING");
        hashMap.put(AppStatus.DOWNLOADED, "DOWNLOADED");
        hashMap.put(AppStatus.INSTALL, "INSTALL");
        hashMap.put(AppStatus.INSTALLING, "INSTALLING");
        hashMap.put(AppStatus.DOWNLOADFAILED, "DOWNLOADFAILED");
        hashMap.put(AppStatus.DOWNLOADCANCELLED, "DOWNLOADCANCELLED");
        hashMap.put(AppStatus.PAUSE, "PAUSE");
        hashMap.put(AppStatus.RESUME, "RESUME");
        hashMap.put(AppStatus.WAITING, "WAITING");
        hashMap.put(AppStatus.DOWNLOAD, "DOWNLOAD");
    }

    public static fa a(Activity activity, WebView webView) {
        if (l == null) {
            synchronized (fa.class) {
                if (l == null) {
                    l = new fa(activity, webView);
                }
            }
        }
        return l;
    }

    public static void a() {
        HwLog.i(f11028a, "removeAll: enter");
        if (l == null) {
            return;
        }
        l.f();
        l.b();
        l.e();
        IHiAd a2 = fb.a(Environment.getApplicationContext());
        if (a2 != null) {
            a2.setAppDownloadListener(null);
        }
        l = null;
    }

    private void e() {
        if (this.i != null) {
            this.i = null;
        }
    }

    private fa(Activity activity, WebView webView) {
        this.b = new WeakReference<>(activity);
        this.i = webView;
    }

    private void a(NativeAdListener nativeAdListener, String str, int i) {
        NativeAdLoader nativeAdLoader = new NativeAdLoader(Environment.getApplicationContext(), new String[]{str}, i, null);
        nativeAdLoader.setListener(nativeAdListener);
        nativeAdLoader.enableDirectReturnVideoAd(true);
        a(nativeAdLoader);
        nativeAdLoader.loadAds(4, false);
    }

    public static void a(NativeAdLoader nativeAdLoader) {
        if (nativeAdLoader == null || HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            HwLog.i(HwLog.TAG, "setRequestOptions -- isOversea");
            return;
        }
        HwLog.i(f11028a, "setRequestOptions - enter");
        RequestOptions.Builder builder = new RequestOptions.Builder();
        App app = new App();
        app.setPkgname("com.huawei.android.thememanager");
        nativeAdLoader.setRequestOptions(builder.setApp(app).setNonPersonalizedAd(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getNonPersonalizedAdSwitch(JsbMapKeyNames.H5_NPA)).setHwNonPersonalizedAd(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getNonPersonalizedAdSwitch(JsbMapKeyNames.HW_DSP_NPA)).setThirdNonPersonalizedAd(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getNonPersonalizedAdSwitch(JsbMapKeyNames.THIRD_DSP_NPA)).build());
    }

    public void a(PPSNativeView pPSNativeView) {
        this.c = pPSNativeView;
    }

    private void a(WebView webView) {
        HwLog.i(f11028a, "showEncourageAdInfos: enter");
        IHiAd a2 = fb.a(Environment.getApplicationContext());
        if (a2 != null) {
            a2.enableUserInfo(true);
        }
        final WeakReference weakReference = new WeakReference(webView);
        AppDownloadListener appDownloadListener = new AppDownloadListener() { // from class: com.huawei.watchface.fa.1
            @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
            public void onStatusChanged(AppStatus appStatus, AppInfo appInfo) {
                String appName = appInfo == null ? Constants.NULL : appInfo.getAppName();
                HwLog.i(fa.f11028a, "onStatusChanged appStatus:" + appStatus + ",appInfo name:" + appName);
                if (!CommonUtils.l((Context) fa.this.b.get()) || weakReference.get() == null) {
                    return;
                }
                fa.this.a((WebView) weakReference.get(), appStatus, appInfo);
            }

            @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
            public void onDownloadProgress(AppInfo appInfo, int i) {
                HwLog.i(fa.f11028a, "onDownloadProgress: progress == " + i);
                if (!CommonUtils.l((Context) fa.this.b.get()) || weakReference.get() == null) {
                    return;
                }
                fa.this.a((WebView) weakReference.get(), appInfo, i);
            }

            @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
            public void onAppOpen(AppInfo appInfo) {
                if (appInfo != null) {
                    String appName = appInfo.getAppName();
                    HwLog.i(fa.f11028a, "onAppOpen: enter info_name:" + appName);
                    fa.this.a(appInfo.getPackageName(), appName, appInfo.getUniqueId(), dp.a("operator", "themename"));
                }
            }

            @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
            public void onAppOpen(String str) {
                HwLog.i(fa.f11028a, "onAppOpen enter name: " + str);
            }

            @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
            public void onUserCancel(AppInfo appInfo) {
                HwLog.i(fa.f11028a, "onUserCancel enter");
                fa.this.m = true;
                if (!CommonUtils.l((Context) fa.this.b.get()) || weakReference.get() == null) {
                    return;
                }
                fa.this.a((WebView) weakReference.get(), AppStatus.DOWNLOADCANCELLED, appInfo);
            }
        };
        if (a2 != null) {
            a2.setAppDownloadListener(appDownloadListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final WebView webView, final AppStatus appStatus, final AppInfo appInfo) {
        BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.fa.2
            @Override // java.lang.Runnable
            public void run() {
                String str;
                if (!fa.n.containsKey(appStatus) || appInfo == null) {
                    return;
                }
                if (fa.this.m && appStatus == AppStatus.DOWNLOADFAILED) {
                    str = "javascript:notifyDownloadResult('" + ((String) fa.n.get(AppStatus.DOWNLOADCANCELLED)) + "','" + appInfo.getPackageName() + Constants.RIGHT_BRACKET;
                } else {
                    str = "javascript:notifyDownloadResult('" + ((String) fa.n.get(appStatus)) + "','" + appInfo.getPackageName() + Constants.RIGHT_BRACKET;
                }
                HwLog.i(fa.f11028a, "notifyDownloadResult js: " + str);
                webView.evaluateJavascript(str, null);
                if (AppStatus.DOWNLOADED.equals(appStatus)) {
                    fa.this.a(webView, appInfo);
                    return;
                }
                HwLog.e(fa.f11028a, "uploadDownloadStatus default..  status ==  " + appStatus);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WebView webView, AppInfo appInfo) {
        String str = f11028a;
        HwLog.i(str, "uploadDownloadSuccess enter");
        AppInfo a2 = a(appInfo);
        if (a2 != null) {
            HwLog.d(str, "uploadDownloadSuccess adName:" + a2.getAppName());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("uniqueId", a2.getUniqueId());
                jSONObject.put("packageName", a2.getPackageName());
                jSONObject.put("adName", a2.getAppName());
            } catch (JSONException e) {
                HwLog.e(f11028a, "DownloadResultAdApp JSONException:" + HwLog.printException((Exception) e));
            }
            webView.evaluateJavascript("javascript:notifyDownloadSuccess(" + jSONObject.toString() + Constants.RIGHT_BRACKET_ONLY, null);
            a(appInfo.getUniqueId(), appInfo.getPackageName(), a2.getAppName());
        }
    }

    private AppInfo a(AppInfo appInfo) {
        if (appInfo == null) {
            return null;
        }
        if (!TextUtils.isEmpty(appInfo.getAppName()) && !TextUtils.isEmpty(appInfo.getPackageName())) {
            return appInfo;
        }
        String str = f11028a;
        HwLog.d(str, "appInfo getAppName:" + appInfo.getAppName() + ",getPackageName:" + appInfo.getPackageName());
        if (TextUtils.isEmpty(appInfo.getUniqueId())) {
            HwLog.d(str, "getUniqueId is empty");
            return appInfo;
        }
        if (ArrayUtils.isEmpty(this.h)) {
            HwLog.d(str, "nativeAds is empty");
            return appInfo;
        }
        for (INativeAd iNativeAd : this.h) {
            if (iNativeAd.getAppInfo() != null && appInfo.getUniqueId().equals(iNativeAd.getAppInfo().getUniqueId())) {
                HwLog.d(f11028a, "nativeAds getAppName:" + iNativeAd.getAppInfo().getAppName() + ",getPackageName:" + iNativeAd.getAppInfo().getPackageName());
                return iNativeAd.getAppInfo();
            }
        }
        return appInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final WebView webView, AppInfo appInfo, int i) {
        String packageName = appInfo != null ? appInfo.getPackageName() : "";
        HwLog.i(f11028a, "uploadDownloaingProgress params packageName:" + packageName + ",progress:" + i);
        if (TextUtils.isEmpty(packageName) || i > 100 || i <= 0) {
            return;
        }
        List<String> list = this.k;
        if (list != null) {
            list.remove(packageName);
        }
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("packageName", packageName);
            jSONObject.put("progress", i);
            BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.fa$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    fa.a(webView, jSONObject);
                }
            });
        } catch (JSONException e) {
            HwLog.e(f11028a, "uploadDownloaingProgress JSONException:" + HwLog.printException((Exception) e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(WebView webView, JSONObject jSONObject) {
        if (webView == null) {
            HwLog.i(f11028a, "uploadDownloaingProgress webView is null.");
            return;
        }
        webView.evaluateJavascript("javascript:notifyDownloadingPpsProgress(" + jSONObject.toString() + Constants.RIGHT_BRACKET_ONLY, null);
    }

    private void a(final String str, final String str2, final String str3) {
        HwLog.i(f11028a, "launcherAdApp: enter");
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.fa.3
            @Override // java.lang.Runnable
            public void run() {
                fa faVar = fa.this;
                boolean a2 = fa.this.a("1", str2, str3, str, faVar.b(faVar.i), dp.a("operator", "themename"));
                HwLog.i(fa.f11028a, "launcherAdApp,Whether to report success:" + a2);
            }
        });
    }

    public static byte[] a(String str) {
        if (str == null) {
            return new byte[0];
        }
        int length = str.length();
        if (length % 2 != 0) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.getDefault());
        for (int i = 0; i < length; i++) {
            char charAt = upperCase.charAt(i);
            if (('0' > charAt || charAt > '9') && ('A' > charAt || charAt > 'F')) {
                return new byte[0];
            }
        }
        int i2 = length / 2;
        byte[] bArr = new byte[i2];
        byte[] bArr2 = new byte[2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + 1;
            bArr2[0] = (byte) upperCase.charAt(i3);
            i3 += 2;
            bArr2[1] = (byte) upperCase.charAt(i5);
            for (int i6 = 0; i6 < 2; i6++) {
                byte b = bArr2[i6];
                bArr2[i6] = (byte) (b - ((65 > b || b > 70) ? (byte) 48 : (byte) 55));
            }
            bArr[i4] = (byte) ((bArr2[0] << 4) | bArr2[1]);
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        char[] cArr2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr[i] = cArr2[(b >>> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & BaseType.Obj];
        }
        return new String(cArr);
    }

    public void b(String str) {
        String str2 = f11028a;
        HwLog.i(str2, "recordShowStartEvent: enter");
        INativeAd e = e(str);
        if (e != null && this.b.get() != null) {
            e.recordShowStartEvent(this.b.get(), null);
        } else {
            HwLog.i(str2, "recordShowStartEvent nativeAd is null or activity is null");
        }
    }

    public void c(String str) {
        String str2 = f11028a;
        HwLog.i(str2, "recordImpressionEvent: enter");
        INativeAd e = e(str);
        if (e != null && this.b.get() != null) {
            e.recordImpressionEvent(this.b.get(), null);
        } else {
            HwLog.i(str2, "recordImpressionEvent nativeAd is null or activity is null");
        }
    }

    private INativeAd e(String str) {
        String str2 = f11028a;
        HwLog.i(str2, "getNativeAdByPackageName: enter");
        if (!TextUtils.isEmpty(str)) {
            if (ArrayUtils.b(this.j) > 0) {
                return this.j.get(str);
            }
            HwLog.i(str2, "getNativeAdByAppName mNativeAdMap size of " + ArrayUtils.b(this.j));
            return null;
        }
        HwLog.i(str2, "getNativeAdByAppName appName is empty");
        return null;
    }

    public void b() {
        if (ArrayUtils.b(this.j) > 0) {
            this.j.clear();
        }
    }

    public void a(String str, int i, final a aVar) {
        HwLog.i(f11028a, "initEncourageAdHelper: enter");
        a(new NativeAdListener() { // from class: com.huawei.watchface.fa.4
            @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
            public void onAdsLoaded(Map<String, List<INativeAd>> map) {
                HwLog.i(fa.f11028a, "onAdsLoaded: enter");
                Iterator<Map.Entry<String, List<INativeAd>>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    List<INativeAd> value = it.next().getValue();
                    fa.a(value);
                    if (value == null || value.isEmpty()) {
                        fa.this.h = new ArrayList();
                    } else {
                        fa.this.h = value;
                    }
                    if (value != null && !value.isEmpty()) {
                        JsonArray jsonArray = new JsonArray();
                        for (INativeAd iNativeAd : value) {
                            AppInfo appInfo = iNativeAd.getAppInfo();
                            ImageInfo icon = iNativeAd.getIcon();
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("uniqueId", iNativeAd.getUniqueId());
                                if (fa.this.b.get() != null) {
                                    jSONObject.put("isValid", iNativeAd.isValid((Context) fa.this.b.get()));
                                }
                                jSONObject.put("isExpired", iNativeAd.isExpired());
                                jSONObject.put("title", iNativeAd.getTitle());
                                jSONObject.put(VastTag.DESCRIPTION, iNativeAd.getDescription());
                                jSONObject.put("Lable", iNativeAd.getLabel());
                                jSONObject.put("AdSign", iNativeAd.getAdSign());
                                String str2 = fa.f11028a;
                                StringBuilder sb = new StringBuilder();
                                sb.append("nativeAd.getVideoInfo is null:");
                                sb.append(iNativeAd.getVideoInfo() == null);
                                HwLog.i(str2, sb.toString());
                                if (appInfo == null) {
                                    HwLog.e(fa.f11028a, iNativeAd.getTitle() + "：appInfo is null");
                                } else {
                                    jSONObject.put("AppName", appInfo.getAppName());
                                    jSONObject.put("IconUrl", appInfo.getIconUrl());
                                    String packageName = appInfo.getPackageName();
                                    jSONObject.put("packageName", packageName);
                                    fa.this.j.put(packageName, iNativeAd);
                                    String str3 = "";
                                    jSONObject.put("VersionName", appInfo.getVersionName() == null ? "" : appInfo.getVersionName());
                                    jSONObject.put("DeveloperName", appInfo.getDeveloperName() == null ? "" : appInfo.getDeveloperName());
                                    jSONObject.put("AppDetailUrl", appInfo.getAppDetailUrl() == null ? "" : appInfo.getAppDetailUrl());
                                    jSONObject.put("PrivacyLink", appInfo.getPrivacyLink() == null ? "" : appInfo.getPrivacyLink());
                                    if (appInfo.getPermissionUrl() != null) {
                                        str3 = appInfo.getPermissionUrl();
                                    }
                                    jSONObject.put("PermissionUrl", str3);
                                }
                                if (icon != null) {
                                    jSONObject.put("appImageUrl", icon.getUrl());
                                } else {
                                    Iterator<ImageInfo> it2 = iNativeAd.getImageInfos().iterator();
                                    while (it2.hasNext()) {
                                        jSONObject.put("appImageUrl", it2.next().getOriginalUrl());
                                    }
                                }
                                jsonArray.add(jSONObject.toString());
                            } catch (JSONException e) {
                                HwLog.e(fa.f11028a, "onAdsLoaded JSONException" + HwLog.printException((Exception) e));
                            }
                        }
                        String jsonArray2 = jsonArray.toString();
                        HwLog.i(fa.f11028a, "Encourage onAdsLoaded: " + jsonArray2);
                        fa.this.f(jsonArray2);
                        a aVar2 = aVar;
                        if (aVar2 != null) {
                            aVar2.a();
                        }
                    }
                }
            }

            @Override // com.huawei.openalliance.ad.inter.listeners.NativeAdListener
            public void onAdFailed(int i2) {
                HwLog.e(fa.f11028a, "Encourage onAdFailed: " + i2);
                fa.this.f((String) null);
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a();
                }
            }
        }, str, i);
        WebView webView = this.i;
        if (webView != null) {
            a(webView);
        }
    }

    public void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a(str, i, (a) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(final String str) {
        String str2 = f11028a;
        HwLog.i(str2, "notifyApplicationInfo enter");
        WeakReference<Activity> weakReference = this.b;
        if (weakReference == null) {
            HwLog.e(str2, "notifyApplicationInfo() activity is null.");
            return;
        }
        Activity activity = weakReference.get();
        if (activity == null) {
            HwLog.e(str2, "notifyApplicationInfo() webViewActivity is null.");
        } else {
            if (this.i == null || activity.isFinishing()) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.watchface.fa.5
                @Override // java.lang.Runnable
                public void run() {
                    if (fa.this.i != null) {
                        HwLog.i(fa.f11028a, "notifyApplicationInfo isEmpty:" + TextUtils.isEmpty(str));
                        fa.this.i.evaluateJavascript("javascript:notifyApplicationInfo(" + str + Constants.RIGHT_BRACKET_ONLY, null);
                    }
                }
            });
        }
    }

    public void a(final String str, final boolean z) {
        String str2 = f11028a;
        HwLog.i(str2, "showDownloadView: enter isUserCancel:" + z);
        if (TextUtils.isEmpty(str)) {
            HwLog.e(str2, "showDownloadView() appName is empty.");
            return;
        }
        WeakReference<Activity> weakReference = this.b;
        if (weakReference == null) {
            HwLog.e(str2, "showDownloadView() activity is null.");
            return;
        }
        final Activity activity = weakReference.get();
        if (activity == null) {
            HwLog.e(str2, "showDownloadView() webViewActivity is null.");
        } else {
            if (!(activity instanceof WebViewActivity) || activity.isFinishing()) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.watchface.fa.6
                @Override // java.lang.Runnable
                public void run() {
                    HwLog.i(fa.f11028a, "showDownloadView runOnUiThread enter");
                    if (fa.this.h == null) {
                        HwLog.e(fa.f11028a, "showDownloadView nativeAds is null");
                        return;
                    }
                    int size = fa.this.h.size();
                    INativeAd iNativeAd = null;
                    for (int i = 0; i < size; i++) {
                        INativeAd iNativeAd2 = (INativeAd) fa.this.h.get(i);
                        if (iNativeAd2.getAppInfo() != null && str.equals(iNativeAd2.getAppInfo().getAppName())) {
                            iNativeAd = iNativeAd2;
                        }
                    }
                    fa.this.g = (PPSNativeView) activity.findViewById(R$id.pps_root_layout);
                    fa.this.e = (ImageView) activity.findViewById(R$id.pps_image_iv);
                    fa.this.d = (TextView) activity.findViewById(R$id.pps_label_tv);
                    fa.this.f = (AppDownloadButton) activity.findViewById(R$id.pps_download_btn);
                    if (iNativeAd == null) {
                        HwLog.e(fa.f11028a, "showDownloadView nativeAd is null");
                    } else if (!fc.a().b()) {
                        HwLog.e(fa.f11028a, "showDownloadView is showAd is false");
                    } else {
                        fa.this.a(iNativeAd, z);
                    }
                }
            });
        }
    }

    private void f() {
        String str = f11028a;
        HwLog.i(str, "hidePpsView enter...");
        Activity activity = this.b.get();
        if (activity == null) {
            HwLog.e(str, "hidePpsView() webViewActivity is null.");
        } else {
            if (activity.isFinishing()) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.watchface.fa$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    fa.this.g();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        PPSNativeView pPSNativeView = this.c;
        if (pPSNativeView != null) {
            pPSNativeView.setVisibility(8);
        }
        PPSNativeView pPSNativeView2 = this.g;
        if (pPSNativeView2 != null) {
            pPSNativeView2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(INativeAd iNativeAd, boolean z) {
        PPSNativeView pPSNativeView;
        String str = f11028a;
        HwLog.i(str, "showPpsView enter...");
        if (this.b.get() == null || !iNativeAd.isValid(this.b.get()) || (pPSNativeView = this.g) == null) {
            return;
        }
        a(pPSNativeView);
        this.g.register(iNativeAd);
        this.g.setOnNativeAdClickListener(new fc.a());
        List<ImageInfo> imageInfos = iNativeAd.getImageInfos();
        if (imageInfos == null) {
            HwLog.i(str, "showPpsView imageInfos ==null");
            return;
        }
        Iterator<ImageInfo> it = imageInfos.iterator();
        String str2 = "";
        while (it.hasNext()) {
            str2 = it.next().getOriginalUrl();
        }
        ImageInfo icon = iNativeAd.getIcon();
        if (icon != null) {
            str2 = icon.getUrl();
        }
        int i = R$drawable.watchface_res_detail_pps_bottom_default;
        ImageView imageView = this.e;
        if (imageView != null) {
            imageView.setImageResource(R$drawable.watchface_ic_public_search);
        }
        cz.a(this.b.get(), str2, i, i, this.e);
        a(iNativeAd, this.g, z);
    }

    public void d(String str) {
        INativeAd e = e(str);
        if (e == null || this.b.get() == null) {
            return;
        }
        e.triggerClick(this.b.get(), null);
    }

    private void a(INativeAd iNativeAd, PPSNativeView pPSNativeView, boolean z) {
        TextView textView;
        TextView textView2;
        if (this.f == null || this.b.get() == null) {
            return;
        }
        this.m = z;
        boolean register = pPSNativeView.register(this.f);
        fc.a(pPSNativeView);
        if (z) {
            this.f.post(new Runnable() { // from class: com.huawei.watchface.fa.7
                @Override // java.lang.Runnable
                public void run() {
                    fa.this.f.cancel();
                }
            });
            return;
        }
        this.f.post(new Runnable() { // from class: com.huawei.watchface.fa.8
            @Override // java.lang.Runnable
            public void run() {
                fa.this.f.performClick();
            }
        });
        if (register) {
            this.f.refreshStatus();
            if (!TextUtils.isEmpty(iNativeAd.getTitle()) && (textView2 = this.d) != null) {
                textView2.setText(iNativeAd.getTitle());
            }
            AppInfo appInfo = iNativeAd.getAppInfo();
            if (appInfo == null || (textView = this.d) == null) {
                return;
            }
            textView.setText(appInfo.getAppName());
            return;
        }
        this.f.setVisibility(8);
    }

    public void a(String str, String str2, String str3, String str4) {
        String str5 = f11028a;
        HwLog.i(str5, "openApp enter");
        String b = b(this.i);
        if (this.k == null) {
            this.k = new CopyOnWriteArrayList();
        }
        if (this.k.contains(str)) {
            WeakReference<Activity> weakReference = this.b;
            if (weakReference == null) {
                HwLog.e(str5, "openApp() activity is null.");
                return;
            }
            Activity activity = weakReference.get();
            if (activity == null) {
                HwLog.e(str5, "openApp() webViewActivity is null.");
                return;
            } else {
                CommonUtils.a(activity, activity.getPackageManager().getLaunchIntentForPackage(str));
                a(str, MonitorResult.SUCCESS);
                return;
            }
        }
        boolean a2 = a("2", str, str2, str3, b, str4);
        HwLog.i(str5, "openApp, result : " + a2 + " adName: " + str2);
        if (a2) {
            this.k.add(str);
            WeakReference<Activity> weakReference2 = this.b;
            if (weakReference2 == null) {
                HwLog.e(str5, "openApp() activity is null.");
                return;
            }
            Activity activity2 = weakReference2.get();
            if (activity2 == null) {
                HwLog.e(str5, "openApp() webViewActivity is null.");
                return;
            } else {
                CommonUtils.a(activity2, activity2.getPackageManager().getLaunchIntentForPackage(str));
                a(str, MonitorResult.SUCCESS);
                return;
            }
        }
        a(str, "FAILURE");
    }

    private void a(String str, final String str2) {
        String encodeForJavaScript = EncodeUtil.encodeForJavaScript(str);
        HwLog.i(f11028a, "notifyOpenApp enter encodePackageName ==" + encodeForJavaScript);
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("packageName", encodeForJavaScript);
            jSONObject.put("status", str2);
            BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.fa$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    fa.this.a(str2, jSONObject);
                }
            });
        } catch (JSONException e) {
            HwLog.e(f11028a, "notifyOpenApp JSONException:" + HwLog.printException((Exception) e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, JSONObject jSONObject) {
        if (this.i != null) {
            HwLog.i(f11028a, "notifyOpenApp openApp status: " + str);
            this.i.evaluateJavascript("javascript:notifyOpenApp(" + jSONObject.toString() + Constants.RIGHT_BRACKET_ONLY, null);
        }
    }

    public String a(String str, String str2, String str3, String str4, String str5) {
        String accessToken = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getAccessToken();
        if (this.b.get() == null) {
            return "";
        }
        cf cfVar = new cf(str, str3, str2, str4, accessToken, str5);
        String str6 = cfVar.c(cfVar.d()).retCode;
        HwLog.i(f11028a, "reportServer , retCode is empty: " + TextUtils.isEmpty(str6));
        return str6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = f11028a;
        HwLog.i(str7, "isReportServer: enter");
        b(str2, str3, str4, str5, str6);
        String a2 = a(str, str3, str4, str5, str6);
        if (TextUtils.equals("0", a2)) {
            g(str2);
            return true;
        }
        if (TextUtils.equals("9010120001", a2)) {
            HwLog.e(str7, "isReportServer token invalid : " + str);
            if (TextUtils.equals("1", str)) {
                b(str2, str);
            }
            HwWatchFaceApi.getInstance(Environment.getApplicationContext()).loginByHealthHms(Environment.getApplicationContext(), new ILoginCallback() { // from class: com.huawei.watchface.fa.9
                @Override // com.huawei.watchface.utils.callback.ILoginCallback
                public void onLoginSuccess(Object obj) {
                    HwLog.i(fa.f11028a, "setDownloadInfo() onLoginSuccess. ");
                }

                @Override // com.huawei.watchface.utils.callback.ILoginCallback
                public void onLoginFailed(Object obj) {
                    HwLog.i(fa.f11028a, "setDownloadInfo() onLoginSuccess. ");
                }
            });
            return false;
        }
        if ((TextUtils.equals("9010410006", a2) || TextUtils.isEmpty(a2)) && TextUtils.equals("1", str)) {
            HwLog.e(str7, "isReportServer the first device change , download failure ,retCode is empty: " + TextUtils.isEmpty(a2));
            b(str2, str);
            return false;
        }
        HwLog.e(str7, "isReportServer,failure: " + a2);
        return false;
    }

    private void b(String str, String str2) {
        LinkedHashMap<String, String> linkedHashMap = this.o;
        if (linkedHashMap != null) {
            linkedHashMap.put(str, str2);
        }
    }

    private void g(String str) {
        LinkedHashMap<String, String> linkedHashMap = this.o;
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return;
        }
        this.o.remove(str);
    }

    private void b(String str, String str2, String str3, String str4, String str5) {
        String str6 = f11028a;
        HwLog.i(str6, "downloadTypeRereportServer: enter");
        LinkedHashMap<String, String> linkedHashMap = this.o;
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return;
        }
        String str7 = this.o.get(str);
        if (TextUtils.isEmpty(str7) || !TextUtils.equals("1", str7)) {
            return;
        }
        HwLog.e(str6, "downloadTypeReportServer  token invalid " + str + " retCode :" + a(str7, str2, str3, str4, str5));
        g(str);
    }

    public static void a(List<INativeAd> list) {
        if (ArrayUtils.isEmpty(list)) {
            return;
        }
        Iterator<INativeAd> it = list.iterator();
        while (it.hasNext()) {
            INativeAd next = it.next();
            if (a(next)) {
                HwLog.d(f11028a, "delete INativeAd title:" + next.getTitle() + ",UniqueId:" + next.getUniqueId());
                it.remove();
            }
        }
    }

    private static boolean a(INativeAd iNativeAd) {
        AppInfo appInfo;
        if (iNativeAd == null || (appInfo = iNativeAd.getAppInfo()) == null) {
            return true;
        }
        if (TextUtils.isEmpty(appInfo.getDownloadUrl()) && TextUtils.isEmpty(appInfo.getSafeDownloadUrl()) && TextUtils.isEmpty(appInfo.getSha256()) && 0 == appInfo.getFileSize()) {
            return true;
        }
        return TextUtils.isEmpty(appInfo.getAppName()) || TextUtils.isEmpty(appInfo.getDeveloperName()) || TextUtils.isEmpty(appInfo.getVersionName()) || TextUtils.isEmpty(appInfo.getAppDetailUrl()) || TextUtils.isEmpty(appInfo.getPrivacyLink()) || TextUtils.isEmpty(appInfo.getPermissionUrl());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(WebView webView) {
        String a2 = dp.a("campId", "themename");
        String str = f11028a;
        HwLog.i(str, "downloaded report campId={" + a2 + "}");
        if (webView == null || !TextUtils.isEmpty(a2)) {
            return a2;
        }
        String a3 = WebViewUtils.a(webView);
        if (TextUtils.isEmpty(a3)) {
            return a2;
        }
        String a4 = an.a(a3, "(?<=/camp/).*?(?=/index.html)");
        HwLog.i(str, "getCampId getMatchSubString " + a4);
        return a4;
    }
}
