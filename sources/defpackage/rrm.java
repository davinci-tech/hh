package defpackage;

import android.text.TextUtils;
import com.huawei.hihealthservice.hihealthkit.cpcheck.BaseRequest;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;

/* loaded from: classes7.dex */
public class rrm<T> extends BaseRequest<T> {
    private static final String d = ipb.b + "/healthkit/v1/activityRecords";

    public rrm(String str, String str2, String str3) {
        this.mUrl = d;
        if (!TextUtils.isEmpty(str)) {
            this.mParams.put("cursor", str);
        }
        if (!TextUtils.isEmpty(str3)) {
            this.mParams.put(SyncDataConstant.BI_KEY_ACTIVITY_TYPE, str3);
        }
        this.mParams.put("clientId", str2);
        this.mParams.put("order", "endTimeDesc");
    }
}
