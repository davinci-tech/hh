package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotEmpty;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tuv implements KfsConstraintValidator<KfsNotEmpty, short[]> {
    private String b;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsNotEmpty kfsNotEmpty) {
        this.b = tub.c(kfsNotEmpty, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean isValid(short[] sArr) {
        return sArr == null || sArr.length > 0;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.b;
    }
}
