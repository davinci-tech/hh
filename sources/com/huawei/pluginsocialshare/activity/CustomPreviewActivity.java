package com.huawei.pluginsocialshare.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.embedded.y5;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.activity.CustomPreviewActivity;
import com.huawei.pluginsocialshare.view.ShareButtonView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.fdu;
import defpackage.mto;
import defpackage.mwd;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class CustomPreviewActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8511a;
    private LinearLayout b;
    private BroadcastReceiver c;
    private HealthCardView d;
    private c e = new c(this);
    private fdu g;
    private ShareButtonView h;
    private HealthSwitchButton j;

    class c extends BaseHandler<CustomPreviewActivity> {
        c(CustomPreviewActivity customPreviewActivity) {
            super(customPreviewActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: coL_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(CustomPreviewActivity customPreviewActivity, Message message) {
            if (message.what == 1) {
                if (message.obj instanceof Bitmap) {
                    CustomPreviewActivity.this.coI_().setImageBitmap((Bitmap) message.obj);
                    CustomPreviewActivity.this.g.awp_((Bitmap) message.obj);
                    CustomPreviewActivity.this.h.setShareContent(CustomPreviewActivity.this.g);
                    return;
                }
                return;
            }
            LogUtil.h("CustomPreviewActivity", "msg unknown");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("CustomPreviewActivity", "onCreate");
        setContentView(R.layout.activity_custom_preview);
        f();
        c();
        b();
        g();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("CustomPreviewActivity", "onDestroy");
        BroadcastReceiver broadcastReceiver = this.c;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            this.c = null;
        }
    }

    private void f() {
        this.f8511a = (ImageView) findViewById(R.id.preview_img);
        this.b = (LinearLayout) findViewById(R.id.share_watermark_layout);
        HealthCardView healthCardView = (HealthCardView) findViewById(R.id.share_custom_preview_cardView);
        this.d = healthCardView;
        mwd.cqK_(healthCardView, this, true);
    }

    private void b() {
        mwd.c();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.pluginsocialshare.activity.CustomPreviewActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent != null && "android.intent.action.LOCALE_CHANGED".equals(intent.getAction()) && (context instanceof Activity) && mwd.g()) {
                    ((Activity) context).finish();
                    LogUtil.h("CustomPreviewActivity", "onReceive ACTION_LOCALE_CHANGED finish");
                }
            }
        };
        this.c = broadcastReceiver;
        registerReceiver(broadcastReceiver, intentFilter);
    }

    public ImageView coI_() {
        return this.f8511a;
    }

    private void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mtq
            @Override // java.lang.Runnable
            public final void run() {
                CustomPreviewActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        Bitmap cqs_ = mwd.cqs_(mwd.cqu_(this.g), y5.h);
        if (cqs_ == null || this.e == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = cqs_;
        this.e.sendMessage(obtain);
    }

    private void c() {
        this.h = (ShareButtonView) findViewById(R.id.share_button_view);
        fdu d = mto.d();
        this.g = d;
        this.h.setShareContent(d);
        this.h.setPerViewImage(this.f8511a);
        this.h.setLogLayout(this.b);
        if (this.g == null) {
            LogUtil.h("CustomPreviewActivity", "mShareContent is null");
            finish();
        } else {
            a();
            this.h.setMap(this.g.s());
        }
    }

    private void g() {
        if (CommonUtil.bv() || CommonUtil.as()) {
            return;
        }
        ((RelativeLayout) findViewById(R.id.layout_share_userinfo)).setVisibility(0);
        ((HealthDivider) findViewById(R.id.userinfo_divder)).setVisibility(0);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.share_userinfo_switch);
        this.j = healthSwitchButton;
        healthSwitchButton.setChecked(mwd.i());
        this.j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.pluginsocialshare.activity.CustomPreviewActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CustomPreviewActivity.this.coK_(compoundButton, z);
            }
        });
    }

    /* synthetic */ void coK_(CompoundButton compoundButton, boolean z) {
        if (z) {
            mwd.e(true);
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            mwd.cqJ_(this, R.string._2130847207_res_0x7f0225e7, R.string._2130847202_res_0x7f0225e2, new View.OnClickListener() { // from class: mtn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomPreviewActivity.coH_(view);
                }
            }, new View.OnClickListener() { // from class: mtm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomPreviewActivity.this.coJ_(view);
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    public static /* synthetic */ void coH_(View view) {
        mwd.e(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void coJ_(View view) {
        this.j.setChecked(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.a("CustomPreviewActivity", "onConfigurationChanged");
        if (mwd.cqx_(this.b) == null) {
            LogUtil.h("CustomPreviewActivity", "onConfigurationChanged getWaterMarkBitmap is null");
            finish();
        } else {
            mwd.cqK_(this.d, this, true);
        }
    }
}
