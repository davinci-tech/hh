package com.huawei.hms.framework.network.restclient.hwhttp.url;

import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.network.base.util.HttpUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class HttpUrl {
    private MalformedURLException exception;
    private List<String> ips = new ArrayList();
    private String strUrl;
    private URL url;

    public HttpUrl(String str) {
        setUrl(str);
    }

    public String getUrl() {
        return this.strUrl;
    }

    private HttpUrl setUrl(String str) {
        CheckParamUtils.checkNotNull(str, "url == null");
        if (str.regionMatches(true, 0, "ws:", 0, 3)) {
            str = "http:" + str.substring(3);
        } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
            str = "https:" + str.substring(4);
        }
        if (!HttpUtils.isHttpOrGrsUrl(str)) {
            throw new IllegalArgumentException("url not valid must be http://;https://;grs://");
        }
        this.strUrl = str;
        if (HttpUtils.isHttpUrl(str)) {
            try {
                this.url = new URL(str);
            } catch (MalformedURLException e) {
                this.exception = e;
            }
        }
        return this;
    }

    public URL getURL() throws MalformedURLException {
        URL url = this.url;
        if (url != null) {
            return url;
        }
        throw this.exception;
    }

    public String getHost() {
        URL url = this.url;
        return url == null ? "" : url.getHost();
    }

    public List<String> getIps() {
        return this.ips;
    }

    public HttpUrl setIps(List<String> list) {
        this.ips = list;
        return this;
    }

    public HttpUrl addIp(String str) {
        this.ips.add(str);
        return this;
    }

    public int getPort() {
        URL url = this.url;
        if (url == null) {
            return -1;
        }
        return url.getPort();
    }

    public String toString() {
        return super.toString();
    }
}
