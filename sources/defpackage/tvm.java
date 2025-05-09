package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvm implements KfsConstraintValidator<KfsSize, long[]> {

    /* renamed from: a, reason: collision with root package name */
    private String f17389a;
    private int d;
    private int e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.d = kfsSize.min();
        this.e = kfsSize.max();
        this.f17389a = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean isValid(long[] jArr) {
        if (jArr == null) {
            return true;
        }
        int length = jArr.length;
        return length >= this.d && length <= this.e;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.f17389a;
    }
}
