package com.huawei.maps.offlinedata.handler;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.maps.offlinedata.handler.dto.StorageConfig;
import com.huawei.maps.offlinedata.service.storage.a;
import com.huawei.maps.offlinedata.utils.c;
import com.huawei.maps.offlinedata.utils.d;
import java.io.File;

/* loaded from: classes5.dex */
public class StorageHandler extends JsBaseModule {
    @JavascriptInterface
    public long getAvailableStorageSize() {
        return a.a().b();
    }

    @JavascriptInterface
    public String getStorageConfig() {
        StorageConfig storageConfig = new StorageConfig();
        storageConfig.setDownloadDir(a.a().d());
        storageConfig.setDecompressDir(a.a().e());
        return d.a(storageConfig);
    }

    @JavascriptInterface
    public void deleteFile(String str) {
        c.a(new File(str));
    }
}
