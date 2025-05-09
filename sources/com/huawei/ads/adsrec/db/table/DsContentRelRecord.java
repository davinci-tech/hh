package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;

@DataKeep
/* loaded from: classes2.dex */
public class DsContentRelRecord extends RecordBean {
    private double adIntentScore;
    private double adMergedScore;
    private double adScore;
    private int adType;
    private String aes128EncryptEcpm;
    private String clientRequestId;
    private String contentId;
    private String dayIntentId1st;
    private String dayIntentId2nd;
    private String dspId;
    private String hostPkgName;
    private String industryId1st;
    private String industryId2nd;
    private int interactionType;
    private String mediaType;
    private String reqIntentId1st;
    private String reqIntentId2nd;
    private long updateTime;

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return 1296000000L;
    }

    public String toString() {
        return "DsContentRelRecord{contentId='" + this.contentId + "', clientRequestId='" + this.clientRequestId + "', updateTime='" + this.updateTime + "', mediaType='" + this.mediaType + "', adScore=" + this.adScore + ", adIntentScore=" + this.adIntentScore + ", adMergedScore='" + this.adMergedScore + '}';
    }

    public String s() {
        return this.reqIntentId2nd;
    }

    public String r() {
        return this.reqIntentId1st;
    }

    public String n() {
        return this.mediaType;
    }

    public int k() {
        return this.interactionType;
    }

    public String m() {
        return this.industryId2nd;
    }

    public void n(String str) {
        this.reqIntentId2nd = str;
    }

    public String l() {
        return this.industryId1st;
    }

    public void o(String str) {
        this.reqIntentId1st = str;
    }

    public String o() {
        return this.hostPkgName;
    }

    public void j(String str) {
        this.mediaType = str;
    }

    public String j() {
        return this.dspId;
    }

    public void h(String str) {
        this.industryId2nd = str;
    }

    public String g() {
        return this.dayIntentId2nd;
    }

    public void g(String str) {
        this.industryId1st = str;
    }

    public String i() {
        return this.dayIntentId1st;
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "updateTime<?";
    }

    public void i(String str) {
        this.hostPkgName = str;
    }

    public String f() {
        return this.contentId;
    }

    public void f(String str) {
        this.dspId = str;
    }

    public String h() {
        return this.clientRequestId;
    }

    public void a(String str) {
        this.dayIntentId2nd = str;
    }

    public String c() {
        return this.aes128EncryptEcpm;
    }

    public void b(String str) {
        this.dayIntentId1st = str;
    }

    public int a() {
        return this.adType;
    }

    public void d(String str) {
        this.contentId = str;
    }

    public void d(double d) {
        this.adScore = d;
    }

    public double e() {
        return this.adScore;
    }

    public void c(String str) {
        this.clientRequestId = str;
    }

    public void a(int i) {
        this.interactionType = i;
    }

    public void b(double d) {
        this.adMergedScore = d;
    }

    public double d() {
        return this.adMergedScore;
    }

    public void e(String str) {
        this.aes128EncryptEcpm = str;
    }

    public void d(long j) {
        this.updateTime = j;
    }

    public void d(int i) {
        this.adType = i;
    }

    public void a(double d) {
        this.adIntentScore = d;
    }

    public double b() {
        return this.adIntentScore;
    }
}
