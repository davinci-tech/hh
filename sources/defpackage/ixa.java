package defpackage;

import android.content.Context;
import com.huawei.health.adapterwear.api.AdapterWearMgrApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.pluginbase.PluginBaseAdapter;

@ApiDefine(uri = AdapterWearMgrApi.class)
@Singleton
/* loaded from: classes4.dex */
public class ixa implements AdapterWearMgrApi {
    @Override // com.huawei.health.adapterwear.api.AdapterWearMgrApi
    public PluginBaseAdapter getPayAdapterImpl(Context context) {
        return ixb.p();
    }
}
