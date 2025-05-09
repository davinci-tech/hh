package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.List;

/* loaded from: classes6.dex */
public class EquipmentActionVideo implements Parcelable {
    public static final Parcelable.Creator<EquipmentActionVideo> CREATOR = new Parcelable.Creator<EquipmentActionVideo>() { // from class: com.huawei.pluginfitnessadvice.EquipmentActionVideo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: clP_, reason: merged with bridge method [inline-methods] */
        public EquipmentActionVideo createFromParcel(Parcel parcel) {
            return new EquipmentActionVideo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public EquipmentActionVideo[] newArray(int i) {
            return new EquipmentActionVideo[i];
        }
    };
    private static final String TAG = "Fitness_EquipmentActionVideo";

    @SerializedName(RecommendConstants.FILE_ID)
    private String mFieldId;

    @SerializedName("smartScreenImgUrl")
    private String mSmartScreenImgUrl;

    @SerializedName("videoSegmentList")
    private List<VideoSegment> mVideoSegmentList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private EquipmentActionVideo(b bVar) {
        this.mFieldId = bVar.b;
        this.mVideoSegmentList = bVar.d;
        this.mSmartScreenImgUrl = bVar.e;
    }

    protected EquipmentActionVideo(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "EquipmentActionVideo in == null");
            return;
        }
        this.mFieldId = parcel.readString();
        this.mVideoSegmentList = parcel.createTypedArrayList(VideoSegment.CREATOR);
        this.mSmartScreenImgUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mFieldId);
        parcel.writeTypedList(this.mVideoSegmentList);
        parcel.writeString(this.mSmartScreenImgUrl);
    }

    /* loaded from: classes9.dex */
    public static final class b {
        private String b;
        private List<VideoSegment> d;
        private String e;
    }

    public String getFieldId() {
        return this.mFieldId;
    }

    public List<VideoSegment> getVideoSegmentList() {
        return this.mVideoSegmentList;
    }

    public String getSmartScreenImgUrl() {
        return this.mSmartScreenImgUrl;
    }
}
