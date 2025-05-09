package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import androidx.core.content.FileProvider;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.jcu;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes4.dex */
public class CameraUtil {
    private Context e;

    public CameraUtil(Context context) {
        this.e = context;
    }

    public File e() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(1);
        intent.setFlags(2);
        intent.putExtra("camerasensortype", 2);
        intent.putExtra("autofocus", true);
        intent.putExtra("fullScreen", true);
        intent.putExtra("showActionIcons", false);
        intent.putExtra("return-data", false);
        PackageManager packageManager = this.e.getPackageManager();
        if (packageManager == null) {
            LogUtil.h("CameraUtils", "takePhoto packageManager is null");
            return null;
        }
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveActivity == null) {
            LogUtil.h("CameraUtils", "takePhoto resolveInfo is null");
            return null;
        }
        ActivityInfo activityInfo = resolveActivity.activityInfo;
        if (activityInfo == null) {
            LogUtil.h("CameraUtils", "takePhoto activityInfo is null");
            return null;
        }
        intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
        File c = c();
        if (c != null) {
            Uri uriForFile = FileProvider.getUriForFile(this.e, jcu.f13746a, c);
            intent.putExtra("output", uriForFile);
            LogUtil.a("CameraUtils", "imageUri：", uriForFile);
        }
        try {
            Context context = this.e;
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, 4);
            }
        } catch (ActivityNotFoundException | SecurityException e) {
            ReleaseLogUtil.a("CameraUtils", "takePhoto exception ", ExceptionUtils.d(e));
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("CameraUtils", DfxUtils.d(Thread.currentThread(), e));
        }
        return c;
    }

    private File c() {
        String str = "Camera_IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date()) + "_";
        Context context = this.e;
        try {
            return File.createTempFile(str, ".jpg", context != null ? context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) : null);
        } catch (IOException e) {
            LogUtil.b("CameraUtils", e.getMessage());
            return null;
        }
    }
}
