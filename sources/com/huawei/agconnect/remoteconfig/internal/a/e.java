package com.huawei.agconnect.remoteconfig.internal.a;

import com.huawei.agconnect.https.annotation.Result;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class e {

    @Result("experiments")
    private List<Map<String, String>> experiments;

    @Result("configItems")
    private List<b> parameters;

    @Result("ret")
    private a ret;

    @Result("tag")
    private String tag;

    public void setTag(String str) {
        this.tag = str;
    }

    public void setRet(a aVar) {
        this.ret = aVar;
    }

    public void setParameters(List<b> list) {
        this.parameters = list;
    }

    public void setExperiments(List<Map<String, String>> list) {
        this.experiments = list;
    }

    public boolean isSuccess() {
        a aVar = this.ret;
        return aVar != null && aVar.getCode() == 0;
    }

    public String getTag() {
        return this.tag;
    }

    public a getRet() {
        return this.ret;
    }

    public List<b> getParameters() {
        return this.parameters;
    }

    public List<Map<String, String>> getExperiments() {
        return this.experiments;
    }
}
