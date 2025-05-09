package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.health.h5pro.utils.LogUtil;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/* loaded from: classes3.dex */
public class MediaUtils {
    public static void startDefaultActivityForResult(Activity activity, Intent intent, String str, int i) {
        ResolveInfo resolveActivity;
        try {
            LogUtil.i("H5PRO_MediaUtils", "startDefaultActivityForResult");
            PackageManager packageManager = activity.getPackageManager();
            if (packageManager != null && (((resolveActivity = packageManager.resolveActivity(intent, 65536)) == null || "com.huawei.android.internal.app".equalsIgnoreCase(resolveActivity.activityInfo.packageName)) && getPackageInfo(activity, str) != null)) {
                intent.setPackage(str);
                activity.startActivityForResult(intent, i);
                return;
            }
        } catch (Throwable unused) {
            LogUtil.e("H5PRO_MediaUtils", "startDefaultActivityForResult error");
        }
        try {
            activity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException unused2) {
            LogUtil.e("H5PRO_MediaUtils", "startDefaultActivityForResult error");
        }
    }

    public static PackageInfo getPackageInfo(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("H5PRO_MediaUtils", "get package info NameNotFoundException");
            return null;
        }
    }

    public static String fileToBase64(String str) {
        byte[] bArr;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String obj = TextUtils.concat("data:", URLConnection.getFileNameMap().getContentTypeFor(str), ";base64,").toString();
        try {
            bArr = Files.readAllBytes(Paths.get(str, new String[0]));
        } catch (IOException e) {
            LogUtil.e("H5PRO_MediaUtils", "fileToBase64: ioException(>=O) -> " + e.getMessage());
            bArr = null;
        }
        if (bArr == null) {
            return "";
        }
        String encodeToString = Base64.encodeToString(bArr, 16);
        return !TextUtils.isEmpty(encodeToString) ? TextUtils.concat(obj, encodeToString).toString() : "";
    }
}
