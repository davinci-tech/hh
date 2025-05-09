package com.huawei.health.plugintrack.protobuf.rqdata;

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
public final class UserRunLevelDataForDevice {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016UserRunLevelData.proto\"ü\u0001\n\fRunLevelInfo\u0012\u0014\n\fmodifiedTime\u0018\u0001 \u0002(\r\u0012\u000b\n\u0003km1\u0018\u0002 \u0002(\r\u0012\u000b\n\u0003km3\u0018\u0003 \u0002(\r\u0012\u000b\n\u0003km5\u0018\u0004 \u0002(\r\u0012\f\n\u0004km10\u0018\u0005 \u0002(\r\u0012\u0014\n\fhalfMarathon\u0018\u0006 \u0002(\r\u0012\u0010\n\bmarathon\u0018\u0007 \u0002(\r\u0012\u0017\n\u000fcurrentRunLevel\u0018\b \u0002(\u0002\u0012\u0017\n\u000fRunLevelPercent\u0018\t \u0002(\u0002\u0012\u0011\n\tcondition\u0018\n \u0002(\u0002\u0012\u000f\n\u0007fitness\u0018\u000b \u0002(\u0002\u0012\u000f\n\u0007fatigue\u0018\f \u0002(\u0002\u0012\u0012\n\ntotalPoint\u0018\r \u0002(\u0002BJ\n-com.huawei.health.plugintrack.protobuf.rqdataB\u0019UserRunLevelDataForDevice"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_RunLevelInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_RunLevelInfo_fieldAccessorTable;

    public interface RunLevelInfoOrBuilder extends MessageOrBuilder {
        float getCondition();

        float getCurrentRunLevel();

        float getFatigue();

        float getFitness();

        int getHalfMarathon();

        int getKm1();

        int getKm10();

        int getKm3();

        int getKm5();

        int getMarathon();

        int getModifiedTime();

        float getRunLevelPercent();

        float getTotalPoint();

        boolean hasCondition();

        boolean hasCurrentRunLevel();

        boolean hasFatigue();

        boolean hasFitness();

        boolean hasHalfMarathon();

        boolean hasKm1();

        boolean hasKm10();

        boolean hasKm3();

        boolean hasKm5();

        boolean hasMarathon();

        boolean hasModifiedTime();

        boolean hasRunLevelPercent();

        boolean hasTotalPoint();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private UserRunLevelDataForDevice() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class RunLevelInfo extends GeneratedMessageV3 implements RunLevelInfoOrBuilder {
        public static final int CONDITION_FIELD_NUMBER = 10;
        public static final int CURRENTRUNLEVEL_FIELD_NUMBER = 8;
        public static final int FATIGUE_FIELD_NUMBER = 12;
        public static final int FITNESS_FIELD_NUMBER = 11;
        public static final int HALFMARATHON_FIELD_NUMBER = 6;
        public static final int KM10_FIELD_NUMBER = 5;
        public static final int KM1_FIELD_NUMBER = 2;
        public static final int KM3_FIELD_NUMBER = 3;
        public static final int KM5_FIELD_NUMBER = 4;
        public static final int MARATHON_FIELD_NUMBER = 7;
        public static final int MODIFIEDTIME_FIELD_NUMBER = 1;
        public static final int RUNLEVELPERCENT_FIELD_NUMBER = 9;
        public static final int TOTALPOINT_FIELD_NUMBER = 13;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private float condition_;
        private float currentRunLevel_;
        private float fatigue_;
        private float fitness_;
        private int halfMarathon_;
        private int km10_;
        private int km1_;
        private int km3_;
        private int km5_;
        private int marathon_;
        private byte memoizedIsInitialized;
        private int modifiedTime_;
        private float runLevelPercent_;
        private float totalPoint_;
        private static final RunLevelInfo DEFAULT_INSTANCE = new RunLevelInfo();

        @Deprecated
        public static final Parser<RunLevelInfo> PARSER = new AbstractParser<RunLevelInfo>() { // from class: com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfo.1
            @Override // com.google.protobuf.Parser
            public RunLevelInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RunLevelInfo(codedInputStream, extensionRegistryLite);
            }
        };

