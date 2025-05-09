package com.huawei.hmf.orb.aidl;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.hmf.orb.RemoteInvoker;
import com.huawei.hmf.repository.ComponentRepository;
import com.huawei.hmf.services.ApiSpec;
import com.huawei.hmf.services.Module;
import com.huawei.hmf.services.inject.InjectBindingRegistry;
import com.huawei.hmf.services.inject.InjectValue;
import com.huawei.hmf.services.inject.ModuleInjection;
import com.huawei.hmf.services.inject.SelectorImpl;
import com.huawei.hmf.services.internal.ApplicationContext;
import com.huawei.hmf.services.ui.ActivityResultFragment;
import com.huawei.hmf.services.ui.UIModule;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes9.dex */
public class RemoteUIModule extends UIModule {
    private static final String TAG = "RemoteUIModule";
    private PendingIntent mPendingIntent;
    private final IRemoteActivity remoteActivity;

    public RemoteUIModule(Module module, ApiSpec apiSpec, RemoteInvoker remoteInvoker, String str) {
        super(module, apiSpec);
        this.remoteActivity = (IRemoteActivity) NamingRemoteProxy.create(remoteInvoker, new Class[]{IRemoteActivity.class}, IRemoteActivity.Uri, str);
    }

    public RemoteUIModule(Module module, UIModule uIModule, PendingIntent pendingIntent) {
        super(module, uIModule);
        this.remoteActivity = null;
        this.mPendingIntent = pendingIntent;
    }

    @Override // com.huawei.hmf.services.ui.UIModule
    public void startActivity(Context context, Intent intent) {
        Bundle bundle;
        PendingIntent pendingIntent = getPendingIntent(hashCode());
        if (pendingIntent != null) {
            Intent intent2 = getIntent(context);
            if (intent != null) {
                Bundle andRemoveActivityOptions = getAndRemoveActivityOptions(intent);
                intent2.fillIn(intent, 0);
                bundle = andRemoveActivityOptions;
            } else {
                bundle = null;
            }
            try {
                context.startIntentSender(pendingIntent.getIntentSender(), intent2, 0, 0, 0, bundle);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.huawei.hmf.services.ui.UIModule
    public void startActivityForResult(Context context, Intent intent, int i) {
        Bundle bundle;
        PendingIntent pendingIntent = getPendingIntent(i);
        if (pendingIntent != null) {
            Intent intent2 = getIntent(context);
            if (intent != null) {
                Bundle andRemoveActivityOptions = getAndRemoveActivityOptions(intent);
                intent2.fillIn(intent, 0);
                bundle = andRemoveActivityOptions;
            } else {
                bundle = null;
            }
            try {
                Fragment injectIfNeededIn = ActivityResultFragment.injectIfNeededIn((Activity) context, i);
                if (injectIfNeededIn == null) {
                    return;
                }
                injectIfNeededIn.startIntentSenderForResult(pendingIntent.getIntentSender(), i, intent2, 0, 0, 0, bundle);
            } catch (Exception e) {
                Log.e(TAG, "start remote's activity failed, " + e.getMessage());
            }
        }
    }

    PendingIntent getPendingIntent(int i) {
        PendingIntent pendingIntent = this.mPendingIntent;
        return pendingIntent != null ? pendingIntent : this.remoteActivity.getActivity(i);
    }

    @Override // com.huawei.hmf.services.ui.UIModule
    public void buildInjectData(InjectBindingRegistry injectBindingRegistry) {
        Class<?> protocol;
        SelectorImpl selectorImpl = (SelectorImpl) ModuleInjection.selector(this);
        if (!selectorImpl.isEmpty()) {
            for (Map.Entry<String, Object> entry : selectorImpl.getValue().entrySet()) {
                String key = entry.getKey();
                Class cls = (Class) entry.getValue();
                if (!Activity.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("Can not inject non Activity `" + cls + "` to remote.");
                }
                injectBindingRegistry.add(key, new InjectValue(InjectValue.Type.EXPLICIT_INJECT, makePendingIntent(cls, hashCode(key), AMapEngineUtils.HALF_MAX_P20_WIDTH)));
            }
        }
        if (!isInjected() || (protocol = getUIModuleSpec().getProtocol()) == null) {
            return;
        }
        for (String str : ModuleInjection.getInjectNames(protocol)) {
            UIModule createUIModule = ComponentRepository.getRepository().lookup(injectBindingRegistry.getModuleName()).createUIModule(str);
            injectBindingRegistry.add(str, new InjectValue(InjectValue.Type.IMPLICIT_INJECT, makePendingIntent(createUIModule.getUIModuleSpec().getType(), createUIModule.hashCode(), AMapEngineUtils.HALF_MAX_P20_WIDTH)));
        }
    }

    private PendingIntent makePendingIntent(Class cls, int i, int i2) {
        Context context = ApplicationContext.getContext();
        return PendingIntent.getActivity(context, i, new Intent(context, (Class<?>) cls), i2);
    }

    public int hashCode(Object obj) {
        return Arrays.hashCode(new Object[]{this, obj});
    }
}
