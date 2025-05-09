package defpackage;

import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;

/* loaded from: classes6.dex */
public class nbx extends AuthParam {
    private int e;
    private int f = 0;
    private String b = "";

    /* renamed from: a, reason: collision with root package name */
    private String f15240a = "";
    private String d = "";
    private String c = "";

    public nbx(int i) {
        this.e = 3;
        this.e = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AuthParam
    public int getAuthType() {
        return ((Integer) jdy.d(Integer.valueOf(this.e))).intValue();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AuthParam
    public void setSlotId(int i) {
        this.f = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AuthParam
    public int getSlotId() {
        return ((Integer) jdy.d(Integer.valueOf(this.f))).intValue();
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AuthParam
    public void setImsi(String str) {
        this.b = (String) jdy.d(str);
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AuthParam
    public String getImsi() {
        return (String) jdy.d(this.b);
    }

    @Override // com.huawei.multisimsdk.multidevicemanager.common.AuthParam
    public String getPhoneNumber() {
        return (String) jdy.d(this.c);
    }
}
