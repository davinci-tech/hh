package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class ccr {

    @SerializedName("countryCodeList")
    private List<String> c = new ArrayList();

    ccr() {
    }

    public List<String> a() {
        return this.c;
    }

    public String toString() {
        return "CountryCodeStruct{countryCodeList='" + this.c + '}';
    }
}
