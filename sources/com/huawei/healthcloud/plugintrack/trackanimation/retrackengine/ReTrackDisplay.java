package com.huawei.healthcloud.plugintrack.trackanimation.retrackengine;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.MarkerBuilder;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapMarkerClickCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.hag;
import defpackage.hah;
import defpackage.hak;
import defpackage.hau;
import defpackage.hbx;
import defpackage.hbz;
import defpackage.hca;
import defpackage.hcb;
import defpackage.hcr;
import defpackage.hjd;
import defpackage.hjg;
import defpackage.hji;
import defpackage.hkt;
import defpackage.hla;
import defpackage.hld;
import defpackage.hle;
import defpackage.hlg;
import defpackage.hlh;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nsy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class ReTrackDisplay extends ReTrackEngine {

    /* renamed from: a, reason: collision with root package name */
    private static final Pair<Float, Float> f3593a;
    private static final Pair<Float, Float> b;
    private static final Pair<Float, Float> c;
    private static final Pair<Float, Float> e;
    private byte[] aa;
    private hah ab;
    private InterfaceMapStatusChangeCallback ac;
    private InterfaceMapMarkerClickCallback ad;
    private MarkerBuilder ae;
    private int af;
    private b ag;
    private ArrayList<Integer> ah;
    private List<Double> ai;
    private a aj;
    private hlh ak;
    private VideoView al;
    private List<Integer> am;
    private int an;
    private long ao;
    private int ap;
    private InterfaceUpdateReTrack aq;
    private int ar;
    private List<Pair<Integer, Integer>> d;
    private List<Pair<Integer, List<hjd>>> f;
    private AlphaAnimation g;
    private SparseIntArray h;
    private AnimatorSet i;
    private Map<Integer, String> j;
    private boolean k;
    private HashMap<Integer, Integer> l;
    private Map<Integer, List<Bitmap>> m;
    private Map<Integer, ArrayList<VideoModel>> n;
    private HashMap<Integer, Integer> o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private List<hjd> u;
    private c v;
    private long w;
    private InterfaceMapLoadedCallback x;
    private volatile boolean y;
    private MapTypeDescription z;

    enum KeyExerciseType {
        MAX_HEART_RATE,
        MAX_SPEED,
        MAX_ALTITUDE
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setScreenHeight(int i) {
    }

    static /* synthetic */ long c(ReTrackDisplay reTrackDisplay, long j) {
        long j2 = reTrackDisplay.ao + j;
        reTrackDisplay.ao = j2;
        return j2;
    }

    static {
        Float valueOf = Float.valueOf(0.5f);
        e = new Pair<>(valueOf, valueOf);
        f3593a = new Pair<>(valueOf, Float.valueOf(1.0f));
        Float valueOf2 = Float.valueOf(0.9f);
        b = new Pair<>(valueOf, valueOf2);
        c = new Pair<>(Float.valueOf(0.3f), valueOf2);
    }

    private TrackAnimationControl g() {
        int d2 = gwg.d(this.mContext);
        if (d2 == 1) {
            return new hbx(this.mTrackData, this.mLensData, this.mTrackSimplify);
        }
        if (d2 == 3) {
            return new hca(this.mTrackData, this.mLensData, this.mTrackSimplify);
        }
        if (d2 == 2) {
            return new hcb(this.mTrackData, this.mLensData, this.mTrackSimplify);
        }
        return new hbx(this.mTrackData, this.mLensData, this.mTrackSimplify);
    }

    private void n() {
        Context context = this.mContext;
        Context context2 = this.mContext;
        SharedPreferences sharedPreferences = context.getSharedPreferences("retrack_file", 0);
        if ("retrack_mode_simple".equals(sharedPreferences.getString("retrack_mode_key", "retrack_mode_complex"))) {
            this.mStrength = TrackAnimationControl.Strength.LOW;
        } else {
            this.mStrength = TrackAnimationControl.Strength.HIGH;
        }
        this.mIsSupportArea = sharedPreferences.getBoolean("retrack_area_key", true);
        if (this.mMapEngine.getMapEngineType() == 1) {
            this.mIsSupportArea = false;
        }
    }

    public ReTrackDisplay(Handler handler, ArrayList<LatLong> arrayList, ArrayList<LenLatLong> arrayList2, ReTrackSimplify reTrackSimplify) {
        super(handler, arrayList, arrayList2, reTrackSimplify);
        this.af = -1;
        this.f = null;
        this.u = null;
        this.ai = null;
        this.d = null;
        this.t = false;
        this.aa = null;
        this.z = null;
        this.ak = null;
        this.aj = new a();
        this.ag = new b();
        this.v = new c();
        this.ae = new MarkerBuilder();
        this.ab = null;
        this.s = false;
        this.y = false;
        this.p = false;
        this.m = new HashMap();
        this.n = new HashMap();
        this.j = new HashMap();
        this.i = new AnimatorSet();
        this.g = null;
        this.ah = new ArrayList<>();
        this.o = new HashMap<>();
        this.l = new HashMap<>();
        this.am = new ArrayList();
        this.h = new SparseIntArray();
        this.ad = new InterfaceMapMarkerClickCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.2
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapMarkerClickCallback
            public void onMarkerClick(int i) {
                int i2;
                if (i == -1 || (i2 = ReTrackDisplay.this.h.get(i, -1)) == -1) {
                    return;
                }
                ReTrackDisplay.this.mMsgHandler.sendMessage(ReTrackDisplay.this.mMsgHandler.obtainMessage(129, Integer.valueOf(i2)));
            }
        };
        this.ar = 0;
        this.ap = 0;
        this.k = false;
        this.q = false;
        this.x = new InterfaceMapLoadedCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.6
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback
            public void onMapLoaded() {
                if (ReTrackDisplay.this.mReTrackStateManager.e() == -1 || ReTrackDisplay.this.mMapEngine == null) {
                    LogUtil.b("Track_ReTrackDisplay", "in loaded,track engine is error");
                    return;
                }
                if (!(ReTrackDisplay.this.mContext instanceof Activity) || !((Activity) ReTrackDisplay.this.mContext).isFinishing()) {
                    ReTrackDisplay.this.p();
                    ReTrackDisplay.this.mMapEngine.showPureMap();
                    ReTrackDisplay.this.mReTrackStateManager.b(1);
                    ReTrackDisplay.this.q();
                    return;
                }
                LogUtil.h("Track_ReTrackDisplay", "Activity is finishing in onMapLoaded");
            }
        };
        this.aq = new h();
        this.ac = new InterfaceMapStatusChangeCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.10
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback
            public void onMapStatusChange(hlh hlhVar) {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback
            public void onMapStatusChangeFinish(hlh hlhVar) {
                ReTrackDisplay.this.r = true;
                ReTrackDisplay.this.q();
            }
        };
    }

    public ReTrackDisplay e(boolean z) {
        this.t = !z;
        return this;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setIsHideKmMarker(boolean z) {
        this.q = z;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    public ReTrackEngine setContext(Context context) {
        this.ae = new MarkerBuilder(context);
        return super.setContext(context);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addStartMarker() {
        hlg hlgVar = new hlg();
        hlgVar.d(this.mTrackData.get(0).getLatLng());
        if (this.ab == null) {
            hlgVar.bhP_(b);
            hlgVar.e(this.ae.d(MarkerBuilder.KeyInfoType.START_Marker, this.mTrackSimplify.getSportType(), j()));
            hlgVar.bhQ_(this.ae.aZa_(MarkerBuilder.KeyInfoType.START_Marker, this.mTrackSimplify.getSportType(), j()));
        } else {
            hlgVar.bhP_(new Pair<>(Float.valueOf(0.5f), Float.valueOf(0.5f)));
            hlgVar.bhQ_(hcr.bag_(this.mContext, this.ab.h()));
        }
        this.h.append(this.mMapEngine.addMarker(hlgVar, null), 0);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addEndMarker() {
        if (this.af != -1) {
            this.mMapEngine.hideMarker(this.af, null);
            this.af = -1;
        }
        hlg hlgVar = new hlg();
        if (this.mTrackData.size() > 0) {
            hlgVar.d(this.mTrackData.get(this.mTrackData.size() - 1).getLatLng());
        }
        if (this.ab == null) {
            hlgVar.bhP_(b);
            hlgVar.e(this.ae.d(MarkerBuilder.KeyInfoType.END_Marker, this.mTrackSimplify.getSportType(), j()));
            Bitmap aUI_ = gwe.aUI_(this.mCustomMarkerList);
            if (aUI_ == null) {
                hlgVar.bhQ_(this.ae.aZa_(MarkerBuilder.KeyInfoType.END_Marker, this.mTrackSimplify.getSportType(), j()));
            } else {
                hlgVar.bhQ_(aUI_);
            }
        } else {
            hlgVar.bhP_(new Pair<>(Float.valueOf(0.5f), Float.valueOf(0.5f)));
            hlgVar.bhQ_(hcr.bag_(this.mContext, this.ab.c()));
        }
        this.h.append(this.mMapEngine.addMarker(hlgVar, null), this.mTrackData.size() - 1);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addMoveMarker() {
        hlg hlgVar = new hlg();
        hlgVar.bhP_(e);
        hlgVar.d(this.mTrackData.get(0).getLatLng());
        hlgVar.e(this.ae.d(MarkerBuilder.KeyInfoType.ADVANCE_Marker, this.mTrackSimplify.getSportType(), j()));
        hlgVar.bhQ_(this.ae.aZa_(MarkerBuilder.KeyInfoType.ADVANCE_Marker, this.mTrackSimplify.getSportType(), j()));
        this.af = this.mMapEngine.addMarker(hlgVar, null);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addMarker(LatLong latLong, Bitmap bitmap) {
        hlg hlgVar = new hlg();
        hlgVar.bhP_(f3593a);
        hlgVar.d(latLong.getLatLng());
        hlgVar.bhQ_(bitmap);
        this.mMapEngine.addMarker(hlgVar, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GrowAnimationBuilder.MarkerType b(int i) {
        if (i == 1) {
            return GrowAnimationBuilder.MarkerType.KM_MARKER;
        }
        if (i == 3) {
            return GrowAnimationBuilder.MarkerType.KEYDATA_MARKER;
        }
        if (i == 4) {
            return GrowAnimationBuilder.MarkerType.ALBUM_MARKER;
        }
        if (i == 5) {
            return GrowAnimationBuilder.MarkerType.VIDEO_MARKER;
        }
        return GrowAnimationBuilder.MarkerType.KM_MARKER;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addGrowAnimationMarker(hlg hlgVar, int i, final int i2) {
        ReleaseLogUtil.e("Track_ReTrackDisplay", "addGrowAnimationMarker: type is ", Integer.valueOf(i));
        GrowAnimationBuilder growAnimation = this.mMapEngine.getGrowAnimation();
        growAnimation.setType(b(i));
        growAnimation.setFrictionAnimationDuration(400L);
        if (i == 3) {
            this.mMsgHandler.postDelayed(new Runnable() { // from class: hbt
                @Override // java.lang.Runnable
                public final void run() {
                    ReTrackDisplay.this.d(i2);
                }
            }, 410L);
        }
        this.d.add(new Pair<>(Integer.valueOf(i), Integer.valueOf(this.mMapEngine.addMarker(hlgVar, growAnimation))));
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void d(int i) {
        if (!this.aj.b() || this.m.containsKey(Integer.valueOf(i)) || this.j.containsKey(Integer.valueOf(i)) || this.n.containsKey(Integer.valueOf(i))) {
            return;
        }
        this.aj.g();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addMarkerAnimation(LatLong latLong, final int i, final int i2, InterfaceUpdateReTrack.MarkerType markerType) {
        LogUtil.a("Track_ReTrackDisplay", "addMarkerAnimation: ");
        hlg hlgVar = new hlg();
        hlgVar.bhP_(b);
        hlgVar.d(latLong.getLatLng());
        if (b(markerType, hlgVar)) {
            return;
        }
        GrowAnimationBuilder growAnimation = this.mMapEngine.getGrowAnimation();
        growAnimation.setType(b(i));
        growAnimation.setFrictionAnimationDuration(400L);
        int addMarker = this.mMapEngine.addMarker(hlgVar, growAnimation);
        this.mMsgHandler.postDelayed(new Runnable() { // from class: hbw
            @Override // java.lang.Runnable
            public final void run() {
                ReTrackDisplay.this.b(i, i2);
            }
        }, 410L);
        this.mAlbumMarkers.put(Integer.valueOf(i2), markerType);
        this.o.put(Integer.valueOf(i2), Integer.valueOf(addMarker));
    }

    public /* synthetic */ void b(int i, int i2) {
        LogUtil.a("Track_ReTrackDisplay", "onFinish: addMarkerAnimation type is ", Integer.valueOf(i), " index = ", Integer.valueOf(i2));
        Message obtainMessage = this.mMsgHandler.obtainMessage();
        Map<Integer, List<Bitmap>> map = this.m;
        if (map != null && map.containsKey(Integer.valueOf(i2))) {
            obtainMessage.what = 116;
            obtainMessage.obj = Integer.valueOf(i2);
            this.mMsgHandler.sendMessage(obtainMessage);
            return;
        }
        Map<Integer, ArrayList<VideoModel>> map2 = this.n;
        if (map2 != null && map2.containsKey(Integer.valueOf(i2))) {
            obtainMessage.what = OldToNewMotionPath.SPORT_TYPE_TENNIS;
            obtainMessage.obj = Integer.valueOf(i2);
            this.mMsgHandler.sendMessage(obtainMessage);
        } else {
            this.ag.b();
            this.aj.g();
        }
    }

    private boolean b(InterfaceUpdateReTrack.MarkerType markerType, hlg hlgVar) {
        Bitmap aZe_;
        int i = AnonymousClass8.e[markerType.ordinal()];
        if (i == 1) {
            hlgVar.e(this.ae.d());
            aZe_ = this.ae.aZe_();
        } else if (i == 2) {
            hlgVar.e(this.ae.a());
            aZe_ = this.ae.aZc_();
        } else if (i != 3) {
            aZe_ = null;
        } else {
            hlgVar.e(this.ae.b());
            aZe_ = this.ae.aZf_();
        }
        if (aZe_ == null) {
            return true;
        }
        hlgVar.bhQ_(aZe_);
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addAlbumAnimation(final int i) {
        if (this.mIsExitReTrack) {
            return;
        }
        this.i = new AnimatorSet();
        List<Bitmap> list = this.m.get(Integer.valueOf(i));
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                this.mTrackPhotoList.get(i2).setImageBitmap(list.get(i2));
            }
        }
        this.i.playTogether(aYO_(this.mTrackPhotoList.get(0)), aYN_(this.mTrackPhotoBackound));
        this.i.addListener(new Animator.AnimatorListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.9
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ReTrackDisplay.this.mTrackPhotoBackound.setVisibility(0);
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(121);
                ReTrackDisplay.this.mTrackPhotoList.get(0).setVisibility(0);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ReTrackDisplay reTrackDisplay = ReTrackDisplay.this;
                reTrackDisplay.a(0, (List<Bitmap>) reTrackDisplay.m.get(Integer.valueOf(i)));
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                ReTrackDisplay.this.mTrackPhotoList.get(0).setVisibility(8);
                ReTrackDisplay.this.mTrackPhotoBackound.setVisibility(8);
            }
        });
        if (this.j.containsKey(Integer.valueOf(i))) {
            Message obtain = Message.obtain();
            obtain.what = 117;
            obtain.obj = this.j.get(Integer.valueOf(i));
            this.mMsgHandler.sendMessage(obtain);
        }
        this.i.start();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    public void playVideo(int i) {
        LogUtil.a("Track_ReTrackDisplay", "playVideo: ", Integer.valueOf(i));
        Map<Integer, ArrayList<VideoModel>> map = this.n;
        if (map == null || !map.containsKey(Integer.valueOf(i)) || koq.b(this.n.get(Integer.valueOf(i)))) {
            LogUtil.h("Track_ReTrackDisplay", "playVideo: mIndexOfVideoPath ==null or not containsKey");
            this.aj.g();
            return;
        }
        if (this.n.get(Integer.valueOf(i)).get(0) == null || TextUtils.isEmpty(this.n.get(Integer.valueOf(i)).get(0).getVideoPath())) {
            LogUtil.h("Track_ReTrackDisplay", "playVideo: mIndexOfVideoPath ==null or VideoPath is empty");
            this.aj.g();
            return;
        }
        VideoView videoView = this.al;
        if (videoView == null) {
            LogUtil.h("Track_ReTrackDisplay", "playVideo: playeView is null");
            k();
            return;
        }
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: hbq
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                ReTrackDisplay.this.aYS_(mediaPlayer);
            }
        });
        this.al.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: hbp
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(0.0f, 0.0f);
            }
        });
        try {
            String videoPath = this.n.get(Integer.valueOf(i)).get(0).getVideoPath();
            LogUtil.a("Track_ReTrackDisplay", "playVideo: videoPath is ", videoPath);
            this.al.setVideoPath(videoPath);
            this.mTrackPhotoBackound.setAlpha(0.6f);
            this.mTrackPhotoBackound.setVisibility(0);
            this.al.start();
            this.al.setVisibility(0);
        } catch (IllegalStateException e2) {
            LogUtil.h("Track_ReTrackDisplay", "playVideo: ", e2.toString());
            k();
        }
        if (this.j.containsKey(Integer.valueOf(i))) {
            Message obtain = Message.obtain();
            obtain.what = 117;
            obtain.obj = this.j.get(Integer.valueOf(i));
            this.mMsgHandler.sendMessage(obtain);
            this.mMsgHandler.sendEmptyMessageDelayed(120, 2000L);
        }
    }

    public /* synthetic */ void aYS_(MediaPlayer mediaPlayer) {
        LogUtil.a("Track_ReTrackDisplay", "playVideo: Completion");
        k();
    }

    private void k() {
        this.al.setVisibility(8);
        this.mTrackPhotoBackound.setVisibility(8);
        this.mTrackPhotoBackound.setAlpha(1.0f);
        this.aj.g();
    }

    private Animator aYO_(ImageView imageView) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(imageView, PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f));
        ofPropertyValuesHolder.setDuration(350L);
        ofPropertyValuesHolder.setInterpolator(new hkt(0.2f, 0.0f, 0.2f, 1.0f));
        return ofPropertyValuesHolder;
    }

    private Animator aYN_(FrameLayout frameLayout) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(frameLayout, PropertyValuesHolder.ofFloat("alpha", 0.0f, 0.6f));
        ofPropertyValuesHolder.setDuration(200L);
        ofPropertyValuesHolder.setInterpolator(new hkt(0.2f, 0.0f, 0.2f, 1.0f));
        return ofPropertyValuesHolder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final List<Bitmap> list) {
        LogUtil.a("Track_ReTrackDisplay", "numberOfPhotos:", String.valueOf(i));
        if (this.mIsExitReTrack) {
            this.mTrackPhotoBackound.setVisibility(8);
            this.mMsgHandler.sendEmptyMessage(120);
            this.mMsgHandler.sendEmptyMessage(128);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.95f);
        this.g = alphaAnimation;
        alphaAnimation.setDuration(800L);
        this.g.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.11
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (!ReTrackDisplay.this.mIsExitReTrack) {
                    ReTrackDisplay.this.c(i, (List<Bitmap>) list);
                    return;
                }
                ReTrackDisplay.this.mTrackPhotoBackound.setVisibility(8);
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(120);
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(128);
            }
        });
        if (koq.d(this.mTrackPhotoList, i)) {
            this.mTrackPhotoList.get(i).startAnimation(this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i, final List<Bitmap> list) {
        this.i = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        arrayList.add(aYQ_(this.mTrackPhotoList.get(i)));
        int i2 = i + 1;
        if (i2 < list.size()) {
            this.mTrackPhotoList.get(i2).setVisibility(0);
            arrayList.add(aYP_(this.mTrackPhotoList.get(i2)));
        }
        this.i.setDuration(500L);
        this.i.setInterpolator(new hkt(0.2f, 0.0f, 0.2f, 1.0f));
        this.i.playTogether(arrayList);
        this.i.addListener(new Animator.AnimatorListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.14
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int i3;
                ReTrackDisplay.this.mTrackPhotoList.get(i).setVisibility(8);
                ReTrackDisplay.this.mTrackPhotoList.get(i).setTranslationX(0.0f);
                if (i != list.size() - 1 && (i3 = i) != 2) {
                    ReTrackDisplay.this.a(i3 + 1, (List<Bitmap>) list);
                    return;
                }
                ReTrackDisplay.this.ag.b();
                ReTrackDisplay.this.aj.g();
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(120);
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(128);
                ReTrackDisplay.this.mTrackPhotoBackound.setVisibility(8);
            }
        });
        this.i.start();
    }

    private Animator aYQ_(ImageView imageView) {
        return ObjectAnimator.ofPropertyValuesHolder(imageView, PropertyValuesHolder.ofFloat("translationX", 0.0f, -this.an));
    }

    private Animator aYP_(ImageView imageView) {
        return ObjectAnimator.ofPropertyValuesHolder(imageView, PropertyValuesHolder.ofFloat("translationX", this.an, 0.0f));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay$15] */
    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void hideAnimationMarker() {
        new Thread("Track_ReTrackDisplay") { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.15
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (ReTrackDisplay.this.d == null || ReTrackDisplay.this.d.size() < 1) {
                    ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(32);
                    return;
                }
                if (ReTrackDisplay.this.mIsExitReTrack) {
                    return;
                }
                e eVar = new e();
                for (int i = 0; i < ReTrackDisplay.this.d.size(); i++) {
                    eVar.d(i);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                        LogUtil.h("Track_ReTrackDisplay", "error in thread sleep");
                    }
                }
                try {
                    Thread.sleep(400L);
                } catch (InterruptedException unused2) {
                    LogUtil.h("Track_ReTrackDisplay", "error in thread sleep");
                }
            }
        }.start();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void drawLine(LatLong latLong, LatLong latLong2) {
        int parseColor;
        hld hldVar = new hld();
        hah hahVar = this.ab;
        if (hahVar == null) {
            parseColor = latLong.getLineStatus() == -1 ? ReTrackEngine.d.f3607a : ReTrackEngine.d.d;
        } else {
            parseColor = Color.parseColor(hahVar.g());
        }
        hldVar.a(latLong.getLineStatus() == -1).a(latLong.getLatLng()).c(latLong2.getLatLng()).c(true).c(parseColor).b(hag.a(4.0f));
        this.mMapEngine.drawLine(hldVar);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void drawLines(List<LatLong> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        int i = 0;
        while (i < list.size() - 1) {
            LatLong latLong = list.get(i);
            i++;
            drawLine(latLong, list.get(i));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void moveMarker(LatLong latLong) {
        if (this.af != -1) {
            this.mMapEngine.moveMarker(this.af, latLong.getLatLng());
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void initialZoomAnimation(float f2) {
        hlh hlhVar = new hlh();
        hlh e2 = hlhVar.c(this.mTrackData.get(0).getLatLng()).e(f2);
        Float valueOf = Float.valueOf(0.0f);
        e2.d(0.0f).b(0.0f);
        ReleaseLogUtil.e("Track_ReTrackDisplay", "inititalZoomAnimation zoom:", Float.valueOf(f2), " bearing:", valueOf, " tilt:", valueOf, " duration:", 1000L);
        this.mCameraId++;
        final long j = this.mCameraId;
        this.mMapEngine.animateCamera(hlhVar, 1000L, new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.13
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onCancel() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onFinish() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mCameraId != j) {
                    return;
                }
                LogUtil.a("Track_ReTrackDisplay", "initialZoomAnimation finish");
                Message obtain = Message.obtain();
                obtain.what = 49;
                obtain.obj = Float.valueOf(ReTrackDisplay.this.mTrackAnimationControl.getRunTiltAngle());
                ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void initialWhirlAnimation(float f2) {
        float f3;
        if (!this.mLensData.get(0).isTurnState()) {
            f3 = 0.0f;
        } else if (this.mMapEngine.isClockwise()) {
            f3 = 360.0f - ((float) this.mLensData.get(0).getAngle());
        } else {
            f3 = (float) this.mLensData.get(0).getAngle();
        }
        hlh mapStatus = this.mMapEngine.getMapStatus();
        mapStatus.d(f3).b(f2);
        LogUtil.a("Track_ReTrackDisplay", "initialWhirlAnimation bearing:", Float.valueOf(f3), " tilt:", Float.valueOf(f2), " duration:", 500L);
        this.mCameraId++;
        final long j = this.mCameraId;
        this.mMapEngine.animateCamera(mapStatus, 500L, new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.12
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onCancel() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onFinish() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mCameraId != j) {
                    return;
                }
                LogUtil.a("Track_ReTrackDisplay", "initialWhirlAnimation finish");
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(64);
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void finalZoomAnimation(final float f2) {
        hlh hlhVar = new hlh();
        hlh e2 = hlhVar.c(this.mTrackSimplify.getCenterPoint().getLatLng()).e(f2);
        Float valueOf = Float.valueOf(0.0f);
        e2.d(0.0f).b(0.0f);
        ReleaseLogUtil.e("Track_ReTrackDisplay", "finalZoomAnimation bearing:", valueOf, " tilt:", valueOf, " duration:", 1000L);
        this.mCameraId++;
        final long j = this.mCameraId;
        this.mMapEngine.animateCamera(hlhVar, 1000L, new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.17
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onFinish() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mCameraId != j) {
                    return;
                }
                LogUtil.a("Track_ReTrackDisplay", "finalZoomAnimation finish");
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(114);
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onCancel() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mMapEngine.getMapZoom() - f2 > 1.0E-6d || ReTrackDisplay.this.mMapEngine.getMapEngineType() != 3) {
                    return;
                }
                LogUtil.a("Track_ReTrackDisplay", "retry finalZoomAnimation");
                ReTrackDisplay.this.finalZoomAnimation(f2);
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void finalWhirlAnimation(final float f2) {
        hlh mapStatus = this.mMapEngine.getMapStatus();
        mapStatus.d(0.0f).b(f2);
        LogUtil.a("Track_ReTrackDisplay", "finalWhirlAnimation bearing:", Float.valueOf(0.0f), " tilt:", Float.valueOf(f2), " duration:", 500L);
        this.mCameraId++;
        final long j = this.mCameraId;
        this.mMapEngine.animateCamera(mapStatus, 500L, new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.1
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onFinish() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mCameraId != j) {
                    return;
                }
                LogUtil.a("Track_ReTrackDisplay", "finalWhirlAnimation finish");
                Message obtain = Message.obtain();
                obtain.what = 50;
                obtain.obj = Float.valueOf(ReTrackDisplay.this.ak.c());
                ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onCancel() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mMapEngine.getMapTilt() - f2 > 1.0E-6d || ReTrackDisplay.this.mMapEngine.getMapEngineType() != 3) {
                    return;
                }
                LogUtil.a("Track_ReTrackDisplay", "retry finalWhirlAnimation");
                ReTrackDisplay.this.finalWhirlAnimation(f2);
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addGpsTrackLine() {
        addStartMarker();
        addEndMarker();
        Set<Map.Entry<Integer, InterfaceUpdateReTrack.MarkerType>> entrySet = this.mAlbumMarkers.entrySet();
        if (koq.c(entrySet)) {
            for (Map.Entry<Integer, InterfaceUpdateReTrack.MarkerType> entry : entrySet) {
                InterfaceUpdateReTrack.MarkerType value = entry.getValue();
                if (value != null) {
                    addAlbumMarker(entry.getKey().intValue(), value);
                }
            }
        }
        b();
        for (int i = 0; i < this.f.size(); i++) {
            hle hleVar = new hle();
            hleVar.e((List) this.f.get(i).second);
            hah hahVar = this.ab;
            if (hahVar == null) {
                hleVar.c(((Integer) this.f.get(i).first).intValue() > 0 ? ReTrackEngine.d.d : ReTrackEngine.d.f3607a);
            } else {
                hleVar.c(Color.parseColor(hahVar.g()));
            }
            hleVar.a(((Integer) this.f.get(i).first).intValue() <= 0);
            hleVar.b(hag.a(4.0f));
            this.mMapEngine.drawLines(hleVar);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addUnpaintedGpsTrackLine() {
        addStartMarker();
        addEndMarker();
        Set<Map.Entry<Integer, InterfaceUpdateReTrack.MarkerType>> entrySet = this.mAlbumMarkers.entrySet();
        if (koq.c(entrySet)) {
            for (Map.Entry<Integer, InterfaceUpdateReTrack.MarkerType> entry : entrySet) {
                InterfaceUpdateReTrack.MarkerType value = entry.getValue();
                if (value != null) {
                    Integer key = entry.getKey();
                    addAlbumMarker(key.intValue(), value);
                    LogUtil.a("Track_ReTrackDisplay", "mAlbumMarkers:", String.valueOf(key));
                }
            }
        }
        b();
        for (int i = 0; i < this.f.size(); i++) {
            hle hleVar = new hle();
            hleVar.e((List) this.f.get(i).second);
            hleVar.c(((Integer) this.f.get(i).first).intValue() > 0 ? ReTrackEngine.d.c : ReTrackEngine.d.b);
            hleVar.a(((Integer) this.f.get(i).first).intValue() <= 0);
            hleVar.b(hag.a(4.0f));
            this.mMapEngine.drawLines(hleVar);
        }
    }

    private void b() {
        if (this.f == null) {
            ArrayList arrayList = new ArrayList();
            this.f = new ArrayList();
            this.ai = new ArrayList();
            int i = 1;
            int i2 = 0;
            for (int i3 = 0; i3 < this.mTrackData.size(); i3++) {
                int lineStatus = this.mTrackData.get(i3).getLineStatus();
                if (lineStatus > 0) {
                    this.ai.add(Double.valueOf(1.0d));
                    i2++;
                } else {
                    this.ai.add(Double.valueOf(-1.0d));
                }
                arrayList.add(this.mTrackData.get(i3).getLatLng());
                if (i * lineStatus < 0) {
                    if (arrayList.size() > 0) {
                        this.f.add(new Pair<>(Integer.valueOf(i), arrayList));
                    }
                    arrayList = new ArrayList();
                    arrayList.add(this.mTrackData.get(i3).getLatLng());
                    i = lineStatus;
                }
            }
            if (arrayList.size() > 0) {
                this.f.add(new Pair<>(Integer.valueOf(i), arrayList));
            }
            new d().a(i2);
            this.v.e();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addLenTrackLine() {
        if (this.t) {
            if (this.u == null) {
                this.u = new ArrayList();
                for (int i = 0; i < this.mLensData.size(); i++) {
                    this.u.add(this.mLensData.get(i).getLatLng());
                }
            }
            hle hleVar = new hle();
            hleVar.e(this.u);
            hleVar.c(ReTrackEngine.d.e);
            hleVar.a(false);
            hleVar.b(hag.a(4.0f));
            this.mMapEngine.drawLines(hleVar);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void addCustomMarkerList() {
        if (koq.b(this.mCustomMarkerList)) {
            LogUtil.h("Track_ReTrackDisplay", "mMarkersList is empty.");
            return;
        }
        for (int i = 1; i < this.mCustomMarkerList.size() - 1; i++) {
            hjg hjgVar = this.mCustomMarkerList.get(i);
            if (hjgVar == null) {
                ReleaseLogUtil.c("Track_ReTrackDisplay", "hiHealthMarker is null.");
            } else {
                hlg hlgVar = new hlg();
                hlgVar.d(hjgVar.b()).e(hjgVar.a(), hjgVar.c()).bhQ_(nrf.cHF_(hjgVar.bgH_()));
                this.mMapEngine.addMarker(hlgVar, null);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay$3] */
    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void trackMoveLooper() {
        new Thread("Track_ReTrackDisplay") { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                ReTrackDisplay.this.addStartMarker();
                ReTrackDisplay.this.addMoveMarker();
                f fVar = new f();
                ReTrackDisplay.this.d = new ArrayList();
                ReTrackDisplay.this.aj.c();
                ReTrackDisplay.this.ag.a();
                ReTrackDisplay.this.ao = 0L;
                int i = 0;
                ReTrackDisplay.this.s = false;
                ReTrackDisplay.this.y = false;
                long currentTimeMillis = System.currentTimeMillis();
                while (!ReTrackDisplay.this.mIsExitReTrack) {
                    if (!ReTrackDisplay.this.aj.a()) {
                        if (i >= ReTrackDisplay.this.mTrackData.size()) {
                            fVar.c();
                            ReTrackDisplay.this.addEndMarker();
                            fVar.d();
                            fVar.b();
                            return;
                        }
                        fVar.a(i);
                        fVar.d(i);
                        fVar.g(i);
                        fVar.e(i);
                        fVar.i(i);
                        fVar.h(i);
                        fVar.f(i);
                        fVar.b(i);
                        fVar.f();
                        if (i == 0) {
                            ReTrackDisplay.this.s();
                        }
                        fVar.d(currentTimeMillis);
                        i++;
                    }
                }
            }
        }.start();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void updateAnimation(LenLatLong lenLatLong) {
        if (this.mIsExitReTrack || this.mReTrackStateManager.e() == 2 || this.aj.e(lenLatLong) || lenLatLong.isStopState()) {
            return;
        }
        if (lenLatLong.isTurnState()) {
            Message obtain = Message.obtain();
            obtain.what = 82;
            obtain.obj = lenLatLong;
            this.mMsgHandler.sendMessage(obtain);
            return;
        }
        if (lenLatLong.isStraightState()) {
            Message obtain2 = Message.obtain();
            obtain2.what = 81;
            if (koq.d(this.mLensData, lenLatLong.getIndex() + 1)) {
                obtain2.obj = new Pair(lenLatLong, this.mLensData.get(lenLatLong.getIndex() + 1));
            }
            this.mMsgHandler.sendMessage(obtain2);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void moveCamera(final LenLatLong lenLatLong, final LenLatLong lenLatLong2) {
        final int lenMoveDuration = ((int) this.mTrackAnimationControl.getLenMoveDuration(lenLatLong.getDistance())) + 1;
        hlh mapStatus = this.mMapEngine.getMapStatus();
        mapStatus.c(lenLatLong2.getLatLng());
        LogUtil.a("Track_ReTrackDisplay", "moveCamera bearing:", Float.valueOf(mapStatus.a()), " tilt:", Float.valueOf(mapStatus.b()), " duration:", Integer.valueOf(lenMoveDuration));
        this.mCameraId++;
        final long j = this.mCameraId;
        final long currentTimeMillis = System.currentTimeMillis();
        long c2 = c(currentTimeMillis);
        long j2 = lenMoveDuration;
        if (this.y) {
            return;
        }
        long min = Math.min(j2, 100L);
        if (this.aj.e(lenLatLong)) {
            d(lenLatLong, lenLatLong2);
        } else {
            this.mMapEngine.animateCamera(mapStatus, Math.max(j2 - c2, min), new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.4
                @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
                public void onFinish() {
                    if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mCameraId != j) {
                        return;
                    }
                    LogUtil.a("Track_ReTrackDisplay", "moveCamera finish");
                    Message obtain = Message.obtain();
                    obtain.what = 80;
                    obtain.obj = lenLatLong2;
                    ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
                    ReTrackDisplay.this.aj.d(lenLatLong2);
                    ReTrackDisplay.c(ReTrackDisplay.this, lenMoveDuration);
                }

                @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
                public void onCancel() {
                    if (ReTrackDisplay.this.mIsExitReTrack) {
                        return;
                    }
                    LenLatLong lenLatLong3 = new LenLatLong(ReTrackDisplay.this.mMapEngine.getMapStatus().d().b, ReTrackDisplay.this.mMapEngine.getMapStatus().d().d);
                    lenLatLong3.setIndex(lenLatLong.getIndex());
                    lenLatLong3.setDistance(hau.b(lenLatLong3, lenLatLong2));
                    lenLatLong3.setLineStatus(lenLatLong.getLineStatus());
                    lenLatLong3.setState(1);
                    ReTrackDisplay.this.aj.d(lenLatLong3);
                    ReTrackDisplay.c(ReTrackDisplay.this, System.currentTimeMillis() - currentTimeMillis);
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void spinCamera(final LenLatLong lenLatLong) {
        double angle;
        hlh mapStatus = this.mMapEngine.getMapStatus();
        float a2 = mapStatus.a();
        if (this.mMapEngine.isClockwise()) {
            angle = 360.0d - lenLatLong.getAngle();
        } else {
            angle = lenLatLong.getAngle();
        }
        float f2 = (float) angle;
        mapStatus.c(lenLatLong.getLatLng()).d(f2);
        final int spinDuration = ((int) this.mTrackAnimationControl.getSpinDuration(a2, f2)) + 1;
        LogUtil.a("Track_ReTrackDisplay", "spinCamera bearing:", Float.valueOf(mapStatus.a()), " tilt:", Float.valueOf(mapStatus.b()), " duration:", Integer.valueOf(spinDuration));
        this.mCameraId++;
        final long j = this.mCameraId;
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.y) {
            return;
        }
        this.mMapEngine.animateCamera(mapStatus, spinDuration, new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.5
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onFinish() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mCameraId != j) {
                    return;
                }
                LogUtil.a("Track_ReTrackDisplay", "spinCamera Finish");
                Message obtain = Message.obtain();
                obtain.what = 81;
                if (koq.d(ReTrackDisplay.this.mLensData, lenLatLong.getIndex() + 1)) {
                    obtain.obj = new Pair(lenLatLong, ReTrackDisplay.this.mLensData.get(lenLatLong.getIndex() + 1));
                }
                ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
                ReTrackDisplay.c(ReTrackDisplay.this, spinDuration);
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onCancel() {
                if (ReTrackDisplay.this.mIsExitReTrack) {
                    return;
                }
                ReTrackDisplay.this.aj.d(lenLatLong);
                ReTrackDisplay.c(ReTrackDisplay.this, System.currentTimeMillis() - currentTimeMillis);
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void zoomCamera(float f2) {
        hlh mapStatus = this.mMapEngine.getMapStatus();
        mapStatus.e(f2);
        Float valueOf = Float.valueOf(f2);
        Float valueOf2 = Float.valueOf(0.0f);
        LogUtil.a("Track_ReTrackDisplay", "zoomCamera zoom:", valueOf, " bearing:", valueOf2, " tilt:", valueOf2, " duration:", 1000L);
        this.mCameraId++;
        final long j = this.mCameraId;
        this.mMapEngine.animateCamera(mapStatus, Math.max(1000 - c(System.currentTimeMillis()), 100L), new InterfaceMapCallback() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.7
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onCancel() {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback
            public void onFinish() {
                if (ReTrackDisplay.this.mIsExitReTrack || ReTrackDisplay.this.mCameraId != j) {
                    return;
                }
                ReTrackDisplay.this.aj.g();
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void stopCamera() {
        LogUtil.a("Track_ReTrackDisplay", "stop camera");
        this.mMapEngine.stopAnimation();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    public ReTrackEngine setTrackVideo(VideoView videoView) {
        this.al = videoView;
        return this;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackEngine
    protected void firstFrameAwait() {
        this.mMapEngine.clear();
        this.h.clear();
        Message obtain = Message.obtain();
        obtain.what = 48;
        obtain.obj = Float.valueOf(this.mTrackAnimationControl.getRunZoomLevel());
        this.mMsgHandler.sendMessage(obtain);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void startReTrack() {
        if (this.mReTrackStateManager.e() == -1 || this.mMapEngine == null) {
            return;
        }
        this.mIsExitReTrack = false;
        this.mMsgHandler.sendEmptyMessageDelayed(52, 300L);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void reset() {
        if (this.mReTrackStateManager.e() == -1 || this.mMapEngine == null) {
            return;
        }
        this.mMapEngine.stopAnimation();
        this.mIsExitReTrack = true;
        for (int i = 0; i < this.mTrackPhotoList.size(); i++) {
            if (this.mTrackPhotoList.get(i).getVisibility() == 0) {
                this.mTrackPhotoList.get(i).clearAnimation();
                this.mTrackPhotoList.get(i).setVisibility(8);
            }
        }
        this.mTrackPhotoBackound.setVisibility(8);
        nsy.cMA_(this.al, 8);
        hlh hlhVar = this.ak;
        if (hlhVar == null || this.k) {
            return;
        }
        hlh a2 = hlh.a(hlhVar);
        a2.c(this.mTrackSimplify.getCenterPoint().getLatLng());
        this.mMapEngine.animateCamera(a2, -1L, (InterfaceMapCallback) null);
        this.mMapEngine.clear();
        this.h.clear();
        addGpsTrackLine();
        addLenTrackLine();
        addCustomMarkerList();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void resetLine() {
        if (this.mMapEngine == null) {
            return;
        }
        this.mMapEngine.clear();
        this.h.clear();
        addGpsTrackLine();
        addLenTrackLine();
        addCustomMarkerList();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setCustomMarkInfo(hah hahVar) {
        this.ab = hahVar;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public long getTotalDuration() {
        if (this.mTrackAnimationControl != null) {
            return this.mTrackAnimationControl.getDurationMs();
        }
        return 0L;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void onCreate(Bundle bundle) {
        if (this.mReTrackStateManager.e() == -1 || this.mMapEngine == null) {
            return;
        }
        this.ae = new MarkerBuilder(this.mContext);
        this.mMapEngine.onCreatePurely(bundle);
        this.mMapEngine.setMapLoadedCallback(this.x);
        this.mMapEngine.setCameraChangeCallback(this.ac);
        this.mMapEngine.setLogoPadding(hag.a(4.0f), 0, 0, hag.a(4.0f));
        this.mMapEngine.disableAllGestures();
        this.mMapEngine.disableMarkerClick();
        this.mMapEngine.showPureMap();
        h();
        this.mMapEngine.changeMapType(b(this.mMapType), null);
        e(this.mMapType);
        if (this.mMapEngine.getMapEngineType() == 2 || this.mMapEngine.getMapEngineType() == 3) {
            p();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void onResume() {
        if (this.mReTrackStateManager.e() == -1 || this.mMapEngine == null) {
            return;
        }
        this.mIsExitReTrack = false;
        this.mMapEngine.onResumePurely();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void onPause() {
        if (this.mReTrackStateManager.e() == -1 || this.mMapEngine == null) {
            return;
        }
        this.mIsExitReTrack = true;
        for (int i = 0; i < this.mTrackPhotoList.size(); i++) {
            if (this.mTrackPhotoList.get(i).getVisibility() == 0) {
                this.mTrackPhotoList.get(i).clearAnimation();
                this.mTrackPhotoList.get(i).setVisibility(8);
            }
        }
        this.mTrackPhotoBackound.setVisibility(8);
        nsy.cMA_(this.al, 8);
        this.i.end();
        this.mMapEngine.onPausePurely();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void onDestroy() {
        if (this.mReTrackStateManager.e() == -1 || this.mMapEngine == null) {
            return;
        }
        this.mIsExitReTrack = true;
        for (int i = 0; i < this.mTrackPhotoList.size(); i++) {
            if (this.mTrackPhotoList.get(i).getVisibility() == 0) {
                this.mTrackPhotoList.get(i).clearAnimation();
                this.mTrackPhotoList.get(i).setVisibility(8);
            }
        }
        this.mTrackPhotoBackound.setVisibility(8);
        this.i.end();
        this.mMapEngine.stopAnimation();
        this.mMapEngine.onDestroyPurely();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void changeMapType(MapTypeDescription.MapType mapType, hak hakVar) {
        if (mapType == null || this.mReTrackStateManager.e() == -1 || this.mMapEngine == null) {
            return;
        }
        this.mMapType = mapType;
        this.mMapEngine.changeMapType(b(mapType), hakVar);
        e(this.mMapType);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setAutoMatchPhotos(HashMap<Integer, List<Bitmap>> hashMap) {
        if (hashMap == null) {
            return;
        }
        for (Map.Entry<Integer, List<Bitmap>> entry : hashMap.entrySet()) {
            if (hbz.b(entry.getValue(), Bitmap.class)) {
                this.m.put(entry.getKey(), entry.getValue());
                LogUtil.a("Track_ReTrackDisplay", "automatchindex:", String.valueOf(entry.getKey()));
                Message obtain = Message.obtain();
                obtain.what = 119;
                obtain.obj = new Pair(entry.getKey(), InterfaceUpdateReTrack.MarkerType.Image_type);
                this.mMsgHandler.sendMessage(obtain);
            }
        }
    }

    private void d(LenLatLong lenLatLong, LenLatLong lenLatLong2) {
        LenLatLong lenLatLong3 = new LenLatLong(this.mMapEngine.getMapStatus().d().b, this.mMapEngine.getMapStatus().d().d);
        lenLatLong3.setIndex(lenLatLong.getIndex());
        lenLatLong3.setDistance(hau.b(lenLatLong, lenLatLong2));
        lenLatLong3.setLineStatus(lenLatLong.getLineStatus());
        lenLatLong3.setState(1);
        this.aj.d(lenLatLong3);
    }

    private void o() {
        InputStream inputStream = null;
        try {
            try {
                inputStream = this.mContext.getAssets().open("style.data");
                byte[] bArr = new byte[inputStream.available()];
                this.aa = bArr;
                if (inputStream.read(bArr) <= 0) {
                    LogUtil.b("Track_ReTrackDisplay", "read map style data fail.");
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        LogUtil.b("Track_ReTrackDisplay", e2.getMessage());
                    }
                }
            } catch (IOException e3) {
                LogUtil.b("Track_ReTrackDisplay", e3.getMessage());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        LogUtil.b("Track_ReTrackDisplay", e4.getMessage());
                    }
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    LogUtil.b("Track_ReTrackDisplay", e5.getMessage());
                }
            }
            throw th;
        }
    }

    private MapTypeDescription b(MapTypeDescription.MapType mapType) {
        if (this.aa == null) {
            o();
        }
        if (this.z == null) {
            MapTypeDescription mapTypeDescription = new MapTypeDescription();
            this.z = mapTypeDescription;
            mapTypeDescription.b(this.aa);
        }
        this.z.d(mapType);
        int i = AnonymousClass8.f3602a[mapType.ordinal()];
        if (i == 1) {
            this.z.e(true);
        } else if (i == 2 || i == 3 || i == 4) {
            this.z.e(false);
        } else {
            this.z.e(false);
        }
        return this.z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (this.mLensData.size() < 2) {
            LogUtil.h("Track_ReTrackDisplay", "mLensData.size less 2");
            return;
        }
        LenLatLong lenLatLong = new LenLatLong(this.mLensData.get(0).getLatLng().b, this.mLensData.get(0).getLatLng().d);
        lenLatLong.setIndex(0);
        lenLatLong.setDistance(this.mLensData.get(0).getDistance());
        lenLatLong.setLineStatus(this.mLensData.get(0).getLineStatus());
        lenLatLong.setState(1);
        this.w = System.currentTimeMillis();
        if (this.aj.e(lenLatLong)) {
            return;
        }
        LogUtil.a("Track_ReTrackDisplay", "start Lens Move");
        Message obtain = Message.obtain();
        obtain.what = 81;
        obtain.obj = new Pair(this.mLensData.get(0), this.mLensData.get(1));
        this.mMsgHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        return this.mMapType == MapTypeDescription.MapType.MAP_TYPE_SATELLITE || this.mMapType == MapTypeDescription.MapType.MAP_TYPE_NIGHT;
    }

    private long c(long j) {
        long j2 = (j - this.w) - this.ao;
        if (j2 < 0) {
            return 0L;
        }
        return j2;
    }

    private void h() {
        if (nrt.a(this.mContext) && !m() && l()) {
            this.mMapType = MapTypeDescription.MapType.MAP_TYPE_NIGHT;
            c(true);
        } else if (nrt.a(this.mContext) && m() && l()) {
            f();
        } else {
            f();
            c(false);
        }
    }

    private boolean l() {
        return this.mMapEngine.getMapEngineType() == 1 || this.mMapEngine.getMapEngineType() == 2 || this.mMapEngine.getMapEngineType() == 3;
    }

    private void f() {
        Context context = this.mContext;
        Context context2 = this.mContext;
        this.mMapType = hcr.d(context.getSharedPreferences("retrack_file", 0).getString(hcr.e(this.mMapEngine.getMapEngineType()), hcr.b(this.mMapEngine.getMapEngineType())));
    }

    private void e(MapTypeDescription.MapType mapType) {
        String e2 = hcr.e(mapType);
        Context context = this.mContext;
        Context context2 = this.mContext;
        SharedPreferences.Editor edit = context.getSharedPreferences("retrack_file", 0).edit();
        edit.putString(hcr.e(this.mMapEngine.getMapEngineType()), e2);
        edit.commit();
    }

    private boolean m() {
        Context context = this.mContext;
        Context context2 = this.mContext;
        return context.getSharedPreferences("retrack_file", 0).getBoolean(i(), false);
    }

    private void c(boolean z) {
        Context context = this.mContext;
        Context context2 = this.mContext;
        SharedPreferences.Editor edit = context.getSharedPreferences("retrack_file", 0).edit();
        edit.putBoolean(i(), z);
        edit.commit();
    }

    private String i() {
        if (this.mMapEngine.getMapEngineType() == 1) {
            return "map_type_init_by_dark_mode_done";
        }
        return "map_type_init_by_dark_mode_done" + this.mMapEngine.getMapEngineType();
    }

    public class f {

        /* renamed from: a, reason: collision with root package name */
        private double f3606a;
        private double c;
        private boolean d;
        private double e;
        private double g;
        private Deque<LatLong> i;
        private double j;

        private f() {
            this.f3606a = 0.0d;
            this.c = 0.0d;
            this.j = 0.0d;
            this.e = 0.0d;
            this.g = 0.0d;
            this.i = new LinkedList();
            this.d = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i) {
            if (i >= ReTrackDisplay.this.mTrackData.size()) {
                return;
            }
            if (i == 0) {
                LinkedList linkedList = new LinkedList();
                this.i = linkedList;
                linkedList.offerLast(ReTrackDisplay.this.mTrackData.get(i));
                this.i.offerLast(ReTrackDisplay.this.mTrackData.get(i));
                this.j = 0.0d;
                this.f3606a = 0.0d;
                this.c = 0.0d;
            } else {
                this.i.offerLast(ReTrackDisplay.this.mTrackData.get(i));
                double lineDrawDuration = ReTrackDisplay.this.mTrackAnimationControl.getLineDrawDuration(ReTrackDisplay.this.mTrackData.get(i - 1).getDistance());
                this.j += lineDrawDuration;
                a(lineDrawDuration);
            }
            this.d = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(int i) {
            if (i < ReTrackDisplay.this.mTrackData.size()) {
                int i2 = 0;
                ReTrackDisplay.this.p = false;
                if (ReTrackDisplay.this.m.containsKey(Integer.valueOf(i)) || ReTrackDisplay.this.j.containsKey(Integer.valueOf(i)) || ReTrackDisplay.this.n.containsKey(Integer.valueOf(i))) {
                    ReTrackDisplay.this.p = true;
                    ReTrackDisplay.this.aj.d();
                    ReTrackDisplay.this.aj.e();
                    ReTrackDisplay.this.ag.d();
                    InterfaceUpdateReTrack.MarkerType c = c(i);
                    Message obtain = Message.obtain();
                    obtain.what = 115;
                    obtain.obj = new Pair(new Pair(ReTrackDisplay.this.mTrackData.get(i), 4), new Pair(Integer.valueOf(i), c));
                    ReTrackDisplay.this.mMsgHandler.sendMessageDelayed(obtain, 300L);
                    long j = 0;
                    if (ReTrackDisplay.this.m.containsKey(Integer.valueOf(i))) {
                        i2 = ((List) ReTrackDisplay.this.m.get(Integer.valueOf(i))).size();
                    } else if (ReTrackDisplay.this.n.containsKey(Integer.valueOf(i))) {
                        j = ReTrackDisplay.this.a(i);
                    } else {
                        Message obtain2 = Message.obtain();
                        obtain2.what = 117;
                        obtain2.obj = ReTrackDisplay.this.j.containsKey(Integer.valueOf(i)) ? (String) ReTrackDisplay.this.j.get(Integer.valueOf(i)) : "";
                        ReTrackDisplay.this.mMsgHandler.sendMessage(obtain2);
                        ReTrackDisplay.this.mMsgHandler.sendEmptyMessageDelayed(120, 2000L);
                    }
                    double d = this.g;
                    long j2 = i2 * TrackAnimationControl.DISPLAY_PHOTO_DURATION;
                    this.g = d + 600.0d + 150.0d + 250.0d + j2 + j;
                    ReTrackDisplay reTrackDisplay = ReTrackDisplay.this;
                    reTrackDisplay.ao = reTrackDisplay.ao + 1000 + j2 + j;
                    this.d = true;
                    return;
                }
                return;
            }
            LogUtil.h("Track_ReTrackDisplay", "updateAlbum: index >= track data size");
        }

        private InterfaceUpdateReTrack.MarkerType c(int i) {
            if (!ReTrackDisplay.this.m.containsKey(Integer.valueOf(i))) {
                if (ReTrackDisplay.this.n.containsKey(Integer.valueOf(i))) {
                    return InterfaceUpdateReTrack.MarkerType.Video_type;
                }
                return InterfaceUpdateReTrack.MarkerType.Text_type;
            }
            return InterfaceUpdateReTrack.MarkerType.Image_type;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(final int i) {
            if (i >= ReTrackDisplay.this.mTrackData.size() || ReTrackDisplay.this.q) {
                return;
            }
            double doubleValue = ((Double) ReTrackDisplay.this.ai.get(i)).doubleValue();
            if (doubleValue < 0.0d) {
                Bitmap aZb_ = ReTrackDisplay.this.ae.aZb_(Math.abs(doubleValue));
                final hlg hlgVar = new hlg();
                hlgVar.bhP_(ReTrackDisplay.f3593a);
                hlgVar.d(ReTrackDisplay.this.mTrackData.get(i).getLatLng());
                hlgVar.c(ReTrackDisplay.this.mIs3dMarker);
                hlgVar.e(ReTrackDisplay.this.ae.c(Math.abs(doubleValue)));
                hlgVar.bhQ_(aZb_);
                ReTrackDisplay.this.mMsgHandler.post(new Runnable() { // from class: hbu
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReTrackDisplay.f.this.a(hlgVar, i);
                    }
                });
                this.d = true;
                ReTrackDisplay.this.ag.d();
            }
        }

        public /* synthetic */ void a(hlg hlgVar, int i) {
            ReTrackDisplay.this.addGrowAnimationMarker(hlgVar, 1, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f(final int i) {
            if (i >= ReTrackDisplay.this.mTrackData.size()) {
                return;
            }
            if (ReTrackDisplay.this.mTrackSimplify.getHiHealthMarkerMap() == null) {
                LogUtil.a("Track_ReTrackDisplay", "no HiHealth Marker Map");
                return;
            }
            if (ReTrackDisplay.this.mTrackSimplify.getHiHealthMarkerMap().containsKey(Integer.valueOf(i))) {
                hjg hjgVar = ReTrackDisplay.this.mTrackSimplify.getHiHealthMarkerMap().get(Integer.valueOf(i));
                if (hjgVar == null) {
                    ReleaseLogUtil.c("Track_ReTrackDisplay", "hiHealthMarker is null.");
                    return;
                }
                Bitmap cHF_ = nrf.cHF_(hjgVar.bgH_());
                final hlg hlgVar = new hlg();
                hlgVar.e(hjgVar.a(), hjgVar.c()).d(hjgVar.b()).bhQ_(cHF_);
                ReTrackDisplay.this.mMsgHandler.post(new Runnable() { // from class: hbv
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReTrackDisplay.f.this.e(hlgVar, i);
                    }
                });
                this.d = true;
                ReTrackDisplay.this.ag.d();
            }
        }

        public /* synthetic */ void e(hlg hlgVar, int i) {
            ReTrackDisplay.this.addGrowAnimationMarker(hlgVar, 1, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i) {
            if (ReTrackDisplay.this.mIsSupportArea && i < ReTrackDisplay.this.mTrackData.size() && !ReTrackDisplay.this.aj.b() && ReTrackDisplay.this.s != ReTrackDisplay.this.mTrackData.get(i).getIsInArea()) {
                ReTrackDisplay.this.s = !r0.s;
                ReTrackDisplay.this.aj.d();
                ReTrackDisplay.this.aj.e();
                float newZoomLevel = ReTrackDisplay.this.s ? ReTrackDisplay.this.mTrackAnimationControl.getNewZoomLevel((float) ReTrackDisplay.this.mTrackData.get(i).getAreaFraction()) : ReTrackDisplay.this.mTrackAnimationControl.getRunZoomLevel();
                Message obtain = Message.obtain();
                obtain.what = 83;
                obtain.obj = Float.valueOf(newZoomLevel);
                ReTrackDisplay.this.mMsgHandler.sendMessageDelayed(obtain, 300L);
                this.g = this.g + 1000.0d + 600.0d;
                ReTrackDisplay.this.ao += TrackAnimationControl.DISPLAY_PHOTO_DURATION;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i(final int i) {
            if (i < ReTrackDisplay.this.mTrackData.size() && ReTrackDisplay.this.v.c(i)) {
                if (!ReTrackDisplay.this.m.containsKey(Integer.valueOf(i)) && !ReTrackDisplay.this.j.containsKey(Integer.valueOf(i))) {
                    ReTrackDisplay.this.aj.d();
                    ReTrackDisplay.this.aj.e();
                }
                ReTrackDisplay.this.ag.d();
                Bitmap aYZ_ = ReTrackDisplay.this.ae.aYZ_(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(i)), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType());
                final hlg hlgVar = new hlg();
                hlgVar.bhP_(ReTrackDisplay.f3593a);
                hlgVar.d(ReTrackDisplay.this.mTrackData.get(i).getLatLng());
                hlgVar.e(ReTrackDisplay.this.ae.c(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(i)), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType()));
                hlgVar.bhQ_(aYZ_);
                ReTrackDisplay.this.mMsgHandler.postDelayed(new Runnable() { // from class: hby
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReTrackDisplay.f.this.d(hlgVar, i);
                    }
                }, 200L);
                this.d = true;
                Message obtain = Message.obtain();
                obtain.what = 97;
                obtain.obj = new Pair(Integer.valueOf(ReTrackDisplay.this.v.b(ReTrackDisplay.this.v.e(i))), Double.valueOf(ReTrackDisplay.this.v.e(ReTrackDisplay.this.v.e(i))));
                ReTrackDisplay.this.mMsgHandler.sendMessageDelayed(obtain, 200L);
                if (ReTrackDisplay.this.m.containsKey(Integer.valueOf(i)) || ReTrackDisplay.this.j.containsKey(Integer.valueOf(i))) {
                    return;
                }
                this.g = this.g + 600.0d + 150.0d + 250.0d;
                ReTrackDisplay.this.ao += 1000;
            }
        }

        public /* synthetic */ void d(hlg hlgVar, int i) {
            ReTrackDisplay.this.addGrowAnimationMarker(hlgVar, 3, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            this.e = 0.0d;
            if (this.d || this.j >= 15.0d) {
                a();
                this.d = false;
            }
        }

        private void a() {
            LatLong peekLast = this.i.peekLast();
            if (peekLast == null) {
                return;
            }
            LatLong pollFirst = this.i.pollFirst();
            ArrayList arrayList = new ArrayList();
            while (pollFirst != null) {
                arrayList.add(pollFirst);
                pollFirst = this.i.pollFirst();
            }
            Message obtain = Message.obtain();
            obtain.what = 65;
            obtain.obj = arrayList;
            ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
            this.i.offerLast(peekLast);
            double d = this.j;
            this.e = d >= 15.0d ? d : 15.0d;
            this.g += d;
            this.j = 0.0d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i) {
            if (i >= ReTrackDisplay.this.mTrackData.size()) {
                return;
            }
            if (ReTrackDisplay.this.p || this.f3606a >= 45.0d || i >= ReTrackDisplay.this.mTrackData.size() - 1) {
                double abs = Math.abs(((Double) ReTrackDisplay.this.ai.get(i)).doubleValue());
                Message obtain = Message.obtain();
                obtain.what = 96;
                obtain.obj = hji.e(abs);
                ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
            }
        }

        public void e(int i) {
            if (i >= ReTrackDisplay.this.mTrackData.size()) {
                return;
            }
            if (this.c >= 15.0d || i == ReTrackDisplay.this.mTrackData.size() - 1 || i == 0 || ReTrackDisplay.this.v.c(i)) {
                Message obtain = Message.obtain();
                obtain.what = 99;
                obtain.obj = Integer.valueOf(i);
                ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.g = 0.0d;
            ReTrackDisplay.this.y = true;
            Message obtain = Message.obtain();
            obtain.what = 51;
            obtain.obj = Float.valueOf(0.0f);
            ReTrackDisplay.this.mMsgHandler.sendMessage(obtain);
            ReTrackDisplay.this.mReTrackStateManager.b(2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            if (ReTrackDisplay.this.mMapEngine.getMapEngineType() == 1) {
                ReTrackDisplay.this.mMapEngine.onPause();
                h();
                ReTrackDisplay.this.mMapEngine.onResume();
                return;
            }
            h();
        }

        private void h() {
            try {
                Thread.sleep(ReTrackDisplay.this.ag.c());
            } catch (InterruptedException unused) {
                LogUtil.h("Track_ReTrackDisplay", "some error in threadSyncMarker");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (ReTrackDisplay.this.mMapEngine.getMapEngineType() == 1) {
                ReTrackDisplay.this.mMapEngine.onPause();
                e();
                ReTrackDisplay.this.mMapEngine.onResume();
                return;
            }
            e();
        }

        private void e() {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
                LogUtil.h("Track_ReTrackDisplay", "some error in threadSyncLens");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(long j) {
            if (this.e >= 15.0d && System.currentTimeMillis() - j <= this.g) {
                try {
                    Thread.sleep((int) (this.e + 0.5d));
                } catch (InterruptedException unused) {
                    LogUtil.h("Track_ReTrackDisplay", "some error in threadSleep");
                }
            }
        }

        private void a(double d) {
            if (this.f3606a >= 45.0d) {
                this.f3606a = 0.0d;
            }
            if (this.c >= 15.0d) {
                this.c = 0.0d;
            }
            this.f3606a += d;
            this.c += d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a(int i) {
        if (koq.b(this.n.get(Integer.valueOf(i))) || this.n.get(Integer.valueOf(i)).get(0) == null) {
            return 0L;
        }
        return this.n.get(Integer.valueOf(i)).get(0).getDuration();
    }

    class e {
        private e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(int i) {
            if (i >= ReTrackDisplay.this.d.size()) {
                return;
            }
            GrowAnimationBuilder growAnimation = ReTrackDisplay.this.mMapEngine.getGrowAnimation();
            ReTrackDisplay reTrackDisplay = ReTrackDisplay.this;
            growAnimation.setType(reTrackDisplay.b(((Integer) ((Pair) reTrackDisplay.d.get(i)).first).intValue()));
            growAnimation.setFrictionAnimationDuration(400L);
            ReTrackDisplay.this.mMapEngine.hideMarker(((Integer) ((Pair) ReTrackDisplay.this.d.get(i)).second).intValue(), growAnimation);
            if (i == ReTrackDisplay.this.d.size() - 1) {
                ReTrackDisplay.this.mMsgHandler.sendEmptyMessageDelayed(32, 410L);
            }
        }
    }

    class d {
        private d() {
        }

        public void a(int i) {
            if (i <= 1) {
                return;
            }
            double validTotalDistance = ReTrackDisplay.this.mTrackSimplify.getValidTotalDistance();
            double d = i - 1;
            double d2 = validTotalDistance / d;
            double kmMarkerSpace = ReTrackDisplay.this.mTrackAnimationControl.getKmMarkerSpace();
            int i2 = (int) ((d * kmMarkerSpace) / validTotalDistance);
            double d3 = 0.0d;
            int i3 = 0;
            ReTrackDisplay.this.ai.set(0, Double.valueOf(0.0d));
            int i4 = 1;
            double d4 = 0.0d;
            double d5 = 0.0d;
            while (i4 < ReTrackDisplay.this.ai.size() - 1) {
                if (((Double) ReTrackDisplay.this.ai.get(i4)).doubleValue() <= d3) {
                    ReTrackDisplay.this.ai.set(i4, Double.valueOf(d4));
                } else {
                    i3++;
                    d4 += d2;
                    if (i2 == 0 || i3 % i2 != 0) {
                        ReTrackDisplay.this.ai.set(i4, Double.valueOf(d4));
                    } else {
                        d5 += kmMarkerSpace;
                        if (d5 <= validTotalDistance) {
                            ReTrackDisplay.this.ai.set(i4, Double.valueOf((-1.0d) * d5));
                        } else {
                            ReTrackDisplay.this.ai.set(i4, Double.valueOf(d4));
                        }
                    }
                }
                i4++;
                d3 = 0.0d;
            }
            if (i2 == 0 || (i3 + 1) % i2 != 0) {
                ReTrackDisplay.this.ai.set(ReTrackDisplay.this.ai.size() - 1, Double.valueOf(validTotalDistance));
            } else {
                ReTrackDisplay.this.ai.set(ReTrackDisplay.this.ai.size() - 1, Double.valueOf(validTotalDistance * (-1.0d)));
            }
        }
    }

    class a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f3603a;
        private LenLatLong c;
        private final Object d;
        private boolean e;

        private a() {
            this.d = new Object();
            this.e = false;
            this.f3603a = false;
            this.c = null;
        }

        public boolean b() {
            boolean z;
            synchronized (this.d) {
                z = this.e;
            }
            return z;
        }

        public boolean e(LenLatLong lenLatLong) {
            boolean z;
            synchronized (this.d) {
                z = this.e;
                if (z) {
                    this.c = lenLatLong;
                }
            }
            return z;
        }

        public boolean a() {
            boolean z;
            synchronized (this.d) {
                z = this.f3603a;
            }
            return z;
        }

        public void d() {
            synchronized (this.d) {
                this.c = null;
                this.e = true;
                if (System.currentTimeMillis() - ReTrackDisplay.this.mLastCameraStartTime <= 30) {
                    ReTrackDisplay.this.mMsgHandler.sendEmptyMessageDelayed(84, 30L);
                } else {
                    ReTrackDisplay.this.mMsgHandler.sendEmptyMessage(84);
                }
            }
        }

        public void c() {
            synchronized (this.d) {
                this.e = false;
                this.f3603a = false;
                this.c = null;
            }
        }

        public void e() {
            synchronized (this.d) {
                this.f3603a = true;
            }
        }

        public void d(LenLatLong lenLatLong) {
            synchronized (this.d) {
                this.c = lenLatLong;
            }
        }

        public void g() {
            synchronized (this.d) {
                this.e = false;
                ReTrackDisplay.this.mMsgHandler.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (a.this.d) {
                            a.this.f3603a = false;
                        }
                    }
                }, 300L);
                if (this.c == null) {
                    return;
                }
                Message obtainMessage = ReTrackDisplay.this.mMsgHandler.obtainMessage(80);
                obtainMessage.obj = this.c;
                ReTrackDisplay.this.mMsgHandler.sendMessageDelayed(obtainMessage, 300L);
                this.c = null;
            }
        }
    }

    class c {
        private Map<Integer, KeyExerciseType> b;

        private c() {
            this.b = new HashMap();
        }

        public boolean c(int i) {
            return this.b.containsKey(Integer.valueOf(i));
        }

        public KeyExerciseType e(int i) {
            return this.b.get(Integer.valueOf(i));
        }

        public double e(KeyExerciseType keyExerciseType) {
            int i = AnonymousClass8.d[keyExerciseType.ordinal()];
            if (i == 1) {
                return ((Double) ReTrackDisplay.this.mTrackSimplify.getMaxAltitude().second).doubleValue();
            }
            if (i == 2) {
                return ((Double) ReTrackDisplay.this.mTrackSimplify.getMaxHeartRate().second).doubleValue();
            }
            if (i != 3) {
                return 0.0d;
            }
            return ((Double) ReTrackDisplay.this.mTrackSimplify.getMaxSpeed().second).doubleValue();
        }

        public int b(KeyExerciseType keyExerciseType) {
            int i = AnonymousClass8.d[keyExerciseType.ordinal()];
            if (i != 1) {
                return i != 2 ? 3 : 2;
            }
            return 1;
        }

        public MarkerBuilder.KeyExerciseInfoType d(KeyExerciseType keyExerciseType) {
            int i = AnonymousClass8.d[keyExerciseType.ordinal()];
            if (i == 1) {
                return MarkerBuilder.KeyExerciseInfoType.Altitude_Marker;
            }
            if (i == 2) {
                return MarkerBuilder.KeyExerciseInfoType.HeartRate_Marker;
            }
            if (i == 3) {
                return MarkerBuilder.KeyExerciseInfoType.Speed_Marker;
            }
            return MarkerBuilder.KeyExerciseInfoType.HeartRate_Marker;
        }

        public void e() {
            if (ReTrackDisplay.this.mTrackData == null || ReTrackDisplay.this.mTrackSimplify == null) {
                LogUtil.h("Track_ReTrackDisplay", "ReTrack data is null");
                return;
            }
            this.b.clear();
            if (((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxAltitude().first).intValue() != -1) {
                this.b.put((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxAltitude().first, KeyExerciseType.MAX_ALTITUDE);
            }
            if (((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxHeartRate().first).intValue() != -1) {
                this.b.put((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxHeartRate().first, KeyExerciseType.MAX_HEART_RATE);
            }
            if (((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxSpeed().first).intValue() != -1) {
                this.b.put((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxSpeed().first, KeyExerciseType.MAX_SPEED);
            }
            switch (ReTrackDisplay.this.mTrackSimplify.getCurveType()) {
                case 32:
                case 34:
                case 35:
                    if (((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxSpeed().first).intValue() != -1) {
                        this.b.put((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxSpeed().first, KeyExerciseType.MAX_SPEED);
                        break;
                    }
                    break;
                case 33:
                    if (((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxAltitude().first).intValue() != -1) {
                        this.b.put((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxAltitude().first, KeyExerciseType.MAX_ALTITUDE);
                        break;
                    }
                    break;
                case 36:
                    if (((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxHeartRate().first).intValue() != -1) {
                        this.b.put((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxHeartRate().first, KeyExerciseType.MAX_HEART_RATE);
                        break;
                    }
                    break;
                default:
                    if (((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxSpeed().first).intValue() != -1) {
                        this.b.put((Integer) ReTrackDisplay.this.mTrackSimplify.getMaxSpeed().first, KeyExerciseType.MAX_SPEED);
                        break;
                    }
                    break;
            }
        }
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.ReTrackDisplay$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3602a;
        static final /* synthetic */ int[] d;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[KeyExerciseType.values().length];
            d = iArr;
            try {
                iArr[KeyExerciseType.MAX_ALTITUDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[KeyExerciseType.MAX_HEART_RATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[KeyExerciseType.MAX_SPEED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[MapTypeDescription.MapType.values().length];
            f3602a = iArr2;
            try {
                iArr2[MapTypeDescription.MapType.MAP_TYPE_SATELLITE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3602a[MapTypeDescription.MapType.MAP_TYPE_NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3602a[MapTypeDescription.MapType.MAP_TYPE_NIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3602a[MapTypeDescription.MapType.MAP_TYPE_NAVI.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[InterfaceUpdateReTrack.MarkerType.values().length];
            e = iArr3;
            try {
                iArr3[InterfaceUpdateReTrack.MarkerType.Text_type.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                e[InterfaceUpdateReTrack.MarkerType.Image_type.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                e[InterfaceUpdateReTrack.MarkerType.Video_type.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setAlgorithmStrength(TrackAnimationControl.Strength strength) {
        this.mTrackAnimationControl.setAlgorithmStrength(strength).update();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setIsSupportArea(boolean z) {
        this.mIsSupportArea = z;
        this.mTrackAnimationControl.setIsSupportArea(z);
    }

    static class b {
        private final Object c;
        private int d;

        private b() {
            this.d = 0;
            this.c = new Object();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            synchronized (this.c) {
                this.d = 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            synchronized (this.c) {
                this.d++;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            synchronized (this.c) {
                this.d--;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long c() {
            long j;
            synchronized (this.c) {
                j = this.d > 0 ? 400L : 0L;
            }
            return j;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public InterfaceUpdateReTrack obtainInterfaceUpdateReTrack() {
        return this.aq;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void addAlbumMarker(int i, InterfaceUpdateReTrack.MarkerType markerType) {
        if (this.mMapEngine == null) {
            LogUtil.h("Track_ReTrackDisplay", " mMapEngine is null in addAlbumMarker");
            return;
        }
        LogUtil.a("Track_ReTrackDisplay", "addAlbumMarkerPoint:", String.valueOf(i));
        hlg hlgVar = new hlg();
        hlgVar.bhP_(b);
        if (koq.b(this.mTrackData, i)) {
            LogUtil.h("Track_ReTrackDisplay", "addAlbumMarker index is out of bounds");
            return;
        }
        hlgVar.d(this.mTrackData.get(i).getLatLng());
        if (b(markerType, hlgVar)) {
            return;
        }
        int addMarker = this.mMapEngine.addMarker(hlgVar, null);
        this.h.append(addMarker, i);
        this.mAlbumMarkers.put(Integer.valueOf(i), markerType);
        this.o.put(Integer.valueOf(i), Integer.valueOf(addMarker));
        LogUtil.a("Track_ReTrackDisplay", "addAlbumMarkerId", String.valueOf(addMarker));
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void deleteAlbumMarker(int i) {
        if (!this.o.containsKey(Integer.valueOf(i)) || this.o.get(Integer.valueOf(i)) == null) {
            return;
        }
        LogUtil.a("Track_ReTrackDisplay", "deleteAlbumMarker:", Integer.valueOf(i));
        this.mMapEngine.hideMarker(this.o.get(Integer.valueOf(i)).intValue(), null);
        this.h.delete(this.o.get(Integer.valueOf(i)).intValue());
        this.mAlbumMarkers.remove(Integer.valueOf(i));
        this.o.remove(Integer.valueOf(i));
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void deleteMarkerIndex(int i) {
        if (this.ah.contains(Integer.valueOf(i))) {
            this.ah.remove(Integer.valueOf(i));
            this.mTrackAnimationControl.setMarkersNumber(this.ah.size());
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void updateTrackAnimationControl() {
        this.mTrackAnimationControl.update();
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setSumPhotosNumber(int i) {
        this.ar = i;
        this.mTrackAnimationControl.setPhotosNumber(this.ar);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void setScreenWidth(int i) {
        this.an = i;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void addMarkerIndex(int i) {
        if (this.ah.contains(Integer.valueOf(i))) {
            return;
        }
        this.ah.add(Integer.valueOf(i));
        this.mTrackAnimationControl.setMarkersNumber(this.ah.size());
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public double getCurrentDistance(int i) {
        if (koq.b(this.ai, i)) {
            LogUtil.h("Track_ReTrackDisplay", "getCurrentDistance index is out of bounds");
            return 0.0d;
        }
        return this.ai.get(i).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.mMapEngine.setPreviewStatus(new hla(this.mTrackSimplify.getMinBoundary().getLatLng(), this.mTrackSimplify.getMaxBoundary().getLatLng()), hag.a(54.0f), hag.a(54.0f), hag.a(238.5f), hag.a(238.5f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.r && this.mReTrackStateManager.e() == 1) {
            this.ak = this.mMapEngine.getMapStatus();
            if (this.mTrackAnimationControl == null) {
                this.mTrackAnimationControl = g();
            }
            n();
            this.mTrackAnimationControl.setAlgorithmStrength(this.mStrength);
            this.mTrackAnimationControl.setIsSupportArea(this.mIsSupportArea);
            this.mTrackAnimationControl.setOrgZoomLevel(this.ak.c()).setMaxZoomLevel(this.mMapEngine.getMaxZoomLevel()).update();
            this.mMsgHandler.sendEmptyMessage(16);
            this.mReTrackStateManager.b(0);
            this.r = false;
            reset();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void changeEditStatus(boolean z) {
        this.k = z;
        if (z) {
            this.mMapEngine.setAllGesturesEnabled(true);
            hjd latLng = this.mTrackSimplify.getMinBoundary().getLatLng();
            hjd latLng2 = this.mTrackSimplify.getMaxBoundary().getLatLng();
            Point screenLocation = this.mMapEngine.getScreenLocation(new hjd(latLng2.b + ((latLng.b - latLng2.b) / 2.0d), latLng2.d + ((latLng.d - latLng2.d) / 2.0d)));
            if (screenLocation == null) {
                screenLocation = new Point(0, 0);
            }
            screenLocation.set(screenLocation.x, (int) (screenLocation.y * 1.5f));
            this.mMapEngine.moveCameraByLatLng(this.mMapEngine.getLatLngByScreenPoint(screenLocation));
            this.mMapEngine.addMarkerClickListener(this.ad);
            return;
        }
        this.mMapEngine.setAllGesturesEnabled(false);
        hlh hlhVar = this.ak;
        if (hlhVar == null) {
            return;
        }
        hlh a2 = hlh.a(hlhVar);
        a2.c(this.mTrackSimplify.getCenterPoint().getLatLng());
        this.mMapEngine.animateCamera(a2, -1L, (InterfaceMapCallback) null);
        this.mMapEngine.disableMarkerClick();
    }

    class h implements InterfaceUpdateReTrack {
        private h() {
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void addText(int i, String str) {
            if (str == null || str.length() <= 0) {
                ReTrackDisplay.this.j.remove(Integer.valueOf(i));
            } else {
                ReTrackDisplay.this.j.put(Integer.valueOf(i), str);
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void addPhotosList(Map<Integer, List<Bitmap>> map) {
            ReTrackDisplay.this.m = map;
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void addMarker(int i, InterfaceUpdateReTrack.MarkerType markerType) {
            if (ReTrackDisplay.this.mAlbumMarkers.containsKey(Integer.valueOf(i))) {
                if (ReTrackDisplay.this.mAlbumMarkers.get(Integer.valueOf(i)) == markerType || ReTrackDisplay.this.mAlbumMarkers.get(Integer.valueOf(i)) == InterfaceUpdateReTrack.MarkerType.Video_type || ReTrackDisplay.this.mAlbumMarkers.get(Integer.valueOf(i)) == InterfaceUpdateReTrack.MarkerType.Image_type) {
                    return;
                }
                ReTrackDisplay.this.deleteAlbumMarker(i);
                ReTrackDisplay.this.deleteMarkerIndex(i);
            }
            if (ReTrackDisplay.this.l.containsKey(Integer.valueOf(i)) && !ReTrackDisplay.this.am.contains(Integer.valueOf(i))) {
                ReTrackDisplay.this.mMapEngine.hideMarker(((Integer) ReTrackDisplay.this.l.get(Integer.valueOf(i))).intValue(), null);
                ReTrackDisplay.this.h.delete(((Integer) ReTrackDisplay.this.l.get(Integer.valueOf(i))).intValue());
                ReTrackDisplay.this.am.add(Integer.valueOf(i));
                hlg hlgVar = new hlg();
                hlgVar.bhP_(ReTrackDisplay.c);
                hlgVar.d(ReTrackDisplay.this.mTrackData.get(i).getLatLng());
                String a2 = ReTrackDisplay.this.ae.a(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(i)), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType());
                if (!TextUtils.isEmpty(a2)) {
                    hlgVar.e(a2);
                }
                Bitmap aZd_ = ReTrackDisplay.this.ae.aZd_(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(i)), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType());
                if (aZd_ != null) {
                    hlgVar.bhQ_(aZd_);
                }
                int addMarker = ReTrackDisplay.this.mMapEngine.addMarker(hlgVar, null);
                ReTrackDisplay.this.h.append(addMarker, i);
                ReTrackDisplay.this.l.put(Integer.valueOf(i), Integer.valueOf(addMarker));
            }
            ReTrackDisplay.this.addAlbumMarker(i, markerType);
            ReTrackDisplay.this.addMarkerIndex(i);
            ReTrackDisplay.this.updateTrackAnimationControl();
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void clearMarker(int i) {
            if (i >= 0) {
                ReTrackDisplay.this.deleteAlbumMarker(i);
                ReTrackDisplay.this.deleteMarkerIndex(i);
                if (ReTrackDisplay.this.am.contains(Integer.valueOf(i)) && ReTrackDisplay.this.l.containsKey(Integer.valueOf(i))) {
                    ReTrackDisplay.this.mMapEngine.hideMarker(((Integer) ReTrackDisplay.this.l.get(Integer.valueOf(i))).intValue(), null);
                    ReTrackDisplay.this.h.delete(((Integer) ReTrackDisplay.this.l.get(Integer.valueOf(i))).intValue());
                    ReTrackDisplay.this.am.remove(Integer.valueOf(i));
                    Bitmap aYZ_ = ReTrackDisplay.this.ae.aYZ_(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(i)), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType());
                    if (aYZ_ == null) {
                        return;
                    }
                    hlg hlgVar = new hlg();
                    hlgVar.bhP_(ReTrackDisplay.b);
                    if (koq.b(ReTrackDisplay.this.mTrackData, i)) {
                        LogUtil.h("Track_ReTrackDisplay", "clearMarker index is out of bounds");
                        return;
                    }
                    hlgVar.d(ReTrackDisplay.this.mTrackData.get(i).getLatLng());
                    hlgVar.bhQ_(aYZ_);
                    int addMarker = ReTrackDisplay.this.mMapEngine.addMarker(hlgVar, null);
                    ReTrackDisplay.this.h.append(addMarker, i);
                    ReTrackDisplay.this.l.put(Integer.valueOf(i), Integer.valueOf(addMarker));
                }
                ReTrackDisplay.this.updateTrackAnimationControl();
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public int addCurMarker(int i, InterfaceUpdateReTrack.MarkerType markerType) {
            Bitmap aYW_;
            if (ReTrackDisplay.this.mMapEngine == null) {
                LogUtil.h("Track_ReTrackDisplay", "mMapEngine is null in addCurMarker");
                return -1;
            }
            hlg hlgVar = new hlg();
            hlgVar.bhP_(ReTrackDisplay.b);
            if (koq.b(ReTrackDisplay.this.mTrackData, i)) {
                return -1;
            }
            hlgVar.d(ReTrackDisplay.this.mTrackData.get(i).getLatLng());
            int i2 = AnonymousClass8.e[markerType.ordinal()];
            if (i2 == 2) {
                aYW_ = ReTrackDisplay.this.ae.aYW_();
                hlgVar.e(ReTrackDisplay.this.ae.c());
            } else if (i2 != 3) {
                aYW_ = ReTrackDisplay.this.ae.aYX_();
                hlgVar.e(ReTrackDisplay.this.ae.e());
            } else {
                aYW_ = ReTrackDisplay.this.ae.aYY_();
                hlgVar.e(ReTrackDisplay.this.ae.j());
            }
            if (aYW_ == null) {
                return -1;
            }
            hlgVar.bhQ_(aYW_);
            int addMarker = ReTrackDisplay.this.mMapEngine.addMarker(hlgVar, null);
            ReTrackDisplay.this.h.append(addMarker, i);
            return addMarker;
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void clearCurMarker(int i) {
            if (i >= 0) {
                ReTrackDisplay.this.mMapEngine.hideMarker(i, null);
                ReTrackDisplay.this.h.delete(i);
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void resetDismiss() {
            ReTrackDisplay.this.mMapEngine.clear();
            ReTrackDisplay.this.h.clear();
            ReTrackDisplay.this.addGpsTrackLine();
            ReTrackDisplay.this.addLenTrackLine();
            ReTrackDisplay.this.addCustomMarkerList();
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void resetShow() {
            if (ReTrackDisplay.this.mMapEngine != null) {
                ReTrackDisplay.this.mMapEngine.clear();
                ReTrackDisplay.this.h.clear();
            }
            for (Integer num : ReTrackDisplay.this.v.b.keySet()) {
                if (!koq.b(ReTrackDisplay.this.mTrackData, num.intValue())) {
                    if (ReTrackDisplay.this.am.contains(num)) {
                        Bitmap aZd_ = ReTrackDisplay.this.ae.aZd_(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(num.intValue())), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType());
                        if (aZd_ == null) {
                            return;
                        }
                        hlg aYT_ = aYT_(num, aZd_);
                        aYT_.bhP_(ReTrackDisplay.c);
                        int addMarker = ReTrackDisplay.this.mMapEngine.addMarker(aYT_, null);
                        ReTrackDisplay.this.h.append(addMarker, num.intValue());
                        ReTrackDisplay.this.l.put(num, Integer.valueOf(addMarker));
                    } else {
                        Bitmap aYZ_ = ReTrackDisplay.this.ae.aYZ_(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(num.intValue())), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType());
                        if (aYZ_ == null) {
                            return;
                        }
                        hlg aYT_2 = aYT_(num, aYZ_);
                        aYT_2.bhP_(ReTrackDisplay.b);
                        int addMarker2 = ReTrackDisplay.this.mMapEngine.addMarker(aYT_2, null);
                        ReTrackDisplay.this.h.append(addMarker2, num.intValue());
                        ReTrackDisplay.this.l.put(num, Integer.valueOf(addMarker2));
                    }
                } else {
                    LogUtil.h("Track_ReTrackDisplay", "resetShow index is out of bounds");
                    return;
                }
            }
            ReTrackDisplay.this.addUnpaintedGpsTrackLine();
            ReTrackDisplay.this.addLenTrackLine();
            ReTrackDisplay.this.addCustomMarkerList();
        }

        private hlg aYT_(Integer num, Bitmap bitmap) {
            hlg hlgVar = new hlg();
            hlgVar.e(ReTrackDisplay.this.ae.c(ReTrackDisplay.this.v.d(ReTrackDisplay.this.v.e(num.intValue())), ReTrackDisplay.this.mTrackSimplify.getMaxSpeedType()));
            hlgVar.d(ReTrackDisplay.this.mTrackData.get(num.intValue()).getLatLng());
            hlgVar.bhQ_(bitmap);
            return hlgVar;
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void moveMarkerToCurrent(int i) {
            if (koq.d(ReTrackDisplay.this.mTrackData, i)) {
                ReTrackDisplay reTrackDisplay = ReTrackDisplay.this;
                reTrackDisplay.moveMarker(reTrackDisplay.mTrackData.get(i));
                ReTrackDisplay.this.h.append(ReTrackDisplay.this.af, i);
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void addMoveMarker(int i) {
            hlg hlgVar = new hlg();
            hlgVar.bhP_(ReTrackDisplay.e);
            if (koq.b(ReTrackDisplay.this.mTrackData, i)) {
                LogUtil.h("Track_ReTrackDisplay", "addMoveMarker index is out of bounds");
                return;
            }
            hlgVar.d(ReTrackDisplay.this.mTrackData.get(i).getLatLng());
            hlgVar.bhQ_(ReTrackDisplay.this.ae.aZa_(MarkerBuilder.KeyInfoType.ADVANCE_Marker, ReTrackDisplay.this.mTrackSimplify.getSportType(), ReTrackDisplay.this.j()));
            ReTrackDisplay reTrackDisplay = ReTrackDisplay.this;
            reTrackDisplay.af = reTrackDisplay.mMapEngine.addMarker(hlgVar, null);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void deleteMoveMarker() {
            ReTrackDisplay.this.mMapEngine.hideMarker(ReTrackDisplay.this.af, null);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void updateTrack(int i, int i2) {
            if (i < i2) {
                for (int i3 = i + 1; i3 <= i2; i3++) {
                    hld hldVar = new hld();
                    hldVar.a(ReTrackDisplay.this.mTrackData.get(i3).getLineStatus() < 0).c(true).b(hag.a(4.0f)).a(ReTrackDisplay.this.mTrackData.get(i3 - 1).getLatLng()).c(ReTrackDisplay.this.mTrackData.get(i3).getLatLng());
                    if (ReTrackDisplay.this.ab != null) {
                        hldVar.c(Color.parseColor(ReTrackDisplay.this.ab.g()));
                    } else {
                        hldVar.c(ReTrackEngine.d.d);
                    }
                    ReTrackDisplay.this.mMapEngine.addLinePoint(hldVar);
                }
                return;
            }
            while (true) {
                i--;
                if (i < i2) {
                    return;
                } else {
                    ReTrackDisplay.this.mMapEngine.removeLinePoint();
                }
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public ReTrackSimplify obtainSimlify() {
            return ReTrackDisplay.this.mTrackSimplify;
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public int getTrackNumber() {
            return ReTrackDisplay.this.mTrackData.size();
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void setPhotosNumber(int i) {
            ReTrackDisplay.this.setSumPhotosNumber(i);
            ReTrackDisplay.this.updateTrackAnimationControl();
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void setVideoNumber(int i) {
            LogUtil.a("Track_ReTrackDisplay", "setVideoNumber: ", Integer.valueOf(i));
            ReTrackDisplay.this.ap = i;
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack
        public void addVideoPath(Map<Integer, ArrayList<VideoModel>> map) {
            LogUtil.a("Track_ReTrackDisplay", "addVideoPath: ");
            ReTrackDisplay.this.n = map;
        }
    }
}
