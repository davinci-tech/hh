package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;

/* loaded from: classes6.dex */
public class onz extends QrCodeDataBase {
    private String e;

    public onz(String str) {
        super(str);
    }

    public String a() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (!(obj instanceof String)) {
            LogUtil.b("R_QrCode_FamilyQrCodeData", "QrCodeBase is null");
            return -3;
        }
        String str = (String) obj;
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("R_QrCode_FamilyQrCodeData", "parser dataByte is null");
            return -3;
        }
        e(str);
        return 0;
    }
}
