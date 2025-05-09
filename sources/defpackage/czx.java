package defpackage;

import com.huawei.health.device.api.ResourceManagerApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hmf.annotation.ApiDefine;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApiDefine(uri = ResourceManagerApi.class)
/* loaded from: classes3.dex */
public class czx implements ResourceManagerApi {
    @Override // com.huawei.health.device.api.ResourceManagerApi
    public ArrayList<HealthDevice.HealthDeviceKind> getProductKinds(List<String> list) {
        LogUtil.d("ResourceManagerImpl", "run getProductKinds");
        ArrayList<HealthDevice.HealthDeviceKind> arrayList = new ArrayList<>(10);
        if (bkz.e(list)) {
            LogUtil.c("ResourceManagerImpl", "getProductKinds bindProductLists is empty");
            return arrayList;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            dcz d = ResourceManager.e().d(it.next());
            if (d != null && d.n() != null && d.n().d() != null && !d.n().d().trim().isEmpty()) {
                arrayList.add(d.l());
            }
        }
        return arrayList;
    }
}
