package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;
import org.json.JSONObject;

@DataKeep
/* loaded from: classes2.dex */
public class AdContentRspRecord extends RecordBean {
    private String adContentRsp;
    private String pkgName;
    private long updateTime = System.currentTimeMillis();

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return 1296000000L;
    }

    public String toString() {
        return "AdContentRspRecord{pkgName='" + this.pkgName + "', rsp=" + this.adContentRsp + '}';
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "updateTime<?";
    }

    public String a() {
        return this.pkgName;
    }

    public String e() {
        return this.adContentRsp;
    }

    public AdContentRspRecord(String str, JSONObject jSONObject) {
        this.pkgName = str;
        if (jSONObject != null) {
            this.adContentRsp = jSONObject.toString();
        }
    }

    public AdContentRspRecord() {
    }
}
