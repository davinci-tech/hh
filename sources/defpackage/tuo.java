package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsIn;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class tuo implements KfsConstraintValidator<KfsIn, String> {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f17380a;
    private String c;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsIn kfsIn) {
        this.f17380a = Arrays.asList(kfsIn.strArr());
        this.c = tub.d(kfsIn.message(), str + " must in strArr:" + Arrays.toString(kfsIn.strArr()));
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean isValid(String str) {
        if (str == null) {
            return true;
        }
        return this.f17380a.contains(str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.c;
    }
}
