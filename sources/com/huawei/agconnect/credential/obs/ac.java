package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.common.api.BaseResponse;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.exception.AGCNetworkException;
import com.huawei.agconnect.exception.AGCServerException;
import com.huawei.agconnect.https.Adapter;
import com.huawei.agconnect.https.HttpsException;
import com.huawei.agconnect.https.HttpsKit;
import com.huawei.agconnect.https.HttpsResult;
import com.huawei.agconnect.https.Method;
import com.huawei.agconnect.https.adapter.JsonAdapterFactory;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Interceptor;

/* loaded from: classes2.dex */
public class ac {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1724a = "BackendImpl";
    private static final long b = 5000;
    private static final ac c = new ac();
    private static final ExecutorService d = ak.a();
    private final Map<ag, ai> e = new HashMap();
    private final Adapter.Factory f = new JsonAdapterFactory();

    public Map<ag, ai> b() {
        return this.e;
    }

    public <Req, Rsp> Task<Rsp> a(Req req, int i, Class<Rsp> cls, List<Interceptor> list, Authenticator authenticator, AGConnectOptions aGConnectOptions) {
        return a(req, i, cls, this.f, 5000L, TimeUnit.MILLISECONDS, list, authenticator, aGConnectOptions);
    }

    public <Req, Rsp> Task<Rsp> a(Req req, int i, final Class<Rsp> cls, Adapter.Factory factory, long j, TimeUnit timeUnit, List<Interceptor> list, Authenticator authenticator, AGConnectOptions aGConnectOptions) {
        AGCServerException aGCServerException;
        final Adapter.Factory factory2 = factory != null ? factory : this.f;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        String string = aGConnectOptions.getString("agcgw/url");
        String string2 = aGConnectOptions.getString("agcgw/backurl");
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
            aGCServerException = new AGCServerException("url is null", 5);
        } else {
            Context b2 = al.a().b();
            if (b2 != null) {
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    arrayList.addAll(list);
                } else {
                    arrayList.add(new aj(string, string2));
                }
                a(b2, arrayList, authenticator, j, timeUnit).create(b2).execute(a(req, i, factory2)).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<HttpsResult>() { // from class: com.huawei.agconnect.credential.obs.ac.2
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(HttpsResult httpsResult) {
                        Object response;
                        if (httpsResult.isSuccess()) {
                            if (String.class.equals(cls)) {
                                response = httpsResult.getResponse();
                            } else {
                                try {
                                    response = httpsResult.getResponse(cls, factory2);
                                } catch (RuntimeException e) {
                                    taskCompletionSource.setException(e);
                                    return;
                                }
                            }
                            taskCompletionSource.setResult(response);
                            return;
                        }
                        if (httpsResult.code() == 401) {
                            try {
                                BaseResponse baseResponse = (BaseResponse) httpsResult.getResponse(BaseResponse.class, factory2);
                                if (baseResponse != null && baseResponse.getRet() != null) {
                                    taskCompletionSource.setException(new AGCServerException(httpsResult.getErrorMsg(), httpsResult.code(), baseResponse.getRet().getCode()));
                                    return;
                                }
                            } catch (RuntimeException unused) {
                                Logger.e(ac.f1724a, "get base response error");
                            }
                        }
                        taskCompletionSource.setException(new AGCServerException(httpsResult.getErrorMsg(), httpsResult.code()));
                    }
                }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.credential.obs.ac.1
                    @Override // com.huawei.hmf.tasks.OnFailureListener
                    public void onFailure(Exception exc) {
                        taskCompletionSource.setException(exc instanceof HttpsException ? !((HttpsException) exc).hasRequest() ? new AGCNetworkException(exc.getMessage(), 0) : new AGCNetworkException(exc.getMessage(), 1) : new AGCServerException(exc.getMessage(), 2));
                    }
                });
                return taskCompletionSource.getTask();
            }
            aGCServerException = new AGCServerException("context is error", 4);
        }
        taskCompletionSource.setException(aGCServerException);
        return taskCompletionSource.getTask();
    }

    public <Req, Rsp> Task<Rsp> a(Req req, int i, Class<Rsp> cls, AGConnectOptions aGConnectOptions) {
        return a(req, i, cls, this.f, 5000L, TimeUnit.MILLISECONDS, null, null, aGConnectOptions);
    }

    private <Req> Method a(Req req, int i, Adapter.Factory factory) {
        if (i == 1) {
            return new Method.Post(req, factory);
        }
        return i == 2 ? new Method.Put(req, factory) : new Method.Get(req);
    }

    private HttpsKit a(Context context, List<Interceptor> list, Authenticator authenticator, long j, TimeUnit timeUnit) {
        if (j == 0) {
            timeUnit = TimeUnit.MILLISECONDS;
            j = 5000;
        }
        return new HttpsKit.Builder().client(new ae(context, Collections.unmodifiableList(list), true).a(authenticator).a(j, timeUnit)).executor(d).build();
    }

    public static ac a() {
        return c;
    }

    private ac() {
    }
}
