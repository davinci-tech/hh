package com.huawei.health.ecologydevice.ui.measure.presenter;

import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView;
import defpackage.dcz;
import java.math.BigDecimal;

/* loaded from: classes3.dex */
public interface SportIntroductionContentPresenter {
    void connectDevice(int i, dcz dczVar);

    void disconnect(boolean z);

    DeviceInformation getDeviceInformation();

    void getLastMonthRecord(int i, long j);

    void getLastSportRecord(int i);

    BigDecimal getTargetCacheValue(String str);

    boolean isNeedShowDescription(String str);

    void onCreate(SportIntroductionContentView sportIntroductionContentView);

    void onDetach();

    void queryTrackDetailData();

    void removeModeCache();

    void removeNoShowDescriptionMac(String str);

    void saveTargetData(String str, String str2);

    void setModeSelectBiEvent(String str, int i, String str2, int i2);

    void setSubPageBiEvent(String str, String str2, String str3, int i);
}
