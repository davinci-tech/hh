package com.huawei.openalliance.ad.beans.vast;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class VastContent {
    private String adSystem;
    private String adSystemVersion;
    private String adTile;
    private String advertiser;
    private String description;
    private String id;
    private String version;
    private List<Impression> impressions = new ArrayList();
    private List<Creative> creatives = new ArrayList();
    private Boolean isWrapper = false;

    public Boolean g() {
        return this.isWrapper;
    }

    public void f(String str) {
        this.advertiser = str;
    }

    public String f() {
        return this.advertiser;
    }

    public void e(String str) {
        this.description = str;
    }

    public List<Creative> e() {
        return this.creatives;
    }

    public void d(String str) {
        this.adTile = str;
    }

    public List<Impression> d() {
        return this.impressions;
    }

    public void c(String str) {
        this.adSystemVersion = str;
    }

    public String c() {
        return this.description;
    }

    public void b(String str) {
        this.adSystem = str;
    }

    public String b() {
        return this.adTile;
    }

    public void a(List<Creative> list) {
        this.creatives = list;
    }

    public void a(String str) {
        this.id = str;
    }

    public void a(Impression impression) {
        if (this.impressions.contains(impression)) {
            return;
        }
        this.impressions.add(impression);
    }

    public String a() {
        return this.id;
    }
}
