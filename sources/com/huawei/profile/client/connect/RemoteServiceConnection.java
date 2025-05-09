package com.huawei.profile.client.connect;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes6.dex */
public class RemoteServiceConnection {
    private static final String PROFILE_SERVICE_ACTION = "com.huawei.profile.service.ProfileService.START";
    private static final String SERVICE_CLASS_NAME = "com.huawei.profile.service.ProfileService";
    private static final String SERVICE_PACKAGE_NAME = "com.huawei.profile";
    private static final String TAG = "RemoteServiceConnection";
    private IBinder binder = null;
    private OnConnectListener connectListener;
    private volatile ServiceConnectionWrapper connection;
    private final Context context;
    private final int userId;

    public interface OnConnectListener {
        void onConnect(IBinder iBinder);

        void onDisconnect();
    }

    public RemoteServiceConnection(Context context, int i) {
        this.context = context;
        this.userId = i;
    }

    public boolean open(OnConnectListener onConnectListener) {
        this.connectListener = onConnectListener;
        Intent intent = new Intent(PROFILE_SERVICE_ACTION);
        intent.setComponent(new ComponentName("com.huawei.profile", SERVICE_CLASS_NAME));
        this.connection = new ServiceConnectionWrapper();
        try {
            if (bindService(intent)) {
                return true;
            }
            Log.e(TAG, "Failed to bind to the target service.");
            this.connection = null;
            return false;
        } catch (SecurityException unused) {
            Log.e(TAG, "Failed to bind service, error: %s.");
            this.connection = null;
            return false;
        }
    }

    private boolean bindService(Intent intent) {
        if (isHeadlessSystemUser()) {
            int i = this.userId;
            if (i == 0) {
                Log.e(TAG, "Bind as user headless true and user 0.");
                return false;
            }
            if (i == -2) {
                return this.context.bindService(intent, this.connection, 1);
            }
            return bindServiceAsUser(this.context, intent, this.connection, 1, getUserHandle(this.userId));
        }
        int i2 = this.userId;
        if (i2 == 0) {
            return bindServiceAsUser(this.context, intent, this.connection, 1, getUserHandle(this.userId));
        }
        if (i2 == -2) {
            return this.context.bindService(intent, this.connection, 1);
        }
        Log.e(TAG, "Bind as user headless false and not user 0.");
        return false;
    }

