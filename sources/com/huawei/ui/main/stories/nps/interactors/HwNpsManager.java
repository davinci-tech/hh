package com.huawei.ui.main.stories.nps.interactors;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.nps.activity.DialogActivityUtils;
import com.huawei.ui.main.stories.nps.activity.NpsDialogActivity;
import com.huawei.ui.main.stories.nps.activity.QuestionMainActivity;
import com.huawei.ui.main.stories.nps.harid.HagridNpsManager;
import com.huawei.ui.main.stories.nps.interactors.cache.QuestionCache;
import com.huawei.ui.main.stories.nps.interactors.db.QuestionSurveyDataBase;
import com.huawei.ui.main.stories.nps.interactors.db.QuestionSurveyTable;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionDestSiteResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSureyResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyCommitParam;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyDetailResponse;
import com.huawei.ui.main.stories.nps.interactors.mode.Url;
import com.huawei.ui.main.stories.nps.interactors.util.DeviceNpsDelayUtils;
import com.huawei.ui.main.stories.nps.interactors.util.EcologyNpsUtil;
import com.huawei.ui.main.stories.nps.interactors.util.TaskUtils;
import com.huawei.ui.main.stories.nps.interactors.util.ThirdpartyPhoneNpsUtils;
import com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController;
import defpackage.ceo;
import defpackage.cjv;
import defpackage.cpa;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cvz;
import defpackage.dcz;
import defpackage.ixx;
import defpackage.jah;
import defpackage.jcx;
import defpackage.jec;
import defpackage.jpt;
import defpackage.jpu;
import defpackage.koq;
import defpackage.kxy;
import defpackage.lqi;
import defpackage.lql;
import defpackage.nrv;
import defpackage.rbi;
import defpackage.rbj;
import defpackage.rbp;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.DataBaseHelper;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.HwEncryptUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class HwNpsManager extends HwBaseManager {
    private static final String APP_ID_DEFAULT = "3";
    private static final String CHANNEL = "&channel=100001";
    private static final String CHANNEL_COMMON_ID = "channel_common_id";
    private static final String CHANNEL_VERSION = "100001";
    private static final String COMMA = ",";
    private static final int COUNTRY_LIST_INDEX_ZERO = 0;
    private static final long DAY = 86400000;
    private static final int DB_VERSION_102 = 102;
    private static final String DEVICE_NAME = "deviceName";
    private static final String DEVICE_NAME_K1 = "K1";
    private static final String DEVICE_NAME_K2 = "K2";
    private static final String DEVICE_NPS_NOTIFY_FLAG = "device_nps";
    private static final String ECOLOGY_NPS_TRUE = "true";
    private static final String ENCODING = "UTF-8";
    private static final String EQUAL_SIGN = "=";
    private static final int ERROR_CODE = 305003;
    private static final long FIFTEEN_DAYS = 1296000000;
    private static final String FIRMWARE = "firmware";
    public static final String KEY_ECOLOGY_DEVICE_UUID = "nps_ecology_device_uuid";
    public static final String KEY_ECOLOGY_MODEL = "ecology_model";
    public static final String KEY_ECOLOGY_NPS_ID = "ecology_nps_id";
    public static final String KEY_ECOLOGY_NPS_REQUEST = "nps_ecology_request";
    public static final String KEY_NPS_LAST_TIME = "nps_last_time";
    public static final String KEY_NPS_SHOW = "KEY_NPS_SHOW";
    public static final String KEY_WEAR_FIRMWARE = "wear_nps_firmware";
    public static final String KEY_WEAR_MODEL = "wear_nps_model";
    public static final String KEY_WEAR_NAME = "wear_nps_name";
    public static final String KEY_WEAR_SN = "wear_nps_sn";
    private static final String LEFT_SLASH = "/";
    public static final String MESSAGE_NPS_ID = "MESSAGE_NPSID";
    private static final int MILLISECONDS = 1000;
    private static final String MNP_SHOW_TIME = "MNP_SHOW_TIME";
    private static final String MOBILE_COUNTRY_CODE_CHINA = "460";
    private static final String NOTIFY_SHOW_TIME = "notify_show_time";
    private static final String NOT_IN_DATABASE_BI = "1";
    private static final String NOT_SUPPORT_NAME = "W1";
    public static final String NPS_CLOUD_CONFIG = "KEY_NPS_CLOUD_CONFIG";
    public static final String NPS_ECOLOGY_QUESTION_CONTENT_KEY = "nps_ecology_question_content_info";
    public static final int NPS_NOTIFICATION_OPEN = 1;
    private static final String NPS_PART_URL = "ccpc-cn.consumer";
    private static final String NPS_QUESTION_CONTENT_KEY = "nps_question_content_info";
    public static final int NPS_RECYCLE_QUESTION_OPEN = 1;
    private static final String NPS_SERVER_ERROR_BI = "4";
    private static final String NPS_SERVER_QUESTION_EXIST_BI = "3";
    private static final String NPS_SERVER_REQUEST_BI = "1";
    private static final String NPS_SERVER_RESPONSE_BI = "2";
    public static final int NPS_START_HOUR_DEFAULT = 8;
    public static final int NPS_START_MINUTE_DEFAULT = 23;
    private static final String NPS_SURVEY_IDS = "1591839460740, b51c89db-8816-11e7-b0e5-fa163ec7a185";
    private static final String NPS_SURVEY_NUMBER_OF_TIMES_BI = "time";
    private static final String NPS_SURVEY_TYPE = "1";
    private static final String NPS_TEST_PART_URL = "ccpc.test";
    private static final String NPS_TRIGGER_BROADCASE_BI = "1";
    public static final String NPS_TRIGGER_CONDITIONAL_ERROR_BI = "5";
    private static final String NPS_TRIGGER_CONDITIONAL_PASS_BI = "4";
    private static final String NPS_TRIGGER_DEVICE_EXIST_DB_BI = "3";
    private static final String NPS_TRIGGER_DEVICE_RIGHT_BI = "2";
    private static final String NPS_TRIGGER_ERROR_DEVICE_UNCONNECT = "disconnectErr";
    private static final String NPS_TRIGGER_ERROR_FIRMWARE = "firmwareErr";
    private static final String NPS_TRIGGER_ERROR_IS_SUBMIT = "mIsGoSubmitSurveyState";
    private static final String NPS_TRIGGER_ERROR_MAC = "macErr";
    public static final String NPS_TRIGGER_ERROR_QUESTION_TABLE = "questionSurveyTableErr";
    private static final String NPS_TRIGGER_ERROR_WEAR_NAME = "wearNameErr";
    private static final String NPS_TRIGGER_REPONSE_DEST_SITE_BI = "7";
    private static final String NPS_TRIGGER_REQUEST_DEST_SITE_BI = "6";
    private static final String NPS_URL = "/ccpcmd/services/dispatch";
    private static final String NSS_SURVEY_TYPE = "2";
    private static final String OS = "os";
    private static final int QUESTION_ID_DEFAULT = -1;
    private static final String QUESTION_MARK = "?";
    private static final String QUESTION_SURVEY_ID_DEFAULT = "-1";
    private static final int QUESTION_SURVEY_TABLE_ID_DEFAULT = 0;
    private static final int QUESTION_SURVEY_TABLE_TIME_DEFAULT = 0;
    private static final int REQUEST_TIME_DEFAULT = -1;
    private static final int REQUEST_TIME_ZERO = 0;
    private static final int RESPONSE_CODE = 0;
    private static final String SAVE_DATABASE_BI = "2";
    private static final String SN = "sn";
    private static final int SN_TYPE = 1;
    private static final long SPAN_DAY_SECOND_NINETY = 7776000;
    private static final long SPAN_DAY_SECOND_ONE_HUNDRED_EIGHTY = 15552000;
    private static final long SPAN_DAY_SECOND_SEVEN_DAY = 604800;
    private static final long SPAN_DAY_SECOND_SIXTY = 5184000;
    private static final long SPAN_DAY_SECOND_THIRTY = 2592000;
    private static final long SPAN_DAY_SECOND_TWO_HUNDRED_TEN = 18144000;
    private static final long SPAN_SECONDS_UPDATE_TIME = 30;
    private static final long SPAN_TRIGGER_BROADCAST_TIME = 3;
    private static final int SUCCESS = 0;
    private static final String SURVEY_ID = "0";
    private static final String SURVEY_ID_DEFAULT = "0";
    private static final String TAG = "HwNpsManager";
    private static final String TAG_RELEASE = "Nps_HwNpsManager";
    private static final String TIMES_DEFAULT = "1";
    private static final int TIME_ONE = 1;
    private static final int TIME_TWO = 2;
    private static final int TWO_TIMES = 2;
    private static final String VERSION = "cVer";
    private static final String VERSION_NEW_CONTENT = "100101620";
    private static final String VERSION_NSS_NEW_CONTENT = "100101621";
    private static final String VERSION_OLD_CONTENT = "21319";
    private final Context mContext;
    private String mCountryCode;
    private long mCurrentRequestTime;
    private final Type mDestSiteType;
    private QuestionSurveyDetailResponse mDetailResponse;
    private String mDeviceMac;
    private String mDeviceName;
    private boolean mEcologyNpsRequest;
    private volatile int mEcologyRequestTime;
    private ExecutorService mExecutorService;
    private String mFirmware;
    private HwNpsCallback mHwNpsCallback;
    private boolean mIsGoSubmitSurveyState;
    private String mLanguage;
    private MessageCenterApi mMessageCenterApi;
    private String mModel;
    private String mOs;
    private int mQuestionId;
    private List<QuestionSureyResponse> mQuestionList;
    private QuestionSurveyDataBase mQuestionSurveyDateBase;
    private String mQuestionSurveyId;
    private QuestionSurveyTable mQuestionSurveyTable;
    private int mRequestTime;
    private String mSn;
    private String mSubmitAnswers;
    private long mTriggerActivatedTime;
    private final Type mType;
    private static final Set<String> NO_REMIND_DEVICE_CERT_MODEL = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.1
        private static final long serialVersionUID = -2170731284928559475L;

        {
            add("ASK-B19");
            add("ASK-B29");
            add("ASK-B19B");
            add("ASK-B29B");
            add("AND-B19");
            add("AND-B29");
            add("AND-B39");
            add("GLL-AL08");
            add("ARC-AL00");
            add("ARC-AL2B");
            add("ARA-B19");
        }
    });
    private static final Object LOCK = new Object();
    private static HwNpsManager sHwNpsManager = null;

    private HwNpsManager(Context context) {
        super(context);
        this.mType = new TypeToken<QuestionSurveyDetailResponse>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.2
        }.getType();
        this.mDestSiteType = new TypeToken<QuestionDestSiteResponse>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.3
        }.getType();
        this.mQuestionSurveyTable = null;
        this.mDeviceMac = "";
        this.mQuestionSurveyId = "0";
        this.mQuestionId = -1;
        this.mRequestTime = -1;
        this.mEcologyRequestTime = -1;
        this.mIsGoSubmitSurveyState = false;
        this.mEcologyNpsRequest = false;
        this.mSubmitAnswers = "";
        this.mHwNpsCallback = null;
        this.mCurrentRequestTime = 0L;
        this.mTriggerActivatedTime = 0L;
        this.mContext = BaseApplication.getContext();
        ReleaseLogUtil.e(TAG_RELEASE, "HwNpsManager nps 0");
        this.mMessageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        this.mIsGoSubmitSurveyState = false;
        this.mRequestTime = -1;
        this.mQuestionSurveyId = "-1";
        initQuestionSurveyDb();
    }

    public static HwNpsManager getInstance() {
        HwNpsManager hwNpsManager;
        synchronized (LOCK) {
            if (sHwNpsManager == null) {
                sHwNpsManager = new HwNpsManager(BaseApplication.getContext());
            }
            hwNpsManager = sHwNpsManager;
        }
        return hwNpsManager;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10004;
    }

    private void initQuestionSurveyDb() {
        if (this.mExecutorService == null) {
            this.mExecutorService = Executors.newSingleThreadExecutor();
        }
        this.mQuestionSurveyDateBase = new QuestionSurveyDataBase();
        int b = DataBaseHelper.c(String.valueOf(getModuleId())).b();
        LogUtil.a(TAG, "nps initQuestionSurveyDb newDbVersion:", 112, ", oldDbVersion:", Integer.valueOf(b));
        if (112 > b && b > 0 && b <= 102) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps initQuestionSurveyDb upgradeQstnSurveyDB");
            this.mExecutorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.4
                @Override // java.lang.Runnable
                public void run() {
                    QuestionSurveyDataBase questionSurveyDataBase = HwNpsManager.this.mQuestionSurveyDateBase;
                    HwNpsManager hwNpsManager = HwNpsManager.this;
                    questionSurveyDataBase.upgradeQuestionSurveyDateBase(hwNpsManager, 10004, hwNpsManager.mContext);
                }
            });
        } else {
            LogUtil.a(TAG, "nps initQuestionSurveyDb createDBTable");
            this.mExecutorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.5
                @Override // java.lang.Runnable
                public void run() {
                    HwNpsManager.this.mQuestionSurveyDateBase.createDateBaseTable(HwNpsManager.this);
                    ReleaseLogUtil.e(HwNpsManager.TAG_RELEASE, "nps initQuestionSurveyDb createDBTable thread finish");
                }
            });
            ReleaseLogUtil.e(TAG_RELEASE, "nps initQuestionSurveyDb createDBTable finish");
        }
    }

    public void activatedQuestionSurvey() {
        setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "1", "");
        LogUtil.a(TAG, "nps Enter activatedQuestionSurvey");
        if (this.mIsGoSubmitSurveyState) {
            setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", NPS_TRIGGER_ERROR_IS_SUBMIT);
            ReleaseLogUtil.d(TAG_RELEASE, "nps activatedQuestionSurvey now submitSurvey return!");
            return;
        }
        if (isDeviceInfoRight()) {
            setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "2", "");
            String deviceId = getDeviceId(jcx.j());
            this.mDeviceMac = deviceId;
            this.mQuestionId = -1;
            this.mQuestionSurveyId = "-1";
            if (isDeviceIdInDb(deviceId)) {
                setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "3", "");
                ReleaseLogUtil.e(TAG_RELEASE, "nps activatedQuestionSurvey isDeviceIdInDb");
                QuestionSurveyTable surveyTableByDevice = getSurveyTableByDevice(this.mDeviceMac);
                this.mQuestionSurveyTable = surveyTableByDevice;
                if (surveyTableByDevice == null) {
                    setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", NPS_TRIGGER_ERROR_QUESTION_TABLE);
                    ReleaseLogUtil.d(TAG_RELEASE, "nps activatedQuestionSurvey mQuestionSurveyTable is null");
                    return;
                }
                ReleaseLogUtil.e(TAG_RELEASE, "nps mQuestionSurveyTable times:", surveyTableByDevice.toString());
                int times = this.mQuestionSurveyTable.getTimes();
                this.mRequestTime = times;
                if (times < 2 && isJudgeGetSurveyTimeArrive(times, this.mQuestionSurveyTable)) {
                    startRequestDeviceNps();
                    loadCloudNpsConfigList();
                }
            } else {
                ReleaseLogUtil.e(TAG_RELEASE, "nps activatedQuestionSurvey not isDeviceIdInDb");
                this.mEcologyNpsRequest = false;
                this.mRequestTime = -1;
                setBiNpsEvent(AnalyticsValue.NPS_SAVE_DATABASE.value(), "1", "");
                requestGetDestSite();
                loadCloudNpsConfigList();
            }
            ReleaseLogUtil.e(TAG_RELEASE, "nps Leave activatedQuestionSurvey");
        }
    }

    public boolean isHideDeviceByCertModel(String str, rbj rbjVar) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "isHideByCertModel CertModel empty!");
            return false;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        LogUtil.a(TAG, "isHideByCertModel is ", upperCase);
        if (rbjVar != null && rbjVar.a().contains(upperCase)) {
            ReleaseLogUtil.e(TAG_RELEASE, "isHideByCertModel true");
            return true;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "isHideByCertModel false");
        return false;
    }

    public void sendNotify(DeviceInfo deviceInfo) {
        LogUtil.h(TAG, "sendNotify enter");
        rbj npsCloudConfig = getNpsCloudConfig();
        if (!isShowToDo(npsCloudConfig)) {
            cancelNotify();
            LogUtil.h(TAG, "sendNotify isShowToDo is false");
            return;
        }
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceName()) || HagridNpsManager.getInstance().isWeightDeviceNps()) {
            LogUtil.h(TAG, "sendNotify: not support send notify");
            return;
        }
        boolean isNotificationNps = isNotificationNps(npsCloudConfig, deviceInfo.getCertModel());
        if (!isNotificationNps) {
            ReleaseLogUtil.d(TAG_RELEASE, "isNotificationNps :", Boolean.valueOf(isNotificationNps));
            return;
        }
        if (!isTimeOfNps(npsCloudConfig)) {
            ReleaseLogUtil.d(TAG_RELEASE, "sendNotify: not support send notify time");
            return;
        }
        if (Math.abs(System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), getSharedPreference(NOTIFY_SHOW_TIME))) < (npsCloudConfig.i().intValue() / 2) * 86400000) {
            LogUtil.h(TAG, "sendNotify less than 7 days");
            return;
        }
        String deviceName = deviceInfo.getDeviceName();
        int lastIndexOf = deviceName.lastIndexOf(Constants.LINK);
        if (lastIndexOf != -1) {
            deviceName = deviceName.substring(0, lastIndexOf);
        }
        String string = this.mContext.getResources().getString(R$string.IDS_device_nps_notify_title);
        String format = String.format(this.mContext.getResources().getString(R$string.IDS_device_nps_notify_content), deviceName);
        Intent intent = new Intent(this.mContext, (Class<?>) QuestionMainActivity.class);
        intent.putExtra("from", "notify");
        PendingIntent activity = PendingIntent.getActivity(this.mContext, 781507976, intent, 201326592);
        long abs = FIFTEEN_DAYS - Math.abs(System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), getSharedPreference(MNP_SHOW_TIME)));
        LogUtil.a(TAG, "sendNotify timeout:", Long.valueOf(abs));
        NotificationCompat.Builder priority = new NotificationCompat.Builder(this.mContext, CHANNEL_COMMON_ID).setContentTitle(string).setContentText(format).setSmallIcon(R.drawable.healthlogo_ic_notification).setContentIntent(activity).setTimeoutAfter(abs).setPriority(0);
        if (EnvironmentInfo.j() || CommonUtil.bm()) {
            priority.setStyle(new NotificationCompat.BigTextStyle().bigText(format));
        } else {
            RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.device_nps_notify_layout);
            remoteViews.setTextViewText(R.id.tv_title, string);
            remoteViews.setTextViewText(R.id.tv_content, format);
            priority.setCustomBigContentView(remoteViews);
            priority.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        }
        NotificationManagerCompat from = NotificationManagerCompat.from(this.mContext);
        Notification build = priority.build();
        build.flags |= 2;
        from.notify(781507976, build);
        setSharedPreference(NOTIFY_SHOW_TIME, String.valueOf(System.currentTimeMillis()));
    }

    public void cancelNotify() {
        if (HagridNpsManager.getInstance().isWeightDeviceNps()) {
            LogUtil.h(TAG, "cancelNotify isWeightDeviceNps");
        } else {
            NotificationManagerCompat.from(this.mContext).cancel(781507976);
            LogUtil.a(TAG, "cancelNotify");
        }
    }

    private boolean isNotificationNps(rbj rbjVar, String str) {
        if (rbjVar == null) {
            rbjVar = getNpsCloudConfig();
        }
        if (CommonUtil.bh()) {
            return rbjVar.b().intValue() == 1;
        }
        return ThirdpartyPhoneNpsUtils.isSupportThirdPartyPhone(str);
    }

    public boolean isRecycleQuestionNps(String str) {
        rbj npsCloudConfig = getNpsCloudConfig();
        if (CommonUtil.bh()) {
            return npsCloudConfig.d().intValue() == 1;
        }
        return ThirdpartyPhoneNpsUtils.isSupportThirdPartyPhone(str);
    }

    public boolean isTimeOfNps() {
        return isTimeOfNps(getNpsCloudConfig());
    }

    private boolean isTimeOfNps(rbj rbjVar) {
        if (rbjVar == null) {
            rbjVar = getNpsCloudConfig();
        }
        if (Utils.o()) {
            return true;
        }
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        int[] e = rbjVar.e();
        int[] c = rbjVar.c();
        ReleaseLogUtil.e(TAG_RELEASE, "indexHour: ", Integer.valueOf(i), " indexMinute ", Integer.valueOf(i2), " startTime : ", Arrays.toString(e), " endTime :", Arrays.toString(c));
        if (i >= e[0] && i2 >= e[1]) {
            int i3 = c[0];
            if (i < i3) {
                return true;
            }
            if (i == i3 && i2 < c[1]) {
                return true;
            }
        }
        return false;
    }

    private boolean isDeviceInfoRight() {
        LogUtil.a(TAG, "nps Enter activatedQuestionSurvey wearname：", jcx.f());
        if (TextUtils.isEmpty(jcx.f()) || NOT_SUPPORT_NAME.equals(jcx.f())) {
            setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", NPS_TRIGGER_ERROR_WEAR_NAME);
            ReleaseLogUtil.d(TAG_RELEASE, "nps activatedQuestionSurvey DeviceNpsInfoCache.getHuaWeiWearName is not support return");
            return false;
        }
        if (TextUtils.isEmpty(jcx.i())) {
            setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", NPS_TRIGGER_ERROR_FIRMWARE);
            ReleaseLogUtil.d(TAG_RELEASE, "nps activatedQuestionSurvey firmware sysVersion is null return");
            return false;
        }
        if (!TextUtils.isEmpty(jcx.j())) {
            return true;
        }
        setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", NPS_TRIGGER_ERROR_MAC);
        ReleaseLogUtil.d(TAG_RELEASE, "nps activatedQuestionSurvey mac is null return");
        return false;
    }

    private void startRequestDeviceNps() {
        long abs = Math.abs(new Date().getTime() - this.mTriggerActivatedTime) / 1000;
        ReleaseLogUtil.e(TAG_RELEASE, "nps span time is :", Long.valueOf(abs));
        if (abs < 3) {
            ReleaseLogUtil.e(TAG_RELEASE, "nps trigger span time is too short");
            return;
        }
        this.mTriggerActivatedTime = new Date().getTime();
        setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "4", "");
        this.mEcologyNpsRequest = false;
        ReleaseLogUtil.e(TAG_RELEASE, "nps open Select question");
        requestGetDestSite();
    }

    private boolean isDeviceIdInDb(String str) {
        return new QuestionSurveyDataBase().isDeviceIdInDateBaseTable(this, str);
    }

    public QuestionSurveyTable getSurveyTableByDevice(String str) {
        QuestionSurveyTable questionSurveyTable;
        LogUtil.a(TAG, "nps Enter getSurveyTableByDevice");
        QuestionSurveyDataBase questionSurveyDataBase = this.mQuestionSurveyDateBase;
        if (questionSurveyDataBase != null) {
            questionSurveyTable = questionSurveyDataBase.selectSurveyTableByDeviceId(this, str);
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "nps getSurveyTableByDevice enter else null");
            questionSurveyTable = null;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave getSurveyTableByDevice");
        return questionSurveyTable;
    }

    private boolean isJudgeGetSurveyTimeArrive(int i, QuestionSurveyTable questionSurveyTable) {
        boolean isTimeDayThanNinety;
        LogUtil.a(TAG, "nps Enter isJudgeGetSurveyTimeArrive");
        int i2 = i + 1;
        if (i2 < 1 || i2 > 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "nps isJudgeGetSurveyTimeArrive GetSurvey times is off limits!");
            return false;
        }
        if (questionSurveyTable == null) {
            return false;
        }
        LogUtil.a(TAG, "nps isJudgeGetSurveyTimeArrive GetSurvey times:", Integer.valueOf(i2));
        if (i2 == 1) {
            isTimeDayThanNinety = isTimeDayCompare(questionSurveyTable.getLastSurveyTime(), questionSurveyTable);
        } else {
            isTimeDayThanNinety = isTimeDayThanNinety(questionSurveyTable.getLastSurveyTime());
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave isJudgeGetSurveyTimeArrive");
        return isTimeDayThanNinety;
    }

    private boolean isTimeDayCompare(long j, QuestionSurveyTable questionSurveyTable) {
        boolean z;
        ReleaseLogUtil.e(TAG_RELEASE, "NPS enter isTimeDayCompare 30 days currentTimeMillis:", Long.valueOf(System.currentTimeMillis()), "lastSurveyTime:", Long.valueOf(j));
        long currentTimeMillis = (System.currentTimeMillis() - j) / 1000;
        LogUtil.a(TAG, "NPS isTimeDayCompare spanTimeSecond:", Long.valueOf(currentTimeMillis), "spanDaySecond:", Long.valueOf(SPAN_DAY_SECOND_THIRTY));
        if (currentTimeMillis > SPAN_DAY_SECOND_THIRTY) {
            DeviceInfo device = getDevice();
            OpAnalyticsUtil.getInstance().setRiskWarningEvent("isTimeDayCompare30", "lastSurveyTime " + j + ", now " + System.currentTimeMillis() + ", " + questionSurveyTable.toString() + ", deviceName " + device.getDeviceName() + ", deviceModel " + device.getDeviceModel());
            z = true;
        } else {
            z = false;
        }
        LogUtil.a(TAG, "NPS leave isTimeDayCompare isThan:", Boolean.valueOf(z));
        return z;
    }

    private DeviceInfo getDevice() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, TAG);
        return !koq.b(deviceList) ? deviceList.get(0) : new DeviceInfo();
    }

    public boolean isTimeDayThanNinety(long j) {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter isTimeDayThanNinety currentTimeMillis:", Long.valueOf(System.currentTimeMillis()), "lastSurveyTime:", Long.valueOf(j));
        long currentTimeMillis = (System.currentTimeMillis() - j) / 1000;
        long j2 = (!isConnected() || HagridNpsManager.getInstance().isWeightDeviceNps()) ? SPAN_DAY_SECOND_NINETY : SPAN_DAY_SECOND_ONE_HUNDRED_EIGHTY;
        LogUtil.a(TAG, "nps isTimeDayThanNinety spanTimeSecond:", Long.valueOf(currentTimeMillis), ",spanNinetyDaySecond:", Long.valueOf(j2));
        boolean z = currentTimeMillis > j2;
        LogUtil.a(TAG, "nps Leave isTimeDayThanNinety isThan:", Boolean.valueOf(z));
        return z;
    }

    private void requestGetDestSite() {
        LogUtil.a(TAG, "nps Enter requestGetDestSite");
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        final HashMap hashMap = new HashMap(16);
        hashMap.put("countryCode", commonCountryCode);
        LogUtil.a(TAG, "requestGetDestSite getDestSite map:", hashMap.toString());
        if (CommonUtil.aa(this.mContext)) {
            if (this.mExecutorService == null) {
                this.mExecutorService = Executors.newSingleThreadExecutor();
            }
            this.mExecutorService.execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.6
                @Override // java.lang.Runnable
                public void run() {
                    String str;
                    String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainCcpcConsumerHuawei");
                    DeviceInfo d = jpt.d(HwNpsManager.TAG);
                    boolean newHonorScale = HwNpsManager.this.getNewHonorScale();
                    if (cvz.c(d) || newHonorScale) {
                        String str2 = (String) hashMap.get("countryCode");
                        String e = jah.c().e("domain_honor_nps");
                        if (TextUtils.isEmpty(e) || !HwNpsManager.this.isHonorCountry(str2)) {
                            LogUtil.h(HwNpsManager.TAG, "Honor only supports Chinese mainland");
                            return;
                        } else {
                            str = Url.QUESTION_HONOR_URL;
                            url = e;
                        }
                    } else {
                        str = Url.QUESTION_GET_DESTSITE_URL;
                    }
                    jcx.d(GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
                    if (TextUtils.isEmpty(url)) {
                        return;
                    }
                    if (CompileParameterUtil.a("IS_TEST_VERSION") && url.contains(HwNpsManager.NPS_PART_URL)) {
                        url = url.replace(HwNpsManager.NPS_PART_URL, HwNpsManager.NPS_TEST_PART_URL);
                    }
                    LogUtil.a(HwNpsManager.TAG, "requestGetDestSite getDestSite npsurl:", url);
                    HwNpsManager.this.setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "6", "");
                    HwNpsManager.this.executeDestSiteRequest(url + HwNpsManager.NPS_URL + str, hashMap);
                }
            });
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave requestGetDestSite");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getNewHonorScale() {
        if (isConnected()) {
            return false;
        }
        String h = jcx.h();
        boolean h2 = kxy.h(this.mContext, h);
        LogUtil.h(TAG, "Honor uniqueId: ", h, " isNewHonorScale: ", Boolean.valueOf(h2));
        return h2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHonorCountry(String str) {
        String e = jah.c().e("domain_honor_country");
        LogUtil.a(TAG, "honorCountrysString: ", e);
        if (e == null) {
            return false;
        }
        if ("ALL".equalsIgnoreCase(e)) {
            return true;
        }
        for (String str2 : e.split(",")) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    private void insertDeviceToDb() {
        LogUtil.a(TAG, "nps Enter insertDeviceToDb");
        QuestionSurveyTable questionSurveyTable = new QuestionSurveyTable();
        questionSurveyTable.setDeviceId(this.mDeviceMac);
        questionSurveyTable.setLastSurveyTime(new Date().getTime());
        questionSurveyTable.setDeviceType(jcx.f());
        questionSurveyTable.setTimes(0);
        questionSurveyTable.setId(0);
        questionSurveyTable.setSurveyId("0");
        LogUtil.a(TAG, "nps insertDeviceToDb questionSurveyTable:", questionSurveyTable.toString());
        this.mQuestionSurveyDateBase.insertDateBaseTable(this, questionSurveyTable);
        setBiNpsEvent(AnalyticsValue.NPS_SAVE_DATABASE.value(), "2", "");
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave insertDeviceToDb");
    }

    private void insertDeviceToDb(cjv cjvVar) {
        LogUtil.a(TAG, "ecology device nps,Enter insertDeviceToDb");
        String asString = cjvVar.FT_().getAsString("uniqueId");
        String asString2 = cjvVar.FT_().getAsString("name");
        QuestionSurveyTable questionSurveyTable = new QuestionSurveyTable();
        questionSurveyTable.setDeviceId(getDeviceId(asString));
        questionSurveyTable.setLastSurveyTime(cjvVar.d());
        questionSurveyTable.setDeviceType(asString2);
        questionSurveyTable.setTimes(0);
        questionSurveyTable.setId(0);
        questionSurveyTable.setSurveyId("0");
        LogUtil.a(TAG, "nps insertDeviceToDb questionSurveyTable:", questionSurveyTable.toString());
        this.mQuestionSurveyDateBase.insertDateBaseTable(this, questionSurveyTable);
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave insertDeviceToDb");
    }

    public void migrateToDb(QuestionSurveyTable questionSurveyTable) {
        LogUtil.a(TAG, "nps Enter migrateToDb");
        if (questionSurveyTable != null) {
            LogUtil.a(TAG, "nps migrateToDb questionSurveyTable:", questionSurveyTable.toString());
            ReleaseLogUtil.e(TAG_RELEASE, "nps Leave migrateToDb count:", Long.valueOf(this.mQuestionSurveyDateBase.insertDateBaseTable(this, questionSurveyTable)));
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "nps Enter migrateToDb questionSurveyTable is null return");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processGetDestSiteResponse(QuestionDestSiteResponse questionDestSiteResponse) {
        LogUtil.a(TAG, "nps Enter processGetDestSiteResponse requestTime:", Integer.valueOf(this.mRequestTime));
        if (questionDestSiteResponse != null && questionDestSiteResponse.getResCode() == 0) {
            if (this.mRequestTime >= 0 || (this.mEcologyNpsRequest && this.mEcologyRequestTime >= 0)) {
                ReleaseLogUtil.e(TAG_RELEASE, "nps processGetDestSiteResponse to get NPS question");
                processGetDestSiteResponsePart(questionDestSiteResponse.getCountryInfo());
            } else {
                LogUtil.a(TAG, "nps processGetDestSiteResponse activation success");
                insertDeviceToDb();
            }
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave processGetDestSiteResponse");
    }

    private void processGetDestSiteResponsePart(ArrayList arrayList) {
        if (arrayList == null || arrayList.size() <= 0 || !(arrayList.get(0) instanceof QuestionDestSiteResponse.CountryInfo)) {
            return;
        }
        String serverAddress = ((QuestionDestSiteResponse.CountryInfo) arrayList.get(0)).getServerAddress();
        if (this.mEcologyNpsRequest) {
            loadNpsRequestData(serverAddress);
        } else {
            loadInitialData(serverAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDestSiteSubmitSurvey(QuestionDestSiteResponse questionDestSiteResponse) {
        ReleaseLogUtil.e(TAG_RELEASE, "nps getDestSiteSubmitSurvey");
        if (questionDestSiteResponse == null || questionDestSiteResponse.getResCode() != 0) {
            return;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps getDestSiteSubmitSurvey to submit NPS question");
        ArrayList<QuestionDestSiteResponse.CountryInfo> countryInfo = questionDestSiteResponse.getCountryInfo();
        if (countryInfo == null || countryInfo.size() <= 0 || !(countryInfo.get(0) instanceof QuestionDestSiteResponse.CountryInfo)) {
            return;
        }
        String serverAddress = countryInfo.get(0).getServerAddress();
        getQuestionSurveyTable();
        String sharedPreference = getSharedPreference(KEY_ECOLOGY_NPS_REQUEST);
        if (!TextUtils.isEmpty(sharedPreference) && "true".equals(sharedPreference)) {
            submitEcologySurvey(serverAddress);
        } else {
            submitSurvey(serverAddress);
        }
    }

    private void submitSurvey(String str) {
        String str2;
        LogUtil.a(TAG, "nps Enter submitSurvey");
        getRequestInfo();
        this.mDeviceName = getSharedPreference(KEY_WEAR_NAME);
        this.mModel = getSharedPreference(KEY_WEAR_MODEL);
        if (TextUtils.isEmpty(this.mSn)) {
            this.mSn = getSharedPreference(KEY_WEAR_SN);
        }
        if (TextUtils.isEmpty(this.mFirmware)) {
            this.mFirmware = getSharedPreference(KEY_WEAR_FIRMWARE);
        }
        LogUtil.a(TAG, "submitSurvey mModel:", this.mModel, ", deviceModel:", jcx.g(), ", mDeviceName:", this.mDeviceName);
        jcx.f(this.mModel);
        HashMap hashMap = new HashMap(16);
        putSurveyIdAndTimes(hashMap);
        String str3 = this.mDeviceName;
        if (str3 == null) {
            str3 = "deviceName";
        }
        hashMap.put("model", str3);
        hashMap.put("firmware", TextUtils.isEmpty(this.mFirmware) ? "firmware" : this.mFirmware);
        hashMap.put("language", this.mLanguage);
        String str4 = this.mOs;
        if (str4 == null) {
            str4 = "os";
        }
        hashMap.put("os", str4);
        hashMap.put("appID", "3");
        hashMap.put("sn", this.mSn);
        hashMap.put(QuestionSurveyCommitParam.ANSWERS, this.mSubmitAnswers);
        hashMap.put("band", this.mModel);
        hashMap.put("countryCode", this.mCountryCode);
        ReleaseLogUtil.e(TAG_RELEASE, "nps submitSurvey map");
        String str5 = (!isConnected() || HagridNpsManager.getInstance().isWeightDeviceNps()) ? "?cVer=21319" : "?cVer=100101621";
        if (str.endsWith("/")) {
            str2 = str + Url.QUESTION_SURVEY_ANSWER_URL + str5 + CHANNEL;
        } else {
            str2 = str + "/secured/CCPC/EN/ccpcnps/submitSurveyV2/1" + str5 + CHANNEL;
        }
        LogUtil.a(TAG, "nps submitSurvey requestNpsUrl:", str2, " ,request params: ", hashMap.toString());
        if (CommonUtil.aa(this.mContext)) {
            executeSubmiteSurveyRequest(str2, hashMap);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave submitSurvey");
    }

    public QuestionSurveyDetailResponse getDetailResponse() {
        QuestionSurveyDetailResponse questionSurveyDetailResponse = new QuestionSurveyDetailResponse();
        try {
            return (QuestionSurveyDetailResponse) new Gson().fromJson(getQuestionDetail(), this.mType);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "nps putQuestionsCache error:", e.toString());
            return questionSurveyDetailResponse;
        }
    }

    private void putSurveyIdAndTimes(Map<String, String> map) {
        String str;
        String str2;
        QuestionSurveyDetailResponse detailResponse = getDetailResponse();
        if (detailResponse != null && detailResponse.getResCode() == 0) {
            str = detailResponse.getSurveyID();
            str2 = String.valueOf(detailResponse.getQueryTimes());
            ReleaseLogUtil.e(TAG_RELEASE, "nps putQuestionsCache SUCCESS surveyID:", str, ", times:", str2);
        } else {
            QuestionSurveyTable questionSurveyTable = this.mQuestionSurveyTable;
            if (questionSurveyTable != null) {
                str = questionSurveyTable.getSurveyId();
                str2 = "" + this.mQuestionSurveyTable.getTimes();
                ReleaseLogUtil.e(TAG_RELEASE, "nps putQuestionsCache mQuestionSurveyTable surveyID:", str, ", times:", str2);
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "nps putQuestionsCache mQuestionSurveyTable is null");
                str = "0";
                str2 = "1";
            }
        }
        map.put(QuestionSurveyCommitParam.SURVEY_ID, str);
        map.put("times", str2);
    }

    private void submitEcologySurvey(String str) {
        String str2;
        LogUtil.a(TAG, "nps Enter submitEcologySurvey");
        HashMap hashMap = new HashMap(16);
        String sharedPreference = getSharedPreference(KEY_NPS_SHOW);
        QuestionSurveyTable surveyTableByDevice = getSurveyTableByDevice(sharedPreference);
        String language = Locale.getDefault().getLanguage();
        if (surveyTableByDevice != null) {
            hashMap.put(QuestionSurveyCommitParam.SURVEY_ID, surveyTableByDevice.getSurveyId());
            hashMap.put("times", "" + surveyTableByDevice.getTimes());
        } else {
            hashMap.put(QuestionSurveyCommitParam.SURVEY_ID, "0");
            hashMap.put("times", "1");
        }
        String sharedPreference2 = getSharedPreference(KEY_ECOLOGY_MODEL);
        String sharedPreference3 = getSharedPreference(KEY_ECOLOGY_NPS_ID);
        hashMap.put("model", sharedPreference2);
        hashMap.put("language", language);
        hashMap.put("os", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("appID", sharedPreference3);
        hashMap.put("sn", sharedPreference);
        hashMap.put(QuestionSurveyCommitParam.ANSWERS, this.mSubmitAnswers);
        hashMap.put("countryCode", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
        hashMap.put("channel", "100001");
        hashMap.put("cVer", VERSION_NEW_CONTENT);
        ReleaseLogUtil.e(TAG_RELEASE, "nps submitEcologySurvey map");
        if (str.endsWith("/")) {
            str2 = str + Url.QUESTION_SURVEY_ANSWER_URL;
        } else {
            str2 = str + "/secured/CCPC/EN/ccpcnps/submitSurveyV2/1";
        }
        LogUtil.a(TAG, "nps submitEcologySurvey requestNpsUrl:", str2, " ,request params: ", hashMap.toString());
        if (CommonUtil.aa(this.mContext)) {
            executeSubmiteSurveyRequest(str2, hashMap);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave submitEcologySurvey");
    }

    private void generateNpsMessage() {
        ReleaseLogUtil.e(TAG_RELEASE, "nps Enter generateNpsMessage");
        this.mMessageCenterApi.getMessageId(String.valueOf(19), MessageConstant.NPS_MESSAGE_TYPE, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && obj != null && (obj instanceof String)) {
                    String str = (String) obj;
                    MessageObject messageObject = new MessageObject();
                    messageObject.setMsgId(str);
                    messageObject.setModule(String.valueOf(19));
                    messageObject.setType(MessageConstant.NPS_MESSAGE_TYPE);
                    messageObject.setMsgType(2);
                    messageObject.setPosition(1);
                    messageObject.setMsgPosition(11);
                    messageObject.setExpireTime(-1L);
                    messageObject.setReadFlag(0);
                    String string = HwNpsManager.this.mContext.getString(R$string.IDS_nps_success_message_2);
                    messageObject.setMsgContent(string);
                    LogUtil.a(HwNpsManager.TAG, "nps generateNpsMessage contentString:", string);
                    String string2 = HwNpsManager.this.mContext.getString(R$string.IDS_messagecenter_nps_title);
                    messageObject.setMsgTitle(string2);
                    messageObject.setMetadata(string2);
                    LogUtil.a(HwNpsManager.TAG, "nps generateNpsMessage messageCenterTitle:", string2);
                    messageObject.setCreateTime(System.currentTimeMillis());
                    messageObject.setDetailUri("messagecenter://nps_question");
                    messageObject.setImgUri("assets://localMessageIcon/ic_investigation.webp");
                    if (LoginInit.getInstance(BaseApplication.getContext()) != null) {
                        messageObject.setHuid(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
                    }
                    HwNpsManager.this.mMessageCenterApi.insertMessage(messageObject);
                    ReleaseLogUtil.e(HwNpsManager.TAG_RELEASE, "nps Leave generateNpsMessage");
                    if (HwNpsManager.this.mEcologyNpsRequest) {
                        HwNpsManager.this.saveEcologyNpsInfo();
                    } else {
                        HwNpsManager.this.saveWearNpsInfo();
                    }
                    LogUtil.a(HwNpsManager.TAG, "KEY_NPS_SHOW result:", 0);
                    LogUtil.a(HwNpsManager.TAG, "MESSAGE_NPS_ID result:", Integer.valueOf(HwNpsManager.this.setSharedPreference(HwNpsManager.MESSAGE_NPS_ID, str, null)));
                    LogUtil.a(HwNpsManager.TAG, "MNP_SHOW_TIME result:", Integer.valueOf(HwNpsManager.this.setSharedPreference(HwNpsManager.MNP_SHOW_TIME, String.valueOf(System.currentTimeMillis()), null)));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveWearNpsInfo() {
        setSharedPreference(KEY_NPS_SHOW, jcx.j(), null);
        setSharedPreference(KEY_WEAR_MODEL, jcx.g(), null);
        setSharedPreference(KEY_WEAR_NAME, jcx.f(), null);
        setSharedPreference(KEY_WEAR_SN, getDeviceId(jcx.j()), null);
        setSharedPreference(KEY_WEAR_FIRMWARE, jcx.i(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveEcologyNpsInfo() {
        setSharedPreference(KEY_NPS_SHOW, jcx.e(), null);
        setSharedPreference(KEY_ECOLOGY_DEVICE_UUID, jcx.b(), null);
        setSharedPreference(KEY_ECOLOGY_NPS_REQUEST, "true", null);
        setSharedPreference(KEY_ECOLOGY_NPS_ID, jcx.c(), null);
        setSharedPreference(KEY_ECOLOGY_MODEL, jcx.a(), null);
    }

    public void showSelectQuestionDialog() {
        LogUtil.a(TAG, "nps Enter showSelectQuestionDialog");
        DialogActivityUtils dialogActivityUtils = DialogActivityUtils.getInstance();
        dialogActivityUtils.setTitle(this.mContext.getString(R$string.IDS_messagecenter_nps_title));
        dialogActivityUtils.setMessage(this.mContext.getString(R$string.IDS_nps_success_message_2));
        dialogActivityUtils.setPositiveButton(this.mContext.getString(R$string.IDS_nps_participate_sure), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(HwNpsManager.TAG, "nps press PositiveButton");
                Intent intent = new Intent();
                intent.setClassName(HwNpsManager.this.mContext, QuestionMainActivity.class.getName());
                if (HwNpsManager.this.mQuestionSurveyTable != null) {
                    HwNpsManager.this.mQuestionSurveyTable.setId(HwNpsManager.this.mQuestionId);
                }
                intent.setFlags(268435456);
                HwNpsManager.this.mContext.startActivity(intent);
                ReleaseLogUtil.e(HwNpsManager.TAG_RELEASE, "nps press mContext.startActivity");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        dialogActivityUtils.setNegativeButton(this.mContext.getString(R$string.IDS_nps_participate_cancel), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c(HwNpsManager.TAG, "nps press NegativeButton");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        Intent intent = new Intent();
        intent.setClassName(this.mContext, NpsDialogActivity.class.getName());
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave showSelectQuestionDialog");
    }

    public QuestionSurveyTable getQuestionSurveyTable() {
        String sharedPreference = getSharedPreference(KEY_ECOLOGY_NPS_REQUEST);
        if (TextUtils.isEmpty(sharedPreference) && "true".equals(sharedPreference)) {
            this.mQuestionSurveyTable = getSurveyTableByDevice(jcx.e());
        } else {
            this.mQuestionSurveyTable = getSurveyTableByDevice(getDeviceId(jcx.j()));
        }
        return this.mQuestionSurveyTable;
    }

    private void setQuestionSurveyTable(long j) {
        String deviceId;
        LogUtil.a(TAG, "Enter setQuestionSurveyTable");
        if (this.mEcologyNpsRequest) {
            deviceId = jcx.e();
        } else {
            deviceId = getDeviceId(jcx.j());
        }
        QuestionSurveyTable surveyTableByDevice = getSurveyTableByDevice(deviceId);
        this.mQuestionSurveyTable = surveyTableByDevice;
        if (surveyTableByDevice == null) {
            return;
        }
        int i = this.mQuestionId;
        if (i != -1) {
            surveyTableByDevice.setId(i);
        }
        this.mQuestionSurveyTable.setSurveyId(this.mQuestionSurveyId);
        long time = new Date().getTime();
        if (j <= 0) {
            j = time;
        }
        long abs = Math.abs(new Date().getTime() - this.mCurrentRequestTime) / 1000;
        ReleaseLogUtil.e(TAG_RELEASE, "setQuestionSurveyTable spanTimeSecond:", Long.valueOf(abs));
        if (this.mEcologyNpsRequest || abs > SPAN_SECONDS_UPDATE_TIME) {
            QuestionSurveyTable questionSurveyTable = this.mQuestionSurveyTable;
            questionSurveyTable.setTimes(questionSurveyTable.getTimes() + 1);
        }
        this.mCurrentRequestTime = new Date().getTime();
        ReleaseLogUtil.e(TAG_RELEASE, "setQuestionSurveyTable lastTime ", Long.valueOf(j));
        if (!this.mEcologyNpsRequest) {
            this.mQuestionSurveyTable.setLastSurveyTime(j);
        }
        updateQuestionSurveyTable(this.mQuestionSurveyTable);
        ReleaseLogUtil.e(TAG_RELEASE, "Leave setQuestionSurveyTable");
    }

    public void updateQuestionSurveyTable(QuestionSurveyTable questionSurveyTable) {
        LogUtil.a(TAG, "nps Enter updateQuestionSurveyTable");
        if (questionSurveyTable == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "nps mQuestionSurveyTable is null return");
            return;
        }
        QuestionSurveyDataBase questionSurveyDataBase = new QuestionSurveyDataBase();
        LogUtil.a(TAG, "nps mQuestionSurveyTable:", questionSurveyTable.toString());
        questionSurveyDataBase.updateSurveyTableByDeviceId(this, questionSurveyTable);
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave updateQuestionSurveyTable");
    }

    private void loadInitialData(String str) {
        String str2;
        String str3;
        LogUtil.a(TAG, "nps Enter loadInitialData paramNpsUrl:", str);
        int i = this.mRequestTime + 1;
        LogUtil.a(TAG, "nps loadInitialData times:", Integer.valueOf(i));
        if (i < 1 || i > 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "nps loadInitialData (times < 1 || times > 2) return!");
            return;
        }
        getRequestInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("model", this.mDeviceName);
        hashMap.put("firmware", TextUtils.isEmpty(this.mFirmware) ? "firmware" : this.mFirmware);
        String str4 = this.mSn;
        if (str4 == null) {
            str4 = "sn";
        }
        hashMap.put("sn", str4);
        hashMap.put("language", this.mLanguage);
        hashMap.put("os", this.mOs);
        hashMap.put("appID", "3");
        hashMap.put("band", this.mModel);
        hashMap.put("countryCode", this.mCountryCode);
        hashMap.put("times", "" + i);
        ReleaseLogUtil.e(TAG_RELEASE, "nps loadInitialData map");
        if (isConnected() && !HagridNpsManager.getInstance().isWeightDeviceNps()) {
            str2 = "?cVer=100101621";
        } else {
            if (!HagridNpsManager.getInstance().isWeightDeviceNps()) {
                setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", NPS_TRIGGER_ERROR_DEVICE_UNCONNECT);
                LogUtil.a(TAG, "nps loadInitialData device info error");
                return;
            }
            str2 = "?cVer=21319";
        }
        if (str.endsWith("/")) {
            str3 = str + Url.QUESTION_SURVEY_REQUEST_URL + str2 + CHANNEL;
        } else {
            str3 = str + "/secured/CCPC/EN/ccpcnps/getSurveyV2/1" + str2 + CHANNEL;
        }
        LogUtil.a(TAG, "nps loadInitialData requestNpsUrl:", str3, ", request map:", hashMap);
        if (CommonUtil.aa(this.mContext)) {
            setRequestServerBI("1", "");
            executeGetSurveyRequest(str3, hashMap);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave loadInitialData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestServerBI(String str, String str2) {
        LogUtil.a(TAG, "nps setRequestServerBI:", Integer.valueOf(this.mRequestTime));
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("time", Integer.valueOf(this.mRequestTime));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("type", str);
            if ("3".equals(str)) {
                hashMap.put("questionType", getQuestionType(this.mQuestionSurveyId));
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("errorCode", str2);
        }
        hashMap.put("deviceType", jcx.f());
        hashMap.put("deviceMac", jcx.j());
        LogUtil.a(TAG, "setRequestServerBI map:", hashMap.toString());
        ixx.d().d(this.mContext, AnalyticsValue.NPS_GET_QUESTION_1090040.value(), hashMap, 0);
    }

    public String getQuestionType(String str) {
        for (String str2 : NPS_SURVEY_IDS.split(",")) {
            if (str2.equals(str)) {
                return "1";
            }
        }
        return "2";
    }

    private void loadNpsRequestData(String str) {
        String str2;
        LogUtil.a(TAG, "Enter ecology device init request param,paramNpsUrl:", str);
        int i = this.mEcologyRequestTime + 1;
        LogUtil.a(TAG, "Ecology nps load InitialData times:", Integer.valueOf(i));
        if (i < 1 || i > 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "nps loadInitialData (times < 1 || times > 2) return!");
            return;
        }
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        String language = Locale.getDefault().getLanguage();
        HashMap hashMap = new HashMap(16);
        hashMap.put("model", jcx.a());
        hashMap.put("sn", jcx.e());
        hashMap.put("language", language);
        hashMap.put("os", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("appID", jcx.c());
        hashMap.put("countryCode", commonCountryCode);
        hashMap.put("times", "" + i);
        hashMap.put("channel", "100001");
        hashMap.put("cVer", VERSION_NEW_CONTENT);
        ReleaseLogUtil.e(TAG_RELEASE, "nps loadNpsRequestData map");
        if (str.endsWith("/")) {
            str2 = str + Url.QUESTION_SURVEY_REQUEST_URL;
        } else {
            str2 = str + "/secured/CCPC/EN/ccpcnps/getSurveyV2/1";
        }
        LogUtil.a(TAG, "nps loadNpsRequestData requestNpsUrl:", str2);
        if (CommonUtil.aa(this.mContext)) {
            executeGetSurveyRequest(str2, hashMap);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave loadNpsRequestData");
    }

    private void getRequestInfo() {
        QuestionSurveyTable questionSurveyTable;
        QuestionSurveyTable questionSurveyTable2;
        this.mLanguage = Locale.getDefault().getLanguage();
        String f = jcx.f();
        this.mDeviceName = f;
        if (TextUtils.isEmpty(f) && (questionSurveyTable2 = this.mQuestionSurveyTable) != null) {
            this.mDeviceName = questionSurveyTable2.getDeviceType();
        }
        this.mFirmware = jcx.i();
        this.mOs = "" + Build.VERSION.SDK_INT;
        String deviceId = getDeviceId(jcx.j());
        this.mSn = deviceId;
        if (TextUtils.isEmpty(deviceId) && (questionSurveyTable = this.mQuestionSurveyTable) != null) {
            this.mSn = questionSurveyTable.getDeviceId();
        }
        this.mModel = jcx.g();
        this.mCountryCode = jcx.d();
    }

    public void setBiNpsEvent(String str, String str2) {
        ReleaseLogUtil.e(TAG_RELEASE, "setBiNpsEvent");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (!this.mEcologyNpsRequest) {
            hashMap.put("deviceType", jcx.f());
            hashMap.put("deviceMac", jcx.j());
            QuestionSurveyDetailResponse detailResponse = getDetailResponse();
            if (detailResponse != null) {
                hashMap.put("questionType", getQuestionType(detailResponse.getSurveyID()));
                hashMap.put("questionTime", Integer.valueOf(detailResponse.getQueryTimes()));
            }
            hashMap.put("from", str2);
        }
        LogUtil.a(TAG, "setBiNpsEvent map:", hashMap.toString());
        ixx.d().d(this.mContext, str, hashMap, 0);
    }

    public void setBiNpsEvent(String str, String str2, String str3) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter setBiNpsEvent");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("errorCode", str3);
        }
        ixx.d().d(this.mContext, str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processGetSurveyResponse() {
        QuestionSurveyDetailResponse questionSurveyDetailResponse = this.mDetailResponse;
        if (questionSurveyDetailResponse == null) {
            return;
        }
        LogUtil.c(TAG, "nps processGetSurveyResponse resCode:", Integer.valueOf(questionSurveyDetailResponse.getResCode()));
        if (this.mDetailResponse.getResCode() == 0) {
            LogUtil.c(TAG, "nps processGetSurveyResponse to get new questionDetail");
            List<QuestionSureyResponse> questions = QuestionCache.getQuestions();
            this.mQuestionList = questions;
            if (questions == null) {
                this.mQuestionList = new ArrayList(16);
            }
            this.mQuestionList.clear();
            if (this.mDetailResponse.getSurveyContent() == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "nps processGetSurveyResponse questionnaireInfo is null");
                return;
            }
            List<QuestionSureyResponse> questions2 = this.mDetailResponse.getSurveyContent().getQuestions();
            if (questions2 == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "nps processGetSurveyResponse questionList is null");
                return;
            }
            setRequestServerBI("3", "");
            this.mQuestionList.addAll(questions2);
            if (this.mQuestionList.isEmpty()) {
                ReleaseLogUtil.d(TAG_RELEASE, "nps processGetSurveyResponse mQuestionList isEmpty");
                return;
            }
            QuestionCache.setQuestions(this.mQuestionList);
            if (DEVICE_NAME_K1.equals(this.mDeviceName) || DEVICE_NAME_K2.equals(this.mDeviceName)) {
                showSelectQuestionDialog();
            } else {
                generateNpsMessage();
            }
            String surveyID = this.mDetailResponse.getSurveyID();
            this.mQuestionSurveyId = surveyID;
            if (surveyID != null) {
                this.mQuestionId = CommonUtil.e(surveyID, -1);
            }
            LogUtil.a(TAG, "nps processGetSurveyResponse mQuestionId:", Integer.valueOf(this.mQuestionId), " mQuestionSurveyId:", this.mQuestionSurveyId);
            setQuestionSurveyTable(getLastTime(this.mDetailResponse.getFirstTime()));
            return;
        }
        processGetSurveyResponseError();
    }

    private long getLastTime(String str) {
        Date f = jec.f(str);
        if (f == null) {
            return 0L;
        }
        LogUtil.c(TAG, "nps processGetSurveyResponse firstDate:", f, " firstTime:", Long.valueOf(f.getTime()));
        return f.getTime();
    }

    private void processGetSurveyResponseError() {
        long j;
        setRequestServerBI("4", String.valueOf(this.mDetailResponse.getResCode()));
        if (this.mDetailResponse.getResCode() == ERROR_CODE) {
            LogUtil.c(TAG, "nps processGetSurveyResponseError errorCode 305003 getFirstTime:", this.mDetailResponse.getFirstTime());
            this.mQuestionSurveyId = "0";
            Date f = jec.f(this.mDetailResponse.getFirstTime());
            if (f != null) {
                LogUtil.c(TAG, "nps processGetSurveyResponseError errorCode 305003, update table firstDate:", f, " firstTime:", Long.valueOf(f.getTime()));
                j = f.getTime();
            } else {
                j = 0;
            }
            setQuestionSurveyTable(j);
            return;
        }
        ReleaseLogUtil.d(TAG_RELEASE, "processGetSurveyResponseError other errorCode");
    }

    private Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Accept", "application/json");
        hashMap.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeDestSiteRequest(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h(TAG, "nps executeDestSiteRequest map is null");
            return;
        }
        LogUtil.a(TAG, "nps executeDestSiteRequest map:", lql.e(map));
        lqi.d().b(str, getHeaders(), lql.e(map), String.class, new ResultCallback<String>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.10
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str2) {
                ReleaseLogUtil.e(HwNpsManager.TAG_RELEASE, "nps executeDestSiteRequest success");
                HwNpsManager.this.responseDestSiteParse(str2);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c(HwNpsManager.TAG_RELEASE, "nps executeDestSiteRequest error");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void responseDestSiteParse(final String str) {
        if (str == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "nps responseDestSiteListener response is null");
        } else {
            TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.11
                @Override // android.os.AsyncTask
                protected Object doInBackground(Object... objArr) {
                    LogUtil.a(HwNpsManager.TAG, "nps responseDestSiteListener executeAsyncTask doInBackground response:", str);
                    try {
                        QuestionDestSiteResponse questionDestSiteResponse = (QuestionDestSiteResponse) new Gson().fromJson(str, HwNpsManager.this.mDestSiteType);
                        HwNpsManager.this.setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "7", "");
                        return questionDestSiteResponse;
                    } catch (JsonSyntaxException unused) {
                        ReleaseLogUtil.c(HwNpsManager.TAG_RELEASE, "nps responseDestSiteListener executeAsyncTask doInBackground JsonSyntaxException");
                        return null;
                    }
                }

                @Override // android.os.AsyncTask
                protected void onPostExecute(Object obj) {
                    if (obj == null) {
                        return;
                    }
                    super.onPostExecute(obj);
                    ReleaseLogUtil.e(HwNpsManager.TAG_RELEASE, "nps responseDestSiteListener executeAsyncTask onPostExecute");
                    if (obj instanceof QuestionDestSiteResponse) {
                        QuestionDestSiteResponse questionDestSiteResponse = (QuestionDestSiteResponse) obj;
                        if (HwNpsManager.this.mIsGoSubmitSurveyState) {
                            HwNpsManager.this.getDestSiteSubmitSurvey(questionDestSiteResponse);
                        } else {
                            HwNpsManager.this.processGetDestSiteResponse(questionDestSiteResponse);
                        }
                    }
                }
            }, new Object[0]);
        }
    }

    private void executeGetSurveyRequest(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h(TAG, "nps executeGetSurveyRequest map is null");
        } else {
            lqi.d().b(str, getHeaders(), lql.e(map), String.class, new ResultCallback<String>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.12
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(String str2) {
                    ReleaseLogUtil.e(HwNpsManager.TAG_RELEASE, "executeGetSurveyRequest nps get survey successful");
                    HwNpsManager.this.responseSurveyParse(str2);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.c(HwNpsManager.TAG_RELEASE, "executeGetSurveyRequest nps get survey errorListener");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void responseSurveyParse(final String str) {
        LogUtil.a(TAG, "nps responseSurveyParse get question successful:", str);
        if (str == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "nps responseSurveyParse response is null");
        } else {
            TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.13
                @Override // android.os.AsyncTask
                protected Object doInBackground(Object... objArr) {
                    Gson gson = new Gson();
                    LogUtil.a(HwNpsManager.TAG, "nps executeAsyncTask responseSurveyParse response:", str);
                    try {
                        HwNpsManager hwNpsManager = HwNpsManager.this;
                        hwNpsManager.mDetailResponse = (QuestionSurveyDetailResponse) gson.fromJson(str, hwNpsManager.mType);
                        HwNpsManager.this.setRequestServerBI("2", "");
                        HwNpsManager.this.setQuestionDetail(str);
                        LogUtil.a(HwNpsManager.TAG, "nps executeAsyncTask mDetailResponse:", HwNpsManager.this.mDetailResponse.toString());
                        return HwNpsManager.this.mDetailResponse;
                    } catch (JsonSyntaxException unused) {
                        ReleaseLogUtil.c(HwNpsManager.TAG_RELEASE, "nps executeAsyncTask json error!");
                        HwNpsManager.this.mDetailResponse = new QuestionSurveyDetailResponse();
                        return null;
                    }
                }

                @Override // android.os.AsyncTask
                protected void onPostExecute(Object obj) {
                    super.onPostExecute(obj);
                    HwNpsManager.this.processGetSurveyResponse();
                }
            }, new Object[0]);
        }
    }

    private void executeSubmiteSurveyRequest(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h(TAG, "nps executeSubmiteSurveyRequest map is null");
        } else {
            lqi.d().b(str, getHeaders(), lql.e(map), String.class, new ResultCallback<String>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.14
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(String str2) {
                    ReleaseLogUtil.e(HwNpsManager.TAG_RELEASE, "executeSubmiteSurveyRequest nps submite successful");
                    HwNpsManager.this.mHwNpsCallback.onSuccess(str2);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.c(HwNpsManager.TAG_RELEASE, "executeSubmiteSurveyRequest nps submite failed");
                    HwNpsManager.this.mHwNpsCallback.onError();
                }
            });
        }
    }

    public String getQuestionDetail() {
        if (!TextUtils.isEmpty(SharedPreferenceManager.b(this.mContext, String.valueOf(10004), KEY_ECOLOGY_NPS_REQUEST))) {
            String b = SharedPreferenceManager.b(this.mContext, String.valueOf(10004), NPS_ECOLOGY_QUESTION_CONTENT_KEY);
            LogUtil.a(TAG, "nps getEcologyQuestionDetail question:", b);
            return b;
        }
        String b2 = SharedPreferenceManager.b(this.mContext, String.valueOf(10004), NPS_QUESTION_CONTENT_KEY);
        LogUtil.a(TAG, "nps getQuestionDetail question:", b2);
        return b2;
    }

    public void setQuestionDetail(String str) {
        StorageParams storageParams = new StorageParams();
        if (this.mEcologyNpsRequest) {
            SharedPreferenceManager.e(this.mContext, String.valueOf(10004), NPS_ECOLOGY_QUESTION_CONTENT_KEY, str, storageParams);
        } else if (this.mDetailResponse.getResCode() == 0) {
            SharedPreferenceManager.e(this.mContext, String.valueOf(10004), NPS_QUESTION_CONTENT_KEY, str, storageParams);
        } else {
            LogUtil.h(TAG, "nps setQuestionDetail ResCode:", Integer.valueOf(this.mDetailResponse.getResCode()));
        }
        LogUtil.a(TAG, "nps setQuestionDetail question:", str);
    }

    public void submitSurveyQuestion(String str, HwNpsCallback hwNpsCallback) {
        LogUtil.a(TAG, "nps Enter submitSurveyQuestion!");
        if (str != null && !str.isEmpty()) {
            this.mIsGoSubmitSurveyState = true;
            this.mSubmitAnswers = str;
            this.mHwNpsCallback = hwNpsCallback;
            requestGetDestSite();
        }
        ReleaseLogUtil.e(TAG_RELEASE, "nps Leave submitSurveyQuestion!");
    }

    public void setIsGoSubmitSurveyState(boolean z) {
        this.mIsGoSubmitSurveyState = z;
    }

    private String getMobileCountryCode(Context context) {
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        String[] stringArray = context.getResources().getStringArray(R.array._2130968633_res_0x7f040039);
        LogUtil.a(TAG, "getMobileCountryCode stringCountryId:", commonCountryCode);
        String str = "";
        if (stringArray == null) {
            return "";
        }
        int length = stringArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String[] split = stringArray[i].split(",");
            if (split.length > 1 && split[1].trim().equals(commonCountryCode.trim())) {
                str = split[0];
                break;
            }
            i++;
        }
        LogUtil.a(TAG, "getMobileCountryCode resourceCountryCode:", str);
        return str;
    }

    private String getDeviceId(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getDeviceId wearMacString is null return!");
            return "";
        }
        if ("460".equals(getMobileCountryCode(this.mContext))) {
            ReleaseLogUtil.e(TAG_RELEASE, "getDeviceId in China!");
            return str;
        }
        LogUtil.a(TAG, "getDeviceId in Overseas!");
        HwEncryptUtil c = HwEncryptUtil.c(this.mContext);
        String b = c != null ? c.b("09F98935DF23B3E011F5638614670662IrzLoccccR72B/H4EI3GKB6ny7lTZGH7IB4hQWa2qra9LliDA6e9/qgL/9yUjVL0") : "";
        return !TextUtils.isEmpty(b) ? hmacSha256(str, b) : "";
    }

    private String hmacSha256(String str, String str2) {
        LogUtil.c(TAG, "nps Enter hmacSha256");
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            byte[] bytes2 = str.getBytes("UTF-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "HMACSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return URLEncoder.encode(Base64.encodeToString(mac.doFinal(bytes2), 2), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            ReleaseLogUtil.c(TAG_RELEASE, "UnsupportedEncodingException");
            return "";
        } catch (InvalidKeyException unused2) {
            ReleaseLogUtil.c(TAG_RELEASE, "InvalidKeyException");
            return "";
        } catch (NoSuchAlgorithmException unused3) {
            ReleaseLogUtil.c(TAG_RELEASE, "NoSuchAlgorithmException");
            return "";
        }
    }

    private String getSecretDeviceIdSha256(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "deviceId is null return!");
            return "";
        }
        HwEncryptUtil c = HwEncryptUtil.c(BaseApplication.getContext());
        String b = c != null ? c.b("09F98935DF23B3E011F5638614670662IrzLoccccR72B/H4EI3GKB6ny7lTZGH7IB4hQWa2qra9LliDA6e9/qgL/9yUjVL0") : "";
        return !TextUtils.isEmpty(b) ? hmacSha256(str, b) : "";
    }

    public int setSharedPreference(String str, String str2) {
        return SharedPreferenceManager.e(this.mContext, String.valueOf(getModuleId()), str, str2, (StorageParams) null);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public String getSharedPreference(String str) {
        return SharedPreferenceManager.b(this.mContext, String.valueOf(getModuleId()), str);
    }

    public boolean isShowToDo() {
        return isShowToDo(getNpsCloudConfig());
    }

    private boolean isShowToDo(rbj rbjVar) {
        String sharedPreference = getSharedPreference(KEY_NPS_SHOW);
        LogUtil.a(TAG, "isShowToDo KEY_NPS_SHOW value: ", sharedPreference);
        if (!HagridNpsManager.getInstance().isWeightDeviceNps() && isFilterWearNpsByClould()) {
            return false;
        }
        if (TextUtils.isEmpty(sharedPreference)) {
            ReleaseLogUtil.e(TAG_RELEASE, "isShowToDo false, deviceMac empty");
            return false;
        }
        if ((isConnected() || HagridNpsManager.getInstance().isBindWifiDevice()) && jcx.j().equals(sharedPreference)) {
            if (rbjVar == null) {
                rbjVar = getNpsCloudConfig();
            }
            long abs = Math.abs(System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), getSharedPreference(MNP_SHOW_TIME)));
            int intValue = rbjVar.i().intValue();
            Object[] objArr = new Object[6];
            objArr[0] = "isShowToDo showTime: ";
            objArr[1] = Long.valueOf(abs);
            objArr[2] = " residenceDuration : ";
            objArr[3] = Integer.valueOf(intValue);
            objArr[4] = " isShowToDo ";
            long j = intValue * 86400000;
            objArr[5] = Boolean.valueOf(abs < j);
            ReleaseLogUtil.e(TAG_RELEASE, objArr);
            if (abs < j) {
                ReleaseLogUtil.e(TAG_RELEASE, "isShowToDo true");
                return true;
            }
            if (DeviceNpsDelayUtils.isDelayNps(jcx.k())) {
                setSharedPreference(MNP_SHOW_TIME, String.valueOf(System.currentTimeMillis()), null);
                DeviceNpsDelayUtils.updateNpsDelayStatus(true);
                QuestionSurveyTable questionSurveyTable = getQuestionSurveyTable();
                if (questionSurveyTable == null) {
                    setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", NPS_TRIGGER_ERROR_QUESTION_TABLE);
                    ReleaseLogUtil.d(TAG_RELEASE, "isShowToDo questionSurveyTable is null");
                    return false;
                }
                questionSurveyTable.setLastSurveyTime(new Date().getTime());
                updateQuestionSurveyTable(questionSurveyTable);
                ReleaseLogUtil.e(TAG_RELEASE, "isShowToDo true, cause delay");
                return true;
            }
        }
        ReleaseLogUtil.e(TAG_RELEASE, "isShowToDo false");
        return false;
    }

    private rbj getDeviceNpsInfoBySp() {
        List<rbj> list;
        String sharedPreference = getSharedPreference(NPS_CLOUD_CONFIG);
        String k = jcx.k();
        if (TextUtils.isEmpty(sharedPreference)) {
            ReleaseLogUtil.e(TAG_RELEASE, "certifiedModel == null");
            return null;
        }
        ReleaseLogUtil.e(TAG, "npsCloudConfig = ", sharedPreference, " certifiedModel = ", k);
        try {
            list = (List) nrv.c(sharedPreference, new TypeToken<List<rbj>>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.15
            }.getType());
        } catch (JsonParseException e) {
            ReleaseLogUtil.d(TAG_RELEASE, "getDeviceNpsInfoBySp: ", LogAnonymous.b((Throwable) e));
        }
        if (koq.b(list)) {
            ReleaseLogUtil.e(TAG_RELEASE, "npsInfos == null");
            return null;
        }
        for (rbj rbjVar : list) {
            if (rbjVar.a().contains(k)) {
                return rbjVar;
            }
        }
        return null;
    }

    private rbj getNpsCloudConfig() {
        rbj deviceNpsInfoBySp = getDeviceNpsInfoBySp();
        if (deviceNpsInfoBySp != null) {
            ReleaseLogUtil.e(TAG, "npsInfo ", deviceNpsInfoBySp.toString());
            return deviceNpsInfoBySp;
        }
        return setDefaultDeviceNpsInfo();
    }

    public void loadCloudNpsConfigList() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HwNpsManager.this.m831xc5fceed0();
            }
        });
    }

    /* renamed from: lambda$loadCloudNpsConfigList$0$com-huawei-ui-main-stories-nps-interactors-HwNpsManager, reason: not valid java name */
    /* synthetic */ void m831xc5fceed0() {
        rbi b = new rbi.c().b();
        ReleaseLogUtil.e(TAG, "loadNpsCloudConfig ", b);
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        lqi.d().b(b.d(), healthDataCloudFactory.getHeaders(), lql.b(healthDataCloudFactory.getBody(b)), rbp.class, new ResultCallback<rbp>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.16
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(rbp rbpVar) {
                ReleaseLogUtil.e(HwNpsManager.TAG, "getNpsConfig: ", rbpVar.getResultCode(), " npsInfosRsp = ", rbpVar);
                if (koq.c(rbpVar.a())) {
                    HwNpsManager.this.setSharedPreference(HwNpsManager.NPS_CLOUD_CONFIG, nrv.a(rbpVar.a()));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c(HwNpsManager.TAG, "onFailure: ", LogAnonymous.b(th));
            }
        });
    }

    private rbj setDefaultDeviceNpsInfo() {
        rbj rbjVar = new rbj();
        rbjVar.c(new ArrayList(NO_REMIND_DEVICE_CERT_MODEL));
        if (isHideDeviceByCertModel(jcx.k(), rbjVar)) {
            rbjVar.e(0);
            rbjVar.d(0);
        } else {
            rbjVar.e(1);
            rbjVar.d(0);
        }
        if (CommonUtil.bh()) {
            rbjVar.b((Integer) 15);
        } else {
            rbjVar.b((Integer) 10);
        }
        if (Utils.o()) {
            rbjVar.a("0:00");
            rbjVar.b("24:00");
        }
        return rbjVar;
    }

    public int getShowDay() {
        return (int) (Math.abs(System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), getSharedPreference(MNP_SHOW_TIME))) / 86400000);
    }

    public boolean isShowEcologyNps() {
        if (TextUtils.isEmpty(getSharedPreference(KEY_NPS_SHOW))) {
            return false;
        }
        String sharedPreference = getSharedPreference(KEY_ECOLOGY_NPS_REQUEST);
        return !TextUtils.isEmpty(sharedPreference) && sharedPreference.equals("true") && Math.abs(System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), getSharedPreference(MNP_SHOW_TIME))) < FIFTEEN_DAYS;
    }

    private boolean isConnected() {
        DeviceInfo d = jpt.d(TAG);
        boolean z = false;
        boolean z2 = (d == null && (d = jpu.e(TAG)) == null) ? false : true;
        if (d == null) {
            d = getFollowedDeviceInfo();
            z2 = d != null;
            z = z2;
        }
        if (z2) {
            if (TextUtils.isEmpty(jcx.j()) || z) {
                jcx.i(d.getSoftVersion());
                setNpsIdentify(d);
            }
            if (z) {
                setFollowedDeviceInfo(d);
            }
        }
        ReleaseLogUtil.e(TAG_RELEASE, "isConnected isConnected is ", Boolean.valueOf(z2));
        return z2;
    }

    private void setFollowedDeviceInfo(DeviceInfo deviceInfo) {
        jcx.f(deviceInfo.getDeviceModel());
        jcx.g(deviceInfo.getCertModel());
        jcx.j(deviceInfo.getDeviceModel());
    }

    public DeviceInfo getFollowedDeviceInfo() {
        LogUtil.a(TAG, "enter getFollowedDeviceInfo");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.FOLLOWED_DEVICES, null, TAG);
        if (deviceList == null || deviceList.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                arrayList.add(deviceInfo);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        Collections.sort(arrayList, new Comparator<DeviceInfo>() { // from class: com.huawei.ui.main.stories.nps.interactors.HwNpsManager.17
            @Override // java.util.Comparator
            public int compare(DeviceInfo deviceInfo2, DeviceInfo deviceInfo3) {
                return deviceInfo2.getSecurityDeviceId().compareTo(deviceInfo3.getSecurityDeviceId());
            }
        });
        return (DeviceInfo) arrayList.get(0);
    }

    public void setNpsIdentify(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        if (!TextUtils.isEmpty(udidFromDevice)) {
            jcx.h(udidFromDevice);
            return;
        }
        if (Utils.o() || deviceInfo.getDeviceIdType() == 1) {
            jcx.h(getInstance().getSecretDeviceIdSha256(deviceInfo.getSecurityDeviceId()));
        } else {
            jcx.h(deviceInfo.getDeviceIdentify());
        }
        String deviceUdid = deviceInfo.getDeviceUdid();
        if (deviceInfo.getProductType() < 34 || TextUtils.isEmpty(deviceUdid)) {
            return;
        }
        jcx.h(deviceUdid);
    }

    public void startEcologyDeviceNps() {
        String sharedPreference = getSharedPreference(KEY_NPS_LAST_TIME);
        try {
            if (!TextUtils.isEmpty(sharedPreference) && Long.valueOf((System.currentTimeMillis() - Long.parseLong(sharedPreference)) / 1000).longValue() < 604800) {
                LogUtil.a(TAG, "nps only once a day");
            } else if (!TextUtils.isEmpty(getSharedPreference(KEY_NPS_SHOW))) {
                LogUtil.a(TAG, "last nps survey is not finished");
            } else {
                handleEcologyDeviceList();
            }
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "time formatException ");
        }
    }

    private void handleEcologyDeviceList() {
        int times;
        Long valueOf;
        List<cjv> bondEcologyDevice = getBondEcologyDevice();
        if (koq.b(bondEcologyDevice)) {
            LogUtil.a(TAG, "no ecology device");
            return;
        }
        for (cjv cjvVar : bondEcologyDevice) {
            String deviceId = getDeviceId(cjvVar.FT_().getAsString("uniqueId"));
            String asString = cjvVar.FT_().getAsString("productId");
            if (!TextUtils.isEmpty(asString) && !TextUtils.isEmpty(deviceId)) {
                QuestionSurveyTable surveyTableByDevice = getSurveyTableByDevice(deviceId);
                if (surveyTableByDevice == null) {
                    insertDeviceToDb(cjvVar);
                    valueOf = Long.valueOf(cjvVar.FT_().getAsString(EventMonitorRecord.ADD_TIME));
                    times = 0;
                } else {
                    times = surveyTableByDevice.getTimes();
                    valueOf = Long.valueOf(surveyTableByDevice.getLastSurveyTime());
                }
                if (isTimeToNpsRequest(times, valueOf.longValue())) {
                    this.mEcologyNpsRequest = true;
                    this.mEcologyRequestTime = times;
                    this.mQuestionSurveyTable = surveyTableByDevice;
                    this.mIsGoSubmitSurveyState = false;
                    jcx.c(deviceId);
                    jcx.e(asString);
                    jcx.b(EcologyNpsUtil.getEcologyModel(asString));
                    jcx.a(String.valueOf(cjvVar.a()));
                    requestGetDestSite();
                    return;
                }
            }
        }
    }

    public List<cjv> getBondEcologyDevice() {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList<ContentValues> f = ceo.d().f();
        if (f != null && f.size() != 0) {
            Iterator<ContentValues> it = f.iterator();
            while (it.hasNext()) {
                ContentValues next = it.next();
                String asString = next.getAsString("productId");
                String deviceId = getDeviceId(next.getAsString("uniqueId"));
                String asString2 = next.getAsString(EventMonitorRecord.ADD_TIME);
                String asString3 = next.getAsString("kitUuid");
                if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(deviceId)) {
                    LogUtil.h(TAG, "initDeviceList : productId or deviceIdentify is empty");
                } else {
                    if (isWearNpsTime(asString3)) {
                        LogUtil.a(TAG, "is wear device nps time");
                        return new ArrayList();
                    }
                    if (isScaleNpsTime(asString, deviceId)) {
                        LogUtil.a(TAG, "is scale device nps time");
                        return new ArrayList();
                    }
                    int deviceNpsId = EcologyNpsUtil.getDeviceNpsId(asString);
                    if (deviceNpsId != 0) {
                        dcz d = ResourceManager.e().d(asString);
                        cjv cjvVar = new cjv();
                        cjvVar.c(d);
                        cjvVar.FU_(next);
                        setCompareTime(cjvVar, asString2);
                        cjvVar.a(deviceNpsId);
                        arrayList2.add(cjvVar);
                    }
                }
            }
            if (koq.c(arrayList2)) {
                arrayList.addAll(arrayList2);
            }
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    private void setCompareTime(cjv cjvVar, String str) {
        try {
            cjvVar.a(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "addDeviceTime formatException ");
        }
    }

    private boolean isWearNpsTime(String str) {
        if (TextUtils.isEmpty(str) || !"0".equals(str)) {
            return false;
        }
        setNpsIdentify(jpt.d(TAG));
        QuestionSurveyTable surveyTableByDevice = getSurveyTableByDevice(getDeviceId(jcx.j()));
        if (surveyTableByDevice != null) {
            return isJudgeGetSurveyTimeArrive(surveyTableByDevice.getTimes(), surveyTableByDevice);
        }
        return false;
    }

    private boolean isScaleNpsTime(String str, String str2) {
        QuestionSurveyTable surveyTableByDevice;
        if (isConnected() || !cpa.ad(str) || (surveyTableByDevice = getSurveyTableByDevice(str2)) == null || !isJudgeGetSurveyTimeArrive(surveyTableByDevice.getTimes(), surveyTableByDevice)) {
            return false;
        }
        LogUtil.a(TAG, "isScaleNps");
        return true;
    }

    private boolean isTimeToNpsRequest(int i, long j) {
        LogUtil.a(TAG, "ecology nps time judge");
        int i2 = i + 1;
        if (i2 >= 1 && i2 <= 2) {
            LogUtil.a(TAG, "nps isJudgeGetSurveyTimeArrive GetSurvey times:", Integer.valueOf(i2));
            boolean isFirstTimeNps = i2 == 1 ? isFirstTimeNps(j) : false;
            if (!isFirstTimeNps) {
                isFirstTimeNps = isSecondTimeNps(j);
            }
            ReleaseLogUtil.e(TAG_RELEASE, "nps Leave isJudgeGetSurveyTimeArrive");
            return isFirstTimeNps;
        }
        ReleaseLogUtil.d(TAG_RELEASE, "nps isJudgeGetSurveyTimeArrive GetSurvey times is off limits!");
        return false;
    }

    private boolean isFirstTimeNps(long j) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter first time nps compare");
        long currentTimeMillis = (System.currentTimeMillis() - j) / 1000;
        boolean z = currentTimeMillis > SPAN_DAY_SECOND_THIRTY && currentTimeMillis < SPAN_DAY_SECOND_SIXTY;
        LogUtil.a(TAG, "isFirstTimeNps ： ", Boolean.valueOf(z));
        return z;
    }

    private boolean isSecondTimeNps(long j) {
        ReleaseLogUtil.e(TAG_RELEASE, "isSecondTimeNps");
        long currentTimeMillis = (System.currentTimeMillis() - j) / 1000;
        boolean z = currentTimeMillis > SPAN_DAY_SECOND_ONE_HUNDRED_EIGHTY && currentTimeMillis < SPAN_DAY_SECOND_TWO_HUNDRED_TEN;
        LogUtil.a(TAG, "isSecondTimeNps :", Boolean.valueOf(z));
        return z;
    }

    private boolean isFilterWearNpsByClould() {
        NpsUserShowController npsUserShowController = NpsUserShowController.getInstance(BaseApplication.getContext());
        String k = jcx.k();
        boolean isFilterDeviceNpsByType = npsUserShowController.isFilterDeviceNpsByType(k);
        boolean isFilterWithPhoneForDevicesNps = npsUserShowController.isFilterWithPhoneForDevicesNps();
        ReleaseLogUtil.e(TAG_RELEASE, "isFilterWearNpsByClould FilterByRatio is ", Boolean.valueOf(isFilterDeviceNpsByType), " , FilterWithPhoneForDevicesNps is ", Boolean.valueOf(isFilterWithPhoneForDevicesNps), ", deviceCertMode is ", k);
        return isFilterDeviceNpsByType || isFilterWithPhoneForDevicesNps;
    }
}
