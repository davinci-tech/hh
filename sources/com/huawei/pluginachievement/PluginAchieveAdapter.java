package com.huawei.pluginachievement;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.pluginachievement.manager.model.TrackData;
import com.huawei.pluginbase.PluginBaseAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/* loaded from: classes6.dex */
public interface PluginAchieveAdapter extends PluginBaseAdapter {
    String decryptData(String str, Context context);

    void deleteMessage(String str);

    void fetchSequenceDataBySportType(Context context, long j, long j2, int i, AchieveCallback achieveCallback);

    String generateMessage(MessageObject messageObject);

    void getAnnualActivityReport(Context context, int i, AchieveCallback achieveCallback);

    void getAnnualBloodPressureRecordData(int i, AchieveCallback achieveCallback);

    void getAnnualBloodSugarRecordData(int i, AchieveCallback achieveCallback);

    void getAnnualFitnessRecord(Context context, long j, long j2, AchieveCallback achieveCallback);

    void getAnnualFitnessReport(Context context, long j, long j2, AchieveCallback achieveCallback);

    void getAnnualSleepReport(Context context, long j, long j2, AchieveCallback achieveCallback);

    boolean getBondResult(String str);

    String getEncryptData(String str, Context context);

    String getExchangeUrl(Context context);

    void getFitSportEverydayRecords(int i, AchieveCallback achieveCallback);

    void getFitSportSumDatas(int i, AchieveCallback achieveCallback);

    Map<String, String> getInfo(String[] strArr);

    void getLatestMesureDatas(long j, long j2, int i, AchieveCallback achieveCallback);

    void getLatestSleepDatas(long j, long j2, AchieveCallback achieveCallback);

    void getOtherFitSportEverydayRecords(int i, AchieveCallback achieveCallback);

    void getOtherFitSportSumData(int i, AchieveCallback achieveCallback);

    void getReportStatistics(Context context, long j, long j2, int i, AchieveCallback achieveCallback);

    void getSleepDatas(long j, long j2, AchieveCallback achieveCallback);

    void getSumData(Context context, long j, long j2, int i, AchieveCallback achieveCallback);

    void getSumYearData(Context context, long j, long j2, int i, AchieveCallback achieveCallback);

    void getTotalCalorie(AchieveCallback achieveCallback, Context context);

    int getTotalFitDuration(Date date, Date date2);

    void getTotalSteps(AchieveCallback achieveCallback);

    void getTrackDetailDataByTimestamp(Context context, long j, long j2, AchieveCallback achieveCallback);

    void getTrackListData(Context context, long j, long j2, int i, AchieveCallback achieveCallback);

    void getTrackSumDistanceData(Context context, long j, long j2, int i, AchieveCallback achieveCallback);

    void getWeightData(Context context, long j, long j2, AchieveCallback achieveCallback);

    void goToPhysiologicalCycle(Context context);

    void gotoAllDeviceBinding(Context context);

    void gotoBondDevice(Context context, String str);

    void gotoBubuBaoService(Context context, String str);

    void gotoFitness();

    void gotoFitnessAllCourses();

    void gotoFitnessRecord();

    void gotoMeasureDeivice(Context context, String str);

    void gotoRunRecord(Context context);

    void gotoSecIBaoService(Context context, String str);

    void gotoShumianService(Context context, String str);

    void gotoSleepDetailPage(Context context);

    void gotoStepRecord(Context context);

    void gotoTrackDetailData(Context context, long j, long j2);

    void gotoTrainDetailActivity(String str, String str2);

    void gotoWeChatActivity(Context context);

    void readBestStepDayOfYear(Context context, long j, long j2, AchieveCallback achieveCallback);

    void readEveryMonthStepsOfYear(Context context, long j, long j2, AchieveCallback achieveCallback);

    void readSingleTrackData(Context context, long j, long j2, IBaseResponseCallback iBaseResponseCallback);

    void readStepDataAsDayUnit(Context context, long j, long j2, int i, AchieveCallback achieveCallback);

    void sendTrackData(Context context, ArrayList<TrackData> arrayList);

    void startInputWeight(Context context);

    void startMyTargetActivity(Context context);

    void startWebActivityForResult(Context context, String str);

    void toReadAggregateData(AchieveCallback achieveCallback, Context context, long j, long j2);
}
