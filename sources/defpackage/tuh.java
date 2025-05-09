package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringRegex;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tuh implements KfsConstraintValidator<KfsStringRegex, String> {
    private String b;
    private String d;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsStringRegex kfsStringRegex) throws ttr {
        this.b = kfsStringRegex.regex();
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean isValid(String str) {
        if (str == null) {
            this.d = "string is null";
            return false;
        }
        if (str.matches(this.b)) {
            return true;
        }
        this.d = "value is not match";
        return false;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.d;
    }
}
