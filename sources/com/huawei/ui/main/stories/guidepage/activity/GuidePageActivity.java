package com.huawei.ui.main.stories.guidepage.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Utils;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.guidepage.data.GuideResource;
import com.huawei.ui.main.stories.guidepage.views.PlayVideo;
import defpackage.jdw;
import defpackage.koq;
import defpackage.moh;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nrp;
import defpackage.pfh;
import defpackage.qbd;
import defpackage.qbg;
import defpackage.qbh;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class GuidePageActivity extends BaseActivity {
    private HealthDotsPageIndicator b;
    private HealthViewPager c;
    private Context e;
    private qbh f;
    private List<View> o = new ArrayList(6);
    private List<GuideResource> d = new ArrayList(6);
    private int j = -1;
    private int h = 0;
    private boolean g = true;
    private Map<Integer, PlayVideo> i = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, Bitmap> f10006a = new HashMap();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        getWindow().setFlags(1024, 1024);
        BaseActivity.setNavigationBarVisibility(this, 8);
        getWindow().addFlags(AppRouterExtras.COLDSTART);
        getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        setContentView(R.layout.activity_guide_page);
        this.e = this;
        e();
        c();
    }

    private void e() {
        this.c = (HealthViewPager) findViewById(R.id.guide_view_pager);
    }

    private void c() {
        LogUtil.a("GuidePageActivity", "enter GuidePageActivity initData");
        if (getIntent() == null) {
            finish();
            return;
        }
        List<GuideResource> a2 = qbg.a();
        this.d = a2;
        if (koq.b(a2)) {
            LogUtil.a("GuidePageActivity", "initData mGuideResources is Empty");
            finish();
            return;
        }
        for (int i = 0; i < this.d.size(); i++) {
            this.o.add(dyn_(i));
        }
        this.f = new qbh(this.o);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.guide_view_pager);
        this.c = healthViewPager;
        healthViewPager.setDynamicSpringAnimaitionEnabled(false);
        this.c.setAdapter(this.f);
        this.c.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                GuidePageActivity.this.h = i2;
                GuidePageActivity.this.a(i2);
            }
        });
        this.b = (HealthDotsPageIndicator) findViewById(R.id.guide_dots);
        if (this.d.size() == 1) {
            this.b.setVisibility(8);
        }
        this.b.setViewPager(this.c);
        a(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("GuidePageActivity", "setSelect:", Integer.valueOf(i));
        if (this.i.containsKey(Integer.valueOf(this.j)) && this.i.get(Integer.valueOf(this.j)) != null) {
            LogUtil.a("GuidePageActivity", "mHistoryPositon:", Integer.valueOf(this.j));
            this.i.get(Integer.valueOf(this.j)).pause();
            if (this.f10006a.containsKey(Integer.valueOf(this.j)) && this.f10006a.get(Integer.valueOf(this.j)) != null) {
                this.i.get(Integer.valueOf(this.j)).setBackground(new BitmapDrawable((Resources) null, this.f10006a.get(Integer.valueOf(this.j))));
            }
        }
        if (this.i.containsKey(Integer.valueOf(i)) && this.i.get(Integer.valueOf(i)) != null) {
            e(this.d.get(i).getResourceUrl(), this.i.get(Integer.valueOf(i)), i);
        }
        this.j = i;
    }

    private void e(String str, final PlayVideo playVideo, int i) {
        LogUtil.a("GuidePageActivity", "enter playVideo");
        String a2 = qbg.a(str);
        playVideo.setAudioFocusRequest(2);
        playVideo.setVideoPath(a2);
        playVideo.start();
        playVideo.requestFocus();
        playVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity.4
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                LogUtil.a("GuidePageActivity", "onCompletion");
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        playVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity.3
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity.3.2
                    @Override // android.media.MediaPlayer.OnInfoListener
                    public boolean onInfo(MediaPlayer mediaPlayer2, int i2, int i3) {
                        LogUtil.a("GuidePageActivity", "setOnPreparedListener:", "what:", Integer.valueOf(i2));
                        if (i2 != 3) {
                            return true;
                        }
                        playVideo.setBackgroundColor(0);
                        return true;
                    }
                });
            }
        });
        playVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity.1
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                LogUtil.b("GuidePageActivity", "what:", Integer.valueOf(i2), " extra:", Integer.valueOf(i3));
                return true;
            }
        });
    }

    private void c(PlayVideo playVideo, String str, int i) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(str);
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(0L, 2);
                this.f10006a.put(Integer.valueOf(i), frameAtTime);
                playVideo.setBackground(new BitmapDrawable((Resources) null, frameAtTime));
            } catch (IllegalArgumentException unused) {
                LogUtil.b("GuidePageActivity", "setBackground IllegalArgumentException");
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException e) {
                    LogUtil.b("GuidePageActivity", "setBackground release exception:", ExceptionUtils.d(e));
                }
            }
        } finally {
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e2) {
                LogUtil.b("GuidePageActivity", "setBackground release exception:", ExceptionUtils.d(e2));
            }
        }
    }

    private View dyn_(int i) {
        View inflate = View.inflate(this.e, R.layout.fragment_guide_page, null);
        LogUtil.a("GuidePageActivity", "enter buildViewLayout");
        GuideResource guideResource = this.d.get(i);
        if (guideResource == null) {
            LogUtil.h("GuidePageActivity", "guideResource = null");
            return inflate;
        }
        int resourceType = guideResource.getResourceType();
        LogUtil.a("GuidePageActivity", "type:", Integer.valueOf(resourceType));
        if (resourceType == 1) {
            PlayVideo playVideo = (PlayVideo) inflate.findViewById(R.id.guide_page_video);
            playVideo.setVisibility(0);
            this.i.put(Integer.valueOf(i), playVideo);
            c(playVideo, qbg.a(guideResource.getResourceUrl()), i);
        } else if (resourceType == 0 || resourceType == 2) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.guide_page_image);
            imageView.setVisibility(0);
            dyp_(qbg.a(guideResource.getResourceUrl()), imageView);
        } else {
            LogUtil.h("GuidePageActivity", "type not match");
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.guide_page_skip);
        if (guideResource.getSkip() == 0) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    GuidePageActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        dym_(inflate, guideResource);
        return inflate;
    }

    private void dym_(View view, GuideResource guideResource) {
        if (guideResource.getDisplay() == 1) {
            HealthButton healthButton = (HealthButton) view.findViewById(R.id.guide_page_experience);
            healthButton.setVisibility(0);
            healthButton.setText(guideResource.getButtonName());
            String targetUrl = guideResource.getTargetUrl();
            boolean isNotSupportBrowseUrl = TextUtils.isEmpty(targetUrl) ? false : Utils.isNotSupportBrowseUrl(targetUrl);
            BaseActivity d = pfh.d();
            if (d != null) {
                healthButton.setOnClickListener(nkx.cwZ_(dyo_(targetUrl), d, isNotSupportBrowseUrl, ""));
            } else {
                healthButton.setOnClickListener(dyo_(targetUrl));
            }
        }
    }

    private View.OnClickListener dyo_(final String str) {
        return new View.OnClickListener() { // from class: com.huawei.ui.main.stories.guidepage.activity.GuidePageActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GuidePageActivity.this.d(str);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    private void dyp_(String str, ImageView imageView) {
        LogUtil.a("GuidePageActivity", "loadImage");
        File file = new File(str);
        if (file.exists()) {
            RequestOptions placeholder = new RequestOptions().skipMemoryCache(true).transform(new nrp(imageView.getContext().getApplicationContext(), 0)).placeholder(R.drawable._2131427608_res_0x7f0b0118);
            nrf.e(placeholder, R.drawable._2131427608_res_0x7f0b0118);
            nrf.cIt_(file, placeholder, imageView);
            return;
        }
        LogUtil.h("GuidePageActivity", "loadImage fail file not exist");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("GuidePageActivity", "startDetailActivity url is empty");
            finish();
            return;
        }
        if (str.startsWith("huaweischeme")) {
            LogUtil.a("GuidePageActivity", "startDetailActivity HUAWEI_SCHEME");
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent.setPackage(this.e.getPackageName());
            jdw.bGh_(intent, this.e);
        } else {
            LogUtil.a("GuidePageActivity", "startDetailActivity url");
            Intent intent2 = new Intent(this.e, (Class<?>) WebViewActivity.class);
            intent2.setFlags(268435456);
            intent2.putExtra("url", str);
            this.e.startActivity(intent2);
        }
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!this.g && this.i.containsKey(Integer.valueOf(this.h)) && this.i.get(Integer.valueOf(this.h)) != null) {
            e(this.d.get(this.h).getResourceUrl(), this.i.get(Integer.valueOf(this.h)), this.h);
        }
        this.g = false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (!this.i.containsKey(Integer.valueOf(this.h)) || this.i.get(Integer.valueOf(this.h)) == null) {
            return;
        }
        this.i.get(Integer.valueOf(this.h)).pause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        d();
        b();
    }

    private void d() {
        for (Map.Entry<Integer, PlayVideo> entry : this.i.entrySet()) {
            if (entry instanceof Map.Entry) {
                Map.Entry<Integer, PlayVideo> entry2 = entry;
                if (entry2.getValue() instanceof PlayVideo) {
                    PlayVideo value = entry2.getValue();
                    if (value == null) {
                        LogUtil.h("GuidePageActivity", "playVideo = null");
                        return;
                    } else {
                        value.stopPlayback();
                        value.destroyDrawingCache();
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private void b() {
        qbg.c("", "");
        qbd.a("");
        try {
            LogUtil.a("GuidePageActivity", "isClearSuccess:", Boolean.valueOf(moh.b(BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "guidevideo")));
        } catch (IOException unused) {
            LogUtil.b("GuidePageActivity", "clear IOException");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("GuidePageActivity", "onBackPressed");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
