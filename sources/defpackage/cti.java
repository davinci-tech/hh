package defpackage;

import com.huawei.operation.utils.Constants;

/* loaded from: classes3.dex */
public class cti extends ctc {
    private static final long serialVersionUID = 8809056063570514076L;
    private Integer b;
    private String c;
    private Integer d;
    private Integer e;

    public Integer a() {
        return this.b;
    }

    public void a(Integer num) {
        this.b = num;
    }

    public Integer c() {
        return this.e;
    }

    public void d(Integer num) {
        this.e = num;
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public Integer e() {
        return this.d;
    }

    public void c(Integer num) {
        this.d = num;
    }

    @Override // defpackage.ctc
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CoapSessionInterfaceOEntityModel{errorCode =");
        stringBuffer.append(b());
        StringBuffer append = stringBuffer.append(", type =");
        Object obj = this.b;
        Object obj2 = Constants.NULL;
        if (obj == null) {
            obj = Constants.NULL;
        }
        append.append(obj);
        StringBuffer append2 = stringBuffer.append(", modeSupport =");
        Integer num = this.e;
        if (num != null) {
            obj2 = num;
        }
        append2.append(obj2);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
