package com.huawei.featureuserprofile.todo.datatype;

/* loaded from: classes3.dex */
public class TodoCheckEntity {
    private boolean isChecked;
    private String key;
    private String title;

    public TodoCheckEntity(String str, String str2, boolean z) {
        this.title = str;
        this.isChecked = z;
        this.key = str2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }
}
