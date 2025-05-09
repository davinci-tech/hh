package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotEmpty;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tuy implements KfsConstraintValidator<KfsNotEmpty, float[]> {
    private String b;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsNotEmpty kfsNotEmpty) {
        this.b = tub.c(kfsNotEmpty, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean isValid(float[] fArr) {
        return fArr == null || fArr.length > 0;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.b;
    }
}
