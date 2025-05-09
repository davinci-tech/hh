package defpackage;

import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.util.SmartRulesApi;
import health.compact.a.SharedPreferenceManager;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = SmartRulesApi.class)
@Singleton
/* loaded from: classes5.dex */
public class kwo implements SmartRulesApi {
    @Override // com.huawei.hwsmartinteractmgr.util.SmartRulesApi
    public boolean isRuleOpen(int i, String str) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(i), str);
        if (!TextUtils.isEmpty(b)) {
            try {
                return new JSONObject(b).getBoolean(k.g);
            } catch (JSONException e) {
                LogUtil.b("SmarterUtils", "isRuleOpen JSONException exception = ", e.getMessage());
            }
        }
        return false;
    }

    @Override // com.huawei.hwsmartinteractmgr.util.SmartRulesApi
    public String getRuleData(int i, String str, String str2) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(i), str);
        if (!TextUtils.isEmpty(b)) {
            try {
                JSONObject jSONObject = new JSONObject(b);
                if (jSONObject.getBoolean(k.g)) {
                    return jSONObject.getJSONObject(e.n).getString(str2);
                }
            } catch (JSONException e) {
                LogUtil.b("SmarterUtils", "isRuleOpen JSONException exception = ", e.getMessage());
            }
        }
        return null;
    }

    @Override // com.huawei.hwsmartinteractmgr.util.SmartRulesApi
    public void deleteSmartMsgByType(int i) {
        kvt.e(BaseApplication.getContext()).b(i);
    }
}
