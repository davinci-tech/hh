package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.hihealth.data.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class DataTypeAddOptions extends aabq {
    public static final Parcelable.Creator<DataTypeAddOptions> CREATOR = new aabq.aab(DataTypeAddOptions.class);

    @aaby(id = 1)
    private final String aab;

    @aaby(id = 2)
    private final List<Field> aaba;

    public static class Builder {
        private String aab;
        private List<Field> aaba = new ArrayList();

        public Builder addField(String str, int i) {
            Preconditions.checkArgument((str == null || str.isEmpty()) ? false : true, "Invalid field name");
            Preconditions.checkArgument(i == 1 || i == 2 || i == 3 || i == 4, "Invalid field format");
            return addField(i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : Field.newMapField(str) : Field.newStringField(str) : Field.newDoubleField(str) : Field.newIntField(str));
        }

        public Builder setName(String str) {
            this.aab = str;
            return this;
        }

        public DataTypeAddOptions build() {
            Preconditions.checkState(this.aab != null, "The name of the new data type should not be null");
            Preconditions.checkState(!this.aaba.isEmpty(), "The data fields of the new data type should not be empty");
            return new DataTypeAddOptions(this);
        }

        public Builder addField(Field field) {
            if (!this.aaba.contains(field)) {
                this.aaba.add(field);
            }
            return this;
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.aab).add("fields", this.aaba).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.aab, this.aaba);
    }

    public String getName() {
        return this.aab;
    }

    public List<Field> getFields() {
        return this.aaba;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataTypeAddOptions)) {
            return false;
        }
        DataTypeAddOptions dataTypeAddOptions = (DataTypeAddOptions) obj;
        return Objects.equal(this.aab, dataTypeAddOptions.aab) && Objects.equal(this.aaba, dataTypeAddOptions.aaba);
    }

    @aabw
    private DataTypeAddOptions(@aabv(id = 1) String str, @aabv(id = 2) List<Field> list) {
        this.aab = str;
        this.aaba = Collections.unmodifiableList(list);
    }
}
