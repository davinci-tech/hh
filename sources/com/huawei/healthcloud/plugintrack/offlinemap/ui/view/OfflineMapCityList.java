package com.huawei.healthcloud.plugintrack.offlinemap.ui.view;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class OfflineMapCityList implements Parcelable {
    public static final Parcelable.Creator<OfflineMapCityList> CREATOR = new Parcelable.Creator<OfflineMapCityList>() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.view.OfflineMapCityList.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: aWG_, reason: merged with bridge method [inline-methods] */
        public OfflineMapCityList createFromParcel(Parcel parcel) {
            return new OfflineMapCityList(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public OfflineMapCityList[] newArray(int i) {
            return new OfflineMapCityList[i];
        }
    };
    private HashMap<Integer, CityListBean> mAllCityMap;
    private transient ArrayList<OfflineMapCity> mDownCityList;
    private transient ArrayList<OfflineMapCity> mLoadingCityList;
    private transient ArrayList<OfflineMapProvince> mProvinceList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OfflineMapCityList() {
        this.mProvinceList = new ArrayList<>(10);
        this.mDownCityList = new ArrayList<>(10);
        this.mLoadingCityList = new ArrayList<>(10);
        this.mAllCityMap = new HashMap<>(16);
    }

    protected OfflineMapCityList(Parcel parcel) {
        this.mProvinceList = new ArrayList<>(10);
        this.mDownCityList = new ArrayList<>(10);
        this.mLoadingCityList = new ArrayList<>(10);
        this.mAllCityMap = new HashMap<>(16);
        if (parcel != null) {
            this.mProvinceList = parcel.readArrayList(OfflineMapProvince.class.getClassLoader());
            this.mAllCityMap = parcel.readHashMap(CityListBean.class.getClassLoader());
            this.mDownCityList = parcel.readArrayList(OfflineMapCity.class.getClassLoader());
            this.mLoadingCityList = parcel.readArrayList(OfflineMapCity.class.getClassLoader());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeList(this.mProvinceList);
            parcel.writeMap(this.mAllCityMap);
            parcel.writeList(this.mDownCityList);
            parcel.writeList(this.mLoadingCityList);
        }
    }

    public ArrayList<OfflineMapProvince> getProvinceList() {
        return this.mProvinceList;
    }

    public void setProvinceList(ArrayList<OfflineMapProvince> arrayList) {
        this.mProvinceList = arrayList;
    }

    public HashMap<Integer, CityListBean> getCityMap() {
        return this.mAllCityMap;
    }

    public void setCityMap(HashMap<Integer, CityListBean> hashMap) {
        this.mAllCityMap = hashMap;
    }

    public ArrayList<OfflineMapCity> getDownCityList() {
        return this.mDownCityList;
    }

    public void setDownCityList(ArrayList<OfflineMapCity> arrayList) {
        this.mDownCityList = arrayList;
    }

    public ArrayList<OfflineMapCity> getLoadingCityList() {
        return this.mLoadingCityList;
    }

    public void setLoadingCityList(ArrayList<OfflineMapCity> arrayList) {
        this.mLoadingCityList = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("OfflineMapCityList [mProvinceList=");
        sb.append(this.mProvinceList);
        sb.append(", mCityMap=");
        sb.append(this.mAllCityMap);
        sb.append(", mDownCityList=");
        sb.append(this.mDownCityList);
        sb.append(", mLoadingCityList=");
        sb.append(this.mLoadingCityList);
        sb.append("]");
        return sb.toString();
    }
}
