package com.huawei.watchface.utils.analytice.data;

import com.huawei.watchface.em;
import com.huawei.watchface.eo;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public abstract class BaseEvent<T extends BaseEvent> implements em {
    public static final String KEY_CONTENTINFO = "contentInfo";
    public static final String KEY_DESCINFO = "descinfo";
    public static final String KEY_ERRORCODE = "errorCode";
    protected long b;
    protected long c;
    protected long d;
    protected String e;
    protected String f;
    protected String g;

    public void a_() {
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.utils.analytice.data.BaseEvent$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BaseEvent.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        LinkedHashMap<String, String> b = b();
        if (ArrayUtils.a(b)) {
            return;
        }
        b.putAll(eo.b());
        eo.a().a(1, a(), b);
    }

    public long m() {
        return this.b;
    }

    public T a(long j) {
        this.b = j;
        return this;
    }

    public long n() {
        return this.c;
    }

    public T b(long j) {
        this.c = j;
        return this;
    }

    public long o() {
        return this.d;
    }

    public T c(long j) {
        this.d = j;
        return this;
    }

    public String p() {
        return this.e;
    }

    public T k(String str) {
        this.e = str;
        return this;
    }

    public String q() {
        return this.f;
    }

    public T l(String str) {
        this.f = str;
        return this;
    }

    public String r() {
        return this.g;
    }

    public void m(String str) {
        this.g = str;
    }
}
