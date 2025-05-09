package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class olu {

    @SerializedName("playList")
    private List<enq> b;

    @SerializedName("detailList")
    private List<oly> e;

    public List<enq> c() {
        return this.b;
    }

    public void d(List<enq> list) {
        this.b = list;
    }

    public List<oly> d() {
        return this.e;
    }

    public void e(List<oly> list) {
        this.e = list;
    }

    public String toString() {
        return "{,\"mPlayList\":" + this.b + ",\"mDetailList\":" + this.e + '}';
    }
}
