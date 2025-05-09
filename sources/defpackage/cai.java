package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class cai {

    @SerializedName("sportWordsList")
    private ArrayList<cal> c;

    @SerializedName("version")
    private String d;

    cai() {
    }

    public String a() {
        return this.d;
    }

    public ArrayList<cal> c() {
        return (ArrayList) this.c.clone();
    }
}
