package defpackage;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class cvm implements Serializable {
    private static final long serialVersionUID = 1557041249539848525L;

    /* renamed from: a, reason: collision with root package name */
    private List<String> f11502a;
    private String b;
    private String c;
    private String d;
    private String e;
    private List<String> j;

    public List<String> f() {
        return this.j;
    }

    public void c(List<String> list) {
        this.j = list;
    }

    public String a() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public String e() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String c() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public List<String> b() {
        return this.f11502a;
    }

    public void d(List<String> list) {
        this.f11502a = list;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("SecondResourceBean{mUuid='");
        stringBuffer.append(String.valueOf(this.j.toString()));
        stringBuffer.append("mDeviceName='");
        stringBuffer.append(this.e);
        stringBuffer.append("mDeviceDescription='");
        stringBuffer.append(this.c);
        stringBuffer.append("mSecondIcon='");
        stringBuffer.append(this.b);
        stringBuffer.append("mBluetoothNameList='");
        stringBuffer.append(String.valueOf(this.f11502a.toString()));
        stringBuffer.append("mBluetoothMatch='");
        stringBuffer.append(this.d);
        return stringBuffer.toString();
    }
}
