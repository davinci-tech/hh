package com.huawei.openalliance.ad.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.gi;
import com.huawei.openalliance.ad.gj;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class aq {
    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final IBinder iBinder, final boolean z, final cg cgVar) {
        m.b(new Runnable() { // from class: com.huawei.openalliance.ad.utils.aq.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    gj a2 = gj.a.a(iBinder);
                    if (a2 == null) {
                        return;
                    }
                    a2.a(new gi.a() { // from class: com.huawei.openalliance.ad.utils.aq.2.1
                        @Override // com.huawei.openalliance.ad.gi
                        public void a(int i, long j, boolean z2, float f, double d, String str) {
                        }

                        @Override // com.huawei.openalliance.ad.gi
                        public void a(int i, Bundle bundle) {
                            if (bundle == null) {
                                ho.d("HonorIdentifierManager", "param err");
                                return;
                            }
                            String string = bundle.getString("oa_id_flag");
                            boolean z2 = TextUtils.isEmpty(string) || Constants.NIL_UUID.equals(string);
                            cgVar.a(string, Boolean.valueOf(z2), z);
                            ho.a("HonorIdentifierManager", "OAIDCallBack handleResult success oaid: %s, oaidLimitState: %s", string, Boolean.valueOf(z2));
                        }
                    });
                } catch (Throwable th) {
                    ho.d("HonorIdentifierManager", "HnOaIdService get oaid error: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    public static Pair<String, Boolean> a(final Context context, final boolean z) {
        try {
            ho.a("HonorIdentifierManager", "requestHonorOaid start");
            PackageManager packageManager = context.getPackageManager();
            final cg a2 = cg.a(context);
            Intent intent = new Intent("com.hihonor.id.HnOaIdService");
            intent.setPackage("com.hihonor.id");
            if (!packageManager.queryIntentServices(intent, 0).isEmpty()) {
                context.bindService(intent, new ServiceConnection() { // from class: com.huawei.openalliance.ad.utils.aq.1
                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName componentName) {
                        ho.a("HonorIdentifierManager", "HnOaIdService IdentifyService disconnected");
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        ho.a("HonorIdentifierManager", "HnOaIdService IdentifyService connected");
                        try {
                            try {
                                aq.b(iBinder, z, a2);
                            } catch (Throwable th) {
                                try {
                                    context.unbindService(this);
                                } catch (Throwable unused) {
                                    ho.d("HonorIdentifierManager", "HnOaIdService IdentifyService, unbind service error");
                                }
                                throw th;
                            }
                        } catch (Throwable unused2) {
                            ho.d("HonorIdentifierManager", "HnOaIdService IdentifyService, bind service error");
                        }
                        try {
                            context.unbindService(this);
                        } catch (Throwable unused3) {
                            ho.d("HonorIdentifierManager", "HnOaIdService IdentifyService, unbind service error");
                        }
                    }
                }, 1);
            }
            return a2.ag();
        } catch (Exception unused) {
            ho.d("HonorIdentifierManager", "HnOaIdService IdentifyService getPackageInfo exception.");
            return null;
        }
    }

    public static Pair<String, Boolean> a(Context context) {
        gc b = fh.b(context);
        long cl = b.cl();
        long R = b.R() * 60000;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - cl >= R) {
            ho.a("HonorIdentifierManager", "HnOaIdService getOaidAndTrackLimit cache lastReqHonorOaidTime: %s, reqOaidTimeInterval: %s", Long.valueOf(cl), Long.valueOf(R));
            b.o(currentTimeMillis);
            return a(context, false);
        }
        Pair<String, Boolean> ag = cg.a(context).ag();
        if (ho.a() && ag != null) {
            ho.a("HonorIdentifierManager", "HnOaIdService getOaidAndTrackLimit cache oaid: %s, oaidLimitState: %s", ag.first, ag.second);
        }
        return ag;
    }
}
