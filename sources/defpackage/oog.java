package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class oog extends QrCodeDataBase {
    private static final Map<String, String> b;
    private String e;

    static {
        HashMap hashMap = new HashMap(2);
        b = hashMap;
        hashMap.put("1", "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4");
        hashMap.put("2", "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4");
        hashMap.put("3", "e835d102-af95-48a6-ae13-2983bc06f5c0");
        hashMap.put("4", "b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
        hashMap.put("5", "c943c933-442e-4c34-bcd0-66597f24aaed");
    }

    public oog(String str) {
        super(str);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (!(obj instanceof String)) {
            LogUtil.b("R_QrCode_ScaleQrCodeData", "mQrCodeDataBase is null.");
            return -3;
        }
        String b2 = opf.b("st", (String) obj);
        Map<String, String> map = b;
        if (!map.containsKey(b2)) {
            LogUtil.b("ScaleQrCodeData", "subType not in list: ", b2);
            return -2;
        }
        String str = map.get(b2);
        this.e = str;
        if (!TextUtils.isEmpty(str)) {
            return 0;
        }
        LogUtil.b("ScaleQrCodeData", "type from PRODUCT_ID_MAP return null, type is ", b2);
        return -2;
    }

    public String e() {
        return this.e;
    }
}
