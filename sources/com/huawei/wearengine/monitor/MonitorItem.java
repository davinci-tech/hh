package com.huawei.wearengine.monitor;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;

/* loaded from: classes7.dex */
public class MonitorItem implements Parcelable {
    private String mName;
    public static final MonitorItem MONITOR_ITEM_CONNECTION = new MonitorItem("connectionStatus");
    public static final MonitorItem MONITOR_ITEM_WEAR = new MonitorItem("wearStatus");
    public static final MonitorItem MONITOR_ITEM_SLEEP = new MonitorItem(KnitHealthDetailActivity.KEY_SLEEP_STATUS);
    public static final MonitorItem MONITOR_ITEM_LOW_POWER = new MonitorItem("lowPower");
    public static final MonitorItem MONITOR_ITEM_SPORT = new MonitorItem("sportStatus");
    public static final MonitorItem MONITOR_POWER_STATUS = new MonitorItem("powerStatus");
    public static final MonitorItem MONITOR_CHARGE_STATUS = new MonitorItem("chargeStatus");
    public static final MonitorItem MONITOR_ITEM_HEART_RATE_ALARM = new MonitorItem("heartRateAlarm");
    public static final MonitorItem MONITOR_ITEM_USER_AVAILABLE_KBYTES = new MonitorItem("userAvailableKbytes");
    public static final MonitorItem MONITOR_ITEM_POWER_MODE = new MonitorItem("powerMode");
    public static final Parcelable.Creator<MonitorItem> CREATOR = new Parcelable.Creator<MonitorItem>() { // from class: com.huawei.wearengine.monitor.MonitorItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: fdx_, reason: merged with bridge method [inline-methods] */
        public MonitorItem createFromParcel(Parcel parcel) {
            MonitorItem monitorItem = new MonitorItem();
            if (parcel != null) {
                monitorItem.setName(parcel.readString());
            }
            return monitorItem;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public MonitorItem[] newArray(int i) {
            return (i > 65535 || i < 0) ? new MonitorItem[0] : new MonitorItem[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MonitorItem() {
    }

    protected MonitorItem(Parcel parcel) {
        if (parcel != null) {
            this.mName = parcel.readString();
        }
    }

    private MonitorItem(String str) {
        this.mName = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mName);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mName = parcel.readString();
    }
}
