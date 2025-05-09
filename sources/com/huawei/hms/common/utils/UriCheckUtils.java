package com.huawei.hms.common.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import defpackage.ksy;
import java.io.File;

/* loaded from: classes4.dex */
public class UriCheckUtils {
    public static final int SCENE_CAMERA = 0;
    public static final int SCENE_GALLERY = 1;

    public static boolean isUriValid(int i, Uri uri) {
        if (uri == null) {
            ksy.c("UriCheckUtils", "uri is invalid. uri is null.", true);
            return false;
        }
        if ("content".equals(uri.getScheme())) {
            return a(uri);
        }
        String path = uri.getPath();
        if (i == 0) {
            return b(path);
        }
        if (1 == i) {
            return a(path);
        }
        return false;
    }

    private static boolean a(Uri uri) {
        if (uri == null) {
            ksy.c("UriCheckUtils", "uri is invalid. uri is null.", true);
            return false;
        }
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            ksy.c("UriCheckUtils", "uri is invalid. content uri's authority is empty.", true);
            return false;
        }
        if (!authority.startsWith("com.huawei.hms") && !authority.startsWith("com.huawei.hwid")) {
            return true;
        }
        ksy.c("UriCheckUtils", "uri is invalid. content uri's authority is " + authority, true);
        return false;
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            ksy.c("UriCheckUtils", "uri is invalid. path is null.", true);
            return false;
        }
        try {
            String canonicalPath = new File(str).getCanonicalPath();
            if (TextUtils.isEmpty(canonicalPath)) {
                ksy.c("UriCheckUtils", "uri is invalid. CanonicalPath is empty.", true);
                return false;
            }
            if (canonicalPath.contains("data/data/com.huawei.hwid")) {
                ksy.c("UriCheckUtils", "uri is invalid. path is private FILTER_PRIVATE_DIR.", true);
                return false;
            }
            if (canonicalPath.contains("data/user") && canonicalPath.contains("com.huawei.hwid")) {
                ksy.c("UriCheckUtils", "uri is invalid. path is private data-user.", true);
                return false;
            }
            if (!canonicalPath.contains("./") && !canonicalPath.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
                if (!canonicalPath.endsWith(WatchFaceConstant.XML_SUFFIX) && !canonicalPath.endsWith(".js") && !canonicalPath.endsWith(".json") && !canonicalPath.endsWith(Event.EventConstants.PROPERTIES)) {
                    return true;
                }
                ksy.c("UriCheckUtils", "uri is invalid. path is private invalid suffix.", true);
                return false;
            }
            ksy.c("UriCheckUtils", "uri is invalid. path is private relative path.", true);
            return false;
        } catch (Throwable unused) {
            ksy.c("UriCheckUtils", "uri is invalid. path is error when read to file.", true);
            return false;
        }
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            ksy.c("UriCheckUtils", "uri is invalid. path is null.", true);
            return false;
        }
        try {
            String canonicalPath = new File(str).getCanonicalPath();
            if (TextUtils.isEmpty(canonicalPath)) {
                ksy.c("UriCheckUtils", "uri is invalid. CanonicalPath is empty.", true);
                return false;
            }
            if (!canonicalPath.contains("./") && !canonicalPath.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
                if (canonicalPath.endsWith(".jpg")) {
                    return true;
                }
                ksy.c("UriCheckUtils", "uri is invalid. path is private invalid suffix.", true);
                return false;
            }
            ksy.c("UriCheckUtils", "uri is invalid. path is private relative path.", true);
            return false;
        } catch (Throwable unused) {
            ksy.c("UriCheckUtils", "uri is invalid. path is error when read to file.", true);
            return false;
        }
    }
}
