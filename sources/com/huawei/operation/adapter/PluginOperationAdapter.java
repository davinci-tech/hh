package com.huawei.operation.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.operation.beans.WebViewConfig;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.fdu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface PluginOperationAdapter extends PluginBaseAdapter {
    int bindDevice(String str, String str2, String str3, String str4, String str5);

    void breatheControl(int i, int i2, int i3, int i4, IBaseResponseCallback iBaseResponseCallback);

    void calibrationControl(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback);

    int canFinish(Activity activity);

    void checkCalibration(IBaseResponseCallback iBaseResponseCallback);

    void checkConnected(IBaseResponseCallback iBaseResponseCallback);

    boolean checkCurrentUrlAuth(String str, String str2);

    void checkUserWeightAndShowDialog(Context context, Object obj);

    boolean checkWhiteUrl(String str);

    void createHpkInstalled(String str, String str2);

    void deleteSleepQuestionnaireResult(long j, IBaseResponseCallback iBaseResponseCallback);

    void gameControl(int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback);

    void getAnnualReport(int i, IBaseResponseCallback iBaseResponseCallback);

    String getAppMarketLocal();

    int getAuthType(String str, String str2);

    String getBondedDeviceAddress(String str);

    ArrayList<ContentValues> getBondedDeviceByProductId(String str);

    String getContentCenterTest();

    void getCourseRecommend(String str, String str2, String str3, IBaseResponseCallback iBaseResponseCallback);

    String getCurrentUserUuid();

    void getFitnessSumData(long j, long j2, String str, SportsStatisticsCallback sportsStatisticsCallback);

    void getHealthData(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback);

    void getHealthDataStatistics(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback);

    void getHealthStat(String str, String str2, int i, IBaseResponseCallback iBaseResponseCallback);

    Map<String, String> getInfo(String[] strArr);

    int getJanusDeviceConnect();

    String getLocale();

    void getMotionPathData(long j, long j2, IBaseResponseCallback iBaseResponseCallback);

    int getNetworkStatus();

    String getPersonalPrivacySettingValue(int i);

    String getProductDir();

    List<Map<String, Object>> getRecordsByDateRange(String str, String str2);

    String getServiceIdByUrl(String str);

    SportData getSportData();

    void getSportData(String str, String str2, IBaseResponseCallback iBaseResponseCallback);

    void getUserInfo(IBaseResponseCallback iBaseResponseCallback);

    String getUserLabels();

    void getWorkoutRecordByTimeAndId(long j, long j2, String str, String str2, SportsStatisticsCallback sportsStatisticsCallback);

    void go2PersonalPrivacySettingsActivity();

    boolean isBindDevice();

    boolean isRopeDevice(String str);

    boolean isSupportStepCounter(Context context);

    void jumpAchieveKakaPage(Context context);

    void jumpAchieveMedalById(Context context, String str);

    void jumpMyAwardPage(Context context);

    String queryServiceNameById(String str);

    void querySleepQuestionnaireResults(long j, IBaseResponseCallback iBaseResponseCallback);

    List<String> queryUrlList(String str);

    WebViewConfig queryWebViewConfig();

    void relaxControl(int i, int i2, IBaseResponseCallback iBaseResponseCallback);

    void resetCalibration(IBaseResponseCallback iBaseResponseCallback);

    void saveMeasureData(String str, String str2, IBaseResponseCallback iBaseResponseCallback);

    void setAchieveEvent(String str, Map<String, Object> map);

    void setAppMarketParameter(int i, Object obj);

    void setSendOpenId(String str);

    void share(Context context, fdu fduVar, IBaseResponseCallback iBaseResponseCallback);

    void showServiceTips(Context context, HashMap<String, String> hashMap, IBaseResponseCallback iBaseResponseCallback);

    void startAchieveAwardShare(Context context, String str, String str2);

    void startFitnessList();

    void startFitnessPage(Context context, String str, String str2);

    void startGpsTrackPage(Context context, int i, String str, float f);

    void stressAbort(IBaseResponseCallback iBaseResponseCallback, boolean z);

    void stressControl(int i, int i2, IBaseResponseCallback iBaseResponseCallback);

    boolean unbindDevice(Context context, String str, String str2);

    boolean unbindHaveBindingDevice(Context context, ContentValues contentValues, String str);

    void uploadSleepQuestionnaireResults(long j, String str, IBaseResponseCallback iBaseResponseCallback);

    void vmallAgrQuery(IBaseResponseCallback iBaseResponseCallback);

    void vmallAgrSign(IBaseResponseCallback iBaseResponseCallback);
}
