package com.huawei.ads.adsrec.bean;

import com.huawei.ads.adsrec.IUtilCallback;
import com.huawei.ads.adsrec.db.table.DsContentRelRecord;
import com.huawei.ads.fund.anno.DataKeep;
import defpackage.uw;
import java.util.UUID;

@DataKeep
/* loaded from: classes8.dex */
public class SampleData {
    private String adIntentScore;
    private String adMergedScore;
    private String adScore;
    private int adType;
    private String bagId;
    private String cost7d;
    private String dayIntentId1st;
    private String dayIntentId2nd;
    private String dspId;
    private String ecpm;
    private String industryId1st;
    private String industryId2nd;
    private String interactionType;
    private String mediaType;
    private String reqIntentId1st;
    private String reqIntentId2nd;

    public SampleData(DsContentRelRecord dsContentRelRecord, double d) {
        IUtilCallback d2 = uw.d();
        if (d2 != null) {
            this.ecpm = d2.aes128Decrypt(dsContentRelRecord.c());
        }
        this.adScore = String.valueOf(dsContentRelRecord.e());
        this.adIntentScore = String.valueOf(dsContentRelRecord.b());
        this.adMergedScore = String.valueOf(dsContentRelRecord.d());
        this.cost7d = String.valueOf(d);
        this.interactionType = String.valueOf(dsContentRelRecord.k());
        this.adType = dsContentRelRecord.a();
        this.dspId = dsContentRelRecord.j();
        this.industryId1st = dsContentRelRecord.l();
        this.industryId2nd = dsContentRelRecord.m();
        this.reqIntentId1st = dsContentRelRecord.r();
        this.reqIntentId2nd = dsContentRelRecord.s();
        this.dayIntentId1st = dsContentRelRecord.i();
        this.dayIntentId2nd = dsContentRelRecord.g();
        this.mediaType = dsContentRelRecord.n();
        this.bagId = UUID.randomUUID().toString();
    }

    public SampleData() {
    }
}
