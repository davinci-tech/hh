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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class PlanDataForDevice {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\rrunPlan.proto\"ô\u0005\n\bPlanInfo\u0012.\n\rplanBasicInfo\u0018\u0001 \u0002(\u000b2\u0017.PlanInfo.PlanBasicInfo\u00126\n\u0011weekPlanBasicInfo\u0018\u0002 \u0003(\u000b2\u001b.PlanInfo.WeekPlanBasicInfo\u00124\n\u0010dayPlanBasicInfo\u0018\u0003 \u0003(\u000b2\u001a.PlanInfo.DayPlanBasicInfo\u001a¹\u0001\n\rPlanBasicInfo\u0012\u000e\n\u0006planId\u0018\u0001 \u0002(\t\u0012\u0010\n\bplanName\u0018\u0002 \u0003(\r\u0012\u0014\n\flanguageType\u0018\u0003 \u0002(\t\u0012\u0016\n\u000epunchTimeStamp\u0018\u0004 \u0002(\r\u0012\u0015\n\rplanTotalDays\u0018\u0005 \u0002(\r\u0012\u0016\n\u000eplanTotalWeeks\u0018\u0006 \u0002(\r\u0012\u0015\n\rplanStartTime\u0018\u0007 \u0002(\r\u0012\u0012\n\nzoneOffset\u0018\b \u0002(\u0005\u001a\u009e\u0001\n\u0011WeekPlanBasicInfo\u0012\u0014\n\fweekPlanName\u0018\u0001 \u0003(\r\u0012\u0015\n\rexerciseTimes\u0018\u0002 \u0002(\r\u0012\u0017\n\u000fcurrentWeekDays\u0018\u0003 \u0002(\r\u0012\u000f\n\u0007weekNum\u0018\u0004 \u0002(\r\u0012\u0019\n\u0011weekPlanStartTime\u0018\u0005 \u0002(\r\u0012\u0017\n\u000fweekPlanEndTime\u0018\u0006 \u0002(\r\u001at\n\rCourseOutLine\u0012\u0010\n\bcourseId\u0018\u0001 \u0002(\t\u0012\u0012\n\ncourseName\u0018\u0002 \u0003(\r\u0012\u0012\n\nupdateTime\u0018\u0003 \u0002(\r\u0012\u0012\n\ncourseType\u0018\u0004 \u0002(\r\u0012\u0015\n\risCoursePunch\u0018\u0005 \u0002(\r\u001aw\n\u0010DayPlanBasicInfo\u0012\f\n\u0004time\u0018\u0001 \u0002(\r\u0012\u0011\n\tcourseNum\u0018\u0002 \u0002(\r\u0012\u0012\n\nisDayPunch\u0018\u0003 \u0002(\r\u0012.\n\rdayPlanCourse\u0018\u0004 \u0003(\u000b2\u0017.PlanInfo.CourseOutLine\"e\n\u0012PlanShakeHandsInfo\u0012\u000e\n\u0006planId\u0018\u0001 \u0002(\t\u0012\u0010\n\blanguage\u0018\u0002 \u0002(\t\u0012\u0015\n\rweekStartTime\u0018\u0003 \u0002(\r\u0012\u0016\n\u000epunchTimeStamp\u0018\u0004 \u0002(\rB:\n%com.huawei.health.suggestion.protobufB\u0011PlanDataForDevice"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_PlanInfo_CourseOutLine_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_PlanInfo_CourseOutLine_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_PlanInfo_DayPlanBasicInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_PlanInfo_DayPlanBasicInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_PlanInfo_PlanBasicInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_PlanInfo_PlanBasicInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_PlanInfo_WeekPlanBasicInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_PlanInfo_WeekPlanBasicInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_PlanInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_PlanInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_PlanShakeHandsInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_PlanShakeHandsInfo_fieldAccessorTable;

    public interface PlanInfoOrBuilder extends MessageOrBuilder {
        PlanInfo.DayPlanBasicInfo getDayPlanBasicInfo(int i);

        int getDayPlanBasicInfoCount();

        List<PlanInfo.DayPlanBasicInfo> getDayPlanBasicInfoList();

        PlanInfo.DayPlanBasicInfoOrBuilder getDayPlanBasicInfoOrBuilder(int i);

        List<? extends PlanInfo.DayPlanBasicInfoOrBuilder> getDayPlanBasicInfoOrBuilderList();

        PlanInfo.PlanBasicInfo getPlanBasicInfo();

        PlanInfo.PlanBasicInfoOrBuilder getPlanBasicInfoOrBuilder();

        PlanInfo.WeekPlanBasicInfo getWeekPlanBasicInfo(int i);

        int getWeekPlanBasicInfoCount();

        List<PlanInfo.WeekPlanBasicInfo> getWeekPlanBasicInfoList();

        PlanInfo.WeekPlanBasicInfoOrBuilder getWeekPlanBasicInfoOrBuilder(int i);

        List<? extends PlanInfo.WeekPlanBasicInfoOrBuilder> getWeekPlanBasicInfoOrBuilderList();

        boolean hasPlanBasicInfo();
    }

    public interface PlanShakeHandsInfoOrBuilder extends MessageOrBuilder {
        String getLanguage();

        ByteString getLanguageBytes();

        String getPlanId();

        ByteString getPlanIdBytes();

        int getPunchTimeStamp();

        int getWeekStartTime();

        boolean hasLanguage();

        boolean hasPlanId();

        boolean hasPunchTimeStamp();

        boolean hasWeekStartTime();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private PlanDataForDevice() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class PlanInfo extends GeneratedMessageV3 implements PlanInfoOrBuilder {
        public static final int DAYPLANBASICINFO_FIELD_NUMBER = 3;
        private static final PlanInfo DEFAULT_INSTANCE = new PlanInfo();

        @Deprecated
        public static final Parser<PlanInfo> PARSER = new AbstractParser<PlanInfo>() { // from class: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.1
            @Override // com.google.protobuf.Parser
            public PlanInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PlanInfo(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PLANBASICINFO_FIELD_NUMBER = 1;
        public static final int WEEKPLANBASICINFO_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private List<DayPlanBasicInfo> dayPlanBasicInfo_;
        private byte memoizedIsInitialized;
        private PlanBasicInfo planBasicInfo_;
        private List<WeekPlanBasicInfo> weekPlanBasicInfo_;

        public interface CourseOutLineOrBuilder extends MessageOrBuilder {
            String getCourseId();

            ByteString getCourseIdBytes();

            int getCourseName(int i);

            int getCourseNameCount();

            List<Integer> getCourseNameList();

            int getCourseType();

            int getIsCoursePunch();

            int getUpdateTime();

            boolean hasCourseId();

            boolean hasCourseType();

            boolean hasIsCoursePunch();

            boolean hasUpdateTime();
        }

        public interface DayPlanBasicInfoOrBuilder extends MessageOrBuilder {
            int getCourseNum();

            CourseOutLine getDayPlanCourse(int i);

            int getDayPlanCourseCount();

            List<CourseOutLine> getDayPlanCourseList();

            CourseOutLineOrBuilder getDayPlanCourseOrBuilder(int i);

            List<? extends CourseOutLineOrBuilder> getDayPlanCourseOrBuilderList();

            int getIsDayPunch();

            int getTime();

            boolean hasCourseNum();

            boolean hasIsDayPunch();

            boolean hasTime();
        }

        public interface PlanBasicInfoOrBuilder extends MessageOrBuilder {
            String getLanguageType();

            ByteString getLanguageTypeBytes();

            String getPlanId();

            ByteString getPlanIdBytes();

            int getPlanName(int i);

            int getPlanNameCount();

            List<Integer> getPlanNameList();

            int getPlanStartTime();

            int getPlanTotalDays();

            int getPlanTotalWeeks();

            int getPunchTimeStamp();

            int getZoneOffset();

            boolean hasLanguageType();

            boolean hasPlanId();

            boolean hasPlanStartTime();

            boolean hasPlanTotalDays();

            boolean hasPlanTotalWeeks();

            boolean hasPunchTimeStamp();

            boolean hasZoneOffset();
        }

        public interface WeekPlanBasicInfoOrBuilder extends MessageOrBuilder {
            int getCurrentWeekDays();

            int getExerciseTimes();

            int getWeekNum();

            int getWeekPlanEndTime();

            int getWeekPlanName(int i);

            int getWeekPlanNameCount();

            List<Integer> getWeekPlanNameList();

            int getWeekPlanStartTime();

            boolean hasCurrentWeekDays();

            boolean hasExerciseTimes();

            boolean hasWeekNum();

            boolean hasWeekPlanEndTime();

            boolean hasWeekPlanStartTime();
        }

        private PlanInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private PlanInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.weekPlanBasicInfo_ = Collections.emptyList();
            this.dayPlanBasicInfo_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new PlanInfo();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PlanInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                PlanBasicInfo.Builder builder = (this.bitField0_ & 1) != 0 ? this.planBasicInfo_.toBuilder() : null;
                                PlanBasicInfo planBasicInfo = (PlanBasicInfo) codedInputStream.readMessage(PlanBasicInfo.PARSER, extensionRegistryLite);
                                this.planBasicInfo_ = planBasicInfo;
                                if (builder != null) {
                                    builder.mergeFrom(planBasicInfo);
                                    this.planBasicInfo_ = builder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (readTag == 18) {
                                if ((i & 2) == 0) {
                                    this.weekPlanBasicInfo_ = new ArrayList();
                                    i |= 2;
                                }
                                this.weekPlanBasicInfo_.add((WeekPlanBasicInfo) codedInputStream.readMessage(WeekPlanBasicInfo.PARSER, extensionRegistryLite));
                            } else if (readTag == 26) {
                                if ((i & 4) == 0) {
                                    this.dayPlanBasicInfo_ = new ArrayList();
                                    i |= 4;
                                }
                                this.dayPlanBasicInfo_.add((DayPlanBasicInfo) codedInputStream.readMessage(DayPlanBasicInfo.PARSER, extensionRegistryLite));
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
                    if ((i & 2) != 0) {
                        this.weekPlanBasicInfo_ = Collections.unmodifiableList(this.weekPlanBasicInfo_);
                    }
                    if ((i & 4) != 0) {
                        this.dayPlanBasicInfo_ = Collections.unmodifiableList(this.dayPlanBasicInfo_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return PlanDataForDevice.internal_static_PlanInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PlanDataForDevice.internal_static_PlanInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanInfo.class, Builder.class);
        }

        public static final class PlanBasicInfo extends GeneratedMessageV3 implements PlanBasicInfoOrBuilder {
            public static final int LANGUAGETYPE_FIELD_NUMBER = 3;
            public static final int PLANID_FIELD_NUMBER = 1;
            public static final int PLANNAME_FIELD_NUMBER = 2;
            public static final int PLANSTARTTIME_FIELD_NUMBER = 7;
            public static final int PLANTOTALDAYS_FIELD_NUMBER = 5;
            public static final int PLANTOTALWEEKS_FIELD_NUMBER = 6;
            public static final int PUNCHTIMESTAMP_FIELD_NUMBER = 4;
            public static final int ZONEOFFSET_FIELD_NUMBER = 8;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private volatile Object languageType_;
            private byte memoizedIsInitialized;
            private volatile Object planId_;
            private Internal.IntList planName_;
            private int planStartTime_;
            private int planTotalDays_;
            private int planTotalWeeks_;
            private int punchTimeStamp_;
            private int zoneOffset_;
            private static final PlanBasicInfo DEFAULT_INSTANCE = new PlanBasicInfo();

            @Deprecated
            public static final Parser<PlanBasicInfo> PARSER = new AbstractParser<PlanBasicInfo>() { // from class: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfo.1
                @Override // com.google.protobuf.Parser
                public PlanBasicInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new PlanBasicInfo(codedInputStream, extensionRegistryLite);
                }
            };

            private PlanBasicInfo(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private PlanBasicInfo() {
                this.memoizedIsInitialized = (byte) -1;
                this.planId_ = "";
                this.planName_ = emptyIntList();
                this.languageType_ = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new PlanBasicInfo();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private PlanBasicInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                char c = 0;
                while (!z) {
                    try {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 10) {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.planId_ = readBytes;
                                    } else if (readTag == 16) {
                                        if ((c & 2) == 0) {
                                            this.planName_ = newIntList();
                                            c = 2;
                                        }
                                        this.planName_.addInt(codedInputStream.readUInt32());
                                    } else if (readTag == 18) {
                                        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                        if ((c & 2) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                            this.planName_ = newIntList();
                                            c = 2;
                                        }
                                        while (codedInputStream.getBytesUntilLimit() > 0) {
                                            this.planName_.addInt(codedInputStream.readUInt32());
                                        }
                                        codedInputStream.popLimit(pushLimit);
                                    } else if (readTag == 26) {
                                        ByteString readBytes2 = codedInputStream.readBytes();
                                        this.bitField0_ |= 2;
                                        this.languageType_ = readBytes2;
                                    } else if (readTag == 32) {
                                        this.bitField0_ |= 4;
                                        this.punchTimeStamp_ = codedInputStream.readUInt32();
                                    } else if (readTag == 40) {
                                        this.bitField0_ |= 8;
                                        this.planTotalDays_ = codedInputStream.readUInt32();
                                    } else if (readTag == 48) {
                                        this.bitField0_ |= 16;
                                        this.planTotalWeeks_ = codedInputStream.readUInt32();
                                    } else if (readTag == 56) {
                                        this.bitField0_ |= 32;
                                        this.planStartTime_ = codedInputStream.readUInt32();
                                    } else if (readTag == 64) {
                                        this.bitField0_ |= 64;
                                        this.zoneOffset_ = codedInputStream.readInt32();
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
                        if ((c & 2) != 0) {
                            this.planName_.makeImmutable();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PlanDataForDevice.internal_static_PlanInfo_PlanBasicInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PlanDataForDevice.internal_static_PlanInfo_PlanBasicInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanBasicInfo.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public boolean hasPlanId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public String getPlanId() {
                Object obj = this.planId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.planId_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public ByteString getPlanIdBytes() {
                Object obj = this.planId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.planId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public List<Integer> getPlanNameList() {
                return this.planName_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public int getPlanNameCount() {
                return this.planName_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public int getPlanName(int i) {
                return this.planName_.getInt(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public boolean hasLanguageType() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public String getLanguageType() {
                Object obj = this.languageType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.languageType_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public ByteString getLanguageTypeBytes() {
                Object obj = this.languageType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.languageType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public boolean hasPunchTimeStamp() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public int getPunchTimeStamp() {
                return this.punchTimeStamp_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public boolean hasPlanTotalDays() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public int getPlanTotalDays() {
                return this.planTotalDays_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public boolean hasPlanTotalWeeks() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public int getPlanTotalWeeks() {
                return this.planTotalWeeks_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public boolean hasPlanStartTime() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public int getPlanStartTime() {
                return this.planStartTime_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public boolean hasZoneOffset() {
                return (this.bitField0_ & 64) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
            public int getZoneOffset() {
                return this.zoneOffset_;
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
                if (!hasPlanId()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasLanguageType()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasPunchTimeStamp()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasPlanTotalDays()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasPlanTotalWeeks()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasPlanStartTime()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasZoneOffset()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.planId_);
                }
                for (int i = 0; i < this.planName_.size(); i++) {
                    codedOutputStream.writeUInt32(2, this.planName_.getInt(i));
                }
                if ((this.bitField0_ & 2) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageType_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeUInt32(4, this.punchTimeStamp_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeUInt32(5, this.planTotalDays_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    codedOutputStream.writeUInt32(6, this.planTotalWeeks_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    codedOutputStream.writeUInt32(7, this.planStartTime_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    codedOutputStream.writeInt32(8, this.zoneOffset_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.planId_) : 0;
                int i2 = 0;
                for (int i3 = 0; i3 < this.planName_.size(); i3++) {
                    i2 += CodedOutputStream.computeUInt32SizeNoTag(this.planName_.getInt(i3));
                }
                int size = computeStringSize + i2 + getPlanNameList().size();
                if ((this.bitField0_ & 2) != 0) {
                    size += GeneratedMessageV3.computeStringSize(3, this.languageType_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    size += CodedOutputStream.computeUInt32Size(4, this.punchTimeStamp_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    size += CodedOutputStream.computeUInt32Size(5, this.planTotalDays_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    size += CodedOutputStream.computeUInt32Size(6, this.planTotalWeeks_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    size += CodedOutputStream.computeUInt32Size(7, this.planStartTime_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    size += CodedOutputStream.computeInt32Size(8, this.zoneOffset_);
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
                if (!(obj instanceof PlanBasicInfo)) {
                    return super.equals(obj);
                }
                PlanBasicInfo planBasicInfo = (PlanBasicInfo) obj;
                if (hasPlanId() != planBasicInfo.hasPlanId()) {
                    return false;
                }
                if ((hasPlanId() && !getPlanId().equals(planBasicInfo.getPlanId())) || !getPlanNameList().equals(planBasicInfo.getPlanNameList()) || hasLanguageType() != planBasicInfo.hasLanguageType()) {
                    return false;
                }
                if ((hasLanguageType() && !getLanguageType().equals(planBasicInfo.getLanguageType())) || hasPunchTimeStamp() != planBasicInfo.hasPunchTimeStamp()) {
                    return false;
                }
                if ((hasPunchTimeStamp() && getPunchTimeStamp() != planBasicInfo.getPunchTimeStamp()) || hasPlanTotalDays() != planBasicInfo.hasPlanTotalDays()) {
                    return false;
                }
                if ((hasPlanTotalDays() && getPlanTotalDays() != planBasicInfo.getPlanTotalDays()) || hasPlanTotalWeeks() != planBasicInfo.hasPlanTotalWeeks()) {
                    return false;
                }
                if ((hasPlanTotalWeeks() && getPlanTotalWeeks() != planBasicInfo.getPlanTotalWeeks()) || hasPlanStartTime() != planBasicInfo.hasPlanStartTime()) {
                    return false;
                }
                if ((!hasPlanStartTime() || getPlanStartTime() == planBasicInfo.getPlanStartTime()) && hasZoneOffset() == planBasicInfo.hasZoneOffset()) {
                    return (!hasZoneOffset() || getZoneOffset() == planBasicInfo.getZoneOffset()) && this.unknownFields.equals(planBasicInfo.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasPlanId()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getPlanId().hashCode();
                }
                if (getPlanNameCount() > 0) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getPlanNameList().hashCode();
                }
                if (hasLanguageType()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getLanguageType().hashCode();
                }
                if (hasPunchTimeStamp()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getPunchTimeStamp();
                }
                if (hasPlanTotalDays()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getPlanTotalDays();
                }
                if (hasPlanTotalWeeks()) {
                    hashCode = (((hashCode * 37) + 6) * 53) + getPlanTotalWeeks();
                }
                if (hasPlanStartTime()) {
                    hashCode = (((hashCode * 37) + 7) * 53) + getPlanStartTime();
                }
                if (hasZoneOffset()) {
                    hashCode = (((hashCode * 37) + 8) * 53) + getZoneOffset();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static PlanBasicInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static PlanBasicInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static PlanBasicInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static PlanBasicInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static PlanBasicInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static PlanBasicInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static PlanBasicInfo parseFrom(InputStream inputStream) throws IOException {
                return (PlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static PlanBasicInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (PlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static PlanBasicInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (PlanBasicInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static PlanBasicInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (PlanBasicInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static PlanBasicInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (PlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static PlanBasicInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (PlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(PlanBasicInfo planBasicInfo) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(planBasicInfo);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PlanBasicInfoOrBuilder {
                private int bitField0_;
                private Object languageType_;
                private Object planId_;
                private Internal.IntList planName_;
                private int planStartTime_;
                private int planTotalDays_;
                private int planTotalWeeks_;
                private int punchTimeStamp_;
                private int zoneOffset_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return PlanDataForDevice.internal_static_PlanInfo_PlanBasicInfo_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return PlanDataForDevice.internal_static_PlanInfo_PlanBasicInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanBasicInfo.class, Builder.class);
                }

                private Builder() {
                    this.planId_ = "";
                    this.planName_ = PlanBasicInfo.emptyIntList();
                    this.languageType_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.planId_ = "";
                    this.planName_ = PlanBasicInfo.emptyIntList();
                    this.languageType_ = "";
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = PlanBasicInfo.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.planId_ = "";
                    this.bitField0_ &= -2;
                    this.planName_ = PlanBasicInfo.emptyIntList();
                    int i = this.bitField0_;
                    this.languageType_ = "";
                    this.punchTimeStamp_ = 0;
                    this.planTotalDays_ = 0;
                    this.planTotalWeeks_ = 0;
                    this.planStartTime_ = 0;
                    this.zoneOffset_ = 0;
                    this.bitField0_ = i & (-255);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return PlanDataForDevice.internal_static_PlanInfo_PlanBasicInfo_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public PlanBasicInfo getDefaultInstanceForType() {
                    return PlanBasicInfo.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public PlanBasicInfo build() {
                    PlanBasicInfo buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public PlanBasicInfo buildPartial() {
                    PlanBasicInfo planBasicInfo = new PlanBasicInfo(this);
                    int i = this.bitField0_;
                    int i2 = (i & 1) != 0 ? 1 : 0;
                    planBasicInfo.planId_ = this.planId_;
                    if ((this.bitField0_ & 2) != 0) {
                        this.planName_.makeImmutable();
                        this.bitField0_ &= -3;
                    }
                    planBasicInfo.planName_ = this.planName_;
                    if ((i & 4) != 0) {
                        i2 |= 2;
                    }
                    planBasicInfo.languageType_ = this.languageType_;
                    if ((i & 8) != 0) {
                        planBasicInfo.punchTimeStamp_ = this.punchTimeStamp_;
                        i2 |= 4;
                    }
                    if ((i & 16) != 0) {
                        planBasicInfo.planTotalDays_ = this.planTotalDays_;
                        i2 |= 8;
                    }
                    if ((i & 32) != 0) {
                        planBasicInfo.planTotalWeeks_ = this.planTotalWeeks_;
                        i2 |= 16;
                    }
                    if ((i & 64) != 0) {
                        planBasicInfo.planStartTime_ = this.planStartTime_;
                        i2 |= 32;
                    }
                    if ((i & 128) != 0) {
                        planBasicInfo.zoneOffset_ = this.zoneOffset_;
                        i2 |= 64;
                    }
                    planBasicInfo.bitField0_ = i2;
                    onBuilt();
                    return planBasicInfo;
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
                    if (message instanceof PlanBasicInfo) {
                        return mergeFrom((PlanBasicInfo) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(PlanBasicInfo planBasicInfo) {
                    if (planBasicInfo == PlanBasicInfo.getDefaultInstance()) {
                        return this;
                    }
                    if (planBasicInfo.hasPlanId()) {
                        this.bitField0_ |= 1;
                        this.planId_ = planBasicInfo.planId_;
                        onChanged();
                    }
                    if (!planBasicInfo.planName_.isEmpty()) {
                        if (this.planName_.isEmpty()) {
                            this.planName_ = planBasicInfo.planName_;
                            this.bitField0_ &= -3;
                        } else {
                            ensurePlanNameIsMutable();
                            this.planName_.addAll(planBasicInfo.planName_);
                        }
                        onChanged();
                    }
                    if (planBasicInfo.hasLanguageType()) {
                        this.bitField0_ |= 4;
                        this.languageType_ = planBasicInfo.languageType_;
                        onChanged();
                    }
                    if (planBasicInfo.hasPunchTimeStamp()) {
                        setPunchTimeStamp(planBasicInfo.getPunchTimeStamp());
                    }
                    if (planBasicInfo.hasPlanTotalDays()) {
                        setPlanTotalDays(planBasicInfo.getPlanTotalDays());
                    }
                    if (planBasicInfo.hasPlanTotalWeeks()) {
                        setPlanTotalWeeks(planBasicInfo.getPlanTotalWeeks());
                    }
                    if (planBasicInfo.hasPlanStartTime()) {
                        setPlanStartTime(planBasicInfo.getPlanStartTime());
                    }
                    if (planBasicInfo.hasZoneOffset()) {
                        setZoneOffset(planBasicInfo.getZoneOffset());
                    }
                    mergeUnknownFields(planBasicInfo.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return hasPlanId() && hasLanguageType() && hasPunchTimeStamp() && hasPlanTotalDays() && hasPlanTotalWeeks() && hasPlanStartTime() && hasZoneOffset();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$PlanBasicInfo> r0 = com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$PlanBasicInfo r2 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$PlanBasicInfo r3 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfo) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$PlanBasicInfo$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public boolean hasPlanId() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public String getPlanId() {
                    Object obj = this.planId_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.planId_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public ByteString getPlanIdBytes() {
                    Object obj = this.planId_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.planId_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setPlanId(String str) {
                    str.getClass();
                    this.bitField0_ |= 1;
                    this.planId_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearPlanId() {
                    this.bitField0_ &= -2;
                    this.planId_ = PlanBasicInfo.getDefaultInstance().getPlanId();
                    onChanged();
                    return this;
                }

                public Builder setPlanIdBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 1;
                    this.planId_ = byteString;
                    onChanged();
                    return this;
                }

                private void ensurePlanNameIsMutable() {
                    if ((this.bitField0_ & 2) == 0) {
                        this.planName_ = PlanBasicInfo.mutableCopy(this.planName_);
                        this.bitField0_ |= 2;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public List<Integer> getPlanNameList() {
                    return (this.bitField0_ & 2) != 0 ? Collections.unmodifiableList(this.planName_) : this.planName_;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public int getPlanNameCount() {
                    return this.planName_.size();
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public int getPlanName(int i) {
                    return this.planName_.getInt(i);
                }

                public Builder setPlanName(int i, int i2) {
                    ensurePlanNameIsMutable();
                    this.planName_.setInt(i, i2);
                    onChanged();
                    return this;
                }

                public Builder addPlanName(int i) {
                    ensurePlanNameIsMutable();
                    this.planName_.addInt(i);
                    onChanged();
                    return this;
                }

                public Builder addAllPlanName(Iterable<? extends Integer> iterable) {
                    ensurePlanNameIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.planName_);
                    onChanged();
                    return this;
                }

                public Builder clearPlanName() {
                    this.planName_ = PlanBasicInfo.emptyIntList();
                    this.bitField0_ &= -3;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public boolean hasLanguageType() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public String getLanguageType() {
                    Object obj = this.languageType_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.languageType_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public ByteString getLanguageTypeBytes() {
                    Object obj = this.languageType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.languageType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setLanguageType(String str) {
                    str.getClass();
                    this.bitField0_ |= 4;
                    this.languageType_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearLanguageType() {
                    this.bitField0_ &= -5;
                    this.languageType_ = PlanBasicInfo.getDefaultInstance().getLanguageType();
                    onChanged();
                    return this;
                }

                public Builder setLanguageTypeBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 4;
                    this.languageType_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public boolean hasPunchTimeStamp() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public int getPunchTimeStamp() {
                    return this.punchTimeStamp_;
                }

                public Builder setPunchTimeStamp(int i) {
                    this.bitField0_ |= 8;
                    this.punchTimeStamp_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearPunchTimeStamp() {
                    this.bitField0_ &= -9;
                    this.punchTimeStamp_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public boolean hasPlanTotalDays() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public int getPlanTotalDays() {
                    return this.planTotalDays_;
                }

                public Builder setPlanTotalDays(int i) {
                    this.bitField0_ |= 16;
                    this.planTotalDays_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearPlanTotalDays() {
                    this.bitField0_ &= -17;
                    this.planTotalDays_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public boolean hasPlanTotalWeeks() {
                    return (this.bitField0_ & 32) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public int getPlanTotalWeeks() {
                    return this.planTotalWeeks_;
                }

                public Builder setPlanTotalWeeks(int i) {
                    this.bitField0_ |= 32;
                    this.planTotalWeeks_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearPlanTotalWeeks() {
                    this.bitField0_ &= -33;
                    this.planTotalWeeks_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public boolean hasPlanStartTime() {
                    return (this.bitField0_ & 64) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public int getPlanStartTime() {
                    return this.planStartTime_;
                }

                public Builder setPlanStartTime(int i) {
                    this.bitField0_ |= 64;
                    this.planStartTime_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearPlanStartTime() {
                    this.bitField0_ &= -65;
                    this.planStartTime_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public boolean hasZoneOffset() {
                    return (this.bitField0_ & 128) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PlanBasicInfoOrBuilder
                public int getZoneOffset() {
                    return this.zoneOffset_;
                }

                public Builder setZoneOffset(int i) {
                    this.bitField0_ |= 128;
                    this.zoneOffset_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearZoneOffset() {
                    this.bitField0_ &= -129;
                    this.zoneOffset_ = 0;
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

            public static PlanBasicInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<PlanBasicInfo> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<PlanBasicInfo> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PlanBasicInfo getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class WeekPlanBasicInfo extends GeneratedMessageV3 implements WeekPlanBasicInfoOrBuilder {
            public static final int CURRENTWEEKDAYS_FIELD_NUMBER = 3;
            public static final int EXERCISETIMES_FIELD_NUMBER = 2;
            public static final int WEEKNUM_FIELD_NUMBER = 4;
            public static final int WEEKPLANENDTIME_FIELD_NUMBER = 6;
            public static final int WEEKPLANNAME_FIELD_NUMBER = 1;
            public static final int WEEKPLANSTARTTIME_FIELD_NUMBER = 5;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private int currentWeekDays_;
            private int exerciseTimes_;
            private byte memoizedIsInitialized;
            private int weekNum_;
            private int weekPlanEndTime_;
            private Internal.IntList weekPlanName_;
            private int weekPlanStartTime_;
            private static final WeekPlanBasicInfo DEFAULT_INSTANCE = new WeekPlanBasicInfo();

            @Deprecated
            public static final Parser<WeekPlanBasicInfo> PARSER = new AbstractParser<WeekPlanBasicInfo>() { // from class: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.1
                @Override // com.google.protobuf.Parser
                public WeekPlanBasicInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new WeekPlanBasicInfo(codedInputStream, extensionRegistryLite);
                }
            };

            private WeekPlanBasicInfo(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private WeekPlanBasicInfo() {
                this.memoizedIsInitialized = (byte) -1;
                this.weekPlanName_ = emptyIntList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new WeekPlanBasicInfo();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private WeekPlanBasicInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 8) {
                                    if (!(z2 & true)) {
                                        this.weekPlanName_ = newIntList();
                                        z2 = true;
                                    }
                                    this.weekPlanName_.addInt(codedInputStream.readUInt32());
                                } else if (readTag == 10) {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if (!(z2 & true) && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.weekPlanName_ = newIntList();
                                        z2 = true;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.weekPlanName_.addInt(codedInputStream.readUInt32());
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 1;
                                    this.exerciseTimes_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 2;
                                    this.currentWeekDays_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 4;
                                    this.weekNum_ = codedInputStream.readUInt32();
                                } else if (readTag == 40) {
                                    this.bitField0_ |= 8;
                                    this.weekPlanStartTime_ = codedInputStream.readUInt32();
                                } else if (readTag == 48) {
                                    this.bitField0_ |= 16;
                                    this.weekPlanEndTime_ = codedInputStream.readUInt32();
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
                        if (z2 & true) {
                            this.weekPlanName_.makeImmutable();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PlanDataForDevice.internal_static_PlanInfo_WeekPlanBasicInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PlanDataForDevice.internal_static_PlanInfo_WeekPlanBasicInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(WeekPlanBasicInfo.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public List<Integer> getWeekPlanNameList() {
                return this.weekPlanName_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public int getWeekPlanNameCount() {
                return this.weekPlanName_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public int getWeekPlanName(int i) {
                return this.weekPlanName_.getInt(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public boolean hasExerciseTimes() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public int getExerciseTimes() {
                return this.exerciseTimes_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public boolean hasCurrentWeekDays() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public int getCurrentWeekDays() {
                return this.currentWeekDays_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public boolean hasWeekNum() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public int getWeekNum() {
                return this.weekNum_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public boolean hasWeekPlanStartTime() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public int getWeekPlanStartTime() {
                return this.weekPlanStartTime_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public boolean hasWeekPlanEndTime() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
            public int getWeekPlanEndTime() {
                return this.weekPlanEndTime_;
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
                if (!hasExerciseTimes()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasCurrentWeekDays()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasWeekNum()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasWeekPlanStartTime()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasWeekPlanEndTime()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                for (int i = 0; i < this.weekPlanName_.size(); i++) {
                    codedOutputStream.writeUInt32(1, this.weekPlanName_.getInt(i));
                }
                if ((this.bitField0_ & 1) != 0) {
                    codedOutputStream.writeUInt32(2, this.exerciseTimes_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeUInt32(3, this.currentWeekDays_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeUInt32(4, this.weekNum_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeUInt32(5, this.weekPlanStartTime_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    codedOutputStream.writeUInt32(6, this.weekPlanEndTime_);
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
                for (int i3 = 0; i3 < this.weekPlanName_.size(); i3++) {
                    i2 += CodedOutputStream.computeUInt32SizeNoTag(this.weekPlanName_.getInt(i3));
                }
                int size = i2 + getWeekPlanNameList().size();
                if ((this.bitField0_ & 1) != 0) {
                    size += CodedOutputStream.computeUInt32Size(2, this.exerciseTimes_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    size += CodedOutputStream.computeUInt32Size(3, this.currentWeekDays_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    size += CodedOutputStream.computeUInt32Size(4, this.weekNum_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    size += CodedOutputStream.computeUInt32Size(5, this.weekPlanStartTime_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    size += CodedOutputStream.computeUInt32Size(6, this.weekPlanEndTime_);
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
                if (!(obj instanceof WeekPlanBasicInfo)) {
                    return super.equals(obj);
                }
                WeekPlanBasicInfo weekPlanBasicInfo = (WeekPlanBasicInfo) obj;
                if (!getWeekPlanNameList().equals(weekPlanBasicInfo.getWeekPlanNameList()) || hasExerciseTimes() != weekPlanBasicInfo.hasExerciseTimes()) {
                    return false;
                }
                if ((hasExerciseTimes() && getExerciseTimes() != weekPlanBasicInfo.getExerciseTimes()) || hasCurrentWeekDays() != weekPlanBasicInfo.hasCurrentWeekDays()) {
                    return false;
                }
                if ((hasCurrentWeekDays() && getCurrentWeekDays() != weekPlanBasicInfo.getCurrentWeekDays()) || hasWeekNum() != weekPlanBasicInfo.hasWeekNum()) {
                    return false;
                }
                if ((hasWeekNum() && getWeekNum() != weekPlanBasicInfo.getWeekNum()) || hasWeekPlanStartTime() != weekPlanBasicInfo.hasWeekPlanStartTime()) {
                    return false;
                }
                if ((!hasWeekPlanStartTime() || getWeekPlanStartTime() == weekPlanBasicInfo.getWeekPlanStartTime()) && hasWeekPlanEndTime() == weekPlanBasicInfo.hasWeekPlanEndTime()) {
                    return (!hasWeekPlanEndTime() || getWeekPlanEndTime() == weekPlanBasicInfo.getWeekPlanEndTime()) && this.unknownFields.equals(weekPlanBasicInfo.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (getWeekPlanNameCount() > 0) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getWeekPlanNameList().hashCode();
                }
                if (hasExerciseTimes()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getExerciseTimes();
                }
                if (hasCurrentWeekDays()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getCurrentWeekDays();
                }
                if (hasWeekNum()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getWeekNum();
                }
                if (hasWeekPlanStartTime()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getWeekPlanStartTime();
                }
                if (hasWeekPlanEndTime()) {
                    hashCode = (((hashCode * 37) + 6) * 53) + getWeekPlanEndTime();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static WeekPlanBasicInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static WeekPlanBasicInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static WeekPlanBasicInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static WeekPlanBasicInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static WeekPlanBasicInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static WeekPlanBasicInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static WeekPlanBasicInfo parseFrom(InputStream inputStream) throws IOException {
                return (WeekPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static WeekPlanBasicInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (WeekPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static WeekPlanBasicInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (WeekPlanBasicInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static WeekPlanBasicInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (WeekPlanBasicInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static WeekPlanBasicInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (WeekPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static WeekPlanBasicInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (WeekPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(WeekPlanBasicInfo weekPlanBasicInfo) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(weekPlanBasicInfo);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WeekPlanBasicInfoOrBuilder {
                private int bitField0_;
                private int currentWeekDays_;
                private int exerciseTimes_;
                private int weekNum_;
                private int weekPlanEndTime_;
                private Internal.IntList weekPlanName_;
                private int weekPlanStartTime_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return PlanDataForDevice.internal_static_PlanInfo_WeekPlanBasicInfo_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return PlanDataForDevice.internal_static_PlanInfo_WeekPlanBasicInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(WeekPlanBasicInfo.class, Builder.class);
                }

                private Builder() {
                    this.weekPlanName_ = WeekPlanBasicInfo.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.weekPlanName_ = WeekPlanBasicInfo.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = WeekPlanBasicInfo.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.weekPlanName_ = WeekPlanBasicInfo.emptyIntList();
                    int i = this.bitField0_;
                    this.exerciseTimes_ = 0;
                    this.currentWeekDays_ = 0;
                    this.weekNum_ = 0;
                    this.weekPlanStartTime_ = 0;
                    this.weekPlanEndTime_ = 0;
                    this.bitField0_ = i & (-64);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return PlanDataForDevice.internal_static_PlanInfo_WeekPlanBasicInfo_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public WeekPlanBasicInfo getDefaultInstanceForType() {
                    return WeekPlanBasicInfo.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public WeekPlanBasicInfo build() {
                    WeekPlanBasicInfo buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public WeekPlanBasicInfo buildPartial() {
                    int i;
                    WeekPlanBasicInfo weekPlanBasicInfo = new WeekPlanBasicInfo(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        this.weekPlanName_.makeImmutable();
                        this.bitField0_ &= -2;
                    }
                    weekPlanBasicInfo.weekPlanName_ = this.weekPlanName_;
                    if ((i2 & 2) != 0) {
                        weekPlanBasicInfo.exerciseTimes_ = this.exerciseTimes_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 4) != 0) {
                        weekPlanBasicInfo.currentWeekDays_ = this.currentWeekDays_;
                        i |= 2;
                    }
                    if ((i2 & 8) != 0) {
                        weekPlanBasicInfo.weekNum_ = this.weekNum_;
                        i |= 4;
                    }
                    if ((i2 & 16) != 0) {
                        weekPlanBasicInfo.weekPlanStartTime_ = this.weekPlanStartTime_;
                        i |= 8;
                    }
                    if ((i2 & 32) != 0) {
                        weekPlanBasicInfo.weekPlanEndTime_ = this.weekPlanEndTime_;
                        i |= 16;
                    }
                    weekPlanBasicInfo.bitField0_ = i;
                    onBuilt();
                    return weekPlanBasicInfo;
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
                    if (message instanceof WeekPlanBasicInfo) {
                        return mergeFrom((WeekPlanBasicInfo) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(WeekPlanBasicInfo weekPlanBasicInfo) {
                    if (weekPlanBasicInfo == WeekPlanBasicInfo.getDefaultInstance()) {
                        return this;
                    }
                    if (!weekPlanBasicInfo.weekPlanName_.isEmpty()) {
                        if (this.weekPlanName_.isEmpty()) {
                            this.weekPlanName_ = weekPlanBasicInfo.weekPlanName_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureWeekPlanNameIsMutable();
                            this.weekPlanName_.addAll(weekPlanBasicInfo.weekPlanName_);
                        }
                        onChanged();
                    }
                    if (weekPlanBasicInfo.hasExerciseTimes()) {
                        setExerciseTimes(weekPlanBasicInfo.getExerciseTimes());
                    }
                    if (weekPlanBasicInfo.hasCurrentWeekDays()) {
                        setCurrentWeekDays(weekPlanBasicInfo.getCurrentWeekDays());
                    }
                    if (weekPlanBasicInfo.hasWeekNum()) {
                        setWeekNum(weekPlanBasicInfo.getWeekNum());
                    }
                    if (weekPlanBasicInfo.hasWeekPlanStartTime()) {
                        setWeekPlanStartTime(weekPlanBasicInfo.getWeekPlanStartTime());
                    }
                    if (weekPlanBasicInfo.hasWeekPlanEndTime()) {
                        setWeekPlanEndTime(weekPlanBasicInfo.getWeekPlanEndTime());
                    }
                    mergeUnknownFields(weekPlanBasicInfo.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return hasExerciseTimes() && hasCurrentWeekDays() && hasWeekNum() && hasWeekPlanStartTime() && hasWeekPlanEndTime();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$WeekPlanBasicInfo> r0 = com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$WeekPlanBasicInfo r2 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$WeekPlanBasicInfo r3 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfo) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$WeekPlanBasicInfo$Builder");
                }

                private void ensureWeekPlanNameIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.weekPlanName_ = WeekPlanBasicInfo.mutableCopy(this.weekPlanName_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public List<Integer> getWeekPlanNameList() {
                    return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.weekPlanName_) : this.weekPlanName_;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public int getWeekPlanNameCount() {
                    return this.weekPlanName_.size();
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public int getWeekPlanName(int i) {
                    return this.weekPlanName_.getInt(i);
                }

                public Builder setWeekPlanName(int i, int i2) {
                    ensureWeekPlanNameIsMutable();
                    this.weekPlanName_.setInt(i, i2);
                    onChanged();
                    return this;
                }

                public Builder addWeekPlanName(int i) {
                    ensureWeekPlanNameIsMutable();
                    this.weekPlanName_.addInt(i);
                    onChanged();
                    return this;
                }

                public Builder addAllWeekPlanName(Iterable<? extends Integer> iterable) {
                    ensureWeekPlanNameIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.weekPlanName_);
                    onChanged();
                    return this;
                }

                public Builder clearWeekPlanName() {
                    this.weekPlanName_ = WeekPlanBasicInfo.emptyIntList();
                    this.bitField0_ &= -2;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public boolean hasExerciseTimes() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public int getExerciseTimes() {
                    return this.exerciseTimes_;
                }

                public Builder setExerciseTimes(int i) {
                    this.bitField0_ |= 2;
                    this.exerciseTimes_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearExerciseTimes() {
                    this.bitField0_ &= -3;
                    this.exerciseTimes_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public boolean hasCurrentWeekDays() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public int getCurrentWeekDays() {
                    return this.currentWeekDays_;
                }

                public Builder setCurrentWeekDays(int i) {
                    this.bitField0_ |= 4;
                    this.currentWeekDays_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearCurrentWeekDays() {
                    this.bitField0_ &= -5;
                    this.currentWeekDays_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public boolean hasWeekNum() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public int getWeekNum() {
                    return this.weekNum_;
                }

                public Builder setWeekNum(int i) {
                    this.bitField0_ |= 8;
                    this.weekNum_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearWeekNum() {
                    this.bitField0_ &= -9;
                    this.weekNum_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public boolean hasWeekPlanStartTime() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public int getWeekPlanStartTime() {
                    return this.weekPlanStartTime_;
                }

                public Builder setWeekPlanStartTime(int i) {
                    this.bitField0_ |= 16;
                    this.weekPlanStartTime_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearWeekPlanStartTime() {
                    this.bitField0_ &= -17;
                    this.weekPlanStartTime_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public boolean hasWeekPlanEndTime() {
                    return (this.bitField0_ & 32) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.WeekPlanBasicInfoOrBuilder
                public int getWeekPlanEndTime() {
                    return this.weekPlanEndTime_;
                }

                public Builder setWeekPlanEndTime(int i) {
                    this.bitField0_ |= 32;
                    this.weekPlanEndTime_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearWeekPlanEndTime() {
                    this.bitField0_ &= -33;
                    this.weekPlanEndTime_ = 0;
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

            public static WeekPlanBasicInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<WeekPlanBasicInfo> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<WeekPlanBasicInfo> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public WeekPlanBasicInfo getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class CourseOutLine extends GeneratedMessageV3 implements CourseOutLineOrBuilder {
            public static final int COURSEID_FIELD_NUMBER = 1;
            public static final int COURSENAME_FIELD_NUMBER = 2;
            public static final int COURSETYPE_FIELD_NUMBER = 4;
            public static final int ISCOURSEPUNCH_FIELD_NUMBER = 5;
            public static final int UPDATETIME_FIELD_NUMBER = 3;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private volatile Object courseId_;
            private Internal.IntList courseName_;
            private int courseType_;
            private int isCoursePunch_;
            private byte memoizedIsInitialized;
            private int updateTime_;
            private static final CourseOutLine DEFAULT_INSTANCE = new CourseOutLine();

            @Deprecated
            public static final Parser<CourseOutLine> PARSER = new AbstractParser<CourseOutLine>() { // from class: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLine.1
                @Override // com.google.protobuf.Parser
                public CourseOutLine parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new CourseOutLine(codedInputStream, extensionRegistryLite);
                }
            };

            private CourseOutLine(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private CourseOutLine() {
                this.memoizedIsInitialized = (byte) -1;
                this.courseId_ = "";
                this.courseName_ = emptyIntList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new CourseOutLine();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private CourseOutLine(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.courseId_ = readBytes;
                                } else if (readTag == 16) {
                                    if ((c & 2) == 0) {
                                        this.courseName_ = newIntList();
                                        c = 2;
                                    }
                                    this.courseName_.addInt(codedInputStream.readUInt32());
                                } else if (readTag == 18) {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if ((c & 2) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.courseName_ = newIntList();
                                        c = 2;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.courseName_.addInt(codedInputStream.readUInt32());
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 2;
                                    this.updateTime_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 4;
                                    this.courseType_ = codedInputStream.readUInt32();
                                } else if (readTag == 40) {
                                    this.bitField0_ |= 8;
                                    this.isCoursePunch_ = codedInputStream.readUInt32();
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
                        if ((c & 2) != 0) {
                            this.courseName_.makeImmutable();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PlanDataForDevice.internal_static_PlanInfo_CourseOutLine_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PlanDataForDevice.internal_static_PlanInfo_CourseOutLine_fieldAccessorTable.ensureFieldAccessorsInitialized(CourseOutLine.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public boolean hasCourseId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public String getCourseId() {
                Object obj = this.courseId_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.courseId_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public ByteString getCourseIdBytes() {
                Object obj = this.courseId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.courseId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public List<Integer> getCourseNameList() {
                return this.courseName_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public int getCourseNameCount() {
                return this.courseName_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public int getCourseName(int i) {
                return this.courseName_.getInt(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public boolean hasUpdateTime() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public int getUpdateTime() {
                return this.updateTime_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public boolean hasCourseType() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public int getCourseType() {
                return this.courseType_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public boolean hasIsCoursePunch() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
            public int getIsCoursePunch() {
                return this.isCoursePunch_;
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
                if (!hasCourseId()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasUpdateTime()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasCourseType()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasIsCoursePunch()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.courseId_);
                }
                for (int i = 0; i < this.courseName_.size(); i++) {
                    codedOutputStream.writeUInt32(2, this.courseName_.getInt(i));
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeUInt32(3, this.updateTime_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeUInt32(4, this.courseType_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeUInt32(5, this.isCoursePunch_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.courseId_) : 0;
                int i2 = 0;
                for (int i3 = 0; i3 < this.courseName_.size(); i3++) {
                    i2 += CodedOutputStream.computeUInt32SizeNoTag(this.courseName_.getInt(i3));
                }
                int size = computeStringSize + i2 + getCourseNameList().size();
                if ((this.bitField0_ & 2) != 0) {
                    size += CodedOutputStream.computeUInt32Size(3, this.updateTime_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    size += CodedOutputStream.computeUInt32Size(4, this.courseType_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    size += CodedOutputStream.computeUInt32Size(5, this.isCoursePunch_);
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
                if (!(obj instanceof CourseOutLine)) {
                    return super.equals(obj);
                }
                CourseOutLine courseOutLine = (CourseOutLine) obj;
                if (hasCourseId() != courseOutLine.hasCourseId()) {
                    return false;
                }
                if ((hasCourseId() && !getCourseId().equals(courseOutLine.getCourseId())) || !getCourseNameList().equals(courseOutLine.getCourseNameList()) || hasUpdateTime() != courseOutLine.hasUpdateTime()) {
                    return false;
                }
                if ((hasUpdateTime() && getUpdateTime() != courseOutLine.getUpdateTime()) || hasCourseType() != courseOutLine.hasCourseType()) {
                    return false;
                }
                if ((!hasCourseType() || getCourseType() == courseOutLine.getCourseType()) && hasIsCoursePunch() == courseOutLine.hasIsCoursePunch()) {
                    return (!hasIsCoursePunch() || getIsCoursePunch() == courseOutLine.getIsCoursePunch()) && this.unknownFields.equals(courseOutLine.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasCourseId()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getCourseId().hashCode();
                }
                if (getCourseNameCount() > 0) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getCourseNameList().hashCode();
                }
                if (hasUpdateTime()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getUpdateTime();
                }
                if (hasCourseType()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getCourseType();
                }
                if (hasIsCoursePunch()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getIsCoursePunch();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static CourseOutLine parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static CourseOutLine parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static CourseOutLine parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static CourseOutLine parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static CourseOutLine parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static CourseOutLine parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static CourseOutLine parseFrom(InputStream inputStream) throws IOException {
                return (CourseOutLine) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static CourseOutLine parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CourseOutLine) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CourseOutLine parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (CourseOutLine) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static CourseOutLine parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CourseOutLine) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CourseOutLine parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (CourseOutLine) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static CourseOutLine parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CourseOutLine) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(CourseOutLine courseOutLine) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(courseOutLine);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CourseOutLineOrBuilder {
                private int bitField0_;
                private Object courseId_;
                private Internal.IntList courseName_;
                private int courseType_;
                private int isCoursePunch_;
                private int updateTime_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return PlanDataForDevice.internal_static_PlanInfo_CourseOutLine_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return PlanDataForDevice.internal_static_PlanInfo_CourseOutLine_fieldAccessorTable.ensureFieldAccessorsInitialized(CourseOutLine.class, Builder.class);
                }

                private Builder() {
                    this.courseId_ = "";
                    this.courseName_ = CourseOutLine.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.courseId_ = "";
                    this.courseName_ = CourseOutLine.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = CourseOutLine.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.courseId_ = "";
                    this.bitField0_ &= -2;
                    this.courseName_ = CourseOutLine.emptyIntList();
                    int i = this.bitField0_;
                    this.updateTime_ = 0;
                    this.courseType_ = 0;
                    this.isCoursePunch_ = 0;
                    this.bitField0_ = i & (-31);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return PlanDataForDevice.internal_static_PlanInfo_CourseOutLine_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public CourseOutLine getDefaultInstanceForType() {
                    return CourseOutLine.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public CourseOutLine build() {
                    CourseOutLine buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public CourseOutLine buildPartial() {
                    CourseOutLine courseOutLine = new CourseOutLine(this);
                    int i = this.bitField0_;
                    int i2 = (i & 1) != 0 ? 1 : 0;
                    courseOutLine.courseId_ = this.courseId_;
                    if ((this.bitField0_ & 2) != 0) {
                        this.courseName_.makeImmutable();
                        this.bitField0_ &= -3;
                    }
                    courseOutLine.courseName_ = this.courseName_;
                    if ((i & 4) != 0) {
                        courseOutLine.updateTime_ = this.updateTime_;
                        i2 |= 2;
                    }
                    if ((i & 8) != 0) {
                        courseOutLine.courseType_ = this.courseType_;
                        i2 |= 4;
                    }
                    if ((i & 16) != 0) {
                        courseOutLine.isCoursePunch_ = this.isCoursePunch_;
                        i2 |= 8;
                    }
                    courseOutLine.bitField0_ = i2;
                    onBuilt();
                    return courseOutLine;
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
                    if (message instanceof CourseOutLine) {
                        return mergeFrom((CourseOutLine) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(CourseOutLine courseOutLine) {
                    if (courseOutLine == CourseOutLine.getDefaultInstance()) {
                        return this;
                    }
                    if (courseOutLine.hasCourseId()) {
                        this.bitField0_ |= 1;
                        this.courseId_ = courseOutLine.courseId_;
                        onChanged();
                    }
                    if (!courseOutLine.courseName_.isEmpty()) {
                        if (this.courseName_.isEmpty()) {
                            this.courseName_ = courseOutLine.courseName_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureCourseNameIsMutable();
                            this.courseName_.addAll(courseOutLine.courseName_);
                        }
                        onChanged();
                    }
                    if (courseOutLine.hasUpdateTime()) {
                        setUpdateTime(courseOutLine.getUpdateTime());
                    }
                    if (courseOutLine.hasCourseType()) {
                        setCourseType(courseOutLine.getCourseType());
                    }
                    if (courseOutLine.hasIsCoursePunch()) {
                        setIsCoursePunch(courseOutLine.getIsCoursePunch());
                    }
                    mergeUnknownFields(courseOutLine.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return hasCourseId() && hasUpdateTime() && hasCourseType() && hasIsCoursePunch();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLine.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$CourseOutLine> r0 = com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLine.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$CourseOutLine r2 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLine) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$CourseOutLine r3 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLine) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLine.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$CourseOutLine$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public boolean hasCourseId() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public String getCourseId() {
                    Object obj = this.courseId_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.courseId_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public ByteString getCourseIdBytes() {
                    Object obj = this.courseId_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.courseId_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setCourseId(String str) {
                    str.getClass();
                    this.bitField0_ |= 1;
                    this.courseId_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearCourseId() {
                    this.bitField0_ &= -2;
                    this.courseId_ = CourseOutLine.getDefaultInstance().getCourseId();
                    onChanged();
                    return this;
                }

                public Builder setCourseIdBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 1;
                    this.courseId_ = byteString;
                    onChanged();
                    return this;
                }

                private void ensureCourseNameIsMutable() {
                    if ((this.bitField0_ & 2) == 0) {
                        this.courseName_ = CourseOutLine.mutableCopy(this.courseName_);
                        this.bitField0_ |= 2;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public List<Integer> getCourseNameList() {
                    return (this.bitField0_ & 2) != 0 ? Collections.unmodifiableList(this.courseName_) : this.courseName_;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public int getCourseNameCount() {
                    return this.courseName_.size();
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public int getCourseName(int i) {
                    return this.courseName_.getInt(i);
                }

                public Builder setCourseName(int i, int i2) {
                    ensureCourseNameIsMutable();
                    this.courseName_.setInt(i, i2);
                    onChanged();
                    return this;
                }

                public Builder addCourseName(int i) {
                    ensureCourseNameIsMutable();
                    this.courseName_.addInt(i);
                    onChanged();
                    return this;
                }

                public Builder addAllCourseName(Iterable<? extends Integer> iterable) {
                    ensureCourseNameIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.courseName_);
                    onChanged();
                    return this;
                }

                public Builder clearCourseName() {
                    this.courseName_ = CourseOutLine.emptyIntList();
                    this.bitField0_ &= -3;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public boolean hasUpdateTime() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public int getUpdateTime() {
                    return this.updateTime_;
                }

                public Builder setUpdateTime(int i) {
                    this.bitField0_ |= 4;
                    this.updateTime_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearUpdateTime() {
                    this.bitField0_ &= -5;
                    this.updateTime_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public boolean hasCourseType() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public int getCourseType() {
                    return this.courseType_;
                }

                public Builder setCourseType(int i) {
                    this.bitField0_ |= 8;
                    this.courseType_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearCourseType() {
                    this.bitField0_ &= -9;
                    this.courseType_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public boolean hasIsCoursePunch() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.CourseOutLineOrBuilder
                public int getIsCoursePunch() {
                    return this.isCoursePunch_;
                }

                public Builder setIsCoursePunch(int i) {
                    this.bitField0_ |= 16;
                    this.isCoursePunch_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearIsCoursePunch() {
                    this.bitField0_ &= -17;
                    this.isCoursePunch_ = 0;
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

            public static CourseOutLine getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CourseOutLine> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<CourseOutLine> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CourseOutLine getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class DayPlanBasicInfo extends GeneratedMessageV3 implements DayPlanBasicInfoOrBuilder {
            public static final int COURSENUM_FIELD_NUMBER = 2;
            public static final int DAYPLANCOURSE_FIELD_NUMBER = 4;
            public static final int ISDAYPUNCH_FIELD_NUMBER = 3;
            public static final int TIME_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private int courseNum_;
            private List<CourseOutLine> dayPlanCourse_;
            private int isDayPunch_;
            private byte memoizedIsInitialized;
            private int time_;
            private static final DayPlanBasicInfo DEFAULT_INSTANCE = new DayPlanBasicInfo();

            @Deprecated
            public static final Parser<DayPlanBasicInfo> PARSER = new AbstractParser<DayPlanBasicInfo>() { // from class: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfo.1
                @Override // com.google.protobuf.Parser
                public DayPlanBasicInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new DayPlanBasicInfo(codedInputStream, extensionRegistryLite);
                }
            };

            private DayPlanBasicInfo(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private DayPlanBasicInfo() {
                this.memoizedIsInitialized = (byte) -1;
                this.dayPlanCourse_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new DayPlanBasicInfo();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private DayPlanBasicInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.time_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.courseNum_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.isDayPunch_ = codedInputStream.readUInt32();
                                } else if (readTag == 34) {
                                    if ((c & '\b') == 0) {
                                        this.dayPlanCourse_ = new ArrayList();
                                        c = '\b';
                                    }
                                    this.dayPlanCourse_.add((CourseOutLine) codedInputStream.readMessage(CourseOutLine.PARSER, extensionRegistryLite));
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
                        if ((c & '\b') != 0) {
                            this.dayPlanCourse_ = Collections.unmodifiableList(this.dayPlanCourse_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PlanDataForDevice.internal_static_PlanInfo_DayPlanBasicInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PlanDataForDevice.internal_static_PlanInfo_DayPlanBasicInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DayPlanBasicInfo.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public boolean hasTime() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public int getTime() {
                return this.time_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public boolean hasCourseNum() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public int getCourseNum() {
                return this.courseNum_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public boolean hasIsDayPunch() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public int getIsDayPunch() {
                return this.isDayPunch_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public List<CourseOutLine> getDayPlanCourseList() {
                return this.dayPlanCourse_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public List<? extends CourseOutLineOrBuilder> getDayPlanCourseOrBuilderList() {
                return this.dayPlanCourse_;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public int getDayPlanCourseCount() {
                return this.dayPlanCourse_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public CourseOutLine getDayPlanCourse(int i) {
                return this.dayPlanCourse_.get(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
            public CourseOutLineOrBuilder getDayPlanCourseOrBuilder(int i) {
                return this.dayPlanCourse_.get(i);
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
                if (!hasTime()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasCourseNum()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasIsDayPunch()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                for (int i = 0; i < getDayPlanCourseCount(); i++) {
                    if (!getDayPlanCourse(i).isInitialized()) {
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
                    codedOutputStream.writeUInt32(1, this.time_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeUInt32(2, this.courseNum_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeUInt32(3, this.isDayPunch_);
                }
                for (int i = 0; i < this.dayPlanCourse_.size(); i++) {
                    codedOutputStream.writeMessage(4, this.dayPlanCourse_.get(i));
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeUInt32Size = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeUInt32Size(1, this.time_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, this.courseNum_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(3, this.isDayPunch_);
                }
                for (int i2 = 0; i2 < this.dayPlanCourse_.size(); i2++) {
                    computeUInt32Size += CodedOutputStream.computeMessageSize(4, this.dayPlanCourse_.get(i2));
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
                if (!(obj instanceof DayPlanBasicInfo)) {
                    return super.equals(obj);
                }
                DayPlanBasicInfo dayPlanBasicInfo = (DayPlanBasicInfo) obj;
                if (hasTime() != dayPlanBasicInfo.hasTime()) {
                    return false;
                }
                if ((hasTime() && getTime() != dayPlanBasicInfo.getTime()) || hasCourseNum() != dayPlanBasicInfo.hasCourseNum()) {
                    return false;
                }
                if ((!hasCourseNum() || getCourseNum() == dayPlanBasicInfo.getCourseNum()) && hasIsDayPunch() == dayPlanBasicInfo.hasIsDayPunch()) {
                    return (!hasIsDayPunch() || getIsDayPunch() == dayPlanBasicInfo.getIsDayPunch()) && getDayPlanCourseList().equals(dayPlanBasicInfo.getDayPlanCourseList()) && this.unknownFields.equals(dayPlanBasicInfo.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasTime()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getTime();
                }
                if (hasCourseNum()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getCourseNum();
                }
                if (hasIsDayPunch()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getIsDayPunch();
                }
                if (getDayPlanCourseCount() > 0) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getDayPlanCourseList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static DayPlanBasicInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static DayPlanBasicInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static DayPlanBasicInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static DayPlanBasicInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static DayPlanBasicInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static DayPlanBasicInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static DayPlanBasicInfo parseFrom(InputStream inputStream) throws IOException {
                return (DayPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static DayPlanBasicInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (DayPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static DayPlanBasicInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (DayPlanBasicInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static DayPlanBasicInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (DayPlanBasicInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static DayPlanBasicInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (DayPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static DayPlanBasicInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (DayPlanBasicInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(DayPlanBasicInfo dayPlanBasicInfo) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(dayPlanBasicInfo);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DayPlanBasicInfoOrBuilder {
                private int bitField0_;
                private int courseNum_;
                private RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> dayPlanCourseBuilder_;
                private List<CourseOutLine> dayPlanCourse_;
                private int isDayPunch_;
                private int time_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return PlanDataForDevice.internal_static_PlanInfo_DayPlanBasicInfo_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return PlanDataForDevice.internal_static_PlanInfo_DayPlanBasicInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DayPlanBasicInfo.class, Builder.class);
                }

                private Builder() {
                    this.dayPlanCourse_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.dayPlanCourse_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (DayPlanBasicInfo.alwaysUseFieldBuilders) {
                        getDayPlanCourseFieldBuilder();
                    }
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.time_ = 0;
                    int i = this.bitField0_;
                    this.courseNum_ = 0;
                    this.isDayPunch_ = 0;
                    this.bitField0_ = i & (-8);
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.dayPlanCourse_ = Collections.emptyList();
                        this.bitField0_ &= -9;
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return PlanDataForDevice.internal_static_PlanInfo_DayPlanBasicInfo_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public DayPlanBasicInfo getDefaultInstanceForType() {
                    return DayPlanBasicInfo.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public DayPlanBasicInfo build() {
                    DayPlanBasicInfo buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public DayPlanBasicInfo buildPartial() {
                    int i;
                    DayPlanBasicInfo dayPlanBasicInfo = new DayPlanBasicInfo(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        dayPlanBasicInfo.time_ = this.time_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        dayPlanBasicInfo.courseNum_ = this.courseNum_;
                        i |= 2;
                    }
                    if ((i2 & 4) != 0) {
                        dayPlanBasicInfo.isDayPunch_ = this.isDayPunch_;
                        i |= 4;
                    }
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        dayPlanBasicInfo.dayPlanCourse_ = repeatedFieldBuilderV3.build();
                    } else {
                        if ((this.bitField0_ & 8) != 0) {
                            this.dayPlanCourse_ = Collections.unmodifiableList(this.dayPlanCourse_);
                            this.bitField0_ &= -9;
                        }
                        dayPlanBasicInfo.dayPlanCourse_ = this.dayPlanCourse_;
                    }
                    dayPlanBasicInfo.bitField0_ = i;
                    onBuilt();
                    return dayPlanBasicInfo;
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
                    if (message instanceof DayPlanBasicInfo) {
                        return mergeFrom((DayPlanBasicInfo) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(DayPlanBasicInfo dayPlanBasicInfo) {
                    if (dayPlanBasicInfo == DayPlanBasicInfo.getDefaultInstance()) {
                        return this;
                    }
                    if (dayPlanBasicInfo.hasTime()) {
                        setTime(dayPlanBasicInfo.getTime());
                    }
                    if (dayPlanBasicInfo.hasCourseNum()) {
                        setCourseNum(dayPlanBasicInfo.getCourseNum());
                    }
                    if (dayPlanBasicInfo.hasIsDayPunch()) {
                        setIsDayPunch(dayPlanBasicInfo.getIsDayPunch());
                    }
                    if (this.dayPlanCourseBuilder_ == null) {
                        if (!dayPlanBasicInfo.dayPlanCourse_.isEmpty()) {
                            if (this.dayPlanCourse_.isEmpty()) {
                                this.dayPlanCourse_ = dayPlanBasicInfo.dayPlanCourse_;
                                this.bitField0_ &= -9;
                            } else {
                                ensureDayPlanCourseIsMutable();
                                this.dayPlanCourse_.addAll(dayPlanBasicInfo.dayPlanCourse_);
                            }
                            onChanged();
                        }
                    } else if (!dayPlanBasicInfo.dayPlanCourse_.isEmpty()) {
                        if (!this.dayPlanCourseBuilder_.isEmpty()) {
                            this.dayPlanCourseBuilder_.addAllMessages(dayPlanBasicInfo.dayPlanCourse_);
                        } else {
                            this.dayPlanCourseBuilder_.dispose();
                            this.dayPlanCourseBuilder_ = null;
                            this.dayPlanCourse_ = dayPlanBasicInfo.dayPlanCourse_;
                            this.bitField0_ &= -9;
                            this.dayPlanCourseBuilder_ = DayPlanBasicInfo.alwaysUseFieldBuilders ? getDayPlanCourseFieldBuilder() : null;
                        }
                    }
                    mergeUnknownFields(dayPlanBasicInfo.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (!hasTime() || !hasCourseNum() || !hasIsDayPunch()) {
                        return false;
                    }
                    for (int i = 0; i < getDayPlanCourseCount(); i++) {
                        if (!getDayPlanCourse(i).isInitialized()) {
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
                public com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$DayPlanBasicInfo> r0 = com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$DayPlanBasicInfo r2 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$DayPlanBasicInfo r3 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfo) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$DayPlanBasicInfo$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public boolean hasTime() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public int getTime() {
                    return this.time_;
                }

                public Builder setTime(int i) {
                    this.bitField0_ |= 1;
                    this.time_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearTime() {
                    this.bitField0_ &= -2;
                    this.time_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public boolean hasCourseNum() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public int getCourseNum() {
                    return this.courseNum_;
                }

                public Builder setCourseNum(int i) {
                    this.bitField0_ |= 2;
                    this.courseNum_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearCourseNum() {
                    this.bitField0_ &= -3;
                    this.courseNum_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public boolean hasIsDayPunch() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public int getIsDayPunch() {
                    return this.isDayPunch_;
                }

                public Builder setIsDayPunch(int i) {
                    this.bitField0_ |= 4;
                    this.isDayPunch_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearIsDayPunch() {
                    this.bitField0_ &= -5;
                    this.isDayPunch_ = 0;
                    onChanged();
                    return this;
                }

                private void ensureDayPlanCourseIsMutable() {
                    if ((this.bitField0_ & 8) == 0) {
                        this.dayPlanCourse_ = new ArrayList(this.dayPlanCourse_);
                        this.bitField0_ |= 8;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public List<CourseOutLine> getDayPlanCourseList() {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return Collections.unmodifiableList(this.dayPlanCourse_);
                    }
                    return repeatedFieldBuilderV3.getMessageList();
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public int getDayPlanCourseCount() {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.dayPlanCourse_.size();
                    }
                    return repeatedFieldBuilderV3.getCount();
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public CourseOutLine getDayPlanCourse(int i) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.dayPlanCourse_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessage(i);
                }

                public Builder setDayPlanCourse(int i, CourseOutLine courseOutLine) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        courseOutLine.getClass();
                        ensureDayPlanCourseIsMutable();
                        this.dayPlanCourse_.set(i, courseOutLine);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, courseOutLine);
                    }
                    return this;
                }

                public Builder setDayPlanCourse(int i, CourseOutLine.Builder builder) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureDayPlanCourseIsMutable();
                        this.dayPlanCourse_.set(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addDayPlanCourse(CourseOutLine courseOutLine) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        courseOutLine.getClass();
                        ensureDayPlanCourseIsMutable();
                        this.dayPlanCourse_.add(courseOutLine);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(courseOutLine);
                    }
                    return this;
                }

                public Builder addDayPlanCourse(int i, CourseOutLine courseOutLine) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        courseOutLine.getClass();
                        ensureDayPlanCourseIsMutable();
                        this.dayPlanCourse_.add(i, courseOutLine);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, courseOutLine);
                    }
                    return this;
                }

                public Builder addDayPlanCourse(CourseOutLine.Builder builder) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureDayPlanCourseIsMutable();
                        this.dayPlanCourse_.add(builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addDayPlanCourse(int i, CourseOutLine.Builder builder) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureDayPlanCourseIsMutable();
                        this.dayPlanCourse_.add(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addAllDayPlanCourse(Iterable<? extends CourseOutLine> iterable) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureDayPlanCourseIsMutable();
                        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.dayPlanCourse_);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder clearDayPlanCourse() {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.dayPlanCourse_ = Collections.emptyList();
                        this.bitField0_ &= -9;
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                public Builder removeDayPlanCourse(int i) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureDayPlanCourseIsMutable();
                        this.dayPlanCourse_.remove(i);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.remove(i);
                    }
                    return this;
                }

                public CourseOutLine.Builder getDayPlanCourseBuilder(int i) {
                    return getDayPlanCourseFieldBuilder().getBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public CourseOutLineOrBuilder getDayPlanCourseOrBuilder(int i) {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.dayPlanCourse_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.DayPlanBasicInfoOrBuilder
                public List<? extends CourseOutLineOrBuilder> getDayPlanCourseOrBuilderList() {
                    RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> repeatedFieldBuilderV3 = this.dayPlanCourseBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        return repeatedFieldBuilderV3.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.dayPlanCourse_);
                }

                public CourseOutLine.Builder addDayPlanCourseBuilder() {
                    return getDayPlanCourseFieldBuilder().addBuilder(CourseOutLine.getDefaultInstance());
                }

                public CourseOutLine.Builder addDayPlanCourseBuilder(int i) {
                    return getDayPlanCourseFieldBuilder().addBuilder(i, CourseOutLine.getDefaultInstance());
                }

                public List<CourseOutLine.Builder> getDayPlanCourseBuilderList() {
                    return getDayPlanCourseFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilderV3<CourseOutLine, CourseOutLine.Builder, CourseOutLineOrBuilder> getDayPlanCourseFieldBuilder() {
                    if (this.dayPlanCourseBuilder_ == null) {
                        this.dayPlanCourseBuilder_ = new RepeatedFieldBuilderV3<>(this.dayPlanCourse_, (this.bitField0_ & 8) != 0, getParentForChildren(), isClean());
                        this.dayPlanCourse_ = null;
                    }
                    return this.dayPlanCourseBuilder_;
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

            public static DayPlanBasicInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<DayPlanBasicInfo> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<DayPlanBasicInfo> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DayPlanBasicInfo getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public boolean hasPlanBasicInfo() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public PlanBasicInfo getPlanBasicInfo() {
            PlanBasicInfo planBasicInfo = this.planBasicInfo_;
            return planBasicInfo == null ? PlanBasicInfo.getDefaultInstance() : planBasicInfo;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public PlanBasicInfoOrBuilder getPlanBasicInfoOrBuilder() {
            PlanBasicInfo planBasicInfo = this.planBasicInfo_;
            return planBasicInfo == null ? PlanBasicInfo.getDefaultInstance() : planBasicInfo;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public List<WeekPlanBasicInfo> getWeekPlanBasicInfoList() {
            return this.weekPlanBasicInfo_;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public List<? extends WeekPlanBasicInfoOrBuilder> getWeekPlanBasicInfoOrBuilderList() {
            return this.weekPlanBasicInfo_;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public int getWeekPlanBasicInfoCount() {
            return this.weekPlanBasicInfo_.size();
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public WeekPlanBasicInfo getWeekPlanBasicInfo(int i) {
            return this.weekPlanBasicInfo_.get(i);
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public WeekPlanBasicInfoOrBuilder getWeekPlanBasicInfoOrBuilder(int i) {
            return this.weekPlanBasicInfo_.get(i);
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public List<DayPlanBasicInfo> getDayPlanBasicInfoList() {
            return this.dayPlanBasicInfo_;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public List<? extends DayPlanBasicInfoOrBuilder> getDayPlanBasicInfoOrBuilderList() {
            return this.dayPlanBasicInfo_;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public int getDayPlanBasicInfoCount() {
            return this.dayPlanBasicInfo_.size();
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public DayPlanBasicInfo getDayPlanBasicInfo(int i) {
            return this.dayPlanBasicInfo_.get(i);
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
        public DayPlanBasicInfoOrBuilder getDayPlanBasicInfoOrBuilder(int i) {
            return this.dayPlanBasicInfo_.get(i);
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
            if (!hasPlanBasicInfo()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!getPlanBasicInfo().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getWeekPlanBasicInfoCount(); i++) {
                if (!getWeekPlanBasicInfo(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getDayPlanBasicInfoCount(); i2++) {
                if (!getDayPlanBasicInfo(i2).isInitialized()) {
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
                codedOutputStream.writeMessage(1, getPlanBasicInfo());
            }
            for (int i = 0; i < this.weekPlanBasicInfo_.size(); i++) {
                codedOutputStream.writeMessage(2, this.weekPlanBasicInfo_.get(i));
            }
            for (int i2 = 0; i2 < this.dayPlanBasicInfo_.size(); i2++) {
                codedOutputStream.writeMessage(3, this.dayPlanBasicInfo_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeMessageSize(1, getPlanBasicInfo()) : 0;
            for (int i2 = 0; i2 < this.weekPlanBasicInfo_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, this.weekPlanBasicInfo_.get(i2));
            }
            for (int i3 = 0; i3 < this.dayPlanBasicInfo_.size(); i3++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, this.dayPlanBasicInfo_.get(i3));
            }
            int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PlanInfo)) {
                return super.equals(obj);
            }
            PlanInfo planInfo = (PlanInfo) obj;
            if (hasPlanBasicInfo() != planInfo.hasPlanBasicInfo()) {
                return false;
            }
            return (!hasPlanBasicInfo() || getPlanBasicInfo().equals(planInfo.getPlanBasicInfo())) && getWeekPlanBasicInfoList().equals(planInfo.getWeekPlanBasicInfoList()) && getDayPlanBasicInfoList().equals(planInfo.getDayPlanBasicInfoList()) && this.unknownFields.equals(planInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasPlanBasicInfo()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getPlanBasicInfo().hashCode();
            }
            if (getWeekPlanBasicInfoCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getWeekPlanBasicInfoList().hashCode();
            }
            if (getDayPlanBasicInfoCount() > 0) {
                hashCode = (((hashCode * 37) + 3) * 53) + getDayPlanBasicInfoList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static PlanInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PlanInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PlanInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PlanInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PlanInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PlanInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PlanInfo parseFrom(InputStream inputStream) throws IOException {
            return (PlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PlanInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PlanInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PlanInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PlanInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlanInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PlanInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PlanInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PlanInfo planInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(planInfo);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PlanInfoOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> dayPlanBasicInfoBuilder_;
            private List<DayPlanBasicInfo> dayPlanBasicInfo_;
            private SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> planBasicInfoBuilder_;
            private PlanBasicInfo planBasicInfo_;
            private RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> weekPlanBasicInfoBuilder_;
            private List<WeekPlanBasicInfo> weekPlanBasicInfo_;

            public static final Descriptors.Descriptor getDescriptor() {
                return PlanDataForDevice.internal_static_PlanInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PlanDataForDevice.internal_static_PlanInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanInfo.class, Builder.class);
            }

            private Builder() {
                this.weekPlanBasicInfo_ = Collections.emptyList();
                this.dayPlanBasicInfo_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.weekPlanBasicInfo_ = Collections.emptyList();
                this.dayPlanBasicInfo_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (PlanInfo.alwaysUseFieldBuilders) {
                    getPlanBasicInfoFieldBuilder();
                    getWeekPlanBasicInfoFieldBuilder();
                    getDayPlanBasicInfoFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.planBasicInfo_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -2;
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.weekPlanBasicInfo_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV32 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    this.dayPlanBasicInfo_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV32.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return PlanDataForDevice.internal_static_PlanInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PlanInfo getDefaultInstanceForType() {
                return PlanInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PlanInfo build() {
                PlanInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PlanInfo buildPartial() {
                PlanInfo planInfo = new PlanInfo(this);
                int i = 1;
                if ((this.bitField0_ & 1) != 0) {
                    SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        planInfo.planBasicInfo_ = this.planBasicInfo_;
                    } else {
                        planInfo.planBasicInfo_ = singleFieldBuilderV3.build();
                    }
                } else {
                    i = 0;
                }
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    planInfo.weekPlanBasicInfo_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 2) != 0) {
                        this.weekPlanBasicInfo_ = Collections.unmodifiableList(this.weekPlanBasicInfo_);
                        this.bitField0_ &= -3;
                    }
                    planInfo.weekPlanBasicInfo_ = this.weekPlanBasicInfo_;
                }
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV32 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV32 != null) {
                    planInfo.dayPlanBasicInfo_ = repeatedFieldBuilderV32.build();
                } else {
                    if ((this.bitField0_ & 4) != 0) {
                        this.dayPlanBasicInfo_ = Collections.unmodifiableList(this.dayPlanBasicInfo_);
                        this.bitField0_ &= -5;
                    }
                    planInfo.dayPlanBasicInfo_ = this.dayPlanBasicInfo_;
                }
                planInfo.bitField0_ = i;
                onBuilt();
                return planInfo;
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
                if (message instanceof PlanInfo) {
                    return mergeFrom((PlanInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PlanInfo planInfo) {
                if (planInfo == PlanInfo.getDefaultInstance()) {
                    return this;
                }
                if (planInfo.hasPlanBasicInfo()) {
                    mergePlanBasicInfo(planInfo.getPlanBasicInfo());
                }
                if (this.weekPlanBasicInfoBuilder_ == null) {
                    if (!planInfo.weekPlanBasicInfo_.isEmpty()) {
                        if (this.weekPlanBasicInfo_.isEmpty()) {
                            this.weekPlanBasicInfo_ = planInfo.weekPlanBasicInfo_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureWeekPlanBasicInfoIsMutable();
                            this.weekPlanBasicInfo_.addAll(planInfo.weekPlanBasicInfo_);
                        }
                        onChanged();
                    }
                } else if (!planInfo.weekPlanBasicInfo_.isEmpty()) {
                    if (!this.weekPlanBasicInfoBuilder_.isEmpty()) {
                        this.weekPlanBasicInfoBuilder_.addAllMessages(planInfo.weekPlanBasicInfo_);
                    } else {
                        this.weekPlanBasicInfoBuilder_.dispose();
                        this.weekPlanBasicInfoBuilder_ = null;
                        this.weekPlanBasicInfo_ = planInfo.weekPlanBasicInfo_;
                        this.bitField0_ &= -3;
                        this.weekPlanBasicInfoBuilder_ = PlanInfo.alwaysUseFieldBuilders ? getWeekPlanBasicInfoFieldBuilder() : null;
                    }
                }
                if (this.dayPlanBasicInfoBuilder_ == null) {
                    if (!planInfo.dayPlanBasicInfo_.isEmpty()) {
                        if (this.dayPlanBasicInfo_.isEmpty()) {
                            this.dayPlanBasicInfo_ = planInfo.dayPlanBasicInfo_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureDayPlanBasicInfoIsMutable();
                            this.dayPlanBasicInfo_.addAll(planInfo.dayPlanBasicInfo_);
                        }
                        onChanged();
                    }
                } else if (!planInfo.dayPlanBasicInfo_.isEmpty()) {
                    if (!this.dayPlanBasicInfoBuilder_.isEmpty()) {
                        this.dayPlanBasicInfoBuilder_.addAllMessages(planInfo.dayPlanBasicInfo_);
                    } else {
                        this.dayPlanBasicInfoBuilder_.dispose();
                        this.dayPlanBasicInfoBuilder_ = null;
                        this.dayPlanBasicInfo_ = planInfo.dayPlanBasicInfo_;
                        this.bitField0_ &= -5;
                        this.dayPlanBasicInfoBuilder_ = PlanInfo.alwaysUseFieldBuilders ? getDayPlanBasicInfoFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(planInfo.unknownFields);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasPlanBasicInfo() || !getPlanBasicInfo().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getWeekPlanBasicInfoCount(); i++) {
                    if (!getWeekPlanBasicInfo(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getDayPlanBasicInfoCount(); i2++) {
                    if (!getDayPlanBasicInfo(i2).isInitialized()) {
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
            public com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo> r0 = com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo r2 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                    com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo r3 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo) r3     // Catch: java.lang.Throwable -> Le
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
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanInfo$Builder");
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public boolean hasPlanBasicInfo() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public PlanBasicInfo getPlanBasicInfo() {
                SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PlanBasicInfo planBasicInfo = this.planBasicInfo_;
                    return planBasicInfo == null ? PlanBasicInfo.getDefaultInstance() : planBasicInfo;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setPlanBasicInfo(PlanBasicInfo planBasicInfo) {
                SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    planBasicInfo.getClass();
                    this.planBasicInfo_ = planBasicInfo;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(planBasicInfo);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setPlanBasicInfo(PlanBasicInfo.Builder builder) {
                SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.planBasicInfo_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergePlanBasicInfo(PlanBasicInfo planBasicInfo) {
                PlanBasicInfo planBasicInfo2;
                SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) != 0 && (planBasicInfo2 = this.planBasicInfo_) != null && planBasicInfo2 != PlanBasicInfo.getDefaultInstance()) {
                        this.planBasicInfo_ = PlanBasicInfo.newBuilder(this.planBasicInfo_).mergeFrom(planBasicInfo).buildPartial();
                    } else {
                        this.planBasicInfo_ = planBasicInfo;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(planBasicInfo);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearPlanBasicInfo() {
                SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.planBasicInfo_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -2;
                return this;
            }

            public PlanBasicInfo.Builder getPlanBasicInfoBuilder() {
                this.bitField0_ |= 1;
                onChanged();
                return getPlanBasicInfoFieldBuilder().getBuilder();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public PlanBasicInfoOrBuilder getPlanBasicInfoOrBuilder() {
                SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PlanBasicInfo planBasicInfo = this.planBasicInfo_;
                return planBasicInfo == null ? PlanBasicInfo.getDefaultInstance() : planBasicInfo;
            }

            private SingleFieldBuilderV3<PlanBasicInfo, PlanBasicInfo.Builder, PlanBasicInfoOrBuilder> getPlanBasicInfoFieldBuilder() {
                if (this.planBasicInfoBuilder_ == null) {
                    this.planBasicInfoBuilder_ = new SingleFieldBuilderV3<>(getPlanBasicInfo(), getParentForChildren(), isClean());
                    this.planBasicInfo_ = null;
                }
                return this.planBasicInfoBuilder_;
            }

            private void ensureWeekPlanBasicInfoIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.weekPlanBasicInfo_ = new ArrayList(this.weekPlanBasicInfo_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public List<WeekPlanBasicInfo> getWeekPlanBasicInfoList() {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.weekPlanBasicInfo_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public int getWeekPlanBasicInfoCount() {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.weekPlanBasicInfo_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public WeekPlanBasicInfo getWeekPlanBasicInfo(int i) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.weekPlanBasicInfo_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setWeekPlanBasicInfo(int i, WeekPlanBasicInfo weekPlanBasicInfo) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    weekPlanBasicInfo.getClass();
                    ensureWeekPlanBasicInfoIsMutable();
                    this.weekPlanBasicInfo_.set(i, weekPlanBasicInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, weekPlanBasicInfo);
                }
                return this;
            }

            public Builder setWeekPlanBasicInfo(int i, WeekPlanBasicInfo.Builder builder) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureWeekPlanBasicInfoIsMutable();
                    this.weekPlanBasicInfo_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addWeekPlanBasicInfo(WeekPlanBasicInfo weekPlanBasicInfo) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    weekPlanBasicInfo.getClass();
                    ensureWeekPlanBasicInfoIsMutable();
                    this.weekPlanBasicInfo_.add(weekPlanBasicInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(weekPlanBasicInfo);
                }
                return this;
            }

            public Builder addWeekPlanBasicInfo(int i, WeekPlanBasicInfo weekPlanBasicInfo) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    weekPlanBasicInfo.getClass();
                    ensureWeekPlanBasicInfoIsMutable();
                    this.weekPlanBasicInfo_.add(i, weekPlanBasicInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, weekPlanBasicInfo);
                }
                return this;
            }

            public Builder addWeekPlanBasicInfo(WeekPlanBasicInfo.Builder builder) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureWeekPlanBasicInfoIsMutable();
                    this.weekPlanBasicInfo_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addWeekPlanBasicInfo(int i, WeekPlanBasicInfo.Builder builder) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureWeekPlanBasicInfoIsMutable();
                    this.weekPlanBasicInfo_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllWeekPlanBasicInfo(Iterable<? extends WeekPlanBasicInfo> iterable) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureWeekPlanBasicInfoIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.weekPlanBasicInfo_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearWeekPlanBasicInfo() {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.weekPlanBasicInfo_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeWeekPlanBasicInfo(int i) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureWeekPlanBasicInfoIsMutable();
                    this.weekPlanBasicInfo_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public WeekPlanBasicInfo.Builder getWeekPlanBasicInfoBuilder(int i) {
                return getWeekPlanBasicInfoFieldBuilder().getBuilder(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public WeekPlanBasicInfoOrBuilder getWeekPlanBasicInfoOrBuilder(int i) {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.weekPlanBasicInfo_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public List<? extends WeekPlanBasicInfoOrBuilder> getWeekPlanBasicInfoOrBuilderList() {
                RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.weekPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.weekPlanBasicInfo_);
            }

            public WeekPlanBasicInfo.Builder addWeekPlanBasicInfoBuilder() {
                return getWeekPlanBasicInfoFieldBuilder().addBuilder(WeekPlanBasicInfo.getDefaultInstance());
            }

            public WeekPlanBasicInfo.Builder addWeekPlanBasicInfoBuilder(int i) {
                return getWeekPlanBasicInfoFieldBuilder().addBuilder(i, WeekPlanBasicInfo.getDefaultInstance());
            }

            public List<WeekPlanBasicInfo.Builder> getWeekPlanBasicInfoBuilderList() {
                return getWeekPlanBasicInfoFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<WeekPlanBasicInfo, WeekPlanBasicInfo.Builder, WeekPlanBasicInfoOrBuilder> getWeekPlanBasicInfoFieldBuilder() {
                if (this.weekPlanBasicInfoBuilder_ == null) {
                    this.weekPlanBasicInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.weekPlanBasicInfo_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                    this.weekPlanBasicInfo_ = null;
                }
                return this.weekPlanBasicInfoBuilder_;
            }

            private void ensureDayPlanBasicInfoIsMutable() {
                if ((this.bitField0_ & 4) == 0) {
                    this.dayPlanBasicInfo_ = new ArrayList(this.dayPlanBasicInfo_);
                    this.bitField0_ |= 4;
                }
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public List<DayPlanBasicInfo> getDayPlanBasicInfoList() {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.dayPlanBasicInfo_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public int getDayPlanBasicInfoCount() {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dayPlanBasicInfo_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public DayPlanBasicInfo getDayPlanBasicInfo(int i) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dayPlanBasicInfo_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setDayPlanBasicInfo(int i, DayPlanBasicInfo dayPlanBasicInfo) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dayPlanBasicInfo.getClass();
                    ensureDayPlanBasicInfoIsMutable();
                    this.dayPlanBasicInfo_.set(i, dayPlanBasicInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, dayPlanBasicInfo);
                }
                return this;
            }

            public Builder setDayPlanBasicInfo(int i, DayPlanBasicInfo.Builder builder) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDayPlanBasicInfoIsMutable();
                    this.dayPlanBasicInfo_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addDayPlanBasicInfo(DayPlanBasicInfo dayPlanBasicInfo) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dayPlanBasicInfo.getClass();
                    ensureDayPlanBasicInfoIsMutable();
                    this.dayPlanBasicInfo_.add(dayPlanBasicInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(dayPlanBasicInfo);
                }
                return this;
            }

            public Builder addDayPlanBasicInfo(int i, DayPlanBasicInfo dayPlanBasicInfo) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dayPlanBasicInfo.getClass();
                    ensureDayPlanBasicInfoIsMutable();
                    this.dayPlanBasicInfo_.add(i, dayPlanBasicInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, dayPlanBasicInfo);
                }
                return this;
            }

            public Builder addDayPlanBasicInfo(DayPlanBasicInfo.Builder builder) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDayPlanBasicInfoIsMutable();
                    this.dayPlanBasicInfo_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addDayPlanBasicInfo(int i, DayPlanBasicInfo.Builder builder) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDayPlanBasicInfoIsMutable();
                    this.dayPlanBasicInfo_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllDayPlanBasicInfo(Iterable<? extends DayPlanBasicInfo> iterable) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDayPlanBasicInfoIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.dayPlanBasicInfo_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearDayPlanBasicInfo() {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dayPlanBasicInfo_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeDayPlanBasicInfo(int i) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDayPlanBasicInfoIsMutable();
                    this.dayPlanBasicInfo_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public DayPlanBasicInfo.Builder getDayPlanBasicInfoBuilder(int i) {
                return getDayPlanBasicInfoFieldBuilder().getBuilder(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public DayPlanBasicInfoOrBuilder getDayPlanBasicInfoOrBuilder(int i) {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dayPlanBasicInfo_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanInfoOrBuilder
            public List<? extends DayPlanBasicInfoOrBuilder> getDayPlanBasicInfoOrBuilderList() {
                RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> repeatedFieldBuilderV3 = this.dayPlanBasicInfoBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.dayPlanBasicInfo_);
            }

            public DayPlanBasicInfo.Builder addDayPlanBasicInfoBuilder() {
                return getDayPlanBasicInfoFieldBuilder().addBuilder(DayPlanBasicInfo.getDefaultInstance());
            }

            public DayPlanBasicInfo.Builder addDayPlanBasicInfoBuilder(int i) {
                return getDayPlanBasicInfoFieldBuilder().addBuilder(i, DayPlanBasicInfo.getDefaultInstance());
            }

            public List<DayPlanBasicInfo.Builder> getDayPlanBasicInfoBuilderList() {
                return getDayPlanBasicInfoFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<DayPlanBasicInfo, DayPlanBasicInfo.Builder, DayPlanBasicInfoOrBuilder> getDayPlanBasicInfoFieldBuilder() {
                if (this.dayPlanBasicInfoBuilder_ == null) {
                    this.dayPlanBasicInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.dayPlanBasicInfo_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                    this.dayPlanBasicInfo_ = null;
                }
                return this.dayPlanBasicInfoBuilder_;
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

        public static PlanInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PlanInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<PlanInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PlanInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class PlanShakeHandsInfo extends GeneratedMessageV3 implements PlanShakeHandsInfoOrBuilder {
        public static final int LANGUAGE_FIELD_NUMBER = 2;
        public static final int PLANID_FIELD_NUMBER = 1;
        public static final int PUNCHTIMESTAMP_FIELD_NUMBER = 4;
        public static final int WEEKSTARTTIME_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private volatile Object language_;
        private byte memoizedIsInitialized;
        private volatile Object planId_;
        private int punchTimeStamp_;
        private int weekStartTime_;
        private static final PlanShakeHandsInfo DEFAULT_INSTANCE = new PlanShakeHandsInfo();

        @Deprecated
        public static final Parser<PlanShakeHandsInfo> PARSER = new AbstractParser<PlanShakeHandsInfo>() { // from class: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfo.1
            @Override // com.google.protobuf.Parser
            public PlanShakeHandsInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PlanShakeHandsInfo(codedInputStream, extensionRegistryLite);
            }
        };

        private PlanShakeHandsInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private PlanShakeHandsInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.planId_ = "";
            this.language_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new PlanShakeHandsInfo();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PlanShakeHandsInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.planId_ = readBytes;
                                } else if (readTag == 18) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.language_ = readBytes2;
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.weekStartTime_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 8;
                                    this.punchTimeStamp_ = codedInputStream.readUInt32();
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
            return PlanDataForDevice.internal_static_PlanShakeHandsInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PlanDataForDevice.internal_static_PlanShakeHandsInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanShakeHandsInfo.class, Builder.class);
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public boolean hasPlanId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public String getPlanId() {
            Object obj = this.planId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.planId_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public ByteString getPlanIdBytes() {
            Object obj = this.planId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.planId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public boolean hasLanguage() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public String getLanguage() {
            Object obj = this.language_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.language_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public ByteString getLanguageBytes() {
            Object obj = this.language_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.language_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public boolean hasWeekStartTime() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public int getWeekStartTime() {
            return this.weekStartTime_;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public boolean hasPunchTimeStamp() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
        public int getPunchTimeStamp() {
            return this.punchTimeStamp_;
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
            if (!hasPlanId()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasLanguage()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasWeekStartTime()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasPunchTimeStamp()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.planId_);
            }
            if ((this.bitField0_ & 2) != 0) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.language_);
            }
            if ((this.bitField0_ & 4) != 0) {
                codedOutputStream.writeUInt32(3, this.weekStartTime_);
            }
            if ((this.bitField0_ & 8) != 0) {
                codedOutputStream.writeUInt32(4, this.punchTimeStamp_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) != 0 ? GeneratedMessageV3.computeStringSize(1, this.planId_) : 0;
            if ((this.bitField0_ & 2) != 0) {
                computeStringSize += GeneratedMessageV3.computeStringSize(2, this.language_);
            }
            if ((this.bitField0_ & 4) != 0) {
                computeStringSize += CodedOutputStream.computeUInt32Size(3, this.weekStartTime_);
            }
            if ((this.bitField0_ & 8) != 0) {
                computeStringSize += CodedOutputStream.computeUInt32Size(4, this.punchTimeStamp_);
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
            if (!(obj instanceof PlanShakeHandsInfo)) {
                return super.equals(obj);
            }
            PlanShakeHandsInfo planShakeHandsInfo = (PlanShakeHandsInfo) obj;
            if (hasPlanId() != planShakeHandsInfo.hasPlanId()) {
                return false;
            }
            if ((hasPlanId() && !getPlanId().equals(planShakeHandsInfo.getPlanId())) || hasLanguage() != planShakeHandsInfo.hasLanguage()) {
                return false;
            }
            if ((hasLanguage() && !getLanguage().equals(planShakeHandsInfo.getLanguage())) || hasWeekStartTime() != planShakeHandsInfo.hasWeekStartTime()) {
                return false;
            }
            if ((!hasWeekStartTime() || getWeekStartTime() == planShakeHandsInfo.getWeekStartTime()) && hasPunchTimeStamp() == planShakeHandsInfo.hasPunchTimeStamp()) {
                return (!hasPunchTimeStamp() || getPunchTimeStamp() == planShakeHandsInfo.getPunchTimeStamp()) && this.unknownFields.equals(planShakeHandsInfo.unknownFields);
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasPlanId()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getPlanId().hashCode();
            }
            if (hasLanguage()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getLanguage().hashCode();
            }
            if (hasWeekStartTime()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getWeekStartTime();
            }
            if (hasPunchTimeStamp()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getPunchTimeStamp();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static PlanShakeHandsInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PlanShakeHandsInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PlanShakeHandsInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PlanShakeHandsInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PlanShakeHandsInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PlanShakeHandsInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PlanShakeHandsInfo parseFrom(InputStream inputStream) throws IOException {
            return (PlanShakeHandsInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PlanShakeHandsInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlanShakeHandsInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PlanShakeHandsInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PlanShakeHandsInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PlanShakeHandsInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlanShakeHandsInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PlanShakeHandsInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PlanShakeHandsInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PlanShakeHandsInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PlanShakeHandsInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PlanShakeHandsInfo planShakeHandsInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(planShakeHandsInfo);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PlanShakeHandsInfoOrBuilder {
            private int bitField0_;
            private Object language_;
            private Object planId_;
            private int punchTimeStamp_;
            private int weekStartTime_;

            public static final Descriptors.Descriptor getDescriptor() {
                return PlanDataForDevice.internal_static_PlanShakeHandsInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PlanDataForDevice.internal_static_PlanShakeHandsInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanShakeHandsInfo.class, Builder.class);
            }

            private Builder() {
                this.planId_ = "";
                this.language_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.planId_ = "";
                this.language_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PlanShakeHandsInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.planId_ = "";
                int i = this.bitField0_;
                this.language_ = "";
                this.weekStartTime_ = 0;
                this.punchTimeStamp_ = 0;
                this.bitField0_ = i & (-16);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return PlanDataForDevice.internal_static_PlanShakeHandsInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PlanShakeHandsInfo getDefaultInstanceForType() {
                return PlanShakeHandsInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PlanShakeHandsInfo build() {
                PlanShakeHandsInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PlanShakeHandsInfo buildPartial() {
                PlanShakeHandsInfo planShakeHandsInfo = new PlanShakeHandsInfo(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 0 ? 1 : 0;
                planShakeHandsInfo.planId_ = this.planId_;
                if ((i & 2) != 0) {
                    i2 |= 2;
                }
                planShakeHandsInfo.language_ = this.language_;
                if ((i & 4) != 0) {
                    planShakeHandsInfo.weekStartTime_ = this.weekStartTime_;
                    i2 |= 4;
                }
                if ((i & 8) != 0) {
                    planShakeHandsInfo.punchTimeStamp_ = this.punchTimeStamp_;
                    i2 |= 8;
                }
                planShakeHandsInfo.bitField0_ = i2;
                onBuilt();
                return planShakeHandsInfo;
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
                if (message instanceof PlanShakeHandsInfo) {
                    return mergeFrom((PlanShakeHandsInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PlanShakeHandsInfo planShakeHandsInfo) {
                if (planShakeHandsInfo == PlanShakeHandsInfo.getDefaultInstance()) {
                    return this;
                }
                if (planShakeHandsInfo.hasPlanId()) {
                    this.bitField0_ |= 1;
                    this.planId_ = planShakeHandsInfo.planId_;
                    onChanged();
                }
                if (planShakeHandsInfo.hasLanguage()) {
                    this.bitField0_ |= 2;
                    this.language_ = planShakeHandsInfo.language_;
                    onChanged();
                }
                if (planShakeHandsInfo.hasWeekStartTime()) {
                    setWeekStartTime(planShakeHandsInfo.getWeekStartTime());
                }
                if (planShakeHandsInfo.hasPunchTimeStamp()) {
                    setPunchTimeStamp(planShakeHandsInfo.getPunchTimeStamp());
                }
                mergeUnknownFields(planShakeHandsInfo.unknownFields);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasPlanId() && hasLanguage() && hasWeekStartTime() && hasPunchTimeStamp();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanShakeHandsInfo> r0 = com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanShakeHandsInfo r2 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                    com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanShakeHandsInfo r3 = (com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfo) r3     // Catch: java.lang.Throwable -> Le
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
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.PlanDataForDevice$PlanShakeHandsInfo$Builder");
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public boolean hasPlanId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public String getPlanId() {
                Object obj = this.planId_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.planId_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public ByteString getPlanIdBytes() {
                Object obj = this.planId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.planId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setPlanId(String str) {
                str.getClass();
                this.bitField0_ |= 1;
                this.planId_ = str;
                onChanged();
                return this;
            }

            public Builder clearPlanId() {
                this.bitField0_ &= -2;
                this.planId_ = PlanShakeHandsInfo.getDefaultInstance().getPlanId();
                onChanged();
                return this;
            }

            public Builder setPlanIdBytes(ByteString byteString) {
                byteString.getClass();
                this.bitField0_ |= 1;
                this.planId_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public boolean hasLanguage() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public String getLanguage() {
                Object obj = this.language_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.language_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public ByteString getLanguageBytes() {
                Object obj = this.language_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.language_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setLanguage(String str) {
                str.getClass();
                this.bitField0_ |= 2;
                this.language_ = str;
                onChanged();
                return this;
            }

            public Builder clearLanguage() {
                this.bitField0_ &= -3;
                this.language_ = PlanShakeHandsInfo.getDefaultInstance().getLanguage();
                onChanged();
                return this;
            }

            public Builder setLanguageBytes(ByteString byteString) {
                byteString.getClass();
                this.bitField0_ |= 2;
                this.language_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public boolean hasWeekStartTime() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public int getWeekStartTime() {
                return this.weekStartTime_;
            }

            public Builder setWeekStartTime(int i) {
                this.bitField0_ |= 4;
                this.weekStartTime_ = i;
                onChanged();
                return this;
            }

            public Builder clearWeekStartTime() {
                this.bitField0_ &= -5;
                this.weekStartTime_ = 0;
                onChanged();
                return this;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public boolean hasPunchTimeStamp() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.PlanDataForDevice.PlanShakeHandsInfoOrBuilder
            public int getPunchTimeStamp() {
                return this.punchTimeStamp_;
            }

            public Builder setPunchTimeStamp(int i) {
                this.bitField0_ |= 8;
                this.punchTimeStamp_ = i;
                onChanged();
                return this;
            }

            public Builder clearPunchTimeStamp() {
                this.bitField0_ &= -9;
                this.punchTimeStamp_ = 0;
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

        public static PlanShakeHandsInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PlanShakeHandsInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<PlanShakeHandsInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PlanShakeHandsInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_PlanInfo_descriptor = descriptor2;
        internal_static_PlanInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"PlanBasicInfo", "WeekPlanBasicInfo", "DayPlanBasicInfo"});
        Descriptors.Descriptor descriptor3 = descriptor2.getNestedTypes().get(0);
        internal_static_PlanInfo_PlanBasicInfo_descriptor = descriptor3;
        internal_static_PlanInfo_PlanBasicInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"PlanId", "PlanName", "LanguageType", "PunchTimeStamp", "PlanTotalDays", "PlanTotalWeeks", "PlanStartTime", "ZoneOffset"});
        Descriptors.Descriptor descriptor4 = descriptor2.getNestedTypes().get(1);
        internal_static_PlanInfo_WeekPlanBasicInfo_descriptor = descriptor4;
        internal_static_PlanInfo_WeekPlanBasicInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"WeekPlanName", "ExerciseTimes", "CurrentWeekDays", "WeekNum", "WeekPlanStartTime", "WeekPlanEndTime"});
        Descriptors.Descriptor descriptor5 = descriptor2.getNestedTypes().get(2);
        internal_static_PlanInfo_CourseOutLine_descriptor = descriptor5;
        internal_static_PlanInfo_CourseOutLine_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"CourseId", "CourseName", "UpdateTime", "CourseType", "IsCoursePunch"});
        Descriptors.Descriptor descriptor6 = descriptor2.getNestedTypes().get(3);
        internal_static_PlanInfo_DayPlanBasicInfo_descriptor = descriptor6;
        internal_static_PlanInfo_DayPlanBasicInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Time", "CourseNum", "IsDayPunch", "DayPlanCourse"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(1);
        internal_static_PlanShakeHandsInfo_descriptor = descriptor7;
        internal_static_PlanShakeHandsInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"PlanId", "Language", "WeekStartTime", "PunchTimeStamp"});
    }
}
