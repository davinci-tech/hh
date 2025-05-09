package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class rwz {

    @SerializedName("level")
    private final int b;

    @SerializedName("joinTime")
    private final long c;

    @SerializedName("activityId")
    private final String d;

    @SerializedName("joinStatus")
    private final int e;

    public String d() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public String toString() {
        return "UserActivityInfo{activityId='" + this.d + "', level=" + this.b + ", joinStatus=" + this.e + ", joinTime=" + this.c + '}';
    }
}
