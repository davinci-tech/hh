package com.huawei.health.h5pro.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GeneralUtil {
    public static final Pattern e = Pattern.compile("^[-\\+]?[\\d]*$");

    public static boolean isShouldSelfProtection(Context context, String str, String[] strArr, boolean z) {
        if (TextUtils.isEmpty(str) || context == null || strArr == null || strArr.length == 0) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return z;
    }

    public static boolean isSafePath(String str) {
        return (TextUtils.isEmpty(str) || str.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) ? false : true;
    }

    public static boolean isNumbers(String str) {
        return !TextUtils.isEmpty(str) && e.matcher(str).matches();
    }

    public static <T> T getReferent(WeakReference<T> weakReference) {
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static String getFileSafePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String path = Uri.parse(str).getPath();
        return TextUtils.isEmpty(path) ? "" : getFileSafePath(new File(path));
    }

    public static String getFileSafePath(File file) {
        if (file == null) {
            return "";
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e2) {
            LogUtil.e("H5PRO_GeneralUtil", "getFileSafePath: ioException-> " + e2.getMessage());
            return "";
        }
    }
}
