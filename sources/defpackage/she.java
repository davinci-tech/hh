package defpackage;

import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class she extends shd<String> {
    private int b;

    static /* synthetic */ int a(she sheVar) {
        int i = sheVar.b;
        sheVar.b = i + 1;
        return i;
    }

    public she() {
        super(String.class, "/dataOpen/wechat/bindDeviceTicket");
        this.b = 0;
    }

    public void a(final Map<String, String> map, final ICloudOperationResult<String> iCloudOperationResult) {
        if (this.b == 1) {
            ReleaseLogUtil.e("HiH_BindDeviceService", "retry bindDevice");
        }
        String str = GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + this.urlPath;
        Map<String, Object> c = c(map);
        LogUtil.c("BindDeviceService", "Req body = ", lql.b(c));
        lqi.d().b(str, d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015)), lql.b(c), String.class, new ResultCallback<String>() { // from class: she.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                LogUtil.a("BindDeviceService", "bindDevice onSuccess:", str2);
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    int optInt = jSONObject.optInt("resultCode", -1);
                    ReleaseLogUtil.e("BindDeviceService", "bindDevice resultCode:", Integer.valueOf(optInt));
                    if (optInt != 0) {
                        if ((optInt == 1002 || optInt == 1005) && she.this.b < 1) {
                            ReleaseLogUtil.d("HiH_BindDeviceService", "bindDevice failed, at is expired, retry once again");
                            she.a(she.this);
                            she.this.a(map, iCloudOperationResult);
                        } else {
                            if (optInt != 30005 && optInt != 112000000 && optInt != 112000090 && optInt != 1001) {
                                if (optInt != 9999 && optInt != 40060) {
                                    iCloudOperationResult.operationResult(null, "server error", false);
                                    she.this.b = 0;
                                }
                                iCloudOperationResult.operationResult(null, "wechat error", false);
                                she.this.b = 0;
                            }
                            iCloudOperationResult.operationResult(null, "bind device error", false);
                            she.this.b = 0;
                        }
                    } else {
                        iCloudOperationResult.operationResult(str2, jSONObject.optString("ticket"), true);
                        she.this.b = 0;
                    }
                } catch (JSONException e) {
                    iCloudOperationResult.operationResult(null, e.getMessage(), false);
                    she.this.b = 0;
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("BindDeviceService", "bindDevice onFailure:", th.getMessage());
                if (th instanceof lqj) {
                    ReleaseLogUtil.c("HiH_BindDeviceService", "bindDevice responseCode: ", Integer.valueOf(((lqj) th).e()));
                    iCloudOperationResult.operationResult(null, "server error", false);
                } else {
                    iCloudOperationResult.operationResult(null, th.getMessage(), false);
                }
                she.this.b = 0;
            }
        });
    }

    private Map<String, String> d(String str) {
        Map<String, String> createHeader = super.createHeader();
        createHeader.put("Cache-Control", " no-cache");
        createHeader.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + str);
        return createHeader;
    }

    private Map<String, Object> c(Map<String, String> map) {
        Map<String, Object> createParams = super.createParams();
        createParams.putAll(map);
        createParams.put("siteId", super.getParamSiteID());
        return createParams;
    }
}
