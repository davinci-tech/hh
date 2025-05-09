package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.knit.ui.KnitFragment;
import java.util.List;

/* loaded from: classes3.dex */
public class dsu {

    @SerializedName("calories")
    private int mCalories;

    @SerializedName("courseName")
    private String mCourseName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("detailImage")
    private String mDetailImage;

    @SerializedName("difficulty")
    private int mDifficulty;

    @SerializedName(KnitFragment.KEY_EXTRA)
    private String mExtra;
    private String mGroupName;

    @SerializedName("id")
    private String mId;

    @SerializedName("level")
    private String mLevel;

    @SerializedName("levelName")
    private String mLevelName;

    @SerializedName("lowerHeartRateAdjust")
    private int mLowerHeartRateAdjust;

    @SerializedName("stageList")
    private List<dsx> mStageLists;

    @SerializedName("thumbnail")
    private String mThumbnail;

    @SerializedName("totalTime")
    private int mTotalTime;

    @SerializedName("upperHeartRateAdjust")
    private int mUpperHeartRateAdjust;

    @SerializedName("version")
    private String mVersion;

    public String getDetailImage() {
        return this.mDetailImage;
    }

    public String getThumbnail() {
        return this.mThumbnail;
    }

    public String getId() {
        return this.mId;
    }

    public String getCourseName() {
        return this.mCourseName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getTotalTime() {
        return this.mTotalTime;
    }

    public String getLevel() {
        return this.mLevel;
    }

    public int getUpperHeartRateAdjust() {
        return this.mUpperHeartRateAdjust;
    }

    public int getLowerHeartRateAdjust() {
        return this.mLowerHeartRateAdjust;
    }

    public int getCalories() {
        return this.mCalories;
    }

    public List<dsx> getStageList() {
        return this.mStageLists;
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    public void setGroupName(String str) {
        this.mGroupName = str;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public int getDifficulty() {
        return this.mDifficulty;
    }
}
