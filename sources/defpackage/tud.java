package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsIntegerRange;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tud implements KfsConstraintValidator<KfsIntegerRange, Integer> {

    /* renamed from: a, reason: collision with root package name */
    private int f17377a;
    private String b;
    private int d;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsIntegerRange kfsIntegerRange) throws ttr {
        this.f17377a = kfsIntegerRange.min();
        this.d = kfsIntegerRange.max();
        this.e = str;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Integer num) {
        if (num == null) {
            this.b = this.e + " is null";
            return false;
        }
        if (this.f17377a > num.intValue()) {
            this.b = this.e + " must >= " + this.f17377a;
            return false;
        }
        if (this.d >= num.intValue()) {
            return true;
        }
        this.b = this.e + " must <= " + this.d;
        return false;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.b;
    }
}
