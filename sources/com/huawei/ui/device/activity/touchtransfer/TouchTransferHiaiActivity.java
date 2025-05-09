package com.huawei.ui.device.activity.touchtransfer;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class TouchTransferHiaiActivity extends BaseActivity {
    private Context b;
    private AnimationDrawable c;
    private ImageView d;
    private e e = null;

    /* renamed from: a, reason: collision with root package name */
    private SmartClipManager.SmartLoadPluginCallback f9243a = new SmartClipManager.SmartLoadPluginCallback() { // from class: com.huawei.ui.device.activity.touchtransfer.TouchTransferHiaiActivity.4
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.SmartLoadPluginCallback
        public void onLoadPluginResult(int i) {
            LogUtil.a("TouchTransferHiaiActivity", "loadPluginCallback:", Integer.valueOf(i));
            if (TouchTransferHiaiActivity.this.c != null) {
                TouchTransferHiaiActivity.this.c.stop();
                TouchTransferHiaiActivity.this.c = null;
            }
            if (TouchTransferHiaiActivity.this.d != null) {
                TouchTransferHiaiActivity.this.d = null;
            }
            if (TouchTransferHiaiActivity.this.e != null) {
                Message obtainMessage = TouchTransferHiaiActivity.this.e.obtainMessage();
                obtainMessage.what = 1000;
                TouchTransferHiaiActivity.this.e.sendMessageDelayed(obtainMessage, 500L);
            }
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.SmartLoadPluginCallback
        public void onLoadPluginProgress(int i) {
            LogUtil.a("TouchTransferHiaiActivity", "loadPluginProgress:", Integer.valueOf(i));
        }
    };

    static class e extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<TouchTransferHiaiActivity> f9244a;

        e(TouchTransferHiaiActivity touchTransferHiaiActivity, Looper looper) {
            super(looper);
            this.f9244a = new WeakReference<>(touchTransferHiaiActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TouchTransferHiaiActivity touchTransferHiaiActivity = this.f9244a.get();
            if (touchTransferHiaiActivity == null) {
                return;
            }
            if (message.what == 1000) {
                touchTransferHiaiActivity.finish();
            } else {
                LogUtil.h("TouchTransferHiaiActivity", "Handler default");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_touch_transfer_selector);
        this.b = this;
        b();
        LogUtil.a("TouchTransferHiaiActivity", "startLoadSmartPlugin");
        this.e = new e(this, BaseApplication.getContext().getMainLooper());
        SmartClipManager.e(BaseApplication.getContext()).a(this.f9243a);
    }

    private void b() {
        this.d = (ImageView) findViewById(R.id.touch_album_image);
        WindowManager windowManager = (WindowManager) this.b.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (windowManager != null) {
            int width = windowManager.getDefaultDisplay().getWidth();
            ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = width;
            this.d.setLayoutParams(layoutParams);
            this.d.setImageResource(R.drawable._2131431790_res_0x7f0b116e);
            if (this.d.getDrawable() instanceof AnimationDrawable) {
                this.c = (AnimationDrawable) this.d.getDrawable();
            }
            this.c.start();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AnimationDrawable animationDrawable = this.c;
        if (animationDrawable != null) {
            animationDrawable.stop();
            this.c = null;
        }
        if (this.d != null) {
            this.d = null;
        }
        e eVar = this.e;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
            this.e = null;
        }
        LogUtil.a("TouchTransferHiaiActivity", "onDestroy()");
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
