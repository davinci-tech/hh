package defpackage;

import com.huawei.health.device.kit.hwch.ch18.bean.WeightCmd;

/* loaded from: classes3.dex */
public class cgn<T> {
    private int c = 0;
    private byte[] e = null;
    private T d = null;
    private WeightCmd b = null;

    public int b() {
        return ((Integer) cpt.d(Integer.valueOf(this.c))).intValue();
    }

    public void c(int i) {
        this.c = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public byte[] e() {
        byte[] bArr = this.e;
        return (bArr == null || bArr.length == 0) ? new byte[0] : (byte[]) bArr.clone();
    }

    public void d(byte[] bArr) {
        this.e = bArr == null ? null : (byte[]) bArr.clone();
    }

    public T d() {
        return (T) cpt.d(this.d);
    }

    public void e(T t) {
        this.d = (T) cpt.d(t);
    }

    public WeightCmd c() {
        return this.b;
    }

    public void b(WeightCmd weightCmd) {
        this.b = weightCmd;
    }
}
