package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class iqg {
    private List<String> scopeList;
    private long updateTimeStamp;

    public List<String> getScopeList() {
        if (this.scopeList == null) {
            this.scopeList = new ArrayList();
        }
        return this.scopeList;
    }

    public long getUpdateTimeStamp() {
        return this.updateTimeStamp;
    }

    public void setUpdateTimeStamp(long j) {
        this.updateTimeStamp = j;
    }
}
