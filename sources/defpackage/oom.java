package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;

/* loaded from: classes6.dex */
public class oom extends QrCodeDataBase {
    private String b;

    public oom(String str) {
        super(str);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (!(obj instanceof String)) {
            LogUtil.h("WatchFaceQrCodeData", "mQrCodeDataBase is null.");
            return -3;
        }
        String a2 = ope.a("address", (String) obj);
        this.b = a2;
        if (!TextUtils.isEmpty(a2)) {
            return 0;
        }
        LogUtil.h("WatchFaceQrCodeData", "mAddress is null");
        return -2;
    }

    public String b() {
        return this.b;
    }
}
