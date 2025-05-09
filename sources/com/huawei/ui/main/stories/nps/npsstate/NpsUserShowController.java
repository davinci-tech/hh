package com.huawei.ui.main.stories.nps.npsstate;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.messageremind.receiver.SportReminderReceiver;
import com.huawei.ui.main.stories.nps.component.ActiveConfigModel;
import com.huawei.ui.main.stories.nps.component.NpsApi;
import com.huawei.ui.main.stories.nps.component.NpsConfig;
import com.huawei.ui.main.stories.nps.component.NpsSharePreferenceUtils;
import com.huawei.ui.main.stories.nps.harid.HagridNpsManager;
import defpackage.bzs;
import defpackage.cun;
import defpackage.ixx;
import defpackage.jdh;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsn;
import defpackage.sqa;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* loaded from: classes7.dex */
public class NpsUserShowController {
    private static final long FIFTEEN_DAYS_MILLISECONDS = 1296000000;
    private static final String IS_FILTER_WITH_NO_STEPS_SP_KEY = "IS_FILTER_WITH_NO_STEPS_SP_KEY";
    private static final Object LOCK = new Object();
    private static final int NEED_SURVEY = 1;
    private static final long NINETY_DAYS_MILLISECONDS = 7776000000L;
    private static final int NOTIFICATION_ID = 20230821;
    private static final String NPS_ENTER_FALSE = "false";
    private static final String NPS_ENTER_TRUE = "true";
    private static final String NPS_SEND_TIME = "npsSendTime";
    private static final long ONE_DAY_TIME = 86400000;
    private static final long SEVENTY_FIVE_DAYS_MILLISECONDS = 6480000000L;
    private static final int SURVEY_TIME_ONE = 1;
    private static final int SURVEY_TIME_THREE = 3;
    private static final int SURVEY_TIME_TWO = 2;
    private static final String TAG = "NpsUserShowController";
    private static final long THIRTY_DAYS_MILLISECONDS = 2592000000L;
    private static final int UNNEED_SURVEY = 0;
    private static volatile String sConnectedWhitelistDevices = "";
    private static volatile NpsUserShowController sNpsUserShowController;
    private static String[] sSupportOverseasCountryCode;
    private Context mContext;
    private int mSurveyTime;

    private NpsUserShowController(Context context) {
        this.mContext = context;
    }

    public static NpsUserShowController getInstance(Context context) {
        NpsUserShowController npsUserShowController;
        synchronized (NpsUserShowController.class) {
            if (sNpsUserShowController == null) {
                synchronized (LOCK) {
                    if (sNpsUserShowController == null) {
                        sNpsUserShowController = new NpsUserShowController(context.getApplicationContext());
                    }
                }
            }
            npsUserShowController = sNpsUserShowController;
        }
        return npsUserShowController;
    }

    private boolean isNewBecomeOld(Context context, String str) {
        return NpsSharePreferenceUtils.getIsNewBecomeOld(context, str);
    }

    private void setNewBecomeOld(Context context, String str, boolean z) {
        NpsSharePreferenceUtils.setIsNewBecomeOld(context, str, z);
    }

