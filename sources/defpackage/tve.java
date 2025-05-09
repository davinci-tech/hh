package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsMax;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tve implements KfsConstraintValidator<KfsMax, Long> {

    /* renamed from: a, reason: collision with root package name */
    private long f17384a;
    private String c;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsMax kfsMax) {
        this.c = tub.a(kfsMax, str);
        this.f17384a = kfsMax.value();
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Long l) {
        return l == null || l.compareTo(Long.valueOf(this.f17384a)) <= 0;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.c;
    }
}
