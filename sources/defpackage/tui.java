package defpackage;

import android.text.TextUtils;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringNotEmpty;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tui implements KfsConstraintValidator<KfsStringNotEmpty, String> {
    private String c;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsStringNotEmpty kfsStringNotEmpty) throws ttr {
        this.c = tub.d(kfsStringNotEmpty.message(), str + " can't be empty");
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean isValid(String str) {
        return !TextUtils.isEmpty(str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.c;
    }
}
