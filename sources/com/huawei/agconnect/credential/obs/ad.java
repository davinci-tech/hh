package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.Backend;
import com.huawei.agconnect.common.api.BackendService;
import com.huawei.agconnect.common.api.BaseRequest;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.common.api.RequestThrottle;
import com.huawei.agconnect.core.service.auth.AuthProvider;
import com.huawei.agconnect.core.service.auth.CredentialsProvider;
import com.huawei.agconnect.core.service.auth.Token;
import com.huawei.agconnect.exception.AGCServerException;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class ad {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1727a = "BackendServiceImpl";

    private static <Rsp> Task<Rsp> d(BaseRequest baseRequest, int i, Class<Rsp> cls, BackendService.Options options) {
        return (options == null || options.getFactory() == null) ? Backend.call(baseRequest, i, cls, a(options).getOptions()) : Backend.call(baseRequest, i, cls, options.getFactory(), options.getTimeout(), options.getTimeUnit(), a(options).getOptions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <Rsp> void c(final BaseRequest baseRequest, final int i, final Class<Rsp> cls, final TaskCompletionSource<Rsp> taskCompletionSource, final BackendService.Options options) {
        d(baseRequest, i, cls, options).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<Rsp>() { // from class: com.huawei.agconnect.credential.obs.ad.13
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(Rsp rsp) {
                TaskCompletionSource.this.setResult(rsp);
            }
        }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ad.12
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ad.b(exc, BackendService.Options.this, baseRequest, i, cls, taskCompletionSource);
            }
        });
    }

    private static <Rsp> Task<Rsp> c(final BaseRequest baseRequest, final int i, final Class<Rsp> cls, final BackendService.Options options) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ((CredentialsProvider) a(options).getService(CredentialsProvider.class)).getTokens().addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<Token>() { // from class: com.huawei.agconnect.credential.obs.ad.9
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Token token) {
                ad.b(token, BaseRequest.this, options, taskCompletionSource, i, cls);
            }
        }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ad.8
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                TaskCompletionSource.this.setException(exc);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <Rsp> void b(final Exception exc, final BackendService.Options options, final BaseRequest baseRequest, final int i, final Class<Rsp> cls, final TaskCompletionSource<Rsp> taskCompletionSource) {
        Task<Token> addOnSuccessListener;
        Executor immediate;
        OnFailureListener onFailureListener;
        if (a(exc)) {
            int retCode = ((AGCServerException) exc).getRetCode();
            if (retCode == 205524993 && !options.isClientTokenRefreshed()) {
                options.setClientTokenRefreshed(true);
                addOnSuccessListener = ((CredentialsProvider) a(options).getService(CredentialsProvider.class)).getTokens(true).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<Token>() { // from class: com.huawei.agconnect.credential.obs.ad.2
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Token token) {
                        BaseRequest.this.setAuthorization(HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + token.getTokenString());
                        ad.c(BaseRequest.this, i, cls, taskCompletionSource, options);
                    }
                });
                immediate = TaskExecutors.immediate();
                onFailureListener = new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ad.14
                    @Override // com.huawei.hmf.tasks.OnFailureListener
                    public void onFailure(Exception exc2) {
                        TaskCompletionSource.this.setException(exc2);
                    }
                };
            } else if (retCode == 205524994 && !options.isAccessTokenRefreshed()) {
                options.setAccessTokenRefreshed(true);
                AuthProvider authProvider = (AuthProvider) a(options).getService(AuthProvider.class);
                if (authProvider == null) {
                    taskCompletionSource.setException(exc);
                    return;
                } else {
                    addOnSuccessListener = authProvider.getTokens(true).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<Token>() { // from class: com.huawei.agconnect.credential.obs.ad.4
                        @Override // com.huawei.hmf.tasks.OnSuccessListener
                        /* renamed from: a, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(Token token) {
                            if (token == null) {
                                taskCompletionSource.setException(exc);
                            } else {
                                BaseRequest.this.setAccessToken(token.getTokenString());
                                ad.c(BaseRequest.this, i, cls, taskCompletionSource, options);
                            }
                        }
                    });
                    immediate = TaskExecutors.immediate();
                    onFailureListener = new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ad.3
                        @Override // com.huawei.hmf.tasks.OnFailureListener
                        public void onFailure(Exception exc2) {
                            TaskCompletionSource.this.setException(exc2);
                        }
                    };
                }
            }
            addOnSuccessListener.addOnFailureListener(immediate, onFailureListener);
            return;
        }
        taskCompletionSource.setException(exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <Rsp> void b(Token token, final BaseRequest baseRequest, final BackendService.Options options, final TaskCompletionSource<Rsp> taskCompletionSource, final int i, final Class<Rsp> cls) {
        baseRequest.setAuthorization(HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + token.getTokenString());
        if (!options.isAccessToken()) {
            a(baseRequest, i, cls, taskCompletionSource, options);
            return;
        }
        AuthProvider authProvider = (AuthProvider) a(options).getService(AuthProvider.class);
        if (authProvider == null) {
            taskCompletionSource.setException(new AGCServerException("no user login", 3));
        } else {
            authProvider.getTokens().addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<Token>() { // from class: com.huawei.agconnect.credential.obs.ad.11
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Token token2) {
                    if (token2 != null) {
                        BaseRequest.this.setAccessToken(token2.getTokenString());
                    } else if (options.getAccessTokenType() != BackendService.AccessTokenType.EXPECT) {
                        taskCompletionSource.setException(new AGCServerException("no user login", 3));
                        return;
                    }
                    ad.a(BaseRequest.this, i, cls, taskCompletionSource, options);
                }
            }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ad.10
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    TaskCompletionSource.this.setException(exc);
                }
            });
        }
    }

    public static <Rsp> Task<Rsp> b(BaseRequest baseRequest, int i, Class<Rsp> cls, BackendService.Options options) {
        Logger.i(f1727a, "sendRequest2");
        BackendService.Options build = options == null ? new BackendService.Options.Builder().app(AGConnectInstance.getInstance()).build() : options;
        if (build.getApp() == null) {
            build = build.newBuilder().app(AGConnectInstance.getInstance()).build();
        }
        baseRequest.initBase(build.getApp());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new aj(build));
        if (!a(cls)) {
            return Backend.call(baseRequest, i, cls, build.getFactory(), build.getTimeout(), build.getTimeUnit(), arrayList, null, a(build).getOptions());
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final RequestThrottle.Throttle throttle = build.getThrottle();
        Logger.i(f1727a, "clientToken:" + build.isClientToken());
        if (build.isClientToken()) {
            arrayList.add(new af(build.getApp()));
        }
        if (build.isApiKey()) {
            arrayList.add(new ab(build.getApp()));
        }
        Logger.i(f1727a, "accessToken:" + build.isAccessToken());
        if (build.isAccessToken()) {
            arrayList.add(new aa(build.getApp(), build.getAccessTokenType() == BackendService.AccessTokenType.MUST, build.isAccessTokenExtra()));
        }
        if (build.getThrottle() != null) {
            if (build.getThrottle().getEndTime() > 0) {
                taskCompletionSource.setException(new AGCServerException("fetch throttled, try again later", 1));
                return taskCompletionSource.getTask();
            }
            build.getThrottle().addForStart();
        }
        Backend.call(baseRequest, i, cls, build.getFactory(), build.getTimeout(), build.getTimeUnit(), arrayList, new y(build), a(build).getOptions()).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<Rsp>() { // from class: com.huawei.agconnect.credential.obs.ad.6
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(Rsp rsp) {
                RequestThrottle.Throttle throttle2 = RequestThrottle.Throttle.this;
                if (throttle2 != null) {
                    throttle2.addForSuccess();
                }
                taskCompletionSource.setResult(rsp);
            }
        }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ad.5
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                RequestThrottle.Throttle throttle2 = RequestThrottle.Throttle.this;
                if (throttle2 != null && throttle2.checkFail(exc)) {
                    RequestThrottle.Throttle.this.addForFail();
                }
                taskCompletionSource.setException(exc);
            }
        });
        return taskCompletionSource.getTask();
    }

    private static boolean a(Exception exc) {
        return (exc instanceof AGCServerException) && ((AGCServerException) exc).getCode() == 401;
    }

    private static boolean a(Class cls) {
        return (cls == an.class || cls == av.class) ? false : true;
    }

    public static <Rsp> void a(BaseRequest baseRequest, int i, Class<Rsp> cls, TaskCompletionSource<Rsp> taskCompletionSource, BackendService.Options options) {
        if (options.getThrottle() != null) {
            if (options.getThrottle().getEndTime() > 0) {
                taskCompletionSource.setException(new AGCServerException("fetch throttled, try again later", 1));
                return;
            }
            options.getThrottle().addForStart();
        }
        c(baseRequest, i, cls, taskCompletionSource, options);
    }

    public static <Rsp> Task<Rsp> a(BaseRequest baseRequest, int i, Class<Rsp> cls, BackendService.Options options) {
        if (options != null && options.getApp() != null) {
            baseRequest.initBase(options.getApp());
        }
        if (options == null || !options.isClientToken()) {
            return Backend.call(baseRequest, i, cls, a(options).getOptions());
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final RequestThrottle.Throttle throttle = options.getThrottle();
        c(baseRequest, i, cls, options).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<Rsp>() { // from class: com.huawei.agconnect.credential.obs.ad.7
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(Rsp rsp) {
                RequestThrottle.Throttle throttle2 = RequestThrottle.Throttle.this;
                if (throttle2 != null) {
                    throttle2.addForSuccess();
                }
                taskCompletionSource.setResult(rsp);
            }
        }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ad.1
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                RequestThrottle.Throttle throttle2 = RequestThrottle.Throttle.this;
                if (throttle2 != null && throttle2.checkFail(exc)) {
                    RequestThrottle.Throttle.this.addForFail();
                }
                taskCompletionSource.setException(exc);
            }
        });
        return taskCompletionSource.getTask();
    }

    private static AGConnectInstance a(BackendService.Options options) {
        return (options == null || options.getApp() == null) ? AGConnectInstance.getInstance() : options.getApp();
    }
}
