package com.huawei.ui.homehealth.runcard.trackfragments;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.model.OperatorStatus;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.utils.RunPopularRoutesUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog;
import com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider;
import com.huawei.ui.homehealth.runcard.utils.SportMusicUtils;
import com.huawei.ui.homehealth.runcard.utils.TargetChoicePickerUtils;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.zhangyue.iReader.sdk.scheme.ISchemeListener;
import defpackage.cau;
import defpackage.ggs;
import defpackage.ggx;
import defpackage.gie;
import defpackage.gnm;
import defpackage.gso;
import defpackage.gsy;
import defpackage.gtb;
import defpackage.gts;
import defpackage.gtv;
import defpackage.gtx;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.gww;
import defpackage.gxu;
import defpackage.gzi;
import defpackage.hab;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kor;
import defpackage.kxz;
import defpackage.mwu;
import defpackage.mxb;
import defpackage.mxc;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.owp;
import defpackage.tye;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public abstract class SportStatisticProvider extends BaseKnitDataProvider<HiHealthData> {
    private static final int COURSE_CATEGORY = 12731;
    private static final boolean IS_AI_RUN_PLAN_OPEN = true;
    private static final int IS_NOT_CUSTOM_DIALOG_VALUE = 2;
    private static final int KILOCALORIE_TO_CALORIE = 1000;
    private static final int MINUTE_TO_SECOND = 60;
    private static final int MSG_REFRESH_UI = 3;
    private static final int MSG_SHOW_DIALOG = 2;
    private static final int MSG_START_SPORT = 1;
    private static final int MUSIC_NOT_SUPPORT_BI = 0;
    private static final int MUSIC_OPERATOR_SELECTED = 1;
    private static final int MUSIC_SUPPORT_BI = 4;
    private static final String MUSIC_TAG = "isSupportSportMusic";
    private static final double THRESHOLD = 1.0E-5d;
    private String mAdvertisementTextTitle;
    public Context mContext;
    private Plan mCurrentPlan;
    private HotPathCityInfo mHotPathCityInfo;
    private ResourceBriefInfo mIndoorResourceBriefInfo;
    private boolean mIsBlueToothEnable;
    private boolean mIsShowImperial;
    private volatile boolean mIsSportSupportMusic;
    private volatile boolean mIsSystemSupportMusic;
    private Bitmap mMusicBitmap;
    private volatile boolean mNeedRefresh;
    private volatile boolean mNotHasMusicContentElement;
    private CustomViewDialog mPlanDialog;
    private SectionBean mSectionBean;
    private SportTargetDialog mSportTargetDialog;
    private float mTargetValue;
    private int mTargetType = -2;
    private a mStatDataCallback = new a(this);
    private c mLoadMusicElementCallback = new c(this);
    private HiHealthData mHealthData = new HiHealthData();
    private final String TAG = "Track_Provider_" + getTag();
    private boolean mIsSchemeManagerInit = false;
    private final Handler mRefreshTargetHandler = new Handler() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h(SportStatisticProvider.this.TAG, "mRefreshTargetHandler msg is null");
                return;
            }
            super.handleMessage(message);
            SportStatisticProvider.this.mTargetType = message.arg1;
            if (message.obj instanceof Float) {
                SportStatisticProvider.this.mTargetValue = ((Float) message.obj).floatValue();
            }
            boolean z = message.arg2 != 2;
            SportStatisticProvider sportStatisticProvider = SportStatisticProvider.this;
            sportStatisticProvider.refreshTargetTypeAndValue(sportStatisticProvider.mTargetType, SportStatisticProvider.this.mTargetValue, true, z);
            LogUtil.a(SportStatisticProvider.this.TAG, "handleMessage targetType = ", Integer.valueOf(SportStatisticProvider.this.mTargetType), " targetValue = ", Float.valueOf(SportStatisticProvider.this.mTargetValue));
            if (SportStatisticProvider.this.mSectionBean != null) {
                SportStatisticProvider.this.mSectionBean.e(SportStatisticProvider.this.mHealthData);
            }
        }
    };
    private final Handler mStartSportHandler = new Handler() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.a(SportStatisticProvider.this.TAG, "mStartSportHandler msg is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                if (!gtx.c(SportStatisticProvider.this.mContext).bc() || gtx.c(SportStatisticProvider.this.mContext).r() == 1) {
                    ReleaseLogUtil.e(SportStatisticProvider.this.TAG, "goto StartSport");
                    SportStatisticProvider.this.gotoStartSport();
                    return;
                }
                return;
            }
            if (i == 2) {
                SportStatisticProvider.this.showStartSportIgnoreLinkDialog();
                return;
            }
            if (i != 3) {
                return;
            }
            LogUtil.a(SportStatisticProvider.this.TAG, "MSG_REFRESH_UI");
            if (SportStatisticProvider.this.mSectionBean == null || SportStatisticProvider.this.mHealthData == null) {
                return;
            }
            SportStatisticProvider.this.mSectionBean.e(SportStatisticProvider.this.mHealthData);
        }
    };
    private final BroadcastReceiver mHiBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getIntExtra("refresh_type", -1) == 0) {
                SportStatisticProvider.this.requestSportStatData();
            }
        }
    };
    private StorageParams mStorageParams = new StorageParams();
    private String mModuleId = Integer.toString(20002);
    private gww mTrackSharedPreferenceUtil = new gww(BaseApplication.getContext(), this.mStorageParams, this.mModuleId);
    private SportMusicUtils mSportMusicUtils = new SportMusicUtils(BaseApplication.getContext());

    protected abstract double getHiHealthDataDistance(HiHealthData hiHealthData);

    protected abstract String getHiHealthDataDistanceKey();

    protected abstract int getSportType();

    protected abstract String getTag();

    protected abstract void setData(Context context, HashMap<String, Object> hashMap, Object obj);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (HiHealthData) obj);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        if (this.mHealthData != null) {
            String a2 = cau.a(String.valueOf(getSportType()));
            String a3 = cau.a(MUSIC_TAG);
            double a4 = !TextUtils.isEmpty(a2) ? CommonUtils.a(a2) : 0.0d;
            if (!TextUtils.isEmpty(a3)) {
                requestImageMusicAsync(BaseApplication.getContext(), Boolean.parseBoolean(a3));
            }
            String hiHealthDataDistanceKey = getHiHealthDataDistanceKey();
            LogUtil.a(this.TAG, "loadDefaultData() cachedDistanceStr: ", a2, ", cachedSumDistance: ", Double.valueOf(a4), ", distanceKey: ", hiHealthDataDistanceKey);
            if (!TextUtils.isEmpty(hiHealthDataDistanceKey)) {
                this.mHealthData.putDouble(hiHealthDataDistanceKey, a4);
            }
        }
        this.mIsShowImperial = UnitUtil.h();
        sectionBean.e(this.mHealthData);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, SectionBean sectionBean) {
        LogUtil.a(this.TAG, "loadData");
        this.mSectionBean = sectionBean;
        ThreadPoolManager.d().execute(new Runnable() { // from class: orh
            @Override // java.lang.Runnable
            public final void run() {
                SportStatisticProvider.this.m789xa45a37e8(context);
            }
        });
        requestSportStatData();
        requestMusicData();
        registerHiHealthDataBroadcast(context);
        refreshTarget();
        refreshUnit();
        gsy.b().c(this.TAG, new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a(SportStatisticProvider.this.TAG, "bolt errCode is ", Integer.valueOf(i), " objData ", obj);
                if (SportStatisticProvider.this.mSectionBean == null || SportStatisticProvider.this.mHealthData == null) {
                    return;
                }
                SportStatisticProvider.this.mSectionBean.e(SportStatisticProvider.this.mHealthData);
            }
        });
        if (this.mNeedRefresh) {
            this.mSectionBean.e(this.mHealthData);
        }
    }

    /* renamed from: lambda$loadData$0$com-huawei-ui-homehealth-runcard-trackfragments-SportStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m789xa45a37e8(Context context) {
        if (CommonUtil.bd() && gwg.i(context) && owp.e(this.mContext) != 0) {
            checkSupportListenBook();
        }
        List<MessageObject> messages = ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).getMessages(42);
        if (messages == null || messages.size() != 1) {
            return;
        }
        String msgTitle = messages.get(0).getMsgTitle();
        this.mAdvertisementTextTitle = msgTitle;
        ReleaseLogUtil.e(this.TAG, "loadData mAdvertisementTextTitle  ", msgTitle);
    }

    private void refreshUnit() {
        boolean h = UnitUtil.h();
        if (this.mIsShowImperial != h) {
            ReleaseLogUtil.e(getTag(), "refresh unit");
            this.mIsShowImperial = h;
            this.mNeedRefresh = true;
        }
    }

    private void refreshTarget() {
        int e2 = owp.e(this.mContext, getSportType());
        float a2 = owp.a(this.mContext, getSportType());
        int i = this.mTargetType;
        if (i != -2) {
            if (i == e2 && this.mTargetValue == a2) {
                return;
            }
            ReleaseLogUtil.e(this.TAG, "refresh target of ", Integer.valueOf(e2));
            this.mNeedRefresh = true;
        }
    }

    private void registerHiHealthDataBroadcast(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(this.mHiBroadcastReceiver, intentFilter);
        LogUtil.a(this.TAG, "mHiBroadcastReceiver register success", this);
    }

    private void unregisterHiHealthDataBroadcast() {
        if (this.mHiBroadcastReceiver != null) {
            try {
                LocalBroadcastManager.getInstance(com.huawei.haf.application.BaseApplication.e()).unregisterReceiver(this.mHiBroadcastReceiver);
                LogUtil.a(this.TAG, "mHiBroadcastReceiver unregister", this);
            } catch (IllegalArgumentException unused) {
                LogUtil.b(this.TAG, "IllegalArgumentException mHiBroadcastReceiver unregister");
            }
        }
    }

    protected void requestSportStatData() {
        LogUtil.a(this.TAG, "requestSportStatData");
        int sportType = getSportType();
        if (sportType == 264) {
            kor.a().d(0L, System.currentTimeMillis(), 7, 258, this.mStatDataCallback);
            return;
        }
        if (sportType == 258 || sportType == 257 || sportType == 259 || sportType == 282) {
            kor.a().d(0L, System.currentTimeMillis(), 7, sportType == 282 ? 257 : sportType, this.mStatDataCallback);
        } else {
            gts.b(this.mContext).e(0L, System.currentTimeMillis(), 7, sportType, this.mStatDataCallback);
        }
    }

    protected void requestMusicData() {
        LogUtil.a(this.TAG, "requestMusicData");
        if (this.mTrackSharedPreferenceUtil.f(getSportType()) != 1 || this.mTrackSharedPreferenceUtil.d(getSportType()) == null) {
            LogUtil.a(this.TAG, "requestMusicData set data");
            if (this.mMusicBitmap != null) {
                LogUtil.a(this.TAG, "has bitmap before");
                requestImageMusicAsync(BaseApplication.getContext(), Boolean.parseBoolean(cau.a(MUSIC_TAG)));
                this.mNeedRefresh = true;
                return;
            }
            return;
        }
        this.mSportMusicUtils.b(this.mTrackSharedPreferenceUtil, getSportType(), this.mLoadMusicElementCallback);
    }

    class c implements IBaseResponseCallback {
        WeakReference<SportStatisticProvider> e;

        c(SportStatisticProvider sportStatisticProvider) {
            this.e = new WeakReference<>(sportStatisticProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a(SportStatisticProvider.this.TAG, "loadMusicCallback errorCode is ", Integer.valueOf(i));
            SportStatisticProvider sportStatisticProvider = this.e.get();
            if (sportStatisticProvider == null) {
                LogUtil.b(SportStatisticProvider.this.TAG, "loadMusicCallback error, fragment is null.");
                return;
            }
            if (!LoginInit.getInstance(null).getIsLogined()) {
                sportStatisticProvider.updateHealthData(new HiHealthData());
                return;
            }
            if (i != 100000 || obj == null) {
                SportStatisticProvider.this.mMusicBitmap = null;
                return;
            }
            if (obj instanceof Bitmap) {
                SportStatisticProvider.this.mMusicBitmap = (Bitmap) obj;
                sportStatisticProvider.requestImageMusicAsync(BaseApplication.getContext(), gwg.i(BaseApplication.getContext()));
                sportStatisticProvider.mSectionBean.e(SportStatisticProvider.this.mHealthData);
                return;
            }
            LogUtil.b(SportStatisticProvider.this.TAG, "loadMusicCallback objData is error!");
            SportStatisticProvider.this.mMusicBitmap = null;
        }
    }

    private List<Integer> getMusicBgList() {
        ArrayList arrayList = new ArrayList(4);
        Integer valueOf = Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad);
        arrayList.add(valueOf);
        arrayList.add(valueOf);
        arrayList.add(Integer.valueOf(R.drawable._2131430458_res_0x7f0b0c3a));
        arrayList.add(Integer.valueOf(R.string._2130842049_res_0x7f0211c1));
        return arrayList;
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        LogUtil.a(this.TAG, "parseParams");
        this.mContext = context;
        if (this.mSportTargetDialog == null) {
            this.mSportTargetDialog = new SportTargetDialog(this.mContext);
        }
        setData(context, hashMap, hiHealthData);
        updateImageMusic(hashMap);
        updatePopularRoutesImage(hashMap);
        setClickListener(hashMap);
        setTargetTypeAndValue(hashMap);
        updateDistance(hashMap, hiHealthData);
        gtb.c().d(hashMap, getSportType());
        this.mNeedRefresh = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestImageMusicAsync(Context context, boolean z) {
        LogUtil.a(this.TAG, "requestImageMusicAsync");
        this.mIsSystemSupportMusic = CommonUtil.bd() && z;
        this.mIsSportSupportMusic = owp.e(context) == 0 && z;
        this.mNotHasMusicContentElement = this.mTrackSharedPreferenceUtil.f(getSportType()) != 1 || this.mTrackSharedPreferenceUtil.d(getSportType()) == null || this.mMusicBitmap == null;
    }

    private void updateImageMusic(HashMap<String, Object> hashMap) {
        if (owp.e(this.mContext) == 1) {
            addBookImage(hashMap);
        } else if (this.mIsSystemSupportMusic && this.mIsSportSupportMusic) {
            LogUtil.a(this.TAG, "updateImageMusic");
            if (this.mNotHasMusicContentElement) {
                hashMap.put("RIGHT_IMAGEVIEW", getMusicBgList());
                return;
            } else {
                hashMap.put("RIGHT_IMAGEVIEW", Collections.singletonList(this.mMusicBitmap));
                owp.d(this.mContext, 0);
            }
        } else {
            hashMap.remove("RIGHT_IMAGEVIEW");
        }
        if (isShowMusicRun()) {
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad));
            arrayList.add(Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad));
            arrayList.add(Integer.valueOf(R.drawable._2131430458_res_0x7f0b0c3a));
            arrayList.add(Integer.valueOf(R.string._2130850491_res_0x7f0232bb));
            hashMap.put("RIGHT_IMAGEVIEW", arrayList);
        }
    }

    private void addBookImage(HashMap<String, Object> hashMap) {
        ArrayList arrayList = new ArrayList(4);
        Integer valueOf = Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad);
        arrayList.add(valueOf);
        arrayList.add(valueOf);
        arrayList.add(Integer.valueOf(R.drawable._2131430457_res_0x7f0b0c39));
        arrayList.add(Integer.valueOf(R.string._2130842532_res_0x7f0213a4));
        hashMap.put("RIGHT_IMAGEVIEW", arrayList);
        owp.d(this.mContext, 1);
    }

    private boolean isShowMusicRun() {
        return Utils.j() && !gvv.a() && (getSportType() == 258 || getSportType() == 264);
    }

    private void jumpToMusicCourse() {
        int i;
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (getSportType() == 258) {
            i = 4;
        } else if (getSportType() == 264) {
            i = 5;
        } else {
            LogUtil.a(this.TAG, "jumpToMusicCourse getSportType()", Integer.valueOf(getSportType()));
            i = 0;
        }
        if (fitnessAdviceApi != null) {
            fitnessAdviceApi.jumpToAllCourse(this.mContext, COURSE_CATEGORY, i, "RUNNING_COURSE");
        }
    }

    private void updatePopularRoutesImage(HashMap<String, Object> hashMap) {
        if (owp.c(getSportType())) {
            hashMap.put("RIGHT_TOP_IMAGE", Integer.valueOf(R.drawable._2131430369_res_0x7f0b0be1));
            hashMap.put("RIGHT_TOP_TITLE", this.mContext.getResources().getString(R.string._2130846048_res_0x7f022160));
            hashMap.put("RIGHT_TOP_TITLE_SECOND", this.mContext.getResources().getString(R.string.IDS_device_title_use));
            hashMap.put("RIGHT_TOP_ICON_SHOW", true);
            return;
        }
        hashMap.put("RIGHT_TOP_ICON_SHOW", false);
    }

    private void goToRoutes() {
        RunPopularRoutesUtil.e(this.mContext, 1, new RunPopularRoutesUtil.DialogCallBack() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.14
            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void goNext() {
                LogUtil.a(SportStatisticProvider.this.TAG, "GoToRoutes SUCCESS And Jump");
                Intent intent = new Intent(SportStatisticProvider.this.getContext(), (Class<?>) RunningRouteListActivity.class);
                intent.putExtra("RUNNING_PATH_CITY_INFO", SportStatisticProvider.this.mHotPathCityInfo);
                gzi.a(1);
                gnm.aPB_(SportStatisticProvider.this.getContext(), intent);
            }

            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void notGoNext() {
                LogUtil.a(SportStatisticProvider.this.TAG, "Not GoToRoutes");
                nsn.o();
            }
        });
    }

    private void updateDistance(HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        String a2;
        double hiHealthDataDistance = hiHealthData != null ? getHiHealthDataDistance(hiHealthData) : 0.0d;
        LogUtil.a(this.TAG, "distance ", LogAnonymous.b((int) hiHealthDataDistance));
        if (UnitUtil.h()) {
            a2 = TargetChoicePickerUtils.a(UnitUtil.e(hiHealthDataDistance / 1000.0d, 3), this.mContext);
        } else {
            a2 = TargetChoicePickerUtils.a(hiHealthDataDistance / 1000.0d, this.mContext);
        }
        hashMap.put("ACCUMULATED_DURATION", a2);
    }

    private void setTargetTypeAndValue(Map<String, Object> map) {
        String string;
        String str;
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            this.mTargetType = owp.e(this.mContext, getSportType());
            this.mTargetValue = owp.a(this.mContext, getSportType());
            String string2 = this.mContext.getResources().getString(R.string._2130841846_res_0x7f0210f6);
            int i = this.mTargetType;
            if (i == 1) {
                if (UnitUtil.h()) {
                    string = UnitUtil.e(UnitUtil.e(this.mTargetValue, 3), 1, 2);
                    str = this.mContext.getResources().getString(R.string._2130841383_res_0x7f020f27);
                } else {
                    string = getTargetValueStr(this.mTargetValue);
                    str = this.mContext.getResources().getString(R.string._2130837660_res_0x7f02009c);
                }
            } else if (i == 0) {
                string = UnitUtil.e(this.mTargetValue / 60.0f, 1, 0);
                str = this.mContext.getResources().getQuantityString(R.plurals._2130903233_res_0x7f0300c1, (int) this.mTargetValue);
            } else if (i == 2) {
                string = UnitUtil.e(this.mTargetValue / 1000.0f, 1, 0);
                str = this.mContext.getResources().getString(R.string._2130837659_res_0x7f02009b);
            } else {
                string = this.mContext.getResources().getString(R.string._2130842526_res_0x7f02139e);
                string2 = "";
                str = "";
            }
            LogUtil.a(this.TAG, "setTargetStyleAndValue() targetTypeStr = ", string2, ", targetValueStr = ", string);
            putViewMap(map, string, string2, str);
            return;
        }
        map.put("IS_GOAL_BUTTON_SHOW", false);
    }

    private String getTargetValueStr(float f) {
        double d = f;
        if (Math.abs(d - 42.195d) < 1.0E-5d) {
            return UnitUtil.e(d, 1, 3);
        }
        if (Math.abs(d - 21.0975d) < 1.0E-5d) {
            return UnitUtil.e(d, 1, 4);
        }
        return UnitUtil.e(d, 1, 2);
    }

    private void checkSupportListenBook() {
        tye.e(BaseApplication.getContext(), "huaweisport", "q3@!DF5*&$9MrhCS", "tingshu", new ISchemeListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.12
            @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
            public void onSuccess(Object obj) {
                if (obj != null) {
                    LogUtil.a(SportStatisticProvider.this.TAG, "checkSuportListenBook", obj.toString());
                } else {
                    LogUtil.h(SportStatisticProvider.this.TAG, "checkSuportListenBook obj is null");
                }
                SportStatisticProvider.this.mIsSchemeManagerInit = true;
                owp.d(BaseApplication.getContext(), 1);
                if (SportStatisticProvider.this.mStartSportHandler != null) {
                    SportStatisticProvider.this.mStartSportHandler.sendEmptyMessage(3);
                }
            }

            @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
            public void onError(int i, String str) {
                LogUtil.h(SportStatisticProvider.this.TAG, "errorMsg is", str, "errorCode is", Integer.valueOf(i));
                owp.d(BaseApplication.getContext(), 0);
                if (SportStatisticProvider.this.mStartSportHandler != null) {
                    SportStatisticProvider.this.mStartSportHandler.sendEmptyMessage(3);
                }
            }
        });
    }

    private void putViewMap(Map<String, Object> map, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + " " + str + str3;
        }
        map.put("IS_GOAL_BUTTON_SHOW", true);
        map.put("GOAL_BUTTON", str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTargetTypeAndValue(int i, float f, boolean z, boolean z2) {
        if (z) {
            owp.c(this.mContext, getSportType(), i);
            owp.c(this.mContext, f, getSportType());
            owp.e(this.mContext, getSportType(), z2);
            if (z2) {
                owp.b(this.mContext, getSportType(), i, f);
            } else {
                owp.b(this.mContext, getSportType(), i, -1.0f);
            }
        }
    }

    private void setClickListener(Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.15
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("ACCUMULATED_DURATION_CLICK_VIEW".equals(str)) {
                    if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                        SportStatisticProvider.this.clickToLogin();
                        return;
                    } else {
                        SportStatisticProvider.this.startSportHistory();
                        return;
                    }
                }
                if ("GOAL_BUTTON".equals(str)) {
                    if (nsn.a(500)) {
                        return;
                    }
                    SportStatisticProvider.this.mSportTargetDialog.dgr_(SportStatisticProvider.this.getSportType(), SportStatisticProvider.this.mRefreshTargetHandler);
                    return;
                }
                if ("LEFT_IMAGEVIEW".equals(str)) {
                    gie.a(SportStatisticProvider.this.mContext, SportStatisticProvider.this.getSportType(), SportStatisticProvider.this.mTargetType, SportStatisticProvider.this.mTargetValue);
                    return;
                }
                if ("MIDDLE_IMAGEVIEW".equals(str)) {
                    SportStatisticProvider.this.refreshBoltImageIcon();
                    SportStatisticProvider.this.showBoltSelectedListDialog();
                    return;
                }
                if ("CANCEL_GUIDE_BUBBLE_TEXT".equals(str)) {
                    SharedPreferenceManager.e(SportStatisticProvider.this.mContext, Integer.toString(20002), "show_outdoor_cycle_tip", Boolean.toString(false), new StorageParams());
                    return;
                }
                if ("RIGHT_IMAGEVIEW".equals(str)) {
                    SportStatisticProvider.this.rightIconClickEvent();
                    return;
                }
                if ("RIGHT_TOP_IMAGE".equals(str)) {
                    SportStatisticProvider.this.routesEntranceBegin();
                } else if ("RIGHT_TOP_IMAGE_SECOND".equals(str)) {
                    SportStatisticProvider.this.refreshBoltImageIcon();
                    gtb.c().e(SportStatisticProvider.this.mContext, SportStatisticProvider.this.getSportType());
                } else {
                    LogUtil.a(SportStatisticProvider.this.TAG, "useless click");
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rightIconClickEvent() {
        if (isShowMusicRun()) {
            jumpToMusicCourse();
        } else {
            ixx.d().d(this.mContext, AnalyticsValue.MOTION_TRACK_1040014.value(), gotoSportMusic(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBoltImageIcon() {
        HiHealthData hiHealthData;
        SectionBean sectionBean = this.mSectionBean;
        if (sectionBean == null || (hiHealthData = this.mHealthData) == null) {
            return;
        }
        sectionBean.e(hiHealthData);
        LogUtil.a(this.TAG, "refreshBoltImageIcon setData(mHealthData)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBoltSelectedListDialog() {
        int boltErrorStatus = getBoltErrorStatus();
        if (isSupportBoltSportType() && boltErrorStatus != 0 && !BoltCustomDialog.a().e()) {
            BoltCustomDialog.a().b(this.mContext, getSportType(), boltErrorStatus, new BoltCustomDialog.OnConfirmCallBack() { // from class: orm
                @Override // com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.OnConfirmCallBack
                public final void confirmCallBack() {
                    SportStatisticProvider.this.m791x2f78b3c4();
                }
            });
        } else if (isShowBoltNumberListDialog()) {
            BoltCustomDialog.a().b(this.mContext, getSportType(), new BoltCustomDialog.OnConfirmCallBack() { // from class: orr
                @Override // com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog.OnConfirmCallBack
                public final void confirmCallBack() {
                    SportStatisticProvider.this.m792xb1c368a3();
                }
            }, 0);
        } else {
            m792xb1c368a3();
        }
    }

    private boolean isShowBoltNumberListDialog() {
        if (isSupportBoltSportType() && gtb.c().d() >= 2) {
            return true;
        }
        int sportType = getSportType();
        if ((sportType == 258 || sportType == 264) && gtb.c().b() >= 2) {
            return true;
        }
        return sportType == 259 && gtb.c().a() >= 2;
    }

    private int getBoltErrorStatus() {
        List<gsy.b> a2 = gsy.b().a(true);
        if (koq.b(a2)) {
            LogUtil.a(this.TAG, "No connect bolt device list.");
            return 0;
        }
        int sportType = getSportType();
        if (gsy.b().b(a2, sportType)) {
            return 0;
        }
        List<gsy.b> b = gsy.b().b(a2);
        if (!koq.b(gsy.b().e(a2, sportType))) {
            return 2;
        }
        if (!koq.b(b)) {
            return 1;
        }
        LogUtil.a(this.TAG, "Normal bolt status.");
        return 0;
    }

    private boolean isSupportBoltSportType() {
        int sportType = getSportType();
        return sportType == 258 || sportType == 259 || sportType == 264;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickToLogin() {
        LoginInit.getInstance(this.mContext).browsingToLogin(new IBaseResponseCallback() { // from class: ori
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SportStatisticProvider.this.m788x4130b46f(i, obj);
            }
        }, "");
    }

    /* renamed from: lambda$clickToLogin$3$com-huawei-ui-homehealth-runcard-trackfragments-SportStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m788x4130b46f(int i, Object obj) {
        if (i == 0) {
            LogUtil.a(this.TAG, "clickToSportHistory login success");
        } else {
            LogUtil.h(this.TAG, "errorCode is not success", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSportHistory() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(getSportType()));
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_GPS_HISTORY_2010015.value(), hashMap, 0);
        Intent intent = new Intent(this.mContext, (Class<?>) SportHistoryActivity.class);
        intent.putExtra(BleConstants.SPORT_TYPE, getSportType());
        try {
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(this.TAG, "ActivityNotFoundException", e2.getMessage());
        }
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }

    public Map<String, Object> gotoSportMusic() {
        LogUtil.c(this.TAG, "gotoSportMusic");
        Map<String, Object> hashMap = new HashMap<>(5);
        if (owp.e(this.mContext) == 0) {
            hashMap = this.mSportMusicUtils.e(hashMap, getSportType());
        } else {
            if (this.mIsSchemeManagerInit) {
                BaseTrackProvider.b();
            } else {
                tye.e(BaseApplication.getContext(), "huaweisport", "q3@!DF5*&$9MrhCS", "tingshu", new ISchemeListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.11
                    @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                    public void onSuccess(Object obj) {
                        if (obj != null) {
                            LogUtil.a(SportStatisticProvider.this.TAG, "checkSuportListenBook", obj.toString());
                        } else {
                            LogUtil.h(SportStatisticProvider.this.TAG, "checkSuportListenBook obj is null");
                        }
                        SportStatisticProvider.this.mIsSchemeManagerInit = true;
                        BaseTrackProvider.b();
                    }

                    @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                    public void onError(int i, String str) {
                        LogUtil.h(SportStatisticProvider.this.TAG, "errorMsg is", str, "errorCode is", Integer.valueOf(i));
                    }
                });
            }
            hashMap.put("sportMusicType", 1);
        }
        hashMap.put("click", 1);
        hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 0);
        hashMap.put("musicType", Integer.valueOf(gwg.i(this.mContext) ? 4 : 0));
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(getSportType()));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void routesEntranceBegin() {
        LoginInit.getInstance(this.mContext).browsingToLogin(new IBaseResponseCallback() { // from class: orq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SportStatisticProvider.this.m790x50090425(i, obj);
            }
        }, "");
    }

    /* renamed from: lambda$routesEntranceBegin$4$com-huawei-ui-homehealth-runcard-trackfragments-SportStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m790x50090425(int i, Object obj) {
        if (i == 0) {
            checkUserInfo(2);
        } else {
            LogUtil.h(this.TAG, "errorCode is not success", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sportEntranceBegin, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m792xb1c368a3() {
        LoginInit.getInstance(this.mContext).browsingToLogin(new IBaseResponseCallback() { // from class: orp
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SportStatisticProvider.this.m794xcf895294(i, obj);
            }
        }, "");
    }

    /* renamed from: lambda$sportEntranceBegin$5$com-huawei-ui-homehealth-runcard-trackfragments-SportStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m794xcf895294(int i, Object obj) {
        if (i == 0) {
            checkUserInfo(1);
        } else {
            LogUtil.h(this.TAG, "errorCode is not success", Integer.valueOf(i));
        }
    }

    private void checkUserInfo(final int i) {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).checkUserInfo(this.mContext, new IBaseResponseCallback() { // from class: ork
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                SportStatisticProvider.this.m787x6c252acb(i, i2, obj);
            }
        });
    }

    /* renamed from: lambda$checkUserInfo$6$com-huawei-ui-homehealth-runcard-trackfragments-SportStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m787x6c252acb(int i, int i2, Object obj) {
        if (i2 != 0) {
            if (nsn.a(1500)) {
                ReleaseLogUtil.e(this.TAG, "onClick isFastClick");
            } else if (i == 1) {
                checkDeviceStatus();
            } else {
                ReleaseLogUtil.e(this.TAG, "go To Routes");
                goToRoutes();
            }
        }
    }

    private void checkDeviceStatus() {
        this.mIsBlueToothEnable = false;
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            this.mIsBlueToothEnable = BluetoothAdapter.getDefaultAdapter().isEnabled();
        }
        if (gtx.c(this.mContext).bc() && gtx.c(this.mContext).u()) {
            ReleaseLogUtil.d(this.TAG, "Third-app has started sport");
            new NoTitleCustomAlertDialog.Builder(this.mContext).e(R.string._2130843877_res_0x7f0218e5).czC_(R.string._2130843855_res_0x7f0218cf, new View.OnClickListener() { // from class: orj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SportStatisticProvider.this.m786xfd8f833b(view);
                }
            }).e().show();
        } else if (isSupportSportType()) {
            checkMultilingualAudio();
        } else {
            checkTodayCourse();
        }
    }

    /* renamed from: lambda$checkDeviceStatus$7$com-huawei-ui-homehealth-runcard-trackfragments-SportStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m786xfd8f833b(View view) {
        LogUtil.a(this.TAG, "checkDeviceStatus:PositiveButton has been clicked");
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean isSupportSportType() {
        LogUtil.a(this.TAG, "getSportType()= ", Integer.valueOf(getSportType()));
        return getSportType() == 258 || getSportType() == 257 || getSportType() == 259 || getSportType() == 264;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkTodayCourse() {
        if (getSportType() != 258) {
            startSport();
            return;
        }
        ReleaseLogUtil.e(this.TAG, "into getCutPlan");
        Plan cutPlan = getCutPlan();
        if (cutPlan == null) {
            startSport();
        } else {
            getCourseName(cutPlan);
        }
    }

    private void checkMultilingualAudio() {
        AudioResProviderInterface audioResProviderInterface = (AudioResProviderInterface) AppRouter.e(AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_DOWNLOAD, AudioResProviderInterface.class);
        boolean c2 = mxb.a().c(this.mContext);
        String str = this.TAG;
        Object[] objArr = new Object[4];
        objArr[0] = "service = ";
        objArr[1] = Boolean.valueOf(audioResProviderInterface != null);
        objArr[2] = " isEnableCurLang = ";
        objArr[3] = Boolean.valueOf(c2);
        ReleaseLogUtil.e(str, objArr);
        if (audioResProviderInterface != null && c2) {
            audioResProviderInterface.queryOrDownAudioResource(BaseApplication.getActivity(), mxc.a(this.mContext, "Sport"), this.mContext.getResources().getString(R$string.IDS_hwh_base_model_multilingualaudio), new AudioResDownloadListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.13
                @Override // com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener
                public void onResult(int i) {
                    ReleaseLogUtil.e(SportStatisticProvider.this.TAG, "result = ", Integer.valueOf(i));
                    SportStatisticProvider.this.checkTodayCourse();
                }
            });
        } else {
            checkTodayCourse();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSport() {
        boolean a2 = ggx.a(this.mContext, this.mIsBlueToothEnable);
        boolean c2 = ggx.c(this.mContext);
        boolean i = kxz.i(BaseApplication.getContext());
        ReleaseLogUtil.e(this.TAG, "isConnect", Boolean.valueOf(a2), "isIndependentSportDevice", Boolean.valueOf(c2), "update status", Boolean.valueOf(i));
        if (a2 && c2 && !i) {
            mwu.d().a(new e(this));
            Handler handler = this.mStartSportHandler;
            if (handler != null) {
                handler.sendEmptyMessageDelayed(1, 1000L);
                return;
            }
            return;
        }
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.5
            @Override // java.lang.Runnable
            public void run() {
                SportStatisticProvider.this.gotoStartSport();
            }
        });
    }

    private Plan getCutPlan() {
        final Plan[] planArr = new Plan[1];
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b(this.TAG, "refreshMyPlanBanner, getCurrentPlan : planApi is null.");
            return planArr[0];
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        planApi.getCurrentIntPlan(new UiCallback<IntPlan>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.d(SportStatisticProvider.this.TAG, "errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                if (intPlan == null || (!intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) && !intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN))) {
                    LogUtil.b(SportStatisticProvider.this.TAG, "getCutPlan : data is null.");
                    countDownLatch.countDown();
                    return;
                }
                Plan compatiblePlan = intPlan.getCompatiblePlan();
                if (compatiblePlan == null) {
                    LogUtil.b(SportStatisticProvider.this.TAG, "getCutPlan currentPlan == null");
                    countDownLatch.countDown();
                } else {
                    if (SportStatisticProvider.isAiRunPlanOpen()) {
                        planArr[0] = compatiblePlan;
                    }
                    countDownLatch.countDown();
                }
            }
        });
        try {
            if (!countDownLatch.await(1L, TimeUnit.SECONDS)) {
                LogUtil.b(this.TAG, "countDownAwait timeOut");
            }
        } catch (InterruptedException unused) {
            LogUtil.b(this.TAG, "countDownAwait interruptedException");
        }
        return planArr[0];
    }

    private void getCourseName(Plan plan) {
        this.mCurrentPlan = plan;
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b(this.TAG, "createTrainPlanView getWorkoutById : planApi is null.");
        } else {
            planApi.getTodayRunPanCourse(plan, new UiCallback<FitWorkout>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    SportStatisticProvider.this.startSport();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(FitWorkout fitWorkout) {
                    if (fitWorkout == null) {
                        LogUtil.b(SportStatisticProvider.this.TAG, "getTodayRunPanCourse onSuccess data == null");
                        SportStatisticProvider.this.startSport();
                    } else if ("Race".equals(fitWorkout.acquireId())) {
                        SportStatisticProvider.this.startSport();
                    } else {
                        SportStatisticProvider.this.showTodayPlanDialog(fitWorkout);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAiRunPlanOpen() {
        return !Utils.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTodayPlanDialog(FitWorkout fitWorkout) {
        CustomViewDialog customViewDialog = this.mPlanDialog;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            LogUtil.a(this.TAG, "mPlanDialog is showing");
            return;
        }
        View createTrainPlanView = createTrainPlanView(fitWorkout);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mContext);
        builder.czg_(createTrainPlanView).c(false);
        CustomViewDialog e2 = builder.e();
        this.mPlanDialog = e2;
        e2.show();
    }

    private View createTrainPlanView(final FitWorkout fitWorkout) {
        View inflate = View.inflate(this.mContext, R.layout.dialog_train_plan, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.goto_plan_describe);
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.start_plan);
        HealthButton healthButton2 = (HealthButton) inflate.findViewById(R.id.more_train_plan);
        HealthButton healthButton3 = (HealthButton) inflate.findViewById(R.id.ignore_train_plan);
        healthTextView.setText(this.mContext.getResources().getString(R.string._2130844443_res_0x7f021b1b, fitWorkout.acquireName()));
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(SportStatisticProvider.this.TAG, "start plan");
                SportStatisticProvider sportStatisticProvider = SportStatisticProvider.this;
                sportStatisticProvider.closeDialog(sportStatisticProvider.mPlanDialog);
                if (fitWorkout.isRunModelCourse()) {
                    ggs.d(SportStatisticProvider.this.mContext, fitWorkout, SportStatisticProvider.this.mCurrentPlan, new Date().getTime());
                } else {
                    ggs.c(SportStatisticProvider.this.mContext, fitWorkout, SportStatisticProvider.this.mCurrentPlan, new Date().getTime());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(SportStatisticProvider.this.TAG, "more plan");
                SportStatisticProvider sportStatisticProvider = SportStatisticProvider.this;
                sportStatisticProvider.closeDialog(sportStatisticProvider.mPlanDialog);
                SportStatisticProvider.this.jumpToPlanTab();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        healthButton3.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportStatisticProvider.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(SportStatisticProvider.this.TAG, "ignore plan");
                SportStatisticProvider.this.startSport();
                SportStatisticProvider sportStatisticProvider = SportStatisticProvider.this;
                sportStatisticProvider.closeDialog(sportStatisticProvider.mPlanDialog);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToPlanTab() {
        Intent intent = new Intent();
        intent.setPackage("com.huawei.health");
        intent.setClassName("com.huawei.health", "com.huawei.health.MainActivity");
        intent.setFlags(872415232);
        intent.putExtra("isToSportTab", true);
        intent.putExtra(BleConstants.SPORT_TYPE, 2);
        intent.putExtra(Constants.HOME_TAB_NAME, "SPORT");
        try {
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b(this.TAG, "ActivityNotFoundException", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoStartSport() {
        StorageParams storageParams = new StorageParams();
        String num = Integer.toString(20002);
        String b = SharedPreferenceManager.b(this.mContext, num, "map_tracking_sport_type_sportting");
        ReleaseLogUtil.e(this.TAG, "gotoStartSport typeStr = ", b);
        if (!TextUtils.isEmpty(b)) {
            ReleaseLogUtil.e(this.TAG, "gotoStartSport mSportType = ", Integer.valueOf(getSportType()));
            SharedPreferenceManager.e(this.mContext, num, "map_tracking_sport_type_sportting", Integer.toString(getSportType()), storageParams);
            SharedPreferenceManager.e(this.mContext, num, "sport_target_type_sportting", Integer.toString(-1), storageParams);
        }
        if (isOpenFreeIndoorRunning(this.mContext)) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", 1);
            ixx.d().d(this.mContext, AnalyticsValue.BI_TRACK_ENTER_RUNNING_WITH_VIBRATE_STEPCOUNT_1040050.value(), hashMap, 0);
        }
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent("track_receiver_go_to_sport"));
        gso.e().a(this.mContext, getSportType(), this.mTargetType, this.mTargetValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeDialog(BaseDialog baseDialog) {
        if (baseDialog == null || !baseDialog.isShowing()) {
            return;
        }
        baseDialog.dismiss();
    }

    private boolean isOpenFreeIndoorRunning(Context context) {
        return context != null && "true".equals(SharedPreferenceManager.b(context, Integer.toString(20002), "ihealthlabs")) && LanguageUtil.m(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showStartSportIgnoreLinkDialog() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mContext);
        builder.e(R.string._2130839499_res_0x7f0207cb);
        builder.czC_(R.string._2130841379_res_0x7f020f23, new View.OnClickListener() { // from class: orf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportStatisticProvider.this.m793x1709472d(view);
            }
        });
        builder.czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: orl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* renamed from: lambda$showStartSportIgnoreLinkDialog$8$com-huawei-ui-homehealth-runcard-trackfragments-SportStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m793x1709472d(View view) {
        LogUtil.a(this.TAG, "ignore link and continue");
        hab.c(true);
        gotoStartSport();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHealthData(HiHealthData hiHealthData) {
        if (hiHealthData != null) {
            this.mHealthData = hiHealthData.copyData();
        }
        String a2 = cau.a(String.valueOf(getSportType()));
        double a3 = !TextUtils.isEmpty(a2) ? CommonUtils.a(a2) : 0.0d;
        double hiHealthDataDistance = getHiHealthDataDistance(this.mHealthData);
        boolean i = gwg.i(BaseApplication.getContext());
        boolean parseBoolean = Boolean.parseBoolean(cau.a(MUSIC_TAG));
        LogUtil.a(this.TAG, "old data is ", a2, " new data is ", Double.valueOf(hiHealthDataDistance));
        if (a3 == hiHealthDataDistance && i == parseBoolean) {
            LogUtil.a(this.TAG, "same data, do not refresh");
            return;
        }
        requestImageMusicAsync(BaseApplication.getContext(), i);
        this.mSectionBean.e(this.mHealthData);
        if (this.mHealthData != null) {
            cau.d(String.valueOf(getSportType()), String.valueOf(hiHealthDataDistance));
            cau.d(MUSIC_TAG, String.valueOf(i));
        }
    }

    static class a implements IBaseResponseCallback {
        WeakReference<SportStatisticProvider> d;

        a(SportStatisticProvider sportStatisticProvider) {
            this.d = new WeakReference<>(sportStatisticProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            this.d.get();
            SportStatisticProvider sportStatisticProvider = this.d.get();
            if (sportStatisticProvider == null) {
                return;
            }
            if (!LoginInit.getInstance(null).getIsLogined()) {
                sportStatisticProvider.updateHealthData(new HiHealthData());
                return;
            }
            if (!(obj instanceof SparseArray)) {
                if (koq.e(obj, HiHealthData.class)) {
                    List list = (List) obj;
                    if (koq.c(list)) {
                        sportStatisticProvider.updateHealthData((HiHealthData) list.get(0));
                    } else {
                        sportStatisticProvider.updateHealthData(new HiHealthData());
                    }
                    LogUtil.a(sportStatisticProvider.getTag(), list);
                    return;
                }
                LogUtil.a(sportStatisticProvider.getTag(), "wrong data : ", Integer.valueOf(i));
                return;
            }
            try {
                List list2 = (List) ((SparseArray) obj).get(0);
                if (koq.c(list2)) {
                    sportStatisticProvider.updateHealthData((HiHealthData) list2.get(0));
                } else {
                    sportStatisticProvider.updateHealthData(new HiHealthData());
                }
                LogUtil.a(sportStatisticProvider.getTag(), list2);
            } catch (ClassCastException e) {
                LogUtil.h(sportStatisticProvider.getTag(), e.getMessage());
            }
        }
    }

    static class e implements IBaseResponseCallback {
        WeakReference<SportStatisticProvider> e;

        e(SportStatisticProvider sportStatisticProvider) {
            this.e = new WeakReference<>(sportStatisticProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i != 100000 || obj == null) {
                return;
            }
            OperatorStatus operatorStatus = (OperatorStatus) new Gson().fromJson(CommonUtil.z((String) obj), OperatorStatus.class);
            SportStatisticProvider sportStatisticProvider = this.e.get();
            if (sportStatisticProvider == null || sportStatisticProvider.mStartSportHandler == null) {
                return;
            }
            Handler handler = sportStatisticProvider.mStartSportHandler;
            handler.removeMessages(1);
            if (operatorStatus.getTrainMonitorState() == 0) {
                ReleaseLogUtil.e(sportStatisticProvider.getTag(), "device is not running");
                handler.sendMessage(handler.obtainMessage(1));
            } else {
                ReleaseLogUtil.e(sportStatisticProvider.getTag(), "MSG_SHOW_DIALOG");
                handler.sendMessage(handler.obtainMessage(2));
            }
        }
    }

    public final Context getContext() {
        Context context = this.mContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public void setInitData(Context context, HashMap<String, Object> hashMap, Object obj, boolean z, Integer num, String str) {
        String a2;
        if (obj instanceof HiHealthData) {
            if (z) {
                hashMap.put("SECTION_STYLE", BaseSection.OUTDOOR_RUN_STYLE);
                ArrayList arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(R.drawable._2131430478_res_0x7f0b0c4e));
                arrayList.add(Integer.valueOf(R.drawable._2131430479_res_0x7f0b0c4f));
                hashMap.put("BACKGROUND", arrayList);
                hashMap.put("MARKER", Integer.valueOf(R.drawable._2131430480_res_0x7f0b0c50));
                gxu a3 = gtv.a("SportAdStartSportIconOutdoor");
                if (a3 != null && a3.c()) {
                    if (!TextUtils.isEmpty(a3.a())) {
                        LogUtil.a(this.TAG, "initStartSportIcon() SportAd show outdoor run icon");
                        a2 = a3.a();
                        ResourceBriefInfo b = gtv.b(14001);
                        this.mIndoorResourceBriefInfo = b;
                        MarketingBiUtils.d(14001, b);
                    } else {
                        LogUtil.a(this.TAG, "initStartSportIcon() SportAd indoor imageUrl is null or empty");
                    }
                }
                a2 = "";
            } else {
                hashMap.put("SECTION_STYLE", BaseSection.INDOOR_RUN_STYLE);
                ArrayList arrayList2 = new ArrayList();
                if (CommonUtil.h(LoginInit.getInstance(context).getAccountInfo(1009)) == 7) {
                    arrayList2.add(Integer.valueOf(R.drawable._2131431803_res_0x7f0b117b));
                    arrayList2.add(Integer.valueOf(R.drawable._2131431804_res_0x7f0b117c));
                } else {
                    arrayList2.add(Integer.valueOf(R.drawable._2131431801_res_0x7f0b1179));
                    arrayList2.add(Integer.valueOf(R.drawable._2131431802_res_0x7f0b117a));
                }
                hashMap.put("BACKGROUND", arrayList2);
                gxu a4 = gtv.a("SportAdStartSportIconIndoor");
                if (a4 != null && a4.c()) {
                    if (!TextUtils.isEmpty(a4.a())) {
                        LogUtil.a(this.TAG, "initStartSportIcon() SportAd show indoor run icon");
                        a2 = a4.a();
                        ResourceBriefInfo b2 = gtv.b(14002);
                        this.mIndoorResourceBriefInfo = b2;
                        MarketingBiUtils.d(14002, b2);
                    } else {
                        LogUtil.a(this.TAG, "initStartSportIcon() SportAd indoor imageUrl is null or empty");
                    }
                }
                a2 = "";
            }
            hashMap.put("ACCUMULATED_DURATION_TEXT", this.mContext.getResources().getString(R.string._2130844759_res_0x7f021c57, this.mIsShowImperial ? this.mContext.getResources().getString(R.string._2130841383_res_0x7f020f27) : this.mContext.getResources().getString(R.string._2130837660_res_0x7f02009c)));
            if (LoginInit.getInstance(context).getIsLogined()) {
                hashMap.put("IS_GOAL_BUTTON_SHOW", true);
            }
            hashMap.put("GOAL_BUTTON", context.getResources().getString(R.string._2130842526_res_0x7f02139e));
            LogUtil.b(this.TAG, "imageURl  " + a2);
            if ("".equals(a2)) {
                ArrayList arrayList3 = new ArrayList(2);
                arrayList3.add(num);
                arrayList3.add(num);
                hashMap.put("MIDDLE_IMAGEVIEW", arrayList3);
            } else {
                ArrayList arrayList4 = new ArrayList(1);
                arrayList4.add(a2);
                hashMap.put("MIDDLE_IMAGEVIEW", arrayList4);
            }
            hashMap.put("MIDDLE_IMAGEVIEW_DESC", nsf.h(R.string._2130842267_res_0x7f02129b));
            ArrayList arrayList5 = new ArrayList(4);
            arrayList5.add(Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad));
            arrayList5.add(Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad));
            arrayList5.add(Integer.valueOf(R.drawable._2131430459_res_0x7f0b0c3b));
            arrayList5.add(Integer.valueOf(R.string._2130842050_res_0x7f0211c2));
            if (!Utils.o()) {
                hashMap.put("LEFT_IMAGEVIEW", arrayList5);
            }
            if (!TextUtils.isEmpty(this.mAdvertisementTextTitle)) {
                hashMap.put("ADVERTISEMENT_TEXT", this.mAdvertisementTextTitle);
                ReleaseLogUtil.e(this.TAG, "setInitData mAdvertisementTextTitle  ", this.mAdvertisementTextTitle);
            }
            LogUtil.a(str, hashMap.toString());
        }
    }

    public static double getDataDistance(HiHealthData hiHealthData) {
        return hiHealthData.getDouble("Track_Run_Distance_Sum");
    }

    protected List<Integer> getWarmUpIcon() {
        ArrayList arrayList = new ArrayList(4);
        Integer valueOf = Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad);
        arrayList.add(valueOf);
        arrayList.add(valueOf);
        arrayList.add(Integer.valueOf(R.drawable._2131430459_res_0x7f0b0c3b));
        arrayList.add(Integer.valueOf(R.string._2130842050_res_0x7f0211c2));
        return arrayList;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDataLite(Context context) {
        LogUtil.a(this.TAG, "loadDataLite refresh start");
        if (koq.c(gsy.b().a(true))) {
            refreshBoltImageIcon();
        }
        LogUtil.a(this.TAG, "loadDataLite refresh end");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        gsy.b().e(this.TAG);
        unregisterHiHealthDataBroadcast();
    }
}
