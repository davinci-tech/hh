package net.lingala.zip4j.model;

import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* loaded from: classes7.dex */
public class ZipParameters {

    /* renamed from: a, reason: collision with root package name */
    private AesVersion f15269a;
    private CompressionLevel b;
    private AesKeyStrength c;
    private String d;
    private CompressionMethod e;
    private ExcludeFileFilter f;
    private boolean g;
    private long h;
    private EncryptionMethod i;
    private long j;
    private boolean k;
    private String l;
    private long m;
    private String n;
    private boolean o;
    private String p;
    private boolean q;
    private boolean r;
    private SymbolicLinkAction s;
    private boolean t;
    private boolean y;

    public enum SymbolicLinkAction {
        INCLUDE_LINK_ONLY,
        INCLUDE_LINKED_FILE_ONLY,
        INCLUDE_LINK_AND_LINKED_FILE
    }

    public ZipParameters() {
        this.e = CompressionMethod.DEFLATE;
        this.b = CompressionLevel.NORMAL;
        this.g = false;
        this.i = EncryptionMethod.NONE;
        this.t = true;
        this.q = true;
        this.c = AesKeyStrength.KEY_STRENGTH_256;
        this.f15269a = AesVersion.TWO;
        this.o = true;
        this.m = 0L;
        this.j = -1L;
        this.y = true;
        this.k = true;
        this.s = SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY;
    }

    public ZipParameters(ZipParameters zipParameters) {
        this.e = CompressionMethod.DEFLATE;
        this.b = CompressionLevel.NORMAL;
        this.g = false;
        this.i = EncryptionMethod.NONE;
        this.t = true;
        this.q = true;
        this.c = AesKeyStrength.KEY_STRENGTH_256;
        this.f15269a = AesVersion.TWO;
        this.o = true;
        this.m = 0L;
        this.j = -1L;
        this.y = true;
        this.k = true;
        this.s = SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY;
        this.e = zipParameters.a();
        this.b = zipParameters.d();
        this.g = zipParameters.l();
        this.i = zipParameters.h();
        this.t = zipParameters.r();
        this.q = zipParameters.p();
        this.c = zipParameters.e();
        this.f15269a = zipParameters.b();
        this.o = zipParameters.q();
        this.h = zipParameters.g();
        this.d = zipParameters.c();
        this.l = zipParameters.o();
        this.m = zipParameters.n();
        this.j = zipParameters.j();
        this.y = zipParameters.u();
        this.k = zipParameters.s();
        this.p = zipParameters.k();
        this.n = zipParameters.i();
        this.s = zipParameters.m();
        this.f = zipParameters.f();
        this.r = zipParameters.t();
    }

    public CompressionMethod a() {
        return this.e;
    }

    public void e(CompressionMethod compressionMethod) {
        this.e = compressionMethod;
    }

    public boolean l() {
        return this.g;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public EncryptionMethod h() {
        return this.i;
    }

    public void d(EncryptionMethod encryptionMethod) {
        this.i = encryptionMethod;
    }

    public CompressionLevel d() {
        return this.b;
    }

    public void c(CompressionLevel compressionLevel) {
        this.b = compressionLevel;
    }

    public boolean r() {
        return this.t;
    }

    public boolean p() {
        return this.q;
    }

    public AesKeyStrength e() {
        return this.c;
    }

    public AesVersion b() {
        return this.f15269a;
    }

    public boolean q() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public long g() {
        return this.h;
    }

    public void b(long j) {
        this.h = j;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String o() {
        return this.l;
    }

    public void e(String str) {
        this.l = str;
    }

    public long n() {
        return this.m;
    }

    public void e(long j) {
        if (j < 0) {
            this.m = 0L;
        } else {
            this.m = j;
        }
    }

    public long j() {
        return this.j;
    }

    public void c(long j) {
        this.j = j;
    }

    public boolean u() {
        return this.y;
    }

    public void c(boolean z) {
        this.y = z;
    }

    public boolean s() {
        return this.k;
    }

    public String k() {
        return this.p;
    }

    public String i() {
        return this.n;
    }

    public SymbolicLinkAction m() {
        return this.s;
    }

    public ExcludeFileFilter f() {
        return this.f;
    }

    public boolean t() {
        return this.r;
    }
}
