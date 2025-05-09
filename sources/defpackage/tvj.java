package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.util.Map;

/* loaded from: classes7.dex */
public class tvj implements KfsConstraintValidator<KfsSize, Map> {

    /* renamed from: a, reason: collision with root package name */
    private int f17387a;
    private String c;
    private int e;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.e = kfsSize.min();
        this.f17387a = kfsSize.max();
        this.c = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean isValid(Map map) {
        if (map == null) {
            return true;
        }
        int size = map.size();
        return size >= this.e && size <= this.f17387a;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.c;
    }
}
