package com.tencent.connect.common;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.tencent.open.b.e;
import com.tencent.open.b.h;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.m;
import com.tencent.tauth.Tencent;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class AssistActivity extends Activity {
    public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
    public static final String KEY_EXTRA_PENDING_INTENT = "key_extra_pending_intent";
    public static final String KEY_REQUEST_ORIENTATION = "key_request_orientation";
    private String d;
    private QQStayReceiver e;
    private boolean f;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f11297a = false;
    protected Handler b = new Handler() { // from class: com.tencent.connect.common.AssistActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0 && !AssistActivity.this.isFinishing()) {
                SLog.w("openSDK_LOG.AssistActivity", "-->finish by timeout");
                AssistActivity.this.finish();
            }
        }
    };

    public static Intent getAssistActivityIntent(Context context) {
        return new Intent(context, (Class<?>) AssistActivity.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0167  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onCreate(android.os.Bundle r15) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.common.AssistActivity.onCreate(android.os.Bundle):void");
    }

    private void a(Intent intent, boolean z) {
        if (intent == null) {
            SLog.d("openSDK_LOG.AssistActivity", "reportStartActivitySuccess, but intent is null.");
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra(Constants.KEY_PASS_REPORT_VIA_PARAM);
        if (bundleExtra != null) {
            m.a(bundleExtra, z ? "0" : "1");
            h.a().a(bundleExtra, this.d, intent.getBooleanExtra(Constants.KEY_PASS_REPORT_VIA_TIMELY, false));
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onStart");
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onResume");
        super.onResume();
        Intent intent = getIntent();
        if (intent.getBooleanExtra("is_login", false)) {
            return;
        }
        if (!intent.getBooleanExtra("is_qq_mobile_share", false) && this.c && !isFinishing()) {
            finish();
        }
        if (this.f11297a) {
            this.b.sendMessage(this.b.obtainMessage(0));
        } else {
            this.f11297a = true;
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onPause");
        this.b.removeMessages(0);
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onStop");
        super.onStop();
        if (Tencent.disableResetOrientation) {
            return;
        }
        try {
            int intExtra = getIntent().getIntExtra(KEY_REQUEST_ORIENTATION, -1);
            SLog.i("openSDK_LOG.AssistActivity", "getRequestedOrientation= " + intExtra);
            if (intExtra != -1) {
                setRequestedOrientation(intExtra);
            }
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.AssistActivity", "reset requestedOrientation catch exception", th);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onDestroy");
        super.onDestroy();
        QQStayReceiver qQStayReceiver = this.e;
        if (qQStayReceiver != null) {
            unregisterReceiver(qQStayReceiver);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        SLog.i("openSDK_LOG.AssistActivity", "--onNewIntent");
        super.onNewIntent(intent);
        int intExtra = intent.getIntExtra(Constants.KEY_REQUEST_CODE, -1);
        SLog.i("openSDK_LOG.AssistActivity", "--onNewIntent callbackRequestCode= " + intExtra);
        if (intExtra == 10108) {
            intent.putExtra(Constants.KEY_ACTION, "action_request_avatar");
            if (intent.getBooleanExtra(Constants.KEY_STAY, false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        if (intExtra == 10109) {
            intent.putExtra(Constants.KEY_ACTION, "action_request_set_emotion");
            if (intent.getBooleanExtra(Constants.KEY_STAY, false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        if (intExtra == 10110) {
            intent.putExtra(Constants.KEY_ACTION, "action_request_dynamic_avatar");
            if (intent.getBooleanExtra(Constants.KEY_STAY, false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        if (intExtra == 10111) {
            intent.putExtra(Constants.KEY_ACTION, "joinGroup");
            if (intent.getBooleanExtra(Constants.KEY_STAY, false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        if (intExtra == 10112) {
            intent.putExtra(Constants.KEY_ACTION, "bindGroup");
            if (intent.getBooleanExtra(Constants.KEY_STAY, false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        if (intExtra == 10113) {
            intent.putExtra(Constants.KEY_ACTION, intent.getStringExtra("action"));
            setResult(-1, intent);
            if (isFinishing()) {
                return;
            }
            SLog.i("openSDK_LOG.AssistActivity", "--onNewIntent--activity not finished, finish now");
            finish();
            return;
        }
        if (intExtra == 10114) {
            intent.putExtra(Constants.KEY_ACTION, intent.getStringExtra("action"));
            setResult(-1, intent);
            if (isFinishing()) {
                return;
            }
            SLog.i("openSDK_LOG.AssistActivity", "--onNewIntent--activity not finished, finish now");
            finish();
            return;
        }
        intent.putExtra(Constants.KEY_ACTION, "action_share");
        setResult(-1, intent);
        if (isFinishing()) {
            return;
        }
        SLog.i("openSDK_LOG.AssistActivity", "--onNewIntent--activity not finished, finish now");
        finish();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        SLog.i("openSDK_LOG.AssistActivity", "--onSaveInstanceState--");
        bundle.putBoolean("RESTART_FLAG", true);
        bundle.putBoolean("RESUME_FLAG", this.f11297a);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        StringBuilder sb = new StringBuilder("--onActivityResult--requestCode: ");
        sb.append(i);
        sb.append(" | resultCode: ");
        sb.append(i2);
        sb.append("data = null ? ");
        sb.append(intent == null);
        SLog.i("openSDK_LOG.AssistActivity", sb.toString());
        super.onActivityResult(i, i2, intent);
        if (i == 0) {
            return;
        }
        if (intent != null) {
            intent.putExtra(Constants.KEY_ACTION, "action_login");
        }
        setResultData(i, intent);
        if (!this.f) {
            SLog.i("openSDK_LOG.AssistActivity", "onActivityResult finish immediate");
            finish();
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.tencent.connect.common.AssistActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    SLog.i("openSDK_LOG.AssistActivity", "onActivityResult finish delay");
                    AssistActivity.this.finish();
                }
            }, 200L);
        }
    }

    public void setResultData(int i, Intent intent) {
        if (intent == null) {
            SLog.w("openSDK_LOG.AssistActivity", "--setResultData--intent is null, setResult ACTIVITY_CANCEL");
            setResult(0);
            if (i == 11101) {
                e.a().a("", this.d, "2", "1", "7", "2");
                return;
            }
            return;
        }
        try {
            String stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
            SLog.d("openSDK_LOG.AssistActivity", "--setResultDataForLogin-- ");
            if (!TextUtils.isEmpty(stringExtra)) {
                JSONObject jSONObject = new JSONObject(stringExtra);
                String optString = jSONObject.optString("openid");
                String optString2 = jSONObject.optString(Constants.PARAM_ACCESS_TOKEN);
                String optString3 = jSONObject.optString("proxy_code");
                long optLong = jSONObject.optLong("proxy_expires_in");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                    SLog.i("openSDK_LOG.AssistActivity", "--setResultData--openid and token not empty, setResult ACTIVITY_OK");
                    setResult(-1, intent);
                    e.a().a(optString, this.d, "2", "1", "7", "0");
                } else if (!TextUtils.isEmpty(optString3) && optLong != 0) {
                    SLog.i("openSDK_LOG.AssistActivity", "--setResultData--proxy_code and proxy_expires_in are valid");
                    setResult(-1, intent);
                } else {
                    SLog.w("openSDK_LOG.AssistActivity", "--setResultData--openid or token is empty, setResult ACTIVITY_CANCEL");
                    setResult(0, intent);
                    e.a().a("", this.d, "2", "1", "7", "1");
                }
            } else {
                SLog.w("openSDK_LOG.AssistActivity", "--setResultData--response is empty, setResult ACTIVITY_OK");
                setResult(-1, intent);
            }
        } catch (Exception e) {
            SLog.e("openSDK_LOG.AssistActivity", "--setResultData--parse response failed");
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.os.Bundle r13) {
        /*
            r12 = this;
            java.lang.String r0 = "viaShareType"
            java.lang.String r8 = r13.getString(r0)
            java.lang.String r0 = "callbackAction"
            java.lang.String r0 = r13.getString(r0)
            java.lang.String r1 = "url"
            java.lang.String r1 = r13.getString(r1)
            java.lang.String r2 = "openId"
            java.lang.String r2 = r13.getString(r2)
            java.lang.String r3 = "appId"
            java.lang.String r3 = r13.getString(r3)
            java.lang.String r13 = "shareToQQ"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L2d
            java.lang.String r13 = "ANDROIDQQ.SHARETOQQ.XX"
            java.lang.String r4 = "10"
            goto L39
        L2d:
            java.lang.String r13 = "shareToQzone"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L3c
            java.lang.String r13 = "ANDROIDQQ.SHARETOQZ.XX"
            java.lang.String r4 = "11"
        L39:
            r5 = r4
            r4 = r13
            goto L40
        L3c:
            java.lang.String r13 = ""
            r4 = r13
            r5 = r4
        L40:
            boolean r13 = com.tencent.open.utils.m.a(r12, r1)
            if (r13 != 0) goto L72
            com.tencent.connect.common.UIListenerManager r13 = com.tencent.connect.common.UIListenerManager.getInstance()
            com.tencent.tauth.IUiListener r13 = r13.getListnerWithAction(r0)
            if (r13 == 0) goto L5d
            com.tencent.tauth.UiError r0 = new com.tencent.tauth.UiError
            java.lang.String r1 = "打开浏览器失败!"
            r6 = 0
            r7 = -6
            r0.<init>(r7, r1, r6)
            r13.onError(r0)
        L5d:
            com.tencent.open.b.e r1 = com.tencent.open.b.e.a()
            java.lang.String r6 = "3"
            java.lang.String r7 = "1"
            java.lang.String r9 = "0"
            java.lang.String r10 = "2"
            java.lang.String r11 = "0"
            r1.a(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r12.finish()
            goto L83
        L72:
            com.tencent.open.b.e r1 = com.tencent.open.b.e.a()
            java.lang.String r6 = "3"
            java.lang.String r7 = "0"
            java.lang.String r9 = "0"
            java.lang.String r10 = "2"
            java.lang.String r11 = "0"
            r1.a(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
        L83:
            android.content.Intent r13 = r12.getIntent()
            java.lang.String r0 = "shareH5"
            r13.removeExtra(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.common.AssistActivity.a(android.os.Bundle):void");
    }

    class QQStayReceiver extends BroadcastReceiver {
        private QQStayReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intent intent2 = new Intent();
            intent2.putExtra(Constants.KEY_ACTION, "action_share");
            try {
                Uri uri = (Uri) intent.getParcelableExtra("uriData");
                String uri2 = uri.toString();
                for (String str : uri2.substring(uri2.indexOf(uri2.contains("#") ? "#" : "?") + 1).split("&")) {
                    String[] split = str.split("=");
                    intent2.putExtra(split[0], split[1]);
                }
                intent2.setData(uri);
            } catch (Exception e) {
                SLog.i("openSDK_LOG.AssistActivity", "QQStayReceiver parse uri error : " + e.getMessage());
                intent2.putExtra("result", VastAttribute.ERROR);
                intent2.putExtra(TrackConstants$Opers.RESPONSE, "parse error.");
            }
            AssistActivity.this.setResult(-1, intent2);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
