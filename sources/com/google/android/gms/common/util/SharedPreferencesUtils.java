package com.google.android.gms.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import java.io.File;

/* loaded from: classes8.dex */
public class SharedPreferencesUtils {
    private SharedPreferencesUtils() {
    }

    @Deprecated
    public static void publishWorldReadableSharedPreferences(Context context, SharedPreferences.Editor editor, String str) {
        File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.setExecutable(true, false);
        }
        file.setExecutable(true, false);
        editor.commit();
        new File(file, String.valueOf(str).concat(WatchFaceConstant.XML_SUFFIX)).setReadable(true, false);
    }
}
