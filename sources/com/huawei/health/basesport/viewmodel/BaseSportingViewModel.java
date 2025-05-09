package com.huawei.health.basesport.viewmodel;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.IHeartRateControlCallback;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportObserverType;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.cav;
import defpackage.ffd;
import defpackage.fgm;
import defpackage.ixx;
import defpackage.koq;
import health.compact.a.CommonUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public abstract class BaseSportingViewModel extends ViewModel implements SportLifecycle, ISportPagerConfig, SportDataNotify, ITargetUpdateListener, IHeartRateControlCallback, Handler.Callback {
    private static final int STOP_SPORT_DELAY = 100;
    private static final String TAG = "Track_BaseSportingViewModel";
    protected SportDataOutputApi mSportDataOutputApi;
    private SportLaunchParams mSportLaunchParams;
    protected SportLifecycle mSportLifeCircleApi;
    private MutableLiveData<Bundle> mSportingData;
    protected boolean mIsFirstIn = false;
    protected List<ReceivedDataHandler> mReceivedDataHandlers = new ArrayList();
    public ExtendHandler mExtendHandler = null;
    private Map<String, MutableLiveData> mDataMap = new ConcurrentHashMap();
    private List<Fragment> mFragments = new ArrayList();
    private a mSportLifecycleObserves = new a();
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public int getDefaultItem() {
        return 0;
    }

    protected abstract List<String> getSubscribeTagList();

    protected abstract void initFragment();

    protected abstract void initReceivedDataHandlers();

    public void init(Bundle bundle) {
        initSportParams(bundle);
        this.mSportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        this.mSportLifeCircleApi = (SportLifecycle) Services.a("SportService", SportLifecycle.class);
        if (!this.mSportDataOutputApi.isAlreadyInit()) {
            this.mSportDataOutputApi.initModel(getSportManagerBundle());
        } else if (this.mSportLaunchParams != null) {
            this.mSportDataOutputApi.setParas(SportParamsType.SPORT_LAUNCH_PARAS, this.mSportLaunchParams);
        }
        this.mSportDataOutputApi.registerSportStatus(getTag(), this.mSportLifecycleObserves);
        this.mSportingData = new MutableLiveData<>();
        addSportDataMap("isFirst");
        addSportDataMap("sportTarget");
        addSportDataMap("targetProgress");
        this.mExtendHandler = HandlerCenter.yt_(this, getTag());
        initFragment();
    }

    protected void addFragment(Fragment fragment) {
        if (fragment != null) {
            this.mFragments.add(fragment);
        }
    }

    public SportLaunchParams getSportLaunchParams() {
        return this.mSportLaunchParams;
    }

    public void addSportDataMap(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(getTag(), "addSportDataMap failed with empty key");
        } else if (this.mDataMap.containsKey(str)) {
            LogUtil.h(getTag(), "addSportDataMap failed with key data exist.");
        } else {
            LogUtil.a(getTag(), "addSportDataMap key:", str);
            this.mDataMap.put(str, new MutableLiveData());
        }
    }

    public MutableLiveData<Bundle> getSportingData() {
        return this.mSportingData;
    }

    protected void initSportParams(Bundle bundle) {
        if (bundle != null) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) bundle.getParcelable("bundle_key_sport_launch_paras");
            this.mSportLaunchParams = sportLaunchParams;
            if (sportLaunchParams == null) {
                SportLaunchParams sportLaunchParams2 = new SportLaunchParams();
                this.mSportLaunchParams = sportLaunchParams2;
                sportLaunchParams2.setSportType(bundle.getInt("map_tracking_sport_type_sportting", 0)).setSportTarget(bundle.getInt("sport_target_type_sportting", -1)).setTargetValue(bundle.getFloat("sport_target_value_sportting", -1.0f)).setDataSource(bundle.getInt("sport_data_source_sportting", -1)).addExtra("origintarget", Integer.valueOf(bundle.getInt("origintarget", -1))).addExtra("isHrControlCourse", Boolean.valueOf(bundle.getBoolean("isHrControlCourse", false))).setNeedRestart(true);
            }
        }
    }

    protected Bundle getSportManagerBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(BleConstants.SPORT_TYPE, getSportType());
        bundle.putInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, getSportTarget());
        bundle.putFloat(WorkoutRecord.Extend.COURSE_TARGET_VALUE, getTargetValue());
        bundle.putInt("sport_data_source_sportting", getDataSource());
        bundle.putParcelable("bundle_key_sport_launch_paras", this.mSportLaunchParams);
        return bundle;
    }

    public void postValue(String str, Object obj) {
        LogUtil.h(getTag(), "post data tag: ", str, " value:", obj);
        MutableLiveData liveData = getLiveData(str);
        if (liveData == null) {
            LogUtil.h(getTag(), "post data tag: ", str, " value:", obj);
        } else {
            liveData.postValue(obj);
        }
    }

    public MutableLiveData getLiveData(String str) {
        return this.mDataMap.get(str);
    }

    public void requestData() {
        requestLocalData();
        requestDataFromManager();
    }

    protected void requestDataFromManager() {
        List<String> subscribeTagList = getSubscribeTagList();
        initReceivedDataHandlers();
        LogUtil.a(getTag(), "subscribe sport data:", subscribeTagList);
        this.mSportCallbackOption.a(subscribeTagList);
        this.mSportCallbackOption.c(getTag());
        this.mSportDataOutputApi.subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: cay
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                BaseSportingViewModel.this.onChange(list, map);
            }
        });
        this.mSportDataOutputApi.subscribeTargetStatus(TAG, this);
        this.mSportDataOutputApi.addObserver(SportObserverType.HEART_RATE_OBSERVE, TAG, this);
    }

    protected boolean isStopSportByTarget() {
        return getSportType() == 283 && getDataSource() == 5;
    }

    public void requestLocalData() {
        if (CommonUtils.g(SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(20002), "sport_first_start_time_" + getSportType())) > 0) {
            this.mIsFirstIn = false;
            postValue("isFirst", false);
        } else {
            this.mIsFirstIn = true;
            postValue("isFirst", true);
        }
    }

    @Override // com.huawei.health.sportservice.SportDataNotify
    public void onChange(List<String> list, Map<String, Object> map) {
        if (koq.b(list) || map == null) {
            LogUtil.h(getTag(), "receive change error with wrong paras.");
            return;
        }
        Iterator<ReceivedDataHandler> it = this.mReceivedDataHandlers.iterator();
        while (it.hasNext()) {
            it.next().handle(list, map);
        }
    }

    public void observeSportLifeCycle(String str, SportLifecycle sportLifecycle) {
        this.mSportLifecycleObserves.a(str, sportLifecycle);
    }

    public void unregisterAll() {
        SportDataOutputApi sportDataOutputApi = this.mSportDataOutputApi;
        if (sportDataOutputApi != null) {
            sportDataOutputApi.unRegisterSportStatus(getTag());
            this.mSportDataOutputApi.unRegisterSportBleStatus(getTag());
            this.mSportDataOutputApi.cancelSubscribeNotify(this.mSportCallbackOption);
        }
    }

    public void removeSportLifeCycleObserve(String str) {
        this.mSportLifecycleObserves.c(str);
    }

    public void observeSportingData(String str, LifecycleOwner lifecycleOwner, Observer observer) {
        MutableLiveData liveData = getLiveData(str);
        if (liveData == null) {
            LogUtil.h(getTag(), "observeSportingData failed. keyStr:", str);
        } else {
            liveData.observe(lifecycleOwner, observer);
        }
    }

    public void observeForeverSportingData(String str, Observer observer) {
        MutableLiveData liveData = getLiveData(str);
        if (liveData == null) {
            LogUtil.h(getTag(), "observeForeverSportingData failed. keyStr:", str);
        } else {
            liveData.observeForever(observer);
        }
    }

    public void removeSportDataObservers(String str, LifecycleOwner lifecycleOwner) {
        MutableLiveData liveData = getLiveData(str);
        if (liveData == null) {
            LogUtil.h(getTag(), "removeSportDataObserver failed. keyStr:", str);
        } else {
            liveData.removeObservers(lifecycleOwner);
        }
    }

    public MotionPath getMotionPath() {
        SportDataOutputApi sportDataOutputApi = this.mSportDataOutputApi;
        if (sportDataOutputApi == null) {
            return null;
        }
        Object data = sportDataOutputApi.getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            return (MotionPath) data;
        }
        return null;
    }

    public MotionPathSimplify getMotionPathSimplify() {
        SportDataOutputApi sportDataOutputApi = this.mSportDataOutputApi;
        if (sportDataOutputApi == null) {
            return null;
        }
        Object data = sportDataOutputApi.getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            return (MotionPathSimplify) data;
        }
        return null;
    }

    public int getSportType() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams != null) {
            return sportLaunchParams.getSportType();
        }
        return 0;
    }

    public int getSportTarget() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams != null) {
            return sportLaunchParams.getSportTarget();
        }
        return -1;
    }

    public float getTargetValue() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams != null) {
            return sportLaunchParams.getTargetValue();
        }
        return -1.0f;
    }

    public int getDataSource() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams != null) {
            return sportLaunchParams.getDataSource();
        }
        return -1;
    }

    public <T> T getExtraValue(String str, Class<T> cls) {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams != null) {
            return (T) sportLaunchParams.getExtra(str, cls);
        }
        return null;
    }

    public boolean isToSave() {
        SportDataOutputApi sportDataOutputApi = this.mSportDataOutputApi;
        if (sportDataOutputApi != null) {
            return sportDataOutputApi.isToSave();
        }
        return false;
    }

    public int getSportStatus() {
        SportDataOutputApi sportDataOutputApi = this.mSportDataOutputApi;
        if (sportDataOutputApi != null) {
            return sportDataOutputApi.getStatus();
        }
        return 0;
    }

    public int getTargetSportStatus() {
        SportDataOutputApi sportDataOutputApi = this.mSportDataOutputApi;
        if (sportDataOutputApi != null) {
            return sportDataOutputApi.getTargetSportStatus();
        }
        return 0;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        ReleaseLogUtil.e(TAG, "onCleared()");
        this.mDataMap.clear();
        this.mSportLifecycleObserves.e();
        this.mFragments.clear();
        if (this.mSportDataOutputApi != null) {
            unregisterAll();
            this.mSportDataOutputApi.onDestroy();
            this.mSportDataOutputApi.destroyModel();
        }
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.quit(true);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        SportDataOutputApi sportDataOutputApi;
        if (this.mSportLifeCircleApi == null || (sportDataOutputApi = this.mSportDataOutputApi) == null || sportDataOutputApi.getStatus() == 7) {
            return;
        }
        this.mSportLifeCircleApi.onPreSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
        SportDataOutputApi sportDataOutputApi;
        if (this.mSportLifeCircleApi == null || (sportDataOutputApi = this.mSportDataOutputApi) == null || sportDataOutputApi.getStatus() == 6) {
            return;
        }
        this.mSportLifeCircleApi.onCountDown();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        SportDataOutputApi sportDataOutputApi;
        if (this.mSportLifeCircleApi != null && (sportDataOutputApi = this.mSportDataOutputApi) != null && sportDataOutputApi.getStatus() != 1) {
            this.mSportLifeCircleApi.onStartSport();
        }
        if (this.mIsFirstIn) {
            SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(20002), "sport_first_start_time_" + getSportType(), String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        }
        doStartSportBi();
    }

    protected void doStartSportBi() {
        HashMap hashMap = new HashMap(7);
        hashMap.put("sportId", cav.e());
        if (!Utils.o()) {
            hashMap.put("startTime", Long.valueOf(System.currentTimeMillis()));
            hashMap.put("goalValue", Integer.valueOf((int) getTargetValue()));
        }
        hashMap.put("goalType", Integer.valueOf(getSportTarget()));
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(getSportType()));
        hashMap.put("trackType", 0);
        if (getDataSource() == 100) {
            hashMap.put("from", "e4");
        }
        ixx.d().d(BaseApplication.e(), AnalyticsValue.BI_TRACK_SPORT_START_SPORT_KEY.value(), hashMap, 0);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        SportDataOutputApi sportDataOutputApi;
        if (this.mSportLifeCircleApi == null || (sportDataOutputApi = this.mSportDataOutputApi) == null || sportDataOutputApi.getStatus() != 2) {
            return;
        }
        ReleaseLogUtil.e(TAG, "onResumeSport()");
        this.mSportLifeCircleApi.onResumeSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        SportDataOutputApi sportDataOutputApi;
        if (this.mSportLifeCircleApi == null || (sportDataOutputApi = this.mSportDataOutputApi) == null || sportDataOutputApi.getStatus() != 1) {
            return;
        }
        ReleaseLogUtil.e(TAG, "onPauseSport()");
        this.mSportLifeCircleApi.onPauseSport();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport, reason: merged with bridge method [inline-methods] */
    public void m134x32b3e3a1() {
        SportDataOutputApi sportDataOutputApi;
        if (this.mSportLifeCircleApi == null || (sportDataOutputApi = this.mSportDataOutputApi) == null || sportDataOutputApi.getStatus() == 3) {
            return;
        }
        ReleaseLogUtil.e(TAG, "onStopSport()");
        this.mSportLifeCircleApi.m134x32b3e3a1();
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        if (ffdVar != null) {
            postValue("targetProgress", Integer.valueOf((int) (ffdVar.a() * 100.0f)));
        }
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
        if (i == 200 && isStopSportByTarget()) {
            this.mExtendHandler.postTask(new Runnable() { // from class: cax
                @Override // java.lang.Runnable
                public final void run() {
                    BaseSportingViewModel.this.m134x32b3e3a1();
                }
            }, 100L);
        }
    }

    @Override // com.huawei.health.sportservice.IHeartRateControlCallback
    public void onDataUpdate(Object obj) {
        LogUtil.a(TAG, "onDataUpdate, data = ", obj);
    }

    @Override // com.huawei.health.sportservice.IHeartRateControlCallback
    public void onStatusUpdate(int i) {
        LogUtil.a(TAG, "onStatusUpdate, status = ", Integer.valueOf(i));
    }

    @Override // com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public int getCount() {
        return this.mFragments.size();
    }

    @Override // com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public boolean isCanScroll() {
        return getCount() > 1;
    }

    @Override // com.huawei.health.basesport.viewmodel.ISportPagerConfig
    public List<Fragment> getFragments() {
        return new ArrayList(this.mFragments);
    }

    public static class a implements SportLifecycle {
        private Map<String, SportLifecycle> e = new ConcurrentHashMap();

        public void a(String str, SportLifecycle sportLifecycle) {
            if (TextUtils.isEmpty(str) || sportLifecycle == null) {
                LogUtil.h(BaseSportingViewModel.TAG, "observeSportLifeCycle tag:", str, " SportLifecycle is ", sportLifecycle);
            } else {
                this.e.put(str, sportLifecycle);
            }
        }

        public void c(String str) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h(BaseSportingViewModel.TAG, "observeSportLifeCycle tag:", str);
            } else {
                this.e.remove(str);
            }
        }

        @Override // com.huawei.health.sportservice.SportLifecycle
        public void onPreSport() {
            Iterator<SportLifecycle> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().onPreSport();
            }
        }

        @Override // com.huawei.health.sportservice.SportLifecycle
        public void onCountDown() {
            Iterator<SportLifecycle> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().onCountDown();
            }
        }

        @Override // com.huawei.health.sportservice.SportLifecycle
        public void onStartSport() {
            Iterator<SportLifecycle> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().onStartSport();
            }
        }

        @Override // com.huawei.health.sportservice.SportLifecycle
        public void onResumeSport() {
            Iterator<SportLifecycle> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().onResumeSport();
            }
        }

        @Override // com.huawei.health.sportservice.SportLifecycle
        public void onPauseSport() {
            Iterator<SportLifecycle> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().onPauseSport();
            }
        }

        @Override // com.huawei.health.sportservice.SportLifecycle
        /* renamed from: onStopSport */
        public void m134x32b3e3a1() {
            LogUtil.a(BaseSportingViewModel.TAG, "SportLifecycleObserves onStopSport()");
            Iterator<SportLifecycle> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().m134x32b3e3a1();
            }
        }

        public void e() {
            this.e.clear();
        }
    }

    public static abstract class ReceivedDataHandler<T> {
        protected String mManagerDataTag;
        private Class<T> mType;
        protected BaseSportingViewModel mViewModel;
        protected String mViewModelTag;

        protected abstract void handleInner(T t, Map<String, Object> map);

        public ReceivedDataHandler(BaseSportingViewModel baseSportingViewModel, String str, String str2, Class<T> cls) {
            this.mViewModel = baseSportingViewModel;
            this.mManagerDataTag = str;
            this.mViewModelTag = str2;
            this.mType = cls;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void handle(List<String> list, Map<String, Object> map) {
            if (list.contains(this.mManagerDataTag)) {
                Object obj = map.get(this.mManagerDataTag);
                if (obj == null || !this.mType.isInstance(obj)) {
                    LogUtil.h(BaseSportingViewModel.TAG, "data is null or type error:", this.mType, " data:", obj);
                } else {
                    handleInner(obj, map);
                }
            }
        }
    }

    public static class c<T> extends ReceivedDataHandler<T> {
        public c(BaseSportingViewModel baseSportingViewModel, String str, String str2, Class cls) {
            super(baseSportingViewModel, str, str2, cls);
        }

        @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
        protected void handleInner(Object obj, Map map) {
            LogUtil.a(this.mViewModel.getTag(), "mManagerDataTag:", this.mManagerDataTag, " mViewModelTag:", this.mViewModelTag, " value", obj);
            this.mViewModel.postValue(this.mViewModelTag, obj);
        }
    }

    protected String getTag() {
        return TAG;
    }
}
