package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;

/* loaded from: classes6.dex */
public class onx extends QrCodeDataBase {

    /* renamed from: a, reason: collision with root package name */
    private String f15819a;
    private String c;
    private String d;

    public onx(String str) {
        super(str);
        this.c = "";
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (!(obj instanceof String)) {
            LogUtil.b("ThirdVentilatorQrCodeData", "mQrCodeDataBase is null.");
            return -3;
        }
        String str = (String) obj;
        this.d = ope.a("sn", str);
        this.c = ope.a("ex", str);
        this.f15819a = ope.a(u3.m, str);
        if (!TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.f15819a)) {
            return 0;
        }
        LogUtil.b("ThirdVentilatorQrCodeData", "sn or ex or pid is null");
        return -2;
    }

    public String c() {
        return this.d;
    }

    public String b() {
        return this.f15819a;
    }

    public String d() {
        return this.c;
    }
}
