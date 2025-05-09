package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import java.util.List;

/* loaded from: classes3.dex */
public class exi {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwPayConstant.KEY_USER_ID)
    private String f12370a;

    @SerializedName("grpID")
    private int b;

    @SerializedName("srcType")
    private int c;

    @SerializedName("dstUserID")
    private String d;

    @SerializedName("otherUserList")
    private List<Object> e;

    public void e(String str) {
        this.f12370a = str;
    }

    public void a(String str) {
        this.d = str;
    }

    public void e(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public void c(List<Object> list) {
        this.e = list;
    }
}
