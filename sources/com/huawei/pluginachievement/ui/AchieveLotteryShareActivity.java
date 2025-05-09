package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.model.UserInfomation;
import defpackage.mcx;
import defpackage.mfc;
import defpackage.mfg;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mlg;
import defpackage.nrf;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes9.dex */
public class AchieveLotteryShareActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8391a;
    private HandlerThread b;
    private Context c;
    private String d;
    private Handler e;
    private HealthTextView f;
    private ImageView g;
    private Bitmap h;
    private String i;
    private ImageView j;
    private HealthTextView l;
    private HealthTextView o;

    private void c() {
        LogUtil.a("PLGACHIEVE_AchieveLotteryShareActivity", "getData()");
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        setContentView(R.layout.achieve_lottery_share);
        HandlerThread handlerThread = new HandlerThread("handlerThread");
        this.b = handlerThread;
        handlerThread.start();
        this.e = new a(this.b.getLooper(), this);
        e();
        c();
    }

    static class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<AchieveLotteryShareActivity> f8393a;

        a(Looper looper, AchieveLotteryShareActivity achieveLotteryShareActivity) {
            super(looper);
            this.f8393a = new WeakReference<>(achieveLotteryShareActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            AchieveLotteryShareActivity achieveLotteryShareActivity = this.f8393a.get();
            if (achieveLotteryShareActivity == null) {
                LogUtil.a("PLGACHIEVE_AchieveLotteryShareActivity", "refrence of AchieveLotteryShareAcitiviry is null!");
                return;
            }
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof UserInfomation) {
                    achieveLotteryShareActivity.e((UserInfomation) message.obj);
                    return;
                } else {
                    LogUtil.h("PLGACHIEVE_AchieveLotteryShareActivity", "MSG_GET_USER_INFO_SUCCESS is not UserInfomation!");
                    return;
                }
            }
            if (i == 2) {
                LogUtil.h("PLGACHIEVE_AchieveLotteryShareActivity", "MSG_GET_USER_INFO_FAIL");
                return;
            }
            if (i == 1001) {
                Bitmap cgJ_ = mfp.cgJ_(achieveLotteryShareActivity.findViewById(R.id.share_rlayout));
                if (cgJ_ != null) {
                    mcx.cfN_(achieveLotteryShareActivity.c, cgJ_, AnalyticsValue.SUCCESSES_SHARE_1100015.value(), null);
                    return;
                }
                return;
            }
            LogUtil.a("PLGACHIEVE_AchieveLotteryShareActivity", "handleMessageWhenReferenceNotNull msg.what == ", Integer.valueOf(message.what));
        }
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            this.i = intent.getStringExtra("imgUrl");
            this.d = intent.getStringExtra("awardName");
        }
        if (this.i == null) {
            this.i = "";
        }
        if (this.d == null) {
            this.d = "";
        }
        LogUtil.c("PLGACHIEVE_AchieveLotteryShareActivity", "init mImgUrl=", this.i, " awardName=", this.d);
        this.o = (HealthTextView) mfm.cgL_(this, R.id.lottery_nick);
        this.l = (HealthTextView) mfm.cgL_(this, R.id.lottery_text_prize_name);
        this.g = (ImageView) mfm.cgL_(this, R.id.lottery_head);
        this.j = (ImageView) mfm.cgL_(this, R.id.lottery_img_prize);
        ((CustomTitleBar) mfm.cgL_(this, R.id.lottery_title)).setTitleText(mfg.a(this));
        this.f8391a = (HealthTextView) mfm.cgL_(this, R.id.lottery_text_title);
        this.f = (HealthTextView) mfm.cgL_(this, R.id.lottery_share_content);
        this.f8391a.setText(String.format(Locale.ROOT, getString(R.string._2130840735_res_0x7f020c9f), mfg.a(this)));
        this.f.setText(String.format(Locale.ROOT, getString(R.string._2130840736_res_0x7f020ca0), mfg.a(this)));
        b();
    }

    private void b() {
        this.l.setText(this.d);
        if (TextUtils.isEmpty(this.i) || TextUtils.isEmpty(this.i.trim())) {
            LogUtil.h("PLGACHIEVE_AchieveLotteryShareActivity", "mImgUrl invalid");
        } else {
            nrf.d(this.i, new CustomTarget<Drawable>() { // from class: com.huawei.pluginachievement.ui.AchieveLotteryShareActivity.5
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: chj_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    AchieveLotteryShareActivity.this.j.setImageDrawable(drawable);
                    AchieveLotteryShareActivity.this.a();
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    super.onLoadFailed(drawable);
                    AchieveLotteryShareActivity.this.d();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (!mcx.d(this.c)) {
            mlg.e(this.c);
        } else {
            final View findViewById = findViewById(R.id.share_rlayout);
            findViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.pluginachievement.ui.AchieveLotteryShareActivity.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    AchieveLotteryShareActivity.this.e.sendMessageDelayed(AchieveLotteryShareActivity.this.e.obtainMessage(1001), 500L);
                    findViewById.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HandlerThread handlerThread = this.b;
        if (handlerThread != null) {
            handlerThread.getLooper().quit();
        }
        Bitmap bitmap = this.h;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.h.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: com.huawei.pluginachievement.ui.AchieveLotteryShareActivity.4
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, UserInfomation userInfomation) {
                if (i != 0) {
                    AchieveLotteryShareActivity.this.e.sendEmptyMessage(2);
                    return;
                }
                if (userInfomation == null) {
                    LogUtil.h("PLGACHIEVE_AchieveLotteryShareActivity", "get userinfo success but obtain null objData");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.obj = userInfomation;
                obtain.what = 1;
                AchieveLotteryShareActivity.this.e.sendMessage(obtain);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.a("PLGACHIEVE_AchieveLotteryShareActivity", "userInfomation is null");
        } else {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveLotteryShareActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    String picPath = userInfomation.getPicPath();
                    AchieveLotteryShareActivity.this.o.setText(userInfomation.getName());
                    if (!TextUtils.isEmpty(picPath)) {
                        AchieveLotteryShareActivity achieveLotteryShareActivity = AchieveLotteryShareActivity.this;
                        achieveLotteryShareActivity.h = mfc.cgv_(achieveLotteryShareActivity, picPath);
                        if (AchieveLotteryShareActivity.this.h != null) {
                            AchieveLotteryShareActivity.this.g.setImageBitmap(AchieveLotteryShareActivity.this.h);
                            return;
                        }
                    }
                    mcx.cfM_(userInfomation.getPortraitUrl(), AchieveLotteryShareActivity.this.g);
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
