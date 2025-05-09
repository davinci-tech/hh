package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.automatchphoto.TrackPhotosDownload;
import com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag;
import com.huawei.healthcloud.plugintrack.ui.map.HiMapHolder;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceSnapshotCallback;
import com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor;
import com.huawei.healthcloud.plugintrack.ui.view.TrackPaceColorGradientView;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.emc;
import defpackage.enc;
import defpackage.gvv;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.haz;
import defpackage.hjd;
import defpackage.hji;
import defpackage.hjr;
import defpackage.hju;
import defpackage.hjw;
import defpackage.hky;
import defpackage.hmj;
import defpackage.hoh;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jcs;
import defpackage.koq;
import defpackage.kxb;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class TrackScreenFrag extends Fragment implements View.OnClickListener {
    public static final int c = BaseApplication.getContext().getColor(R.color._2131296651_res_0x7f09018b);

    /* renamed from: a, reason: collision with root package name */
    private ImageButton f3744a;
    private AnimatorSet aa;
    private Animator ab;
    private AnimatorSet ad;
    private TrackDetailActivity.LoadMapListener af;
    private RelativeLayout ah;
    private LinearLayout ai;
    private hmj aj;
    private TrackPhotosDownload al;
    private LinearLayout ao;
    private SyncMapCallback ap;
    private View aq;
    private List<hjd> ar;
    private LinearLayout av;
    private HealthBubbleLayout ax;
    private boolean e;
    private int f;
    private ImageButton h;
    private HealthTextView l;
    private Bitmap m;
    private int o;
    private HiMapHolder p;
    private boolean q;
    private boolean r;
    private boolean v;
    private boolean w;
    private Context i = null;
    private hjw as = null;
    private InterfaceHiMap ag = null;
    private b aw = null;
    private View au = null;
    private Handler n = new c();
    private int ae = 0;
    private ImageButton j = null;
    private ImageButton b = null;
    private ImageButton g = null;
    private LinearLayout d = null;
    private boolean z = false;
    private boolean k = true;
    private boolean u = true;
    private boolean s = false;
    private boolean x = false;
    private haz an = new haz();
    private ScreenListener ak = null;
    private boolean t = false;
    private Handler am = new a(Looper.getMainLooper(), this);
    private boolean y = false;
    private boolean ac = false;

    static class a extends BaseHandler<TrackScreenFrag> {
        public a(Looper looper, TrackScreenFrag trackScreenFrag) {
            super(looper, trackScreenFrag);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bgo_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TrackScreenFrag trackScreenFrag, Message message) {
            if (message.what == 6 && (message.obj instanceof Integer)) {
                trackScreenFrag.c(((Integer) message.obj).intValue());
            }
        }
    }

    static class d extends UiCallback<enc> {
        private final WeakReference<TrackScreenFrag> d;

        d(TrackScreenFrag trackScreenFrag) {
            this.d = new WeakReference<>(trackScreenFrag);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            if (this.d.get() != null) {
                nrh.e(BaseApplication.getContext(), R.string._2130840056_res_0x7f0209f8);
            } else {
                LogUtil.b("Track_TrackScreenFrag", "failed frag is null");
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(enc encVar) {
            if (encVar == null) {
                LogUtil.b("Track_TrackScreenFrag", "hot track path data is null, return");
                return;
            }
            TrackScreenFrag trackScreenFrag = this.d.get();
            if (trackScreenFrag != null) {
                trackScreenFrag.c(encVar.k(), encVar.e());
            } else {
                LogUtil.b("Track_TrackScreenFrag", "success frag is null");
            }
        }
    }

    public void d(SyncMapCallback syncMapCallback) {
        if (syncMapCallback == null) {
            LogUtil.b("Track_TrackScreenFrag", "callback is null, return");
        } else if (this.ag != null) {
            LogUtil.a("Track_TrackScreenFrag", "mMap is not null, trigger callback");
            syncMapCallback.onMapReady(this.ag);
        } else {
            this.ap = syncMapCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("Track_TrackScreenFrag", "startMatchAlbum IS_IGNORE_MATCH_PHOTOS");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("Track_TrackScreenFrag", "onCreateView");
        this.i = getActivity();
        FragmentActivity activity = getActivity();
        if (!(activity instanceof TrackDetailActivity)) {
            LogUtil.b("Track_TrackScreenFrag", "object is not instanceof TrackDetailActivity");
            return null;
        }
        this.t = false;
        boolean a2 = ScreenUtil.a();
        this.w = a2;
        LogUtil.a("Track_TrackScreenFrag", "screen on when create", Boolean.valueOf(a2));
        TrackDetailActivity trackDetailActivity = (TrackDetailActivity) activity;
        this.as = trackDetailActivity.e();
        this.aq = trackDetailActivity.bcQ_();
        if (ab() && !this.as.b(0)) {
            gwg.b(this.i);
            this.ae = this.as.e(this.i);
            if (layoutInflater == null) {
                throw new AssertionError("LayoutInflater not found.");
            }
            this.au = layoutInflater.inflate(R.layout.track_detail_map_fragment, viewGroup, false);
            this.s = nrt.a(this.i);
            y();
            r();
            p();
            this.aw = new b(this.au);
            HealthTextView healthTextView = (HealthTextView) this.au.findViewById(R.id.track_main_page_right_unit);
            TrackPaceColorGradientView trackPaceColorGradientView = (TrackPaceColorGradientView) this.au.findViewById(R.id.pace_color_gradient);
            if (LanguageUtil.bc(this.i)) {
                trackPaceColorGradientView.setRotationY(180.0f);
                healthTextView.setMaxWidth(nsn.c(getActivity(), 72.0f));
                ViewGroup.LayoutParams layoutParams = this.aw.l.getLayoutParams();
                if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
                    LogUtil.b("Track_TrackScreenFrag", "objectLayoutParams is not LinearLayout.LayoutParams");
                    return null;
                }
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.gravity = 17;
                this.aw.l.setLayoutParams(layoutParams2);
            }
            w();
            v();
            u();
            k();
            g();
            return this.au;
        }
        this.x = true;
        return new View(this.i);
    }

    private void w() {
        hjw hjwVar = this.as;
        if (hjwVar == null) {
            ReleaseLogUtil.c("Track_TrackScreenFrag", "mTrackDetailDataManager is null in initMapMsg");
            e();
        } else if (hjwVar.at()) {
            ReleaseLogUtil.e("Track_TrackScreenFrag", "NeedAfterProcess, wait");
            this.n.sendEmptyMessageDelayed(7, 3000L);
        } else {
            e();
        }
    }

    private void g() {
        this.l.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hiu
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                TrackScreenFrag.this.h();
            }
        });
    }

    public /* synthetic */ void h() {
        int width = this.ao.getWidth();
        int c2 = nsn.c(this.i, 50.0f);
        int width2 = ((width - c2) - this.aw.i.getWidth()) - this.aw.l.getWidth();
        int width3 = this.l.getWidth();
        if (width2 <= 0 || width2 >= width3) {
            return;
        }
        float textSize = this.l.getTextSize();
        this.l.setMaxWidth(width2);
        this.l.setTextSize(0, textSize - 1.0f);
    }

    private void k() {
        MotionPathSimplify e = this.as.e();
        if (e == null || e.requestExtendDataMap() == null) {
            LogUtil.b("Track_TrackScreenFrag", "motionPathSimplify is null, return");
            return;
        }
        Map<String, String> requestExtendDataMap = e.requestExtendDataMap();
        String str = requestExtendDataMap.get("hotPathId");
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("Track_TrackScreenFrag", "hotPathId is null, return");
        } else if (hjr.a(e)) {
            a(requestExtendDataMap);
        } else {
            emc.d().getHotPathDetail(str, new d(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final List<GpsPoint> list, final String str) {
        d(new SyncMapCallback() { // from class: hja
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public final void onMapReady(InterfaceHiMap interfaceHiMap) {
                hpu.d(list, str, interfaceHiMap);
            }
        });
    }

    private void u() {
        this.r = this.i.getSharedPreferences("retrack_file", 0).getBoolean("is_first_time_use_save_alignment_track", true);
    }

    private void y() {
        this.ao = (LinearLayout) this.au.findViewById(R.id.track_detail_sport_data_layout);
        this.av = (LinearLayout) this.au.findViewById(R.id.triathlon_sport_detail_layout);
        ImageButton imageButton = (ImageButton) this.au.findViewById(R.id.track_btn_showMap);
        this.j = imageButton;
        imageButton.setOnClickListener(this);
        ImageButton imageButton2 = (ImageButton) this.au.findViewById(R.id.track_btn_move_to_center);
        this.b = imageButton2;
        imageButton2.setOnClickListener(this);
        this.ai = (LinearLayout) this.au.findViewById(R.id.track_layout_map_share_control);
        this.f3744a = (ImageButton) this.au.findViewById(R.id.track_btn_save_route);
        this.ax = (HealthBubbleLayout) this.au.findViewById(R.id.track_save_route_bottom_bubble_layout);
        this.ah = (RelativeLayout) this.au.findViewById(R.id.track_detail_map_londing);
        this.d = (LinearLayout) this.au.findViewById(R.id.track_goto_retrack_ll);
        HealthTextView healthTextView = (HealthTextView) this.au.findViewById(R.id.dynamic_track_guide_content);
        this.l = healthTextView;
        healthTextView.setOnClickListener(this);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ai.getLayoutParams();
        layoutParams.setMarginEnd(layoutParams.getMarginEnd() + ((Integer) safeRegionWidth.second).intValue());
        this.ai.setLayoutParams(layoutParams);
        this.av.setPadding(((Integer) safeRegionWidth.first).intValue(), 0, ((Integer) safeRegionWidth.second).intValue(), 0);
    }

    private void r() {
        this.g = (ImageButton) this.au.findViewById(R.id.track_btn_show_km_mi_markers);
        this.h = (ImageButton) this.au.findViewById(R.id.track_btn_show_map_type_satellite);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.g.setBackgroundResource(kxb.c(this.as.e().requestSportType()) ? R.drawable._2131431930_res_0x7f0b11fa : R.drawable._2131431932_res_0x7f0b11fc);
    }

    private void p() {
        if (hju.e(this.as.e().requestSportType()) && Utils.i()) {
            this.f3744a.setOnClickListener(this);
            this.f3744a.setVisibility(0);
        }
    }

    public void e() {
        if (this.p != null) {
            ReleaseLogUtil.e("Track_TrackScreenFrag", "mHiMapHolder != null ", Integer.valueOf(this.ae));
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.b("Track_TrackScreenFrag", "initMap parentActivity is null");
            return;
        }
        this.p = (HiMapHolder) this.au.findViewById(R.id.sport_track_map);
        ReleaseLogUtil.e("Track_TrackScreenFrag", "mHiMapHolder.isSyncLoadMap()=", Integer.valueOf(this.ae));
        int i = this.ae;
        if (i == 1) {
            bge_(activity);
        } else if (i == 2) {
            bgf_(activity);
        } else if (i == 3) {
            bgg_(activity);
        } else if (i == -1) {
            bgd_(activity);
        } else {
            LogUtil.h("Track_TrackScreenFrag", "map type not match");
        }
        ScreenListener screenListener = new ScreenListener(this.i);
        this.ak = screenListener;
        screenListener.b(new ScreenListener.ScreenStateListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.1
            @Override // com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener.ScreenStateListener
            public void onScreenOff() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener.ScreenStateListener
            public void onUserPresent() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener.ScreenStateListener
            public void onScreenOn() {
                if (TrackScreenFrag.this.ak != null) {
                    TrackScreenFrag.this.ak.c();
                    TrackScreenFrag.this.ak = null;
                }
                if (TrackScreenFrag.this.w || TrackScreenFrag.this.ag == null) {
                    return;
                }
                TrackScreenFrag.this.ai();
            }
        });
        this.n.sendEmptyMessageDelayed(2, 600L);
    }

    private void bge_(final Activity activity) {
        this.ao.setVisibility(8);
        this.ai.setVisibility(8);
        this.d.setVisibility(8);
        this.ag = this.p.bhK_(null, this.i);
        new Thread(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.14
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Track_TrackScreenFrag", "start run initGaode thread");
                TrackScreenFrag.this.ag();
                if (TrackScreenFrag.this.ag != null) {
                    if (TrackScreenFrag.this.s) {
                        TrackScreenFrag.this.ag.setMapShowType(3);
                    } else {
                        TrackScreenFrag.this.ag.setMapShowType(0);
                    }
                    TrackScreenFrag.this.n.sendEmptyMessage(6);
                    return;
                }
                LogUtil.b("Track_TrackScreenFrag", "mMap is null");
                activity.finish();
            }
        }).start();
    }

    private void bgf_(final Activity activity) {
        t();
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
        ae();
        this.e = false;
        this.p.a(this.i, (Fragment) null, new SyncMapCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.13
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public void onMapReady(InterfaceHiMap interfaceHiMap) {
                Activity activity2 = activity;
                if (activity2 == null || activity2.isFinishing()) {
                    LogUtil.h("Track_TrackScreenFrag", "Activity is null or is finishing in onMapReady");
                    return;
                }
                if (interfaceHiMap != null) {
                    TrackScreenFrag.this.ag = interfaceHiMap;
                    if (TrackScreenFrag.this.ap != null) {
                        TrackScreenFrag.this.ap.onMapReady(TrackScreenFrag.this.ag);
                    }
                    TrackScreenFrag.this.ag();
                    if (TrackScreenFrag.this.ac()) {
                        if (!gwg.c(TrackScreenFrag.this.i)) {
                            TrackScreenFrag.this.ah.setVisibility(8);
                            TrackScreenFrag.this.aq.setVisibility(0);
                            return;
                        } else {
                            TrackScreenFrag trackScreenFrag = TrackScreenFrag.this;
                            trackScreenFrag.a(trackScreenFrag.ag);
                            return;
                        }
                    }
                    LogUtil.h("Track_TrackScreenFrag", "onMapReady() mTrackDetailDataManager is null");
                    TrackScreenFrag.this.ah.setVisibility(8);
                    TrackScreenFrag.this.aq.setVisibility(0);
                    if (TrackScreenFrag.this.af != null) {
                        TrackScreenFrag.this.af.endLoadMap();
                        return;
                    }
                    return;
                }
                LogUtil.b("Track_TrackScreenFrag", "onMapReady map is null");
                activity.finish();
            }
        });
    }

    private void bgg_(final Activity activity) {
        t();
        ae();
        ImageButton imageButton = this.h;
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
        this.e = false;
        this.p.b(this.i, new SyncMapCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.15
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public void onMapReady(InterfaceHiMap interfaceHiMap) {
                if (interfaceHiMap != null) {
                    TrackScreenFrag.this.ag = interfaceHiMap;
                    if (TrackScreenFrag.this.ap != null) {
                        TrackScreenFrag.this.ap.onMapReady(TrackScreenFrag.this.ag);
                    }
                    TrackScreenFrag.this.ag();
                    if (TrackScreenFrag.this.s) {
                        TrackScreenFrag.this.ag.setMapShowType(3);
                    } else {
                        TrackScreenFrag.this.ag.setMapShowType(0);
                    }
                    if (!TrackScreenFrag.this.ac()) {
                        LogUtil.h("Track_TrackScreenFrag", "onMapReady() mTrackDetailDataManager is null");
                        TrackScreenFrag.this.ah.setVisibility(8);
                        TrackScreenFrag.this.aq.setVisibility(8);
                        if (TrackScreenFrag.this.af != null) {
                            TrackScreenFrag.this.af.endLoadMap();
                            return;
                        }
                        return;
                    }
                    TrackScreenFrag trackScreenFrag = TrackScreenFrag.this;
                    trackScreenFrag.a(trackScreenFrag.ag);
                    return;
                }
                LogUtil.h("Track_TrackScreenFrag", "onMapReady hiMap is null");
                activity.finish();
            }
        });
    }

    private void bgd_(final Activity activity) {
        this.ao.setVisibility(8);
        this.ai.setVisibility(8);
        this.d.setVisibility(8);
        this.ag = this.p.bhJ_(null, this.i);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.11
            @Override // java.lang.Runnable
            public void run() {
                TrackScreenFrag.this.ag();
                if (TrackScreenFrag.this.ag != null) {
                    if (TrackScreenFrag.this.s) {
                        TrackScreenFrag.this.ag.setMapShowType(3);
                    } else {
                        TrackScreenFrag.this.ag.setMapShowType(0);
                    }
                    TrackScreenFrag.this.n.sendEmptyMessage(6);
                    return;
                }
                LogUtil.b("Track_TrackScreenFrag", "mMap is null");
                activity.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        InterfaceHiMap interfaceHiMap = this.ag;
        if (interfaceHiMap == null) {
            LogUtil.b("Track_TrackScreenFrag", "syncLoadMap mMap is null");
            return;
        }
        interfaceHiMap.setIsStop(true);
        boolean z = false;
        this.ag.onCreate(null, false, false);
        this.ag.setShowMapEnd(true);
        ReleaseLogUtil.e("Track_TrackScreenFrag", "onMapLoaded");
        MotionPathSimplify e = this.as.e();
        if (e != null && !(z = e.isNewCoordinate()) && e.requestMapType() == 2) {
            z = true;
        }
        List<MotionData> b2 = this.as.b();
        if (z) {
            b2 = this.ag.convertCoordinate(b2);
        }
        this.ag.loadMapWithPreprocessData(hky.c(b2, this.as.e()).preprocess());
        this.ac = true;
        ah();
        this.ag.showOrHide(true);
        af();
        s();
        n();
        l();
        this.n.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.18
            @Override // java.lang.Runnable
            public void run() {
                if (TrackScreenFrag.this.i instanceof Activity) {
                    Activity activity = (Activity) TrackScreenFrag.this.i;
                    if (!activity.isDestroyed() && !activity.isFinishing()) {
                        TrackScreenFrag.this.aa.start();
                        return;
                    } else {
                        LogUtil.b("Track_TrackScreenFrag", "activity is isDestroyed or isFinishing return ");
                        return;
                    }
                }
                LogUtil.b("Track_TrackScreenFrag", "runload context is not instanceof Activity");
            }
        });
    }

    private void af() {
        this.ag.registerAnimationListener(new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.19
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onFinish() {
                TrackScreenFrag.this.n.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.19.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TrackScreenFrag.this.ai.setVisibility(0);
                        TrackScreenFrag.this.ak();
                        TrackScreenFrag.this.aq.setVisibility(0);
                        TrackScreenFrag.this.ac = false;
                        TrackScreenFrag.this.al();
                        if (TrackScreenFrag.this.af != null) {
                            TrackScreenFrag.this.af.endLoadMap();
                        }
                        TrackScreenFrag.this.ad.start();
                        TrackScreenFrag.this.an();
                        TrackScreenFrag.this.ar = TrackScreenFrag.this.ag.requestSimplePoints();
                    }
                });
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onCancel() {
                TrackScreenFrag.this.t();
                TrackScreenFrag.this.aq.setVisibility(0);
                if (TrackScreenFrag.this.af != null) {
                    TrackScreenFrag.this.af.endLoadMap();
                }
                TrackScreenFrag.this.ae();
                TrackScreenFrag.this.ai.setVisibility(0);
                TrackScreenFrag.this.ak();
                TrackScreenFrag.this.ac = false;
                TrackScreenFrag.this.al();
                TrackScreenFrag.this.an();
                TrackScreenFrag trackScreenFrag = TrackScreenFrag.this;
                trackScreenFrag.ar = trackScreenFrag.ag.requestSimplePoints();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        if (kxb.c(this.as.e().requestSportType())) {
            this.ag.showTrackMarkers(true);
        } else if (ad()) {
            this.ag.showTrackMarkers(false);
            this.g.setVisibility(8);
        } else {
            this.ag.showTrackMarkers(true ^ UnitUtil.h());
            f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        if (this.r && this.f3744a.getVisibility() == 0) {
            this.ax.setVisibility(0);
            this.ax.setOnClickListener(this);
        } else {
            this.ax.setVisibility(8);
        }
    }

    private boolean ad() {
        return this.as.e().requestSportType() == 222;
    }

    private void s() {
        Animator animator;
        Animator loadAnimator = AnimatorInflater.loadAnimator(this.i, R.animator._2131034182_res_0x7f050046);
        loadAnimator.setTarget(this.ao);
        Animator loadAnimator2 = AnimatorInflater.loadAnimator(this.i, R.animator._2131034182_res_0x7f050046);
        loadAnimator2.setTarget(this.ai);
        if (aa()) {
            animator = AnimatorInflater.loadAnimator(this.i, R.animator._2131034182_res_0x7f050046);
            animator.setTarget(this.d);
        } else {
            animator = null;
        }
        Animator loadAnimator3 = AnimatorInflater.loadAnimator(this.i, R.animator._2131034182_res_0x7f050046);
        loadAnimator3.setTarget(this.aq);
        this.ad = new AnimatorSet();
        this.aa = new AnimatorSet();
        AnimatorSet animatorSet = new AnimatorSet();
        if (animator == null) {
            animatorSet.play(loadAnimator);
        } else {
            animatorSet.playTogether(loadAnimator, animator);
        }
        this.ad.playTogether(loadAnimator2, loadAnimator3);
        Animator loadAnimator4 = AnimatorInflater.loadAnimator(this.i, R.animator._2131034183_res_0x7f050047);
        this.ab = loadAnimator4;
        loadAnimator4.setTarget(this.ah);
        this.aa.playSequentially(this.ab, animatorSet);
    }

    private void n() {
        this.ab.addListener(new Animator.AnimatorListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.17
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                TrackScreenFrag.this.n.removeMessages(2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                TrackScreenFrag.this.ah.setVisibility(8);
                TrackScreenFrag.this.t();
                TrackScreenFrag.this.ae();
                TrackScreenFrag.this.q();
                TrackScreenFrag.this.aq.setVisibility(0);
                if (TrackScreenFrag.this.af != null) {
                    TrackScreenFrag.this.af.endLoadMap();
                }
            }
        });
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag$20, reason: invalid class name */
    public class AnonymousClass20 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        AnonymousClass20() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            FragmentActivity activity = TrackScreenFrag.this.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: hjb
                    @Override // java.lang.Runnable
                    public final void run() {
                        TrackScreenFrag.AnonymousClass20.this.e();
                    }
                });
            }
        }

        public /* synthetic */ void e() {
            if (!TrackScreenFrag.this.t) {
                if (TrackScreenFrag.this.ag != null) {
                    TrackScreenFrag.this.ag.startMapAnimation(TrackScreenFrag.this.f == 0);
                    return;
                } else {
                    ReleaseLogUtil.e("Track_TrackScreenFrag", "LoadFadeOutBeforeDrawing animation end mMap is null");
                    return;
                }
            }
            ReleaseLogUtil.e("Track_TrackScreenFrag", "LoadFadeOutBeforeDrawing animation end then fragment is destroying");
        }
    }

    private void l() {
        this.aa.addListener(new AnonymousClass20());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        hjw hjwVar = this.as;
        if (hjwVar == null || hjwVar.e() == null) {
            return;
        }
        if (this.as.e().requestSportType() == 512) {
            this.ao.setVisibility(8);
            this.av.setVisibility(0);
        } else {
            this.ao.setVisibility(0);
            this.av.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (!ab()) {
            LogUtil.h("Track_TrackScreenFrag", "initData() mTrackDetailDataManager is null");
            return;
        }
        MotionPathSimplify e = this.as.e();
        long requestStartTime = e.requestStartTime();
        int requestSportType = e.requestSportType();
        if (e.requestTrackType() == 1) {
            this.aw.k.setVisibility(0);
        }
        if (LanguageUtil.bt(this.i)) {
            this.aw.m.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).format(Long.valueOf(requestStartTime)));
        } else {
            this.aw.m.setText(nsj.c(this.i, requestStartTime, 21));
        }
        e(e.requestTotalDistance(), requestSportType);
        String c2 = hji.c(e.requestTotalTime());
        this.aw.h.setText(c2);
        this.aw.y.setText(c2);
        String c3 = hji.c(e.requestAvgPace());
        this.aw.g.setText(c3);
        String c4 = hji.c(e.requestTotalCalories());
        this.aw.b.setText(c4);
        this.aw.x.setText(c4);
        if (requestSportType == 260) {
            i(e);
        } else if (requestSportType == 266 || requestSportType == 262) {
            this.aw.g.setText(hji.f(e.requestAvgPace()));
            this.aw.r.setVisibility(0);
            this.aw.r.setGravity(GravityCompat.START);
            if (UnitUtil.h()) {
                this.aw.r.setText(getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100));
            } else {
                this.aw.r.setText(getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100));
            }
        } else if (hji.j(requestSportType)) {
            this.aw.g.setText(gvv.a(e.requestAvgPace() / 2.0f));
            this.aw.r.setVisibility(0);
            this.aw.r.setGravity(GravityCompat.START);
            this.aw.r.setText(nsf.a(R.plurals._2130903225_res_0x7f0300b9, 500, 500));
        } else if (requestSportType == 259 || requestSportType == 219 || hji.h(requestSportType)) {
            e(e);
        } else if (kxb.c(requestSportType)) {
            a(e);
        } else if (requestSportType == 222) {
            c(e);
        } else {
            this.aw.g.setText(c3);
        }
        this.aw.t.setAutoTextInfo(9, 1, 1);
        Map<Integer, Float> bb = this.as.bb();
        a(requestSportType, bb);
        if (e(e, bb)) {
            this.g.setVisibility(8);
        }
        MotionPath d2 = this.as.d();
        if (d2 != null) {
            a(e, d2.requestPaceMap());
        }
    }

    private void v() {
        int i = this.ae;
        if (i == 1) {
            this.aw.f3753a.setText(this.i.getResources().getString(R.string._2130837686_res_0x7f0200b6));
            this.aw.v.setText(this.i.getResources().getString(R.string._2130837686_res_0x7f0200b6));
        } else if (i == 2) {
            this.aw.f3753a.setText(this.i.getResources().getString(R.string._2130837687_res_0x7f0200b7));
            this.aw.v.setText(this.i.getResources().getString(R.string._2130837687_res_0x7f0200b7));
        } else if (i == 3) {
            this.aw.f3753a.setText(this.i.getResources().getString(R.string._2130845162_res_0x7f021dea));
            this.aw.v.setText(this.i.getResources().getString(R.string._2130845162_res_0x7f021dea));
        } else {
            LogUtil.h("Track_TrackScreenFrag", "initMapLogo is default");
        }
    }

    private void a(MotionPathSimplify motionPathSimplify, Map<Integer, Float> map) {
        if (!e(map)) {
            Float[] e = gvv.e(map);
            List<Integer> b2 = gwe.b(e[1].floatValue(), e[0].floatValue(), motionPathSimplify.requestSportType());
            if (koq.b(b2)) {
                return;
            }
            this.aw.c.setColors(b2);
            this.aw.e.setTextColor(b2.get(b2.size() - 1).intValue());
            this.aw.f.setTextColor(b2.get(0).intValue());
            this.aw.d.setTextColor(b2.get(b2.size() - 1).intValue());
            this.aw.j.setTextColor(b2.get(0).intValue());
            return;
        }
        this.aw.c.setVisibility(8);
        this.aw.e.setVisibility(8);
        this.aw.f.setVisibility(8);
        this.aw.d.setVisibility(8);
        this.aw.j.setVisibility(8);
    }

    private boolean e(Map<Integer, Float> map) {
        return c(map) || !this.as.a(this.i);
    }

    private boolean e(MotionPathSimplify motionPathSimplify, Map<Integer, Float> map) {
        return (c(map) && d(motionPathSimplify)) || (!kxb.c(motionPathSimplify.requestSportType()) && UnitUtil.h()) || b(motionPathSimplify) || motionPathSimplify.requestSportType() == 512;
    }

    private void i(MotionPathSimplify motionPathSimplify) {
        String string;
        String e;
        double requestCreepingWave = motionPathSimplify.requestCreepingWave() / 10.0f;
        if (requestCreepingWave <= 1.0E-6d) {
            e(motionPathSimplify);
            return;
        }
        this.aw.t.setText(R.string._2130842325_res_0x7f0212d5);
        if (UnitUtil.h()) {
            double e2 = UnitUtil.e(requestCreepingWave, 1);
            string = this.i.getResources().getQuantityString(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(e2));
            e = UnitUtil.e(e2, 1, 2);
        } else {
            string = this.i.getResources().getString(R.string._2130841568_res_0x7f020fe0);
            e = UnitUtil.e(requestCreepingWave, 1, 1);
        }
        this.aw.g.setText(e);
        this.aw.r.setVisibility(0);
        this.aw.r.setGravity(GravityCompat.START);
        this.aw.r.setText(string);
    }

    private void a(int i, Map<Integer, Float> map) {
        if (map == null) {
            LogUtil.b("Track_TrackScreenFrag", "constructPace paceMap is null");
            return;
        }
        if (map.size() > 1) {
            Float[] e = gvv.e(map);
            String e2 = e(e[0], i, this.aw);
            String e3 = e(e[1], i, this.aw);
            if (hji.g(i) && hji.c(this.as)) {
                if (hji.d(this.as)) {
                    e3 = hji.c(this.as, false);
                    e2 = hji.a(this.as, false);
                } else {
                    this.aw.e.setVisibility(8);
                    this.aw.f.setVisibility(8);
                    this.aw.d.setVisibility(8);
                    this.aw.j.setVisibility(8);
                }
            }
            b bVar = this.aw;
            bVar.a(true, e2, e3, i, this.i);
            return;
        }
        this.aw.a(false, null, null, i, this.i);
    }

    private void e(MotionPathSimplify motionPathSimplify) {
        String g = hji.g(motionPathSimplify.requestAvgPace());
        this.aw.t.setText(R.string._2130839763_res_0x7f0208d3);
        this.aw.g.setText(g);
        this.aw.r.setVisibility(0);
        this.aw.r.setGravity(GravityCompat.START);
        if (UnitUtil.h()) {
            this.aw.r.setText(this.i.getString(R.string._2130844079_res_0x7f0219af));
        } else {
            this.aw.r.setText(this.i.getString(R.string._2130844078_res_0x7f0219ae));
        }
    }

    private void c(MotionPathSimplify motionPathSimplify) {
        this.aw.i.setText(gvv.c(motionPathSimplify.requestTotalTime(), R.style.sport_day_hour_min_num_50dp, R.style.sport_day_hour_min_unit_12dp));
        this.aw.u.setVisibility(8);
        this.aw.l.setVisibility(8);
        this.aw.w.setVisibility(8);
        View findViewById = this.au.findViewById(R.id.track_main_page_center_data_Layout);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        this.aw.o.setText(R.string._2130846373_res_0x7f0222a5);
        int extendDataInt = motionPathSimplify.getExtendDataInt("wayPointDistance");
        this.aw.h.setText(extendDataInt == 0 ? this.i.getString(R.string._2130850262_res_0x7f0231d6) : hji.e(extendDataInt));
        this.aw.n.setVisibility(0);
        this.aw.p.setMaxWidth(R.dimen._2131363588_res_0x7f0a0704);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime());
        this.aw.s.setText(R.string._2130846653_res_0x7f0223bd);
        this.aw.b.setText(hji.b(extendDataInt, seconds));
        if (UnitUtil.h()) {
            this.aw.p.setText(this.i.getString(R.string._2130844079_res_0x7f0219af));
            this.aw.n.setText(R.string._2130844081_res_0x7f0219b1);
        } else {
            this.aw.p.setText(this.i.getString(R.string._2130844078_res_0x7f0219ae));
            this.aw.n.setText(R.string._2130844082_res_0x7f0219b2);
        }
    }

    private void a(MotionPathSimplify motionPathSimplify) {
        int requestTotalDistance = motionPathSimplify.requestTotalDistance();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime());
        this.aw.o.setText(R.string._2130839890_res_0x7f020952);
        this.aw.t.setText(R.string._2130839893_res_0x7f020955);
        this.aw.g.setText(hji.b(requestTotalDistance, seconds));
        this.aw.r.setVisibility(0);
        this.aw.r.setGravity(GravityCompat.START);
        if (UnitUtil.h()) {
            this.aw.r.setText(this.i.getString(R.string._2130844079_res_0x7f0219af));
        } else {
            this.aw.r.setText(this.i.getString(R.string._2130844078_res_0x7f0219ae));
        }
        this.aw.s.setText(R.string._2130839892_res_0x7f020954);
        this.aw.p.setVisibility(8);
        this.aw.p.setAutoTextInfo(9, 1, 2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.aw.q.getLayoutParams());
        layoutParams.gravity = 1;
        this.aw.q.setLayoutParams(layoutParams);
        this.aw.b.setText(String.valueOf(motionPathSimplify.getExtendDataInt("skiTripTimes", 0)));
        this.aw.c.setVisibility(8);
    }

    private boolean b(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify.requestSportType() == 266 || motionPathSimplify.requestSportType() == 262;
    }

    private boolean c(Map<Integer, Float> map) {
        return map == null || map.isEmpty();
    }

    private boolean d(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify.getExtendDataInt("skiTripTimes", 0) == 0;
    }

    private String e(Float f, int i, b bVar) {
        String a2 = gvv.a(f.floatValue());
        if (hji.g(i) && this.i != null) {
            bVar.r.setVisibility(0);
            bVar.r.setGravity(GravityCompat.START);
            return hji.o(f.floatValue());
        }
        if (i == 266 || i == 262) {
            bVar.r.setVisibility(0);
            bVar.r.setGravity(GravityCompat.START);
            return hji.o(f.floatValue() / 10.0f);
        }
        bVar.r.setVisibility(8);
        bVar.r.setGravity(17);
        return a2;
    }

    private void e(double d2, int i) {
        String i2;
        String str;
        if (hji.j(i)) {
            if (d2 == 0.0d) {
                str = nsf.h(R.string._2130850262_res_0x7f0231d6);
            } else {
                str = UnitUtil.a(d2, 1, 0, false);
            }
            this.aw.l.setText(R.string._2130841568_res_0x7f020fe0);
        } else if (i != 266 && i != 262) {
            if (d2 == 0.0d) {
                str = this.i.getString(R.string._2130850262_res_0x7f0231d6);
            } else {
                str = hji.e(d2);
            }
            if (UnitUtil.h()) {
                this.aw.l.setText(R.string._2130844081_res_0x7f0219b1);
                this.aw.u.setText(R.string._2130844081_res_0x7f0219b1);
            } else {
                this.aw.l.setText(R.string._2130844082_res_0x7f0219b2);
                this.aw.u.setText(R.string._2130844082_res_0x7f0219b2);
            }
        } else {
            if (d2 == 0.0d) {
                i2 = this.i.getString(R.string._2130850262_res_0x7f0231d6);
            } else {
                i2 = hji.i((float) d2);
            }
            if (UnitUtil.h()) {
                this.aw.l.setText(getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, (int) Math.round(UnitUtil.e(d2, 2))));
            } else {
                this.aw.l.setText(R.string._2130841568_res_0x7f020fe0);
            }
            str = i2;
        }
        this.aw.i.setText(str);
        this.aw.w.setText(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        InterfaceHiMap interfaceHiMap = this.ag;
        if (interfaceHiMap != null) {
            interfaceHiMap.onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.x) {
            FragmentInstrumentation.onResumeByFragment(this);
            return;
        }
        InterfaceHiMap interfaceHiMap = this.ag;
        if (interfaceHiMap != null) {
            interfaceHiMap.onResume();
            ai();
        }
        FragmentInstrumentation.onResumeByFragment(this);
    }

    public void j() {
        HiMapHolder hiMapHolder = this.p;
        if (hiMapHolder == null || this.ae != 1) {
            return;
        }
        hiMapHolder.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.x) {
            FragmentInstrumentation.onPauseByFragment(this);
            return;
        }
        InterfaceHiMap interfaceHiMap = this.ag;
        if (interfaceHiMap != null) {
            interfaceHiMap.onPause();
        }
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        InterfaceHiMap interfaceHiMap = this.ag;
        if (interfaceHiMap != null) {
            interfaceHiMap.onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.t = true;
        if (this.x) {
            return;
        }
        Handler handler = this.n;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (this.al != null) {
            LogUtil.a("Track_TrackScreenFrag", "TrackScreenFrag onDestroy TrackPhotosDownload goto destroy");
            this.al.b();
        }
        AnimatorSet animatorSet = this.ad;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.ad.cancel();
            this.ad = null;
        }
        AnimatorSet animatorSet2 = this.aa;
        if (animatorSet2 != null && animatorSet2.isRunning()) {
            this.aa.cancel();
            this.aa = null;
        }
        Bitmap bitmap = this.m;
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                this.m.recycle();
            }
            this.m = null;
        }
        ScreenListener screenListener = this.ak;
        if (screenListener != null) {
            screenListener.c();
            this.ak = null;
        }
        InterfaceHiMap interfaceHiMap = this.ag;
        if (interfaceHiMap != null) {
            interfaceHiMap.onDestroy();
        }
        HiMapHolder hiMapHolder = this.p;
        if (hiMapHolder != null) {
            hiMapHolder.b();
            this.p = null;
        }
        hmj hmjVar = this.aj;
        if (hmjVar != null) {
            hmjVar.c();
            this.aj = null;
        }
        FileUtils.d(new File(jcs.b));
    }

    public void bgn_(final Handler handler) {
        if (handler == null) {
            LogUtil.b("Track_TrackScreenFrag", "handler is null");
            return;
        }
        if (!ac()) {
            LogUtil.h("Track_TrackScreenFrag", "shareSportData() mTrackDetailDataManager is null");
            return;
        }
        if (z()) {
            if (!this.ag.isAnimationStart()) {
                handler.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.3
                    @Override // java.lang.Runnable
                    public void run() {
                        TrackScreenFrag.this.ag.cancelAnimation();
                        TrackScreenFrag.this.bgc_(handler);
                    }
                }, 500L);
                return;
            } else {
                this.ag.cancelAnimation();
                this.ag.getMapScreenShot(handler, this.as.j());
                return;
            }
        }
        if (x()) {
            if (this.o == 0) {
                this.ag.getMapScreenShot(handler, this.as.j());
                return;
            } else {
                bgb_(handler);
                return;
            }
        }
        LogUtil.b("Track_TrackScreenFrag", "Map is all null,share data");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bgc_(final Handler handler) {
        if (this.as.t()) {
            handler.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.5
                @Override // java.lang.Runnable
                public void run() {
                    TrackScreenFrag.this.ag.getMapScreenShot(handler, TrackScreenFrag.this.as.j());
                }
            }, 200L);
        } else {
            this.ag.getMapScreenShot(handler, this.as.j());
        }
    }

    private void bgb_(final Handler handler) {
        if (this.q) {
            if (this.v) {
                handler.sendEmptyMessageDelayed(1, 200L);
                return;
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Message obtain = Message.obtain(handler);
                        try {
                            obtain.obj = BitmapFactory.decodeFile(new File(TrackScreenFrag.this.i.getFilesDir(), "google_temp.png").getCanonicalPath());
                        } catch (IOException unused) {
                            LogUtil.h("Track_TrackScreenFrag", "decode google mIMap jpg error 1");
                            obtain.obj = null;
                        }
                        obtain.what = 1;
                        obtain.sendToTarget();
                    }
                });
                return;
            }
        }
        handler.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.2
            @Override // java.lang.Runnable
            public void run() {
                if (TrackScreenFrag.this.q && !TrackScreenFrag.this.v) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.2.5
                        @Override // java.lang.Runnable
                        public void run() {
                            Message obtain = Message.obtain(handler);
                            try {
                                obtain.obj = BitmapFactory.decodeFile(new File(TrackScreenFrag.this.i.getFilesDir(), "google_temp.png").getCanonicalPath());
                            } catch (IOException unused) {
                                LogUtil.h("Track_TrackScreenFrag", "decode google mIMap jpg error 2");
                                obtain.obj = null;
                            }
                            obtain.what = 1;
                            obtain.sendToTarget();
                        }
                    });
                } else {
                    handler.sendEmptyMessage(1);
                }
            }
        }, 1000L);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.b("Track_TrackScreenFrag", "view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.ag == null) {
            LogUtil.b("Track_TrackScreenFrag", "onClick track_btn_showMap map not ready");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (view.getId() == R.id.track_btn_showMap) {
            boolean z = true ^ this.u;
            this.u = z;
            this.ag.showOrHide(z);
            this.j.setBackgroundResource(this.u ? R.drawable._2131431835_res_0x7f0b119b : R.drawable._2131431832_res_0x7f0b1198);
            jcf.bEz_(this.j, nsf.h(this.u ? R.string._2130850621_res_0x7f02333d : R.string._2130850660_res_0x7f023364));
            LogUtil.a("Track_TrackScreenFrag", "showOrHideMap click , show mIMap is ", Boolean.valueOf(this.u), ",mIsShowTrackMapTypeSatellite=", Boolean.valueOf(this.z));
            hashMap.put("type", 1);
        } else {
            if (view.getId() == R.id.track_btn_move_to_center) {
                ai();
                LogUtil.a("Track_TrackScreenFrag", "moveMapToCenter click");
                hashMap.put("type", 0);
            } else if (view.getId() == R.id.track_btn_show_km_mi_markers) {
                d(hashMap);
            } else if (view.getId() == R.id.track_btn_show_map_type_satellite) {
                boolean z2 = true ^ this.z;
                this.z = z2;
                this.ag.showSatelLiteState(this.u, z2, this.s ? 3 : 0);
                this.h.setBackgroundResource(this.z ? R.drawable._2131431934_res_0x7f0b11fe : R.drawable._2131431933_res_0x7f0b11fd);
                jcf.bEz_(this.h, nsf.h(this.z ? R.string._2130850658_res_0x7f023362 : R.string._2130850661_res_0x7f023365));
            } else if (view.getId() == R.id.dynamic_track_guide_content) {
                c();
            } else if (view.getId() == R.id.track_btn_save_route) {
                am();
                a();
            } else if (view.getId() == R.id.track_save_route_bottom_bubble_layout) {
                a();
            } else if (view.getId() == R.id.route_draw_btn_track) {
                b(false);
            } else if (view.getId() == R.id.route_draw_btn_shape) {
                b(true);
            } else {
                LogUtil.c("Track_TrackScreenFrag", "touch is not id");
            }
        }
        b(hashMap);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(Map<String, Object> map) {
        int i;
        MotionPathSimplify e = this.as.e();
        if (e != null) {
            map.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.as.e().requestSportType()));
            i = e.requestTrackType();
            if (e.requestSportDataSource() == 2) {
                i = 6;
            }
        } else {
            i = 0;
        }
        map.put("trackType", Integer.valueOf(i));
        ixx.d().d(this.i, AnalyticsValue.MOTION_TRACK_1040022.value(), map, 0);
    }

    private void d(Map<String, Object> map) {
        boolean z = !this.k;
        this.k = z;
        c(z);
        map.put("type", 3);
    }

    private void c(boolean z) {
        this.k = z;
        this.ag.showTrackMarkers(z);
        if (kxb.c(this.as.e().requestSportType())) {
            this.g.setBackgroundResource(this.k ? R.drawable._2131431930_res_0x7f0b11fa : R.drawable._2131431929_res_0x7f0b11f9);
        } else {
            this.g.setBackgroundResource(this.k ? R.drawable._2131431932_res_0x7f0b11fc : R.drawable._2131431931_res_0x7f0b11fb);
        }
        jcf.bEz_(this.g, nsf.h(this.k ? R.string._2130850620_res_0x7f02333c : R.string._2130850659_res_0x7f023363));
    }

    public boolean a() {
        Context context = this.i;
        if (context == null) {
            LogUtil.h("Track_TrackScreenFrag", "mContext is null in hideRouteGuild");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("retrack_file", 0);
        this.ax.setVisibility(8);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("is_first_time_use_save_alignment_track", false);
        edit.apply();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        if (this.u) {
            if (this.z) {
                this.ag.setMapShowType(1);
                return;
            } else if (this.s) {
                this.ag.setMapShowType(3);
                return;
            } else {
                this.ag.setMapShowType(0);
                return;
            }
        }
        this.ag.setMapShowType(2);
    }

    private boolean z() {
        return (this.ae == 2 || this.ag == null) ? false : true;
    }

    private boolean x() {
        return this.ae == 2 && this.ag != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ac() {
        hjw hjwVar = this.as;
        return hjwVar != null && koq.c(hjwVar.b());
    }

    private boolean ab() {
        hjw hjwVar = this.as;
        return (hjwVar == null || hjwVar.e() == null) ? false : true;
    }

    public void d(int i) {
        if (this.x) {
            return;
        }
        this.f = i;
        if (i == 0) {
            this.q = false;
        } else if (z()) {
            this.ag.cancelAnimation();
        } else {
            LogUtil.c("Track_TrackScreenFrag", "is google mIMap save tab");
        }
        if (this.e) {
            if (x() && this.o == 0) {
                this.ag.screenShotToFile(new InterfaceSnapshotCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.8
                    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceSnapshotCallback
                    public void onSnapshotReady(Bitmap bitmap) {
                        if (bitmap == null) {
                            TrackScreenFrag.this.v = true;
                            TrackScreenFrag.this.q = true;
                        } else {
                            TrackScreenFrag.this.bgk_(bitmap);
                        }
                    }
                });
            }
            this.o = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bgk_(Bitmap bitmap) {
        this.m = bitmap;
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.10
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v5, types: [com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag] */
            @Override // java.lang.Runnable
            public void run() {
                Object obj = "close IOException ";
                TrackScreenFrag.this.v = false;
                FileOutputStream fileOutputStream = null;
                try {
                    try {
                        fileOutputStream = org.apache.commons.io.FileUtils.openOutputStream(new File(TrackScreenFrag.this.i.getFilesDir(), "google_temp.png"));
                        TrackScreenFrag.this.m.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                LogUtil.h("Track_TrackScreenFrag", "close IOException ", e.getMessage());
                            }
                        }
                    } catch (Throwable th) {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                LogUtil.h("Track_TrackScreenFrag", obj, e2.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    TrackScreenFrag.this.v = true;
                    LogUtil.h("Track_TrackScreenFrag", "saveBmpToFile:IOException ", e3.getMessage());
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            LogUtil.h("Track_TrackScreenFrag", "close IOException ", e4.getMessage());
                        }
                    }
                } catch (IllegalArgumentException e5) {
                    TrackScreenFrag.this.v = true;
                    LogUtil.h("Track_TrackScreenFrag", "saveBmpToFile:IllegalArgumentException ", e5.getMessage());
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e6) {
                            LogUtil.h("Track_TrackScreenFrag", "close IOException ", e6.getMessage());
                        }
                    }
                }
                obj = TrackScreenFrag.this;
                ((TrackScreenFrag) obj).q = true;
            }
        });
    }

    public boolean b() {
        return this.ae == 2;
    }

    public List<hjd> i() {
        return this.ar;
    }

    public void d(TrackDetailActivity.LoadMapListener loadMapListener) {
        this.af = loadMapListener;
    }

    static class c extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<TrackScreenFrag> f3754a;

        private c(TrackScreenFrag trackScreenFrag) {
            this.f3754a = null;
            this.f3754a = new WeakReference<>(trackScreenFrag);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
            }
            super.handleMessage(message);
            TrackScreenFrag trackScreenFrag = this.f3754a.get();
            if (trackScreenFrag == null) {
                LogUtil.b("Track_TrackScreenFrag", "TrackScreenFragHandler trackScreenFrag is null return");
                return;
            }
            ReleaseLogUtil.e("Track_TrackScreenFrag", "TrackScreenFragHandler received ", Integer.valueOf(message.what));
            switch (message.what) {
                case 2:
                    trackScreenFrag.ah.setVisibility(0);
                    break;
                case 3:
                    trackScreenFrag.ag.loadingEnd();
                    trackScreenFrag.aj();
                    trackScreenFrag.ak();
                    if (kxb.c(trackScreenFrag.as.e().requestSportType())) {
                        trackScreenFrag.ag.showTrackMarkers(true);
                    } else {
                        trackScreenFrag.ag.showTrackMarkers(!UnitUtil.h());
                        trackScreenFrag.f();
                    }
                    trackScreenFrag.ar = trackScreenFrag.ag.requestSimplePoints();
                    trackScreenFrag.aq.setVisibility(0);
                    trackScreenFrag.e = true;
                    trackScreenFrag.aa.start();
                    break;
                case 4:
                    trackScreenFrag.ah.setVisibility(8);
                    trackScreenFrag.aq.setVisibility(0);
                    trackScreenFrag.ak();
                    if (trackScreenFrag.af != null) {
                        trackScreenFrag.af.endLoadMap();
                        break;
                    }
                    break;
                case 5:
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(300L);
                    trackScreenFrag.q();
                    trackScreenFrag.d.startAnimation(alphaAnimation);
                    break;
                case 6:
                    trackScreenFrag.ao();
                    break;
                case 7:
                    trackScreenFrag.e();
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final InterfaceHiMap interfaceHiMap) {
        LogUtil.c("Track_TrackScreenFrag", "initAsyncMap");
        interfaceHiMap.onCreate(null, false, false);
        interfaceHiMap.setAllGesturesEnabled(true);
        interfaceHiMap.saveAddress(this.as.j());
        c(interfaceHiMap);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.9
            @Override // java.lang.Runnable
            public void run() {
                TrackScreenFrag.this.aa = new AnimatorSet();
                TrackScreenFrag trackScreenFrag = TrackScreenFrag.this;
                trackScreenFrag.ab = AnimatorInflater.loadAnimator(trackScreenFrag.i, R.animator._2131034183_res_0x7f050047);
                TrackScreenFrag.this.ab.setTarget(TrackScreenFrag.this.ah);
                Animator loadAnimator = AnimatorInflater.loadAnimator(TrackScreenFrag.this.i, R.animator._2131034182_res_0x7f050046);
                loadAnimator.setTarget(TrackScreenFrag.this.aq);
                TrackScreenFrag.this.aa.playTogether(loadAnimator, TrackScreenFrag.this.ab);
                TrackScreenFrag.this.m();
                interfaceHiMap.registerLoadingListener(new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.9.2
                    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
                    public void onFinish() {
                        LogUtil.a("Track_TrackScreenFrag", "onFinish send MSG_GOOGLE_LOADING_FINISH");
                        TrackScreenFrag.this.n.sendEmptyMessage(3);
                    }

                    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
                    public void onCancel() {
                        LogUtil.a("Track_TrackScreenFrag", "onCancel send MSG_GOOGLE_LOADING_CANCEL");
                        TrackScreenFrag.this.n.sendEmptyMessage(4);
                    }
                });
                MapDataPreprocessor c2 = hky.c(interfaceHiMap.convertCoordinate(TrackScreenFrag.this.as.b()), TrackScreenFrag.this.as.e());
                if (!TrackScreenFrag.this.t) {
                    interfaceHiMap.loadMapWithPreprocessData(c2.preprocess());
                    TrackScreenFrag.this.ah();
                } else {
                    LogUtil.a("Track_TrackScreenFrag", "fragment is finshing");
                }
            }
        });
    }

    private void c(InterfaceHiMap interfaceHiMap) {
        LogUtil.a("Track_TrackScreenFrag", "setDynamicEntryListener");
        interfaceHiMap.setOnMapLoadedListener(new InterfaceMapLoadedCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.7
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback
            public void onMapLoaded() {
                LogUtil.a("Track_TrackScreenFrag", "dynamicEntry onMaploaded");
                if (TrackScreenFrag.this.aa()) {
                    LogUtil.a("Track_TrackScreenFrag", "show dynamicEntry");
                    TrackScreenFrag.this.n.sendEmptyMessage(5);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.aa.addListener(new Animator.AnimatorListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.6
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                TrackScreenFrag.this.n.removeMessages(2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtil.a("Track_TrackScreenFrag", "onAnimationEnd");
                TrackScreenFrag.this.ah.setVisibility(8);
            }
        });
    }

    static class b implements ViewHolderInterface {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f3753a;
        private View ac;
        private HealthTextView b;
        private TrackPaceColorGradientView c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView f;
        private HealthTextView g;
        private HealthTextView h;
        private HealthTextView i;
        private HealthTextView j;
        private HealthTextView k;
        private HealthTextView l;
        private HealthTextView m;
        private HealthTextView n;
        private HealthTextView o;
        private HealthTextView p;
        private LinearLayout q;
        private HealthTextView r;
        private HealthTextView s;
        private HealthTextView t;
        private HealthTextView u;
        private HealthTextView v;
        private HealthTextView w;
        private HealthTextView x;
        private HealthTextView y;

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface
        public hoh getViewCell() {
            return null;
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface
        public void setEventListener() {
        }

        private b(View view) {
            this.q = null;
            this.m = null;
            this.k = null;
            this.i = null;
            this.h = null;
            this.n = null;
            this.o = null;
            this.t = null;
            this.s = null;
            this.g = null;
            this.r = null;
            this.p = null;
            this.b = null;
            this.j = null;
            this.d = null;
            this.f = null;
            this.e = null;
            this.l = null;
            this.w = null;
            this.u = null;
            this.y = null;
            this.x = null;
            this.c = null;
            this.ac = view;
            a();
        }

        private void a() {
            View view = this.ac;
            if (view == null) {
                LogUtil.b("Track_TrackDetailFragViewHold", "initView mView is null!");
                return;
            }
            this.q = (LinearLayout) view.findViewById(R.id.layout_rightdata);
            this.m = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_sport_time_new);
            this.k = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_sport_record_source);
            this.i = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_sport_distance_value);
            this.l = (HealthTextView) this.ac.findViewById(R.id.text_targetUnit);
            this.h = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_sport_during_time);
            this.n = (HealthTextView) this.ac.findViewById(R.id.track_main_page_left_unit);
            this.o = (HealthTextView) this.ac.findViewById(R.id.track_main_page_left_datatype);
            this.t = (HealthTextView) this.ac.findViewById(R.id.track_main_page_mid_datatype);
            this.s = (HealthTextView) this.ac.findViewById(R.id.track_main_page_right_datatype);
            this.g = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_speed_value);
            this.r = (HealthTextView) this.ac.findViewById(R.id.track_main_page_mid_unit);
            this.p = (HealthTextView) this.ac.findViewById(R.id.track_main_page_right_unit);
            this.b = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_calorie_value);
            this.f = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_min_pace);
            this.e = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_max_pace);
            this.j = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_min_pace_value);
            this.d = (HealthTextView) this.ac.findViewById(R.id.track_detail_map_max_pace_value);
            this.w = (HealthTextView) this.ac.findViewById(R.id.triathlon_map_targetValue);
            this.u = (HealthTextView) this.ac.findViewById(R.id.triathlon_map_targetUnit);
            this.y = (HealthTextView) this.ac.findViewById(R.id.triathlon_map_sport_during_time);
            this.x = (HealthTextView) this.ac.findViewById(R.id.triathlon_map_sport_calorie_value);
            this.c = (TrackPaceColorGradientView) this.ac.findViewById(R.id.pace_color_gradient);
            this.f3753a = (HealthTextView) this.ac.findViewById(R.id.map_type);
            this.v = (HealthTextView) this.ac.findViewById(R.id.triathlon_map_type);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(boolean z, String str, String str2, int i, Context context) {
            if (z) {
                c(str2, i, context, str, str2);
                return;
            }
            this.e.setVisibility(8);
            this.f.setVisibility(8);
            this.d.setVisibility(8);
            this.j.setVisibility(8);
        }

        private void c(String str, int i, Context context, String str2, String str3) {
            if (hji.g(i) && context != null && str2 != null && str3 != null) {
                StringBuffer stringBuffer = new StringBuffer(str2);
                StringBuffer stringBuffer2 = new StringBuffer(str3);
                stringBuffer.append(" ");
                stringBuffer2.append(" ");
                if (UnitUtil.h()) {
                    stringBuffer.append(context.getResources().getString(R.string._2130844079_res_0x7f0219af));
                    stringBuffer2.append(context.getResources().getString(R.string._2130844079_res_0x7f0219af));
                } else {
                    stringBuffer.append(context.getResources().getString(R.string._2130844078_res_0x7f0219ae));
                    stringBuffer2.append(context.getResources().getString(R.string._2130844078_res_0x7f0219ae));
                }
                String stringBuffer3 = stringBuffer.toString();
                str3 = stringBuffer2.toString();
                str2 = stringBuffer3;
            }
            if (str2 != null) {
                this.d.setText(str2);
            }
            if (str != null) {
                this.j.setText(str3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        if (this.ag != null) {
            Context context = BaseApplication.getContext();
            Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            if (!(systemService instanceof WindowManager)) {
                LogUtil.a("Track_TrackScreenFrag", "object is not instanceof WindowManager");
                return;
            }
            ((WindowManager) systemService).getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.ag.setLogoPadding(nsn.c(context, 16.0f), 0, 0, nsn.c(context, 48.0f));
        }
    }

    public void c() {
        if (!this.an.d()) {
            LogUtil.b("Track_TrackScreenFrag", "data init not ready, please wait.");
        } else if (!this.an.b()) {
            LogUtil.b("Track_TrackScreenFrag", "GPS data invalid, can not generate dynamic track animation.");
        } else {
            o();
        }
    }

    private void o() {
        startActivity(bga_(true));
    }

    private Intent bga_(boolean z) {
        Intent intent;
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplify_data", this.an.a());
        bundle.putSerializable("lens_data", this.an.e());
        bundle.putSerializable("retrack_data", this.an.c());
        TrackPhotosDownload trackPhotosDownload = this.al;
        if (trackPhotosDownload != null && trackPhotosDownload.d()) {
            LogUtil.a("Track_TrackScreenFrag", "TrackScreenFrag gotoDynamicTrack TrackPhotosDownload matched photos finish");
            bundle.putSerializable("matched_photos", this.al.e());
        }
        if (z) {
            intent = new Intent(getActivity(), (Class<?>) DynamicTrackActivity.class);
        } else {
            intent = new Intent();
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity");
        }
        intent.putExtra("track_detail_data_bundle", bundle);
        intent.addFlags(HiUserInfo.DATA_CLOUD);
        DynamicTrackActivity.c(true);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        haz e = this.an.c(257).c(258).c(259).c(260).c(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE).c(282).c(222).aYn_(this.am).a(this.as.d()).b(this.as.e()).e(this.as.bb());
        hjw hjwVar = this.as;
        e.b(hjwVar.e(hjwVar.e().requestSportType())).d(this.as.e().requestDeviceType()).b(this.ae).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aa() {
        if (this.ag.getMapEngineType() == 3 && com.huawei.common.util.Utils.getHwidVersionCode(this.i) < 50003300) {
            ReleaseLogUtil.e("Track_TrackScreenFrag", "HMS Map version is earlier than 5.0.3.300");
            return false;
        }
        if (!this.an.f()) {
            ReleaseLogUtil.e("Track_TrackScreenFrag", "Sport type is not supported");
            return false;
        }
        if (200 > this.as.j().getTotalDistance()) {
            ReleaseLogUtil.e("Track_TrackScreenFrag", "Track distance is less than 200 m");
            return false;
        }
        ReleaseLogUtil.e("Track_TrackScreenFrag", "Map type is ", Integer.valueOf(this.ae));
        int i = this.ae;
        return i == 1 || i == 3 || i == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        int d2;
        int b2;
        if (aa()) {
            HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.i, 0);
            if (this.l.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.l.getLayoutParams();
                if (this.as.j().getTotalDistance() >= 100000) {
                    d2 = ((int) healthColumnSystem.d(2)) + nrr.b(this.i);
                    b2 = nsn.c(this.i, 12.0f);
                } else {
                    d2 = (int) healthColumnSystem.d(2);
                    b2 = nrr.b(this.i);
                }
                layoutParams.setMarginStart(d2 + b2);
                this.l.setLayoutParams(layoutParams);
            }
            this.l.setAutoTextInfo(9, 1, 1);
            this.d.setVisibility(0);
            return;
        }
        this.d.setVisibility(8);
    }

    private void am() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.track_detail_route_operation_view, (ViewGroup) null);
        final HealthRadioButton healthRadioButton = (HealthRadioButton) inflate.findViewById(R.id.save_to_my_route_radio_button);
        final HealthRadioButton healthRadioButton2 = (HealthRadioButton) inflate.findViewById(R.id.rb_export_route);
        inflate.findViewById(R.id.rel_save_to_my_route).setOnClickListener(new View.OnClickListener() { // from class: hiw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackScreenFrag.bgh_(HealthRadioButton.this, healthRadioButton2, view);
            }
        });
        inflate.findViewById(R.id.rel_export_route).setOnClickListener(new View.OnClickListener() { // from class: his
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackScreenFrag.bgi_(HealthRadioButton.this, healthRadioButton, view);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(getContext());
        builder.a(getString(R.string._2130840005_res_0x7f0209c5)).czh_(inflate, 0, 0).czc_(R.string._2130837555_res_0x7f020033, new View.OnClickListener() { // from class: hit
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackScreenFrag.bgj_(view);
            }
        }).cze_(R.string._2130837648_res_0x7f020090, new View.OnClickListener() { // from class: hiz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackScreenFrag.this.bgl_(healthRadioButton, view);
            }
        });
        builder.e().show();
    }

    public static /* synthetic */ void bgh_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, View view) {
        if (!healthRadioButton.isChecked()) {
            healthRadioButton.setChecked(true);
            healthRadioButton2.setChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void bgi_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, View view) {
        if (!healthRadioButton.isChecked()) {
            healthRadioButton.setChecked(true);
            healthRadioButton2.setChecked(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void bgj_(View view) {
        LogUtil.a("Track_TrackScreenFrag", "createRouteDialog onClick Negative view");
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bgl_(HealthRadioButton healthRadioButton, View view) {
        LogUtil.a("Track_TrackScreenFrag", "createRouteDialog onClick positive view");
        hju.a(healthRadioButton.isChecked(), this.as);
        e(healthRadioButton.isChecked());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(boolean z) {
        if (z) {
            AppRouter.b("/HWUserProfileMgr/EditRouteActivity").e("is_add", true).e("IS_NEED_CHANGE_POINT", true).c(this.i);
        } else {
            AppRouter.b("/HWUserProfileMgr/RouteExportActivity").e("IS_NEED_CHANGE_POINT", true).c(this.i);
        }
    }

    private void a(Map<String, String> map) {
        if (!"1".equals(map.get("finishState"))) {
            LogUtil.a("Track_TrackScreenFrag", "route not unfinished in initRouteDrawView");
            return;
        }
        hmj hmjVar = new hmj(this.i, this.au, this);
        this.aj = hmjVar;
        hmjVar.e(map, new hmj.e(this), new hmj.a(this));
    }

    public void d() {
        d(new SyncMapCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag.12
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public void onMapReady(InterfaceHiMap interfaceHiMap) {
                if (TrackScreenFrag.this.aj != null) {
                    TrackScreenFrag.this.aj.d(TrackScreenFrag.this.ag, !TrackScreenFrag.this.ac);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        hmj hmjVar = this.aj;
        if (hmjVar == null) {
            LogUtil.b("Track_TrackScreenFrag", "mRouteDrawViewHolder is null in mRouteDrawViewHolder");
        } else {
            hmjVar.e();
        }
    }

    private void b(boolean z) {
        if (this.aj == null) {
            LogUtil.b("Track_TrackScreenFrag", "mRouteDrawViewHolder is null in showShapeTab");
            return;
        }
        this.y = z;
        hjw hjwVar = this.as;
        if (hjwVar != null) {
            hjwVar.d(z);
        }
        this.aj.b(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        hmj hmjVar;
        if (this.y && (hmjVar = this.aj) != null) {
            hmjVar.d();
        } else {
            this.ag.moveToCenter();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        hjw hjwVar = this.as;
        if (hjwVar == null) {
            LogUtil.b("Track_TrackScreenFrag", "mTrackDetailDataManager is null in closeKmButton");
        } else if (hjr.a(hjwVar.e())) {
            c(false);
        }
    }

    public void bgm_(Bitmap bitmap) {
        if (this.as == null) {
            LogUtil.h("Track_TrackScreenFrag", "mTrackDetailDataManager is null");
        } else {
            this.as.c(nrf.cJs_(hjr.bhb_(-1, bitmap), jcs.b));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
