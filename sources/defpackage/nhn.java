package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nhn {

    @SerializedName("name")
    private String b;

    @SerializedName("cardId")
    private List<Integer> e = new ArrayList();

    public String b() {
        return this.b;
    }

    public List<Integer> e() {
        return this.e;
    }

    public String toString() {
        return "DomainTopCardId{mName=" + this.b + ", mCardIdList=" + this.e + '}';
    }
}
