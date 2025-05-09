package com.huawei.health.suggestion.videoplayer;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.view.BrightnessOrVolumeProgressPlus;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.fsg;
import defpackage.gii;
import defpackage.gik;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes8.dex */
public class StandardIngotsVideoPlayer extends IngotsVideoPlayer {
    private static Timer b;

    /* renamed from: a, reason: collision with root package name */
    private BrightnessOrVolumeProgressPlus f3443a;
    private Context c;
    private b d;
    private ImageView e;
    private fsg f;
    private ImageView g;
    private HealthProgressBar h;
    private HealthTextView i;

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public int getLayoutId() {
        return R.layout.layout_ingots_player_standard;
    }

    public StandardIngotsVideoPlayer(Context context) {
        super(context);
        this.c = context;
    }

    public StandardIngotsVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void init(Context context) {
        super.init(context);
        this.e = (ImageView) findViewById(R.id.back);
        this.i = (HealthTextView) findViewById(R.id.title);
        this.h = (HealthProgressBar) findViewById(R.id.loading);
        this.g = (ImageView) findViewById(R.id.back_tiny);
        this.e.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.f3443a = (BrightnessOrVolumeProgressPlus) findViewById(R.id.bright_volume_setting);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.sug_progress_layout);
        fsg fsgVar = new fsg();
        this.f = fsgVar;
        relativeLayout.addView(fsgVar.aFH_());
        this.f.b();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void setPlayerParams(gik gikVar) {
        super.setPlayerParams(gikVar);
        m();
        if (isTmpTestBack()) {
            setTmpTestBack(false);
            gii.b().e(this);
            backPress();
        }
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onVideoViewChange(String str) {
        super.onVideoViewChange(str);
        m();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.i.setText(str);
    }

    private void m() {
        ImageView fullscreenButton = getFullscreenButton();
        if (getCurrentScreen() == 2) {
            fullscreenButton.setImageResource(R$drawable.ic_public_zoom_out);
            this.e.setVisibility(0);
            this.i.setVisibility(0);
            this.g.setVisibility(4);
            b((int) getResources().getDimension(R.dimen._2131363042_res_0x7f0a04e2));
            d(getResources().getDimension(R.dimen._2131362954_res_0x7f0a048a));
            c((int) getResources().getDimension(R.dimen._2131362973_res_0x7f0a049d));
            a((int) getResources().getDimension(R.dimen._2131363039_res_0x7f0a04df));
            return;
        }
        if (getCurrentScreen() == 0) {
            fullscreenButton.setImageResource(R$drawable.ic_public_full_screen);
            this.e.setVisibility(8);
            this.i.setVisibility(4);
            this.g.setVisibility(4);
            b((int) getResources().getDimension(R.dimen._2131362973_res_0x7f0a049d));
            d(getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446));
            c((int) getResources().getDimension(R.dimen._2131362922_res_0x7f0a046a));
            a((int) getResources().getDimension(R.dimen._2131362944_res_0x7f0a0480));
            return;
        }
        LogUtil.h("Ingots_StandardIngotsVideoPlayer", "switchViewSize() mCurrentScreen", Integer.valueOf(getCurrentScreen()));
    }

    private void b(int i) {
        ViewGroup.LayoutParams layoutParams = getStartButton().getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i;
    }

    private void d(float f) {
        getCurrentTimeTextView().setTextSize(0, f);
    }

    private void c(int i) {
        ViewGroup.LayoutParams layoutParams = getFullscreenButton().getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i;
    }

