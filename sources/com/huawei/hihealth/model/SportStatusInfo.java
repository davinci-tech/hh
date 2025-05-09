package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.model.SampleEvent;

/* loaded from: classes.dex */
public class SportStatusInfo extends EventTypeInfo {
    public static final Parcelable.Creator<SportStatusInfo> CREATOR = new Parcelable.Creator<SportStatusInfo>() { // from class: com.huawei.hihealth.model.SportStatusInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwB_, reason: merged with bridge method [inline-methods] */
        public SportStatusInfo createFromParcel(Parcel parcel) {
            return new SportStatusInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SportStatusInfo[] newArray(int i) {
            return new SportStatusInfo[i];
        }
    };

    public SportStatusInfo(String str, String str2) {
        super(SampleEvent.Type.SPORT_STATUS_EVENT, str, str2, 0);
    }

    public SportStatusInfo(String str, String str2, int i) {
        super(SampleEvent.Type.SPORT_STATUS_EVENT, str, str2, i);
    }

    public SportStatusInfo(Parcel parcel) {
        super(parcel);
    }

    public enum Type {
        SPORT_START("SPORT_START"),
        SPORT_END("SPORT_END");

        private final String name;

        Type(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }
}
