package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.tencent.open.SocialOperation;

/* loaded from: classes6.dex */
public class nri {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(RecommendConstants.DOWNLOAD_URL)
    private String f15461a;

    @SerializedName(SocialOperation.GAME_SIGNATURE)
    private String b;

    @SerializedName(RecommendConstants.FILE_ID)
    private String c;

    @SerializedName(RecommendConstants.VER)
    private String e;

    public String b() {
        return this.f15461a;
    }

    public String a() {
        return this.e;
    }

    public String d() {
        return this.c;
    }
}
