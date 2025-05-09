package com.huawei.hmf.orb.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.hmf.orb.CommonCode;
import com.huawei.hmf.orb.ConnectionCallbacks;
import com.huawei.hmf.orb.RemoteConnector;
import com.huawei.hmf.orb.RemoteRepository;
import com.huawei.hmf.orb.RemoteRepositoryFactory;
import com.huawei.hmf.orb.aidl.IAIDLInvoke;
import com.huawei.hmf.orb.aidl.IRemoteServiceBroker;
import com.huawei.hmf.orb.aidl.IRemoteServiceCallbacks;
import com.huawei.hmf.orb.aidl.client.ApiClient;
import com.huawei.hmf.orb.aidl.client.ResultCallback;
import com.huawei.hmf.orb.aidl.client.impl.ResolveResult;
import com.huawei.hmf.orb.aidl.communicate.RequestHeader;
import com.huawei.hmf.orb.aidl.impl.GetServiceRequest;
import com.huawei.hmf.orb.aidl.request.ConnectService;
import com.huawei.hmf.orb.aidl.request.DisconnectService;
import com.huawei.hmf.orb.exception.ConnectRemoteException;
import com.huawei.hmf.services.ApiSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes8.dex */
public class AIDLConnector implements RemoteConnector, ApiClient, ServiceConnection, Handler.Callback {
    private static final int GET_AIDL_INVOKE = 0;
    private static final int GET_SERVICE_BROKER = 1;
    private static final String TAG = "AIDLConnector";
    private Map<String, ApiSet> mApiSets;
    private AtomicReference<ConnectStatus> mConnectStatus;
    private List<ConnectionCallbacks> mConnectionCallbacksList;
    private Context mContext;
    private Intent mFillInIntent;
    private HandlerThread mHandlerThread;
    private String mId;
    private String mPackageName;
    private volatile IAIDLInvoke mService;
    private String mServiceAction;

    enum ConnectStatus {
        DISCONNECTED,
        CONNECTED,
        SUSPENDED
    }

