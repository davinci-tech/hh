package defpackage;

import java.util.Objects;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.AbstractFileHeader;

/* loaded from: classes7.dex */
public class usm extends AbstractFileHeader {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f17531a;
    private byte[] b;
    private int c = 0;
    private int d;
    private String e;
    private long f;
    private int i;

    public usm() {
        setSignature(HeaderSignature.CENTRAL_DIRECTORY);
    }

    public int d() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public void e(int i) {
        this.c = i;
    }

    public int b() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public void d(byte[] bArr) {
        this.f17531a = bArr;
    }

    public byte[] a() {
        return this.b;
    }

    public void b(byte[] bArr) {
        this.b = bArr;
    }

    public long e() {
        return this.f;
    }

    public void c(long j) {
        this.f = j;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String toString() {
        return getFileName();
    }

    @Override // net.lingala.zip4j.model.AbstractFileHeader
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && super.equals(obj) && b(this) == b((usm) obj);
    }

    public int hashCode() {
        return Objects.hash(getFileName(), Long.valueOf(b(this)));
    }

    private long b(usm usmVar) {
        if (usmVar.getZip64ExtendedInfo() != null) {
            return usmVar.getZip64ExtendedInfo().e();
        }
        return usmVar.e();
    }
}
