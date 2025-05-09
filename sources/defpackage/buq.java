package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes3.dex */
public class buq extends CloudCommonReponse {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(ParsedFieldTag.GOAL)
    private List<buo> f515a;

    public List<buo> a() {
        return this.f515a;
    }

    public void d(List<buo> list) {
        this.f515a = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetActiveTargetsRsp{goal=");
        stringBuffer.append(this.f515a);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
