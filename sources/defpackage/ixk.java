package defpackage;

import android.content.Context;
import com.huawei.health.adapterhealthmgr.api.AdapterHealthMgrApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.pluginbase.PluginBaseAdapter;
import java.util.Map;

@ApiDefine(uri = AdapterHealthMgrApi.class)
/* loaded from: classes5.dex */
public class ixk implements AdapterHealthMgrApi {
    @Override // com.huawei.health.adapterhealthmgr.api.AdapterHealthMgrApi
    public Map<String, String> getAppData(String[] strArr) {
        return sqf.e(strArr);
    }

    @Override // com.huawei.health.adapterhealthmgr.api.AdapterHealthMgrApi
    public PluginBaseAdapter getOperationAdapterImpl(Context context) {
        return PluginOperationAdapterImpl.getInstance(context);
    }

    @Override // com.huawei.health.adapterhealthmgr.api.AdapterHealthMgrApi
    public PluginBaseAdapter getAchieveAdapterImpl() {
        return new PluginAchieveAdapterImpl();
    }
}
