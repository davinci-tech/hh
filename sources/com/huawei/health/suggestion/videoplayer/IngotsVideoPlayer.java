package com.huawei.health.suggestion.videoplayer;

import android.R;
import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.gih;
import defpackage.gii;
import defpackage.gik;
import defpackage.gil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes8.dex */
public abstract class IngotsVideoPlayer extends FrameLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private static final double BRIGHTNESS_MAX = 1.0d;
    private static final float BRIGHTNESS_MIN = 0.01f;
    public static final int CURRENT_STATE_AUTO_COMPLETE = 6;
    public static final int CURRENT_STATE_ERROR = 7;
    public static final int CURRENT_STATE_NORMAL = 0;
    public static final int CURRENT_STATE_PAUSE = 5;
    public static final int CURRENT_STATE_PLAYING = 3;
    public static final int CURRENT_STATE_PREPARING = 1;
    public static final int CURRENT_STATE_PREPARING_CHANGING_URL = 2;
    private static final String DATA_SOURCE_TAG_END = "/";
    private static final String DATA_SOURCE_TAG_FILE = "file";
    private static final int DEFAULT_NEGATIVE_ONE = -1;
    private static final int DEFAULT_THREE = 3;
    private static final int DEFAULT_ZERO = 0;
    private static final int FULLSCREEN_ORIENTATION = 0;
    private static final int FULL_SCREEN_NORMAL_DELAY = 300;
    private static final int PROGRESS_DEFAULT_HUNDRED = 100;
    private static final int QUIT_TIME_THRESHOLD = 2000;
    private static final float SCREEN_HALF = 0.5f;
    public static final int SCREEN_WINDOW_FULLSCREEN = 2;
    public static final int SCREEN_WINDOW_LIST = 1;
    public static final int SCREEN_WINDOW_NORMAL = 0;
    public static final int SCREEN_WINDOW_TINY = 3;
    private static final int SENSOR_X_NEGATIVE_THRESHOLD = -12;
    private static final int SENSOR_X_THRESHOLD = 12;
    private static final String TAG = "Ingots_IngotsVideoPlayer";
    private static final int THRESHOLD = 80;
    private static final int TINY_HEIGHT = 400;
    private static final int TINY_WIDTH = 400;
    private static final float TOTAL_COLOR_NUMBER = 255.0f;
    public static final String URL_KEY_DEFAULT = "URL_KEY_DEFAULT";
    private static final int VIDEO_ERROR_CODE_1 = 38;
    private static final int VIDEO_ERROR_CODE_2 = -38;
    private static final int VIDEO_ERROR_CODE_3 = -19;
    public static final int VIDEO_IMAGE_DISPLAY_TYPE_FILL_CROP = 2;
    public static final int VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT = 1;
    public static final int VIDEO_IMAGE_DISPLAY_TYPE_ORIGINAL = 3;
    private static AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer.5
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (i != -2) {
                if (i == -1) {
                    IngotsVideoPlayer.releaseAllVideos();
                    return;
                } else {
                    if (i != 1) {
                        LogUtil.h(IngotsVideoPlayer.TAG, "onAudioFocusChange default");
                        return;
                    }
                    return;
                }
            }
            try {
                IngotsVideoPlayer a2 = gii.b().a();
                if (a2 == null || a2.mCurrentState != 3) {
                    return;
                }
                a2.mStartButton.performClick();
            } catch (IllegalStateException unused) {
                LogUtil.b(IngotsVideoPlayer.TAG, "onAudioFocusChange AUDIO_FOCUS_LOSS_TRANSIENT IllegalStateException");
            }
        }
    };
    private static long sClickQuitFullScreenTime = 0;
    private static IngotsPlayerActionCallback sIngotsPlayerActionCallbackEvent = null;
    private static boolean sIsActionBarExist = true;
    private static boolean sIsToolBarExist = true;
    private static long sLastAutoFullscreenTime;
    private static Timer sUpdateProgressTimer;
    private static int sVideoImageDisplayType;
    private boolean isTouchingProgressBar;
    private AudioManager mAudioManager;
    private ViewGroup mBottomContainer;
    private int mCurrentScreen;
    private int mCurrentState;
    private HealthTextView mCurrentTimeTextView;
    private int mCurrentUrlMapIndex;
    private Map<String, String> mDataSourceObjects;
    private float mDownX;
    private float mDownY;
    private ImageView mFullscreenButton;
    private float mGestureDownBrightness;
    private long mGestureDownPosition;
    private int mGestureDownVolume;
    private int mHeightRatio;
    private boolean mIsChangeBrightness;
    private boolean mIsChangePosition;
    private boolean mIsChangeVolume;
    private boolean mIsLoop;
    private boolean mIsTmpTestBack;
    private int mNormalOrientation;
    private int mPositionInList;
    private HealthSeekBar mProgressBar;
    private e mProgressTimerTask;
    private int mScreenHeight;
    private int mScreenWidth;
    private long mSeekTimePosition;
    private long mSeekToInAdvance;
    private ImageView mStartButton;
    private ViewGroup mTextureViewContainer;
    private ViewGroup mTopContainer;
    private HealthTextView mTotalTimeTextView;
    private String mVideoName;
    private int mVideoRotation;
    private int mWidthRatio;

    public void dismissBrightnessDialog() {
    }

    public void dismissProgressDialog() {
    }

    public void dismissVolumeDialog() {
    }

    public abstract int getLayoutId();

    public void onSeekComplete() {
    }

    public void onVideoViewChange(String str) {
    }

    public void showBrightnessDialog(int i) {
    }

    public void showProgressDialog(float f, String str, long j, String str2, long j2) {
    }

    public void showVolumeDialog(float f, int i) {
    }

    public IngotsVideoPlayer(Context context) {
        super(context);
        this.mCurrentState = -1;
        this.mCurrentScreen = -1;
        this.mIsLoop = false;
        this.mNormalOrientation = 1;
        this.mVideoName = "";
        this.mSeekToInAdvance = 0L;
        this.mWidthRatio = 0;
        this.mHeightRatio = 0;
        this.mCurrentUrlMapIndex = 0;
        this.mPositionInList = -1;
        this.mVideoRotation = 0;
        this.mIsTmpTestBack = false;
        init(context);
    }

    public IngotsVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentState = -1;
        this.mCurrentScreen = -1;
        this.mIsLoop = false;
        this.mNormalOrientation = 1;
        this.mVideoName = "";
        this.mSeekToInAdvance = 0L;
        this.mWidthRatio = 0;
        this.mHeightRatio = 0;
        this.mCurrentUrlMapIndex = 0;
        this.mPositionInList = -1;
        this.mVideoRotation = 0;
        this.mIsTmpTestBack = false;
        init(context);
    }

    public static void releaseAllVideos() {
        if (System.currentTimeMillis() - sClickQuitFullScreenTime > 300) {
            gii.b().d();
            gih.e().e(-1);
            gih.e().n();
        }
    }

    private static void startFullscreen(Context context, Class cls, Map<String, String> map, int i, String str) {
        hideSupportActionBar(context);
        gil.e(context, 0);
        ViewGroup viewGroup = (ViewGroup) gil.aNe_(context).findViewById(R.id.content);
        View findViewById = viewGroup.findViewById(com.huawei.health.R.id.video_fullscreen_id);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        try {
            IngotsVideoPlayer ingotsVideoPlayer = (IngotsVideoPlayer) cls.getConstructor(Context.class).newInstance(context);
            ingotsVideoPlayer.setId(com.huawei.health.R.id.video_fullscreen_id);
            viewGroup.addView(ingotsVideoPlayer, new FrameLayout.LayoutParams(-1, -1));
            ingotsVideoPlayer.setUp(map, i, 2, str);
            sClickQuitFullScreenTime = System.currentTimeMillis();
            ingotsVideoPlayer.mStartButton.performClick();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.b(TAG, "startFullscreen() InstantiationException");
        }
    }

    public static boolean backPress() {
        LogUtil.a(TAG, "backPress action");
        if (System.currentTimeMillis() - sClickQuitFullScreenTime < 300) {
            LogUtil.h(TAG, "backPress() quit time no allow");
            return false;
        }
        if (gii.b().e() != null) {
            sClickQuitFullScreenTime = System.currentTimeMillis();
            if (gil.b(gii.b().c().mDataSourceObjects, gih.c())) {
                IngotsVideoPlayer e2 = gii.b().e();
                e2.onEvent(e2.mCurrentScreen == 2 ? 8 : 10);
                gii.b().c().playOnThisVideo();
            } else {
                quitFullscreenOrTinyWindow();
            }
            return true;
        }
        if (gii.b().c() != null && (gii.b().c().mCurrentScreen == 2 || gii.b().c().mCurrentScreen == 3)) {
            sClickQuitFullScreenTime = System.currentTimeMillis();
            quitFullscreenOrTinyWindow();
            return true;
        }
        LogUtil.h(TAG, "backPress() quit no action");
        return false;
    }

    public static void quitFullscreenOrTinyWindow() {
        gii.b().c().clearFloatScreen();
        gih.e().n();
        gii.b().d();
    }

    public static void showSupportActionBar(Context context) {
        ActionBar supportActionBar;
        if (sIsActionBarExist && gil.d(context) != null && (supportActionBar = gil.d(context).getSupportActionBar()) != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
            supportActionBar.show();
        }
        if (gil.aNd_(context) == null) {
            LogUtil.h(TAG, "showSupportActionBar getWindow(context) is null.");
        } else if (sIsToolBarExist) {
            gil.aNd_(context).clearFlags(1024);
        }
    }

    public static void hideSupportActionBar(Context context) {
        ActionBar supportActionBar;
        if (sIsActionBarExist && gil.d(context) != null && (supportActionBar = gil.d(context).getSupportActionBar()) != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
            supportActionBar.hide();
        }
        if (sIsToolBarExist) {
            if (gil.aNd_(context) == null) {
                LogUtil.h(TAG, "hideSupportActionBar getWindow(context) is null.");
            } else {
                gil.aNd_(context).setFlags(1024, 1024);
            }
        }
    }

    public static void clearSavedProgress(Context context, String str) {
        gil.c(context, str);
    }

    public static void setUserAction(IngotsPlayerActionCallback ingotsPlayerActionCallback) {
        sIngotsPlayerActionCallbackEvent = ingotsPlayerActionCallback;
    }

    public static void goOnPlayOnResume() {
        if (gii.b().a() == null) {
            LogUtil.h(TAG, "goOnPlayOnResume() getCurrentVideoPlayer is null");
            return;
        }
        IngotsVideoPlayer a2 = gii.b().a();
        if (a2.mCurrentState == 5) {
            a2.onStatePlaying();
            gih.i();
        }
    }

    public static void goOnPlayOnPause() {
        if (gii.b().a() != null) {
            IngotsVideoPlayer a2 = gii.b().a();
            int i = a2.mCurrentState;
            if (i == 6 || i == 0 || i == 7) {
                LogUtil.a(TAG, "goOnPlayOnPause() is no use");
            } else {
                a2.onStatePause();
                gih.b();
            }
        }
    }

    public static void onScrollAutoTiny(int i, int i2) {
        int j = gih.e().j();
        if (j >= 0) {
            scrollTiny(j, i, i2 + i);
        } else {
            if (gii.b().a() == null || gii.b().a().mCurrentScreen != 3) {
                return;
            }
            LogUtil.a(TAG, "onScrollAutoTiny() onScroll: into screen");
            backPress();
        }
    }

    private static void scrollTiny(int i, int i2, int i3) {
        if (i >= i2 && i <= i3 - 1) {
            LogUtil.h(TAG, "scrollTiny() currentPlayPosition is invalid");
            return;
        }
        if (gii.b().a() == null || gii.b().a().mCurrentScreen == 3 || gii.b().a().mCurrentScreen == 2) {
            LogUtil.h(TAG, "scrollTiny() statue type is invalid");
        } else if (gii.b().a().mCurrentState == 5) {
            releaseAllVideos();
        } else {
            gii.b().a().startWindowTiny();
        }
    }

    public static void onScrollReleaseAllVideos(int i, int i2) {
        int j = gih.e().j();
        if (j < 0) {
            LogUtil.h(TAG, "onScrollReleaseAllVideos() currentPlayPosition is invalid");
        } else if ((j < i || j > (i2 + i) - 1) && gii.b().a().mCurrentScreen != 2) {
            releaseAllVideos();
        }
    }

    private static void onChildViewAttachedToWindow(View view, int i) {
        IngotsVideoPlayer ingotsVideoPlayer;
        if (view == null) {
            LogUtil.h(TAG, "onChildViewAttachedToWindow() view is null");
        } else {
            if (gii.b().a() == null || gii.b().a().mCurrentScreen != 3 || (ingotsVideoPlayer = (IngotsVideoPlayer) view.findViewById(i)) == null || !gil.b(ingotsVideoPlayer.mDataSourceObjects, ingotsVideoPlayer.mCurrentUrlMapIndex).equals(gih.c())) {
                return;
            }
            backPress();
        }
    }

    private static void onChildViewDetachedFromWindow(View view) {
        if (view == null) {
            LogUtil.h(TAG, "onChildViewAttachedToWindow() view is null");
            return;
        }
        IngotsVideoPlayer a2 = gii.b().a();
        if (a2 == null || a2.mCurrentScreen == 3) {
            LogUtil.h(TAG, "onChildViewAttachedToWindow() ingotsVideoPlayer is null or mCurrentScreen is no SCREEN_WINDOW_TINY");
            return;
        }
        if (!(view instanceof ViewGroup)) {
            LogUtil.h(TAG, "onChildViewAttachedToWindow() view not instance of ViewGroup");
            return;
        }
        if (((ViewGroup) view).indexOfChild(a2) == -1) {
            LogUtil.h(TAG, "onChildViewAttachedToWindow() view not contains videoPlayer");
        } else if (a2.mCurrentState == 5) {
            releaseAllVideos();
        } else {
            a2.startWindowTiny();
        }
    }

    private static void setTextureViewRotation(int i) {
        if (gih.e().l() != null) {
            gih.e().l().setRotation(i);
        }
    }

    private static void setVideoImageDisplayType(int i) {
        sVideoImageDisplayType = i;
        if (gih.e().l() != null) {
            gih.e().l().requestLayout();
        }
    }

    public String getCurrentUrl() {
        return gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex);
    }

    public void init(Context context) {
        View.inflate(context, getLayoutId(), this);
        this.mStartButton = (ImageView) findViewById(com.huawei.health.R.id.start);
        this.mFullscreenButton = (ImageView) findViewById(com.huawei.health.R.id.fullscreen);
        this.mProgressBar = (HealthSeekBar) findViewById(com.huawei.health.R.id.bottom_seek_progress);
        this.mCurrentTimeTextView = (HealthTextView) findViewById(com.huawei.health.R.id.current);
        this.mTotalTimeTextView = (HealthTextView) findViewById(com.huawei.health.R.id.total);
        this.mBottomContainer = (ViewGroup) findViewById(com.huawei.health.R.id.layout_bottom);
        this.mTextureViewContainer = (ViewGroup) findViewById(com.huawei.health.R.id.surface_container);
        this.mTopContainer = (ViewGroup) findViewById(com.huawei.health.R.id.layout_top);
        this.mStartButton.setOnClickListener(this);
        this.mFullscreenButton.setOnClickListener(this);
        this.mProgressBar.setOnSeekBarChangeListener(this);
        this.mBottomContainer.setOnClickListener(this);
        this.mTextureViewContainer.setOnClickListener(this);
        this.mTextureViewContainer.setOnTouchListener(this);
        this.mScreenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        this.mScreenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        this.mAudioManager = (AudioManager) getContext().getSystemService(PresenterUtils.AUDIO);
        if (isCurrentPlay() && (context instanceof AppCompatActivity)) {
            this.mNormalOrientation = ((AppCompatActivity) context).getRequestedOrientation();
        }
    }

    public void setPlayerParams(gik gikVar) {
        if (gikVar == null) {
            LogUtil.h(TAG, "setPlayerParams() ingotsPlayerParam is null");
            return;
        }
        if (gikVar.j().booleanValue() && !gikVar.b().isEmpty()) {
            setUp(gikVar.b(), gikVar.c().intValue(), gikVar.a().intValue(), gikVar.d());
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(URL_KEY_DEFAULT, gikVar.e());
            setUp(linkedHashMap, 0, gikVar.a().intValue(), gikVar.d());
        }
        this.mIsLoop = gikVar.g().booleanValue();
    }

    private void setUp(Map<String, String> map, int i, int i2, String str) {
        long j;
        if (this.mDataSourceObjects != null && gil.b(map, this.mCurrentUrlMapIndex) != null && gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex).equals(gil.b(map, this.mCurrentUrlMapIndex))) {
            LogUtil.h(TAG, "setUp() params is inValid");
            return;
        }
        onVideoViewChange(str);
        if (isCurrentVideo() && gil.b(map, gih.c())) {
            try {
                j = gih.a();
            } catch (IllegalStateException unused) {
                LogUtil.b(TAG, "setUp() IllegalStateException");
                j = 0;
            }
            if (j != 0) {
                gil.e(getContext(), gih.c(), j);
            }
            gih.e().n();
        }
        if (isCurrentVideo() && !gil.b(map, gih.c())) {
            startWindowTiny();
        }
        if (!isCurrentVideo() && gil.b(map, gih.c()) && gii.b().a() != null && gii.b().a().mCurrentScreen == 3) {
            this.mIsTmpTestBack = true;
        }
        this.mDataSourceObjects = map;
        this.mCurrentUrlMapIndex = i;
        this.mCurrentScreen = i2;
        this.mVideoName = str;
        onStateNormal();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h(TAG, "onClick() view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == com.huawei.health.R.id.start) {
            startVideoState();
        }
        if (id == com.huawei.health.R.id.fullscreen) {
            if (this.mCurrentState == 6) {
                LogUtil.h(TAG, "onClick() fullscreen mCurrentState is CURRENT_STATE_AUTO_COMPLETE");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (this.mCurrentScreen == 2) {
                backPress();
            } else {
                onEvent(7);
                startWindowFullscreen();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void startVideoState() {
        Map<String, String> map = this.mDataSourceObjects;
        if (map == null || gil.b(map, this.mCurrentUrlMapIndex) == null) {
            LogUtil.h(TAG, "onClick() mCurrentState is CURRENT_STATE_NORMAL url is inValid");
            return;
        }
        int i = this.mCurrentState;
        if (i == 0) {
            if (!gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex).startsWith(DATA_SOURCE_TAG_FILE) && !gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex).startsWith("/")) {
                LogUtil.h(TAG, "onClick() mCurrentState is CURRENT_STATE_NORMAL url is inValid");
                return;
            } else {
                startVideo();
                onEvent(0);
                return;
            }
        }
        if (i == 3) {
            onEvent(3);
            gih.b();
            onStatePause();
        } else if (i == 5) {
            onEvent(4);
            gih.i();
            onStatePlaying();
        } else if (i == 6) {
            onEvent(2);
            startVideo();
        } else {
            LogUtil.h(TAG, "startVideoState() mCurrentState is ", Integer.valueOf(i));
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == null || motionEvent == null) {
            LogUtil.h(TAG, "onTouch() viewId params is invalid");
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (view.getId() != com.huawei.health.R.id.surface_container) {
            LogUtil.h(TAG, "onTouch() viewId is invalid");
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isTouchingProgressBar = true;
            this.mDownX = x;
            this.mDownY = y;
            this.mIsChangeVolume = false;
            this.mIsChangePosition = false;
            this.mIsChangeBrightness = false;
        } else if (action == 1) {
            this.isTouchingProgressBar = false;
            dismissProgressDialog();
            dismissVolumeDialog();
            dismissBrightnessDialog();
            if (this.mIsChangePosition) {
                onEvent(12);
                gih.a(this.mSeekTimePosition);
                long duration = getDuration();
                long j = this.mSeekTimePosition;
                if (duration == 0) {
                    duration = 1;
                }
                this.mProgressBar.setProgress((int) ((j * 100) / duration));
            }
            if (this.mIsChangeVolume) {
                onEvent(11);
            }
            startProgressTimer();
        } else if (action == 2) {
            touchActionMoveHandle(x, y);
        } else {
            LogUtil.h(TAG, "onTouch() MotionEvent is no use");
        }
        return false;
    }

    private void touchActionMoveHandle(float f, float f2) {
        float f3 = f - this.mDownX;
        float f4 = f2 - this.mDownY;
        float abs = Math.abs(f3);
        float abs2 = Math.abs(f4);
        if (this.mCurrentScreen == 2) {
            checkChange(abs, abs2);
        }
        changeSeekProgress(f3);
        changeBrightness(changeVolume(f4));
    }

    private void checkChange(float f, float f2) {
        if (this.mIsChangePosition || this.mIsChangeVolume || this.mIsChangeBrightness) {
            return;
        }
        checkAbsDelta(f, f2);
    }

    private void checkAbsDelta(float f, float f2) {
        if (f > 80.0f || f2 > 80.0f) {
            absDeltaInvalid(f);
        }
    }

    private void absDeltaInvalid(float f) {
        cancelProgressTimer();
        if (f >= 80.0f) {
            if (this.mCurrentState != 7) {
                this.mIsChangePosition = true;
                this.mGestureDownPosition = getCurrentPositionWhenPlaying();
                return;
            }
            return;
        }
        scrollActionHandle();
    }

    private void scrollActionHandle() {
        if (this.mDownX < this.mScreenHeight * 0.5f) {
            this.mIsChangeBrightness = true;
            if (gil.aNd_(getContext()) == null) {
                LogUtil.h(TAG, "scrollActionHandle() getWindow is null");
                return;
            }
            if (gil.aNd_(getContext()).getAttributes() == null) {
                LogUtil.h(TAG, "scrollActionHandle() getAttributes is null");
                return;
            }
            WindowManager.LayoutParams attributes = gil.aNd_(getContext()).getAttributes();
            if (attributes.screenBrightness < 0.0f) {
                try {
                    float f = Settings.System.getInt(getContext().getContentResolver(), "screen_brightness");
                    this.mGestureDownBrightness = f;
                    LogUtil.a(TAG, "scrollActionHandle() system brightness:", Float.valueOf(f));
                    return;
                } catch (Settings.SettingNotFoundException unused) {
                    LogUtil.a(TAG, "scrollActionHandle() SettingNotFoundException");
                    return;
                }
            }
            float f2 = attributes.screenBrightness * TOTAL_COLOR_NUMBER;
            this.mGestureDownBrightness = f2;
            LogUtil.a(TAG, "scrollActionHandle() activity brightness:", Float.valueOf(f2));
            return;
        }
        this.mIsChangeVolume = true;
        this.mGestureDownVolume = this.mAudioManager.getStreamVolume(3);
    }

    private void changeSeekProgress(float f) {
        if (this.mIsChangePosition) {
            long duration = getDuration();
            long j = (int) (this.mGestureDownPosition + ((duration * f) / this.mScreenWidth));
            this.mSeekTimePosition = j;
            if (j > duration) {
                this.mSeekTimePosition = duration;
            }
            String c = gil.c(this.mSeekTimePosition);
            String c2 = gil.c(duration);
            long j2 = this.mSeekTimePosition;
            if (j2 >= 0) {
                showProgressDialog(f, c, j2, c2, duration);
            }
        }
    }

    private void changeBrightness(float f) {
        if (!this.mIsChangeBrightness) {
            LogUtil.h(TAG, "changeBrightness() isChangeBrightness is false");
            return;
        }
        float f2 = -f;
        int i = (int) (((f2 * TOTAL_COLOR_NUMBER) * 3.0f) / this.mScreenHeight);
        if (gil.aNd_(getContext()) == null) {
            LogUtil.h(TAG, "changeBrightness() getWindow is null");
            return;
        }
        if (gil.aNd_(getContext()).getAttributes() == null) {
            LogUtil.h(TAG, "changeBrightness() getAttributes is null");
            return;
        }
        WindowManager.LayoutParams attributes = gil.aNd_(getContext()).getAttributes();
        float f3 = (this.mGestureDownBrightness + i) / TOTAL_COLOR_NUMBER;
        if (f3 >= 1.0d) {
            attributes.screenBrightness = 1.0f;
        } else if (f3 <= 0.0f) {
            attributes.screenBrightness = 0.01f;
        } else {
            attributes.screenBrightness = f3;
        }
        gil.aNd_(getContext()).setAttributes(attributes);
        int i2 = (int) (((this.mGestureDownBrightness * 100.0f) / TOTAL_COLOR_NUMBER) + (((f2 * 3.0f) * 100.0f) / this.mScreenHeight));
        if (i2 >= 0) {
            showBrightnessDialog(i2);
        }
    }

    private float changeVolume(float f) {
        if (!this.mIsChangeVolume) {
            return f;
        }
        float f2 = -f;
        this.mAudioManager.setStreamVolume(3, this.mGestureDownVolume + ((int) (((this.mAudioManager.getStreamMaxVolume(3) * f2) * 3.0f) / this.mScreenHeight)), 0);
        showVolumeDialog(100.0f, (int) (((this.mGestureDownVolume * 100) / r0) + (((3.0f * f2) * 100.0f) / this.mScreenHeight)));
        return f2;
    }

    public void startVideo() {
        gii.b().d();
        initTextureView();
        addTextureView();
        AudioManager audioManager = getContext().getSystemService(PresenterUtils.AUDIO) instanceof AudioManager ? (AudioManager) getContext().getSystemService(PresenterUtils.AUDIO) : null;
        if (audioManager == null) {
            LogUtil.h(TAG, "startVideo audioManager is null.");
            return;
        }
        audioManager.requestAudioFocus(onAudioFocusChangeListener, 3, 2);
        if (gil.aNe_(getContext()).getWindow() != null) {
            gil.aNe_(getContext()).getWindow().addFlags(128);
        }
        gih.c(this.mDataSourceObjects);
        gih.e(this.mIsLoop);
        gih.d(gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex));
        gih.e().e(this.mPositionInList);
        onStatePreparing();
        gii.b().e(this);
    }

    public void onPrepared() {
        onStatePrepared();
        onStatePlaying();
    }

    public void setState(int i) {
        setState(i, 0, 0);
    }

    private void setState(int i, int i2, int i3) {
        if (i == 0) {
            onStateNormal();
            return;
        }
        if (i == 1) {
            onStatePreparing();
            return;
        }
        if (i == 2) {
            onStatePreparingChangingUrl(i2, i3);
            return;
        }
        if (i == 3) {
            onStatePlaying();
            return;
        }
        if (i == 5) {
            onStatePause();
            return;
        }
        if (i == 6) {
            onStateAutoComplete();
        } else if (i == 7) {
            onStateError();
        } else {
            LogUtil.h(TAG, "setState() state", Integer.valueOf(i));
        }
    }

    public void onStateNormal() {
        this.mCurrentState = 0;
        cancelProgressTimer();
    }

    public void onStatePreparing() {
        this.mCurrentState = 1;
        resetProgressAndTime();
    }

    public void onStatePreparingChangingUrl(int i, long j) {
        this.mCurrentState = 2;
        this.mCurrentUrlMapIndex = i;
        this.mSeekToInAdvance = j;
        gih.c(this.mDataSourceObjects);
        gih.d(gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex));
        gih.e().m();
    }

    public void onStatePrepared() {
        long j = this.mSeekToInAdvance;
        if (j != 0) {
            gih.a(j);
            this.mSeekToInAdvance = 0L;
        } else {
            long e2 = gil.e(getContext(), gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex));
            if (e2 != 0) {
                gih.a(e2);
            }
        }
    }

    public void onStatePlaying() {
        this.mCurrentState = 3;
        startProgressTimer();
    }

    public void onStatePause() {
        this.mCurrentState = 5;
        startProgressTimer();
    }

    public void onStateError() {
        this.mCurrentState = 7;
        cancelProgressTimer();
    }

    public void onStateAutoComplete() {
        this.mCurrentState = 6;
        cancelProgressTimer();
        this.mProgressBar.setProgress(100);
        this.mCurrentTimeTextView.setText(this.mTotalTimeTextView.getText());
    }

    public void onError(int i, int i2) {
        if (i == 38 || i == -38) {
            LogUtil.h(TAG, "onError() what:", Integer.valueOf(i));
            return;
        }
        if (i2 == 38 || i2 == -38 || i2 == -19) {
            LogUtil.h(TAG, "onError() what:", Integer.valueOf(i2));
            return;
        }
        onStateError();
        if (isCurrentPlay()) {
            gih.e().n();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.mCurrentScreen;
        if (i3 == 2 || i3 == 3) {
            super.onMeasure(i, i2);
            LogUtil.h(TAG, "onMeasure() mCurrentScreen", Integer.valueOf(this.mCurrentScreen));
        } else {
            if (this.mWidthRatio != 0 && this.mHeightRatio != 0) {
                int size = View.MeasureSpec.getSize(i);
                int i4 = (int) ((size * this.mHeightRatio) / this.mWidthRatio);
                setMeasuredDimension(size, i4);
                getChildAt(0).measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
                return;
            }
            super.onMeasure(i, i2);
        }
    }

    public void onAutoCompletion() {
        onEvent(6);
        dismissVolumeDialog();
        dismissProgressDialog();
        dismissBrightnessDialog();
        onStateAutoComplete();
        int i = this.mCurrentScreen;
        if (i == 2 || i == 3) {
            backPress();
        }
        gih.e().n();
        gil.e(getContext(), gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex), 0L);
    }

    public void onCompletion() {
        int i = this.mCurrentState;
        if (i == 3 || i == 5) {
            gil.e(getContext(), gil.b(this.mDataSourceObjects, this.mCurrentUrlMapIndex), getCurrentPositionWhenPlaying());
        }
        cancelProgressTimer();
        dismissBrightnessDialog();
        dismissProgressDialog();
        dismissVolumeDialog();
        onStateNormal();
        this.mTextureViewContainer.removeView(gih.e().l());
        gih.e().a(0);
        gih.e().b(0);
        if (getContext().getSystemService(PresenterUtils.AUDIO) instanceof AudioManager) {
            ((AudioManager) getContext().getSystemService(PresenterUtils.AUDIO)).abandonAudioFocus(onAudioFocusChangeListener);
        }
        if (gil.aNe_(getContext()).getWindow() != null) {
            gil.aNe_(getContext()).getWindow().clearFlags(128);
        }
        clearFullscreenLayout();
        gil.e(getContext(), this.mNormalOrientation);
        if (gih.e().aMY_() != null) {
            gih.e().aMY_().release();
        }
        if (gih.e().aMX_() != null) {
            gih.e().aMX_().release();
        }
        gih.e().c((ResizeTextureView) null);
        gih.e().aMZ_(null);
    }

    private void initTextureView() {
        removeTextureView();
        gih.e().c(new ResizeTextureView(getContext()));
        gih.e().l().setSurfaceTextureListener(gih.e());
    }

    private void addTextureView() {
        this.mTextureViewContainer.addView(gih.e().l(), new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private void removeTextureView() {
        gih.e().aMZ_(null);
        if (gih.e().l() == null || gih.e().l().getParent() == null) {
            LogUtil.h(TAG, "removeTextureView() getTextureView() or getTextureView().getParent() is null");
            return;
        }
        ResizeTextureView l = gih.e().l();
        ViewGroup viewGroup = l.getParent() instanceof ViewGroup ? (ViewGroup) l.getParent() : null;
        if (viewGroup == null) {
            LogUtil.h(TAG, "removeTextureView() viewGroup is null");
        } else {
            viewGroup.removeView(gih.e().l());
        }
    }

    private void clearFullscreenLayout() {
        ViewGroup viewGroup = (ViewGroup) gil.aNe_(getContext()).findViewById(R.id.content);
        View findViewById = viewGroup.findViewById(com.huawei.health.R.id.video_fullscreen_id);
        View findViewById2 = viewGroup.findViewById(com.huawei.health.R.id.video_tiny_id);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        if (findViewById2 != null) {
            viewGroup.removeView(findViewById2);
        }
        showSupportActionBar(getContext());
    }

    private void clearFloatScreen() {
        gil.e(getContext(), this.mNormalOrientation);
        showSupportActionBar(getContext());
        ViewGroup viewGroup = (ViewGroup) gil.aNe_(getContext()).findViewById(R.id.content);
        IngotsVideoPlayer ingotsVideoPlayer = (IngotsVideoPlayer) viewGroup.findViewById(com.huawei.health.R.id.video_fullscreen_id);
        IngotsVideoPlayer ingotsVideoPlayer2 = (IngotsVideoPlayer) viewGroup.findViewById(com.huawei.health.R.id.video_tiny_id);
        if (ingotsVideoPlayer != null) {
            viewGroup.removeView(ingotsVideoPlayer);
            ViewGroup viewGroup2 = ingotsVideoPlayer.mTextureViewContainer;
            if (viewGroup2 != null) {
                viewGroup2.removeView(gih.e().l());
            }
        }
        if (ingotsVideoPlayer2 != null) {
            viewGroup.removeView(ingotsVideoPlayer2);
            ViewGroup viewGroup3 = ingotsVideoPlayer2.mTextureViewContainer;
            if (viewGroup3 != null) {
                viewGroup3.removeView(gih.e().l());
            }
        }
        gii.b().c(null);
    }

    public void onVideoSizeChanged() {
        if (gih.e().l() != null) {
            if (this.mVideoRotation != 0) {
                gih.e().l().setRotation(this.mVideoRotation);
            }
            gih.e().l().setVideoSize(gih.e().g(), gih.e().f());
        }
    }

    private void startProgressTimer() {
        cancelProgressTimer();
        sUpdateProgressTimer = new Timer(TAG);
        e eVar = new e();
        this.mProgressTimerTask = eVar;
        sUpdateProgressTimer.schedule(eVar, 0L, 300L);
    }

    private void cancelProgressTimer() {
        Timer timer = sUpdateProgressTimer;
        if (timer != null) {
            timer.cancel();
        }
        e eVar = this.mProgressTimerTask;
        if (eVar != null) {
            eVar.cancel();
        }
    }

    public void setProgressAndText(int i, long j, long j2) {
        if (!this.isTouchingProgressBar && i != 0) {
            this.mProgressBar.setProgress(i);
        }
        if (j != 0) {
            this.mCurrentTimeTextView.setText(gil.c(j));
        }
        this.mTotalTimeTextView.setText(gil.c(j2));
    }

    public void setBufferProgress(int i) {
        if (i != 0) {
            this.mProgressBar.setSecondaryProgress(i);
        }
    }

    public void resetProgressAndTime() {
        this.mProgressBar.setProgress(0);
        this.mProgressBar.setSecondaryProgress(0);
        this.mCurrentTimeTextView.setText(gil.c(0L));
        this.mTotalTimeTextView.setText(gil.c(0L));
    }

    public long getCurrentPositionWhenPlaying() {
        int i = this.mCurrentState;
        if (i != 3 && i != 5) {
            return 0L;
        }
        try {
            return gih.a();
        } catch (IllegalStateException unused) {
            LogUtil.b(TAG, "getCurrentPositionWhenPlaying() IllegalStateException");
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getDuration() {
        try {
            return gih.d();
        } catch (IllegalStateException unused) {
            LogUtil.b(TAG, "getDuration() IllegalStateException");
            return 0L;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        cancelProgressTimer();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        onEvent(5);
        startProgressTimer();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        int i = this.mCurrentState;
        if (i != 3 && i != 5) {
            LogUtil.h(TAG, "onStopTrackingTouch() mCurrentState:", Integer.valueOf(i));
            ViewClickInstrumentation.clickOnView(seekBar);
        } else {
            gih.a((seekBar.getProgress() * getDuration()) / 100);
            ViewClickInstrumentation.clickOnView(seekBar);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.mCurrentTimeTextView.setText(gil.c((i * getDuration()) / 100));
        }
    }

    private void startWindowFullscreen() {
        Constructor<?> constructor;
        hideSupportActionBar(getContext());
        ViewGroup viewGroup = (ViewGroup) gil.aNe_(getContext()).findViewById(R.id.content);
        View findViewById = viewGroup.findViewById(com.huawei.health.R.id.video_fullscreen_id);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        this.mTextureViewContainer.removeView(gih.e().l());
        IngotsVideoPlayer ingotsVideoPlayer = null;
        try {
            constructor = getClass().getConstructor(Context.class);
        } catch (NoSuchMethodException unused) {
            LogUtil.b(TAG, "startWindowFullscreen() NoSuchMethodException");
            constructor = null;
        }
        if (constructor == null) {
            LogUtil.h(TAG, "startWindowFullscreen constructor is null.");
            return;
        }
        try {
            Object newInstance = constructor.newInstance(getContext());
            if (newInstance instanceof IngotsVideoPlayer) {
                ingotsVideoPlayer = (IngotsVideoPlayer) newInstance;
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused2) {
            LogUtil.b(TAG, "startWindowFullscreen() IllegalAccessException or other exception");
        }
        if (ingotsVideoPlayer == null) {
            LogUtil.h(TAG, "startWindowFullscreen ingotsVideoPlayer is null.");
            return;
        }
        ingotsVideoPlayer.setId(com.huawei.health.R.id.video_fullscreen_id);
        viewGroup.addView(ingotsVideoPlayer, new FrameLayout.LayoutParams(-1, -1));
        ingotsVideoPlayer.setSystemUiVisibility(4102);
        ingotsVideoPlayer.setUp(this.mDataSourceObjects, this.mCurrentUrlMapIndex, 2, this.mVideoName);
        ingotsVideoPlayer.setState(this.mCurrentState);
        ingotsVideoPlayer.addTextureView();
        gii.b().c(ingotsVideoPlayer);
        gil.e(getContext(), 0);
        onStateNormal();
        ingotsVideoPlayer.mProgressBar.setSecondaryProgress(this.mProgressBar.getSecondaryProgress());
        ingotsVideoPlayer.startProgressTimer();
        sClickQuitFullScreenTime = System.currentTimeMillis();
    }

    public void startWindowTiny() {
        Constructor<?> constructor;
        onEvent(9);
        int i = this.mCurrentState;
        if (i == 0 || i == 7 || i == 6) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) gil.aNe_(getContext()).findViewById(R.id.content);
        View findViewById = viewGroup.findViewById(com.huawei.health.R.id.video_tiny_id);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        this.mTextureViewContainer.removeView(gih.e().l());
        IngotsVideoPlayer ingotsVideoPlayer = null;
        try {
            constructor = getClass().getConstructor(Context.class);
        } catch (NoSuchMethodException unused) {
            LogUtil.b(TAG, "startWindowTiny() NoSuchMethodException");
            constructor = null;
        }
        if (constructor == null) {
            LogUtil.h(TAG, "startWindowTiny constructor is null.");
            return;
        }
        try {
            ingotsVideoPlayer = (IngotsVideoPlayer) constructor.newInstance(getContext());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused2) {
            LogUtil.b(TAG, "startWindowTiny() InstantiationException or IllegalAccessException or InvocationTargetException");
        }
        if (ingotsVideoPlayer == null) {
            LogUtil.h(TAG, "startWindowTiny ingotsVideoPlayer is null.");
            return;
        }
        ingotsVideoPlayer.setId(com.huawei.health.R.id.video_tiny_id);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(400, 400);
        layoutParams.gravity = 85;
        viewGroup.addView(ingotsVideoPlayer, layoutParams);
        ingotsVideoPlayer.setUp(this.mDataSourceObjects, this.mCurrentUrlMapIndex, 3, this.mVideoName);
        ingotsVideoPlayer.setState(this.mCurrentState);
        ingotsVideoPlayer.addTextureView();
        gii.b().c(ingotsVideoPlayer);
        onStateNormal();
    }

    private boolean isCurrentPlay() {
        return isCurrentVideo() && gil.b(this.mDataSourceObjects, gih.c());
    }

    private boolean isCurrentVideo() {
        return gii.b().a() != null && gii.b().a() == this;
    }

    private void playOnThisVideo() {
        this.mCurrentState = gii.b().e().mCurrentState;
        this.mCurrentUrlMapIndex = gii.b().e().mCurrentUrlMapIndex;
        clearFloatScreen();
        setState(this.mCurrentState);
        addTextureView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoFullscreen(float f) {
        int i;
        if (!isCurrentPlay()) {
            LogUtil.h(TAG, "autoFullscreen() isCurrentPlay is false");
            return;
        }
        int i2 = this.mCurrentState;
        if ((i2 != 3 && i2 != 5) || (i = this.mCurrentScreen) == 2 || i == 3) {
            return;
        }
        if (f > 0.0f) {
            gil.e(getContext(), 0);
        } else {
            gil.e(getContext(), 8);
        }
        onEvent(7);
        startWindowFullscreen();
    }

    private void autoQuitFullscreen() {
        if (System.currentTimeMillis() - sLastAutoFullscreenTime > 2000 && isCurrentPlay() && this.mCurrentState == 3 && this.mCurrentScreen == 2) {
            sLastAutoFullscreenTime = System.currentTimeMillis();
            backPress();
        }
    }

    public void onEvent(int i) {
        Map<String, String> map;
        if (sIngotsPlayerActionCallbackEvent == null || !isCurrentPlay() || (map = this.mDataSourceObjects) == null) {
            return;
        }
        sIngotsPlayerActionCallbackEvent.onEvent(i, gil.b(map, this.mCurrentUrlMapIndex), this.mCurrentScreen, this.mVideoName);
    }

    private static void setMediaInterface(IngotsMediaInterface ingotsMediaInterface) {
        gih.e().c(ingotsMediaInterface);
    }

    public static int getVideoImageDisplayType() {
        return sVideoImageDisplayType;
    }

    public ImageView getStartButton() {
        return this.mStartButton;
    }

    public ImageView getFullscreenButton() {
        return this.mFullscreenButton;
    }

    public HealthSeekBar getProgressBar() {
        return this.mProgressBar;
    }

    public HealthTextView getCurrentTimeTextView() {
        return this.mCurrentTimeTextView;
    }

    public ViewGroup getTopContainer() {
        return this.mTopContainer;
    }

    public ViewGroup getBottomContainer() {
        return this.mBottomContainer;
    }

    public int getCurrentScreen() {
        return this.mCurrentScreen;
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public boolean isTmpTestBack() {
        return this.mIsTmpTestBack;
    }

    public void setTmpTestBack(boolean z) {
        this.mIsTmpTestBack = z;
    }

    public boolean isChangePosition() {
        return this.mIsChangePosition;
    }

    public boolean isChangeVolume() {
        return this.mIsChangeVolume;
    }

    class e extends TimerTask {
        private e() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (IngotsVideoPlayer.this.mCurrentState == 3 || IngotsVideoPlayer.this.mCurrentState == 5) {
                IngotsVideoPlayer.this.post(new Runnable() { // from class: com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer.e.3
                    @Override // java.lang.Runnable
                    public void run() {
                        long currentPositionWhenPlaying = IngotsVideoPlayer.this.getCurrentPositionWhenPlaying();
                        long duration = IngotsVideoPlayer.this.getDuration();
                        IngotsVideoPlayer.this.setProgressAndText((int) ((100 * currentPositionWhenPlaying) / (duration == 0 ? 1L : duration)), currentPositionWhenPlaying, duration);
                    }
                });
            }
        }
    }
}
