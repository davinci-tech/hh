package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;

/* loaded from: classes8.dex */
public final class fp extends ew<String, Integer> {
    private Context k;
    private String l;

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return f();
    }

    public fp(Context context, String str) {
        super(context, str);
        this.k = context;
        this.l = str;
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.k));
        stringBuffer.append("&userid=").append(this.l);
        return stringBuffer.toString();
    }

    private static Integer f() throws AMapException {
        return 0;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.d() + "/nearby/data/delete";
    }
}
