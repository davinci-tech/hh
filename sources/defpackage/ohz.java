package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.operation.utils.Constants;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public class ohz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("cardShowGroups")
    private List<ohx> f15688a;

    @SerializedName("updateConfigs")
    private List<Object> b;

    @SerializedName("configId")
    private String c;

    @SerializedName("cardConfigList")
    private LinkedList<CardConfig> d;

    @SerializedName("isEdited")
    private boolean e = false;

    @SerializedName("version")
    private String i;

    public boolean e() {
        return this.e;
    }

    public String b() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public void e(boolean z) {
        this.e = z;
    }

    public String c() {
        return this.c;
    }

    public LinkedList<CardConfig> d() {
        return this.d;
    }

    public void d(LinkedList<CardConfig> linkedList) {
        this.d = linkedList;
    }

    public List<ohx> a() {
        return this.f15688a;
    }

    public void b(List<ohx> list) {
        this.f15688a = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FunctionCardSetConfig{mVersion='");
        sb.append(this.i);
        sb.append("', mIsEdited=");
        sb.append(this.e);
        sb.append(", mConfigId='");
        sb.append(this.c);
        sb.append("', mCardConfigList=");
        LinkedList<CardConfig> linkedList = this.d;
        String str = Constants.NULL;
        sb.append(linkedList == null ? Constants.NULL : Arrays.toString(linkedList.toArray()));
        sb.append(", mUpdateConfigs=");
        List<Object> list = this.b;
        sb.append(list == null ? Constants.NULL : Arrays.toString(list.toArray()));
        sb.append(", mCardShowGroups=");
        List<ohx> list2 = this.f15688a;
        if (list2 != null) {
            str = Arrays.toString(list2.toArray());
        }
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }
}
