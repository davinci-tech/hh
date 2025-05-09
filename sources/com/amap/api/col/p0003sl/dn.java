package com.amap.api.col.p0003sl;

import java.util.Locale;
import java.util.Random;

/* loaded from: classes8.dex */
public final class dn {

    /* renamed from: a, reason: collision with root package name */
    private static String f972a = "0123456789";

    public static String a() {
        Random random = new Random();
        String format = String.format(Locale.US, "%05d", Integer.valueOf(random.nextInt(10)));
        int nextInt = random.nextInt(10);
        int nextInt2 = random.nextInt(100);
        return new a(f972a, nextInt2).a(nextInt, format) + String.format(Locale.US, "%01d", Integer.valueOf(nextInt)) + String.format(Locale.US, "%02d", Integer.valueOf(nextInt2));
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f973a;
        private int b;
        private int c;

        public a(String str, int i) {
            this.b = 1103515245;
            this.c = 12345;
            this.f973a = a(str, i, str.length());
        }

        public a() {
            this((byte) 0);
        }

        private a(byte b) {
            this("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 11);
        }

        private char a(int i) {
            this.f973a.length();
            return this.f973a.charAt(i);
        }

        private int a(char c) {
            this.f973a.length();
            return this.f973a.indexOf(c);
        }

        private int b(int i) {
            return (int) (((i * this.b) + this.c) & 2147483647L);
        }

        private String a(String str, int i, int i2) {
            StringBuffer stringBuffer = new StringBuffer(str);
            int length = str.length();
            for (int i3 = 0; i3 < i2; i3++) {
                int b = b(i);
                int i4 = b % length;
                i = b(b);
                int i5 = i % length;
                char charAt = stringBuffer.charAt(i4);
                stringBuffer.setCharAt(i4, stringBuffer.charAt(i5));
                stringBuffer.setCharAt(i5, charAt);
            }
            return stringBuffer.toString();
        }

        private String b(int i, String str) {
            StringBuilder sb = new StringBuilder();
            int length = this.f973a.length();
            int length2 = str.length();
            for (int i2 = 0; i2 < length2; i2++) {
                int a2 = a(str.charAt(i2));
                if (a2 < 0) {
                    break;
                }
                sb.append(a(((a2 + i) + i2) % length));
            }
            if (sb.length() == length2) {
                return sb.toString();
            }
            return null;
        }

        public final String a(int i, String str) {
            return b(i, str);
        }
    }
}
