package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotEmpty;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.util.Collection;

/* loaded from: classes7.dex */
public class tup implements KfsConstraintValidator<KfsNotEmpty, Collection> {
    private String d;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsNotEmpty kfsNotEmpty) {
        this.d = tub.c(kfsNotEmpty, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Collection collection) {
        return collection == null || collection.size() > 0;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.d;
    }
}
