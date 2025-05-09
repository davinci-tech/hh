package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsLongRange;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tuc implements KfsConstraintValidator<KfsLongRange, Long> {
    private Long b;
    private Long c;
    private String d;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsLongRange kfsLongRange) throws ttr {
        this.b = Long.valueOf(kfsLongRange.min());
        this.c = Long.valueOf(kfsLongRange.max());
        this.d = str;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Long l) {
        if (l == null) {
            this.e = this.d + " is null";
            return false;
        }
        if (l.longValue() < this.b.longValue()) {
            this.e = this.d + " must >= " + this.b;
            return false;
        }
        if (l.longValue() <= this.c.longValue()) {
            return true;
        }
        this.e = this.d + " must <= " + this.c;
        return false;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.e;
    }
}
