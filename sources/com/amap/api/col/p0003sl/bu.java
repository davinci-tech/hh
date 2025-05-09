package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.ho;
import com.amap.api.maps.AMapException;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class bu<T, V> {

    /* renamed from: a, reason: collision with root package name */
    protected T f932a;
    protected int b = 3;
    protected Context c;

    protected abstract V a(JSONObject jSONObject) throws AMapException;

    protected abstract String a();

    protected abstract JSONObject a(ho.b bVar);

    protected abstract Map<String, String> b();

    public bu(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.c = context;
        this.f932a = t;
    }

    public final V c() throws AMapException {
        if (this.f932a != null) {
            return d();
        }
        return null;
    }

    private V d() throws AMapException {
        int i;
        String str;
        AMapException aMapException;
        int i2 = 0;
        V v = null;
        ho.b bVar = null;
        while (i2 < this.b) {
            try {
                bVar = ho.a(this.c, dv.a(), a(), b());
                v = a(a(bVar));
                i2 = this.b;
            } finally {
                if (i2 < i) {
                    continue;
                }
            }
        }
        return v;
    }
}
