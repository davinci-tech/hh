package com.huawei.hihealth;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes.dex */
public class StartSportParam extends CommonParam {
    public static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<StartSportParam> CREATOR = new Parcelable.Creator<StartSportParam>() { // from class: com.huawei.hihealth.StartSportParam.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwd_, reason: merged with bridge method [inline-methods] */
        public StartSportParam createFromParcel(Parcel parcel) {
            return new StartSportParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public StartSportParam[] newArray(int i) {
            return (i > 65535 || i < 0) ? new StartSportParam[0] : new StartSportParam[i];
        }
    };

    public StartSportParam() {
    }

    protected StartSportParam(Parcel parcel) {
        super(parcel);
    }

    public StartSportParam(int i) {
        this("", 0, i);
    }

    public StartSportParam(String str, int i, int i2, int i3) {
        putString("macAddress", str);
        putInt("ftmp", i);
        putInt("deviceType", i2);
        putInt(BleConstants.SPORT_TYPE, i3);
    }

    public StartSportParam(String str, int i, int i2) {
        this(str, CharacteristicConstant.FtmpType.FTMP_SUPPORTED.getFtmpTypeValue(), i, i2);
    }

    public Bundle getValueHolder() {
        return this.valueHolder;
    }

    public void setValueHolder(Bundle bundle) {
        this.valueHolder = bundle;
    }
}
