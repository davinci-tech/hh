package defpackage;

import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.hihealthservice.hihealthkit.cpcheck.BaseRequest;

/* loaded from: classes7.dex */
public class rro<T> extends BaseRequest<T> {
    private static final String c = ipb.b + "/healthkit/v1/accessRecords";

    public rro(boolean z, String str, String str2) {
        this.mUrl = c;
        this.mParams.put(Wpt.MODE, z ? "read" : "write");
        if (!TextUtils.isEmpty(str)) {
            this.mParams.put("clientId", str);
        }
        this.mParams.put("lang", str2);
    }
}
