package com.huawei.ui.main.stories.health.temperature.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.calendarview.MarkDateTrigger;
import defpackage.koq;
import defpackage.qpk;
import health.compact.a.util.LogUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class TemperatureMarkDateTrigger implements MarkDateTrigger {
    public static final Parcelable.Creator<TemperatureMarkDateTrigger> CREATOR = new Parcelable.Creator<TemperatureMarkDateTrigger>() { // from class: com.huawei.ui.main.stories.health.temperature.utils.TemperatureMarkDateTrigger.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dHd_, reason: merged with bridge method [inline-methods] */
        public TemperatureMarkDateTrigger createFromParcel(Parcel parcel) {
            return new TemperatureMarkDateTrigger(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public TemperatureMarkDateTrigger[] newArray(int i) {
            return new TemperatureMarkDateTrigger[i];
        }
    };
    private static final String TAG = "TemperatureMarkDateTrigger";
    private final int[] mDataTypes;
    private final int mHighAlarmType;
    private final int mLowAlarmType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override // com.huawei.ui.commonui.calendarview.MarkDateTrigger
    public void retrieveMarkDate(long j, long j2, UiCallback<Map<String, HealthCalendar>> uiCallback) {
        if (this.mDataTypes == null) {
            return;
        }
        LogUtil.d(TAG, "retrieveMarkDate, startTime: ", Long.valueOf(j), " endTime: ", Long.valueOf(j2));
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(this.mDataTypes);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, getReadResultListener(uiCallback));
    }

    private HiDataReadResultListener getReadResultListener(final UiCallback<Map<String, HealthCalendar>> uiCallback) {
        return new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.health.temperature.utils.TemperatureMarkDateTrigger.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (!(obj instanceof SparseArray) || TemperatureMarkDateTrigger.this.mDataTypes == null) {
                    return;
                }
                HashMap hashMap = new HashMap();
                for (int i3 : TemperatureMarkDateTrigger.this.mDataTypes) {
                    Object obj2 = ((SparseArray) obj).get(i3);
                    if (koq.e(obj2, HiHealthData.class)) {
                        List<HiHealthData> list = (List) obj2;
                        if (!koq.b(list)) {
                            for (HiHealthData hiHealthData : list) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTimeInMillis(hiHealthData.getStartTime());
                                HealthCalendar healthCalendar = new HealthCalendar(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
                                HealthMark healthMark = new HealthMark();
                                if (i3 != 2104 && i3 != HiHealthDataType.b && i3 != DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()) {
                                    if (i3 == TemperatureMarkDateTrigger.this.mLowAlarmType || i3 == TemperatureMarkDateTrigger.this.mHighAlarmType) {
                                        healthMark.e(HealthMark.MarkType.DRAWABLE);
                                    }
                                } else {
                                    healthMark.e(HealthMark.MarkType.COLOR);
                                    healthMark.b(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296560_res_0x7f090130));
                                }
                                healthCalendar.addMark(healthMark);
                                if (hashMap.get(healthCalendar.toString()) != null) {
                                    TemperatureMarkDateTrigger.this.mergeCalendarItem((HealthCalendar) hashMap.get(healthCalendar.toString()), healthCalendar);
                                } else {
                                    hashMap.put(healthCalendar.toString(), healthCalendar);
                                }
                            }
                        }
                    }
                }
                uiCallback.onSuccess(hashMap);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCalendarItem(HealthCalendar healthCalendar, HealthCalendar healthCalendar2) {
        List<HealthMark> marks = healthCalendar.getMarks();
        List<HealthMark> marks2 = healthCalendar2.getMarks();
        if (marks.size() == 1 && marks2.size() == 1 && marks.get(0).f() != marks2.get(0).f()) {
            marks.addAll(marks2);
        }
    }

    protected TemperatureMarkDateTrigger(Parcel parcel) {
        int j = qpk.d().j();
        this.mLowAlarmType = j;
        int h = qpk.d().h();
        this.mHighAlarmType = h;
        this.mDataTypes = new int[]{2104, HiHealthDataType.b, DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value(), j, h};
    }
}
