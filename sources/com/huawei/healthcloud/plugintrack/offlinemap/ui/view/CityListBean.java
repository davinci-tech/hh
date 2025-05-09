package com.huawei.healthcloud.plugintrack.offlinemap.ui.view;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class CityListBean extends ArrayList<OfflineMapCity> implements Parcelable {
    public static final Parcelable.Creator<CityListBean> CREATOR = new Parcelable.Creator<CityListBean>() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.CityListBean.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: aWB_, reason: merged with bridge method [inline-methods] */
        public CityListBean createFromParcel(Parcel parcel) {
            return new CityListBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CityListBean[] newArray(int i) {
            return new CityListBean[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CityListBean() {
    }

    protected CityListBean(Parcel parcel) {
        if (parcel == null) {
            throw new RuntimeException("CityListBean invalid params in constructor");
        }
        addAll(parcel.readArrayList(OfflineMapCity.class.getClassLoader()));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeList(this);
        }
    }
}
