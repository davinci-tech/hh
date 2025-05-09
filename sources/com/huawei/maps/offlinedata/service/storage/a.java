package com.huawei.maps.offlinedata.service.storage;

import android.content.Context;
import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.maps.offlinedata.utils.j;
import java.io.File;
import java.io.IOException;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6525a;

    private a() {
    }

    public static a a() {
        if (f6525a == null) {
            synchronized (a.class) {
                if (f6525a == null) {
                    f6525a = new a();
                }
            }
        }
        return f6525a;
    }

    public long b() {
        try {
            String f = f();
            if (TextUtils.isEmpty(f)) {
                g.d("StorageService", "getAvailableStorageSize failed , basePath exception.");
                return -1L;
            }
            StatFs statFs = new StatFs(f);
            return (statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong()) / 1048576;
        } catch (IllegalArgumentException unused) {
            g.d("StorageService", "getAvailableStorageSize failed , IllegalArgumentException");
            return -1L;
        }
    }

    public String c() {
        String f = f();
        if (TextUtils.isEmpty(f)) {
            g.d("StorageService", "getOfflineDataDir failed , basePath exception.");
            return null;
        }
        return f + File.separator + "offlineData" + File.separator;
    }

    public String d() {
        String c = c();
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        return c + "download" + File.separator;
    }

    public String e() {
        String c = c();
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        return c + "decompress" + File.separator;
    }

    private String f() {
        Context a2 = com.huawei.maps.offlinedata.utils.a.a();
        String str = null;
        if (a2 == null) {
            g.d("StorageService", "getBaseDir failed , context is null.");
            return null;
        }
        String a3 = j.a("offline_storage_file_path", "", a2);
        if (!TextUtils.isEmpty(a3)) {
            return a3;
        }
        try {
            File filesDir = a2.getFilesDir();
            if (filesDir != null) {
                str = filesDir.getCanonicalPath();
            }
        } catch (IOException unused) {
            g.d("StorageService", "getExternalFileDir failed.");
        }
        if (!TextUtils.isEmpty(str)) {
            j.b("offline_storage_file_path", str, a2);
        }
        return str;
    }
}
