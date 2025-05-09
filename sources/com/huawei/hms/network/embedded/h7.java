package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class h7 extends u7 {
    public static final o7 c = o7.a(RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);

    /* renamed from: a, reason: collision with root package name */
    public final List<String> f5291a;
    public final List<String> b;

    @Override // com.huawei.hms.network.embedded.u7
    public void writeTo(cb cbVar) throws IOException {
        a(cbVar, false);
    }

    public String d(int i) {
        return m7.a(b(i), true);
    }

    @Override // com.huawei.hms.network.embedded.u7
    public o7 contentType() {
        return c;
    }

    @Override // com.huawei.hms.network.embedded.u7
    public long contentLength() {
        return a(null, true);
    }

    public String c(int i) {
        return m7.a(a(i), true);
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final List<String> f5292a;
        public final List<String> b;

        @Nullable
        public final Charset c;

        public a b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f5292a.add(m7.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
            this.b.add(m7.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.c));
            return this;
        }

        public h7 a() {
            return new h7(this.f5292a, this.b);
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f5292a.add(m7.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
            this.b.add(m7.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.c));
            return this;
        }

        public a(@Nullable Charset charset) {
            this.f5292a = new ArrayList();
            this.b = new ArrayList();
            this.c = charset;
        }

        public a() {
            this(null);
        }
    }

    public String b(int i) {
        return this.b.get(i);
    }

    public String a(int i) {
        return this.f5291a.get(i);
    }

    public int a() {
        return this.f5291a.size();
    }

    private long a(@Nullable cb cbVar, boolean z) {
        bb bbVar = z ? new bb() : cbVar.a();
        int size = this.f5291a.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                bbVar.writeByte(38);
            }
            bbVar.a(this.f5291a.get(i));
            bbVar.writeByte(61);
            bbVar.a(this.b.get(i));
        }
        if (!z) {
            return 0L;
        }
        long B = bbVar.B();
        bbVar.s();
        return B;
    }

    public h7(List<String> list, List<String> list2) {
        this.f5291a = f8.a(list);
        this.b = f8.a(list2);
    }
}
