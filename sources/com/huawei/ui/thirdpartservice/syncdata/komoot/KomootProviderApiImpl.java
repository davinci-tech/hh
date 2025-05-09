package com.huawei.ui.thirdpartservice.syncdata.komoot;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.thirdpartservice.OauthStatusCallback;
import com.huawei.thirdpartservice.komootapi.KomootProviderApi;
import com.huawei.ui.thirdpartservice.activity.komoot.KomootConnectActivity;
import com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback;
import com.huawei.ui.thirdpartservice.syncdata.net.SyncDataToKomootApi;
import defpackage.lqi;
import defpackage.sir;
import defpackage.siu;

@ApiDefine(uri = KomootProviderApi.class)
/* loaded from: classes7.dex */
public class KomootProviderApiImpl implements KomootProviderApi {

    public interface DataTaskAction {
        void doAction(siu siuVar, sir sirVar, ResultCallback<String> resultCallback);
    }

    @Override // com.huawei.thirdpartservice.komootapi.KomootProviderApi
    public void performOauth(Context context) {
        context.startActivity(new Intent(context, (Class<?>) KomootConnectActivity.class));
    }

    @Override // com.huawei.thirdpartservice.komootapi.KomootProviderApi
    public void isOauth(final OauthStatusCallback oauthStatusCallback) {
        sir.c().checkConnectStatus(new RefreshTokenCallback() { // from class: siv
            @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
            public final void refreshResult(boolean z, boolean z2) {
                OauthStatusCallback.this.onOauthStatusCallback(r1 && r2);
            }
        });
    }

    @Override // com.huawei.thirdpartservice.komootapi.KomootProviderApi
    public void getRoadList(final String str, final int i, ResultCallback<String> resultCallback) {
        b(resultCallback, new DataTaskAction() { // from class: six
            @Override // com.huawei.ui.thirdpartservice.syncdata.komoot.KomootProviderApiImpl.DataTaskAction
            public final void doAction(siu siuVar, sir sirVar, ResultCallback resultCallback2) {
                siuVar.d(sirVar.getOpenId(), str, i, resultCallback2);
            }
        });
    }

    private void b(final ResultCallback<String> resultCallback, DataTaskAction dataTaskAction) {
        final sir c = sir.c();
        final b bVar = new b();
        ResultCallback<String> resultCallback2 = new ResultCallback<String>() { // from class: com.huawei.ui.thirdpartservice.syncdata.komoot.KomootProviderApiImpl.2
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                if (response.getCode() == 401) {
                    c.refreshAccessToken(bVar);
                } else {
                    resultCallback.onResponse(response);
                }
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        };
        bVar.c(dataTaskAction);
        bVar.a(c);
        bVar.b(resultCallback2);
        c.checkConnectStatus(bVar);
    }

    @Override // com.huawei.thirdpartservice.komootapi.KomootProviderApi
    public void getRoad(final String str, ResultCallback<String> resultCallback) {
        b(resultCallback, new DataTaskAction() { // from class: sit
            @Override // com.huawei.ui.thirdpartservice.syncdata.komoot.KomootProviderApiImpl.DataTaskAction
            public final void doAction(siu siuVar, sir sirVar, ResultCallback resultCallback2) {
                siuVar.b(str, (ResultCallback<String>) resultCallback2);
            }
        });
    }

    static class b implements RefreshTokenCallback {

        /* renamed from: a, reason: collision with root package name */
        private ResultCallback<String> f10566a;
        private siu b = new siu((SyncDataToKomootApi) lqi.d().b(SyncDataToKomootApi.class));
        private sir c;
        private DataTaskAction e;

        b() {
        }

        void c(DataTaskAction dataTaskAction) {
            this.e = dataTaskAction;
        }

        void b(ResultCallback<String> resultCallback) {
            this.f10566a = resultCallback;
        }

        void a(sir sirVar) {
            this.c = sirVar;
        }

        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
        public void refreshResult(boolean z, boolean z2) {
            if (z && z2) {
                if (TextUtils.isEmpty(this.c.getAccessToken())) {
                    this.c.refreshAccessToken(this);
                } else {
                    this.e.doAction(this.b, this.c, this.f10566a);
                }
            }
        }
    }
}
