package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import health.compact.a.utils.StringUtils;

/* loaded from: classes9.dex */
public class jwm implements IRequest {
    private transient String c;
    private transient String e;

    public jwm(String str, String str2) {
        this.c = str;
        this.e = str2;
    }

    public String b() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        if (StringUtils.g(b())) {
            LogUtil.b("HttpProxyCommonReq", "grsHostKey is empty");
            return "";
        }
        String url = GRSManager.a(BaseApplication.e()).getUrl(b());
        if (StringUtils.g(url)) {
            LogUtil.b("HttpProxyCommonReq", "getUrl host is empty");
            return "";
        }
        String d = d();
        if (StringUtils.g(d)) {
            LogUtil.b("HttpProxyCommonReq", "getUrl path is empty");
            return "";
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        if (!d.startsWith("/")) {
            d = "/" + d;
        }
        return url + d;
    }
}
