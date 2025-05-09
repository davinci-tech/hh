package com.google.protobuf;

@CheckReturnValue
/* loaded from: classes2.dex */
final class RawMessageInfo implements MessageInfo {
    private static final int IS_EDITION_BIT = 4;
    private static final int IS_PROTO2_BIT = 1;
    private final MessageLite defaultInstance;
    private final int flags;
    private final String info;
    private final Object[] objects;

    RawMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        this.defaultInstance = messageLite;
        this.info = str;
        this.objects = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 1;
        int i3 = 13;
        while (true) {
            char charAt2 = str.charAt(i2);
            if (charAt2 < 55296) {
                this.flags = i | (charAt2 << i3);
                return;
            } else {
                i |= (charAt2 & 8191) << i3;
                i3 += 13;
                i2++;
            }
        }
    }

    String getStringInfo() {
        return this.info;
    }

    Object[] getObjects() {
        return this.objects;
    }

    @Override // com.google.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    @Override // com.google.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        int i = this.flags;
        if ((i & 1) != 0) {
            return ProtoSyntax.PROTO2;
        }
        if ((i & 4) == 4) {
            return ProtoSyntax.EDITIONS;
        }
        return ProtoSyntax.PROTO3;
    }

    @Override // com.google.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        return (this.flags & 2) == 2;
    }
}
