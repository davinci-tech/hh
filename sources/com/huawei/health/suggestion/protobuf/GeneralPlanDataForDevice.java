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
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class GeneralPlanDataForDevice {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0015generalPlanInfo.proto\"Â\t\n\u000fGeneralPlanInfo\u0012,\n\rplanBasicInfo\u0018\u0001 \u0002(\u000b2\u0015.GeneralPlanInfo.Plan\u001aÖ\u0001\n\u0004Plan\u0012\u000e\n\u0006planId\u0018\u0001 \u0002(\t\u0012\u0010\n\bcategory\u0018\u0002 \u0002(\u0005\u0012\u0013\n\u000bsubCategory\u0018\u0003 \u0002(\u0005\u0012\u000e\n\u0006status\u0018\u0004 \u0002(\u0005\u0012\u0010\n\btimeZone\u0018\u0005 \u0001(\t\u0012\u0011\n\tstartTime\u0018\u0006 \u0001(\u0003\u0012\u000f\n\u0007endTime\u0018\u0007 \u0001(\u0003\u0012+\n\bmetadata\u0018\b \u0001(\u000b2\u0019.GeneralPlanInfo.Metadata\u0012$\n\u0005tasks\u0018\t \u0003(\u000b2\u0015.GeneralPlanInfo.Task\u001a\u0080\u0001\n\u0004Task\u0012\f\n\u0004name\u0018\u0001 \u0003(\r\u0012\f\n\u0004desc\u0018\u0002 \u0001(\t\u0012\f\n\u0004type\u0018\u0003 \u0002(\u0005\u0012\u0010\n\btimeZone\u0018\u0004 \u0001(\t\u0012\u000e\n\u0006status\u0018\u0005 \u0001(\u0005\u0012,\n\tschedules\u0018\u0006 \u0003(\u000b2\u0019.GeneralPlanInfo.Schedule\u001aä\u0001\n\bSchedule\u0012\u0012\n\nallDayType\u0018\u0001 \u0002(\b\u0012\u000f\n\u0007dtStart\u0018\u0002 \u0002(\u0003\u0012\r\n\u0005dtEnd\u0018\u0003 \u0002(\u0003\u0012+\n\bmetadata\u0018\u0004 \u0001(\u000b2\u0019.GeneralPlanInfo.Metadata\u00126\n\u000eobjectiveInfos\u0018\u0005 \u0003(\u000b2\u001e.GeneralPlanInfo.ObjectiveInfo\u0012\u0019\n\u0011objectiveRelation\u0018\u0006 \u0001(\u0005\u0012\u0012\n\nrecurrence\u0018\u0007 \u0001(\t\u0012\u0010\n\btimeZone\u0018\b \u0002(\t\u001aK\n\rObjectiveInfo\u0012\u0015\n\robjectiveType\u0018\u0001 \u0001(\u0005\u0012\u0011\n\tfieldName\u0018\u0002 \u0001(\t\u0012\u0010\n\bdataType\u0018\u0003 \u0001(\t\u001as\n\bMetadata\u00122\n\tcourseMsg\u0018\u0001 \u0001(\u000b2\u001f.GeneralPlanInfo.CourseMetadata\u00123\n\fplanMetadata\u0018\u0002 \u0001(\u000b2\u001d.GeneralPlanInfo.PlanMetadata\u001a°\u0001\n\u000eCourseMetadata\u0012\u0010\n\bdayOrder\u0018\u0001 \u0002(\r\u0012\u0013\n\u000bcourseIndex\u0018\u0002 \u0001(\r\u0012\u0012\n\nisDayPunch\u0018\u0003 \u0002(\r\u0012\u0012\n\ncourseType\u0018\u0004 \u0002(\r\u0012\u0012\n\nupdateTime\u0018\u0005 \u0002(\r\u0012\u0015\n\risCoursePunch\u0018\u0006 \u0002(\r\u0012\u0010\n\bcourseId\u0018\u0007 \u0002(\t\u0012\u0012\n\ncourseName\u0018\b \u0003(\r\u001aÈ\u0001\n\fPlanMetadata\u0012\u0015\n\rplanTotalDays\u0018\u0001 \u0002(\r\u0012\u0016\n\u000eplanTotalWeeks\u0018\u0002 \u0002(\r\u0012\u0014\n\fweekPlanName\u0018\u0003 \u0003(\r\u0012\u0015\n\rexerciseTimes\u0018\u0004 \u0002(\r\u0012\u0017\n\u000fcurrentWeekDays\u0018\u0005 \u0002(\r\u0012\u000f\n\u0007weekNum\u0018\u0006 \u0002(\r\u0012\u0019\n\u0011weekPlanStartTime\u0018\u0007 \u0002(\r\u0012\u0017\n\u000fweekPlanEndTime\u0018\b \u0002(\rBA\n%com.huawei.health.suggestion.protobufB\u0018GeneralPlanDataForDevice"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_CourseMetadata_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_CourseMetadata_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_Metadata_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_Metadata_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_ObjectiveInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_ObjectiveInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_PlanMetadata_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_PlanMetadata_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_Plan_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_Plan_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_Schedule_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_Schedule_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_Task_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_Task_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_GeneralPlanInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_GeneralPlanInfo_fieldAccessorTable;

    public interface GeneralPlanInfoOrBuilder extends MessageOrBuilder {
        GeneralPlanInfo.Plan getPlanBasicInfo();

        GeneralPlanInfo.PlanOrBuilder getPlanBasicInfoOrBuilder();

        boolean hasPlanBasicInfo();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private GeneralPlanDataForDevice() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static final class GeneralPlanInfo extends GeneratedMessageV3 implements GeneralPlanInfoOrBuilder {
        private static final GeneralPlanInfo DEFAULT_INSTANCE = new GeneralPlanInfo();

        @Deprecated
        public static final Parser<GeneralPlanInfo> PARSER = new AbstractParser<GeneralPlanInfo>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.1
            @Override // com.google.protobuf.Parser
            public GeneralPlanInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GeneralPlanInfo(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PLANBASICINFO_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private Plan planBasicInfo_;

        public interface CourseMetadataOrBuilder extends MessageOrBuilder {
            String getCourseId();

            ByteString getCourseIdBytes();

            int getCourseIndex();

            int getCourseName(int i);

            int getCourseNameCount();

            List<Integer> getCourseNameList();

            int getCourseType();

            int getDayOrder();

            int getIsCoursePunch();

            int getIsDayPunch();

            int getUpdateTime();

            boolean hasCourseId();

            boolean hasCourseIndex();

            boolean hasCourseType();

            boolean hasDayOrder();

            boolean hasIsCoursePunch();

            boolean hasIsDayPunch();

            boolean hasUpdateTime();
        }

        public interface MetadataOrBuilder extends MessageOrBuilder {
            CourseMetadata getCourseMsg();

            CourseMetadataOrBuilder getCourseMsgOrBuilder();

            PlanMetadata getPlanMetadata();

            PlanMetadataOrBuilder getPlanMetadataOrBuilder();

            boolean hasCourseMsg();

            boolean hasPlanMetadata();
        }

        public interface ObjectiveInfoOrBuilder extends MessageOrBuilder {
            String getDataType();

            ByteString getDataTypeBytes();

            String getFieldName();

            ByteString getFieldNameBytes();

            int getObjectiveType();

            boolean hasDataType();

            boolean hasFieldName();

            boolean hasObjectiveType();
        }

        public interface PlanMetadataOrBuilder extends MessageOrBuilder {
            int getCurrentWeekDays();

            int getExerciseTimes();

            int getPlanTotalDays();

            int getPlanTotalWeeks();

            int getWeekNum();

            int getWeekPlanEndTime();

            int getWeekPlanName(int i);

            int getWeekPlanNameCount();

            List<Integer> getWeekPlanNameList();

            int getWeekPlanStartTime();

            boolean hasCurrentWeekDays();

            boolean hasExerciseTimes();

            boolean hasPlanTotalDays();

            boolean hasPlanTotalWeeks();

            boolean hasWeekNum();

            boolean hasWeekPlanEndTime();

            boolean hasWeekPlanStartTime();
        }

        public interface PlanOrBuilder extends MessageOrBuilder {
            int getCategory();

            long getEndTime();

            Metadata getMetadata();

            MetadataOrBuilder getMetadataOrBuilder();

            String getPlanId();

            ByteString getPlanIdBytes();

            long getStartTime();

            int getStatus();

            int getSubCategory();

            Task getTasks(int i);

            int getTasksCount();

            List<Task> getTasksList();

            TaskOrBuilder getTasksOrBuilder(int i);

            List<? extends TaskOrBuilder> getTasksOrBuilderList();

            String getTimeZone();

            ByteString getTimeZoneBytes();

            boolean hasCategory();

            boolean hasEndTime();

            boolean hasMetadata();

            boolean hasPlanId();

            boolean hasStartTime();

            boolean hasStatus();

            boolean hasSubCategory();

            boolean hasTimeZone();
        }

        public interface ScheduleOrBuilder extends MessageOrBuilder {
            boolean getAllDayType();

            long getDtEnd();

            long getDtStart();

            Metadata getMetadata();

            MetadataOrBuilder getMetadataOrBuilder();

            ObjectiveInfo getObjectiveInfos(int i);

            int getObjectiveInfosCount();

            List<ObjectiveInfo> getObjectiveInfosList();

            ObjectiveInfoOrBuilder getObjectiveInfosOrBuilder(int i);

            List<? extends ObjectiveInfoOrBuilder> getObjectiveInfosOrBuilderList();

            int getObjectiveRelation();

            String getRecurrence();

            ByteString getRecurrenceBytes();

            String getTimeZone();

            ByteString getTimeZoneBytes();

            boolean hasAllDayType();

            boolean hasDtEnd();

            boolean hasDtStart();

            boolean hasMetadata();

            boolean hasObjectiveRelation();

            boolean hasRecurrence();

            boolean hasTimeZone();
        }

        public interface TaskOrBuilder extends MessageOrBuilder {
            String getDesc();

            ByteString getDescBytes();

            int getName(int i);

            int getNameCount();

            List<Integer> getNameList();

            Schedule getSchedules(int i);

            int getSchedulesCount();

            List<Schedule> getSchedulesList();

            ScheduleOrBuilder getSchedulesOrBuilder(int i);

            List<? extends ScheduleOrBuilder> getSchedulesOrBuilderList();

            int getStatus();

            String getTimeZone();

            ByteString getTimeZoneBytes();

            int getType();

            boolean hasDesc();

            boolean hasStatus();

            boolean hasTimeZone();

            boolean hasType();
        }

        private GeneralPlanInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GeneralPlanInfo() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new GeneralPlanInfo();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private GeneralPlanInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                Plan.Builder builder = (this.bitField0_ & 1) != 0 ? this.planBasicInfo_.toBuilder() : null;
                                Plan plan = (Plan) codedInputStream.readMessage(Plan.PARSER, extensionRegistryLite);
                                this.planBasicInfo_ = plan;
                                if (builder != null) {
                                    builder.mergeFrom(plan);
                                    this.planBasicInfo_ = builder.buildPartial();
                                }
                                this.bitField0_ |= 1;
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
            return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(GeneralPlanInfo.class, Builder.class);
        }

        public static final class Plan extends GeneratedMessageV3 implements PlanOrBuilder {
            public static final int CATEGORY_FIELD_NUMBER = 2;
            public static final int ENDTIME_FIELD_NUMBER = 7;
            public static final int METADATA_FIELD_NUMBER = 8;
            public static final int PLANID_FIELD_NUMBER = 1;
            public static final int STARTTIME_FIELD_NUMBER = 6;
            public static final int STATUS_FIELD_NUMBER = 4;
            public static final int SUBCATEGORY_FIELD_NUMBER = 3;
            public static final int TASKS_FIELD_NUMBER = 9;
            public static final int TIMEZONE_FIELD_NUMBER = 5;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private int category_;
            private long endTime_;
            private byte memoizedIsInitialized;
            private Metadata metadata_;
            private volatile Object planId_;
            private long startTime_;
            private int status_;
            private int subCategory_;
            private List<Task> tasks_;
            private volatile Object timeZone_;
            private static final Plan DEFAULT_INSTANCE = new Plan();

            @Deprecated
            public static final Parser<Plan> PARSER = new AbstractParser<Plan>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Plan.1
                @Override // com.google.protobuf.Parser
                public Plan parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Plan(codedInputStream, extensionRegistryLite);
                }
            };

            private Plan(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Plan() {
                this.memoizedIsInitialized = (byte) -1;
                this.planId_ = "";
                this.timeZone_ = "";
                this.tasks_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new Plan();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Plan(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.planId_ = readBytes;
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.category_ = codedInputStream.readInt32();
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.subCategory_ = codedInputStream.readInt32();
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 8;
                                    this.status_ = codedInputStream.readInt32();
                                } else if (readTag == 42) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 16;
                                    this.timeZone_ = readBytes2;
                                } else if (readTag == 48) {
                                    this.bitField0_ |= 32;
                                    this.startTime_ = codedInputStream.readInt64();
                                } else if (readTag == 56) {
                                    this.bitField0_ |= 64;
                                    this.endTime_ = codedInputStream.readInt64();
                                } else if (readTag == 66) {
                                    Metadata.Builder builder = (this.bitField0_ & 128) != 0 ? this.metadata_.toBuilder() : null;
                                    Metadata metadata = (Metadata) codedInputStream.readMessage(Metadata.PARSER, extensionRegistryLite);
                                    this.metadata_ = metadata;
                                    if (builder != null) {
                                        builder.mergeFrom(metadata);
                                        this.metadata_ = builder.buildPartial();
                                    }
                                    this.bitField0_ |= 128;
                                } else if (readTag == 74) {
                                    if ((c & 256) == 0) {
                                        this.tasks_ = new ArrayList();
                                        c = 256;
                                    }
                                    this.tasks_.add((Task) codedInputStream.readMessage(Task.PARSER, extensionRegistryLite));
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
                        if ((c & 256) != 0) {
                            this.tasks_ = Collections.unmodifiableList(this.tasks_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Plan_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Plan_fieldAccessorTable.ensureFieldAccessorsInitialized(Plan.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasPlanId() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
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

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public ByteString getPlanIdBytes() {
                Object obj = this.planId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.planId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasCategory() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public int getCategory() {
                return this.category_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasSubCategory() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public int getSubCategory() {
                return this.subCategory_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public int getStatus() {
                return this.status_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasTimeZone() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public String getTimeZone() {
                Object obj = this.timeZone_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.timeZone_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public ByteString getTimeZoneBytes() {
                Object obj = this.timeZone_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeZone_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasStartTime() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public long getStartTime() {
                return this.startTime_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasEndTime() {
                return (this.bitField0_ & 64) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public long getEndTime() {
                return this.endTime_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public boolean hasMetadata() {
                return (this.bitField0_ & 128) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public Metadata getMetadata() {
                Metadata metadata = this.metadata_;
                return metadata == null ? Metadata.getDefaultInstance() : metadata;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public MetadataOrBuilder getMetadataOrBuilder() {
                Metadata metadata = this.metadata_;
                return metadata == null ? Metadata.getDefaultInstance() : metadata;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public List<Task> getTasksList() {
                return this.tasks_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public List<? extends TaskOrBuilder> getTasksOrBuilderList() {
                return this.tasks_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public int getTasksCount() {
                return this.tasks_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public Task getTasks(int i) {
                return this.tasks_.get(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
            public TaskOrBuilder getTasksOrBuilder(int i) {
                return this.tasks_.get(i);
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
                if (!hasCategory()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasSubCategory()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasStatus()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (hasMetadata() && !getMetadata().isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                for (int i = 0; i < getTasksCount(); i++) {
                    if (!getTasks(i).isInitialized()) {
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
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.planId_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeInt32(2, this.category_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeInt32(3, this.subCategory_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeInt32(4, this.status_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 5, this.timeZone_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    codedOutputStream.writeInt64(6, this.startTime_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    codedOutputStream.writeInt64(7, this.endTime_);
                }
                if ((this.bitField0_ & 128) != 0) {
                    codedOutputStream.writeMessage(8, getMetadata());
                }
                for (int i = 0; i < this.tasks_.size(); i++) {
                    codedOutputStream.writeMessage(9, this.tasks_.get(i));
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
                    computeStringSize += CodedOutputStream.computeInt32Size(2, this.category_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    computeStringSize += CodedOutputStream.computeInt32Size(3, this.subCategory_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    computeStringSize += CodedOutputStream.computeInt32Size(4, this.status_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    computeStringSize += GeneratedMessageV3.computeStringSize(5, this.timeZone_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    computeStringSize += CodedOutputStream.computeInt64Size(6, this.startTime_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    computeStringSize += CodedOutputStream.computeInt64Size(7, this.endTime_);
                }
                if ((this.bitField0_ & 128) != 0) {
                    computeStringSize += CodedOutputStream.computeMessageSize(8, getMetadata());
                }
                for (int i2 = 0; i2 < this.tasks_.size(); i2++) {
                    computeStringSize += CodedOutputStream.computeMessageSize(9, this.tasks_.get(i2));
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
                if (!(obj instanceof Plan)) {
                    return super.equals(obj);
                }
                Plan plan = (Plan) obj;
                if (hasPlanId() != plan.hasPlanId()) {
                    return false;
                }
                if ((hasPlanId() && !getPlanId().equals(plan.getPlanId())) || hasCategory() != plan.hasCategory()) {
                    return false;
                }
                if ((hasCategory() && getCategory() != plan.getCategory()) || hasSubCategory() != plan.hasSubCategory()) {
                    return false;
                }
                if ((hasSubCategory() && getSubCategory() != plan.getSubCategory()) || hasStatus() != plan.hasStatus()) {
                    return false;
                }
                if ((hasStatus() && getStatus() != plan.getStatus()) || hasTimeZone() != plan.hasTimeZone()) {
                    return false;
                }
                if ((hasTimeZone() && !getTimeZone().equals(plan.getTimeZone())) || hasStartTime() != plan.hasStartTime()) {
                    return false;
                }
                if ((hasStartTime() && getStartTime() != plan.getStartTime()) || hasEndTime() != plan.hasEndTime()) {
                    return false;
                }
                if ((!hasEndTime() || getEndTime() == plan.getEndTime()) && hasMetadata() == plan.hasMetadata()) {
                    return (!hasMetadata() || getMetadata().equals(plan.getMetadata())) && getTasksList().equals(plan.getTasksList()) && this.unknownFields.equals(plan.unknownFields);
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
                if (hasCategory()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getCategory();
                }
                if (hasSubCategory()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getSubCategory();
                }
                if (hasStatus()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getStatus();
                }
                if (hasTimeZone()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getTimeZone().hashCode();
                }
                if (hasStartTime()) {
                    hashCode = (((hashCode * 37) + 6) * 53) + Internal.hashLong(getStartTime());
                }
                if (hasEndTime()) {
                    hashCode = (((hashCode * 37) + 7) * 53) + Internal.hashLong(getEndTime());
                }
                if (hasMetadata()) {
                    hashCode = (((hashCode * 37) + 8) * 53) + getMetadata().hashCode();
                }
                if (getTasksCount() > 0) {
                    hashCode = (((hashCode * 37) + 9) * 53) + getTasksList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static Plan parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Plan parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Plan parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Plan parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Plan parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Plan parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Plan parseFrom(InputStream inputStream) throws IOException {
                return (Plan) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Plan parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Plan) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Plan parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Plan) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Plan parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Plan) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Plan parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Plan) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Plan parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Plan) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Plan plan) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(plan);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PlanOrBuilder {
                private int bitField0_;
                private int category_;
                private long endTime_;
                private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
                private Metadata metadata_;
                private Object planId_;
                private long startTime_;
                private int status_;
                private int subCategory_;
                private RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> tasksBuilder_;
                private List<Task> tasks_;
                private Object timeZone_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Plan_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Plan_fieldAccessorTable.ensureFieldAccessorsInitialized(Plan.class, Builder.class);
                }

                private Builder() {
                    this.planId_ = "";
                    this.timeZone_ = "";
                    this.tasks_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.planId_ = "";
                    this.timeZone_ = "";
                    this.tasks_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (Plan.alwaysUseFieldBuilders) {
                        getMetadataFieldBuilder();
                        getTasksFieldBuilder();
                    }
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.planId_ = "";
                    int i = this.bitField0_;
                    this.category_ = 0;
                    this.subCategory_ = 0;
                    this.status_ = 0;
                    this.timeZone_ = "";
                    this.startTime_ = 0L;
                    this.endTime_ = 0L;
                    this.bitField0_ = i & (-128);
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.metadata_ = null;
                    } else {
                        singleFieldBuilderV3.clear();
                    }
                    this.bitField0_ &= -129;
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.tasks_ = Collections.emptyList();
                        this.bitField0_ &= -257;
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Plan_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Plan getDefaultInstanceForType() {
                    return Plan.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Plan build() {
                    Plan buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Plan buildPartial() {
                    Plan plan = new Plan(this);
                    int i = this.bitField0_;
                    int i2 = (i & 1) != 0 ? 1 : 0;
                    plan.planId_ = this.planId_;
                    if ((i & 2) != 0) {
                        plan.category_ = this.category_;
                        i2 |= 2;
                    }
                    if ((i & 4) != 0) {
                        plan.subCategory_ = this.subCategory_;
                        i2 |= 4;
                    }
                    if ((i & 8) != 0) {
                        plan.status_ = this.status_;
                        i2 |= 8;
                    }
                    if ((i & 16) != 0) {
                        i2 |= 16;
                    }
                    plan.timeZone_ = this.timeZone_;
                    if ((i & 32) != 0) {
                        plan.startTime_ = this.startTime_;
                        i2 |= 32;
                    }
                    if ((i & 64) != 0) {
                        plan.endTime_ = this.endTime_;
                        i2 |= 64;
                    }
                    if ((i & 128) != 0) {
                        SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            plan.metadata_ = this.metadata_;
                        } else {
                            plan.metadata_ = singleFieldBuilderV3.build();
                        }
                        i2 |= 128;
                    }
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        plan.tasks_ = repeatedFieldBuilderV3.build();
                    } else {
                        if ((this.bitField0_ & 256) != 0) {
                            this.tasks_ = Collections.unmodifiableList(this.tasks_);
                            this.bitField0_ &= -257;
                        }
                        plan.tasks_ = this.tasks_;
                    }
                    plan.bitField0_ = i2;
                    onBuilt();
                    return plan;
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
                    if (message instanceof Plan) {
                        return mergeFrom((Plan) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Plan plan) {
                    if (plan == Plan.getDefaultInstance()) {
                        return this;
                    }
                    if (plan.hasPlanId()) {
                        this.bitField0_ |= 1;
                        this.planId_ = plan.planId_;
                        onChanged();
                    }
                    if (plan.hasCategory()) {
                        setCategory(plan.getCategory());
                    }
                    if (plan.hasSubCategory()) {
                        setSubCategory(plan.getSubCategory());
                    }
                    if (plan.hasStatus()) {
                        setStatus(plan.getStatus());
                    }
                    if (plan.hasTimeZone()) {
                        this.bitField0_ |= 16;
                        this.timeZone_ = plan.timeZone_;
                        onChanged();
                    }
                    if (plan.hasStartTime()) {
                        setStartTime(plan.getStartTime());
                    }
                    if (plan.hasEndTime()) {
                        setEndTime(plan.getEndTime());
                    }
                    if (plan.hasMetadata()) {
                        mergeMetadata(plan.getMetadata());
                    }
                    if (this.tasksBuilder_ == null) {
                        if (!plan.tasks_.isEmpty()) {
                            if (this.tasks_.isEmpty()) {
                                this.tasks_ = plan.tasks_;
                                this.bitField0_ &= -257;
                            } else {
                                ensureTasksIsMutable();
                                this.tasks_.addAll(plan.tasks_);
                            }
                            onChanged();
                        }
                    } else if (!plan.tasks_.isEmpty()) {
                        if (!this.tasksBuilder_.isEmpty()) {
                            this.tasksBuilder_.addAllMessages(plan.tasks_);
                        } else {
                            this.tasksBuilder_.dispose();
                            this.tasksBuilder_ = null;
                            this.tasks_ = plan.tasks_;
                            this.bitField0_ &= -257;
                            this.tasksBuilder_ = Plan.alwaysUseFieldBuilders ? getTasksFieldBuilder() : null;
                        }
                    }
                    mergeUnknownFields(plan.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (!hasPlanId() || !hasCategory() || !hasSubCategory() || !hasStatus()) {
                        return false;
                    }
                    if (hasMetadata() && !getMetadata().isInitialized()) {
                        return false;
                    }
                    for (int i = 0; i < getTasksCount(); i++) {
                        if (!getTasks(i).isInitialized()) {
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
                public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Plan.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Plan> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Plan.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Plan r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Plan) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Plan r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Plan) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Plan.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Plan$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasPlanId() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
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

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
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
                    this.planId_ = Plan.getDefaultInstance().getPlanId();
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

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasCategory() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public int getCategory() {
                    return this.category_;
                }

                public Builder setCategory(int i) {
                    this.bitField0_ |= 2;
                    this.category_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearCategory() {
                    this.bitField0_ &= -3;
                    this.category_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasSubCategory() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public int getSubCategory() {
                    return this.subCategory_;
                }

                public Builder setSubCategory(int i) {
                    this.bitField0_ |= 4;
                    this.subCategory_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearSubCategory() {
                    this.bitField0_ &= -5;
                    this.subCategory_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasStatus() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public int getStatus() {
                    return this.status_;
                }

                public Builder setStatus(int i) {
                    this.bitField0_ |= 8;
                    this.status_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearStatus() {
                    this.bitField0_ &= -9;
                    this.status_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasTimeZone() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public String getTimeZone() {
                    Object obj = this.timeZone_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.timeZone_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public ByteString getTimeZoneBytes() {
                    Object obj = this.timeZone_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.timeZone_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setTimeZone(String str) {
                    str.getClass();
                    this.bitField0_ |= 16;
                    this.timeZone_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearTimeZone() {
                    this.bitField0_ &= -17;
                    this.timeZone_ = Plan.getDefaultInstance().getTimeZone();
                    onChanged();
                    return this;
                }

                public Builder setTimeZoneBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 16;
                    this.timeZone_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasStartTime() {
                    return (this.bitField0_ & 32) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public long getStartTime() {
                    return this.startTime_;
                }

                public Builder setStartTime(long j) {
                    this.bitField0_ |= 32;
                    this.startTime_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearStartTime() {
                    this.bitField0_ &= -33;
                    this.startTime_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasEndTime() {
                    return (this.bitField0_ & 64) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public long getEndTime() {
                    return this.endTime_;
                }

                public Builder setEndTime(long j) {
                    this.bitField0_ |= 64;
                    this.endTime_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearEndTime() {
                    this.bitField0_ &= -65;
                    this.endTime_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public boolean hasMetadata() {
                    return (this.bitField0_ & 128) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public Metadata getMetadata() {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        Metadata metadata = this.metadata_;
                        return metadata == null ? Metadata.getDefaultInstance() : metadata;
                    }
                    return singleFieldBuilderV3.getMessage();
                }

                public Builder setMetadata(Metadata metadata) {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        metadata.getClass();
                        this.metadata_ = metadata;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(metadata);
                    }
                    this.bitField0_ |= 128;
                    return this;
                }

                public Builder setMetadata(Metadata.Builder builder) {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.metadata_ = builder.build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.build());
                    }
                    this.bitField0_ |= 128;
                    return this;
                }

                public Builder mergeMetadata(Metadata metadata) {
                    Metadata metadata2;
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if ((this.bitField0_ & 128) != 0 && (metadata2 = this.metadata_) != null && metadata2 != Metadata.getDefaultInstance()) {
                            this.metadata_ = Metadata.newBuilder(this.metadata_).mergeFrom(metadata).buildPartial();
                        } else {
                            this.metadata_ = metadata;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(metadata);
                    }
                    this.bitField0_ |= 128;
                    return this;
                }

                public Builder clearMetadata() {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.metadata_ = null;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.clear();
                    }
                    this.bitField0_ &= -129;
                    return this;
                }

                public Metadata.Builder getMetadataBuilder() {
                    this.bitField0_ |= 128;
                    onChanged();
                    return getMetadataFieldBuilder().getBuilder();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public MetadataOrBuilder getMetadataOrBuilder() {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    Metadata metadata = this.metadata_;
                    return metadata == null ? Metadata.getDefaultInstance() : metadata;
                }

                private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> getMetadataFieldBuilder() {
                    if (this.metadataBuilder_ == null) {
                        this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                        this.metadata_ = null;
                    }
                    return this.metadataBuilder_;
                }

                private void ensureTasksIsMutable() {
                    if ((this.bitField0_ & 256) == 0) {
                        this.tasks_ = new ArrayList(this.tasks_);
                        this.bitField0_ |= 256;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public List<Task> getTasksList() {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return Collections.unmodifiableList(this.tasks_);
                    }
                    return repeatedFieldBuilderV3.getMessageList();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public int getTasksCount() {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.tasks_.size();
                    }
                    return repeatedFieldBuilderV3.getCount();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public Task getTasks(int i) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.tasks_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessage(i);
                }

                public Builder setTasks(int i, Task task) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        task.getClass();
                        ensureTasksIsMutable();
                        this.tasks_.set(i, task);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, task);
                    }
                    return this;
                }

                public Builder setTasks(int i, Task.Builder builder) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureTasksIsMutable();
                        this.tasks_.set(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addTasks(Task task) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        task.getClass();
                        ensureTasksIsMutable();
                        this.tasks_.add(task);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(task);
                    }
                    return this;
                }

                public Builder addTasks(int i, Task task) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        task.getClass();
                        ensureTasksIsMutable();
                        this.tasks_.add(i, task);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, task);
                    }
                    return this;
                }

                public Builder addTasks(Task.Builder builder) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureTasksIsMutable();
                        this.tasks_.add(builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addTasks(int i, Task.Builder builder) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureTasksIsMutable();
                        this.tasks_.add(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addAllTasks(Iterable<? extends Task> iterable) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureTasksIsMutable();
                        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.tasks_);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder clearTasks() {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.tasks_ = Collections.emptyList();
                        this.bitField0_ &= -257;
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                public Builder removeTasks(int i) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureTasksIsMutable();
                        this.tasks_.remove(i);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.remove(i);
                    }
                    return this;
                }

                public Task.Builder getTasksBuilder(int i) {
                    return getTasksFieldBuilder().getBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public TaskOrBuilder getTasksOrBuilder(int i) {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.tasks_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanOrBuilder
                public List<? extends TaskOrBuilder> getTasksOrBuilderList() {
                    RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.tasksBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        return repeatedFieldBuilderV3.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.tasks_);
                }

                public Task.Builder addTasksBuilder() {
                    return getTasksFieldBuilder().addBuilder(Task.getDefaultInstance());
                }

                public Task.Builder addTasksBuilder(int i) {
                    return getTasksFieldBuilder().addBuilder(i, Task.getDefaultInstance());
                }

                public List<Task.Builder> getTasksBuilderList() {
                    return getTasksFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> getTasksFieldBuilder() {
                    if (this.tasksBuilder_ == null) {
                        this.tasksBuilder_ = new RepeatedFieldBuilderV3<>(this.tasks_, (this.bitField0_ & 256) != 0, getParentForChildren(), isClean());
                        this.tasks_ = null;
                    }
                    return this.tasksBuilder_;
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

            public static Plan getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Plan> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<Plan> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Plan getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class Task extends GeneratedMessageV3 implements TaskOrBuilder {
            public static final int DESC_FIELD_NUMBER = 2;
            public static final int NAME_FIELD_NUMBER = 1;
            public static final int SCHEDULES_FIELD_NUMBER = 6;
            public static final int STATUS_FIELD_NUMBER = 5;
            public static final int TIMEZONE_FIELD_NUMBER = 4;
            public static final int TYPE_FIELD_NUMBER = 3;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private volatile Object desc_;
            private byte memoizedIsInitialized;
            private Internal.IntList name_;
            private List<Schedule> schedules_;
            private int status_;
            private volatile Object timeZone_;
            private int type_;
            private static final Task DEFAULT_INSTANCE = new Task();

            @Deprecated
            public static final Parser<Task> PARSER = new AbstractParser<Task>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Task.1
                @Override // com.google.protobuf.Parser
                public Task parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Task(codedInputStream, extensionRegistryLite);
                }
            };

            private Task(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Task() {
                this.memoizedIsInitialized = (byte) -1;
                this.name_ = emptyIntList();
                this.desc_ = "";
                this.timeZone_ = "";
                this.schedules_ = Collections.emptyList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new Task();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Task(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 8) {
                                    if ((i & 1) == 0) {
                                        this.name_ = newIntList();
                                        i |= 1;
                                    }
                                    this.name_.addInt(codedInputStream.readUInt32());
                                } else if (readTag == 10) {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if ((i & 1) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.name_ = newIntList();
                                        i |= 1;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.name_.addInt(codedInputStream.readUInt32());
                                    }
                                    codedInputStream.popLimit(pushLimit);
                                } else if (readTag == 18) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.desc_ = readBytes;
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 2;
                                    this.type_ = codedInputStream.readInt32();
                                } else if (readTag == 34) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 4;
                                    this.timeZone_ = readBytes2;
                                } else if (readTag == 40) {
                                    this.bitField0_ |= 8;
                                    this.status_ = codedInputStream.readInt32();
                                } else if (readTag == 50) {
                                    if ((i & 32) == 0) {
                                        this.schedules_ = new ArrayList();
                                        i |= 32;
                                    }
                                    this.schedules_.add((Schedule) codedInputStream.readMessage(Schedule.PARSER, extensionRegistryLite));
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
                        if ((i & 1) != 0) {
                            this.name_.makeImmutable();
                        }
                        if ((i & 32) != 0) {
                            this.schedules_ = Collections.unmodifiableList(this.schedules_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Task_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Task_fieldAccessorTable.ensureFieldAccessorsInitialized(Task.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public List<Integer> getNameList() {
                return this.name_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public int getNameCount() {
                return this.name_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public int getName(int i) {
                return this.name_.getInt(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public boolean hasDesc() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public String getDesc() {
                Object obj = this.desc_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.desc_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public ByteString getDescBytes() {
                Object obj = this.desc_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.desc_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public boolean hasType() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public int getType() {
                return this.type_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public boolean hasTimeZone() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public String getTimeZone() {
                Object obj = this.timeZone_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.timeZone_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public ByteString getTimeZoneBytes() {
                Object obj = this.timeZone_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeZone_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public int getStatus() {
                return this.status_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public List<Schedule> getSchedulesList() {
                return this.schedules_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public List<? extends ScheduleOrBuilder> getSchedulesOrBuilderList() {
                return this.schedules_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public int getSchedulesCount() {
                return this.schedules_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public Schedule getSchedules(int i) {
                return this.schedules_.get(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
            public ScheduleOrBuilder getSchedulesOrBuilder(int i) {
                return this.schedules_.get(i);
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
                if (!hasType()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                for (int i = 0; i < getSchedulesCount(); i++) {
                    if (!getSchedules(i).isInitialized()) {
                        this.memoizedIsInitialized = (byte) 0;
                        return false;
                    }
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                for (int i = 0; i < this.name_.size(); i++) {
                    codedOutputStream.writeUInt32(1, this.name_.getInt(i));
                }
                if ((this.bitField0_ & 1) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.desc_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeInt32(3, this.type_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 4, this.timeZone_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeInt32(5, this.status_);
                }
                for (int i2 = 0; i2 < this.schedules_.size(); i2++) {
                    codedOutputStream.writeMessage(6, this.schedules_.get(i2));
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
                for (int i3 = 0; i3 < this.name_.size(); i3++) {
                    i2 += CodedOutputStream.computeUInt32SizeNoTag(this.name_.getInt(i3));
                }
                int size = i2 + getNameList().size();
                if ((this.bitField0_ & 1) != 0) {
                    size += GeneratedMessageV3.computeStringSize(2, this.desc_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    size += CodedOutputStream.computeInt32Size(3, this.type_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    size += GeneratedMessageV3.computeStringSize(4, this.timeZone_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    size += CodedOutputStream.computeInt32Size(5, this.status_);
                }
                for (int i4 = 0; i4 < this.schedules_.size(); i4++) {
                    size += CodedOutputStream.computeMessageSize(6, this.schedules_.get(i4));
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
                if (!(obj instanceof Task)) {
                    return super.equals(obj);
                }
                Task task = (Task) obj;
                if (!getNameList().equals(task.getNameList()) || hasDesc() != task.hasDesc()) {
                    return false;
                }
                if ((hasDesc() && !getDesc().equals(task.getDesc())) || hasType() != task.hasType()) {
                    return false;
                }
                if ((hasType() && getType() != task.getType()) || hasTimeZone() != task.hasTimeZone()) {
                    return false;
                }
                if ((!hasTimeZone() || getTimeZone().equals(task.getTimeZone())) && hasStatus() == task.hasStatus()) {
                    return (!hasStatus() || getStatus() == task.getStatus()) && getSchedulesList().equals(task.getSchedulesList()) && this.unknownFields.equals(task.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (getNameCount() > 0) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getNameList().hashCode();
                }
                if (hasDesc()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getDesc().hashCode();
                }
                if (hasType()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getType();
                }
                if (hasTimeZone()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getTimeZone().hashCode();
                }
                if (hasStatus()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getStatus();
                }
                if (getSchedulesCount() > 0) {
                    hashCode = (((hashCode * 37) + 6) * 53) + getSchedulesList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static Task parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Task parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Task parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Task parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Task parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Task parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Task parseFrom(InputStream inputStream) throws IOException {
                return (Task) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Task parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Task) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Task parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Task) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Task parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Task) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Task parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Task) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Task parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Task) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Task task) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(task);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TaskOrBuilder {
                private int bitField0_;
                private Object desc_;
                private Internal.IntList name_;
                private RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> schedulesBuilder_;
                private List<Schedule> schedules_;
                private int status_;
                private Object timeZone_;
                private int type_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Task_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Task_fieldAccessorTable.ensureFieldAccessorsInitialized(Task.class, Builder.class);
                }

                private Builder() {
                    this.name_ = Task.emptyIntList();
                    this.desc_ = "";
                    this.timeZone_ = "";
                    this.schedules_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.name_ = Task.emptyIntList();
                    this.desc_ = "";
                    this.timeZone_ = "";
                    this.schedules_ = Collections.emptyList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (Task.alwaysUseFieldBuilders) {
                        getSchedulesFieldBuilder();
                    }
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.name_ = Task.emptyIntList();
                    int i = this.bitField0_;
                    this.desc_ = "";
                    this.type_ = 0;
                    this.timeZone_ = "";
                    this.status_ = 0;
                    this.bitField0_ = i & (-32);
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.schedules_ = Collections.emptyList();
                        this.bitField0_ &= -33;
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Task_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Task getDefaultInstanceForType() {
                    return Task.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Task build() {
                    Task buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Task buildPartial() {
                    Task task = new Task(this);
                    int i = this.bitField0_;
                    if ((i & 1) != 0) {
                        this.name_.makeImmutable();
                        this.bitField0_ &= -2;
                    }
                    task.name_ = this.name_;
                    int i2 = (i & 2) != 0 ? 1 : 0;
                    task.desc_ = this.desc_;
                    if ((i & 4) != 0) {
                        task.type_ = this.type_;
                        i2 |= 2;
                    }
                    if ((i & 8) != 0) {
                        i2 |= 4;
                    }
                    task.timeZone_ = this.timeZone_;
                    if ((i & 16) != 0) {
                        task.status_ = this.status_;
                        i2 |= 8;
                    }
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        task.schedules_ = repeatedFieldBuilderV3.build();
                    } else {
                        if ((this.bitField0_ & 32) != 0) {
                            this.schedules_ = Collections.unmodifiableList(this.schedules_);
                            this.bitField0_ &= -33;
                        }
                        task.schedules_ = this.schedules_;
                    }
                    task.bitField0_ = i2;
                    onBuilt();
                    return task;
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
                    if (message instanceof Task) {
                        return mergeFrom((Task) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Task task) {
                    if (task == Task.getDefaultInstance()) {
                        return this;
                    }
                    if (!task.name_.isEmpty()) {
                        if (this.name_.isEmpty()) {
                            this.name_ = task.name_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureNameIsMutable();
                            this.name_.addAll(task.name_);
                        }
                        onChanged();
                    }
                    if (task.hasDesc()) {
                        this.bitField0_ |= 2;
                        this.desc_ = task.desc_;
                        onChanged();
                    }
                    if (task.hasType()) {
                        setType(task.getType());
                    }
                    if (task.hasTimeZone()) {
                        this.bitField0_ |= 8;
                        this.timeZone_ = task.timeZone_;
                        onChanged();
                    }
                    if (task.hasStatus()) {
                        setStatus(task.getStatus());
                    }
                    if (this.schedulesBuilder_ == null) {
                        if (!task.schedules_.isEmpty()) {
                            if (this.schedules_.isEmpty()) {
                                this.schedules_ = task.schedules_;
                                this.bitField0_ &= -33;
                            } else {
                                ensureSchedulesIsMutable();
                                this.schedules_.addAll(task.schedules_);
                            }
                            onChanged();
                        }
                    } else if (!task.schedules_.isEmpty()) {
                        if (!this.schedulesBuilder_.isEmpty()) {
                            this.schedulesBuilder_.addAllMessages(task.schedules_);
                        } else {
                            this.schedulesBuilder_.dispose();
                            this.schedulesBuilder_ = null;
                            this.schedules_ = task.schedules_;
                            this.bitField0_ &= -33;
                            this.schedulesBuilder_ = Task.alwaysUseFieldBuilders ? getSchedulesFieldBuilder() : null;
                        }
                    }
                    mergeUnknownFields(task.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (!hasType()) {
                        return false;
                    }
                    for (int i = 0; i < getSchedulesCount(); i++) {
                        if (!getSchedules(i).isInitialized()) {
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
                public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Task.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Task> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Task.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Task r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Task) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Task r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Task) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Task.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Task$Builder");
                }

                private void ensureNameIsMutable() {
                    if ((this.bitField0_ & 1) == 0) {
                        this.name_ = Task.mutableCopy(this.name_);
                        this.bitField0_ |= 1;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public List<Integer> getNameList() {
                    return (this.bitField0_ & 1) != 0 ? Collections.unmodifiableList(this.name_) : this.name_;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public int getNameCount() {
                    return this.name_.size();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public int getName(int i) {
                    return this.name_.getInt(i);
                }

                public Builder setName(int i, int i2) {
                    ensureNameIsMutable();
                    this.name_.setInt(i, i2);
                    onChanged();
                    return this;
                }

                public Builder addName(int i) {
                    ensureNameIsMutable();
                    this.name_.addInt(i);
                    onChanged();
                    return this;
                }

                public Builder addAllName(Iterable<? extends Integer> iterable) {
                    ensureNameIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.name_);
                    onChanged();
                    return this;
                }

                public Builder clearName() {
                    this.name_ = Task.emptyIntList();
                    this.bitField0_ &= -2;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public boolean hasDesc() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public String getDesc() {
                    Object obj = this.desc_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.desc_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public ByteString getDescBytes() {
                    Object obj = this.desc_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.desc_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setDesc(String str) {
                    str.getClass();
                    this.bitField0_ |= 2;
                    this.desc_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearDesc() {
                    this.bitField0_ &= -3;
                    this.desc_ = Task.getDefaultInstance().getDesc();
                    onChanged();
                    return this;
                }

                public Builder setDescBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 2;
                    this.desc_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public boolean hasType() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public int getType() {
                    return this.type_;
                }

                public Builder setType(int i) {
                    this.bitField0_ |= 4;
                    this.type_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.bitField0_ &= -5;
                    this.type_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public boolean hasTimeZone() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public String getTimeZone() {
                    Object obj = this.timeZone_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.timeZone_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public ByteString getTimeZoneBytes() {
                    Object obj = this.timeZone_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.timeZone_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setTimeZone(String str) {
                    str.getClass();
                    this.bitField0_ |= 8;
                    this.timeZone_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearTimeZone() {
                    this.bitField0_ &= -9;
                    this.timeZone_ = Task.getDefaultInstance().getTimeZone();
                    onChanged();
                    return this;
                }

                public Builder setTimeZoneBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 8;
                    this.timeZone_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public boolean hasStatus() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public int getStatus() {
                    return this.status_;
                }

                public Builder setStatus(int i) {
                    this.bitField0_ |= 16;
                    this.status_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearStatus() {
                    this.bitField0_ &= -17;
                    this.status_ = 0;
                    onChanged();
                    return this;
                }

                private void ensureSchedulesIsMutable() {
                    if ((this.bitField0_ & 32) == 0) {
                        this.schedules_ = new ArrayList(this.schedules_);
                        this.bitField0_ |= 32;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public List<Schedule> getSchedulesList() {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return Collections.unmodifiableList(this.schedules_);
                    }
                    return repeatedFieldBuilderV3.getMessageList();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public int getSchedulesCount() {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.schedules_.size();
                    }
                    return repeatedFieldBuilderV3.getCount();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public Schedule getSchedules(int i) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.schedules_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessage(i);
                }

                public Builder setSchedules(int i, Schedule schedule) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        schedule.getClass();
                        ensureSchedulesIsMutable();
                        this.schedules_.set(i, schedule);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, schedule);
                    }
                    return this;
                }

                public Builder setSchedules(int i, Schedule.Builder builder) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureSchedulesIsMutable();
                        this.schedules_.set(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addSchedules(Schedule schedule) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        schedule.getClass();
                        ensureSchedulesIsMutable();
                        this.schedules_.add(schedule);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(schedule);
                    }
                    return this;
                }

                public Builder addSchedules(int i, Schedule schedule) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        schedule.getClass();
                        ensureSchedulesIsMutable();
                        this.schedules_.add(i, schedule);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, schedule);
                    }
                    return this;
                }

                public Builder addSchedules(Schedule.Builder builder) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureSchedulesIsMutable();
                        this.schedules_.add(builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addSchedules(int i, Schedule.Builder builder) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureSchedulesIsMutable();
                        this.schedules_.add(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addAllSchedules(Iterable<? extends Schedule> iterable) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureSchedulesIsMutable();
                        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.schedules_);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder clearSchedules() {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.schedules_ = Collections.emptyList();
                        this.bitField0_ &= -33;
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                public Builder removeSchedules(int i) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureSchedulesIsMutable();
                        this.schedules_.remove(i);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.remove(i);
                    }
                    return this;
                }

                public Schedule.Builder getSchedulesBuilder(int i) {
                    return getSchedulesFieldBuilder().getBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public ScheduleOrBuilder getSchedulesOrBuilder(int i) {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.schedules_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.TaskOrBuilder
                public List<? extends ScheduleOrBuilder> getSchedulesOrBuilderList() {
                    RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> repeatedFieldBuilderV3 = this.schedulesBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        return repeatedFieldBuilderV3.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.schedules_);
                }

                public Schedule.Builder addSchedulesBuilder() {
                    return getSchedulesFieldBuilder().addBuilder(Schedule.getDefaultInstance());
                }

                public Schedule.Builder addSchedulesBuilder(int i) {
                    return getSchedulesFieldBuilder().addBuilder(i, Schedule.getDefaultInstance());
                }

                public List<Schedule.Builder> getSchedulesBuilderList() {
                    return getSchedulesFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilderV3<Schedule, Schedule.Builder, ScheduleOrBuilder> getSchedulesFieldBuilder() {
                    if (this.schedulesBuilder_ == null) {
                        this.schedulesBuilder_ = new RepeatedFieldBuilderV3<>(this.schedules_, (this.bitField0_ & 32) != 0, getParentForChildren(), isClean());
                        this.schedules_ = null;
                    }
                    return this.schedulesBuilder_;
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

            public static Task getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Task> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<Task> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Task getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class Schedule extends GeneratedMessageV3 implements ScheduleOrBuilder {
            public static final int ALLDAYTYPE_FIELD_NUMBER = 1;
            public static final int DTEND_FIELD_NUMBER = 3;
            public static final int DTSTART_FIELD_NUMBER = 2;
            public static final int METADATA_FIELD_NUMBER = 4;
            public static final int OBJECTIVEINFOS_FIELD_NUMBER = 5;
            public static final int OBJECTIVERELATION_FIELD_NUMBER = 6;
            public static final int RECURRENCE_FIELD_NUMBER = 7;
            public static final int TIMEZONE_FIELD_NUMBER = 8;
            private static final long serialVersionUID = 0;
            private boolean allDayType_;
            private int bitField0_;
            private long dtEnd_;
            private long dtStart_;
            private byte memoizedIsInitialized;
            private Metadata metadata_;
            private List<ObjectiveInfo> objectiveInfos_;
            private int objectiveRelation_;
            private volatile Object recurrence_;
            private volatile Object timeZone_;
            private static final Schedule DEFAULT_INSTANCE = new Schedule();

            @Deprecated
            public static final Parser<Schedule> PARSER = new AbstractParser<Schedule>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Schedule.1
                @Override // com.google.protobuf.Parser
                public Schedule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Schedule(codedInputStream, extensionRegistryLite);
                }
            };

            private Schedule(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Schedule() {
                this.memoizedIsInitialized = (byte) -1;
                this.objectiveInfos_ = Collections.emptyList();
                this.recurrence_ = "";
                this.timeZone_ = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new Schedule();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Schedule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (readTag == 8) {
                                        this.bitField0_ |= 1;
                                        this.allDayType_ = codedInputStream.readBool();
                                    } else if (readTag == 16) {
                                        this.bitField0_ |= 2;
                                        this.dtStart_ = codedInputStream.readInt64();
                                    } else if (readTag == 24) {
                                        this.bitField0_ |= 4;
                                        this.dtEnd_ = codedInputStream.readInt64();
                                    } else if (readTag == 34) {
                                        Metadata.Builder builder = (this.bitField0_ & 8) != 0 ? this.metadata_.toBuilder() : null;
                                        Metadata metadata = (Metadata) codedInputStream.readMessage(Metadata.PARSER, extensionRegistryLite);
                                        this.metadata_ = metadata;
                                        if (builder != null) {
                                            builder.mergeFrom(metadata);
                                            this.metadata_ = builder.buildPartial();
                                        }
                                        this.bitField0_ |= 8;
                                    } else if (readTag == 42) {
                                        if ((c & 16) == 0) {
                                            this.objectiveInfos_ = new ArrayList();
                                            c = 16;
                                        }
                                        this.objectiveInfos_.add((ObjectiveInfo) codedInputStream.readMessage(ObjectiveInfo.PARSER, extensionRegistryLite));
                                    } else if (readTag == 48) {
                                        this.bitField0_ |= 16;
                                        this.objectiveRelation_ = codedInputStream.readInt32();
                                    } else if (readTag == 58) {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ |= 32;
                                        this.recurrence_ = readBytes;
                                    } else if (readTag == 66) {
                                        ByteString readBytes2 = codedInputStream.readBytes();
                                        this.bitField0_ |= 64;
                                        this.timeZone_ = readBytes2;
                                    } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        }
                    } finally {
                        if ((c & 16) != 0) {
                            this.objectiveInfos_ = Collections.unmodifiableList(this.objectiveInfos_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Schedule_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Schedule_fieldAccessorTable.ensureFieldAccessorsInitialized(Schedule.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean hasAllDayType() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean getAllDayType() {
                return this.allDayType_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean hasDtStart() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public long getDtStart() {
                return this.dtStart_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean hasDtEnd() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public long getDtEnd() {
                return this.dtEnd_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean hasMetadata() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public Metadata getMetadata() {
                Metadata metadata = this.metadata_;
                return metadata == null ? Metadata.getDefaultInstance() : metadata;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public MetadataOrBuilder getMetadataOrBuilder() {
                Metadata metadata = this.metadata_;
                return metadata == null ? Metadata.getDefaultInstance() : metadata;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public List<ObjectiveInfo> getObjectiveInfosList() {
                return this.objectiveInfos_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public List<? extends ObjectiveInfoOrBuilder> getObjectiveInfosOrBuilderList() {
                return this.objectiveInfos_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public int getObjectiveInfosCount() {
                return this.objectiveInfos_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public ObjectiveInfo getObjectiveInfos(int i) {
                return this.objectiveInfos_.get(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public ObjectiveInfoOrBuilder getObjectiveInfosOrBuilder(int i) {
                return this.objectiveInfos_.get(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean hasObjectiveRelation() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public int getObjectiveRelation() {
                return this.objectiveRelation_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean hasRecurrence() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public String getRecurrence() {
                Object obj = this.recurrence_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.recurrence_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public ByteString getRecurrenceBytes() {
                Object obj = this.recurrence_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.recurrence_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public boolean hasTimeZone() {
                return (this.bitField0_ & 64) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public String getTimeZone() {
                Object obj = this.timeZone_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.timeZone_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
            public ByteString getTimeZoneBytes() {
                Object obj = this.timeZone_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeZone_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
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
                if (!hasAllDayType()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasDtStart()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasDtEnd()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasTimeZone()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (hasMetadata() && !getMetadata().isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    codedOutputStream.writeBool(1, this.allDayType_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeInt64(2, this.dtStart_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeInt64(3, this.dtEnd_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeMessage(4, getMetadata());
                }
                for (int i = 0; i < this.objectiveInfos_.size(); i++) {
                    codedOutputStream.writeMessage(5, this.objectiveInfos_.get(i));
                }
                if ((this.bitField0_ & 16) != 0) {
                    codedOutputStream.writeInt32(6, this.objectiveRelation_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 7, this.recurrence_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 8, this.timeZone_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeBoolSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeBoolSize(1, this.allDayType_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeBoolSize += CodedOutputStream.computeInt64Size(2, this.dtStart_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    computeBoolSize += CodedOutputStream.computeInt64Size(3, this.dtEnd_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    computeBoolSize += CodedOutputStream.computeMessageSize(4, getMetadata());
                }
                for (int i2 = 0; i2 < this.objectiveInfos_.size(); i2++) {
                    computeBoolSize += CodedOutputStream.computeMessageSize(5, this.objectiveInfos_.get(i2));
                }
                if ((this.bitField0_ & 16) != 0) {
                    computeBoolSize += CodedOutputStream.computeInt32Size(6, this.objectiveRelation_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    computeBoolSize += GeneratedMessageV3.computeStringSize(7, this.recurrence_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    computeBoolSize += GeneratedMessageV3.computeStringSize(8, this.timeZone_);
                }
                int serializedSize = computeBoolSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Schedule)) {
                    return super.equals(obj);
                }
                Schedule schedule = (Schedule) obj;
                if (hasAllDayType() != schedule.hasAllDayType()) {
                    return false;
                }
                if ((hasAllDayType() && getAllDayType() != schedule.getAllDayType()) || hasDtStart() != schedule.hasDtStart()) {
                    return false;
                }
                if ((hasDtStart() && getDtStart() != schedule.getDtStart()) || hasDtEnd() != schedule.hasDtEnd()) {
                    return false;
                }
                if ((hasDtEnd() && getDtEnd() != schedule.getDtEnd()) || hasMetadata() != schedule.hasMetadata()) {
                    return false;
                }
                if ((hasMetadata() && !getMetadata().equals(schedule.getMetadata())) || !getObjectiveInfosList().equals(schedule.getObjectiveInfosList()) || hasObjectiveRelation() != schedule.hasObjectiveRelation()) {
                    return false;
                }
                if ((hasObjectiveRelation() && getObjectiveRelation() != schedule.getObjectiveRelation()) || hasRecurrence() != schedule.hasRecurrence()) {
                    return false;
                }
                if ((!hasRecurrence() || getRecurrence().equals(schedule.getRecurrence())) && hasTimeZone() == schedule.hasTimeZone()) {
                    return (!hasTimeZone() || getTimeZone().equals(schedule.getTimeZone())) && this.unknownFields.equals(schedule.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasAllDayType()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + Internal.hashBoolean(getAllDayType());
                }
                if (hasDtStart()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + Internal.hashLong(getDtStart());
                }
                if (hasDtEnd()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + Internal.hashLong(getDtEnd());
                }
                if (hasMetadata()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getMetadata().hashCode();
                }
                if (getObjectiveInfosCount() > 0) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getObjectiveInfosList().hashCode();
                }
                if (hasObjectiveRelation()) {
                    hashCode = (((hashCode * 37) + 6) * 53) + getObjectiveRelation();
                }
                if (hasRecurrence()) {
                    hashCode = (((hashCode * 37) + 7) * 53) + getRecurrence().hashCode();
                }
                if (hasTimeZone()) {
                    hashCode = (((hashCode * 37) + 8) * 53) + getTimeZone().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static Schedule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Schedule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Schedule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Schedule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Schedule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Schedule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Schedule parseFrom(InputStream inputStream) throws IOException {
                return (Schedule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Schedule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Schedule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Schedule parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Schedule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Schedule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Schedule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Schedule parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Schedule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Schedule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Schedule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Schedule schedule) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(schedule);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScheduleOrBuilder {
                private boolean allDayType_;
                private int bitField0_;
                private long dtEnd_;
                private long dtStart_;
                private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> metadataBuilder_;
                private Metadata metadata_;
                private RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> objectiveInfosBuilder_;
                private List<ObjectiveInfo> objectiveInfos_;
                private int objectiveRelation_;
                private Object recurrence_;
                private Object timeZone_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Schedule_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Schedule_fieldAccessorTable.ensureFieldAccessorsInitialized(Schedule.class, Builder.class);
                }

                private Builder() {
                    this.objectiveInfos_ = Collections.emptyList();
                    this.recurrence_ = "";
                    this.timeZone_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.objectiveInfos_ = Collections.emptyList();
                    this.recurrence_ = "";
                    this.timeZone_ = "";
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (Schedule.alwaysUseFieldBuilders) {
                        getMetadataFieldBuilder();
                        getObjectiveInfosFieldBuilder();
                    }
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.allDayType_ = false;
                    int i = this.bitField0_;
                    this.dtStart_ = 0L;
                    this.dtEnd_ = 0L;
                    this.bitField0_ = i & (-8);
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.metadata_ = null;
                    } else {
                        singleFieldBuilderV3.clear();
                    }
                    this.bitField0_ &= -9;
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.objectiveInfos_ = Collections.emptyList();
                        this.bitField0_ &= -17;
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    this.objectiveRelation_ = 0;
                    int i2 = this.bitField0_;
                    this.recurrence_ = "";
                    this.timeZone_ = "";
                    this.bitField0_ = i2 & (-225);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Schedule_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Schedule getDefaultInstanceForType() {
                    return Schedule.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Schedule build() {
                    Schedule buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Schedule buildPartial() {
                    int i;
                    Schedule schedule = new Schedule(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        schedule.allDayType_ = this.allDayType_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        schedule.dtStart_ = this.dtStart_;
                        i |= 2;
                    }
                    if ((i2 & 4) != 0) {
                        schedule.dtEnd_ = this.dtEnd_;
                        i |= 4;
                    }
                    if ((i2 & 8) != 0) {
                        SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            schedule.metadata_ = this.metadata_;
                        } else {
                            schedule.metadata_ = singleFieldBuilderV3.build();
                        }
                        i |= 8;
                    }
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        schedule.objectiveInfos_ = repeatedFieldBuilderV3.build();
                    } else {
                        if ((this.bitField0_ & 16) != 0) {
                            this.objectiveInfos_ = Collections.unmodifiableList(this.objectiveInfos_);
                            this.bitField0_ &= -17;
                        }
                        schedule.objectiveInfos_ = this.objectiveInfos_;
                    }
                    if ((i2 & 32) != 0) {
                        schedule.objectiveRelation_ = this.objectiveRelation_;
                        i |= 16;
                    }
                    if ((i2 & 64) != 0) {
                        i |= 32;
                    }
                    schedule.recurrence_ = this.recurrence_;
                    if ((i2 & 128) != 0) {
                        i |= 64;
                    }
                    schedule.timeZone_ = this.timeZone_;
                    schedule.bitField0_ = i;
                    onBuilt();
                    return schedule;
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
                    if (message instanceof Schedule) {
                        return mergeFrom((Schedule) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Schedule schedule) {
                    if (schedule == Schedule.getDefaultInstance()) {
                        return this;
                    }
                    if (schedule.hasAllDayType()) {
                        setAllDayType(schedule.getAllDayType());
                    }
                    if (schedule.hasDtStart()) {
                        setDtStart(schedule.getDtStart());
                    }
                    if (schedule.hasDtEnd()) {
                        setDtEnd(schedule.getDtEnd());
                    }
                    if (schedule.hasMetadata()) {
                        mergeMetadata(schedule.getMetadata());
                    }
                    if (this.objectiveInfosBuilder_ == null) {
                        if (!schedule.objectiveInfos_.isEmpty()) {
                            if (this.objectiveInfos_.isEmpty()) {
                                this.objectiveInfos_ = schedule.objectiveInfos_;
                                this.bitField0_ &= -17;
                            } else {
                                ensureObjectiveInfosIsMutable();
                                this.objectiveInfos_.addAll(schedule.objectiveInfos_);
                            }
                            onChanged();
                        }
                    } else if (!schedule.objectiveInfos_.isEmpty()) {
                        if (!this.objectiveInfosBuilder_.isEmpty()) {
                            this.objectiveInfosBuilder_.addAllMessages(schedule.objectiveInfos_);
                        } else {
                            this.objectiveInfosBuilder_.dispose();
                            this.objectiveInfosBuilder_ = null;
                            this.objectiveInfos_ = schedule.objectiveInfos_;
                            this.bitField0_ &= -17;
                            this.objectiveInfosBuilder_ = Schedule.alwaysUseFieldBuilders ? getObjectiveInfosFieldBuilder() : null;
                        }
                    }
                    if (schedule.hasObjectiveRelation()) {
                        setObjectiveRelation(schedule.getObjectiveRelation());
                    }
                    if (schedule.hasRecurrence()) {
                        this.bitField0_ |= 64;
                        this.recurrence_ = schedule.recurrence_;
                        onChanged();
                    }
                    if (schedule.hasTimeZone()) {
                        this.bitField0_ |= 128;
                        this.timeZone_ = schedule.timeZone_;
                        onChanged();
                    }
                    mergeUnknownFields(schedule.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (hasAllDayType() && hasDtStart() && hasDtEnd() && hasTimeZone()) {
                        return !hasMetadata() || getMetadata().isInitialized();
                    }
                    return false;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Schedule.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Schedule> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Schedule.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Schedule r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Schedule) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Schedule r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Schedule) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Schedule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Schedule$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean hasAllDayType() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean getAllDayType() {
                    return this.allDayType_;
                }

                public Builder setAllDayType(boolean z) {
                    this.bitField0_ |= 1;
                    this.allDayType_ = z;
                    onChanged();
                    return this;
                }

                public Builder clearAllDayType() {
                    this.bitField0_ &= -2;
                    this.allDayType_ = false;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean hasDtStart() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public long getDtStart() {
                    return this.dtStart_;
                }

                public Builder setDtStart(long j) {
                    this.bitField0_ |= 2;
                    this.dtStart_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearDtStart() {
                    this.bitField0_ &= -3;
                    this.dtStart_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean hasDtEnd() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public long getDtEnd() {
                    return this.dtEnd_;
                }

                public Builder setDtEnd(long j) {
                    this.bitField0_ |= 4;
                    this.dtEnd_ = j;
                    onChanged();
                    return this;
                }

                public Builder clearDtEnd() {
                    this.bitField0_ &= -5;
                    this.dtEnd_ = 0L;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean hasMetadata() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public Metadata getMetadata() {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        Metadata metadata = this.metadata_;
                        return metadata == null ? Metadata.getDefaultInstance() : metadata;
                    }
                    return singleFieldBuilderV3.getMessage();
                }

                public Builder setMetadata(Metadata metadata) {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        metadata.getClass();
                        this.metadata_ = metadata;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(metadata);
                    }
                    this.bitField0_ |= 8;
                    return this;
                }

                public Builder setMetadata(Metadata.Builder builder) {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.metadata_ = builder.build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.build());
                    }
                    this.bitField0_ |= 8;
                    return this;
                }

                public Builder mergeMetadata(Metadata metadata) {
                    Metadata metadata2;
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if ((this.bitField0_ & 8) != 0 && (metadata2 = this.metadata_) != null && metadata2 != Metadata.getDefaultInstance()) {
                            this.metadata_ = Metadata.newBuilder(this.metadata_).mergeFrom(metadata).buildPartial();
                        } else {
                            this.metadata_ = metadata;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(metadata);
                    }
                    this.bitField0_ |= 8;
                    return this;
                }

                public Builder clearMetadata() {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.metadata_ = null;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.clear();
                    }
                    this.bitField0_ &= -9;
                    return this;
                }

                public Metadata.Builder getMetadataBuilder() {
                    this.bitField0_ |= 8;
                    onChanged();
                    return getMetadataFieldBuilder().getBuilder();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public MetadataOrBuilder getMetadataOrBuilder() {
                    SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> singleFieldBuilderV3 = this.metadataBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    Metadata metadata = this.metadata_;
                    return metadata == null ? Metadata.getDefaultInstance() : metadata;
                }

                private SingleFieldBuilderV3<Metadata, Metadata.Builder, MetadataOrBuilder> getMetadataFieldBuilder() {
                    if (this.metadataBuilder_ == null) {
                        this.metadataBuilder_ = new SingleFieldBuilderV3<>(getMetadata(), getParentForChildren(), isClean());
                        this.metadata_ = null;
                    }
                    return this.metadataBuilder_;
                }

                private void ensureObjectiveInfosIsMutable() {
                    if ((this.bitField0_ & 16) == 0) {
                        this.objectiveInfos_ = new ArrayList(this.objectiveInfos_);
                        this.bitField0_ |= 16;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public List<ObjectiveInfo> getObjectiveInfosList() {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return Collections.unmodifiableList(this.objectiveInfos_);
                    }
                    return repeatedFieldBuilderV3.getMessageList();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public int getObjectiveInfosCount() {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.objectiveInfos_.size();
                    }
                    return repeatedFieldBuilderV3.getCount();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public ObjectiveInfo getObjectiveInfos(int i) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.objectiveInfos_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessage(i);
                }

                public Builder setObjectiveInfos(int i, ObjectiveInfo objectiveInfo) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        objectiveInfo.getClass();
                        ensureObjectiveInfosIsMutable();
                        this.objectiveInfos_.set(i, objectiveInfo);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, objectiveInfo);
                    }
                    return this;
                }

                public Builder setObjectiveInfos(int i, ObjectiveInfo.Builder builder) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureObjectiveInfosIsMutable();
                        this.objectiveInfos_.set(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addObjectiveInfos(ObjectiveInfo objectiveInfo) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        objectiveInfo.getClass();
                        ensureObjectiveInfosIsMutable();
                        this.objectiveInfos_.add(objectiveInfo);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(objectiveInfo);
                    }
                    return this;
                }

                public Builder addObjectiveInfos(int i, ObjectiveInfo objectiveInfo) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        objectiveInfo.getClass();
                        ensureObjectiveInfosIsMutable();
                        this.objectiveInfos_.add(i, objectiveInfo);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, objectiveInfo);
                    }
                    return this;
                }

                public Builder addObjectiveInfos(ObjectiveInfo.Builder builder) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureObjectiveInfosIsMutable();
                        this.objectiveInfos_.add(builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addObjectiveInfos(int i, ObjectiveInfo.Builder builder) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureObjectiveInfosIsMutable();
                        this.objectiveInfos_.add(i, builder.build());
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addAllObjectiveInfos(Iterable<? extends ObjectiveInfo> iterable) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureObjectiveInfosIsMutable();
                        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.objectiveInfos_);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder clearObjectiveInfos() {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        this.objectiveInfos_ = Collections.emptyList();
                        this.bitField0_ &= -17;
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.clear();
                    }
                    return this;
                }

                public Builder removeObjectiveInfos(int i) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        ensureObjectiveInfosIsMutable();
                        this.objectiveInfos_.remove(i);
                        onChanged();
                    } else {
                        repeatedFieldBuilderV3.remove(i);
                    }
                    return this;
                }

                public ObjectiveInfo.Builder getObjectiveInfosBuilder(int i) {
                    return getObjectiveInfosFieldBuilder().getBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public ObjectiveInfoOrBuilder getObjectiveInfosOrBuilder(int i) {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 == null) {
                        return this.objectiveInfos_.get(i);
                    }
                    return repeatedFieldBuilderV3.getMessageOrBuilder(i);
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public List<? extends ObjectiveInfoOrBuilder> getObjectiveInfosOrBuilderList() {
                    RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> repeatedFieldBuilderV3 = this.objectiveInfosBuilder_;
                    if (repeatedFieldBuilderV3 != null) {
                        return repeatedFieldBuilderV3.getMessageOrBuilderList();
                    }
                    return Collections.unmodifiableList(this.objectiveInfos_);
                }

                public ObjectiveInfo.Builder addObjectiveInfosBuilder() {
                    return getObjectiveInfosFieldBuilder().addBuilder(ObjectiveInfo.getDefaultInstance());
                }

                public ObjectiveInfo.Builder addObjectiveInfosBuilder(int i) {
                    return getObjectiveInfosFieldBuilder().addBuilder(i, ObjectiveInfo.getDefaultInstance());
                }

                public List<ObjectiveInfo.Builder> getObjectiveInfosBuilderList() {
                    return getObjectiveInfosFieldBuilder().getBuilderList();
                }

                private RepeatedFieldBuilderV3<ObjectiveInfo, ObjectiveInfo.Builder, ObjectiveInfoOrBuilder> getObjectiveInfosFieldBuilder() {
                    if (this.objectiveInfosBuilder_ == null) {
                        this.objectiveInfosBuilder_ = new RepeatedFieldBuilderV3<>(this.objectiveInfos_, (this.bitField0_ & 16) != 0, getParentForChildren(), isClean());
                        this.objectiveInfos_ = null;
                    }
                    return this.objectiveInfosBuilder_;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean hasObjectiveRelation() {
                    return (this.bitField0_ & 32) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public int getObjectiveRelation() {
                    return this.objectiveRelation_;
                }

                public Builder setObjectiveRelation(int i) {
                    this.bitField0_ |= 32;
                    this.objectiveRelation_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearObjectiveRelation() {
                    this.bitField0_ &= -33;
                    this.objectiveRelation_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean hasRecurrence() {
                    return (this.bitField0_ & 64) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public String getRecurrence() {
                    Object obj = this.recurrence_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.recurrence_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public ByteString getRecurrenceBytes() {
                    Object obj = this.recurrence_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.recurrence_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setRecurrence(String str) {
                    str.getClass();
                    this.bitField0_ |= 64;
                    this.recurrence_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearRecurrence() {
                    this.bitField0_ &= -65;
                    this.recurrence_ = Schedule.getDefaultInstance().getRecurrence();
                    onChanged();
                    return this;
                }

                public Builder setRecurrenceBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 64;
                    this.recurrence_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public boolean hasTimeZone() {
                    return (this.bitField0_ & 128) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public String getTimeZone() {
                    Object obj = this.timeZone_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.timeZone_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ScheduleOrBuilder
                public ByteString getTimeZoneBytes() {
                    Object obj = this.timeZone_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.timeZone_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setTimeZone(String str) {
                    str.getClass();
                    this.bitField0_ |= 128;
                    this.timeZone_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearTimeZone() {
                    this.bitField0_ &= -129;
                    this.timeZone_ = Schedule.getDefaultInstance().getTimeZone();
                    onChanged();
                    return this;
                }

                public Builder setTimeZoneBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 128;
                    this.timeZone_ = byteString;
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

            public static Schedule getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Schedule> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<Schedule> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Schedule getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class ObjectiveInfo extends GeneratedMessageV3 implements ObjectiveInfoOrBuilder {
            public static final int DATATYPE_FIELD_NUMBER = 3;
            public static final int FIELDNAME_FIELD_NUMBER = 2;
            public static final int OBJECTIVETYPE_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private volatile Object dataType_;
            private volatile Object fieldName_;
            private byte memoizedIsInitialized;
            private int objectiveType_;
            private static final ObjectiveInfo DEFAULT_INSTANCE = new ObjectiveInfo();

            @Deprecated
            public static final Parser<ObjectiveInfo> PARSER = new AbstractParser<ObjectiveInfo>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfo.1
                @Override // com.google.protobuf.Parser
                public ObjectiveInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ObjectiveInfo(codedInputStream, extensionRegistryLite);
                }
            };

            private ObjectiveInfo(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private ObjectiveInfo() {
                this.memoizedIsInitialized = (byte) -1;
                this.fieldName_ = "";
                this.dataType_ = "";
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new ObjectiveInfo();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private ObjectiveInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.bitField0_ |= 1;
                                    this.objectiveType_ = codedInputStream.readInt32();
                                } else if (readTag == 18) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.fieldName_ = readBytes;
                                } else if (readTag == 26) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 4;
                                    this.dataType_ = readBytes2;
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
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_ObjectiveInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_ObjectiveInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectiveInfo.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public boolean hasObjectiveType() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public int getObjectiveType() {
                return this.objectiveType_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public boolean hasFieldName() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public String getFieldName() {
                Object obj = this.fieldName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.fieldName_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public ByteString getFieldNameBytes() {
                Object obj = this.fieldName_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.fieldName_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public boolean hasDataType() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public String getDataType() {
                Object obj = this.dataType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.dataType_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
            public ByteString getDataTypeBytes() {
                Object obj = this.dataType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dataType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
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
                if ((this.bitField0_ & 1) != 0) {
                    codedOutputStream.writeInt32(1, this.objectiveType_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.fieldName_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.dataType_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeInt32Size = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeInt32Size(1, this.objectiveType_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.fieldName_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    computeInt32Size += GeneratedMessageV3.computeStringSize(3, this.dataType_);
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
                if (!(obj instanceof ObjectiveInfo)) {
                    return super.equals(obj);
                }
                ObjectiveInfo objectiveInfo = (ObjectiveInfo) obj;
                if (hasObjectiveType() != objectiveInfo.hasObjectiveType()) {
                    return false;
                }
                if ((hasObjectiveType() && getObjectiveType() != objectiveInfo.getObjectiveType()) || hasFieldName() != objectiveInfo.hasFieldName()) {
                    return false;
                }
                if ((!hasFieldName() || getFieldName().equals(objectiveInfo.getFieldName())) && hasDataType() == objectiveInfo.hasDataType()) {
                    return (!hasDataType() || getDataType().equals(objectiveInfo.getDataType())) && this.unknownFields.equals(objectiveInfo.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasObjectiveType()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getObjectiveType();
                }
                if (hasFieldName()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getFieldName().hashCode();
                }
                if (hasDataType()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getDataType().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static ObjectiveInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static ObjectiveInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static ObjectiveInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static ObjectiveInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ObjectiveInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static ObjectiveInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static ObjectiveInfo parseFrom(InputStream inputStream) throws IOException {
                return (ObjectiveInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static ObjectiveInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ObjectiveInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ObjectiveInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (ObjectiveInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static ObjectiveInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ObjectiveInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ObjectiveInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (ObjectiveInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static ObjectiveInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (ObjectiveInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ObjectiveInfo objectiveInfo) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(objectiveInfo);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ObjectiveInfoOrBuilder {
                private int bitField0_;
                private Object dataType_;
                private Object fieldName_;
                private int objectiveType_;

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_ObjectiveInfo_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_ObjectiveInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ObjectiveInfo.class, Builder.class);
                }

                private Builder() {
                    this.fieldName_ = "";
                    this.dataType_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.fieldName_ = "";
                    this.dataType_ = "";
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = ObjectiveInfo.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.objectiveType_ = 0;
                    int i = this.bitField0_;
                    this.fieldName_ = "";
                    this.dataType_ = "";
                    this.bitField0_ = i & (-8);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_ObjectiveInfo_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public ObjectiveInfo getDefaultInstanceForType() {
                    return ObjectiveInfo.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ObjectiveInfo build() {
                    ObjectiveInfo buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public ObjectiveInfo buildPartial() {
                    int i;
                    ObjectiveInfo objectiveInfo = new ObjectiveInfo(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        objectiveInfo.objectiveType_ = this.objectiveType_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        i |= 2;
                    }
                    objectiveInfo.fieldName_ = this.fieldName_;
                    if ((i2 & 4) != 0) {
                        i |= 4;
                    }
                    objectiveInfo.dataType_ = this.dataType_;
                    objectiveInfo.bitField0_ = i;
                    onBuilt();
                    return objectiveInfo;
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
                    if (message instanceof ObjectiveInfo) {
                        return mergeFrom((ObjectiveInfo) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ObjectiveInfo objectiveInfo) {
                    if (objectiveInfo == ObjectiveInfo.getDefaultInstance()) {
                        return this;
                    }
                    if (objectiveInfo.hasObjectiveType()) {
                        setObjectiveType(objectiveInfo.getObjectiveType());
                    }
                    if (objectiveInfo.hasFieldName()) {
                        this.bitField0_ |= 2;
                        this.fieldName_ = objectiveInfo.fieldName_;
                        onChanged();
                    }
                    if (objectiveInfo.hasDataType()) {
                        this.bitField0_ |= 4;
                        this.dataType_ = objectiveInfo.dataType_;
                        onChanged();
                    }
                    mergeUnknownFields(objectiveInfo.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$ObjectiveInfo> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$ObjectiveInfo r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$ObjectiveInfo r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfo) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$ObjectiveInfo$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public boolean hasObjectiveType() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public int getObjectiveType() {
                    return this.objectiveType_;
                }

                public Builder setObjectiveType(int i) {
                    this.bitField0_ |= 1;
                    this.objectiveType_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearObjectiveType() {
                    this.bitField0_ &= -2;
                    this.objectiveType_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public boolean hasFieldName() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public String getFieldName() {
                    Object obj = this.fieldName_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.fieldName_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public ByteString getFieldNameBytes() {
                    Object obj = this.fieldName_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.fieldName_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setFieldName(String str) {
                    str.getClass();
                    this.bitField0_ |= 2;
                    this.fieldName_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearFieldName() {
                    this.bitField0_ &= -3;
                    this.fieldName_ = ObjectiveInfo.getDefaultInstance().getFieldName();
                    onChanged();
                    return this;
                }

                public Builder setFieldNameBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 2;
                    this.fieldName_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public boolean hasDataType() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public String getDataType() {
                    Object obj = this.dataType_;
                    if (!(obj instanceof String)) {
                        ByteString byteString = (ByteString) obj;
                        String stringUtf8 = byteString.toStringUtf8();
                        if (byteString.isValidUtf8()) {
                            this.dataType_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) obj;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.ObjectiveInfoOrBuilder
                public ByteString getDataTypeBytes() {
                    Object obj = this.dataType_;
                    if (obj instanceof String) {
                        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.dataType_ = copyFromUtf8;
                        return copyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public Builder setDataType(String str) {
                    str.getClass();
                    this.bitField0_ |= 4;
                    this.dataType_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearDataType() {
                    this.bitField0_ &= -5;
                    this.dataType_ = ObjectiveInfo.getDefaultInstance().getDataType();
                    onChanged();
                    return this;
                }

                public Builder setDataTypeBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 4;
                    this.dataType_ = byteString;
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

            public static ObjectiveInfo getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ObjectiveInfo> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<ObjectiveInfo> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ObjectiveInfo getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class Metadata extends GeneratedMessageV3 implements MetadataOrBuilder {
            public static final int COURSEMSG_FIELD_NUMBER = 1;
            private static final Metadata DEFAULT_INSTANCE = new Metadata();

            @Deprecated
            public static final Parser<Metadata> PARSER = new AbstractParser<Metadata>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.1
                @Override // com.google.protobuf.Parser
                public Metadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Metadata(codedInputStream, extensionRegistryLite);
                }
            };
            public static final int PLANMETADATA_FIELD_NUMBER = 2;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private CourseMetadata courseMsg_;
            private byte memoizedIsInitialized;
            private PlanMetadata planMetadata_;

            private Metadata(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private Metadata() {
                this.memoizedIsInitialized = (byte) -1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new Metadata();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private Metadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        CourseMetadata.Builder builder = (this.bitField0_ & 1) != 0 ? this.courseMsg_.toBuilder() : null;
                                        CourseMetadata courseMetadata = (CourseMetadata) codedInputStream.readMessage(CourseMetadata.PARSER, extensionRegistryLite);
                                        this.courseMsg_ = courseMetadata;
                                        if (builder != null) {
                                            builder.mergeFrom(courseMetadata);
                                            this.courseMsg_ = builder.buildPartial();
                                        }
                                        this.bitField0_ |= 1;
                                    } else if (readTag == 18) {
                                        PlanMetadata.Builder builder2 = (this.bitField0_ & 2) != 0 ? this.planMetadata_.toBuilder() : null;
                                        PlanMetadata planMetadata = (PlanMetadata) codedInputStream.readMessage(PlanMetadata.PARSER, extensionRegistryLite);
                                        this.planMetadata_ = planMetadata;
                                        if (builder2 != null) {
                                            builder2.mergeFrom(planMetadata);
                                            this.planMetadata_ = builder2.buildPartial();
                                        }
                                        this.bitField0_ |= 2;
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
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Metadata_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
            public boolean hasCourseMsg() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
            public CourseMetadata getCourseMsg() {
                CourseMetadata courseMetadata = this.courseMsg_;
                return courseMetadata == null ? CourseMetadata.getDefaultInstance() : courseMetadata;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
            public CourseMetadataOrBuilder getCourseMsgOrBuilder() {
                CourseMetadata courseMetadata = this.courseMsg_;
                return courseMetadata == null ? CourseMetadata.getDefaultInstance() : courseMetadata;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
            public boolean hasPlanMetadata() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
            public PlanMetadata getPlanMetadata() {
                PlanMetadata planMetadata = this.planMetadata_;
                return planMetadata == null ? PlanMetadata.getDefaultInstance() : planMetadata;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
            public PlanMetadataOrBuilder getPlanMetadataOrBuilder() {
                PlanMetadata planMetadata = this.planMetadata_;
                return planMetadata == null ? PlanMetadata.getDefaultInstance() : planMetadata;
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
                if (hasCourseMsg() && !getCourseMsg().isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (hasPlanMetadata() && !getPlanMetadata().isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    codedOutputStream.writeMessage(1, getCourseMsg());
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeMessage(2, getPlanMetadata());
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeMessageSize = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeMessageSize(1, getCourseMsg()) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeMessageSize += CodedOutputStream.computeMessageSize(2, getPlanMetadata());
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
                if (!(obj instanceof Metadata)) {
                    return super.equals(obj);
                }
                Metadata metadata = (Metadata) obj;
                if (hasCourseMsg() != metadata.hasCourseMsg()) {
                    return false;
                }
                if ((!hasCourseMsg() || getCourseMsg().equals(metadata.getCourseMsg())) && hasPlanMetadata() == metadata.hasPlanMetadata()) {
                    return (!hasPlanMetadata() || getPlanMetadata().equals(metadata.getPlanMetadata())) && this.unknownFields.equals(metadata.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasCourseMsg()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getCourseMsg().hashCode();
                }
                if (hasPlanMetadata()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getPlanMetadata().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static Metadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static Metadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Metadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static Metadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Metadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static Metadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Metadata parseFrom(InputStream inputStream) throws IOException {
                return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Metadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Metadata parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Metadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Metadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Metadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Metadata parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Metadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Metadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Metadata metadata) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(metadata);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataOrBuilder {
                private int bitField0_;
                private SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> courseMsgBuilder_;
                private CourseMetadata courseMsg_;
                private SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> planMetadataBuilder_;
                private PlanMetadata planMetadata_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Metadata_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Metadata_fieldAccessorTable.ensureFieldAccessorsInitialized(Metadata.class, Builder.class);
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    if (Metadata.alwaysUseFieldBuilders) {
                        getCourseMsgFieldBuilder();
                        getPlanMetadataFieldBuilder();
                    }
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.courseMsg_ = null;
                    } else {
                        singleFieldBuilderV3.clear();
                    }
                    this.bitField0_ &= -2;
                    SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV32 = this.planMetadataBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        this.planMetadata_ = null;
                    } else {
                        singleFieldBuilderV32.clear();
                    }
                    this.bitField0_ &= -3;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_Metadata_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public Metadata getDefaultInstanceForType() {
                    return Metadata.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Metadata build() {
                    Metadata buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Metadata buildPartial() {
                    int i;
                    Metadata metadata = new Metadata(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            metadata.courseMsg_ = this.courseMsg_;
                        } else {
                            metadata.courseMsg_ = singleFieldBuilderV3.build();
                        }
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV32 = this.planMetadataBuilder_;
                        if (singleFieldBuilderV32 == null) {
                            metadata.planMetadata_ = this.planMetadata_;
                        } else {
                            metadata.planMetadata_ = singleFieldBuilderV32.build();
                        }
                        i |= 2;
                    }
                    metadata.bitField0_ = i;
                    onBuilt();
                    return metadata;
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
                    if (message instanceof Metadata) {
                        return mergeFrom((Metadata) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Metadata metadata) {
                    if (metadata == Metadata.getDefaultInstance()) {
                        return this;
                    }
                    if (metadata.hasCourseMsg()) {
                        mergeCourseMsg(metadata.getCourseMsg());
                    }
                    if (metadata.hasPlanMetadata()) {
                        mergePlanMetadata(metadata.getPlanMetadata());
                    }
                    mergeUnknownFields(metadata.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (!hasCourseMsg() || getCourseMsg().isInitialized()) {
                        return !hasPlanMetadata() || getPlanMetadata().isInitialized();
                    }
                    return false;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Metadata> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Metadata r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Metadata) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Metadata r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Metadata) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Metadata$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
                public boolean hasCourseMsg() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
                public CourseMetadata getCourseMsg() {
                    SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        CourseMetadata courseMetadata = this.courseMsg_;
                        return courseMetadata == null ? CourseMetadata.getDefaultInstance() : courseMetadata;
                    }
                    return singleFieldBuilderV3.getMessage();
                }

                public Builder setCourseMsg(CourseMetadata courseMetadata) {
                    SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        courseMetadata.getClass();
                        this.courseMsg_ = courseMetadata;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(courseMetadata);
                    }
                    this.bitField0_ |= 1;
                    return this;
                }

                public Builder setCourseMsg(CourseMetadata.Builder builder) {
                    SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.courseMsg_ = builder.build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.build());
                    }
                    this.bitField0_ |= 1;
                    return this;
                }

                public Builder mergeCourseMsg(CourseMetadata courseMetadata) {
                    CourseMetadata courseMetadata2;
                    SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if ((this.bitField0_ & 1) != 0 && (courseMetadata2 = this.courseMsg_) != null && courseMetadata2 != CourseMetadata.getDefaultInstance()) {
                            this.courseMsg_ = CourseMetadata.newBuilder(this.courseMsg_).mergeFrom(courseMetadata).buildPartial();
                        } else {
                            this.courseMsg_ = courseMetadata;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(courseMetadata);
                    }
                    this.bitField0_ |= 1;
                    return this;
                }

                public Builder clearCourseMsg() {
                    SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.courseMsg_ = null;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.clear();
                    }
                    this.bitField0_ &= -2;
                    return this;
                }

                public CourseMetadata.Builder getCourseMsgBuilder() {
                    this.bitField0_ |= 1;
                    onChanged();
                    return getCourseMsgFieldBuilder().getBuilder();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
                public CourseMetadataOrBuilder getCourseMsgOrBuilder() {
                    SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> singleFieldBuilderV3 = this.courseMsgBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    CourseMetadata courseMetadata = this.courseMsg_;
                    return courseMetadata == null ? CourseMetadata.getDefaultInstance() : courseMetadata;
                }

                private SingleFieldBuilderV3<CourseMetadata, CourseMetadata.Builder, CourseMetadataOrBuilder> getCourseMsgFieldBuilder() {
                    if (this.courseMsgBuilder_ == null) {
                        this.courseMsgBuilder_ = new SingleFieldBuilderV3<>(getCourseMsg(), getParentForChildren(), isClean());
                        this.courseMsg_ = null;
                    }
                    return this.courseMsgBuilder_;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
                public boolean hasPlanMetadata() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
                public PlanMetadata getPlanMetadata() {
                    SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV3 = this.planMetadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        PlanMetadata planMetadata = this.planMetadata_;
                        return planMetadata == null ? PlanMetadata.getDefaultInstance() : planMetadata;
                    }
                    return singleFieldBuilderV3.getMessage();
                }

                public Builder setPlanMetadata(PlanMetadata planMetadata) {
                    SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV3 = this.planMetadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        planMetadata.getClass();
                        this.planMetadata_ = planMetadata;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(planMetadata);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setPlanMetadata(PlanMetadata.Builder builder) {
                    SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV3 = this.planMetadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.planMetadata_ = builder.build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.build());
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder mergePlanMetadata(PlanMetadata planMetadata) {
                    PlanMetadata planMetadata2;
                    SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV3 = this.planMetadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if ((this.bitField0_ & 2) != 0 && (planMetadata2 = this.planMetadata_) != null && planMetadata2 != PlanMetadata.getDefaultInstance()) {
                            this.planMetadata_ = PlanMetadata.newBuilder(this.planMetadata_).mergeFrom(planMetadata).buildPartial();
                        } else {
                            this.planMetadata_ = planMetadata;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(planMetadata);
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder clearPlanMetadata() {
                    SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV3 = this.planMetadataBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.planMetadata_ = null;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.clear();
                    }
                    this.bitField0_ &= -3;
                    return this;
                }

                public PlanMetadata.Builder getPlanMetadataBuilder() {
                    this.bitField0_ |= 2;
                    onChanged();
                    return getPlanMetadataFieldBuilder().getBuilder();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.MetadataOrBuilder
                public PlanMetadataOrBuilder getPlanMetadataOrBuilder() {
                    SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> singleFieldBuilderV3 = this.planMetadataBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    PlanMetadata planMetadata = this.planMetadata_;
                    return planMetadata == null ? PlanMetadata.getDefaultInstance() : planMetadata;
                }

                private SingleFieldBuilderV3<PlanMetadata, PlanMetadata.Builder, PlanMetadataOrBuilder> getPlanMetadataFieldBuilder() {
                    if (this.planMetadataBuilder_ == null) {
                        this.planMetadataBuilder_ = new SingleFieldBuilderV3<>(getPlanMetadata(), getParentForChildren(), isClean());
                        this.planMetadata_ = null;
                    }
                    return this.planMetadataBuilder_;
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

            public static Metadata getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Metadata> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<Metadata> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Metadata getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class CourseMetadata extends GeneratedMessageV3 implements CourseMetadataOrBuilder {
            public static final int COURSEID_FIELD_NUMBER = 7;
            public static final int COURSEINDEX_FIELD_NUMBER = 2;
            public static final int COURSENAME_FIELD_NUMBER = 8;
            public static final int COURSETYPE_FIELD_NUMBER = 4;
            public static final int DAYORDER_FIELD_NUMBER = 1;
            public static final int ISCOURSEPUNCH_FIELD_NUMBER = 6;
            public static final int ISDAYPUNCH_FIELD_NUMBER = 3;
            public static final int UPDATETIME_FIELD_NUMBER = 5;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private volatile Object courseId_;
            private int courseIndex_;
            private Internal.IntList courseName_;
            private int courseType_;
            private int dayOrder_;
            private int isCoursePunch_;
            private int isDayPunch_;
            private byte memoizedIsInitialized;
            private int updateTime_;
            private static final CourseMetadata DEFAULT_INSTANCE = new CourseMetadata();

            @Deprecated
            public static final Parser<CourseMetadata> PARSER = new AbstractParser<CourseMetadata>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata.1
                @Override // com.google.protobuf.Parser
                public CourseMetadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new CourseMetadata(codedInputStream, extensionRegistryLite);
                }
            };

            private CourseMetadata(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private CourseMetadata() {
                this.memoizedIsInitialized = (byte) -1;
                this.courseId_ = "";
                this.courseName_ = emptyIntList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new CourseMetadata();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private CourseMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.dayOrder_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.courseIndex_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.isDayPunch_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 8;
                                    this.courseType_ = codedInputStream.readUInt32();
                                } else if (readTag == 40) {
                                    this.bitField0_ |= 16;
                                    this.updateTime_ = codedInputStream.readUInt32();
                                } else if (readTag == 48) {
                                    this.bitField0_ |= 32;
                                    this.isCoursePunch_ = codedInputStream.readUInt32();
                                } else if (readTag == 58) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ |= 64;
                                    this.courseId_ = readBytes;
                                } else if (readTag == 64) {
                                    if ((c & 128) == 0) {
                                        this.courseName_ = newIntList();
                                        c = 128;
                                    }
                                    this.courseName_.addInt(codedInputStream.readUInt32());
                                } else if (readTag == 66) {
                                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                    if ((c & 128) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                        this.courseName_ = newIntList();
                                        c = 128;
                                    }
                                    while (codedInputStream.getBytesUntilLimit() > 0) {
                                        this.courseName_.addInt(codedInputStream.readUInt32());
                                    }
                                    codedInputStream.popLimit(pushLimit);
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
                        if ((c & 128) != 0) {
                            this.courseName_.makeImmutable();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_CourseMetadata_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_CourseMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(CourseMetadata.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public boolean hasDayOrder() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getDayOrder() {
                return this.dayOrder_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public boolean hasCourseIndex() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getCourseIndex() {
                return this.courseIndex_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public boolean hasIsDayPunch() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getIsDayPunch() {
                return this.isDayPunch_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public boolean hasCourseType() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getCourseType() {
                return this.courseType_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public boolean hasUpdateTime() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getUpdateTime() {
                return this.updateTime_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public boolean hasIsCoursePunch() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getIsCoursePunch() {
                return this.isCoursePunch_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public boolean hasCourseId() {
                return (this.bitField0_ & 64) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
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

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public ByteString getCourseIdBytes() {
                Object obj = this.courseId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.courseId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public List<Integer> getCourseNameList() {
                return this.courseName_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getCourseNameCount() {
                return this.courseName_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
            public int getCourseName(int i) {
                return this.courseName_.getInt(i);
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
                if (!hasDayOrder()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasIsDayPunch()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasCourseType()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasUpdateTime()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasIsCoursePunch()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasCourseId()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) != 0) {
                    codedOutputStream.writeUInt32(1, this.dayOrder_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeUInt32(2, this.courseIndex_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeUInt32(3, this.isDayPunch_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeUInt32(4, this.courseType_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    codedOutputStream.writeUInt32(5, this.updateTime_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    codedOutputStream.writeUInt32(6, this.isCoursePunch_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    GeneratedMessageV3.writeString(codedOutputStream, 7, this.courseId_);
                }
                for (int i = 0; i < this.courseName_.size(); i++) {
                    codedOutputStream.writeUInt32(8, this.courseName_.getInt(i));
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeUInt32Size = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeUInt32Size(1, this.dayOrder_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, this.courseIndex_);
                }
                if ((this.bitField0_ & 4) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(3, this.isDayPunch_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(4, this.courseType_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(5, this.updateTime_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(6, this.isCoursePunch_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    computeUInt32Size += GeneratedMessageV3.computeStringSize(7, this.courseId_);
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.courseName_.size(); i3++) {
                    i2 += CodedOutputStream.computeUInt32SizeNoTag(this.courseName_.getInt(i3));
                }
                int size = computeUInt32Size + i2 + getCourseNameList().size() + this.unknownFields.getSerializedSize();
                this.memoizedSize = size;
                return size;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof CourseMetadata)) {
                    return super.equals(obj);
                }
                CourseMetadata courseMetadata = (CourseMetadata) obj;
                if (hasDayOrder() != courseMetadata.hasDayOrder()) {
                    return false;
                }
                if ((hasDayOrder() && getDayOrder() != courseMetadata.getDayOrder()) || hasCourseIndex() != courseMetadata.hasCourseIndex()) {
                    return false;
                }
                if ((hasCourseIndex() && getCourseIndex() != courseMetadata.getCourseIndex()) || hasIsDayPunch() != courseMetadata.hasIsDayPunch()) {
                    return false;
                }
                if ((hasIsDayPunch() && getIsDayPunch() != courseMetadata.getIsDayPunch()) || hasCourseType() != courseMetadata.hasCourseType()) {
                    return false;
                }
                if ((hasCourseType() && getCourseType() != courseMetadata.getCourseType()) || hasUpdateTime() != courseMetadata.hasUpdateTime()) {
                    return false;
                }
                if ((hasUpdateTime() && getUpdateTime() != courseMetadata.getUpdateTime()) || hasIsCoursePunch() != courseMetadata.hasIsCoursePunch()) {
                    return false;
                }
                if ((!hasIsCoursePunch() || getIsCoursePunch() == courseMetadata.getIsCoursePunch()) && hasCourseId() == courseMetadata.hasCourseId()) {
                    return (!hasCourseId() || getCourseId().equals(courseMetadata.getCourseId())) && getCourseNameList().equals(courseMetadata.getCourseNameList()) && this.unknownFields.equals(courseMetadata.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasDayOrder()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getDayOrder();
                }
                if (hasCourseIndex()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getCourseIndex();
                }
                if (hasIsDayPunch()) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getIsDayPunch();
                }
                if (hasCourseType()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getCourseType();
                }
                if (hasUpdateTime()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getUpdateTime();
                }
                if (hasIsCoursePunch()) {
                    hashCode = (((hashCode * 37) + 6) * 53) + getIsCoursePunch();
                }
                if (hasCourseId()) {
                    hashCode = (((hashCode * 37) + 7) * 53) + getCourseId().hashCode();
                }
                if (getCourseNameCount() > 0) {
                    hashCode = (((hashCode * 37) + 8) * 53) + getCourseNameList().hashCode();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static CourseMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static CourseMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static CourseMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static CourseMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static CourseMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static CourseMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static CourseMetadata parseFrom(InputStream inputStream) throws IOException {
                return (CourseMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static CourseMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CourseMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CourseMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (CourseMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static CourseMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CourseMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CourseMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (CourseMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static CourseMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (CourseMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(CourseMetadata courseMetadata) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(courseMetadata);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CourseMetadataOrBuilder {
                private int bitField0_;
                private Object courseId_;
                private int courseIndex_;
                private Internal.IntList courseName_;
                private int courseType_;
                private int dayOrder_;
                private int isCoursePunch_;
                private int isDayPunch_;
                private int updateTime_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_CourseMetadata_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_CourseMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(CourseMetadata.class, Builder.class);
                }

                private Builder() {
                    this.courseId_ = "";
                    this.courseName_ = CourseMetadata.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.courseId_ = "";
                    this.courseName_ = CourseMetadata.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = CourseMetadata.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.dayOrder_ = 0;
                    int i = this.bitField0_;
                    this.courseIndex_ = 0;
                    this.isDayPunch_ = 0;
                    this.courseType_ = 0;
                    this.updateTime_ = 0;
                    this.isCoursePunch_ = 0;
                    this.courseId_ = "";
                    this.bitField0_ = i & (-128);
                    this.courseName_ = CourseMetadata.emptyIntList();
                    this.bitField0_ &= -129;
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_CourseMetadata_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public CourseMetadata getDefaultInstanceForType() {
                    return CourseMetadata.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public CourseMetadata build() {
                    CourseMetadata buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public CourseMetadata buildPartial() {
                    int i;
                    CourseMetadata courseMetadata = new CourseMetadata(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        courseMetadata.dayOrder_ = this.dayOrder_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        courseMetadata.courseIndex_ = this.courseIndex_;
                        i |= 2;
                    }
                    if ((i2 & 4) != 0) {
                        courseMetadata.isDayPunch_ = this.isDayPunch_;
                        i |= 4;
                    }
                    if ((i2 & 8) != 0) {
                        courseMetadata.courseType_ = this.courseType_;
                        i |= 8;
                    }
                    if ((i2 & 16) != 0) {
                        courseMetadata.updateTime_ = this.updateTime_;
                        i |= 16;
                    }
                    if ((i2 & 32) != 0) {
                        courseMetadata.isCoursePunch_ = this.isCoursePunch_;
                        i |= 32;
                    }
                    if ((i2 & 64) != 0) {
                        i |= 64;
                    }
                    courseMetadata.courseId_ = this.courseId_;
                    if ((this.bitField0_ & 128) != 0) {
                        this.courseName_.makeImmutable();
                        this.bitField0_ &= -129;
                    }
                    courseMetadata.courseName_ = this.courseName_;
                    courseMetadata.bitField0_ = i;
                    onBuilt();
                    return courseMetadata;
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
                    if (message instanceof CourseMetadata) {
                        return mergeFrom((CourseMetadata) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(CourseMetadata courseMetadata) {
                    if (courseMetadata == CourseMetadata.getDefaultInstance()) {
                        return this;
                    }
                    if (courseMetadata.hasDayOrder()) {
                        setDayOrder(courseMetadata.getDayOrder());
                    }
                    if (courseMetadata.hasCourseIndex()) {
                        setCourseIndex(courseMetadata.getCourseIndex());
                    }
                    if (courseMetadata.hasIsDayPunch()) {
                        setIsDayPunch(courseMetadata.getIsDayPunch());
                    }
                    if (courseMetadata.hasCourseType()) {
                        setCourseType(courseMetadata.getCourseType());
                    }
                    if (courseMetadata.hasUpdateTime()) {
                        setUpdateTime(courseMetadata.getUpdateTime());
                    }
                    if (courseMetadata.hasIsCoursePunch()) {
                        setIsCoursePunch(courseMetadata.getIsCoursePunch());
                    }
                    if (courseMetadata.hasCourseId()) {
                        this.bitField0_ |= 64;
                        this.courseId_ = courseMetadata.courseId_;
                        onChanged();
                    }
                    if (!courseMetadata.courseName_.isEmpty()) {
                        if (this.courseName_.isEmpty()) {
                            this.courseName_ = courseMetadata.courseName_;
                            this.bitField0_ &= -129;
                        } else {
                            ensureCourseNameIsMutable();
                            this.courseName_.addAll(courseMetadata.courseName_);
                        }
                        onChanged();
                    }
                    mergeUnknownFields(courseMetadata.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return hasDayOrder() && hasIsDayPunch() && hasCourseType() && hasUpdateTime() && hasIsCoursePunch() && hasCourseId();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$CourseMetadata> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$CourseMetadata r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$CourseMetadata r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$CourseMetadata$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public boolean hasDayOrder() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public int getDayOrder() {
                    return this.dayOrder_;
                }

                public Builder setDayOrder(int i) {
                    this.bitField0_ |= 1;
                    this.dayOrder_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearDayOrder() {
                    this.bitField0_ &= -2;
                    this.dayOrder_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public boolean hasCourseIndex() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public int getCourseIndex() {
                    return this.courseIndex_;
                }

                public Builder setCourseIndex(int i) {
                    this.bitField0_ |= 2;
                    this.courseIndex_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearCourseIndex() {
                    this.bitField0_ &= -3;
                    this.courseIndex_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public boolean hasIsDayPunch() {
                    return (this.bitField0_ & 4) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
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

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public boolean hasCourseType() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
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

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public boolean hasUpdateTime() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public int getUpdateTime() {
                    return this.updateTime_;
                }

                public Builder setUpdateTime(int i) {
                    this.bitField0_ |= 16;
                    this.updateTime_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearUpdateTime() {
                    this.bitField0_ &= -17;
                    this.updateTime_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public boolean hasIsCoursePunch() {
                    return (this.bitField0_ & 32) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public int getIsCoursePunch() {
                    return this.isCoursePunch_;
                }

                public Builder setIsCoursePunch(int i) {
                    this.bitField0_ |= 32;
                    this.isCoursePunch_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearIsCoursePunch() {
                    this.bitField0_ &= -33;
                    this.isCoursePunch_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public boolean hasCourseId() {
                    return (this.bitField0_ & 64) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
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

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
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
                    this.bitField0_ |= 64;
                    this.courseId_ = str;
                    onChanged();
                    return this;
                }

                public Builder clearCourseId() {
                    this.bitField0_ &= -65;
                    this.courseId_ = CourseMetadata.getDefaultInstance().getCourseId();
                    onChanged();
                    return this;
                }

                public Builder setCourseIdBytes(ByteString byteString) {
                    byteString.getClass();
                    this.bitField0_ |= 64;
                    this.courseId_ = byteString;
                    onChanged();
                    return this;
                }

                private void ensureCourseNameIsMutable() {
                    if ((this.bitField0_ & 128) == 0) {
                        this.courseName_ = CourseMetadata.mutableCopy(this.courseName_);
                        this.bitField0_ |= 128;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public List<Integer> getCourseNameList() {
                    return (this.bitField0_ & 128) != 0 ? Collections.unmodifiableList(this.courseName_) : this.courseName_;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
                public int getCourseNameCount() {
                    return this.courseName_.size();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadataOrBuilder
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
                    this.courseName_ = CourseMetadata.emptyIntList();
                    this.bitField0_ &= -129;
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

            public static CourseMetadata getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CourseMetadata> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<CourseMetadata> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CourseMetadata getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        public static final class PlanMetadata extends GeneratedMessageV3 implements PlanMetadataOrBuilder {
            public static final int CURRENTWEEKDAYS_FIELD_NUMBER = 5;
            public static final int EXERCISETIMES_FIELD_NUMBER = 4;
            public static final int PLANTOTALDAYS_FIELD_NUMBER = 1;
            public static final int PLANTOTALWEEKS_FIELD_NUMBER = 2;
            public static final int WEEKNUM_FIELD_NUMBER = 6;
            public static final int WEEKPLANENDTIME_FIELD_NUMBER = 8;
            public static final int WEEKPLANNAME_FIELD_NUMBER = 3;
            public static final int WEEKPLANSTARTTIME_FIELD_NUMBER = 7;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private int currentWeekDays_;
            private int exerciseTimes_;
            private byte memoizedIsInitialized;
            private int planTotalDays_;
            private int planTotalWeeks_;
            private int weekNum_;
            private int weekPlanEndTime_;
            private Internal.IntList weekPlanName_;
            private int weekPlanStartTime_;
            private static final PlanMetadata DEFAULT_INSTANCE = new PlanMetadata();

            @Deprecated
            public static final Parser<PlanMetadata> PARSER = new AbstractParser<PlanMetadata>() { // from class: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata.1
                @Override // com.google.protobuf.Parser
                public PlanMetadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new PlanMetadata(codedInputStream, extensionRegistryLite);
                }
            };

            private PlanMetadata(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private PlanMetadata() {
                this.memoizedIsInitialized = (byte) -1;
                this.weekPlanName_ = emptyIntList();
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
                return new PlanMetadata();
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            private PlanMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (readTag == 8) {
                                        this.bitField0_ |= 1;
                                        this.planTotalDays_ = codedInputStream.readUInt32();
                                    } else if (readTag == 16) {
                                        this.bitField0_ |= 2;
                                        this.planTotalWeeks_ = codedInputStream.readUInt32();
                                    } else if (readTag == 24) {
                                        if ((c & 4) == 0) {
                                            this.weekPlanName_ = newIntList();
                                            c = 4;
                                        }
                                        this.weekPlanName_.addInt(codedInputStream.readUInt32());
                                    } else if (readTag == 26) {
                                        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                        if ((c & 4) == 0 && codedInputStream.getBytesUntilLimit() > 0) {
                                            this.weekPlanName_ = newIntList();
                                            c = 4;
                                        }
                                        while (codedInputStream.getBytesUntilLimit() > 0) {
                                            this.weekPlanName_.addInt(codedInputStream.readUInt32());
                                        }
                                        codedInputStream.popLimit(pushLimit);
                                    } else if (readTag == 32) {
                                        this.bitField0_ |= 4;
                                        this.exerciseTimes_ = codedInputStream.readUInt32();
                                    } else if (readTag == 40) {
                                        this.bitField0_ |= 8;
                                        this.currentWeekDays_ = codedInputStream.readUInt32();
                                    } else if (readTag == 48) {
                                        this.bitField0_ |= 16;
                                        this.weekNum_ = codedInputStream.readUInt32();
                                    } else if (readTag == 56) {
                                        this.bitField0_ |= 32;
                                        this.weekPlanStartTime_ = codedInputStream.readUInt32();
                                    } else if (readTag == 64) {
                                        this.bitField0_ |= 64;
                                        this.weekPlanEndTime_ = codedInputStream.readUInt32();
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
                        if ((c & 4) != 0) {
                            this.weekPlanName_.makeImmutable();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_PlanMetadata_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_PlanMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanMetadata.class, Builder.class);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public boolean hasPlanTotalDays() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getPlanTotalDays() {
                return this.planTotalDays_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public boolean hasPlanTotalWeeks() {
                return (this.bitField0_ & 2) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getPlanTotalWeeks() {
                return this.planTotalWeeks_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public List<Integer> getWeekPlanNameList() {
                return this.weekPlanName_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getWeekPlanNameCount() {
                return this.weekPlanName_.size();
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getWeekPlanName(int i) {
                return this.weekPlanName_.getInt(i);
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public boolean hasExerciseTimes() {
                return (this.bitField0_ & 4) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getExerciseTimes() {
                return this.exerciseTimes_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public boolean hasCurrentWeekDays() {
                return (this.bitField0_ & 8) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getCurrentWeekDays() {
                return this.currentWeekDays_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public boolean hasWeekNum() {
                return (this.bitField0_ & 16) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getWeekNum() {
                return this.weekNum_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public boolean hasWeekPlanStartTime() {
                return (this.bitField0_ & 32) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public int getWeekPlanStartTime() {
                return this.weekPlanStartTime_;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
            public boolean hasWeekPlanEndTime() {
                return (this.bitField0_ & 64) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
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
                if (!hasPlanTotalDays()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasPlanTotalWeeks()) {
                    this.memoizedIsInitialized = (byte) 0;
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
                if ((this.bitField0_ & 1) != 0) {
                    codedOutputStream.writeUInt32(1, this.planTotalDays_);
                }
                if ((this.bitField0_ & 2) != 0) {
                    codedOutputStream.writeUInt32(2, this.planTotalWeeks_);
                }
                for (int i = 0; i < this.weekPlanName_.size(); i++) {
                    codedOutputStream.writeUInt32(3, this.weekPlanName_.getInt(i));
                }
                if ((this.bitField0_ & 4) != 0) {
                    codedOutputStream.writeUInt32(4, this.exerciseTimes_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    codedOutputStream.writeUInt32(5, this.currentWeekDays_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    codedOutputStream.writeUInt32(6, this.weekNum_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    codedOutputStream.writeUInt32(7, this.weekPlanStartTime_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    codedOutputStream.writeUInt32(8, this.weekPlanEndTime_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int computeUInt32Size = (this.bitField0_ & 1) != 0 ? CodedOutputStream.computeUInt32Size(1, this.planTotalDays_) : 0;
                if ((this.bitField0_ & 2) != 0) {
                    computeUInt32Size += CodedOutputStream.computeUInt32Size(2, this.planTotalWeeks_);
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.weekPlanName_.size(); i3++) {
                    i2 += CodedOutputStream.computeUInt32SizeNoTag(this.weekPlanName_.getInt(i3));
                }
                int size = computeUInt32Size + i2 + getWeekPlanNameList().size();
                if ((this.bitField0_ & 4) != 0) {
                    size += CodedOutputStream.computeUInt32Size(4, this.exerciseTimes_);
                }
                if ((this.bitField0_ & 8) != 0) {
                    size += CodedOutputStream.computeUInt32Size(5, this.currentWeekDays_);
                }
                if ((this.bitField0_ & 16) != 0) {
                    size += CodedOutputStream.computeUInt32Size(6, this.weekNum_);
                }
                if ((this.bitField0_ & 32) != 0) {
                    size += CodedOutputStream.computeUInt32Size(7, this.weekPlanStartTime_);
                }
                if ((this.bitField0_ & 64) != 0) {
                    size += CodedOutputStream.computeUInt32Size(8, this.weekPlanEndTime_);
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
                if (!(obj instanceof PlanMetadata)) {
                    return super.equals(obj);
                }
                PlanMetadata planMetadata = (PlanMetadata) obj;
                if (hasPlanTotalDays() != planMetadata.hasPlanTotalDays()) {
                    return false;
                }
                if ((hasPlanTotalDays() && getPlanTotalDays() != planMetadata.getPlanTotalDays()) || hasPlanTotalWeeks() != planMetadata.hasPlanTotalWeeks()) {
                    return false;
                }
                if ((hasPlanTotalWeeks() && getPlanTotalWeeks() != planMetadata.getPlanTotalWeeks()) || !getWeekPlanNameList().equals(planMetadata.getWeekPlanNameList()) || hasExerciseTimes() != planMetadata.hasExerciseTimes()) {
                    return false;
                }
                if ((hasExerciseTimes() && getExerciseTimes() != planMetadata.getExerciseTimes()) || hasCurrentWeekDays() != planMetadata.hasCurrentWeekDays()) {
                    return false;
                }
                if ((hasCurrentWeekDays() && getCurrentWeekDays() != planMetadata.getCurrentWeekDays()) || hasWeekNum() != planMetadata.hasWeekNum()) {
                    return false;
                }
                if ((hasWeekNum() && getWeekNum() != planMetadata.getWeekNum()) || hasWeekPlanStartTime() != planMetadata.hasWeekPlanStartTime()) {
                    return false;
                }
                if ((!hasWeekPlanStartTime() || getWeekPlanStartTime() == planMetadata.getWeekPlanStartTime()) && hasWeekPlanEndTime() == planMetadata.hasWeekPlanEndTime()) {
                    return (!hasWeekPlanEndTime() || getWeekPlanEndTime() == planMetadata.getWeekPlanEndTime()) && this.unknownFields.equals(planMetadata.unknownFields);
                }
                return false;
            }

            @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = getDescriptor().hashCode() + 779;
                if (hasPlanTotalDays()) {
                    hashCode = (((hashCode * 37) + 1) * 53) + getPlanTotalDays();
                }
                if (hasPlanTotalWeeks()) {
                    hashCode = (((hashCode * 37) + 2) * 53) + getPlanTotalWeeks();
                }
                if (getWeekPlanNameCount() > 0) {
                    hashCode = (((hashCode * 37) + 3) * 53) + getWeekPlanNameList().hashCode();
                }
                if (hasExerciseTimes()) {
                    hashCode = (((hashCode * 37) + 4) * 53) + getExerciseTimes();
                }
                if (hasCurrentWeekDays()) {
                    hashCode = (((hashCode * 37) + 5) * 53) + getCurrentWeekDays();
                }
                if (hasWeekNum()) {
                    hashCode = (((hashCode * 37) + 6) * 53) + getWeekNum();
                }
                if (hasWeekPlanStartTime()) {
                    hashCode = (((hashCode * 37) + 7) * 53) + getWeekPlanStartTime();
                }
                if (hasWeekPlanEndTime()) {
                    hashCode = (((hashCode * 37) + 8) * 53) + getWeekPlanEndTime();
                }
                int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            public static PlanMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static PlanMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static PlanMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static PlanMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static PlanMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static PlanMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static PlanMetadata parseFrom(InputStream inputStream) throws IOException {
                return (PlanMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static PlanMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (PlanMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static PlanMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (PlanMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static PlanMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (PlanMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static PlanMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (PlanMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static PlanMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (PlanMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(PlanMetadata planMetadata) {
                return DEFAULT_INSTANCE.toBuilder().mergeFrom(planMetadata);
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

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PlanMetadataOrBuilder {
                private int bitField0_;
                private int currentWeekDays_;
                private int exerciseTimes_;
                private int planTotalDays_;
                private int planTotalWeeks_;
                private int weekNum_;
                private int weekPlanEndTime_;
                private Internal.IntList weekPlanName_;
                private int weekPlanStartTime_;

                public static final Descriptors.Descriptor getDescriptor() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_PlanMetadata_descriptor;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_PlanMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(PlanMetadata.class, Builder.class);
                }

                private Builder() {
                    this.weekPlanName_ = PlanMetadata.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.weekPlanName_ = PlanMetadata.emptyIntList();
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = PlanMetadata.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public Builder clear() {
                    super.clear();
                    this.planTotalDays_ = 0;
                    int i = this.bitField0_;
                    this.planTotalWeeks_ = 0;
                    this.bitField0_ = i & (-4);
                    this.weekPlanName_ = PlanMetadata.emptyIntList();
                    int i2 = this.bitField0_;
                    this.exerciseTimes_ = 0;
                    this.currentWeekDays_ = 0;
                    this.weekNum_ = 0;
                    this.weekPlanStartTime_ = 0;
                    this.weekPlanEndTime_ = 0;
                    this.bitField0_ = i2 & (-253);
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_PlanMetadata_descriptor;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
                public PlanMetadata getDefaultInstanceForType() {
                    return PlanMetadata.getDefaultInstance();
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public PlanMetadata build() {
                    PlanMetadata buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException((Message) buildPartial);
                }

                @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                public PlanMetadata buildPartial() {
                    int i;
                    PlanMetadata planMetadata = new PlanMetadata(this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 0) {
                        planMetadata.planTotalDays_ = this.planTotalDays_;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        planMetadata.planTotalWeeks_ = this.planTotalWeeks_;
                        i |= 2;
                    }
                    if ((this.bitField0_ & 4) != 0) {
                        this.weekPlanName_.makeImmutable();
                        this.bitField0_ &= -5;
                    }
                    planMetadata.weekPlanName_ = this.weekPlanName_;
                    if ((i2 & 8) != 0) {
                        planMetadata.exerciseTimes_ = this.exerciseTimes_;
                        i |= 4;
                    }
                    if ((i2 & 16) != 0) {
                        planMetadata.currentWeekDays_ = this.currentWeekDays_;
                        i |= 8;
                    }
                    if ((i2 & 32) != 0) {
                        planMetadata.weekNum_ = this.weekNum_;
                        i |= 16;
                    }
                    if ((i2 & 64) != 0) {
                        planMetadata.weekPlanStartTime_ = this.weekPlanStartTime_;
                        i |= 32;
                    }
                    if ((i2 & 128) != 0) {
                        planMetadata.weekPlanEndTime_ = this.weekPlanEndTime_;
                        i |= 64;
                    }
                    planMetadata.bitField0_ = i;
                    onBuilt();
                    return planMetadata;
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
                    if (message instanceof PlanMetadata) {
                        return mergeFrom((PlanMetadata) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(PlanMetadata planMetadata) {
                    if (planMetadata == PlanMetadata.getDefaultInstance()) {
                        return this;
                    }
                    if (planMetadata.hasPlanTotalDays()) {
                        setPlanTotalDays(planMetadata.getPlanTotalDays());
                    }
                    if (planMetadata.hasPlanTotalWeeks()) {
                        setPlanTotalWeeks(planMetadata.getPlanTotalWeeks());
                    }
                    if (!planMetadata.weekPlanName_.isEmpty()) {
                        if (this.weekPlanName_.isEmpty()) {
                            this.weekPlanName_ = planMetadata.weekPlanName_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureWeekPlanNameIsMutable();
                            this.weekPlanName_.addAll(planMetadata.weekPlanName_);
                        }
                        onChanged();
                    }
                    if (planMetadata.hasExerciseTimes()) {
                        setExerciseTimes(planMetadata.getExerciseTimes());
                    }
                    if (planMetadata.hasCurrentWeekDays()) {
                        setCurrentWeekDays(planMetadata.getCurrentWeekDays());
                    }
                    if (planMetadata.hasWeekNum()) {
                        setWeekNum(planMetadata.getWeekNum());
                    }
                    if (planMetadata.hasWeekPlanStartTime()) {
                        setWeekPlanStartTime(planMetadata.getWeekPlanStartTime());
                    }
                    if (planMetadata.hasWeekPlanEndTime()) {
                        setWeekPlanEndTime(planMetadata.getWeekPlanEndTime());
                    }
                    mergeUnknownFields(planMetadata.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return hasPlanTotalDays() && hasPlanTotalWeeks() && hasExerciseTimes() && hasCurrentWeekDays() && hasWeekNum() && hasWeekPlanStartTime() && hasWeekPlanEndTime();
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
                @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                    /*
                        r1 = this;
                        com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$PlanMetadata> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$PlanMetadata r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                        com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$PlanMetadata r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata) r3     // Catch: java.lang.Throwable -> Le
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
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$PlanMetadata$Builder");
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public boolean hasPlanTotalDays() {
                    return (this.bitField0_ & 1) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getPlanTotalDays() {
                    return this.planTotalDays_;
                }

                public Builder setPlanTotalDays(int i) {
                    this.bitField0_ |= 1;
                    this.planTotalDays_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearPlanTotalDays() {
                    this.bitField0_ &= -2;
                    this.planTotalDays_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public boolean hasPlanTotalWeeks() {
                    return (this.bitField0_ & 2) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getPlanTotalWeeks() {
                    return this.planTotalWeeks_;
                }

                public Builder setPlanTotalWeeks(int i) {
                    this.bitField0_ |= 2;
                    this.planTotalWeeks_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearPlanTotalWeeks() {
                    this.bitField0_ &= -3;
                    this.planTotalWeeks_ = 0;
                    onChanged();
                    return this;
                }

                private void ensureWeekPlanNameIsMutable() {
                    if ((this.bitField0_ & 4) == 0) {
                        this.weekPlanName_ = PlanMetadata.mutableCopy(this.weekPlanName_);
                        this.bitField0_ |= 4;
                    }
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public List<Integer> getWeekPlanNameList() {
                    return (this.bitField0_ & 4) != 0 ? Collections.unmodifiableList(this.weekPlanName_) : this.weekPlanName_;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getWeekPlanNameCount() {
                    return this.weekPlanName_.size();
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
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
                    this.weekPlanName_ = PlanMetadata.emptyIntList();
                    this.bitField0_ &= -5;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public boolean hasExerciseTimes() {
                    return (this.bitField0_ & 8) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getExerciseTimes() {
                    return this.exerciseTimes_;
                }

                public Builder setExerciseTimes(int i) {
                    this.bitField0_ |= 8;
                    this.exerciseTimes_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearExerciseTimes() {
                    this.bitField0_ &= -9;
                    this.exerciseTimes_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public boolean hasCurrentWeekDays() {
                    return (this.bitField0_ & 16) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getCurrentWeekDays() {
                    return this.currentWeekDays_;
                }

                public Builder setCurrentWeekDays(int i) {
                    this.bitField0_ |= 16;
                    this.currentWeekDays_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearCurrentWeekDays() {
                    this.bitField0_ &= -17;
                    this.currentWeekDays_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public boolean hasWeekNum() {
                    return (this.bitField0_ & 32) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getWeekNum() {
                    return this.weekNum_;
                }

                public Builder setWeekNum(int i) {
                    this.bitField0_ |= 32;
                    this.weekNum_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearWeekNum() {
                    this.bitField0_ &= -33;
                    this.weekNum_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public boolean hasWeekPlanStartTime() {
                    return (this.bitField0_ & 64) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getWeekPlanStartTime() {
                    return this.weekPlanStartTime_;
                }

                public Builder setWeekPlanStartTime(int i) {
                    this.bitField0_ |= 64;
                    this.weekPlanStartTime_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearWeekPlanStartTime() {
                    this.bitField0_ &= -65;
                    this.weekPlanStartTime_ = 0;
                    onChanged();
                    return this;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public boolean hasWeekPlanEndTime() {
                    return (this.bitField0_ & 128) != 0;
                }

                @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadataOrBuilder
                public int getWeekPlanEndTime() {
                    return this.weekPlanEndTime_;
                }

                public Builder setWeekPlanEndTime(int i) {
                    this.bitField0_ |= 128;
                    this.weekPlanEndTime_ = i;
                    onChanged();
                    return this;
                }

                public Builder clearWeekPlanEndTime() {
                    this.bitField0_ &= -129;
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

            public static PlanMetadata getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<PlanMetadata> parser() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
            public Parser<PlanMetadata> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PlanMetadata getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }
        }

        @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfoOrBuilder
        public boolean hasPlanBasicInfo() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfoOrBuilder
        public Plan getPlanBasicInfo() {
            Plan plan = this.planBasicInfo_;
            return plan == null ? Plan.getDefaultInstance() : plan;
        }

        @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfoOrBuilder
        public PlanOrBuilder getPlanBasicInfoOrBuilder() {
            Plan plan = this.planBasicInfo_;
            return plan == null ? Plan.getDefaultInstance() : plan;
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
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) != 0) {
                codedOutputStream.writeMessage(1, getPlanBasicInfo());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = ((this.bitField0_ & 1) != 0 ? CodedOutputStream.computeMessageSize(1, getPlanBasicInfo()) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeMessageSize;
            return computeMessageSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GeneralPlanInfo)) {
                return super.equals(obj);
            }
            GeneralPlanInfo generalPlanInfo = (GeneralPlanInfo) obj;
            if (hasPlanBasicInfo() != generalPlanInfo.hasPlanBasicInfo()) {
                return false;
            }
            return (!hasPlanBasicInfo() || getPlanBasicInfo().equals(generalPlanInfo.getPlanBasicInfo())) && this.unknownFields.equals(generalPlanInfo.unknownFields);
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
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static GeneralPlanInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static GeneralPlanInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GeneralPlanInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static GeneralPlanInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GeneralPlanInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static GeneralPlanInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GeneralPlanInfo parseFrom(InputStream inputStream) throws IOException {
            return (GeneralPlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GeneralPlanInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralPlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GeneralPlanInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GeneralPlanInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GeneralPlanInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralPlanInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GeneralPlanInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GeneralPlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GeneralPlanInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GeneralPlanInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GeneralPlanInfo generalPlanInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(generalPlanInfo);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GeneralPlanInfoOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> planBasicInfoBuilder_;
            private Plan planBasicInfo_;

            public static final Descriptors.Descriptor getDescriptor() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            public GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(GeneralPlanInfo.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (GeneralPlanInfo.alwaysUseFieldBuilders) {
                    getPlanBasicInfoFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.planBasicInfo_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return GeneralPlanDataForDevice.internal_static_GeneralPlanInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public GeneralPlanInfo getDefaultInstanceForType() {
                return GeneralPlanInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneralPlanInfo build() {
                GeneralPlanInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GeneralPlanInfo buildPartial() {
                GeneralPlanInfo generalPlanInfo = new GeneralPlanInfo(this);
                int i = 1;
                if ((this.bitField0_ & 1) != 0) {
                    SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        generalPlanInfo.planBasicInfo_ = this.planBasicInfo_;
                    } else {
                        generalPlanInfo.planBasicInfo_ = singleFieldBuilderV3.build();
                    }
                } else {
                    i = 0;
                }
                generalPlanInfo.bitField0_ = i;
                onBuilt();
                return generalPlanInfo;
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
                if (message instanceof GeneralPlanInfo) {
                    return mergeFrom((GeneralPlanInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GeneralPlanInfo generalPlanInfo) {
                if (generalPlanInfo == GeneralPlanInfo.getDefaultInstance()) {
                    return this;
                }
                if (generalPlanInfo.hasPlanBasicInfo()) {
                    mergePlanBasicInfo(generalPlanInfo.getPlanBasicInfo());
                }
                mergeUnknownFields(generalPlanInfo.unknownFields);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasPlanBasicInfo() && getPlanBasicInfo().isInitialized();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r2, com.google.protobuf.ExtensionRegistryLite r3) throws java.io.IOException {
                /*
                    r1 = this;
                    com.google.protobuf.Parser<com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo> r0 = com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.PARSER     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    java.lang.Object r2 = r0.parsePartialFrom(r2, r3)     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
                    com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo r2 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo) r2     // Catch: java.lang.Throwable -> Le com.google.protobuf.InvalidProtocolBufferException -> L10
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
                    com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo r3 = (com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo) r3     // Catch: java.lang.Throwable -> Le
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
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice$GeneralPlanInfo$Builder");
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfoOrBuilder
            public boolean hasPlanBasicInfo() {
                return (this.bitField0_ & 1) != 0;
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfoOrBuilder
            public Plan getPlanBasicInfo() {
                SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Plan plan = this.planBasicInfo_;
                    return plan == null ? Plan.getDefaultInstance() : plan;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setPlanBasicInfo(Plan plan) {
                SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    plan.getClass();
                    this.planBasicInfo_ = plan;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(plan);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setPlanBasicInfo(Plan.Builder builder) {
                SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.planBasicInfo_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergePlanBasicInfo(Plan plan) {
                Plan plan2;
                SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) != 0 && (plan2 = this.planBasicInfo_) != null && plan2 != Plan.getDefaultInstance()) {
                        this.planBasicInfo_ = Plan.newBuilder(this.planBasicInfo_).mergeFrom(plan).buildPartial();
                    } else {
                        this.planBasicInfo_ = plan;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(plan);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearPlanBasicInfo() {
                SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.planBasicInfo_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -2;
                return this;
            }

            public Plan.Builder getPlanBasicInfoBuilder() {
                this.bitField0_ |= 1;
                onChanged();
                return getPlanBasicInfoFieldBuilder().getBuilder();
            }

            @Override // com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice.GeneralPlanInfoOrBuilder
            public PlanOrBuilder getPlanBasicInfoOrBuilder() {
                SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> singleFieldBuilderV3 = this.planBasicInfoBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Plan plan = this.planBasicInfo_;
                return plan == null ? Plan.getDefaultInstance() : plan;
            }

            private SingleFieldBuilderV3<Plan, Plan.Builder, PlanOrBuilder> getPlanBasicInfoFieldBuilder() {
                if (this.planBasicInfoBuilder_ == null) {
                    this.planBasicInfoBuilder_ = new SingleFieldBuilderV3<>(getPlanBasicInfo(), getParentForChildren(), isClean());
                    this.planBasicInfo_ = null;
                }
                return this.planBasicInfoBuilder_;
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

        public static GeneralPlanInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GeneralPlanInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<GeneralPlanInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public GeneralPlanInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_GeneralPlanInfo_descriptor = descriptor2;
        internal_static_GeneralPlanInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"PlanBasicInfo"});
        Descriptors.Descriptor descriptor3 = descriptor2.getNestedTypes().get(0);
        internal_static_GeneralPlanInfo_Plan_descriptor = descriptor3;
        internal_static_GeneralPlanInfo_Plan_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"PlanId", "Category", "SubCategory", "Status", "TimeZone", "StartTime", "EndTime", "Metadata", "Tasks"});
        Descriptors.Descriptor descriptor4 = descriptor2.getNestedTypes().get(1);
        internal_static_GeneralPlanInfo_Task_descriptor = descriptor4;
        internal_static_GeneralPlanInfo_Task_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "Desc", FaqConstants.FAQ_UPLOAD_FLAG, "TimeZone", "Status", "Schedules"});
        Descriptors.Descriptor descriptor5 = descriptor2.getNestedTypes().get(2);
        internal_static_GeneralPlanInfo_Schedule_descriptor = descriptor5;
        internal_static_GeneralPlanInfo_Schedule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"AllDayType", "DtStart", "DtEnd", "Metadata", "ObjectiveInfos", "ObjectiveRelation", "Recurrence", "TimeZone"});
        Descriptors.Descriptor descriptor6 = descriptor2.getNestedTypes().get(3);
        internal_static_GeneralPlanInfo_ObjectiveInfo_descriptor = descriptor6;
        internal_static_GeneralPlanInfo_ObjectiveInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"ObjectiveType", "FieldName", "DataType"});
        Descriptors.Descriptor descriptor7 = descriptor2.getNestedTypes().get(4);
        internal_static_GeneralPlanInfo_Metadata_descriptor = descriptor7;
        internal_static_GeneralPlanInfo_Metadata_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"CourseMsg", "PlanMetadata"});
        Descriptors.Descriptor descriptor8 = descriptor2.getNestedTypes().get(5);
        internal_static_GeneralPlanInfo_CourseMetadata_descriptor = descriptor8;
        internal_static_GeneralPlanInfo_CourseMetadata_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"DayOrder", "CourseIndex", "IsDayPunch", "CourseType", "UpdateTime", "IsCoursePunch", "CourseId", "CourseName"});
        Descriptors.Descriptor descriptor9 = descriptor2.getNestedTypes().get(6);
        internal_static_GeneralPlanInfo_PlanMetadata_descriptor = descriptor9;
        internal_static_GeneralPlanInfo_PlanMetadata_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"PlanTotalDays", "PlanTotalWeeks", "WeekPlanName", "ExerciseTimes", "CurrentWeekDays", "WeekNum", "WeekPlanStartTime", "WeekPlanEndTime"});
    }
}
