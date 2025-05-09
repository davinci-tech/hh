package com.huawei.caas.messageservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.caas.messageservice.HwShareUtils;
import com.huawei.caassharea.caassharea.caassharea;
import com.huawei.caassharea.caassharea.caasshareb;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes8.dex */
public class HwCaasShareManager {
    private static HwCaasShareManager caasshareb;
    Context caassharea;
    private volatile boolean caassharec;
    private HwCaasShareHandler caasshared;
    private HwCaasShareCallBack caassharee;
    private volatile caasshareb caasshareg;
    private final com.huawei.caassharea.caassharea.caassharea caassharef = new caassharea.AbstractBinderC0046caassharea() { // from class: com.huawei.caas.messageservice.HwCaasShareManager.1
        @Override // com.huawei.caassharea.caassharea.caassharea
        public final void caassharea(int i, String str) {
            Log.d("HwCaaSShareManager", "retCode: " + i + "Meetime apk version " + str);
            if (i == 0) {
                Log.i("HwCaaSShareManager", "HwCaaSShareCallback initSuccess!");
                if (HwCaasShareManager.this.caassharee != null) {
                    HwCaasShareManager.this.caassharee.initSuccess(HwCaasShareManager.this.caasshared);
                    return;
                }
                return;
            }
            if (i != 2001) {
                if (HwCaasShareManager.this.caassharee != null) {
                    Log.i("HwCaaSShareManager", "HwCaaSShareCallback initFail! retCode is " + i);
                    HwCaasShareManager.this.caassharee.initFail(i);
                    return;
                }
                return;
            }
            Log.i("HwCaaSShareManager", "HwCaaSShareCallback initFail! retCode is " + i);
            if (HwCaasShareManager.this.caassharee != null) {
                HwCaasShareManager.this.caassharee.initFail(i);
            }
        }

        @Override // com.huawei.caassharea.caassharea.caassharea
        public final void caassharea(int i) {
            HwCaasShareCallBack hwCaasShareCallBack;
            HwShareUtils.SendResultEnum sendResultEnum;
            Log.i("HwCaaSShareManager", "callStateCallback callState : " + i);
            switch (i) {
                case 1000:
                    Log.d("HwCaaSShareManager", "HwCaaSShareCallback SHARE_SUCCESS!");
                    if (HwCaasShareManager.this.caassharee != null) {
                        hwCaasShareCallBack = HwCaasShareManager.this.caassharee;
                        sendResultEnum = HwShareUtils.SendResultEnum.SEND_SUCCESS;
                        break;
                    } else {
                        return;
                    }
                case 1001:
                    Log.d("HwCaaSShareManager", "HwCaaSShareCallback SHARE_FAIL!");
                    if (HwCaasShareManager.this.caassharee != null) {
                        hwCaasShareCallBack = HwCaasShareManager.this.caassharee;
                        sendResultEnum = HwShareUtils.SendResultEnum.SEND_FAIL;
                        break;
                    } else {
                        return;
                    }
                case 1002:
                    Log.d("HwCaaSShareManager", "HwCaaSShareCallback SHARE_CANCEL!");
                    if (HwCaasShareManager.this.caassharee != null) {
                        hwCaasShareCallBack = HwCaasShareManager.this.caassharee;
                        sendResultEnum = HwShareUtils.SendResultEnum.SEND_CANCEL;
                        break;
                    } else {
                        return;
                    }
                default:
                    Log.e("HwCaaSShareManager", "HwCaaSShareCallback error!");
                    return;
            }
            hwCaasShareCallBack.sendResult(sendResultEnum);
        }
    };
    private final ServiceConnection caasshareh = new ServiceConnection() { // from class: com.huawei.caas.messageservice.HwCaasShareManager.2
        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            Log.d("HwCaaSShareManager", "onServiceDisconnected success.");
            if (HwCaasShareManager.this.caassharec) {
                HwCaasShareManager.this.caasshareg = null;
                HwCaasShareManager.this.caassharec = false;
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("HwCaaSShareManager", "onServiceConnected success.");
            try {
                HwCaasShareManager.this.caasshareg = caasshareb.caassharea.caassharea(iBinder);
                HwCaasShareManager.caassharec(HwCaasShareManager.this);
                HwCaasShareManager.caasshared(HwCaasShareManager.this);
                if (HwCaasShareManager.this.caasshareg != null) {
                    HwCaasShareManager.this.caassharec = true;
                }
            } catch (SecurityException unused) {
                HwCaasShareManager.this.caasshareg = null;
                Log.e("HwCaaSShareManager", "bind SecurityException.");
            }
        }
    };
    private boolean caassharei = false;

    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void release() {
        /*
            r3 = this;
            java.lang.String r0 = "release."
            java.lang.String r1 = "HwCaaSShareManager"
            android.util.Log.d(r1, r0)
            java.lang.String r0 = "removeCallback: "
            android.util.Log.i(r1, r0)
            com.huawei.caassharea.caassharea.caasshareb r0 = r3.caasshareg     // Catch: android.os.RemoteException -> L16
            if (r0 == 0) goto L1b
            com.huawei.caassharea.caassharea.caasshareb r0 = r3.caasshareg     // Catch: android.os.RemoteException -> L16
            r0.caassharea()     // Catch: android.os.RemoteException -> L16
            goto L1b
        L16:
            java.lang.String r0 = "removeCallback Exception."
            android.util.Log.e(r1, r0)
        L1b:
            java.lang.String r0 = "bindShareService start."
            android.util.Log.i(r1, r0)
            android.content.Context r0 = r3.caassharea
            if (r0 != 0) goto L27
            java.lang.String r0 = "mContext is null."
            goto L35
        L27:
            android.content.ServiceConnection r2 = r3.caasshareh     // Catch: java.lang.IllegalArgumentException -> L2d java.lang.IllegalStateException -> L30 java.lang.SecurityException -> L33
            r0.unbindService(r2)     // Catch: java.lang.IllegalArgumentException -> L2d java.lang.IllegalStateException -> L30 java.lang.SecurityException -> L33
            goto L38
        L2d:
            java.lang.String r0 = "IllegalArgumentException."
            goto L35
        L30:
            java.lang.String r0 = "IllegalStateException."
            goto L35
        L33:
            java.lang.String r0 = "unbind SecurityException."
        L35:
            android.util.Log.e(r1, r0)
        L38:
            boolean r0 = r3.caassharec
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L44
            r3.caasshareg = r2
            r3.caasshared = r2
            r3.caassharec = r1
        L44:
            com.huawei.caas.messageservice.HwCaasShareCallBack r0 = r3.caassharee
            if (r0 == 0) goto L4d
            r0.releaseSuccess()
            r3.caassharee = r2
        L4d:
            r3.caassharei = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.caas.messageservice.HwCaasShareManager.release():void");
    }

    public void init(Context context, HwCaasShareCallBack hwCaasShareCallBack) {
        String str;
        String str2 = "isBind: false";
        Log.d("HwCaaSShareManager", "init.");
        Log.d("HwCaaSShareManager", "initHandler.");
        if (context == null || hwCaasShareCallBack == null) {
            Log.e("HwCaaSShareManager", "entry parameter is empty or not huawei phone.");
            if (hwCaasShareCallBack != null) {
                hwCaasShareCallBack.initFail(2001);
            }
        } else {
            if (this.caassharei) {
                release();
            }
            Context applicationContext = context.getApplicationContext();
            this.caassharea = applicationContext;
            this.caassharei = true;
            this.caassharee = hwCaasShareCallBack;
            if (this.caasshared == null) {
                this.caasshared = new HwCaasShareHandler(this, applicationContext);
            }
            Log.i("HwCaaSShareManager", "bindShareService start.");
            if (this.caassharea == null) {
                Log.e("HwCaaSShareManager", "mContext is null.");
            } else {
                Intent intent = new Intent();
                intent.setAction("com.huawei.message.service.HwCaasShareService");
                intent.setPackage("com.huawei.meetime");
                try {
                    try {
                        boolean bindService = this.caassharea.bindService(intent, this.caasshareh, 1);
                        if (!bindService) {
                            this.caassharee.initFail(2001);
                        }
                        str2 = "isBind: ";
                        Log.i("HwCaaSShareManager", "isBind: " + bindService);
                    } finally {
                        this.caassharee.initFail(2001);
                        Log.i("HwCaaSShareManager", str2);
                    }
                } catch (IllegalStateException unused) {
                    str = "bindService fail, IllegalStateException.";
                    Log.e("HwCaaSShareManager", str);
                    caassharea.caassharea(this);
                } catch (SecurityException unused2) {
                    str = "bindService fail, SecurityException.";
                    Log.e("HwCaaSShareManager", str);
                    caassharea.caassharea(this);
                }
            }
        }
        caassharea.caassharea(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0076, code lost:
    
        if (r6.caasshareg != null) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final int caassharea(int r7, com.huawei.caas.messageservice.HwShareMsgInfo r8) {
        /*
            r6 = this;
            java.lang.String r0 = "sendShareInfo."
            java.lang.String r1 = "HwCaaSShareManager"
            android.util.Log.d(r1, r0)
            if (r8 == 0) goto Lad
            android.content.Context r0 = r6.caassharea
            if (r0 != 0) goto Lf
            goto Lad
        Lf:
            boolean r0 = r8.shareDataCheckArgs()
            if (r0 != 0) goto L18
            r7 = 2004(0x7d4, float:2.808E-42)
            return r7
        L18:
            r0 = 1
            java.lang.String r2 = "target_scene"
            if (r8 != 0) goto L24
            java.lang.String r3 = "getShareInfoBundle: Error "
            android.util.Log.e(r1, r3)
            r3 = 0
            goto L5f
        L24:
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            java.lang.String r4 = "ShareType"
            int r5 = r8.shareType()
            r3.putInt(r4, r5)
            android.content.Context r4 = r6.caassharea
            java.lang.String r4 = caassharea(r4)
            java.lang.String r5 = "ThirdAppName"
            r3.putString(r5, r4)
            android.content.Context r4 = r6.caassharea
            java.lang.String r4 = r4.getPackageName()
            java.lang.String r5 = "callAppName"
            r3.putString(r5, r4)
            android.content.Context r4 = r6.caassharea
            android.graphics.Bitmap r4 = r6.caasshareb(r4)
            if (r4 == 0) goto L59
            java.lang.String r5 = "ThirdAppIcon"
            byte[] r4 = caassharea(r4)
            r3.putByteArray(r5, r4)
        L59:
            r8.shareDataSerialize(r3)
            r3.putInt(r2, r0)
        L5f:
            if (r3 != 0) goto L64
            java.lang.String r7 = "bundle: Error "
            goto Laf
        L64:
            if (r7 == r0) goto L7f
            r0 = 2
            if (r7 == r0) goto L71
            java.lang.String r7 = "Only small circles and chatting are supported."
            android.util.Log.e(r1, r7)
            r7 = 2005(0x7d5, float:2.81E-42)
            return r7
        L71:
            r3.putInt(r2, r0)
            com.huawei.caassharea.caassharea.caasshareb r7 = r6.caasshareg     // Catch: android.os.RemoteException -> L8b
            if (r7 == 0) goto L90
        L78:
            com.huawei.caassharea.caassharea.caasshareb r7 = r6.caasshareg     // Catch: android.os.RemoteException -> L8b
            int r8 = r8.shareType()     // Catch: android.os.RemoteException -> L8b
            goto L87
        L7f:
            r3.putInt(r2, r0)
            com.huawei.caassharea.caassharea.caasshareb r7 = r6.caasshareg     // Catch: android.os.RemoteException -> L8b
            if (r7 == 0) goto L90
            goto L78
        L87:
            r7.caassharea(r8)     // Catch: android.os.RemoteException -> L8b
            goto L90
        L8b:
            java.lang.String r7 = "queryCallAbility exception."
            android.util.Log.e(r1, r7)
        L90:
            android.content.Intent r7 = new android.content.Intent
            r7.<init>()
            java.lang.String r8 = "bundle"
            r7.putExtra(r8, r3)
            android.content.Context r8 = r6.caassharea
            java.lang.Class<com.huawei.caas.messageservice.JumpActivity> r0 = com.huawei.caas.messageservice.JumpActivity.class
            r7.setClass(r8, r0)
            r8 = 268435456(0x10000000, float:2.524355E-29)
            r7.setFlags(r8)
            android.content.Context r8 = r6.caassharea
            r8.startActivity(r7)
            r7 = 0
            return r7
        Lad:
            java.lang.String r7 = "shareInfo: Error "
        Laf:
            android.util.Log.e(r1, r7)
            r7 = 2003(0x7d3, float:2.807E-42)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.caas.messageservice.HwCaasShareManager.caassharea(int, com.huawei.caas.messageservice.HwShareMsgInfo):int");
    }

    public static HwCaasShareManager getInstance() {
        HwCaasShareManager hwCaasShareManager;
        synchronized (HwCaasShareManager.class) {
            Log.d("HwCaaSShareManager", "getInstance.");
            if (caasshareb == null) {
                caasshareb = new HwCaasShareManager();
            }
            hwCaasShareManager = caasshareb;
        }
        return hwCaasShareManager;
    }

    static /* synthetic */ void caasshared(HwCaasShareManager hwCaasShareManager) {
        Bundle bundle;
        Log.i("HwCaaSShareManager", "sendAppInfoToService.");
        if (hwCaasShareManager.caassharea == null) {
            bundle = null;
        } else {
            bundle = new Bundle();
            bundle.putString("callAppId", caassharec(hwCaasShareManager.caassharea));
            bundle.putString("sdkVersion", "1.0.0.500");
            bundle.putString("callAppName", hwCaasShareManager.caassharea.getPackageName());
        }
        try {
            if (hwCaasShareManager.caasshareg != null) {
                hwCaasShareManager.caasshareg.caasshareb(bundle);
            }
        } catch (RemoteException unused) {
            Log.e("HwCaaSShareManager", "sendAppInfoToService exception.");
        }
    }

    static /* synthetic */ void caassharec(HwCaasShareManager hwCaasShareManager) {
        Log.i("HwCaaSShareManager", "registerCallback: ");
        try {
            if (hwCaasShareManager.caasshareg != null) {
                hwCaasShareManager.caasshareg.caassharea(hwCaasShareManager.caassharef);
            }
        } catch (RemoteException unused) {
            Log.i("HwCaaSShareManager", "Error why why: ");
        }
    }

    private static String caassharec(Context context) {
        StringBuilder sb;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        String str = "";
        try {
            if (packageManager == null) {
                Log.e("HwCaaSShareManager", "getAppId error.");
                return "";
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get("com.huawei.hms.client.appid")) != null) {
                    str = String.valueOf(obj);
                    if (str.startsWith("appid=")) {
                        str = str.substring(6);
                    }
                }
                sb = new StringBuilder("appid ");
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("HwCaaSShareManager", "NameNotFoundException.");
                sb = new StringBuilder("appid ");
            }
            sb.append(str);
            Log.i("HwCaaSShareManager", sb.toString());
            return str;
        } catch (Throwable th) {
            Log.i("HwCaaSShareManager", "appid ");
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001f A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Bitmap caasshareb(android.content.Context r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 0
            android.content.pm.PackageManager r2 = r6.getPackageManager()     // Catch: java.lang.Throwable -> L12 android.content.pm.PackageManager.NameNotFoundException -> L14
            if (r2 == 0) goto L1c
            java.lang.String r6 = r6.getPackageName()     // Catch: java.lang.Throwable -> L12 android.content.pm.PackageManager.NameNotFoundException -> L15
            android.content.pm.ApplicationInfo r6 = r2.getApplicationInfo(r6, r0)     // Catch: java.lang.Throwable -> L12 android.content.pm.PackageManager.NameNotFoundException -> L15
            goto L1d
        L12:
            r6 = move-exception
            goto L52
        L14:
            r2 = r1
        L15:
            java.lang.String r6 = "HwCaaSShareManager"
            java.lang.String r3 = "name not found exception"
            android.util.Log.e(r6, r3)     // Catch: java.lang.Throwable -> L12
        L1c:
            r6 = r1
        L1d:
            if (r2 == 0) goto L49
            if (r6 != 0) goto L22
            goto L49
        L22:
            android.graphics.drawable.Drawable r6 = r2.getApplicationIcon(r6)     // Catch: java.lang.Throwable -> L12
            int r1 = r6.getIntrinsicWidth()     // Catch: java.lang.Throwable -> L12
            int r2 = r6.getIntrinsicHeight()     // Catch: java.lang.Throwable -> L12
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888     // Catch: java.lang.Throwable -> L12
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r1, r2, r3)     // Catch: java.lang.Throwable -> L12
            android.graphics.Canvas r2 = new android.graphics.Canvas     // Catch: java.lang.Throwable -> L12
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L12
            int r3 = r2.getWidth()     // Catch: java.lang.Throwable -> L12
            int r4 = r2.getHeight()     // Catch: java.lang.Throwable -> L12
            r6.setBounds(r0, r0, r3, r4)     // Catch: java.lang.Throwable -> L12
            r6.draw(r2)     // Catch: java.lang.Throwable -> L12
            monitor-exit(r5)
            return r1
        L49:
            java.lang.String r6 = "HwCaaSShareManager"
            java.lang.String r0 = "getAppIcon error"
            android.util.Log.e(r6, r0)     // Catch: java.lang.Throwable -> L12
            monitor-exit(r5)
            return r1
        L52:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.caas.messageservice.HwCaasShareManager.caasshareb(android.content.Context):android.graphics.Bitmap");
    }

    private static byte[] caassharea(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            bitmap.recycle();
            return byteArray;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
                Log.e("HwCaaSShareManager", "bmpToByteArray error");
            }
        }
    }

    private static String caassharea(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return String.valueOf(packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 128)));
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("HwCaaSShareManager", "getAppName error");
        }
        return "";
    }

    private HwCaasShareManager() {
    }
}
