package com.huawei.health.plan.model.util;

import health.compact.a.utils.StringUtils;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SqlUtil {

    public static class b {
        private String b;
        private boolean c;
        private LinkedHashMap<String, SqliteColumnType> e = new LinkedHashMap<>();

        public b a(String str) {
            this.b = str;
            return this;
        }

        public b e() {
            this.c = true;
            return this;
        }

        public b e(String str, SqliteColumnType sqliteColumnType) {
            if (StringUtils.i(str) && sqliteColumnType != null) {
                this.e.put(str, sqliteColumnType);
            }
            return this;
        }

        public String b() {
            StringBuilder sb = new StringBuilder();
            String str = this.b;
            if (str != null) {
                if (this.c) {
                    sb.append(str);
                    sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT");
                } else {
                    sb.append(str);
                    sb.append(" INTEGER AUTOINCREMENT");
                }
            }
            if (!this.e.isEmpty()) {
                for (Map.Entry<String, SqliteColumnType> entry : this.e.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append(entry.getKey());
                    sb.append(" ");
                    sb.append(entry.getValue().getValue());
                }
            }
            return sb.toString();
        }
    }

    public enum SqliteColumnType {
        INTEGER("INTEGER"),
        TEXT("TEXT"),
        BLOB("BLOB"),
        REAL("REAL");

        private String mValue;

        SqliteColumnType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    /* loaded from: classes8.dex */
    public enum SqliteOrderType {
        ASC("ASC"),
        DESC("DESC");

        private String mValue;

        SqliteOrderType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }

        public String by(String... strArr) {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                if (!StringUtils.g(str)) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    } else {
                        sb.append(" order by");
                        sb.append(" ");
                    }
                    sb.append(str);
                    sb.append(" ");
                    sb.append(name());
                }
            }
            return sb.toString();
        }
    }

    /* loaded from: classes8.dex */
    public enum SqliteOperateType {
        SELECT("select");

        private String mValue;

        SqliteOperateType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    /* loaded from: classes8.dex */
    public enum ConditionLinkerType {
        AND(" and "),
        OR(" or ");

        private String mValue;

        ConditionLinkerType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }
}
