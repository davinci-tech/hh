package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class EquipmentWorkoutAction implements Parcelable {
    public static final Parcelable.Creator<EquipmentWorkoutAction> CREATOR = new Parcelable.Creator<EquipmentWorkoutAction>() { // from class: com.huawei.pluginfitnessadvice.EquipmentWorkoutAction.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: clR_, reason: merged with bridge method [inline-methods] */
        public EquipmentWorkoutAction createFromParcel(Parcel parcel) {
            return new EquipmentWorkoutAction(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public EquipmentWorkoutAction[] newArray(int i) {
            return new EquipmentWorkoutAction[i];
        }
    };
    private static final String TAG = "Fitness_EquipmentWorkoutAction";

    @SerializedName("equipmentType")
    private int mEquipmentType;

    @SerializedName("stageList")
    private List<EquipmentStage> mStageList;

    @SerializedName("videos")
    private List<EquipmentActionVideo> mVideoList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected EquipmentWorkoutAction(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "EquipmentWorkoutAction in == null");
            return;
        }
        this.mEquipmentType = parcel.readInt();
        this.mVideoList = parcel.createTypedArrayList(EquipmentActionVideo.CREATOR);
        this.mStageList = parcel.createTypedArrayList(EquipmentStage.CREATOR);
    }

    private EquipmentWorkoutAction(a aVar) {
        this.mEquipmentType = aVar.d;
        this.mVideoList = aVar.f8474a;
        this.mStageList = aVar.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeInt(this.mEquipmentType);
        parcel.writeTypedList(this.mVideoList);
        parcel.writeTypedList(this.mStageList);
    }

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private List<EquipmentActionVideo> f8474a;
        private int d;
        private List<EquipmentStage> e;
    }

    public int getEquipmentType() {
        return this.mEquipmentType;
    }

    public List<EquipmentActionVideo> getVideoList() {
        return this.mVideoList;
    }

    public List<EquipmentStage> getStageList() {
        return this.mStageList;
    }
}
