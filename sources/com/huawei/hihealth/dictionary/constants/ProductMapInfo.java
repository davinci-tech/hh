package com.huawei.hihealth.dictionary.constants;

import android.text.TextUtils;
import java.util.Objects;

/* loaded from: classes.dex */
public class ProductMapInfo {

    /* renamed from: a, reason: collision with root package name */
    private String f4123a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String g;
    private String h;
    private OptionalFileds i;

    public static class OptionalFileds {
        private String d;

        public void b(String str) {
            this.d = str;
        }

        public int hashCode() {
            return Objects.hash(this.d);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof OptionalFileds) {
                return TextUtils.equals(this.d, ((OptionalFileds) obj).d);
            }
            return false;
        }

        public String toString() {
            return "OptionalFileds{deviceEnName='" + this.d + "'}";
        }
    }

    public ProductMapInfo(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        this.f4123a = str;
        this.h = str2;
        this.g = str3;
        this.c = str4;
        this.e = str5;
        this.b = str6;
        this.d = i;
    }

    public String e() {
        return this.f4123a;
    }

    public String f() {
        return this.h;
    }

    public String h() {
        return this.g;
    }

    public String a() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.d;
    }

    public void d(OptionalFileds optionalFileds) {
        this.i = optionalFileds;
    }

    public int hashCode() {
        return Objects.hash(this.f4123a, this.h, this.g, this.c, this.e, this.b, Integer.valueOf(this.d));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductMapInfo)) {
            return false;
        }
        ProductMapInfo productMapInfo = (ProductMapInfo) obj;
        return TextUtils.equals(this.f4123a, productMapInfo.f4123a) && TextUtils.equals(this.h, productMapInfo.h) && TextUtils.equals(this.g, productMapInfo.g) && TextUtils.equals(this.c, productMapInfo.c) && TextUtils.equals(this.e, productMapInfo.e) && TextUtils.equals(this.b, productMapInfo.b) && this.d == productMapInfo.d && a(this.i, productMapInfo.i);
    }

    private boolean a(OptionalFileds optionalFileds, OptionalFileds optionalFileds2) {
        return (optionalFileds == null && optionalFileds2 == null) || (optionalFileds != null && optionalFileds.equals(optionalFileds2));
    }

    public String toString() {
        return "ProductMapInfo{mDeviceType='" + this.f4123a + "', mSmartProductId='" + this.h + "', mProductId='" + this.g + "', mModelName='" + this.c + "', mManufactorId='" + this.e + "', mMarketingName='" + this.b + "', mDeviceId=" + this.d + ", optionalFileds=" + this.i + '}';
    }
}
