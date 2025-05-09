package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;
import java.util.Map;

/* loaded from: classes9.dex */
public class kva extends kuz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("details")
    private String f14621a;

    @SerializedName("configSubType")
    private kun b;

    @SerializedName("fields")
    private Map<String, Object> c;

    @SerializedName("configDescription")
    private String d;

    @SerializedName("configData")
    private String e;

    @SerializedName("recordId")
    private String f;

    @SerializedName("subDataType")
    private kun g;

    @SerializedName("scopeDeviceType")
    private String h;

    @SerializedName(JsUtil.SUMMARIES_KEY)
    private Map<String, Object> i;

    @SerializedName("scopeApp")
    private String j;

    @Override // defpackage.kuz
    public String toString() {
        return "SampleData{fields=" + this.c + ", configSubtype=" + this.b + ", configData='" + this.e + "', scopeApp='" + this.j + "', scopeDeviceType='" + this.h + "', configDescription='" + this.d + "', subDataType=" + this.g + ", summaries=" + this.i + ", details='" + this.f14621a + "', recordId='" + this.f + "'}";
    }
}
