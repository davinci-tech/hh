package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.ConditionVariable;
import android.text.TextUtils;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.m.w.a;
import defpackage.ih;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class me {

    public static final class a implements a.InterfaceC0014a<Object, Boolean> {
        @Override // com.alipay.sdk.m.w.a.InterfaceC0014a
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object obj) {
            return Boolean.valueOf((obj instanceof String) || obj == null);
        }
    }

    public static final class b implements a.InterfaceC0014a<Object, Boolean> {
        @Override // com.alipay.sdk.m.w.a.InterfaceC0014a
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object obj) {
            return Boolean.valueOf((obj instanceof String) || obj == null);
        }
    }

    public static final class c implements a.InterfaceC0014a<Object, Boolean> {
        @Override // com.alipay.sdk.m.w.a.InterfaceC0014a
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object obj) {
            return Boolean.valueOf((obj instanceof String) || obj == null);
        }
    }

    public static final class d implements Callable<String> {
        public final /* synthetic */ lv c;
        public final /* synthetic */ Context e;

        public d(Context context, lv lvVar) {
            this.e = context;
            this.c = lvVar;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public String call() {
            try {
                return la.b(this.e);
            } catch (Throwable th) {
                kl.c(this.c, "third", "GetUtdidEx", th.getClass().getName());
                return "";
            }
        }
    }

    public static final class e implements a.InterfaceC0014a<Object, Boolean> {
        @Override // com.alipay.sdk.m.w.a.InterfaceC0014a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object obj) {
            return Boolean.valueOf((obj instanceof NetworkInfo) || obj == null);
        }
    }

    public static final class j implements Callable<String> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ lv f14914a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ String d;
        public final /* synthetic */ String e;

        public class e implements APSecuritySdk.InitResultListener {
            public final /* synthetic */ ConditionVariable b;
            public final /* synthetic */ String[] d;

            public e(String[] strArr, ConditionVariable conditionVariable) {
                this.d = strArr;
                this.b = conditionVariable;
            }

            @Override // com.alipay.apmobilesecuritysdk.face.APSecuritySdk.InitResultListener
            public void onResult(APSecuritySdk.TokenResult tokenResult) {
                if (tokenResult != null) {
                    this.d[0] = tokenResult.apdidToken;
                }
                this.b.open();
            }
        }

        public j(String str, String str2, Context context, lv lvVar) {
            this.d = str;
            this.e = str2;
            this.b = context;
            this.f14914a = lvVar;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public String call() {
            HashMap hashMap = new HashMap();
            hashMap.put("tid", this.d);
            hashMap.put("utdid", this.e);
            String[] strArr = {""};
            try {
                APSecuritySdk aPSecuritySdk = APSecuritySdk.getInstance(this.b);
                ConditionVariable conditionVariable = new ConditionVariable();
                aPSecuritySdk.initToken(0, hashMap, new e(strArr, conditionVariable));
                conditionVariable.block(3000L);
            } catch (Throwable th) {
                ma.c(th);
                kl.c(this.f14914a, "third", "GetApdidEx", th.getClass().getName());
            }
            if (TextUtils.isEmpty(strArr[0])) {
                kl.c(this.f14914a, "third", "GetApdidNull", "missing token");
            }
            return strArr[0];
        }
    }

    public static String a(lv lvVar, Context context) {
        if (!kr.a().aa()) {
            return "";
        }
        final Context e2 = com.alipay.sdk.m.w.a.e(context);
        return (String) com.alipay.sdk.m.w.a.b(1, 1L, TimeUnit.DAYS, new c(), new Callable<String>() { // from class: com.alipay.sdk.m.w.b$b
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public String call() {
                return ih.d(e2);
            }
        }, true, 200L, TimeUnit.MILLISECONDS, lvVar, true);
    }

    public static NetworkInfo bi_(lv lvVar, Context context) {
        final Context e2 = com.alipay.sdk.m.w.a.e(context);
        return (NetworkInfo) com.alipay.sdk.m.w.a.b(2, 10L, TimeUnit.SECONDS, new e(), new Callable<NetworkInfo>() { // from class: com.alipay.sdk.m.w.b$d
            @Override // java.util.concurrent.Callable
            /* renamed from: bj_, reason: merged with bridge method [inline-methods] */
            public NetworkInfo call() {
                return ((ConnectivityManager) e2.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            }
        }, false, 10L, TimeUnit.SECONDS, lvVar, false);
    }

    public static String d(lv lvVar, Context context) {
        return (String) com.alipay.sdk.m.w.a.b(3, 1L, TimeUnit.DAYS, new a(), new d(com.alipay.sdk.m.w.a.e(context), lvVar), true, 3L, TimeUnit.SECONDS, lvVar, false);
    }

    public static String e(lv lvVar, Context context, String str, String str2) {
        return (String) com.alipay.sdk.m.w.a.b(4, 10L, TimeUnit.SECONDS, new b(), new j(str, str2, com.alipay.sdk.m.w.a.e(context), lvVar), true, 3L, TimeUnit.SECONDS, lvVar, true);
    }
}
