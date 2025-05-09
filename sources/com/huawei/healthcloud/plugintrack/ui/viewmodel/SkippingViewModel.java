package com.huawei.healthcloud.plugintrack.ui.viewmodel;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.fragment.SkippingFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.SkippingIntroduceFragment;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.SkipAchieveType;
import com.huawei.hwsportmodel.SportAchieveSubscribe;
import defpackage.cei;
import defpackage.fgj;
import defpackage.gso;
import defpackage.gut;
import defpackage.gwg;
import defpackage.gww;
import defpackage.ixx;
import defpackage.kxd;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class SkippingViewModel extends BaseSportingViewModel implements SportDataNotify, Camera2Helper.OnPreviewListener, Handler.Callback {

    /* renamed from: a, reason: collision with root package name */
    private int f3826a;
    private int g;
    private gww h;
    private boolean i;
    private int j = 1001;
    private int f = 0;
    private boolean c = false;
    private boolean b = false;
    private boolean d = false;
    private int e = 0;

    public static /* synthetic */ void b(int i, Object obj) {
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public boolean isCanScroll() {
        return false;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public boolean isStopSportByTarget() {
        return true;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void init(Bundle bundle) {
        super.init(bundle);
        addSportDataMap("skipTimes");
        addSportDataMap("showSportTime");
        addSportDataMap("encourageType");
        addSportDataMap("skipContinuouslyTimes");
        addSportDataMap("interruptTimes");
        addSportDataMap("footPosition");
        addSportDataMap("bodyDetectionError");
        addSportDataMap("preSportProgress");
        addSportDataMap("boneData");
        addSportDataMap("sportTimeCountDown");
        addSportDataMap(IndoorEquipManagerApi.KEY_HEART_RATE);
        addSportDataMap("calorie");
        this.mSportDataOutputApi.registerDeviceDataCallback(gso.e().f());
    }

    public void e(boolean z) {
        this.b = z;
        if (!z || this.mExtendHandler == null) {
            return;
        }
        this.mExtendHandler.sendEmptyMessage(2);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            int intValue = ((Integer) message.obj).intValue();
            if (this.f >= 100) {
                postValue("bodyDetectionError", Integer.valueOf(intValue));
            } else if (this.j != intValue && this.b && this.mExtendHandler != null) {
                this.j = intValue;
                this.mExtendHandler.sendEmptyMessage(2);
            }
        } else {
            if (i != 2) {
                return false;
            }
            if (this.j == 1 || getDataSource() == 5) {
                if (this.f < 100 && this.mExtendHandler != null) {
                    int i2 = this.f + 5;
                    this.f = i2;
                    postValue("preSportProgress", Integer.valueOf(i2));
                    this.mExtendHandler.sendEmptyMessage(2, 50L);
                }
            } else if (this.f < 100) {
                this.f = 0;
                postValue("preSportProgress", 0);
            }
        }
        return true;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void observeSportingData(String str, LifecycleOwner lifecycleOwner, Observer observer) {
        super.observeSportingData(str, lifecycleOwner, observer);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void requestData() {
        super.requestData();
        EcologyDeviceApi ecologyDeviceApi = (EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class);
        if (ecologyDeviceApi.isSupportPerformanceByProductId(cei.b().getProductId())) {
            ecologyDeviceApi.requestPerformanceRank(new IBaseResponseCallback() { // from class: hol
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SkippingViewModel.b(i, obj);
                }
            });
        }
        this.mSportDataOutputApi.registerAchieveLevel(getTag(), new SportAchieveSubscribe() { // from class: hoo
            @Override // com.huawei.hwsportmodel.SportAchieveSubscribe
            public final void onChange(Object obj) {
                SkippingViewModel.this.a(obj);
            }
        });
    }

    public /* synthetic */ void a(Object obj) {
        if (obj instanceof SkipAchieveType) {
            postValue("encourageType", Integer.valueOf(((SkipAchieveType) obj).getLevel()));
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void requestLocalData() {
        super.requestLocalData();
        postValue("skipTimes", 0);
        if (getSportTarget() == 0) {
            postValue("showSportTime", UnitUtil.a((int) getTargetValue()));
        } else {
            postValue("showSportTime", UnitUtil.a(0));
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SKIP_NUM_DATA");
        arrayList.add("FOOT_POINT_DATA");
        arrayList.add("CONTINUOUS_SKIPPING_JUMP_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("STATUS_CODE_DATA");
        arrayList.add("STUMBLING_ROPE_DATA");
        arrayList.add("BONE_DATA");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("CALORIES_DATA");
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper.OnPreviewListener
    public void onPreviewFrame(Image image, int i, int i2) {
        if (this.mSportDataOutputApi == null) {
            return;
        }
        if (this.g != i || this.f3826a != i2) {
            this.g = i;
            this.f3826a = i2;
            this.mSportDataOutputApi.setParas(SportParamsType.AI_ROPE_PARAS, new fgj(this.g, this.f3826a, this.e));
            if (!this.c) {
                this.c = true;
                onPreSport();
            }
        }
        if (getDataSource() == 7 && this.mSportDataOutputApi.isAlreadyInit()) {
            this.mSportDataOutputApi.setParas(SportParamsType.AI_ROPE_INPUT_IMAGE, image);
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        ArrayList arrayList = new ArrayList(4);
        d(arrayList);
        a((List<BaseSportingViewModel.ReceivedDataHandler>) arrayList);
        c(arrayList);
        b(arrayList);
        e(arrayList);
    }

    private void e(List<BaseSportingViewModel.ReceivedDataHandler> list) {
        list.add(new BaseSportingViewModel.c(this, "STUMBLING_ROPE_DATA", "interruptTimes", Integer.class));
        this.mReceivedDataHandlers.addAll(list);
    }

    void b(List<BaseSportingViewModel.ReceivedDataHandler> list) {
        list.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "STATUS_CODE_DATA", "bodyDetectionError", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map map) {
                LogUtil.h(SkippingViewModel.this.getTag(), this.mManagerDataTag, num);
                if (SkippingViewModel.this.mExtendHandler != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = num;
                    SkippingViewModel.this.mExtendHandler.sendMessage(obtain);
                }
            }
        });
    }

    private void c(List<BaseSportingViewModel.ReceivedDataHandler> list) {
        list.add(new BaseSportingViewModel.ReceivedDataHandler<Long>(this, "TIME_ONE_SECOND_DURATION", "showSportTime", Long.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void handleInner(Long l, Map<String, Object> map) {
                int round = Math.round((l.longValue() * 1.0f) / 1000.0f);
                if (SkippingViewModel.this.getSportTarget() == 0) {
                    int targetValue = (int) (SkippingViewModel.this.getTargetValue() - round);
                    SkippingViewModel.this.postValue(this.mViewModelTag, UnitUtil.a(targetValue));
                    if (targetValue <= 10) {
                        SkippingViewModel.this.postValue("sportTimeCountDown", true);
                    }
                } else {
                    SkippingViewModel.this.postValue(this.mViewModelTag, UnitUtil.a(round));
                }
                SkippingViewModel.this.a(map);
            }
        });
    }

    private void a(List<BaseSportingViewModel.ReceivedDataHandler> list) {
        list.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "CONTINUOUS_SKIPPING_JUMP_DATA", "skipContinuouslyTimes", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                if (num.intValue() > 1) {
                    SkippingViewModel.this.postValue("skipContinuouslyTimes", num);
                }
            }
        });
    }

    private void d(List<BaseSportingViewModel.ReceivedDataHandler> list) {
        list.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "SKIP_NUM_DATA", "skipTimes", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map<String, Object> map) {
                SkippingViewModel.this.c(this.mViewModelTag, num.intValue(), map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<String, Object> map) {
        if (map.containsKey("HEART_RATE_DATA")) {
            postValue(IndoorEquipManagerApi.KEY_HEART_RATE, map.get("HEART_RATE_DATA"));
        }
        if (map.containsKey("CALORIES_DATA")) {
            postValue("calorie", map.get("CALORIES_DATA"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i, Map<String, Object> map) {
        postValue(str, Integer.valueOf(i));
        Object obj = map.get("FOOT_POINT_DATA");
        if (obj instanceof kxd) {
            kxd kxdVar = (kxd) obj;
            postValue("footPosition", new Pair(Float.valueOf(kxdVar.d()), Float.valueOf(kxdVar.e())));
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
        addFragment(new SkippingFragment());
        addFragment(new SkippingIntroduceFragment());
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public int getDefaultItem() {
        if (this.mIsFirstIn) {
            return Math.max(0, getCount() - 1);
        }
        return 0;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        g();
    }

    public void i() {
        SportMusicController.a().d(this.h.d(getSportType()) == null ? 1 : 2, getSportType(), true);
    }

    public void c() {
        this.h = new gww(BaseApplication.e(), new StorageParams(1), Integer.toString(20002));
    }

    public boolean e() {
        return (this.i && CommonUtil.bd()) ? false : true;
    }

    public boolean a() {
        return this.mIsFirstIn;
    }

    public void b() {
        if (e()) {
            LogUtil.a("Track_SkippingViewModel", "not supportMusic");
            return;
        }
        if (f()) {
            SportMusicController.a().a(1, (String) null);
        }
        gut.aUo_(BaseApplication.e(), new Intent("action_stop_play_sport_music"));
    }

    private boolean f() {
        if (this.h == null) {
            c();
        }
        return this.h.f(getSportType()) == 1;
    }

    public gww d() {
        return this.h;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        if (getDataSource() == 5) {
            cei.b().requestDeviceInfoAndState("SportService_EcologyManager");
        }
        boolean a2 = gwg.a(BaseApplication.e());
        this.i = a2;
        ReleaseLogUtil.e("Track_SkippingViewModel", "onPreSport() isSupportMusic ", Boolean.valueOf(a2));
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.d = true;
        if (this.mSportLifeCircleApi != null) {
            ReleaseLogUtil.e("Track_SkippingViewModel", "onStopSport()");
            this.mSportLifeCircleApi.m134x32b3e3a1();
        }
    }

    public void g() {
        LogUtil.a("Track_SkippingViewModel", "mHasStopSkipSport is ", Boolean.valueOf(this.d), " getDataSource() is ", Integer.valueOf(getDataSource()));
        if (this.d || getDataSource() != 5) {
            return;
        }
        ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).ropeSetFitnessMachineControl(3, 6, null);
    }

    public void c(boolean z) {
        if (this.g == 0 || this.f3826a == 0) {
            LogUtil.h(getTag(), "mWidth or mHeight is zero, return");
            return;
        }
        if (getDataSource() == 7) {
            this.e = z ? 1 : 0;
            this.mSportDataOutputApi.setParas(SportParamsType.AI_ROPE_PARAS, new fgj(this.g, this.f3826a, z ? 1 : 0));
        }
        b(z);
    }

    public void b(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", z ? "1" : "2");
        hashMap.put(Wpt.MODE, getDataSource() == 7 ? "1" : "2");
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_SKIP_CAMERA_2040229.value(), hashMap, 0);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return "Track_SkippingViewModel";
    }
}
