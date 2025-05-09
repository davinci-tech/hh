package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class fcz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("questionResult")
    private List<fcy> f12451a;

    public void e(List<fcy> list) {
        this.f12451a = list;
    }

    public List<fcy> c() {
        return this.f12451a;
    }

    public String toString() {
        return "{\"QuestionnaireResult\": {\"questionnaire\":" + this.f12451a + "}}";
    }
}
