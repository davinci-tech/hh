package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.List;

/* loaded from: classes3.dex */
public class CourseModel {

    @SerializedName("banner_img_url")
    private String mBannerImgUrl;

    @SerializedName("bin_file_size")
    private int mBinFileSize;

    @SerializedName("bin_file_url")
    private String mBinFileUrl;

    @SerializedName(alternate = {"courses", "musics"}, value = "mCourses")
    private List<Course> mCourses;

    @SerializedName("version")
    private int mVersion;

    public int getVersion() {
        return this.mVersion;
    }

    public String getBinFileUrl() {
        return this.mBinFileUrl;
    }

    public int getBinFileSize() {
        return this.mBinFileSize;
    }

    public String getBannerImgUrl() {
        return this.mBannerImgUrl;
    }

    public List<Course> getCourses() {
        return this.mCourses;
    }

    public static class Course implements Parcelable {
        public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel.Course.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Course createFromParcel(Parcel parcel) {
                return new Course(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Course[] newArray(int i) {
                return new Course[i];
            }
        };
        public static final int STATUS_ADDED = 2;
        public static final int STATUS_DISABLE = 3;
        public static final int STATUS_NO_ADD = 1;
        private int mBackgroundResourceId;

        @SerializedName("bpm")
        private int mBpm;

        @SerializedName("cal")
        private String mCal;

        @SerializedName("description")
        private String mDescription;

        @SerializedName("detail_desc")
        private String mDetailDesc;

        @SerializedName("device")
        private String mDevice;

        @SerializedName("file_size")
        private long mFileSize;

        @SerializedName("high_quality_size")
        private long mHighQualitySize;

        @SerializedName("id")
        private int mId;

        @SerializedName("isCheckboxCheck")
        private boolean mIsCheckboxCheck;

        @SerializedName("large_img_url")
        private String mLargeImgUrl;

        @SerializedName("level")
        private String mLevel;

        @SerializedName("name")
        private String mName;

        @SerializedName("notice")
        private String mNotice;

        @SerializedName("people")
        private String mPeople;

        @SerializedName("short_desc")
        private String mShortDesc;

        @SerializedName("small_img_url")
        private String mSmallImgUrl;

        @SerializedName("sound_url")
        private String mSoundUrl;
        private int mStatus;

        @SerializedName("suggestion")
        private String mSuggestion;

        @SerializedName("taboo")
        private String mTaboo;

        @SerializedName("time")
        private long mTime;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Course(Parcel parcel) {
            if (parcel != null) {
                this.mId = parcel.readInt();
                this.mName = parcel.readString();
                this.mLevel = parcel.readString();
                this.mTime = parcel.readLong();
                this.mFileSize = parcel.readLong();
                this.mHighQualitySize = parcel.readLong();
                this.mSoundUrl = parcel.readString();
                this.mSmallImgUrl = parcel.readString();
                this.mLargeImgUrl = parcel.readString();
                this.mShortDesc = parcel.readString();
                this.mDetailDesc = parcel.readString();
                this.mDescription = parcel.readString();
                this.mCal = parcel.readString();
                this.mBpm = parcel.readInt();
                this.mDevice = parcel.readString();
                this.mSuggestion = parcel.readString();
                this.mPeople = parcel.readString();
                this.mTaboo = parcel.readString();
                this.mNotice = parcel.readString();
            }
        }

        public Course(int i, int i2) {
            this(i, "", "");
            this.mStatus = i2;
        }

        public Course(int i, String str, String str2) {
            this.mId = i;
            this.mName = str;
            this.mDescription = str2;
        }

        public int getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public String getLevel() {
            return this.mLevel;
        }

        public long getTime() {
            return this.mTime;
        }

        public long getFileSize() {
            return this.mFileSize;
        }

        public long getHighQualitySize() {
            return this.mHighQualitySize;
        }

        public String getSoundUrl() {
            return this.mSoundUrl;
        }

        public String getSmallImgUrl() {
            return this.mSmallImgUrl;
        }

        public String getLargeImgUrl() {
            return this.mLargeImgUrl;
        }

        public String getShortDesc() {
            return this.mShortDesc;
        }

        public String getDetailDesc() {
            return this.mDetailDesc;
        }

        public int getBackgroundResourceId() {
            return this.mBackgroundResourceId;
        }

        public void setBackgroundResourceId(int i) {
            this.mBackgroundResourceId = i;
        }

        public boolean isCheckboxCheck() {
            return this.mIsCheckboxCheck;
        }

        public void setIsCheckboxCheck(boolean z) {
            this.mIsCheckboxCheck = z;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public void setStatus(int i) {
            this.mStatus = i;
        }

        public String getCal() {
            return this.mCal;
        }

        public int getBpm() {
            return this.mBpm;
        }

        public String getDevice() {
            return this.mDevice;
        }

        public String getSuggestion() {
            return this.mSuggestion;
        }

        public String getPeople() {
            return this.mPeople;
        }

        public String getTaboo() {
            return this.mTaboo;
        }

        public String getNotice() {
            return this.mNotice;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel != null) {
                parcel.writeInt(this.mId);
                parcel.writeString(this.mName);
                parcel.writeString(this.mLevel);
                parcel.writeLong(this.mTime);
                parcel.writeLong(this.mFileSize);
                parcel.writeLong(this.mHighQualitySize);
                parcel.writeString(this.mSoundUrl);
                parcel.writeString(this.mSmallImgUrl);
                parcel.writeString(this.mLargeImgUrl);
                parcel.writeString(this.mShortDesc);
                parcel.writeString(this.mDetailDesc);
                parcel.writeString(this.mDescription);
                parcel.writeString(this.mCal);
                parcel.writeInt(this.mBpm);
                parcel.writeString(this.mDevice);
                parcel.writeString(this.mSuggestion);
                parcel.writeString(this.mPeople);
                parcel.writeString(this.mTaboo);
                parcel.writeString(this.mNotice);
            }
        }

        /* loaded from: classes8.dex */
        public static class ActionsBean {

            @SerializedName("id")
            private String mId;

            @SerializedName("name")
            private String mName;

            @SerializedName(RemoteMessageConst.MessageBody.PARAM)
            private int mParam;

            @SerializedName("rest_time")
            private int mRestTime;

            @SerializedName("sounds")
            private List<SoundsBean> mSounds;

            @SerializedName("type")
            private int mType;

            public String getId() {
                return this.mId;
            }

            public String getName() {
                return this.mName;
            }

            public int getType() {
                return this.mType;
            }

            public int getParam() {
                return this.mParam;
            }

            public int getRestTime() {
                return this.mRestTime;
            }

            public List<SoundsBean> getSounds() {
                return this.mSounds;
            }

            public static class SoundsBean {

                @SerializedName("end_time")
                private int mEndTime;

                @SerializedName("start_time")
                private int mStartTime;

                public int getStartTime() {
                    return this.mStartTime;
                }

                public int getEndTime() {
                    return this.mEndTime;
                }
            }
        }
    }
}
