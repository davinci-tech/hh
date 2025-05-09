package defpackage;

import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.ZipHeader;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;

/* loaded from: classes7.dex */
public class use extends ZipHeader {

    /* renamed from: a, reason: collision with root package name */
    private AesKeyStrength f17527a;
    private String b;
    private AesVersion c;
    private int d;
    private CompressionMethod e;

    public use() {
        setSignature(HeaderSignature.AES_EXTRA_DATA_RECORD);
        this.d = 7;
        this.c = AesVersion.TWO;
        this.b = "AE";
        this.f17527a = AesKeyStrength.KEY_STRENGTH_256;
        this.e = CompressionMethod.DEFLATE;
    }

    public int b() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public AesVersion a() {
        return this.c;
    }

    public void a(AesVersion aesVersion) {
        this.c = aesVersion;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public AesKeyStrength d() {
        return this.f17527a;
    }

    public void c(AesKeyStrength aesKeyStrength) {
        this.f17527a = aesKeyStrength;
    }

    public CompressionMethod e() {
        return this.e;
    }

    public void d(CompressionMethod compressionMethod) {
        this.e = compressionMethod;
    }
}
