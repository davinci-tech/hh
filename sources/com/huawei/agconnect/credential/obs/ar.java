package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.annotation.Singleton;
import com.huawei.agconnect.common.api.BackendService;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.core.service.auth.CredentialsProvider;
import com.huawei.agconnect.core.service.auth.Token;
import com.huawei.agconnect.exception.AGCServerException;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Singleton
/* loaded from: classes8.dex */
public class ar implements CredentialsProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1754a = "CredentialsProviderImpl";
    private static final long b = 3600000;
    private final Executor c;
    private final String d;
    private ap e = new ap();
    private long f;
    private final AGConnectInstance g;

    @Override // com.huawei.agconnect.core.service.auth.CredentialsProvider
    public Task<Token> getTokens(final boolean z) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (a(z)) {
            this.c.execute(new Runnable() { // from class: com.huawei.agconnect.credential.obs.ar.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ar.this.a(z)) {
                        ar.this.a((TaskCompletionSource<Token>) taskCompletionSource);
                    } else {
                        taskCompletionSource.setResult(ar.this.e);
                    }
                }
            });
        } else {
            taskCompletionSource.setResult(this.e);
        }
        return taskCompletionSource.getTask();
    }

    @Override // com.huawei.agconnect.core.service.auth.CredentialsProvider
    public Task<Token> getTokens() {
        return getTokens(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(boolean z) {
        ap apVar = this.e;
        return apVar == null || !apVar.a() || (z && (this.f == 0 || SystemClock.elapsedRealtime() - this.f > 3600000));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final TaskCompletionSource<Token> taskCompletionSource) {
        IllegalArgumentException illegalArgumentException;
        am amVar = new am(this.g);
        if (TextUtils.isEmpty(amVar.getClientId())) {
            illegalArgumentException = new IllegalArgumentException("client token request miss client id, please check whether the 'agconnect-services.json' is configured correctly");
        } else {
            if (!TextUtils.isEmpty(amVar.getClientSecret())) {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                BackendService.sendRequest(amVar, 1, an.class, new BackendService.Options.Builder().app(this.g).clientToken(false).build()).addOnCompleteListener(TaskExecutors.immediate(), new OnCompleteListener<an>() { // from class: com.huawei.agconnect.credential.obs.ar.2
                    @Override // com.huawei.hmf.tasks.OnCompleteListener
                    public void onComplete(Task<an> task) {
                        Logger.i(ar.f1754a, "onComplete");
                        if (!task.isSuccessful()) {
                            taskCompletionSource.setException(task.getException());
                            countDownLatch.countDown();
                            return;
                        }
                        an result = task.getResult();
                        if (result.getRet() != null && result.getRet().getCode() != 0) {
                            taskCompletionSource.setException(new AGCServerException(result.getRet().getMsg(), result.getRet().getCode()));
                            countDownLatch.countDown();
                            return;
                        }
                        ar.this.e = new ap(result.getAccessToken(), result.getExpiresIn());
                        aq.a().a(ar.this.e, ar.this.d);
                        aq.a().b(ar.this.e, ar.this.d);
                        aq.a().c(ar.this.e, ar.this.d);
                        countDownLatch.countDown();
                        ar.this.f = SystemClock.elapsedRealtime();
                        taskCompletionSource.setResult(ar.this.e);
                    }
                });
                try {
                    if (countDownLatch.await(5L, TimeUnit.SECONDS)) {
                        return;
                    }
                    Logger.e(f1754a, "await failed");
                    return;
                } catch (InterruptedException unused) {
                    Logger.e(f1754a, "await InterruptedException");
                    return;
                }
            }
            illegalArgumentException = new IllegalArgumentException("client token request miss client secret, please check whether the 'agconnect-services.json' is configured correctly");
        }
        taskCompletionSource.setException(illegalArgumentException);
    }

    public ar(Context context, AGConnectInstance aGConnectInstance) {
        this.g = aGConnectInstance;
        String identifier = aGConnectInstance.getOptions().getIdentifier();
        this.d = identifier;
        aq.a().d(this.e, identifier);
        aq.a().e(this.e, identifier);
        aq.a().f(this.e, identifier);
        this.c = Executors.newSingleThreadExecutor();
    }
}
