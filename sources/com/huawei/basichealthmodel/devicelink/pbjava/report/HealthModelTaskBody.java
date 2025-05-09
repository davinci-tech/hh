package com.huawei.basichealthmodel.devicelink.pbjava.report;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
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
public final class HealthModelTaskBody {

    /* renamed from: a, reason: collision with root package name */
    private static final Descriptors.Descriptor f1904a;
    private static final GeneratedMessageV3.FieldAccessorTable b;
    private static final GeneratedMessageV3.FieldAccessorTable c;
    private static final Descriptors.Descriptor d;
    private static Descriptors.FileDescriptor e = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\ntask.proto\u00124com.huawei.basichealthmodel.devicelink.pbjava.report\"\u0083\u0004\n\bTaskBody\u0012\u0012\n\nresultCode\u0018\u0001 \u0001(\u0005\u0012\u0011\n\tresultDes\u0018\u0002 \u0001(\t\u0012\u0011\n\trecordDay\u0018\u0003 \u0001(\u0005\u0012\u0010\n\ballTasks\u0018\u0004 \u0001(\t\u0012X\n\u0005tasks\u0018\u0005 \u0003(\u000b2I.com.huawei.basichealthmodel.devicelink.pbjava.report.TaskBody.ReportTask\u0012\u0016\n\u000ecompletedTasks\u0018\u0006 \u0001(\t\u0012\u0016\n\u000ecloverProgress\u0018\u0007 \u0001(\f\u001aä\u0001\n\nReportTask\u0012\u000e\n\u0006taskId\u0018\u0001 \u0001(\u0005\u0012\u0012\n\ntaskTarget\u0018\u0002 \u0001(\t\u0012\u0011\n\ttargetDes\u0018\u0003 \u0001(\t\u0012\u0011\n\ttaskValue\u0018\u0004 \u0001(\t\u0012\u000e\n\u0006status\u0018\u0005 \u0001(\u0005\u0012\u0012\n\nrestStatus\u0018\u0006 \u0001(\u0005\u0012\u0011\n\ttimestamp\u0018\u0007 \u0001(\u0003\u0012\u0010\n\btaskType\u0018\b \u0001(\u0005\u0012\u0010\n\btaskName\u0018\t \u0001(\t\u0012\f\n\u0004unit\u0018\n \u0001(\t\u0012\u0010\n\bprogress\u0018\u000b \u0001(\u0002\u0012\u0011\n\tstatusDes\u0018\f \u0001(\t\u001a:\n\u000eCloverProgress\u0012\u000b\n\u0003top\u0018\u0001 \u0001(\u0002\u0012\f\n\u0004left\u0018\u0002 \u0001(\u0002\u0012\r\n\u0005right\u0018\u0003 \u0001(\u0002B\u0015B\u0013HealthModelTaskBodyb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    private static final GeneratedMessageV3.FieldAccessorTable f;
    private static final Descriptors.Descriptor g;

    public interface TaskBodyOrBuilder extends MessageOrBuilder {
        String getAllTasks();

        ByteString getAllTasksBytes();

        ByteString getCloverProgress();

        String getCompletedTasks();

        ByteString getCompletedTasksBytes();

        int getRecordDay();

        int getResultCode();

        String getResultDes();

        ByteString getResultDesBytes();

        TaskBody.c getTasks(int i);

        int getTasksCount();

        List<TaskBody.c> getTasksList();

        TaskBody.ReportTaskOrBuilder getTasksOrBuilder(int i);

        List<? extends TaskBody.ReportTaskOrBuilder> getTasksOrBuilderList();
    }

    public static final class TaskBody extends GeneratedMessageV3 implements TaskBodyOrBuilder {

