package com.huawei.pluginachievement.manager.model;

/* loaded from: classes6.dex */
public class KakaRecord {
    private long date;
    private int description;
    private int kakaNum;
    private long occurDate;

    public KakaRecord() {
        this(0L, 0, 0, 0L);
    }

    public KakaRecord(long j, int i, int i2, long j2) {
        this.date = j;
        this.kakaNum = i2;
        this.description = i;
        this.occurDate = j2;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public int getDescription() {
        return this.description;
    }

    public void setDescription(int i) {
        this.description = i;
    }

    public int getKakaNum() {
        return this.kakaNum;
    }

    public void setKakaNum(int i) {
        this.kakaNum = i;
    }

    public void setOccurDate(long j) {
        this.occurDate = j;
    }

    public long getOccurDate() {
        return this.occurDate;
    }

    public String toString() {
        return "KakaRecord{date=" + this.date + ", description=" + this.description + ", kakaNum=" + this.kakaNum + ", occurDate=" + this.occurDate + '}';
    }
}
