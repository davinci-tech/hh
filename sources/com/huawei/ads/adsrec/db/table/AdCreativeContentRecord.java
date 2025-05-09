package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.vj;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@DataKeep
/* loaded from: classes2.dex */
public class AdCreativeContentRecord extends RecordBean {
    private static final String TAG = "AdCreativeContentRecord";
    private String basicTargetTag;
    private String contentId;
    private String creativeLabel;
    private String deviceParam;
    String encryptEcpm;
    private String industryId1st;
    private String industryId2nd;
    private String metaData;
    private String monitor;
    private String priceType;
    private String tradeMode;
    private long uptime;

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return 2592000000L;
    }

    public String h() {
        return this.tradeMode;
    }

    public String j() {
        return this.monitor;
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "uptime<?";
    }

    public String i() {
        return this.metaData;
    }

    public String g() {
        return this.industryId2nd;
    }

    public String d() {
        return this.industryId1st;
    }

    public String c() {
        return this.encryptEcpm;
    }

    public String b() {
        return this.creativeLabel;
    }

    public String e() {
        return this.contentId;
    }

    public String a() {
        return this.basicTargetTag;
    }

    private void a(JSONObject jSONObject) {
        JSONArray e;
        String str;
        JSONObject a2 = vj.a(jSONObject.optString("creativeLabel"));
        jSONObject.optString("creativeLabel");
        if (a2 == null || (e = vj.e(a2.optString("creativeCategory"))) == null) {
            return;
        }
        String str2 = null;
        try {
            str = e.getJSONObject(0).optString("category");
        } catch (JSONException e2) {
            HiAdLog.w(TAG, "getCategory exception %s", e2.getClass().getSimpleName());
            str = null;
        }
        this.industryId1st = (str == null || str.length() < 4) ? null : str.substring(0, 4);
        if (str != null && str.length() >= 8) {
            str2 = str.substring(0, 8);
        }
        this.industryId2nd = str2;
    }

    public AdCreativeContentRecord(JSONObject jSONObject) {
        this.uptime = System.currentTimeMillis();
        if (jSONObject != null) {
            this.contentId = jSONObject.optString("contentid");
            this.metaData = jSONObject.optString("metaData");
            this.monitor = jSONObject.optString(MapKeyNames.THIRD_MONITORS);
            JSONObject optJSONObject = jSONObject.optJSONObject("deviceAiParam");
            if (optJSONObject != null) {
                this.tradeMode = optJSONObject.optString("tradeMode");
                this.priceType = optJSONObject.optString("priceType");
                this.deviceParam = optJSONObject.toString();
                a(optJSONObject);
                this.creativeLabel = optJSONObject.optString("creativeLabel");
                this.basicTargetTag = optJSONObject.optString("basicTargetTag");
                this.encryptEcpm = optJSONObject.optString("encryptEcpm");
            }
            this.uptime = System.currentTimeMillis();
        }
    }

    public AdCreativeContentRecord() {
        this.uptime = System.currentTimeMillis();
    }
}
