package com.huawei.openalliance.ad.inter.data;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class AdvertiserInfo implements Serializable, Comparable {
    private static final long serialVersionUID = -3124209648823884395L;
    private String key;
    private Integer seq;
    private String value;

    public int hashCode() {
        Integer num = this.seq;
        return (num != null ? num.hashCode() : -1) & super.hashCode();
    }

    public String getValue() {
        return this.value;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public String getKey() {
        return this.key;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AdvertiserInfo)) {
            return false;
        }
        Integer num = this.seq;
        Integer num2 = ((AdvertiserInfo) obj).seq;
        if (num == null) {
            if (num2 != null) {
                return false;
            }
        } else if (!num.equals(num2)) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return ((obj instanceof AdvertiserInfo) && ((AdvertiserInfo) obj).getSeq().intValue() <= getSeq().intValue()) ? 1 : -1;
    }

    public void b(String str) {
        this.value = str;
    }

    public void a(String str) {
        this.key = str;
    }

    public void a(Integer num) {
        this.seq = num;
    }
}
