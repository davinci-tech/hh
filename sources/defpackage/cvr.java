package defpackage;

/* loaded from: classes3.dex */
public class cvr {
    private int mCommandId = -1;
    private String mSrcPkgName;
    private String mWearPkgName;

    public String getSrcPkgName() {
        return this.mSrcPkgName;
    }

    public void setSrcPkgName(String str) {
        this.mSrcPkgName = str;
    }

    public String getWearPkgName() {
        return this.mWearPkgName;
    }

    public void setWearPkgName(String str) {
        this.mWearPkgName = str;
    }

    public int getCommandId() {
        return this.mCommandId;
    }

    public void setCommandId(int i) {
        this.mCommandId = i;
    }

    public String toString() {
        return "SampleBase{mSrcPkgName=" + this.mSrcPkgName + ", mWearPkgName=" + this.mWearPkgName + ", mCommandId=" + this.mCommandId + '}';
    }
}
