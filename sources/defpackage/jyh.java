package defpackage;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;
import com.google.gson.Gson;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarAllSyncInfo;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarDbBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SmartSimpleCalendarBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class jyh {
    private static final String[] d = {"_id", "calendar_color", "calendar_displayName", "visible"};
    private static final String[] c = {"calendar_id", "_sync_id", "_id", "account_name", "account_type", "title", "eventLocation", "eventTimezone", "eventEndTimezone", "eventStatus", "duration", "organizer", "selfAttendeeStatus", "availability", "hasExtendedProperties", "description", "dtstart", "dtend", "allDay", "hasAlarm", "rrule", "exdate"};
    private static final String[] b = {HeaderType.EVENT_ID, "begin", "rrule", "exdate", "allDay"};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14196a = {HeaderType.EVENT_ID, "state"};
    private static final String[] e = {HeaderType.EVENT_ID, "minutes", "method"};

    private static long e(long j, long j2, long j3) {
        return (j2 <= 0 && j3 > 0) ? j + j3 : j2;
    }

    public static String a(SimpleCalendarBean simpleCalendarBean) {
        if (simpleCalendarBean == null) {
            LogUtil.h("CalendarBuilderUtils", "[buildVCard] : bean is null");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append(simpleCalendarBean.getCalendarId()).append(simpleCalendarBean.getCalendarColor()).append(simpleCalendarBean.getAccountName()).append(simpleCalendarBean.getCalendarDisplayName()).append(simpleCalendarBean.getTitle()).append(simpleCalendarBean.getEventLocation()).append(simpleCalendarBean.getDescription()).append(simpleCalendarBean.getDtstart()).append(simpleCalendarBean.getDtend()).append(simpleCalendarBean.getAllDay()).append(simpleCalendarBean.getHasAlarm()).append(simpleCalendarBean.getMinutes()).append(simpleCalendarBean.getRule());
        return stringBuffer.toString();
    }

    public static String d(SmartSimpleCalendarBean smartSimpleCalendarBean) {
        if (smartSimpleCalendarBean == null) {
            LogUtil.h("CalendarBuilderUtils", "[buildVCard] : bean is null");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append(smartSimpleCalendarBean.getCalendarId()).append(smartSimpleCalendarBean.getCalendarColor()).append(smartSimpleCalendarBean.getAccountName()).append(smartSimpleCalendarBean.getCalendarDisplayName()).append(smartSimpleCalendarBean.getTitle()).append(smartSimpleCalendarBean.getEventLocation()).append(smartSimpleCalendarBean.getDescription()).append(smartSimpleCalendarBean.getDtstart()).append(smartSimpleCalendarBean.getDtend()).append(smartSimpleCalendarBean.getAllDay()).append(smartSimpleCalendarBean.getHasAlarm()).append(smartSimpleCalendarBean.getMinutes()).append(smartSimpleCalendarBean.getRule()).append(smartSimpleCalendarBean.getSyncId()).append(smartSimpleCalendarBean.getEventStatus()).append(smartSimpleCalendarBean.getEventTimezone()).append(smartSimpleCalendarBean.getEventEndTimezone()).append(smartSimpleCalendarBean.getDuration()).append(smartSimpleCalendarBean.getOrganizer()).append(smartSimpleCalendarBean.getSelfAttendeeStatus()).append(smartSimpleCalendarBean.getAvailability()).append(smartSimpleCalendarBean.getHasExtendedProperties()).append(smartSimpleCalendarBean.getMethod()).append(smartSimpleCalendarBean.getExdate()).append(smartSimpleCalendarBean.getVisible());
        return stringBuffer.toString();
    }

    public static List<String> c(List<SimpleCalendarBean> list, int i) {
        LogUtil.a("CalendarBuilderUtils", "generateVcards: start");
        if (list == null || list.isEmpty()) {
            LogUtil.h("CalendarBuilderUtils", "list is null or empty.");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        List b2 = kao.b(list, 10);
        Gson gson = new Gson();
        int i2 = 0;
        while (i2 < b2.size()) {
            int i3 = i2 + 1;
            i += ((List) b2.get(i2)).size() * i3;
            LogUtil.a("CalendarBuilderUtils", "calendarMinor: ", Integer.valueOf(i));
            CalendarAllSyncInfo calendarAllSyncInfo = new CalendarAllSyncInfo();
            calendarAllSyncInfo.setMajor(CommonUtil.a(BaseApplication.getContext(), false));
            calendarAllSyncInfo.setMinor(i);
            calendarAllSyncInfo.setCalendarBeanList((List) b2.get(i2));
            arrayList.add(gson.toJson(calendarAllSyncInfo));
            i2 = i3;
        }
        LogUtil.a("CalendarBuilderUtils", "generateVcards: end, jsonList size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x04d1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x04a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean> e(android.content.Context r52, int r53) {
        /*
            Method dump skipped, instructions count: 1253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyh.e(android.content.Context, int):java.util.List");
    }

    public static List<SimpleCalendarBean> d(Context context, String str, long j, long j2) {
        LogUtil.a("CalendarBuilderUtils", "Enter getCalendarDataForSmart");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ContentResolver contentResolver = context.getContentResolver();
        LogUtil.a("CalendarBuilderUtils", "Start query Calendar events for SmartWatch");
        List<String> b2 = b(context, str, j, j2, arrayList2, arrayList3);
        if (arrayList3.size() != 0) {
            LogUtil.a("CalendarBuilderUtils", "smartWatch:query events from oppo, size is " + arrayList3.size());
            bLa_(arrayList, contentResolver, b2.get(0), new Uri[]{Uri.parse("content://com.coloros.calendar/events"), Uri.parse("content://com.coloros.calendar/calendars"), Uri.parse("content://com.coloros.calendar/reminders"), Uri.parse("content://com.coloros.calendar/calendar_alerts")});
        }
        if (arrayList2.size() != 0) {
            LogUtil.a("CalendarBuilderUtils", "smartWatch:query events from android, size is " + arrayList2.size());
            bLa_(arrayList, contentResolver, b2.get(b2.size() + (-1)), new Uri[]{CalendarContract.Events.CONTENT_URI, CalendarContract.Calendars.CONTENT_URI, CalendarContract.Reminders.CONTENT_URI, CalendarContract.CalendarAlerts.CONTENT_URI});
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void bLa_(java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean> r54, android.content.ContentResolver r55, java.lang.String r56, android.net.Uri[] r57) {
        /*
            Method dump skipped, instructions count: 956
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyh.bLa_(java.util.List, android.content.ContentResolver, java.lang.String, android.net.Uri[]):void");
    }

    private static int bKY_(Uri uri, ContentResolver contentResolver, String str) {
        try {
            int i = 0;
            Cursor query = contentResolver.query(uri, new String[]{"setLunar"}, "_id== " + str, null, null);
            if (query != null && query.moveToFirst()) {
                i = query.getInt(query.getColumnIndex("setLunar"));
                query.close();
            }
            LogUtil.b("CalendarBuilderUtils", "getLunar calendarType: ", Integer.valueOf(i));
            return i;
        } catch (Exception e2) {
            LogUtil.b("CalendarBuilderUtils", "get lunar signal catch exception", ExceptionUtils.d(e2));
            return bKZ_(uri, contentResolver, str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int bKZ_(android.net.Uri r10, android.content.ContentResolver r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "CalendarBuilderUtils"
            java.lang.String r1 = "BirthdayState"
            r2 = 0
            r3 = 1
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Exception -> L38
            r6[r2] = r1     // Catch: java.lang.Exception -> L38
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L38
            java.lang.String r5 = "_id== "
            r4.<init>(r5)     // Catch: java.lang.Exception -> L38
            r4.append(r12)     // Catch: java.lang.Exception -> L38
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Exception -> L38
            r8 = 0
            r9 = 0
            r4 = r11
            r5 = r10
            android.database.Cursor r10 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L38
            if (r10 == 0) goto L36
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Exception -> L38
            if (r11 == 0) goto L36
            int r11 = r10.getColumnIndex(r1)     // Catch: java.lang.Exception -> L38
            int r11 = r10.getInt(r11)     // Catch: java.lang.Exception -> L38
            r10.close()     // Catch: java.lang.Exception -> L34
            goto L47
        L34:
            r10 = move-exception
            goto L3a
        L36:
            r11 = r2
            goto L47
        L38:
            r10 = move-exception
            r11 = r2
        L3a:
            java.lang.String r12 = "get lunar signal catch exception"
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r12, r10}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)
        L47:
            java.lang.String r10 = "getVivoLunar calendarType: "
            java.lang.Integer r12 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r12}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)
            r10 = 2
            if (r11 != r10) goto L58
            r2 = r3
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyh.bKZ_(android.net.Uri, android.content.ContentResolver, java.lang.String):int");
    }

    private static List<String> b(Context context, String str, long j, long j2, List<String> list, List<String> list2) {
        LogUtil.a("CalendarBuilderUtils", "query event_id from instance for smartwatch");
        ArrayList arrayList = new ArrayList();
        ContentResolver contentResolver = context.getContentResolver();
        if (jyo.c(BaseApplication.getContext())) {
            Uri.Builder buildUpon = Uri.parse("content://com.coloros.calendar/instances/when").buildUpon();
            ContentUris.appendId(buildUpon, j);
            ContentUris.appendId(buildUpon, j2);
            Cursor query = context.getContentResolver().query(buildUpon.build(), b, "visible=?", new String[]{"1"}, "event_id ASC");
            LogUtil.a("CalendarBuilderUtils", "oppo exdate update success ?", Boolean.valueOf(bLb_(contentResolver, bKX_(query.getCount(), query, list2, arrayList, str + "oppoInstance"), Uri.parse("content://com.coloros.calendar/events"), str)));
        }
        Cursor query2 = CalendarContract.Instances.query(context.getContentResolver(), b, j, j2);
        int count = query2.getCount();
        LogUtil.a("CalendarBuilderUtils", "query event_id from android instance,count is: " + count);
        LogUtil.a("CalendarBuilderUtils", "oppo exdate update success ?", Boolean.valueOf(bLb_(contentResolver, bKX_(count, query2, list, arrayList, str + "Instance"), CalendarContract.Events.CONTENT_URI, str)));
        return arrayList;
    }

    private static Map<String, String> bKX_(int i, Cursor cursor, List<String> list, List<String> list2, String str) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        if (i > 0) {
            sb.append("_id IN (");
        }
        while (cursor != null && cursor.moveToNext()) {
            String string = cursor.getString(cursor.getColumnIndex(HeaderType.EVENT_ID));
            long j = cursor.getLong(cursor.getColumnIndex("begin"));
            String string2 = cursor.getString(cursor.getColumnIndex("rrule"));
            String string3 = cursor.getString(cursor.getColumnIndex("exdate"));
            int i2 = cursor.getInt(cursor.getColumnIndex("allDay"));
            long j2 = !kat.b(string2) ? 1L : 0L;
            CalendarDbBean calendarDbBean = new CalendarDbBean(string + "_" + j, 1, string, j, j2, i2, string3);
            if (j2 == 1) {
                arrayList.add(calendarDbBean);
            }
            if (!list.contains(string)) {
                list.add(string);
                sb.append(string);
                sb.append(",");
            }
        }
        if (i > 0) {
            sb.delete(sb.length() - 1, sb.length());
            sb.append(Constants.RIGHT_BRACKET_ONLY);
            list2.add(sb.toString());
        }
        if (cursor != null) {
            cursor.close();
        }
        return jym.c(str, arrayList);
    }

    private static boolean bLb_(ContentResolver contentResolver, Map<String, String> map, Uri uri, String str) {
        LogUtil.a("CalendarBuilderUtils", "Enter updateExDate");
        if (map == null || uri == null) {
            LogUtil.b("CalendarBuilderUtils", "param of updateExDate is invalid");
            return false;
        }
        if (map.size() == 0) {
            return true;
        }
        jye.e(BaseApplication.getContext());
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                long parseLong = Long.parseLong(key);
                String value = entry.getValue();
                ContentValues contentValues = new ContentValues();
                contentValues.put("exdate", value);
                int update = contentResolver.update(ContentUris.withAppendedId(uri, parseLong), contentValues, null, null);
                Object[] objArr = new Object[6];
                objArr[0] = "update exDate success?";
                objArr[1] = update > 0 ? "yes" : "no";
                objArr[2] = "eventId = ";
                objArr[3] = key;
                objArr[4] = "exDate = ";
                objArr[5] = value;
                LogUtil.a("CalendarBuilderUtils", objArr);
            }
            return true;
        } catch (Exception e2) {
            LogUtil.b("CalendarBuilderUtils", "update ExDate catch error", ExceptionUtils.d(e2));
            return false;
        } finally {
            jyk.b().b(BaseApplication.getContext());
        }
    }

    private static String b(String str, int i) {
        String b2 = HEXUtils.b(str);
        String b3 = HEXUtils.b("...");
        if (b2.length() / 2 > i) {
            String substring = b2.substring(0, (i - 6) * 2);
            String d2 = HEXUtils.d(substring);
            char[] charArray = d2.substring(d2.length() - 2).toCharArray();
            char c2 = charArray[0];
            char c3 = charArray[1];
            if (!Character.isHighSurrogate(c2) || !Character.isLowSurrogate(c3)) {
                substring = HEXUtils.b(d2.substring(0, d2.length() - 1));
                Log.i("CalendarBuilderUtils", "getCalendarContentHex discard last char");
            }
            Log.i("CalendarBuilderUtils", "getCalendarContentHex source textContentHex: " + b2);
            b2 = substring + b3;
        }
        return HEXUtils.d(b2);
    }

    private static long a(long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (z) {
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
        } else {
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 0);
            calendar.set(14, 0);
        }
        calendar.setTimeZone(TimeZone.getDefault());
        long timeInMillis = calendar.getTimeInMillis();
        LogUtil.a("CalendarBuilderUtils", "getBeginOfDate1 beginTimestamp : ", Long.valueOf(timeInMillis));
        return timeInMillis;
    }

    private static long e(String str, long j) {
        try {
            TimeZone timeZone = TimeZone.getTimeZone(TimeZone.getDefault().getID());
            TimeZone timeZone2 = TimeZone.getTimeZone(str);
            return j + (timeZone2.getOffset(j) - timeZone.getOffset(j));
        } catch (Exception e2) {
            LogUtil.a("CalendarBuilderUtils", "timeZoneConversion Exception : ", ExceptionUtils.d(e2));
            return j;
        }
    }

    public static long d(Context context, SmartSimpleCalendarBean smartSimpleCalendarBean) {
        long j;
        Cursor query = context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, d, "((account_name = ?) AND (account_type = ?))", new String[]{smartSimpleCalendarBean.getAccountName(), smartSimpleCalendarBean.getAccountType()}, null);
        if (query == null || !query.moveToFirst()) {
            j = 0;
        } else {
            j = query.getLong(query.getColumnIndex("_id"));
            query.close();
        }
        if (j != 0) {
            return j;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_name", smartSimpleCalendarBean.getAccountName());
        contentValues.put("account_type", smartSimpleCalendarBean.getAccountType());
        contentValues.put("calendar_displayName", smartSimpleCalendarBean.getCalendarDisplayName());
        contentValues.put("calendar_color", Integer.valueOf(smartSimpleCalendarBean.getCalendarColor()));
        contentValues.put("calendar_access_level", (Integer) 700);
        contentValues.put("sync_events", (Integer) 1);
        contentValues.put("ownerAccount", smartSimpleCalendarBean.getAccountName());
        Uri insert = context.getContentResolver().insert(Uri.parse(String.valueOf(CalendarContract.Calendars.CONTENT_URI)).buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", smartSimpleCalendarBean.getAccountName()).appendQueryParameter("account_type", smartSimpleCalendarBean.getAccountType()).build(), contentValues);
        return insert == null ? -1L : ContentUris.parseId(insert);
    }

    public static boolean b(Context context, String str, int i, String str2) {
        boolean z;
        LogUtil.a("CalendarBuilderUtils", "Enter inSertReminder");
        if (context == null) {
            return false;
        }
        if (kat.b(str) || kat.b(str2)) {
            LogUtil.h("CalendarBuilderUtils", "not need to insert reminder");
            return false;
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            String[] split = str.split(",");
            int length = split.length;
            int[] iArr = new int[length];
            for (int i2 = 0; i2 < split.length; i2++) {
                iArr[i2] = Integer.parseInt(split[i2].trim());
            }
            long parseLong = Long.parseLong(str2);
            int i3 = 0;
            z = false;
            while (i3 < length) {
                try {
                    try {
                        int i4 = iArr[i3];
                        contentValues.put(HeaderType.EVENT_ID, Long.valueOf(parseLong));
                        contentValues.put("minutes", Integer.valueOf(i4));
                        contentValues.put("method", Integer.valueOf(i));
                        Uri insert = contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, contentValues);
                        if (insert == null) {
                            LogUtil.b("CalendarBuilderUtils", "insertReminder to URi failed");
                            return z;
                        }
                        LogUtil.a("CalendarBuilderUtils", "insertReminder result reminderId is" + Long.parseLong(insert.getLastPathSegment()));
                        i3++;
                        z = true;
                    } catch (Exception e2) {
                        e = e2;
                        LogUtil.b("CalendarBuilderUtils", "insertReminder fail", ExceptionUtils.d(e));
                        return z;
                    }
                } catch (Throwable unused) {
                    return z;
                }
            }
            return z;
        } catch (Exception e3) {
            e = e3;
            z = false;
        } catch (Throwable unused2) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x007f A[Catch: Exception -> 0x00f2, TRY_LEAVE, TryCatch #4 {Exception -> 0x00f2, blocks: (B:19:0x0046, B:25:0x004e, B:26:0x0077, B:28:0x007f, B:72:0x0064, B:74:0x0070), top: B:14:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a2 A[Catch: Exception -> 0x00f0, TRY_LEAVE, TryCatch #0 {Exception -> 0x00f0, blocks: (B:30:0x0087, B:34:0x0093, B:35:0x009a, B:37:0x00a2), top: B:29:0x0087 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c3 A[Catch: Exception -> 0x00ed, TRY_LEAVE, TryCatch #1 {Exception -> 0x00ed, blocks: (B:40:0x00aa, B:43:0x00b4, B:44:0x00bb, B:46:0x00c3), top: B:39:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e2 A[Catch: Exception -> 0x00eb, TRY_LEAVE, TryCatch #2 {Exception -> 0x00eb, blocks: (B:49:0x00cb, B:53:0x00d3, B:54:0x00da, B:56:0x00e2), top: B:48:0x00cb }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long c(java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyh.c(java.lang.String):long");
    }

    private static String c() {
        return "_id=?";
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }
}
