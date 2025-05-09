package defpackage;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001f\u0010\u001f\u001a\u00020 \"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0014*\u0006\u0012\u0002\b\u00030!¢\u0006\u0002\u0010\"\"'\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\";\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u000e\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t*\b\u0012\u0004\u0012\u0002H\b0\t8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"-\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018G¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"&\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\u0002H\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0015\";\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018Ç\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012\"+\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0012\"-\u0010\u001a\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0012\"+\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006#"}, d2 = {"annotationClass", "Lkotlin/reflect/KClass;", ExifInterface.GPS_DIRECTION_TRUE, "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "declaringJavaClass", "Ljava/lang/Class;", ExifInterface.LONGITUDE_EAST, "", "getDeclaringJavaClass$annotations", "(Ljava/lang/Enum;)V", "getDeclaringJavaClass", "(Ljava/lang/Enum;)Ljava/lang/Class;", "java", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: uhq, reason: from Kotlin metadata */
/* loaded from: classes7.dex */
public final class annotationClass {
    public static final <T> Class<T> c(KClass<T> kClass) {
        uhy.e((Object) kClass, "");
        Class<T> cls = (Class<T>) ((ClassBasedDeclarationContainer) kClass).getJClass();
        uhy.b(cls, "");
        return cls;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public static final <T> Class<T> b(KClass<T> kClass) {
        uhy.e((Object) kClass, "");
        Class<T> cls = (Class<T>) ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (!cls.isPrimitive()) {
            uhy.b(cls, "");
            return cls;
        }
        String name = cls.getName();
        if (name != null) {
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals(SequenceDetailFieldConfig.DOUBLE)) {
                        cls = (Class<T>) Double.class;
                        break;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        cls = (Class<T>) Integer.class;
                        break;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        cls = (Class<T>) Byte.class;
                        break;
                    }
                    break;
                case 3052374:
                    if (name.equals(SequenceDetailFieldConfig.CHAR)) {
                        cls = (Class<T>) Character.class;
                        break;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        cls = (Class<T>) Long.class;
                        break;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        cls = (Class<T>) Void.class;
                        break;
                    }
                    break;
                case 64711720:
                    if (name.equals(TypedValues.Custom.S_BOOLEAN)) {
                        cls = (Class<T>) Boolean.class;
                        break;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        cls = (Class<T>) Float.class;
                        break;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        cls = (Class<T>) Short.class;
                        break;
                    }
                    break;
            }
        }
        uhy.b(cls, "");
        return cls;
    }

    public static final <T> KClass<T> c(Class<T> cls) {
        uhy.e((Object) cls, "");
        return uie.d(cls);
    }
}
