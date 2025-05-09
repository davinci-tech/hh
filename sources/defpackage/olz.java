package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import java.util.List;

/* loaded from: classes6.dex */
public class olz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int f15778a;

    @SerializedName("resultCode")
    private String b;

    @SerializedName("recommendMore")
    private List<Object> c;

    @SerializedName("hasMore")
    private boolean d;

    @SerializedName("audioWorkoutList")
    private List<omb> e;

    @SerializedName("topicBackImgUrl")
    private String f;

    @SerializedName("resultDesc")
    private String g;

    @SerializedName("topicBackLargeImgUrl")
    private String h;

    @SerializedName("topicDesc")
    private String i;

    @SerializedName("subTitle")
    private String j;

    @SerializedName("topicName")
    private String l;

    @SerializedName("total")
    private int n;

    public String b() {
        return this.l;
    }

    public String d() {
        return this.b;
    }

    public String a() {
        return this.g;
    }

    public int c() {
        return this.n;
    }

    public List<omb> e() {
        return this.e;
    }

    public String toString() {
        return "AudioWorkoutByTopicIdRsp{resultCode='" + this.b + "', resultDesc='" + this.g + "', total=" + this.n + ", pageSize=" + this.f15778a + ", hasMore=" + this.d + ", topicName=" + this.l + ", topicDesc=" + this.i + ", subTitle=" + this.j + ", topicBackImgUrl=" + this.f + ", topicBackLargeImgUrl=" + this.h + ", audioWorkoutList=" + this.e + ", recommendMore=" + this.c + '}';
    }
}
