package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringRange;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tuk implements KfsConstraintValidator<KfsStringRange, String> {

    /* renamed from: a, reason: collision with root package name */
    private String f17379a;
    private int b;
    private String c;
    private int e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsStringRange kfsStringRange) throws ttr {
        this.b = kfsStringRange.min();
        this.e = kfsStringRange.max();
        this.f17379a = str;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean isValid(String str) {
        if (str == null) {
            this.c = this.f17379a + " is null";
            return false;
        }
        if (str.length() < this.b) {
            this.c = this.f17379a + " length must >= " + this.b;
            return false;
        }
        if (str.length() <= this.e) {
            return true;
        }
        this.c = this.f17379a + " length must <= " + this.e;
        return false;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.c;
    }
}
