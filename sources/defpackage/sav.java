package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class sav {

    @SerializedName("mediaEvaluationSwitch")
    private boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("countryBlocklist")
    private List<String> f16992a = new ArrayList();

    public boolean c() {
        return this.e;
    }

    public List<String> a() {
        return this.f16992a;
    }

    public String toString() {
        return "BetaSubmissionConfigBean MediaEvaluationSwitch = " + this.e + " CountryBlocklist = " + this.f16992a;
    }
}
