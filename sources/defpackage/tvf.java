package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvf implements KfsConstraintValidator<KfsSize, boolean[]> {

    /* renamed from: a, reason: collision with root package name */
    private int f17385a;
    private int d;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.f17385a = kfsSize.min();
        this.d = kfsSize.max();
        this.e = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean isValid(boolean[] zArr) {
        if (zArr == null) {
            return true;
        }
        int length = zArr.length;
        return length >= this.f17385a && length <= this.d;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.e;
    }
}
