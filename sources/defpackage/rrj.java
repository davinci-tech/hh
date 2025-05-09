package defpackage;

import java.util.List;

/* loaded from: classes9.dex */
public class rrj {
    private String dataTypeName;
    private long endTime;
    private long modifyTime;
    private long startTime;
    private List<rrn> value;

    public long getStartTime() {
        return this.startTime / 1000000;
    }

    public long getEndTime() {
        return this.endTime / 1000000;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
    }

    public List<rrn> getValue() {
        return this.value;
    }
}
