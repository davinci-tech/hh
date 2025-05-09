package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.suggestion.protobuf.CourseProto;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class dwh {
    public static final Map<Integer, String> e = Collections.unmodifiableMap(new HashMap<Integer, String>() { // from class: dwh.5
        private static final long serialVersionUID = -2469811930284704720L;

        {
            put(5, "010101");
            put(6, "010102");
            put(7, "010103");
        }
    });

    public static void a(CourseProto.CourseStorageInfo courseStorageInfo, DeviceInfo deviceInfo) {
        cuk.b().sendSampleConfigCommand(deviceInfo, c(courseStorageInfo), "CourseLinkageUtils");
    }

    public static void d(CourseStateProto.CourseState courseState, DeviceInfo deviceInfo) {
        cuk.b().sendSampleConfigCommand(deviceInfo, e(courseState), "CourseLinkageUtils");
    }

    public static void a(CourseStateProto.CourseState courseState, int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("CourseLinkageUtils", "device info null");
        } else {
            cuk.b().sendSampleConfigCommand(deviceInfo, a(courseState, i), "CourseLinkageUtils");
        }
    }

    public static boolean b(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE);
        ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageUtils", "isSupportReverseControlCourse is ", Boolean.valueOf(c));
        return c;
    }

    private static cvq c(CourseProto.CourseStorageInfo courseStorageInfo) {
        byte[] b = b(courseStorageInfo.toByteArray(), (byte) 2);
        LogUtil.a("CourseLinkageUtils", "createCourseSampleConfig courseTlv: ", cvx.d(b));
        return d(b);
    }

    private static cvq e(CourseStateProto.CourseState courseState) {
        byte[] b = b(courseState.toByteArray(), (byte) 3);
        LogUtil.a("CourseLinkageUtils", "createCourseStateSampleConfig courseStateTlv: ", cvx.d(b));
        return d(b);
    }

    private static cvq a(CourseStateProto.CourseState courseState, int i) {
        Map<Integer, String> map = e;
        String str = map.containsKey(Integer.valueOf(i)) ? map.get(Integer.valueOf(i)) : "";
        String str2 = str + cvx.d(b(courseState.toByteArray(), (byte) 3));
        LogUtil.c("CourseLinkageUtils", "createCourseStateSampleConfig configData: ", str2);
        return d(cvx.a(str2));
    }

    private static byte[] b(byte[] bArr, byte b) {
        int length = bArr.length;
        int length2 = cvx.d(length).length() / 2;
        byte[] a2 = cvx.a(cvx.d(length));
        ByteBuffer allocate = ByteBuffer.allocate(length2 + 1 + length);
        allocate.put(b).put(a2).put(bArr);
        ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageUtils", "valueCapacity: ", Integer.valueOf(length), ", lenCapacity: ", Integer.valueOf(length2), ", len: ", Integer.valueOf(a2.length));
        return allocate.array();
    }

    private static cvq d(byte[] bArr) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.course.linkage");
        cvqVar.setWearPkgName("hw.course.linkage");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900200005L);
        cvnVar.d(1);
        cvnVar.c(bArr);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }
}
