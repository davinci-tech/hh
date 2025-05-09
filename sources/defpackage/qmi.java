package defpackage;

import android.content.Intent;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateActivity;
import com.huawei.ui.main.stories.health.pressure.provider.PressureEntryProvider;

/* loaded from: classes8.dex */
public class qmi extends JsBaseModule {
    @JavascriptInterface
    public void jumpToPressureCalibration(final long j) {
        LogUtil.a("PressureNewJsApi", "jumpToPressureCalibration");
        PressureEntryProvider.a(this.mContext, new IBaseResponseCallback() { // from class: qmh
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qmi.this.c(j, i, obj);
            }
        }, new ServiceApiCallback<String>() { // from class: qmi.1
            @Override // com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                qmi.this.onSuccessCallback(j, str);
            }

            @Override // com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback
            public void onFailure(int i, String str) {
                qmi.this.onFailureCallback(j, str);
            }
        });
    }

    /* synthetic */ void c(long j, int i, Object obj) {
        Intent intent = new Intent(this.mContext, (Class<?>) PressureCalibrateActivity.class);
        intent.putExtra("from_health_record", true);
        gnm.aPB_(this.mContext, intent);
        onSuccessCallback(j);
    }
}
