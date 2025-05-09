package com.huawei.basichealthmodel.devicelink.pbjava.report;

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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class HealthModelNoticeBody {

    /* renamed from: a, reason: collision with root package name */
    private static final GeneratedMessageV3.FieldAccessorTable f1899a;
    private static final Descriptors.Descriptor b;
    private static final Descriptors.Descriptor c;
    private static final GeneratedMessageV3.FieldAccessorTable d;
    private static Descriptors.FileDescriptor e = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\fnotice.proto\u00124com.huawei.basichealthmodel.devicelink.pbjava.report\"¾\u0001\n\nNoticeBody\u0012^\n\u0007notices\u0018\u0001 \u0003(\u000b2M.com.huawei.basichealthmodel.devicelink.pbjava.report.NoticeBody.ReportNotice\u001aP\n\fReportNotice\u0012\u0011\n\trecordDay\u0018\u0001 \u0001(\u0005\u0012\u000e\n\u0006taskId\u0018\u0002 \u0001(\u0005\u0012\u0010\n\btaskName\u0018\u0003 \u0001(\t\u0012\u000b\n\u0003msg\u0018\u0004 \u0001(\tB\u0017B\u0015HealthModelNoticeBodyb\u0006proto3"}, new Descriptors.FileDescriptor[0]);

    public interface NoticeBodyOrBuilder extends MessageOrBuilder {
        NoticeBody.e getNotices(int i);

        int getNoticesCount();

        List<NoticeBody.e> getNoticesList();

        NoticeBody.ReportNoticeOrBuilder getNoticesOrBuilder(int i);

        List<? extends NoticeBody.ReportNoticeOrBuilder> getNoticesOrBuilderList();
    }

    public static final class NoticeBody extends GeneratedMessageV3 implements NoticeBodyOrBuilder {
        private static final NoticeBody b = new NoticeBody();
        private static final Parser<NoticeBody> c = new AbstractParser<NoticeBody>() { // from class: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.3
            @Override // com.google.protobuf.Parser
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public NoticeBody parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new NoticeBody(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;

        /* renamed from: a, reason: collision with root package name */
        private byte f1900a;
        private List<e> e;

        public interface ReportNoticeOrBuilder extends MessageOrBuilder {
            String getMsg();

            ByteString getMsgBytes();

            int getRecordDay();

            int getTaskId();

            String getTaskName();

            ByteString getTaskNameBytes();
        }

        private NoticeBody(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.f1900a = (byte) -1;
        }

        private NoticeBody() {
            this.f1900a = (byte) -1;
            this.e = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new NoticeBody();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NoticeBody(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!(z2 & true)) {
                                    this.e = new ArrayList();
                                    z2 = true;
                                }
                                this.e.add((e) codedInputStream.readMessage(e.j(), extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.e = Collections.unmodifiableList(this.e);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor c() {
            return HealthModelNoticeBody.b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthModelNoticeBody.d.ensureFieldAccessorsInitialized(NoticeBody.class, b.class);
        }

        public static final class e extends GeneratedMessageV3 implements ReportNoticeOrBuilder {

            /* renamed from: a, reason: collision with root package name */
            private static final e f1902a = new e();
            private static final Parser<e> d = new AbstractParser<e>() { // from class: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.e.5
                @Override // com.google.protobuf.Parser
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public e parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new e(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private int b;
            private byte c;
            private volatile Object e;
            private int f;
            private volatile Object g;

            private e(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            private e() {
                this.c = (byte) -1;
                this.g = "";
                this.e = "";
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
                                    this.b = codedInputStream.readInt32();
                                } else if (readTag == 16) {
                                    this.f = codedInputStream.readInt32();
                                } else if (readTag == 26) {
                                    this.g = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 34) {
                                    this.e = codedInputStream.readStringRequireUtf8();
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

            public static final Descriptors.Descriptor a() {
                return HealthModelNoticeBody.c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthModelNoticeBody.f1899a.ensureFieldAccessorsInitialized(e.class, C0045e.class);
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
            public int getRecordDay() {
                return this.b;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
            public int getTaskId() {
                return this.f;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
            public String getTaskName() {
                Object obj = this.g;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.g = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
            public ByteString getTaskNameBytes() {
                Object obj = this.g;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.g = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
            public String getMsg() {
                Object obj = this.e;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.e = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
            public ByteString getMsgBytes() {
                Object obj = this.e;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.e = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b = this.c;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                int i = this.b;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                int i2 = this.f;
                if (i2 != 0) {
                    codedOutputStream.writeInt32(2, i2);
                }
                if (!getTaskNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.g);
                }
                if (!getMsgBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.e);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.b;
                int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
                int i3 = this.f;
                if (i3 != 0) {
                    computeInt32Size += CodedOutputStream.computeInt32Size(2, i3);
                }
                if (!getTaskNameBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(3, this.g);
                }
                if (!getMsgBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(4, this.e);
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
                return getRecordDay() == eVar.getRecordDay() && getTaskId() == eVar.getTaskId() && getTaskName().equals(eVar.getTaskName()) && getMsg().equals(eVar.getMsg()) && this.unknownFields.equals(eVar.unknownFields);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = a().hashCode();
                int recordDay = getRecordDay();
                int taskId = getTaskId();
                int hashCode2 = ((((((((((((((((((hashCode + 779) * 37) + 1) * 53) + recordDay) * 37) + 2) * 53) + taskId) * 37) + 3) * 53) + getTaskName().hashCode()) * 37) + 4) * 53) + getMsg().hashCode()) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: g, reason: merged with bridge method [inline-methods] */
            public C0045e newBuilderForType() {
                return e();
            }

            public static C0045e e() {
                return f1902a.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: i, reason: merged with bridge method [inline-methods] */
            public C0045e toBuilder() {
                return this == f1902a ? new C0045e() : new C0045e().b(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public C0045e newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new C0045e(builderParent);
            }

            /* renamed from: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody$NoticeBody$e$e, reason: collision with other inner class name */
            public static final class C0045e extends GeneratedMessageV3.Builder<C0045e> implements ReportNoticeOrBuilder {

                /* renamed from: a, reason: collision with root package name */
                private int f1903a;
                private Object b;
                private int d;
                private Object e;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return HealthModelNoticeBody.f1899a.ensureFieldAccessorsInitialized(e.class, C0045e.class);
                }

                private C0045e() {
                    this.b = "";
                    this.e = "";
                    j();
                }

                private C0045e(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.b = "";
                    this.e = "";
                    j();
                }

                private void j() {
                    boolean unused = e.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public C0045e clear() {
                    super.clear();
                    this.f1903a = 0;
                    this.d = 0;
                    this.b = "";
                    this.e = "";
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return HealthModelNoticeBody.c;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public e getDefaultInstanceForType() {
                    return e.b();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
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
                    eVar.b = this.f1903a;
                    eVar.f = this.d;
                    eVar.g = this.b;
                    eVar.e = this.e;
                    onBuilt();
                    return eVar;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public C0045e mo113clone() {
                    return (C0045e) super.mo113clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public C0045e setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (C0045e) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public C0045e clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (C0045e) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public C0045e clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (C0045e) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public C0045e setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (C0045e) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public C0045e addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (C0045e) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public C0045e mergeFrom(Message message) {
                    if (message instanceof e) {
                        return b((e) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public C0045e b(e eVar) {
                    if (eVar == e.b()) {
                        return this;
                    }
                    if (eVar.getRecordDay() != 0) {
                        d(eVar.getRecordDay());
                    }
                    if (eVar.getTaskId() != 0) {
                        a(eVar.getTaskId());
                    }
                    if (!eVar.getTaskName().isEmpty()) {
                        this.b = eVar.g;
                        onChanged();
                    }
                    if (!eVar.getMsg().isEmpty()) {
                        this.e = eVar.e;
                        onChanged();
                    }
                    mergeUnknownFields(eVar.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.e.C0045e mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser r0 = com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.e.d()     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody$NoticeBody$e r2 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.e) r2     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        if (r2 == 0) goto Lf
                        r1.b(r2)
                    Lf:
                        return r1
                    L10:
                        r2 = move-exception
                        goto L20
                    L12:
                        r2 = move-exception
                        com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L10
                        com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody$NoticeBody$e r3 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.e) r3     // Catch: java.lang.Throwable -> L10
                        java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1e
                        throw r2     // Catch: java.lang.Throwable -> L1e
                    L1e:
                        r2 = move-exception
                        goto L21
                    L20:
                        r3 = 0
                    L21:
                        if (r3 == 0) goto L26
                        r1.b(r3)
                    L26:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.e.C0045e.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody$NoticeBody$e$e");
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
                public int getRecordDay() {
                    return this.f1903a;
                }

                public C0045e d(int i) {
                    this.f1903a = i;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
                public int getTaskId() {
                    return this.d;
                }

                public C0045e a(int i) {
                    this.d = i;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
                public String getTaskName() {
                    Object obj = this.b;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.b = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
                public ByteString getTaskNameBytes() {
                    Object obj = this.b;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.b = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public C0045e b(String str) {
                    str.getClass();
                    this.b = str;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
                public String getMsg() {
                    Object obj = this.e;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.e = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.ReportNoticeOrBuilder
                public ByteString getMsgBytes() {
                    Object obj = this.e;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.e = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public C0045e d(String str) {
                    str.getClass();
                    this.e = str;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public final C0045e setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (C0045e) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public final C0045e mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (C0045e) super.mergeUnknownFields(unknownFieldSet);
                }
            }

            public static e b() {
                return f1902a;
            }

            public static Parser<e> j() {
                return d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<e> getParserForType() {
                return d;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: f, reason: merged with bridge method [inline-methods] */
            public e getDefaultInstanceForType() {
                return f1902a;
            }
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
        public List<e> getNoticesList() {
            return this.e;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
        public List<? extends ReportNoticeOrBuilder> getNoticesOrBuilderList() {
            return this.e;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
        public int getNoticesCount() {
            return this.e.size();
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
        public e getNotices(int i) {
            return this.e.get(i);
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
        public ReportNoticeOrBuilder getNoticesOrBuilder(int i) {
            return this.e.get(i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b2 = this.f1900a;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.f1900a = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.e.size(); i++) {
                codedOutputStream.writeMessage(1, this.e.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.e.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.e.get(i3));
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof NoticeBody)) {
                return super.equals(obj);
            }
            NoticeBody noticeBody = (NoticeBody) obj;
            return getNoticesList().equals(noticeBody.getNoticesList()) && this.unknownFields.equals(noticeBody.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = c().hashCode() + 779;
            if (getNoticesCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getNoticesList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public b newBuilderForType() {
            return g();
        }

        public static b g() {
            return b.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public b toBuilder() {
            return this == b ? new b() : new b().a(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public b newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new b(builderParent);
        }

        public static final class b extends GeneratedMessageV3.Builder<b> implements NoticeBodyOrBuilder {

            /* renamed from: a, reason: collision with root package name */
            private List<e> f1901a;
            private int d;
            private RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> e;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthModelNoticeBody.d.ensureFieldAccessorsInitialized(NoticeBody.class, b.class);
            }

            private b() {
                this.f1901a = Collections.emptyList();
                i();
            }

            private b(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.f1901a = Collections.emptyList();
                i();
            }

            private void i() {
                if (NoticeBody.alwaysUseFieldBuilders) {
                    h();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public b clear() {
                super.clear();
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 == null) {
                    this.f1901a = Collections.emptyList();
                    this.d &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return HealthModelNoticeBody.b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public NoticeBody getDefaultInstanceForType() {
                return NoticeBody.d();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public NoticeBody build() {
                NoticeBody buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public NoticeBody buildPartial() {
                NoticeBody noticeBody = new NoticeBody(this);
                int i = this.d;
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.f1901a = Collections.unmodifiableList(this.f1901a);
                        this.d &= -2;
                    }
                    noticeBody.e = this.f1901a;
                } else {
                    noticeBody.e = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return noticeBody;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public b mo113clone() {
                return (b) super.mo113clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public b setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public b clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (b) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public b clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (b) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public b setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (b) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public b addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (b) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public b mergeFrom(Message message) {
                if (message instanceof NoticeBody) {
                    return a((NoticeBody) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public b a(NoticeBody noticeBody) {
                if (noticeBody == NoticeBody.d()) {
                    return this;
                }
                if (this.e == null) {
                    if (!noticeBody.e.isEmpty()) {
                        if (this.f1901a.isEmpty()) {
                            this.f1901a = noticeBody.e;
                            this.d &= -2;
                        } else {
                            j();
                            this.f1901a.addAll(noticeBody.e);
                        }
                        onChanged();
                    }
                } else if (!noticeBody.e.isEmpty()) {
                    if (!this.e.isEmpty()) {
                        this.e.addAllMessages(noticeBody.e);
                    } else {
                        this.e.dispose();
                        this.e = null;
                        this.f1901a = noticeBody.e;
                        this.d &= -2;
                        this.e = NoticeBody.alwaysUseFieldBuilders ? h() : null;
                    }
                }
                mergeUnknownFields(noticeBody.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.b mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser r0 = com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.a()     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody$NoticeBody r2 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody) r2     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    if (r2 == 0) goto Lf
                    r1.a(r2)
                Lf:
                    return r1
                L10:
                    r2 = move-exception
                    goto L20
                L12:
                    r2 = move-exception
                    com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L10
                    com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody$NoticeBody r3 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody) r3     // Catch: java.lang.Throwable -> L10
                    java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1e
                    throw r2     // Catch: java.lang.Throwable -> L1e
                L1e:
                    r2 = move-exception
                    goto L21
                L20:
                    r3 = 0
                L21:
                    if (r3 == 0) goto L26
                    r1.a(r3)
                L26:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBody.b.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody$NoticeBody$b");
            }

            private void j() {
                if ((this.d & 1) == 0) {
                    this.f1901a = new ArrayList(this.f1901a);
                    this.d |= 1;
                }
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
            public List<e> getNoticesList() {
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.f1901a);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
            public int getNoticesCount() {
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 == null) {
                    return this.f1901a.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
            public e getNotices(int i) {
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 == null) {
                    return this.f1901a.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public b d(int i, e eVar) {
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 == null) {
                    eVar.getClass();
                    j();
                    this.f1901a.add(i, eVar);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, eVar);
                }
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
            public ReportNoticeOrBuilder getNoticesOrBuilder(int i) {
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 == null) {
                    return this.f1901a.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody.NoticeBodyOrBuilder
            public List<? extends ReportNoticeOrBuilder> getNoticesOrBuilderList() {
                RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> repeatedFieldBuilderV3 = this.e;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.f1901a);
            }

            private RepeatedFieldBuilderV3<e, e.C0045e, ReportNoticeOrBuilder> h() {
                if (this.e == null) {
                    this.e = new RepeatedFieldBuilderV3<>(this.f1901a, (this.d & 1) != 0, getParentForChildren(), isClean());
                    this.f1901a = null;
                }
                return this.e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final b setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public final b mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (b) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static NoticeBody d() {
            return b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NoticeBody> getParserForType() {
            return c;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public NoticeBody getDefaultInstanceForType() {
            return b;
        }
    }

    public static Descriptors.FileDescriptor c() {
        return e;
    }

    static {
        Descriptors.Descriptor descriptor = c().getMessageTypes().get(0);
        b = descriptor;
        d = new GeneratedMessageV3.FieldAccessorTable(descriptor, new String[]{"Notices"});
        Descriptors.Descriptor descriptor2 = descriptor.getNestedTypes().get(0);
        c = descriptor2;
        f1899a = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"RecordDay", "TaskId", "TaskName", "Msg"});
    }
}
