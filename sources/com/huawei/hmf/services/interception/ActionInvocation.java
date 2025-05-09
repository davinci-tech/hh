package com.huawei.hmf.services.interception;

import com.huawei.hmf.services.internal.ApplicationContext;

/* loaded from: classes4.dex */
public class ActionInvocation {
    private String mCallingPackageName;
    private String mModuleName;
    private Signature mSignature;

    public static class Builder {
        private String mCallingPackageName;
        private String mModuleName;
        private Signature mSignature;

        private Builder() {
        }

        public Builder callingPackageName(String str) {
            this.mCallingPackageName = str;
            return this;
        }

        public Builder moduleName(String str) {
            this.mModuleName = str;
            return this;
        }

        public Builder signature(Signature signature) {
            this.mSignature = signature;
            return this;
        }

        public ActionInvocation build() {
            return new ActionInvocation(this);
        }
    }

    ActionInvocation(Builder builder) {
        String str = builder.mCallingPackageName;
        this.mCallingPackageName = str;
        if (str == null) {
            this.mCallingPackageName = ApplicationContext.getContext().getPackageName();
        }
        this.mModuleName = builder.mModuleName;
        this.mSignature = builder.mSignature;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Signature getSignature() {
        return this.mSignature;
    }

    public String getCallingPackageName() {
        return this.mCallingPackageName;
    }

    public String getModuleName() {
        return this.mModuleName;
    }

    public String toString() {
        return "ActionInvocation{CallingPackageName='" + this.mCallingPackageName + "', ModuleName='" + this.mModuleName + "', " + this.mSignature + '}';
    }
}
