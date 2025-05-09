package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.haf.router.routes.AppRoute$$Info$$HuaweiHealth;
import com.huawei.health.R;
import com.huawei.health.motiontrack.api.TrackManagerApi;
import com.huawei.health.motiontrack.api.ViewHolderBase;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public class eme extends AppBundlePluginProxy<TrackManagerApi> implements TrackManagerApi {
    private static volatile eme d;
    private TrackManagerApi c;

    private eme() {
        super("TrackManagerProxy", AppRoute$$Info$$HuaweiHealth.M_51, "com.huawei.healthcloud.plugintrack.impl.TrackManagerImpl");
        this.c = createPluginApi();
    }

    public static eme b() {
        eme emeVar;
        if (d == null) {
            synchronized (eme.class) {
                if (d == null) {
                    d = new eme();
                }
                emeVar = d;
            }
            return emeVar;
        }
        return d;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return d != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(TrackManagerApi trackManagerApi) {
        this.c = trackManagerApi;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean getRunWorkoutStarted() {
        if (isPluginAvaiable()) {
            return this.c.getRunWorkoutStarted();
        }
        return false;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean getVoiceEnable() {
        if (isPluginAvaiable()) {
            return this.c.getVoiceEnable();
        }
        return false;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void initTargetList(List<ffd> list) {
        if (isPluginAvaiable()) {
            this.c.initTargetList(list);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public ViewHolderBase getChartViewHolder(Context context, int i) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.getChartViewHolder(context, i);
        }
        return null;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public int getHeartPostureType(int i) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.getHeartPostureType(i);
        }
        return 1;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean isSportingStatus() {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.isSportingStatus();
        }
        return false;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public Map<Integer, Float> validPaceMap(Map<Integer, Float> map) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.validPaceMap(map);
        }
        return new TreeMap();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean sentHeartZoneSplicingMessages() {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.sentHeartZoneSplicingMessages();
        }
        return false;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void pushRqData(int i) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.pushRqData(i);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void getUserRunLevelDataByRq(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.getUserRunLevelDataByRq(i, i2, i3, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void getTrackDraw(List<Map<Long, double[]>> list, IBaseResponseCallback iBaseResponseCallback) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.getTrackDraw(list, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void getTrackDraw(List<Long> list, List<Long> list2, IBaseResponseCallback iBaseResponseCallback) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.getTrackDraw(list, list2, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public MotionPath readTemporaryMotionPath(Context context, String str) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi == null) {
            return null;
        }
        trackManagerApi.readTemporaryMotionPath(context, str);
        return null;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void initGolfDeviceEngineManager() {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.initGolfDeviceEngineManager();
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void startTrackDetail(long j, long j2) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.startTrackDetail(j, j2);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void startNearTrackDetail() {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.startNearTrackDetail();
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void gotoSport(Context context, int i, int i2, float f) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.gotoSport(context, i, i2, f);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public int startRunCourseSport(Context context, int i, int i2, float f, Bundle bundle) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.startRunCourseSport(context, i, i2, f, bundle);
        }
        return 1;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void playSound(Object obj, String str) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.playSound(obj, str);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void playSound(String str, Object obj, String str2) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.playSound(str, obj, str2);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void sendPlayerCommand(String str, int i) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.sendPlayerCommand(str, i);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void startVoiceService(String str, String str2, int i) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.startVoiceService(str, str2, i);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void stopVoiceService(String str) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.stopVoiceService(str);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public List<Integer> getFitnessSportTypeList(String str) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.getFitnessSportTypeList(str);
        }
        return new ArrayList();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void pourInterfaceToKitSportApi() {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            trackManagerApi.pourInterfaceToKitSportApi();
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public int getSportState() {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.getSportState();
        }
        return -1;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public Drawable acquireSportTypeDrawable(int i) {
        TrackManagerApi trackManagerApi = this.c;
        if (trackManagerApi != null) {
            return trackManagerApi.acquireSportTypeDrawable(i);
        }
        return BaseApplication.getContext().getDrawable(R.drawable.ic_health_list_outdoor_running);
    }
}