    private boolean isHeadlessSystemUser() {
        return getBooleanProp("ro.fw.mu.headless_system_user", false);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.os.UserHandle getUserHandle(int r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "enter getUserHandle,userId:"
            r0.<init>(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "RemoteServiceConnection"
            android.util.Log.i(r1, r0)
            java.lang.String r0 = "android.os.UserHandle"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            java.lang.reflect.Constructor r0 = r0.getDeclaredConstructor(r3)     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            r2[r5] = r7     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            java.lang.Object r7 = r0.newInstance(r2)     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            boolean r0 = r7 instanceof android.os.UserHandle     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            if (r0 == 0) goto L38
            android.os.UserHandle r7 = (android.os.UserHandle) r7     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            goto L68
        L38:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            java.lang.String r2 = "failed to get user service, object is "
            r0.<init>(r2)     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            r0.append(r7)     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            java.lang.String r7 = r0.toString()     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            android.util.Log.w(r1, r7)     // Catch: java.lang.InstantiationException -> L4a java.lang.reflect.InvocationTargetException -> L50 java.lang.IllegalAccessException -> L56 java.lang.NoSuchMethodException -> L5c java.lang.ClassNotFoundException -> L62
            goto L67
        L4a:
            java.lang.String r7 = "UserHandle InstantiationException"
            android.util.Log.e(r1, r7)
            goto L67
        L50:
            java.lang.String r7 = "UserHandle InvocationTargetException"
            android.util.Log.e(r1, r7)
            goto L67
        L56:
            java.lang.String r7 = "UserHandle IllegalAccessException"
            android.util.Log.e(r1, r7)
            goto L67
        L5c:
            java.lang.String r7 = "UserHandle NoSuchMethodException"
            android.util.Log.e(r1, r7)
            goto L67
        L62:
            java.lang.String r7 = "UserHandle ClassNotFoundException"
            android.util.Log.e(r1, r7)
        L67:
            r7 = 0
        L68:
            if (r7 != 0) goto L6f
            java.lang.String r0 = "failed to get user handler"
            android.util.Log.w(r1, r0)
        L6f:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.profile.client.connect.RemoteServiceConnection.getUserHandle(int):android.os.UserHandle");
    }

    private boolean bindServiceAsUser(Context context, Intent intent, ServiceConnection serviceConnection, int i, UserHandle userHandle) {
        if (userHandle == null) {
            Log.e(TAG, "userHandle is null, failed to bind service as user");
            return false;
        }
        try {
            Method declaredMethod = Context.class.getDeclaredMethod("bindServiceAsUser", Intent.class, ServiceConnection.class, Integer.TYPE, UserHandle.class);
            if (declaredMethod != null) {
                Object invoke = declaredMethod.invoke(context, intent, serviceConnection, Integer.valueOf(i), userHandle);
                if (invoke instanceof Boolean) {
                    return ((Boolean) invoke).booleanValue();
                }
            }
        } catch (IllegalAccessException unused) {
            Log.e(TAG, "bindServiceAsUser IllegalAccessException");
        } catch (NoSuchMethodException unused2) {
            Log.e(TAG, "bindServiceAsUser NoSuchMethodException");
        } catch (InvocationTargetException unused3) {
            Log.e(TAG, "bindServiceAsUser InvocationTargetException");
        }
        return false;
    }

    private static boolean getBooleanProp(String str, boolean z) {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getDeclaredMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z));
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        } catch (ClassNotFoundException unused) {
            Log.e(TAG, "SystemProperties ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            Log.e(TAG, "SystemProperties IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            Log.e(TAG, "SystemProperties NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            Log.e(TAG, "SystemProperties InvocationTargetException");
        }
        return z;
    }

    public boolean close() {
        unbind(this.connection);
        this.connection = null;
        this.binder = null;
        this.connectListener = null;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbind(ServiceConnectionWrapper serviceConnectionWrapper) {
        if (serviceConnectionWrapper != null) {
            try {
                serviceConnectionWrapper.isClosed = true;
                this.context.unbindService(serviceConnectionWrapper);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "IllegalArgumentException: " + e.getMessage());
            } catch (RuntimeException unused) {
                Log.e(TAG, "unbindService with Unexpected runtimeException");
            }
        }
    }

    class ServiceConnectionWrapper implements ServiceConnection {
        private static final long TIME_DELAY = 1000;
        volatile boolean isClosed;

        private ServiceConnectionWrapper() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (!this.isClosed) {
                RemoteServiceConnection.this.binder = iBinder;
                if (RemoteServiceConnection.this.connectListener != null) {
                    RemoteServiceConnection.this.connectListener.onConnect(RemoteServiceConnection.this.binder);
                    return;
                }
                return;
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huawei.profile.client.connect.RemoteServiceConnection.ServiceConnectionWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    Log.i(RemoteServiceConnection.TAG, "the service is already closed, unbindService");
                    RemoteServiceConnection.this.unbind(ServiceConnectionWrapper.this);
                }
            }, 1000L);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (RemoteServiceConnection.this.binder == null || this.isClosed) {
                return;
            }
            RemoteServiceConnection.this.binder = null;
            if (RemoteServiceConnection.this.connectListener != null) {
                RemoteServiceConnection.this.connectListener.onDisconnect();
            }
            Log.e(RemoteServiceConnection.TAG, "Connection to data service is disconnected unexpectedly.");
        }
    }
}
