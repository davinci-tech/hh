package com.huawei.health.h5pro.jsbridge.system.option;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes8.dex */
public class LaunchOptionObj {

    @SerializedName(BleConstants.KEY_PATH)
    public String j = "";

    @SerializedName("isStatusBarTextBlack")
    public int h = 0;

    @SerializedName("isImmerse")
    public int c = 0;

    @SerializedName("isShowStatusBar")
    public int i = 0;

    @SerializedName("isNeedSoftInputAdapter")
    public int b = 0;

    @SerializedName("forceDarkMode")
    public int d = -1;

    @SerializedName("startFlag")
    public String f = IntentStartFlag.FLAG_ACTIVITY_STANDARD.flagKey;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("backgroundColor")
    public int f2414a = -1;

    @SerializedName("isKeepCustomizeJsModule")
    public int e = 0;

    public void setStartFlag(IntentStartFlag intentStartFlag) {
        this.f = intentStartFlag.flagKey;
    }

    public void setPath(String str) {
        this.j = str;
    }

    public void setIsStatusBarTextBlack(int i) {
        this.h = i;
    }

    public void setIsShowStatusBar(int i) {
        this.i = i;
    }

    public void setIsNeedSoftInputAdapter(int i) {
        this.b = i;
    }

    public void setIsKeepCustomizeJsModule(int i) {
        this.e = i;
    }

    public void setIsImmerse(int i) {
        this.c = i;
    }

    public void setForceDarkMode(int i) {
        this.d = i;
    }

    public void setBackgroundColor(int i) {
        this.f2414a = i;
    }

    public int getStartFlag() {
        IntentStartFlag intentStartFlag;
        if (TextUtils.equals(this.f, IntentStartFlag.FLAG_ACTIVITY_NEW_TASK.flagKey)) {
            intentStartFlag = IntentStartFlag.FLAG_ACTIVITY_NEW_TASK;
        } else {
            intentStartFlag = TextUtils.equals(this.f, IntentStartFlag.FLAG_ACTIVITY_SINGLE_TOP.flagKey) ? IntentStartFlag.FLAG_ACTIVITY_SINGLE_TOP : IntentStartFlag.FLAG_ACTIVITY_STANDARD;
        }
        return intentStartFlag.flagValue;
    }

    public String getPath() {
        return this.j;
    }

    public int getIsStatusBarTextBlack() {
        return this.h;
    }

    public int getIsShowStatusBar() {
        return this.i;
    }

    public int getIsNeedSoftInputAdapter() {
        return this.b;
    }

    public enum IntentStartFlag {
        FLAG_ACTIVITY_SINGLE_TOP("FLAG_ACTIVITY_SINGLE_TOP", 536870912),
        FLAG_ACTIVITY_NEW_TASK("FLAG_ACTIVITY_NEW_TASK", 268435456),
        FLAG_ACTIVITY_STANDARD("FLAG_ACTIVITY_STANDARD", 0);

        public final String flagKey;
        public final int flagValue;

        IntentStartFlag(String str, int i) {
            this.flagKey = str;
            this.flagValue = i;
        }
    }

    public int getIsKeepCustomizeJsModule() {
        return this.e;
    }

    public int getIsImmerse() {
        return this.c;
    }

    public int getForceDarkMode() {
        return this.d;
    }

    public int getBackgroundColor() {
        return this.f2414a;
    }
}
