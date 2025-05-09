package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsIn;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class tun implements KfsConstraintValidator<KfsIn, Long> {
    private List<Long> c;
    private String e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsIn kfsIn) {
        this.c = new ArrayList();
        int length = kfsIn.intArr().length;
        for (int i = 0; i < length; i++) {
            this.c.add(Long.valueOf(r0[i]));
        }
        this.e = tub.d(kfsIn.message(), str + " must in intArr:" + Arrays.toString(kfsIn.intArr()));
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Long l) {
        if (l == null) {
            return true;
        }
        return this.c.contains(l);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.e;
    }
}
