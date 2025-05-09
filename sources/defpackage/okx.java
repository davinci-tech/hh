package defpackage;

import android.media.session.PlaybackState;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;
import androidx.core.util.Consumer;
import com.alipay.sdk.m.p.e;
import com.google.android.gms.common.util.BiConsumer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.vengine.H5ProViewControls;
import com.huawei.health.musicservice.api.IAudioPlayer;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter;
import com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView;
import com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback;
import com.huawei.ui.main.R$string;
import defpackage.okx;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class okx extends JsBaseModule {
    private BottomPlayerView d;
    private H5ProViewControls e;
    private Runnable j;

    /* renamed from: a, reason: collision with root package name */
    private final oli f15757a = oli.a();
    private final b c = new b();
    private boolean b = false;

    private int d(int i) {
        if (i == 3) {
            return 1;
        }
        if (i == 99) {
            return i;
        }
        return 0;
    }

    final class b extends MediaStatusSubscriberAdapter {
        private final ConcurrentHashMap<String, Long> b;

        private b() {
            this.b = new ConcurrentHashMap<>();
        }

        void a(String str, long j) {
            this.b.put(str, Long.valueOf(j));
        }

        @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            Iterator<Long> it = this.b.values().iterator();
            while (it.hasNext()) {
                okx.this.e(it.next().longValue(), "onPlaybackStateChanged");
            }
        }

        @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
        public void onMediaChanged(enq enqVar, int i) {
            Iterator<Long> it = this.b.values().iterator();
            while (it.hasNext()) {
                okx.this.d(it.next().longValue(), "onMediaChanged", i);
            }
            okx.this.b();
            okx.this.b = false;
        }

        @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
        public void onProgressChanged(int i, int i2, float f, int i3) {
            Iterator<Long> it = this.b.values().iterator();
            while (it.hasNext()) {
                okx.this.c(it.next().longValue(), "onProgressChanged");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, String str) {
        d(j, str, this.f15757a.g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, String str) {
        String d = this.f15757a.f().d();
        int d2 = d(this.f15757a.n());
        float l = this.f15757a.l();
        int r = this.f15757a.r();
        String h = this.f15757a.f().h();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("playprogressTime", r);
            jSONObject.put("playProgress", l);
            jSONObject.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, h);
            jSONObject.put("time", d);
            jSONObject.put("playStatus", d2);
            onSuccessCallback(j, jSONObject);
            LogUtil.a("HealthHeadLinesInnerApi", str, " callbackProgressToH5 callbackId = ", Long.valueOf(j), ", params = ", jSONObject);
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j, String str, int i) {
        int d;
        int e = oli.a().e();
        LogUtil.a("HealthHeadLinesInnerApi", "getCurrentHomePageState = ", Integer.valueOf(e));
        if (e == 3) {
            d = 1;
        } else {
            d = e == 6 ? 3 : d(this.f15757a.n());
        }
        enq f = this.f15757a.f();
        String d2 = f.d();
        String h = f.h();
        float l = this.f15757a.l();
        int r = this.f15757a.r();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("playStatus", d);
            jSONObject.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, h);
            jSONObject.put("time", d2);
            jSONObject.put("playprogressTime", r);
            jSONObject.put("playProgress", l);
            jSONObject.put("index", i);
            onSuccessCallback(j, jSONObject);
            LogUtil.a("HealthHeadLinesInnerApi", str, " callbackToH5 callbackId = ", Long.valueOf(j), ", params = ", jSONObject);
        } catch (JSONException e2) {
            onFailureCallback(j, e2.getMessage());
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        LogUtil.a("HealthHeadLinesInnerApi", "onCreate");
        this.e = c();
        this.f15757a.a(this.c);
        this.f15757a.d(this);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("HealthHeadLinesInnerApi", "onDestroy");
        this.f15757a.b(this.c);
        this.f15757a.a(this);
        e();
        this.e = null;
    }

    public H5ProViewControls c() {
        if (this.mH5ProInstance == null) {
            LogUtil.b("HealthHeadLinesInnerApi", "getH5ProViewControls failed, cause mH5ProInstance is null!");
            return null;
        }
        return this.mH5ProInstance.getViewControls();
    }

    @JavascriptInterface
    public void postInfo(long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback postInfo callbackId = ", Long.valueOf(j), ", params = ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID);
            String optString2 = jSONObject.optString("audioId");
            if (TextUtils.isEmpty(optString)) {
                optString = this.f15757a.a(optString2);
            }
            if (TextUtils.isEmpty(optString)) {
                LogUtil.b("HealthHeadLinesInnerApi", "postInfo empty with workoutId = ", optString, ", audioId = ", optString2);
            }
            int e = this.f15757a.e(optString);
            if (e < 0) {
                LogUtil.b("HealthHeadLinesInnerApi", "postInfo failed, cause not found mediaId = ", optString, " in list!");
            } else {
                this.f15757a.c(e, 3);
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:param invalid");
        }
    }

    @JavascriptInterface
    public boolean isNeedShowMobileDataDialog() {
        LogUtil.a("HealthHeadLinesInnerApi", "isNeedShowWifiDialog enter.");
        return omn.e();
    }

    @JavascriptInterface
    public void checkMobileDataDialogBeforePlay(final long j) {
        omn.d(new MediaCenterCallback() { // from class: okx.3
            @Override // com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback
            public void onResponse(int i, String str) {
                LogUtil.a("HealthHeadLinesInnerApi", "checkMobileDataDialogBeforePlay when play = ", Integer.valueOf(i));
                if (i == 0) {
                    okx.this.onSuccessCallback(j, "success");
                } else {
                    okx.this.onSuccessCallback(j, ParamConstants.CallbackMethod.ON_FAIL);
                }
            }
        });
    }

    @JavascriptInterface
    public void getPlayStatus(long j) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback getPlayStatus callbackId = ", Long.valueOf(j));
        this.c.a("getPlayStatus", j);
        e(j, "getPlayStatus");
    }

    @JavascriptInterface
    public void changePlayStatus(final long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback changePlayStatus callbackId = ", Long.valueOf(j), ", params = ", str);
        try {
            final int optInt = new JSONObject(str).optInt("playStatus");
            if (!this.f15757a.d("H5changePlayStatus")) {
                Consumer<Boolean> consumer = new Consumer() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInnerApi$$ExternalSyntheticLambda0
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj) {
                        okx.this.b(optInt, j, (Boolean) obj);
                    }
                };
                List<enq> t = this.f15757a.t();
                List<oly> w = this.f15757a.w();
                if (koq.b(t) || koq.b(w)) {
                    LogUtil.b("HealthHeadLinesInnerApi", "playWithInitList failed cause play list is empty!");
                    return;
                }
                oli oliVar = this.f15757a;
                oliVar.b(t, w, oliVar.g(), consumer);
                this.f15757a.y();
                return;
            }
            int n = this.f15757a.n();
            if (optInt == 1) {
                if (n == 2) {
                    this.f15757a.ab();
                } else {
                    this.f15757a.c(r7.g());
                }
                onSuccessCallback(j, "success");
                return;
            }
            if (optInt != 1 && n == 3) {
                this.f15757a.ad();
                onSuccessCallback(j, "success");
            } else {
                LogUtil.h("HealthHeadLinesInnerApi", "changePlayStatus playStatus is ", Integer.valueOf(optInt), ", lastPlayStatus = ", Integer.valueOf(n));
                onSuccessCallback(j, "no need to changePlayStatus");
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:param invalid");
        }
    }

    public /* synthetic */ void b(int i, long j, Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        if (i == 1) {
            this.f15757a.c(r3.g());
        } else {
            this.f15757a.ad();
        }
        onSuccessCallback(j, "success");
    }

    @JavascriptInterface
    public void changePlayProgress(long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback changePlayProgress callbackId = ", Long.valueOf(j), ", params = ", str);
        try {
            double optDouble = new JSONObject(str).optDouble("playProgress");
            LogUtil.a("HealthHeadLinesInnerApi", "changePlayProgress playProgress is ", Double.valueOf(optDouble));
            this.f15757a.b((long) (optDouble * this.f15757a.h()));
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:param invalid");
        }
    }

    @JavascriptInterface
    public void isShowEntrance(long j) {
        LogUtil.a("HealthHeadLinesInnerApi", "isShowEntrance ", Boolean.valueOf(oko.d()));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isShowEntrance", oko.d());
            onSuccessCallback(j, jSONObject);
        } catch (JSONException unused) {
            onFailureCallback(j, "isShowEntrance error");
        }
    }

    @JavascriptInterface
    public void changeEntranceStatus(long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "changeEntranceStatus");
        try {
            boolean optBoolean = new JSONObject(str).optBoolean("isShowEntrance");
            LogUtil.a("HealthHeadLinesInnerApi", "changeEntranceStatus isShowEntrance ", Boolean.valueOf(optBoolean));
            oko.e(optBoolean);
            ObserverManagerUtil.c("HOME_RECYCLE_VIEW_MOVE", Boolean.valueOf(optBoolean), true);
            onSuccessCallback(j, "success");
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:param invalid");
        }
    }

    @JavascriptInterface
    public void isEntranceBeResident(long j, String str) {
        try {
            boolean optBoolean = new JSONObject(str).optBoolean("isEntranceBeResident");
            LogUtil.a("HealthHeadLinesInnerApi", "isEntranceBeResident: ", Boolean.valueOf(optBoolean));
            if (optBoolean) {
                oko.c(optBoolean);
                onSuccessCallback(j, "success");
            } else {
                onFailureCallback(j, "not beyond 3");
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:param invalid");
        }
    }

    private boolean b(String str, int i) {
        if (str == null || str.equals(Constants.NULL)) {
            ReleaseLogUtil.d("R_HealthHeadLinesInnerApi", "selMediaId is null.");
            return false;
        }
        int n = this.f15757a.n();
        boolean equals = TextUtils.equals(str, this.f15757a.k());
        if (n == 3 && equals) {
            ReleaseLogUtil.e("R_HealthHeadLinesInnerApi", "Current media is playing.");
            return true;
        }
        if ((n == 2 || n == 1) && equals) {
            ReleaseLogUtil.e("R_HealthHeadLinesInnerApi", "Current media is not playing. set play to continue.");
            this.f15757a.b(i);
            return true;
        }
        int e = this.f15757a.e(str);
        if (e < 0) {
            ReleaseLogUtil.d("R_HealthHeadLinesInnerApi", "skipToListSelMediaIfNeeded failed, cause not found selMediaId = ", str, " in list!");
            return false;
        }
        this.f15757a.c(e, i);
        return true;
    }

    @JavascriptInterface
    public void getPlayList(final long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback getPlayList callbackId = ", Long.valueOf(j), ", params = ", str);
        this.f15757a.c("2");
        this.f15757a.g("2");
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean optBoolean = jSONObject.optBoolean("isLocalList");
            final int optInt = jSONObject.optInt("type");
            final String optString = jSONObject.optString(e.n);
            final int optInt2 = jSONObject.optInt("pageNum");
            final String optString2 = jSONObject.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID);
            final boolean optBoolean2 = jSONObject.optBoolean("isUseForPlay");
            String optString3 = jSONObject.optString("time");
            LogUtil.a("HealthHeadLinesInnerApi", "getPlayList type: ", Integer.valueOf(optInt), " lectureOrTopicId: ", optString, " pageNum: ", Integer.valueOf(optInt2), " workoutId: ", optString2, " isUserForPlay: ", Boolean.valueOf(optBoolean2));
            this.b = !optString2.equals(this.f15757a.f().h());
            if (!CommonUtil.aa(this.mContext)) {
                nrh.c(this.mContext, this.mContext.getResources().getString(R$string.IDS_network_connect_error));
                onFailureCallback(j, "netWork is disConnected");
            }
            Consumer consumer = new Consumer() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInnerApi$$ExternalSyntheticLambda2
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    okx.this.d(j, optInt2, (JSONArray) obj);
                }
            };
            BiConsumer<List<enq>, List<oly>> biConsumer = new BiConsumer() { // from class: old
                @Override // com.google.android.gms.common.util.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    okx.this.e(optBoolean2, optString2, optInt, optInt2, optString, j, (List) obj, (List) obj2);
                }
            };
            List<oly> c = optBoolean ? this.f15757a.c() : null;
            this.f15757a.g(optInt);
            switch (optInt) {
                case 1:
                case 3:
                case 10:
                    onFailureCallback(j, "loadH5ProApp fail:type invalid");
                    break;
                case 2:
                case 6:
                    ojw.a(biConsumer, optString2, optInt2, optString3);
                    break;
                case 4:
                    ojw.e(consumer, biConsumer, c);
                    break;
                case 5:
                    ojw.c((Consumer<JSONArray>) consumer, biConsumer, c);
                    break;
                case 7:
                    ojw.a(optString, (Consumer<JSONArray>) consumer, biConsumer, c);
                    this.f15757a.c(optString);
                    break;
                case 8:
                case 9:
                    ojz.e().a(optString, biConsumer, optInt2);
                    this.f15757a.g(optString);
                    break;
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:parameter invalid");
        }
    }

    public /* synthetic */ void d(long j, int i, JSONArray jSONArray) {
        if (jSONArray == null) {
            LogUtil.b("HealthHeadLinesInnerApi", "fetch h5 play list failed, return!");
        } else {
            c(j, i, jSONArray);
        }
    }

    /* synthetic */ void e(final boolean z, final String str, final int i, int i2, String str2, long j, List list, List list2) {
        JSONArray b2;
        boolean d = this.f15757a.d("H5getPlayList");
        Consumer<Boolean> consumer = new Consumer() { // from class: com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInnerApi$$ExternalSyntheticLambda4
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                okx.this.b(z, str, i, (Boolean) obj);
            }
        };
        int i3 = 0;
        boolean z2 = i == 2 || i == 6;
        boolean z3 = i == 8 || i == 9;
        if (koq.c(list) && koq.c(list2)) {
            if (z2) {
                b(list, list2, d, str, consumer);
                if (i2 == 0) {
                    this.f15757a.y();
                }
            } else if (i2 == 0) {
                b(list, list2, d, str, consumer);
                this.f15757a.y();
            } else if (z3 && i2 > 0 && ojz.e().c(i2, str2)) {
                this.f15757a.a((List<enq>) list, (List<oly>) list2);
            }
        }
        if (z2) {
            i3 = 30;
        } else if (z3) {
            i3 = 20;
        } else if (list2 != null) {
            i3 = list2.size();
        }
        if (!z3) {
            b2 = ojw.a(list2, i2, i3);
        } else {
            b2 = ojw.b(list2);
        }
        LogUtil.a("HealthHeadLinesInnerApi", "nativePlayerListCallback callbackId = ", Long.valueOf(j), ", pageNum = ", Integer.valueOf(i2), ", h5JsonArray = ", b2);
        c(j, i2, b2);
        if (d) {
            consumer.accept(true);
        }
    }

    public /* synthetic */ void b(boolean z, String str, int i, Boolean bool) {
        if (bool != null && bool.booleanValue() && z) {
            ReleaseLogUtil.e("R_HealthHeadLinesInnerApi", "need to play! selMediaId = ", str);
            if (b(str, i)) {
                oli.a().h(3);
            } else {
                LogUtil.a("HealthHeadLinesInnerApi", "skipToListSelMediaIfNeeded fail.");
                oli.a().h(99);
            }
        }
    }

    private void b(List<enq> list, List<oly> list2, boolean z, String str, Consumer<Boolean> consumer) {
        oli oliVar = this.f15757a;
        int g = oliVar.g();
        if (z) {
            consumer = null;
        }
        oliVar.b(list, list2, g, consumer);
        int e = this.f15757a.e(str);
        LogUtil.a("HealthHeadLinesInnerApi", "aboutToPlayIndex in setPlayList : ", Integer.valueOf(e));
        if (e >= 0) {
            this.f15757a.c(e);
        }
    }

    @JavascriptInterface
    public void deleteWorkoutId(long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback deleteWorkoutId callbackId = ", Long.valueOf(j), ", params = ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("deleteWorkoutId");
            LogUtil.a("HealthHeadLinesInnerApi", "deleteWorkoutId: ", optString, " deleteAudioId: ", jSONObject.optString("deleteAudioId"));
            this.f15757a.b(optString);
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:parameter invalid");
        }
    }

    private void a() {
        if (this.d == null) {
            LogUtil.h("HealthHeadLinesInnerApi", "mBottomPlayerView == null in refreshMiniPlayerUi.");
            return;
        }
        this.d.e(this.f15757a.f(), this.f15757a.n(), this.f15757a.m());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d() {
        RelativeLayout.LayoutParams layoutParams;
        if (this.e == null) {
            LogUtil.h("HealthHeadLinesInnerApi", "showMiniPlayer mH5ProViewControls == null.");
            return;
        }
        LogUtil.a("HealthHeadLinesInnerApi", "showMiniPlayer mBottomPlayerView begin = ", this.d);
        if (this.d != null) {
            this.e.removeAllNativeView();
            this.e.showNativeView(this.d);
            a();
            return;
        }
        if (this.mContext == null) {
            LogUtil.h("HealthHeadLinesInnerApi", "showMiniPlayer mContext = null.");
            return;
        }
        BottomPlayerView bottomPlayerView = new BottomPlayerView(this.mContext, this);
        this.d = bottomPlayerView;
        ViewGroup.LayoutParams layoutParams2 = bottomPlayerView.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(layoutParams2);
        }
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        this.d.setLayoutParams(layoutParams);
        LogUtil.a("HealthHeadLinesInnerApi", "showMiniPlayer mBottomPlayerView created = ", this.d);
        if (this.e != null) {
            LogUtil.a("HealthHeadLinesInnerApi", "mH5ProViewControls not null, show minibar");
            this.e.showNativeView(this.d);
        }
        a();
    }

    private void b(long j) {
        e();
        onSuccessCallback(j);
    }

    public void e() {
        BottomPlayerView bottomPlayerView = this.d;
        if (bottomPlayerView != null) {
            bottomPlayerView.c();
        }
        H5ProViewControls h5ProViewControls = this.e;
        if (h5ProViewControls == null) {
            LogUtil.b("HealthHeadLinesInnerApi", "hideMiniPlayer failed, mH5ProViewControls is null!");
            return;
        }
        h5ProViewControls.removeAllNativeView();
        LogUtil.a("HealthHeadLinesInnerApi", "hideMiniPlayer done mBottomPlayerView = ", this.d);
        this.d = null;
    }

    @JavascriptInterface
    public void showMiniPlayer(long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback showMiniPlayer callbackId = ", Long.valueOf(j), ", params = ", str);
        try {
            boolean optBoolean = new JSONObject(str).optBoolean("showMiniPlayer");
            if (!CommonUtil.aa(this.mContext)) {
                LogUtil.b("HealthHeadLinesInnerApi", "netWork is disConnected");
                onFailureCallback(j, "netWork is disConnected");
                return;
            }
            if (optBoolean) {
                LogUtil.a("HealthHeadLinesInnerApi", "showMiniPlayer currentItem = ", this.f15757a.f().h(), ", mIsSkippingToOtherMedia = ", Boolean.valueOf(this.b));
                if (!TextUtils.isEmpty(this.f15757a.f().h()) && !this.b) {
                    if (b()) {
                        return;
                    }
                    d();
                    return;
                } else {
                    LogUtil.a("HealthHeadLinesInnerApi", "do not showMiniPlayer now, to schedule as a task!");
                    this.j = new Runnable() { // from class: oky
                        @Override // java.lang.Runnable
                        public final void run() {
                            okx.this.d();
                        }
                    };
                    return;
                }
            }
            LogUtil.a("HealthHeadLinesInnerApi", "hideMiniPlayer now");
            b(j);
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:parameter invalid");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        Runnable runnable = this.j;
        if (runnable != null) {
            runnable.run();
            this.j = null;
            LogUtil.a("HealthHeadLinesInnerApi", "executeShowMiniPlayerTask true");
            return true;
        }
        LogUtil.a("HealthHeadLinesInnerApi", "executeShowMiniPlayerTask false");
        return false;
    }

    @JavascriptInterface
    public void setPlayMode(long j, String str) {
        try {
            IAudioPlayer.PlayMode a2 = a(new JSONObject(str).optInt("playMode"));
            LogUtil.a("HealthHeadLinesInnerApi", "H5 callback setPlayMode callbackId = ", Long.valueOf(j), ", params = ", str, ", playMode = ", a2.name());
            this.f15757a.a(a2);
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:parameter invalid");
        }
    }

    private IAudioPlayer.PlayMode a(int i) {
        if (i == 2) {
            return IAudioPlayer.PlayMode.RANDOM_CIRCLE;
        }
        if (i == 3) {
            return IAudioPlayer.PlayMode.SEQUENCE_CIRCLE;
        }
        if (i == 5) {
            return IAudioPlayer.PlayMode.PLAY_ONCE;
        }
        return IAudioPlayer.PlayMode.SEQUENCE_PLAY;
    }

    /* renamed from: okx$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[IAudioPlayer.PlayMode.values().length];
            e = iArr;
            try {
                iArr[IAudioPlayer.PlayMode.RANDOM_CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[IAudioPlayer.PlayMode.SEQUENCE_CIRCLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[IAudioPlayer.PlayMode.PLAY_ONCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[IAudioPlayer.PlayMode.SEQUENCE_PLAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private int c(IAudioPlayer.PlayMode playMode) {
        int i = AnonymousClass1.e[playMode.ordinal()];
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 1 : 5;
        }
        return 3;
    }

    @JavascriptInterface
    public void getPlayMode(long j) {
        JSONObject jSONObject = new JSONObject();
        IAudioPlayer.PlayMode o = this.f15757a.o();
        int c = c(o);
        LogUtil.a("HealthHeadLinesInnerApi", "getPlayMode playMode = ", o.name(), ", callbackId = ", Long.valueOf(j));
        try {
            jSONObject.put("playMode", c);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void changeMusic(long j, String str) {
        LogUtil.a("HealthHeadLinesInnerApi", "H5 callback changeMusic callbackId = ", Long.valueOf(j), ", params = ", str);
        try {
            int optInt = new JSONObject(str).optInt("changeMusic");
            this.c.a("changeMusic", j);
            if (optInt == 0) {
                this.f15757a.ag();
            } else if (optInt == 1) {
                this.f15757a.ac();
            } else {
                LogUtil.h("HealthHeadLinesInnerApi", "changeMusic is wrong!");
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:parameter invalid");
        }
    }

    private void c(long j, int i, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("playList", jSONArray);
            jSONObject.put("pageNum", i);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
        }
    }
}
