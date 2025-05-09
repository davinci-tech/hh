package com.huawei.agconnect.apms;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes8.dex */
public class p0 implements FilenameFilter {
    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return TextUtils.isDigitsOnly(str);
    }
}
