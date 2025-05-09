package com.huawei.ui.main.stories.userprofile.card.familycard.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nrv;
import defpackage.rbe;
import health.compact.a.LogAnonymous;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class FamilyHealthCardInfo implements Parcelable {
    public static final Parcelable.Creator<FamilyHealthCardInfo> CREATOR = new Parcelable.Creator<FamilyHealthCardInfo>() { // from class: com.huawei.ui.main.stories.userprofile.card.familycard.beans.FamilyHealthCardInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: dUF_, reason: merged with bridge method [inline-methods] */
        public FamilyHealthCardInfo createFromParcel(Parcel parcel) {
            return new FamilyHealthCardInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public FamilyHealthCardInfo[] newArray(int i) {
            return new FamilyHealthCardInfo[i];
        }
    };
    private static final String TAG = "FamilyHealthCardInfo";

    @SerializedName("agreeCount")
    private int mAgreeCount;

    @SerializedName("imageMap")
    private String mImageMap;

    @SerializedName("showType")
    private int mShowType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FamilyHealthCardInfo() {
        LogUtil.a(TAG, "FamilyHealthCardInfo create");
    }

    protected FamilyHealthCardInfo(Parcel parcel) {
        this.mImageMap = parcel.readString();
        this.mAgreeCount = parcel.readInt();
        this.mShowType = parcel.readInt();
    }

    public String getImageMap() {
        return this.mImageMap;
    }

    public LinkedHashMap<Long, rbe> getImageLinkedMap() {
        if (TextUtils.isEmpty(getImageMap()) || "{}".equals(getImageMap())) {
            return new LinkedHashMap<>();
        }
        try {
            return (LinkedHashMap) nrv.c(getImageMap(), new TypeToken<Map<Long, rbe>>() { // from class: com.huawei.ui.main.stories.userprofile.card.familycard.beans.FamilyHealthCardInfo.4
            }.getType());
        } catch (JsonSyntaxException | NumberFormatException e) {
            LogUtil.b(TAG, LogAnonymous.b(e));
            return new LinkedHashMap<>();
        }
    }

    public void setImageMap(Map<Long, rbe> map) {
        if (map == null || map.size() <= 0) {
            setImageMap("");
        } else {
            setImageMap(nrv.a(map));
        }
    }

    public void setImageMap(String str) {
        this.mImageMap = str;
    }

    public int getShowType() {
        return this.mShowType;
    }

    public void setShowType(int i) {
        this.mShowType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mImageMap);
        parcel.writeInt(this.mAgreeCount);
        parcel.writeInt(this.mShowType);
    }
}
