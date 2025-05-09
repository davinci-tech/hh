package com.huawei.operation.h5pro.jsmodules.compact;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.JsDataCallback;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.h5pro.H5MiniProgramSecurityHelper;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginbase.PluginBaseAdapter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JsDataCompact implements JsDataCallback {
    private static final String TAG = "PluginOperation_JsDataCompact";
    private PluginOperationAdapter mAdapter;
    private H5ProInstance mInstance;
    private H5ProJsCbkInvoker<Object> mJsInvoker;

    @Override // com.huawei.operation.adapter.JsDataCallback
    public void callJsSportDataFunction(String str, String str2, String str3, boolean z) {
    }

    public JsDataCompact(Context context, H5ProInstance h5ProInstance) {
        this.mJsInvoker = h5ProInstance.getJsCbkInvoker();
        this.mInstance = h5ProInstance;
        PluginBaseAdapter adapter = PluginOperation.getInstance(context).getAdapter();
        this.mAdapter = adapter instanceof PluginOperationAdapter ? (PluginOperationAdapter) adapter : null;
    }

    @Override // com.huawei.operation.adapter.JsDataCallback
    public void callJsServiceFunction(String str, String str2, String str3, String str4, boolean z) {
        H5ProInstance h5ProInstance;
        LogUtil.a(TAG, "callJsServiceFunction:", str, str2);
        if (TextUtils.isEmpty(str4)) {
            LogUtil.b(TAG, "callJsServiceFunction callbackFunc is null");
            return;
        }
        if (!z && ((h5ProInstance = this.mInstance) == null || !H5MiniProgramSecurityHelper.isSuperTrustedMiniProgram(h5ProInstance.getAppInfo()))) {
            LogUtil.h(TAG, "callJsServiceFunction is not legal");
            this.mJsInvoker.invokeJsFunc(str4, 1001, Constants.NULL);
        } else if (JsUtil.ServiceType.DATA.equals(str) && JsUtil.DataFunc.ANNUAL_REPORT_DATA.equals(str2)) {
            getAnnualReport(str3, str4);
        }
    }

    private void getAnnualReport(String str, final String str2) {
        LogUtil.a(TAG, "getAnnualReport functionRes : ", str2);
        try {
            this.mAdapter.getAnnualReport(new JSONObject(str).getInt(JsUtil.ANNUAL_YEAR), new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.compact.JsDataCompact.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a(JsDataCompact.TAG, "getAnnualReport errCode = ", Integer.valueOf(i));
                    if (i != 0 || obj == null) {
                        JsDataCompact.this.mJsInvoker.invokeJsFunc(str2, Integer.valueOf(i), Constants.NULL);
                    } else {
                        JsDataCompact.this.mJsInvoker.invokeJsFunc(str2, Integer.valueOf(i), obj.toString());
                    }
                }
            });
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getAnnualReport parse param json fail!");
            this.mJsInvoker.invokeJsFunc(str2, 1001, Constants.NULL);
        }
    }
}
