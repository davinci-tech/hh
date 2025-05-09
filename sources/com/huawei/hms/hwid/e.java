package com.huawei.hms.hwid;

import android.content.Context;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.entity.core.CoreNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.utils.Util;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class e extends HuaweiApi<a> {

    /* renamed from: a, reason: collision with root package name */
    private static final Api<a> f4646a = new Api<>(HuaweiApiAvailability.HMS_API_NAME_GAME);
    private static final a c = new a();
    private Context b;

    public static class a implements Api.ApiOptions.Optional {
    }

    @Override // com.huawei.hms.common.HuaweiApi
    public int getApiLevel() {
        return 1;
    }

    public e(Context context) {
        super(context, f4646a, c, new d());
        this.b = context;
    }

    public void a() {
        as.b("AccountSignInNoticeClientImpl", "request Jos Notice.", true);
        String reportEntry = HiAnalyticsClient.reportEntry(getContext(), CoreNaming.GETNOTICE, AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("noticeType", 1);
            jSONObject.put("hmsSdkVersionName", "6.12.0.300");
            jSONObject.put("cpId", Util.getCpId(this.b));
            doWrite(new k(CoreNaming.GETNOTICE, jSONObject.toString(), reportEntry));
        } catch (JSONException unused) {
            as.c("AccountSignInNoticeClientImpl", "createParams Notice request meet JSONException.", true);
        }
    }
}
