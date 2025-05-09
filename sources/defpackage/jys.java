package defpackage;

import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwcommonfilemgr.bean.Priority;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class jys {
    private Priority aa;
    private ParcelFileDescriptor ab;
    private String ac;
    private String ad;
    private long af;
    private long ah;
    private int b;
    private ByteBuffer d;
    private ByteBuffer e;
    private String g;
    private String i;
    private String j;
    private int k;
    private int l;
    private int o;
    private int p;
    private int r;
    private int s;
    private String t;
    private int w;
    private boolean x;
    private String z;
    private String m = "";
    private boolean u = true;
    private String v = "";
    private long ag = -1;
    private int ai = 0;
    private int ae = 0;
    private int f = 0;
    private IBaseCallback h = null;
    private IOTAResultAIDLCallback n = null;

    /* renamed from: a, reason: collision with root package name */
    private IAppTransferFileResultAIDLCallback f14204a = null;
    private ITransferSleepAndDFXFileCallback q = null;
    private IBaseCommonCallback y = null;
    private boolean c = false;
    private int an = -1;

    public IBaseCallback c() {
        return this.h;
    }

    public void c(IBaseCallback iBaseCallback) {
        this.h = iBaseCallback;
    }

    public int q() {
        return this.r;
    }

    public void g(int i) {
        this.r = i;
    }

    public long ai() {
        return this.ag;
    }

    public void c(long j) {
        this.ag = j;
    }

    public IOTAResultAIDLCallback j() {
        return this.n;
    }

    public void b(IOTAResultAIDLCallback iOTAResultAIDLCallback) {
        this.n = iOTAResultAIDLCallback;
    }

    public IAppTransferFileResultAIDLCallback ah() {
        return this.f14204a;
    }

    public void e(IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        this.f14204a = iAppTransferFileResultAIDLCallback;
    }

    public ITransferSleepAndDFXFileCallback t() {
        return this.q;
    }

    public void b(ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        this.q = iTransferSleepAndDFXFileCallback;
    }

    public int h() {
        return this.o;
    }

    public void b(int i) {
        this.o = i;
    }

    public String n() {
        return this.m;
    }

    public void e(String str) {
        this.m = str;
    }

    public int s() {
        return this.w;
    }

    public void h(int i) {
        this.w = i;
    }

    public String l() {
        return this.t;
    }

    public void a(String str) {
        this.t = str;
    }

    public boolean al() {
        return this.u;
    }

    public void c(boolean z) {
        this.u = z;
    }

    public String p() {
        return this.v;
    }

    public void h(String str) {
        this.v = str;
    }

    public int k() {
        return this.l;
    }

    public void d(int i) {
        this.l = i;
    }

    public int o() {
        return this.k;
    }

    public void c(int i) {
        this.k = i;
    }

    public int m() {
        return this.p;
    }

    public void f(int i) {
        this.p = i;
    }

    public int r() {
        return this.s;
    }

    public void i(int i) {
        this.s = i;
    }

    public ByteBuffer e() {
        return this.d;
    }

    public void e(ByteBuffer byteBuffer) {
        this.d = byteBuffer;
    }

    public ByteBuffer a() {
        return this.e;
    }

    public void a(ByteBuffer byteBuffer) {
        this.e = byteBuffer;
    }

    public IBaseCommonCallback w() {
        return this.y;
    }

    public void b(IBaseCommonCallback iBaseCommonCallback) {
        this.y = iBaseCommonCallback;
    }

    public int aa() {
        return this.ai;
    }

    public void n(int i) {
        this.ai = i;
    }

    public int z() {
        return this.ae;
    }

    public void j(int i) {
        this.ae = i;
    }

    public int g() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public String ab() {
        return this.z;
    }

    public void i(String str) {
        this.z = str;
    }

    public String i() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public String b() {
        return this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public int d() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public Priority v() {
        return this.aa;
    }

    public void c(Priority priority) {
        this.aa = priority;
    }

    public long ac() {
        return this.af;
    }

    public void d(long j) {
        this.af = j;
    }

    public long ad() {
        return this.ah;
    }

    public void a(long j) {
        this.ah = j;
    }

    public ParcelFileDescriptor bLe_() {
        return this.ab;
    }

    public void bLf_(ParcelFileDescriptor parcelFileDescriptor) {
        this.ab = parcelFileDescriptor;
    }

    public String u() {
        return this.ac;
    }

    public void f(String str) {
        this.ac = str;
    }

    public String y() {
        return this.ad;
    }

    public void j(String str) {
        this.ad = str;
    }

    public String f() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public boolean ae() {
        return this.x;
    }

    public void a(boolean z) {
        this.x = z;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof jys)) {
            return false;
        }
        jys jysVar = (jys) obj;
        return s() == jysVar.s() && TextUtils.equals(ab(), jysVar.ab()) && TextUtils.equals(i(), jysVar.i()) && TextUtils.equals(n(), jysVar.n());
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean ag() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public int af() {
        return this.an;
    }

    public void m(int i) {
        this.an = i;
    }
}
