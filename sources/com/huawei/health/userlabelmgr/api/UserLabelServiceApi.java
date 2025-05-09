package com.huawei.health.userlabelmgr.api;

import android.content.Context;
import defpackage.fda;
import defpackage.fdh;
import java.util.List;

/* loaded from: classes.dex */
public interface UserLabelServiceApi {
    void clickRecord(int i, String str);

    void destroyOdmf();

    List<String> getAllLabels(String str);

    List<String> getLabels(List<String> list, String str);

    void getPengineAllLabels(Context context);

    void initDailySleepLabel(fda fdaVar);

    void initLabel(String str, String str2, String str3);

    void initMonthlySleepLabel(fdh fdhVar);

    boolean isContainsHealthLabel(List<String> list);

    boolean isContainsOtherLabel(List<String> list);

    void registerCallback(int i);

    void saveBindingDeviceToOdmf(String str);

    void saveThirtyDaysTrackDataToOdmf(Context context);

    void saveTrackDataToOdmf(String str);

    void subscribeTrackDataChanged(Context context);

    void unRegisterCallback(int i);

    void unSubscribeTrackData(Context context);

    void uploadBindingDevice();

    void uploadSleepData();

    void uploadSportEvent();
}
