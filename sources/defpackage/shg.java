package defpackage;

import com.google.gson.Gson;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import java.util.Map;

/* loaded from: classes8.dex */
public class shg<T> extends shd<T> {
    public shg(Class<T> cls, String str) {
        super(cls, str);
    }

    public void d(Map<String, Object> map, final ICloudOperationResult<T> iCloudOperationResult) {
        LogUtil.a("AuthorizeService", "enter getAuthorizeToken()");
        lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + this.urlPath, createHeader(), lql.b(addCommonParams(map)), this.clazz, new ResultCallback<T>() { // from class: shg.3
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(T t) {
                String json = new Gson().toJson(t);
                LogUtil.a("AuthorizeService", "getAuthorizeToken result:", json);
                iCloudOperationResult.operationResult(t, json, true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("AuthorizeService", "getAuthorizeToken onFailure:", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }
}
