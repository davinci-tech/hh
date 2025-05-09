package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes8.dex */
public final class Int64Value extends GeneratedMessageV3 implements Int64ValueOrBuilder {
    private static final Int64Value DEFAULT_INSTANCE = new Int64Value();
    private static final Parser<Int64Value> PARSER = new AbstractParser<Int64Value>() { // from class: com.google.protobuf.Int64Value.1
        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseDelimitedFrom(InputStream inputStream) throws InvalidProtocolBufferException {
            return super.parseDelimitedFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return super.parseFrom(byteString);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
            return super.parseFrom(codedInputStream);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parseFrom(codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(InputStream inputStream) throws InvalidProtocolBufferException {
            return super.parseFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parseFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return super.parseFrom(byteBuffer);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parseFrom(byteBuffer, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return super.parseFrom(bArr);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            return super.parseFrom(bArr, i, i2);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parseFrom(bArr, i, i2, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parseFrom(bArr, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialDelimitedFrom(InputStream inputStream) throws InvalidProtocolBufferException {
            return super.parsePartialDelimitedFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parsePartialDelimitedFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(byteString);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(codedInputStream);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(InputStream inputStream) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(bArr);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(bArr, i, i2);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(bArr, i, i2, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractParser, com.google.protobuf.Parser
        public /* bridge */ /* synthetic */ Object parsePartialFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return super.parsePartialFrom(bArr, extensionRegistryLite);
        }

        @Override // com.google.protobuf.Parser
        public Int64Value parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Builder newBuilder = Int64Value.newBuilder();
            try {
                newBuilder.mergeFrom(codedInputStream, extensionRegistryLite);
                return newBuilder.buildPartial();
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(newBuilder.buildPartial());
            } catch (UninitializedMessageException e2) {
                throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(newBuilder.buildPartial());
            } catch (IOException e3) {
                throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(newBuilder.buildPartial());
            }
        }
    };
    public static final int VALUE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private long value_;

    private Int64Value(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.value_ = 0L;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Int64Value() {
        this.value_ = 0L;
        this.memoizedIsInitialized = (byte) -1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Int64Value();
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return WrappersProto.internal_static_google_protobuf_Int64Value_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WrappersProto.internal_static_google_protobuf_Int64Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Int64Value.class, Builder.class);
    }

    @Override // com.google.protobuf.Int64ValueOrBuilder
    public long getValue() {
        return this.value_;
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
        long j = this.value_;
        if (j != 0) {
            codedOutputStream.writeInt64(1, j);
        }
        getUnknownFields().writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.value_;
        int computeInt64Size = (j != 0 ? CodedOutputStream.computeInt64Size(1, j) : 0) + getUnknownFields().getSerializedSize();
        this.memoizedSize = computeInt64Size;
        return computeInt64Size;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Int64Value)) {
            return super.equals(obj);
        }
        Int64Value int64Value = (Int64Value) obj;
        return getValue() == int64Value.getValue() && getUnknownFields().equals(int64Value.getUnknownFields());
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((getDescriptor().hashCode() + 779) * 37) + 1) * 53) + Internal.hashLong(getValue())) * 29) + getUnknownFields().hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Int64Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Int64Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Int64Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Int64Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Int64Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Int64Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Int64Value parseFrom(InputStream inputStream) throws IOException {
        return (Int64Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Int64Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Int64Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Int64Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Int64Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Int64Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Int64Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Int64Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Int64Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Int64Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Int64Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Int64Value int64Value) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(int64Value);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Int64ValueOrBuilder {
        private int bitField0_;
        private long value_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WrappersProto.internal_static_google_protobuf_Int64Value_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WrappersProto.internal_static_google_protobuf_Int64Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Int64Value.class, Builder.class);
        }

        private Builder() {
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.value_ = 0L;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return WrappersProto.internal_static_google_protobuf_Int64Value_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Int64Value getDefaultInstanceForType() {
            return Int64Value.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Int64Value build() {
            Int64Value buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Int64Value buildPartial() {
            Int64Value int64Value = new Int64Value(this);
            if (this.bitField0_ != 0) {
                buildPartial0(int64Value);
            }
            onBuilt();
            return int64Value;
        }

        private void buildPartial0(Int64Value int64Value) {
            if ((this.bitField0_ & 1) != 0) {
                int64Value.value_ = this.value_;
            }
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
            if (message instanceof Int64Value) {
                return mergeFrom((Int64Value) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Int64Value int64Value) {
            if (int64Value == Int64Value.getDefaultInstance()) {
                return this;
            }
            if (int64Value.getValue() != 0) {
                setValue(int64Value.getValue());
            }
            mergeUnknownFields(int64Value.getUnknownFields());
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            extensionRegistryLite.getClass();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.value_ = codedInputStream.readInt64();
                                this.bitField0_ |= 1;
                            } else if (!super.parseUnknownField(codedInputStream, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.unwrapIOException();
                    }
                } finally {
                    onChanged();
                }
            }
            return this;
        }

        @Override // com.google.protobuf.Int64ValueOrBuilder
        public long getValue() {
            return this.value_;
        }

        public Builder setValue(long j) {
            this.value_ = j;
            this.bitField0_ |= 1;
            onChanged();
            return this;
        }

        public Builder clearValue() {
            this.bitField0_ &= -2;
            this.value_ = 0L;
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

    public static Int64Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Int64Value of(long j) {
        return newBuilder().setValue(j).build();
    }

    public static Parser<Int64Value> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Int64Value> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Int64Value getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
