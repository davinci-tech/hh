package com.alipay.android.phone.mrpc.core.a;

import com.alipay.android.phone.mrpc.core.RpcException;
import defpackage.jj;
import java.lang.reflect.Type;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class d extends a {
    @Override // com.alipay.android.phone.mrpc.core.a.c
    public final Object a() {
        try {
            String str = new String(this.b);
            Thread.currentThread().getId();
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("resultStatus");
            if (i == 1000) {
                return this.f813a == String.class ? jSONObject.optString("result") : jj.b(jSONObject.optString("result"), this.f813a);
            }
            throw new RpcException(Integer.valueOf(i), jSONObject.optString("tips"));
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("response  =");
            sb.append(new String(this.b));
            sb.append(":");
            sb.append(e);
            throw new RpcException((Integer) 10, sb.toString() == null ? "" : e.getMessage());
        }
    }

    public d(Type type, byte[] bArr) {
        super(type, bArr);
    }
}
