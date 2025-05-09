package com.huawei.hms.common;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.adapter.AvailableUtil;
import com.huawei.hms.adapter.BinderAdapter;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.Api.ApiOptions;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.BindResolveClients;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.HmsClient;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.RequestManager;
import com.huawei.hms.common.internal.ResolveClientBean;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.common.internal.TaskApiCallWrapper;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.core.aidl.IAIDLInvoke;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.hianalytics.HiAnalyticsInnerClient;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSBIInitializer;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class HuaweiApi<TOption extends Api.ApiOptions> {

    /* renamed from: a, reason: collision with root package name */
    private Context f4429a;
    private TOption b;
    private Context c;
    private AbstractClientBuilder<?, TOption> d;
    private String e;
    private String f;
    private SubAppInfo g;
    private WeakReference<Activity> h;
    private int i;
    private int j = 1;
    private boolean k = false;
    private String l;
    private boolean m;
    private RequestManager n;

    public static class RequestHandler<OptionsT extends Api.ApiOptions> implements BaseHmsClient.ConnectionCallbacks, BaseHmsClient.OnConnectionFailedListener {
        private final AnyClient b;
        private final HuaweiApi<OptionsT> d;
        private ResolveClientBean e;
        public final Queue<TaskApiCallbackWrapper> callbackWaitQueue = new LinkedList();

        /* renamed from: a, reason: collision with root package name */
        private final Queue<TaskApiCallbackWrapper> f4431a = new LinkedList();
        private ConnectionResult c = null;

        RequestHandler(HuaweiApi<OptionsT> huaweiApi) {
            this.d = huaweiApi;
            this.b = huaweiApi.getClient(RequestManager.getHandler().getLooper(), this);
        }

        public AnyClient getClient() {
            return this.b;
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnected() {
            HMSLog.i("HuaweiApi", "onConnected");
            BindResolveClients.getInstance().unRegister(this.e);
            this.e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.4
                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.b();
                }
            });
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.OnConnectionFailedListener
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            HMSLog.i("HuaweiApi", "onConnectionFailed");
            BindResolveClients.getInstance().unRegister(this.e);
            this.e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.3
                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.b(connectionResult);
                }
            });
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            HMSLog.i("HuaweiApi", "onConnectionSuspended");
            BindResolveClients.getInstance().unRegister(this.e);
            this.e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.5
                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.c();
                }
            });
        }

        public void postMessage(final TaskApiCallbackWrapper taskApiCallbackWrapper) {
            RequestManager.addToConnectedReqMap(taskApiCallbackWrapper.a().getTaskApiCall().getTransactionId(), this);
            this.f4431a.add(taskApiCallbackWrapper);
            String uri = taskApiCallbackWrapper.a().getTaskApiCall().getUri();
            String packageName = (((HuaweiApi) this.d).c == null ? this.d.getContext() : ((HuaweiApi) this.d).c).getPackageName();
            if (((HuaweiApi) this.d).c != null) {
                HuaweiApi<OptionsT> huaweiApi = this.d;
                huaweiApi.b(((HuaweiApi) huaweiApi).c);
            }
            final RequestHeader requestHeader = new RequestHeader();
            requestHeader.setSrvName(uri.split("\\.")[0]);
            requestHeader.setApiName(uri);
            requestHeader.setAppID(this.d.getAppID() + "|" + this.d.getSubAppID());
            requestHeader.setPkgName(packageName);
            requestHeader.setSessionId(this.b.getSessionId());
            TaskApiCall taskApiCall = taskApiCallbackWrapper.a().getTaskApiCall();
            requestHeader.setTransactionId(a(taskApiCall.getTransactionId(), uri));
            requestHeader.setParcelable(taskApiCall.getParcelable());
            requestHeader.setKitSdkVersion(this.d.getKitSdkVersion());
            requestHeader.setApiLevel(Math.max(this.d.getApiLevel(), taskApiCall.getApiLevel()));
            this.b.post(requestHeader, taskApiCall.getRequestJson(), new AnyClient.CallBack() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.2
                @Override // com.huawei.hms.common.internal.AnyClient.CallBack
                public void onCallback(IMessageEntity iMessageEntity, String str) {
                    AnyClient.CallBack b = taskApiCallbackWrapper.b();
                    if (b != null) {
                        b.onCallback(iMessageEntity, str);
                    }
                    RequestManager.removeReqByTransId(requestHeader.getTransactionId());
                    RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RequestHandler.this.f4431a.remove(taskApiCallbackWrapper);
                        }
                    });
                }
            });
        }

        private TaskApiCallbackWrapper b(final TaskApiCallWrapper taskApiCallWrapper) {
            return new TaskApiCallbackWrapper(taskApiCallWrapper, new AnyClient.CallBack() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.1

                /* renamed from: a, reason: collision with root package name */
                private AtomicBoolean f4432a = new AtomicBoolean(true);

                @Override // com.huawei.hms.common.internal.AnyClient.CallBack
                public void onCallback(IMessageEntity iMessageEntity, String str) {
                    if (!(iMessageEntity instanceof ResponseHeader)) {
                        HMSLog.e("HuaweiApi", "header is not instance of ResponseHeader");
                        return;
                    }
                    ResponseHeader responseHeader = (ResponseHeader) iMessageEntity;
                    if (responseHeader.getErrorCode() == 11) {
                        RequestHandler.this.a();
                        HMSLog.i("HuaweiApi", "unbind service");
                    }
                    if (!TextUtils.isEmpty(responseHeader.getResolution())) {
                        HMSLog.e("HuaweiApi", "Response has resolution: " + responseHeader.getResolution());
                    }
                    if (this.f4432a.compareAndSet(true, false)) {
                        HiAnalyticsInnerClient.reportEntryExit(RequestHandler.this.d.getContext(), responseHeader, String.valueOf(RequestHandler.this.d.getKitSdkVersion()));
                    }
                    taskApiCallWrapper.getTaskApiCall().onResponse(RequestHandler.this.b, responseHeader, str, taskApiCallWrapper.getTaskCompletionSource());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            HMSLog.i("HuaweiApi", "wait queue size = " + this.callbackWaitQueue.size());
            HMSLog.i("HuaweiApi", "run queue size = " + this.f4431a.size());
            Iterator<TaskApiCallbackWrapper> it = this.callbackWaitQueue.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Iterator<TaskApiCallbackWrapper> it2 = this.f4431a.iterator();
            while (it2.hasNext()) {
                a(it2.next());
            }
            this.callbackWaitQueue.clear();
            this.f4431a.clear();
            this.c = null;
            this.b.disconnect();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(ConnectionResult connectionResult) {
            this.c = connectionResult;
            Iterator<TaskApiCallbackWrapper> it = this.callbackWaitQueue.iterator();
            boolean z = true;
            while (it.hasNext()) {
                TaskApiCallWrapper a2 = it.next().a();
                ResponseHeader responseHeader = new ResponseHeader(1, 907135003, "Connection Failed:" + a(connectionResult) + Constants.LEFT_BRACKET_ONLY + connectionResult.getErrorCode() + Constants.RIGHT_BRACKET_ONLY);
                responseHeader.setTransactionId(a2.getTaskApiCall().getTransactionId());
                HiAnalyticsInnerClient.reportEntryExit(this.d.getContext(), responseHeader, String.valueOf(this.d.getKitSdkVersion()));
                if (this.c.getResolution() != null && z) {
                    responseHeader.setParcelable(this.c.getResolution());
                    if (Util.isAvailableLibExist(this.d.getContext()) && this.c.getErrorCode() == 26) {
                        responseHeader.setResolution(CommonCode.Resolution.HAS_RESOLUTION);
                    }
                    z = false;
                }
                int errorCode = this.c.getErrorCode();
                if (errorCode == 30 || errorCode == 31) {
                    responseHeader.setErrorCode(errorCode);
                }
                a2.getTaskApiCall().onResponse(this.b, responseHeader, null, a2.getTaskCompletionSource());
            }
            this.callbackWaitQueue.clear();
            this.f4431a.clear();
            this.c = null;
            this.b.disconnect();
        }

        void a(TaskApiCallWrapper taskApiCallWrapper) {
            HMSLog.i("HuaweiApi", "sendRequest");
            TaskApiCallbackWrapper b = b(taskApiCallWrapper);
            if (HMSPackageManager.getInstance(this.d.getContext()).isUpdateHmsForThirdPartyDevice()) {
                this.b.disconnect();
            }
            int hmsVersionCode = HMSPackageManager.getInstance(((HuaweiApi) this.d).f4429a).getHmsVersionCode();
            if (hmsVersionCode < 40000000 && hmsVersionCode > 0 && this.b.isConnected() && !((HuaweiApi) this.d).m && ((BaseHmsClient) this.b).getAdapter().getServiceAction().equals("com.huawei.hms.core.aidlservice")) {
                int requestHmsVersionCode = this.b.getRequestHmsVersionCode();
                if (requestHmsVersionCode <= taskApiCallWrapper.getTaskApiCall().getMinApkVersion()) {
                    requestHmsVersionCode = taskApiCallWrapper.getTaskApiCall().getMinApkVersion();
                }
                if (requestHmsVersionCode > hmsVersionCode) {
                    this.b.disconnect();
                }
            }
            if (this.b.isConnected()) {
                HMSLog.i("HuaweiApi", "isConnected:true.");
                BinderAdapter adapter = ((BaseHmsClient) this.b).getAdapter();
                adapter.updateDelayTask();
                ((HmsClient) this.b).setService(IAIDLInvoke.Stub.asInterface(adapter.getServiceBinder()));
                postMessage(b);
                return;
            }
            HMSLog.i("HuaweiApi", "isConnected:false.");
            this.callbackWaitQueue.add(b);
            ConnectionResult connectionResult = this.c;
            if (connectionResult != null && connectionResult.getErrorCode() != 0) {
                HMSLog.i("HuaweiApi", "onConnectionFailed, ErrorCode:" + this.c.getErrorCode());
                onConnectionFailed(this.c);
                return;
            }
            RequestManager.addRequestToQueue(this);
            Object obj = this.b;
            if (obj instanceof BaseHmsClient) {
                ((BaseHmsClient) obj).setInternalRequest(this);
            }
            a(taskApiCallWrapper.getTaskApiCall().getMinApkVersion(), b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.c = null;
            this.f4431a.clear();
            Iterator<TaskApiCallbackWrapper> it = this.callbackWaitQueue.iterator();
            while (it.hasNext()) {
                postMessage(it.next());
            }
            this.callbackWaitQueue.clear();
        }

        private String a(String str, String str2) {
            return TextUtils.isEmpty(str) ? TransactionIdCreater.getId(this.d.getAppID(), str2) : str;
        }

        void a(int i, TaskApiCallbackWrapper taskApiCallbackWrapper) {
            synchronized (this) {
                if (this.b.isConnected()) {
                    HMSLog.d("HuaweiApi", "client is connected");
                    return;
                }
                if (this.b.isConnecting()) {
                    HMSLog.d("HuaweiApi", "client is isConnecting");
                    return;
                }
                if (this.d.getActivity() != null) {
                    if (this.e == null) {
                        this.e = new ResolveClientBean(this.b, i);
                    }
                    if (BindResolveClients.getInstance().isClientRegistered(this.e)) {
                        HMSLog.i("HuaweiApi", "mResolveClientBean has already register, return!");
                        return;
                    }
                    BindResolveClients.getInstance().register(this.e);
                }
                this.b.connect(i);
            }
        }

        void a() {
            this.b.disconnect();
        }

        private void a(TaskApiCallbackWrapper taskApiCallbackWrapper) {
            TaskApiCallWrapper a2 = taskApiCallbackWrapper.a();
            ResponseHeader responseHeader = new ResponseHeader(1, 907135003, "Connection Suspended");
            responseHeader.setTransactionId(a2.getTaskApiCall().getTransactionId());
            a2.getTaskApiCall().onResponse(this.b, responseHeader, null, a2.getTaskCompletionSource());
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0046 A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private java.lang.String a(com.huawei.hms.api.ConnectionResult r5) {
            /*
                r4 = this;
                com.huawei.hms.common.HuaweiApi<OptionsT extends com.huawei.hms.api.Api$ApiOptions> r0 = r4.d
                android.content.Context r0 = r0.getContext()
                boolean r0 = com.huawei.hms.utils.Util.isAvailableLibExist(r0)
                r1 = 10
                r2 = 8
                r3 = -1
                if (r0 == 0) goto L3c
                int r5 = r5.getErrorCode()
                if (r5 == r3) goto L4f
                r0 = 3
                if (r5 == r0) goto L39
                if (r5 == r2) goto L4c
                if (r5 == r1) goto L49
                r0 = 13
                if (r5 == r0) goto L36
                r0 = 21
                if (r5 == r0) goto L33
                switch(r5) {
                    case 25: goto L30;
                    case 26: goto L2d;
                    case 27: goto L2a;
                    default: goto L29;
                }
            L29:
                goto L46
            L2a:
                java.lang.String r5 = "there is already an update popup at the front desk, but it hasn't been clicked or it is not effective for a while"
                goto L51
            L2d:
                java.lang.String r5 = "update failed, because no activity incoming, can't pop update page"
                goto L51
            L30:
                java.lang.String r5 = "failed to get update result"
                goto L51
            L33:
                java.lang.String r5 = "device is too old to be support"
                goto L51
            L36:
                java.lang.String r5 = "update cancelled"
                goto L51
            L39:
                java.lang.String r5 = "HuaWei Mobile Service is disabled"
                goto L51
            L3c:
                int r5 = r5.getErrorCode()
                if (r5 == r3) goto L4f
                if (r5 == r2) goto L4c
                if (r5 == r1) goto L49
            L46:
                java.lang.String r5 = "unknown errorReason"
                goto L51
            L49:
                java.lang.String r5 = "application configuration error, please developer check configuration"
                goto L51
            L4c:
                java.lang.String r5 = "internal error"
                goto L51
            L4f:
                java.lang.String r5 = "get update result, but has other error codes"
            L51:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.HuaweiApi.RequestHandler.a(com.huawei.hms.api.ConnectionResult):java.lang.String");
        }
    }

    public static class TaskApiCallbackWrapper {

        /* renamed from: a, reason: collision with root package name */
        private final TaskApiCallWrapper f4438a;
        private final AnyClient.CallBack b;

        TaskApiCallbackWrapper(TaskApiCallWrapper taskApiCallWrapper, AnyClient.CallBack callBack) {
            this.f4438a = taskApiCallWrapper;
            this.b = callBack;
        }

        TaskApiCallWrapper a() {
            return this.f4438a;
        }

        AnyClient.CallBack b() {
            return this.b;
        }
    }

    static class a<OptionsT extends Api.ApiOptions> implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final HuaweiApi<OptionsT> f4439a;
        private final TaskApiCallWrapper b;

        public a(HuaweiApi<OptionsT> huaweiApi, TaskApiCallWrapper taskApiCallWrapper) {
            this.f4439a = huaweiApi;
            this.b = taskApiCallWrapper;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0055 A[ADDED_TO_REGION] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void a(com.huawei.hms.common.HuaweiApi.RequestHandler r8, java.lang.Throwable r9) {
            /*
                r7 = this;
                java.lang.String r0 = "HuaweiApi"
                r1 = 0
                com.huawei.hms.common.internal.AnyClient r8 = r8.getClient()     // Catch: java.lang.Throwable -> L38
                com.huawei.hms.common.internal.ResponseHeader r2 = new com.huawei.hms.common.internal.ResponseHeader     // Catch: java.lang.Throwable -> L35
                java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L35
                r3 = 1
                r4 = 907135001(0x3611c819, float:2.1723156E-6)
                r2.<init>(r3, r4, r9)     // Catch: java.lang.Throwable -> L35
                org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L30
                r9.<init>()     // Catch: java.lang.Throwable -> L30
                java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L30
                com.huawei.hms.common.internal.TaskApiCallWrapper r3 = r7.b     // Catch: java.lang.Throwable -> L2c
                com.huawei.hmf.tasks.TaskCompletionSource r3 = r3.getTaskCompletionSource()     // Catch: java.lang.Throwable -> L2c
                com.huawei.hms.common.internal.TaskApiCallWrapper r4 = r7.b     // Catch: java.lang.Throwable -> L2a
                com.huawei.hms.common.internal.TaskApiCall r1 = r4.getTaskApiCall()     // Catch: java.lang.Throwable -> L2a
                goto L53
            L2a:
                r4 = move-exception
                goto L3e
            L2c:
                r3 = move-exception
                r4 = r3
                r3 = r1
                goto L3e
            L30:
                r9 = move-exception
                r4 = r9
                r9 = r1
                r3 = r9
                goto L3e
            L35:
                r9 = move-exception
                r4 = r9
                goto L3b
            L38:
                r8 = move-exception
                r4 = r8
                r8 = r1
            L3b:
                r9 = r1
                r2 = r9
                r3 = r2
            L3e:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r6 = "<notifyCpException> "
                r5.<init>(r6)
                java.lang.String r4 = r4.getMessage()
                r5.append(r4)
                java.lang.String r4 = r5.toString()
                com.huawei.hms.support.log.HMSLog.e(r0, r4)
            L53:
                if (r8 == 0) goto L61
                if (r2 == 0) goto L61
                if (r9 == 0) goto L61
                if (r3 == 0) goto L61
                if (r1 == 0) goto L61
                r1.onResponse(r8, r2, r9, r3)
                goto L66
            L61:
                java.lang.String r8 = "<notifyCpException> isNotify is false, Can not notify CP."
                com.huawei.hms.support.log.HMSLog.e(r0, r8)
            L66:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.HuaweiApi.a.a(com.huawei.hms.common.HuaweiApi$RequestHandler, java.lang.Throwable):void");
        }

        @Override // java.lang.Runnable
        public void run() {
            RequestHandler requestHandler = new RequestHandler(this.f4439a);
            try {
                requestHandler.a(this.b);
            } catch (Throwable th) {
                HMSLog.e("HuaweiApi", "sendRequest has exception", th);
                a(requestHandler, th);
            }
        }
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.h = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, i, null);
    }

    @Deprecated
    public Task<Boolean> disconnectService() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        RequestManager.getInstance();
        RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.1
            @Override // java.lang.Runnable
            public void run() {
                HuaweiApi.this.a((HuaweiApi<?>) this, (TaskCompletionSource<Boolean>) taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    public <TResult, TClient extends AnyClient> Task<TResult> doWrite(TaskApiCall<TClient, TResult> taskApiCall) {
        this.k = true;
        if (taskApiCall == null) {
            HMSLog.e("HuaweiApi", "in doWrite:taskApiCall is null");
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(Status.FAILURE));
            return taskCompletionSource.getTask();
        }
        HiAnalyticsInnerClient.reportEntryClient(this.f4429a, taskApiCall.getUri(), TextUtils.isEmpty(this.g.getSubAppID()) ? this.f : this.g.getSubAppID(), taskApiCall.getTransactionId(), String.valueOf(getKitSdkVersion()));
        if (this.n == null) {
            this.n = RequestManager.getInstance();
        }
        return a(taskApiCall);
    }

    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.h;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getApiLevel() {
        return this.j;
    }

    public String getAppID() {
        return this.f;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.huawei.hms.common.internal.AnyClient] */
    public AnyClient getClient(Looper looper, RequestHandler requestHandler) {
        return this.d.buildClient(this.f4429a, getClientSetting(), requestHandler, requestHandler);
    }

    protected ClientSettings getClientSetting() {
        ClientSettings clientSettings = new ClientSettings(this.f4429a.getPackageName(), this.f4429a.getClass().getName(), getScopes(), this.e, null, this.g);
        if (!this.m) {
            String hMSPackageNameForMultiService = HMSPackageManager.getInstance(this.f4429a).getHMSPackageNameForMultiService();
            if (TextUtils.isEmpty(hMSPackageNameForMultiService)) {
                hMSPackageNameForMultiService = "com.huawei.hwid";
            }
            this.l = hMSPackageNameForMultiService;
            HMSLog.i("HuaweiApi", "No setInnerHms, hms pkg name is " + this.l);
        }
        clientSettings.setInnerHmsPkg(this.l);
        clientSettings.setUseInnerHms(this.m);
        WeakReference<Activity> weakReference = this.h;
        if (weakReference != null) {
            clientSettings.setCpActivity(weakReference.get());
        }
        return clientSettings;
    }

    public Context getContext() {
        return this.f4429a;
    }

    public int getKitSdkVersion() {
        return this.i;
    }

    public TOption getOption() {
        return this.b;
    }

    protected List<Scope> getScopes() {
        return Collections.emptyList();
    }

    public String getSubAppID() {
        return this.g.getSubAppID();
    }

    public void setApiLevel(int i) {
        this.j = i;
    }

    public void setHostContext(Context context) {
        this.c = context;
    }

    public void setInnerHms() {
        this.l = this.f4429a.getPackageName();
        this.m = true;
        HMSLog.i("HuaweiApi", "<setInnerHms> init inner hms pkg info:" + this.l);
    }

    public void setKitSdkVersion(int i) {
        this.i = i;
    }

    public void setSubAppId(String str) throws ApiException {
        if (!setSubAppInfo(new SubAppInfo(str))) {
            throw new ApiException(Status.FAILURE);
        }
    }

    @Deprecated
    public boolean setSubAppInfo(SubAppInfo subAppInfo) {
        HMSLog.i("HuaweiApi", "Enter setSubAppInfo");
        SubAppInfo subAppInfo2 = this.g;
        if (subAppInfo2 != null && !TextUtils.isEmpty(subAppInfo2.getSubAppID())) {
            HMSLog.e("HuaweiApi", "subAppInfo is already set");
            return false;
        }
        if (subAppInfo == null) {
            HMSLog.e("HuaweiApi", "subAppInfo is null");
            return false;
        }
        String subAppID = subAppInfo.getSubAppID();
        if (TextUtils.isEmpty(subAppID)) {
            HMSLog.e("HuaweiApi", "subAppId is empty");
            return false;
        }
        if (subAppID.equals(this.e)) {
            HMSLog.e("HuaweiApi", "subAppId is host appid");
            return false;
        }
        if (this.k) {
            HMSLog.e("HuaweiApi", "Client has sent request to Huawei Mobile Services, setting subAppId is not allowed");
            return false;
        }
        this.g = new SubAppInfo(subAppInfo);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        String appId = Util.getAppId(context);
        this.e = appId;
        this.f = appId;
    }

    private void a(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        this.f4429a = context.getApplicationContext();
        this.b = toption;
        this.d = abstractClientBuilder;
        b(context);
        this.g = new SubAppInfo("");
        this.i = i;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(this.e)) {
                HMSLog.e("HuaweiApi", "subAppId is host appid");
            } else {
                HMSLog.i("HuaweiApi", "subAppId is " + str);
                this.g = new SubAppInfo(str);
            }
        }
        a(context);
        if (Util.isAvailableLibExist(context)) {
            AvailableUtil.asyncCheckHmsUpdateInfo(context);
        }
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.h = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, i, str);
    }

    private void a(Context context) {
        HMSBIInitializer.getInstance(context).initBI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HuaweiApi<?> huaweiApi, TaskCompletionSource<Boolean> taskCompletionSource) {
        HMSLog.i("HuaweiApi", "innerDisconnect.");
        try {
            huaweiApi.getClient(RequestManager.getHandler().getLooper(), null).disconnect();
            taskCompletionSource.setResult(Boolean.TRUE);
        } catch (Exception e) {
            HMSLog.w("HuaweiApi", "disconnect the binder failed for:" + e.getMessage());
        }
    }

    private <TResult, TClient extends AnyClient> Task<TResult> a(TaskApiCall<TClient, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource;
        if (taskApiCall.getToken() == null) {
            taskCompletionSource = new TaskCompletionSource();
        } else {
            taskCompletionSource = new TaskCompletionSource(taskApiCall.getToken());
        }
        RequestManager.getHandler().post(new a(this, new TaskApiCallWrapper(taskApiCall, taskCompletionSource)));
        return taskCompletionSource.getTask();
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.h = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, 0, null);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, i, null);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, i, str);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, 0, null);
    }
}
