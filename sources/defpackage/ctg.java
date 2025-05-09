package defpackage;

import com.huawei.operation.utils.Constants;

/* loaded from: classes3.dex */
public class ctg extends ctc {
    private static final long serialVersionUID = -1074804273256729256L;

    /* renamed from: a, reason: collision with root package name */
    private Integer f11458a;
    private Integer b;
    private String c;
    private Long d;
    private Integer e;
    private String f;

    public String h() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public Integer e() {
        return this.e;
    }

    public void d(Integer num) {
        this.e = num;
    }

    public String i() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public Long a() {
        return this.d;
    }

    public void e(Long l) {
        this.d = l;
    }

    public Integer d() {
        return this.b;
    }

    public void a(Integer num) {
        this.b = num;
    }

    public Integer c() {
        return this.f11458a;
    }

    public void c(Integer num) {
        this.f11458a = num;
    }

    @Override // defpackage.ctc
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CoapSessionResponseOEntityModel{errcode =");
        Object obj = this.b;
        Object obj2 = Constants.NULL;
        if (obj == null) {
            obj = Constants.NULL;
        }
        stringBuffer.append(obj).append("', modeResp =");
        Integer num = this.e;
        if (num != null) {
            obj2 = num;
        }
        stringBuffer.append(obj2).append("'}");
        return stringBuffer.toString();
    }
}
