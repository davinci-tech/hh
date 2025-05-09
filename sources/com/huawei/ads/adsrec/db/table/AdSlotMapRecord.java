package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import org.json.JSONObject;

@DataKeep
/* loaded from: classes2.dex */
public class AdSlotMapRecord extends RecordBean {
    private int adClicked;
    private int adShown;
    private String content;
    private String contentId;
    private long endTime;
    private String pkgName;
    private String slotId;
    private long startTime;

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL;
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "endTime<?";
    }

    public String c() {
        return this.slotId;
    }

    public String a() {
        return this.pkgName;
    }

    public void b(int i) {
        this.adShown = i;
    }

    public String b() {
        return this.contentId;
    }

    public void e(int i) {
        this.adClicked = i;
    }

    public String e() {
        return this.content;
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.contentId = jSONObject.optString("contentid");
            this.content = jSONObject.toString();
            this.startTime = jSONObject.optLong("starttime");
            this.endTime = jSONObject.optLong("endtime");
        }
    }

    public AdSlotMapRecord(String str, String str2, JSONObject jSONObject) {
        this.pkgName = str;
        this.slotId = str2;
        a(jSONObject);
    }

    public AdSlotMapRecord() {
    }
}
