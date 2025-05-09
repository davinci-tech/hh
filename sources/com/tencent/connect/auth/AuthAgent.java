package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.b.e;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.g;
import com.tencent.open.utils.i;
import com.tencent.open.utils.j;
import com.tencent.open.utils.k;
import com.tencent.open.utils.l;
import com.tencent.open.utils.m;
import com.tencent.open.web.security.JniInterface;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class AuthAgent extends BaseApi {
    public static final String KEY_FORCE_QR_LOGIN = "KEY_FORCE_QR_LOGIN";
    public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
    public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_NAME = null;
    public static final int SECURE_LIB_VERSION = 5;
    public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
    public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";

    /* renamed from: a, reason: collision with root package name */
    private IUiListener f11258a;
    private String d;
    private WeakReference<Activity> e;

    static {
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        String str = Build.CPU_ABI;
        if (str != null && !str.equals("")) {
            if (str.equalsIgnoreCase("arm64-v8a")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_ARM64_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                SLog.i("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
                return;
            }
            if (str.equalsIgnoreCase("x86")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_X86_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                SLog.i("openSDK_LOG.AuthAgent", "is x86 architecture");
                return;
            }
            if (str.equalsIgnoreCase("x86_64")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_X86_64_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                SLog.i("openSDK_LOG.AuthAgent", "is x86_64 architecture");
                return;
            }
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            SLog.i("openSDK_LOG.AuthAgent", "is arm(default) architecture");
            return;
        }
        SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        SLog.i("openSDK_LOG.AuthAgent", "is arm(default) architecture");
    }

    public AuthAgent(QQToken qQToken) {
        super(qQToken);
    }

    /* loaded from: classes8.dex */
    class c extends DefaultUiListener {
        private final IUiListener b;
        private final boolean c;
        private final Context d;

        public c(Context context, IUiListener iUiListener, boolean z, boolean z2) {
            this.d = context;
            this.b = iUiListener;
            this.c = z;
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                String string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = jSONObject.getString("openid");
                if (string != null && AuthAgent.this.c != null && string3 != null) {
                    AuthAgent.this.c.setAccessToken(string, string2);
                    AuthAgent.this.c.setOpenId(string3);
                    com.tencent.connect.a.a.d(this.d, AuthAgent.this.c);
                }
                String string4 = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string4 != null) {
                    try {
                        this.d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string4).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        SLog.e("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e);
                    }
                }
                if (this.c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                SLog.e("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e2);
            }
            this.b.onComplete(jSONObject);
            AuthAgent.this.releaseResource();
            SLog.release();
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
            this.b.onError(uiError);
            SLog.release();
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
            this.b.onCancel();
            SLog.release();
        }
    }

    int a(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment, boolean z2) {
        return doLogin(activity, str, iUiListener, z, fragment, z2, null);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment, boolean z2, Map<String, Object> map) {
        if (com.tencent.connect.a.a("openSDK_LOG.AuthAgent", iUiListener)) {
            return -1;
        }
        this.d = str;
        this.e = new WeakReference<>(activity);
        this.f11258a = iUiListener;
        Object[] objArr = new Object[2];
        boolean booleanExtra = activity.getIntent().getBooleanExtra(KEY_FORCE_QR_LOGIN, false);
        boolean b2 = i.a(activity, this.c.getAppId()).b("C_LoginWeb");
        SLog.i("openSDK_LOG.AuthAgent", "doLogin needForceQrLogin=" + booleanExtra + ", toWebLogin=" + b2);
        if (!booleanExtra && !b2 && a(activity, fragment, map, z, objArr)) {
            SLog.i("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "2", "1", "5", (String) objArr[0], "0", "0");
            return ((Integer) objArr[1]).intValue();
        }
        e.a().a(this.c.getOpenId(), this.c.getAppId(), "2", "1", "5", "1", "0", "0");
        SLog.w("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
        b bVar = new b(this.f11258a);
        this.f11258a = bVar;
        return a(z, bVar, z2, map);
    }

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
        this.f11258a = null;
    }

    private int a(boolean z, IUiListener iUiListener, boolean z2, Map<String, Object> map) {
        CookieSyncManager.createInstance(g.a());
        Bundle a2 = a();
        if (z) {
            a2.putString("isadd", "1");
        }
        a2.putString("scope", this.d);
        a2.putString("client_id", this.c.getAppId());
        if (isOEM) {
            a2.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-android-" + registerChannel + com.huawei.openalliance.ad.constant.Constants.LINK + businessId);
        } else {
            a2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str = (System.currentTimeMillis() / 1000) + "";
        a2.putString(HwPayConstant.KEY_SIGN, k.b(g.a(), str));
        a2.putString("time", str);
        a2.putString("display", JsNetwork.NetworkType.MOBILE);
        a2.putString(CommonConstant.ReqAccessTokenParam.RESPONSE_TYPE, "token");
        a2.putString(CommonConstant.ReqAccessTokenParam.REDIRECT_URI, "auth://tauth.qq.com/");
        a2.putString("cancel_display", "1");
        a2.putString("switch", "1");
        a2.putString("compat_v", "1");
        if (z2) {
            a2.putString(TemplateStyleRecord.STYLE, "qr");
        }
        String b2 = b(map);
        SLog.i("openSDK_LOG.AuthAgent", "OpenUI, showDialog addLoginAccount: " + b2);
        if (b2 != null && !b2.isEmpty()) {
            a2.putString("uin", b2);
        }
        boolean a3 = a(map);
        a2.putString("show_download_ui", Boolean.toString(a3));
        SLog.i("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- start, isShowDownloadUi=" + a3);
        final String str2 = j.a().a(g.a(), "https://openmobile.qq.com/oauth2.0/m_authorize?") + HttpUtils.encodeUrl(a2);
        final c cVar = new c(g.a(), iUiListener, true, false);
        SLog.d("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
        l.b(new Runnable() { // from class: com.tencent.connect.auth.AuthAgent.1
            @Override // java.lang.Runnable
            public void run() {
                final Activity activity;
                k.a(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 5);
                JniInterface.loadSo();
                if (AuthAgent.this.e == null || (activity = (Activity) AuthAgent.this.e.get()) == null) {
                    return;
                }
                activity.runOnUiThread(new Runnable() { // from class: com.tencent.connect.auth.AuthAgent.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JniInterface.isJniOk) {
                            com.tencent.connect.auth.a aVar = new com.tencent.connect.auth.a(activity, "action_login", str2, cVar, AuthAgent.this.c);
                            if (activity.isFinishing()) {
                                return;
                            }
                            aVar.show();
                            return;
                        }
                        SLog.w("openSDK_LOG.AuthAgent", "OpenUi, secure so load failed, goto download QQ.");
                        TDialog tDialog = new TDialog(activity, "", AuthAgent.this.a(""), cVar, AuthAgent.this.c);
                        if (activity.isFinishing()) {
                            return;
                        }
                        tDialog.show();
                    }
                });
            }
        });
        SLog.i("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- end");
        return 2;
    }

    private boolean a(Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            Object obj = map.get(Constants.KEY_ENABLE_SHOW_DOWNLOAD_URL);
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
        }
        return true;
    }

    private String b(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        Object obj = map.get(Constants.KEY_ADD_LOGIN_ACCOUNT);
        return obj instanceof String ? ((String) obj).trim() : "";
    }

    private boolean a(Activity activity, Fragment fragment, Map<String, Object> map, boolean z, Object[] objArr) {
        SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
        Intent c2 = c();
        if (c2 != null) {
            Bundle a2 = a();
            if (z) {
                a2.putString("isadd", "1");
            }
            a2.putString("scope", this.d);
            a2.putString("client_id", this.c.getAppId());
            if (isOEM) {
                a2.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-android-" + registerChannel + com.huawei.openalliance.ad.constant.Constants.LINK + businessId);
            } else {
                a2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            a2.putString("need_pay", "1");
            a(a2, map);
            a2.putString(Constants.KEY_APP_NAME, k.a(g.a()));
            c2.putExtra(Constants.KEY_ACTION, "action_login");
            c2.putExtra(Constants.KEY_PARAMS, a2);
            c2.putExtra("appid", this.c.getAppId());
            a2.putString(Constants.KEY_PPSTS, k.a(activity, a(a2)));
            try {
                this.f11258a = new b(this.f11258a);
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_LOGIN, this.f11258a);
                if (fragment != null) {
                    SLog.d("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
                    a(fragment, c2, Constants.REQUEST_LOGIN, map);
                } else {
                    SLog.d("openSDK_LOG.AuthAgent", "startAssitActivity activity");
                    a(activity, c2, Constants.REQUEST_LOGIN, map);
                }
                SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
                e.a().a(0, "LOGIN_CHECK_SDK", "1000", this.c.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
                objArr[0] = "0";
                objArr[1] = 1;
                return true;
            } catch (Exception e) {
                SLog.e("openSDK_LOG.AuthAgent", "startActionActivity() exception", e);
            }
        }
        e.a().a(1, "LOGIN_CHECK_SDK", "1000", this.c.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
        return false;
    }

    private void a(Bundle bundle, Map<String, Object> map) {
        String b2 = b(map);
        if (b2 == null || b2.isEmpty()) {
            return;
        }
        bundle.putString(Constants.KEY_ADD_LOGIN_ACCOUNT, b2);
    }

    private String a(Bundle bundle) {
        String string = bundle.getString("status_os");
        String string2 = bundle.getString("status_machine");
        String string3 = bundle.getString("status_version");
        String string4 = bundle.getString("sdkv");
        String string5 = bundle.getString("client_id");
        String string6 = bundle.getString("need_pay");
        String string7 = bundle.getString(Constants.PARAM_PLATFORM_ID);
        SLog.d("openSDK_LOG.AuthAgent", "os=" + string + ", machine=" + string2 + ", version=" + string3 + ", sdkv=" + string4 + ", appId=" + string5 + ", needPay=" + string6 + ", pf=" + string7);
        StringBuilder sb = new StringBuilder();
        if (string == null) {
            string = "";
        }
        sb.append(string);
        if (string2 == null) {
            string2 = "";
        }
        sb.append(string2);
        if (string3 == null) {
            string3 = "";
        }
        sb.append(string3);
        if (string4 == null) {
            string4 = "";
        }
        sb.append(string4);
        if (string5 == null) {
            string5 = "";
        }
        sb.append(string5);
        if (string6 == null) {
            string6 = "";
        }
        sb.append(string6);
        if (string7 == null) {
            string7 = "";
        }
        sb.append(string7);
        return sb.toString();
    }

    protected void a(IUiListener iUiListener) {
        String str;
        SLog.i("openSDK_LOG.AuthAgent", "reportDAU() -- start");
        String accessToken = this.c.getAccessToken();
        String openId = this.c.getOpenId();
        String appId = this.c.getAppId();
        if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(appId)) {
            str = "";
        } else {
            str = m.g("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
        }
        if (TextUtils.isEmpty(str)) {
            SLog.e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
            return;
        }
        Bundle a2 = a();
        a2.putString("encrytoken", str);
        HttpUtils.requestAsync(this.c, g.a(), "https://openmobile.qq.com/user/user_login_statis", a2, "POST", null);
        SLog.i("openSDK_LOG.AuthAgent", "reportDAU() -- end");
    }

    protected void b(IUiListener iUiListener) {
        Bundle a2 = a();
        a2.putString("reqType", "checkLogin");
        HttpUtils.requestAsync(this.c, g.a(), "https://openmobile.qq.com/v3/user/get_info", a2, "GET", new BaseApi.TempRequestListener(new a(iUiListener)));
    }

    /* loaded from: classes10.dex */
    class a extends DefaultUiListener {

        /* renamed from: a, reason: collision with root package name */
        IUiListener f11261a;

        public a(IUiListener iUiListener) {
            this.f11261a = iUiListener;
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            if (obj == null) {
                SLog.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
                return;
            }
            JSONObject jSONObject = (JSONObject) obj;
            try {
                int i = jSONObject.getInt("ret");
                String string = i == 0 ? "success" : jSONObject.getString("msg");
                IUiListener iUiListener = this.f11261a;
                if (iUiListener != null) {
                    iUiListener.onComplete(new JSONObject().put("ret", i).put("msg", string));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                SLog.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            IUiListener iUiListener = this.f11261a;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f11261a;
            if (iUiListener != null) {
                iUiListener.onCancel();
            }
        }
    }

    /* loaded from: classes8.dex */
    class b extends DefaultUiListener {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<IUiListener> f11262a;
        private final String c = "sendinstall";
        private final String d = "installwording";
        private final String e = "https://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";

        public b(IUiListener iUiListener) {
            this.f11262a = new WeakReference<>(iUiListener);
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject;
            String str;
            if (obj == null || (jSONObject = (JSONObject) obj) == null) {
                return;
            }
            try {
                r2 = jSONObject.getInt("sendinstall") == 1;
                str = jSONObject.getString("installwording");
            } catch (JSONException unused) {
                SLog.w("openSDK_LOG.AuthAgent", "FeedConfirmListener onComplete There is no value for sendinstall.");
                str = "";
            }
            String decode = URLDecoder.decode(str);
            SLog.i("openSDK_LOG.AuthAgent", " WORDING = " + decode + "xx,showConfirmDialog=" + r2);
            if (r2 && !TextUtils.isEmpty(decode)) {
                a(decode, this.f11262a.get(), obj);
                SLog.i("openSDK_LOG.AuthAgent", " WORDING is not empty and return");
                return;
            }
            IUiListener iUiListener = this.f11262a.get();
            if (iUiListener != null) {
                if (AuthAgent.this.c != null) {
                    boolean saveSession = AuthAgent.this.c.saveSession(jSONObject);
                    SLog.i("openSDK_LOG.AuthAgent", " saveSession saveSuccess=" + saveSession);
                    if (saveSession) {
                        iUiListener.onComplete(obj);
                        return;
                    }
                }
                iUiListener.onError(new UiError(-6, Constants.MSG_PERSISTENCE_FAIL, null));
                return;
            }
            SLog.i("openSDK_LOG.AuthAgent", " userListener is null");
        }

        abstract class a implements View.OnClickListener {
            Dialog d;

            a(Dialog dialog) {
                this.d = dialog;
            }
        }

        private void a(String str, final IUiListener iUiListener, final Object obj) {
            PackageInfo packageInfo;
            if (AuthAgent.this.e != null) {
                Activity activity = (Activity) AuthAgent.this.e.get();
                if (activity == null) {
                    SLog.i("openSDK_LOG.AuthAgent", "showFeedConfrimDialog mActivity.get() null and return");
                    return;
                }
                Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(1);
                PackageManager packageManager = activity.getPackageManager();
                try {
                    packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    SLog.e("openSDK_LOG.AuthAgent", "showFeedConfrimDialog exception:" + e.getStackTrace().toString());
                    packageInfo = null;
                }
                Drawable loadIcon = packageInfo != null ? packageInfo.applicationInfo.loadIcon(packageManager) : null;
                View.OnClickListener onClickListener = new a(dialog) { // from class: com.tencent.connect.auth.AuthAgent.b.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        b.this.a();
                        if (this.d != null && this.d.isShowing()) {
                            this.d.dismiss();
                        }
                        IUiListener iUiListener2 = iUiListener;
                        if (iUiListener2 != null) {
                            iUiListener2.onComplete(obj);
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                };
                View.OnClickListener onClickListener2 = new a(dialog) { // from class: com.tencent.connect.auth.AuthAgent.b.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (this.d != null && this.d.isShowing()) {
                            this.d.dismiss();
                        }
                        IUiListener iUiListener2 = iUiListener;
                        if (iUiListener2 != null) {
                            iUiListener2.onComplete(obj);
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                };
                ColorDrawable colorDrawable = new ColorDrawable();
                colorDrawable.setAlpha(0);
                dialog.getWindow().setBackgroundDrawable(colorDrawable);
                dialog.setContentView(a(activity, loadIcon, str, onClickListener, onClickListener2));
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.tencent.connect.auth.AuthAgent.b.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        IUiListener iUiListener2 = iUiListener;
                        if (iUiListener2 != null) {
                            iUiListener2.onComplete(obj);
                        }
                    }
                });
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                dialog.show();
                return;
            }
            SLog.i("openSDK_LOG.AuthAgent", "showFeedConfrimDialog mActivity null and return");
        }

        private Drawable a(String str, Context context) {
            InputStream open;
            Bitmap bitmap;
            Drawable drawable = null;
            try {
                open = context.getApplicationContext().getAssets().open(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (open == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                try {
                    bitmap = BitmapFactory.decodeStream(open);
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    bitmap = null;
                }
                if (bitmap == null) {
                    return null;
                }
                byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                NinePatch.isNinePatchChunk(ninePatchChunk);
                return new NinePatchDrawable(bitmap, ninePatchChunk, new Rect(), null);
            }
            drawable = Drawable.createFromStream(open, str);
            open.close();
            return drawable;
        }

        private View a(Context context, Drawable drawable, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(com.huawei.openalliance.ad.constant.Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            RelativeLayout relativeLayout = new RelativeLayout(context);
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setId(1);
            int i = (int) (60.0f * f);
            int i2 = (int) (f * 14.0f);
            int i3 = (int) (18.0f * f);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
            layoutParams.addRule(9);
            layoutParams.setMargins(0, i3, (int) (6.0f * f), i3);
            relativeLayout.addView(imageView, layoutParams);
            TextView textView = new TextView(context);
            textView.setText(str);
            textView.setTextSize(14.0f);
            textView.setGravity(3);
            textView.setIncludeFontPadding(false);
            textView.setPadding(0, 0, 0, 0);
            textView.setLines(2);
            textView.setId(5);
            textView.setMinWidth((int) (185.0f * f));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, 1);
            layoutParams2.addRule(6, 1);
            float f2 = 5.0f * f;
            layoutParams2.setMargins(0, 0, (int) f2, 0);
            relativeLayout.addView(textView, layoutParams2);
            View view = new View(context);
            view.setBackgroundColor(Color.rgb(com.huawei.hms.kit.awareness.barrier.internal.e.a.M, com.huawei.hms.kit.awareness.barrier.internal.e.a.M, com.huawei.hms.kit.awareness.barrier.internal.e.a.M));
            view.setId(3);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 2);
            layoutParams3.addRule(3, 1);
            layoutParams3.addRule(5, 1);
            layoutParams3.addRule(7, 5);
            int i4 = (int) (12.0f * f);
            layoutParams3.setMargins(0, 0, 0, i4);
            relativeLayout.addView(view, layoutParams3);
            LinearLayout linearLayout = new LinearLayout(context);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(5, 1);
            layoutParams4.addRule(7, 5);
            layoutParams4.addRule(3, 3);
            Button button = new Button(context);
            button.setText("跳过");
            button.setBackgroundDrawable(a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(36, 97, 131));
            button.setTextSize(20.0f);
            button.setOnClickListener(onClickListener2);
            button.setId(4);
            int i5 = (int) (45.0f * f);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, i5);
            layoutParams5.rightMargin = i2;
            int i6 = (int) (4.0f * f);
            layoutParams5.leftMargin = i6;
            layoutParams5.weight = 1.0f;
            linearLayout.addView(button, layoutParams5);
            Button button2 = new Button(context);
            button2.setText("确定");
            button2.setTextSize(20.0f);
            button2.setTextColor(Color.rgb(255, 255, 255));
            button2.setBackgroundDrawable(a("buttonPost.png", context));
            button2.setOnClickListener(onClickListener);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, i5);
            layoutParams6.weight = 1.0f;
            layoutParams6.rightMargin = i6;
            linearLayout.addView(button2, layoutParams6);
            relativeLayout.addView(linearLayout, layoutParams4);
            ViewGroup.LayoutParams layoutParams7 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (f * 163.0f));
            relativeLayout.setPadding(i2, 0, i4, i4);
            relativeLayout.setLayoutParams(layoutParams7);
            relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
            PaintDrawable paintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
            paintDrawable.setCornerRadius(f2);
            relativeLayout.setBackgroundDrawable(paintDrawable);
            return relativeLayout;
        }

        protected void a() {
            Activity activity;
            Bundle b = AuthAgent.this.b();
            if (AuthAgent.this.e == null || (activity = (Activity) AuthAgent.this.e.get()) == null) {
                return;
            }
            HttpUtils.requestAsync(AuthAgent.this.c, activity, "https://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", b, "POST", null);
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            if (this.f11262a.get() != null) {
                this.f11262a.get().onError(uiError);
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            if (this.f11262a.get() != null) {
                this.f11262a.get().onCancel();
            }
        }
    }
}
