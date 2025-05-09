package defpackage;

import android.text.TextUtils;
import com.huawei.hihealthservice.hihealthkit.cpcheck.BaseRequest;

/* loaded from: classes7.dex */
public class rrr<T> extends BaseRequest<T> {
    private static final String b = ipb.b + "/healthkit/v1/sampleSets";

    public rrr(String str, String str2, String str3, String str4) {
        this.mUrl = b;
        if (!TextUtils.isEmpty(str)) {
            this.mParams.put("cursor", str);
        }
        this.mParams.put("clientId", str2);
        this.mParams.put("dataType", str3);
        this.mParams.put("timeZone", str4);
        this.mParams.put("order", "endTimeDesc");
    }
}
