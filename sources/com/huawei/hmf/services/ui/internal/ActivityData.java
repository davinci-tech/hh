package com.huawei.hmf.services.ui.internal;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.hmf.annotation.ActivityDefine;
import com.huawei.hmf.services.codec.MessageCodec;
import com.huawei.hmf.services.inject.InjectBindingRegistry;
import com.huawei.hmf.services.inject.ModuleInjection;

/* loaded from: classes9.dex */
public class ActivityData {
    private InjectBindingRegistry mInjectBindingRegistry;
    private final SecurityIntent mIntent;

    public void free() {
    }

    public ActivityData(InjectBindingRegistry injectBindingRegistry, String str) {
        this(new Intent());
        this.mIntent.getIntent().putExtra(BundleKey.MODULE_NAME, str);
        this.mInjectBindingRegistry = injectBindingRegistry;
    }

    public ActivityData(Intent intent) {
        this.mIntent = SecurityIntent.from(intent);
    }

    public Intent toIntent() {
        return this.mIntent.getIntent();
    }

    public String getModuleName() {
        return this.mIntent.getStringExtra(BundleKey.MODULE_NAME);
    }

    public void setProtocol(Object obj) {
        MessageCodec messageCodec = new MessageCodec();
        if (obj != null) {
            this.mIntent.getIntent().putExtra("_protocol", messageCodec.encode(obj, new Bundle()));
        }
        this.mIntent.getIntent().putExtra(InjectBindingRegistry.DESCRIPTOR, messageCodec.encode(this.mInjectBindingRegistry, new Bundle()));
    }

    public void setCallingInfo(CallingInfo callingInfo) {
        MessageCodec messageCodec = new MessageCodec();
        if (callingInfo != null) {
            this.mIntent.getIntent().putExtra(CallingInfo.DESCRIPTOR, messageCodec.encode(callingInfo, new Bundle()));
        }
    }

    public CallingInfo getCallingInfo() {
        Bundle bundleExtra = this.mIntent.getBundleExtra(CallingInfo.DESCRIPTOR);
        if (bundleExtra == null) {
            return null;
        }
        return (CallingInfo) new MessageCodec().decode(bundleExtra, (Bundle) new CallingInfo());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T getProtocol(ActivityDefine activityDefine) {
        Object newInstance;
        Bundle bundleExtra = this.mIntent.getBundleExtra("_protocol");
        if (activityDefine.protocol().isInterface()) {
            newInstance = new PojoGenerator(activityDefine.protocol());
        } else {
            try {
                newInstance = activityDefine.protocol().newInstance();
            } catch (Exception unused) {
                return null;
            }
        }
        new MessageCodec().decode(bundleExtra, (Bundle) newInstance);
        T t = (T) inject(newInstance);
        return t instanceof PojoGenerator ? (T) ((PojoGenerator) t).get() : t;
    }

    public void setResultType(Class cls) {
        this.mIntent.getIntent().putExtra("__ResultClassname__", cls.getName());
    }

    public String getResultType() {
        return this.mIntent.getStringExtra("__ResultClassname__");
    }

    private Object inject(Object obj) {
        this.mInjectBindingRegistry = (InjectBindingRegistry) new MessageCodec().decode(this.mIntent.getBundleExtra(InjectBindingRegistry.DESCRIPTOR), (Bundle) new InjectBindingRegistry());
        return new ModuleInjection(this.mInjectBindingRegistry).inject(obj);
    }
}
