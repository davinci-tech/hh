package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import java.util.List;

/* loaded from: classes9.dex */
public class ppl {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("moreFavoritesUrl")
    private String f16217a;

    @SerializedName("pageNo")
    private int b;

    @SerializedName("priority")
    private int c;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int d;

    @SerializedName("resultCode")
    private int e;

    @SerializedName("sleepAudios")
    private List<ppn> f;

    @SerializedName("totalCount")
    private int h;

    @SerializedName("resultDesc")
    private String i;

    public int c() {
        return this.c;
    }

    public String b() {
        return this.f16217a;
    }

    public int d() {
        return this.e;
    }

    public List<ppn> e() {
        return this.f;
    }

    public String toString() {
        return "SleepFavoritesModel{mPageSize=" + this.d + ", mPageNo=" + this.b + ", mResultCode=" + this.e + ", mResultDesc='" + this.i + "', mTotalCount=" + this.h + ", mSleepAudios=" + this.f + ", mMoreFavoritesUrl='" + this.f16217a + "'}";
    }
}
