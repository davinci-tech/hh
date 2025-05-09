package com.huawei.openalliance.ad;

import androidx.exifinterface.media.ExifInterface;

/* loaded from: classes5.dex */
public abstract class hs {
    public static String a(int i) {
        return i != 3 ? i != 4 ? i != 5 ? i != 6 ? String.valueOf(i) : ExifInterface.LONGITUDE_EAST : "W" : "I" : "D";
    }
}
