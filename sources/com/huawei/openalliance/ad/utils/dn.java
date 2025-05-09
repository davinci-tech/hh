package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import java.io.File;

/* loaded from: classes5.dex */
public class dn {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7700a = "dn";

    public static boolean a(Context context, String str, String str2) {
        if (cz.b(str)) {
            ho.c(f7700a, "file path is empty");
            return false;
        }
        File b = ae.b(context, str, "normal");
        if ((b == null || !ae.d(b)) && (b = ae.b(context, str, Constants.TPLATE_CACHE)) == null) {
            return false;
        }
        return ae.a(str2, b);
    }

    public static boolean a(Context context, String str, long j) {
        if (cz.b(str)) {
            ho.c(f7700a, "file path is empty");
            return false;
        }
        File b = ae.b(context, str, "normal");
        if ((b == null || !ae.b(b, j)) && (b = ae.b(context, str, Constants.TPLATE_CACHE)) == null) {
            return false;
        }
        return ae.b(b, j);
    }
}
