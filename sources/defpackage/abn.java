package defpackage;

import com.huawei.animation.physical2.ParamTransfer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class abn {

    /* renamed from: a, reason: collision with root package name */
    private float f165a;
    private int b = 0;
    private List<abp> c = new ArrayList();
    private ParamTransfer<Float> d;

    private abn(ParamTransfer<Float> paramTransfer) {
        this.d = paramTransfer;
    }

    public static abn c(int i, int i2, float f, ParamTransfer<Float> paramTransfer) {
        if (i <= 0 || paramTransfer == null) {
            throw new IllegalArgumentException("illegal parameter");
        }
        abn abnVar = new abn(paramTransfer);
        abnVar.d = paramTransfer;
        abnVar.f165a = f;
        for (int i3 = 0; i3 < i; i3++) {
            abnVar.c.add(new abp(i2, paramTransfer.transfer(Float.valueOf(abnVar.f165a), i3).floatValue()));
        }
        return abnVar;
    }

    public float e(int i, float f) {
        if (i < 0 || i >= this.c.size() || this.c.get(i) == null) {
            throw new IllegalArgumentException("parameter out of range");
        }
        return this.c.get(i).getRate(f);
    }

    public void a(int i) {
        if (i == this.b) {
            return;
        }
        this.b = i;
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            abp abpVar = this.c.get(i2);
            if (abpVar != null) {
                abpVar.b(this.d.transfer(Float.valueOf(this.f165a), Math.abs(i2 - i)).floatValue());
            }
        }
    }
}
