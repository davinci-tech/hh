package defpackage;

import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class kvr {
    public static boolean d(SmartMsgDbObject smartMsgDbObject) {
        return (smartMsgDbObject == null || smartMsgDbObject.getStatus() == 1) ? false : true;
    }

    public static boolean c(SmartMsgDbObject smartMsgDbObject) {
        return smartMsgDbObject != null && smartMsgDbObject.getExpireTime() > 0 && smartMsgDbObject.getExpireTime() < jec.i();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean d(java.lang.String r8) {
        /*
            java.lang.String r0 = "SMART_SmartDataInteractors"
            r1 = 0
            if (r8 == 0) goto L71
            int r2 = r8.length()
            r3 = 8
            if (r2 >= r3) goto Le
            goto L71
        Le:
            int r2 = d()
            r4 = 4
            java.lang.String r5 = r8.substring(r1, r4)     // Catch: java.lang.NumberFormatException -> L26
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L26
            java.lang.String r4 = r8.substring(r4, r3)     // Catch: java.lang.NumberFormatException -> L24
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L24
            goto L36
        L24:
            r4 = move-exception
            goto L28
        L26:
            r4 = move-exception
            r5 = r1
        L28:
            java.lang.String r6 = "judgeDisplayTime NumberFormatException "
            java.lang.String r4 = r4.getMessage()
            java.lang.Object[] r4 = new java.lang.Object[]{r6, r4}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)
            r4 = r1
        L36:
            int r6 = r8.length()
            if (r6 == r3) goto L6c
            r7 = 16
            if (r6 == r7) goto L41
            goto L71
        L41:
            r6 = 12
            java.lang.String r3 = r8.substring(r3, r6)     // Catch: java.lang.NumberFormatException -> L5d
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L5d
            java.lang.String r8 = r8.substring(r6, r7)     // Catch: java.lang.NumberFormatException -> L5d
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.NumberFormatException -> L5d
            if (r2 < r5) goto L58
            if (r2 > r4) goto L58
            goto L70
        L58:
            if (r2 < r3) goto L71
            if (r2 > r8) goto L71
            goto L70
        L5d:
            r8 = move-exception
            java.lang.String r2 = "judgeDisplayTime TWO_PERIODS_OF_TIME NumberFormatException"
            java.lang.String r8 = r8.getMessage()
            java.lang.Object[] r8 = new java.lang.Object[]{r2, r8}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
            goto L71
        L6c:
            if (r2 < r5) goto L71
            if (r2 > r4) goto L71
        L70:
            r1 = 1
        L71:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kvr.d(java.lang.String):boolean");
    }

    public static int d() {
        return nsn.e(new SimpleDateFormat("HHmm").format(new Date(System.currentTimeMillis())));
    }

    public static boolean a(SmartMsgDbObject smartMsgDbObject) {
        if (smartMsgDbObject == null) {
            return false;
        }
        return smartMsgDbObject.getMsgType() == 10000 || smartMsgDbObject.getMsgType() == 10002 || smartMsgDbObject.getMsgType() == 20006 || smartMsgDbObject.getMsgSrc() == 4;
    }

    public static boolean a(SmartMsgDbObject smartMsgDbObject, int i) {
        if (smartMsgDbObject != null && smartMsgDbObject.getStatus() == 1 && smartMsgDbObject.getShowCount() < i) {
            return smartMsgDbObject.getExpireTime() <= 0 || smartMsgDbObject.getExpireTime() >= jec.i();
        }
        return false;
    }
}
