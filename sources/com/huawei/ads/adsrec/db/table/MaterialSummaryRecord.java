package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;
import defpackage.uv;
import defpackage.uz;
import defpackage.vq;
import org.json.JSONObject;

@DataKeep
/* loaded from: classes2.dex */
public class MaterialSummaryRecord extends RecordBean {
    private String adType;
    private String contentId;
    private int height;
    private String pkgName;
    private String slotId;
    private long updateTime;
    private String urls;
    private int width;

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return 2592000000L;
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "updateTime<?";
    }

    public int i() {
        return this.width;
    }

    public String f() {
        return this.urls;
    }

    public String e() {
        return this.slotId;
    }

    public String d() {
        return this.pkgName;
    }

    public int c() {
        return this.height;
    }

    public String a() {
        return this.contentId;
    }

    public String b() {
        return this.adType;
    }

    public MaterialSummaryRecord(String str, String str2, String str3, String str4, JSONObject jSONObject) {
        this.pkgName = str;
        this.adType = str2;
        this.slotId = str3;
        this.contentId = str4;
        vq e = jSONObject != null ? "3".equals(jSONObject.optString("apiVer")) ? uz.e(jSONObject) : uv.a(jSONObject) : null;
        if (e != null) {
            this.width = e.c();
            this.height = e.b();
            this.urls = e.a();
        }
        this.updateTime = System.currentTimeMillis();
    }

    public MaterialSummaryRecord() {
    }
}
