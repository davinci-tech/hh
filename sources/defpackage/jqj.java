package defpackage;

import android.os.ParcelFileDescriptor;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import java.util.List;

/* loaded from: classes.dex */
public class jqj {

    /* renamed from: a, reason: collision with root package name */
    private String f14025a;
    private List<Integer> b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private ParcelFileDescriptor h = null;
    private int i;
    private int j;
    private long k;
    private TransferFileReqType l;
    private String m;
    private int n;
    private boolean o;
    private int[] p;
    private String r;
    private String s;

    public TransferFileReqType o() {
        return this.l;
    }

    public void c(TransferFileReqType transferFileReqType) {
        this.l = transferFileReqType;
    }

    public List<Integer> a() {
        return this.b;
    }

    public void e(List<Integer> list) {
        this.b = list;
    }

    public String j() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public int n() {
        return this.n;
    }

    public void d(int i) {
        this.n = i;
    }

    public String h() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public long m() {
        return this.k;
    }

    public void e(long j) {
        this.k = j;
    }

    public String k() {
        return this.m;
    }

    public void j(String str) {
        this.m = str;
    }

    public boolean s() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public int[] r() {
        int[] iArr = this.p;
        if (iArr != null) {
            return (int[]) iArr.clone();
        }
        return null;
    }

    public void e(int[] iArr) {
        if (iArr != null) {
            this.p = (int[]) iArr.clone();
        }
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public int f() {
        return this.j;
    }

    public void b(int i) {
        this.j = i;
    }

    public ParcelFileDescriptor bIQ_() {
        return this.h;
    }

    public void bIR_(ParcelFileDescriptor parcelFileDescriptor) {
        this.h = parcelFileDescriptor;
    }

    public int g() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public String q() {
        return this.s;
    }

    public void h(String str) {
        this.s = str;
    }

    public String e() {
        return this.f14025a;
    }

    public void d(String str) {
        this.f14025a = str;
    }

    public String l() {
        return this.r;
    }

    public void g(String str) {
        this.r = str;
    }

    public String c() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }
}
