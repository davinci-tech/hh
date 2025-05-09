package defpackage;

import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class shk extends shd<String> {
    public shk() {
        super(String.class, "/dataOpen/wechat/supportedProductIds");
    }

    public void a(final ICloudOperationResult<List<String>> iCloudOperationResult) {
        lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + this.urlPath, b(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015)), lql.b(createParams()), String.class, new ResultCallback<String>() { // from class: shk.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("SupportedProductIdsService", "onSuccess:", "resp=" + str);
                iCloudOperationResult.operationResult(shk.this.c(str), str, true);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("SupportedProductIdsService", "onFailure:", th.getMessage());
                iCloudOperationResult.operationResult(null, th.getMessage(), false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> c(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("wiseDeviceProdIds");
            if (optJSONArray != null && optJSONArray.length() != 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            return arrayList;
        } catch (JSONException e) {
            LogUtil.b("SupportedProductIdsService", "parseProductIds exception", e.getMessage());
            return arrayList;
        }
    }

    private Map<String, String> b(String str) {
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
