package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class rbl {

    @SerializedName("memberHuid")
    private String b;

    @SerializedName("postId")
    private String c;

    public String c() {
        return this.c;
    }

    public String toString() {
        return "MemberPostId{mMemberHuid='" + this.b + "', mPostId='" + this.c + "'}";
    }
}
