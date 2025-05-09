package com.huawei.watchface;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.cm;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.datatype.ScreenInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.ChoosePicUtil;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class o {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11154a = "o";
    private static o b;
    private Context c;
    private HwWatchFaceManager d;
    private cm.a e;
    private cm f;
    private String g;

    private o(Context context) {
        this.c = context;
        this.d = HwWatchFaceManager.getInstance(context);
        this.g = this.c.getFilesDir().getAbsolutePath() + File.separator + "watchface" + File.separator;
    }

    public static o a(Context context) {
        if (b == null) {
            synchronized (o.class) {
                if (b == null) {
                    b = new o(context);
                }
            }
        }
        return b;
    }

    public void b(Context context) {
        if (!this.d.isBtConnect()) {
            ds.a(DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
            return;
        }
        WebViewActivity c = c(context);
        if (c == null) {
            HwLog.w(f11154a, "openSystemFileManager activity is null");
            return;
        }
        if (this.d.getWatchFaceInstallState() != 0) {
            c.notifyNotInstallation();
            return;
        }
        if (this.d.getWatchFaceInstallState() != 0) {
            ds.a(DensityUtil.getStringById(R$string.watch_face_only_one_installed));
            return;
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        try {
            if (CommonUtils.b(context, intent)) {
                c.startActivityForResult(intent, 10088);
            }
        } catch (ActivityNotFoundException unused) {
            HwLog.e(f11154a, "openSystemFileManager() ActivityNotFoundException");
            ds.a(DensityUtil.getStringById(R$string.watch_face_open_filemanager_fail));
        }
    }

    private WebViewActivity c(Context context) {
        if (context instanceof WebViewActivity) {
            return (WebViewActivity) context;
        }
        return null;
    }

    public void a(Uri uri, Context context) {
        WebViewActivity c = c(context);
        if (c == null) {
            HwLog.w(f11154a, "activity is null");
            return;
        }
        this.e = new cm.a(c);
        String picPathByUri = new ChoosePicUtil(this.c).getPicPathByUri(this.c, uri);
        if (TextUtils.isEmpty(picPathByUri)) {
            HwLog.w(f11154a, "transferWatchFaceFile() path is empty.");
            ds.a(DensityUtil.getStringById(R$string.watch_face_get_watchface_path_fail));
        } else {
            if (!picPathByUri.toLowerCase(Locale.ENGLISH).endsWith(WatchFaceConstant.HWT_SUFFIX)) {
                ds.a(DensityUtil.getStringById(R$string.watch_face_file_is_not_support));
                return;
            }
            a(c, picPathByUri, uri);
            cm a2 = this.e.a();
            this.f = a2;
            a2.setCancelable(false);
            this.f.show();
        }
    }

    private void a(final Activity activity, final String str, final Uri uri) {
        StringBuilder sb = new StringBuilder(32);
        String name = new File(CommonUtils.filterFilePath(str)).getName();
        if (name.length() > 20) {
            sb.append(SafeString.substring(name, 0, 20));
            sb.append("...");
        } else {
            sb.append(name);
        }
        this.e.a(R$string.title_note).b(String.format(Locale.ENGLISH, DensityUtil.getStringById(R$string.watch_face_whether_to_install_the_watch), sb.toString())).a(R$string.watch_face_install_watchface, new View.OnClickListener() { // from class: com.huawei.watchface.o.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (o.this.f != null) {
                    o.this.f.dismiss();
                    o.this.f = null;
                }
                if (HwWatchFaceManager.getInstance(o.this.c).isBtConnect()) {
                    o.this.b(activity, str, uri);
                } else {
                    HwLog.i(o.f11154a, "showTransferDialog() bluetooth is disconnect");
                    o.this.d.notifyH5DeviceIsBreak();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.o.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (o.this.f != null) {
                    o.this.f.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Activity activity, String str, Uri uri) {
        String randomVersion = this.d.getRandomVersion();
        this.d.setCurrentInstallWatchFaceHiTopId("000000001");
        this.d.setCurrentInstallWatchFaceVersion(randomVersion);
        StringBuilder sb = new StringBuilder("000000001");
        sb.append("_");
        sb.append(randomVersion);
        StringBuffer stringBuffer = new StringBuffer(this.g);
        stringBuffer.append(sb.toString());
        String str2 = f11154a;
        HwLog.i(str2, "unzipWatchFaceFile() start time start Zip: " + System.currentTimeMillis());
        if (FileHelper.a(this.c, str, uri, stringBuffer.toString()) < 0) {
            HwLog.w(str2, "unzipWatchFaceFile() unzip failed.");
            ds.a(R$string.watch_face_get_watchface_path_fail);
            return;
        }
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.c).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.w(str2, "unzipWatchFaceFile() watchFaceInfo is null");
            return;
        }
        String watchFaceScreen = watchFaceSupportInfo.getWatchFaceScreen();
        boolean a2 = a(watchFaceScreen);
        HwLog.i(str2, "unzipWatchFaceFile() isDeviceScreenExist: " + a2);
        String str3 = stringBuffer.toString() + File.separator + "description.xml";
        String d = d(str3, "version");
        if (a(watchFaceSupportInfo, watchFaceScreen, a2, str3, d)) {
            return;
        }
        HwLog.i(str2, "unzipWatchFaceFile() start time start Transfer: " + System.currentTimeMillis());
        a(activity, sb, stringBuffer, watchFaceScreen, d);
    }

    private void a(Activity activity, StringBuilder sb, StringBuffer stringBuffer, String str, String str2) {
        stringBuffer.append(File.separator);
        stringBuffer.append(FlavorConfig.SERVICE_WATCH_FACE);
        String str3 = f11154a;
        FlavorConfig.safeHwLog(str3, "unzip file path: " + stringBuffer.toString());
        byte[] c = FileHelper.c(new File(stringBuffer.toString()));
        if (c == null || c.length <= 0) {
            HwLog.i(str3, "unzipWatchFaceFile in <= 0");
            return;
        }
        cx.a().a(sb.toString(), m.a().a(this.c, c, a(str, str2)));
        if (this.d.isCanceled()) {
            HwLog.w(str3, "unzipWatchFaceFile() is canceled: " + this.d.isCanceled());
            this.d.setIsCanceled(false);
            return;
        }
        this.d.showInstallProgress(0, activity);
        this.d.transferFile(stringBuffer.toString(), sb.toString(), 1);
    }

    private boolean a(WatchFaceSupportInfo watchFaceSupportInfo, String str, boolean z, String str2, String str3) {
        if (z) {
            String watchFaceMaxVersion = watchFaceSupportInfo.getWatchFaceMaxVersion();
            boolean a2 = a(watchFaceSupportInfo.getCompatibleList(), str3);
            boolean c = c(str3, watchFaceMaxVersion);
            if (!c && !a2) {
                ds.b(DensityUtil.getStringById(R$string.watch_face_version_not_supported));
            }
            if (b(str2, str) && (c || a2)) {
                return false;
            }
            HwLog.w(f11154a, "unzipWatchFaceFile() watch face version is not support");
            return true;
        }
        HwLog.w(f11154a, "unzipWatchFaceFile() device screen is not find");
        return false;
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            HwLog.w(f11154a, "isMuse() deviceScreen || deviceVersion isEmpty.");
            return false;
        }
        HwLog.i(f11154a, "isMuse() deviceScreen : " + str + ", deviceVersion: " + str2);
        return str.equals("466*466") && str2.contains("2.0.");
    }

    private boolean a(List<ScreenInfo> list, String str) {
        if (ArrayUtils.isEmpty(list)) {
            HwLog.i(f11154a, "hasCompatibleVersion() compatibleList empty.");
            return false;
        }
        Iterator<ScreenInfo> it = list.iterator();
        while (it.hasNext()) {
            for (String str2 : it.next().getSupportVersion().split(",")) {
                if (c(str, str2)) {
                    HwLog.i(f11154a, "hasCompatibleVersion() hasCompatibleVersion.");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean a(String str) {
        String c = c();
        if (TextUtils.isEmpty(c)) {
            HwLog.w(f11154a, "verifyDeviceScreen() watchFaceInfoJson is empty");
            return false;
        }
        try {
            JSONArray jSONArray = new JSONArray(c);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null && str.equals(jSONObject.getString("screenResolution"))) {
                    return true;
                }
            }
            return false;
        } catch (JSONException unused) {
            HwLog.e(f11154a, "verifyDeviceScreen() JSONException");
            return false;
        }
    }

    private boolean b(String str, String str2) {
        String d = d(str, "screen");
        if (TextUtils.isEmpty(d)) {
            HwLog.w(f11154a, "verifyWatchFaceScreen() watch face screen version is empty");
            return false;
        }
        String str3 = f11154a;
        HwLog.i(str3, "verifyWatchFaceScreen() screen: " + d);
        String b2 = b(d);
        HwLog.i(str3, "verifyWatchFaceScreen() watchFaceScreen: " + b2 + ", deviceWatchFaceScreen: " + str2);
        if (b2.equals(str2)) {
            return true;
        }
        ds.a(DensityUtil.getStringById(R$string.watch_face_screen_not_supported));
        return false;
    }

    private boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w(f11154a, "verifyWatchFaceVersion() resourceVersion is empty");
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            HwLog.w(f11154a, "verifyWatchFaceVersion() deviceVersion is empty");
            return false;
        }
        String str3 = f11154a;
        HwLog.i(str3, "verifyWatchFaceVersion() resourceVersion: " + str + ", deviceVersion: " + str2);
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        if (split.length >= 2 && split2.length >= 2) {
            int a2 = IntegerUtils.a(split[0]);
            int a3 = IntegerUtils.a(split[1]);
            int a4 = IntegerUtils.a(split2[0]);
            int a5 = IntegerUtils.a(split2[1]);
            HwLog.i(str3, "verifyWatchFaceVersion() firstVersion: " + a2 + ", firstMaxVersion: " + a4 + ", secondVersion: " + a3 + ", secondMaxVersion: " + a5);
            return a2 == a4 && a3 <= a5;
        }
        HwLog.w(str3, "verifyWatchFaceVersion() watch face versions or max versions length less than two");
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b6, code lost:
    
        if (0 == 0) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "fetchWatchFaceVersionInfo() close IOException"
            r2 = 0
            javax.xml.parsers.DocumentBuilderFactory r3 = com.huawei.secure.android.common.xml.DocumentBuilderFactorySecurity.getInstance()     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            r4 = 0
            r3.setExpandEntityReferences(r4)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            javax.xml.parsers.DocumentBuilder r3 = r3.newDocumentBuilder()     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            java.lang.String[] r6 = new java.lang.String[]{r6}     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            java.io.File r6 = com.huawei.watchface.utils.FileHelper.a(r6)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            java.io.FileInputStream r2 = com.huawei.watchface.utils.FileHelper.b(r6)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            org.w3c.dom.Document r6 = r3.parse(r2)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            org.w3c.dom.Element r6 = r6.getDocumentElement()     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r6 != 0) goto L3a
            java.lang.String r6 = com.huawei.watchface.o.f11154a     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            java.lang.String r7 = "fetchWatchFaceVersionInfo() rootElement is null"
            com.huawei.watchface.utils.HwLog.w(r6, r7)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r2 == 0) goto L39
            r2.close()     // Catch: java.io.IOException -> L34
            goto L39
        L34:
            java.lang.String r6 = com.huawei.watchface.o.f11154a
            com.huawei.watchface.utils.HwLog.e(r6, r1)
        L39:
            return r0
        L3a:
            org.w3c.dom.NodeList r6 = r6.getElementsByTagName(r7)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            int r7 = r6.getLength()     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r7 != 0) goto L57
            java.lang.String r6 = com.huawei.watchface.o.f11154a     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            java.lang.String r7 = "fetchWatchFaceVersionInfo() tagElement is null"
            com.huawei.watchface.utils.HwLog.w(r6, r7)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r2 == 0) goto L56
            r2.close()     // Catch: java.io.IOException -> L51
            goto L56
        L51:
            java.lang.String r6 = com.huawei.watchface.o.f11154a
            com.huawei.watchface.utils.HwLog.e(r6, r1)
        L56:
            return r0
        L57:
            org.w3c.dom.Node r6 = r6.item(r4)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r6 != 0) goto L70
            java.lang.String r6 = com.huawei.watchface.o.f11154a     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            java.lang.String r7 = "fetchWatchFaceVersionInfo() tagNode is null"
            com.huawei.watchface.utils.HwLog.w(r6, r7)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r2 == 0) goto L6f
            r2.close()     // Catch: java.io.IOException -> L6a
            goto L6f
        L6a:
            java.lang.String r6 = com.huawei.watchface.o.f11154a
            com.huawei.watchface.utils.HwLog.e(r6, r1)
        L6f:
            return r0
        L70:
            org.w3c.dom.Node r6 = r6.getFirstChild()     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r6 != 0) goto L89
            java.lang.String r6 = com.huawei.watchface.o.f11154a     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            java.lang.String r7 = "fetchWatchFaceVersionInfo() firstChild is null"
            com.huawei.watchface.utils.HwLog.w(r6, r7)     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r2 == 0) goto L88
            r2.close()     // Catch: java.io.IOException -> L83
            goto L88
        L83:
            java.lang.String r6 = com.huawei.watchface.o.f11154a
            com.huawei.watchface.utils.HwLog.e(r6, r1)
        L88:
            return r0
        L89:
            java.lang.String r6 = r6.getNodeValue()     // Catch: java.lang.Throwable -> L99 org.xml.sax.SAXException -> L9b java.io.IOException -> La5 javax.xml.parsers.ParserConfigurationException -> Laf
            if (r2 == 0) goto L98
            r2.close()     // Catch: java.io.IOException -> L93
            goto L98
        L93:
            java.lang.String r7 = com.huawei.watchface.o.f11154a
            com.huawei.watchface.utils.HwLog.e(r7, r1)
        L98:
            return r6
        L99:
            r6 = move-exception
            goto Lc2
        L9b:
            java.lang.String r6 = com.huawei.watchface.o.f11154a     // Catch: java.lang.Throwable -> L99
            java.lang.String r7 = "fetchWatchFaceVersionInfo() SAXException"
            com.huawei.watchface.utils.HwLog.e(r6, r7)     // Catch: java.lang.Throwable -> L99
            if (r2 == 0) goto Lc1
            goto Lb8
        La5:
            java.lang.String r6 = com.huawei.watchface.o.f11154a     // Catch: java.lang.Throwable -> L99
            java.lang.String r7 = "fetchWatchFaceVersionInfo() IOException"
            com.huawei.watchface.utils.HwLog.e(r6, r7)     // Catch: java.lang.Throwable -> L99
            if (r2 == 0) goto Lc1
            goto Lb8
        Laf:
            java.lang.String r6 = com.huawei.watchface.o.f11154a     // Catch: java.lang.Throwable -> L99
            java.lang.String r7 = "fetchWatchFaceVersionInfo() ParserConfigurationException"
            com.huawei.watchface.utils.HwLog.e(r6, r7)     // Catch: java.lang.Throwable -> L99
            if (r2 == 0) goto Lc1
        Lb8:
            r2.close()     // Catch: java.io.IOException -> Lbc
            goto Lc1
        Lbc:
            java.lang.String r6 = com.huawei.watchface.o.f11154a
            com.huawei.watchface.utils.HwLog.e(r6, r1)
        Lc1:
            return r0
        Lc2:
            if (r2 == 0) goto Lcd
            r2.close()     // Catch: java.io.IOException -> Lc8
            goto Lcd
        Lc8:
            java.lang.String r7 = com.huawei.watchface.o.f11154a
            com.huawei.watchface.utils.HwLog.e(r7, r1)
        Lcd:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.o.d(java.lang.String, java.lang.String):java.lang.String");
    }

    private String b(String str) {
        String c = c();
        if (TextUtils.isEmpty(c)) {
            HwLog.w(f11154a, "fetchWatchFaceScreen() watchFaceVersionJson is empty");
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(c);
            int length = jSONArray.length();
            String str2 = "";
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    String string = jSONObject.getString("themeVersion");
                    String string2 = jSONObject.getString("screenResolution");
                    if (str.equals(string)) {
                        str2 = string2;
                    }
                }
            }
            return str2;
        } catch (JSONException unused) {
            HwLog.e(f11154a, "fetchWatchFaceScreen() JSONException");
            return "";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.BufferedReader, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.huawei.watchface.o] */
    private String c() {
        Throwable th;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        Closeable closeable;
        ?? r4;
        AssetManager assets = this.c.getResources().getAssets();
        StringBuilder sb = new StringBuilder(32);
        InputStream inputStream2 = null;
        try {
            inputStream = assets.open("watchFace/watch_face_version.json");
        } catch (IOException unused) {
            inputStreamReader = null;
            closeable = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            inputStreamReader = null;
        }
        try {
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            try {
                r4 = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        String readLine = r4.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (IOException unused2) {
                        inputStream2 = inputStream;
                        closeable = r4;
                        try {
                            HwLog.e(f11154a, "readContentFromFile() IOException");
                            a(closeable);
                            inputStream = inputStream2;
                            a(inputStreamReader);
                            a(inputStream);
                            return sb.toString();
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = inputStream2;
                            r4 = closeable;
                            inputStreamReader = inputStreamReader;
                            inputStream2 = r4;
                            a(inputStream2);
                            a(inputStreamReader);
                            a(inputStream);
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream2 = r4;
                        a(inputStream2);
                        a(inputStreamReader);
                        a(inputStream);
                        throw th;
                    }
                }
                a(r4);
            } catch (IOException unused3) {
                r4 = 0;
            } catch (Throwable th5) {
                th = th5;
                r4 = 0;
                inputStreamReader = inputStreamReader;
                inputStream2 = r4;
                a(inputStream2);
                a(inputStreamReader);
                a(inputStream);
                throw th;
            }
        } catch (IOException unused4) {
            inputStreamReader = null;
            r4 = 0;
        } catch (Throwable th6) {
            th = th6;
            inputStreamReader = null;
            a(inputStream2);
            a(inputStreamReader);
            a(inputStream);
            throw th;
        }
        a(inputStreamReader);
        a(inputStream);
        return sb.toString();
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                HwLog.e(f11154a, "safeClose() IOException.");
            }
        }
    }

    public void a() {
        d();
    }

    private static void d() {
        synchronized (o.class) {
            if (b != null) {
                b = null;
            }
        }
    }
}
