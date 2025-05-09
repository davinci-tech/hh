package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.util.Collection;

/* loaded from: classes7.dex */
public class tvi implements KfsConstraintValidator<KfsSize, Collection> {
    private int c;
    private int d;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.d = kfsSize.min();
        this.c = kfsSize.max();
        this.e = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Collection collection) {
        if (collection == null) {
            return true;
        }
        int size = collection.size();
        return size >= this.d && size <= this.c;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.e;
    }
}
