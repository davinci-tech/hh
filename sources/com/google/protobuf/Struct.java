package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MapFieldBuilder;
import com.google.protobuf.Value;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes8.dex */
public final class Struct extends GeneratedMessageV3 implements StructOrBuilder {
    public static final int FIELDS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private MapField<String, Value> fields_;
    private byte memoizedIsInitialized;
    private static final Struct DEFAULT_INSTANCE = new Struct();
    private static final Parser<Struct> PARSER = new AbstractParser<Struct>() { // from class: com.google.protobuf.Struct.1
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
        public Struct parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Builder newBuilder = Struct.newBuilder();
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

    private Struct(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Struct() {
        this.memoizedIsInitialized = (byte) -1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Struct();
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StructProto.internal_static_google_protobuf_Struct_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int i) {
        if (i == 1) {
            return internalGetFields();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized(Struct.class, Builder.class);
    }

    static final class FieldsDefaultEntryHolder {
        static final MapEntry<String, Value> defaultEntry = MapEntry.newDefaultInstance(StructProto.internal_static_google_protobuf_Struct_FieldsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

        private FieldsDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Value> internalGetFields() {
        MapField<String, Value> mapField = this.fields_;
        return mapField == null ? MapField.emptyMapField(FieldsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.protobuf.StructOrBuilder
    public int getFieldsCount() {
        return internalGetFields().getMap().size();
    }

    @Override // com.google.protobuf.StructOrBuilder
    public boolean containsFields(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        return internalGetFields().getMap().containsKey(str);
    }

    @Override // com.google.protobuf.StructOrBuilder
    @Deprecated
    public Map<String, Value> getFields() {
        return getFieldsMap();
    }

    @Override // com.google.protobuf.StructOrBuilder
    public Map<String, Value> getFieldsMap() {
        return internalGetFields().getMap();
    }

    @Override // com.google.protobuf.StructOrBuilder
    public Value getFieldsOrDefault(String str, Value value) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, Value> map = internalGetFields().getMap();
        return map.containsKey(str) ? map.get(str) : value;
    }

    @Override // com.google.protobuf.StructOrBuilder
    public Value getFieldsOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, Value> map = internalGetFields().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str);
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
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetFields(), FieldsDefaultEntryHolder.defaultEntry, 1);
        getUnknownFields().writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (Map.Entry<String, Value> entry : internalGetFields().getMap().entrySet()) {
            i2 += CodedOutputStream.computeMessageSize(1, FieldsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = i2 + getUnknownFields().getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Struct)) {
            return super.equals(obj);
        }
        Struct struct = (Struct) obj;
        return internalGetFields().equals(struct.internalGetFields()) && getUnknownFields().equals(struct.getUnknownFields());
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (!internalGetFields().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 1) * 53) + internalGetFields().hashCode();
        }
        int hashCode2 = (hashCode * 29) + getUnknownFields().hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Struct parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Struct parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Struct parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Struct parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Struct parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Struct parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Struct parseFrom(InputStream inputStream) throws IOException {
        return (Struct) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Struct parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Struct) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Struct parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Struct) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Struct parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Struct) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Struct parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Struct) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Struct parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Struct) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Struct struct) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(struct);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StructOrBuilder {
        private static final FieldsConverter fieldsConverter = new FieldsConverter();
        private int bitField0_;
        private MapFieldBuilder<String, ValueOrBuilder, Value, Value.Builder> fields_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StructProto.internal_static_google_protobuf_Struct_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapFieldReflectionAccessor internalGetMapFieldReflection(int i) {
            if (i == 1) {
                return internalGetFields();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int i) {
            if (i == 1) {
                return internalGetMutableFields();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized(Struct.class, Builder.class);
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
            internalGetMutableFields().clear();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return StructProto.internal_static_google_protobuf_Struct_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Struct getDefaultInstanceForType() {
            return Struct.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Struct build() {
            Struct buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Struct buildPartial() {
            Struct struct = new Struct(this);
            if (this.bitField0_ != 0) {
                buildPartial0(struct);
            }
            onBuilt();
            return struct;
        }

        private void buildPartial0(Struct struct) {
            if ((this.bitField0_ & 1) != 0) {
                struct.fields_ = internalGetFields().build(FieldsDefaultEntryHolder.defaultEntry);
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
            if (message instanceof Struct) {
                return mergeFrom((Struct) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Struct struct) {
            if (struct == Struct.getDefaultInstance()) {
                return this;
            }
            internalGetMutableFields().mergeFrom(struct.internalGetFields());
            this.bitField0_ |= 1;
            mergeUnknownFields(struct.getUnknownFields());
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
                            if (readTag == 10) {
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(FieldsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                internalGetMutableFields().ensureBuilderMap().put((String) mapEntry.getKey(), (ValueOrBuilder) mapEntry.getValue());
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

        static final class FieldsConverter implements MapFieldBuilder.Converter<String, ValueOrBuilder, Value> {
            private FieldsConverter() {
            }

            @Override // com.google.protobuf.MapFieldBuilder.Converter
            public Value build(ValueOrBuilder valueOrBuilder) {
                return valueOrBuilder instanceof Value ? (Value) valueOrBuilder : ((Value.Builder) valueOrBuilder).build();
            }

            @Override // com.google.protobuf.MapFieldBuilder.Converter
            public MapEntry<String, Value> defaultEntry() {
                return FieldsDefaultEntryHolder.defaultEntry;
            }
        }

        private MapFieldBuilder<String, ValueOrBuilder, Value, Value.Builder> internalGetFields() {
            MapFieldBuilder<String, ValueOrBuilder, Value, Value.Builder> mapFieldBuilder = this.fields_;
            return mapFieldBuilder == null ? new MapFieldBuilder<>(fieldsConverter) : mapFieldBuilder;
        }

        private MapFieldBuilder<String, ValueOrBuilder, Value, Value.Builder> internalGetMutableFields() {
            if (this.fields_ == null) {
                this.fields_ = new MapFieldBuilder<>(fieldsConverter);
            }
            this.bitField0_ |= 1;
            onChanged();
            return this.fields_;
        }

        @Override // com.google.protobuf.StructOrBuilder
        public int getFieldsCount() {
            return internalGetFields().ensureBuilderMap().size();
        }

        @Override // com.google.protobuf.StructOrBuilder
        public boolean containsFields(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            return internalGetFields().ensureBuilderMap().containsKey(str);
        }

        @Override // com.google.protobuf.StructOrBuilder
        @Deprecated
        public Map<String, Value> getFields() {
            return getFieldsMap();
        }

        @Override // com.google.protobuf.StructOrBuilder
        public Map<String, Value> getFieldsMap() {
            return internalGetFields().getImmutableMap();
        }

        @Override // com.google.protobuf.StructOrBuilder
        public Value getFieldsOrDefault(String str, Value value) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, ValueOrBuilder> ensureBuilderMap = internalGetMutableFields().ensureBuilderMap();
            return ensureBuilderMap.containsKey(str) ? fieldsConverter.build(ensureBuilderMap.get(str)) : value;
        }

        @Override // com.google.protobuf.StructOrBuilder
        public Value getFieldsOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, ValueOrBuilder> ensureBuilderMap = internalGetMutableFields().ensureBuilderMap();
            if (!ensureBuilderMap.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return fieldsConverter.build(ensureBuilderMap.get(str));
        }

        public Builder clearFields() {
            this.bitField0_ &= -2;
            internalGetMutableFields().clear();
            return this;
        }

        public Builder removeFields(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableFields().ensureBuilderMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Value> getMutableFields() {
            this.bitField0_ |= 1;
            return internalGetMutableFields().ensureMessageMap();
        }

        public Builder putFields(String str, Value value) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableFields().ensureBuilderMap().put(str, value);
            this.bitField0_ |= 1;
            return this;
        }

        public Builder putAllFields(Map<String, Value> map) {
            for (Map.Entry<String, Value> entry : map.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw null;
                }
            }
            internalGetMutableFields().ensureBuilderMap().putAll(map);
            this.bitField0_ |= 1;
            return this;
        }

        public Value.Builder putFieldsBuilderIfAbsent(String str) {
            Map<String, ValueOrBuilder> ensureBuilderMap = internalGetMutableFields().ensureBuilderMap();
            ValueOrBuilder valueOrBuilder = ensureBuilderMap.get(str);
            if (valueOrBuilder == null) {
                valueOrBuilder = Value.newBuilder();
                ensureBuilderMap.put(str, valueOrBuilder);
            }
            if (valueOrBuilder instanceof Value) {
                valueOrBuilder = ((Value) valueOrBuilder).toBuilder();
                ensureBuilderMap.put(str, valueOrBuilder);
            }
            return (Value.Builder) valueOrBuilder;
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

    public static Struct getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Struct> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Struct> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Struct getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
