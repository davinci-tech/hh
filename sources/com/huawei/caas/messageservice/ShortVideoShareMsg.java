package com.huawei.caas.messageservice;

import android.os.Bundle;
import android.util.Log;
import com.huawei.caas.messageservice.HwShareMsgInfo;

/* loaded from: classes8.dex */
public class ShortVideoShareMsg extends HwShareMsgInfo {
    private static final String TAG = "ShortVideoShareMsg";
    private String mQuickPlayUrl;

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected int shareType() {
        return 16;
    }

    public static class Builder extends HwShareMsgInfo.caassharea<Builder> {
        private String mQuickPlayUrl;

        public Builder setQuickPlayUrl(String str) {
            this.mQuickPlayUrl = str;
            return this;
        }

        public ShortVideoShareMsg build() {
            if (this.mQuickPlayUrl == null) {
                return null;
            }
            return new ShortVideoShareMsg(this);
        }
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected void shareDataSerialize(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "bundle is null");
        } else {
            super.shareDataSerialize(bundle);
            bundle.putString("ShareUrlExt", this.mQuickPlayUrl);
        }
    }

    @Override // com.huawei.caas.messageservice.HwShareMsgInfo
    protected boolean shareDataCheckArgs() {
        String str = this.mQuickPlayUrl;
        if (str == null || str.length() <= 10240) {
            return super.shareDataCheckArgs();
        }
        Log.e(TAG, "data URL is invalid");
        return false;
    }

    private ShortVideoShareMsg(Builder builder) {
        super(builder);
        this.mQuickPlayUrl = builder.mQuickPlayUrl;
    }
}
