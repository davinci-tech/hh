package com.huawei.hmf.orb.aidl;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.huawei.hmf.repository.ComponentRepository;
import com.huawei.hmf.services.Module;
import com.huawei.hmf.services.codec.Variant;
import com.huawei.hmf.services.internal.ApplicationContext;
import com.huawei.hmf.services.ui.UIModule;
import java.util.List;

/* loaded from: classes9.dex */
public class RemoteActivity implements IRemoteActivity {
    public static final RemoteTargetFactory FACTORY = new RemoteTargetFactory() { // from class: com.huawei.hmf.orb.aidl.RemoteActivity.1
        @Override // com.huawei.hmf.orb.aidl.RemoteTargetFactory
        public NamingRemoteTarget create(String str, List<Variant> list) {
            return new NamingRemoteTarget(new RemoteActivity(str, (String) list.get(0).cast(String.class)));
        }
    };
    private String mAlias;
    private String mModule;

    private RemoteActivity(String str, String str2) {
        this.mModule = str;
        this.mAlias = str2;
    }

    @Override // com.huawei.hmf.orb.aidl.IRemoteActivity
    public PendingIntent getActivity(int i) {
        UIModule createUIModule;
        Module lookup = ComponentRepository.getRepository().lookup(this.mModule);
        if (lookup == null || (createUIModule = lookup.createUIModule(this.mAlias)) == null) {
            return null;
        }
        Class<?> type = createUIModule.getUIModuleSpec().getType();
        Context context = ApplicationContext.getContext();
        return PendingIntent.getActivity(context, i, new Intent(context, type), 1073741824);
    }
}
