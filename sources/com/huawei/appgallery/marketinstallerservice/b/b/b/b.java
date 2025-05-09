package com.huawei.appgallery.marketinstallerservice.b.b.b;

import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.operation.utils.Constants;
import defpackage.agr;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.slf4j.Marker;

/* loaded from: classes3.dex */
public abstract class b {
    public static boolean b(String str) {
        return str != null && str.trim().startsWith("{") && str.trim().endsWith("}");
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, "UTF-8").replace(Marker.ANY_NON_NULL_MARKER, Constants.PERCENT_20).replace("*", "%2A").replace(AwarenessConstants.SECOND_ACTION_SPLITE_TAG, "%7E");
        } catch (UnsupportedEncodingException unused) {
            agr.e("StringUtils", "encode2utf8 error");
            return null;
        }
    }
}
