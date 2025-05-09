package com.huawei.openalliance.ad.inter.data;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class BiddingInfo implements Serializable {
    private static final long serialVersionUID = 30721000906L;
    private String cur;
    private String lurl;
    private String nurl;

    @com.huawei.openalliance.ad.annotations.a
    private Float price;

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f7043a;

        @com.huawei.openalliance.ad.annotations.a
        private Float b;
        private String c;
        private String d;

        public String toString() {
            return "BiddingInfo{cur = " + this.f7043a + ", nurl = " + this.c + ", lurl = " + this.d + '}';
        }

        public a setLurl(String str) {
            this.d = str;
            return this;
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public BiddingInfo a() {
            return new BiddingInfo(this);
        }

        public a a(String str) {
            this.f7043a = str;
            return this;
        }

        public a a(Float f) {
            this.b = f;
            return this;
        }
    }

    public String toString() {
        return "BiddingInfo{cur = " + this.cur + ", nurl = " + this.nurl + ", lurl = " + this.lurl + '}';
    }

    public Float getPrice() {
        return this.price;
    }

    public String getNurl() {
        return this.nurl;
    }

    public String getLurl() {
        return this.lurl;
    }

    public String getCur() {
        return this.cur;
    }

    public boolean a() {
        return getPrice() == null && getCur() == null && getNurl() == null && getLurl() == null;
    }

    public BiddingInfo(a aVar) {
        if (aVar != null) {
            this.cur = aVar.f7043a;
            this.price = aVar.b;
            this.nurl = aVar.c;
            this.lurl = aVar.d;
        }
    }

    public BiddingInfo() {
    }
}
