package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvd implements KfsConstraintValidator<KfsSize, CharSequence> {

    /* renamed from: a, reason: collision with root package name */
    private int f17383a;
    private int c;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.f17383a = kfsSize.min();
        this.c = kfsSize.max();
        this.e = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean isValid(CharSequence charSequence) {
        if (charSequence == null) {
            return true;
        }
        int length = charSequence.length();
        return length >= this.f17383a && length <= this.c;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.e;
    }
}
