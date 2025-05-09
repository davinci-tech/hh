package defpackage;

import com.huawei.ads.adsrec.db.table.AdEventRecord;

/* loaded from: classes2.dex */
public class uy {

    /* renamed from: a, reason: collision with root package name */
    private AdEventRecord f17585a;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f17586a;
        private Long b;
        private String c;
        private String d;
        private String e;
        private String f;
        private Integer g;
        private String h;
        private Long j;

        public a f(String str) {
            this.e = str;
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a c(Long l) {
            this.b = l;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a c(Integer num) {
            this.g = num;
            return this;
        }

        public a d(String str) {
            this.h = str;
            return this;
        }

        public a a(Long l) {
            this.j = l;
            return this;
        }

        public a e(String str) {
            this.f17586a = str;
            return this;
        }

        public a b(String str) {
            this.f = str;
            return this;
        }

        public uy b() {
            AdEventRecord adEventRecord = new AdEventRecord();
            adEventRecord.b(this.d);
            adEventRecord.h(this.e);
            adEventRecord.d(this.f17586a);
            adEventRecord.e(this.c);
            Long l = this.b;
            if (l != null) {
                adEventRecord.a(l.longValue());
            }
            Integer num = this.g;
            if (num != null) {
                adEventRecord.e(num.intValue());
            }
            adEventRecord.c(this.h);
            Long l2 = this.j;
            if (l2 != null) {
                adEventRecord.d(l2.longValue());
            }
            String str = this.f;
            if (str != null) {
                adEventRecord.a(str);
            }
            return new uy(adEventRecord);
        }
    }

    public String toString() {
        return "AdAffair{" + this.f17585a + '}';
    }

    public AdEventRecord b() {
        return this.f17585a;
    }

    private uy(AdEventRecord adEventRecord) {
        this.f17585a = adEventRecord;
    }
}
