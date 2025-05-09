package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarDbBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SimpleCalendarBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SmartSimpleCalendarBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.sensor.DataResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class jym {
    public static List<SimpleCalendarBean> d(String str, List<SimpleCalendarBean> list, boolean z) {
        List<SimpleCalendarBean> b = b(str, list, z);
        b.addAll(a(str, list, z));
        LogUtil.a("CalendarCompareUtils", "calendarCompare: ", Integer.valueOf(b.size()));
        return b;
    }

    public static Map<String, String> c(String str, List<CalendarDbBean> list) {
        LogUtil.a("CalendarCompareUtils", "Enter exDateCompare");
        HashMap hashMap = new HashMap();
        if (kat.b(str) || list == null) {
            LogUtil.b("CalendarCompareUtils", "invalid params in exDateCompare");
            return hashMap;
        }
        List<CalendarDbBean> d = jyi.d(str);
        List<String> d2 = d(d);
        List<String> d3 = d(list);
        HashMap hashMap2 = new HashMap(d.size());
        for (CalendarDbBean calendarDbBean : d) {
            hashMap2.put(calendarDbBean.getEventUuid(), calendarDbBean);
        }
        if (d2.removeAll(d3)) {
            LogUtil.a("CalendarCompareUtils", "calculate: deletedRidList or updateContactRidList is empty.");
        }
        jyf d4 = jyf.d(BaseApplication.getContext());
        for (String str2 : d2) {
            d4.c(str2, str);
            CalendarDbBean calendarDbBean2 = (CalendarDbBean) hashMap2.get(str2);
            if (calendarDbBean2 != null) {
                long dtstart = calendarDbBean2.getDtstart();
                long dtend = calendarDbBean2.getDtend();
                int allDay = calendarDbBean2.getAllDay();
                if (dtend != 0) {
                    String calendarFeature = calendarDbBean2.getCalendarFeature();
                    String e = e(dtstart, allDay);
                    LogUtil.a("CalendarCompareUtils", "convert timeStamp to exDate: ", e);
                    String eventId = calendarDbBean2.getEventId();
                    String str3 = "," + e;
                    if (hashMap.get(eventId) == null) {
                        hashMap.put(eventId, kat.b(calendarFeature) ? str3.substring(1) : calendarFeature + str3);
                    } else {
                        hashMap.put(eventId, ((String) hashMap.get(eventId)) + str3);
                    }
                }
            }
        }
        d4.e(c(list, hashMap), str);
        return hashMap;
    }

    private static String e(long j, int i) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        String format = simpleDateFormat.format(date);
        if (i == 1) {
            format.replace(format.substring(9, 15), "000000");
        }
        return format;
    }

    private static List<CalendarDbBean> c(List<CalendarDbBean> list, Map<String, String> map) {
        LogUtil.a("Enter updateInstanceExDate", new Object[0]);
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            for (CalendarDbBean calendarDbBean : list) {
                String str = map.get(calendarDbBean.getEventId());
                if (!kat.b(str)) {
                    calendarDbBean.setCalendarFeature(str);
                }
                arrayList.add(calendarDbBean);
            }
        }
        return arrayList;
    }

    private static List<SimpleCalendarBean> b(String str, List<SimpleCalendarBean> list, boolean z) {
        SimpleCalendarBean simpleCalendarBean;
        ArrayList arrayList = new ArrayList();
        List<CalendarDbBean> d = jyi.d(str);
        List<String> d2 = d(d);
        List<String> b = b(list, z);
        HashMap hashMap = new HashMap(d.size());
        for (CalendarDbBean calendarDbBean : d) {
            hashMap.put(calendarDbBean.getEventUuid(), calendarDbBean);
        }
        if (d2.removeAll(b)) {
            LogUtil.a("CalendarCompareUtils", "calculate: deletedRidList or updateContactRidList is empty.");
        }
        Iterator<String> it = d2.iterator();
        while (it.hasNext()) {
            CalendarDbBean calendarDbBean2 = (CalendarDbBean) hashMap.get(it.next());
            if (calendarDbBean2 != null) {
                if (z) {
                    simpleCalendarBean = new SmartSimpleCalendarBean();
                    simpleCalendarBean.setEventUuid(calendarDbBean2.getEventId());
                } else {
                    simpleCalendarBean = new SimpleCalendarBean();
                    simpleCalendarBean.setEventUuid(calendarDbBean2.getEventUuid());
                }
                simpleCalendarBean.setEventId(calendarDbBean2.getEventId());
                simpleCalendarBean.setOperation(2);
                arrayList.add(simpleCalendarBean);
            }
        }
        LogUtil.a("CalendarCompareUtils", "getDeletedCalendarList size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static List<SimpleCalendarBean> a(String str, List<SimpleCalendarBean> list, boolean z) {
        SmartSimpleCalendarBean smartSimpleCalendarBean;
        ArrayList arrayList = new ArrayList();
        List<CalendarDbBean> d = jyi.d(str);
        List<String> d2 = d(d);
        List<String> b = b(list, z);
        HashMap hashMap = new HashMap(list.size());
        for (SimpleCalendarBean simpleCalendarBean : list) {
            hashMap.put(simpleCalendarBean.getEventUuid(), simpleCalendarBean);
        }
        if (b.removeAll(d2)) {
            LogUtil.a("CalendarCompareUtils", "calculate: deletedRidList or updateContactRidList is empty.");
        }
        if (z) {
            for (String str2 : b) {
                if ((hashMap.get(str2) instanceof SmartSimpleCalendarBean) && (smartSimpleCalendarBean = (SmartSimpleCalendarBean) hashMap.get(str2)) != null) {
                    smartSimpleCalendarBean.setOperation(1);
                    arrayList.add(smartSimpleCalendarBean);
                }
            }
        } else {
            Iterator<String> it = b.iterator();
            while (it.hasNext()) {
                SimpleCalendarBean simpleCalendarBean2 = (SimpleCalendarBean) hashMap.get(it.next());
                if (simpleCalendarBean2 != null) {
                    simpleCalendarBean2.setOperation(1);
                    arrayList.add(simpleCalendarBean2);
                }
            }
        }
        LogUtil.a("CalendarCompareUtils", "getUpdatedCalendarList size: ", Integer.valueOf(arrayList.size()));
        a(d, list, arrayList, hashMap);
        return arrayList;
    }

    private static void a(List<CalendarDbBean> list, List<SimpleCalendarBean> list2, List<SimpleCalendarBean> list3, Map<String, SimpleCalendarBean> map) {
        HashMap hashMap = new HashMap(list2.size());
        for (CalendarDbBean calendarDbBean : list) {
            hashMap.put(calendarDbBean.getEventUuid(), calendarDbBean);
        }
        for (CalendarDbBean calendarDbBean2 : b(list2)) {
            CalendarDbBean calendarDbBean3 = (CalendarDbBean) hashMap.get(calendarDbBean2.getEventUuid());
            if (calendarDbBean3 == null) {
                LogUtil.a("CalendarCompareUtils", "calculateUpdatedContacts: cachedSimpleContactBean is null.");
            } else if (!calendarDbBean2.getCalendarFeature().equals(calendarDbBean3.getCalendarFeature())) {
                SimpleCalendarBean simpleCalendarBean = map.get(calendarDbBean2.getEventUuid());
                if (simpleCalendarBean != null) {
                    simpleCalendarBean.setOperation(3);
                    list3.add(simpleCalendarBean);
                }
            } else {
                LogUtil.a("CalendarCompareUtils", "calculateUpdatedContacts: the rawContactFeature is same to cache's rawContactFeature ");
            }
        }
        LogUtil.a("CalendarCompareUtils", "getModifyCalendarList size: ", Integer.valueOf(list3.size()));
    }

    private static List<String> d(List<CalendarDbBean> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("CalendarCompareUtils", "getCachedUidList: cache list is null or empty.");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<CalendarDbBean> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getEventUuid());
        }
        return arrayList;
    }

    private static List<String> b(List<SimpleCalendarBean> list, boolean z) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("CalendarCompareUtils", "getCachedUidList: cache list is null or empty.");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (SimpleCalendarBean simpleCalendarBean : list) {
            if (z) {
                arrayList.add(((SmartSimpleCalendarBean) simpleCalendarBean).getEventId());
            } else {
                arrayList.add(simpleCalendarBean.getEventUuid());
            }
        }
        return arrayList;
    }

    private static List<CalendarDbBean> b(List<SimpleCalendarBean> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (SimpleCalendarBean simpleCalendarBean : list) {
            if (simpleCalendarBean instanceof SmartSimpleCalendarBean) {
                SmartSimpleCalendarBean smartSimpleCalendarBean = (SmartSimpleCalendarBean) simpleCalendarBean;
                arrayList.add(new CalendarDbBean(smartSimpleCalendarBean.getEventId(), smartSimpleCalendarBean.getOperation(), smartSimpleCalendarBean.getEventId(), smartSimpleCalendarBean.getDtstart(), smartSimpleCalendarBean.getDtend(), smartSimpleCalendarBean.getAllDay(), jyh.d(smartSimpleCalendarBean)));
            } else {
                arrayList.add(new CalendarDbBean(simpleCalendarBean.getEventUuid(), simpleCalendarBean.getOperation(), simpleCalendarBean.getEventId(), simpleCalendarBean.getDtstart(), simpleCalendarBean.getDtend(), simpleCalendarBean.getAllDay(), jyh.a(simpleCalendarBean)));
            }
        }
        return arrayList;
    }
}
