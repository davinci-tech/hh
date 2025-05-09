package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsMax;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvc implements KfsConstraintValidator<KfsMax, Integer> {
    private long d;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsMax kfsMax) {
        this.e = tub.a(kfsMax, str);
        this.d = kfsMax.value();
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Integer num) {
        if (num == null || this.d >= 2147483647L) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.d);
        sb.append("");
        return num.compareTo(Integer.valueOf(Integer.parseInt(sb.toString()))) <= 0;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.e;
    }
}
