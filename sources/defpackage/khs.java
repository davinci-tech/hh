package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.DataPromptData;
import com.huawei.hwcommonmodel.datatypes.MsgImage;
import com.huawei.hwcommonmodel.datatypes.MsgText;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.up.model.UserInfomation;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Marker;

/* loaded from: classes5.dex */
public class khs {
    private static boolean b = false;
    private static int c = 30;
    private static int e = 30;
    private static int f = 30;
    private static boolean g = false;
    private static boolean h = false;

    /* renamed from: a, reason: collision with root package name */
    private static StorageParams f14376a = new StorageParams(0);
    private static int d = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;

    private static Bitmap bOm_(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                int i5 = (width * i3) + i4;
                int i6 = iArr[i5];
                int i7 = (int) ((((16711680 & i6) >> 16) * 0.3f) + (((65280 & i6) >> 8) * 0.59f) + ((i6 & 255) * 0.11f));
                iArr[i5] = i7 | (i7 << 16) | (-16777216) | (i7 << 8);
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return ThumbnailUtils.extractThumbnail(createBitmap, i, i2);
    }

    public static jcw c(boolean z, List<MsgImage> list, List<MsgText> list2, String str, khq khqVar) {
        jcw jcwVar = new jcw();
        if (khqVar == null) {
            return jcwVar;
        }
        int b2 = khqVar.b();
        int e2 = khqVar.e();
        int d2 = khqVar.d();
        int c2 = khqVar.c();
        LogUtil.a("NotifySendUtil", "pushNotificationToDevice getDataPromptContent yellowPagesFormat: ", Integer.valueOf(e2), " contentSignFormat: ", Integer.valueOf(d2), " incomingNumberFormat: ", Integer.valueOf(c2));
        jcwVar.b(b2);
        if (z) {
            jcwVar.d(1);
        } else {
            jcwVar.d(0);
        }
        ArrayList<jda> arrayList = new ArrayList<>(16);
        jdb jdbVar = new jdb();
        if (list != null && !list.isEmpty()) {
            c(list, arrayList, jdbVar);
        }
        ArrayList<jcz> arrayList2 = new ArrayList<>(16);
        if (list2 != null && !list2.isEmpty()) {
            e(list2, e2, d2, c2, arrayList2);
        }
        jcwVar.b(arrayList);
        jcwVar.e(arrayList2);
        jcwVar.c(str);
        return jcwVar;
    }

    private static void e(List<MsgText> list, int i, int i2, int i3, ArrayList<jcz> arrayList) {
        for (MsgText msgText : list) {
            if (msgText != null) {
                jcz jczVar = new jcz();
                jczVar.a(msgText.getTextType());
                if (msgText.getTextType() == 5) {
                    jczVar.d(i);
                } else if (msgText.getTextType() == 6) {
                    jczVar.d(i2);
                } else if (msgText.getTextType() == 7) {
                    jczVar.d(i3);
                } else {
                    jczVar.d(2);
                }
                jczVar.d(msgText.getTextContent());
                arrayList.add(jczVar);
            }
        }
    }

    private static void c(List<MsgImage> list, ArrayList<jda> arrayList, jdb jdbVar) {
        byte[] bArr = null;
        for (MsgImage msgImage : list) {
            jda jdaVar = new jda();
            if (msgImage != null) {
                Bitmap imageBitmap = msgImage.getImageBitmap();
                if (imageBitmap != null) {
                    bArr = jdbVar.bFn_(bOm_(imageBitmap, 33, 24));
                }
                jdaVar.c(msgImage.getImageType());
                jdaVar.d(bArr);
                jdaVar.c(msgImage.getImageType());
                jdaVar.e(1);
                jdaVar.d(33);
                jdaVar.b(24);
                arrayList.add(jdaVar);
            }
        }
    }

    public static String d(StringBuilder sb, ArrayList<jcz> arrayList) {
        String str = "";
        if (sb == null) {
            return "";
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            a(sb, arrayList);
            if (sb.length() != 0) {
                str = cvx.e(140) + b(sb.length() / 2);
            }
        }
        LogUtil.a("NotifySendUtil", "notificationMsgToTLVS, textListTlvHex: ", str);
        return str;
    }

    private static void a(StringBuilder sb, ArrayList<jcz> arrayList) {
        String str;
        Iterator<jcz> it = arrayList.iterator();
        while (it.hasNext()) {
            jcz next = it.next();
            int e2 = next.e();
            LogUtil.c("NotifySendUtil", "notificationMsgToTLVS, textType: ", Integer.valueOf(e2));
            if (e2 != 5 || g) {
                if (e2 != 6 || h) {
                    if (e2 != 7 || b) {
                        String str2 = cvx.e(14) + cvx.e(1) + cvx.e(next.e());
                        int b2 = next.b();
                        String str3 = cvx.e(15) + cvx.e(1) + cvx.e(b2);
                        if (next.d() != null) {
                            String c2 = c(b2, next.d());
                            String c3 = c(b2, "...");
                            if (!TextUtils.isEmpty(c2)) {
                                ReleaseLogUtil.e("R_NotifySendUtil", "setStringTextList textContentLength: ", Integer.valueOf(c2.length() / 2), " ,sContentLength: ", Integer.valueOf(d));
                                String b3 = b(b2, c2, c3, d(next));
                                str = cvx.e(16) + b(b3.length() / 2) + b3;
                                b(sb, str2, str3, str);
                            }
                        }
                        str = "";
                        b(sb, str2, str3, str);
                    }
                }
            }
        }
    }

