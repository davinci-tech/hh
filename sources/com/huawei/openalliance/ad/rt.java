package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public class rt {

    /* renamed from: a, reason: collision with root package name */
    private String f7518a;
    private String c;
    private String d;
    private Long j;
    private ContentRecord k;
    private HttpConnection l;
    private long m;
    private Long n;
    private String o;
    private Integer s;
    private long b = 53687091200L;
    private int e = 10000;
    private int f = 10000;
    private boolean g = true;
    private boolean h = false;
    private boolean i = false;
    private String p = "normal";
    private boolean q = false;
    private boolean r = false;

    public Integer s() {
        return this.s;
    }

    public boolean r() {
        return this.r;
    }

    public boolean q() {
        return this.q;
    }

    public String p() {
        return this.p;
    }

    public String o() {
        return this.o;
    }

    public Long n() {
        return this.n;
    }

    public long m() {
        return this.m;
    }

    public HttpConnection l() {
        return this.l;
    }

    public boolean k() {
        return this.h;
    }

    public ContentRecord j() {
        return this.k;
    }

    public Long i() {
        return this.j;
    }

    public boolean h() {
        return this.g;
    }

    public String g() {
        return this.f7518a;
    }

    public long f() {
        return this.b;
    }

    public int e() {
        return this.f;
    }

    public void d(boolean z) {
        this.r = z;
    }

    public void d(String str) {
        this.p = str;
    }

    public int d() {
        return this.e;
    }

    public void c(boolean z) {
        this.h = z;
    }

    public void c(String str) {
        this.f7518a = str;
    }

    public void c(int i) {
        this.b = i * 1024;
    }

    public String c() {
        return this.d;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(Long l) {
        this.n = l;
    }

    public void b(long j) {
        this.m = j;
    }

    public void b(int i) {
        this.f = i;
    }

    public String b() {
        return this.c;
    }

    public boolean a() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(Long l) {
        this.j = l;
    }

    public void a(Integer num) {
        this.s = num;
    }

    public void a(ContentRecord contentRecord) {
        this.k = contentRecord;
    }

    public void a(HttpConnection httpConnection) {
        this.l = httpConnection;
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(int i) {
        this.e = i;
    }
}
