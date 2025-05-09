package com.huawei.caas.messageservice;

import android.os.Bundle;
import android.util.Log;

/* loaded from: classes8.dex */
public class HwShareMsgInfo {
    private static final String TAG = "HwShareMsgInfo";
    private String mMultiWindowUrl;
    private String mTitle;
    private String mUrl;

    protected int shareType() {
        return 0;
    }

    public static class caassharea<T extends caassharea> {
        private String mMultiWindowUrl;
        private String mTitle;
        private String mUrl;

        public T setUrl(String str) {
            this.mUrl = str;
            return this;
        }

        public T setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public T setMultiUrl(String str) {
            this.mMultiWindowUrl = str;
            return this;
        }
    }

    protected void shareDataSerialize(Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "bundle is null");
            return;
        }
        bundle.putString("ShareTitle", this.mTitle);
        bundle.putString("ShareUrl", this.mUrl);
        bundle.putString("ShareMultiUrl", this.mMultiWindowUrl);
    }

    protected boolean shareDataCheckArgs() {
        String str;
        String str2 = this.mTitle;
        if (str2 == null || str2.length() <= 512) {
            String str3 = this.mUrl;
            if (str3 == null || str3.length() <= 10240) {
                String str4 = this.mMultiWindowUrl;
                if (str4 != null && str4.length() > 10240) {
                    str = "check args fail, multi window url address is invalid";
                } else if (this.mUrl == null && this.mMultiWindowUrl == null) {
                    str = "check args fail, At least one of mUrl and multiWindowUrl is not empty.";
                } else {
                    if (this.mTitle != null) {
                        return true;
                    }
                    str = "check args fail, title can not be null";
                }
            } else {
                str = "check args fail, url address is invalid";
            }
        } else {
            str = "check args fail, title is invalid";
        }
        Log.e(TAG, str);
        return false;
    }

    public <T extends caassharea> HwShareMsgInfo(caassharea caasshareaVar) {
        this.mTitle = caasshareaVar.mTitle;
        this.mUrl = caasshareaVar.mUrl;
        this.mMultiWindowUrl = caasshareaVar.mMultiWindowUrl;
    }

    private HwShareMsgInfo() {
    }
}