    private static String c(int i, String str) {
        if (i == 2) {
            return cvx.c(str);
        }
        if (i == 1) {
            return cvx.g(str);
        }
        if (i == 3) {
            return cvx.c(str);
        }
        LogUtil.a("NotifySendUtil", "textFormat: ", Integer.valueOf(i));
        return "";
    }

    private static void b(StringBuilder sb, String str, String str2, String str3) {
        String b2 = b((str + str2 + str3).length() / 2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(cvx.e(141));
        sb2.append(b2);
        sb.append(sb2.toString());
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
    }

    public static String b(int i, String str, String str2, int i2) {
        if (str.length() / 2 <= i2) {
            return str;
        }
        String substring = str.substring(0, (i2 - 6) * 2);
        String e2 = cvx.e(substring);
        char[] charArray = e2.substring(e2.length() - 2).toCharArray();
        char c2 = charArray[0];
        char c3 = charArray[1];
        if (!Character.isHighSurrogate(c2) || !Character.isLowSurrogate(c3)) {
            substring = c(i, e2.substring(0, e2.length() - 1));
            LogUtil.a("NotifySendUtil", "getSportDeviceTextContentHex discard last char");
        }
        String str3 = substring + str2;
        LogUtil.a("NotifySendUtil", "getSportDeviceTextContentHex source textContentHex: ", str);
        return str3;
    }

    private static int d(jcz jczVar) {
        if (jczVar.e() == 5) {
            return f;
        }
        if (jczVar.e() == 6) {
            return e;
        }
        if (jczVar.e() == 7) {
            return c;
        }
        return d;
    }

    public static String b(int i) {
        if (i > 127) {
            return cvx.e((i >> 7) + 128) + cvx.e(i & 127);
        }
        return cvx.e(i);
    }

    public static void a(khz khzVar) {
        if (khzVar == null) {
            return;
        }
        g = khzVar.j();
        h = khzVar.f();
        b = khzVar.d();
        d = khzVar.b();
        f = khzVar.e();
        e = khzVar.a();
        c = khzVar.c();
    }

    private static String e(int i, String str) {
        String str2;
        String str3 = "";
        if (str != null) {
            if (i == 2) {
                str2 = cvx.c(str);
            } else if (i == 1) {
                str2 = cvx.g(str);
            } else {
                LogUtil.h("NotifySendUtil", "textFormat is default");
                str2 = "";
            }
            if (!"".equals(str2)) {
                str3 = cvx.e(str2.length() / 2);
            } else {
                str3 = cvx.e(0);
            }
        } else {
            str2 = "";
        }
        return cvx.e(16) + str3 + str2;
    }

    public static String a(jcy jcyVar, DataPromptData dataPromptData) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        str = "";
        if (jcyVar == null || dataPromptData == null) {
            return "";
        }
        if (jcyVar.g()) {
            if (jcyVar.f()) {
                String str6 = cvx.e(15) + cvx.e(1) + cvx.e(dataPromptData.getTextFormat());
                str5 = jcyVar.h() ? e(dataPromptData.getTextFormat(), dataPromptData.getTextContent()) : "";
                str = str6;
            } else {
                str5 = "";
            }
            String str7 = cvx.e(141) + b((str + str5).length() / 2);
            StringBuilder sb = new StringBuilder();
            sb.append(cvx.e(140));
            sb.append(b(((str + str5).length() / 2) + 2));
            str4 = sb.toString();
            String str8 = str5;
            str2 = str;
            str = str7;
            str3 = str8;
        } else {
            str2 = "";
            str3 = str2;
            str4 = str3;
        }
        khr khrVar = new khr();
        khrVar.d(str);
        khrVar.c(str2);
        khrVar.a(str3);
        khrVar.e(str4);
        return e(jcyVar, dataPromptData, khrVar);
    }

