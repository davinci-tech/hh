package defpackage;

import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;

/* loaded from: classes3.dex */
public class dal implements HealthDataParser {

    /* renamed from: a, reason: collision with root package name */
    private int f11571a;
    protected byte[] b;

    private int a(byte b) {
        return b & 255;
    }

    private int a(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }

    private int b(int i) {
        return i & 15;
    }

    public void e(int i) {
        this.f11571a = i;
    }

    @Override // com.huawei.health.device.open.data.HealthDataParser
    public HealthData parseData(byte[] bArr) {
        der derVar = new der();
        derVar.setHeartRate(a(bArr));
        return derVar;
    }

    private int a(byte b, byte b2) {
        return a(b) + (a(b2) << 8);
    }

    private int e(byte b, byte b2, byte b3, byte b4) {
        return a(b) + (a(b2) << 8) + (a(b3) << 16) + (a(b4) << 24);
    }

    private Integer c(int i, int i2) {
        int b = b(i);
        byte[] bArr = this.b;
        if (b + i2 > bArr.length) {
            return null;
        }
        if (i == 17) {
            return Integer.valueOf(a(bArr[i2]));
        }
        if (i == 18) {
            return Integer.valueOf(a(bArr[i2], bArr[i2 + 1]));
        }
        if (i == 20) {
            return Integer.valueOf(e(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]));
        }
        if (i == 36) {
            return Integer.valueOf(a(e(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]), 32));
        }
        if (i == 33) {
            return Integer.valueOf(a(a(bArr[i2]), 8));
        }
        if (i != 34) {
            return null;
        }
        return Integer.valueOf(a(a(bArr[i2], bArr[i2 + 1]), 16));
    }

    private Integer a(byte[] bArr) {
        this.b = bArr;
        return c((this.f11571a & 1) != 0 ? 18 : 17, 1);
    }
}
