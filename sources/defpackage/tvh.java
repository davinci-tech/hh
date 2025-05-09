package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;
import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;

/* loaded from: classes7.dex */
public class tvh implements KfsConstraintValidator<KfsSize, byte[]> {

    /* renamed from: a, reason: collision with root package name */
    private int f17386a;
    private int c;
    private String d;

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(String str, KfsSize kfsSize) throws ttr {
        tvp.a(kfsSize);
        this.f17386a = kfsSize.min();
        this.c = kfsSize.max();
        this.d = tub.c(kfsSize, str);
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean isValid(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        int length = bArr.length;
        return length >= this.f17386a && length <= this.c;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator
    public String getMessage() {
        return this.d;
    }
}
