package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsIn;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class tug implements KfsConstraintValidator<KfsIn, Integer> {

    /* renamed from: a, reason: collision with root package name */
    private String f17378a;
    private List<Integer> e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsIn kfsIn) {
        this.e = new ArrayList();
        for (int i : kfsIn.intArr()) {
            this.e.add(Integer.valueOf(i));
        }
        this.f17378a = tub.d(kfsIn.message(), str + " must in intArr:" + Arrays.toString(kfsIn.intArr()));
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Integer num) {
        if (num == null) {
            return true;
        }
        return this.e.contains(num);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.f17378a;
    }
}
