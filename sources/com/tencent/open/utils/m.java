package com.tencent.open.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import androidx.core.content.FileProvider;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private static String f11384a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static int e = -1;
    private static String f = "0123456789ABCDEF";

    private static char a(int i) {
        int i2 = i & 15;
        return (char) (i2 < 10 ? i2 + 48 : i2 + 87);
    }

    public static Bundle a(String str) {
        Bundle bundle = new Bundle();
        if (str == null) {
            return bundle;
        }
        try {
            for (String str2 : str.split("&")) {
                String[] a2 = a(str2, "=");
                if (a2.length == 2) {
                    bundle.putString(URLDecoder.decode(a2[0]), URLDecoder.decode(a2[1]));
                }
            }
            return bundle;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String[] a(String str, String str2) {
        int indexOf = str.indexOf(str2);
        return indexOf == -1 ? new String[]{str} : new String[]{str.substring(0, indexOf), str.substring(indexOf + str2.length())};
    }

    public static JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    try {
                        try {
                            split[0] = URLDecoder.decode(split[0]);
                            split[1] = URLDecoder.decode(split[1]);
                        } catch (JSONException e2) {
                            SLog.e("openSDK_LOG.Util", "decodeUrlToJson has exception: " + e2.getMessage());
                        }
                    } catch (Exception unused) {
                    }
                    jSONObject.put(split[0], split[1]);
                }
            }
        }
        return jSONObject;
    }

    public static Bundle b(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            Bundle a2 = a(url.getQuery());
            a2.putAll(a(url.getRef()));
            return a2;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    public static JSONObject c(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            JSONObject a2 = a((JSONObject) null, url.getQuery());
            a(a2, url.getRef());
            return a2;
        } catch (MalformedURLException unused) {
            return new JSONObject();
        }
    }

    public static JSONObject d(String str) throws JSONException {
        if (str.equals("false")) {
            str = "{value : false}";
        }
        if (str.equals("true")) {
            str = "{value : true}";
        }
        if (str.contains("allback(")) {
            str = str.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        if (str.contains("online[0]=")) {
            str = "{online:" + str.charAt(str.length() - 2) + "}";
        }
        return new JSONObject(str);
    }

    public static boolean e(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean g(Context context) {
        Signature[] signatureArr;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mtt", 64);
            String str = packageInfo.versionName;
            if (k.a(str, "4.3") >= 0 && !str.startsWith("4.4") && (signatureArr = packageInfo.signatures) != null) {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(signatureArr[0].toByteArray());
                    String a2 = a(messageDigest.digest());
                    messageDigest.reset();
                    if (a2.equals("d8391a394d4a179e6fe7bdb8a301258b")) {
                        return true;
                    }
                } catch (NoSuchAlgorithmException e2) {
                    SLog.e("openSDK_LOG.Util", "isQQBrowerAvailable has exception: " + e2.getMessage());
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static boolean a(Context context, String str) {
        try {
            boolean g = g(context);
            try {
                if (g) {
                    a(context, "com.tencent.mtt", "com.tencent.mtt.MainActivity", str);
                } else {
                    a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
                }
                return true;
            } catch (Exception unused) {
                if (g) {
                    try {
                        try {
                            try {
                                a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
                                return true;
                            } catch (Exception unused2) {
                                return false;
                            }
                        } catch (Exception unused3) {
                            a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                            return true;
                        }
                    } catch (Exception unused4) {
                        a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                        return true;
                    }
                }
                try {
                    try {
                        a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                        return true;
                    } catch (Exception unused5) {
                        a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                        return true;
                    }
                } catch (Exception unused6) {
                    return false;
                }
            }
        } catch (Exception unused7) {
        }
    }

    private static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.addFlags(1073741824);
        intent.addFlags(268435456);
        intent.setData(Uri.parse(str3));
        context.startActivity(intent);
    }

    public static String f(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            SLog.e("openSDK_LOG.Util", "urlEncode: UnsupportedEncodingException", e2);
            return "";
        }
    }

    public static String g(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(j(str));
            byte[] digest = messageDigest.digest();
            if (digest == null) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                sb.append(a(b2 >>> 4));
                sb.append(a(b2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            SLog.e("openSDK_LOG.Util", "encrypt has exception: " + e2.getMessage());
            return str;
        }
    }

    public static boolean a() {
        return (Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : null) != null;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            String num = Integer.toString(b2 & 255, 16);
            if (num.length() == 1) {
                num = "0" + num;
            }
            sb.append(num);
        }
        return sb.toString();
    }

    public static final String a(Context context) {
        CharSequence applicationLabel;
        if (context == null || (applicationLabel = context.getPackageManager().getApplicationLabel(context.getApplicationInfo())) == null) {
            return null;
        }
        return applicationLabel.toString();
    }

    public static final boolean h(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("http://") || str.startsWith("https://");
    }

    public static boolean i(String str) {
        return str != null && new File(str).exists();
    }

    public static final String a(String str, int i, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "UTF-8";
        }
        try {
            if (str.getBytes(str2).length <= i) {
                return str;
            }
            int i2 = 0;
            int i3 = 0;
            while (i2 < str.length()) {
                int i4 = i2 + 1;
                i3 += str.substring(i2, i4).getBytes(str2).length;
                if (i3 > i) {
                    String substring = str.substring(0, i2);
                    if (TextUtils.isEmpty(str3)) {
                        return substring;
                    }
                    return substring + str3;
                }
                i2 = i4;
            }
            return str;
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.Util", "Util.subString has exception: " + e2.getMessage());
            return str;
        }
    }

    public static boolean b(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo[] networkInfoArr;
        if (context == null || i(context, "android.permission.ACCESS_NETWORK_STATE") || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return true;
        }
        try {
            networkInfoArr = connectivityManager.getAllNetworkInfo();
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.Util", "Util.isNetWorkAvailable has exception: ", e2);
            networkInfoArr = null;
        }
        if (networkInfoArr != null && networkInfoArr.length != 0) {
            for (NetworkInfo networkInfo : networkInfoArr) {
                if (networkInfo.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean i(Context context, String str) {
        try {
            return context.checkSelfPermission(str) != 0;
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.Util", "checkSelfPermission exception", e2);
            return false;
        }
    }

    public static Bundle a(Bundle bundle, String str) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("result", str);
        return bundle;
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        Bundle bundle = new Bundle();
        bundle.putString("openid", str);
        bundle.putString("report_type", str2);
        bundle.putString("act_type", str3);
        bundle.putString("via", str4);
        bundle.putString("app_id", str5);
        bundle.putString("type", str6);
        bundle.putString("login_status", str7);
        bundle.putString("need_user_auth", str8);
        bundle.putString("to_uin", str9);
        bundle.putString("call_source", str10);
        bundle.putString("to_type", str11);
        bundle.putString("platform", "1");
        return bundle;
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5) {
        return a(str, str3, str4, str2, str5, "", "", "", "", "", "");
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6) {
        return a(str, str3, str4, str2, str5, str6, "", "", "", "", "", "");
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        return a(a(str, str2, str3, str4, str5, str7, str8, str9, str10, str11, str12), str6);
    }

    public static void b(Context context, String str) {
        if (context == null) {
            return;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            String str2 = packageInfo.versionName;
            b = str2;
            f11384a = str2.substring(0, str2.lastIndexOf(46));
            String str3 = b;
            d = str3.substring(str3.lastIndexOf(46) + 1, b.length());
            e = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            SLog.e("openSDK_LOG.Util", "getPackageInfo has exception: " + e2.getMessage());
        } catch (Exception e3) {
            SLog.e("openSDK_LOG.Util", "getPackageInfo has exception: " + e3.getMessage());
        }
    }

    public static String c(Context context, String str) {
        if (context == null) {
            return "";
        }
        b(context, str);
        return b;
    }

    public static String d(Context context, String str) {
        if (context == null) {
            return "";
        }
        b(context, str);
        return f11384a;
    }

    public static String e(Context context, String str) {
        if (context == null) {
            return "";
        }
        String d2 = d(context, str);
        c = d2;
        return d2;
    }

    public static byte[] j(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            SLog.e("openSDK_LOG.Util", "getBytesUTF8: UnsupportedEncodingException", e2);
            return new byte[0];
        }
    }

    public static boolean c(Context context) {
        double d2;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            d2 = Math.sqrt(Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d) + Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d));
        } catch (Throwable unused) {
            d2 = 0.0d;
        }
        return d2 > 6.5d;
    }

    public static boolean f(Context context, String str) {
        boolean z = !c(context) || k.a(context, Constants.PACKAGE_QQ_PAD) == null;
        if (z && k.a(context, Constants.PACKAGE_TIM) != null) {
            z = false;
        }
        if (z) {
            return k.c(context, str) < 0;
        }
        return z;
    }

    public static boolean g(Context context, String str) {
        boolean z = !c(context) || k.a(context, Constants.PACKAGE_QQ_PAD) == null;
        if (z) {
            return k.c(context, str) < 0;
        }
        return z;
    }

    public static boolean a(Context context, boolean z) {
        return (c(context) && k.a(context, Constants.PACKAGE_QQ_PAD) != null) || k.c(context, "4.1") >= 0 || k.a(context, Constants.PACKAGE_TIM) != null;
    }

    public static boolean d(Context context) {
        return k.c(context, "8.1.5") >= 0;
    }

    public static boolean e(Context context) {
        return k.c(context, "8.1.8") >= 0;
    }

    public static boolean f(Context context) {
        return k.c(context, "5.9.5") >= 0;
    }

    public static long a(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, new String[]{"_size"}, null, null, null);
        long j = 0;
        if (query != null) {
            try {
                if (query.getCount() != 0) {
                    try {
                        int columnIndexOrThrow = query.getColumnIndexOrThrow("_size");
                        if (query.moveToFirst()) {
                            j = query.getLong(columnIndexOrThrow);
                        }
                    } catch (Exception e2) {
                        SLog.e("openSDK_LOG.Util", "cursor exception", e2);
                    }
                    return j;
                }
            } finally {
                try {
                    query.close();
                } catch (Exception e3) {
                    SLog.e("openSDK_LOG.Util", "cursor exception", e3);
                }
            }
        }
        return 0L;
    }

    public static String b(Context context, Uri uri) {
        Uri uri2;
        if (uri == null) {
            return null;
        }
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String authority = uri.getAuthority();
            if ("com.android.externalstorage.documents".equals(authority)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String str = split[0];
                if ("primary".equals(str)) {
                    return Environment.getExternalStorageDirectory().getAbsolutePath().concat("/").concat(split[1]);
                }
                return "/storage/".concat(str).concat("/").concat(split[1]);
            }
            if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (documentId.startsWith("raw:")) {
                    return documentId.replaceFirst("raw:", "");
                }
                return c(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(documentId)));
            }
            if ("com.android.providers.media.documents".equals(authority)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str2 = split2[0];
                if (Constant.TYPE_PHOTO.equals(str2)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str2)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (PresenterUtils.AUDIO.equals(str2)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return c(context, ContentUris.withAppendedId(uri2, Long.parseLong(split2[1])));
            }
            return null;
        }
        String scheme = uri.getScheme();
        if ("content".equals(scheme)) {
            return c(context, uri);
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(android.content.Context r11, android.net.Uri r12) {
        /*
            Method dump skipped, instructions count: 504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.m.c(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static String k(String str) {
        return a(str, 2);
    }

    public static String a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Base64.encodeToString(str.getBytes("UTF-8"), i);
            } catch (UnsupportedEncodingException e2) {
                SLog.e("openSDK_LOG.Util", "convert2Base64String exception: " + e2.getMessage());
            }
        }
        return "";
    }

    public static Drawable a(String str, Context context) {
        InputStream inputStream;
        StringBuilder sb;
        InputStream inputStream2 = null;
        r2 = null;
        Drawable drawable = null;
        if (context == null) {
            SLog.e("openSDK_LOG.Util", "context null!");
            return null;
        }
        try {
            inputStream = context.getAssets().open(str);
            try {
                try {
                    drawable = Drawable.createFromStream(inputStream, str);
                } catch (IOException e2) {
                    e = e2;
                    SLog.e("openSDK_LOG.Util", "getDrawable exception: " + e.getMessage());
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                        e = e3;
                        sb = new StringBuilder("inputStream close exception: ");
                        sb.append(e.getMessage());
                        SLog.e("openSDK_LOG.Util", sb.toString());
                        return drawable;
                    }
                    return drawable;
                }
                try {
                    inputStream.close();
                } catch (Exception e4) {
                    e = e4;
                    sb = new StringBuilder("inputStream close exception: ");
                    sb.append(e.getMessage());
                    SLog.e("openSDK_LOG.Util", sb.toString());
                    return drawable;
                }
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                try {
                    inputStream2.close();
                } catch (Exception e5) {
                    SLog.e("openSDK_LOG.Util", "inputStream close exception: " + e5.getMessage());
                }
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream2.close();
            throw th;
        }
        return drawable;
    }

    public static File h(Context context, String str) {
        File[] externalFilesDirs;
        if (context == null || (externalFilesDirs = context.getExternalFilesDirs(str)) == null || externalFilesDirs.length <= 0) {
            return null;
        }
        return externalFilesDirs[0];
    }

    public static String b() {
        File e2 = g.e();
        if (e2 == null) {
            return null;
        }
        if (!e2.exists()) {
            e2.mkdirs();
        }
        return e2.toString();
    }

    public static boolean c() {
        Context a2 = g.a();
        return a2 != null && a2.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", a2.getPackageName()) == 0;
    }

    public static File l(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    file.createNewFile();
                } else {
                    SLog.d("openSDK_LOG.Util", "createFile failed" + str);
                }
            } else {
                file.createNewFile();
            }
        }
        return file;
    }

    public static boolean b(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            try {
                return a(file, l(str2));
            } catch (IOException e2) {
                SLog.d("openSDK_LOG.Util", "copy fail from " + str + " to " + str2 + " ", e2);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r8v9 */
    public static boolean a(File file, File file2) {
        ?? r8;
        FileOutputStream fileOutputStream;
        ?? bufferedInputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                if (file2.exists()) {
                    file2.delete();
                }
                if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream((File) file2);
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                } catch (IOException e2) {
                    e = e2;
                } catch (OutOfMemoryError e3) {
                    e = e3;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream2;
                r8 = file2;
            }
        } catch (IOException e4) {
            e = e4;
            file2 = 0;
        } catch (OutOfMemoryError e5) {
            e = e5;
            file2 = 0;
        } catch (Throwable th3) {
            th = th3;
            r8 = 0;
        }
        try {
            byte[] bArr = new byte[102400];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                    fileOutputStream.flush();
                } else {
                    try {
                        break;
                    } catch (IOException e6) {
                        SLog.e("openSDK_LOG.Util", "copyFile error, ", e6);
                    }
                }
            }
            fileOutputStream.close();
            try {
                bufferedInputStream.close();
            } catch (IOException e7) {
                SLog.e("openSDK_LOG.Util", "copyFile error, ", e7);
            }
            return true;
        } catch (IOException e8) {
            e = e8;
            fileOutputStream2 = bufferedInputStream;
            file2 = fileOutputStream2;
            fileOutputStream2 = fileOutputStream;
            SLog.e("openSDK_LOG.Util", "copyFile error, ", e);
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e9) {
                    SLog.e("openSDK_LOG.Util", "copyFile error, ", e9);
                }
            }
            if (file2 == 0) {
                return false;
            }
            try {
                file2.close();
                return false;
            } catch (IOException e10) {
                SLog.e("openSDK_LOG.Util", "copyFile error, ", e10);
                return false;
            }
        } catch (OutOfMemoryError e11) {
            e = e11;
            fileOutputStream2 = bufferedInputStream;
            file2 = fileOutputStream2;
            fileOutputStream2 = fileOutputStream;
            SLog.e("openSDK_LOG.Util", "copyFile error, ", e);
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e12) {
                    SLog.e("openSDK_LOG.Util", "copyFile error, ", e12);
                }
            }
            if (file2 == 0) {
                return false;
            }
            file2.close();
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream2 = bufferedInputStream;
            r8 = fileOutputStream2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e13) {
                    SLog.e("openSDK_LOG.Util", "copyFile error, ", e13);
                }
            }
            if (r8 == 0) {
                throw th;
            }
            try {
                r8.close();
                throw th;
            } catch (IOException e14) {
                SLog.e("openSDK_LOG.Util", "copyFile error, ", e14);
                throw th;
            }
        }
    }

    public static boolean a(Context context, String str, String str2) {
        boolean b2 = b(str, str2);
        SLog.i("openSDK_LOG.Util", "copyFileByCheckPermission() copy success:" + b2);
        return b2;
    }

    public static boolean m(String str) {
        String b2 = b();
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(b2) || !str.contains(b2)) ? false : true;
    }

    public static String a(String str, Activity activity, String str2, IUiListener iUiListener) {
        String str3;
        try {
            boolean m = m(str2);
            SLog.i("openSDK_LOG.Util", "doPublishMood() check file: isAppSpecificDir=" + m + ",hasSDPermission=" + c());
            if (!m) {
                File a2 = g.a("Images");
                if (a2 != null) {
                    str3 = a2.getAbsolutePath() + File.separator + Constants.QQ_SHARE_TEMP_DIR;
                } else {
                    File cacheDir = g.a().getCacheDir();
                    if (cacheDir == null) {
                        SLog.e("openSDK_LOG.Util", "getMediaFileUri error, cacheDir is null");
                        return null;
                    }
                    str3 = cacheDir.getAbsolutePath() + File.separator + Constants.QQ_SHARE_TEMP_DIR;
                }
                File file = new File(str2);
                String absolutePath = file.getAbsolutePath();
                String str4 = str3 + File.separator + file.getName();
                str2 = b(absolutePath, str4) ? str4 : null;
            }
            Uri a3 = a(activity, str, str2);
            if (a3 == null) {
                return null;
            }
            return a3.toString();
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.Util", "getMediaFileUri error", e2);
            return null;
        }
    }

    public static boolean a(Map<String, Object> map, String str, boolean z) {
        if (map == null) {
            SLog.e("openSDK_LOG.Util", "getBoolean error, params==null");
            return z;
        }
        if (!map.containsKey(str)) {
            SLog.e("openSDK_LOG.Util", "getBoolean error, not comtain : " + str);
            return z;
        }
        Object obj = map.get(str);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
    }

    public static String a(Map<String, Object> map, String str, String str2) {
        if (map == null) {
            SLog.e("openSDK_LOG.Util", "getString error, params==null");
            return str2;
        }
        if (!map.containsKey(str)) {
            SLog.e("openSDK_LOG.Util", "getString error, not comtain : " + str);
            return str2;
        }
        Object obj = map.get(str);
        return obj instanceof String ? (String) obj : str2;
    }

    public static Uri a(Activity activity, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            SLog.e("openSDK_LOG.Util", "grantUriPermissionToAllQQVersion -- stringForFileUri is empty");
            return null;
        }
        try {
            String authorities = Tencent.getAuthorities(str);
            if (TextUtils.isEmpty(authorities)) {
                return null;
            }
            Uri uriForFile = FileProvider.getUriForFile(activity, authorities, new File(str2));
            activity.grantUriPermission("com.tencent.mobileqq", uriForFile, 3);
            activity.grantUriPermission(Constants.PACKAGE_TIM, uriForFile, 3);
            activity.grantUriPermission(Constants.PACKAGE_QQ_PAD, uriForFile, 3);
            activity.grantUriPermission(Constants.PACKAGE_QQ_SPEED, uriForFile, 3);
            return uriForFile;
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.Util", "grantUriPermissionToAllQQVersion exception:", e2);
            return null;
        }
    }
}
