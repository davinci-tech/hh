package com.huawei.openalliance.ad.beans.metadata;

import java.util.Objects;

/* loaded from: classes5.dex */
public class WifiInfo implements Comparable<WifiInfo> {
    private String bssid;
    private Integer encrypted;
    private Integer frequency;
    private Integer level;
    private String ssid;
    private Long time;
    private int type;
    private int wifiState;

    public int hashCode() {
        return Objects.hash(this.bssid, this.ssid, this.time);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WifiInfo wifiInfo = (WifiInfo) obj;
        return Objects.equals(this.bssid, wifiInfo.bssid) && Objects.equals(this.ssid, wifiInfo.ssid) && Objects.equals(this.time, wifiInfo.time);
    }

    public Integer a() {
        return this.level;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(WifiInfo wifiInfo) {
        if (wifiInfo == null) {
            return -1;
        }
        int i = wifiInfo.wifiState - this.wifiState;
        if (i != 0) {
            return i;
        }
        return (wifiInfo.a() != null ? wifiInfo.a().intValue() : Integer.MIN_VALUE) - (a() == null ? Integer.MIN_VALUE : a().intValue());
    }
}
