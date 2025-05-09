package defpackage;

import com.google.gson.Gson;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class shn<T> extends shd<T> {
    private static final String TAG = "TokenService";

    public shn(Class<T> cls, String str) {
        super(cls, str);
    }

    public void getAuthorizeToken(final ICloudOperationResult<T> iCloudOperationResult) {
        LogUtil.a(TAG, "enter getAuthorizeToken()");
        GRSManager.a(BaseApplication.getContext()).e("healthCloudUrl", new GrsQueryCallback() { // from class: shn.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                lqi.d().b(str + shn.this.urlPath, shn.this.createHeader(), lql.b(shn.this.createParams()), shn.this.clazz, new ResultCallback<T>() { // from class: shn.3.4
                    @Override // com.huawei.networkclient.ResultCallback
                    public void onSuccess(T t) {
                        String json = new Gson().toJson(t);
                        LogUtil.a(shn.TAG, "getAuthorizeToken result:", json);
                        iCloudOperationResult.operationResult(t, json, true);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        LogUtil.a(shn.TAG, "getAuthorizeToken onFailure:", th.getMessage());
                        iCloudOperationResult.operationResult(null, th.getMessage(), false);
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h(shn.TAG, "onCallBackFail:", Integer.valueOf(i));
            }
        });
    }
}
