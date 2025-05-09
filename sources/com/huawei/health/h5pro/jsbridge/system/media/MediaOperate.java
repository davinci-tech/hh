package com.huawei.health.h5pro.jsbridge.system.media;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import java.io.File;
import java.util.Locale;

/* loaded from: classes3.dex */
public class MediaOperate {
    public final Activity d;

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public final File getMediaStorageDir(String str, String str2) {
        if (!GeneralUtil.isSafePath(str) || !GeneralUtil.isSafePath(str2)) {
            LogUtil.w("H5PRO_MediaOperate", "getPicturesPath: invalid appTag or subDir");
            return null;
        }
        Locale locale = Locale.ENGLISH;
        String str3 = File.separator;
        return this.d.getExternalFilesDir(String.format(locale, "h5pro%smedia%s%s%s%s", str3, str3, str, str3, str2));
    }

    public void deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        deleteFile(new File(str));
    }

    public void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (!file.exists()) {
            LogUtil.w("H5PRO_MediaOperate", "deleteFile -> The file does not exist");
            return;
        }
        LogUtil.d("H5PRO_MediaOperate", "deleteFile -> " + file.getAbsolutePath());
        if (!file.isDirectory()) {
            file.delete();
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            file2.delete();
        }
    }

    public final boolean checkPermission(String[] strArr, boolean z, int i) {
        if (PermissionUtil.getInstance().checkPermission(this.d, strArr)) {
            return true;
        }
        if (!z) {
            return false;
        }
        PermissionUtil.getInstance().requestPermission(this.d, strArr, i);
        return false;
    }

    public MediaOperate(Activity activity) {
        this.d = activity;
    }
}