    public NpsConfig readNpsConfig() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(BaseApplication.getContext().getFilesDir().getCanonicalPath());
            sb.append(File.separator);
            sb.append("lightcloud");
            sb.append(File.separator);
            sb.append(Utils.o() ? "servicefwo" : "servicefw");
            String t = CommonUtil.t(sb.toString() + File.separator + "NPSConfig.txt");
            LogUtil.d(TAG, "readNpsConfig npsStr: NPSConfig.txt");
            try {
                return (NpsConfig) HiJsonUtil.e(t, NpsConfig.class);
            } catch (JsonSyntaxException e) {
                LogUtil.e(TAG, "readNpsConfig JsonSyntaxException, e is ", e.getMessage());
                return null;
            }
        } catch (IOException e2) {
            LogUtil.e(TAG, "getCanonicalPath suffix invalid,error:", e2.getMessage());
            return null;
        }
    }

    private boolean isTheNewUserHadConfigInfo(String str) {
        return !TextUtils.isEmpty(NpsSharePreferenceUtils.getNewUserNativeConfigStr(this.mContext, str));
    }

    private void generateNewUserConfigInfo(String str) {
        NativeConfigBean nativeConfigBean = new NativeConfigBean();
        long currentTimeMillis = System.currentTimeMillis();
        long j = FIFTEEN_DAYS_MILLISECONDS + currentTimeMillis;
        nativeConfigBean.setHuidStr(str);
        nativeConfigBean.setShowTime(j);
        nativeConfigBean.setExpireTime(3888000000L + currentTimeMillis);
        nativeConfigBean.setNeedSurvey(true);
        NativeConfigBean nativeConfigBean2 = new NativeConfigBean();
        long j2 = NINETY_DAYS_MILLISECONDS + currentTimeMillis;
        nativeConfigBean2.setHuidStr(str);
        nativeConfigBean2.setShowTime(j2);
        nativeConfigBean.setExpireTime(currentTimeMillis + 10368000000L);
        nativeConfigBean2.setNeedSurvey(true);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        LogUtil.d(TAG, " new user first survey time: ", simpleDateFormat.format(Long.valueOf(j)), " second survey time: ", simpleDateFormat.format(Long.valueOf(j2)));
        HashMap hashMap = new HashMap();
        hashMap.put(1, nativeConfigBean);
        hashMap.put(2, nativeConfigBean2);
        NativeConfigMap nativeConfigMap = new NativeConfigMap();
        nativeConfigMap.setNewUserConfig(hashMap);
        NpsSharePreferenceUtils.setNewUserNativeConfig(this.mContext, str, HiJsonUtil.e(nativeConfigMap));
    }

    public Map<Integer, NativeConfigBean> getNewUserConfigInfo(String str) {
        return NpsSharePreferenceUtils.getNewUserNativeConfig(this.mContext, str).getNewUserConfig();
    }

    private void saveNewUserConfigInfo(String str, int i, NativeConfigBean nativeConfigBean) {
        Map<Integer, NativeConfigBean> newUserConfigInfo = getNewUserConfigInfo(str);
        newUserConfigInfo.put(Integer.valueOf(i), nativeConfigBean);
        NativeConfigMap nativeConfigMap = new NativeConfigMap();
        nativeConfigMap.setNewUserConfig(newUserConfigInfo);
        NpsSharePreferenceUtils.setNewUserNativeConfig(this.mContext, str, HiJsonUtil.e(nativeConfigMap));
    }

    private int getNeedSurveyBatchs(String str) {
        Map<Integer, NativeConfigBean> newUserConfigInfo = getNewUserConfigInfo(str);
        if (newUserConfigInfo == null) {
            return 0;
        }
        NativeConfigBean nativeConfigBean = newUserConfigInfo.get(1);
        if (nativeConfigBean != null && nativeConfigBean.isNeedSurvey()) {
            LogUtil.d(TAG, "getNeedSurveyBatchs() return 1 bean.isNeedSurvey: ", Boolean.valueOf(nativeConfigBean.isNeedSurvey()));
            return 1;
        }
        NativeConfigBean nativeConfigBean2 = newUserConfigInfo.get(2);
        if (nativeConfigBean2 == null || !nativeConfigBean2.isNeedSurvey()) {
            return 0;
        }
        LogUtil.d(TAG, "getNeedSurveyBatchs() return 2 bean.isNeedSurvey: ", Boolean.valueOf(nativeConfigBean2.isNeedSurvey()));
        return 2;
    }

    private boolean isNewUser(String str) {
        return "done".equals(str);
    }

    private boolean isTheOldUserHadConfigInfo(String str) {
        return NpsSharePreferenceUtils.getOldUserNativeConfig(this.mContext, str).isHasNativeConfig();
    }

    private void generateOldUserConfigInfo(NpsConfig npsConfig, String str, String str2) {
        NativeConfigBean nativeConfigBean = new NativeConfigBean();
        int[] iArr = npsConfig.getNeedInvestigate().get(Integer.valueOf(generateNumberFromHuid(str)));
        int d = CommonUtil.d(BaseApplication.getContext());
        String healthVersion = npsConfig.getHealthVersion();
        LogUtil.d(TAG, "generateOldUserConfigInfo HealthVersion: ", healthVersion);
        if (TextUtils.isEmpty(healthVersion)) {
            healthVersion = "0";
        }
        if (iArr != null && d >= nsn.e(healthVersion)) {
            nativeConfigBean.setHuidStr(str2);
            nativeConfigBean.setNeedSurvey(true);
            try {
                long currentTimeMillis = System.currentTimeMillis() + ((iArr[0] + (SecureRandom.getInstanceStrong().nextInt(iArr[1] - iArr[0]) - 1)) * 86400000);
                boolean isNewBecomeOld = isNewBecomeOld(this.mContext, str2);
                LogUtil.d(TAG, "is new turn to old user? ", Boolean.valueOf(isNewBecomeOld));
                if (isNewBecomeOld) {
                    currentTimeMillis += NINETY_DAYS_MILLISECONDS;
                    setNewBecomeOld(this.mContext, str2, false);
                }
                LogUtil.d(TAG, "the old user config survey time is: ", new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(Long.valueOf(currentTimeMillis)));
                nativeConfigBean.setShowTime(currentTimeMillis);
                nativeConfigBean.setExpireTime(currentTimeMillis + THIRTY_DAYS_MILLISECONDS);
            } catch (NoSuchMethodError | NoSuchAlgorithmException e) {
                nativeConfigBean.setNeedSurvey(false);
                LogUtil.e(TAG, "generateOldUserConfigInfo: exception -> " + e.getMessage());
            }
        } else {
            nativeConfigBean.setNeedSurvey(false);
        }
        nativeConfigBean.setHasNativeConfig(true);
        NpsSharePreferenceUtils.setOldUserNativeConfig(this.mContext, str, HiJsonUtil.e(nativeConfigBean));
    }

    private int generateNumberFromHuid(String str) {
        char[] charArray = str.toCharArray();
        for (int length = charArray.length - 1; length >= 0; length--) {
            String valueOf = String.valueOf(charArray[length]);
            if (nsn.c(valueOf)) {
                return nsn.e(valueOf);
            }
        }
        return 0;
    }

    public NativeConfigBean getOldUserConfigInfo(String str) {
        return NpsSharePreferenceUtils.getOldUserNativeConfig(this.mContext, str);
    }

    private void saveOldUserConfigInfo(String str, String str2) {
        NpsSharePreferenceUtils.setOldUserNativeConfig(this.mContext, str, str2);
    }

    private DeviceInfo getConnectedOrPairedDeviceInfo() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, TAG);
        if (koq.b(deviceList)) {
            LogUtil.c(TAG, "getCurrentDeviceInfo() deviceInfoList is empty");
            return null;
        }
        LogUtil.d(TAG, "getCurrentDeviceInfo() deviceInfoList.size() = ", Integer.valueOf(deviceList.size()));
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo == null) {
                LogUtil.c(TAG, "deviceInfo is null");
            } else if (deviceInfo.getDeviceActiveState() == 1 && (deviceInfo.getDeviceConnectState() == 2 || deviceInfo.getDeviceConnectState() == 3)) {
                return deviceInfo;
            }
        }
        LogUtil.d(TAG, "getCurrentDeviceInfo() deviceInfo's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
        return null;
    }

    public int getSurveyTime() {
        return this.mSurveyTime;
    }

    private boolean isCheckShowNps(NativeConfigBean nativeConfigBean, String str, int i) {
        if (nativeConfigBean == null) {
            LogUtil.c(TAG, "isCheckShowNps configBean == null");
            return false;
        }
        if (!nativeConfigBean.isNeedSurvey()) {
            LogUtil.d(TAG, "isCheckShowNps the survey is not need surveyed");
            return false;
        }
        long expireTime = nativeConfigBean.getExpireTime();
        if (expireTime == 0) {
            expireTime = nativeConfigBean.getShowTime() + THIRTY_DAYS_MILLISECONDS;
            nativeConfigBean.setExpireTime(expireTime);
            setTheNpsEnterUnSee(str, i, nativeConfigBean);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < nativeConfigBean.getShowTime()) {
            LogUtil.c(TAG, "isCheckShowNps the survey time is not achieve!");
            return false;
        }
        if (currentTimeMillis <= expireTime) {
            LogUtil.d(TAG, "isCheckShow show the nps enter: ", true);
            return true;
        }
        LogUtil.d(TAG, "isCheckShow is expired");
        nativeConfigBean.setNeedSurvey(false);
        setTheNpsEnterUnSee(str, i, nativeConfigBean);
        return false;
    }

    private void setTheNpsEnterUnSee(String str, int i, NativeConfigBean nativeConfigBean) {
        if (i == 1 || i == 2) {
            saveNewUserConfigInfo(str, i, nativeConfigBean);
        } else {
            saveOldUserConfigInfo(str, HiJsonUtil.e(nativeConfigBean));
        }
    }

    public void setTheSurveyUnNeeded(int i) {
        NpsConfig readNpsConfig;
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        LogUtil.d(TAG, "setTheSurveyUnNeeded surveyTime: ", Integer.valueOf(i));
        if (TextUtils.isEmpty(accountInfo)) {
            return;
        }
        if (i <= 2 && i > 0) {
            NativeConfigBean nativeConfigBean = getNewUserConfigInfo(accountInfo).get(Integer.valueOf(i));
            LogUtil.d(TAG, "setTheSurveyUnNeeded() newBean.isNeedSurvey: ", Boolean.valueOf(nativeConfigBean.isNeedSurvey()));
            if (i == 2) {
                LogUtil.d(TAG, " surveyTime == 2, new user turn to old user");
                setNewBecomeOld(this.mContext, accountInfo, true);
            }
            nativeConfigBean.setNeedSurvey(false);
            setTheNpsEnterUnSee(accountInfo, i, nativeConfigBean);
        }
        if (i != 3 || (readNpsConfig = readNpsConfig()) == null) {
            return;
        }
        String str = readNpsConfig.getVersion() + accountInfo;
        NativeConfigBean oldUserConfigInfo = getOldUserConfigInfo(str);
        oldUserConfigInfo.setNeedSurvey(false);
        setTheNpsEnterUnSee(str, i, oldUserConfigInfo);
    }

    public boolean isShowNps() {
        if (!isSendNps()) {
            return false;
        }
        boolean isFilterNps = isFilterNps();
        ReleaseLogUtil.e(TAG, "isFilterNps, no show: ", Boolean.valueOf(isFilterNps));
        return !isFilterNps;
    }

    private boolean checkDeviceWhitelist(NpsConfig npsConfig, DeviceInfo deviceInfo) {
        List<String> deviceWhitelist;
        sConnectedWhitelistDevices = "";
        if (npsConfig != null && (deviceWhitelist = npsConfig.getDeviceWhitelist()) != null && !deviceWhitelist.isEmpty()) {
            LogUtil.d(TAG, "checkDeviceWhitelist: deviceWhitelist -> ", deviceWhitelist.toString());
            if (deviceInfo != null) {
                String certModel = deviceInfo.getCertModel();
                LogUtil.d(TAG, "checkDeviceWhitelist: certModel -> ", certModel);
                if (TextUtils.isEmpty(certModel)) {
                    return false;
                }
                Iterator<String> it = deviceWhitelist.iterator();
                while (it.hasNext()) {
                    if (certModel.equalsIgnoreCase(it.next())) {
                        sConnectedWhitelistDevices = certModel;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDeviceBlacklist(NpsConfig npsConfig, DeviceInfo deviceInfo) {
        List<Integer> deviceList;
        if (checkIsEnterWearHome()) {
            return true;
        }
        if (npsConfig != null && (deviceList = npsConfig.getDeviceList()) != null && !deviceList.isEmpty()) {
            LogUtil.d(TAG, "checkDeviceBlacklist: deviceBlacklist -> ", deviceList.toString());
            if (deviceInfo != null) {
                int deviceBluetoothType = deviceInfo.getDeviceBluetoothType();
                int productType = deviceInfo.getProductType();
                return deviceList.contains(Integer.valueOf(deviceBluetoothType)) || productType == -1 || deviceList.contains(Integer.valueOf(productType));
            }
        }
        return false;
    }

    private boolean isInterceptDeviceOrPhoneUser() {
        if (HagridNpsManager.getInstance().isBindWifiDevice()) {
            LogUtil.d(TAG, "isInterceptDeviceOrPhoneUser: isBindWifiDevice");
            return true;
        }
        NpsConfig readNpsConfig = readNpsConfig();
        DeviceInfo connectedOrPairedDeviceInfo = getConnectedOrPairedDeviceInfo();
        if (checkDeviceWhitelist(readNpsConfig, connectedOrPairedDeviceInfo)) {
            boolean isFilterPhoneWithDeviceNps = isFilterPhoneWithDeviceNps();
            LogUtil.d(TAG, "isInterceptDeviceOrPhoneUser: isFilterPhoneWithDeviceNps -> " + isFilterPhoneWithDeviceNps);
            if (isFilterPhoneWithDeviceNps) {
                sConnectedWhitelistDevices = "";
            }
            return isFilterPhoneWithDeviceNps;
        }
        if (checkDeviceBlacklist(readNpsConfig, connectedOrPairedDeviceInfo)) {
            LogUtil.d(TAG, "isInterceptDeviceOrPhoneUser: checkDeviceBlacklist -> true");
            return true;
        }
        boolean isFilterPhoneNps = isFilterPhoneNps();
        LogUtil.d(TAG, "isInterceptDeviceOrPhoneUser: isFilterPhoneNps -> " + isFilterPhoneNps);
        return isFilterPhoneNps;
    }

    private boolean isInterceptedCountry() {
        boolean m = LanguageUtil.m(this.mContext);
        if (!Utils.o()) {
            return !m;
        }
        LogUtil.b(TAG, "isInterceptedCountry: isOversea -> ", true);
        String[] strArr = sSupportOverseasCountryCode;
        if (strArr == null || strArr.length == 0) {
            sSupportOverseasCountryCode = new String[]{"GB", "FR", "DE", "IT", "PL", "ES", "TR"};
        }
        final String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1010);
        if (Arrays.stream(sSupportOverseasCountryCode).anyMatch(new Predicate() { // from class: com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equalsIgnoreCase;
                equalsIgnoreCase = ((String) obj).equalsIgnoreCase(accountInfo);
                return equalsIgnoreCase;
            }
        })) {
            return false;
        }
        LogUtil.b(TAG, "isInterceptedCountry: userCountryCode -> ", accountInfo);
        return true;
    }

    private boolean isSendNps() {
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b(TAG, "isShowNps huidStr is no char: ", false);
            return false;
        }
        if (isInterceptedCountry()) {
            LogUtil.b(TAG, "isShowNps isInterceptedCountry: ", true);
            return false;
        }
        if (isInterceptDeviceOrPhoneUser()) {
            LogUtil.b(TAG, "isShowNps isInterceptDeviceOrPhoneUser: ", true);
            return false;
        }
        Map<Integer, NativeConfigBean> newUserConfigInfo = getNewUserConfigInfo(accountInfo);
        if (newUserConfigInfo != null) {
            if (isCheckShowNps(newUserConfigInfo.get(1), accountInfo, 1)) {
                LogUtil.d(TAG, " NpsUserShowController isShow nps time 1 return true");
                this.mSurveyTime = 1;
                return true;
            }
            if (isCheckShowNps(newUserConfigInfo.get(2), accountInfo, 2)) {
                LogUtil.d(TAG, " NpsUserShowController isShow nps time 2 return true");
                this.mSurveyTime = 2;
                return true;
            }
        }
        NpsConfig readNpsConfig = readNpsConfig();
        if (readNpsConfig == null) {
            LogUtil.c(TAG, "isShowNps the config == null ");
            return false;
        }
        if (!isCheckShowNps(getOldUserConfigInfo(readNpsConfig.getVersion() + accountInfo), readNpsConfig.getVersion() + accountInfo, 3)) {
            return false;
        }
        this.mSurveyTime = 3;
        LogUtil.d(TAG, "isShowNps NpsUserShowController isShow nps time 3 return true");
        return true;
    }

    private boolean checkIsEnterWearHome() {
        String b = SharedPreferenceManager.b(this.mContext, String.valueOf(10099), "key_ui_nps_enter_wear_home");
        if ("true".equals(b)) {
            LogUtil.c(TAG, "isShowNps IsEnterWearHome: NPS_ENTER_TRUE");
            return true;
        }
        if (!NPS_ENTER_FALSE.equals(b)) {
            isHaveStepsFromWear();
        }
        LogUtil.d(TAG, "isShowNps IsEnterWearHome: NPS_ENTER_FALSE");
        return false;
    }

    public String getHuidStr() {
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        return TextUtils.isEmpty(accountInfo) ? "" : accountInfo;
    }

    public void threadExecuteNpsModule() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                NpsUserShowController.this.m833xc342a6b();
            }
        });
    }

    /* renamed from: lambda$threadExecuteNpsModule$1$com-huawei-ui-main-stories-nps-npsstate-NpsUserShowController, reason: not valid java name */
    /* synthetic */ void m833xc342a6b() {
        if (isInterceptedCountry()) {
            LogUtil.b(TAG, "threadExecuteNpsModule isInterceptedCountry: ", true);
        } else {
            executeNpsModule();
        }
    }

    private void executeNpsModule() {
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        LogUtil.b(TAG, "executeNpsModule huidStr: ", accountInfo);
        if (TextUtils.isEmpty(accountInfo)) {
            return;
        }
        boolean isTheNewUserHadConfigInfo = isTheNewUserHadConfigInfo(accountInfo);
        LogUtil.d(TAG, "NpsUserShowController isTheNewUserHadConfigInfo: ", Boolean.valueOf(isTheNewUserHadConfigInfo));
        if (!isTheNewUserHadConfigInfo) {
            String userType = NpsSharePreferenceUtils.getUserType(BaseApplication.getContext(), "NEWBIE" + accountInfo);
            LogUtil.d(TAG, "NpsUserShowController get the user type: ", userType);
            if (TextUtils.isEmpty(userType)) {
                return;
            }
            boolean isNewUser = isNewUser(userType);
            LogUtil.d(TAG, "executeNpsModule is new user? ", Boolean.valueOf(isNewUser));
            if (!isNewUser) {
                LogUtil.d(TAG, "executeNpsModule gotoTheOldUserConfig");
                gotoTheOldUserConfig(accountInfo);
                return;
            } else {
                LogUtil.d(TAG, "executeNpsModule generateNewUserConfigInfo");
                generateNewUserConfigInfo(accountInfo);
            }
        }
        int needSurveyBatchs = getNeedSurveyBatchs(accountInfo);
        LogUtil.d(TAG, "executeNpsModule get the new user batch: ", Integer.valueOf(needSurveyBatchs));
        if (needSurveyBatchs == 0) {
            LogUtil.d(TAG, "executeNpsModule queryBatch == 0 gotoTheOldUserConfig");
            gotoTheOldUserConfig(accountInfo);
        } else {
            LogUtil.d(TAG, "executeNpsModule needDownload!");
        }
    }

    private void gotoTheOldUserConfig(String str) {
        NpsConfig readNpsConfig = readNpsConfig();
        if (readNpsConfig != null) {
            String str2 = readNpsConfig.getVersion() + str;
            boolean isTheOldUserHadConfigInfo = isTheOldUserHadConfigInfo(str2);
            LogUtil.d(TAG, "gotoTheOldUserConfig isHasConfig: ", Boolean.valueOf(isTheOldUserHadConfigInfo));
            if (!isTheOldUserHadConfigInfo) {
                LogUtil.d(TAG, "gotoTheOldUserConfig generateOldUserConfigInfo");
                generateOldUserConfigInfo(readNpsConfig, str2, str);
            } else {
                LogUtil.d(TAG, "gotoTheOldUserConfig needDownload!");
            }
        }
    }

    private void isHaveStepsFromWear() {
        HiTimeInterval hiTimeInterval = new HiTimeInterval();
        hiTimeInterval.setStartTime(0L);
        hiTimeInterval.setEndTime(System.currentTimeMillis());
        LogUtil.d(TAG, "enter isHaveStepsFromWear");
        HiHealthManager.d(this.mContext).fetchDataSourceByType(2, hiTimeInterval, new HiDataClientListener() { // from class: com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController.1
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list) {
                int deviceType;
                if (list == null) {
                    LogUtil.c(NpsUserShowController.TAG, "clientList null");
                    return;
                }
                for (HiHealthClient hiHealthClient : list) {
                    if (hiHealthClient != null && (deviceType = hiHealthClient.getHiDeviceInfo().getDeviceType()) != 30 && deviceType != 32) {
                        SharedPreferenceManager.e(NpsUserShowController.this.mContext, String.valueOf(10099), "key_ui_nps_enter_wear_home", "true", (StorageParams) null);
                        return;
                    }
                }
                SharedPreferenceManager.e(NpsUserShowController.this.mContext, String.valueOf(10099), "key_ui_nps_enter_wear_home", NpsUserShowController.NPS_ENTER_FALSE, (StorageParams) null);
            }
        });
    }

    public void showNpsPage(Context context) {
        if (context == null) {
            return;
        }
        bzs.e().initH5Pro();
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.nps", new H5ProLaunchOption.Builder().addCustomizeJsModule("npsapi", NpsApi.class).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(1).setNeedSoftInputAdapter().addCustomizeArg("connectedWhitelistDevices", sConnectedWhitelistDevices).build());
        sConnectedWhitelistDevices = "";
    }

    public void sendNpsNotification() {
        if (!isShowNps()) {
            LogUtil.d(TAG, "do not show NPS in sendNpsNotification");
            return;
        }
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setContentTitle(this.mContext.getResources().getString(R$string.nps_user_survey_title));
        xf_.setContentText(this.mContext.getString(R$string.IDS_hw_todocard_nps_dialog_content));
        xf_.setStyle(new Notification.BigTextStyle().bigText(this.mContext.getString(R$string.IDS_hw_todocard_nps_dialog_content)));
        xf_.setAutoCancel(true);
        xf_.setOngoing(false);
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportReminderReceiver.class);
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setAction("npsNotification");
        xf_.setContentIntent(PendingIntent.getBroadcast(BaseApplication.getContext(), NOTIFICATION_ID, intent, 201326592));
        jdh.c().xh_(NOTIFICATION_ID, xf_.build());
        ReleaseLogUtil.e(TAG, "send Nps Notification with ", Integer.valueOf(NOTIFICATION_ID));
        setNpsNotificationBi(1);
        SharedPreferenceManager.e(Integer.toString(20002), NPS_SEND_TIME, System.currentTimeMillis());
    }

    public void sendNpsAfterRun() {
        long b = SharedPreferenceManager.b(Integer.toString(20002), NPS_SEND_TIME, 0L);
        if (b > 0 && System.currentTimeMillis() < jdl.d(b, 30)) {
            LogUtil.d(TAG, "send nps less than 30 days with ", Long.valueOf(b));
            return;
        }
        long npsIntervalAfterSport = getNpsIntervalAfterSport();
        LogUtil.d(TAG, "Nps send interval is ", Long.valueOf(npsIntervalAfterSport));
        if (npsIntervalAfterSport <= 0) {
            return;
        }
        if (!isShowNps()) {
            LogUtil.d(TAG, "do not show NPS in sendNpsAfterRun");
            return;
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportReminderReceiver.class);
        intent.setAction("npsAlarm");
        sqa.ekn_(NOTIFICATION_ID, intent, 335544320);
        long currentTimeMillis = System.currentTimeMillis() + (npsIntervalAfterSport * 1000);
        LogUtil.d(TAG, "sendTime is ", Long.valueOf(currentTimeMillis));
        sqa.ekx_(NOTIFICATION_ID, intent, 335544320, 0, currentTimeMillis);
    }

    public void setNpsNotificationBi(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_NPS_SHOW_2010094.value(), hashMap, 0);
    }

    public long getNpsIntervalAfterSport() {
        NpsConfig readNpsConfig = readNpsConfig();
        if (readNpsConfig == null || readNpsConfig.getActiveConfigModel() == null) {
            LogUtil.d(TAG, "content or model is null in isSendNpsAfterSport");
            return -1L;
        }
        if (!isBelongFilterId(readNpsConfig.getActiveConfigModel().getAfterRunFilter())) {
            return -1L;
        }
        long afterRunPeriod = readNpsConfig.getActiveConfigModel().getAfterRunPeriod();
        LogUtil.d(TAG, "send Nps after ", Long.valueOf(afterRunPeriod));
        return afterRunPeriod;
    }

    public boolean isFilterNps() {
        NpsConfig readNpsConfig = readNpsConfig();
        LogUtil.d(TAG, "isFilterNps, config is: ", readNpsConfig);
        if (readNpsConfig == null || readNpsConfig.getActiveConfigModel() == null) {
            LogUtil.d(TAG, "content or model is null in isFilterNPS");
            return false;
        }
        ActiveConfigModel activeConfigModel = readNpsConfig.getActiveConfigModel();
        if (isFilterWithPeriod(activeConfigModel)) {
            ReleaseLogUtil.e(TAG, "isFilterWithPeriod, not show");
            return true;
        }
        if (isFilterWithDevicesType(activeConfigModel)) {
            ReleaseLogUtil.e(TAG, "isFilterWithDevicesType, not show");
            return true;
        }
        return isFilterWithNoSteps(activeConfigModel);
    }

    public boolean isFilterPhoneNps() {
        return isFilterByRatio(ActiveConfigModel.NpsRatioType.PHONE_NPS_RATIO);
    }

    public boolean isFilterDeviceNpsByType(String str) {
        NpsConfig readNpsConfig = readNpsConfig();
        if (readNpsConfig == null || readNpsConfig.getActiveConfigModel() == null) {
            LogUtil.d(TAG, "content or model is null in isFilterDeviceNpsByType");
            return false;
        }
        return isRandomBiggerRatio(ActiveConfigModel.NpsRatioType.DEVICES_NPS_RATIO + str, nru.d((Map) readNpsConfig.getActiveConfigModel().getDevicesNpsRatioMap(), str, -1));
    }

    public boolean isFilterDeviceNps() {
        return isFilterByRatio(ActiveConfigModel.NpsRatioType.DEVICES_NPS_RATIO);
    }

    public boolean isFilterPhoneWithDeviceNps() {
        return isFilterByRatio(ActiveConfigModel.NpsRatioType.PHONE_WITH_DEVICES_NPS_RATIO);
    }

    public boolean isFilterWithPhoneForDevicesNps() {
        NpsConfig readNpsConfig = readNpsConfig();
        if (readNpsConfig == null || readNpsConfig.getActiveConfigModel() == null) {
            LogUtil.d(TAG, "content or model is null in isFilterWithPhoneForDevicesNps");
            return false;
        }
        List<String> npsInvalidPhoneForDeviceNps = readNpsConfig.getActiveConfigModel().getNpsInvalidPhoneForDeviceNps();
        LogUtil.d(TAG, "invalidPhones in isFilterWithPhoneForDevicesNps: ", npsInvalidPhoneForDeviceNps);
        if (koq.b(npsInvalidPhoneForDeviceNps)) {
            LogUtil.c(TAG, "invalidPhones is empty in isFilterWithPhoneForDevicesNps");
            return false;
        }
        return CommonUtil.b((String[]) npsInvalidPhoneForDeviceNps.toArray(new String[0]));
    }

    private boolean isFilterByRatio(ActiveConfigModel.NpsRatioType npsRatioType) {
        int phoneNpsRatio;
        NpsConfig readNpsConfig = readNpsConfig();
        if (readNpsConfig == null || readNpsConfig.getActiveConfigModel() == null) {
            LogUtil.d(TAG, "content or model is null in isFilterByRatio");
            return false;
        }
        ActiveConfigModel activeConfigModel = readNpsConfig.getActiveConfigModel();
        int i = AnonymousClass3.$SwitchMap$com$huawei$ui$main$stories$nps$component$ActiveConfigModel$NpsRatioType[npsRatioType.ordinal()];
        if (i == 1) {
            phoneNpsRatio = activeConfigModel.getPhoneNpsRatio();
        } else if (i == 2) {
            phoneNpsRatio = activeConfigModel.getDevicesNpsRatio();
        } else {
            if (i != 3) {
                return false;
            }
            phoneNpsRatio = activeConfigModel.getPhoneWithDeviceNpsRatio();
        }
        if (phoneNpsRatio < 0) {
            return false;
        }
        int a2 = SharedPreferenceManager.a(Integer.toString(10099), npsRatioType.toString(), -1);
        if (a2 < 0) {
            a2 = new SecureRandom().nextInt(100);
            SharedPreferenceManager.b(Integer.toString(10099), npsRatioType.toString(), a2);
            ReleaseLogUtil.e(TAG, "random is ", Integer.valueOf(a2), "ratioType is ", npsRatioType);
        }
        LogUtil.d(TAG, "random is ", Integer.valueOf(a2), "ratioType is ", npsRatioType);
        return a2 >= phoneNpsRatio;
    }

    /* renamed from: com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$huawei$ui$main$stories$nps$component$ActiveConfigModel$NpsRatioType;

        static {
            int[] iArr = new int[ActiveConfigModel.NpsRatioType.values().length];
            $SwitchMap$com$huawei$ui$main$stories$nps$component$ActiveConfigModel$NpsRatioType = iArr;
            try {
                iArr[ActiveConfigModel.NpsRatioType.PHONE_NPS_RATIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huawei$ui$main$stories$nps$component$ActiveConfigModel$NpsRatioType[ActiveConfigModel.NpsRatioType.DEVICES_NPS_RATIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huawei$ui$main$stories$nps$component$ActiveConfigModel$NpsRatioType[ActiveConfigModel.NpsRatioType.PHONE_WITH_DEVICES_NPS_RATIO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private boolean isRandomBiggerRatio(String str, int i) {
        if (i < 0) {
            LogUtil.d(TAG, "no ratio with ", Integer.valueOf(i));
            return false;
        }
        int a2 = SharedPreferenceManager.a(Integer.toString(10099), str, -1);
        if (a2 < 0) {
            a2 = new SecureRandom().nextInt(100);
            SharedPreferenceManager.b(Integer.toString(10099), str, a2);
            ReleaseLogUtil.e(TAG, "random is ", Integer.valueOf(a2), "ratioType is ", str);
        }
        LogUtil.d(TAG, "random is ", Integer.valueOf(a2), "ratioType is ", str, "ratio is ", Integer.valueOf(i));
        return a2 >= i;
    }

    private boolean isFilterWithPeriod(ActiveConfigModel activeConfigModel) {
        List<String> npsInvalidPeriod = activeConfigModel.getNpsInvalidPeriod();
        if (koq.b(npsInvalidPeriod)) {
            LogUtil.c(TAG, "npsHidePeriodList is empty in isFilterWithPeriod");
            return false;
        }
        LogUtil.d(TAG, "npsHidePeriodList in isFilterWithPeriod: ", npsInvalidPeriod);
        for (String str : npsInvalidPeriod) {
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(",");
                if (split.length > 1 && isFilterTime(split[0], split[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isFilterTime(String str, String str2) {
        try {
            int i = Calendar.getInstance().get(11);
            LogUtil.d(TAG, "isFilterTime hour=", Integer.valueOf(i), " start=", str, " end=", str2);
            if (i >= Integer.parseInt(str)) {
                return i < Integer.parseInt(str2);
            }
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.e(TAG, "isFilterTime NumberFormatException");
            return false;
        }
    }

    private boolean isFilterWithDevicesType(ActiveConfigModel activeConfigModel) {
        List<String> npsInvalidDevices = activeConfigModel.getNpsInvalidDevices();
        if (koq.b(npsInvalidDevices)) {
            LogUtil.c(TAG, "npsHideDeviceTypeList is empty in isHideNpsDevicesType");
            return false;
        }
        String[] strArr = (String[]) npsInvalidDevices.toArray(new String[0]);
        LogUtil.d(TAG, "npsHideDeviceTypes in isFilterWithDevicesType: ", strArr);
        return CommonUtil.b(strArr);
    }

    private boolean isFilterWithNoSteps(ActiveConfigModel activeConfigModel) {
        final long noStepsPeriod = activeConfigModel.getNoStepsPeriod();
        LogUtil.d(TAG, "queryDays is ", Long.valueOf(noStepsPeriod));
        if (noStepsPeriod < 1) {
            return false;
        }
        if (!isBelongFilterId(activeConfigModel.getNoStepsFilter())) {
            LogUtil.d(TAG, "Step Filters not contain");
            return false;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NpsUserShowController.this.m832xca5157e(noStepsPeriod);
            }
        });
        return SharedPreferenceManager.a(Integer.toString(10099), IS_FILTER_WITH_NO_STEPS_SP_KEY, true);
    }

    /* renamed from: lambda$isFilterWithNoSteps$2$com-huawei-ui-main-stories-nps-npsstate-NpsUserShowController, reason: not valid java name */
    /* synthetic */ void m832xca5157e(long j) {
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateHiHealthData(createAggregateOption(j), new HiAggregateListener() { // from class: com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                boolean z;
                LogUtil.d(NpsUserShowController.TAG, "aggregateHiHealthData errorCode is ", Integer.valueOf(i));
                if (koq.b(list)) {
                    return;
                }
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    HiHealthData next = it.next();
                    if (next != null && next.getInt("stepUserValue") > 0) {
                        z = false;
                        break;
                    }
                }
                SharedPreferenceManager.e(Integer.toString(10099), NpsUserShowController.IS_FILTER_WITH_NO_STEPS_SP_KEY, z);
                LogUtil.d(NpsUserShowController.TAG, "aggregateHiHealthData isFilterWithNoSteps is ", Boolean.valueOf(z));
            }
        });
    }

    private boolean isBelongFilterId(List<String> list) {
        if (koq.b(list)) {
            LogUtil.c(TAG, "noStepsFilters is empty isStepFilter");
            return false;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.d(TAG, "isStepFilterId huidStr is no char: ", false);
            return false;
        }
        for (String str : list) {
            if (StringUtils.i(str) && accountInfo.endsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private HiAggregateOption createAggregateOption(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        long d = jdl.d(currentTimeMillis, (int) (1 - j));
        long e = jdl.e(currentTimeMillis);
        LogUtil.d(TAG, "start: ", DateFormatUtil.a(d, DateFormatUtil.DateFormatType.DATE_FORMAT_14), " end: ", DateFormatUtil.a(e, DateFormatUtil.DateFormatType.DATE_FORMAT_14));
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(d);
        hiAggregateOption.setEndTime(e);
        hiAggregateOption.setType(new int[]{40002});
        hiAggregateOption.setConstantsKey(new String[]{"stepUserValue"});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }
}
