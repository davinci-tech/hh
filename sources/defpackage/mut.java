package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;

/* loaded from: classes6.dex */
public class mut extends fec {
    private static final long serialVersionUID = 1;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(RecommendConstants.DOWNLOAD_URL)
    private String f15186a;

    @SerializedName("isLoading")
    private boolean c;

    @SerializedName("isDownload")
    private boolean e;

    public void d(boolean z) {
        this.e = z;
    }

    public String c() {
        return this.f15186a;
    }

    public void c(String str) {
        this.f15186a = str;
    }

    public boolean e() {
        return this.c;
    }

    public void c(boolean z) {
        this.c = z;
    }
}
