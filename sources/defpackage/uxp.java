package defpackage;

/* loaded from: classes7.dex */
public final class uxp {
    private final int d;

    public uxp(int i) {
        if (i < 0 || i > 255) {
            throw new IllegalArgumentException("No-Response option " + i + " must be between 0 and 255 inclusive");
        }
        this.d = i;
    }

    public byte[] c() {
        int i = this.d;
        return i == 0 ? vbj.c : new byte[]{(byte) i};
    }

    public uxs d() {
        return new uxs(258, c());
    }

    public String toString() {
        if ((this.d & 26) == 0) {
            return "ALL";
        }
        StringBuilder sb = new StringBuilder("NO ");
        if ((this.d & 2) != 0) {
            sb.append("SUCCESS,");
        }
        if ((this.d & 8) != 0) {
            sb.append("CLIENT_ERROR,");
        }
        if ((this.d & 16) != 0) {
            sb.append("SERVER_ERROR,");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof uxp) && this.d == ((uxp) obj).d;
    }

    public int hashCode() {
        return this.d;
    }
}
