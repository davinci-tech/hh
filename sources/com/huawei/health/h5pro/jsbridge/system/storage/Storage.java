package com.huawei.health.h5pro.jsbridge.system.storage;

import com.huawei.health.h5pro.jsbridge.system.storage.AndroidStorage;

/* loaded from: classes3.dex */
public interface Storage {
    boolean access(String str);

    void base64ToFile(String str, String str2);

    void base64ToImageFile(String str, String str2, AndroidStorage.Callback callback);

    void clearKeyValues(String str);

    void copy(String str, String str2);

    void delete(String str);

    String get(String str);

    String getValueByKey(String str, String str2);

    String[] list(String str);

    void mkdir(String str);

    void move(String str, String str2);

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    String readArrayBuffer(String str);

    String readText(String str);

    void rmdir(String str);

    void saveImageToAlbum(String str, String str2, String str3, AndroidStorage.Callback callback);

    void setValueByKey(String str, String str2, String str3);

    void writeArrayBuffer(String str, String str2);

    void writeText(String str, String str2, boolean z);
}
