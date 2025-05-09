package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.maps.AMapException;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class hf<T, V> extends cz {

    /* renamed from: a, reason: collision with root package name */
    protected T f1120a;
    protected Context g;
    protected String h;
    protected int b = 1;
    protected boolean i = false;

    protected V a(kb kbVar) throws he {
        return null;
    }

    protected abstract V a(String str) throws he;

    protected abstract String c();

    public hf(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.g = context;
        this.f1120a = t;
        this.b = 1;
        setSoTimeout(30000);
        setConnectionTimeout(30000);
    }

    protected V a(byte[] bArr) throws he {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        if (str == null || "".equals(str)) {
            return null;
        }
        hh.a(str);
        return a(str);
    }

    public final V d() throws he {
        if (this.f1120a == null) {
            return null;
        }
        try {
            return e();
        } catch (he e) {
            dv.a(e);
            throw e;
        }
    }

    private V e() throws he {
        V v = null;
        int i = 0;
        while (i < this.b) {
            try {
                setProxy(hy.a(this.g));
                if (this.i) {
                    v = b(makeHttpRequestNeedHeader());
                } else {
                    v = b(makeHttpRequest());
                }
                i = this.b;
            } catch (he e) {
                i++;
                if (i >= this.b) {
                    throw new he(e.a());
                }
            } catch (hm e2) {
                i++;
                if (i < this.b) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                        if (AMapException.ERROR_CONNECTION.equals(e2.getMessage()) || AMapException.ERROR_SOCKET.equals(e2.getMessage()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e2.getMessage())) {
                            throw new he(com.amap.api.services.core.AMapException.AMAP_CLIENT_NETWORK_EXCEPTION);
                        }
                        throw new he(e2.a());
                    }
                } else {
                    if (AMapException.ERROR_CONNECTION.equals(e2.getMessage()) || AMapException.ERROR_SOCKET.equals(e2.getMessage()) || AMapException.ERROR_UNKNOWN.equals(e2.a()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e2.getMessage())) {
                        throw new he(com.amap.api.services.core.AMapException.AMAP_CLIENT_NETWORK_EXCEPTION);
                    }
                    throw new he(e2.a());
                }
            }
        }
        return v;
    }

    private V b(byte[] bArr) throws he {
        return a(bArr);
    }

    private V b(kb kbVar) throws he {
        return a(kbVar);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public Map<String, String> getRequestHead() {
        hz a2 = dv.a();
        String b = a2 != null ? a2.b() : null;
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("User-Agent", v.c);
        hashtable.put(j2.v, Constants.GZIP);
        hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", b, "3dmap"));
        hashtable.put("X-INFO", hq.b(this.g));
        hashtable.put(MedalConstants.EVENT_KEY, hn.f(this.g));
        hashtable.put("logversion", "2.1");
        return hashtable;
    }
}
