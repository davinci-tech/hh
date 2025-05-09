package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class EquipmentStage implements Parcelable {
    public static final Parcelable.Creator<EquipmentStage> CREATOR = new Parcelable.Creator<EquipmentStage>() { // from class: com.huawei.pluginfitnessadvice.EquipmentStage.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clQ_, reason: merged with bridge method [inline-methods] */
        public EquipmentStage createFromParcel(Parcel parcel) {
            return new EquipmentStage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public EquipmentStage[] newArray(int i) {
            return new EquipmentStage[i];
        }
    };
    private static final String TAG = "Fitness_EquipmentStage";

    @SerializedName("actionList")
    private List<EquipmentAction> mActionList;

    @SerializedName("stageName")
    private String mStageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected EquipmentStage(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "EquipmentStage in == null");
        } else {
            this.mStageName = parcel.readString();
            this.mActionList = parcel.createTypedArrayList(EquipmentAction.CREATOR);
        }
    }

    private EquipmentStage(c cVar) {
        this.mStageName = cVar.d;
        this.mActionList = cVar.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
        } else {
            parcel.writeString(this.mStageName);
            parcel.writeTypedList(this.mActionList);
        }
    }

    /* loaded from: classes9.dex */
    public static final class c {
        private String d;
        private List<EquipmentAction> e;
    }

    public String getStageName() {
        return this.mStageName;
    }

    public List<EquipmentAction> getActionList() {
        return this.mActionList;
    }
}
