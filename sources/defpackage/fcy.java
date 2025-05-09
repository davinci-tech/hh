package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;

/* loaded from: classes.dex */
public class fcy {

    @SerializedName(JsUtil.SCORE)
    private int d;

    @SerializedName("question")
    private int e;

    public void c(int i) {
        this.e = i;
    }

    public void d(int i) {
        this.d = i;
    }

    public String toString() {
        return "{\"Questionnaire\": {\"question\":" + this.e + ", \"score\":" + this.d + "}}";
    }
}
