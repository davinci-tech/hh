package com.huawei.health.healthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.TodoTaskInterface;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class HealthLifeTaskBean implements Parcelable, TodoTaskInterface {
    public static final Parcelable.Creator<HealthLifeTaskBean> CREATOR = new Parcelable.Creator<HealthLifeTaskBean>() { // from class: com.huawei.health.healthmodel.bean.HealthLifeTaskBean.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: ZD_, reason: merged with bridge method [inline-methods] */
        public HealthLifeTaskBean createFromParcel(Parcel parcel) {
            return new HealthLifeTaskBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HealthLifeTaskBean[] newArray(int i) {
            return new HealthLifeTaskBean[i];
        }
    };

    @SerializedName("backgroundColorResourcesId")
    private int mBackgroundColorResourcesId;

    @SerializedName("colorResourcesId")
    private int mColorResourcesId;

    @SerializedName("isComplete")
    private int mComplete;

    @SerializedName("dialogImageBean")
    private ImageBean mDialogImageBean;

    @SerializedName("healthLifeBean")
    private HealthLifeBean mHealthLifeBean;

    @SerializedName("iconResourcesId")
    private int mIconResourcesId;

    @SerializedName("id")
    private int mId;

    @SerializedName("imageBean")
    private ImageBean mImageBean;

    @SerializedName("name")
    private String mName;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private int mRecordDay;

    @SerializedName("value")
    private String mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HealthLifeTaskBean() {
        this.mName = "";
        this.mValue = "";
    }

    protected HealthLifeTaskBean(Parcel parcel) {
        this.mName = "";
        this.mValue = "";
        if (parcel == null) {
            return;
        }
        this.mId = parcel.readInt();
        this.mRecordDay = parcel.readInt();
        this.mComplete = parcel.readInt();
        this.mIconResourcesId = parcel.readInt();
        this.mColorResourcesId = parcel.readInt();
        this.mBackgroundColorResourcesId = parcel.readInt();
        this.mName = parcel.readString();
        this.mValue = parcel.readString();
        this.mImageBean = (ImageBean) parcel.readParcelable(ImageBean.class.getClassLoader());
        this.mDialogImageBean = (ImageBean) parcel.readParcelable(ImageBean.class.getClassLoader());
        this.mHealthLifeBean = (HealthLifeBean) parcel.readParcelable(HealthLifeBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mRecordDay);
        parcel.writeInt(this.mComplete);
        parcel.writeInt(this.mIconResourcesId);
        parcel.writeInt(this.mColorResourcesId);
        parcel.writeInt(this.mBackgroundColorResourcesId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mValue);
        parcel.writeParcelable(this.mImageBean, i);
        parcel.writeParcelable(this.mDialogImageBean, i);
        parcel.writeParcelable(this.mHealthLifeBean, i);
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getRecordDay() {
        return this.mRecordDay;
    }

    public void setRecordDay(int i) {
        this.mRecordDay = i;
    }

    public int getComplete() {
        return this.mComplete;
    }

    public void setComplete(int i) {
        this.mComplete = i;
    }

    public int getIconResourcesId() {
        return this.mIconResourcesId;
    }

    public void setIconResourcesId(int i) {
        this.mIconResourcesId = i;
    }

    public int getColorResourcesId() {
        return this.mColorResourcesId;
    }

    public void setColorResourcesId(int i) {
        this.mColorResourcesId = i;
    }

    public int getBackgroundColorResourcesId() {
        return this.mBackgroundColorResourcesId;
    }

    public void setBackgroundColorResourcesId(int i) {
        this.mBackgroundColorResourcesId = i;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mName = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public void setValue(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mValue = str;
    }

    public ImageBean getImageBean() {
        return this.mImageBean;
    }

    public void setImageBean(ImageBean imageBean) {
        this.mImageBean = imageBean;
    }

    public ImageBean getDialogImageBean() {
        return this.mDialogImageBean;
    }

    public void setDialogImageBean(ImageBean imageBean) {
        this.mDialogImageBean = imageBean;
    }

    public HealthLifeBean getHealthLifeBean() {
        return this.mHealthLifeBean;
    }

    public void setHealthLifeBean(HealthLifeBean healthLifeBean) {
        this.mHealthLifeBean = healthLifeBean;
    }

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public String getProgress() {
        return getValue();
    }

    @Override // com.huawei.operation.utils.TodoTaskInterface
    public int getIconId() {
        return getIconResourcesId();
    }

    public String toString() {
        return "HealthLifeTaskBean{mId=" + this.mId + ", mRecordDay=" + this.mRecordDay + ", mComplete=" + this.mComplete + ", mIconResourcesId=" + this.mIconResourcesId + ", mColorResourcesId=" + this.mColorResourcesId + ", mBackgroundColorResourcesId=" + this.mBackgroundColorResourcesId + ", mName=" + this.mName + ", mValue=" + this.mValue + ", mImageBean=" + this.mImageBean + ", mDialogImageBean=" + this.mDialogImageBean + ", mHealthLifeBean=" + this.mHealthLifeBean + "}";
    }
}
