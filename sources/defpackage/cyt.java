package defpackage;

import health.compact.a.util.LogUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class cyt {
    private byte[] d;
    private int e = 0;
    private int b = 0;
    private HashMap<Integer, byte[]> h = new HashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private boolean f11544a = false;
    private int c = 0;

    public void c(int i) {
        this.b = i;
    }

    public void d(boolean z) {
        this.f11544a = z;
    }

    public int d() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public HashMap<Integer, byte[]> c() {
        return this.h;
    }

    public byte[] a() {
        return this.d;
    }

    public void c(byte[] bArr) {
        this.d = bArr;
    }

    public boolean e(int i, byte[] bArr) {
        this.h.put(Integer.valueOf(i), bArr);
        return this.f11544a && this.h.size() == this.c;
    }

    public void b() {
        HashMap<Integer, byte[]> c = c();
        int d = d();
        byte[] bArr = new byte[0];
        for (int i = 0; i < d; i++) {
            byte[] bArr2 = c.get(Integer.valueOf(i));
            if (bArr2 == null) {
                return;
            }
            bArr = e(bArr, bArr2);
        }
        c(bArr);
    }

    private byte[] e(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    protected ckh e(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        ckh ckhVar = new ckh();
        ckhVar.a(cyw.e(bArr, 18, i));
        ckhVar.e(cyw.e(bArr, 18, i + 2));
        ckhVar.d(cyw.e(bArr, 18, i + 4));
        ckhVar.c(cyw.e(bArr, 18, i + 6));
        LogUtil.b("PDROPE_DataPackets", "RopeDataCharacteristic", ckhVar.toString());
        return ckhVar;
    }

    public boolean a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            i3 += bArr[i];
            i++;
        }
        return bArr[i2] == ((byte) (i3 & 255));
    }
}
