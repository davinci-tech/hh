package com.huawei.health.h5pro.jsbridge.system.media;

import android.content.Context;
import android.content.Intent;
import com.huawei.health.h5pro.jsbridge.Callback;
import com.huawei.health.h5pro.jsbridge.system.media.AndroidMedia;

/* loaded from: classes3.dex */
public interface Media {
    void chooseFile(String str, Callback callback);

    void destroy();

    void onActivityResult(int i, int i2, Intent intent);

    void onMount(Context context);

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    void scanQrCode(AndroidMedia.Callback callback);

    void uploadPicture(String str);
}
