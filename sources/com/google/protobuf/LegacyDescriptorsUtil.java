package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* loaded from: classes8.dex */
public final class LegacyDescriptorsUtil {

    public static final class LegacyFileDescriptor {

        public enum Syntax {
            UNKNOWN("unknown"),
            PROTO2("proto2"),
            PROTO3("proto3");

            final String name;

            Syntax(String str) {
                this.name = str;
            }
        }

        public static Syntax getSyntax(Descriptors.FileDescriptor fileDescriptor) {
            int i = AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[fileDescriptor.getSyntax().ordinal()];
            if (i == 1) {
                return Syntax.UNKNOWN;
            }
            if (i == 2) {
                return Syntax.PROTO2;
            }
            if (i == 3) {
                return Syntax.PROTO3;
            }
            throw new IllegalArgumentException("Unexpected syntax");
        }

        private LegacyFileDescriptor() {
        }
    }

    /* renamed from: com.google.protobuf.LegacyDescriptorsUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax;

        static {
            int[] iArr = new int[Descriptors.FileDescriptor.Syntax.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax = iArr;
            try {
                iArr[Descriptors.FileDescriptor.Syntax.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[Descriptors.FileDescriptor.Syntax.PROTO2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[Descriptors.FileDescriptor.Syntax.PROTO3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static final class LegacyFieldDescriptor {
        public static boolean hasOptionalKeyword(Descriptors.FieldDescriptor fieldDescriptor) {
            return fieldDescriptor.hasOptionalKeyword();
        }

        private LegacyFieldDescriptor() {
        }
    }

    public static final class LegacyOneofDescriptor {
        public static boolean isSynthetic(Descriptors.OneofDescriptor oneofDescriptor) {
            return oneofDescriptor.isSynthetic();
        }

        private LegacyOneofDescriptor() {
        }
    }

    private LegacyDescriptorsUtil() {
    }
}
