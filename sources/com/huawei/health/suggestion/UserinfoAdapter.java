package com.huawei.health.suggestion;

import android.net.Uri;

/* loaded from: classes8.dex */
public interface UserinfoAdapter {
    public static final int MAN = 0;
    public static final int WOMEN = 1;

    int getAge();

    int getGender();

    int getHeight();

    Uri getPortrait();

    float getWeight();
}
