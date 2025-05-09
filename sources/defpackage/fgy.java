package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class fgy {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("fileSize")
    private int f12502a;

    @SerializedName("description")
    private String b;

    @SerializedName("jsonUrl")
    private String c;

    @SerializedName("langId")
    private String d;

    @SerializedName("langName")
    private String e;

    @SerializedName("version")
    private String f;

    @SerializedName("language")
    private String g;

    @SerializedName("langUrl")
    private String h;

    @SerializedName("moduleName")
    private String i;

    @SerializedName("timbre")
    private String j;

    public int d() {
        return this.f12502a;
    }

    public String b() {
        return this.i;
    }

    public String c() {
        return this.c;
    }

    public String a() {
        return this.h;
    }

    public String g() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.j;
    }

    public String toString() {
        return "VoiceBroadcastInfo{mLangName='" + this.e + "', mDescription='" + this.b + "', mLangId='" + this.d + "', mVersion='" + this.f + "', mLanguage='" + this.g + "', mTimbre='" + this.j + "', mModuleName='" + this.i + "', mJsonUrl='" + this.c + "', mFileSize=" + this.f12502a + '}';
    }
}
