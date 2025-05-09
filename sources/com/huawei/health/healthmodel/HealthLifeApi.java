package com.huawei.health.healthmodel;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.util.Pair;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.ResponseCallback;
import defpackage.cbk;
import defpackage.drx;
import defpackage.dsa;
import defpackage.dsb;
import defpackage.dso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes3.dex */
public interface HealthLifeApi {
    int getActiveTaskId(List<HealthLifeBean> list, int i);

    List<cbk> getBloodPressureAlarmInfoList();

    ArrayList<Integer> getBloodPressurePlanIdList();

    Object getCardReader(Context context, Object obj);

    int getCloverComplete(int i, int i2);

    float getCloverScale(List<HealthLifeBean> list, int i);

    void getDayRecordList(int i, ResponseCallback<List<HealthLifeTaskBean>> responseCallback);

    List<HealthLifeTaskBean> getDayRecordListCache();

    void getHealthLifeChallenge(String str, ResponseCallback<drx> responseCallback);

    void getHealthLifeConDays(int i, ResponseCallback<dsa> responseCallback);

    boolean getHealthLifeSwitch(String str, boolean z);

    Calendar getHourAndMinute(String str);

    int getJoinTime();

    List<HealthLifeTaskBean> getLastDayRecordListCache();

    int getPlayType();

    Pair<Integer, String> getRemindTipMap(List<HealthLifeBean> list);

    List<Integer> getSmallShownIdsCache(int i);

    String getSubscriptionData(int i);

    List<HealthLifeTaskBean> getTaskDayRecordList(List<Integer> list);

    List<ImageBean> getTaskDialogImageList(String str);

    void initDeviceManager();

    void initHealthLife();

    boolean isAgreeProtocol();

    boolean isJoinHealthModel();

    boolean isPerfectClover(List<HealthLifeBean> list, float f, float f2, float f3);

    List<HealthLifeBean> queryHealthLifeBeanList(long j, long j2);

    void queryInterventionPlanInfo(String str, ResponseCallback<dso> responseCallback);

    void queryWeekHealthReport(int i, ResponseCallback<dsb> responseCallback);

    void refreshLastDayRecordListCache(List<HealthLifeBean> list);

    void refreshTask(int i);

    void registerH5ProService();

    void resetCache();

    void sendDeviceTaskData();

    void setAboutEvent(int i, Context context);

    void setBinder();

    void setHealthLifeSwitch(String str, boolean z);

    void setHealthLifeTaskConsInfoCache(dsa dsaVar);

    void setSmallShownIdsCache(List<Integer> list);

    void shareSleepTask(int i, int i2, Bitmap bitmap);

    void showTaskDialog(Context context, HealthLifeTaskBean healthLifeTaskBean, String str, ResponseCallback<HealthLifeTaskBean> responseCallback);

    ArrayList<Integer> subscribeHiHealthData(String str, HiSubscribeListener hiSubscribeListener);

    void syncUpdateHealthGoal(List<HealthLifeBean> list, ResponseCallback<List<HealthLifeBean>> responseCallback, String str);

    void unSubscribeHiHealthData(String str, List<Integer> list, HiUnSubscribeListener hiUnSubscribeListener);

    void updateBloodPressureTarget(List<cbk> list);
}
