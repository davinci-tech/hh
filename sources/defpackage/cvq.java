package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cvq extends cvr {
    private List<cvn> mConfigInfoList = new ArrayList(5);

    public List<cvn> getConfigInfoList() {
        return this.mConfigInfoList;
    }

    public void setConfigInfoList(List<cvn> list) {
        this.mConfigInfoList = list;
    }

    private String getConfigInfoString() {
        List<cvn> list = this.mConfigInfoList;
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(5);
        Iterator<cvn> it = this.mConfigInfoList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    @Override // defpackage.cvr
    public String toString() {
        return "SampleConfig {mSrcPkgName=" + getSrcPkgName() + ", mWearPkgName=" + getWearPkgName() + ", mCommandId=" + getCommandId() + ", mConfigInfoList=" + getConfigInfoString() + '}';
    }
}
