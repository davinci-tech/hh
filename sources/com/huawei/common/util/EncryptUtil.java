package com.huawei.common.util;

import android.util.Base64;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.WhiteBoxManager;
import java.io.UnsupportedEncodingException;

/* loaded from: classes8.dex */
public class EncryptUtil {
    private static final String DEFAULT_ENCODE = "UTF-8";
    private static final int SEGMENT_KEY_INDEX_1004 = 1004;
    private static final int SEGMENT_KEY_INDEX_2004 = 2004;
    private static final int SEGMENT_KEY_INDEX_4 = 4;
    private static final String TAG = "EncryptUtil";
    private static final int TYPE_HEALTH = 1;

    private EncryptUtil() {
    }

    public static String getUpServiceToken() {
        String str;
        WhiteBoxManager d = WhiteBoxManager.d();
        String str2 = d.d(1, 4) + d.d(1, 1004) + d.d(1, 2004);
        LogUtil.b(TAG, "upServiceToken before decrypt = " + str2);
        try {
            str = new String(d.a(Base64.decode(str2, 0)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e = e;
        }
        try {
            LogUtil.b(TAG, "upServiceToken after decrypt = ".concat(str));
            return str;
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            str2 = str;
            LogUtil.b(TAG, "getUpServiceToken e = " + e.getMessage());
            return str2;
        }
    }
}