        private RunLevelInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private RunLevelInfo() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new RunLevelInfo();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private RunLevelInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.modifiedTime_ = codedInputStream.readUInt32();
                            case 16:
                                this.bitField0_ |= 2;
                                this.km1_ = codedInputStream.readUInt32();
                            case 24:
                                this.bitField0_ |= 4;
                                this.km3_ = codedInputStream.readUInt32();
                            case 32:
                                this.bitField0_ |= 8;
                                this.km5_ = codedInputStream.readUInt32();
                            case 40:
                                this.bitField0_ |= 16;
                                this.km10_ = codedInputStream.readUInt32();
                            case 48:
                                this.bitField0_ |= 32;
                                this.halfMarathon_ = codedInputStream.readUInt32();
                            case 56:
                                this.bitField0_ |= 64;
                                this.marathon_ = codedInputStream.readUInt32();
                            case 69:
                                this.bitField0_ |= 128;
                                this.currentRunLevel_ = codedInputStream.readFloat();
                            case 77:
                                this.bitField0_ |= 256;
                                this.runLevelPercent_ = codedInputStream.readFloat();
                            case 85:
                                this.bitField0_ |= 512;
                                this.condition_ = codedInputStream.readFloat();
                            case 93:
                                this.bitField0_ |= 1024;
                                this.fitness_ = codedInputStream.readFloat();
                            case 101:
                                this.bitField0_ |= 2048;
                                this.fatigue_ = codedInputStream.readFloat();
                            case 109:
                                this.bitField0_ |= 4096;
                                this.totalPoint_ = codedInputStream.readFloat();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return UserRunLevelDataForDevice.internal_static_RunLevelInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return UserRunLevelDataForDevice.internal_static_RunLevelInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RunLevelInfo.class, Builder.class);
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasModifiedTime() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public int getModifiedTime() {
            return this.modifiedTime_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasKm1() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public int getKm1() {
            return this.km1_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasKm3() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public int getKm3() {
            return this.km3_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasKm5() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public int getKm5() {
            return this.km5_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasKm10() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public int getKm10() {
            return this.km10_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasHalfMarathon() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public int getHalfMarathon() {
            return this.halfMarathon_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasMarathon() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public int getMarathon() {
            return this.marathon_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasCurrentRunLevel() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public float getCurrentRunLevel() {
            return this.currentRunLevel_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasRunLevelPercent() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public float getRunLevelPercent() {
            return this.runLevelPercent_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasCondition() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public float getCondition() {
            return this.condition_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasFitness() {
            return (this.bitField0_ & 1024) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public float getFitness() {
            return this.fitness_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasFatigue() {
            return (this.bitField0_ & 2048) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public float getFatigue() {
            return this.fatigue_;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public boolean hasTotalPoint() {
            return (this.bitField0_ & 4096) != 0;
        }

        @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
        public float getTotalPoint() {
            return this.totalPoint_;
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
            if (!hasModifiedTime()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasKm1()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasKm3()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasKm5()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasKm10()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasHalfMarathon()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasMarathon()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasCurrentRunLevel()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasRunLevelPercent()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasCondition()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasFitness()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasFatigue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasTotalPoint()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                codedOutputStream.writeUInt32(1, this.modifiedTime_);
            }
            if ((this.bitField0_ & 2) != 0) {
                codedOutputStream.writeUInt32(2, this.km1_);
            }
            if ((this.bitField0_ & 4) != 0) {
                codedOutputStream.writeUInt32(3, this.km3_);
            }
            if ((this.bitField0_ & 8) != 0) {
                codedOutputStream.writeUInt32(4, this.km5_);
            }
            if ((this.bitField0_ & 16) != 0) {
                codedOutputStream.writeUInt32(5, this.km10_);
            }
            if ((this.bitField0_ & 32) != 0) {
                codedOutputStream.writeUInt32(6, this.halfMarathon_);
            }
            if ((this.bitField0_ & 64) != 0) {
                codedOutputStream.writeUInt32(7, this.marathon_);
            }
            if ((this.bitField0_ & 128) != 0) {
                codedOutputStream.writeFloat(8, this.currentRunLevel_);
            }
            if ((this.bitField0_ & 256) != 0) {
                codedOutputStream.writeFloat(9, this.runLevelPercent_);
            }
            if ((this.bitField0_ & 512) != 0) {
                codedOutputStream.writeFloat(10, this.condition_);
            }
            if ((this.bitField0_ & 1024) != 0) {
                codedOutputStream.writeFloat(11, this.fitness_);
            }
            if ((this.bitField0_ & 2048) != 0) {
                codedOutputStream.writeFloat(12, this.fatigue_);
            }
            if ((this.bitField0_ & 4096) != 0) {
                codedOutputStream.writeFloat(13, this.totalPoint_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeUInt32Size = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeUInt32Size(1, this.modifiedTime_) : 0;
            if ((this.bitField0_ & 2) != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, this.km1_);
            }
            if ((this.bitField0_ & 4) != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(3, this.km3_);
            }
            if ((this.bitField0_ & 8) != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(4, this.km5_);
            }
            if ((this.bitField0_ & 16) != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(5, this.km10_);
            }
            if ((this.bitField0_ & 32) != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(6, this.halfMarathon_);
            }
            if ((this.bitField0_ & 64) != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(7, this.marathon_);
            }
            if ((this.bitField0_ & 128) != 0) {
                computeUInt32Size += CodedOutputStream.computeFloatSize(8, this.currentRunLevel_);
            }
            if ((this.bitField0_ & 256) != 0) {
                computeUInt32Size += CodedOutputStream.computeFloatSize(9, this.runLevelPercent_);
            }
            if ((this.bitField0_ & 512) != 0) {
                computeUInt32Size += CodedOutputStream.computeFloatSize(10, this.condition_);
            }
            if ((this.bitField0_ & 1024) != 0) {
                computeUInt32Size += CodedOutputStream.computeFloatSize(11, this.fitness_);
            }
            if ((this.bitField0_ & 2048) != 0) {
                computeUInt32Size += CodedOutputStream.computeFloatSize(12, this.fatigue_);
            }
            if ((this.bitField0_ & 4096) != 0) {
                computeUInt32Size += CodedOutputStream.computeFloatSize(13, this.totalPoint_);
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
            if (!(obj instanceof RunLevelInfo)) {
                return super.equals(obj);
            }
            RunLevelInfo runLevelInfo = (RunLevelInfo) obj;
            if (hasModifiedTime() != runLevelInfo.hasModifiedTime()) {
                return false;
            }
            if ((hasModifiedTime() && getModifiedTime() != runLevelInfo.getModifiedTime()) || hasKm1() != runLevelInfo.hasKm1()) {
                return false;
            }
            if ((hasKm1() && getKm1() != runLevelInfo.getKm1()) || hasKm3() != runLevelInfo.hasKm3()) {
                return false;
            }
            if ((hasKm3() && getKm3() != runLevelInfo.getKm3()) || hasKm5() != runLevelInfo.hasKm5()) {
                return false;
            }
            if ((hasKm5() && getKm5() != runLevelInfo.getKm5()) || hasKm10() != runLevelInfo.hasKm10()) {
                return false;
            }
            if ((hasKm10() && getKm10() != runLevelInfo.getKm10()) || hasHalfMarathon() != runLevelInfo.hasHalfMarathon()) {
                return false;
            }
            if ((hasHalfMarathon() && getHalfMarathon() != runLevelInfo.getHalfMarathon()) || hasMarathon() != runLevelInfo.hasMarathon()) {
                return false;
            }
            if ((hasMarathon() && getMarathon() != runLevelInfo.getMarathon()) || hasCurrentRunLevel() != runLevelInfo.hasCurrentRunLevel()) {
                return false;
            }
            if ((hasCurrentRunLevel() && Float.floatToIntBits(getCurrentRunLevel()) != Float.floatToIntBits(runLevelInfo.getCurrentRunLevel())) || hasRunLevelPercent() != runLevelInfo.hasRunLevelPercent()) {
                return false;
            }
            if ((hasRunLevelPercent() && Float.floatToIntBits(getRunLevelPercent()) != Float.floatToIntBits(runLevelInfo.getRunLevelPercent())) || hasCondition() != runLevelInfo.hasCondition()) {
                return false;
            }
            if ((hasCondition() && Float.floatToIntBits(getCondition()) != Float.floatToIntBits(runLevelInfo.getCondition())) || hasFitness() != runLevelInfo.hasFitness()) {
                return false;
            }
            if ((hasFitness() && Float.floatToIntBits(getFitness()) != Float.floatToIntBits(runLevelInfo.getFitness())) || hasFatigue() != runLevelInfo.hasFatigue()) {
                return false;
            }
            if ((!hasFatigue() || Float.floatToIntBits(getFatigue()) == Float.floatToIntBits(runLevelInfo.getFatigue())) && hasTotalPoint() == runLevelInfo.hasTotalPoint()) {
                return (!hasTotalPoint() || Float.floatToIntBits(getTotalPoint()) == Float.floatToIntBits(runLevelInfo.getTotalPoint())) && this.unknownFields.equals(runLevelInfo.unknownFields);
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasModifiedTime()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getModifiedTime();
            }
            if (hasKm1()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getKm1();
            }
            if (hasKm3()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getKm3();
            }
            if (hasKm5()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getKm5();
            }
            if (hasKm10()) {
                hashCode = (((hashCode * 37) + 5) * 53) + getKm10();
            }
            if (hasHalfMarathon()) {
                hashCode = (((hashCode * 37) + 6) * 53) + getHalfMarathon();
            }
            if (hasMarathon()) {
                hashCode = (((hashCode * 37) + 7) * 53) + getMarathon();
            }
            if (hasCurrentRunLevel()) {
                hashCode = (((hashCode * 37) + 8) * 53) + Float.floatToIntBits(getCurrentRunLevel());
            }
            if (hasRunLevelPercent()) {
                hashCode = (((hashCode * 37) + 9) * 53) + Float.floatToIntBits(getRunLevelPercent());
            }
            if (hasCondition()) {
                hashCode = (((hashCode * 37) + 10) * 53) + Float.floatToIntBits(getCondition());
            }
            if (hasFitness()) {
                hashCode = (((hashCode * 37) + 11) * 53) + Float.floatToIntBits(getFitness());
            }
            if (hasFatigue()) {
                hashCode = (((hashCode * 37) + 12) * 53) + Float.floatToIntBits(getFatigue());
            }
            if (hasTotalPoint()) {
                hashCode = (((hashCode * 37) + 13) * 53) + Float.floatToIntBits(getTotalPoint());
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static RunLevelInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RunLevelInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RunLevelInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RunLevelInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RunLevelInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RunLevelInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RunLevelInfo parseFrom(InputStream inputStream) throws IOException {
            return (RunLevelInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RunLevelInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RunLevelInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RunLevelInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RunLevelInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RunLevelInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RunLevelInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RunLevelInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RunLevelInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RunLevelInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RunLevelInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RunLevelInfo runLevelInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(runLevelInfo);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RunLevelInfoOrBuilder {
            private int bitField0_;
            private float condition_;
            private float currentRunLevel_;
            private float fatigue_;
            private float fitness_;
            private int halfMarathon_;
            private int km10_;
            private int km1_;
            private int km3_;
            private int km5_;
            private int marathon_;
            private int modifiedTime_;
            private float runLevelPercent_;
            private float totalPoint_;

            public static final Descriptors.Descriptor getDescriptor() {
                return UserRunLevelDataForDevice.internal_static_RunLevelInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return UserRunLevelDataForDevice.internal_static_RunLevelInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RunLevelInfo.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RunLevelInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.modifiedTime_ = 0;
                int i = this.bitField0_;
                this.km1_ = 0;
                this.km3_ = 0;
                this.km5_ = 0;
                this.km10_ = 0;
                this.halfMarathon_ = 0;
                this.marathon_ = 0;
                this.currentRunLevel_ = 0.0f;
                this.runLevelPercent_ = 0.0f;
                this.condition_ = 0.0f;
                this.fitness_ = 0.0f;
                this.fatigue_ = 0.0f;
                this.totalPoint_ = 0.0f;
                this.bitField0_ = i & (-8192);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return UserRunLevelDataForDevice.internal_static_RunLevelInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public RunLevelInfo getDefaultInstanceForType() {
                return RunLevelInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RunLevelInfo build() {
                RunLevelInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RunLevelInfo buildPartial() {
                int i;
                RunLevelInfo runLevelInfo = new RunLevelInfo(this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 0) {
                    runLevelInfo.modifiedTime_ = this.modifiedTime_;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    runLevelInfo.km1_ = this.km1_;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    runLevelInfo.km3_ = this.km3_;
                    i |= 4;
                }
                if ((i2 & 8) != 0) {
                    runLevelInfo.km5_ = this.km5_;
                    i |= 8;
                }
                if ((i2 & 16) != 0) {
                    runLevelInfo.km10_ = this.km10_;
                    i |= 16;
                }
                if ((i2 & 32) != 0) {
                    runLevelInfo.halfMarathon_ = this.halfMarathon_;
                    i |= 32;
                }
                if ((i2 & 64) != 0) {
                    runLevelInfo.marathon_ = this.marathon_;
                    i |= 64;
                }
                if ((i2 & 128) != 0) {
                    runLevelInfo.currentRunLevel_ = this.currentRunLevel_;
                    i |= 128;
                }
                if ((i2 & 256) != 0) {
                    runLevelInfo.runLevelPercent_ = this.runLevelPercent_;
                    i |= 256;
                }
                if ((i2 & 512) != 0) {
                    runLevelInfo.condition_ = this.condition_;
                    i |= 512;
                }
                if ((i2 & 1024) != 0) {
                    runLevelInfo.fitness_ = this.fitness_;
                    i |= 1024;
                }
                if ((i2 & 2048) != 0) {
                    runLevelInfo.fatigue_ = this.fatigue_;
                    i |= 2048;
                }
                if ((i2 & 4096) != 0) {
                    runLevelInfo.totalPoint_ = this.totalPoint_;
                    i |= 4096;
                }
                runLevelInfo.bitField0_ = i;
                onBuilt();
                return runLevelInfo;
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
                if (message instanceof RunLevelInfo) {
                    return mergeFrom((RunLevelInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RunLevelInfo runLevelInfo) {
                if (runLevelInfo == RunLevelInfo.getDefaultInstance()) {
                    return this;
                }
                if (runLevelInfo.hasModifiedTime()) {
                    setModifiedTime(runLevelInfo.getModifiedTime());
                }
                if (runLevelInfo.hasKm1()) {
                    setKm1(runLevelInfo.getKm1());
                }
                if (runLevelInfo.hasKm3()) {
                    setKm3(runLevelInfo.getKm3());
                }
                if (runLevelInfo.hasKm5()) {
                    setKm5(runLevelInfo.getKm5());
                }
                if (runLevelInfo.hasKm10()) {
                    setKm10(runLevelInfo.getKm10());
                }
                if (runLevelInfo.hasHalfMarathon()) {
                    setHalfMarathon(runLevelInfo.getHalfMarathon());
                }
                if (runLevelInfo.hasMarathon()) {
                    setMarathon(runLevelInfo.getMarathon());
                }
                if (runLevelInfo.hasCurrentRunLevel()) {
                    setCurrentRunLevel(runLevelInfo.getCurrentRunLevel());
                }
                if (runLevelInfo.hasRunLevelPercent()) {
                    setRunLevelPercent(runLevelInfo.getRunLevelPercent());
                }
                if (runLevelInfo.hasCondition()) {
                    setCondition(runLevelInfo.getCondition());
                }
                if (runLevelInfo.hasFitness()) {
                    setFitness(runLevelInfo.getFitness());
                }
                if (runLevelInfo.hasFatigue()) {
                    setFatigue(runLevelInfo.getFatigue());
                }
                if (runLevelInfo.hasTotalPoint()) {
                    setTotalPoint(runLevelInfo.getTotalPoint());
                }
                mergeUnknownFields(runLevelInfo.unknownFields);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasModifiedTime() && hasKm1() && hasKm3() && hasKm5() && hasKm10() && hasHalfMarathon() && hasMarathon() && hasCurrentRunLevel() && hasRunLevelPercent() && hasCondition() && hasFitness() && hasFatigue() && hasTotalPoint();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser<com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice$RunLevelInfo> r0 = com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice$RunLevelInfo r2 = (com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                    com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice$RunLevelInfo r3 = (com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfo) r3     // Catch: java.lang.Throwable -> Le
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
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice$RunLevelInfo$Builder");
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasModifiedTime() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public int getModifiedTime() {
                return this.modifiedTime_;
            }

            public Builder setModifiedTime(int i) {
                this.bitField0_ |= 1;
                this.modifiedTime_ = i;
                onChanged();
                return this;
            }

            public Builder clearModifiedTime() {
                this.bitField0_ &= -2;
                this.modifiedTime_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasKm1() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public int getKm1() {
                return this.km1_;
            }

            public Builder setKm1(int i) {
                this.bitField0_ |= 2;
                this.km1_ = i;
                onChanged();
                return this;
            }

            public Builder clearKm1() {
                this.bitField0_ &= -3;
                this.km1_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasKm3() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public int getKm3() {
                return this.km3_;
            }

            public Builder setKm3(int i) {
                this.bitField0_ |= 4;
                this.km3_ = i;
                onChanged();
                return this;
            }

            public Builder clearKm3() {
                this.bitField0_ &= -5;
                this.km3_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasKm5() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public int getKm5() {
                return this.km5_;
            }

            public Builder setKm5(int i) {
                this.bitField0_ |= 8;
                this.km5_ = i;
                onChanged();
                return this;
            }

            public Builder clearKm5() {
                this.bitField0_ &= -9;
                this.km5_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasKm10() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public int getKm10() {
                return this.km10_;
            }

            public Builder setKm10(int i) {
                this.bitField0_ |= 16;
                this.km10_ = i;
                onChanged();
                return this;
            }

            public Builder clearKm10() {
                this.bitField0_ &= -17;
                this.km10_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasHalfMarathon() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public int getHalfMarathon() {
                return this.halfMarathon_;
            }

            public Builder setHalfMarathon(int i) {
                this.bitField0_ |= 32;
                this.halfMarathon_ = i;
                onChanged();
                return this;
            }

            public Builder clearHalfMarathon() {
                this.bitField0_ &= -33;
                this.halfMarathon_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasMarathon() {
                return (this.bitField0_ & 64) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public int getMarathon() {
                return this.marathon_;
            }

            public Builder setMarathon(int i) {
                this.bitField0_ |= 64;
                this.marathon_ = i;
                onChanged();
                return this;
            }

            public Builder clearMarathon() {
                this.bitField0_ &= -65;
                this.marathon_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasCurrentRunLevel() {
                return (this.bitField0_ & 128) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public float getCurrentRunLevel() {
                return this.currentRunLevel_;
            }

            public Builder setCurrentRunLevel(float f) {
                this.bitField0_ |= 128;
                this.currentRunLevel_ = f;
                onChanged();
                return this;
            }

            public Builder clearCurrentRunLevel() {
                this.bitField0_ &= -129;
                this.currentRunLevel_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasRunLevelPercent() {
                return (this.bitField0_ & 256) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public float getRunLevelPercent() {
                return this.runLevelPercent_;
            }

            public Builder setRunLevelPercent(float f) {
                this.bitField0_ |= 256;
                this.runLevelPercent_ = f;
                onChanged();
                return this;
            }

            public Builder clearRunLevelPercent() {
                this.bitField0_ &= -257;
                this.runLevelPercent_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasCondition() {
                return (this.bitField0_ & 512) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public float getCondition() {
                return this.condition_;
            }

            public Builder setCondition(float f) {
                this.bitField0_ |= 512;
                this.condition_ = f;
                onChanged();
                return this;
            }

            public Builder clearCondition() {
                this.bitField0_ &= -513;
                this.condition_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasFitness() {
                return (this.bitField0_ & 1024) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public float getFitness() {
                return this.fitness_;
            }

            public Builder setFitness(float f) {
                this.bitField0_ |= 1024;
                this.fitness_ = f;
                onChanged();
                return this;
            }

            public Builder clearFitness() {
                this.bitField0_ &= -1025;
                this.fitness_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasFatigue() {
                return (this.bitField0_ & 2048) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public float getFatigue() {
                return this.fatigue_;
            }

            public Builder setFatigue(float f) {
                this.bitField0_ |= 2048;
                this.fatigue_ = f;
                onChanged();
                return this;
            }

            public Builder clearFatigue() {
                this.bitField0_ &= -2049;
                this.fatigue_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public boolean hasTotalPoint() {
                return (this.bitField0_ & 4096) != 0;
            }

            @Override // com.huawei.health.plugintrack.protobuf.rqdata.UserRunLevelDataForDevice.RunLevelInfoOrBuilder
            public float getTotalPoint() {
                return this.totalPoint_;
            }

            public Builder setTotalPoint(float f) {
                this.bitField0_ |= 4096;
                this.totalPoint_ = f;
                onChanged();
                return this;
            }

            public Builder clearTotalPoint() {
                this.bitField0_ &= -4097;
                this.totalPoint_ = 0.0f;
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

        public static RunLevelInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RunLevelInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<RunLevelInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RunLevelInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_RunLevelInfo_descriptor = descriptor2;
        internal_static_RunLevelInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ModifiedTime", "Km1", "Km3", "Km5", "Km10", "HalfMarathon", "Marathon", "CurrentRunLevel", "RunLevelPercent", "Condition", "Fitness", "Fatigue", "TotalPoint"});
    }
}
