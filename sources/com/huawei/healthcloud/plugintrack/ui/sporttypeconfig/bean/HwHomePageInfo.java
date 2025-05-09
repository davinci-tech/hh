package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.R;
import defpackage.hpn;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class HwHomePageInfo {

    @SerializedName("cardImg")
    private String mCardImg;

    @SerializedName("multiImg")
    private HashMap<String, String> mMultiImg;

    public String getCardImg() {
        return this.mCardImg;
    }

    public void setCardImg(String str) {
        this.mCardImg = str;
    }

    private String getShowCardImg(String str) {
        HashMap<String, String> hashMap = this.mMultiImg;
        if (hashMap != null && !TextUtils.isEmpty(hashMap.get(str))) {
            return this.mMultiImg.get(str);
        }
        return this.mCardImg;
    }

    public Drawable getShowCardDrawable(String str) {
        return hpn.bog_(getShowCardImg(str), R.drawable.home_page_cross_country_race);
    }
}
