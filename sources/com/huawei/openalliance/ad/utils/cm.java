package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;

/* loaded from: classes5.dex */
public class cm {
    public static boolean a(Context context, String str) {
        if (cz.b(str)) {
            return false;
        }
        return !cz.b(fh.b(context).e(str));
    }

    public static String a(Context context, ContentRecord contentRecord) {
        if (contentRecord == null) {
            return "0";
        }
        String l = contentRecord.l();
        return cz.b(l) ? "0" : fh.b(context).d(l);
    }
}
