package defpackage;

import android.net.Uri;
import com.huawei.operation.utils.WebViewHelp;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
class dmw {
    private static final Map<String, String> e = new ConcurrentHashMap();

    dmw() {
    }

    static void b(String str) {
        Map<String, String> map = e;
        map.clear();
        try {
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
            String str2 = "";
            if (queryParameter == null) {
                queryParameter = "";
            }
            map.put(WebViewHelp.BI_KEY_PULL_FROM, queryParameter);
            String queryParameter2 = parse.getQueryParameter("fromPageTitle");
            if (queryParameter2 == null) {
                queryParameter2 = "";
            }
            map.put("fromPageTitle", queryParameter2);
            String queryParameter3 = parse.getQueryParameter("pullOrder");
            if (queryParameter3 == null) {
                queryParameter3 = "";
            }
            map.put("pullOrder", queryParameter3);
            String queryParameter4 = parse.getQueryParameter("itemId");
            if (queryParameter4 == null) {
                queryParameter4 = "";
            }
            map.put("itemId", queryParameter4);
            String queryParameter5 = parse.getQueryParameter("resourceName");
            if (queryParameter5 == null) {
                queryParameter5 = "";
            }
            map.put("resourceName", queryParameter5);
            String queryParameter6 = parse.getQueryParameter("resourceId");
            if (queryParameter6 != null) {
                str2 = queryParameter6;
            }
            map.put("resourceId", str2);
        } catch (Exception unused) {
            LogUtil.a("MarketingSourceRecorder", "uri parse failed, will clear record, url: " + str);
        }
    }

    static Map<String, String> e() {
        return new HashMap(e);
    }
}
