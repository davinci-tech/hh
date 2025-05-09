package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.d;
import com.huawei.openalliance.ad.beans.metadata.v3.CachedContent;
import java.util.List;

/* loaded from: classes5.dex */
public class AdSlot30 {

    @d(a = "h")
    private Integer adHeight;

    @d(a = "w")
    private Integer adWidth;
    private int adtype;
    private Integer amttpv;

    @a
    private Float bidFloor;
    private String bidFloorCur;
    private List<String> bpkgName;
    private Integer bsrSet;
    private List<CachedContent> cachedContents;
    private List<String> detailedCreativeTypeList;
    private int fcFlag;
    private int height;

    @a
    private List<ImpEX> impEXs;
    private Integer isSmart;
    private Integer linkedMode;
    private Integer maxCount;
    private Integer orientation;
    private String slotid;
    private Integer splashStartMode;
    private Integer splashType;
    private String templateEnginVer;
    private List<String> templateIds;
    private int test;
    private Integer totalDuration;
    private int width;

    public List<String> y() {
        return this.bpkgName;
    }

    public String x() {
        return this.bidFloorCur;
    }

    public Float w() {
        return this.bidFloor;
    }

    public int v() {
        return this.fcFlag;
    }

    public List<CachedContent> u() {
        return this.cachedContents;
    }

    public List<String> t() {
        return this.templateIds;
    }

    public String s() {
        return this.templateEnginVer;
    }

    public Integer r() {
        return this.adHeight;
    }

    public Integer q() {
        return this.adWidth;
    }

    public Integer p() {
        return this.isSmart;
    }

    public List<String> o() {
        return this.detailedCreativeTypeList;
    }

    public Integer n() {
        return this.bsrSet;
    }

    public Integer m() {
        return this.orientation;
    }

    public Integer l() {
        return this.splashStartMode;
    }

    public void k(Integer num) {
        this.adHeight = num;
    }

    public Integer k() {
        return this.splashType;
    }

    public void j(Integer num) {
        this.adWidth = num;
    }

    public Integer j() {
        return this.totalDuration;
    }

    public void i(Integer num) {
        this.isSmart = num;
    }

    public List<ImpEX> i() {
        return this.impEXs;
    }

    public void h(Integer num) {
        this.bsrSet = num;
    }

    public Integer h() {
        return this.linkedMode;
    }

    public void g(Integer num) {
        this.orientation = num;
    }

    public Integer g() {
        return this.amttpv;
    }

    public void f(Integer num) {
        this.splashStartMode = num;
    }

    public Integer f() {
        return this.maxCount;
    }

    public void e(List<String> list) {
        this.bpkgName = list;
    }

    public void e(Integer num) {
        this.splashType = num;
    }

    public int e() {
        return this.test;
    }

    public void d(List<CachedContent> list) {
        this.cachedContents = list;
    }

    public void d(Integer num) {
        this.totalDuration = num;
    }

    public int d() {
        return this.adtype;
    }

    public void c(List<String> list) {
        this.templateIds = list;
    }

    public void c(Integer num) {
        this.linkedMode = num;
    }

    public int c() {
        return this.height;
    }

    public void b(List<String> list) {
        this.detailedCreativeTypeList = list;
    }

    public void b(String str) {
        this.bidFloorCur = str;
    }

    public void b(Integer num) {
        this.amttpv = num;
    }

    public int b() {
        return this.width;
    }

    public void a(List<ImpEX> list) {
        this.impEXs = list;
    }

    public void a(String str) {
        this.templateEnginVer = str;
    }

    public void a(Integer num) {
        this.maxCount = num;
    }

    public void a(int i) {
        this.fcFlag = i;
    }

    public void a(float f) {
        this.bidFloor = Float.valueOf(f);
    }

    public String a() {
        return this.slotid;
    }

    public AdSlot30(String str, int i, int i2, int i3, boolean z) {
        this.fcFlag = 0;
        this.slotid = str;
        this.width = i;
        this.height = i2;
        this.test = z ? 1 : 0;
        this.adtype = i3;
    }

    public AdSlot30() {
        this.test = 0;
        this.adtype = 1;
        this.fcFlag = 0;
    }
}
