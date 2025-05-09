package com.huawei.agconnect.apms.anr.model;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.m0;

/* loaded from: classes8.dex */
public class ThreadStackInfo extends CollectableArray {
    private int priority;
    private String stackTrace;
    private String state;
    private String threadName;

    public static class Builder {
        private static final String LINE_SEPARATOR = System.lineSeparator();
        private String name;
        private int priority;
        private String stack;
        private String state;

        public Builder(Thread thread) {
            this.stack = "";
            this.name = thread.getName() + " tid=" + thread.getId();
            this.priority = thread.getPriority();
            this.state = thread.getState().toString();
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb.append("\tat ");
                    sb.append(stackTraceElement.toString());
                    sb.append(LINE_SEPARATOR);
                }
                this.stack = sb.toString();
            }
        }

        public ThreadStackInfo build() {
            return new ThreadStackInfo(this.name, this.stack, this.priority, this.state);
        }
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(this.threadName));
        jsonArray.add(m0.abc(Integer.valueOf(this.priority)));
        jsonArray.add(m0.abc(this.state));
        jsonArray.add(m0.abc(this.stackTrace));
        return jsonArray;
    }

    private ThreadStackInfo(String str, String str2, int i, String str3) {
        this.threadName = str;
        this.stackTrace = str2;
        this.priority = i;
        this.state = str3;
    }
}
