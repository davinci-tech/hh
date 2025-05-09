package com.huawei.caas.messageservice;

import android.os.Bundle;
import android.util.Log;
import com.huawei.caas.messageservice.HwShareMsgInfo;

/* loaded from: classes8.dex */
public class LongVideoShareMsg extends HwShareMsgInfo {
    private static final String TAG = "LongVideoShareMsg";
    private String categoryType;
    private String mDescription;
    private String mTVUrl;
    private int playTime;
    private int sum;
    private String titlePictureUrl;
    private int totalTime;
    private String videoPlayPkgName;
    private String vodName;
    private String vodid;
    private int volumeIndex;

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected int shareType() {
        return 19;
    }

    public static class Builder extends HwShareMsgInfo.caassharea<Builder> {
        private String categoryType;
        private String mDescription;
        private String mTVUrl;
        private int playTime;
        private int sum;
        private String titlePictureUrl;
        private int totalTime;
        private String videoPlayPkgName;
        private String vodName;
        private String vodid;
        private int volumeIndex;

        public Builder setVolumeIndex(int i) {
            this.volumeIndex = i;
            return this;
        }

        public Builder setVodid(String str) {
            this.vodid = str;
            return this;
        }

        public Builder setVodName(String str) {
            this.vodName = str;
            return this;
        }

        public Builder setVideoPlayPkgName(String str) {
            this.videoPlayPkgName = str;
            return this;
        }

        public Builder setTotalTime(int i) {
            this.totalTime = i;
            return this;
        }

        public Builder setTitlePictureUrl(String str) {
            this.titlePictureUrl = str;
            return this;
        }

        public Builder setTVUrl(String str) {
            this.mTVUrl = str;
            return this;
        }

        public Builder setSum(int i) {
            this.sum = i;
            return this;
        }

        public Builder setPlayTime(int i) {
            this.playTime = i;
            return this;
        }

        public Builder setDescription(String str) {
            this.mDescription = str;
            return this;
        }

        public Builder setCategoryType(String str) {
            this.categoryType = str;
            return this;
        }

        public LongVideoShareMsg build() {
            if (this.titlePictureUrl == null) {
                return null;
            }
            return new LongVideoShareMsg(this);
        }
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected void shareDataSerialize(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "bundle is null");
            return;
        }
        super.shareDataSerialize(bundle);
        bundle.putString("LongVideoTVUrl", this.mTVUrl);
        bundle.putString("LongVideoPlayPkgName", this.videoPlayPkgName);
        bundle.putString("LongVideoId", this.vodid);
        bundle.putString("LongVideoName", this.vodName);
        bundle.putString("LongVideoType", this.categoryType);
        bundle.putInt("LongVideoSum", this.sum);
        bundle.putInt("LongVideoIndex", this.volumeIndex);
        bundle.putString("LongVideoPicUrl", this.titlePictureUrl);
        bundle.putInt("LongTotalTime", this.totalTime);
        bundle.putInt("LongPlayTime", this.playTime);
        bundle.putString("ShareDescription", this.mDescription);
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected boolean shareDataCheckArgs() {
        String str;
        String str2 = this.mTVUrl;
        if (str2 == null || str2.length() <= 10240) {
            String str3 = this.titlePictureUrl;
            if (str3 == null || str3.length() <= 10240) {
                return super.shareDataCheckArgs();
            }
            str = "titlePictureUrl data is invalid";
        } else {
            str = "TvUrl data is invalid";
        }
        Log.e(TAG, str);
        return false;
    }

    public LongVideoShareMsg(Builder builder) {
        super(builder);
        this.mTVUrl = builder.mTVUrl;
        this.videoPlayPkgName = builder.videoPlayPkgName;
        this.vodid = builder.vodid;
        this.vodName = builder.vodName;
        this.categoryType = builder.categoryType;
        this.sum = builder.sum;
        this.volumeIndex = builder.volumeIndex;
        this.titlePictureUrl = builder.titlePictureUrl;
        this.totalTime = builder.totalTime;
        this.playTime = builder.playTime;
        this.mDescription = builder.mDescription;
    }
}
