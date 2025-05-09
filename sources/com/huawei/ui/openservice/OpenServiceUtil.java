package com.huawei.ui.openservice;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.openservice.db.model.OpenService;
import defpackage.jdm;
import defpackage.jdw;
import defpackage.nrh;
import defpackage.sqd;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class OpenServiceUtil {
    private static final String LOG_TAG = "Opera_OpenServiceUtil";
    public static final String TAG_PRE = "Opera_";

    /* loaded from: classes9.dex */
    public static class Boolean {
        public static final int FALSE = 0;
        public static final int TRUE = 1;
    }

    /* loaded from: classes9.dex */
    public static class HmsAuthType {
        public static final int IS_HEALTH = 2;
    }

    /* loaded from: classes9.dex */
    public static class InitDBType {
        public static final int INIT_FAIL = 0;
        public static final int INIT_SUCCESS_NEW_SERVICE = 2;
        public static final int INIT_SUCCESS_NO_NEW_SERVICE = 1;
    }

    /* loaded from: classes9.dex */
    public static class Location {
        public static final String STEP = "STEP";
        public static final String WEIGHT = "WEIGHT";
    }

    /* loaded from: classes9.dex */
    public static class ServiceAuthType {
        public static final int IS_AUTH = 1;
        public static final int NOT_AUTH = 0;
    }

    /* loaded from: classes9.dex */
    public static class Source {
        public static final String HUAWEI = "HUAWEI";
        public static final String THIRD_H5 = "THIRD_H5";
    }

    public static Bitmap getIcon(Context context, String str) {
        Bitmap bitmap = null;
        if (context != null && !TextUtils.isEmpty(str)) {
            if (CommonUtil.as()) {
                String substring = str.substring(str.indexOf("lightcloud/servicefw/res/") + 25);
                if (new ArrayList(Arrays.asList("child_bg_shudongli.jpg", "idatapowerweight.jpg")).contains(substring)) {
                    try {
                        InputStream open = context.getAssets().open("openservice/" + substring);
                        bitmap = BitmapFactory.decodeStream(open);
                        open.close();
                        return bitmap;
                    } catch (IOException e) {
                        LogUtil.c(LOG_TAG, e.getMessage());
                        return bitmap;
                    }
                }
                File file = new File(str);
                if (!sqd.b(file)) {
                    LogUtil.h(LOG_TAG, "path_crossing getIcon file is invalid,beta filePath ", str);
                    return null;
                }
                if (file.exists()) {
                    return BitmapFactory.decodeFile(str);
                }
            } else {
                File file2 = new File(str);
                if (!sqd.b(file2)) {
                    LogUtil.h(LOG_TAG, "path_crossing getIcon file is invalid,filePath ", str);
                    return null;
                }
                if (file2.exists()) {
                    return BitmapFactory.decodeFile(str);
                }
            }
        }
        return null;
    }

    public static void jumpToApk(Context context, OpenService openService) {
        if (context == null || openService == null) {
            LogUtil.h(LOG_TAG, "jumpToApk() context or openService is null.");
            return;
        }
        LogUtil.a(LOG_TAG, "enter jumpToApk");
        String packageName = openService.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            LogUtil.h(LOG_TAG, "jumpToApk() packageName is empty.");
        } else if (jdm.b(context, packageName)) {
            openInstalledApk(context, openService, packageName);
        } else {
            LogUtil.a(LOG_TAG, "serviceDetailUrl is not install,jump to Market.");
            jumpToMarket(context, openService, packageName);
        }
    }

    private static void openInstalledApk(Context context, OpenService openService, String str) {
        if (context == null || openService == null || TextUtils.isEmpty(str)) {
            LogUtil.h(LOG_TAG, "openInstalledApk() context or openService or packageName is null.");
            return;
        }
        String serviceUrl = openService.getServiceUrl();
        if (TextUtils.isEmpty(serviceUrl)) {
            LogUtil.h(LOG_TAG, "jumpToApk serviceUrl is empty.");
            return;
        }
        String trim = serviceUrl.trim();
        try {
            if (TextUtils.isEmpty(trim)) {
                openApp(context, str);
            } else {
                LogUtil.a(LOG_TAG, "enter scheme skip");
                openAppPage(context, trim, "");
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(LOG_TAG, "jumpToApk Exception: not found");
            openApp(context, str);
        }
    }

    private static void jumpToMarket(Context context, OpenService openService, String str) {
        if (context == null || openService == null) {
            LogUtil.h(LOG_TAG, "jumpToMarket() context or openService is null.");
        } else {
            jumpToHuaweiMarket(context, str, openService.getDownloadWebUrl());
        }
    }

    private static void openAppPage(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(LOG_TAG, "openAppPage serviceDetailUrl is empty.");
            return;
        }
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(str2)) {
            intent.setPackage(str2);
        } else {
            LogUtil.a(LOG_TAG, "not need packageName");
        }
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setFlags(268435456);
        intent.setData(Uri.parse(str));
        jdw.bGh_(intent, context);
    }

    private static void openApp(Context context, String str) {
        LogUtil.a(LOG_TAG, "enter openApp");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(LOG_TAG, "openApp packageName is empty.");
        } else {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
        }
    }

    private static void jumpToHuaweiMarket(Context context, String str, String str2) {
        if (jdm.b(context, "com.huawei.appmarket")) {
            try {
                openAppPage(context, "market://details?id=" + str, "com.huawei.appmarket");
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b(LOG_TAG, "jumpToMarket meet exception");
                return;
            }
        }
        startWebUrl(context, str2);
    }

    private static void startWebUrl(final Context context, String str) {
        if (context == null) {
            LogUtil.h(LOG_TAG, "startWebUrl context is null.");
            return;
        }
        LogUtil.a(LOG_TAG, "startWebUrl enter");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(LOG_TAG, "jumpToMarket downloadWebUrl is empty");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.ui.openservice.OpenServiceUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    Context context2 = context;
                    nrh.d(context2, context2.getResources().getString(R.string._2130841726_res_0x7f02107e));
                }
            });
        } else {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    public static JSONObject getServiceKeepJson(OpenService openService) {
        if (openService == null) {
            LogUtil.h(LOG_TAG, "getServiceKeepJson data is null.");
            return null;
        }
        String serviceVersion = openService.getServiceVersion();
        String packageName = openService.getPackageName();
        String downloadWebUrl = openService.getDownloadWebUrl();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(OpenServiceConstant.MAP_KEY_SERVICE_VERSION, serviceVersion);
            jSONObject.put("packageName", packageName);
            jSONObject.put(OpenServiceConstant.MAP_KEY_DOWNLOAD_WEB_URL, downloadWebUrl);
        } catch (JSONException e) {
            LogUtil.b(LOG_TAG, "getServiceKeepJson JSONException = ", e.getMessage());
        }
        return jSONObject;
    }

    public static String getValueFromJson(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(LOG_TAG, "getValueFromJson() serviceJson is empty.");
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has(str2) ? jSONObject.getString(str2) : "";
        } catch (JSONException e) {
            LogUtil.b(LOG_TAG, "getValueFromJson JSONException = ", e.getMessage());
            return "";
        }
    }
}
