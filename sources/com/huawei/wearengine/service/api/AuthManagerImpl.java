package com.huawei.wearengine.service.api;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.wearengine.AuthManager;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.AuthListener;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.core.common.ClientBinderDied;
import com.huawei.wearengine.core.device.PowerModeChangeManager;
import com.huawei.wearengine.repository.api.AuthInfoRepository;
import defpackage.tnz;
import defpackage.toh;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.trd;
import defpackage.trf;
import defpackage.tri;
import defpackage.trj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AuthManagerImpl extends AuthManager.Stub implements ClientBinderDied {
    private HandlerThread b;
    private tor c;
    private Handler d;
    private AuthInfoRepository e;

    public AuthManagerImpl(AuthInfoRepository authInfoRepository, tor torVar) {
        tos.b("AuthManagerImpl", "AuthManagerImpl Constructor enter!");
        this.e = authInfoRepository;
        this.c = torVar;
        HandlerThread handlerThread = new HandlerThread("WearEngineHandlerThread");
        this.b = handlerThread;
        handlerThread.start();
        if (this.b.getLooper() == null) {
            tos.e("AuthManagerImpl", "mWorkThread getLooper is null!");
        } else {
            this.d = new Handler(this.b.getLooper()) { // from class: com.huawei.wearengine.service.api.AuthManagerImpl.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    AuthManagerImpl.this.feR_(message);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void feR_(Message message) {
        if (message != null && message.what == 101) {
            try {
                feQ_(message);
            } catch (RemoteException unused) {
                tos.e("AuthManagerImpl", "handleMessage RemoteException");
            }
        }
    }

    @Override // com.huawei.wearengine.AuthManager
    public int requestPermission(AuthListener authListener, Permission[] permissionArr) throws RemoteException {
        tos.b("AuthManagerImpl", "requestPermission enter!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        int callingUid = Binder.getCallingUid();
        tos.b("AuthManagerImpl", "requestPermission uid:" + callingUid);
        tos.a("AuthManagerImpl", "requestPermission pid:" + Binder.getCallingPid());
        String c = tri.c(callingUid, a2, this.c.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            a(authListener, permissionArr, "requestPermission", c);
            List<String> a3 = trf.a(c);
            if (trf.c(a(permissionArr), a3)) {
                authListener.onOk(trf.c(a3));
                return 0;
            }
            HashSet hashSet = new HashSet(permissionArr.length);
            for (Permission permission : permissionArr) {
                hashSet.add(permission.getName());
            }
            String[] strArr = (String[]) hashSet.toArray(new String[0]);
            tos.b("AuthManagerImpl", "requestPermission permissionTypes：" + Arrays.toString(strArr));
            tos.a("AuthManagerImpl", "putCallbackToMap PackageName:" + c);
            tnz.b().b(c, authListener);
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.obj = c;
            Bundle bundle = new Bundle();
            bundle.putStringArray("permissionTypes", strArr);
            obtain.setData(bundle);
            tos.b("AuthManagerImpl", "requestPermission sendMessage");
            this.d.sendMessage(obtain);
            wearEngineBiOperate.biApiCalling(a2, c, "requestPermission", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "requestPermission", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    private void a(AuthListener authListener, Permission[] permissionArr, String str, String str2) {
        if (authListener == null || permissionArr == null || permissionArr.length == 0) {
            throw new IllegalStateException(String.valueOf(5));
        }
        if (permissionArr.length > 10) {
            throw new IllegalStateException(String.valueOf(5));
        }
        PowerModeChangeManager.a().b(true);
        e(str, str2);
    }

    private String[] a(Permission[] permissionArr) {
        String[] strArr = new String[permissionArr.length];
        for (int i = 0; i < permissionArr.length; i++) {
            Permission permission = permissionArr[i];
            if (permission != null) {
                strArr[i] = permission.getName();
            } else {
                strArr[i] = "";
                tos.d("AuthManagerImpl", "permissions is null");
            }
        }
        return strArr;
    }

    @Override // com.huawei.wearengine.AuthManager
    public boolean checkPermission(Permission permission) throws RemoteException {
        tos.b("AuthManagerImpl", "checkPermission enter!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        int callingUid = Binder.getCallingUid();
        String c = tri.c(callingUid, a2, this.c.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            if (permission == null) {
                throw new IllegalStateException(String.valueOf(5));
            }
            PowerModeChangeManager.a().b(true);
            if (toh.c(callingUid, c)) {
                tos.a("AuthManagerImpl", "checkPermission isSuperCaller");
                return true;
            }
            List<String> d = d();
            wearEngineBiOperate.biApiCalling(a2, c, "checkPermission", String.valueOf(0));
            return d.contains(permission.getName());
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "checkPermission", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.AuthManager
    public boolean[] checkPermissions(Permission[] permissionArr) throws RemoteException {
        tos.b("AuthManagerImpl", "checkPermission enter!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        int callingUid = Binder.getCallingUid();
        String c = tri.c(callingUid, a2, this.c.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        if (permissionArr != null) {
            try {
                if (permissionArr.length != 0 && permissionArr.length <= 10) {
                    PowerModeChangeManager.a().b(true);
                    int length = permissionArr.length;
                    boolean[] zArr = new boolean[length];
                    if (toh.c(callingUid, c)) {
                        tos.a("AuthManagerImpl", "checkPermission isSuperCaller");
                        Arrays.fill(zArr, true);
                        return zArr;
                    }
                    List<String> d = d();
                    wearEngineBiOperate.biApiCalling(a2, c, "checkPermissions", String.valueOf(0));
                    for (int i = 0; i < length; i++) {
                        zArr[i] = d.contains(permissionArr[i].getName());
                    }
                    return zArr;
                }
            } catch (IllegalStateException e) {
                wearEngineBiOperate.biApiCalling(a2, c, "checkPermissions", String.valueOf(trj.b(e)));
                throw e;
            }
        }
        throw new IllegalStateException(String.valueOf(5));
    }

    @Override // com.huawei.wearengine.AuthManager
    public String preStartAuth(AuthListener authListener) throws RemoteException {
        tos.a("AuthManagerImpl", "preStartAuth enter!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        int callingUid = Binder.getCallingUid();
        tos.a("AuthManagerImpl", "preStartAuth uid:" + callingUid);
        tos.a("AuthManagerImpl", "preStartAuth pid:" + Binder.getCallingPid());
        String c = tri.c(callingUid, a2, this.c.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            if (c == null) {
                throw new IllegalStateException(String.valueOf(12));
            }
            if (authListener == null) {
                throw new IllegalStateException(String.valueOf(5));
            }
            PowerModeChangeManager.a().b(true);
            e("preStartAuth", c);
            tos.a("AuthManagerImpl", "putCallbackToMap PackageName:" + c);
            if (!trf.c(c, trf.c(tri.j(tot.a(), c)))) {
                throw new IllegalStateException(String.valueOf(8));
            }
            tnz.b().b(c, authListener);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("target_package_name", "com.huawei.health");
                jSONObject.put("target_activity_name", "com.huawei.wearengine.ui.JumpActivity");
                wearEngineBiOperate.biApiCalling(a2, c, "preStartAuth", String.valueOf(0));
                return jSONObject.toString();
            } catch (JSONException unused) {
                tos.e("AuthManagerImpl", "preStartAuth JSONException");
                throw new IllegalStateException(String.valueOf(12));
            }
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "preStartAuth", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    private List<String> d() {
        ArrayList arrayList = new ArrayList();
        Context a2 = tot.a();
        String a3 = tri.a(a2);
        if (TextUtils.isEmpty(a3)) {
            tos.e("AuthManagerImpl", "usr_id is null, not login in Huawei Health!");
            throw new IllegalStateException(String.valueOf(3));
        }
        List<AuthInfo> auth = this.e.getAuth(Binder.getCallingUid(), a3, tri.j(a2, tri.c(Binder.getCallingUid(), a2, this.c.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())))));
        if (auth != null && !auth.isEmpty()) {
            for (AuthInfo authInfo : auth) {
                if (authInfo.getOpenStatus() != 0) {
                    arrayList.add(authInfo.getPermission());
                }
            }
        }
        return arrayList;
    }

    private void e(String str, String str2) {
        tos.a("AuthManagerImpl", "checkAllAuthorization apiName is " + str + " ,pkgName is " + str2);
        Context a2 = tot.a();
        if (!trd.e(tri.a(a2))) {
            tos.e("AuthManagerImpl", "requestPermission check health is not login in Huawei Health!");
            throw new IllegalStateException(String.valueOf(3));
        }
        if (tri.d(a2)) {
            return;
        }
        tos.e("AuthManagerImpl", "requestPermission check user not authorized in Huawei Health!");
        throw new IllegalStateException(String.valueOf(7));
    }

    private void feQ_(Message message) throws RemoteException {
        tos.a("AuthManagerImpl", "Start handleAuthMessage");
        Bundle data = message.getData();
        if (data == null) {
            tos.d("AuthManagerImpl", "handleAuthMessage bundle == null!");
            throw new IllegalStateException(String.valueOf(12));
        }
        if (!(message.obj instanceof String)) {
            tos.d("AuthManagerImpl", "handleAuthMessage message.obj is not string");
            return;
        }
        try {
            Intent ffc_ = trf.ffc_((String) message.obj, data.getStringArray("permissionTypes"));
            if (ffc_ == null) {
                tos.d("AuthManagerImpl", " handleAuthMessage intent is null");
            } else {
                tot.a().startActivity(ffc_);
            }
        } catch (ActivityNotFoundException unused) {
            tos.e("AuthManagerImpl", " handleAuthMessage ActivityNotFoundException");
        } catch (ArrayIndexOutOfBoundsException unused2) {
            tos.e("AuthManagerImpl", " bundle getStringArray error");
        }
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        tos.d("AuthManagerImpl", "handleClientBinderDied clientPkgName is: " + str);
        tnz.b().b(str);
        this.e.deleteAuthFromCache(str);
    }
}
