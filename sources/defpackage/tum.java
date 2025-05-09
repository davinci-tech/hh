package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsIn;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class tum implements KfsConstraintValidator<KfsIn, Short> {
    private List<Integer> c;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsIn kfsIn) {
        this.c = new ArrayList();
        for (int i : kfsIn.intArr()) {
            this.c.add(Integer.valueOf(i));
        }
        this.e = tub.d(kfsIn.message(), str + " must in intArr:" + Arrays.toString(kfsIn.intArr()));
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Short sh) {
        if (sh == null) {
            return true;
        }
        return this.c.contains(Integer.valueOf(sh.shortValue()));
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.e;
    }
}
