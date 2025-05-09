package defpackage;

import java.io.Serializable;
import okio.Utf8;

/* loaded from: classes3.dex */
public class dba implements Serializable {
    private static final long serialVersionUID = -204623211515444595L;

    /* renamed from: a, reason: collision with root package name */
    private boolean f11575a = true;
    private dbb b;
    private int c;
    private int d;
    private int e;
    private int j;

    public dba(byte[] bArr) {
        a(bArr);
    }

    private void a(byte[] bArr) {
        byte b;
        Boolean b2;
        Boolean b3;
        if (bArr == null || bArr.length <= 9 || (b2 = day.b((b = bArr[2]), 7)) == null || (b3 = day.b(b, 6)) == null) {
            return;
        }
        if (b2.booleanValue()) {
            this.c = ((bArr[3] & 255) << 8) + (bArr[4] & 255);
            return;
        }
        byte b4 = (byte) (b & Utf8.REPLACEMENT_BYTE);
        byte b5 = bArr[5];
        byte b6 = bArr[6];
        byte b7 = bArr[7];
        byte b8 = bArr[8];
        byte b9 = bArr[9];
        byte b10 = bArr[10];
        if (b4 != 0) {
            this.b = new dbb(b4);
            return;
        }
        if (b3.booleanValue()) {
            this.j = ((b5 & 255) << 8) + (b6 & 255);
            this.d = ((b7 & 255) << 8) + (b8 & 255);
            this.e = ((b9 & 255) << 8) + (b10 & 255);
            this.f11575a = false;
            return;
        }
        this.c = ((bArr[3] & 255) << 8) + (bArr[4] & 255);
    }

    public int d() {
        return this.j;
    }

    public int e() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public int b() {
        return this.c;
    }

    public boolean f() {
        return this.f11575a;
    }

    public dbb a() {
        return this.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("pressure:" + this.c).append(System.lineSeparator());
        stringBuffer.append("dbpValue:" + this.d).append(System.lineSeparator());
        stringBuffer.append("hhValue:" + this.e).append(System.lineSeparator());
        stringBuffer.append("sbpValue:" + this.j).append(System.lineSeparator());
        return stringBuffer.toString();
    }
}
