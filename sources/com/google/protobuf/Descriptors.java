package com.google.protobuf;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.TextFormat;
import com.google.protobuf.WireFormat;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

@CheckReturnValue
/* loaded from: classes2.dex */
public final class Descriptors {
    private static final Logger logger = Logger.getLogger(Descriptors.class.getName());
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Descriptor[] EMPTY_DESCRIPTORS = new Descriptor[0];
    private static final FieldDescriptor[] EMPTY_FIELD_DESCRIPTORS = new FieldDescriptor[0];
    private static final EnumDescriptor[] EMPTY_ENUM_DESCRIPTORS = new EnumDescriptor[0];
    private static final ServiceDescriptor[] EMPTY_SERVICE_DESCRIPTORS = new ServiceDescriptor[0];
    private static final OneofDescriptor[] EMPTY_ONEOF_DESCRIPTORS = new OneofDescriptor[0];

    interface NumberGetter<T> {
        int getNumber(T t);
    }

    public static final class FileDescriptor extends GenericDescriptor {
        private final FileDescriptor[] dependencies;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final Descriptor[] messageTypes;
        private final DescriptorPool pool;
        private DescriptorProtos.FileDescriptorProto proto;
        private final FileDescriptor[] publicDependencies;
        private final ServiceDescriptor[] services;

        @Deprecated
        /* loaded from: classes8.dex */
        public interface InternalDescriptorAssigner {
            ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor);
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.FileDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.proto.getName();
        }

        public String getPackage() {
            return this.proto.getPackage();
        }

