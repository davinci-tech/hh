package com.huawei.health.ecologydevice.ui.measure.view;

import android.content.Context;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;

/* loaded from: classes3.dex */
public interface SportIntroductionContentView {
    Context getContext();

    String getProductId();

    void onDeviceBleStateChanged(int i);

    void onGetLastHistoryDataFail(int i);

    void onGetLastHistoryDataSuccess(HiTrackMetaData hiTrackMetaData, long j);

    void onMonthDataFail(int i);

    void onMonthDataSuccess(String str, String str2);

    void queryTrackDetailDataFail();

    void queryTrackDetailDataSuccess(MotionPathSimplify motionPathSimplify, String str);

    void startSport();
}
