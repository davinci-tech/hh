package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class jce {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f13736a;
    private int e = 200;
    private int c = 0;

    public byte[] b() {
        return this.f13736a;
    }

    public void b(byte[] bArr) {
        if (bArr != null) {
            this.f13736a = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public int d() {
        return this.e;
    }

    public int a() {
        return this.c;
    }

    public String toString() {
        try {
            return "NSPResponse [status=" + this.e + ", code=" + this.c + ", content=" + Arrays.toString(this.f13736a) + " == " + new String(this.f13736a, "UTF-8") + "]";
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("UIME_NspResponse", "toString Exception e = ", e.getMessage());
            return "";
        }
    }
}
