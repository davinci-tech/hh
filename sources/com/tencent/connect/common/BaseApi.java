package com.tencent.connect.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.f;
import com.tencent.open.utils.g;
import com.tencent.open.utils.k;
import com.tencent.open.utils.m;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public abstract class BaseApi {
    public static String businessId = null;
    public static String installChannel = null;
    public static boolean isOEM = false;
    public static String registerChannel;
    protected c b;
    public QQToken c;

    public void releaseResource() {
    }

    public BaseApi(c cVar, QQToken qQToken) {
        this.b = cVar;
        this.c = qQToken;
    }

    public BaseApi(QQToken qQToken) {
        this(null, qQToken);
    }

    protected Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("format", "json");
        bundle.putString("status_os", Build.VERSION.RELEASE);
        bundle.putString("status_machine", f.a().c(g.a()));
        bundle.putString("status_version", Build.VERSION.SDK);
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", VideoPlayFlag.PLAY_IN_ALL);
        QQToken qQToken = this.c;
        if (qQToken != null && qQToken.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.c.getAccessToken());
            bundle.putString("oauth_consumer_key", this.c.getAppId());
            bundle.putString("openid", this.c.getOpenId());
        }
        SharedPreferences sharedPreferences = g.a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-android-" + registerChannel + com.huawei.openalliance.ad.constant.Constants.LINK + businessId);
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        }
        return bundle;
    }

    public String a(String str) {
        Bundle a2 = a();
        StringBuilder sb = new StringBuilder("https://openmobile.qq.com/oauth2.0/m_jump_by_version?");
        if (!TextUtils.isEmpty(str)) {
            a2.putString("need_version", str);
        }
        sb.append(HttpUtils.encodeUrl(a2));
        return sb.toString();
    }

    protected void a(StringBuilder sb, Activity activity) {
        if (sb.indexOf("?") < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        sb.append(Constants.JumpUrlConstants.URL_KEY_SRC);
        sb.append("=");
        sb.append("app");
        String appId = this.c.getAppId();
        String openId = this.c.getOpenId();
        if (!TextUtils.isEmpty(appId)) {
            a(sb, "app_id", appId);
        }
        if (!TextUtils.isEmpty(openId)) {
            a(sb, Constants.JumpUrlConstants.URL_KEY_OPENID, m.k(openId));
        }
        String a2 = m.a(activity);
        if (!TextUtils.isEmpty(a2)) {
            if (a2.length() > 20) {
                a2 = a2.substring(0, 20) + "...";
            }
            a(sb, "app_name", m.k(a2));
        }
        a(sb, "sdk_version", m.k(Constants.SDK_VERSION));
    }

    protected void a(StringBuilder sb, String str, String str2) {
        sb.append("&");
        sb.append(str);
        sb.append("=");
        sb.append(m.f(str2));
    }

    public Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putString("appid", this.c.getAppId());
        if (this.c.isSessionValid()) {
            bundle.putString(Constants.PARAM_KEY_STR, this.c.getAccessToken());
            bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
        }
        String openId = this.c.getOpenId();
        if (openId != null) {
            bundle.putString("hopenid", openId);
        }
        bundle.putString("platform", "androidqz");
        SharedPreferences sharedPreferences = g.a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-android-" + registerChannel + com.huawei.openalliance.ad.constant.Constants.LINK + businessId);
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", VideoPlayFlag.PLAY_IN_ALL);
        return bundle;
    }

    private Intent a(Activity activity, Intent intent, Map<String, Object> map, int i) {
        Intent intent2 = new Intent(activity.getApplicationContext(), (Class<?>) AssistActivity.class);
        intent2.putExtra("is_login", true);
        a(activity, intent2, intent, i);
        if (map == null) {
            return intent2;
        }
        try {
            if (map.containsKey(Constants.KEY_RESTORE_LANDSCAPE)) {
                intent2.putExtra(Constants.KEY_RESTORE_LANDSCAPE, ((Boolean) map.get(Constants.KEY_RESTORE_LANDSCAPE)).booleanValue());
            }
        } catch (Exception e) {
            SLog.e("openSDK_LOG.BaseApi", "Exception", e);
        }
        return intent2;
    }

    public void a(Activity activity, int i, Intent intent, boolean z) {
        Intent intent2 = new Intent(activity.getApplicationContext(), (Class<?>) AssistActivity.class);
        if (z) {
            intent2.putExtra("is_qq_mobile_share", true);
        }
        a(activity, intent2, intent, i);
        try {
            activity.startActivityForResult(intent2, i);
        } catch (Exception e) {
            SLog.e("openSDK_LOG.BaseApi", "startAssistActivity exception", e);
        }
    }

    protected void a(Activity activity, Intent intent, int i) {
        a(activity, intent, i, (Map<String, Object>) null);
    }

    protected void a(Activity activity, Intent intent, int i, Map<String, Object> map) {
        intent.putExtra(Constants.KEY_REQUEST_CODE, i);
        try {
            activity.startActivityForResult(a(activity, intent, map, i), i);
        } catch (Exception e) {
            SLog.e("openSDK_LOG.BaseApi", "startAssitActivity exception", e);
        }
    }

    protected void a(Fragment fragment, Intent intent, int i, Map<String, Object> map) {
        intent.putExtra(Constants.KEY_REQUEST_CODE, i);
        try {
            fragment.startActivityForResult(a(fragment.getActivity(), intent, map, i), i);
        } catch (Exception e) {
            SLog.e("openSDK_LOG.BaseApi", "startAssitActivity exception", e);
        }
    }

    private void a(Activity activity, Intent intent, Intent intent2, int i) {
        if (intent == null || intent2 == null) {
            return;
        }
        try {
            if (intent2.getClipData() == null) {
                intent2.setClipData(ClipData.newPlainText(null, null));
            }
            intent2.setFlags(intent2.getFlags() & (-196));
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.BaseApi", "setActivityIntent security catch exception", th);
        }
        intent.putExtra(AssistActivity.KEY_REQUEST_ORIENTATION, activity.getRequestedOrientation());
        intent.putExtra(AssistActivity.EXTRA_INTENT, intent2);
        try {
            SLog.i("openSDK_LOG.BaseApi", "setActivityIntent requestCode: " + i);
            intent.putExtra(AssistActivity.KEY_EXTRA_PENDING_INTENT, PendingIntent.getActivity(activity, i, intent2, 1140850688));
        } catch (Throwable th2) {
            SLog.e("openSDK_LOG.BaseApi", "setActivityIntent create pendingIntent exception", th2);
        }
    }

    protected Intent b(String str) {
        Intent intent = new Intent();
        if (m.c(g.a())) {
            intent.setClassName(Constants.PACKAGE_QQ_PAD, str);
            if (k.a(g.a(), intent)) {
                return intent;
            }
        }
        intent.setClassName("com.tencent.mobileqq", str);
        if (k.a(g.a(), intent)) {
            return intent;
        }
        intent.setClassName(Constants.PACKAGE_TIM, str);
        if (k.a(g.a(), intent)) {
            return intent;
        }
        return null;
    }

    protected Intent c() {
        Intent intent = new Intent();
        if (m.c(g.a())) {
            intent.setClassName(Constants.PACKAGE_QQ_PAD, "com.tencent.open.agent.AgentActivity");
            if (k.b(g.a(), intent)) {
                return intent;
            }
        }
        intent.setClassName("com.tencent.mobileqq", "com.tencent.open.agent.AgentActivity");
        if (k.b(g.a(), intent)) {
            return intent;
        }
        intent.setClassName(Constants.PACKAGE_TIM, "com.tencent.open.agent.AgentActivity");
        if (k.b(g.a(), intent)) {
            return intent;
        }
        return null;
    }

    protected void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SLog.i("openSDK_LOG.BaseApi", "--handleDownloadLastestQQ");
        new TDialog(activity, "", "https://imgcache.qq.com/ptlogin/static/qzsjump.html?" + HttpUtils.encodeUrl(bundle), null, this.c).show();
    }

    protected Intent c(String str) {
        Intent intent = new Intent();
        Intent b = b(str);
        if (b == null || b.getComponent() == null) {
            return null;
        }
        intent.setClassName(b.getComponent().getPackageName(), "com.tencent.open.agent.AgentActivity");
        return intent;
    }

    /* loaded from: classes8.dex */
    public class TempRequestListener implements IRequestListener {
        private final IUiListener b;
        private final Handler c;

        public TempRequestListener(IUiListener iUiListener) {
            this.b = iUiListener;
            this.c = new Handler(g.a().getMainLooper()) { // from class: com.tencent.connect.common.BaseApi.TempRequestListener.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 0) {
                        TempRequestListener.this.b.onComplete(message.obj);
                    } else {
                        TempRequestListener.this.b.onError(new UiError(message.what, (String) message.obj, null));
                    }
                }
            };
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onComplete(JSONObject jSONObject) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = jSONObject;
            obtainMessage.what = 0;
            this.c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onIOException(IOException iOException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = iOException.getMessage();
            obtainMessage.what = -2;
            this.c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onMalformedURLException(MalformedURLException malformedURLException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = malformedURLException.getMessage();
            obtainMessage.what = -3;
            this.c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onJSONException(JSONException jSONException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = jSONException.getMessage();
            obtainMessage.what = -4;
            this.c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = socketTimeoutException.getMessage();
            obtainMessage.what = -8;
            this.c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException networkUnavailableException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = networkUnavailableException.getMessage();
            obtainMessage.what = -10;
            this.c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onHttpStatusException(HttpUtils.HttpStatusException httpStatusException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = httpStatusException.getMessage();
            obtainMessage.what = -9;
            this.c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onUnknowException(Exception exc) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = exc.getMessage();
            obtainMessage.what = -6;
            this.c.sendMessage(obtainMessage);
        }
    }
}
