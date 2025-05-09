package com.huawei.healthcloud.plugintrack.trackanimation.retrackengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import defpackage.gwg;
import defpackage.hbx;
import defpackage.hbz;
import defpackage.hca;
import defpackage.hcb;
import defpackage.hjg;
import defpackage.hlg;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class ReTrackEngine implements InterfaceReTrack {
    public static final long MARKER_ANIMATION_DURATION = 400;
    public static final long MARKER_ANIMATION_DURATION_DELAYED = 410;
    private static final String TAG = "Track_ReTrackEngine";
    protected Context mContext;
    protected ArrayList<hjg> mCustomMarkerList;
    protected long mLastCameraStartTime;
    protected ArrayList<LenLatLong> mLensData;
    protected Handler mMsgHandler;
    protected TrackAnimationControl mTrackAnimationControl;
    protected ArrayList<LatLong> mTrackData;
    protected FrameLayout mTrackPhotoBackound;
    protected ReTrackSimplify mTrackSimplify;
    protected e mReTrackStateManager = new e();
    protected InterfaceHiMap mMapEngine = null;
    protected TrackAnimationControl.Strength mStrength = TrackAnimationControl.Strength.HIGH;
    protected boolean mIsSupportArea = true;
    protected MapTypeDescription.MapType mMapType = MapTypeDescription.MapType.MAP_TYPE_SATELLITE;
    protected boolean mIsExitReTrack = false;
    protected long mCameraId = 0;
    protected ArrayList<ImageView> mTrackPhotoList = new ArrayList<>();
    protected Map<Integer, InterfaceUpdateReTrack.MarkerType> mAlbumMarkers = new LinkedHashMap();
    protected boolean mIs3dMarker = true;

    protected abstract void addAlbumAnimation(int i);

    protected abstract void addCustomMarkerList();

    protected abstract void addEndMarker();

    protected abstract void addGpsTrackLine();

    protected abstract void addGrowAnimationMarker(hlg hlgVar, int i, int i2);

    protected abstract void addLenTrackLine();

    protected abstract void addMarker(LatLong latLong, Bitmap bitmap);

    protected abstract void addMarkerAnimation(LatLong latLong, int i, int i2, InterfaceUpdateReTrack.MarkerType markerType);

    protected abstract void addMoveMarker();

    protected abstract void addStartMarker();

    protected abstract void addUnpaintedGpsTrackLine();

    protected abstract void drawLine(LatLong latLong, LatLong latLong2);

    protected abstract void drawLines(List<LatLong> list);

    protected abstract void finalWhirlAnimation(float f);

    protected abstract void finalZoomAnimation(float f);

    protected abstract void firstFrameAwait();

    protected abstract void hideAnimationMarker();

    protected abstract void initialWhirlAnimation(float f);

    protected abstract void initialZoomAnimation(float f);

    protected abstract void moveCamera(LenLatLong lenLatLong, LenLatLong lenLatLong2);

    protected abstract void moveMarker(LatLong latLong);

    protected abstract void playVideo(int i);

    public abstract ReTrackEngine setTrackVideo(VideoView videoView);

    protected abstract void spinCamera(LenLatLong lenLatLong);

    protected abstract void stopCamera();

    protected abstract void trackMoveLooper();

    protected abstract void updateAnimation(LenLatLong lenLatLong);

    protected abstract void zoomCamera(float f);

    public ReTrackEngine(Handler handler, ArrayList<LatLong> arrayList, ArrayList<LenLatLong> arrayList2, ReTrackSimplify reTrackSimplify) {
        this.mMsgHandler = null;
        this.mTrackData = null;
        this.mLensData = null;
        this.mTrackSimplify = null;
        this.mTrackAnimationControl = null;
        if (handler == null || arrayList == null || arrayList2 == null || reTrackSimplify == null) {
            this.mReTrackStateManager.b(-1);
        }
        this.mMsgHandler = handler;
        this.mLensData = arrayList2;
        this.mTrackData = arrayList;
        this.mTrackSimplify = reTrackSimplify;
        this.mTrackAnimationControl = getAnimationControl();
        if (reTrackSimplify != null) {
            this.mCustomMarkerList = reTrackSimplify.getHiHealthMarkerList();
        }
    }

    private TrackAnimationControl getAnimationControl() {
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

    public ReTrackEngine setContext(Context context) {
        if (context == null) {
            this.mReTrackStateManager.b(-1);
        }
        this.mContext = context;
        return this;
    }

    public ReTrackEngine setMapEngine(InterfaceHiMap interfaceHiMap) {
        if (interfaceHiMap == null) {
            this.mReTrackStateManager.b(-1);
        }
        this.mMapEngine = interfaceHiMap;
        return this;
    }

    public ReTrackEngine setTrackPhoto(ImageView imageView, ImageView imageView2, ImageView imageView3) {
        this.mTrackPhotoList.add(imageView3);
        this.mTrackPhotoList.add(imageView2);
        this.mTrackPhotoList.add(imageView);
        return this;
    }

    public ReTrackEngine setTrackPhotoBackground(FrameLayout frameLayout) {
        if (frameLayout == null) {
            this.mReTrackStateManager.b(-1);
        }
        this.mTrackPhotoBackound = frameLayout;
        return this;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceReTrack
    public void messageHandle(Message message) {
        if (message == null) {
            return;
        }
        firstMessageHandler(message);
        secondMessageHandler(message);
    }

    private void firstMessageHandler(Message message) {
        int i = message.what;
        if (i == 16) {
            addGpsTrackLine();
            addLenTrackLine();
            addCustomMarkerList();
        }
        switch (i) {
            case 48:
                if (!this.mIsExitReTrack && (message.obj instanceof Float)) {
                    initialZoomAnimation(((Float) message.obj).floatValue());
                    break;
                }
                break;
            case 49:
                if (!this.mIsExitReTrack && (message.obj instanceof Float)) {
                    initialWhirlAnimation(((Float) message.obj).floatValue());
                    break;
                }
                break;
            case 50:
                if (!this.mIsExitReTrack && (message.obj instanceof Float)) {
                    finalZoomAnimation(((Float) message.obj).floatValue());
                    break;
                }
                break;
            case 51:
                if (!this.mIsExitReTrack && (message.obj instanceof Float)) {
                    finalWhirlAnimation(((Float) message.obj).floatValue());
                    break;
                }
                break;
            case 52:
                firstFrameAwait();
                break;
        }
    }

    private void secondMessageHandler(Message message) {
        List<LatLong> list;
        int i = message.what;
        if (i == 64) {
            if (this.mIsExitReTrack) {
                return;
            }
            this.mReTrackStateManager.b(3);
            trackMoveLooper();
        }
        if (i == 65) {
            if (this.mIsExitReTrack || !hbz.b(message.obj, LatLong.class) || (list = (List) message.obj) == null || list.size() <= 0) {
                return;
            }
            drawLines(list);
            moveMarker(list.get(list.size() - 1));
            return;
        }
        switch (i) {
            case 80:
                if (!this.mIsExitReTrack && (message.obj instanceof LenLatLong)) {
                    updateAnimation((LenLatLong) message.obj);
                    break;
                }
                break;
            case 81:
                if (!this.mIsExitReTrack && hbz.d(message.obj, LenLatLong.class, LenLatLong.class)) {
                    Pair pair = (Pair) message.obj;
                    this.mLastCameraStartTime = System.currentTimeMillis();
                    moveCamera((LenLatLong) pair.first, (LenLatLong) pair.second);
                    break;
                }
                break;
            case 82:
                if (!this.mIsExitReTrack && (message.obj instanceof LenLatLong)) {
                    this.mLastCameraStartTime = System.currentTimeMillis();
                    spinCamera((LenLatLong) message.obj);
                    break;
                }
                break;
            default:
                thirdMessageHandler(message);
                break;
        }
    }

    private void thirdMessageHandler(Message message) {
        int i = message.what;
        if (i == 83) {
            if (this.mIsExitReTrack || !(message.obj instanceof Float)) {
                return;
            }
            this.mLastCameraStartTime = System.currentTimeMillis();
            zoomCamera(((Float) message.obj).floatValue());
            return;
        }
        if (i == 130) {
            playVideo(((Integer) message.obj).intValue());
            return;
        }
        if (i == 112) {
            if (this.mIsExitReTrack || !hbz.d(message.obj, LatLong.class, Bitmap.class)) {
                return;
            }
            Pair pair = (Pair) message.obj;
            addMarker((LatLong) pair.first, (Bitmap) pair.second);
            return;
        }
        if (i == 113) {
            addAnimationGrowMarker(message);
            return;
        }
        if (i != 115) {
            if (i == 116) {
                addAlbumAnimation(((Integer) message.obj).intValue());
                return;
            } else {
                fourthMessageHandler(message);
                return;
            }
        }
        if (this.mIsExitReTrack || !hbz.d(message.obj, LatLong.class, Integer.class, Integer.class, InterfaceUpdateReTrack.MarkerType.class)) {
            return;
        }
        Pair pair2 = (Pair) message.obj;
        addMarkerAnimation((LatLong) ((Pair) pair2.first).first, ((Integer) ((Pair) pair2.first).second).intValue(), ((Integer) ((Pair) pair2.second).first).intValue(), (InterfaceUpdateReTrack.MarkerType) ((Pair) pair2.second).second);
    }

    private void addAnimationGrowMarker(Message message) {
        if (!this.mIsExitReTrack && (message.obj instanceof hlg)) {
            addGrowAnimationMarker((hlg) message.obj, message.arg1, message.arg2);
        }
    }

    private void fourthMessageHandler(Message message) {
        int i = message.what;
        if (i == 84) {
            if (this.mIsExitReTrack) {
                return;
            }
            stopCamera();
        } else if (i == 114) {
            hideAnimationMarker();
        } else if (i == 119 && hbz.d(message.obj, Integer.class, InterfaceUpdateReTrack.MarkerType.class)) {
            Pair pair = (Pair) message.obj;
            addAlbumMarker(((Integer) pair.first).intValue(), (InterfaceUpdateReTrack.MarkerType) pair.second);
        }
    }

    protected static class e {

        /* renamed from: a, reason: collision with root package name */
        private final Object f3608a = new Object();
        private int b = 0;

        protected e() {
        }

        public void b(int i) {
            synchronized (this.f3608a) {
                this.b = i;
            }
        }

        public int e() {
            int i;
            synchronized (this.f3608a) {
                i = this.b;
            }
            return i;
        }
    }

    protected static class d {
        public static final int d = Color.rgb(251, 101, 34);

        /* renamed from: a, reason: collision with root package name */
        public static final int f3607a = Color.rgb(251, 101, 34);
        public static final int c = Color.rgb(217, 217, 217);
        public static final int b = Color.rgb(217, 217, 217);
        public static final int e = Color.rgb(0, 255, 7);

        protected d() {
        }
    }
}
