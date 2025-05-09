package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.pressure.PressureApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.ui.main.stories.health.pressure.pressurejs.PressureJsApi;

@ApiDefine(uri = PressureApi.class)
@Singleton
/* loaded from: classes6.dex */
public class qmj implements PressureApi {
    @Override // com.huawei.health.pressure.PressureApi
    public Class<? extends JsBaseModule> getPressureJsApi() {
        return PressureJsApi.class;
    }

    @Override // com.huawei.health.pressure.PressureApi
    public Class<? extends JsBaseModule> getPressureNewJsApi() {
        return qmi.class;
    }

    @Override // com.huawei.health.pressure.PressureApi
    public void gotoPressure(Context context, Bundle bundle) {
        qmf.dFD_(context, bundle);
    }

    @Override // com.huawei.health.pressure.PressureApi
    public void getSupportPressureDevice(IBaseResponseCallback iBaseResponseCallback) {
        psi.c(iBaseResponseCallback);
    }
}
