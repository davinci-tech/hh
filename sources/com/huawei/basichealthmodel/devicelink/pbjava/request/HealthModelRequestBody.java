package com.huawei.basichealthmodel.devicelink.pbjava.request;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class HealthModelRequestBody {

    /* renamed from: a, reason: collision with root package name */
    private static Descriptors.FileDescriptor f1911a = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0011requestBody.proto\u00125com.huawei.basichealthmodel.devicelink.pbjava.request\"_\n\u000bRequestBody\u0012\u0011\n\trecordDay\u0018\u0001 \u0001(\u0005\u0012\u0016\n\u000erequestTaskIds\u0018\u0002 \u0001(\t\u0012\u0010\n\bpageSize\u0018\u0003 \u0001(\u0005\u0012\u0013\n\u000bpackageSize\u0018\u0004 \u0001(\u0005B\u0018B\u0016HealthModelRequestBodyb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    private static final GeneratedMessageV3.FieldAccessorTable b;
    private static final Descriptors.Descriptor d;

    public interface RequestBodyOrBuilder extends MessageOrBuilder {
        int getPackageSize();

        int getPageSize();

        int getRecordDay();

        String getRequestTaskIds();

        ByteString getRequestTaskIdsBytes();
    }

    public static final class e extends GeneratedMessageV3 implements RequestBodyOrBuilder {
        private static final long serialVersionUID = 0;
        private int b;
        private byte d;
        private int e;
        private volatile Object g;
        private int i;
        private static final e c = new e();

        /* renamed from: a, reason: collision with root package name */
        private static final Parser<e> f1912a = new AbstractParser<e>() { // from class: com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.e.1
            @Override // com.google.protobuf.Parser
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public e parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new e(codedInputStream, extensionRegistryLite);
            }
        };

        private e(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.d = (byte) -1;
        }

        private e() {
            this.d = (byte) -1;
            this.g = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new e();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private e(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.i = codedInputStream.readInt32();
                            } else if (readTag == 18) {
                                this.g = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.e = codedInputStream.readInt32();
                            } else if (readTag == 32) {
                                this.b = codedInputStream.readInt32();
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

        public static final Descriptors.Descriptor b() {
            return HealthModelRequestBody.d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthModelRequestBody.b.ensureFieldAccessorsInitialized(e.class, c.class);
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
        public int getRecordDay() {
            return this.i;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
        public String getRequestTaskIds() {
            Object obj = this.g;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.g = stringUtf8;
            return stringUtf8;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
        public ByteString getRequestTaskIdsBytes() {
            Object obj = this.g;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.g = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
        public int getPageSize() {
            return this.e;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
        public int getPackageSize() {
            return this.b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.d;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.d = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.i;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            if (!getRequestTaskIdsBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.g);
            }
            int i2 = this.e;
            if (i2 != 0) {
                codedOutputStream.writeInt32(3, i2);
            }
            int i3 = this.b;
            if (i3 != 0) {
                codedOutputStream.writeInt32(4, i3);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.i;
            int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
            if (!getRequestTaskIdsBytes().isEmpty()) {
                computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.g);
            }
            int i3 = this.e;
            if (i3 != 0) {
                computeInt32Size += CodedOutputStream.computeInt32Size(3, i3);
            }
            int i4 = this.b;
            if (i4 != 0) {
                computeInt32Size += CodedOutputStream.computeInt32Size(4, i4);
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
            if (!(obj instanceof e)) {
                return super.equals(obj);
            }
            e eVar = (e) obj;
            return getRecordDay() == eVar.getRecordDay() && getRequestTaskIds().equals(eVar.getRequestTaskIds()) && getPageSize() == eVar.getPageSize() && getPackageSize() == eVar.getPackageSize() && this.unknownFields.equals(eVar.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = b().hashCode();
            int recordDay = getRecordDay();
            int hashCode2 = getRequestTaskIds().hashCode();
            int pageSize = ((((((((((((((((((hashCode + 779) * 37) + 1) * 53) + recordDay) * 37) + 2) * 53) + hashCode2) * 37) + 3) * 53) + getPageSize()) * 37) + 4) * 53) + getPackageSize()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = pageSize;
            return pageSize;
        }

        public static e b(ByteString byteString) throws InvalidProtocolBufferException {
            return f1912a.parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public c newBuilderForType() {
            return d();
        }

        public static c d() {
            return c.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public c toBuilder() {
            return this == c ? new c() : new c().e(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public c newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new c(builderParent);
        }

        public static final class c extends GeneratedMessageV3.Builder<c> implements RequestBodyOrBuilder {

            /* renamed from: a, reason: collision with root package name */
            private int f1913a;
            private Object b;
            private int c;
            private int d;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthModelRequestBody.b.ensureFieldAccessorsInitialized(e.class, c.class);
            }

            private c() {
                this.b = "";
                f();
            }

            private c(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.b = "";
                f();
            }

            private void f() {
                boolean unused = e.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public c clear() {
                super.clear();
                this.f1913a = 0;
                this.b = "";
                this.d = 0;
                this.c = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return HealthModelRequestBody.d;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public e getDefaultInstanceForType() {
                return e.a();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public e build() {
                e buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public e buildPartial() {
                e eVar = new e(this);
                eVar.i = this.f1913a;
                eVar.g = this.b;
                eVar.e = this.d;
                eVar.b = this.c;
                onBuilt();
                return eVar;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public c mo113clone() {
                return (c) super.mo113clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public c setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (c) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public c clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (c) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public c clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (c) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public c setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (c) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public c addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (c) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public c mergeFrom(Message message) {
                if (message instanceof e) {
                    return e((e) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public c e(e eVar) {
                if (eVar == e.a()) {
                    return this;
                }
                if (eVar.getRecordDay() != 0) {
                    a(eVar.getRecordDay());
                }
                if (!eVar.getRequestTaskIds().isEmpty()) {
                    this.b = eVar.g;
                    onChanged();
                }
                if (eVar.getPageSize() != 0) {
                    e(eVar.getPageSize());
                }
                if (eVar.getPackageSize() != 0) {
                    b(eVar.getPackageSize());
                }
                mergeUnknownFields(eVar.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.e.c mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser r0 = com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.e.e()     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody$e r2 = (com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.e) r2     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    if (r2 == 0) goto Lf
                    r1.e(r2)
                Lf:
                    return r1
                L10:
                    r2 = move-exception
                    goto L20
                L12:
                    r2 = move-exception
                    com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L10
                    com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody$e r3 = (com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.e) r3     // Catch: java.lang.Throwable -> L10
                    java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1e
                    throw r2     // Catch: java.lang.Throwable -> L1e
                L1e:
                    r2 = move-exception
                    goto L21
                L20:
                    r3 = 0
                L21:
                    if (r3 == 0) goto L26
                    r1.e(r3)
                L26:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.e.c.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody$e$c");
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
            public int getRecordDay() {
                return this.f1913a;
            }

            public c a(int i) {
                this.f1913a = i;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
            public String getRequestTaskIds() {
                Object obj = this.b;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.b = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
            public ByteString getRequestTaskIdsBytes() {
                Object obj = this.b;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.b = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
            public int getPageSize() {
                return this.d;
            }

            public c e(int i) {
                this.d = i;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.request.HealthModelRequestBody.RequestBodyOrBuilder
            public int getPackageSize() {
                return this.c;
            }

            public c b(int i) {
                this.c = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final c setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (c) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final c mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (c) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static e a() {
            return c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<e> getParserForType() {
            return f1912a;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public e getDefaultInstanceForType() {
            return c;
        }
    }

    public static Descriptors.FileDescriptor c() {
        return f1911a;
    }

    static {
        Descriptors.Descriptor descriptor = c().getMessageTypes().get(0);
        d = descriptor;
        b = new GeneratedMessageV3.FieldAccessorTable(descriptor, new String[]{"RecordDay", "RequestTaskIds", "PageSize", "PackageSize"});
    }
}
