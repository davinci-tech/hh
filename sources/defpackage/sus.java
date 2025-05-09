package defpackage;

import android.content.Context;
import com.huawei.wear.oversea.router.RouterActionCallBack;
import com.huawei.wear.oversea.router.RouterRequest;
import com.huawei.wear.oversea.router.WalletAction;
import com.huawei.wear.oversea.router.WalletProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* loaded from: classes7.dex */
public class sus {
    private static volatile sus c;
    private static final byte[] e = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private Map<String, WalletProvider> f17240a = new HashMap();

    private sus() {
    }

    public static sus d() {
        if (c == null) {
            synchronized (e) {
                if (c == null) {
                    c = new sus();
                }
            }
        }
        return c;
    }

    public suv b(RouterRequest routerRequest, RouterActionCallBack routerActionCallBack) {
        stq.d("LocalRouter", "Invoke action  " + routerRequest.d(), false);
        WalletAction b = b(routerRequest);
        if (b == null) {
            stq.d("LocalRouter", "Action not found  action " + routerRequest.d(), false);
            return new suv("99998", "Action not found,action:" + routerRequest.d());
        }
        suv suvVar = new suv();
        sux suxVar = new sux(routerRequest.b(), routerRequest.c());
        if (routerActionCallBack == null || !b.isAsync(routerRequest.e(), suxVar)) {
            stq.d("LocalRouter", "Sync invoke action " + routerRequest.d(), false);
            suvVar.c(false);
            suz invoke = b.invoke(routerRequest.e(), suxVar);
            suvVar.b(invoke.c());
            suvVar.a(invoke.e());
        } else {
            stq.d("LocalRouter", "ASync invoke action " + routerRequest.d(), false);
            suvVar.c(true);
            Future submit = svt.e().b().submit(new d(routerActionCallBack, suvVar, suxVar, routerRequest.e(), b));
            if (submit != null && submit.isDone()) {
                stq.d("LocalRouter", "LocalTask is done", false);
            }
        }
        return suvVar;
    }

    private WalletAction b(RouterRequest routerRequest) {
        String a2 = a(routerRequest);
        WalletProvider walletProvider = this.f17240a.get(a2 + ":" + routerRequest.a());
        WalletProvider walletProvider2 = this.f17240a.get(routerRequest.a());
        if (walletProvider == null && walletProvider2 == null) {
            stq.d("LocalRouter", "Provider not found  provider" + routerRequest.a(), false);
            return null;
        }
        WalletAction findAction = walletProvider != null ? walletProvider.findAction(routerRequest.b()) : null;
        if (findAction == null) {
            findAction = walletProvider2.findAction(routerRequest.b());
        }
        if (findAction == null) {
            stq.d("LocalRouter", "Action not found action" + routerRequest.b(), false);
        }
        return findAction;
    }

    static class d implements Callable<suv> {

        /* renamed from: a, reason: collision with root package name */
        private sux f17241a;
        private RouterActionCallBack b;
        private WalletAction c;
        private Context d;
        private suv e;

        public d(RouterActionCallBack routerActionCallBack, suv suvVar, sux suxVar, Context context, WalletAction walletAction) {
            this.d = context;
            this.e = suvVar;
            this.f17241a = suxVar;
            this.c = walletAction;
            this.b = routerActionCallBack;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public suv call() throws Exception {
            suz invoke = this.c.invoke(this.d, this.f17241a);
            this.e.b(invoke.a());
            this.e.a(invoke.d());
            if (invoke.c()) {
                stq.d("LocalRouter", "ASync invoke action " + this.f17241a.a() + " success!", false);
                this.b.onSuccess(this.e);
            } else {
                stq.d("LocalRouter", "ASync invoke action " + this.f17241a.a() + " failed, resultCode " + invoke.a() + " resultDesc " + invoke.d(), false);
                this.b.onFail(this.e);
            }
            return this.e;
        }
    }

    private String a(RouterRequest routerRequest) {
        return " ";
    }
}
