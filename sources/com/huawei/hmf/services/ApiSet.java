package com.huawei.hmf.services;

import com.huawei.hmf.annotation.ActivityDefine;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.FragmentDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hmf.services.ui.UIModuleSpec;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ApiSet {
    private Map<String, ApiSpec> mAliasApiMap;
    private Map<Class<?>, ApiSpec> mApiMap;

    public static class Builder {
        Map<String, ApiSpec> aliasApiMap;
        Map<Class<?>, ApiSpec> apiMap;
        boolean isAdded;
        ApiSpec lastCreatedApiSpec;

        public ApiSpec obtain(Class<?> cls) {
            if (((ApiDefine) cls.getAnnotation(ApiDefine.class)) != null) {
                ApiSpec apiSpec = new ApiSpec(cls);
                apiSpec.setSingleton(cls.getAnnotation(Singleton.class) != null);
                return apiSpec;
            }
            ActivityDefine activityDefine = (ActivityDefine) cls.getAnnotation(ActivityDefine.class);
            if (activityDefine != null) {
                return new UIModuleSpec(cls, activityDefine.protocol(), activityDefine.result());
            }
            FragmentDefine fragmentDefine = (FragmentDefine) cls.getAnnotation(FragmentDefine.class);
            if (fragmentDefine != null) {
                return new UIModuleSpec(cls, fragmentDefine.protocol(), null);
            }
            return null;
        }

        private Builder() {
            this.apiMap = new HashMap();
            this.aliasApiMap = new HashMap();
            this.lastCreatedApiSpec = null;
        }

        public Builder add(Class<?> cls, String str) {
            ApiSpec apiSpec;
            add(cls);
            if (this.isAdded && (apiSpec = this.lastCreatedApiSpec) != null) {
                apiSpec.setTypeName(str);
            }
            return this;
        }

        public Builder add(Class<?> cls) {
            this.isAdded = false;
            if (cls.isInterface()) {
                throw new IllegalArgumentException(cls.getName() + " can not be interface");
            }
            ApiDefine apiDefine = (ApiDefine) cls.getAnnotation(ApiDefine.class);
            if (apiDefine != null) {
                add(apiDefine.uri(), apiDefine.alias(), cls, cls.getAnnotation(Singleton.class) != null);
            } else {
                ActivityDefine activityDefine = (ActivityDefine) cls.getAnnotation(ActivityDefine.class);
                if (activityDefine != null) {
                    add(activityDefine.alias(), cls, activityDefine.protocol(), activityDefine.result());
                } else {
                    FragmentDefine fragmentDefine = (FragmentDefine) cls.getAnnotation(FragmentDefine.class);
                    if (fragmentDefine != null) {
                        add(fragmentDefine.alias(), cls, fragmentDefine.protocol(), (Class<?>) null);
                    } else {
                        throw new IllegalArgumentException(cls.getName() + " annotation is not present");
                    }
                }
            }
            return this;
        }

        public Builder add(Class<?> cls, String str, Class<?> cls2, boolean z) {
            this.isAdded = false;
            ApiSpec apiSpec = new ApiSpec(cls2);
            this.lastCreatedApiSpec = apiSpec;
            apiSpec.setSingleton(z);
            if (str != null && str.length() != 0) {
                this.aliasApiMap.put(ApiSet.getQualifiedAlias(str, cls), this.lastCreatedApiSpec);
            } else {
                this.apiMap.put(cls, this.lastCreatedApiSpec);
            }
            this.isAdded = true;
            return this;
        }

        public Builder add(String str, Class<?> cls, boolean z) {
            this.isAdded = false;
            if (str.length() > 0) {
                ApiSpec apiSpec = new ApiSpec(cls);
                this.lastCreatedApiSpec = apiSpec;
                apiSpec.setSingleton(z);
                this.aliasApiMap.put(str, this.lastCreatedApiSpec);
                this.isAdded = true;
            }
            return this;
        }

        public Builder add(String str, Class<?> cls, Class<?> cls2, Class<?> cls3) {
            this.isAdded = false;
            if (str.length() > 0) {
                UIModuleSpec uIModuleSpec = new UIModuleSpec(cls, cls2, cls3);
                this.lastCreatedApiSpec = uIModuleSpec;
                this.aliasApiMap.put(str, uIModuleSpec);
                this.isAdded = true;
                return this;
            }
            throw new IllegalArgumentException(cls.getName() + ": `alias` can not be empty");
        }

        public ApiSet build() {
            return new ApiSet(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private ApiSet(Builder builder) {
        this.mApiMap = builder.apiMap;
        this.mAliasApiMap = builder.aliasApiMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getQualifiedAlias(String str, Class cls) {
        return str + "/" + cls.getName();
    }

    public ApiSpec getApiSpec(Class cls) {
        return this.mApiMap.get(cls);
    }

    public ApiSpec getApiSpec(String str) {
        return this.mAliasApiMap.get(str);
    }

    public ApiSpec getApiSpec(String str, Class cls) {
        return getApiSpec(getQualifiedAlias(str, cls));
    }
}
