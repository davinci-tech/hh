package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvo implements KfsConstraintValidator<KfsSize, float[]> {

    /* renamed from: a, reason: collision with root package name */
    private int f17391a;
    private String c;
    private int d;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.f17391a = kfsSize.min();
        this.d = kfsSize.max();
        this.c = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean isValid(float[] fArr) {
        if (fArr == null) {
            return true;
        }
        int length = fArr.length;
        return length >= this.f17391a && length <= this.d;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.c;
    }
}
