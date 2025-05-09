package defpackage;

import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.videoedit.utils.Utils;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public final class kso {
    private ConcurrentHashMap<String, String> b;

    public kso() {
        this.b = null;
        ConcurrentHashMap<String, String> d = ksd.d();
        this.b = d;
        if (d.isEmpty()) {
            ksy.b("DataHelper", "resourcesMap is empty", true);
            return;
        }
        ksy.b("DataHelper", "resourcesMap--" + this.b.size(), false);
    }

    public boolean d(String str) {
        return this.b.containsKey(str);
    }

    public WebResourceResponse bQF_(String str) {
        String str2;
        String str3 = this.b.get(str);
        if (TextUtils.isEmpty(str3)) {
            return null;
        }
        try {
            InputStream d = kss.d(ksd.b() + str3);
            if (d == null) {
                return null;
            }
            if (str.contains(".css")) {
                ksy.b("DataHelper", "mimeType is css", false);
                str2 = "text/css";
            } else if (str.contains(".png") || str.contains(".ico") || str.contains(Utils.SUFFIX_GIF)) {
                ksy.b("DataHelper", "mimeType is png", false);
                str2 = Constants.IMAGE_TYPE;
            } else if (str.contains(".js")) {
                ksy.b("DataHelper", "mimeType is js", false);
                str2 = "text/javascript";
            } else {
                ksy.b("DataHelper", "getReplacedWebResourceResponse: mimetype default", false);
                return null;
            }
            ksy.b("DataHelper", "getReplacedWebResourceResponse = " + this.b.get(str), false);
            return new WebResourceResponse(str2, "utf-8", d);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
            ksy.c("DataHelper", "get hw folder or parse InputSteam failed", true);
            return null;
        }
    }
}
