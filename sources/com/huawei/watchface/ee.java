package com.huawei.watchface;

import android.util.SparseArray;
import com.huawei.watchface.mvp.model.webview.JsInteractAddition;
import com.huawei.watchface.utils.IntegerUtils;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class ee extends BaseEvent<ee> {

    /* renamed from: a, reason: collision with root package name */
    private static final SparseArray<String> f11004a;
    private LinkedHashMap<String, String> h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f11004a = sparseArray;
        sparseArray.put(1, "下载成功");
        sparseArray.put(-15, "下载文件写入异常");
        sparseArray.put(-10, "取消下载");
        sparseArray.put(-11, "文件的hash校验失败");
        sparseArray.put(-12, "文件license解密失败");
        sparseArray.put(-6, "文件解压缩失败");
        sparseArray.put(-9, "APP存储空间不足");
        sparseArray.put(-3, "文件下载接口返回失败");
        sparseArray.put(-14, "文件创建异常，或文件路径校验失败");
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return s();
    }

    private LinkedHashMap<String, String> s() {
        if (this.h == null) {
            this.h = new LinkedHashMap<>();
        }
        this.h.put(JsInteractAddition.HI_TOP_ID, q());
        this.h.put("version", r());
        this.h.put("startts", String.valueOf(m()));
        this.h.put("endts", String.valueOf(n()));
        this.h.put("totalTime", String.valueOf(o()));
        this.h.put("errorCode", p());
        this.h.put("protocal", g());
        this.h.put("hashcode", h());
        this.h.put("downUrl", i());
        this.h.put("title", j());
        this.h.put("cnTitle", k());
        this.h.put("datalen", e());
        this.h.put("useType", d());
        this.h.put("downloadResourceType", l());
        this.h.put("productId", c());
        this.h.put(BaseEvent.KEY_DESCINFO, n(p()));
        return this.h;
    }

    private String n(String str) {
        StringBuilder sb = new StringBuilder();
        if (f() != null) {
            sb.append(f());
            sb.append("_");
        }
        sb.append(f11004a.get(IntegerUtils.a(str)));
        return sb.toString();
    }

    public String c() {
        return this.r;
    }

    public void a(String str) {
        this.r = str;
    }

    public String d() {
        return this.p;
    }

    public void b(String str) {
        this.p = str;
    }

    public String e() {
        return this.o;
    }

    public void c(String str) {
        this.o = str;
    }

    public String f() {
        return this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public String g() {
        return this.j;
    }

    public void e(String str) {
        this.j = str;
    }

    public String h() {
        return this.k;
    }

    public void f(String str) {
        this.k = str;
    }

    public String i() {
        return this.l;
    }

    public void g(String str) {
        this.l = str;
    }

    public String j() {
        return this.m;
    }

    public void h(String str) {
        this.m = str;
    }

    public String k() {
        return this.n;
    }

    public void i(String str) {
        this.n = str;
    }

    public String l() {
        return this.q;
    }

    public void j(String str) {
        this.q = str;
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_DOWNLOAD";
    }
}
