package com.huawei.hidatamanager.hwnaturalbase;

import android.content.Context;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.nb.client.ServiceConnectCallback;
import dalvik.system.DexClassLoader;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class NBDataSourceManager {
    private static final String TAG = "NBDataSourceManager ";
    private Context mContext;
    private Object mDataServiceProxy;
    private boolean isInitialized = false;
    private boolean isConnected = false;
    private ClassLoader classLoader = null;

    public NBDataSourceManager(Context context) {
        this.mContext = context;
    }

    public ClassLoader getClassLoader() {
        Context context;
        if (this.classLoader == null && (context = this.mContext) != null) {
            this.classLoader = new DexClassLoader("/system/framework/com.huawei.nb.sdk.jar", null, null, context.getClassLoader());
        }
        return this.classLoader;
    }

    public void init() {
        LogUtils.d(TAG, "NBDataSourceManager, init, isInitialized=" + this.isInitialized);
        if (this.isInitialized) {
            return;
        }
        getClassLoader();
        LogUtils.d(TAG, "NBDataSourceManager, getClassLoader");
        connectService();
        LogUtils.d(TAG, "NBDataSourceManager, connectService");
        this.isInitialized = true;
    }

    public void disConnect() {
        Object obj = this.mDataServiceProxy;
        if (obj != null) {
            try {
                obj.getClass().getMethod(AwarenessRequest.MessageType.DISCONNECT, new Class[0]).invoke(this.mDataServiceProxy, new Object[0]);
                this.mDataServiceProxy = null;
            } catch (Exception unused) {
                return;
            }
        }
        this.isInitialized = false;
        this.isConnected = false;
    }

    private void connectService() {
        try {
            ClassLoader classLoader = this.classLoader;
            if (classLoader == null) {
                LogUtils.e(TAG, "classLoader is null");
                return;
            }
            Class<?> loadClass = classLoader.loadClass("com.huawei.nb.client.DataServiceProxy");
            this.mDataServiceProxy = loadClass.getConstructor(Context.class).newInstance(this.mContext);
            Method method = loadClass.getMethod("connect", ServiceConnectCallback.class);
            if (method == null) {
                LogUtils.e(TAG, "connect is null");
            } else {
                method.invoke(this.mDataServiceProxy, new ServiceConnectCallback() { // from class: com.huawei.hidatamanager.hwnaturalbase.NBDataSourceManager.1
                    @Override // com.huawei.nb.client.ServiceConnectCallback
                    public void onConnect() {
                        NBDataSourceManager.this.isConnected = true;
                        LogUtils.i(NBDataSourceManager.TAG, "NBDataSourceManager, hidatamanager has connected to natural base data service.");
                    }

                    @Override // com.huawei.nb.client.ServiceConnectCallback
                    public void onDisconnect() {
                        NBDataSourceManager.this.isConnected = false;
                        LogUtils.i(NBDataSourceManager.TAG, "NBDataSourceManager, hidatamanager has disconnected from natural base data service.");
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "try to connect fail" + e);
        }
    }

    private boolean checkService() {
        if (!this.isInitialized) {
            LogUtils.w(TAG, "service is not init, do init!");
            init();
            return false;
        }
        if (this.isConnected) {
            return true;
        }
        LogUtils.w(TAG, "service is not connected, do init!");
        connectService();
        return false;
    }

    public long insert(String str, Object obj) {
        if (!checkService()) {
            LogUtils.e(TAG, "service not connected!");
            return -1L;
        }
        if (obj == null) {
            LogUtils.e(TAG, "NBDataSource, insert, content can NOT be null.");
            return -1L;
        }
        if (this.mDataServiceProxy == null) {
            LogUtils.w(TAG, "mDataServiceProxy is null!");
            return -1L;
        }
        try {
            Class<?> loadClass = this.classLoader.loadClass("com.huawei.odmf.core.AManagedObject");
            if (loadClass == null) {
                LogUtils.e(TAG, "NBDataSource,AManagedClass is null !");
                return -1L;
            }
            Method method = this.mDataServiceProxy.getClass().getMethod("executeInsert", loadClass);
            if (method == null) {
                LogUtils.e(TAG, "executeInsert is null !");
                return -1L;
            }
            if (method.invoke(this.mDataServiceProxy, obj) == null) {
                LogUtils.w(TAG, "NBDataSource, insert, failed. inserted null !");
                return -1L;
            }
            LogUtils.d(TAG, "NBDataSource, insert success, rowId=");
            return 0L;
        } catch (Exception e) {
            LogUtils.e(TAG, "try to executeInsert fail" + e);
            return -1L;
        }
    }
}
