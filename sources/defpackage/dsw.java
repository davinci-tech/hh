package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class dsw {

    @SerializedName("groupList")
    private List<dsv> mGroupLists;

    @SerializedName("version")
    private long mVersion;

    public long getVersion() {
        return this.mVersion;
    }

    public List<dsv> getGroupLists() {
        return this.mGroupLists;
    }
}
