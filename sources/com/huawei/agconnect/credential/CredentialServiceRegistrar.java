package com.huawei.agconnect.credential;

import android.content.Context;
import com.huawei.agconnect.AGCInitFinishManager;
import com.huawei.agconnect.common.api.HaConnector;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.core.Service;
import com.huawei.agconnect.core.ServiceRegistrar;
import com.huawei.agconnect.core.service.EndpointService;
import com.huawei.agconnect.core.service.auth.CredentialsProvider;
import com.huawei.agconnect.credential.obs.al;
import com.huawei.agconnect.credential.obs.ar;
import com.huawei.agconnect.credential.obs.at;
import com.huawei.agconnect.credential.obs.v;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class CredentialServiceRegistrar implements ServiceRegistrar {
    @Override // com.huawei.agconnect.core.ServiceRegistrar
    public void initialize(final Context context) {
        Logger.d("CredentialServiceRegistrar", "initialize");
        al.a(context);
        SharedPrefUtil.init(context);
        v.a(context);
        AGCInitFinishManager.getInstance().addAGCInitFinishCallback(new AGCInitFinishManager.AGCInitFinishCallback() { // from class: com.huawei.agconnect.credential.CredentialServiceRegistrar.1
            @Override // com.huawei.agconnect.AGCInitFinishManager.AGCInitFinishCallback
            public void onFinish() {
                HaConnector.getInstance().init(context);
            }
        });
    }

    @Override // com.huawei.agconnect.core.ServiceRegistrar
    public List<Service> getServices(Context context) {
        return Arrays.asList(Service.builder((Class<?>) CredentialsProvider.class, (Class<?>) ar.class).build(), Service.builder((Class<?>) EndpointService.class, (Class<?>) at.class).isSingleton(true).build());
    }
}
