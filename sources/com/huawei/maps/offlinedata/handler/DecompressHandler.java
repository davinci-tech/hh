package com.huawei.maps.offlinedata.handler;

import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.maps.offlinedata.handler.dto.DecompressParams;
import com.huawei.maps.offlinedata.jsbridge.a;
import com.huawei.maps.offlinedata.utils.c;
import com.huawei.maps.offlinedata.utils.d;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.secure.android.common.util.SecurityCommonException;
import com.huawei.secure.android.common.util.ZipUtil;
import java.io.File;
import java.util.List;

/* loaded from: classes5.dex */
public class DecompressHandler extends JsBaseModule {
    @JavascriptInterface
    public static void decompress(long j, String str) {
        g.a("DecompressHandler", "decompress offline map data " + j);
        DecompressParams decompressParams = (DecompressParams) d.a(str, DecompressParams.class);
        if (decompressParams == null) {
            g.a("DecompressHandler", "decompress offline map data : params is null");
            a.a().a(-1, "param is error", j);
            return;
        }
        File file = new File(decompressParams.getZipFile());
        File file2 = new File(decompressParams.getTargetPath());
        if (!file.exists() && file2.exists()) {
            a.a().a(true, j);
            return;
        }
        try {
            List<File> unZipNew = ZipUtil.unZipNew(decompressParams.getZipFile(), decompressParams.getTargetPath(), 5368709120L, 1000, decompressParams.isDeleteOld());
            if (decompressParams.isDeleteZip()) {
                c.b(file2);
            }
            if (unZipNew != null && !unZipNew.isEmpty()) {
                a.a().a(true, j);
            } else {
                a.a().a(-1, "no data", j);
            }
        } catch (SecurityCommonException unused) {
            c.a(file2);
            g.d("DecompressHandler", "unZip: SecurityCommonException: ");
            a.a().a(-1, "Exception", j);
        } catch (IllegalArgumentException unused2) {
            c.a(file2);
            g.d("DecompressHandler", "unZip: IllegalArgumentException: ");
            a.a().a(-1, "Exception", j);
        } catch (Exception unused3) {
            c.a(file2);
            g.d("DecompressHandler", "unZip: Exception: ");
            a.a().a(-1, "Exception", j);
        }
    }
}
