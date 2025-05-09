package defpackage;

import com.huawei.health.device.api.MultiUsersManagerApi;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwbasemgr.ResponseCallback;
import health.compact.a.util.LogUtil;

@ApiDefine(uri = MultiUsersManagerApi.class)
/* loaded from: classes3.dex */
public class dyz implements MultiUsersManagerApi {
    @Override // com.huawei.health.device.api.MultiUsersManagerApi
    public cfi getCurrentUser() {
        LogUtil.d("MultiUsersManagerImpl", "getCurrentUser");
        return MultiUsersManager.INSTANCE.getCurrentUser();
    }

    @Override // com.huawei.health.device.api.MultiUsersManagerApi
    public void getCurrentUserForUserInfo(ResponseCallback<cfi> responseCallback) {
        LogUtil.d("MultiUsersManagerImpl", "getCurrentUserForUserInfo");
        MultiUsersManager.INSTANCE.getCurrentUserForUserInfo(responseCallback);
    }
}
