package com.huawei.hms.framework.network.restclient.hwhttp;

import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public final class MediaType {
    private final String charset;
    private final String mediaType;
    private final String subtype;
    private final String type;

    private MediaType(String str, String str2, String str3, String str4) {
        this.charset = str4;
        this.mediaType = str;
        this.subtype = str3;
        this.type = str2;
    }

    public static MediaType parse(String str) {
        com.huawei.hms.network.base.common.MediaType parse = com.huawei.hms.network.base.common.MediaType.parse(str);
        if (parse == null) {
            return null;
        }
        Charset charset = parse.charset();
        return new MediaType(str, parse.type(), parse.subtype(), charset != null ? charset.toString() : null);
    }

    public static MediaType get(String str) {
        com.huawei.hms.network.base.common.MediaType mediaType = com.huawei.hms.network.base.common.MediaType.get(str);
        Charset charset = mediaType.charset();
        return new MediaType(str, mediaType.type(), mediaType.subtype(), charset == null ? null : charset.toString());
    }

    public String type() {
        return this.type;
    }

    public String subtype() {
        return this.subtype;
    }

    public Charset charset() {
        String str = this.charset;
        if (str != null) {
            return Charset.forName(str);
        }
        return null;
    }

    public String toString() {
        return this.mediaType;
    }

    public Charset charset(Charset charset) {
        String str = this.charset;
        return str != null ? Charset.forName(str) : charset;
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof MediaType) && ((MediaType) obj).mediaType.equals(this.mediaType);
    }
}
