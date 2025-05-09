package defpackage;

import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class crx implements Serializable {
    private static final long serialVersionUID = -1815768583998220679L;

    /* renamed from: a, reason: collision with root package name */
    private String f11421a;
    private String b;
    private String c;
    private String d;
    private String e;

    public String e() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.f11421a;
    }

    public void c(String str) {
        this.f11421a = str;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        this.e = ctm.e(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SubUser{mHuid ='");
        String str = this.b;
        String str2 = Constants.NULL;
        stringBuffer.append(str == null ? Constants.NULL : cpw.d(str)).append("'mNickName ='");
        String str3 = this.c;
        stringBuffer.append(str3 == null ? Constants.NULL : cpw.d(str3)).append("'mSid ='");
        String str4 = this.f11421a;
        if (str4 == null) {
            str4 = Constants.NULL;
        }
        stringBuffer.append(str4).append("'mUserAccount ='");
        String str5 = this.e;
        if (str5 == null) {
            str5 = Constants.NULL;
        }
        stringBuffer.append(str5).append("'mIconPath ='");
        String str6 = this.d;
        if (str6 != null) {
            str2 = str6;
        }
        stringBuffer.append(str2);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public boolean h() {
        return TextUtils.isEmpty(this.b);
    }
}
