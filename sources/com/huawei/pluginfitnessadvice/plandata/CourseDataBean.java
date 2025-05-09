package com.huawei.pluginfitnessadvice.plandata;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import defpackage.mnt;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class CourseDataBean implements Serializable, Cloneable {
    private static final long serialVersionUID = 774731271769351368L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("courseId")
    private String f8478a;

    @SerializedName("courseInfo")
    private mnt b;

    @SerializedName("clockFlag")
    private int c;

    @SerializedName(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE)
    private int e;

    public mnt e() {
        return this.b;
    }

    public int c() {
        return this.e;
    }

    public String a() {
        return this.f8478a;
    }

    public void e(boolean z) {
        if (z) {
            this.c = 1;
        } else {
            this.c = 0;
        }
    }

    public boolean d() {
        return this.c == 1;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public enum PlanCourseType {
        RACE_DAY(0),
        RUN_COURSE(1),
        STRENGHT_COURSE(2);

        int mValue;

        PlanCourseType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }
}
