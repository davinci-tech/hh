package com.huawei.openalliance.ad.beans;

import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.openalliance.ad.annotations.d;

/* loaded from: classes5.dex */
public class AgendaBean {

    @d(a = "allday")
    private int allDay;
    private String description;

    @d(a = "dtend")
    private long dtEnd;

    @d(a = "dtstart")
    private long dtStart;
    private String location;
    private Integer minutes;

    @d(a = DBSessionCommon.COLUMN_TIME_ZONE)
    private String timeZone;
    private String title;

    public String h() {
        return this.description;
    }

    public Integer g() {
        return this.minutes;
    }

    public String f() {
        return this.timeZone;
    }

    public int e() {
        return this.allDay;
    }

    public long d() {
        return this.dtEnd;
    }

    public long c() {
        return this.dtStart;
    }

    public String b() {
        return this.location;
    }

    public void a(int i) {
        this.allDay = i;
    }

    public String a() {
        return this.title;
    }
}
