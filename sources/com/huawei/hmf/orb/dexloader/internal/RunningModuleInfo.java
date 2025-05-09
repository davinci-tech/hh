package com.huawei.hmf.orb.dexloader.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.Releasable;
import com.huawei.hmf.orb.aidl.communicate.ObjectWrapper;
import com.huawei.hmf.services.codec.MessageCodec;
import com.huawei.hmf.services.ui.internal.SecurityIntent;

/* loaded from: classes9.dex */
public class RunningModuleInfo implements IMessageEntity, Releasable {
    private static final transient MessageCodec CODEC = new MessageCodec();
    public static final transient String DESCRIPTOR = "__RunningModuleInfo__";
    public static final String TARGET_CONTEXT_TAG = "__TargetContext__";
    private final boolean mIsExternalModule;
    private transient Context mTargetContext;

    @Override // com.huawei.hmf.orb.Releasable
    public void release() {
    }

    private RunningModuleInfo() {
        this.mTargetContext = null;
        this.mIsExternalModule = false;
    }

    protected RunningModuleInfo(Context context) {
        this(context, false);
    }

    protected RunningModuleInfo(Context context, boolean z) {
        this.mTargetContext = context;
        this.mIsExternalModule = z;
    }

    public void attachTo(Bundle bundle) {
        Bundle encode = CODEC.encode(this, new Bundle());
        encode.putBinder(TARGET_CONTEXT_TAG, ObjectWrapper.wrap(this.mTargetContext));
        bundle.putBundle(DESCRIPTOR, encode);
    }

    public void attachTo(Intent intent) {
        Bundle encode = CODEC.encode(this, new Bundle());
        encode.putBinder(TARGET_CONTEXT_TAG, ObjectWrapper.wrap(this.mTargetContext));
        intent.putExtra(DESCRIPTOR, encode);
    }

    public Context getTargetContext() {
        return this.mTargetContext;
    }

    public boolean isExternalModule() {
        return this.mIsExternalModule;
    }

    public static RunningModuleInfo from(Intent intent) {
        Bundle bundleExtra;
        if (intent == null || (bundleExtra = SecurityIntent.from(intent).getBundleExtra(DESCRIPTOR)) == null) {
            return null;
        }
        RunningModuleInfo runningModuleInfo = (RunningModuleInfo) CODEC.decode(bundleExtra, (Bundle) new RunningModuleInfo());
        runningModuleInfo.mTargetContext = (Context) ObjectWrapper.unwrap(bundleExtra.getBinder(TARGET_CONTEXT_TAG));
        return runningModuleInfo;
    }

    public static RunningModuleInfo from(Bundle bundle) {
        Bundle bundle2;
        if (bundle == null || (bundle2 = bundle.getBundle(DESCRIPTOR)) == null) {
            return null;
        }
        RunningModuleInfo runningModuleInfo = (RunningModuleInfo) CODEC.decode(bundle2, (Bundle) new RunningModuleInfo());
        runningModuleInfo.mTargetContext = (Context) ObjectWrapper.unwrap(bundle2.getBinder(TARGET_CONTEXT_TAG));
        return runningModuleInfo;
    }

    public static RunningModuleInfo create(Context context) {
        return new RunningModuleInfo(context);
    }

    public static RunningModuleInfo create(Context context, boolean z) {
        return new RunningModuleInfo(context, z);
    }
}
