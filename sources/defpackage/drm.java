package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;

/* loaded from: classes3.dex */
public class drm {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(BleConstants.KEY_PATH)
    private String f11807a;

    @SerializedName(RecommendConstants.VER)
    private int b;

    @SerializedName(RecommendConstants.DOWNLOAD_URL)
    private String c;
    private int d;

    @SerializedName(RecommendConstants.FILE_ID)
    private String e;

    public String b() {
        return this.e;
    }

    public drm d(String str) {
        this.e = str;
        return this;
    }

    public int d() {
        return this.b;
    }

    public drm e(int i) {
        this.b = i;
        return this;
    }

    public drm e(String str) {
        this.c = str;
        return this;
    }

    public int c() {
        return this.d;
    }

    public drm c(int i) {
        this.d = i;
        return this;
    }

    public String a() {
        return this.f11807a;
    }

    public drm c(String str) {
        this.f11807a = str;
        return this;
    }

    public String toString() {
        return "VoiceConfigData{mFileName='" + this.e + "', mVersion=" + this.b + ", mDownloadUrl='" + this.c + "', mFileSize=" + this.d + ", mFilePath='" + this.f11807a + "'}";
    }
}
