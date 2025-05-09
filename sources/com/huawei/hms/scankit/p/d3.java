package com.huawei.hms.scankit.p;

import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
final class d3 {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f5761a = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})T(\\d{2})(\\d{2})(\\d{2})Z");
    private static final Pattern b = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})T(\\d{2})(\\d{2})(\\d{2})");
    private static final Pattern c = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");
    private static final Pattern d = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})\\d{6}Z");

    static void a(String str, HmsScan.EventTime eventTime) {
        Matcher matcher = f5761a.matcher(str);
        Matcher matcher2 = b.matcher(str);
        Matcher matcher3 = c.matcher(str);
        Matcher matcher4 = d.matcher(str);
        try {
            if (matcher.matches()) {
                a(eventTime, Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
                b(eventTime, Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
                eventTime.isUTCTime = true;
                eventTime.originalValue = str;
            } else if (matcher2.matches()) {
                a(eventTime, Integer.parseInt(matcher2.group(1)), Integer.parseInt(matcher2.group(2)), Integer.parseInt(matcher2.group(3)));
                b(eventTime, Integer.parseInt(matcher2.group(4)), Integer.parseInt(matcher2.group(5)), Integer.parseInt(matcher2.group(6)));
                eventTime.originalValue = str;
            } else if (matcher3.matches()) {
                a(eventTime, Integer.parseInt(matcher3.group(1)), Integer.parseInt(matcher3.group(2)), Integer.parseInt(matcher3.group(3)));
                eventTime.originalValue = str;
            } else if (matcher4.matches()) {
                a(eventTime, Integer.parseInt(matcher4.group(1)), Integer.parseInt(matcher4.group(2)), Integer.parseInt(matcher4.group(3)));
            }
        } catch (NullPointerException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "NullPointerException");
        }
    }

    private static void b(HmsScan.EventTime eventTime, int i, int i2, int i3) {
        eventTime.hours = i;
        eventTime.minutes = i2;
        eventTime.seconds = i3;
    }

    private static void a(HmsScan.EventTime eventTime, int i, int i2, int i3) {
        eventTime.year = i;
        eventTime.month = i2;
        eventTime.day = i3;
    }
}
