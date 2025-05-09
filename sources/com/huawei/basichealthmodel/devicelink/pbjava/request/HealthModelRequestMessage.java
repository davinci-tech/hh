package com.huawei.basichealthmodel.devicelink.pbjava.request;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public final class HealthModelRequestMessage {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\rrequest.proto\u00125com.huawei.basichealthmodel.devicelink.pbjava.request\";\n\u0007Request\u0012\u000f\n\u0007msgType\u0018\u0001 \u0001(\u0005\u0012\u000e\n\u0006msgVer\u0018\u0002 \u0001(\u0002\u0012\u000f\n\u0007msgBody\u0018\u0003 \u0001(\fB\u001bB\u0019HealthModelRequestMessageb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_fieldAccessorTable;

    public interface RequestOrBuilder extends MessageOrBuilder {
        ByteString getMsgBody();

        int getMsgType();

        float getMsgVer();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private HealthModelRequestMessage() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class Request extends GeneratedMessageV3 implements RequestOrBuilder {
        public static final int MSGBODY_FIELD_NUMBER = 3;
        public static final int MSGTYPE_FIELD_NUMBER = 1;
        public static final int MSGVER_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private ByteString msgBody_;
        private int msgType_;
        private float msgVer_;
        private static final Request DEFAULT_INSTANCE = new Request();
        private static final Parser<Request> PARSER = new AbstractParser<Request>() { // from class: com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.Request.1
            @Override // com.google.protobuf.Parser
            public Request parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Request(codedInputStream, extensionRegistryLite);
            }
        };

        private Request(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Request() {
            this.memoizedIsInitialized = (byte) -1;
            this.msgBody_ = ByteString.EMPTY;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Request();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Request(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.msgType_ = codedInputStream.readInt32();
                            } else if (readTag == 21) {
                                this.msgVer_ = codedInputStream.readFloat();
                            } else if (readTag == 26) {
                                this.msgBody_ = codedInputStream.readBytes();
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthModelRequestMessage.internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthModelRequestMessage.internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_fieldAccessorTable.ensureFieldAccessorsInitialized(Request.class, Builder.class);
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.RequestOrBuilder
        public int getMsgType() {
            return this.msgType_;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.RequestOrBuilder
        public float getMsgVer() {
            return this.msgVer_;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.RequestOrBuilder
        public ByteString getMsgBody() {
            return this.msgBody_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.msgType_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            float f = this.msgVer_;
            if (f != 0.0f) {
                codedOutputStream.writeFloat(2, f);
            }
            if (!this.msgBody_.isEmpty()) {
                codedOutputStream.writeBytes(3, this.msgBody_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.msgType_;
            int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
            float f = this.msgVer_;
            if (f != 0.0f) {
                computeInt32Size += CodedOutputStream.computeFloatSize(2, f);
            }
            if (!this.msgBody_.isEmpty()) {
                computeInt32Size += CodedOutputStream.computeBytesSize(3, this.msgBody_);
            }
            int serializedSize = computeInt32Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Request)) {
                return super.equals(obj);
            }
            Request request = (Request) obj;
            return getMsgType() == request.getMsgType() && Float.floatToIntBits(getMsgVer()) == Float.floatToIntBits(request.getMsgVer()) && getMsgBody().equals(request.getMsgBody()) && this.unknownFields.equals(request.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode();
            int msgType = getMsgType();
            int floatToIntBits = ((((((((((((((hashCode + 779) * 37) + 1) * 53) + msgType) * 37) + 2) * 53) + Float.floatToIntBits(getMsgVer())) * 37) + 3) * 53) + getMsgBody().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = floatToIntBits;
            return floatToIntBits;
        }

        public static Request parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Request parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Request parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Request parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Request parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Request parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Request parseFrom(InputStream inputStream) throws IOException {
            return (Request) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Request parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Request) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Request parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Request) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Request parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Request) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Request parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Request) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Request parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Request) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Request request) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(request);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RequestOrBuilder {
            private ByteString msgBody_;
            private int msgType_;
            private float msgVer_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthModelRequestMessage.internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthModelRequestMessage.internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_fieldAccessorTable.ensureFieldAccessorsInitialized(Request.class, Builder.class);
            }

            private Builder() {
                this.msgBody_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.msgBody_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Request.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.msgType_ = 0;
                this.msgVer_ = 0.0f;
                this.msgBody_ = ByteString.EMPTY;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return HealthModelRequestMessage.internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Request getDefaultInstanceForType() {
                return Request.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Request build() {
                Request buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Request buildPartial() {
                Request request = new Request(this);
                request.msgType_ = this.msgType_;
                request.msgVer_ = this.msgVer_;
                request.msgBody_ = this.msgBody_;
                onBuilt();
                return request;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo113clone() {
                return (Builder) super.mo113clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof Request) {
                    return mergeFrom((Request) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Request request) {
                if (request == Request.getDefaultInstance()) {
                    return this;
                }
                if (request.getMsgType() != 0) {
                    setMsgType(request.getMsgType());
                }
                if (request.getMsgVer() != 0.0f) {
                    setMsgVer(request.getMsgVer());
                }
                if (request.getMsgBody() != ByteString.EMPTY) {
                    setMsgBody(request.getMsgBody());
                }
                mergeUnknownFields(request.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.Request.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser r0 = com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.Request.access$1000()     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage$Request r2 = (com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.Request) r2     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    if (r2 == 0) goto Lf
                    r1.mergeFrom(r2)
                Lf:
                    return r1
                L10:
                    r2 = move-exception
                    goto L20
                L12:
                    r2 = move-exception
                    com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L10
                    com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage$Request r3 = (com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.Request) r3     // Catch: java.lang.Throwable -> L10
                    java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1e
                    throw r2     // Catch: java.lang.Throwable -> L1e
                L1e:
                    r2 = move-exception
                    goto L21
                L20:
                    r3 = 0
                L21:
                    if (r3 == 0) goto L26
                    r1.mergeFrom(r3)
                L26:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.Request.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage$Request$Builder");
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.RequestOrBuilder
            public int getMsgType() {
                return this.msgType_;
            }

            public Builder setMsgType(int i) {
                this.msgType_ = i;
                onChanged();
                return this;
            }

            public Builder clearMsgType() {
                this.msgType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.RequestOrBuilder
            public float getMsgVer() {
                return this.msgVer_;
            }

            public Builder setMsgVer(float f) {
                this.msgVer_ = f;
                onChanged();
                return this;
            }

            public Builder clearMsgVer() {
                this.msgVer_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestMessage.RequestOrBuilder
            public ByteString getMsgBody() {
                return this.msgBody_;
            }

            public Builder setMsgBody(ByteString byteString) {
                byteString.getClass();
                this.msgBody_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearMsgBody() {
                this.msgBody_ = Request.getDefaultInstance().getMsgBody();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static Request getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Request> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Request> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Request getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_descriptor = descriptor2;
        internal_static_com_huawei_basichealthmodel_devicelink_pbjava_request_Request_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"MsgType", "MsgVer", "MsgBody"});
    }
}
