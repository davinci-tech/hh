package com.huawei.profile.kv;

import android.content.Context;
import android.util.Log;
import com.huawei.profile.service.IProfileServiceCall;
import com.huawei.profile.utils.BaseUtil;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes6.dex */
public class ProfileStoreFactory {
    private static final String TAG = "ProfileStoreFactory";
    private ProfileStoreBaseProxy localStoreProxy;
    private final Object lock = new Object();
    private ProfileStoreBaseProxy remoteStoreProxy;

    public ProfileStoreBaseProxy generateStore(Context context, IProfileServiceCall iProfileServiceCall, boolean z) {
        synchronized (this.lock) {
            ProfileStoreBaseProxy storeProxy = getStoreProxy(z);
            if (storeProxy != null) {
                return storeProxy;
            }
            if (z) {
                ProfileRemoteStore profileRemoteStore = new ProfileRemoteStore(iProfileServiceCall);
                this.remoteStoreProxy = profileRemoteStore;
                return profileRemoteStore;
            }
            ProfileStoreBaseProxy generateSdkStore = generateSdkStore(context);
            if (generateSdkStore == null) {
                generateSdkStore = new ProfileFalseStore();
            }
            this.localStoreProxy = generateSdkStore;
            return generateSdkStore;
        }
    }

    private ProfileStoreBaseProxy getStoreProxy(boolean z) {
        return z ? this.remoteStoreProxy : this.localStoreProxy;
    }

    public void releaseStore(boolean z) {
        synchronized (this.lock) {
            if (z) {
                this.remoteStoreProxy = null;
            } else {
                this.localStoreProxy = null;
            }
        }
    }

    private static ProfileStoreBaseProxy generateSdkStore(Context context) {
        try {
            return (ProfileStoreBaseProxy) BaseUtil.cast(Class.forName("com.huawei.profile.kv.ProfileSdkStore").getConstructor(Context.class).newInstance(context), ProfileStoreBaseProxy.class);
        } catch (ClassNotFoundException e) {
            e = e;
            Log.e(TAG, " Failed to generate store by name, error: " + e.getMessage());
            return null;
        } catch (IllegalAccessException e2) {
            e = e2;
            Log.e(TAG, " Failed to generate store by name, error: " + e.getMessage());
            return null;
        } catch (InstantiationException e3) {
            e = e3;
            Log.e(TAG, " Failed to generate store by name, error: " + e.getMessage());
            return null;
        } catch (NoSuchMethodException e4) {
            e = e4;
            Log.e(TAG, " Failed to generate store by name, error: " + e.getMessage());
            return null;
        } catch (RuntimeException unused) {
            Log.e(TAG, " Failed to generate store by name with Unexpected runtimeException");
            return null;
        } catch (InvocationTargetException e5) {
            e = e5;
            Log.e(TAG, " Failed to generate store by name, error: " + e.getMessage());
            return null;
        }
    }
}
