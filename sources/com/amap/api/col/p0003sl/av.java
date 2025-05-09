package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.bn;
import com.amap.api.col.p0003sl.bw;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import java.io.File;

/* loaded from: classes2.dex */
public final class av extends OfflineMapCity implements be, bv {
    public static final Parcelable.Creator<av> CREATOR = new Parcelable.Creator<av>() { // from class: com.amap.api.col.3sl.av.2
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ av createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ av[] newArray(int i) {
            return a(i);
        }

        private static av a(Parcel parcel) {
            return new av(parcel);
        }

        private static av[] a(int i) {
            return new av[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    public final bz f904a;
    public final bz b;
    public final bz c;
    public final bz d;
    public final bz e;
    public final bz f;
    public final bz g;
    public final bz h;
    public final bz i;
    public final bz j;
    public final bz k;
    bz l;
    Context m;
    boolean n;
    private String o;
    private String p;
    private long q;

    @Override // com.amap.api.maps.offlinemap.OfflineMapCity, com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final void a(String str) {
        this.p = str;
    }

    public final String a() {
        return this.p;
    }

    @Override // com.amap.api.col.p0003sl.be
    public final String b() {
        return getUrl();
    }

    public av(Context context, OfflineMapCity offlineMapCity) {
        this(context, offlineMapCity.getState());
        setCity(offlineMapCity.getCity());
        setUrl(offlineMapCity.getUrl());
        setState(offlineMapCity.getState());
        setCompleteCode(offlineMapCity.getcompleteCode());
        setAdcode(offlineMapCity.getAdcode());
        setVersion(offlineMapCity.getVersion());
        setSize(offlineMapCity.getSize());
        setCode(offlineMapCity.getCode());
        setJianpin(offlineMapCity.getJianpin());
        setPinyin(offlineMapCity.getPinyin());
        s();
    }

    private av(Context context, int i) {
        this.f904a = new cb(this);
        this.b = new ci(this);
        this.c = new ce(this);
        this.d = new cg(this);
        this.e = new ch(this);
        this.f = new ca(this);
        this.g = new cf(this);
        this.h = new cc(-1, this);
        this.i = new cc(101, this);
        this.j = new cc(102, this);
        this.k = new cc(103, this);
        this.o = null;
        this.p = "";
        this.n = false;
        this.q = 0L;
        this.m = context;
        a(i);
    }

    public final void a(int i) {
        if (i == -1) {
            this.l = this.h;
        } else if (i == 0) {
            this.l = this.c;
        } else if (i == 1) {
            this.l = this.e;
        } else if (i == 2) {
            this.l = this.b;
        } else if (i == 3) {
            this.l = this.d;
        } else if (i == 4) {
            this.l = this.f;
        } else if (i == 6) {
            this.l = this.f904a;
        } else if (i == 7) {
            this.l = this.g;
        } else {
            switch (i) {
                case 101:
                    this.l = this.i;
                    break;
                case 102:
                    this.l = this.j;
                    break;
                case 103:
                    this.l = this.k;
                    break;
                default:
                    if (i < 0) {
                        this.l = this.h;
                        break;
                    }
                    break;
            }
        }
        setState(i);
    }

    public final void a(bz bzVar) {
        this.l = bzVar;
        setState(bzVar.b());
    }

    public final bz c() {
        return this.l;
    }

    public final void d() {
        aw a2 = aw.a(this.m);
        if (a2 != null) {
            a2.c(this);
        }
    }

    public final void e() {
        aw a2 = aw.a(this.m);
        if (a2 != null) {
            a2.e(this);
            d();
        }
    }

    public final void f() {
        c().b();
        if (this.l.equals(this.d)) {
            this.l.d();
            return;
        }
        if (this.l.equals(this.c)) {
            this.l.e();
            return;
        }
        if (this.l.equals(this.g) || this.l.equals(this.h)) {
            z();
            this.n = true;
        } else if (this.l.equals(this.j) || this.l.equals(this.i) || this.l.a(this.k)) {
            this.l.c();
        } else {
            c().h();
        }
    }

    public final void g() {
        this.l.e();
    }

    public final void h() {
        this.l.a(this.k.b());
    }

    public final void i() {
        this.l.a();
        if (this.n) {
            this.l.h();
        }
        this.n = false;
    }

    public final void j() {
        this.l.equals(this.f);
        this.l.f();
    }

    private void z() {
        aw a2 = aw.a(this.m);
        if (a2 != null) {
            a2.a(this);
        }
    }

    public final void k() {
        aw a2 = aw.a(this.m);
        if (a2 != null) {
            a2.b(this);
        }
    }

    public final void l() {
        aw a2 = aw.a(this.m);
        if (a2 != null) {
            a2.d(this);
        }
    }

    @Override // com.amap.api.col.p0003sl.bw
    public final void m() {
        this.q = 0L;
        this.l.equals(this.b);
        this.l.c();
    }

    @Override // com.amap.api.col.p0003sl.bw
    public final void a(long j, long j2) {
        int i = (int) ((j2 * 100) / j);
        if (i != getcompleteCode()) {
            setCompleteCode(i);
            d();
        }
    }

    @Override // com.amap.api.col.p0003sl.bw
    public final void n() {
        this.l.equals(this.c);
        this.l.g();
    }

    /* renamed from: com.amap.api.col.3sl.av$3, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f906a;

        static {
            int[] iArr = new int[bw.a.values().length];
            f906a = iArr;
            try {
                iArr[bw.a.amap_exception.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f906a[bw.a.file_io_exception.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f906a[bw.a.network_exception.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amap.api.col.p0003sl.bw
    public final void a(bw.a aVar) {
        int b;
        int i = AnonymousClass3.f906a[aVar.ordinal()];
        if (i == 1) {
            b = this.j.b();
        } else if (i == 2) {
            b = this.k.b();
        } else {
            b = i != 3 ? 6 : this.i.b();
        }
        if (this.l.equals(this.c) || this.l.equals(this.b)) {
            this.l.a(b);
        }
    }

    @Override // com.amap.api.col.p0003sl.bw
    public final void o() {
        e();
    }

    @Override // com.amap.api.col.p0003sl.bo
    public final void p() {
        this.q = 0L;
        setCompleteCode(0);
        this.l.equals(this.e);
        this.l.c();
    }

    @Override // com.amap.api.col.p0003sl.bo
    public final void q() {
        this.l.equals(this.e);
        this.l.a(this.h.b());
    }

    @Override // com.amap.api.col.p0003sl.bo
    public final void a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.q > 500) {
            int i = (int) j;
            if (i > getcompleteCode()) {
                setCompleteCode(i);
                d();
            }
            this.q = currentTimeMillis;
        }
    }

    @Override // com.amap.api.col.p0003sl.bo
    public final void b(String str) {
        this.l.equals(this.e);
        this.p = str;
        String A = A();
        String B = B();
        if (TextUtils.isEmpty(A) || TextUtils.isEmpty(B)) {
            q();
            return;
        }
        File file = new File(B + "/");
        File file2 = new File(dv.a(this.m) + File.separator + "map/");
        File file3 = new File(dv.a(this.m));
        if (file3.exists() || file3.mkdir()) {
            if (file2.exists() || file2.mkdir()) {
                a(file, file2, A);
            }
        }
    }

    @Override // com.amap.api.col.p0003sl.bo
    public final void r() {
        e();
    }

    protected final void s() {
        String str = aw.f907a;
        String b = bt.b(getUrl());
        if (b != null) {
            this.o = str + b + ".zip.tmp";
            return;
        }
        this.o = str + getPinyin() + ".zip.tmp";
    }

    private String A() {
        if (TextUtils.isEmpty(this.o)) {
            return null;
        }
        String str = this.o;
        return str.substring(0, str.lastIndexOf("."));
    }

    private String B() {
        if (TextUtils.isEmpty(this.o)) {
            return null;
        }
        String A = A();
        return A.substring(0, A.lastIndexOf(46));
    }

    private void a(final File file, File file2, final String str) {
        new bn().a(file, file2, -1L, bt.a(file), new bn.a() { // from class: com.amap.api.col.3sl.av.1
            @Override // com.amap.api.col.3sl.bn.a
            public final void a(float f) {
                int i = (int) ((f * 0.39d) + 60.0d);
                if (i - av.this.getcompleteCode() <= 0 || System.currentTimeMillis() - av.this.q <= 1000) {
                    return;
                }
                av.this.setCompleteCode(i);
                av.this.q = System.currentTimeMillis();
            }

            @Override // com.amap.api.col.3sl.bn.a
            public final void a() {
                try {
                    if (new File(str).delete()) {
                        bt.b(file);
                        av.this.setCompleteCode(100);
                        av.this.l.g();
                    }
                } catch (Exception unused) {
                    av.this.l.a(av.this.k.b());
                }
            }

            @Override // com.amap.api.col.3sl.bn.a
            public final void b() {
                av.this.l.a(av.this.k.b());
            }
        });
    }

    private boolean C() {
        bt.a();
        getSize();
        getcompleteCode();
        getSize();
        return false;
    }

    public final bg t() {
        setState(this.l.b());
        bg bgVar = new bg(this, this.m);
        bgVar.a(a());
        a();
        return bgVar;
    }

    @Override // com.amap.api.maps.offlinemap.OfflineMapCity, com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.p);
    }

    public av(Parcel parcel) {
        super(parcel);
        this.f904a = new cb(this);
        this.b = new ci(this);
        this.c = new ce(this);
        this.d = new cg(this);
        this.e = new ch(this);
        this.f = new ca(this);
        this.g = new cf(this);
        this.h = new cc(-1, this);
        this.i = new cc(101, this);
        this.j = new cc(102, this);
        this.k = new cc(103, this);
        this.o = null;
        this.p = "";
        this.n = false;
        this.q = 0L;
        this.p = parcel.readString();
    }

    @Override // com.amap.api.col.p0003sl.bv
    public final boolean u() {
        return C();
    }

    @Override // com.amap.api.col.p0003sl.bv
    public final String v() {
        StringBuffer stringBuffer = new StringBuffer();
        String b = bt.b(getUrl());
        if (b != null) {
            stringBuffer.append(b);
        } else {
            stringBuffer.append(getPinyin());
        }
        stringBuffer.append(".zip");
        return stringBuffer.toString();
    }

    @Override // com.amap.api.col.p0003sl.bv
    public final String w() {
        return getAdcode();
    }

    @Override // com.amap.api.col.p0003sl.bp
    public final String x() {
        return A();
    }

    @Override // com.amap.api.col.p0003sl.bp
    public final String y() {
        return B();
    }

    public final bz b(int i) {
        switch (i) {
            case 101:
                return this.i;
            case 102:
                return this.j;
            case 103:
                return this.k;
            default:
                return this.h;
        }
    }
}
