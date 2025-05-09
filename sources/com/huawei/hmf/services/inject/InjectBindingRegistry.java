package com.huawei.hmf.services.inject;

import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.services.inject.InjectValue;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class InjectBindingRegistry implements IMessageEntity {
    public static final transient String DESCRIPTOR = "com.huawei.hmf.services.inject.InjectBindingRegistry";
    private String mModuleName;
    private Map<String, Object> mExplicitInjectMap = new HashMap();
    private Map<String, Object> mImplicitInjectMap = new HashMap();
    private boolean mFromExternal = false;

    public InjectBindingRegistry() {
    }

    public InjectBindingRegistry(String str) {
        this.mModuleName = str;
    }

    public String getModuleName() {
        return this.mModuleName;
    }

    public boolean isFromExternal() {
        return this.mFromExternal;
    }

    public void setFromExternal(boolean z) {
        this.mFromExternal = z;
    }

    public void add(Map<String, Object> map) {
        this.mExplicitInjectMap.putAll(map);
    }

    public void add(String str, InjectValue injectValue) {
        if (injectValue.getType() == InjectValue.Type.EXPLICIT_INJECT) {
            this.mExplicitInjectMap.put(str, injectValue.getValue());
        } else {
            this.mImplicitInjectMap.put(str, injectValue.getValue());
        }
    }

    public InjectValue get(String str) {
        Object obj = this.mExplicitInjectMap.get(str);
        if (obj != null) {
            return new InjectValue(InjectValue.Type.EXPLICIT_INJECT, obj);
        }
        Object obj2 = this.mImplicitInjectMap.get(str);
        if (obj2 != null) {
            return new InjectValue(InjectValue.Type.IMPLICIT_INJECT, obj2);
        }
        return null;
    }
}
