package defpackage;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/* loaded from: classes5.dex */
public class lpz extends CookieManager implements CookieJar {
    private static final String d = "WebkitCookieProxy";
    private android.webkit.CookieManager c;

    public lpz() {
        this(null, null);
    }

    lpz(CookieStore cookieStore, CookiePolicy cookiePolicy) {
        super(cookieStore, cookiePolicy);
        this.c = android.webkit.CookieManager.getInstance();
    }

    @Override // okhttp3.CookieJar
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Cookie cookie : list) {
            if (cookie != null) {
                arrayList.add(cookie.toString());
            }
        }
        hashMap.put("Set-Cookie", arrayList);
        try {
            put(httpUrl.uri(), hashMap);
        } catch (IOException e) {
            loq.d(d, "saveFromResponse header put cookie error", e);
        }
    }

    @Override // okhttp3.CookieJar
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<List<String>> it = get(httpUrl.uri(), new HashMap()).values().iterator();
            while (it.hasNext()) {
                Iterator<String> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    for (String str : it2.next().split(";")) {
                        arrayList.add(Cookie.parse(httpUrl, str));
                    }
                }
            }
        } catch (IOException e) {
            loq.d(d, "loadForRequest IOException", e);
        }
        return arrayList;
    }

    @Override // java.net.CookieManager, java.net.CookieHandler
    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        if (uri == null || map == null) {
            loq.b(d, "put uri or responseHeaders is null.");
            return;
        }
        String uri2 = uri.toString();
        Iterator<Map.Entry<String, List<String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (key != null && (key.equalsIgnoreCase("Set-Cookie2") || key.equalsIgnoreCase("Set-Cookie"))) {
                Iterator<String> it2 = map.get(key).iterator();
                while (it2.hasNext()) {
                    this.c.setCookie(uri2, it2.next());
                }
            }
        }
    }

    @Override // java.net.CookieManager, java.net.CookieHandler
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        if (uri == null || map == null) {
            loq.b(d, "get uri or responseHeaders is null.");
            throw new IllegalArgumentException("get uri or responseHeaders is null");
        }
        String uri2 = uri.toString();
        HashMap hashMap = new HashMap();
        String cookie = this.c.getCookie(uri2);
        if (cookie != null) {
            hashMap.put("Cookie", Arrays.asList(cookie));
        }
        return hashMap;
    }

    @Override // java.net.CookieManager
    public CookieStore getCookieStore() {
        throw new UnsupportedOperationException();
    }
}