    @Override // com.huawei.hmf.orb.aidl.client.ApiClient
    public int getProtocol() {
        return 0;
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public RemoteRepositoryFactory getRepositoryFactory() {
        return RemoteRepository.FACTORY;
    }

    public AIDLConnector(Context context, String str) {
        this(context, str, null);
    }

    public AIDLConnector(Context context, String str, String str2) {
        this(context, str, str2, null);
    }

    public AIDLConnector(Context context, String str, String str2, Intent intent) {
        this.mServiceAction = "com.huawei.hmf.remotemoduleservice";
        this.mConnectionCallbacksList = new ArrayList();
        this.mApiSets = new HashMap();
        this.mConnectStatus = new AtomicReference<>(ConnectStatus.DISCONNECTED);
        this.mPackageName = str;
        this.mId = str + "/" + String.valueOf(hashCode());
        this.mContext = context;
        if (str2 != null) {
            this.mServiceAction = str2;
        }
        this.mFillInIntent = intent;
    }

    private Intent makeRequestIntent() {
        Intent intent = new Intent(this.mServiceAction);
        intent.setPackage(this.mPackageName);
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.packageName = getPackageName();
        intent.putExtra(RequestHeader.getDescriptor(), requestHeader.toBundle());
        Intent intent2 = this.mFillInIntent;
        if (intent2 != null) {
            intent.fillIn(intent2, 0);
        }
        return intent;
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public RemoteConnector newInstance() {
        return new AIDLConnector(this.mContext, this.mPackageName, this.mServiceAction, this.mFillInIntent);
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public void connect(ConnectionCallbacks connectionCallbacks) throws ConnectRemoteException {
        ConnectionCallbacksForConnect connectionCallbacksForConnect = new ConnectionCallbacksForConnect(connectionCallbacks);
        this.mConnectionCallbacksList.add(connectionCallbacksForConnect);
        if (isConnected()) {
            connectionCallbacksForConnect.onConnected();
            return;
        }
        try {
            bindService();
        } catch (ConnectRemoteException e) {
            connectionCallbacksForConnect.onConnectionFailed(e);
            this.mConnectionCallbacksList.remove(connectionCallbacksForConnect);
            throw e;
        }
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public void close() {
        if (isConnected()) {
            disconnect();
            Iterator<ConnectionCallbacks> it = this.mConnectionCallbacksList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks next = it.next();
                next.onDisconnected();
                if (next instanceof ConnectionCallbacksForConnect) {
                    it.remove();
                }
            }
        }
    }

    @Override // com.huawei.hmf.orb.RemoteConnector, com.huawei.hmf.orb.aidl.client.ApiClient
    public boolean isConnected() {
        return this.mConnectStatus.get() == ConnectStatus.CONNECTED;
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public String getID() {
        return this.mId;
    }

    @Override // com.huawei.hmf.orb.aidl.client.ApiClient
    public String getAppID() {
        return this.mId;
    }

    @Override // com.huawei.hmf.orb.aidl.client.ApiClient
    public String getPackageName() {
        return this.mContext.getPackageName();
    }

    @Override // com.huawei.hmf.orb.aidl.client.ApiClient
    public IAIDLInvoke getService() {
        return this.mService;
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public Map<String, ApiSet> getApiSet() {
        return this.mApiSets;
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public void addConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.mConnectionCallbacksList.add(connectionCallbacks);
    }

    @Override // com.huawei.hmf.orb.RemoteConnector
    public void removeConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.mConnectionCallbacksList.remove(connectionCallbacks);
    }

    private void disconnect() {
        try {
            if (!isConnected() || this.mContext == null) {
                return;
            }
            DisconnectService.build(this).get();
            this.mContext.unbindService(this);
            this.mConnectStatus.set(ConnectStatus.DISCONNECTED);
        } catch (Exception unused) {
            this.mConnectStatus.set(ConnectStatus.DISCONNECTED);
        }
    }

    private void bindService() throws ConnectRemoteException {
        if (!checkServiceAvailable()) {
            throw new ConnectRemoteException(ConnectRemoteException.Status.NotFoundService, "Not Found Service with package name " + this.mPackageName);
        }
        if (this.mContext.bindService(makeRequestIntent(), this, 1)) {
            return;
        }
        this.mContext.unbindService(this);
        throw new ConnectRemoteException(ConnectRemoteException.Status.UnableBindService, "Unable bind to service with package name " + this.mPackageName);
    }

    private boolean checkServiceAvailable() {
        return this.mContext.getPackageManager().getApplicationInfo(this.mPackageName, 0) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parserModuleInfo(ConnectService.Response response) {
        for (String str : response.getRemoteModules()) {
            RemoteProxyRegistry registry = RemoteProxyRegistry.getRegistry(str);
            if (registry != null) {
                this.mApiSets.put(str, registry.getProxy());
            }
        }
    }

    private void getRemoteModule() {
        ConnectService.build(this).setResultCallback(new ResultCallback<ResolveResult<ConnectService.Response>>() { // from class: com.huawei.hmf.orb.aidl.AIDLConnector.1
            @Override // com.huawei.hmf.orb.aidl.client.ResultCallback
            public void onResult(ResolveResult<ConnectService.Response> resolveResult) {
                if (resolveResult != null && resolveResult.getStatus().isSuccess()) {
                    AIDLConnector.this.parserModuleInfo(resolveResult.getValue());
                    Iterator it = AIDLConnector.this.mConnectionCallbacksList.iterator();
                    while (it.hasNext()) {
                        ((ConnectionCallbacks) it.next()).onConnected();
                    }
                    return;
                }
                ConnectRemoteException.Status status = ConnectRemoteException.Status.RejectBindService;
                StringBuilder sb = new StringBuilder("Get module metadata error from remote repository, error code: ");
                sb.append(resolveResult != null ? Integer.valueOf(resolveResult.getStatus().getStatusCode()) : "unknown");
                AIDLConnector.this.connectFailed(new ConnectRemoteException(status, sb.toString()));
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        int i;
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        try {
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if ("com.huawei.hmf.orb.aidl.IAIDLInvoke".equals(interfaceDescriptor)) {
                i = 0;
            } else {
                if (!"com.huawei.hmf.orb.aidl.IRemoteServiceBroker".equals(interfaceDescriptor)) {
                    onGetServiceResult(207135000, null);
                    return;
                }
                i = 1;
            }
            HandlerThread handlerThread2 = new HandlerThread("RemoteServiceHandler");
            this.mHandlerThread = handlerThread2;
            handlerThread2.start();
            Handler handler = new Handler(this.mHandlerThread.getLooper(), this);
            handler.sendMessage(handler.obtainMessage(i, iBinder));
        } catch (RemoteException unused) {
            onGetServiceResult(CommonCode.ErrorCode.INTERNAL_ERROR, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGetServiceResult(int i, IBinder iBinder) {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
        if (i == 0) {
            this.mService = IAIDLInvoke.Stub.asInterface(iBinder);
            this.mConnectStatus.set(ConnectStatus.CONNECTED);
            getRemoteModule();
            return;
        }
        this.mContext.unbindService(this);
        this.mConnectStatus.set(ConnectStatus.DISCONNECTED);
        final ConnectRemoteException connectRemoteException = new ConnectRemoteException(ConnectRemoteException.Status.RejectBindService, "Connection rejected, error code: " + i);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hmf.orb.aidl.AIDLConnector.2
            @Override // java.lang.Runnable
            public void run() {
                AIDLConnector.this.connectFailed(connectRemoteException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectFailed(ConnectRemoteException connectRemoteException) {
        Iterator<ConnectionCallbacks> it = this.mConnectionCallbacksList.iterator();
        while (it.hasNext()) {
            ConnectionCallbacks next = it.next();
            next.onConnectionFailed(connectRemoteException);
            if (next instanceof ConnectionCallbacksForConnect) {
                it.remove();
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.mConnectStatus.set(ConnectStatus.SUSPENDED);
        Log.e(TAG, "service " + getPackageName() + ": Connection Status set to SUSPENDED");
        Iterator<ConnectionCallbacks> it = this.mConnectionCallbacksList.iterator();
        while (it.hasNext()) {
            it.next().onDisconnected();
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            int i = message.what;
            if (i == 0) {
                onGetServiceResult(0, (IBinder) message.obj);
            } else if (i == 1) {
                IRemoteServiceBroker.Stub.asInterface((IBinder) message.obj).getService(new GetServiceRequest(0, makeRequestIntent()), new IRemoteServiceCallbacks.Stub() { // from class: com.huawei.hmf.orb.aidl.AIDLConnector.3
                    @Override // com.huawei.hmf.orb.aidl.IRemoteServiceCallbacks
                    public void onResult(int i2, IBinder iBinder) {
                        AIDLConnector.this.onGetServiceResult(i2, iBinder);
                    }
                });
            }
        } catch (RemoteException unused) {
            onGetServiceResult(CommonCode.ErrorCode.INTERNAL_ERROR, null);
        }
        return true;
    }
}
