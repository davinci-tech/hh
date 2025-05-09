package defpackage;

/* loaded from: classes.dex */
public class iea extends idy {
    public iea() {
        this(0, 0L, 0L, null, null);
    }

    public iea(int i, long j, long j2, String str, String str2) {
        super(i, j, j2, str2);
        this.mValueHolder.put("detail_data", str);
    }

    public String getSequenceData() {
        return this.mValueHolder.getAsString("detail_data");
    }

    public void setSequenceData(String str) {
        this.mValueHolder.put("detail_data", str);
    }

    @Override // defpackage.idy
    public String getMetaData() {
        return this.mValueHolder.getAsString("meta_data");
    }

    @Override // defpackage.idy
    public void setMetaData(String str) {
        this.mValueHolder.put("meta_data", str);
    }
}
