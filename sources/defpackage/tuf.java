package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotBlank;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tuf implements KfsConstraintValidator<KfsNotBlank, CharSequence> {
    private String b;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsNotBlank kfsNotBlank) {
        this.b = tub.d(kfsNotBlank.message(), str + " can't be blank");
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean isValid(CharSequence charSequence) {
        return charSequence == null || charSequence.toString().trim().length() > 0;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.b;
    }
}
