package defpackage;

import android.widget.LinearLayout;
import com.huawei.health.configuredpage.api.ConfiguredPageApi;
import com.huawei.health.configuredpage.api.ConfiguredPageDataCallback;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;

@ApiDefine(uri = ConfiguredPageApi.class)
@Singleton
/* loaded from: classes6.dex */
public class pfi implements ConfiguredPageApi {
    @Override // com.huawei.health.configuredpage.api.ConfiguredPageApi
    public void refreshConfiguredPageUi(int i, LinearLayout linearLayout) {
        pfe.dok_(i, linearLayout);
    }

    @Override // com.huawei.health.configuredpage.api.ConfiguredPageApi
    public void initOperationConfiguredPage(int i, LinearLayout linearLayout, ConfiguredPageDataCallback configuredPageDataCallback) {
        pfe.doh_(i, linearLayout, configuredPageDataCallback);
    }
}
