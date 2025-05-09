package com.alipay.android.phone.mrpc.core.a;

import com.alipay.android.phone.mrpc.core.RpcException;
import defpackage.jh;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* loaded from: classes7.dex */
public final class e extends b {
    public int c;
    public Object d;

    @Override // com.alipay.android.phone.mrpc.core.a.f
    public final byte[] a() {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.d != null) {
                arrayList.add(new BasicNameValuePair("extParam", jh.b(this.d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.f814a));
            StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            arrayList.add(new BasicNameValuePair("id", sb.toString()));
            Objects.toString(this.b);
            arrayList.add(new BasicNameValuePair("requestData", this.b == null ? "[]" : jh.b(this.b)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("request  =");
            sb2.append(this.b);
            sb2.append(":");
            sb2.append(e);
            throw new RpcException(9, sb2.toString() == null ? "" : e.getMessage(), e);
        }
    }

    @Override // com.alipay.android.phone.mrpc.core.a.f
    public final void a(Object obj) {
        this.d = obj;
    }

    public e(int i, String str, Object obj) {
        super(str, obj);
        this.c = i;
    }
}
