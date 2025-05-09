package com.huawei.haf.store;

import android.content.SharedPreferences;

/* loaded from: classes.dex */
public interface KeyValueStoreFactory {
    boolean close(String str, SharedPreferences sharedPreferences);

    SharedPreferences create(String str);
}
