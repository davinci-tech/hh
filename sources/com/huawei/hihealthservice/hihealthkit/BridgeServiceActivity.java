package com.huawei.hihealthservice.hihealthkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import defpackage.iip;
import defpackage.iqs;
import defpackage.ivw;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes8.dex */
public class BridgeServiceActivity extends Activity {
    private static final String e = BaseApplication.getAppPackage();

    /* renamed from: a, reason: collision with root package name */
    private HandlerThread f4150a;
    private Handler d;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        ReleaseLogUtil.e("HiH_BridgeServiceActivity", "BridgeServiceActivity onCreate");
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            b(7, "BridgeServiceActivity start invalid intent");
        } else {
            a();
            bBv_(intent);
        }
    }

    private void a() {
        HandlerThread handlerThread = new HandlerThread("BridgeServiceActivity");
        this.f4150a = handlerThread;
        handlerThread.start();
        if (this.f4150a.getLooper() == null) {
            ReleaseLogUtil.d("HiH_BridgeServiceActivity", "initData mWorkThread getLooper is null");
        } else {
            this.d = new Handler(this.f4150a.getLooper()) { // from class: com.huawei.hihealthservice.hihealthkit.BridgeServiceActivity.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    if (message.what == 101) {
                        BridgeServiceActivity.this.bBu_(message);
                    } else {
                        ReleaseLogUtil.d("HiH_BridgeServiceActivity", "Unmatched msg.what = ", Integer.valueOf(message.what));
                    }
                }
            };
        }
    }

    private void bBv_(Intent intent) {
        String str;
        try {
            str = intent.getStringExtra("request_json_data");
        } catch (BadParcelableException e2) {
            ReleaseLogUtil.c("HiH_BridgeServiceActivity", "parseData getStringExtra catch BadParcelableException", LogAnonymous.b((Throwable) e2));
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            b(2, "parseData requestDataJson is empty");
            return;
        }
        String b = iqs.b(str);
        if (TextUtils.isEmpty(b)) {
            b(2, "parseData requestType data is empty");
        } else if (b.equals("request_auth")) {
            a(str);
        } else {
            b(4, "parseData no have requestType");
        }
    }

    private void a(String str) {
        String a2 = iqs.a(str);
        int[] c = iqs.c(str);
        int[] d = iqs.d(str);
        if (TextUtils.isEmpty(a2)) {
            b(2, "startAuthActivity requestPackageName is empty");
            return;
        }
        if (c.length == 0 && d.length == 0) {
            b(2, "startAuthActivity scopeReadTypes length is 0 or scopeWriteTypes length is 0");
            return;
        }
        if (!e(a2)) {
            b(2, "startAuthActivity start requestPackageName failed");
            return;
        }
        int e2 = iqs.e(str);
        Message obtain = Message.obtain();
        obtain.what = 101;
        obtain.obj = a2;
        obtain.arg1 = e2;
        Bundle bundle = new Bundle();
        bundle.putIntArray("writeTypes", d);
        bundle.putIntArray("readTypes", c);
        obtain.setData(bundle);
        bBw_(obtain);
    }

    private void bBw_(Message message) {
        Handler handler = this.d;
        if (handler == null) {
            b(4, "BridgeServiceActivity sendMessageToHandler is null");
        } else {
            handler.sendMessage(message);
        }
    }

    private boolean e(String str) {
        String callingPackage = getCallingPackage();
        if (TextUtils.isEmpty(callingPackage)) {
            ReleaseLogUtil.d("HiH_BridgeServiceActivity", "checkPackageName callingPackage is invalid");
            return false;
        }
        if (e.equals(callingPackage)) {
            ReleaseLogUtil.d("HiH_BridgeServiceActivity", "checkPackageName callingPackage is health");
            return true;
        }
        if (callingPackage.equals(str)) {
            return true;
        }
        ReleaseLogUtil.d("HiH_BridgeServiceActivity", "checkPackageName packageName is different, callingPackage:" + callingPackage + ", packageName from intent:" + str);
        return false;
    }

    private void b(int i, String str) {
        ReleaseLogUtil.d("HiH_BridgeServiceActivity", "finishErrorReturn:" + str + ", resultCode:" + i);
        setResult(i);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bBu_(Message message) {
        LogUtil.a("BridgeServiceActivity", "start handleAuthMessage");
        if (message.getData() == null) {
            b(2, "handleAuthMessage bundle is null");
            return;
        }
        if (!(message.obj instanceof String)) {
            b(2, "handleAuthMessage message.obj is not string");
            return;
        }
        Intent bBt_ = bBt_();
        bBs_(bBt_, message);
        startActivity(bBt_);
        setResult(0);
        finish();
    }

    private Intent bBt_() {
        Intent intent = new Intent();
        String str = e;
        intent.setClassName(str, "com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity");
        intent.setPackage(str);
        intent.setFlags(268435456);
        intent.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        intent.addFlags(524288);
        intent.addFlags(8388608);
        return intent;
    }

    private void bBs_(Intent intent, Message message) {
        if (message.obj instanceof String) {
            try {
                Bundle data = message.getData();
                String str = (String) message.obj;
                int[] intArray = data.getIntArray("writeTypes");
                int[] intArray2 = data.getIntArray("readTypes");
                intent.putExtra("third_party_package_name", str);
                intent.putExtra("writeTypes", intArray);
                intent.putExtra("readTypes", intArray2);
                intent.putExtra("uidTypes", message.arg1);
                intent.putExtra(MapKeyNames.APP_INFO, b(getApplicationContext(), str));
            } catch (ArrayIndexOutOfBoundsException e2) {
                ReleaseLogUtil.c("HiH_BridgeServiceActivity", "addExtraAuthIntentData ArrayIndexOutOfBoundsException", LogAnonymous.b((Throwable) e2));
            }
        }
    }

    private HiAppInfo b(Context context, String str) {
        HiAppInfo b = iip.b().b(str);
        if (b != null) {
            return b;
        }
        HiAppInfo c = ivw.c(context, str);
        if (c == null) {
            return c;
        }
        iip.b().e(c, 0);
        return iip.b().b(str);
    }

    private void b() {
        HandlerThread handlerThread = this.f4150a;
        if (handlerThread == null || handlerThread.getLooper() == null) {
            return;
        }
        ReleaseLogUtil.e("HiH_BridgeServiceActivity", "enter quitHandlerThread.");
        this.f4150a.getLooper().quit();
        this.f4150a = null;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        b();
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.d = null;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
