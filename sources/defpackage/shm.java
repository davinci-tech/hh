package defpackage;

import com.google.gson.Gson;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;

/* loaded from: classes7.dex */
public class shm extends shd<skb> {
    private int c;

    static /* synthetic */ int d(shm shmVar) {
        int i = shmVar.c;
        shmVar.c = i + 1;
        return i;
    }

    shm() {
        super(skb.class, "/dataOpen/wechat/supportedDevices");
        this.c = 0;
    }

    public void a(final ICloudOperationResult<skb> iCloudOperationResult) {
        if (this.c == 1) {
            ReleaseLogUtil.e("HiH_SupportDeviceService", "retry requestSupportDevice");
        }
        lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + this.urlPath, c(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015)), lql.b(createParams()), skb.class, new ResultCallback<skb>() { // from class: shm.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(skb skbVar) {
                if (skbVar == null) {
                    iCloudOperationResult.operationResult(null, "server error", false);
                    return;
                }
                int resultCode = skbVar.getResultCode();
                ReleaseLogUtil.e("HiH_SupportDeviceService", "requestSupportDevice resultCode: ", Integer.valueOf(resultCode));
                if (resultCode != 0) {
                    if ((resultCode == 1002 || resultCode == 1005) && shm.this.c < 1) {
                        ReleaseLogUtil.d("HiH_SupportDeviceService", "requestSupportDevice failed, at is expired, retry once again");
                        shm.d(shm.this);
                        shm.this.a(iCloudOperationResult);
                        return;
                    } else {
                        ReleaseLogUtil.c("HiH_SupportDeviceService", "requestSupportDevice failed, resultCode: ", Integer.valueOf(resultCode));
                        iCloudOperationResult.operationResult(null, "server error", false);
                        shm.this.c = 0;
                        return;
                    }
                }
                String json = new Gson().toJson(skbVar);
                LogUtil.a("SupportDeviceService", "onSuccess:", json);
                iCloudOperationResult.operationResult(skbVar, json, true);
                shm.this.c = 0;
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("SupportDeviceService", "onFailure:", th.getMessage());
                if (th instanceof lqj) {
                    ReleaseLogUtil.c("HiH_SupportDeviceService", "requestSupportDevice responseCode: ", Integer.valueOf(((lqj) th).e()));
                    iCloudOperationResult.operationResult(null, "server error", false);
                } else {
                    iCloudOperationResult.operationResult(null, th.getMessage(), false);
                }
                shm.this.c = 0;
            }
        });
    }

    private Map<String, String> c(String str) {
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
