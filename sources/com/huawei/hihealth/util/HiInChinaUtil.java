package com.huawei.hihealth.util;

/* loaded from: classes.dex */
public class HiInChinaUtil {
    private static Rectangle[] b = {new Rectangle(49.2204d, 79.4462d, 42.8899d, 96.33d), new Rectangle(54.1415d, 109.6872d, 39.3742d, 135.0002d), new Rectangle(42.8899d, 73.1246d, 29.5297d, 124.143255d), new Rectangle(29.5297d, 82.9684d, 26.7186d, 97.0352d), new Rectangle(29.5297d, 97.0253d, 20.414096d, 124.367395d), new Rectangle(20.414096d, 107.975793d, 17.871542d, 111.744104d)};

    /* renamed from: a, reason: collision with root package name */
    private static Rectangle[] f4141a = {new Rectangle(25.398623d, 119.921265d, 21.785006d, 122.497559d), new Rectangle(22.284d, 101.8652d, 20.0988d, 106.665d), new Rectangle(21.5422d, 106.4525d, 20.4878d, 108.051d), new Rectangle(55.8175d, 109.0323d, 50.3257d, 119.127d), new Rectangle(55.8175d, 127.4568d, 49.5574d, 137.0227d), new Rectangle(44.8922d, 131.2662d, 42.5692d, 137.0227d)};
    private static Rectangle[] c = {new Rectangle(22.158318d, 113.538441d, 22.213538d, 113.598688d), new Rectangle(22.109501d, 113.553527d, 22.158318d, 113.598688d), new Rectangle(22.154752d, 113.834609d, 22.444842d, 114.407653d), new Rectangle(22.444842d, 113.955365d, 22.502398d, 114.407653d)};

    public static int c(double d, double d2) {
        for (Rectangle rectangle : c) {
            if (rectangle.d(d, d2)) {
                return 3;
            }
        }
        for (Rectangle rectangle2 : b) {
            if (rectangle2.d(d, d2)) {
                for (Rectangle rectangle3 : f4141a) {
                    if (rectangle3.d(d, d2)) {
                        return 2;
                    }
                }
                return 1;
            }
        }
        return 2;
    }

    static class Rectangle {
        private double b;
        private double c;
        private double d;
        private double e;

        Rectangle(double d, double d2, double d3, double d4) {
            this.b = Math.min(d2, d4);
            this.d = Math.max(d, d3);
            this.e = Math.max(d2, d4);
            this.c = Math.min(d, d3);
        }

        boolean d(double d, double d2) {
            return ((this.b > d2 ? 1 : (this.b == d2 ? 0 : -1)) <= 0 && (this.e > d2 ? 1 : (this.e == d2 ? 0 : -1)) >= 0) && ((this.d > d ? 1 : (this.d == d ? 0 : -1)) >= 0 && (this.c > d ? 1 : (this.c == d ? 0 : -1)) <= 0);
        }
    }
}
