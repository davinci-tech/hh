package defpackage;

import com.huawei.animation.physical2.ParamTransfer;

/* loaded from: classes8.dex */
public class abq implements ParamTransfer<Float> {

    /* renamed from: a, reason: collision with root package name */
    private float f168a;

    public abq() {
        this(0.0f);
    }

    public abq(float f) {
        this.f168a = f;
    }

    @Override // com.huawei.animation.physical2.ParamTransfer
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Float transfer(Float f, int i) {
        return Float.valueOf((float) (Math.pow(i + 1, (-this.f168a) * 0.18f) * f.floatValue()));
    }
}
