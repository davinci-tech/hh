package com.huawei.ui.main.stories.fitness.activity.stressgame.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.algorithm.api.StressGameApi;
import com.huawei.health.algorithm.callback.StressGameBindCallback;
import com.huawei.health.algorithm.callback.StressGameNoticeInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.view.CustomWebView;
import com.huawei.operation.view.WebViewClientImpl;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.R$string;
import defpackage.foq;
import defpackage.gge;
import defpackage.pvo;
import defpackage.pvv;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class StressGameMainActivity extends Activity implements ServiceConnection {

    /* renamed from: a, reason: collision with root package name */
    private foq f9905a;
    private CustomWebView b;
    private e f;
    private HealthProgressBar g;
    private boolean h;
    private ImageView i;
    private SharedPreferences l;
    private a m;
    private b n;
    private StressGameApi o;
    private HealthTextView p;
    private WebView q;
    private RelativeLayout s;
    private boolean c = false;
    private float d = 0.0f;
    private float r = 0.0f;
    private boolean e = false;
    private boolean j = false;
    private long k = 0;

    public StressGameMainActivity() {
        this.f = new e();
        this.m = new a();
        this.n = new b();
    }

    static class e extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<StressGameMainActivity> f9908a;

        private e(StressGameMainActivity stressGameMainActivity) {
            this.f9908a = new WeakReference<>(stressGameMainActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            StressGameMainActivity stressGameMainActivity = this.f9908a.get();
            if (stressGameMainActivity != null) {
                int i = message.what;
                if (i == 100) {
                    LogUtil.a("StressGameMainActivity", "handler message 100");
                    stressGameMainActivity.c();
                    stressGameMainActivity.e();
                    stressGameMainActivity.finish();
                    stressGameMainActivity.overridePendingTransition(0, 0);
                    return;
                }
                if (i == 200) {
                    if (stressGameMainActivity.s != null) {
                        stressGameMainActivity.s.setVisibility(0);
                        return;
                    }
                    return;
                }
                if (i == 300) {
                    LogUtil.a("StressGameMainActivity", "handleMessage: Enter STOP_SERVIER_TIMER");
                    LogUtil.a("StressGameMainActivity", "handleMessage: STOP_SERVIER_TIMER mIsCloseMesure :", Boolean.valueOf(stressGameMainActivity.e));
                    LogUtil.a("StressGameMainActivity", "handleMessage: STOP_SERVIER_TIMER mIsStopTimer : ", Boolean.valueOf(stressGameMainActivity.j));
                    if (stressGameMainActivity.o != null) {
                        if (!stressGameMainActivity.e) {
                            stressGameMainActivity.o.closeMeasure();
                            LogUtil.a("StressGameMainActivity", "handler message close mesure");
                            stressGameMainActivity.e = true;
                        }
                        if (!stressGameMainActivity.j) {
                            stressGameMainActivity.j = true;
                            LogUtil.a("StressGameMainActivity", " handler message stopTimer()");
                            stressGameMainActivity.o.stopTimer();
                        }
                        stressGameMainActivity.c = false;
                        return;
                    }
                    LogUtil.a("StressGameMainActivity", "restart stressGameMainActivity.mStressGameBindService is null");
                    return;
                }
                LogUtil.a("StressGameMainActivity", "MainHandler handleMessage(Message msg) the msg.what not in action");
                return;
            }
            LogUtil.a("StressGameMainActivity", "StressGameMainActivity MainHandler stressGameMainActivity == null ");
        }
    }

    static class a implements StressGameBindCallback {
        private final WeakReference<StressGameMainActivity> d;

        @Override // com.huawei.health.algorithm.callback.StressGameBindCallback
        public void connectState(float f) {
        }

        @Override // com.huawei.health.algorithm.callback.StressGameBindCallback
        public void requstConnet(float f) {
        }

        private a(StressGameMainActivity stressGameMainActivity) {
            this.d = new WeakReference<>(stressGameMainActivity);
        }

        @Override // com.huawei.health.algorithm.callback.StressGameBindCallback
        public void algResult(String str) {
            StressGameMainActivity stressGameMainActivity = this.d.get();
            if (stressGameMainActivity != null && str != null) {
                stressGameMainActivity.e("algResult", str);
            } else {
                LogUtil.a("StressGameMainActivity", "StressGameMainActivity stressGameMainActivity == null ");
            }
        }

        @Override // com.huawei.health.algorithm.callback.StressGameBindCallback
        public void measureEnd(float f) {
            StressGameMainActivity stressGameMainActivity = this.d.get();
            if (stressGameMainActivity != null) {
                stressGameMainActivity.c = false;
                stressGameMainActivity.d("measureEnd", f);
            } else {
                LogUtil.a("StressGameMainActivity", "StressGameMainActivity stressGameMainActivity == null ");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, final float f) {
        if (f == 100.0f) {
            this.l.edit().putBoolean("MeasureEnd", true).commit();
        } else {
            this.l.edit().putBoolean("MeasureEnd", false).commit();
        }
        this.q.post(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameMainActivity.3
            @Override // java.lang.Runnable
            public void run() {
                WebView webView = StressGameMainActivity.this.q;
                String str2 = Constants.JAVA_SCRIPT + str + Constants.LEFT_BRACKET_ONLY + f + Constants.RIGHT_BRACKET_ONLY;
                webView.loadUrl(str2);
                WebViewInstrumentation.loadUrl(webView, str2);
                LogUtil.a("StressGameMainActivity", "sendMessageToH5() functionName = ", str, " algorithmValue = ", Float.valueOf(f));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final String str, final String str2) {
        this.q.post(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameMainActivity.1
            @Override // java.lang.Runnable
            public void run() {
                WebView webView = StressGameMainActivity.this.q;
                String str3 = Constants.JAVA_SCRIPT + str + Constants.LEFT_BRACKET + str2 + Constants.RIGHT_BRACKET;
                webView.loadUrl(str3);
                WebViewInstrumentation.loadUrl(webView, str3);
                LogUtil.a("StressGameMainActivity", "sendStringMessageToH5() functionName = ", str, " algorithmValue = ", str2);
            }
        });
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Window window = getWindow();
        requestWindowFeature(1);
        window.setFlags(1024, 1024);
        super.onCreate(bundle);
        getWindow().setFlags(128, 128);
        setContentView(R.layout.activity_stress_game_main);
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getLongExtra("STRESSGAME_STARTTIME", System.currentTimeMillis());
        }
        LogUtil.a("StressGameMainActivity", "mStressGameStartTime = ", Long.valueOf(this.k));
        this.l = getSharedPreferences("sleep_shared_pref_smart_msg", 0);
        h();
        if (this.l.getString("stress_game_result_data", "").length() == 0) {
            i();
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    private void f() {
        foq aBU_ = foq.aBU_(this);
        this.f9905a = aBU_;
        aBU_.a(R.layout.stress_game_main_back_dialog).aBW_(R.id.sug_stress_dialog_No, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameMainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StressGameMainActivity.this.getWindow().getDecorView().setSystemUiVisibility(5894);
                StressGameMainActivity.this.f9905a.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).aBW_(R.id.sug_coach_dialog_yes, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameMainActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("StressGameMainActivity", "mDialogHelper click sure");
                StressGameMainActivity.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        StressGameApi stressGameApi = this.o;
        if (stressGameApi == null) {
            LogUtil.a("StressGameMainActivity", "initDialogDismissButton mStressGameBindService is null");
        } else if (this.d == 1.0f && this.r == 1.0f) {
            d("measureEnd", 100.0f);
            if (!this.e) {
                this.o.closeMeasure();
                LogUtil.a("StressGameMainActivity", "have measure data , back in the process of measurement , close Measure()");
                this.e = true;
            }
            if (!this.j) {
                this.j = true;
                LogUtil.a("StressGameMainActivity", "have measure data , if if back in the process of measurement stopTimer()");
                this.o.stopTimer();
            }
            this.c = false;
        } else {
            stressGameApi.closeMeasure();
            d("measureEnd", 300.0f);
            if (!this.e) {
                this.o.closeMeasure();
                LogUtil.a("StressGameMainActivity", "no have measure data , back in the process of measurement , close Measure()");
                this.e = true;
            }
            if (!this.j) {
                this.j = true;
                LogUtil.a("StressGameMainActivity", "no have measure data , back in the process of measurement , stopTimer()");
                this.o.stopTimer();
            }
        }
        c();
        this.h = true;
        b(0, 1);
        e();
        finish();
        overridePendingTransition(0, 0);
        LogUtil.a("StressGameMainActivity", "clean stressgame Page");
        this.f9905a.a();
    }

    static class b implements StressGameNoticeInterface {
        private final WeakReference<StressGameMainActivity> e;

        private b(StressGameMainActivity stressGameMainActivity) {
            this.e = new WeakReference<>(stressGameMainActivity);
        }

        @Override // com.huawei.health.algorithm.callback.StressGameNoticeInterface
        public void setCloseMeasure(boolean z) {
            StressGameMainActivity stressGameMainActivity = this.e.get();
            if (stressGameMainActivity != null) {
                stressGameMainActivity.e = z;
            } else {
                LogUtil.a("StressGameMainActivity", "mIsCloseMesure stressGameMainActivity is null");
            }
        }

        @Override // com.huawei.health.algorithm.callback.StressGameNoticeInterface
        public void setStopTimer(boolean z) {
            StressGameMainActivity stressGameMainActivity = this.e.get();
            if (stressGameMainActivity != null) {
                stressGameMainActivity.j = z;
            } else {
                LogUtil.a("StressGameMainActivity", "setIsStopTimer stressGameMainActivity is null");
            }
        }

        @Override // com.huawei.health.algorithm.callback.StressGameNoticeInterface
        public boolean getCloseMeasure() {
            StressGameMainActivity stressGameMainActivity = this.e.get();
            if (stressGameMainActivity != null) {
                return stressGameMainActivity.e;
            }
            LogUtil.a("StressGameMainActivity", "getIsCloseMesure stressGameMainActivity is null");
            return false;
        }

        @Override // com.huawei.health.algorithm.callback.StressGameNoticeInterface
        public boolean getStopTimer() {
            StressGameMainActivity stressGameMainActivity = this.e.get();
            if (stressGameMainActivity != null) {
                return stressGameMainActivity.j;
            }
            LogUtil.a("StressGameMainActivity", "getIsStopTimer stressGameMainActivity is null");
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null || this.o == null) {
            return;
        }
        LogUtil.a("StressGameMainActivity", "setStressGameBindCallback");
        this.o.setStressGameServiceCallBack(iBinder, this.n, this.m);
        this.o.startTimer();
        this.o.openMeasure();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        LogUtil.a("StressGameMainActivity", "onServiceDisconnected() ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        b();
        this.l.edit().putString("stress_game_result_data", "").commit();
        this.l.edit().putBoolean("MeasureEnd", false).commit();
        c();
        Intent intent = new Intent(this, (Class<?>) StressGameMainActivity.class);
        intent.putExtra("STRESSGAME_STARTTIME", this.k);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    class c {
        private c() {
        }

        @JavascriptInterface
        public void sharePage(int i) {
            LogUtil.a("StressGameMainActivity", "sharePage() count = ", Integer.valueOf(i));
            StressGameMainActivity.this.f.sendEmptyMessage(200);
            if (i == 200) {
                LogUtil.a("StressGameMainActivity", "sharePage MeasureEnd");
                StressGameMainActivity.this.c = false;
                StressGameMainActivity.this.f.sendEmptyMessage(300);
                LogUtil.a("StressGameMainActivity", "isServiceRunning is true 200 MeasureEnd");
                if (StressGameMainActivity.this.h) {
                    return;
                }
                StressGameMainActivity.this.h = true;
                StressGameMainActivity.this.b(1, 0);
                return;
            }
            if (i == 100) {
                StressGameMainActivity.this.n();
                return;
            }
            if (i == 300) {
                if (StressGameMainActivity.this.h) {
                    return;
                }
                StressGameMainActivity.this.h = true;
                StressGameMainActivity.this.b(0, 0);
                return;
            }
            LogUtil.a("StressGameMainActivity", "sharePage err ");
        }

        @JavascriptInterface
        public void saveResultData(String str) {
            if (str != null) {
                StressGameMainActivity.this.l.edit().putString("stress_game_result_data", str).commit();
                LogUtil.a("StressGameMainActivity", "-----h5ResultData------", str);
                LogUtil.a("StressGameMainActivity", " save values = ", StressGameMainActivity.this.l.getString("stress_game_result_data", ""));
            }
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        pvv.duv_(this);
        WebView webView = this.q;
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        LogUtil.a("StressGameMainActivity", "Activity onPause close window or change page");
        if (this.c) {
            LogUtil.a("StressGameMainActivity", "onPause mesureing senmessageToH5 close window or change page");
            d("measureEnd", 100.0f);
        }
        m();
        WebView webView = this.q;
        if (webView != null) {
            webView.onPause();
        }
        this.c = false;
    }

    private void h() {
        this.g = (HealthProgressBar) findViewById(R.id.load_url_progress);
        this.q = (WebView) findViewById(R.id.web_stress);
        this.s = (RelativeLayout) findViewById(R.id.stress_titlebar);
        this.i = (ImageView) findViewById(R.id.stress_titlebar_iv);
        this.p = (HealthTextView) findViewById(R.id.stress_titlebar_title_text);
        this.s.setVisibility(8);
        g();
        d();
    }

    private void g() {
        WebSettings settings = this.q.getSettings();
        settings.setAllowFileAccess(false);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.q.setWebChromeClient(new WebChromeClient());
        this.q.addJavascriptInterface(new c(), OsType.ANDROID);
    }

    private void d() {
        String str;
        this.b = new CustomWebView(this, this.g, this.q, this.f, 3002);
        if (pvo.a()) {
            LogUtil.a("StressGameMainActivity", "special phone");
            str = "file:///android_asset/stressGame/html/twoVideoPlay.html";
        } else {
            str = "file:///android_asset/stressGame/html/twoVideoPlay_old.html";
        }
        this.b.setMyWebViewClientImpl(new WebViewClientImpl() { // from class: com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameMainActivity.4
            @Override // com.huawei.operation.view.WebViewClientImpl
            public void onMyPageStarted() {
            }

            @Override // com.huawei.operation.view.WebViewClientImpl
            public void shouldMyInterceptRequest() {
            }

            @Override // com.huawei.operation.view.WebViewClientImpl
            public void onMyPageFinished() {
                StressGameMainActivity.this.a();
            }
        });
        this.b.load(str);
        this.p.setText(getString(R$string.IDS_hw_biofeedback_game));
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.stressgame.activity.StressGameMainActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StressGameMainActivity.this.k();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        d("measureEnd", 100.0f);
        m();
        this.c = false;
        e();
        finish();
        overridePendingTransition(0, 0);
    }

    private void m() {
        StressGameApi stressGameApi = this.o;
        if (stressGameApi == null) {
            LogUtil.a("StressGameMainActivity", "stopService mStressGameBindService is null");
            return;
        }
        if (!this.e) {
            stressGameApi.closeMeasure();
            LogUtil.a("StressGameMainActivity", "stopService closeMeasure");
            this.e = true;
        }
        if (this.j) {
            return;
        }
        this.j = true;
        LogUtil.a("StressGameMainActivity", "stopService stopTimer");
        this.o.stopTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("StressGameMainActivity", " iniStartPage state= ", Boolean.valueOf(this.l.getBoolean("MeasureEnd", false)));
        LogUtil.a("StressGameMainActivity", "iniStartPage() H5 result = ", this.l.getString("stress_game_result_data", ""));
        if (this.l.getString("stress_game_result_data", "").length() == 0) {
            LogUtil.a("StressGameMainActivity", "iniStartPage StressGame Play");
            e("pageInitFinish", "true");
            WebView webView = this.q;
            webView.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})();javascript:(function() { var audio = document.getElementsByTagName('audio'); for(var i=0;i<audio.length;i++){audio[i].play();}})()");
            WebViewInstrumentation.loadUrl(webView, "javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})();javascript:(function() { var audio = document.getElementsByTagName('audio'); for(var i=0;i<audio.length;i++){audio[i].play();}})()");
            return;
        }
        LogUtil.a("StressGameMainActivity", "show resultValues page");
        LogUtil.a("StressGameMainActivity", this.l.getString("stress_game_result_data", ""));
        e("resultData", this.l.getString("stress_game_result_data", ""));
    }

    private void i() {
        LogUtil.a("StressGameMainActivity", "initService");
        StressGameApi stressGameApi = (StressGameApi) Services.c("StressGame", StressGameApi.class);
        this.o = stressGameApi;
        if (stressGameApi != null) {
            stressGameApi.bindService(this, this);
        }
        this.c = true;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        WebView webView = this.q;
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("StressGameMainActivity", "StressGameMainActivity.mIsCloseMesure ", Boolean.valueOf(this.e));
        LogUtil.a("StressGameMainActivity", " StressGameMainActivity.mIsStopTimer", Boolean.valueOf(this.j));
        StressGameApi stressGameApi = this.o;
        if (stressGameApi != null) {
            if (!this.e) {
                stressGameApi.closeMeasure();
                LogUtil.a("StressGameMainActivity", " Activity onDestroy closeMeasure");
                this.e = true;
            }
            if (!this.j) {
                this.j = true;
                LogUtil.a("StressGameMainActivity", " Activity onDestroy topTimer()");
                this.o.stopTimer();
            }
            this.o.unbindService(this, this);
            return;
        }
        LogUtil.a("StressGameMainActivity", "cleanData mStressGameBindService is null");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            LogUtil.a("StressGameMainActivity", "onKeyDown() key back, isMesureing is ", Boolean.valueOf(this.c));
            if (this.c) {
                f();
                this.f9905a.e();
                return false;
            }
            l();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void l() {
        StressGameApi stressGameApi = this.o;
        if (stressGameApi != null) {
            if (!this.e) {
                stressGameApi.closeMeasure();
                LogUtil.a("StressGameMainActivity", "onKeyDown closeMeasure");
                this.e = true;
            }
            if (!this.j) {
                this.j = true;
                LogUtil.a("StressGameMainActivity", "onKeyDown stopTimer()");
                this.o.stopTimer();
            }
        } else {
            LogUtil.a("StressGameMainActivity", "onKeyDown mStressGameBindService is null");
        }
        c();
        b(0, 1);
        e();
        finish();
        overridePendingTransition(0, 0);
    }

    private void b() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        gge.e(AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_RETRY_2160021.value(), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("stress_game_status", Integer.valueOf(i));
        hashMap.put("halfway_out", Integer.valueOf(i2));
        gge.e(AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_START_2160020.value(), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.k > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.k;
            HashMap hashMap = new HashMap(16);
            hashMap.put("dwelltime", Long.valueOf(currentTimeMillis - j));
            gge.e(AnalyticsValue.HEALTH_PRESSUER_STRESSGAME_RETRY_2160022.value(), hashMap);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
