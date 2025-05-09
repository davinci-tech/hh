package com.huawei.wearengine.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.wearengine.auth.AuthListenerManagerProxy;
import defpackage.tos;
import defpackage.tot;
import defpackage.tqw;
import defpackage.trf;
import defpackage.tri;
import java.util.List;

/* loaded from: classes9.dex */
public class JumpActivity extends Activity {
    private Handler c;
    private HandlerThread d;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        tos.a("JumpActivity", "JumpActivity onCreate");
        super.onCreate(bundle);
        tot.a(this);
        d();
        Intent intent = getIntent();
        if (intent == null) {
            c(5, "JumpActivity start invalid intent");
        } else {
            feX_(intent);
        }
    }

    private void d() {
        HandlerThread handlerThread = new HandlerThread("JumpActivityHandlerThread");
        this.d = handlerThread;
        handlerThread.start();
        if (this.d.getLooper() == null) {
            tos.d("JumpActivity", "initHandler mWorkThread getLooper is null!");
            this.d.quit();
            c(12, "initHandler looper is null");
            return;
        }
        this.c = new Handler(this.d.getLooper()) { // from class: com.huawei.wearengine.ui.JumpActivity.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 1001) {
                    JumpActivity.this.feV_(message);
                } else {
                    if (i != 1002) {
                        return;
                    }
                    JumpActivity.this.feW_(message);
                }
            }
        };
    }

    private void feX_(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("start_request_json");
            if (TextUtils.isEmpty(stringExtra)) {
                c(5, "JumpActivity start requestDataJson is empty");
                return;
            }
            String c = tqw.c(stringExtra);
            if (TextUtils.isEmpty(c)) {
                c(5, "JumpActivity start requestType data is empty");
            } else if (c.equals("request_auth")) {
                e(stringExtra);
            } else {
                c(5, "JumpActivity start no have requestType");
            }
        } catch (BadParcelableException unused) {
            tos.e("JumpActivity", "getStringExtra catch a BadParcelableException");
            c(12, "resolveData exception");
        } catch (RuntimeException unused2) {
            tos.e("JumpActivity", "getStringExtra catch a RuntimeException");
            c(12, "resolveData exception");
        }
    }

    private void e(String str) {
        Message obtain = Message.obtain();
        obtain.what = 1002;
        obtain.obj = str;
        feY_(obtain);
    }

    private void feY_(Message message) {
        Handler handler = this.c;
        if (handler == null) {
            tos.d("JumpActivity", "JumpActivity sendMessageToHandler is null");
        } else {
            handler.sendMessage(message);
        }
    }

    private boolean d(String str) {
        String callingPackage = getCallingPackage();
        if (TextUtils.isEmpty(callingPackage)) {
            tos.d("JumpActivity", "checkPackageName callingPackage is invalid");
            return false;
        }
        if ("com.huawei.health".equals(callingPackage)) {
            tos.d("JumpActivity", "callingPackage is health");
            return true;
        }
        if (callingPackage.equals(str)) {
            return true;
        }
        tos.d("JumpActivity", "packageName is different, callingPackage:" + callingPackage + ", packageName from intent:" + str);
        return false;
    }

    private void c(int i, String str) {
        tos.d("JumpActivity", "errorReturn:" + str + ", resultCode:" + i);
        setResult(i + 1000);
        finish();
    }

    private void c(String str, String[] strArr) {
        tos.a("JumpActivity", "startAuthActivity");
        Intent ffc_ = trf.ffc_(str, strArr);
        if (ffc_ == null) {
            c(12, "startAuthActivity intent is null");
            return;
        }
        startActivity(ffc_);
        setResult(1000);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void feW_(Message message) {
        tos.a("JumpActivity", "Start handleScopeAuthMessage" + getCallingPackage());
        if (!(message.obj instanceof String)) {
            tos.d("JumpActivity", "handleScopeAuthMessage message.obj is not string");
            return;
        }
        String str = (String) message.obj;
        String d = tqw.d(str);
        String[] a2 = tqw.a(str);
        if (TextUtils.isEmpty(d) || a2.length == 0) {
            c(5, "JumpActivity start requestPackageName is empty or permissionTypes is null or permissionTypes length is 0");
            return;
        }
        if (!d(d)) {
            c(5, "handleScopeAuthMessage JumpActivity start requestPackageName failed");
            return;
        }
        List<String> a3 = trf.a(d);
        if (trf.c(a2, a3)) {
            AuthListenerManagerProxy authListenerManagerProxy = new AuthListenerManagerProxy(getApplicationContext());
            try {
                tos.a("JumpActivity", "handleScopeAuthMessage authListener.onOk");
                authListenerManagerProxy.authListenerOnOk(d, trf.c(a3));
                c(0, " authListener.onOk");
                return;
            } catch (RemoteException unused) {
                tos.e("JumpActivity", "handleScopeAuthMessage RemoteException");
                c(12, "handleScopeAuthMessage RemoteException");
                return;
            }
        }
        if (!trf.c(getCallingPackage(), trf.c(tri.j(tot.a(), getCallingPackage())))) {
            c(12, "handleScopeAuthMessage invalid external invoking");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1001;
        obtain.obj = d;
        Bundle bundle = new Bundle();
        bundle.putStringArray("permissions", a2);
        obtain.setData(bundle);
        feY_(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void feV_(Message message) {
        tos.a("JumpActivity", "Start handleAuthMessage");
        Bundle data = message.getData();
        if (data == null) {
            c(12, "handleAuthMessage bundle is null");
        } else {
            if (!(message.obj instanceof String)) {
                c(12, "handleAuthMessage message.obj is not string");
                return;
            }
            try {
                c((String) message.obj, data.getStringArray("permissions"));
            } catch (ArrayIndexOutOfBoundsException unused) {
                c(12, "handleAuthMessage getStringArray ArrayIndexOutOfBoundsException");
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.c = null;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
