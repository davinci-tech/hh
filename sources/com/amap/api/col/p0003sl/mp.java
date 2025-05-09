package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class mp {

    /* renamed from: a, reason: collision with root package name */
    public long f1338a;
    public String b;
    public int d;
    public long e;
    public short g;
    public boolean h;
    public int c = -113;
    public long f = 0;

    public mp(boolean z) {
        this.h = z;
    }

    public final String a() {
        return this.h + "#" + this.f1338a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public mp clone() {
        mp mpVar = new mp(this.h);
        mpVar.f1338a = this.f1338a;
        mpVar.b = this.b;
        mpVar.c = this.c;
        mpVar.d = this.d;
        mpVar.e = this.e;
        mpVar.f = this.f;
        mpVar.g = this.g;
        mpVar.h = this.h;
        return mpVar;
    }

    public final String toString() {
        return "AmapWifi{mac=" + this.f1338a + ", ssid='" + this.b + "', rssi=" + this.c + ", frequency=" + this.d + ", timestamp=" + this.e + ", lastUpdateUtcMills=" + this.f + ", freshness=" + ((int) this.g) + ", connected=" + this.h + '}';
    }

    public static String a(long j) {
        if (j < 0 || j > 281474976710655L) {
            return null;
        }
        return mx.a(mx.a(j), ":");
    }

    public static long a(String str) {
        long j;
        if (str == null || str.length() == 0) {
            return 0L;
        }
        int i = 0;
        long j2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            long charAt = str.charAt(length);
            if (charAt < 48 || charAt > 57) {
                long j3 = 97;
                if (charAt < 97 || charAt > 102) {
                    j3 = 65;
                    if (charAt < 65 || charAt > 70) {
                        if (charAt != 58 && charAt != 124) {
                            return 0L;
                        }
                    }
                }
                j = (charAt - j3) + 10;
            } else {
                j = charAt - 48;
            }
            j2 += j << i;
            i += 4;
        }
        if (i != 48) {
            return 0L;
        }
        return j2;
    }
}