        /* renamed from: a, reason: collision with root package name */
        private static final TaskBody f1905a = new TaskBody();
        private static final Parser<TaskBody> c = new AbstractParser<TaskBody>() { // from class: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.2
            @Override // com.google.protobuf.Parser
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public TaskBody parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TaskBody(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private ByteString b;
        private volatile Object d;
        private volatile Object e;
        private int f;
        private int g;
        private volatile Object h;
        private List<c> i;
        private byte j;

        public interface CloverProgressOrBuilder extends MessageOrBuilder {
            float getLeft();

            float getRight();

            float getTop();
        }

        public interface ReportTaskOrBuilder extends MessageOrBuilder {
            float getProgress();

            int getRestStatus();

            int getStatus();

            String getStatusDes();

            ByteString getStatusDesBytes();

            String getTargetDes();

            ByteString getTargetDesBytes();

            int getTaskId();

            String getTaskName();

            ByteString getTaskNameBytes();

            String getTaskTarget();

            ByteString getTaskTargetBytes();

            int getTaskType();

            String getTaskValue();

            ByteString getTaskValueBytes();

            long getTimestamp();

            String getUnit();

            ByteString getUnitBytes();
        }

        private TaskBody(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.j = (byte) -1;
        }

        private TaskBody() {
            this.j = (byte) -1;
            this.h = "";
            this.d = "";
            this.i = Collections.emptyList();
            this.e = "";
            this.b = ByteString.EMPTY;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new TaskBody();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private TaskBody(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.g = codedInputStream.readInt32();
                                } else if (readTag == 18) {
                                    this.h = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 24) {
                                    this.f = codedInputStream.readInt32();
                                } else if (readTag == 34) {
                                    this.d = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 42) {
                                    if (!(z2 & true)) {
                                        this.i = new ArrayList();
                                        z2 = true;
                                    }
                                    this.i.add((c) codedInputStream.readMessage(c.g(), extensionRegistryLite));
                                } else if (readTag == 50) {
                                    this.e = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 58) {
                                    this.b = codedInputStream.readBytes();
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        }
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.i = Collections.unmodifiableList(this.i);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor a() {
            return HealthModelTaskBody.g;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthModelTaskBody.f.ensureFieldAccessorsInitialized(TaskBody.class, e.class);
        }

        public static final class c extends GeneratedMessageV3 implements ReportTaskOrBuilder {
            private static final long serialVersionUID = 0;

            /* renamed from: a, reason: collision with root package name */
            private byte f1906a;
            private float c;
            private int d;
            private volatile Object f;
            private int g;
            private volatile Object h;
            private int i;
            private volatile Object j;
            private volatile Object k;
            private volatile Object l;
            private int m;
            private volatile Object n;
            private long o;
            private static final c e = new c();
            private static final Parser<c> b = new AbstractParser<c>() { // from class: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.c.1
                @Override // com.google.protobuf.Parser
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public c parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new c(codedInputStream, extensionRegistryLite);
                }
            };

            private c(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.f1906a = (byte) -1;
            }

            private c() {
                this.f1906a = (byte) -1;
                this.k = "";
                this.h = "";
                this.l = "";
                this.j = "";
                this.n = "";
                this.f = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new c();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                switch (readTag) {
                                    case 0:
                                        z = true;
                                    case 8:
                                        this.g = codedInputStream.readInt32();
                                    case 18:
                                        this.k = codedInputStream.readStringRequireUtf8();
                                    case 26:
                                        this.h = codedInputStream.readStringRequireUtf8();
                                    case 34:
                                        this.l = codedInputStream.readStringRequireUtf8();
                                    case 40:
                                        this.i = codedInputStream.readInt32();
                                    case 48:
                                        this.d = codedInputStream.readInt32();
                                    case 56:
                                        this.o = codedInputStream.readInt64();
                                    case 64:
                                        this.m = codedInputStream.readInt32();
                                    case 74:
                                        this.j = codedInputStream.readStringRequireUtf8();
                                    case 82:
                                        this.n = codedInputStream.readStringRequireUtf8();
                                    case 93:
                                        this.c = codedInputStream.readFloat();
                                    case 98:
                                        this.f = codedInputStream.readStringRequireUtf8();
                                    default:
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                            z = true;
                                        }
                                }
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e3) {
                            throw e3.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor b() {
                return HealthModelTaskBody.f1904a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthModelTaskBody.b.ensureFieldAccessorsInitialized(c.class, a.class);
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public int getTaskId() {
                return this.g;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public String getTaskTarget() {
                Object obj = this.k;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.k = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public ByteString getTaskTargetBytes() {
                Object obj = this.k;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.k = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public String getTargetDes() {
                Object obj = this.h;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.h = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public ByteString getTargetDesBytes() {
                Object obj = this.h;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.h = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public String getTaskValue() {
                Object obj = this.l;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.l = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public ByteString getTaskValueBytes() {
                Object obj = this.l;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.l = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public int getStatus() {
                return this.i;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public int getRestStatus() {
                return this.d;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public long getTimestamp() {
                return this.o;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public int getTaskType() {
                return this.m;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public String getTaskName() {
                Object obj = this.j;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.j = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public ByteString getTaskNameBytes() {
                Object obj = this.j;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.j = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public String getUnit() {
                Object obj = this.n;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.n = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public ByteString getUnitBytes() {
                Object obj = this.n;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.n = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public float getProgress() {
                return this.c;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public String getStatusDes() {
                Object obj = this.f;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.f = stringUtf8;
                return stringUtf8;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
            public ByteString getStatusDesBytes() {
                Object obj = this.f;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.f = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.f1906a;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.f1906a = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                int i = this.g;
                if (i != 0) {
                    codedOutputStream.writeInt32(1, i);
                }
                if (!getTaskTargetBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.k);
                }
                if (!getTargetDesBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.h);
                }
                if (!getTaskValueBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.l);
                }
                int i2 = this.i;
                if (i2 != 0) {
                    codedOutputStream.writeInt32(5, i2);
                }
                int i3 = this.d;
                if (i3 != 0) {
                    codedOutputStream.writeInt32(6, i3);
                }
                long j = this.o;
                if (j != 0) {
                    codedOutputStream.writeInt64(7, j);
                }
                int i4 = this.m;
                if (i4 != 0) {
                    codedOutputStream.writeInt32(8, i4);
                }
                if (!getTaskNameBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 9, this.j);
                }
                if (!getUnitBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 10, this.n);
                }
                float f = this.c;
                if (f != 0.0f) {
                    codedOutputStream.writeFloat(11, f);
                }
                if (!getStatusDesBytes().isEmpty()) {
                    GeneratedMessageV3.writeString(codedOutputStream, 12, this.f);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = this.g;
                int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
                if (!getTaskTargetBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.k);
                }
                if (!getTargetDesBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(3, this.h);
                }
                if (!getTaskValueBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(4, this.l);
                }
                int i3 = this.i;
                if (i3 != 0) {
                    computeInt32Size += CodedOutputStream.computeInt32Size(5, i3);
                }
                int i4 = this.d;
                if (i4 != 0) {
                    computeInt32Size += CodedOutputStream.computeInt32Size(6, i4);
                }
                long j = this.o;
                if (j != 0) {
                    computeInt32Size += CodedOutputStream.computeInt64Size(7, j);
                }
                int i5 = this.m;
                if (i5 != 0) {
                    computeInt32Size += CodedOutputStream.computeInt32Size(8, i5);
                }
                if (!getTaskNameBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(9, this.j);
                }
                if (!getUnitBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(10, this.n);
                }
                float f = this.c;
                if (f != 0.0f) {
                    computeInt32Size += CodedOutputStream.computeFloatSize(11, f);
                }
                if (!getStatusDesBytes().isEmpty()) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(12, this.f);
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
                if (!(obj instanceof c)) {
                    return super.equals(obj);
                }
                c cVar = (c) obj;
                return getTaskId() == cVar.getTaskId() && getTaskTarget().equals(cVar.getTaskTarget()) && getTargetDes().equals(cVar.getTargetDes()) && getTaskValue().equals(cVar.getTaskValue()) && getStatus() == cVar.getStatus() && getRestStatus() == cVar.getRestStatus() && getTimestamp() == cVar.getTimestamp() && getTaskType() == cVar.getTaskType() && getTaskName().equals(cVar.getTaskName()) && getUnit().equals(cVar.getUnit()) && Float.floatToIntBits(getProgress()) == Float.floatToIntBits(cVar.getProgress()) && getStatusDes().equals(cVar.getStatusDes()) && this.unknownFields.equals(cVar.unknownFields);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = b().hashCode();
                int taskId = getTaskId();
                int hashCode2 = getTaskTarget().hashCode();
                int hashCode3 = getTargetDes().hashCode();
                int hashCode4 = getTaskValue().hashCode();
                int status = getStatus();
                int restStatus = getRestStatus();
                int hashLong = Internal.hashLong(getTimestamp());
                int taskType = getTaskType();
                int hashCode5 = getTaskName().hashCode();
                int hashCode6 = getUnit().hashCode();
                int floatToIntBits = ((((((((((((((((((((((((((((((((((((((((((((((((((hashCode + 779) * 37) + 1) * 53) + taskId) * 37) + 2) * 53) + hashCode2) * 37) + 3) * 53) + hashCode3) * 37) + 4) * 53) + hashCode4) * 37) + 5) * 53) + status) * 37) + 6) * 53) + restStatus) * 37) + 7) * 53) + hashLong) * 37) + 8) * 53) + taskType) * 37) + 9) * 53) + hashCode5) * 37) + 10) * 53) + hashCode6) * 37) + 11) * 53) + Float.floatToIntBits(getProgress())) * 37) + 12) * 53) + getStatusDes().hashCode()) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = floatToIntBits;
                return floatToIntBits;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: j, reason: merged with bridge method [inline-methods] */
            public a newBuilderForType() {
                return e();
            }

            public static a e() {
                return e.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: f, reason: merged with bridge method [inline-methods] */
            public a toBuilder() {
                return this == e ? new a() : new a().c(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public a newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new a(builderParent);
            }

            public static final class a extends GeneratedMessageV3.Builder<a> implements ReportTaskOrBuilder {

                /* renamed from: a, reason: collision with root package name */
                private float f1907a;
                private Object b;
                private int c;
                private Object d;
                private int e;
                private Object f;
                private int g;
                private Object h;
                private int i;
                private Object j;
                private long l;
                private Object o;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return HealthModelTaskBody.b.ensureFieldAccessorsInitialized(c.class, a.class);
                }

                private a() {
                    this.j = "";
                    this.b = "";
                    this.h = "";
                    this.f = "";
                    this.o = "";
                    this.d = "";
                    i();
                }

                private a(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.j = "";
                    this.b = "";
                    this.h = "";
                    this.f = "";
                    this.o = "";
                    this.d = "";
                    i();
                }

                private void i() {
                    boolean unused = c.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public a clear() {
                    super.clear();
                    this.g = 0;
                    this.j = "";
                    this.b = "";
                    this.h = "";
                    this.e = 0;
                    this.c = 0;
                    this.l = 0L;
                    this.i = 0;
                    this.f = "";
                    this.o = "";
                    this.f1907a = 0.0f;
                    this.d = "";
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return HealthModelTaskBody.f1904a;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public c getDefaultInstanceForType() {
                    return c.d();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public c build() {
                    c buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public c buildPartial() {
                    c cVar = new c(this);
                    cVar.g = this.g;
                    cVar.k = this.j;
                    cVar.h = this.b;
                    cVar.l = this.h;
                    cVar.i = this.e;
                    cVar.d = this.c;
                    cVar.o = this.l;
                    cVar.m = this.i;
                    cVar.j = this.f;
                    cVar.n = this.o;
                    cVar.c = this.f1907a;
                    cVar.f = this.d;
                    onBuilt();
                    return cVar;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public a mo113clone() {
                    return (a) super.mo113clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public a setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (a) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public a clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (a) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public a clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (a) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public a setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (a) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public a addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (a) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public a mergeFrom(Message message) {
                    if (message instanceof c) {
                        return c((c) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public a c(c cVar) {
                    if (cVar == c.d()) {
                        return this;
                    }
                    if (cVar.getTaskId() != 0) {
                        b(cVar.getTaskId());
                    }
                    if (!cVar.getTaskTarget().isEmpty()) {
                        this.j = cVar.k;
                        onChanged();
                    }
                    if (!cVar.getTargetDes().isEmpty()) {
                        this.b = cVar.h;
                        onChanged();
                    }
                    if (!cVar.getTaskValue().isEmpty()) {
                        this.h = cVar.l;
                        onChanged();
                    }
                    if (cVar.getStatus() != 0) {
                        c(cVar.getStatus());
                    }
                    if (cVar.getRestStatus() != 0) {
                        e(cVar.getRestStatus());
                    }
                    if (cVar.getTimestamp() != 0) {
                        b(cVar.getTimestamp());
                    }
                    if (cVar.getTaskType() != 0) {
                        a(cVar.getTaskType());
                    }
                    if (!cVar.getTaskName().isEmpty()) {
                        this.f = cVar.j;
                        onChanged();
                    }
                    if (!cVar.getUnit().isEmpty()) {
                        this.o = cVar.n;
                        onChanged();
                    }
                    if (cVar.getProgress() != 0.0f) {
                        a(cVar.getProgress());
                    }
                    if (!cVar.getStatusDes().isEmpty()) {
                        this.d = cVar.f;
                        onChanged();
                    }
                    mergeUnknownFields(cVar.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.c.a mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser r0 = com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.c.c()     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody$c r2 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.c) r2     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        if (r2 == 0) goto Lf
                        r1.c(r2)
                    Lf:
                        return r1
                    L10:
                        r2 = move-exception
                        goto L20
                    L12:
                        r2 = move-exception
                        com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L10
                        com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody$c r3 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.c) r3     // Catch: java.lang.Throwable -> L10
                        java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1e
                        throw r2     // Catch: java.lang.Throwable -> L1e
                    L1e:
                        r2 = move-exception
                        goto L21
                    L20:
                        r3 = 0
                    L21:
                        if (r3 == 0) goto L26
                        r1.c(r3)
                    L26:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.c.a.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody$c$a");
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public int getTaskId() {
                    return this.g;
                }

                public a b(int i) {
                    this.g = i;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public String getTaskTarget() {
                    Object obj = this.j;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.j = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public ByteString getTaskTargetBytes() {
                    Object obj = this.j;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.j = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public a e(String str) {
                    str.getClass();
                    this.j = str;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public String getTargetDes() {
                    Object obj = this.b;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.b = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public ByteString getTargetDesBytes() {
                    Object obj = this.b;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.b = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public a d(String str) {
                    str.getClass();
                    this.b = str;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public String getTaskValue() {
                    Object obj = this.h;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.h = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public ByteString getTaskValueBytes() {
                    Object obj = this.h;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.h = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public a c(String str) {
                    str.getClass();
                    this.h = str;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public int getStatus() {
                    return this.e;
                }

                public a c(int i) {
                    this.e = i;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public int getRestStatus() {
                    return this.c;
                }

                public a e(int i) {
                    this.c = i;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public long getTimestamp() {
                    return this.l;
                }

                public a b(long j) {
                    this.l = j;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public int getTaskType() {
                    return this.i;
                }

                public a a(int i) {
                    this.i = i;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public String getTaskName() {
                    Object obj = this.f;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.f = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public ByteString getTaskNameBytes() {
                    Object obj = this.f;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.f = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public a b(String str) {
                    str.getClass();
                    this.f = str;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public String getUnit() {
                    Object obj = this.o;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.o = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public ByteString getUnitBytes() {
                    Object obj = this.o;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.o = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public a g(String str) {
                    str.getClass();
                    this.o = str;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public float getProgress() {
                    return this.f1907a;
                }

                public a a(float f) {
                    this.f1907a = f;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public String getStatusDes() {
                    Object obj = this.d;
                    if (!(obj instanceof String)) {
                        String stringUtf8 = ((ByteString) obj).toStringUtf8();
                        this.d = stringUtf8;
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.ReportTaskOrBuilder
                public ByteString getStatusDesBytes() {
                    Object obj = this.d;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.d = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public a a(String str) {
                    str.getClass();
                    this.d = str;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final a setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (a) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public final a mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (a) super.mergeUnknownFields(unknownFieldSet);
                }
            }

            public static c d() {
                return e;
            }

            public static Parser<c> g() {
                return b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<c> getParserForType() {
                return b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public c getDefaultInstanceForType() {
                return e;
            }
        }

        public static final class d extends GeneratedMessageV3 implements CloverProgressOrBuilder {
            private static final d b = new d();
            private static final Parser<d> e = new AbstractParser<d>() { // from class: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.d.1
                @Override // com.google.protobuf.Parser
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public d parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new d(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;

            /* renamed from: a, reason: collision with root package name */
            private float f1908a;
            private byte c;
            private float d;
            private float j;

            private d(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.c = (byte) -1;
            }

            private d() {
                this.c = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new d();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 13) {
                                    this.j = codedInputStream.readFloat();
                                } else if (readTag == 21) {
                                    this.d = codedInputStream.readFloat();
                                } else if (readTag == 29) {
                                    this.f1908a = codedInputStream.readFloat();
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
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor b() {
                return HealthModelTaskBody.d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthModelTaskBody.c.ensureFieldAccessorsInitialized(d.class, a.class);
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.CloverProgressOrBuilder
            public float getTop() {
                return this.j;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.CloverProgressOrBuilder
            public float getLeft() {
                return this.d;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.CloverProgressOrBuilder
            public float getRight() {
                return this.f1908a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte b2 = this.c;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.c = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                float f = this.j;
                if (f != 0.0f) {
                    codedOutputStream.writeFloat(1, f);
                }
                float f2 = this.d;
                if (f2 != 0.0f) {
                    codedOutputStream.writeFloat(2, f2);
                }
                float f3 = this.f1908a;
                if (f3 != 0.0f) {
                    codedOutputStream.writeFloat(3, f3);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                float f = this.j;
                int computeFloatSize = f != 0.0f ? CodedOutputStream.computeFloatSize(1, f) : 0;
                float f2 = this.d;
                if (f2 != 0.0f) {
                    computeFloatSize += CodedOutputStream.computeFloatSize(2, f2);
                }
                float f3 = this.f1908a;
                if (f3 != 0.0f) {
                    computeFloatSize += CodedOutputStream.computeFloatSize(3, f3);
                }
                int serializedSize = computeFloatSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof d)) {
                    return super.equals(obj);
                }
                d dVar = (d) obj;
                return Float.floatToIntBits(getTop()) == Float.floatToIntBits(dVar.getTop()) && Float.floatToIntBits(getLeft()) == Float.floatToIntBits(dVar.getLeft()) && Float.floatToIntBits(getRight()) == Float.floatToIntBits(dVar.getRight()) && this.unknownFields.equals(dVar.unknownFields);
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = b().hashCode();
                int floatToIntBits = Float.floatToIntBits(getTop());
                int floatToIntBits2 = ((((((((((((((hashCode + 779) * 37) + 1) * 53) + floatToIntBits) * 37) + 2) * 53) + Float.floatToIntBits(getLeft())) * 37) + 3) * 53) + Float.floatToIntBits(getRight())) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = floatToIntBits2;
                return floatToIntBits2;
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: i, reason: merged with bridge method [inline-methods] */
            public a newBuilderForType() {
                return d();
            }

            public static a d() {
                return b.toBuilder();
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            /* renamed from: g, reason: merged with bridge method [inline-methods] */
            public a toBuilder() {
                return this == b ? new a() : new a().d(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public a newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new a(builderParent);
            }

            public static final class a extends GeneratedMessageV3.Builder<a> implements CloverProgressOrBuilder {

                /* renamed from: a, reason: collision with root package name */
                private float f1909a;
                private float b;
                private float d;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return HealthModelTaskBody.c.ensureFieldAccessorsInitialized(d.class, a.class);
                }

                private a() {
                    g();
                }

                private a(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    g();
                }

                private void g() {
                    boolean unused = d.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public a clear() {
                    super.clear();
                    this.f1909a = 0.0f;
                    this.b = 0.0f;
                    this.d = 0.0f;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return HealthModelTaskBody.d;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public d getDefaultInstanceForType() {
                    return d.a();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public d build() {
                    d buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public d buildPartial() {
                    d dVar = new d(this);
                    dVar.j = this.f1909a;
                    dVar.d = this.b;
                    dVar.f1908a = this.d;
                    onBuilt();
                    return dVar;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public a mo113clone() {
                    return (a) super.mo113clone();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public a setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (a) super.setField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public a clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (a) super.clearField(fieldDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public a clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (a) super.clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public a setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (a) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public a addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (a) super.addRepeatedField(fieldDescriptor, obj);
                }

                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public a mergeFrom(Message message) {
                    if (message instanceof d) {
                        return d((d) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public a d(d dVar) {
                    if (dVar == d.a()) {
                        return this;
                    }
                    if (dVar.getTop() != 0.0f) {
                        c(dVar.getTop());
                    }
                    if (dVar.getLeft() != 0.0f) {
                        a(dVar.getLeft());
                    }
                    if (dVar.getRight() != 0.0f) {
                        b(dVar.getRight());
                    }
                    mergeUnknownFields(dVar.unknownFields);
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
                public com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.d.a mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser r0 = com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.d.e()     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody$d r2 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.d) r2     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                        if (r2 == 0) goto Lf
                        r1.d(r2)
                    Lf:
                        return r1
                    L10:
                        r2 = move-exception
                        goto L20
                    L12:
                        r2 = move-exception
                        com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L10
                        com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody$d r3 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.d) r3     // Catch: java.lang.Throwable -> L10
                        java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1e
                        throw r2     // Catch: java.lang.Throwable -> L1e
                    L1e:
                        r2 = move-exception
                        goto L21
                    L20:
                        r3 = 0
                    L21:
                        if (r3 == 0) goto L26
                        r1.d(r3)
                    L26:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.d.a.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody$d$a");
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.CloverProgressOrBuilder
                public float getTop() {
                    return this.f1909a;
                }

                public a c(float f) {
                    this.f1909a = f;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.CloverProgressOrBuilder
                public float getLeft() {
                    return this.b;
                }

                public a a(float f) {
                    this.b = f;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.CloverProgressOrBuilder
                public float getRight() {
                    return this.d;
                }

                public a b(float f) {
                    this.d = f;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public final a setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (a) super.setUnknownFields(unknownFieldSet);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public final a mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (a) super.mergeUnknownFields(unknownFieldSet);
                }
            }

            public static d a() {
                return b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<d> getParserForType() {
                return e;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: f, reason: merged with bridge method [inline-methods] */
            public d getDefaultInstanceForType() {
                return b;
            }
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public int getResultCode() {
            return this.g;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public String getResultDes() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.h = stringUtf8;
            return stringUtf8;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public ByteString getResultDesBytes() {
            Object obj = this.h;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.h = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public int getRecordDay() {
            return this.f;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public String getAllTasks() {
            Object obj = this.d;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.d = stringUtf8;
            return stringUtf8;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public ByteString getAllTasksBytes() {
            Object obj = this.d;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.d = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public List<c> getTasksList() {
            return this.i;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public List<? extends ReportTaskOrBuilder> getTasksOrBuilderList() {
            return this.i;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public int getTasksCount() {
            return this.i.size();
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public c getTasks(int i) {
            return this.i.get(i);
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public ReportTaskOrBuilder getTasksOrBuilder(int i) {
            return this.i.get(i);
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public String getCompletedTasks() {
            Object obj = this.e;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.e = stringUtf8;
            return stringUtf8;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public ByteString getCompletedTasksBytes() {
            Object obj = this.e;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.e = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
        public ByteString getCloverProgress() {
            return this.b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.j;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.j = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.g;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            if (!getResultDesBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.h);
            }
            int i2 = this.f;
            if (i2 != 0) {
                codedOutputStream.writeInt32(3, i2);
            }
            if (!getAllTasksBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.d);
            }
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                codedOutputStream.writeMessage(5, this.i.get(i3));
            }
            if (!getCompletedTasksBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.e);
            }
            if (!this.b.isEmpty()) {
                codedOutputStream.writeBytes(7, this.b);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.g;
            int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) : 0;
            if (!getResultDesBytes().isEmpty()) {
                computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.h);
            }
            int i3 = this.f;
            if (i3 != 0) {
                computeInt32Size += CodedOutputStream.computeInt32Size(3, i3);
            }
            if (!getAllTasksBytes().isEmpty()) {
                computeInt32Size += GeneratedMessageV3.computeStringSize(4, this.d);
            }
            for (int i4 = 0; i4 < this.i.size(); i4++) {
                computeInt32Size += CodedOutputStream.computeMessageSize(5, this.i.get(i4));
            }
            if (!getCompletedTasksBytes().isEmpty()) {
                computeInt32Size += GeneratedMessageV3.computeStringSize(6, this.e);
            }
            if (!this.b.isEmpty()) {
                computeInt32Size += CodedOutputStream.computeBytesSize(7, this.b);
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
            if (!(obj instanceof TaskBody)) {
                return super.equals(obj);
            }
            TaskBody taskBody = (TaskBody) obj;
            return getResultCode() == taskBody.getResultCode() && getResultDes().equals(taskBody.getResultDes()) && getRecordDay() == taskBody.getRecordDay() && getAllTasks().equals(taskBody.getAllTasks()) && getTasksList().equals(taskBody.getTasksList()) && getCompletedTasks().equals(taskBody.getCompletedTasks()) && getCloverProgress().equals(taskBody.getCloverProgress()) && this.unknownFields.equals(taskBody.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = a().hashCode();
            int resultCode = getResultCode();
            int hashCode2 = ((((((((((((((((hashCode + 779) * 37) + 1) * 53) + resultCode) * 37) + 2) * 53) + getResultDes().hashCode()) * 37) + 3) * 53) + getRecordDay()) * 37) + 4) * 53) + getAllTasks().hashCode();
            if (getTasksCount() > 0) {
                hashCode2 = (((hashCode2 * 37) + 5) * 53) + getTasksList().hashCode();
            }
            int hashCode3 = (((((((((hashCode2 * 37) + 6) * 53) + getCompletedTasks().hashCode()) * 37) + 7) * 53) + getCloverProgress().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public e newBuilderForType() {
            return i();
        }

        public static e i() {
            return f1905a.toBuilder();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public e toBuilder() {
            return this == f1905a ? new e() : new e().e(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public e newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new e(builderParent);
        }

        public static final class e extends GeneratedMessageV3.Builder<e> implements TaskBodyOrBuilder {

            /* renamed from: a, reason: collision with root package name */
            private Object f1910a;
            private ByteString b;
            private Object c;
            private int d;
            private int e;
            private List<c> g;
            private int h;
            private Object i;
            private RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> j;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthModelTaskBody.f.ensureFieldAccessorsInitialized(TaskBody.class, e.class);
            }

            private e() {
                this.i = "";
                this.f1910a = "";
                this.g = Collections.emptyList();
                this.c = "";
                this.b = ByteString.EMPTY;
                g();
            }

            private e(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.i = "";
                this.f1910a = "";
                this.g = Collections.emptyList();
                this.c = "";
                this.b = ByteString.EMPTY;
                g();
            }

            private void g() {
                if (TaskBody.alwaysUseFieldBuilders) {
                    i();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public e clear() {
                super.clear();
                this.h = 0;
                this.i = "";
                this.d = 0;
                this.f1910a = "";
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 == null) {
                    this.g = Collections.emptyList();
                    this.e &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.c = "";
                this.b = ByteString.EMPTY;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return HealthModelTaskBody.g;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public TaskBody getDefaultInstanceForType() {
                return TaskBody.b();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public TaskBody build() {
                TaskBody buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public TaskBody buildPartial() {
                TaskBody taskBody = new TaskBody(this);
                taskBody.g = this.h;
                taskBody.h = this.i;
                taskBody.f = this.d;
                taskBody.d = this.f1910a;
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 != null) {
                    taskBody.i = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.e & 1) != 0) {
                        this.g = Collections.unmodifiableList(this.g);
                        this.e &= -2;
                    }
                    taskBody.i = this.g;
                }
                taskBody.e = this.c;
                taskBody.b = this.b;
                onBuilt();
                return taskBody;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public e mo113clone() {
                return (e) super.mo113clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public e setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (e) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public e clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (e) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public e clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (e) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public e setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (e) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public e addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (e) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public e mergeFrom(Message message) {
                if (message instanceof TaskBody) {
                    return e((TaskBody) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public e e(TaskBody taskBody) {
                if (taskBody == TaskBody.b()) {
                    return this;
                }
                if (taskBody.getResultCode() != 0) {
                    d(taskBody.getResultCode());
                }
                if (!taskBody.getResultDes().isEmpty()) {
                    this.i = taskBody.h;
                    onChanged();
                }
                if (taskBody.getRecordDay() != 0) {
                    c(taskBody.getRecordDay());
                }
                if (!taskBody.getAllTasks().isEmpty()) {
                    this.f1910a = taskBody.d;
                    onChanged();
                }
                if (this.j == null) {
                    if (!taskBody.i.isEmpty()) {
                        if (this.g.isEmpty()) {
                            this.g = taskBody.i;
                            this.e &= -2;
                        } else {
                            j();
                            this.g.addAll(taskBody.i);
                        }
                        onChanged();
                    }
                } else if (!taskBody.i.isEmpty()) {
                    if (!this.j.isEmpty()) {
                        this.j.addAllMessages(taskBody.i);
                    } else {
                        this.j.dispose();
                        this.j = null;
                        this.g = taskBody.i;
                        this.e &= -2;
                        this.j = TaskBody.alwaysUseFieldBuilders ? i() : null;
                    }
                }
                if (!taskBody.getCompletedTasks().isEmpty()) {
                    this.c = taskBody.e;
                    onChanged();
                }
                if (taskBody.getCloverProgress() != ByteString.EMPTY) {
                    a(taskBody.getCloverProgress());
                }
                mergeUnknownFields(taskBody.unknownFields);
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
            public com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.e mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser r0 = com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.c()     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
                    com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody r2 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody) r2     // Catch: java.lang.Throwable -> L10 com.google.protobuf.InvalidProtocolBufferException -> L12
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
                    com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody r3 = (com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody) r3     // Catch: java.lang.Throwable -> L10
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
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBody.e.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody$TaskBody$e");
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public int getResultCode() {
                return this.h;
            }

            public e d(int i) {
                this.h = i;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public String getResultDes() {
                Object obj = this.i;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.i = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public ByteString getResultDesBytes() {
                Object obj = this.i;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.i = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public e d(String str) {
                str.getClass();
                this.i = str;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public int getRecordDay() {
                return this.d;
            }

            public e c(int i) {
                this.d = i;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public String getAllTasks() {
                Object obj = this.f1910a;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.f1910a = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public ByteString getAllTasksBytes() {
                Object obj = this.f1910a;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.f1910a = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public e e(String str) {
                str.getClass();
                this.f1910a = str;
                onChanged();
                return this;
            }

            private void j() {
                if ((this.e & 1) == 0) {
                    this.g = new ArrayList(this.g);
                    this.e |= 1;
                }
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public List<c> getTasksList() {
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.g);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public int getTasksCount() {
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 == null) {
                    return this.g.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public c getTasks(int i) {
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 == null) {
                    return this.g.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public e b(int i, c cVar) {
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 == null) {
                    cVar.getClass();
                    j();
                    this.g.add(i, cVar);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, cVar);
                }
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public ReportTaskOrBuilder getTasksOrBuilder(int i) {
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 == null) {
                    return this.g.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public List<? extends ReportTaskOrBuilder> getTasksOrBuilderList() {
                RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> repeatedFieldBuilderV3 = this.j;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.g);
            }

            private RepeatedFieldBuilderV3<c, c.a, ReportTaskOrBuilder> i() {
                if (this.j == null) {
                    this.j = new RepeatedFieldBuilderV3<>(this.g, (this.e & 1) != 0, getParentForChildren(), isClean());
                    this.g = null;
                }
                return this.j;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public String getCompletedTasks() {
                Object obj = this.c;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.c = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public ByteString getCompletedTasksBytes() {
                Object obj = this.c;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.c = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public e c(String str) {
                str.getClass();
                this.c = str;
                onChanged();
                return this;
            }

            @Override // com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody.TaskBodyOrBuilder
            public ByteString getCloverProgress() {
                return this.b;
            }

            public e a(ByteString byteString) {
                byteString.getClass();
                this.b = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public final e setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (e) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final e mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (e) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static TaskBody b() {
            return f1905a;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<TaskBody> getParserForType() {
            return c;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public TaskBody getDefaultInstanceForType() {
            return f1905a;
        }
    }

    public static Descriptors.FileDescriptor h() {
        return e;
    }

    static {
        Descriptors.Descriptor descriptor = h().getMessageTypes().get(0);
        g = descriptor;
        f = new GeneratedMessageV3.FieldAccessorTable(descriptor, new String[]{"ResultCode", "ResultDes", "RecordDay", "AllTasks", "Tasks", "CompletedTasks", "CloverProgress"});
        Descriptors.Descriptor descriptor2 = descriptor.getNestedTypes().get(0);
        f1904a = descriptor2;
        b = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"TaskId", "TaskTarget", "TargetDes", "TaskValue", "Status", "RestStatus", "Timestamp", "TaskType", "TaskName", "Unit", "Progress", "StatusDes"});
        Descriptors.Descriptor descriptor3 = descriptor.getNestedTypes().get(1);
        d = descriptor3;
        c = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Top", "Left", "Right"});
    }
}
