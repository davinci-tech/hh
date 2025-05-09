package defpackage;

import android.app.Activity;
import android.content.Context;
import com.huawei.health.device.api.VersionVerifyUtilApi;
import com.huawei.health.device.callback.VersionNoSupportCallback;
import com.huawei.hmf.annotation.ApiDefine;

@ApiDefine(uri = VersionVerifyUtilApi.class)
/* loaded from: classes3.dex */
public class dzb implements VersionVerifyUtilApi {
    @Override // com.huawei.health.device.api.VersionVerifyUtilApi
    public boolean isPublishVersion(String str, String str2) {
        return crj.c(str, str2);
    }

    @Override // com.huawei.health.device.api.VersionVerifyUtilApi
    public void noSupportDevice(Context context, Activity activity) {
        crj.Lu_(context, activity);
    }

    @Override // com.huawei.health.device.api.VersionVerifyUtilApi
    public void noSupportDevice(Context context, Activity activity, VersionNoSupportCallback versionNoSupportCallback) {
        crj.Lv_(context, activity, versionNoSupportCallback);
    }
}
