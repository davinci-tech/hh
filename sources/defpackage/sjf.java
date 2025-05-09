package defpackage;

import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.thirdpartservice.OauthStatusCallback;
import com.huawei.thirdpartservice.runtasticapi.RuntasticProviderApi;
import com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback;
import com.huawei.ui.thirdpartservice.syncdata.constants.RuntasticOauthConstant;
import java.util.Iterator;

@ApiDefine(uri = RuntasticProviderApi.class)
/* loaded from: classes7.dex */
public class sjf implements RuntasticProviderApi {
    @Override // com.huawei.thirdpartservice.runtasticapi.RuntasticProviderApi
    public void isOauth(final OauthStatusCallback oauthStatusCallback) {
        sjd.d().checkConnectStatus(new RefreshTokenCallback() { // from class: sjg
            @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
            public final void refreshResult(boolean z, boolean z2) {
                OauthStatusCallback.this.onOauthStatusCallback(r1 && r2);
            }
        });
    }

    @Override // com.huawei.thirdpartservice.runtasticapi.RuntasticProviderApi
    public boolean isSupportType(int i) {
        Iterator<Integer> it = RuntasticOauthConstant.SYNC_DATA_TYPE.iterator();
        while (it.hasNext()) {
            if (it.next().intValue() == i) {
                return true;
            }
        }
        return false;
    }
}
