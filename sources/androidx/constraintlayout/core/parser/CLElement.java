package androidx.constraintlayout.core.parser;

import androidx.core.location.LocationRequestCompat;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes8.dex */
public class CLElement {
    protected static int BASE_INDENT = 2;
    protected static int MAX_LINE = 80;
    private int line;
    protected CLContainer mContainer;
    private final char[] mContent;
    protected long start = -1;
    protected long end = LocationRequestCompat.PASSIVE_INTERVAL;

    public CLElement(char[] cArr) {
        this.mContent = cArr;
    }

    public boolean notStarted() {
        return this.start == -1;
    }

    public void setLine(int i) {
        this.line = i;
    }

    public int getLine() {
        return this.line;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public long getStart() {
        return this.start;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        if (this.end != LocationRequestCompat.PASSIVE_INTERVAL) {
            return;
        }
        this.end = j;
        if (CLParser.DEBUG) {
            System.out.println("closing " + hashCode() + " -> " + this);
        }
        CLContainer cLContainer = this.mContainer;
        if (cLContainer != null) {
            cLContainer.add(this);
        }
    }

    protected void addIndent(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
    }

    public String toString() {
        long j = this.start;
        long j2 = this.end;
        if (j > j2 || j2 == LocationRequestCompat.PASSIVE_INTERVAL) {
            return getClass() + " (INVALID, " + this.start + Constants.LINK + this.end + com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY;
        }
        return getStrClass() + " (" + this.start + " : " + this.end + ") <<" + new String(this.mContent).substring((int) this.start, ((int) this.end) + 1) + ">>";
    }

    protected String getStrClass() {
        String cls = getClass().toString();
        return cls.substring(cls.lastIndexOf(46) + 1);
    }

    protected String getDebugName() {
        if (!CLParser.DEBUG) {
            return "";
        }
        return getStrClass() + " -> ";
    }

    public String content() {
        String str = new String(this.mContent);
        long j = this.end;
        if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
            long j2 = this.start;
            if (j >= j2) {
                return str.substring((int) j2, ((int) j) + 1);
            }
        }
        int i = (int) this.start;
        return str.substring(i, i + 1);
    }

    public boolean isDone() {
        return this.end != LocationRequestCompat.PASSIVE_INTERVAL;
    }

    public void setContainer(CLContainer cLContainer) {
        this.mContainer = cLContainer;
    }

    public CLElement getContainer() {
        return this.mContainer;
    }

    public boolean isStarted() {
        return this.start > -1;
    }

    public int getInt() {
        if (this instanceof CLNumber) {
            return ((CLNumber) this).getInt();
        }
        return 0;
    }

    public float getFloat() {
        if (this instanceof CLNumber) {
            return ((CLNumber) this).getFloat();
        }
        return Float.NaN;
    }

    protected String toJSON() {
        return "";
    }

    protected String toFormattedJSON(int i, int i2) {
        return "";
    }
}
