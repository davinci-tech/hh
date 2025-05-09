package com.huawei.healthcloud.plugintrack.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.huawei.health.R;
import com.huawei.health.motiontrack.api.TrackManagerApi;
import com.huawei.health.motiontrack.api.ViewHolderBase;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import defpackage.ffd;
import defpackage.gso;
import defpackage.gtc;
import defpackage.gtx;
import defpackage.gve;
import defpackage.gvv;
import defpackage.gwk;
import defpackage.gxd;
import defpackage.hln;
import defpackage.hmh;
import defpackage.hpu;
import defpackage.hpz;
import defpackage.hqa;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class TrackManagerImpl implements TrackManagerApi {
    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean getRunWorkoutStarted() {
        return gtx.c(BaseApplication.getContext()).ah();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean getVoiceEnable() {
        return gtx.c(BaseApplication.getContext()).al();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void initTargetList(List<ffd> list) {
        gso.e().d(list);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public ViewHolderBase getChartViewHolder(Context context, int i) {
        return new hmh(context, 1, i);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public int getHeartPostureType(int i) {
        HwSportTypeInfo d = hln.c(com.huawei.haf.application.BaseApplication.e()).d(i);
        if (d == null) {
            return 1;
        }
        return d.getHeartPostureType();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public Drawable acquireSportTypeDrawable(int i) {
        hln c = hln.c(BaseApplication.getContext());
        Drawable drawable = BaseApplication.getContext().getDrawable(R.drawable.ic_health_list_outdoor_running);
        if (c.d(i) == null || c.d(i).getHistoryList() == null) {
            LogUtil.a("Track_TrackManagerImpl", "getSportTypeInfoById =null sportType= ", Integer.valueOf(i));
            return drawable;
        }
        return c.d(i).getHistoryList().getItemDrawable();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean isSportingStatus() {
        return gso.e().p();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public Map<Integer, Float> validPaceMap(Map<Integer, Float> map) {
        return gvv.a(map);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public boolean sentHeartZoneSplicingMessages() {
        return hpz.b().a();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void pushRqData(int i) {
        new hqa().b(BaseApplication.getContext(), i);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void getUserRunLevelDataByRq(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        new hqa().a(com.huawei.haf.application.BaseApplication.e(), i, i2, i3, iBaseResponseCallback);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void getTrackDraw(List<Map<Long, double[]>> list, IBaseResponseCallback iBaseResponseCallback) {
        hpu.b(list, iBaseResponseCallback);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void getTrackDraw(List<Long> list, List<Long> list2, IBaseResponseCallback iBaseResponseCallback) {
        hpu.a(list, list2, BaseApplication.getContext(), iBaseResponseCallback);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public MotionPath readTemporaryMotionPath(Context context, String str) {
        return gwk.c(context, str, 0);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void initGolfDeviceEngineManager() {
        GolfDeviceProxy.getInstance().sendDevicePullSuccessMsg();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void startTrackDetail(long j, long j2) {
        gtc.c(j, j2);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public int startRunCourseSport(Context context, int i, int i2, float f, Bundle bundle) {
        gso.e().init(context);
        Bundle aTm_ = gso.e().aTm_(i, i2, f, -1);
        aTm_.putAll(bundle);
        int aTs_ = gso.e().aTs_(0, aTm_, null, context, null);
        ReleaseLogUtil.b("Track_TrackManagerImpl", "startRunCourseSport responseCode = ", Integer.valueOf(aTs_));
        return aTs_;
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void startNearTrackDetail() {
        gtc.c();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void gotoSport(Context context, int i, int i2, float f) {
        gso.e().a(context, i, i2, f);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void playSound(Object obj, String str) {
        LogUtil.c("Track_TrackManagerImpl", "playSound:", obj, " sound desc:", str);
        if (obj instanceof Integer) {
            gxd.a().c(((Integer) obj).intValue());
            return;
        }
        if (obj instanceof String) {
            gxd.a().b((String) obj);
            return;
        }
        if (obj instanceof int[]) {
            gxd.a().a((int[]) obj);
        } else if (obj instanceof String[]) {
            gxd.a().e((String[]) obj, str);
        } else {
            LogUtil.a("Track_TrackManagerImpl", "playSound sound else ");
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void playSound(String str, Object obj, String str2) {
        LogUtil.c("Track_TrackManagerImpl", "playSound player:", str, " sound:", obj, " sound desc:", str2);
        if (obj instanceof String) {
            gxd.a().d(str, (String) obj);
        } else {
            ReleaseLogUtil.a("Track_TrackManagerImpl", "playSound failed player:", str, " sound:", obj, " sound desc:", str2);
        }
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void sendPlayerCommand(String str, int i) {
        ReleaseLogUtil.b("Track_TrackManagerImpl", "setPlayerStatus player:", str, " playerCommand:", Integer.valueOf(i));
        gxd.a().d(str, i);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void startVoiceService(String str, String str2, int i) {
        ReleaseLogUtil.b("Track_TrackManagerImpl", " startVoiceService moduleName:", str, " playerName:", str2, " playerType:", Integer.valueOf(i));
        gxd.a().d(str, str2, i);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void stopVoiceService(String str) {
        ReleaseLogUtil.b("Track_TrackManagerImpl", " stopVoiceService moduleName:", str);
        gxd.a().f(str);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public List<Integer> getFitnessSportTypeList(String str) {
        return hln.c(BaseApplication.getContext()).e(str);
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public void pourInterfaceToKitSportApi() {
        gve.d();
    }

    @Override // com.huawei.health.motiontrack.api.TrackManagerApi
    public int getSportState() {
        return gso.e().i();
    }
}
