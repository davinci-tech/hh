package com.huawei.hmf.services.interception;

/* loaded from: classes4.dex */
public class Signature {
    public static final String ConstructorMethod = "__constructor__";
    private String mAlias;
    private Class mDeclaringType;
    private String mDeclaringTypeName;
    private String mName;

    public Signature(Class cls) {
        this.mDeclaringType = cls;
    }

    public Class getDeclaringType() {
        return this.mDeclaringType;
    }

    public void setDeclaringTypeName(String str) {
        this.mDeclaringTypeName = str;
    }

    public String getDeclaringTypeName() {
        Class cls;
        String str = this.mDeclaringTypeName;
        return (str != null || (cls = this.mDeclaringType) == null) ? str : cls.getName();
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public void setAlias(String str) {
        this.mAlias = str;
    }

    public String toString() {
        return "Signature{DeclaringTypeName='" + getDeclaringTypeName() + "', Alias='" + this.mAlias + "', Name='" + this.mName + "'}";
    }
}
