package com.huawei.openalliance.ad.db.bean;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cp;
import com.huawei.openalliance.ad.utils.f;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class EncryptionField<DATA> implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private Class f6692a;
    private Class b;
    private DATA c;
    private String d;

    public boolean c() {
        return !TextUtils.isEmpty(this.d);
    }

    public boolean b() {
        DATA data = this.c;
        return data instanceof String ? !TextUtils.isEmpty((String) data) : data != null;
    }

    public String b(byte[] bArr) {
        DATA a2 = a(bArr);
        String a3 = f.a(a2 instanceof String ? (String) a2 : be.b(a2), bArr);
        this.d = a3;
        return a3;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(DATA data) {
        this.c = data;
    }

    public String a() {
        return this.d;
    }

    public DATA a(byte[] bArr) {
        DATA data;
        if (this.f6692a == String.class) {
            if (TextUtils.isEmpty((String) this.c)) {
                data = (DATA) f.b(this.d, bArr);
                this.c = data;
            }
        } else if (this.c == null) {
            data = (DATA) be.b(f.b(this.d, bArr), this.f6692a, this.b);
            this.c = data;
        }
        return this.c;
    }

    public DATA a(Context context) {
        DATA data;
        if (this.f6692a == String.class) {
            if (TextUtils.isEmpty((String) this.c)) {
                data = (DATA) f.b(this.d, cp.b(context));
                this.c = data;
            }
        } else if (this.c == null) {
            data = (DATA) be.b(f.b(this.d, cp.b(context)), this.f6692a, this.b);
            this.c = data;
        }
        return this.c;
    }

    public EncryptionField(Class cls, Class cls2) {
        this.f6692a = cls;
        this.b = cls2;
    }

    public EncryptionField(Class cls) {
        this.f6692a = cls;
    }
}
