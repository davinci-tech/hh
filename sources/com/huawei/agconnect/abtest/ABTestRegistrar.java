package com.huawei.agconnect.abtest;

import android.content.Context;
import com.huawei.agconnect.core.Service;
import com.huawei.agconnect.core.ServiceRegistrar;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class ABTestRegistrar implements ServiceRegistrar {
    @Override // com.huawei.agconnect.core.ServiceRegistrar
    public void initialize(Context context) {
    }

    @Override // com.huawei.agconnect.core.ServiceRegistrar
    public List<Service> getServices(Context context) {
        return new ArrayList();
    }
}
