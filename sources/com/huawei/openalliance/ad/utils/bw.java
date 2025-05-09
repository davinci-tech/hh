package com.huawei.openalliance.ad.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.xi;
import java.security.MessageDigest;

/* loaded from: classes5.dex */
public class bw {

    /* renamed from: a, reason: collision with root package name */
    private static String f7643a = null;
    private static String b = "";

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, IBinder iBinder) {
        String packageName = context.getPackageName();
        if (f7643a == null) {
            try {
                byte[] digest = MessageDigest.getInstance("SHA1").digest(context.getPackageManager().getPackageInfo(packageName, 64).signatures[0].toByteArray());
                StringBuilder sb = new StringBuilder();
                for (byte b2 : digest) {
                    sb.append(Integer.toHexString((b2 & 255) | 256).substring(1, 3));
                }
                String sb2 = sb.toString();
                f7643a = sb2;
                return a(iBinder, packageName, sb2);
            } catch (Throwable th) {
                ho.c("OIdentifierManager", "realGetOUID ex: %s", th.getClass().getSimpleName());
            }
        }
        return a(iBinder, packageName, f7643a);
    }

    private static String a(IBinder iBinder, String str, String str2) {
        try {
            xi xiVar = (xi) xi.a.class.getDeclaredMethod(VideoPlayFlag.PLAY_IN_ALL, IBinder.class).invoke(null, iBinder);
            if (xiVar == null) {
                ho.c("OIdentifierManager", "IOpenID is null");
            }
            return xiVar.a(str, str2, "OUID");
        } catch (Throwable th) {
            ho.c("OIdentifierManager", "getSerId error: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public static String a(final Context context) {
        Intent intent = new Intent("action.com.heytap.openid.OPEN_ID_SERVICE");
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        try {
            if (!context.bindService(intent, new ServiceConnection() { // from class: com.huawei.openalliance.ad.utils.bw.1
                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    ho.b("OIdentifierManager", "HeyTap IdentifyService disconnected");
                }

                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    ho.b("OIdentifierManager", "HeyTap IdentifyService connected");
                    try {
                        String b2 = bw.b(context, iBinder);
                        try {
                            if (b2 != null && b2.length() != 0) {
                                String unused = bw.b = b2;
                                context.unbindService(this);
                                return;
                            }
                            context.unbindService(this);
                            return;
                        } catch (Throwable th) {
                            ho.c("OIdentifierManager", "HeyTap IdentifyService, unbind service error: %s", th.getClass().getSimpleName());
                            return;
                        }
                        ho.c("OIdentifierManager", "HeyTap OUID get failed");
                    } catch (Throwable th2) {
                        try {
                            ho.c("OIdentifierManager", "HeyTap IdentifyService, bind service error: %s", th2.getClass().getSimpleName());
                            try {
                                context.unbindService(this);
                            } catch (Throwable th3) {
                                ho.c("OIdentifierManager", "HeyTap IdentifyService, unbind service error: %s", th3.getClass().getSimpleName());
                            }
                        } catch (Throwable th4) {
                            try {
                                context.unbindService(this);
                            } catch (Throwable th5) {
                                ho.c("OIdentifierManager", "HeyTap IdentifyService, unbind service error: %s", th5.getClass().getSimpleName());
                            }
                            throw th4;
                        }
                    }
                }
            }, 1)) {
                ho.c("OIdentifierManager", "HeyTap IdentifyService bind failed");
            }
        } catch (Throwable th) {
            ho.c("OIdentifierManager", "get oaid error: %s", th.getClass().getSimpleName());
        }
        return b;
    }
}
