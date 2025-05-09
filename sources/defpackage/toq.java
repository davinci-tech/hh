package defpackage;

import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class toq {

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, String> f17282a;

    static {
        HashMap hashMap = new HashMap();
        f17282a = hashMap;
        hashMap.put(0, "Success");
        f17282a.put(1, "Generic error");
        f17282a.put(2, "Health app not exist");
        f17282a.put(3, "Health app not login");
        f17282a.put(4, "Health app unbound device");
        f17282a.put(5, "Invalid argument");
        f17282a.put(6, "Server remote binder is null");
        f17282a.put(7, "User unauthorized in health");
        f17282a.put(8, "Scope unauthorized");
        f17282a.put(9, "User unauthorized in wear engine");
        f17282a.put(10, "Invalid file");
        f17282a.put(11, "Too much receivers");
        f17282a.put(12, "Internal error");
        f17282a.put(13, "Device version is not supported");
        f17282a.put(14, "Health version is low");
        f17282a.put(15, "Health does not has permission to start");
        f17282a.put(16, "Device is not connected");
        f17282a.put(17, "Device uuid is invalid");
        f17282a.put(18, "Interface is not supported");
        f17282a.put(200, "Watch app not exist");
        f17282a.put(201, "Watch app not running");
        f17282a.put(202, "Watch app running");
        f17282a.put(203, "Other error");
        f17282a.put(204, "Phone app not exist");
        f17282a.put(205, "Phone app exist");
        f17282a.put(206, "Fail");
        f17282a.put(Integer.valueOf(a.w), "Success");
        f17282a.put(300, "Sensor Watch Wear Off");
        f17282a.put(301, "Sensor Watch Lead Off");
        f17282a.put(302, "Sensor Watch User Stop");
        f17282a.put(303, "Sensor Watch Sensor Used");
        f17282a.put(304, "Sensor Not Abilites");
        f17282a.put(Integer.valueOf(a.A), "Send Offline Msg Success");
        f17282a.put(19, "Service Timeout");
        f17282a.put(20, "Power mode changing");
    }

    public static String c(int i) {
        return f17282a.containsKey(Integer.valueOf(i)) ? f17282a.get(Integer.valueOf(i)) : "Generic error";
    }

    public static int b(String str) {
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            tov.d("WearEngineErrorCode", "NumberFormatException");
            i = 1;
        }
        if (f17282a.containsKey(Integer.valueOf(i))) {
            return i;
        }
        return 1;
    }
}
