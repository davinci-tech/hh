package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.List;

/* loaded from: classes3.dex */
public class dqm {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(RecommendConstants.VER)
    private int f11787a;

    @SerializedName("name")
    protected String b;

    @SerializedName("country")
    private String d;

    @SerializedName("featureList")
    private List<dqo> e;

    public List<dqo> a() {
        return this.e;
    }

    public int e() {
        return this.f11787a;
    }
}
