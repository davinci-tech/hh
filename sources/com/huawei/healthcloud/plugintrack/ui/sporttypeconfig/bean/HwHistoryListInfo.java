package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import defpackage.gxz;
import defpackage.hpn;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class HwHistoryListInfo {

    @SerializedName("itemBgColor")
    private int mItemBgColor;

    @SerializedName("itemImg")
    private String mItemImg;

    @SerializedName("itemImgDefault")
    private String mItemImgDefault;

    @SerializedName("monthlyStatisticsData")
    private ArrayList<MonthlyStatisticsData> mMonthlyStatisticsData;

    @SerializedName("multiItemImg")
    private HashMap<String, String> mMultiItemImg;

    public String getItemImg() {
        return this.mItemImg;
    }

    public int getItemBgColor() {
        return this.mItemBgColor;
    }

    public void setItemImg(String str) {
        this.mItemImg = str;
    }

    public ArrayList<MonthlyStatisticsData> getMonthlyStatisticsData() {
        ArrayList<MonthlyStatisticsData> arrayList = this.mMonthlyStatisticsData;
        if (arrayList == null) {
            return null;
        }
        return (ArrayList) arrayList.clone();
    }

    public ArrayList<String> getTrackRequestDataBase(int i) {
        return getAllRequestString(i);
    }

    public List<Integer> getAllRequestId() {
        if (koq.b(this.mMonthlyStatisticsData)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<MonthlyStatisticsData> it = this.mMonthlyStatisticsData.iterator();
        while (it.hasNext()) {
            MonthlyStatisticsData next = it.next();
            if (next != null) {
                arrayList.add(Integer.valueOf(next.getRequestId()));
            }
        }
        return arrayList;
    }

    public ArrayList<String> getAllRequestString(int i) {
        if (koq.b(this.mMonthlyStatisticsData)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<MonthlyStatisticsData> it = this.mMonthlyStatisticsData.iterator();
        while (it.hasNext()) {
            MonthlyStatisticsData next = it.next();
            if (next != null) {
                arrayList.add("Track_" + i + next.getRequestId());
            }
        }
        return arrayList;
    }

    public void getMainAndBackupMonthlyType(HwSportTypeInfo hwSportTypeInfo, Map<String, HwSportTypeInfo> map) {
        if (koq.b(this.mMonthlyStatisticsData)) {
            return;
        }
        int sportTypeId = hwSportTypeInfo.getSportTypeId();
        Iterator<MonthlyStatisticsData> it = this.mMonthlyStatisticsData.iterator();
        while (it.hasNext()) {
            MonthlyStatisticsData next = it.next();
            if (next != null && (MonthlyStatisticsData.MAIN.equals(next.getPosition()) || MonthlyStatisticsData.BACKUP.equals(next.getPosition()))) {
                map.put("Track_" + sportTypeId + next.getRequestId(), hwSportTypeInfo);
            }
        }
    }

    public ArrayList<String> getAllMonthlyType() {
        if (koq.b(this.mMonthlyStatisticsData)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<MonthlyStatisticsData> it = this.mMonthlyStatisticsData.iterator();
        while (it.hasNext()) {
            MonthlyStatisticsData next = it.next();
            if (next != null) {
                arrayList.add(next.getType());
            }
        }
        return arrayList;
    }

    public String mainPositionData(boolean z) {
        if (koq.b(this.mMonthlyStatisticsData)) {
            return null;
        }
        Iterator<MonthlyStatisticsData> it = this.mMonthlyStatisticsData.iterator();
        while (it.hasNext()) {
            MonthlyStatisticsData next = it.next();
            if (next != null && MonthlyStatisticsData.MAIN.equals(next.getPosition())) {
                if (z) {
                    return String.valueOf(next.getRequestId());
                }
                return next.getType();
            }
        }
        return null;
    }

    private String getItemImg(String str) {
        HashMap<String, String> hashMap = this.mMultiItemImg;
        if (hashMap != null && !TextUtils.isEmpty(hashMap.get(str))) {
            return this.mMultiItemImg.get(str);
        }
        return this.mItemImg;
    }

    private int getItemDefaultId() {
        int d = gxz.d(this.mItemImgDefault, BaseApplication.e());
        return d == 0 ? R.drawable.ic_health_list_outdoor_running : d;
    }

    public Drawable getItemDrawable(String str) {
        return hpn.bog_(getItemImg(str), getItemDefaultId());
    }

    public Drawable getItemDrawable() {
        return hpn.bog_(this.mItemImg, getItemDefaultId());
    }
}