    private static String e(jcy jcyVar, DataPromptData dataPromptData, khr khrVar) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8 = "";
        if (jcyVar.b()) {
            if (jcyVar.a()) {
                if (jcyVar.c()) {
                    str7 = cvx.e(8) + cvx.e(2) + cvx.a(dataPromptData.getDotMatrixHeight());
                } else {
                    str7 = "";
                }
                if (jcyVar.d()) {
                    str3 = cvx.e(9) + cvx.e(2) + cvx.a(dataPromptData.getDotMatrixWidth());
                } else {
                    str3 = "";
                }
                if (jcyVar.e()) {
                    str8 = cvx.e(10) + cvx.e(1) + cvx.e(dataPromptData.getDotMatrixColor());
                }
                String str9 = str7;
                str6 = c(jcyVar, dataPromptData);
                str5 = str8;
                str8 = str9;
            } else {
                str5 = "";
                str6 = str5;
                str3 = str6;
            }
            String b2 = b((str8 + str3 + str5 + str6).length() / 2);
            String str10 = str6;
            str2 = str5;
            str = str8;
            str8 = cvx.e(134) + b2;
            str4 = str10;
        } else {
            str = "";
            str2 = str;
            str3 = str2;
            str4 = str3;
        }
        String str11 = cvx.e(UserInfomation.WEIGHT_DEFAULT_ENGLISH) + b((str8 + khrVar.e() + khrVar.c() + khrVar.a() + khrVar.b() + str + str3 + str2 + str4).length() / 2);
        String str12 = (cvx.e(2) + cvx.e(1) + cvx.e(dataPromptData.getPromptType())) + (cvx.e(3) + cvx.e(1) + cvx.e(dataPromptData.getMotorEnable())) + str11 + str8 + str + str3 + str2 + str4 + khrVar.e() + khrVar.c() + khrVar.a() + khrVar.b();
        LogUtil.a("NotifySendUtil", "promptMsgToTLVS result: ", str12);
        return str12;
    }

    private static String c(jcy jcyVar, DataPromptData dataPromptData) {
        if (!jcyVar.a() || dataPromptData.getDotMatrixData() == null) {
            return "";
        }
        String d2 = cvx.d(dataPromptData.getDotMatrixData());
        return cvx.e(11) + b(d2.length() / 2) + d2;
    }

    private static long d(Context context, String str) {
        long j;
        String b2 = SharedPreferenceManager.b(context, String.valueOf(10039), str);
        try {
            j = Long.parseLong(b2);
        } catch (NumberFormatException unused) {
            LogUtil.b("NotifySendUtil", "getCurrentSystemTime NumberFormatException");
            j = 0;
        }
        LogUtil.a("NotifySendUtil", "getCurrentSystemTime currentTime is: ", b2, " ,time is: ", Long.valueOf(j));
        return j;
    }

    private static void c(Context context, long j, String str) {
        if (SharedPreferenceManager.e(context, String.valueOf(10039), str, String.valueOf(j), f14376a) == 0) {
            LogUtil.a("NotifySendUtil", "setCurrentSystemTime setSharedPreference success");
        } else {
            LogUtil.h("NotifySendUtil", "setCurrentSystemTime setSharedPreference failed");
        }
    }

    public static long c(Context context) {
        if (context == null) {
            LogUtil.h("NotifySendUtil", "getSendingMessageNumberTime context is null");
            return 0L;
        }
        LogUtil.a("NotifySendUtil", "getSendingMessageNumberTime");
        return d(context, "notify_update_record_sending_number_time");
    }

    public static void d(Context context, long j) {
        if (context == null) {
            LogUtil.h("NotifySendUtil", "setSendingMessageNumberTime context is null");
        } else {
            LogUtil.a("NotifySendUtil", "setSendingMessageNumberTime is: ", Long.valueOf(j));
            c(context, j, "notify_update_record_sending_number_time");
        }
    }

    public static long a(Context context) {
        if (context == null) {
            LogUtil.h("NotifySendUtil", "getReportBiTime context is null");
            return 0L;
        }
        LogUtil.a("NotifySendUtil", "getReportBiTime");
        return d(context, "notify_update_report_sending_number_time");
    }

    public static void a(Context context, long j) {
        if (context == null) {
            LogUtil.h("NotifySendUtil", "setReportBiTime context is null");
        } else {
            LogUtil.a("NotifySendUtil", "setReportBiTime is: ", Long.valueOf(j));
            c(context, j, "notify_update_report_sending_number_time");
        }
    }

    public static void e(Context context, int i) {
        int i2;
        if (context == null) {
            LogUtil.h("NotifySendUtil", "setSendingNumber context is null");
            return;
        }
        try {
            i2 = Integer.parseInt(SharedPreferenceManager.b(context, String.valueOf(10039), "sending_message_number"));
        } catch (NumberFormatException unused) {
            LogUtil.b("NotifySendUtil", "setSendingNumber NumberFormatException");
            i2 = 0;
        }
        if (SharedPreferenceManager.e(context, String.valueOf(10039), "sending_message_number", String.valueOf(i2 + i), f14376a) == 0) {
            LogUtil.a("NotifySendUtil", "setSendingNumber setSharedPreference success");
        } else {
            LogUtil.h("NotifySendUtil", "setSendingNumber setSharedPreference failed");
        }
    }

    public static void b(Context context) {
        if (context == null) {
            LogUtil.h("NotifySendUtil", "reportBiToService context is null");
            return;
        }
        String b2 = SharedPreferenceManager.b(context, String.valueOf(10039), "sending_message_number");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("push", b2);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.NOTIFY_SEND_MESSAGE_2129003.value(), linkedHashMap);
        LogUtil.a("NotifySendUtil", "report BI success,reportBiToService value is: ", b2);
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("NotifySendUtil", "formatNumbers phoneNumber is empty");
            return "";
        }
        String formatNumber = PhoneNumberUtils.formatNumber(str, Locale.getDefault().getCountry());
        return TextUtils.isEmpty(formatNumber) ? str : formatNumber;
    }

    public static String a(String str) {
        return (TextUtils.isEmpty(str) || str.length() < 3) ? str : str.substring(str.length() - 3);
    }

    public static kil c(kil kilVar) {
        if (kilVar == null) {
            LogUtil.b("NotifySendUtil", "pushNotificationToDevice querySms ERROR: smsReceivedInfo is null,please check it!");
            return null;
        }
        long h2 = kilVar.h();
        if (h2 == 0) {
            LogUtil.b("NotifySendUtil", "pushNotificationToDevice querySms ERROR: smsReceivedTime is 0L,please check it!");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        a(h2, arrayList);
        if (!arrayList.isEmpty() && arrayList.get(0) != null) {
            LogUtil.a("NotifySendUtil", "pushNotificationToDevice querySmsBoxByTime find resultList have ", Integer.valueOf(arrayList.size()), " items,first: ", arrayList.toString());
            return (kil) arrayList.get(0);
        }
        if (!TextUtils.isEmpty(kilVar.d()) || TextUtils.isEmpty(kilVar.c())) {
            return null;
        }
        LogUtil.a("NotifySendUtil", "pushNotificationToDevice querySms by content start");
        e(kilVar);
        a(kilVar.c(), arrayList);
        return b(h2, arrayList);
    }

    private static void a(String str, final List<kil> list) {
        if (TextUtils.isEmpty(str) || list == null) {
            return;
        }
        Uri uri = Telephony.Sms.Inbox.CONTENT_URI;
        String[] strArr = {"_id", "address", "date", "read", "type", "body", "sub_id", "error_code", "creator"};
        ContactsDatabaseUtils.a e2 = new ContactsDatabaseUtils.a().bMm_(uri).d(strArr).c("body LIKE ?  AND read=0 AND type=1").c(new String[]{"%" + str + '%'}).e("date DESC");
        LogUtil.a("NotifySendUtil", "pushNotificationToDevice selectSmsListByContent sql: ", jdz.a(uri.toString(), strArr, "body LIKE ?  AND read=0 AND type=1", null, "date DESC"));
        ContactsDatabaseUtils.b(e2, new ContactsDatabaseUtils.ResultCallback() { // from class: khv
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
            public final void onResult(Object obj) {
                khs.bOp_(list, (Cursor) obj);
            }
        });
    }

    static /* synthetic */ void bOp_(List list, Cursor cursor) {
        Objects.requireNonNull(list);
        bOl_(cursor, new khx(list));
    }

    public static void a(long j, final List<kil> list) {
        LogUtil.a("NotifySendUtil", "querySmsBoxByTime start!smsReceivedTime: ", Long.valueOf(j));
        if (j == 0 || list == null) {
            return;
        }
        Uri uri = Telephony.Sms.Inbox.CONTENT_URI;
        String[] strArr = {"_id", "address", "date", "read", "type", "body", "sub_id", "error_code", "creator"};
        String[] strArr2 = {String.valueOf(j)};
        ContactsDatabaseUtils.a e2 = new ContactsDatabaseUtils.a().bMm_(uri).d(strArr).c("date=? AND read=0 AND type=1").c(strArr2).e("date DESC");
        LogUtil.a("NotifySendUtil", "pushNotificationToDevice querySmsBoxByTime sql:", jdz.a(uri.toString(), strArr, "date=? AND read=0 AND type=1", strArr2, "date DESC"));
        ContactsDatabaseUtils.b(e2, new ContactsDatabaseUtils.ResultCallback() { // from class: khy
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils.ResultCallback
            public final void onResult(Object obj) {
                khs.bOo_(list, (Cursor) obj);
            }
        });
    }

    static /* synthetic */ void bOo_(List list, Cursor cursor) {
        Objects.requireNonNull(list);
        bOl_(cursor, new khx(list));
    }

    private static kil b(long j, List<kil> list) {
        kil kilVar = null;
        if (j == 0) {
            LogUtil.h("NotifySendUtil", "pushNotificationToDevice getRecentOneSms failed: smsReceivedTime is 0");
            return null;
        }
        boolean z = false;
        if (list.size() == 1) {
            LogUtil.a("NotifySendUtil", "pushNotificationToDevice getRecentOneSms result list has only one item: ", list.get(0).toString(), ",notiPushTime: ", Long.valueOf(j));
            return list.get(0);
        }
        for (kil kilVar2 : list) {
            long h2 = kilVar2.h();
            if (!z || (h2 <= j && Math.abs(j - h2) <= Math.abs(j - kilVar.h()))) {
                kilVar = kilVar2;
                z = true;
            }
        }
        if (kilVar != null) {
            LogUtil.a("NotifySendUtil", "pushNotificationToDevice getRecentOneSms result is: ", kilVar.toString(), ",notiPushTime: ", Long.valueOf(j));
        } else {
            LogUtil.b("NotifySendUtil", "pushNotificationToDevice getRecentOneSms occurs error: mostRecently SMS is null");
        }
        return kilVar;
    }

    private static void e(kil kilVar) {
        String c2 = kilVar.c();
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("NotifySendUtil", "pushNotificationToDevice  querySms by content error: content is empty");
            return;
        }
        int indexOf = c2.indexOf("]");
        if (indexOf > 0 && indexOf < c2.length() - 1) {
            c2 = c2.substring(indexOf + 1).trim();
        }
        if (c2.length() > 10) {
            c2 = c2.substring(0, 10);
            char[] charArray = c2.substring(c2.length() - 2).toCharArray();
            if (!Character.isHighSurrogate(charArray[0]) || !Character.isLowSurrogate(charArray[1])) {
                c2 = c2.substring(0, c2.length() - 1);
                LogUtil.a("NotifySendUtil", "pushNotificationToDevice querySmsByContent discard last char");
            }
        }
        LogUtil.a("NotifySendUtil", "pushNotificationToDevice dealWithContent content:", c2);
        kilVar.d(c2);
    }

    public static void bOl_(Cursor cursor, ContactsDatabaseUtils.ResultCallback<kil> resultCallback) {
        int i = 0;
        int i2 = 1;
        if (ContactsDatabaseUtils.bMi_(cursor)) {
            Object[] objArr = new Object[1];
            objArr[0] = "pushNotificationToDevice assembleSmsReceivedInfo cursor ".concat(cursor == null ? "is null!" : "cannot move!");
            LogUtil.h("NotifySendUtil", objArr);
            return;
        }
        LogUtil.c("NotifySendUtil", "pushNotificationToDevice assembleSmsReceivedInfo cursor.getCount(): ", Integer.valueOf(cursor.getCount()), ",cursor.getColumnCount(): ", Integer.valueOf(cursor.getColumnCount()), ",cursor.getColumnNames(): ", Arrays.toString(cursor.getColumnNames()));
        Integer bGi_ = jdz.bGi_("_id", cursor, "NotifySendUtil");
        Integer bGi_2 = jdz.bGi_("creator", cursor, "NotifySendUtil");
        Integer bGi_3 = jdz.bGi_("address", cursor, "NotifySendUtil");
        Integer bGi_4 = jdz.bGi_("body", cursor, "NotifySendUtil");
        Integer bGi_5 = jdz.bGi_("error_code", cursor, "NotifySendUtil");
        Integer bGi_6 = jdz.bGi_("sub_id", cursor, "NotifySendUtil");
        Integer bGi_7 = jdz.bGi_("type", cursor, "NotifySendUtil");
        Integer bGi_8 = jdz.bGi_("read", cursor, "NotifySendUtil");
        Integer bGi_9 = jdz.bGi_("date", cursor, "NotifySendUtil");
        if (bGi_ == null) {
            LogUtil.h("NotifySendUtil", "pushNotificationToDevice assembleSmsReceivedInfo smsDbIdIndex is null!");
            return;
        }
        while (true) {
            i += i2;
            kil kilVar = new kil(bGi_2 == null ? "" : cursor.getString(bGi_2.intValue()), bGi_3 == null ? "" : cursor.getString(bGi_3.intValue()), bGi_4 != null ? cursor.getString(bGi_4.intValue()) : "", cursor.getInt(bGi_.intValue()), bGi_5 == null ? -100 : cursor.getInt(bGi_5.intValue()), bGi_6 == null ? -100 : cursor.getInt(bGi_6.intValue()), bGi_7 == null ? -100 : cursor.getInt(bGi_7.intValue()), bGi_8 != null ? cursor.getInt(bGi_8.intValue()) : -100, bGi_9 == null ? -100L : cursor.getLong(bGi_9.intValue()));
            LogUtil.c("NotifySendUtil", "pushNotificationToDevice assembleSmsReceivedInfo smsReceivedInfo: ", kilVar.toString(), ",content: " + kilVar.c());
            resultCallback.onResult(kilVar);
            if (!cursor.moveToNext()) {
                LogUtil.a("NotifySendUtil", "pushNotificationToDevice assembleSmsReceivedInfo cursor.getCount(): ", Integer.valueOf(cursor.getCount()), ",result number: ", Integer.valueOf(i));
                return;
            }
            i2 = 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ab, code lost:
    
        if (r1 == null) goto L28;
     */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00b2: MOVE (r13 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:31:0x00b2 */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.kil c(java.lang.String r14) {
        /*
            java.lang.String r0 = "NotifySendUtil"
            android.net.Uri r7 = android.provider.Telephony.Sms.Inbox.CONTENT_URI
            java.lang.String r8 = "address"
            java.lang.String r9 = "sub_id"
            java.lang.String[] r10 = new java.lang.String[]{r8, r9}
            r11 = 0
            r12 = 1
            r13 = 0
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L9f java.lang.Throwable -> La1
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L9f java.lang.Throwable -> La1
            java.lang.String[] r5 = new java.lang.String[]{r14}     // Catch: java.lang.Throwable -> L9f java.lang.Throwable -> La1
            java.lang.String r4 = "address=?"
            java.lang.String r6 = "date DESC"
            r2 = r7
            r3 = r10
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L9f java.lang.Throwable -> La1
            r2 = 2
            if (r1 == 0) goto L85
            int r3 = r1.getCount()     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            if (r3 != 0) goto L30
            goto L85
        L30:
            java.lang.Object[] r3 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r4 = r7.toString()     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String[] r5 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r14 = a(r14)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r5[r11] = r14     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r14 = "address=?"
            java.lang.String r6 = "date DESC"
            java.lang.String r14 = defpackage.jdz.a(r4, r10, r14, r5, r6)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r3[r11] = r14     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            boolean r14 = r1.moveToNext()     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            if (r14 == 0) goto L82
            int r14 = r1.getColumnIndexOrThrow(r8)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r14 = r1.getString(r14)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            int r3 = r1.getColumnIndexOrThrow(r9)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            int r3 = r1.getInt(r3)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r5 = "querySmsByPhoneNumber address: "
            r4[r11] = r5     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r5 = a(r14)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r4[r12] = r5     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r5 = " subId:"
            r4[r2] = r5     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r5 = 3
            r4[r5] = r2     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            kil r2 = new kil     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r2.<init>(r14, r3)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r13 = r2
        L82:
            if (r1 == 0) goto Lb0
            goto Lad
        L85:
            java.lang.Object[] r14 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            java.lang.String r2 = "querySmsByPhoneNumber cursor is null or no data "
            r14[r11] = r2     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            if (r1 != 0) goto L8f
            r2 = r12
            goto L90
        L8f:
            r2 = r11
        L90:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            r14[r12] = r2     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            com.huawei.hwlogsmodel.LogUtil.h(r0, r14)     // Catch: java.lang.Throwable -> La2 java.lang.Throwable -> Lb1
            if (r1 == 0) goto L9e
            r1.close()
        L9e:
            return r13
        L9f:
            r14 = move-exception
            goto Lb3
        La1:
            r1 = r13
        La2:
            java.lang.Object[] r14 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r2 = "querySmsByPhoneNumber Exception"
            r14[r11] = r2     // Catch: java.lang.Throwable -> Lb1
            com.huawei.hwlogsmodel.LogUtil.b(r0, r14)     // Catch: java.lang.Throwable -> Lb1
            if (r1 == 0) goto Lb0
        Lad:
            r1.close()
        Lb0:
            return r13
        Lb1:
            r14 = move-exception
            r13 = r1
        Lb3:
            if (r13 == 0) goto Lb8
            r13.close()
        Lb8:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.khs.c(java.lang.String):kil");
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ff, code lost:
    
        if (r2 != null) goto L49;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.kil d(java.lang.String r16, java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.khs.d(java.lang.String, java.lang.String):kil");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0084, code lost:
    
        if (r6 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00af, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00ac, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00aa, code lost:
    
        if (0 == 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(int r15, java.lang.String r16) {
        /*
            java.lang.String r0 = "sub"
            java.lang.String r1 = "sub_id"
            java.lang.String r2 = "NotifySendUtil"
            r3 = 2
            r4 = 1
            r5 = 0
            r6 = 0
            r7 = -1
            android.content.Context r8 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            android.content.ContentResolver r9 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            android.net.Uri r10 = android.provider.Telephony.Mms.CONTENT_URI     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String[] r11 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r11[r5] = r1     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r11[r4] = r0     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String[] r13 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String r8 = java.lang.String.valueOf(r15)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r13[r5] = r8     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String r12 = "_id=?"
            r14 = 0
            android.database.Cursor r6 = r9.query(r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            if (r6 == 0) goto L87
            int r8 = r6.getCount()     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            if (r8 != 0) goto L35
            goto L87
        L35:
            boolean r8 = r6.moveToNext()     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            if (r8 == 0) goto L84
            int r1 = r6.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            int r7 = r6.getInt(r1)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            int r0 = r6.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            if (r1 != 0) goto L5f
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.nio.charset.Charset r8 = java.nio.charset.StandardCharsets.ISO_8859_1     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            byte[] r0 = r0.getBytes(r8)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.nio.charset.Charset r8 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r1.<init>(r0, r8)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r0 = r1
        L5f:
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String r8 = "querySubIdByMmsPdu subId: "
            r1[r5] = r8     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r1[r4] = r8     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String r8 = " subject: "
            r1[r3] = r8     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r8 = 3
            r1[r8] = r0     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            r1 = r16
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            if (r0 == 0) goto L84
            if (r6 == 0) goto L83
            r6.close()
        L83:
            return r7
        L84:
            if (r6 == 0) goto Laf
            goto Lac
        L87:
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            java.lang.String r1 = "querySubIdByMmsPdu pdu cursor is null"
            r0[r5] = r1     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            com.huawei.hwlogsmodel.LogUtil.h(r2, r0)     // Catch: java.lang.Throwable -> L96 java.lang.IllegalArgumentException -> L98 android.database.SQLException -> L9a
            if (r6 == 0) goto L95
            r6.close()
        L95:
            return r7
        L96:
            r0 = move-exception
            goto Lb0
        L98:
            r0 = move-exception
            goto L9b
        L9a:
            r0 = move-exception
        L9b:
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L96
            java.lang.String r3 = "querySubIdByMmsPdu Exception "
            r1[r5] = r3     // Catch: java.lang.Throwable -> L96
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L96
            r1[r4] = r0     // Catch: java.lang.Throwable -> L96
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)     // Catch: java.lang.Throwable -> L96
            if (r6 == 0) goto Laf
        Lac:
            r6.close()
        Laf:
            return r7
        Lb0:
            if (r6 == 0) goto Lb5
            r6.close()
        Lb5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.khs.a(int, java.lang.String):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x007a, code lost:
    
        if (0 == 0) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String e(int r12) {
        /*
            java.lang.String r0 = "NotifySendUtil"
            java.lang.String r1 = "address"
            java.lang.String r2 = ""
            r3 = 1
            r4 = 0
            r5 = 0
            android.net.Uri r6 = android.provider.Telephony.Mms.CONTENT_URI     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            android.net.Uri$Builder r6 = r6.buildUpon()     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            android.net.Uri$Builder r12 = r6.appendPath(r12)     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String r6 = "addr"
            android.net.Uri$Builder r12 = r12.appendPath(r6)     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            android.net.Uri r7 = r12.build()     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String[] r8 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            r8[r4] = r1     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String[] r10 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String r12 = "137"
            r10[r4] = r12     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            android.content.Context r12 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            android.content.ContentResolver r6 = r12.getContentResolver()     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String r9 = "type=?"
            r11 = 0
            android.database.Cursor r5 = r6.query(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            if (r5 == 0) goto L56
            int r12 = r5.getCount()     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            if (r12 != 0) goto L44
            goto L56
        L44:
            boolean r12 = r5.moveToNext()     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            if (r12 == 0) goto L53
            int r12 = r5.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String r12 = r5.getString(r12)     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            r2 = r12
        L53:
            if (r5 == 0) goto L7f
            goto L7c
        L56:
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            java.lang.String r1 = "queryAddressByMmsAddr addr cursor is null"
            r12[r4] = r1     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            com.huawei.hwlogsmodel.LogUtil.h(r0, r12)     // Catch: java.lang.Throwable -> L65 java.lang.IllegalArgumentException -> L67 android.database.SQLException -> L69
            if (r5 == 0) goto L64
            r5.close()
        L64:
            return r2
        L65:
            r12 = move-exception
            goto L80
        L67:
            r12 = move-exception
            goto L6a
        L69:
            r12 = move-exception
        L6a:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L65
            java.lang.String r6 = "queryAddressByMmsAddr Exception "
            r1[r4] = r6     // Catch: java.lang.Throwable -> L65
            java.lang.String r12 = health.compact.a.LogAnonymous.b(r12)     // Catch: java.lang.Throwable -> L65
            r1[r3] = r12     // Catch: java.lang.Throwable -> L65
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L65
            if (r5 == 0) goto L7f
        L7c:
            r5.close()
        L7f:
            return r2
        L80:
            if (r5 == 0) goto L85
            r5.close()
        L85:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.khs.e(int):java.lang.String");
    }

    private static String b(String str) {
        if (str.length() <= 10) {
            return str;
        }
        String substring = str.substring(0, 10);
        char[] charArray = substring.substring(substring.length() - 2).toCharArray();
        if (Character.isHighSurrogate(charArray[0]) && Character.isLowSurrogate(charArray[1])) {
            return substring;
        }
        String substring2 = substring.substring(0, substring.length() - 1);
        LogUtil.a("NotifySendUtil", "queryMmsByContent discard last char");
        return substring2;
    }

    public static List<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("NotifySendUtil", "getValidPhoneNumber phoneNumber is empty");
            return arrayList;
        }
        String replaceAll = str.replaceAll("[^\\d]", "");
        if (replaceAll.length() < 3) {
            LogUtil.h("NotifySendUtil", "getValidPhoneNumber replaceNumber not is digits only");
            return arrayList;
        }
        if (replaceAll.startsWith("86")) {
            arrayList.add(Marker.ANY_NON_NULL_MARKER + replaceAll);
            arrayList.add(replaceAll.substring(2));
        } else {
            arrayList.add(replaceAll);
        }
        return arrayList;
    }

    public static void bOq_(Intent intent, Context context) {
        if (intent == null) {
            LogUtil.h("NotifySendUtil", "pushPromptToDevice intent is null");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("NotifySendUtil", "pushPromptToDevice current device is disconnected");
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo != null && deviceInfo.getProductType() == 57) {
            LogUtil.h("NotifySendUtil", "pushPromptToDevice current device is galileo");
        } else {
            bOn_(intent, context);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void bOn_(Intent intent, Context context) {
        String str;
        char c2;
        String str2 = null;
        try {
            str = intent.getStringExtra("type");
            try {
                str2 = intent.getStringExtra("message_short");
            } catch (Exception unused) {
                LogUtil.b("NotifySendUtil", "pushPromptToDevice error");
                LogUtil.a("NotifySendUtil", "pushPromptToDevice type: ", str, " message_short: ", str2);
                if (str != null) {
                    return;
                } else {
                    return;
                }
            }
        } catch (Exception unused2) {
            str = null;
        }
        LogUtil.a("NotifySendUtil", "pushPromptToDevice type: ", str, " message_short: ", str2);
        if (str != null || str2 == null) {
            return;
        }
        DataPromptData dataPromptData = new DataPromptData();
        boolean z = true;
        dataPromptData.setMotorEnable(1);
        dataPromptData.setTextFormat(2);
        dataPromptData.setTextContent(str2);
        jcy jcyVar = new jcy(new boolean[]{false, false, false, false, false, true, true, true});
        str.hashCode();
        switch (str.hashCode()) {
            case -1271823248:
                if (str.equals("flight")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 110621192:
                if (str.equals("train")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1223440372:
                if (str.equals(HwExerciseConstants.JSON_NAME_WORKOUT_WEATHER)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1899453439:
                if (str.equals("warm_remind")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            dataPromptData.setPromptType(7);
        } else if (c2 == 1) {
            dataPromptData.setPromptType(8);
        } else if (c2 == 2) {
            dataPromptData.setPromptType(10);
        } else if (c2 != 3) {
            z = false;
        } else {
            dataPromptData.setPromptType(9);
        }
        b(z, dataPromptData, jcyVar, context);
    }

    private static void b(boolean z, DataPromptData dataPromptData, jcy jcyVar, Context context) {
        if (z) {
            LogUtil.a("NotifySendUtil", "pushPromptToDevice start NotifySendData to send command");
            if (khu.a(context) == null) {
                LogUtil.h("NotifySendUtil", "isSendCommand NotifySendData is null");
                return;
            } else {
                khu.a(context).a(khu.a(context).c(dataPromptData, jcyVar), 1);
                return;
            }
        }
        LogUtil.h("NotifySendUtil", "postPromptMsg failure type is not support");
    }

    public static boolean l() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportRepeatedNotifyProcess deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 94);
    }

    public static boolean d() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportDeleteSingleNotification deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 120);
    }

    public static boolean j() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportHarmonyNotificationCollaboration deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 119);
    }

    public static boolean c() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.action.NOTIFICATION_DECISION_CENTER");
        List<ResolveInfo> queryIntentContentProviders = BaseApplication.getContext().getPackageManager().queryIntentContentProviders(intent, 0);
        if (queryIntentContentProviders != null && !queryIntentContentProviders.isEmpty()) {
            return true;
        }
        LogUtil.h("NotifySendUtil", "isPhoneHarmonyNotificationCollaboration resolveInfoList is empty");
        return false;
    }

    public static boolean h() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportHfpConnectNum deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 126);
    }

    public static boolean f() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportNotifyReminderSwitchClose deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 121);
    }

    public static boolean m() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportWechatCallPush deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, OldToNewMotionPath.SPORT_TYPE_TENNIS);
    }

    public static boolean n() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportOverseasCallPush deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 152);
    }

    public static void k() {
        int i;
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        if (CollectionUtils.d(deviceList)) {
            e(-3, 0);
            LogUtil.a("NotifySendUtil", "saveToProviderConnectDeviceInfo HAS_NO_CONNECTED_MAIN_DEVICES!");
            return;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = 0;
                break;
            }
            deviceInfo = it.next();
            if (a(deviceInfo)) {
                i = 1;
                break;
            }
        }
        if (deviceInfo == null) {
            return;
        }
        e(deviceInfo.getProductType(), i);
        Object[] objArr = new Object[2];
        objArr[0] = "saveToProviderConnectDeviceInfo device isSupportNotify? ";
        objArr[1] = Boolean.valueOf(i == 1);
        LogUtil.a("NotifySendUtil", objArr);
    }

    private static void e(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("serialIndex", (Integer) 1);
        contentValues.put("DeviceType", Integer.valueOf(i));
        contentValues.put("isSupportNotify", Integer.valueOf(i2));
        try {
            BaseApplication.getContext().getContentResolver().update(Uri.parse(jdz.b), contentValues, "serialIndex=?", new String[]{String.valueOf(1)});
        } catch (SQLException | IllegalArgumentException | SecurityException e2) {
            LogUtil.b("NotifySendUtil", "insertStateIntoConnectDeviceInfo Exception:", LogAnonymous.b(e2));
        }
    }

    public static boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isDeviceSupportNotificationPush: input param DEVICEINFO is NULL!");
            return false;
        }
        DeviceCapability d2 = d(deviceInfo);
        if (d2 == null) {
            LogUtil.h("NotifySendUtil", "isDeviceSupportNotificationPush: device capabilities is NULL!");
            return false;
        }
        if (!d2.isMessageAlert()) {
            LogUtil.h("NotifySendUtil", "isDeviceSupportNotificationPush: device notification push capability INCAPABLE!");
            return false;
        }
        LogUtil.a("NotifySendUtil", "isDeviceSupportNotificationPush: device notification push capability SUPPORT!");
        return true;
    }

    public static DeviceCapability d(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> queryDeviceCapability;
        HwDeviceMgrInterface b2 = jsz.b(BaseApplication.getContext());
        if (b2 == null || (queryDeviceCapability = b2.queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "NotifySendUtil")) == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }

    public static boolean b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportSyncIconMain deviceInfo is null");
            return false;
        }
        boolean c2 = cwi.c(deviceInfo, 73);
        LogUtil.c("NotifySendUtil", "0x18 deviceReplyAbility result is: ", Boolean.valueOf(c2));
        return c2;
    }

    public static boolean g() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportRepeatedNotifyProcess deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 176);
    }

    public static boolean o() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportVoipType deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 185);
    }

    public static boolean e() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportVoipType deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 194);
    }

    public static boolean a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportOverseasCallPush deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 200);
    }

    public static boolean i() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendUtil");
        DeviceInfo deviceInfo = deviceList.isEmpty() ? null : deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("NotifySendUtil", "isSupportRepeatedNotifyProcess deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 219);
    }
}
