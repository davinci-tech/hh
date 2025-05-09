package defpackage;

import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class shh extends shd<Boolean> {
    public shh() {
        super(Boolean.class, "/dataOpen/wechat/hasBoundDevices");
    }

    public void e(final ICloudOperationResult<Boolean> iCloudOperationResult) {
        lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + this.urlPath, a(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015)), lql.b(createParams()), String.class, new ResultCallback<String>() { // from class: shh.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("CheckBoundDeviceService", "checkBoundDevice onSuccess:", str);
                try {
                    iCloudOperationResult.operationResult(Boolean.valueOf(new JSONObject(str).optBoolean("result")), str, true);
                } catch (JSONException e) {
                    iCloudOperationResult.operationResult(false, e.getMessage(), false);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("CheckBoundDeviceService", "checkBoundDevice onFailure:", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    private Map<String, String> a(String str) {
        Map<String, String> createHeader = super.createHeader();
        createHeader.put("Cache-Control", " no-cache");
        createHeader.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + str);
        return createHeader;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.shd
    public Map<String, Object> createParams() {
        Map<String, Object> createParams = super.createParams();
        createParams.put("siteId", super.getParamSiteID());
        return createParams;
    }
}
