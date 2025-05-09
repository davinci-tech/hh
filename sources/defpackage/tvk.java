package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvk implements KfsConstraintValidator<KfsSize, double[]> {

    /* renamed from: a, reason: collision with root package name */
    private String f17388a;
    private int b;
    private int d;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.d = kfsSize.min();
        this.b = kfsSize.max();
        this.f17388a = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean isValid(double[] dArr) {
        if (dArr == null) {
            return true;
        }
        int length = dArr.length;
        return length >= this.d && length <= this.b;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.f17388a;
    }
}
