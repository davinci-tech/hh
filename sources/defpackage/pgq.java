package defpackage;

import android.text.TextUtils;
import com.huawei.health.messagecenter.model.MessageObject;
import java.util.Objects;

/* loaded from: classes6.dex */
public class pgq extends MessageObject {

    /* renamed from: a, reason: collision with root package name */
    private mdf f16123a;
    private int c;
    private int d = 0;

    public mdf d() {
        return this.f16123a;
    }

    public void a(mdf mdfVar) {
        this.f16123a = mdfVar;
    }

    public boolean b() {
        return d() != null;
    }

    public int e() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    @Override // com.huawei.health.messagecenter.model.MessageObject
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof pgq) {
            return TextUtils.equals(b(this), b((pgq) obj));
        }
        return false;
    }

    private String b(pgq pgqVar) {
        boolean z = pgqVar != null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(z);
        if (z) {
            stringBuffer.append(pgqVar.e()).append(pgqVar.getCreateTime()).append(pgqVar.getMsgId()).append(c(pgqVar.d())).append(pgqVar.getHarmonyImgUrl()).append(pgqVar.getDetailUri());
        }
        return stringBuffer.toString();
    }

    private String c(mdf mdfVar) {
        boolean z = mdfVar != null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(z);
        if (z) {
            stringBuffer.append(mdfVar.p()).append(mdfVar.ac()).append(mdfVar.h()).append(mdfVar.z()).append(mdfVar.aa()).append(mdfVar.v()).append(mdfVar.ab()).append(mdfVar.e());
        }
        return stringBuffer.toString();
    }

    @Override // com.huawei.health.messagecenter.model.MessageObject
    public int hashCode() {
        if (this.d == 0) {
            this.d = Objects.hash(b(this));
        }
        return this.d;
    }
}
