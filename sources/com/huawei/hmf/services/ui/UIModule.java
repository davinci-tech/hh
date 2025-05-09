package com.huawei.hmf.services.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.hmf.services.ApiSpec;
import com.huawei.hmf.services.Module;
import com.huawei.hmf.services.inject.InjectBindingRegistry;
import com.huawei.hmf.services.inject.ModuleInjection;
import com.huawei.hmf.services.inject.SelectorImpl;
import com.huawei.hmf.services.ui.internal.ActivityData;
import com.huawei.hmf.services.ui.internal.CallingInfo;
import com.huawei.hmf.services.ui.internal.FragmentData;
import com.huawei.hmf.services.ui.internal.PojoGenerator;

/* loaded from: classes9.dex */
public class UIModule {
    private UIModuleSpec mApiSpec;
    private InjectBindingRegistry mInjectBindingRegistry;
    private boolean mIsInjected;
    private String mModuleName;
    private Object mProtocol;

    @Deprecated
    public UIModule(Module module, ApiSpec apiSpec) {
        this(module.getName(), apiSpec);
    }

    public UIModule(Module module, UIModule uIModule) {
        this(module, uIModule.getUIModuleSpec());
    }

    public UIModule(String str, ApiSpec apiSpec) {
        this.mIsInjected = false;
        if (!(apiSpec instanceof UIModuleSpec)) {
            throw new ClassCastException("apiSpec can not cast to UIModuleSpec");
        }
        this.mApiSpec = (UIModuleSpec) apiSpec;
        this.mInjectBindingRegistry = new InjectBindingRegistry(str);
        this.mModuleName = str;
    }

    public <T> T createProtocol() {
        if (this.mApiSpec.mProtocol.isInterface()) {
            PojoGenerator pojoGenerator = new PojoGenerator(this.mApiSpec.mProtocol);
            this.mProtocol = pojoGenerator;
            return (T) pojoGenerator.get();
        }
        try {
            T t = (T) this.mApiSpec.mProtocol.newInstance();
            this.mProtocol = t;
            return t;
        } catch (IllegalAccessException | InstantiationException unused) {
            return null;
        }
    }

    public Intent getIntent(Context context) {
        buildInjectData(this.mInjectBindingRegistry);
        ActivityData activityData = new ActivityData(this.mInjectBindingRegistry, this.mModuleName);
        activityData.setProtocol(this.mProtocol);
        activityData.setResultType(this.mApiSpec.mResult);
        CallingInfo callingInfo = new CallingInfo();
        callingInfo.setModule(this.mModuleName);
        callingInfo.setPackageName(context.getPackageName());
        activityData.setCallingInfo(callingInfo);
        Intent intent = activityData.toIntent();
        if (this.mApiSpec.getType() != null) {
            intent.setClass(context, this.mApiSpec.getType());
        }
        return intent;
    }

    protected Bundle getAndRemoveActivityOptions(Intent intent) {
        LauncherOptions createFrom = LauncherOptions.createFrom(intent);
        Bundle activityOptions = createFrom.getActivityOptions();
        createFrom.removeActivityOptions();
        return activityOptions;
    }

    protected void startActivity(Context context, Intent intent) {
        Bundle bundle;
        Intent intent2 = getIntent(context);
        if (intent != null) {
            bundle = getAndRemoveActivityOptions(intent);
            intent2.fillIn(intent, 0);
        } else {
            bundle = null;
        }
        if (intent2.getFlags() == 0 && !(context instanceof Activity)) {
            intent2.setFlags(268435456);
        }
        context.startActivity(intent2, bundle);
    }

    protected void startActivityForResult(Context context, Intent intent, int i) {
        Bundle bundle;
        Activity activity = (Activity) context;
        Intent intent2 = getIntent(context);
        if (intent != null) {
            bundle = getAndRemoveActivityOptions(intent);
            intent2.fillIn(intent, 0);
        } else {
            bundle = null;
        }
        Fragment injectIfNeededIn = ActivityResultFragment.injectIfNeededIn(activity, i);
        if (injectIfNeededIn != null) {
            activity.startActivityFromFragment(injectIfNeededIn, intent2, i, bundle);
        }
    }

    protected void buildInjectData(InjectBindingRegistry injectBindingRegistry) {
        SelectorImpl selectorImpl = (SelectorImpl) ModuleInjection.selector(this);
        if (selectorImpl.isEmpty()) {
            return;
        }
        injectBindingRegistry.add(selectorImpl.getValue());
    }

    protected Bundle getBundle(Context context) {
        buildInjectData(this.mInjectBindingRegistry);
        FragmentData fragmentData = new FragmentData(this.mInjectBindingRegistry, this.mModuleName);
        fragmentData.setProtocol(this.mProtocol);
        return fragmentData.toBundle();
    }

    public boolean isInjected() {
        return this.mIsInjected;
    }

    public void setInjected(boolean z) {
        this.mIsInjected = z;
    }

    public UIModuleSpec getUIModuleSpec() {
        return this.mApiSpec;
    }

    public boolean isActivityModule() {
        return Activity.class.isAssignableFrom(this.mApiSpec.getType());
    }
}
