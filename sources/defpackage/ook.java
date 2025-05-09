package defpackage;

import android.text.TextUtils;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;

/* loaded from: classes6.dex */
public class ook extends QrCodeDataBase {
    private String b;
    private String e;

    public ook(String str) {
        super(str);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (!(obj instanceof String)) {
            LogUtil.b("ThirdEcgQrCodeData", "mQrCodeDataBase is null.");
            return -3;
        }
        String str = (String) obj;
        this.b = ope.a(FitRunPlayAudio.OPPORTUNITY_M, str);
        this.e = ope.a(u3.m, str);
        if (!TextUtils.isEmpty(this.b) && !TextUtils.isEmpty(this.e)) {
            return 0;
        }
        LogUtil.b("ThirdEcgQrCodeData", "mMac or mSmartProductId is null");
        return -2;
    }

    public String d() {
        return this.b;
    }

    public String c() {
        return this.e;
    }
}
