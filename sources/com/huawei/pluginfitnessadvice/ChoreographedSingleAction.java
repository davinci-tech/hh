package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ChoreographedSingleAction extends BaseChoreographyAction {
    public static final Parcelable.Creator<ChoreographedSingleAction> CREATOR = new Parcelable.Creator<ChoreographedSingleAction>() { // from class: com.huawei.pluginfitnessadvice.ChoreographedSingleAction.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: clH_, reason: merged with bridge method [inline-methods] */
        public ChoreographedSingleAction createFromParcel(Parcel parcel) {
            return new ChoreographedSingleAction(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ChoreographedSingleAction[] newArray(int i) {
            return new ChoreographedSingleAction[i];
        }
    };

    @SerializedName("action")
    private AtomicAction mAction;

    @SerializedName("commentaryGap")
    private List<Comment> mCommentaryGap;

    @SerializedName("commentaryTraining")
    private List<Comment> mCommentaryTraining;

    @SerializedName("extendProperty")
    private Map<String, String> mExtendProperty;

    @SerializedName("intensityConfig")
    private TargetConfig mIntensityConfig;

    @SerializedName("targetConfig")
    private TargetConfig mTargetConfig;

    public ChoreographedSingleAction() {
        this.mExtendProperty = new HashMap();
    }

    public ChoreographedSingleAction(Parcel parcel) {
        this.mExtendProperty = new HashMap();
        if (parcel != null) {
            this.mAction = (AtomicAction) parcel.readParcelable(AtomicAction.class.getClassLoader());
            this.mTargetConfig = (TargetConfig) parcel.readParcelable(TargetConfig.class.getClassLoader());
            this.mIntensityConfig = (TargetConfig) parcel.readParcelable(TargetConfig.class.getClassLoader());
            this.mCommentaryTraining = parcel.createTypedArrayList(Comment.CREATOR);
            this.mCommentaryGap = parcel.createTypedArrayList(Comment.CREATOR);
            this.mExtendProperty = parcel.readHashMap(HashMap.class.getClassLoader());
        }
    }

    public AtomicAction getAction() {
        return this.mAction;
    }

    public void setAction(AtomicAction atomicAction) {
        this.mAction = atomicAction;
    }

    public TargetConfig getTargetConfig() {
        return this.mTargetConfig;
    }

    public void setTargetConfig(TargetConfig targetConfig) {
        this.mTargetConfig = targetConfig;
    }

    public TargetConfig getIntensityConfig() {
        return this.mIntensityConfig;
    }

    public void setIntensityConfig(TargetConfig targetConfig) {
        this.mIntensityConfig = targetConfig;
    }

    public List<Comment> getCommentaryTraining() {
        return this.mCommentaryTraining;
    }

    public void setCommentaryTraining(List<Comment> list) {
        this.mCommentaryTraining = list;
    }

    public List<Comment> getCommentaryGap() {
        return this.mCommentaryGap;
    }

    public void setCommentaryGap(List<Comment> list) {
        this.mCommentaryGap = list;
    }

    public String getExtendProperty(String str) {
        Map<String, String> map = this.mExtendProperty;
        return map != null ? map.get(str) : "";
    }

    public void setExtendProperty(Map<String, String> map) {
        Map<String, String> map2;
        if (map == null || (map2 = this.mExtendProperty) == null) {
            return;
        }
        map2.clear();
        this.mExtendProperty.putAll(map);
    }

    public void putExtendProperty(String str, String str2) {
        if (this.mExtendProperty == null) {
            this.mExtendProperty = new HashMap();
        }
        if (str == null || str2 == null) {
            return;
        }
        this.mExtendProperty.put(str, str2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeParcelable(this.mAction, i);
            parcel.writeParcelable(this.mTargetConfig, i);
            parcel.writeParcelable(this.mIntensityConfig, i);
            parcel.writeList(this.mCommentaryTraining);
            parcel.writeList(this.mCommentaryGap);
            parcel.writeMap(this.mExtendProperty);
        }
    }
}
