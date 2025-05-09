package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tuj implements KfsConstraintValidator<KfsNotNull, Object> {
    private String c;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public boolean isValid(Object obj) {
        return obj != null;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsNotNull kfsNotNull) {
        this.c = tub.d(kfsNotNull.message(), str + " can't be null");
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.c;
    }
}