    private void a(int i) {
        getProgressBar().getLayoutParams().height = i;
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onStateNormal() {
        super.onStateNormal();
        e();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onStatePreparing() {
        super.onStatePreparing();
        g();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onStatePreparingChangingUrl(int i, long j) {
        super.onStatePreparingChangingUrl(i, j);
        this.h.setVisibility(0);
        getStartButton().setVisibility(4);
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onStatePlaying() {
        super.onStatePlaying();
        i();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onStatePause() {
        super.onStatePause();
        a();
        d();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onStateError() {
        super.onStateError();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        b();
        d();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() != R.id.surface_container) {
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "onTouch() action", Integer.valueOf(motionEvent.getAction()));
            return super.onTouch(view, motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            l();
            if (!isChangePosition() && !isChangeVolume()) {
                onEvent(102);
                f();
                m();
            }
        }
        return super.onTouch(view, motionEvent);
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view == null) {
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "onClick() view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.surface_container) {
            l();
        }
        if (id == R.id.back) {
            backPress();
        }
        if (id == R.id.back_tiny) {
            o();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void o() {
        if (gii.b().c().getCurrentScreen() == 1) {
            quitFullscreenOrTinyWindow();
        } else {
            backPress();
        }
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        d();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        if (getCurrentState() == 3) {
            h();
        } else {
            l();
        }
    }

    private void f() {
        int currentState = getCurrentState();
        if (currentState == 1) {
            g();
            return;
        }
        if (currentState == 3) {
            if (getBottomContainer().getVisibility() == 0) {
                i();
                return;
            } else {
                j();
                return;
            }
        }
        if (currentState == 5) {
            if (getBottomContainer().getVisibility() == 0) {
                c();
                return;
            } else {
                a();
                return;
            }
        }
        LogUtil.h("Ingots_StandardIngotsVideoPlayer", "onClickUiToggle() mCurrentState:", Integer.valueOf(getCurrentState()));
    }

    private void n() {
        int currentState = getCurrentState();
        if (currentState == 1) {
            if (getBottomContainer().getVisibility() == 0) {
                g();
                return;
            }
            return;
        }
        if (currentState == 3) {
            if (getBottomContainer().getVisibility() == 0) {
                i();
            }
        } else if (currentState == 5) {
            if (getBottomContainer().getVisibility() == 0) {
                c();
            }
        } else {
            if (currentState == 6) {
                if (getBottomContainer().getVisibility() == 0) {
                    b();
                    return;
                }
                return;
            }
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "onCLickUiToggleToClear() mCurrentState:", Integer.valueOf(getCurrentState()));
        }
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void setProgressAndText(int i, long j, long j2) {
        super.setProgressAndText(i, j, j2);
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void setBufferProgress(int i) {
        super.setBufferProgress(i);
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void resetProgressAndTime() {
        super.resetProgressAndTime();
    }

    private void e() {
        int currentScreen = getCurrentScreen();
        if (currentScreen == 0) {
            c(0, 4, 0);
            c(4, 4);
            k();
        } else {
            if (currentScreen == 2) {
                c(0, 4, 0);
                c(4, 0);
                k();
                return;
            }
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "changeUiToNormal() mCurrentScreen:", Integer.valueOf(getCurrentScreen()));
        }
    }

    private void g() {
        int currentScreen = getCurrentScreen();
        if (currentScreen == 0) {
            c(4, 4, 4);
            c(0, 4);
            k();
        } else {
            if (currentScreen == 2) {
                c(4, 4, 4);
                c(0, 0);
                k();
                return;
            }
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "changeUiToPreparing() mCurrentScreen:", Integer.valueOf(getCurrentScreen()));
        }
    }

    private void j() {
        int currentScreen = getCurrentScreen();
        if (currentScreen == 0) {
            c(0, 0, 0);
            c(4, 4);
            k();
        } else {
            if (currentScreen == 2) {
                c(0, 0, 0);
                c(4, 0);
                k();
                return;
            }
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "changeUiToPlayingShow() mCurrentScreen:", Integer.valueOf(getCurrentScreen()));
        }
    }

    private void i() {
        int currentScreen = getCurrentScreen();
        if (currentScreen == 0) {
            c(4, 4, 4);
            c(4, 4);
        } else if (currentScreen == 2) {
            c(4, 4, 4);
            c(4, 0);
        } else {
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "changeUiToPlayingClear() mCurrentScreen:", Integer.valueOf(getCurrentScreen()));
        }
    }

    private void a() {
        int currentScreen = getCurrentScreen();
        if (currentScreen == 0) {
            c(0, 0, 0);
            c(4, 4);
            k();
        } else {
            if (currentScreen == 2) {
                c(0, 0, 0);
                c(4, 0);
                k();
                return;
            }
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "changeUiToPauseShow() mCurrentScreen:", Integer.valueOf(getCurrentScreen()));
        }
    }

    private void c() {
        int currentScreen = getCurrentScreen();
        if (currentScreen == 0) {
            c(4, 4, 4);
            c(4, 4);
        } else if (currentScreen == 2) {
            c(4, 4, 4);
            c(4, 0);
        } else {
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "changeUiToPauseClear() mCurrentScreen:", Integer.valueOf(getCurrentScreen()));
        }
    }

    private void b() {
        int currentScreen = getCurrentScreen();
        if (currentScreen == 0) {
            c(0, 4, 0);
            c(4, 4);
            k();
        } else {
            if (currentScreen == 2) {
                c(0, 4, 0);
                c(4, 0);
                k();
                return;
            }
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "changeUiToComplete() mCurrentScreen:", Integer.valueOf(getCurrentScreen()));
        }
    }

    private void c(int i, int i2, int i3) {
        getTopContainer().setVisibility(i);
        getBottomContainer().setVisibility(i2);
        getStartButton().setVisibility(i3);
    }

    private void c(int i, int i2) {
        this.h.setVisibility(i);
        this.i.setVisibility(i2);
    }

    private void k() {
        if (getCurrentState() == 3) {
            getStartButton().setImageResource(R$drawable.sug_fitness_pause_selector);
        } else {
            getStartButton().setImageResource(R$drawable.sug_fitness_play_normal);
        }
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void showProgressDialog(float f, String str, long j, String str2, long j2) {
        super.showProgressDialog(f, str, j, str2, j2);
        this.f.c((int) j2);
        this.f.e((int) j);
        n();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
        this.f.b();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void showVolumeDialog(float f, int i) {
        super.showVolumeDialog(f, i);
        BrightnessOrVolumeProgressPlus brightnessOrVolumeProgressPlus = this.f3443a;
        if (brightnessOrVolumeProgressPlus == null) {
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "showVolumeDialog() mBrightOrVolumeProgressPlus is null");
            return;
        }
        if (brightnessOrVolumeProgressPlus.getVisibility() == 8) {
            this.f3443a.setVisibility(0);
        }
        this.f3443a.setProgressMax(f);
        if (i <= 0) {
            this.f3443a.a(R$drawable.ic_video_mute);
        } else {
            this.f3443a.a(R$drawable.ic_video_voice);
        }
        if (i >= 0) {
            this.f3443a.setProgress(i);
        }
        this.f3443a.d(getResources().getString(R$string.IDS_FitnessAdvice_volume));
        n();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void dismissVolumeDialog() {
        super.dismissVolumeDialog();
        BrightnessOrVolumeProgressPlus brightnessOrVolumeProgressPlus = this.f3443a;
        if (brightnessOrVolumeProgressPlus == null || brightnessOrVolumeProgressPlus.getVisibility() != 0) {
            return;
        }
        this.f3443a.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void showBrightnessDialog(int i) {
        super.showBrightnessDialog(i);
        BrightnessOrVolumeProgressPlus brightnessOrVolumeProgressPlus = this.f3443a;
        if (brightnessOrVolumeProgressPlus == null) {
            LogUtil.h("Ingots_StandardIngotsVideoPlayer", "showBrightnessDialog() mBrightOrVolumeProgressPlus is null");
            return;
        }
        if (brightnessOrVolumeProgressPlus.getVisibility() == 8) {
            this.f3443a.setVisibility(0);
        }
        this.f3443a.setProgressMax(100.0f);
        this.f3443a.setProgress(i);
        this.f3443a.a(R$drawable.ic_video_light);
        this.f3443a.d(this.c.getResources().getString(R$string.IDS_FitnessAdvice_brightness));
        n();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void dismissBrightnessDialog() {
        super.dismissBrightnessDialog();
        BrightnessOrVolumeProgressPlus brightnessOrVolumeProgressPlus = this.f3443a;
        if (brightnessOrVolumeProgressPlus == null || brightnessOrVolumeProgressPlus.getVisibility() != 0) {
            return;
        }
        this.f3443a.setVisibility(8);
    }

    private void l() {
        d();
        b = new Timer("Ingots_StandardIngotsVideoPlayer");
        b bVar = new b();
        this.d = bVar;
        b.schedule(bVar, 7000L);
    }

    private void d() {
        Timer timer = b;
        if (timer != null) {
            timer.cancel();
        }
        b bVar = this.d;
        if (bVar != null) {
            bVar.cancel();
        }
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onAutoCompletion() {
        super.onAutoCompletion();
        d();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer
    public void onCompletion() {
        super.onCompletion();
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (getCurrentState() == 0 || getCurrentState() == 7 || getCurrentState() == 6) {
            return;
        }
        post(new Runnable() { // from class: com.huawei.health.suggestion.videoplayer.StandardIngotsVideoPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                StandardIngotsVideoPlayer.this.getBottomContainer().setVisibility(4);
                StandardIngotsVideoPlayer.this.getTopContainer().setVisibility(4);
                StandardIngotsVideoPlayer.this.getStartButton().setVisibility(4);
            }
        });
    }

    public class b extends TimerTask {
        public b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            StandardIngotsVideoPlayer.this.h();
        }
    }
}