        public DescriptorProtos.FileOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<Descriptor> getMessageTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public List<ServiceDescriptor> getServices() {
            return Collections.unmodifiableList(Arrays.asList(this.services));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<FileDescriptor> getDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.dependencies));
        }

        public List<FileDescriptor> getPublicDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
        }

        @Deprecated
        public enum Syntax {
            UNKNOWN("unknown"),
            PROTO2("proto2"),
            PROTO3("proto3"),
            EDITIONS("editions");

            private final String name;

            Syntax(String str) {
                this.name = str;
            }
        }

        @Deprecated
        public Syntax getSyntax() {
            if (!Syntax.PROTO3.name.equals(this.proto.getSyntax())) {
                if (Syntax.EDITIONS.name.equals(this.proto.getSyntax())) {
                    return Syntax.EDITIONS;
                }
                return Syntax.PROTO2;
            }
            return Syntax.PROTO3;
        }

        public DescriptorProtos.Edition getEdition() {
            return this.proto.getEdition();
        }

        public String getEditionName() {
            return this.proto.getEdition().equals(DescriptorProtos.Edition.EDITION_UNKNOWN) ? "" : this.proto.getEdition().name().substring(8);
        }

        public void copyHeadingTo(DescriptorProtos.FileDescriptorProto.Builder builder) {
            builder.setName(getName()).setSyntax(getSyntax().name);
            if (!getPackage().isEmpty()) {
                builder.setPackage(getPackage());
            }
            if (getSyntax().equals(Syntax.EDITIONS)) {
                builder.setEdition(getEdition());
            }
            if (getOptions().equals(DescriptorProtos.FileOptions.getDefaultInstance())) {
                return;
            }
            builder.setOptions(getOptions());
        }

        public Descriptor findMessageTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            String str2 = getPackage();
            if (!str2.isEmpty()) {
                str = str2 + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if ((findSymbol instanceof Descriptor) && findSymbol.getFile() == this) {
                return (Descriptor) findSymbol;
            }
            return null;
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            String str2 = getPackage();
            if (!str2.isEmpty()) {
                str = str2 + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if ((findSymbol instanceof EnumDescriptor) && findSymbol.getFile() == this) {
                return (EnumDescriptor) findSymbol;
            }
            return null;
        }

        public ServiceDescriptor findServiceByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            String str2 = getPackage();
            if (!str2.isEmpty()) {
                str = str2 + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if ((findSymbol instanceof ServiceDescriptor) && findSymbol.getFile() == this) {
                return (ServiceDescriptor) findSymbol;
            }
            return null;
        }

        public FieldDescriptor findExtensionByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            String str2 = getPackage();
            if (!str2.isEmpty()) {
                str = str2 + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if ((findSymbol instanceof FieldDescriptor) && findSymbol.getFile() == this) {
                return (FieldDescriptor) findSymbol;
            }
            return null;
        }

        public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr) throws DescriptorValidationException {
            return buildFrom(fileDescriptorProto, fileDescriptorArr, false);
        }

        public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr, boolean z) throws DescriptorValidationException {
            FileDescriptor fileDescriptor = new FileDescriptor(fileDescriptorProto, fileDescriptorArr, new DescriptorPool(fileDescriptorArr, z), z);
            fileDescriptor.crossLink();
            return fileDescriptor;
        }

        private static byte[] latin1Cat(String[] strArr) {
            if (strArr.length == 1) {
                return strArr[0].getBytes(Internal.ISO_8859_1);
            }
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
            }
            return sb.toString().getBytes(Internal.ISO_8859_1);
        }

        private static FileDescriptor[] findDescriptors(Class<?> cls, String[] strArr, String[] strArr2) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < strArr.length; i++) {
                try {
                    arrayList.add((FileDescriptor) cls.getClassLoader().loadClass(strArr[i]).getField("descriptor").get(null));
                } catch (Exception unused) {
                    Descriptors.logger.warning("Descriptors for \"" + strArr2[i] + "\" can not be found.");
                }
            }
            return (FileDescriptor[]) arrayList.toArray(new FileDescriptor[0]);
        }

        @Deprecated
        public static void internalBuildGeneratedFileFrom(String[] strArr, FileDescriptor[] fileDescriptorArr, InternalDescriptorAssigner internalDescriptorAssigner) {
            byte[] latin1Cat = latin1Cat(strArr);
            try {
                DescriptorProtos.FileDescriptorProto parseFrom = DescriptorProtos.FileDescriptorProto.parseFrom(latin1Cat);
                try {
                    FileDescriptor buildFrom = buildFrom(parseFrom, fileDescriptorArr, true);
                    ExtensionRegistry assignDescriptors = internalDescriptorAssigner.assignDescriptors(buildFrom);
                    if (assignDescriptors != null) {
                        try {
                            buildFrom.setProto(DescriptorProtos.FileDescriptorProto.parseFrom(latin1Cat, assignDescriptors));
                        } catch (InvalidProtocolBufferException e) {
                            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                        }
                    }
                } catch (DescriptorValidationException e2) {
                    throw new IllegalArgumentException("Invalid embedded descriptor for \"" + parseFrom.getName() + "\".", e2);
                }
            } catch (InvalidProtocolBufferException e3) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e3);
            }
        }

        public static FileDescriptor internalBuildGeneratedFileFrom(String[] strArr, FileDescriptor[] fileDescriptorArr) {
            try {
                DescriptorProtos.FileDescriptorProto parseFrom = DescriptorProtos.FileDescriptorProto.parseFrom(latin1Cat(strArr));
                try {
                    return buildFrom(parseFrom, fileDescriptorArr, true);
                } catch (DescriptorValidationException e) {
                    throw new IllegalArgumentException("Invalid embedded descriptor for \"" + parseFrom.getName() + "\".", e);
                }
            } catch (InvalidProtocolBufferException e2) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e2);
            }
        }

        @Deprecated
        public static void internalBuildGeneratedFileFrom(String[] strArr, Class<?> cls, String[] strArr2, String[] strArr3, InternalDescriptorAssigner internalDescriptorAssigner) {
            internalBuildGeneratedFileFrom(strArr, findDescriptors(cls, strArr2, strArr3), internalDescriptorAssigner);
        }

        public static FileDescriptor internalBuildGeneratedFileFrom(String[] strArr, Class<?> cls, String[] strArr2, String[] strArr3) {
            return internalBuildGeneratedFileFrom(strArr, findDescriptors(cls, strArr2, strArr3));
        }

        public static void internalUpdateFileDescriptor(FileDescriptor fileDescriptor, ExtensionRegistry extensionRegistry) {
            try {
                fileDescriptor.setProto(DescriptorProtos.FileDescriptorProto.parseFrom(fileDescriptor.proto.toByteString(), extensionRegistry));
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
            }
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private FileDescriptor(com.google.protobuf.DescriptorProtos.FileDescriptorProto r12, com.google.protobuf.Descriptors.FileDescriptor[] r13, com.google.protobuf.Descriptors.DescriptorPool r14, boolean r15) throws com.google.protobuf.Descriptors.DescriptorValidationException {
            /*
                Method dump skipped, instructions count: 313
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.FileDescriptor.<init>(com.google.protobuf.DescriptorProtos$FileDescriptorProto, com.google.protobuf.Descriptors$FileDescriptor[], com.google.protobuf.Descriptors$DescriptorPool, boolean):void");
        }

        FileDescriptor(String str, Descriptor descriptor) throws DescriptorValidationException {
            super(null);
            DescriptorPool descriptorPool = new DescriptorPool(new FileDescriptor[0], true);
            this.pool = descriptorPool;
            this.proto = DescriptorProtos.FileDescriptorProto.newBuilder().setName(descriptor.getFullName() + ".placeholder.proto").setPackage(str).addMessageType(descriptor.toProto()).build();
            this.dependencies = new FileDescriptor[0];
            this.publicDependencies = new FileDescriptor[0];
            this.messageTypes = new Descriptor[]{descriptor};
            this.enumTypes = Descriptors.EMPTY_ENUM_DESCRIPTORS;
            this.services = Descriptors.EMPTY_SERVICE_DESCRIPTORS;
            this.extensions = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            descriptorPool.addPackage(str, this);
            descriptorPool.addSymbol(descriptor);
        }

        private void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.messageTypes) {
                descriptor.crossLink();
            }
            for (ServiceDescriptor serviceDescriptor : this.services) {
                serviceDescriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.extensions) {
                fieldDescriptor.crossLink();
            }
        }

        private void setProto(DescriptorProtos.FileDescriptorProto fileDescriptorProto) {
            this.proto = fileDescriptorProto;
            int i = 0;
            int i2 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.messageTypes;
                if (i2 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i2].setProto(fileDescriptorProto.getMessageType(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i3 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i3].setProto(fileDescriptorProto.getEnumType(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                ServiceDescriptor[] serviceDescriptorArr = this.services;
                if (i4 >= serviceDescriptorArr.length) {
                    break;
                }
                serviceDescriptorArr[i4].setProto(fileDescriptorProto.getService(i4));
                i4++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.extensions;
                if (i >= fieldDescriptorArr.length) {
                    return;
                }
                fieldDescriptorArr[i].setProto(fileDescriptorProto.getExtension(i));
                i++;
            }
        }
    }

    public static final class Descriptor extends GenericDescriptor {
        private final Descriptor containingType;
        private final EnumDescriptor[] enumTypes;
        private final int[] extensionRangeLowerBounds;
        private final int[] extensionRangeUpperBounds;
        private final FieldDescriptor[] extensions;
        private final FieldDescriptor[] fields;
        private final FieldDescriptor[] fieldsSortedByNumber;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final Descriptor[] nestedTypes;
        private final OneofDescriptor[] oneofs;
        private DescriptorProtos.DescriptorProto proto;
        private final int realOneofCount;

        /* synthetic */ Descriptor(DescriptorProtos.DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, AnonymousClass1 anonymousClass1) throws DescriptorValidationException {
            this(descriptorProto, fileDescriptor, descriptor, i);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.DescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos.MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public List<OneofDescriptor> getOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs));
        }

        public List<OneofDescriptor> getRealOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs).subList(0, this.realOneofCount));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public boolean isExtensionNumber(int i) {
            int binarySearch = Arrays.binarySearch(this.extensionRangeLowerBounds, i);
            if (binarySearch < 0) {
                binarySearch = (~binarySearch) - 1;
            }
            return binarySearch >= 0 && i < this.extensionRangeUpperBounds[binarySearch];
        }

        public boolean isReservedNumber(int i) {
            for (DescriptorProtos.DescriptorProto.ReservedRange reservedRange : this.proto.getReservedRangeList()) {
                if (reservedRange.getStart() <= i && i < reservedRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isReservedName(String str) {
            Internal.checkNotNull(str);
            Iterator<String> it = this.proto.getReservedNameList().iterator();
            while (it.hasNext()) {
                if (it.next().equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isExtendable() {
            return !this.proto.getExtensionRangeList().isEmpty();
        }

        public FieldDescriptor findFieldByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol instanceof FieldDescriptor) {
                return (FieldDescriptor) findSymbol;
            }
            return null;
        }

        public FieldDescriptor findFieldByNumber(int i) {
            FieldDescriptor[] fieldDescriptorArr = this.fieldsSortedByNumber;
            return (FieldDescriptor) Descriptors.binarySearch(fieldDescriptorArr, fieldDescriptorArr.length, FieldDescriptor.NUMBER_GETTER, i);
        }

        public Descriptor findNestedTypeByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol instanceof Descriptor) {
                return (Descriptor) findSymbol;
            }
            return null;
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol instanceof EnumDescriptor) {
                return (EnumDescriptor) findSymbol;
            }
            return null;
        }

        Descriptor(String str) throws DescriptorValidationException {
            super(null);
            String str2;
            String str3;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                str3 = str.substring(lastIndexOf + 1);
                str2 = str.substring(0, lastIndexOf);
            } else {
                str2 = "";
                str3 = str;
            }
            this.index = 0;
            this.proto = DescriptorProtos.DescriptorProto.newBuilder().setName(str3).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
            this.fullName = str;
            this.containingType = null;
            this.nestedTypes = Descriptors.EMPTY_DESCRIPTORS;
            this.enumTypes = Descriptors.EMPTY_ENUM_DESCRIPTORS;
            this.fields = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            this.fieldsSortedByNumber = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            this.extensions = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            this.oneofs = Descriptors.EMPTY_ONEOF_DESCRIPTORS;
            this.realOneofCount = 0;
            this.file = new FileDescriptor(str2, this);
            this.extensionRangeLowerBounds = new int[]{1};
            this.extensionRangeUpperBounds = new int[]{536870912};
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private Descriptor(com.google.protobuf.DescriptorProtos.DescriptorProto r11, com.google.protobuf.Descriptors.FileDescriptor r12, com.google.protobuf.Descriptors.Descriptor r13, int r14) throws com.google.protobuf.Descriptors.DescriptorValidationException {
            /*
                Method dump skipped, instructions count: 469
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.Descriptor.<init>(com.google.protobuf.DescriptorProtos$DescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.nestedTypes) {
                descriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.fields) {
                fieldDescriptor.crossLink();
            }
            Arrays.sort(this.fieldsSortedByNumber);
            validateNoDuplicateFieldNumbers();
            for (FieldDescriptor fieldDescriptor2 : this.extensions) {
                fieldDescriptor2.crossLink();
            }
        }

        private void validateNoDuplicateFieldNumbers() throws DescriptorValidationException {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                FieldDescriptor[] fieldDescriptorArr = this.fieldsSortedByNumber;
                if (i2 >= fieldDescriptorArr.length) {
                    return;
                }
                FieldDescriptor fieldDescriptor = fieldDescriptorArr[i];
                FieldDescriptor fieldDescriptor2 = fieldDescriptorArr[i2];
                if (fieldDescriptor.getNumber() == fieldDescriptor2.getNumber()) {
                    throw new DescriptorValidationException(fieldDescriptor2, "Field number " + fieldDescriptor2.getNumber() + " has already been used in \"" + fieldDescriptor2.getContainingType().getFullName() + "\" by field \"" + fieldDescriptor.getName() + "\".", (AnonymousClass1) null);
                }
                i = i2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.DescriptorProto descriptorProto) {
            this.proto = descriptorProto;
            int i = 0;
            int i2 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.nestedTypes;
                if (i2 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i2].setProto(descriptorProto.getNestedType(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                OneofDescriptor[] oneofDescriptorArr = this.oneofs;
                if (i3 >= oneofDescriptorArr.length) {
                    break;
                }
                oneofDescriptorArr[i3].setProto(descriptorProto.getOneofDecl(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i4 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i4].setProto(descriptorProto.getEnumType(i4));
                i4++;
            }
            int i5 = 0;
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.fields;
                if (i5 >= fieldDescriptorArr.length) {
                    break;
                }
                fieldDescriptorArr[i5].setProto(descriptorProto.getField(i5));
                i5++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr2 = this.extensions;
                if (i >= fieldDescriptorArr2.length) {
                    return;
                }
                fieldDescriptorArr2[i].setProto(descriptorProto.getExtension(i));
                i++;
            }
        }
    }

    public static final class FieldDescriptor extends GenericDescriptor implements Comparable<FieldDescriptor>, FieldSet.FieldDescriptorLite<FieldDescriptor> {
        private static final NumberGetter<FieldDescriptor> NUMBER_GETTER = new NumberGetter<FieldDescriptor>() { // from class: com.google.protobuf.Descriptors.FieldDescriptor.1
            @Override // com.google.protobuf.Descriptors.NumberGetter
            public int getNumber(FieldDescriptor fieldDescriptor) {
                return fieldDescriptor.getNumber();
            }
        };
        private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
        private OneofDescriptor containingOneof;
        private Descriptor containingType;
        private Object defaultValue;
        private EnumDescriptor enumType;
        private final Descriptor extensionScope;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final boolean isProto3Optional;
        private String jsonName;
        private Descriptor messageType;
        private DescriptorProtos.FieldDescriptorProto proto;
        private Type type;

        /* synthetic */ FieldDescriptor(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, boolean z, AnonymousClass1 anonymousClass1) throws DescriptorValidationException {
            this(fieldDescriptorProto, fileDescriptor, descriptor, i, z);
        }

        static {
            if (Type.types.length != DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
            }
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.FieldDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public String getJsonName() {
            String str = this.jsonName;
            if (str != null) {
                return str;
            }
            if (this.proto.hasJsonName()) {
                String jsonName = this.proto.getJsonName();
                this.jsonName = jsonName;
                return jsonName;
            }
            String fieldNameToJsonName = fieldNameToJsonName(this.proto.getName());
            this.jsonName = fieldNameToJsonName;
            return fieldNameToJsonName;
        }

        public JavaType getJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return getLiteType().getJavaType();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public Type getType() {
            return this.type;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return table[this.type.ordinal()];
        }

        public boolean needsUtf8Check() {
            if (this.type != Type.STRING) {
                return false;
            }
            if (getContainingType().getOptions().getMapEntry() || getFile().getSyntax() == FileDescriptor.Syntax.PROTO3) {
                return true;
            }
            return getFile().getOptions().getJavaStringCheckUtf8();
        }

        public boolean isMapField() {
            return getType() == Type.MESSAGE && isRepeated() && getMessageType().getOptions().getMapEntry();
        }

        public boolean isRequired() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
        }

        public boolean isOptional() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            if (!isPackable()) {
                return false;
            }
            if (getFile().getSyntax() == FileDescriptor.Syntax.PROTO2) {
                return getOptions().getPacked();
            }
            return !getOptions().hasPacked() || getOptions().getPacked();
        }

        public boolean isPackable() {
            return isRepeated() && getLiteType().isPackable();
        }

        public boolean hasDefaultValue() {
            return this.proto.hasDefaultValue();
        }

        public Object getDefaultValue() {
            if (getJavaType() == JavaType.MESSAGE) {
                throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
            }
            return this.defaultValue;
        }

        public DescriptorProtos.FieldOptions getOptions() {
            return this.proto.getOptions();
        }

        public boolean isExtension() {
            return this.proto.hasExtendee();
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public OneofDescriptor getContainingOneof() {
            return this.containingOneof;
        }

        public OneofDescriptor getRealContainingOneof() {
            OneofDescriptor oneofDescriptor = this.containingOneof;
            if (oneofDescriptor == null || oneofDescriptor.isSynthetic()) {
                return null;
            }
            return this.containingOneof;
        }

        @Deprecated
        public boolean hasOptionalKeyword() {
            return this.isProto3Optional || (this.file.getSyntax() == FileDescriptor.Syntax.PROTO2 && isOptional() && getContainingOneof() == null);
        }

        public boolean hasPresence() {
            if (isRepeated()) {
                return false;
            }
            return getType() == Type.MESSAGE || getType() == Type.GROUP || getContainingOneof() != null || this.file.getSyntax() == FileDescriptor.Syntax.PROTO2;
        }

        public Descriptor getExtensionScope() {
            if (!isExtension()) {
                throw new UnsupportedOperationException(String.format("This field is not an extension. (%s)", this.fullName));
            }
            return this.extensionScope;
        }

        public Descriptor getMessageType() {
            if (getJavaType() != JavaType.MESSAGE) {
                throw new UnsupportedOperationException(String.format("This field is not of message type. (%s)", this.fullName));
            }
            return this.messageType;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public EnumDescriptor getEnumType() {
            if (getJavaType() != JavaType.ENUM) {
                throw new UnsupportedOperationException(String.format("This field is not of enum type. (%s)", this.fullName));
            }
            return this.enumType;
        }

        public boolean legacyEnumFieldTreatedAsClosed() {
            return getType() == Type.ENUM && getFile().getSyntax() == FileDescriptor.Syntax.PROTO2;
        }

        @Override // java.lang.Comparable
        public int compareTo(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.containingType != this.containingType) {
                throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
            }
            return getNumber() - fieldDescriptor.getNumber();
        }

        public String toString() {
            return getFullName();
        }

        public enum Type {
            DOUBLE(JavaType.DOUBLE),
            FLOAT(JavaType.FLOAT),
            INT64(JavaType.LONG),
            UINT64(JavaType.LONG),
            INT32(JavaType.INT),
            FIXED64(JavaType.LONG),
            FIXED32(JavaType.INT),
            BOOL(JavaType.BOOLEAN),
            STRING(JavaType.STRING),
            GROUP(JavaType.MESSAGE),
            MESSAGE(JavaType.MESSAGE),
            BYTES(JavaType.BYTE_STRING),
            UINT32(JavaType.INT),
            ENUM(JavaType.ENUM),
            SFIXED32(JavaType.INT),
            SFIXED64(JavaType.LONG),
            SINT32(JavaType.INT),
            SINT64(JavaType.LONG);

            private static final Type[] types = values();
            private final JavaType javaType;

            Type(JavaType javaType) {
                this.javaType = javaType;
            }

            public DescriptorProtos.FieldDescriptorProto.Type toProto() {
                return DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
            }

            public JavaType getJavaType() {
                return this.javaType;
            }

            public static Type valueOf(DescriptorProtos.FieldDescriptorProto.Type type) {
                return types[type.getNumber() - 1];
            }
        }

        public enum JavaType {
            INT(0),
            LONG(0L),
            FLOAT(Float.valueOf(0.0f)),
            DOUBLE(Double.valueOf(0.0d)),
            BOOLEAN(false),
            STRING(""),
            BYTE_STRING(ByteString.EMPTY),
            ENUM(null),
            MESSAGE(null);

            private final Object defaultDefault;

            JavaType(Object obj) {
                this.defaultDefault = obj;
            }
        }

        private static String fieldNameToJsonName(String str) {
            int length = str.length();
            StringBuilder sb = new StringBuilder(length);
            boolean z = false;
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt == '_') {
                    z = true;
                } else if (z) {
                    if ('a' <= charAt && charAt <= 'z') {
                        charAt = (char) (charAt - ' ');
                    }
                    sb.append(charAt);
                    z = false;
                } else {
                    sb.append(charAt);
                }
            }
            return sb.toString();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private FieldDescriptor(com.google.protobuf.DescriptorProtos.FieldDescriptorProto r2, com.google.protobuf.Descriptors.FileDescriptor r3, com.google.protobuf.Descriptors.Descriptor r4, int r5, boolean r6) throws com.google.protobuf.Descriptors.DescriptorValidationException {
            /*
                r1 = this;
                r0 = 0
                r1.<init>(r0)
                r1.index = r5
                r1.proto = r2
                java.lang.String r5 = r2.getName()
                java.lang.String r5 = com.google.protobuf.Descriptors.access$2300(r3, r4, r5)
                r1.fullName = r5
                r1.file = r3
                boolean r5 = r2.hasType()
                if (r5 == 0) goto L24
                com.google.protobuf.DescriptorProtos$FieldDescriptorProto$Type r5 = r2.getType()
                com.google.protobuf.Descriptors$FieldDescriptor$Type r5 = com.google.protobuf.Descriptors.FieldDescriptor.Type.valueOf(r5)
                r1.type = r5
            L24:
                boolean r5 = r2.getProto3Optional()
                r1.isProto3Optional = r5
                int r5 = r1.getNumber()
                if (r5 <= 0) goto Lbc
                if (r6 == 0) goto L5a
                boolean r5 = r2.hasExtendee()
                if (r5 == 0) goto L52
                r1.containingType = r0
                if (r4 == 0) goto L3f
                r1.extensionScope = r4
                goto L41
            L3f:
                r1.extensionScope = r0
            L41:
                boolean r2 = r2.hasOneofIndex()
                if (r2 != 0) goto L4a
                r1.containingOneof = r0
                goto Lac
            L4a:
                com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                java.lang.String r3 = "FieldDescriptorProto.oneof_index set for extension field."
                r2.<init>(r1, r3, r0)
                throw r2
            L52:
                com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                java.lang.String r3 = "FieldDescriptorProto.extendee not set for extension field."
                r2.<init>(r1, r3, r0)
                throw r2
            L5a:
                boolean r5 = r2.hasExtendee()
                if (r5 != 0) goto Lb4
                r1.containingType = r4
                boolean r5 = r2.hasOneofIndex()
                if (r5 == 0) goto La8
                int r5 = r2.getOneofIndex()
                if (r5 < 0) goto L90
                int r5 = r2.getOneofIndex()
                com.google.protobuf.DescriptorProtos$DescriptorProto r6 = r4.toProto()
                int r6 = r6.getOneofDeclCount()
                if (r5 >= r6) goto L90
                java.util.List r4 = r4.getOneofs()
                int r2 = r2.getOneofIndex()
                java.lang.Object r2 = r4.get(r2)
                com.google.protobuf.Descriptors$OneofDescriptor r2 = (com.google.protobuf.Descriptors.OneofDescriptor) r2
                r1.containingOneof = r2
                com.google.protobuf.Descriptors.OneofDescriptor.access$2608(r2)
                goto Laa
            L90:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "FieldDescriptorProto.oneof_index is out of range for type "
                r2.<init>(r3)
                com.google.protobuf.Descriptors$DescriptorValidationException r3 = new com.google.protobuf.Descriptors$DescriptorValidationException
                java.lang.String r4 = r4.getName()
                r2.append(r4)
                java.lang.String r2 = r2.toString()
                r3.<init>(r1, r2, r0)
                throw r3
            La8:
                r1.containingOneof = r0
            Laa:
                r1.extensionScope = r0
            Lac:
                com.google.protobuf.Descriptors$DescriptorPool r2 = com.google.protobuf.Descriptors.FileDescriptor.access$1900(r3)
                r2.addSymbol(r1)
                return
            Lb4:
                com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                java.lang.String r3 = "FieldDescriptorProto.extendee set for non-extension field."
                r2.<init>(r1, r3, r0)
                throw r2
            Lbc:
                com.google.protobuf.Descriptors$DescriptorValidationException r2 = new com.google.protobuf.Descriptors$DescriptorValidationException
                java.lang.String r3 = "Field numbers must be positive integers."
                r2.<init>(r1, r3, r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.FieldDescriptor.<init>(com.google.protobuf.DescriptorProtos$FieldDescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int, boolean):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public void crossLink() throws DescriptorValidationException {
            AnonymousClass1 anonymousClass1 = null;
            if (this.proto.hasExtendee()) {
                GenericDescriptor lookupSymbol = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!(lookupSymbol instanceof Descriptor)) {
                    throw new DescriptorValidationException(this, "\"" + this.proto.getExtendee() + "\" is not a message type.", anonymousClass1);
                }
                this.containingType = (Descriptor) lookupSymbol;
                if (!getContainingType().isExtensionNumber(getNumber())) {
                    throw new DescriptorValidationException(this, "\"" + getContainingType().getFullName() + "\" does not declare " + getNumber() + " as an extension number.", anonymousClass1);
                }
            }
            if (this.proto.hasTypeName()) {
                GenericDescriptor lookupSymbol2 = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!this.proto.hasType()) {
                    if (lookupSymbol2 instanceof Descriptor) {
                        this.type = Type.MESSAGE;
                    } else if (lookupSymbol2 instanceof EnumDescriptor) {
                        this.type = Type.ENUM;
                    } else {
                        throw new DescriptorValidationException(this, "\"" + this.proto.getTypeName() + "\" is not a type.", anonymousClass1);
                    }
                }
                if (getJavaType() == JavaType.MESSAGE) {
                    if (!(lookupSymbol2 instanceof Descriptor)) {
                        throw new DescriptorValidationException(this, "\"" + this.proto.getTypeName() + "\" is not a message type.", anonymousClass1);
                    }
                    this.messageType = (Descriptor) lookupSymbol2;
                    if (this.proto.hasDefaultValue()) {
                        throw new DescriptorValidationException(this, "Messages can't have default values.", anonymousClass1);
                    }
                } else if (getJavaType() == JavaType.ENUM) {
                    if (!(lookupSymbol2 instanceof EnumDescriptor)) {
                        throw new DescriptorValidationException(this, "\"" + this.proto.getTypeName() + "\" is not an enum type.", anonymousClass1);
                    }
                    this.enumType = (EnumDescriptor) lookupSymbol2;
                } else {
                    throw new DescriptorValidationException(this, "Field with primitive type has type_name.", anonymousClass1);
                }
            } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
                throw new DescriptorValidationException(this, "Field with message or enum type missing type_name.", anonymousClass1);
            }
            if (this.proto.getOptions().getPacked() && !isPackable()) {
                throw new DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.", anonymousClass1);
            }
            if (this.proto.hasDefaultValue()) {
                if (isRepeated()) {
                    throw new DescriptorValidationException(this, "Repeated fields cannot have default values.", anonymousClass1);
                }
                try {
                    switch (AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[getType().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
                            break;
                        case 4:
                        case 5:
                            this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
                            break;
                        case 6:
                        case 7:
                        case 8:
                            this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
                            break;
                        case 9:
                        case 10:
                            this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
                            break;
                        case 11:
                            if (this.proto.getDefaultValue().equals("inf")) {
                                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                                break;
                            } else if (this.proto.getDefaultValue().equals("-inf")) {
                                this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                                break;
                            } else if (this.proto.getDefaultValue().equals("nan")) {
                                this.defaultValue = Float.valueOf(Float.NaN);
                                break;
                            } else {
                                this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                                break;
                            }
                        case 12:
                            if (this.proto.getDefaultValue().equals("inf")) {
                                this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                                break;
                            } else if (this.proto.getDefaultValue().equals("-inf")) {
                                this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                                break;
                            } else if (this.proto.getDefaultValue().equals("nan")) {
                                this.defaultValue = Double.valueOf(Double.NaN);
                                break;
                            } else {
                                this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                                break;
                            }
                        case 13:
                            this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                            break;
                        case 14:
                            this.defaultValue = this.proto.getDefaultValue();
                            break;
                        case 15:
                            try {
                                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                                break;
                            } catch (TextFormat.InvalidEscapeSequenceException e) {
                                throw new DescriptorValidationException(this, "Couldn't parse default value: " + e.getMessage(), e, anonymousClass1);
                            }
                        case 16:
                            EnumValueDescriptor findValueByName = this.enumType.findValueByName(this.proto.getDefaultValue());
                            this.defaultValue = findValueByName;
                            if (findValueByName == null) {
                                throw new DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + '\"', anonymousClass1);
                            }
                            break;
                        case 17:
                        case 18:
                            throw new DescriptorValidationException(this, "Message type had default value.", anonymousClass1);
                    }
                } catch (NumberFormatException e2) {
                    throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + '\"', e2, anonymousClass1);
                }
            } else if (isRepeated()) {
                this.defaultValue = Collections.emptyList();
            } else {
                int i = AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[getJavaType().ordinal()];
                if (i == 1) {
                    this.defaultValue = this.enumType.getValues().get(0);
                } else if (i != 2) {
                    this.defaultValue = getJavaType().defaultDefault;
                } else {
                    this.defaultValue = null;
                }
            }
            Descriptor descriptor = this.containingType;
            if (descriptor == null || !descriptor.getOptions().getMessageSetWireFormat()) {
                return;
            }
            if (isExtension()) {
                if (!isOptional() || getType() != Type.MESSAGE) {
                    throw new DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.", anonymousClass1);
                }
                return;
            }
            throw new DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.", anonymousClass1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto) {
            this.proto = fieldDescriptorProto;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Message.Builder) builder).mergeFrom((Message) messageLite);
        }
    }

    /* renamed from: com.google.protobuf.Descriptors$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[FieldDescriptor.JavaType.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType = iArr;
            try {
                iArr[FieldDescriptor.JavaType.ENUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[FieldDescriptor.JavaType.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr2;
            try {
                iArr2[FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.UINT32.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.INT64.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SINT64.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.UINT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FIXED64.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.DOUBLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.BOOL.ordinal()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    public static final class EnumDescriptor extends GenericDescriptor implements Internal.EnumLiteMap<EnumValueDescriptor> {
        private ReferenceQueue<EnumValueDescriptor> cleanupQueue;
        private final Descriptor containingType;
        private final int distinctNumbers;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumDescriptorProto proto;
        private Map<Integer, WeakReference<EnumValueDescriptor>> unknownValues;
        private final EnumValueDescriptor[] values;
        private final EnumValueDescriptor[] valuesSortedByNumber;

        /* synthetic */ EnumDescriptor(DescriptorProtos.EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, AnonymousClass1 anonymousClass1) throws DescriptorValidationException {
            this(enumDescriptorProto, fileDescriptor, descriptor, i);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public boolean isClosed() {
            return getFile().getSyntax() != FileDescriptor.Syntax.PROTO3;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos.EnumOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<EnumValueDescriptor> getValues() {
            return Collections.unmodifiableList(Arrays.asList(this.values));
        }

        public boolean isReservedNumber(int i) {
            for (DescriptorProtos.EnumDescriptorProto.EnumReservedRange enumReservedRange : this.proto.getReservedRangeList()) {
                if (enumReservedRange.getStart() <= i && i <= enumReservedRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isReservedName(String str) {
            Internal.checkNotNull(str);
            Iterator<String> it = this.proto.getReservedNameList().iterator();
            while (it.hasNext()) {
                if (it.next().equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public EnumValueDescriptor findValueByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol instanceof EnumValueDescriptor) {
                return (EnumValueDescriptor) findSymbol;
            }
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public EnumValueDescriptor findValueByNumber(int i) {
            return (EnumValueDescriptor) Descriptors.binarySearch(this.valuesSortedByNumber, this.distinctNumbers, EnumValueDescriptor.NUMBER_GETTER, i);
        }

        static class UnknownEnumValueReference extends WeakReference<EnumValueDescriptor> {
            private final int number;

            /* synthetic */ UnknownEnumValueReference(int i, EnumValueDescriptor enumValueDescriptor, AnonymousClass1 anonymousClass1) {
                this(i, enumValueDescriptor);
            }

            private UnknownEnumValueReference(int i, EnumValueDescriptor enumValueDescriptor) {
                super(enumValueDescriptor);
                this.number = i;
            }
        }

        public EnumValueDescriptor findValueByNumberCreatingIfUnknown(int i) {
            EnumValueDescriptor enumValueDescriptor;
            EnumValueDescriptor findValueByNumber = findValueByNumber(i);
            if (findValueByNumber != null) {
                return findValueByNumber;
            }
            synchronized (this) {
                if (this.cleanupQueue == null) {
                    this.cleanupQueue = new ReferenceQueue<>();
                    this.unknownValues = new HashMap();
                } else {
                    while (true) {
                        UnknownEnumValueReference unknownEnumValueReference = (UnknownEnumValueReference) this.cleanupQueue.poll();
                        if (unknownEnumValueReference == null) {
                            break;
                        }
                        this.unknownValues.remove(Integer.valueOf(unknownEnumValueReference.number));
                    }
                }
                WeakReference<EnumValueDescriptor> weakReference = this.unknownValues.get(Integer.valueOf(i));
                AnonymousClass1 anonymousClass1 = null;
                enumValueDescriptor = weakReference == null ? null : weakReference.get();
                if (enumValueDescriptor == null) {
                    enumValueDescriptor = new EnumValueDescriptor(this, Integer.valueOf(i), anonymousClass1);
                    this.unknownValues.put(Integer.valueOf(i), new UnknownEnumValueReference(i, enumValueDescriptor, anonymousClass1));
                }
            }
            return enumValueDescriptor;
        }

        int getUnknownEnumValueDescriptorCount() {
            return this.unknownValues.size();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private EnumDescriptor(com.google.protobuf.DescriptorProtos.EnumDescriptorProto r10, com.google.protobuf.Descriptors.FileDescriptor r11, com.google.protobuf.Descriptors.Descriptor r12, int r13) throws com.google.protobuf.Descriptors.DescriptorValidationException {
            /*
                r9 = this;
                r0 = 0
                r9.<init>(r0)
                r9.unknownValues = r0
                r9.cleanupQueue = r0
                r9.index = r13
                r9.proto = r10
                java.lang.String r13 = r10.getName()
                java.lang.String r13 = com.google.protobuf.Descriptors.access$2300(r11, r12, r13)
                r9.fullName = r13
                r9.file = r11
                r9.containingType = r12
                int r12 = r10.getValueCount()
                if (r12 == 0) goto L89
                int r12 = r10.getValueCount()
                com.google.protobuf.Descriptors$EnumValueDescriptor[] r12 = new com.google.protobuf.Descriptors.EnumValueDescriptor[r12]
                r9.values = r12
                r12 = 0
                r13 = r12
            L2a:
                int r1 = r10.getValueCount()
                if (r13 >= r1) goto L45
                com.google.protobuf.Descriptors$EnumValueDescriptor[] r7 = r9.values
                com.google.protobuf.Descriptors$EnumValueDescriptor r8 = new com.google.protobuf.Descriptors$EnumValueDescriptor
                com.google.protobuf.DescriptorProtos$EnumValueDescriptorProto r2 = r10.getValue(r13)
                r6 = 0
                r1 = r8
                r3 = r11
                r4 = r9
                r5 = r13
                r1.<init>(r2, r3, r4, r5, r6)
                r7[r13] = r8
                int r13 = r13 + 1
                goto L2a
            L45:
                com.google.protobuf.Descriptors$EnumValueDescriptor[] r13 = r9.values
                java.lang.Object r13 = r13.clone()
                com.google.protobuf.Descriptors$EnumValueDescriptor[] r13 = (com.google.protobuf.Descriptors.EnumValueDescriptor[]) r13
                r9.valuesSortedByNumber = r13
                java.util.Comparator<com.google.protobuf.Descriptors$EnumValueDescriptor> r1 = com.google.protobuf.Descriptors.EnumValueDescriptor.BY_NUMBER
                java.util.Arrays.sort(r13, r1)
                r13 = 1
                r1 = r13
            L56:
                int r2 = r10.getValueCount()
                if (r1 >= r2) goto L75
                com.google.protobuf.Descriptors$EnumValueDescriptor[] r2 = r9.valuesSortedByNumber
                r3 = r2[r12]
                r2 = r2[r1]
                int r3 = r3.getNumber()
                int r4 = r2.getNumber()
                if (r3 == r4) goto L72
                com.google.protobuf.Descriptors$EnumValueDescriptor[] r3 = r9.valuesSortedByNumber
                int r12 = r12 + 1
                r3[r12] = r2
            L72:
                int r1 = r1 + 1
                goto L56
            L75:
                int r12 = r12 + r13
                r9.distinctNumbers = r12
                com.google.protobuf.Descriptors$EnumValueDescriptor[] r13 = r9.valuesSortedByNumber
                int r10 = r10.getValueCount()
                java.util.Arrays.fill(r13, r12, r10, r0)
                com.google.protobuf.Descriptors$DescriptorPool r10 = com.google.protobuf.Descriptors.FileDescriptor.access$1900(r11)
                r10.addSymbol(r9)
                return
            L89:
                com.google.protobuf.Descriptors$DescriptorValidationException r10 = new com.google.protobuf.Descriptors$DescriptorValidationException
                java.lang.String r11 = "Enums must contain at least one value."
                r10.<init>(r9, r11, r0)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Descriptors.EnumDescriptor.<init>(com.google.protobuf.DescriptorProtos$EnumDescriptorProto, com.google.protobuf.Descriptors$FileDescriptor, com.google.protobuf.Descriptors$Descriptor, int):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumDescriptorProto enumDescriptorProto) {
            this.proto = enumDescriptorProto;
            int i = 0;
            while (true) {
                EnumValueDescriptor[] enumValueDescriptorArr = this.values;
                if (i >= enumValueDescriptorArr.length) {
                    return;
                }
                enumValueDescriptorArr[i].setProto(enumDescriptorProto.getValue(i));
                i++;
            }
        }
    }

    public static final class EnumValueDescriptor extends GenericDescriptor implements Internal.EnumLite {
        static final Comparator<EnumValueDescriptor> BY_NUMBER = new Comparator<EnumValueDescriptor>() { // from class: com.google.protobuf.Descriptors.EnumValueDescriptor.1
            @Override // java.util.Comparator
            public int compare(EnumValueDescriptor enumValueDescriptor, EnumValueDescriptor enumValueDescriptor2) {
                return Integer.valueOf(enumValueDescriptor.getNumber()).compareTo(Integer.valueOf(enumValueDescriptor2.getNumber()));
            }
        };
        static final NumberGetter<EnumValueDescriptor> NUMBER_GETTER = new NumberGetter<EnumValueDescriptor>() { // from class: com.google.protobuf.Descriptors.EnumValueDescriptor.2
            @Override // com.google.protobuf.Descriptors.NumberGetter
            public int getNumber(EnumValueDescriptor enumValueDescriptor) {
                return enumValueDescriptor.getNumber();
            }
        };
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumValueDescriptorProto proto;
        private final EnumDescriptor type;

        /* synthetic */ EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i, AnonymousClass1 anonymousClass1) throws DescriptorValidationException {
            this(enumValueDescriptorProto, fileDescriptor, enumDescriptor, i);
        }

        /* synthetic */ EnumValueDescriptor(EnumDescriptor enumDescriptor, Integer num, AnonymousClass1 anonymousClass1) {
            this(enumDescriptor, num);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumValueDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        public String toString() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.type.file;
        }

        public EnumDescriptor getType() {
            return this.type;
        }

        public DescriptorProtos.EnumValueOptions getOptions() {
            return this.proto.getOptions();
        }

        private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i) throws DescriptorValidationException {
            super(null);
            this.index = i;
            this.proto = enumValueDescriptorProto;
            this.type = enumDescriptor;
            this.fullName = enumDescriptor.getFullName() + FilenameUtils.EXTENSION_SEPARATOR + enumValueDescriptorProto.getName();
            fileDescriptor.pool.addSymbol(this);
        }

        private EnumValueDescriptor(EnumDescriptor enumDescriptor, Integer num) {
            super(null);
            DescriptorProtos.EnumValueDescriptorProto build = DescriptorProtos.EnumValueDescriptorProto.newBuilder().setName("UNKNOWN_ENUM_VALUE_" + enumDescriptor.getName() + "_" + num).setNumber(num.intValue()).build();
            this.index = -1;
            this.proto = build;
            this.type = enumDescriptor;
            this.fullName = enumDescriptor.getFullName() + FilenameUtils.EXTENSION_SEPARATOR + build.getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto) {
            this.proto = enumValueDescriptorProto;
        }
    }

    public static final class ServiceDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private MethodDescriptor[] methods;
        private DescriptorProtos.ServiceDescriptorProto proto;

        /* synthetic */ ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i, AnonymousClass1 anonymousClass1) throws DescriptorValidationException {
            this(serviceDescriptorProto, fileDescriptor, i);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.ServiceDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public DescriptorProtos.ServiceOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<MethodDescriptor> getMethods() {
            return Collections.unmodifiableList(Arrays.asList(this.methods));
        }

        public MethodDescriptor findMethodByName(String str) {
            GenericDescriptor findSymbol = this.file.pool.findSymbol(this.fullName + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol instanceof MethodDescriptor) {
                return (MethodDescriptor) findSymbol;
            }
            return null;
        }

        private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i) throws DescriptorValidationException {
            super(null);
            this.index = i;
            this.proto = serviceDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, null, serviceDescriptorProto.getName());
            this.file = fileDescriptor;
            this.methods = new MethodDescriptor[serviceDescriptorProto.getMethodCount()];
            for (int i2 = 0; i2 < serviceDescriptorProto.getMethodCount(); i2++) {
                this.methods[i2] = new MethodDescriptor(serviceDescriptorProto.getMethod(i2), fileDescriptor, this, i2, null);
            }
            fileDescriptor.pool.addSymbol(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (MethodDescriptor methodDescriptor : this.methods) {
                methodDescriptor.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto) {
            this.proto = serviceDescriptorProto;
            int i = 0;
            while (true) {
                MethodDescriptor[] methodDescriptorArr = this.methods;
                if (i >= methodDescriptorArr.length) {
                    return;
                }
                methodDescriptorArr[i].setProto(serviceDescriptorProto.getMethod(i));
                i++;
            }
        }
    }

    public static final class MethodDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor inputType;
        private Descriptor outputType;
        private DescriptorProtos.MethodDescriptorProto proto;
        private final ServiceDescriptor service;

        /* synthetic */ MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i, AnonymousClass1 anonymousClass1) throws DescriptorValidationException {
            this(methodDescriptorProto, fileDescriptor, serviceDescriptor, i);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.MethodDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public ServiceDescriptor getService() {
            return this.service;
        }

        public Descriptor getInputType() {
            return this.inputType;
        }

        public Descriptor getOutputType() {
            return this.outputType;
        }

        public boolean isClientStreaming() {
            return this.proto.getClientStreaming();
        }

        public boolean isServerStreaming() {
            return this.proto.getServerStreaming();
        }

        public DescriptorProtos.MethodOptions getOptions() {
            return this.proto.getOptions();
        }

        private MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i) throws DescriptorValidationException {
            super(null);
            this.index = i;
            this.proto = methodDescriptorProto;
            this.file = fileDescriptor;
            this.service = serviceDescriptor;
            this.fullName = serviceDescriptor.getFullName() + FilenameUtils.EXTENSION_SEPARATOR + methodDescriptorProto.getName();
            fileDescriptor.pool.addSymbol(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            GenericDescriptor lookupSymbol = getFile().pool.lookupSymbol(this.proto.getInputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
            AnonymousClass1 anonymousClass1 = null;
            if (!(lookupSymbol instanceof Descriptor)) {
                throw new DescriptorValidationException(this, "\"" + this.proto.getInputType() + "\" is not a message type.", anonymousClass1);
            }
            this.inputType = (Descriptor) lookupSymbol;
            GenericDescriptor lookupSymbol2 = getFile().pool.lookupSymbol(this.proto.getOutputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
            if (!(lookupSymbol2 instanceof Descriptor)) {
                throw new DescriptorValidationException(this, "\"" + this.proto.getOutputType() + "\" is not a message type.", anonymousClass1);
            }
            this.outputType = (Descriptor) lookupSymbol2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.MethodDescriptorProto methodDescriptorProto) {
            this.proto = methodDescriptorProto;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String computeFullName(FileDescriptor fileDescriptor, Descriptor descriptor, String str) {
        if (descriptor != null) {
            return descriptor.getFullName() + FilenameUtils.EXTENSION_SEPARATOR + str;
        }
        String str2 = fileDescriptor.getPackage();
        if (str2.isEmpty()) {
            return str;
        }
        return str2 + FilenameUtils.EXTENSION_SEPARATOR + str;
    }

    public static abstract class GenericDescriptor {
        public abstract FileDescriptor getFile();

        public abstract String getFullName();

        public abstract String getName();

        public abstract Message toProto();

        /* synthetic */ GenericDescriptor(AnonymousClass1 anonymousClass1) {
            this();
        }

        private GenericDescriptor() {
        }
    }

    public static class DescriptorValidationException extends Exception {
        private static final long serialVersionUID = 5750205775490483148L;
        private final String description;
        private final String name;
        private final Message proto;

        /* synthetic */ DescriptorValidationException(FileDescriptor fileDescriptor, String str, AnonymousClass1 anonymousClass1) {
            this(fileDescriptor, str);
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, AnonymousClass1 anonymousClass1) {
            this(genericDescriptor, str);
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th, AnonymousClass1 anonymousClass1) {
            this(genericDescriptor, str, th);
        }

        public String getProblemSymbolName() {
            return this.name;
        }

        public Message getProblemProto() {
            return this.proto;
        }

        public String getDescription() {
            return this.description;
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str) {
            super(genericDescriptor.getFullName() + ": " + str);
            this.name = genericDescriptor.getFullName();
            this.proto = genericDescriptor.toProto();
            this.description = str;
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th) {
            this(genericDescriptor, str);
            initCause(th);
        }

        private DescriptorValidationException(FileDescriptor fileDescriptor, String str) {
            super(fileDescriptor.getName() + ": " + str);
            this.name = fileDescriptor.getName();
            this.proto = fileDescriptor.toProto();
            this.description = str;
        }
    }

    static final class DescriptorPool {
        private final boolean allowUnknownDependencies;
        private final Set<FileDescriptor> dependencies;
        private final Map<String, GenericDescriptor> descriptorsByName = new HashMap();

        enum SearchFilter {
            TYPES_ONLY,
            AGGREGATES_ONLY,
            ALL_SYMBOLS
        }

        DescriptorPool(FileDescriptor[] fileDescriptorArr, boolean z) {
            this.dependencies = Collections.newSetFromMap(new IdentityHashMap(fileDescriptorArr.length));
            this.allowUnknownDependencies = z;
            for (FileDescriptor fileDescriptor : fileDescriptorArr) {
                this.dependencies.add(fileDescriptor);
                importPublicDependencies(fileDescriptor);
            }
            for (FileDescriptor fileDescriptor2 : this.dependencies) {
                try {
                    addPackage(fileDescriptor2.getPackage(), fileDescriptor2);
                } catch (DescriptorValidationException e) {
                    throw new AssertionError(e);
                }
            }
        }

        private void importPublicDependencies(FileDescriptor fileDescriptor) {
            for (FileDescriptor fileDescriptor2 : fileDescriptor.getPublicDependencies()) {
                if (this.dependencies.add(fileDescriptor2)) {
                    importPublicDependencies(fileDescriptor2);
                }
            }
        }

        GenericDescriptor findSymbol(String str) {
            return findSymbol(str, SearchFilter.ALL_SYMBOLS);
        }

        GenericDescriptor findSymbol(String str, SearchFilter searchFilter) {
            GenericDescriptor genericDescriptor = this.descriptorsByName.get(str);
            if (genericDescriptor != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor))))) {
                return genericDescriptor;
            }
            Iterator<FileDescriptor> it = this.dependencies.iterator();
            while (it.hasNext()) {
                GenericDescriptor genericDescriptor2 = it.next().pool.descriptorsByName.get(str);
                if (genericDescriptor2 != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor2)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor2))))) {
                    return genericDescriptor2;
                }
            }
            return null;
        }

        boolean isType(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor);
        }

        boolean isAggregate(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor) || (genericDescriptor instanceof PackageDescriptor) || (genericDescriptor instanceof ServiceDescriptor);
        }

        GenericDescriptor lookupSymbol(String str, GenericDescriptor genericDescriptor, SearchFilter searchFilter) throws DescriptorValidationException {
            GenericDescriptor findSymbol;
            String str2;
            if (str.startsWith(".")) {
                str2 = str.substring(1);
                findSymbol = findSymbol(str2, searchFilter);
            } else {
                int indexOf = str.indexOf(46);
                String substring = indexOf == -1 ? str : str.substring(0, indexOf);
                StringBuilder sb = new StringBuilder(genericDescriptor.getFullName());
                while (true) {
                    int lastIndexOf = sb.lastIndexOf(".");
                    if (lastIndexOf == -1) {
                        findSymbol = findSymbol(str, searchFilter);
                        str2 = str;
                        break;
                    }
                    int i = lastIndexOf + 1;
                    sb.setLength(i);
                    sb.append(substring);
                    GenericDescriptor findSymbol2 = findSymbol(sb.toString(), SearchFilter.AGGREGATES_ONLY);
                    if (findSymbol2 != null) {
                        if (indexOf != -1) {
                            sb.setLength(i);
                            sb.append(str);
                            findSymbol = findSymbol(sb.toString(), searchFilter);
                        } else {
                            findSymbol = findSymbol2;
                        }
                        str2 = sb.toString();
                    } else {
                        sb.setLength(lastIndexOf);
                    }
                }
            }
            if (findSymbol != null) {
                return findSymbol;
            }
            if (this.allowUnknownDependencies && searchFilter == SearchFilter.TYPES_ONLY) {
                Descriptors.logger.warning("The descriptor for message type \"" + str + "\" cannot be found and a placeholder is created for it");
                Descriptor descriptor = new Descriptor(str2);
                this.dependencies.add(descriptor.getFile());
                return descriptor;
            }
            throw new DescriptorValidationException(genericDescriptor, "\"" + str + "\" is not defined.", (AnonymousClass1) null);
        }

        void addSymbol(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            validateSymbolName(genericDescriptor);
            String fullName = genericDescriptor.getFullName();
            GenericDescriptor put = this.descriptorsByName.put(fullName, genericDescriptor);
            if (put != null) {
                this.descriptorsByName.put(fullName, put);
                AnonymousClass1 anonymousClass1 = null;
                if (genericDescriptor.getFile() == put.getFile()) {
                    int lastIndexOf = fullName.lastIndexOf(46);
                    if (lastIndexOf == -1) {
                        throw new DescriptorValidationException(genericDescriptor, "\"" + fullName + "\" is already defined.", anonymousClass1);
                    }
                    throw new DescriptorValidationException(genericDescriptor, "\"" + fullName.substring(lastIndexOf + 1) + "\" is already defined in \"" + fullName.substring(0, lastIndexOf) + "\".", anonymousClass1);
                }
                throw new DescriptorValidationException(genericDescriptor, "\"" + fullName + "\" is already defined in file \"" + put.getFile().getName() + "\".", anonymousClass1);
            }
        }

        static final class PackageDescriptor extends GenericDescriptor {
            private final FileDescriptor file;
            private final String fullName;
            private final String name;

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public Message toProto() {
                return this.file.toProto();
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public String getName() {
                return this.name;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public String getFullName() {
                return this.fullName;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public FileDescriptor getFile() {
                return this.file;
            }

            PackageDescriptor(String str, String str2, FileDescriptor fileDescriptor) {
                super(null);
                this.file = fileDescriptor;
                this.fullName = str2;
                this.name = str;
            }
        }

        void addPackage(String str, FileDescriptor fileDescriptor) throws DescriptorValidationException {
            String substring;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf == -1) {
                substring = str;
            } else {
                addPackage(str.substring(0, lastIndexOf), fileDescriptor);
                substring = str.substring(lastIndexOf + 1);
            }
            GenericDescriptor put = this.descriptorsByName.put(str, new PackageDescriptor(substring, str, fileDescriptor));
            if (put != null) {
                this.descriptorsByName.put(str, put);
                if (put instanceof PackageDescriptor) {
                    return;
                }
                throw new DescriptorValidationException(fileDescriptor, "\"" + substring + "\" is already defined (as something other than a package) in file \"" + put.getFile().getName() + "\".", (AnonymousClass1) null);
            }
        }

        static void validateSymbolName(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            String name = genericDescriptor.getName();
            AnonymousClass1 anonymousClass1 = null;
            if (name.length() == 0) {
                throw new DescriptorValidationException(genericDescriptor, "Missing name.", anonymousClass1);
            }
            for (int i = 0; i < name.length(); i++) {
                char charAt = name.charAt(i);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && charAt != '_' && ('0' > charAt || charAt > '9' || i <= 0))) {
                    throw new DescriptorValidationException(genericDescriptor, "\"" + name + "\" is not a valid identifier.", anonymousClass1);
                }
            }
        }
    }

    public static final class OneofDescriptor extends GenericDescriptor {
        private Descriptor containingType;
        private int fieldCount;
        private FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.OneofDescriptorProto proto;

        /* synthetic */ OneofDescriptor(DescriptorProtos.OneofDescriptorProto oneofDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, AnonymousClass1 anonymousClass1) {
            this(oneofDescriptorProto, fileDescriptor, descriptor, i);
        }

        static /* synthetic */ int access$2608(OneofDescriptor oneofDescriptor) {
            int i = oneofDescriptor.fieldCount;
            oneofDescriptor.fieldCount = i + 1;
            return i;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public int getFieldCount() {
            return this.fieldCount;
        }

        public DescriptorProtos.OneofOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public FieldDescriptor getField(int i) {
            return this.fields[i];
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.OneofDescriptorProto toProto() {
            return this.proto;
        }

        @Deprecated
        public boolean isSynthetic() {
            FieldDescriptor[] fieldDescriptorArr = this.fields;
            return fieldDescriptorArr.length == 1 && fieldDescriptorArr[0].isProto3Optional;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.OneofDescriptorProto oneofDescriptorProto) {
            this.proto = oneofDescriptorProto;
        }

        private OneofDescriptor(DescriptorProtos.OneofDescriptorProto oneofDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) {
            super(null);
            this.proto = oneofDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, oneofDescriptorProto.getName());
            this.file = fileDescriptor;
            this.index = i;
            this.containingType = descriptor;
            this.fieldCount = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T binarySearch(T[] tArr, int i, NumberGetter<T> numberGetter, int i2) {
        int i3 = i - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) / 2;
            T t = tArr[i5];
            int number = numberGetter.getNumber(t);
            if (i2 < number) {
                i3 = i5 - 1;
            } else {
                if (i2 <= number) {
                    return t;
                }
                i4 = i5 + 1;
            }
        }
        return null;
    }
}
