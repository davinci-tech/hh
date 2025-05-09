package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.android.hms.ppskit.d;
import com.huawei.android.hms.ppskit.e;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ipc.b;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ms extends com.huawei.openalliance.ad.ipc.b<com.huawei.android.hms.ppskit.e> {
    private static ms d;
    private static final byte[] e = new byte[0];

    @Override // com.huawei.openalliance.ad.ipc.b
    public boolean f() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String g() {
        return null;
    }

    public void i() {
        ho.b(b(), "onRequestingAd");
        a(new a(), 3000L);
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String h() {
        return "com.huawei.android.hms.ppskit.PpsCoreService";
    }

    static class b<T> extends b.a<com.huawei.android.hms.ppskit.e> {

        /* renamed from: a, reason: collision with root package name */
        private String f7273a;
        private String b;
        private RemoteCallResultCallback<T> c;
        private Class<T> d;
        private Context e;

        @Override // com.huawei.openalliance.ad.ipc.b.a
        public void a(String str) {
            b("serviceCallFailed: " + str);
        }

        @Override // com.huawei.openalliance.ad.ipc.b.a
        public void a(com.huawei.android.hms.ppskit.e eVar) {
            String str;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sdk_version", "3.4.74.310");
                jSONObject.put("content", this.b);
                eVar.a(this.f7273a, jSONObject.toString(), new d.a() { // from class: com.huawei.openalliance.ad.ms.b.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.huawei.android.hms.ppskit.d
                    public void a(String str2, int i, String str3) {
                        String message;
                        if (ho.a()) {
                            ho.a("PPSApiServiceManager", "call: %s code: %s result: %s", str2, Integer.valueOf(i), com.huawei.openalliance.ad.utils.dl.a(str3));
                        }
                        CallResult callResult = new CallResult();
                        callResult.setCode(i);
                        try {
                            if (i == 200) {
                                callResult.setData(mv.a(str3, b.this.d));
                            } else {
                                callResult.setMsg(str3);
                            }
                        } catch (IllegalArgumentException e) {
                            ho.c("PPSApiServiceManager", "onCallResult IllegalArgumentException");
                            callResult.setCode(-1);
                            message = e.getMessage();
                            callResult.setMsg(message);
                            b bVar = b.this;
                            bVar.a(bVar.c, str2, callResult);
                        } catch (Throwable th) {
                            ho.c("PPSApiServiceManager", "onCallResult " + th.getClass().getSimpleName());
                            callResult.setCode(-1);
                            message = th.getMessage();
                            callResult.setMsg(message);
                            b bVar2 = b.this;
                            bVar2.a(bVar2.c, str2, callResult);
                        }
                        b bVar22 = b.this;
                        bVar22.a(bVar22.c, str2, callResult);
                    }
                });
            } catch (RemoteException unused) {
                str = "remote call RemoteException";
                b(str);
            } catch (Throwable th) {
                str = "remote call " + th.getClass().getSimpleName();
                b(str);
            }
        }

        private void b(String str) {
            ho.c("PPSApiServiceManager", str);
            CallResult callResult = new CallResult();
            callResult.setCode(-1);
            callResult.setMsg(str);
            a(this.c, this.f7273a, callResult);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(RemoteCallResultCallback remoteCallResultCallback, String str, CallResult callResult) {
            boolean aL;
            if (callResult != null) {
                ho.b("PPSApiServiceManager", "call %s code: %s msg: %s", str, Integer.valueOf(callResult.getCode()), callResult.getMsg());
                if (callResult.getCode() == -100 && (aL = fh.b(this.e).aL())) {
                    HiAd.a(this.e).a(aL);
                }
            }
            if (remoteCallResultCallback != null) {
                remoteCallResultCallback.onRemoteCallResult(str, callResult);
            }
        }

        b(Context context, String str, String str2, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
            this.e = context;
            this.f7273a = str;
            this.b = str2;
            this.c = remoteCallResultCallback;
            this.d = cls;
        }
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String d() {
        return com.huawei.openalliance.ad.utils.i.e(this.f7074a);
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String c() {
        return "com.huawei.android.hms.ppskit.PPS_API_SERVICE";
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public final String b() {
        return "PPSApiServiceManager";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.ipc.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.huawei.android.hms.ppskit.e a(IBinder iBinder) {
        return e.a.a(iBinder);
    }

    static class a extends b.a<com.huawei.android.hms.ppskit.e> {
        @Override // com.huawei.openalliance.ad.ipc.b.a
        public void a(com.huawei.android.hms.ppskit.e eVar) {
            try {
                eVar.a();
            } catch (RemoteException unused) {
                ho.c("PPSApiServiceManager", "setInstallSource RemoteException");
            }
        }

        private a() {
        }
    }

    public <T> void a(String str, String str2, RemoteCallResultCallback<T> remoteCallResultCallback, Class<T> cls) {
        ho.b(b(), "call remote method: " + str);
        a(new b(this.f7074a, str, str2, remoteCallResultCallback, cls), 3000L);
    }

    public static ms a(Context context) {
        ms msVar;
        synchronized (e) {
            if (d == null) {
                d = new ms(context);
            }
            msVar = d;
        }
        return msVar;
    }

    private ms(Context context) {
        super(context);
        this.c = new com.huawei.openalliance.ad.ipc.a(context, b(), this);
    }
}
