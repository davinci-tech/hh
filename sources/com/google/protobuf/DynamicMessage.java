package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Message;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class DynamicMessage extends AbstractMessage {
    private final FieldSet<Descriptors.FieldDescriptor> fields;
    private int memoizedSize = -1;
    private final Descriptors.FieldDescriptor[] oneofCases;
    private final Descriptors.Descriptor type;
    private final UnknownFieldSet unknownFields;

    DynamicMessage(Descriptors.Descriptor descriptor, FieldSet<Descriptors.FieldDescriptor> fieldSet, Descriptors.FieldDescriptor[] fieldDescriptorArr, UnknownFieldSet unknownFieldSet) {
        this.type = descriptor;
        this.fields = fieldSet;
        this.oneofCases = fieldDescriptorArr;
        this.unknownFields = unknownFieldSet;
    }

    public static DynamicMessage getDefaultInstance(Descriptors.Descriptor descriptor) {
        return new DynamicMessage(descriptor, FieldSet.emptySet(), new Descriptors.FieldDescriptor[descriptor.toProto().getOneofDeclCount()], UnknownFieldSet.getDefaultInstance());
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, CodedInputStream codedInputStream) throws IOException {
        return newBuilder(descriptor).mergeFrom(codedInputStream).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, CodedInputStream codedInputStream, ExtensionRegistry extensionRegistry) throws IOException {
        return newBuilder(descriptor).mergeFrom(codedInputStream, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, ByteString byteString) throws InvalidProtocolBufferException {
        return newBuilder(descriptor).mergeFrom(byteString).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, ByteString byteString, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return newBuilder(descriptor).mergeFrom(byteString, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, byte[] bArr) throws InvalidProtocolBufferException {
        return newBuilder(descriptor).mergeFrom(bArr).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, byte[] bArr, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
        return newBuilder(descriptor).mergeFrom(bArr, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, InputStream inputStream) throws IOException {
        return newBuilder(descriptor).mergeFrom(inputStream).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, InputStream inputStream, ExtensionRegistry extensionRegistry) throws IOException {
        return newBuilder(descriptor).mergeFrom(inputStream, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static Builder newBuilder(Descriptors.Descriptor descriptor) {
        return new Builder(descriptor);
    }

    public static Builder newBuilder(Message message) {
        return new Builder(message.getDescriptorForType()).mergeFrom(message);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        return this.type;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public DynamicMessage getDefaultInstanceForType() {
        return getDefaultInstance(this.type);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return this.fields.getAllFields();
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageOrBuilder
    public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
        verifyOneofContainingType(oneofDescriptor);
        return this.oneofCases[oneofDescriptor.getIndex()] != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageOrBuilder
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
        verifyOneofContainingType(oneofDescriptor);
        return this.oneofCases[oneofDescriptor.getIndex()];
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.hasField(fieldDescriptor);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        Object field = this.fields.getField(fieldDescriptor);
        if (field != null) {
            return field;
        }
        if (fieldDescriptor.isRepeated()) {
            return Collections.emptyList();
        }
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            return getDefaultInstance(fieldDescriptor.getMessageType());
        }
        return fieldDescriptor.getDefaultValue();
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.getRepeatedFieldCount(fieldDescriptor);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
        verifyContainingType(fieldDescriptor);
        return this.fields.getRepeatedField(fieldDescriptor, i);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    static boolean isInitialized(Descriptors.Descriptor descriptor, FieldSet<Descriptors.FieldDescriptor> fieldSet) {
        for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
            if (fieldDescriptor.isRequired() && !fieldSet.hasField(fieldDescriptor)) {
                return false;
            }
        }
        return fieldSet.isInitialized();
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return isInitialized(this.type, this.fields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.type.getOptions().getMessageSetWireFormat()) {
            this.fields.writeMessageSetTo(codedOutputStream);
            this.unknownFields.writeAsMessageSetTo(codedOutputStream);
        } else {
            this.fields.writeTo(codedOutputStream);
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int serializedSize;
        int serializedSize2;
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        if (this.type.getOptions().getMessageSetWireFormat()) {
            serializedSize = this.fields.getMessageSetSerializedSize();
            serializedSize2 = this.unknownFields.getSerializedSizeAsMessageSet();
        } else {
            serializedSize = this.fields.getSerializedSize();
            serializedSize2 = this.unknownFields.getSerializedSize();
        }
        int i2 = serializedSize + serializedSize2;
        this.memoizedSize = i2;
        return i2;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return new Builder(this.type);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return newBuilderForType().mergeFrom((Message) this);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<DynamicMessage> getParserForType() {
        return new AbstractParser<DynamicMessage>() { // from class: com.google.protobuf.DynamicMessage.1
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
            public DynamicMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                Builder newBuilder = DynamicMessage.newBuilder(DynamicMessage.this.type);
                try {
                    newBuilder.mergeFrom(codedInputStream, extensionRegistryLite);
                    return newBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(newBuilder.buildPartial());
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(newBuilder.buildPartial());
                }
            }
        };
    }

    private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getContainingType() != this.type) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    private void verifyOneofContainingType(Descriptors.OneofDescriptor oneofDescriptor) {
        if (oneofDescriptor.getContainingType() != this.type) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }
    }

    public static final class Builder extends AbstractMessage.Builder<Builder> {
        private FieldSet.Builder<Descriptors.FieldDescriptor> fields;
        private final Descriptors.FieldDescriptor[] oneofCases;
        private final Descriptors.Descriptor type;
        private UnknownFieldSet unknownFields;

        private Builder(Descriptors.Descriptor descriptor) {
            this.type = descriptor;
            this.fields = FieldSet.newBuilder();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.oneofCases = new Descriptors.FieldDescriptor[descriptor.toProto().getOneofDeclCount()];
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            this.fields = FieldSet.newBuilder();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof DynamicMessage) {
                DynamicMessage dynamicMessage = (DynamicMessage) message;
                if (dynamicMessage.type == this.type) {
                    this.fields.mergeFrom(dynamicMessage.fields);
                    mergeUnknownFields(dynamicMessage.unknownFields);
                    int i = 0;
                    while (true) {
                        Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                        if (i >= fieldDescriptorArr.length) {
                            return this;
                        }
                        if (fieldDescriptorArr[i] == null) {
                            fieldDescriptorArr[i] = dynamicMessage.oneofCases[i];
                        } else if (dynamicMessage.oneofCases[i] != null && this.oneofCases[i] != dynamicMessage.oneofCases[i]) {
                            this.fields.clearField(this.oneofCases[i]);
                            this.oneofCases[i] = dynamicMessage.oneofCases[i];
                        }
                        i++;
                    }
                } else {
                    throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
                }
            } else {
                return (Builder) super.mergeFrom(message);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DynamicMessage build() {
            if (!isInitialized()) {
                Descriptors.Descriptor descriptor = this.type;
                FieldSet<Descriptors.FieldDescriptor> build = this.fields.build();
                Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                throw newUninitializedMessageException((Message) new DynamicMessage(descriptor, build, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields));
            }
            return buildPartial();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DynamicMessage buildParsed() throws InvalidProtocolBufferException {
            if (!isInitialized()) {
                Descriptors.Descriptor descriptor = this.type;
                FieldSet<Descriptors.FieldDescriptor> build = this.fields.build();
                Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                throw newUninitializedMessageException((Message) new DynamicMessage(descriptor, build, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields)).asInvalidProtocolBufferException();
            }
            return buildPartial();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DynamicMessage buildPartial() {
            if (this.type.getOptions().getMapEntry()) {
                for (Descriptors.FieldDescriptor fieldDescriptor : this.type.getFields()) {
                    if (fieldDescriptor.isOptional() && !this.fields.hasField(fieldDescriptor)) {
                        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                            this.fields.setField(fieldDescriptor, DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()));
                        } else {
                            this.fields.setField(fieldDescriptor, fieldDescriptor.getDefaultValue());
                        }
                    }
                }
            }
            Descriptors.Descriptor descriptor = this.type;
            FieldSet<Descriptors.FieldDescriptor> buildPartial = this.fields.buildPartial();
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            return new DynamicMessage(descriptor, buildPartial, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public Builder mo113clone() {
            Builder builder = new Builder(this.type);
            builder.fields.mergeFrom(this.fields.build());
            builder.mergeUnknownFields(this.unknownFields);
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            System.arraycopy(fieldDescriptorArr, 0, builder.oneofCases, 0, fieldDescriptorArr.length);
            return builder;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            for (Descriptors.FieldDescriptor fieldDescriptor : this.type.getFields()) {
                if (fieldDescriptor.isRequired() && !this.fields.hasField(fieldDescriptor)) {
                    return false;
                }
            }
            return this.fields.isInitialized();
        }

        @Override // com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return this.type;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DynamicMessage getDefaultInstanceForType() {
            return DynamicMessage.getDefaultInstance(this.type);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return this.fields.getAllFields();
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
            }
            return new Builder(fieldDescriptor.getMessageType());
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageOrBuilder
        public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            return this.oneofCases[oneofDescriptor.getIndex()] != null;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            return this.oneofCases[oneofDescriptor.getIndex()];
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            Descriptors.FieldDescriptor fieldDescriptor = this.oneofCases[oneofDescriptor.getIndex()];
            if (fieldDescriptor != null) {
                clearField(fieldDescriptor);
            }
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.hasField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            Object field = this.fields.getField(fieldDescriptor);
            if (field != null) {
                return field;
            }
            if (fieldDescriptor.isRepeated()) {
                return Collections.emptyList();
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                return DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType());
            }
            return fieldDescriptor.getDefaultValue();
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            verifyType(fieldDescriptor, obj);
            Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            if (containingOneof != null) {
                int index = containingOneof.getIndex();
                Descriptors.FieldDescriptor fieldDescriptor2 = this.oneofCases[index];
                if (fieldDescriptor2 != null && fieldDescriptor2 != fieldDescriptor) {
                    this.fields.clearField(fieldDescriptor2);
                }
                this.oneofCases[index] = fieldDescriptor;
            } else if (!fieldDescriptor.hasPresence() && !fieldDescriptor.isRepeated() && obj.equals(fieldDescriptor.getDefaultValue())) {
                this.fields.clearField(fieldDescriptor);
                return this;
            }
            this.fields.setField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            if (containingOneof != null) {
                int index = containingOneof.getIndex();
                Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                if (fieldDescriptorArr[index] == fieldDescriptor) {
                    fieldDescriptorArr[index] = null;
                }
            }
            this.fields.clearField(fieldDescriptor);
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.getRepeatedFieldCount(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            verifyContainingType(fieldDescriptor);
            return this.fields.getRepeatedField(fieldDescriptor, i);
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            verifyContainingType(fieldDescriptor);
            verifySingularValueType(fieldDescriptor, obj);
            this.fields.setRepeatedField(fieldDescriptor, i, obj);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            verifySingularValueType(fieldDescriptor, obj);
            this.fields.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.Message.Builder
        public Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = unknownFieldSet;
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFieldSet).build();
            return this;
        }

        private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != this.type) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyOneofContainingType(Descriptors.OneofDescriptor oneofDescriptor) {
            if (oneofDescriptor.getContainingType() != this.type) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
        }

        private void verifySingularValueType(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            int i = AnonymousClass2.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()];
            if (i == 1) {
                Internal.checkNotNull(obj);
                if (!(obj instanceof Descriptors.EnumValueDescriptor)) {
                    throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
                }
            } else if (i == 2 && (obj instanceof Message.Builder)) {
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(fieldDescriptor.getNumber()), fieldDescriptor.getLiteType().getJavaType(), obj.getClass().getName()));
            }
        }

        private void verifyType(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (fieldDescriptor.isRepeated()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    verifySingularValueType(fieldDescriptor, it.next());
                }
                return;
            }
            verifySingularValueType(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor) {
            Message.Builder messageBuilder;
            verifyContainingType(fieldDescriptor);
            if (fieldDescriptor.isMapField()) {
                throw new UnsupportedOperationException("Nested builder not supported for map fields.");
            }
            if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
            Object fieldAllowBuilders = this.fields.getFieldAllowBuilders(fieldDescriptor);
            if (fieldAllowBuilders == null) {
                messageBuilder = new Builder(fieldDescriptor.getMessageType());
            } else {
                messageBuilder = toMessageBuilder(fieldAllowBuilders);
            }
            this.fields.setField(fieldDescriptor, messageBuilder);
            return messageBuilder;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            verifyContainingType(fieldDescriptor);
            if (fieldDescriptor.isMapField()) {
                throw new UnsupportedOperationException("Map fields cannot be repeated");
            }
            if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
            }
            Message.Builder messageBuilder = toMessageBuilder(this.fields.getRepeatedFieldAllowBuilders(fieldDescriptor, i));
            this.fields.setRepeatedField(fieldDescriptor, i, messageBuilder);
            return messageBuilder;
        }

        private static Message.Builder toMessageBuilder(Object obj) {
            if (obj instanceof Message.Builder) {
                return (Message.Builder) obj;
            }
            if (obj instanceof LazyField) {
                obj = ((LazyField) obj).getValue();
            }
            if (obj instanceof Message) {
                return ((Message) obj).toBuilder();
            }
            throw new IllegalArgumentException(String.format("Cannot convert %s to Message.Builder", obj.getClass()));
        }
    }

    /* renamed from: com.google.protobuf.DynamicMessage$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
