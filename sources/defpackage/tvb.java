package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsMin;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvb implements KfsConstraintValidator<KfsMin, Integer> {
    private String b;
    private long c;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsMin kfsMin) {
        this.b = tub.e(kfsMin, str);
        this.c = kfsMin.value();
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Integer num) {
        if (num == null) {
            return true;
        }
        if (this.c >= 2147483647L) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.c);
        sb.append("");
        return num.compareTo(Integer.valueOf(Integer.parseInt(sb.toString()))) >= 0;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.b;
    }
}
