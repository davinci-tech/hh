package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.core.service.EndpointService;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.Tasks;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import okhttp3.Dns;

/* loaded from: classes8.dex */
public class at implements EndpointService {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1757a = "EndpointServiceImpl";
    private ag b;
    private boolean c;

    static class a implements Callable<String> {

        /* renamed from: a, reason: collision with root package name */
        String f1758a;
        String b;

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String call() {
            if (a(this.f1758a)) {
                return this.f1758a;
            }
            if (a(this.b)) {
                return this.b;
            }
            throw new IOException("main/backup url both are invalid");
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                int indexOf = str.indexOf(":");
                Dns.SYSTEM.lookup(indexOf != -1 ? str.substring(0, indexOf) : str);
                return true;
            } catch (UnknownHostException unused) {
                Logger.e(at.f1757a, "checkUrl#UnknownHostException:" + str);
                return false;
            }
        }

        static a a(String str, String str2) {
            a aVar = new a();
            aVar.f1758a = str;
            aVar.b = str2;
            return aVar;
        }

        a() {
        }
    }

    @Override // com.huawei.agconnect.core.service.EndpointService
    public Task<String> getEndpointDomain(boolean z) {
        if (z || !this.c) {
            return Tasks.callInBackground(a.a(this.b.a(), this.b.b()));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(this.b.c());
        return taskCompletionSource.getTask();
    }

    public at(Context context, AGConnectInstance aGConnectInstance) {
        this.c = false;
        this.b = new ag(aGConnectInstance.getOptions().getString("agcgw/url"), aGConnectInstance.getOptions().getString("agcgw/backurl"));
        if (ac.a().b().containsKey(this.b)) {
            this.b = ac.a().b().get(this.b).b();
            this.c = ac.a().b().get(this.b).c().booleanValue();
        }
    }
}
