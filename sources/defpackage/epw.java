package defpackage;

import com.huawei.basefitnessadvice.model.intplan.StatInfo;

/* loaded from: classes3.dex */
public class epw implements StatInfo {
    private String c;
    private Object d;

    public epw(String str, Object obj) {
        this.c = str;
        this.d = obj;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.StatInfo
    public String getStatType() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.StatInfo
    public <T> T getValue() {
        return (T) this.d;
    }
}
