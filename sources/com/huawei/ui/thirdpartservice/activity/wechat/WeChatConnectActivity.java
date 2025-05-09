package com.huawei.ui.thirdpartservice.activity.wechat;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.main.api.WeChatApi;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.thirdpartservice.activity.wechat.WeChatConnectActivity;
import defpackage.ezd;
import defpackage.ezl;
import defpackage.jdx;
import defpackage.nsn;
import defpackage.shv;
import defpackage.shy;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes9.dex */
public class WeChatConnectActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10554a;
    private HealthTextView b;
    private HealthTextView c;
    private b d;
    private HealthTextView f;
    private HealthProgressBar g;
    private ImageView h;
    private HealthTextView l;
    private WeChatApi m;
    private HealthButton n;
    private shy j = null;
    private long i = 0;
    private boolean e = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wechat_health_connect);
        this.m = (WeChatApi) Services.c("Main", WeChatApi.class);
        this.d = new b(this);
        a();
        c(false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.e) {
            jdx.b(new Runnable() { // from class: sgi
                @Override // java.lang.Runnable
                public final void run() {
                    WeChatConnectActivity.this.e();
                }
            });
        }
    }

    public /* synthetic */ void e() {
        if (this.m.isUserBind(BaseApplication.getContext())) {
            this.d.sendEmptyMessage(3);
        }
    }

    private void a() {
        this.h = (ImageView) findViewById(R.id.qrCode_img);
        this.f = (HealthTextView) findViewById(R.id.qrCode_valid_time);
        this.b = (HealthTextView) findViewById(R.id.weChat_qrCode_guide_note1);
        this.f10554a = (HealthTextView) findViewById(R.id.weChat_qrCode_guide_note2);
        this.c = (HealthTextView) findViewById(R.id.weChat_qrCode_guide_note3);
        this.n = (HealthButton) findViewById(R.id.weChat_qrCode_save);
        this.l = (HealthTextView) findViewById(R.id.weChat_faq);
        this.g = (HealthProgressBar) findViewById(R.id.show_my_qr_progressbar);
        this.b.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130844182_res_0x7f021a16), UnitUtil.e(1.0d, 1, 0), nsn.i(BaseApplication.getContext())));
        this.f10554a.setText(String.format(Locale.ENGLISH, getString(R.string._2130844149_res_0x7f0219f5), 2));
        this.c.setText(String.format(Locale.ENGLISH, getString(R.string._2130843434_res_0x7f02172a), 3));
        this.n.setOnClickListener(this);
        this.l.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(shy shyVar) {
        Bitmap bitmap;
        if (shyVar == null) {
            LogUtil.h("WeChatConnectActivity", "fillQrCodeData() qrCodeData is null.");
            return;
        }
        if (this.h == null) {
            LogUtil.h("WeChatConnectActivity", "fillQrCodeData() mQrCodeImg is null.");
            return;
        }
        ezd.e.d dVar = new ezd.e.d();
        dVar.a(this.h.getWidth(), this.h.getHeight());
        try {
            bitmap = ezd.aua_(shyVar.d(), dVar.a());
        } catch (ezl unused) {
            LogUtil.b("WeChatConnectActivity", "fillQrCodeData() generateMap meet exception ");
            bitmap = null;
        }
        if (bitmap == null) {
            LogUtil.h("WeChatConnectActivity", "fillQrCodeData() qrBitmap is null.");
        } else {
            this.h.setImageBitmap(bitmap);
            d(shyVar, this.f);
        }
    }

    private void d(shy shyVar, HealthTextView healthTextView) {
        healthTextView.setText(String.format(Locale.ENGLISH, getString(R.string._2130843431_res_0x7f021727), Integer.valueOf(shyVar.c()), UnitUtil.a("HH:mm", shyVar.e())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final boolean z) {
        new shv().c(new IBaseResponseCallback() { // from class: sgg
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WeChatConnectActivity.this.a(z, i, obj);
            }
        });
    }

    public /* synthetic */ void a(boolean z, int i, Object obj) {
        if (i != 0 || !(obj instanceof shy)) {
            if (i == 2) {
                this.d.sendEmptyMessage(4);
                return;
            } else {
                LogUtil.b("WeChatConnectActivity", ParamConstants.CallbackMethod.ON_FAIL);
                return;
            }
        }
        this.j = (shy) obj;
        if (z) {
            this.d.sendEmptyMessage(2);
        } else {
            this.d.sendEmptyMessage(1);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("WeChatConnectActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (System.currentTimeMillis() - this.i < 500) {
            LogUtil.h("WeChatConnectActivity", "onClick The interval is too short ");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.weChat_qrCode_save) {
            if (this.j == null) {
                LogUtil.h("WeChatConnectActivity", "mQrCodeData is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            PermissionUtil.b(this, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this) { // from class: com.huawei.ui.thirdpartservice.activity.wechat.WeChatConnectActivity.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    WeChatConnectActivity.this.c(true);
                }
            });
        } else if (id == R.id.weChat_faq) {
            this.m.jumpWechatHelp(this);
        } else {
            LogUtil.h("WeChatConnectActivity", "onClick view id mismatch");
        }
        this.i = System.currentTimeMillis();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).saveToLocalGallery(getApplicationContext(), dXs_(dXt_()));
    }

    private View dXt_() {
        View inflate = View.inflate(this, R.layout.wechat_save_layout, null);
        ((ImageView) inflate.findViewById(R.id.save_qrCode_img)).setImageDrawable(this.h.getDrawable());
        d(this.j, (HealthTextView) inflate.findViewById(R.id.save_qrCode_valid_time));
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.save_weChat_qrCode_guide_note1);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.save_weChat_qrCode_guide_note2);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.save_weChat_qrCode_guide_note3);
        healthTextView.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130844182_res_0x7f021a16), UnitUtil.e(1.0d, 1, 0), nsn.i(BaseApplication.getContext())));
        healthTextView2.setText(String.format(Locale.ENGLISH, getString(R.string._2130844149_res_0x7f0219f5), 2));
        healthTextView3.setText(String.format(Locale.ENGLISH, getString(R.string._2130843434_res_0x7f02172a), 3));
        return inflate;
    }

    private Bitmap dXs_(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            canvas.restore();
            return bitmap;
        } catch (IllegalArgumentException | IllegalStateException | OutOfMemoryError unused) {
            LogUtil.b("WeChatConnectActivity", "createBitmap failed!");
            return bitmap;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(268435456);
        intent.setComponent(componentName);
        try {
            nsn.cLM_(intent, "com.tencent.mm", this, getString(R.string._2130844962_res_0x7f021d22));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WeChatConnectActivity", "shareMomentMultiImage err: ActivityNotFoundException");
        }
    }

    static class b extends BaseHandler<WeChatConnectActivity> {
        b(WeChatConnectActivity weChatConnectActivity) {
            super(weChatConnectActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dXu_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeChatConnectActivity weChatConnectActivity, Message message) {
            LogUtil.c("WeChatConnectActivity", "handleMessage msg is ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                weChatConnectActivity.b(weChatConnectActivity.j);
                weChatConnectActivity.g.setVisibility(8);
                return;
            }
            if (i == 2) {
                weChatConnectActivity.b(weChatConnectActivity.j);
                weChatConnectActivity.c();
                weChatConnectActivity.b();
                weChatConnectActivity.e = true;
                return;
            }
            if (i == 3) {
                KeyValDbManager.b(BaseApplication.getContext()).d("third_part_service_we_chat_status", "true", null);
                weChatConnectActivity.startActivity(new Intent(weChatConnectActivity, (Class<?>) WeChatHealthActivity.class));
                weChatConnectActivity.finish();
            } else if (i == 4) {
                weChatConnectActivity.m.showDeviceAlreadyBinded(weChatConnectActivity);
            } else {
                LogUtil.h("WeChatConnectActivity", "handle message mismatch");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
