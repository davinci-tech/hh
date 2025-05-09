package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class cr {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10974a = "cr";

    private cr() {
    }

    public static List<Integer> a(String str) {
        ArrayList arrayList = new ArrayList(10);
        if (TextUtils.isEmpty(str)) {
            HwLog.e(f10974a, "parseAck , bitmapHex is empty");
            return arrayList;
        }
        byte[] a2 = da.a(str);
        StringBuilder sb = new StringBuilder(16);
        for (byte b : a2) {
            sb.append(new StringBuffer(a(b)).reverse().toString());
        }
        String sb2 = sb.toString();
        HwLog.i(f10974a, "parseAck, bufferStr = " + sb2);
        for (int i = 0; i < sb2.length(); i++) {
            if ("0".equalsIgnoreCase(sb2.charAt(i) + "")) {
                arrayList.add(0);
                HwLog.i(f10974a, "errorPackages, error package index = " + i);
            } else {
                arrayList.add(1);
            }
        }
        return arrayList;
    }

    private static String a(byte b) {
        return "" + ((int) ((byte) ((b >> 7) & 1))) + ((int) ((byte) ((b >> 6) & 1))) + ((int) ((byte) ((b >> 5) & 1))) + ((int) ((byte) ((b >> 4) & 1))) + ((int) ((byte) ((b >> 3) & 1))) + ((int) ((byte) ((b >> 2) & 1))) + ((int) ((byte) ((b >> 1) & 1))) + ((int) ((byte) (b & 1)));
    }
}
