package org.eclipse.californium.scandium.util;

import defpackage.vcb;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.eclipse.californium.elements.util.StandardCharsets;

/* loaded from: classes7.dex */
public class ServerName {

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f15914a = StandardCharsets.US_ASCII;
    private final int b;
    private final byte[] c;
    private final NameType e;

    private ServerName(NameType nameType, byte[] bArr) {
        this.e = nameType;
        this.c = bArr;
        this.b = (Arrays.hashCode(bArr) * 31) + nameType.hashCode();
    }

    public static ServerName d(NameType nameType, byte[] bArr) {
        if (nameType == null) {
            throw new NullPointerException("type must not be null");
        }
        if (bArr == null) {
            throw new NullPointerException("name must not be null");
        }
        if (nameType == NameType.HOST_NAME) {
            return c(new String(bArr, f15914a));
        }
        return new ServerName(nameType, bArr);
    }

    public static ServerName c(String str) {
        if (str == null) {
            throw new NullPointerException("host name must not be null");
        }
        if (vcb.a(str)) {
            return new ServerName(NameType.HOST_NAME, str.toLowerCase().getBytes(f15914a));
        }
        throw new IllegalArgumentException("not a valid host name");
    }

    public int c() {
        return this.c.length;
    }

    public byte[] a() {
        return this.c;
    }

    public String e() {
        return new String(this.c, f15914a);
    }

    public NameType b() {
        return this.e;
    }

    public int hashCode() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ServerName serverName = (ServerName) obj;
        if (this.e != serverName.e) {
            return false;
        }
        return Arrays.equals(this.c, serverName.c);
    }

    public enum NameType {
        HOST_NAME((byte) 0),
        UNDEFINED((byte) -1);

        private final byte code;

        NameType(byte b) {
            this.code = b;
        }

        public byte getCode() {
            return this.code;
        }

        public static NameType fromCode(byte b) {
            for (NameType nameType : values()) {
                if (nameType.code == b) {
                    return nameType;
                }
            }
            return UNDEFINED;
        }
    }
}
