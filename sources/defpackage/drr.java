package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class drr {

    @SerializedName("commentTime")
    private String b;

    @SerializedName("commentInfo")
    private String d;

    public String e() {
        return this.b;
    }

    public String toString() {
        return "CommentInfoMap{mCommentTime=" + this.b + ", mCommentInfo=" + this.d + "}";
    }
}
