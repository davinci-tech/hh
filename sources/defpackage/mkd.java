package defpackage;

import java.util.ArrayList;

/* loaded from: classes8.dex */
public class mkd implements Comparable {
    private String b;
    private ArrayList<c> c;
    private long d;

    public String e() {
        return this.b;
    }

    public void e(String str) {
        this.b = str;
    }

    public void d(long j) {
        this.d = j;
    }

    public ArrayList<c> a() {
        return this.c;
    }

    public void a(ArrayList<c> arrayList) {
        this.c = arrayList;
    }

    public String toString() {
        return "HistoricalReportDataBean{mYearOrMonthTitle='" + this.b + "', mYearOrMonthTimestamp=" + this.d + ", mOneMonthOrWeekRecordList=" + this.c + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj instanceof mkd) {
            return Long.compare(((mkd) obj).d, this.d);
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof mkd) && this.d == ((mkd) obj).d;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static class c implements Comparable {
        private long b;
        private long c;
        private int d;
        private String e;

        public long c() {
            return this.b;
        }

        public void c(long j) {
            this.b = j;
        }

        public long b() {
            return this.c;
        }

        public void e(long j) {
            this.c = j;
        }

        public void a(String str) {
            this.e = str;
        }

        public String e() {
            return this.e;
        }

        public int d() {
            return this.d;
        }

        public void c(int i) {
            this.d = i;
        }

        public String toString() {
            return "OneMonthOrWeekRecord{mStartTimestamp=" + this.b + ", mEndTimestamp=" + this.c + ", mRecordTitle='" + this.e + "', mReportNumber=" + this.d + '}';
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            if (obj instanceof c) {
                return Long.compare(((c) obj).b, this.b);
            }
            return -1;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            return (obj instanceof c) && this.b == ((c) obj).b;
        }

        public int hashCode() {
            return super.hashCode();
        }
    }
}
