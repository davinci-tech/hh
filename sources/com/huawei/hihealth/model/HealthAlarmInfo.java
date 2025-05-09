package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;

/* loaded from: classes.dex */
public class HealthAlarmInfo extends EventTypeInfo {
    public static final Parcelable.Creator<HealthAlarmInfo> CREATOR = new Parcelable.Creator<HealthAlarmInfo>() { // from class: com.huawei.hihealth.model.HealthAlarmInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bws_, reason: merged with bridge method [inline-methods] */
        public HealthAlarmInfo createFromParcel(Parcel parcel) {
            return new HealthAlarmInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HealthAlarmInfo[] newArray(int i) {
            return new HealthAlarmInfo[i];
        }
    };

    public HealthAlarmInfo(String str, String str2) {
        super(SampleEvent.Type.HEALTH_ALARM_EVENT, str, str2, 0);
    }

    public HealthAlarmInfo(Parcel parcel) {
        super(parcel);
    }

    public enum Type {
        TACHYCARDIA("TACHYCARDIA"),
        BRADYCARDIA(HwHealthChartHolder.BRADYCARDIA_WARN);

        private final String name;

        Type(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }
}
