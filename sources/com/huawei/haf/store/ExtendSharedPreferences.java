package com.huawei.haf.store;

import android.content.SharedPreferences;

/* loaded from: classes.dex */
public interface ExtendSharedPreferences extends SharedPreferences {
    void addStringSet(String str, String str2);

    boolean containsStringSet(String str, String str2);

    void removeStringSet(String str, String str2);
}
