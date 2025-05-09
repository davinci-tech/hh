package com.huawei.health.suggestion.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
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
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class CourseDataForDevice {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\fcourse.proto\"Ê\u0004\n\u0011CourseStorageInfo\u0012\u0012\n\nexerciseId\u0018\u0001 \u0002(\t\u0012\u0014\n\fexerciseName\u0018\u0002 \u0003(\r\u0012\u001c\n\u0014totalMeasurementType\u0018\u0003 \u0002(\r\u0012\u001d\n\u0015totalMeasurementValue\u0018\u0004 \u0002(\r\u0012\u0017\n\u000fexerciseVersion\u0018\u0005 \u0002(\t\u0012\u0011\n\tdifficult\u0018\u0006 \u0002(\r\u0012\u0011\n\tusingType\u0018\u0007 \u0002(\r\u0012\u0012\n\nupdateTime\u0018\b \u0002(\r\u0012\u0012\n\ndefineType\u0018\t \u0002(\r\u0012\u0012\n\ncourseType\u0018\n \u0002(\r\u0012\u0016\n\u000eactionTotalNum\u0018\u000b \u0002(\r\u00123\n\u000bcombineInfo\u0018\f \u0003(\u000b2\u001e.CourseStorageInfo.CombineInfo\u001a\u009d\u0001\n\nActionInfo\u0012\u0010\n\bactionId\u0018\u0001 \u0002(\t\u0012\u0017\n\u000fmeasurementType\u0018\u0002 \u0002(\r\u0012\u0018\n\u0010measurementValue\u0018\u0003 \u0002(\r\u0012\u0016\n\u000einstensityType\u0018\u0004 \u0002(\r\u0012\u0018\n\u0010instensityValueH\u0018\u0005 \u0002(\r\u0012\u0018\n\u0010instensityValueL\u0018\u0006 \u0002(\r\u001af\n\u000bCombineInfo\u0012\u0011\n\trepeatNum\u0018\u0001 \u0002(\r\u0012\u0011\n\tactionNum\u0018\u0002 \u0002(\r\u00121\n\nactionInfo\u0018\u0003 \u0003(\u000b2\u001d.CourseStorageInfo.ActionInfoB<\n%com.huawei.health.suggestion.protobufB\u0013CourseDataForDevice"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_CourseStorageInfo_ActionInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_CourseStorageInfo_ActionInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_CourseStorageInfo_CombineInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_CourseStorageInfo_CombineInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_CourseStorageInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_CourseStorageInfo_fieldAccessorTable;

    public interface CourseStorageInfoOrBuilder extends MessageOrBuilder {
        int getActionTotalNum();

        CourseStorageInfo.CombineInfo getCombineInfo(int i);

        int getCombineInfoCount();

        List<CourseStorageInfo.CombineInfo> getCombineInfoList();

        CourseStorageInfo.CombineInfoOrBuilder getCombineInfoOrBuilder(int i);

        List<? extends CourseStorageInfo.CombineInfoOrBuilder> getCombineInfoOrBuilderList();

        int getCourseType();

        int getDefineType();

        int getDifficult();

        String getExerciseId();

        ByteString getExerciseIdBytes();

        int getExerciseName(int i);

        int getExerciseNameCount();

        List<Integer> getExerciseNameList();

        String getExerciseVersion();

        ByteString getExerciseVersionBytes();

        int getTotalMeasurementType();

        int getTotalMeasurementValue();

        int getUpdateTime();

        int getUsingType();

        boolean hasActionTotalNum();

        boolean hasCourseType();

        boolean hasDefineType();

        boolean hasDifficult();

        boolean hasExerciseId();

        boolean hasExerciseVersion();

        boolean hasTotalMeasurementType();

        boolean hasTotalMeasurementValue();

        boolean hasUpdateTime();

        boolean hasUsingType();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private CourseDataForDevice() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class CourseStorageInfo extends GeneratedMessageV3 implements CourseStorageInfoOrBuilder {
        public static final int ACTIONTOTALNUM_FIELD_NUMBER = 11;
        public static final int COMBINEINFO_FIELD_NUMBER = 12;
        public static final int COURSETYPE_FIELD_NUMBER = 10;
        public static final int DEFINETYPE_FIELD_NUMBER = 9;
        public static final int DIFFICULT_FIELD_NUMBER = 6;
        public static final int EXERCISEID_FIELD_NUMBER = 1;
        public static final int EXERCISENAME_FIELD_NUMBER = 2;
        public static final int EXERCISEVERSION_FIELD_NUMBER = 5;
        public static final int TOTALMEASUREMENTTYPE_FIELD_NUMBER = 3;
        public static final int TOTALMEASUREMENTVALUE_FIELD_NUMBER = 4;
        public static final int UPDATETIME_FIELD_NUMBER = 8;
        public static final int USINGTYPE_FIELD_NUMBER = 7;
        private static final long serialVersionUID = 0;
        private int actionTotalNum_;
        private int bitField0_;
        private List<CombineInfo> combineInfo_;
        private int courseType_;
        private int defineType_;
        private int difficult_;
        private volatile Object exerciseId_;
        private Internal.IntList exerciseName_;
        private volatile Object exerciseVersion_;
        private byte memoizedIsInitialized;
        private int totalMeasurementType_;
        private int totalMeasurementValue_;
        private int updateTime_;
        private int usingType_;
        private static final CourseStorageInfo DEFAULT_INSTANCE = new CourseStorageInfo();

        @Deprecated
        public static final Parser<CourseStorageInfo> PARSER = new AbstractParser<CourseStorageInfo>() { // from class: com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.1
            @Override // com.google.protobuf.Parser
            public CourseStorageInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CourseStorageInfo(codedInputStream, extensionRegistryLite);
            }
        };

        public interface ActionInfoOrBuilder extends MessageOrBuilder {
            String getActionId();

            ByteString getActionIdBytes();

            int getInstensityType();

            int getInstensityValueH();

            int getInstensityValueL();

            int getMeasurementType();

            int getMeasurementValue();

            boolean hasActionId();

            boolean hasInstensityType();

            boolean hasInstensityValueH();

            boolean hasInstensityValueL();

            boolean hasMeasurementType();

            boolean hasMeasurementValue();
        }

        public interface CombineInfoOrBuilder extends MessageOrBuilder {
            ActionInfo getActionInfo(int i);

            int getActionInfoCount();

            List<ActionInfo> getActionInfoList();

            ActionInfoOrBuilder getActionInfoOrBuilder(int i);

            List<? extends ActionInfoOrBuilder> getActionInfoOrBuilderList();

            int getActionNum();

            int getRepeatNum();

            boolean hasActionNum();

            boolean hasRepeatNum();
        }

        private CourseStorageInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CourseStorageInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.exerciseId_ = "";
            this.exerciseName_ = emptyIntList();
            this.exerciseVersion_ = "";
            this.combineInfo_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new CourseStorageInfo();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CourseStorageInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.exerciseId_ = readBytes;
                            case 16:
                                if ((i & 2) == 0) {
                                    this.exerciseName_ = newIntList();
                                    i |= 2;
                                }
                                this.exerciseName_.addInt(codedInputStream.readUInt32());
                            case 18:
                                int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 2) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.exerciseName_ = newIntList();
                                    i |= 2;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.exerciseName_.addInt(codedInputStream.readUInt32());
                                }
                                codedInputStream.popLimit(pushLimit);
                                break;
                            case 24:
                                this.bitField0_ |= 2;
                                this.totalMeasurementType_ = codedInputStream.readUInt32();
                            case 32:
                                this.bitField0_ |= 4;
                                this.totalMeasurementValue_ = codedInputStream.readUInt32();
                            case 42:
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.bitField0_ |= 8;
                                this.exerciseVersion_ = readBytes2;
                            case 48:
                                this.bitField0_ |= 16;
                                this.difficult_ = codedInputStream.readUInt32();
                            case 56:
                                this.bitField0_ |= 32;
                                this.usingType_ = codedInputStream.readUInt32();
                            case 64:
                                this.bitField0_ |= 64;
                                this.updateTime_ = codedInputStream.readUInt32();
                            case 72:
                                this.bitField0_ |= 128;
                                this.defineType_ = codedInputStream.readUInt32();
                            case 80:
                                this.bitField0_ |= 256;
                                this.courseType_ = codedInputStream.readUInt32();
                            case 88:
                                this.bitField0_ |= 512;
                                this.actionTotalNum_ = codedInputStream.readUInt32();
                            case 98:
                                if ((i & 2048) == 0) {
                                    this.combineInfo_ = new ArrayList();
                                    i |= 2048;
                                }
                                this.combineInfo_.add((CombineInfo) codedInputStream.readMessage(CombineInfo.PARSER, extensionRegistryLite));
                            default:
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    z = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if ((i & 2) != 0) {
                        this.exerciseName_.makeImmutable();
                    }
                    if ((i & 2048) != 0) {
                        this.combineInfo_ = Collections.unmodifiableList(this.combineInfo_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CourseDataForDevice.internal_static_CourseStorageInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CourseDataForDevice.internal_static_CourseStorageInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(CourseStorageInfo.class, Builder.class);
        }

        public static final class ActionInfo extends GeneratedMessageV3 implements ActionInfoOrBuilder {
            public static final int ACTIONID_FIELD_NUMBER = 1;
            public static final int INSTENSITYTYPE_FIELD_NUMBER = 4;
            public static final int INSTENSITYVALUEH_FIELD_NUMBER = 5;
            public static final int INSTENSITYVALUEL_FIELD_NUMBER = 6;
            public static final int MEASUREMENTTYPE_FIELD_NUMBER = 2;
            public static final int MEASUREMENTVALUE_FIELD_NUMBER = 3;
            private static final long serialVersionUID = 0;
            private volatile Object actionId_;
            private int bitField0_;
            private int instensityType_;
            private int instensityValueH_;
            private int instensityValueL_;
            private int measurementType_;
            private int measurementValue_;
            private byte memoizedIsInitialized;
            private static final ActionInfo DEFAULT_INSTANCE = new ActionInfo();

            @Deprecated
            public static final Parser<ActionInfo> PARSER = new AbstractParser<ActionInfo>() { // from class: com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfo.1
                @Override // com.google.protobuf.Parser
                public ActionInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ActionInfo(codedInputStream, extensionRegistryLite);
                }
            };

            private ActionInfo(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private ActionInfo() {
                this.memoizedIsInitialized = (byte) -1;
                this.actionId_ = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new ActionInfo();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private ActionInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 10) {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.actionId_ = readBytes;
                                    } else if (readTag == 16) {
                                        this.bitField0_ |= 2;
                                        this.measurementType_ = codedInputStream.readUInt32();
                                    } else if (readTag == 24) {
                                        this.bitField0_ |= 4;
                                        this.measurementValue_ = codedInputStream.readUInt32();
                                    } else if (readTag == 32) {
                                        this.bitField0_ |= 8;
                                        this.instensityType_ = codedInputStream.readUInt32();
                                    } else if (readTag == 40) {
                                        this.bitField0_ |= 16;
                                        this.instensityValueH_ = codedInputStream.readUInt32();
                                    } else if (readTag == 48) {
                                        this.bitField0_ |= 32;
                                        this.instensityValueL_ = codedInputStream.readUInt32();
                                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.setUnfinishedMessage(this);
                            }
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
                return CourseDataForDevice.internal_static_CourseStorageInfo_ActionInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CourseDataForDevice.internal_static_CourseStorageInfo_ActionInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ActionInfo.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public boolean hasActionId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public String getActionId() {
                Object obj = this.actionId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.actionId_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public ByteString getActionIdBytes() {
                Object obj = this.actionId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.actionId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public boolean hasMeasurementType() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public int getMeasurementType() {
                return this.measurementType_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public boolean hasMeasurementValue() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public int getMeasurementValue() {
                return this.measurementValue_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public boolean hasInstensityType() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public int getInstensityType() {
                return this.instensityType_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public boolean hasInstensityValueH() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public int getInstensityValueH() {
                return this.instensityValueH_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public boolean hasInstensityValueL() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
            public int getInstensityValueL() {
                return this.instensityValueL_;
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
                if (!hasActionId()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasMeasurementType()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasMeasurementValue()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasInstensityType()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasInstensityValueH()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasInstensityValueL()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.actionId_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeUInt32(2, this.measurementType_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeUInt32(3, this.measurementValue_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeUInt32(4, this.instensityType_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    codedOutputStream.writeUInt32(5, this.instensityValueH_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    codedOutputStream.writeUInt32(6, this.instensityValueL_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.actionId_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeStringSize += CodedOutputStream.computeUInt32Size(2, this.measurementType_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    computeStringSize += CodedOutputStream.computeUInt32Size(3, this.measurementValue_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    computeStringSize += CodedOutputStream.computeUInt32Size(4, this.instensityType_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    computeStringSize += CodedOutputStream.computeUInt32Size(5, this.instensityValueH_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    computeStringSize += CodedOutputStream.computeUInt32Size(6, this.instensityValueL_);
                }
                int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ActionInfo)) {
                    return super.equals(obj);
                }
                ActionInfo actionInfo = (ActionInfo) obj;
                if (hasActionId() != actionInfo.hasActionId()) {
                    return false;
                }
                if ((hasActionId() && !getActionId().equals(actionInfo.getActionId())) || hasMeasurementType() != actionInfo.hasMeasurementType()) {
                    return false;
                }
                if ((hasMeasurementType() && getMeasurementType() != actionInfo.getMeasurementType()) || hasMeasurementValue() != actionInfo.hasMeasurementValue()) {
                    return false;
                }
                if ((hasMeasurementValue() && getMeasurementValue() != actionInfo.getMeasurementValue()) || hasInstensityType() != actionInfo.hasInstensityType()) {
                    return false;
                }
                if ((hasInstensityType() && getInstensityType() != actionInfo.getInstensityType()) || hasInstensityValueH() != actionInfo.hasInstensityValueH()) {
                    return false;
                }
                if ((!hasInstensityValueH() || getInstensityValueH() == actionInfo.getInstensityValueH()) && hasInstensityValueL() == actionInfo.hasInstensityValueL()) {
                    return (!hasInstensityValueL() || getInstensityValueL() == actionInfo.getInstensityValueL()) && this.unknownFields.equals(actionInfo.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasActionId()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getActionId().hashCode();
                }
                if (hasMeasurementType()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getMeasurementType();
                }
                if (hasMeasurementValue()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getMeasurementValue();
                }
                if (hasInstensityType()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getInstensityType();
                }
                if (hasInstensityValueH()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getInstensityValueH();
                }
                if (hasInstensityValueL()) {
                    hashCode = (((hashCode * 37) + 6) * 53) + getInstensityValueL();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static ActionInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static ActionInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static ActionInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static ActionInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ActionInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static ActionInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static ActionInfo parseFrom(InputStream inputStream) throws IOException {
                return (ActionInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static ActionInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ActionInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ActionInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (ActionInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static ActionInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ActionInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ActionInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (ActionInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static ActionInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ActionInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ActionInfo actionInfo) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(actionInfo);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ActionInfoOrBuilder {
                private Object actionId_;
                private int bitField0_;
                private int instensityType_;
                private int instensityValueH_;
                private int instensityValueL_;
                private int measurementType_;
                private int measurementValue_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return CourseDataForDevice.internal_static_CourseStorageInfo_ActionInfo_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return CourseDataForDevice.internal_static_CourseStorageInfo_ActionInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ActionInfo.class, Builder.class);
                }

                private Builder() {
                    this.actionId_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.actionId_ = "";
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = ActionInfo.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.actionId_ = "";
                    int i = this.bitField0_;
                    this.measurementType_ = 0;
                    this.measurementValue_ = 0;
                    this.instensityType_ = 0;
                    this.instensityValueH_ = 0;
                    this.instensityValueL_ = 0;
                    this.bitField0_ = i & (-64);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return CourseDataForDevice.internal_static_CourseStorageInfo_ActionInfo_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public ActionInfo getDefaultInstanceForType() {
                    return ActionInfo.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ActionInfo build() {
                    ActionInfo buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ActionInfo buildPartial() {
                    ActionInfo actionInfo = new ActionInfo(this);
                    int i = this.bitField0_;
                    int i2 = (i & 1) != 0 ? 1 : 0;
                    actionInfo.actionId_ = this.actionId_;
                    if ((i & 2) != 0) {
                        actionInfo.measurementType_ = this.measurementType_;
                        i2 |= 2;
                    }
                    if ((i & 4) != 0) {
                        actionInfo.measurementValue_ = this.measurementValue_;
                        i2 |= 4;
                    }
                    if ((i & 8) != 0) {
                        actionInfo.instensityType_ = this.instensityType_;
                        i2 |= 8;
                    }
                    if ((i & 16) != 0) {
                        actionInfo.instensityValueH_ = this.instensityValueH_;
                        i2 |= 16;
                    }
                    if ((i & 32) != 0) {
                        actionInfo.instensityValueL_ = this.instensityValueL_;
                        i2 |= 32;
                    }
                    actionInfo.bitField0_ = i2;
                    onBuilt();
                    return actionInfo;
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
                    if (message instanceof ActionInfo) {
                        return mergeFrom((ActionInfo) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ActionInfo actionInfo) {
                    if (actionInfo == ActionInfo.getDefaultInstance()) {
                        return this;
                    }
                    if (actionInfo.hasActionId()) {
                        this.bitField0_ |= 1;
                        this.actionId_ = actionInfo.actionId_;
                        onChanged();
                    }
                    if (actionInfo.hasMeasurementType()) {
                        setMeasurementType(actionInfo.getMeasurementType());
                    }
                    if (actionInfo.hasMeasurementValue()) {
                        setMeasurementValue(actionInfo.getMeasurementValue());
                    }
                    if (actionInfo.hasInstensityType()) {
                        setInstensityType(actionInfo.getInstensityType());
                    }
                    if (actionInfo.hasInstensityValueH()) {
                        setInstensityValueH(actionInfo.getInstensityValueH());
                    }
                    if (actionInfo.hasInstensityValueL()) {
                        setInstensityValueL(actionInfo.getInstensityValueL());
                    }
                    mergeUnknownFields(actionInfo.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return hasActionId() && hasMeasurementType() && hasMeasurementValue() && hasInstensityType() && hasInstensityValueH() && hasInstensityValueL();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$ActionInfo> r0 = com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$ActionInfo r2 = (com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        if (r2 == 0) goto Ld
                        r1.mergeFrom(r2)
                    Ld:
                        return r1
                    Le:
                        r2 = move-exception
                        goto L1e
                    L10:
                        r2 = move-exception
                        com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Le
                        com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$ActionInfo r3 = (com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfo) r3     // Catch: java.lang.Throwable -> Le
                        java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1c
                        throw r2     // Catch: java.lang.Throwable -> L1c
                    L1c:
                        r2 = move-exception
                        goto L1f
                    L1e:
                        r3 = 0
                    L1f:
                        if (r3 == 0) goto L24
                        r1.mergeFrom(r3)
                    L24:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$ActionInfo$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public boolean hasActionId() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public String getActionId() {
                    Object obj = this.actionId_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.actionId_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public ByteString getActionIdBytes() {
                    Object obj = this.actionId_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.actionId_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setActionId(String str) {
                    str.getClass();
                    this.bitField0_ |= 1;
                    this.actionId_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearActionId() {
                    this.bitField0_ &= -2;
                    this.actionId_ = ActionInfo.getDefaultInstance().getActionId();
                    onChanged();
                    return this;
                }

                public Builder setActionIdBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 1;
                    this.actionId_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public boolean hasMeasurementType() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public int getMeasurementType() {
                    return this.measurementType_;
                }

                public Builder setMeasurementType(int i) {
                    this.bitField0_ |= 2;
                    this.measurementType_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearMeasurementType() {
                    this.bitField0_ &= -3;
                    this.measurementType_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public boolean hasMeasurementValue() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public int getMeasurementValue() {
                    return this.measurementValue_;
                }

                public Builder setMeasurementValue(int i) {
                    this.bitField0_ |= 4;
                    this.measurementValue_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearMeasurementValue() {
                    this.bitField0_ &= -5;
                    this.measurementValue_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public boolean hasInstensityType() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public int getInstensityType() {
                    return this.instensityType_;
                }

                public Builder setInstensityType(int i) {
                    this.bitField0_ |= 8;
                    this.instensityType_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearInstensityType() {
                    this.bitField0_ &= -9;
                    this.instensityType_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public boolean hasInstensityValueH() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public int getInstensityValueH() {
                    return this.instensityValueH_;
                }

                public Builder setInstensityValueH(int i) {
                    this.bitField0_ |= 16;
                    this.instensityValueH_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearInstensityValueH() {
                    this.bitField0_ &= -17;
                    this.instensityValueH_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public boolean hasInstensityValueL() {
                    return (this.bitField0_ & 32) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.ActionInfoOrBuilder
                public int getInstensityValueL() {
                    return this.instensityValueL_;
                }

                public Builder setInstensityValueL(int i) {
                    this.bitField0_ |= 32;
                    this.instensityValueL_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearInstensityValueL() {
                    this.bitField0_ &= -33;
                    this.instensityValueL_ = 0;
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

            public static ActionInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ActionInfo> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<ActionInfo> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ActionInfo getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class CombineInfo extends GeneratedMessageV3 implements CombineInfoOrBuilder {
            public static final int ACTIONINFO_FIELD_NUMBER = 3;
            public static final int ACTIONNUM_FIELD_NUMBER = 2;
            private static final CombineInfo DEFAULT_INSTANCE = new CombineInfo();

            @Deprecated
            public static final Parser<CombineInfo> PARSER = new AbstractParser<CombineInfo>() { // from class: com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfo.1
                @Override // com.google.protobuf.Parser
                public CombineInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new CombineInfo(codedInputStream, extensionRegistryLite);
                }
            };
            public static final int REPEATNUM_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private List<ActionInfo> actionInfo_;
            private int actionNum_;
            private int bitField0_;
            private byte memoizedIsInitialized;
            private int repeatNum_;

            private CombineInfo(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private CombineInfo() {
                this.memoizedIsInitialized = (byte) -1;
                this.actionInfo_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new CombineInfo();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private CombineInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                char c = 0;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.bitField0_ |= 1;
                                    this.repeatNum_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.actionNum_ = codedInputStream.readUInt32();
                                } else if (readTag == 26) {
                                    if ((c & 4) == 0) {
                                        this.actionInfo_ = new ArrayList();
                                        c = 4;
                                    }
                                    this.actionInfo_.add((ActionInfo) codedInputStream.readMessage(ActionInfo.PARSER, extensionRegistryLite));
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
                        if ((c & 4) != 0) {
                            this.actionInfo_ = Collections.unmodifiableList(this.actionInfo_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return CourseDataForDevice.internal_static_CourseStorageInfo_CombineInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CourseDataForDevice.internal_static_CourseStorageInfo_CombineInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(CombineInfo.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public boolean hasRepeatNum() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public int getRepeatNum() {
                return this.repeatNum_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public boolean hasActionNum() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public int getActionNum() {
                return this.actionNum_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public List<ActionInfo> getActionInfoList() {
                return this.actionInfo_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public List<? extends ActionInfoOrBuilder> getActionInfoOrBuilderList() {
                return this.actionInfo_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public int getActionInfoCount() {
                return this.actionInfo_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public ActionInfo getActionInfo(int i) {
                return this.actionInfo_.get(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
            public ActionInfoOrBuilder getActionInfoOrBuilder(int i) {
                return this.actionInfo_.get(i);
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
                if (!hasRepeatNum()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasActionNum()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                for (int i = 0; i < getActionInfoCount(); i++) {
                    if (!getActionInfo(i).isInitialized()) {
                        this.memoizedIsInitialized = (byte) 0;
                        return false;
                    }
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    codedOutputStream.writeUInt32(1, this.repeatNum_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeUInt32(2, this.actionNum_);
                }
                for (int i = 0; i < this.actionInfo_.size(); i++) {
                    codedOutputStream.writeMessage(3, this.actionInfo_.get(i));
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeUInt32Size = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeUInt32Size(1, this.repeatNum_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, this.actionNum_);
                }
                for (int i2 = 0; i2 < this.actionInfo_.size(); i2++) {
                    computeUInt32Size += CodedOutputStream.computeMessageSize(3, this.actionInfo_.get(i2));
                }
                int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof CombineInfo)) {
                    return super.equals(obj);
                }
                CombineInfo combineInfo = (CombineInfo) obj;
                if (hasRepeatNum() != combineInfo.hasRepeatNum()) {
                    return false;
                }
                if ((!hasRepeatNum() || getRepeatNum() == combineInfo.getRepeatNum()) && hasActionNum() == combineInfo.hasActionNum()) {
                    return (!hasActionNum() || getActionNum() == combineInfo.getActionNum()) && getActionInfoList().equals(combineInfo.getActionInfoList()) && this.unknownFields.equals(combineInfo.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasRepeatNum()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getRepeatNum();
                }
                if (hasActionNum()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getActionNum();
                }
                if (getActionInfoCount() > 0) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getActionInfoList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static CombineInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static CombineInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static CombineInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static CombineInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static CombineInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static CombineInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static CombineInfo parseFrom(InputStream inputStream) throws IOException {
                return (CombineInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static CombineInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CombineInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CombineInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (CombineInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static CombineInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CombineInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CombineInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (CombineInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static CombineInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CombineInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(CombineInfo combineInfo) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(combineInfo);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CombineInfoOrBuilder {
                private RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> actionInfoBuilder_;
                private List<ActionInfo> actionInfo_;
                private int actionNum_;
                private int bitField0_;
                private int repeatNum_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return CourseDataForDevice.internal_static_CourseStorageInfo_CombineInfo_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return CourseDataForDevice.internal_static_CourseStorageInfo_CombineInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(CombineInfo.class, Builder.class);
                }

                private Builder() {
                    this.actionInfo_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.actionInfo_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (CombineInfo.alwaysUseFieldBuilders) {
                        getActionInfoFieldBuilder();
                    }
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.repeatNum_ = 0;
                    int i = this.bitField0_;
                    this.actionNum_ = 0;
                    this.bitField0_ = i & (-4);
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.actionInfo_ = Collections.emptyList();
                        this.bitField0_ &= -5;
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return CourseDataForDevice.internal_static_CourseStorageInfo_CombineInfo_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public CombineInfo getDefaultInstanceForType() {
                    return CombineInfo.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public CombineInfo build() {
                    CombineInfo buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public CombineInfo buildPartial() {
                    int i;
                    CombineInfo combineInfo = new CombineInfo(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        combineInfo.repeatNum_ = this.repeatNum_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        combineInfo.actionNum_ = this.actionNum_;
                        i |= 2;
                    }
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        combineInfo.actionInfo_ = repeatedFieldBuilderV3.build();
                    } else {
                        if ((this.bitField0_ & 4) != 0) {
                            this.actionInfo_ = Collections.unmodifiableList(this.actionInfo_);
                            this.bitField0_ &= -5;
                        }
                        combineInfo.actionInfo_ = this.actionInfo_;
                    }
                    combineInfo.bitField0_ = i;
                    onBuilt();
                    return combineInfo;
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
                    if (message instanceof CombineInfo) {
                        return mergeFrom((CombineInfo) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(CombineInfo combineInfo) {
                    if (combineInfo == CombineInfo.getDefaultInstance()) {
                        return this;
                    }
                    if (combineInfo.hasRepeatNum()) {
                        setRepeatNum(combineInfo.getRepeatNum());
                    }
                    if (combineInfo.hasActionNum()) {
                        setActionNum(combineInfo.getActionNum());
                    }
                    if (this.actionInfoBuilder_ == null) {
                        if (!combineInfo.actionInfo_.isEmpty()) {
                            if (this.actionInfo_.isEmpty()) {
                                this.actionInfo_ = combineInfo.actionInfo_;
                                this.bitField0_ &= -5;
                            } else {
                                ensureActionInfoIsMutable();
                                this.actionInfo_.addAll(combineInfo.actionInfo_);
                            }
                            onChanged();
                        }
                    } else if (!combineInfo.actionInfo_.isEmpty()) {
                        if (!this.actionInfoBuilder_.isEmpty()) {
                            this.actionInfoBuilder_.addAllMessages(combineInfo.actionInfo_);
                        } else {
                            this.actionInfoBuilder_.dispose();
                            this.actionInfoBuilder_ = null;
                            this.actionInfo_ = combineInfo.actionInfo_;
                            this.bitField0_ &= -5;
                            this.actionInfoBuilder_ = CombineInfo.alwaysUseFieldBuilders ? getActionInfoFieldBuilder() : null;
                        }
                    }
                    mergeUnknownFields(combineInfo.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (!hasRepeatNum() || !hasActionNum()) {
                        return false;
                    }
                    for (int i = 0; i < getActionInfoCount(); i++) {
                        if (!getActionInfo(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$CombineInfo> r0 = com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$CombineInfo r2 = (com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        if (r2 == 0) goto Ld
                        r1.mergeFrom(r2)
                    Ld:
                        return r1
                    Le:
                        r2 = move-exception
                        goto L1e
                    L10:
                        r2 = move-exception
                        com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Le
                        com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$CombineInfo r3 = (com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfo) r3     // Catch: java.lang.Throwable -> Le
                        java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1c
                        throw r2     // Catch: java.lang.Throwable -> L1c
                    L1c:
                        r2 = move-exception
                        goto L1f
                    L1e:
                        r3 = 0
                    L1f:
                        if (r3 == 0) goto L24
                        r1.mergeFrom(r3)
                    L24:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$CombineInfo$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public boolean hasRepeatNum() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public int getRepeatNum() {
                    return this.repeatNum_;
                }

                public Builder setRepeatNum(int i) {
                    this.bitField0_ |= 1;
                    this.repeatNum_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearRepeatNum() {
                    this.bitField0_ &= -2;
                    this.repeatNum_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public boolean hasActionNum() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public int getActionNum() {
                    return this.actionNum_;
                }

                public Builder setActionNum(int i) {
                    this.bitField0_ |= 2;
                    this.actionNum_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearActionNum() {
                    this.bitField0_ &= -3;
                    this.actionNum_ = 0;
                    onChanged();
                    return this;
                }

                private void ensureActionInfoIsMutable() {
                    if ((this.bitField0_ & 4) == 0) {
                        this.actionInfo_ = new ArrayList(this.actionInfo_);
                        this.bitField0_ |= 4;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public List<ActionInfo> getActionInfoList() {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return Collections.unmodifiableList(this.actionInfo_);
                    }
                    return repeatedFieldBuilderV3.getMessageList();
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public int getActionInfoCount() {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.actionInfo_.size();
                    }
                    return repeatedFieldBuilderV3.getCount();
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public ActionInfo getActionInfo(int i) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.actionInfo_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessage(i);
                }

                public Builder setActionInfo(int i, ActionInfo actionInfo) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        actionInfo.getClass();
                        ensureActionInfoIsMutable();
                        this.actionInfo_.set(i, actionInfo);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, actionInfo);
                    }
                    return this;
                }

                public Builder setActionInfo(int i, ActionInfo.Builder builder) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureActionInfoIsMutable();
                        this.actionInfo_.set(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addActionInfo(ActionInfo actionInfo) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        actionInfo.getClass();
                        ensureActionInfoIsMutable();
                        this.actionInfo_.add(actionInfo);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(actionInfo);
                    }
                    return this;
                }

                public Builder addActionInfo(int i, ActionInfo actionInfo) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        actionInfo.getClass();
                        ensureActionInfoIsMutable();
                        this.actionInfo_.add(i, actionInfo);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, actionInfo);
                    }
                    return this;
                }

                public Builder addActionInfo(ActionInfo.Builder builder) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureActionInfoIsMutable();
                        this.actionInfo_.add(builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addActionInfo(int i, ActionInfo.Builder builder) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureActionInfoIsMutable();
                        this.actionInfo_.add(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addAllActionInfo(Iterable<? extends ActionInfo> iterable) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureActionInfoIsMutable();
                        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.actionInfo_);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder clearActionInfo() {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.actionInfo_ = Collections.emptyList();
                        this.bitField0_ &= -5;
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                public Builder removeActionInfo(int i) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureActionInfoIsMutable();
                        this.actionInfo_.remove(i);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.remove(i);
                    }
                    return this;
                }

                public ActionInfo.Builder getActionInfoBuilder(int i) {
                    return getActionInfoFieldBuilder().getBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public ActionInfoOrBuilder getActionInfoOrBuilder(int i) {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.actionInfo_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.CombineInfoOrBuilder
                public List<? extends ActionInfoOrBuilder> getActionInfoOrBuilderList() {
                    RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> repeatedFieldBuilderV3 = this.actionInfoBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        return repeatedFieldBuilderV3.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.actionInfo_);
                }

                public ActionInfo.Builder addActionInfoBuilder() {
                    return getActionInfoFieldBuilder().addBuilder(ActionInfo.getDefaultInstance());
                }

                public ActionInfo.Builder addActionInfoBuilder(int i) {
                    return getActionInfoFieldBuilder().addBuilder(i, ActionInfo.getDefaultInstance());
                }

                public List<ActionInfo.Builder> getActionInfoBuilderList() {
                    return getActionInfoFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilderV3<ActionInfo, ActionInfo.Builder, ActionInfoOrBuilder> getActionInfoFieldBuilder() {
                    if (this.actionInfoBuilder_ == null) {
                        this.actionInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.actionInfo_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                        this.actionInfo_ = null;
                    }
                    return this.actionInfoBuilder_;
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

            public static CombineInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CombineInfo> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<CombineInfo> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CombineInfo getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasExerciseId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public String getExerciseId() {
            Object obj = this.exerciseId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.exerciseId_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public ByteString getExerciseIdBytes() {
            Object obj = this.exerciseId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.exerciseId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public List<Integer> getExerciseNameList() {
            return this.exerciseName_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getExerciseNameCount() {
            return this.exerciseName_.size();
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getExerciseName(int i) {
            return this.exerciseName_.getInt(i);
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasTotalMeasurementType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getTotalMeasurementType() {
            return this.totalMeasurementType_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasTotalMeasurementValue() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getTotalMeasurementValue() {
            return this.totalMeasurementValue_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasExerciseVersion() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public String getExerciseVersion() {
            Object obj = this.exerciseVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.exerciseVersion_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public ByteString getExerciseVersionBytes() {
            Object obj = this.exerciseVersion_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.exerciseVersion_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasDifficult() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getDifficult() {
            return this.difficult_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasUsingType() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getUsingType() {
            return this.usingType_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasUpdateTime() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getUpdateTime() {
            return this.updateTime_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasDefineType() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getDefineType() {
            return this.defineType_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasCourseType() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getCourseType() {
            return this.courseType_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public boolean hasActionTotalNum() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getActionTotalNum() {
            return this.actionTotalNum_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public List<CombineInfo> getCombineInfoList() {
            return this.combineInfo_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public List<? extends CombineInfoOrBuilder> getCombineInfoOrBuilderList() {
            return this.combineInfo_;
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public int getCombineInfoCount() {
            return this.combineInfo_.size();
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public CombineInfo getCombineInfo(int i) {
            return this.combineInfo_.get(i);
        }

        @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
        public CombineInfoOrBuilder getCombineInfoOrBuilder(int i) {
            return this.combineInfo_.get(i);
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
            if (!hasExerciseId()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasTotalMeasurementType()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasTotalMeasurementValue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasExerciseVersion()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasDifficult()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasUsingType()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasUpdateTime()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasDefineType()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasCourseType()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasActionTotalNum()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getCombineInfoCount(); i++) {
                if (!getCombineInfo(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.exerciseId_);
            }
            for (int i = 0; i < this.exerciseName_.size(); i++) {
                codedOutputStream.writeUInt32(2, this.exerciseName_.getInt(i));
            }
            if ((this.bitField0_ & 2) != 0) {
                codedOutputStream.writeUInt32(3, this.totalMeasurementType_);
            }
            if ((this.bitField0_ & 4) != 0) {
                codedOutputStream.writeUInt32(4, this.totalMeasurementValue_);
            }
            if ((this.bitField0_ & 8) != 0) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.exerciseVersion_);
            }
            if ((this.bitField0_ & 16) != 0) {
                codedOutputStream.writeUInt32(6, this.difficult_);
            }
            if ((this.bitField0_ & 32) != 0) {
                codedOutputStream.writeUInt32(7, this.usingType_);
            }
            if ((this.bitField0_ & 64) != 0) {
                codedOutputStream.writeUInt32(8, this.updateTime_);
            }
            if ((this.bitField0_ & 128) != 0) {
                codedOutputStream.writeUInt32(9, this.defineType_);
            }
            if ((this.bitField0_ & 256) != 0) {
                codedOutputStream.writeUInt32(10, this.courseType_);
            }
            if ((this.bitField0_ & 512) != 0) {
                codedOutputStream.writeUInt32(11, this.actionTotalNum_);
            }
            for (int i2 = 0; i2 < this.combineInfo_.size(); i2++) {
                codedOutputStream.writeMessage(12, this.combineInfo_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.exerciseId_) : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.exerciseName_.size(); i3++) {
                i2 += CodedOutputStream.computeUInt32SizeNoTag(this.exerciseName_.getInt(i3));
            }
            int size = computeStringSize + i2 + getExerciseNameList().size();
            if ((this.bitField0_ & 2) != 0) {
                size += CodedOutputStream.computeUInt32Size(3, this.totalMeasurementType_);
            }
            if ((this.bitField0_ & 4) != 0) {
                size += CodedOutputStream.computeUInt32Size(4, this.totalMeasurementValue_);
            }
            if ((this.bitField0_ & 8) != 0) {
                size += GeneratedMessageV3.computeStringSize(5, this.exerciseVersion_);
            }
            if ((this.bitField0_ & 16) != 0) {
                size += CodedOutputStream.computeUInt32Size(6, this.difficult_);
            }
            if ((this.bitField0_ & 32) != 0) {
                size += CodedOutputStream.computeUInt32Size(7, this.usingType_);
            }
            if ((this.bitField0_ & 64) != 0) {
                size += CodedOutputStream.computeUInt32Size(8, this.updateTime_);
            }
            if ((this.bitField0_ & 128) != 0) {
                size += CodedOutputStream.computeUInt32Size(9, this.defineType_);
            }
            if ((this.bitField0_ & 256) != 0) {
                size += CodedOutputStream.computeUInt32Size(10, this.courseType_);
            }
            if ((this.bitField0_ & 512) != 0) {
                size += CodedOutputStream.computeUInt32Size(11, this.actionTotalNum_);
            }
            for (int i4 = 0; i4 < this.combineInfo_.size(); i4++) {
                size += CodedOutputStream.computeMessageSize(12, this.combineInfo_.get(i4));
            }
            int serializedSize = size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CourseStorageInfo)) {
                return super.equals(obj);
            }
            CourseStorageInfo courseStorageInfo = (CourseStorageInfo) obj;
            if (hasExerciseId() != courseStorageInfo.hasExerciseId()) {
                return false;
            }
            if ((hasExerciseId() && !getExerciseId().equals(courseStorageInfo.getExerciseId())) || !getExerciseNameList().equals(courseStorageInfo.getExerciseNameList()) || hasTotalMeasurementType() != courseStorageInfo.hasTotalMeasurementType()) {
                return false;
            }
            if ((hasTotalMeasurementType() && getTotalMeasurementType() != courseStorageInfo.getTotalMeasurementType()) || hasTotalMeasurementValue() != courseStorageInfo.hasTotalMeasurementValue()) {
                return false;
            }
            if ((hasTotalMeasurementValue() && getTotalMeasurementValue() != courseStorageInfo.getTotalMeasurementValue()) || hasExerciseVersion() != courseStorageInfo.hasExerciseVersion()) {
                return false;
            }
            if ((hasExerciseVersion() && !getExerciseVersion().equals(courseStorageInfo.getExerciseVersion())) || hasDifficult() != courseStorageInfo.hasDifficult()) {
                return false;
            }
            if ((hasDifficult() && getDifficult() != courseStorageInfo.getDifficult()) || hasUsingType() != courseStorageInfo.hasUsingType()) {
                return false;
            }
            if ((hasUsingType() && getUsingType() != courseStorageInfo.getUsingType()) || hasUpdateTime() != courseStorageInfo.hasUpdateTime()) {
                return false;
            }
            if ((hasUpdateTime() && getUpdateTime() != courseStorageInfo.getUpdateTime()) || hasDefineType() != courseStorageInfo.hasDefineType()) {
                return false;
            }
            if ((hasDefineType() && getDefineType() != courseStorageInfo.getDefineType()) || hasCourseType() != courseStorageInfo.hasCourseType()) {
                return false;
            }
            if ((!hasCourseType() || getCourseType() == courseStorageInfo.getCourseType()) && hasActionTotalNum() == courseStorageInfo.hasActionTotalNum()) {
                return (!hasActionTotalNum() || getActionTotalNum() == courseStorageInfo.getActionTotalNum()) && getCombineInfoList().equals(courseStorageInfo.getCombineInfoList()) && this.unknownFields.equals(courseStorageInfo.unknownFields);
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasExerciseId()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getExerciseId().hashCode();
            }
            if (getExerciseNameCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getExerciseNameList().hashCode();
            }
            if (hasTotalMeasurementType()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getTotalMeasurementType();
            }
            if (hasTotalMeasurementValue()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getTotalMeasurementValue();
            }
            if (hasExerciseVersion()) {
                hashCode = (((hashCode * 37) + 5) * 53) + getExerciseVersion().hashCode();
            }
            if (hasDifficult()) {
                hashCode = (((hashCode * 37) + 6) * 53) + getDifficult();
            }
            if (hasUsingType()) {
                hashCode = (((hashCode * 37) + 7) * 53) + getUsingType();
            }
            if (hasUpdateTime()) {
                hashCode = (((hashCode * 37) + 8) * 53) + getUpdateTime();
            }
            if (hasDefineType()) {
                hashCode = (((hashCode * 37) + 9) * 53) + getDefineType();
            }
            if (hasCourseType()) {
                hashCode = (((hashCode * 37) + 10) * 53) + getCourseType();
            }
            if (hasActionTotalNum()) {
                hashCode = (((hashCode * 37) + 11) * 53) + getActionTotalNum();
            }
            if (getCombineInfoCount() > 0) {
                hashCode = (((hashCode * 37) + 12) * 53) + getCombineInfoList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CourseStorageInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CourseStorageInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CourseStorageInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CourseStorageInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CourseStorageInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CourseStorageInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CourseStorageInfo parseFrom(InputStream inputStream) throws IOException {
            return (CourseStorageInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CourseStorageInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CourseStorageInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CourseStorageInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CourseStorageInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CourseStorageInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CourseStorageInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CourseStorageInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CourseStorageInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CourseStorageInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CourseStorageInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CourseStorageInfo courseStorageInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(courseStorageInfo);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CourseStorageInfoOrBuilder {
            private int actionTotalNum_;
            private int bitField0_;
            private RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> combineInfoBuilder_;
            private List<CombineInfo> combineInfo_;
            private int courseType_;
            private int defineType_;
            private int difficult_;
            private Object exerciseId_;
            private Internal.IntList exerciseName_;
            private Object exerciseVersion_;
            private int totalMeasurementType_;
            private int totalMeasurementValue_;
            private int updateTime_;
            private int usingType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return CourseDataForDevice.internal_static_CourseStorageInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return CourseDataForDevice.internal_static_CourseStorageInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(CourseStorageInfo.class, Builder.class);
            }

            private Builder() {
                this.exerciseId_ = "";
                this.exerciseName_ = CourseStorageInfo.emptyIntList();
                this.exerciseVersion_ = "";
                this.combineInfo_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.exerciseId_ = "";
                this.exerciseName_ = CourseStorageInfo.emptyIntList();
                this.exerciseVersion_ = "";
                this.combineInfo_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (CourseStorageInfo.alwaysUseFieldBuilders) {
                    getCombineInfoFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.exerciseId_ = "";
                this.bitField0_ &= -2;
                this.exerciseName_ = CourseStorageInfo.emptyIntList();
                int i = this.bitField0_;
                this.totalMeasurementType_ = 0;
                this.totalMeasurementValue_ = 0;
                this.exerciseVersion_ = "";
                this.difficult_ = 0;
                this.usingType_ = 0;
                this.updateTime_ = 0;
                this.defineType_ = 0;
                this.courseType_ = 0;
                this.actionTotalNum_ = 0;
                this.bitField0_ = i & (-2047);
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.combineInfo_ = Collections.emptyList();
                    this.bitField0_ &= -2049;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return CourseDataForDevice.internal_static_CourseStorageInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CourseStorageInfo getDefaultInstanceForType() {
                return CourseStorageInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CourseStorageInfo build() {
                CourseStorageInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CourseStorageInfo buildPartial() {
                CourseStorageInfo courseStorageInfo = new CourseStorageInfo(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 0 ? 1 : 0;
                courseStorageInfo.exerciseId_ = this.exerciseId_;
                if ((this.bitField0_ & 2) != 0) {
                    this.exerciseName_.makeImmutable();
                    this.bitField0_ &= -3;
                }
                courseStorageInfo.exerciseName_ = this.exerciseName_;
                if ((i & 4) != 0) {
                    courseStorageInfo.totalMeasurementType_ = this.totalMeasurementType_;
                    i2 |= 2;
                }
                if ((i & 8) != 0) {
                    courseStorageInfo.totalMeasurementValue_ = this.totalMeasurementValue_;
                    i2 |= 4;
                }
                if ((i & 16) != 0) {
                    i2 |= 8;
                }
                courseStorageInfo.exerciseVersion_ = this.exerciseVersion_;
                if ((i & 32) != 0) {
                    courseStorageInfo.difficult_ = this.difficult_;
                    i2 |= 16;
                }
                if ((i & 64) != 0) {
                    courseStorageInfo.usingType_ = this.usingType_;
                    i2 |= 32;
                }
                if ((i & 128) != 0) {
                    courseStorageInfo.updateTime_ = this.updateTime_;
                    i2 |= 64;
                }
                if ((i & 256) != 0) {
                    courseStorageInfo.defineType_ = this.defineType_;
                    i2 |= 128;
                }
                if ((i & 512) != 0) {
                    courseStorageInfo.courseType_ = this.courseType_;
                    i2 |= 256;
                }
                if ((i & 1024) != 0) {
                    courseStorageInfo.actionTotalNum_ = this.actionTotalNum_;
                    i2 |= 512;
                }
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    courseStorageInfo.combineInfo_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 2048) != 0) {
                        this.combineInfo_ = Collections.unmodifiableList(this.combineInfo_);
                        this.bitField0_ &= -2049;
                    }
                    courseStorageInfo.combineInfo_ = this.combineInfo_;
                }
                courseStorageInfo.bitField0_ = i2;
                onBuilt();
                return courseStorageInfo;
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
                if (message instanceof CourseStorageInfo) {
                    return mergeFrom((CourseStorageInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CourseStorageInfo courseStorageInfo) {
                if (courseStorageInfo == CourseStorageInfo.getDefaultInstance()) {
                    return this;
                }
                if (courseStorageInfo.hasExerciseId()) {
                    this.bitField0_ |= 1;
                    this.exerciseId_ = courseStorageInfo.exerciseId_;
                    onChanged();
                }
                if (!courseStorageInfo.exerciseName_.isEmpty()) {
                    if (this.exerciseName_.isEmpty()) {
                        this.exerciseName_ = courseStorageInfo.exerciseName_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureExerciseNameIsMutable();
                        this.exerciseName_.addAll(courseStorageInfo.exerciseName_);
                    }
                    onChanged();
                }
                if (courseStorageInfo.hasTotalMeasurementType()) {
                    setTotalMeasurementType(courseStorageInfo.getTotalMeasurementType());
                }
                if (courseStorageInfo.hasTotalMeasurementValue()) {
                    setTotalMeasurementValue(courseStorageInfo.getTotalMeasurementValue());
                }
                if (courseStorageInfo.hasExerciseVersion()) {
                    this.bitField0_ |= 16;
                    this.exerciseVersion_ = courseStorageInfo.exerciseVersion_;
                    onChanged();
                }
                if (courseStorageInfo.hasDifficult()) {
                    setDifficult(courseStorageInfo.getDifficult());
                }
                if (courseStorageInfo.hasUsingType()) {
                    setUsingType(courseStorageInfo.getUsingType());
                }
                if (courseStorageInfo.hasUpdateTime()) {
                    setUpdateTime(courseStorageInfo.getUpdateTime());
                }
                if (courseStorageInfo.hasDefineType()) {
                    setDefineType(courseStorageInfo.getDefineType());
                }
                if (courseStorageInfo.hasCourseType()) {
                    setCourseType(courseStorageInfo.getCourseType());
                }
                if (courseStorageInfo.hasActionTotalNum()) {
                    setActionTotalNum(courseStorageInfo.getActionTotalNum());
                }
                if (this.combineInfoBuilder_ == null) {
                    if (!courseStorageInfo.combineInfo_.isEmpty()) {
                        if (this.combineInfo_.isEmpty()) {
                            this.combineInfo_ = courseStorageInfo.combineInfo_;
                            this.bitField0_ &= -2049;
                        } else {
                            ensureCombineInfoIsMutable();
                            this.combineInfo_.addAll(courseStorageInfo.combineInfo_);
                        }
                        onChanged();
                    }
                } else if (!courseStorageInfo.combineInfo_.isEmpty()) {
                    if (!this.combineInfoBuilder_.isEmpty()) {
                        this.combineInfoBuilder_.addAllMessages(courseStorageInfo.combineInfo_);
                    } else {
                        this.combineInfoBuilder_.dispose();
                        this.combineInfoBuilder_ = null;
                        this.combineInfo_ = courseStorageInfo.combineInfo_;
                        this.bitField0_ &= -2049;
                        this.combineInfoBuilder_ = CourseStorageInfo.alwaysUseFieldBuilders ? getCombineInfoFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(courseStorageInfo.unknownFields);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasExerciseId() || !hasTotalMeasurementType() || !hasTotalMeasurementValue() || !hasExerciseVersion() || !hasDifficult() || !hasUsingType() || !hasUpdateTime() || !hasDefineType() || !hasCourseType() || !hasActionTotalNum()) {
                    return false;
                }
                for (int i = 0; i < getCombineInfoCount(); i++) {
                    if (!getCombineInfo(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo> r0 = com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo r2 = (com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    if (r2 == 0) goto Ld
                    r1.mergeFrom(r2)
                Ld:
                    return r1
                Le:
                    r2 = move-exception
                    goto L1e
                L10:
                    r2 = move-exception
                    com.google.protobuf.MessageLite r3 = r2.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Le
                    com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo r3 = (com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo) r3     // Catch: java.lang.Throwable -> Le
                    java.io.IOException r2 = r2.unwrapIOException()     // Catch: java.lang.Throwable -> L1c
                    throw r2     // Catch: java.lang.Throwable -> L1c
                L1c:
                    r2 = move-exception
                    goto L1f
                L1e:
                    r3 = 0
                L1f:
                    if (r3 == 0) goto L24
                    r1.mergeFrom(r3)
                L24:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.CourseDataForDevice$CourseStorageInfo$Builder");
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasExerciseId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public String getExerciseId() {
                Object obj = this.exerciseId_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.exerciseId_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public ByteString getExerciseIdBytes() {
                Object obj = this.exerciseId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.exerciseId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setExerciseId(String str) {
                str.getClass();
                this.bitField0_ |= 1;
                this.exerciseId_ = str;
                onChanged();
                return this;
            }

            public Builder clearExerciseId() {
                this.bitField0_ &= -2;
                this.exerciseId_ = CourseStorageInfo.getDefaultInstance().getExerciseId();
                onChanged();
                return this;
            }

            public Builder setExerciseIdBytes(ByteString byteString) {
                byteString.getClass();
                this.bitField0_ |= 1;
                this.exerciseId_ = byteString;
                onChanged();
                return this;
            }

            private void ensureExerciseNameIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.exerciseName_ = CourseStorageInfo.mutableCopy(this.exerciseName_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public List<Integer> getExerciseNameList() {
                return (this.bitField0_ & 2) != 0 ? Collections.unmodifiableList(this.exerciseName_) : this.exerciseName_;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getExerciseNameCount() {
                return this.exerciseName_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getExerciseName(int i) {
                return this.exerciseName_.getInt(i);
            }

            public Builder setExerciseName(int i, int i2) {
                ensureExerciseNameIsMutable();
                this.exerciseName_.setInt(i, i2);
                onChanged();
                return this;
            }

            public Builder addExerciseName(int i) {
                ensureExerciseNameIsMutable();
                this.exerciseName_.addInt(i);
                onChanged();
                return this;
            }

            public Builder addAllExerciseName(Iterable<? extends Integer> iterable) {
                ensureExerciseNameIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.exerciseName_);
                onChanged();
                return this;
            }

            public Builder clearExerciseName() {
                this.exerciseName_ = CourseStorageInfo.emptyIntList();
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasTotalMeasurementType() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getTotalMeasurementType() {
                return this.totalMeasurementType_;
            }

            public Builder setTotalMeasurementType(int i) {
                this.bitField0_ |= 4;
                this.totalMeasurementType_ = i;
                onChanged();
                return this;
            }

            public Builder clearTotalMeasurementType() {
                this.bitField0_ &= -5;
                this.totalMeasurementType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasTotalMeasurementValue() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getTotalMeasurementValue() {
                return this.totalMeasurementValue_;
            }

            public Builder setTotalMeasurementValue(int i) {
                this.bitField0_ |= 8;
                this.totalMeasurementValue_ = i;
                onChanged();
                return this;
            }

            public Builder clearTotalMeasurementValue() {
                this.bitField0_ &= -9;
                this.totalMeasurementValue_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasExerciseVersion() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public String getExerciseVersion() {
                Object obj = this.exerciseVersion_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.exerciseVersion_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public ByteString getExerciseVersionBytes() {
                Object obj = this.exerciseVersion_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.exerciseVersion_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setExerciseVersion(String str) {
                str.getClass();
                this.bitField0_ |= 16;
                this.exerciseVersion_ = str;
                onChanged();
                return this;
            }

            public Builder clearExerciseVersion() {
                this.bitField0_ &= -17;
                this.exerciseVersion_ = CourseStorageInfo.getDefaultInstance().getExerciseVersion();
                onChanged();
                return this;
            }

            public Builder setExerciseVersionBytes(ByteString byteString) {
                byteString.getClass();
                this.bitField0_ |= 16;
                this.exerciseVersion_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasDifficult() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getDifficult() {
                return this.difficult_;
            }

            public Builder setDifficult(int i) {
                this.bitField0_ |= 32;
                this.difficult_ = i;
                onChanged();
                return this;
            }

            public Builder clearDifficult() {
                this.bitField0_ &= -33;
                this.difficult_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasUsingType() {
                return (this.bitField0_ & 64) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getUsingType() {
                return this.usingType_;
            }

            public Builder setUsingType(int i) {
                this.bitField0_ |= 64;
                this.usingType_ = i;
                onChanged();
                return this;
            }

            public Builder clearUsingType() {
                this.bitField0_ &= -65;
                this.usingType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasUpdateTime() {
                return (this.bitField0_ & 128) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getUpdateTime() {
                return this.updateTime_;
            }

            public Builder setUpdateTime(int i) {
                this.bitField0_ |= 128;
                this.updateTime_ = i;
                onChanged();
                return this;
            }

            public Builder clearUpdateTime() {
                this.bitField0_ &= -129;
                this.updateTime_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasDefineType() {
                return (this.bitField0_ & 256) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getDefineType() {
                return this.defineType_;
            }

            public Builder setDefineType(int i) {
                this.bitField0_ |= 256;
                this.defineType_ = i;
                onChanged();
                return this;
            }

            public Builder clearDefineType() {
                this.bitField0_ &= -257;
                this.defineType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasCourseType() {
                return (this.bitField0_ & 512) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getCourseType() {
                return this.courseType_;
            }

            public Builder setCourseType(int i) {
                this.bitField0_ |= 512;
                this.courseType_ = i;
                onChanged();
                return this;
            }

            public Builder clearCourseType() {
                this.bitField0_ &= -513;
                this.courseType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public boolean hasActionTotalNum() {
                return (this.bitField0_ & 1024) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getActionTotalNum() {
                return this.actionTotalNum_;
            }

            public Builder setActionTotalNum(int i) {
                this.bitField0_ |= 1024;
                this.actionTotalNum_ = i;
                onChanged();
                return this;
            }

            public Builder clearActionTotalNum() {
                this.bitField0_ &= -1025;
                this.actionTotalNum_ = 0;
                onChanged();
                return this;
            }

            private void ensureCombineInfoIsMutable() {
                if ((this.bitField0_ & 2048) == 0) {
                    this.combineInfo_ = new ArrayList(this.combineInfo_);
                    this.bitField0_ |= 2048;
                }
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public List<CombineInfo> getCombineInfoList() {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.combineInfo_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public int getCombineInfoCount() {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.combineInfo_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public CombineInfo getCombineInfo(int i) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.combineInfo_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setCombineInfo(int i, CombineInfo combineInfo) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    combineInfo.getClass();
                    ensureCombineInfoIsMutable();
                    this.combineInfo_.set(i, combineInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, combineInfo);
                }
                return this;
            }

            public Builder setCombineInfo(int i, CombineInfo.Builder builder) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCombineInfoIsMutable();
                    this.combineInfo_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addCombineInfo(CombineInfo combineInfo) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    combineInfo.getClass();
                    ensureCombineInfoIsMutable();
                    this.combineInfo_.add(combineInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(combineInfo);
                }
                return this;
            }

            public Builder addCombineInfo(int i, CombineInfo combineInfo) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    combineInfo.getClass();
                    ensureCombineInfoIsMutable();
                    this.combineInfo_.add(i, combineInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, combineInfo);
                }
                return this;
            }

            public Builder addCombineInfo(CombineInfo.Builder builder) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCombineInfoIsMutable();
                    this.combineInfo_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addCombineInfo(int i, CombineInfo.Builder builder) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCombineInfoIsMutable();
                    this.combineInfo_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllCombineInfo(Iterable<? extends CombineInfo> iterable) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCombineInfoIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.combineInfo_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearCombineInfo() {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.combineInfo_ = Collections.emptyList();
                    this.bitField0_ &= -2049;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeCombineInfo(int i) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCombineInfoIsMutable();
                    this.combineInfo_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public CombineInfo.Builder getCombineInfoBuilder(int i) {
                return getCombineInfoFieldBuilder().getBuilder(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public CombineInfoOrBuilder getCombineInfoOrBuilder(int i) {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.combineInfo_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.CourseDataForDevice.CourseStorageInfoOrBuilder
            public List<? extends CombineInfoOrBuilder> getCombineInfoOrBuilderList() {
                RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> repeatedFieldBuilderV3 = this.combineInfoBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.combineInfo_);
            }

            public CombineInfo.Builder addCombineInfoBuilder() {
                return getCombineInfoFieldBuilder().addBuilder(CombineInfo.getDefaultInstance());
            }

            public CombineInfo.Builder addCombineInfoBuilder(int i) {
                return getCombineInfoFieldBuilder().addBuilder(i, CombineInfo.getDefaultInstance());
            }

            public List<CombineInfo.Builder> getCombineInfoBuilderList() {
                return getCombineInfoFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<CombineInfo, CombineInfo.Builder, CombineInfoOrBuilder> getCombineInfoFieldBuilder() {
                if (this.combineInfoBuilder_ == null) {
                    this.combineInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.combineInfo_, (this.bitField0_ & 2048) != 0, getParentForChildren(), isClean());
                    this.combineInfo_ = null;
                }
                return this.combineInfoBuilder_;
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

        public static CourseStorageInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CourseStorageInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CourseStorageInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CourseStorageInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_CourseStorageInfo_descriptor = descriptor2;
        internal_static_CourseStorageInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ExerciseId", "ExerciseName", "TotalMeasurementType", "TotalMeasurementValue", "ExerciseVersion", "Difficult", "UsingType", "UpdateTime", "DefineType", "CourseType", "ActionTotalNum", "CombineInfo"});
        Descriptors.Descriptor descriptor3 = descriptor2.getNestedTypes().get(0);
        internal_static_CourseStorageInfo_ActionInfo_descriptor = descriptor3;
        internal_static_CourseStorageInfo_ActionInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"ActionId", "MeasurementType", "MeasurementValue", "InstensityType", "InstensityValueH", "InstensityValueL"});
        Descriptors.Descriptor descriptor4 = descriptor2.getNestedTypes().get(1);
        internal_static_CourseStorageInfo_CombineInfo_descriptor = descriptor4;
        internal_static_CourseStorageInfo_CombineInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"RepeatNum", "ActionNum", "ActionInfo"});
    }
}
