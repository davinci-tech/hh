package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.download.app.AppStatus;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class TextState implements Serializable {
    private static final long serialVersionUID = 30420300;
    private int defaultTextFlag;
    private String language;
    private int showPosition;
    private int state;
    private String text;

    public int e() {
        return this.defaultTextFlag;
    }

    public String d() {
        return this.text;
    }

    public void c(int i) {
        this.defaultTextFlag = i;
    }

    public String c() {
        return this.language;
    }

    public void b(String str) {
        this.text = str;
    }

    public void b(int i) {
        this.state = i;
    }

    public int b() {
        return this.state;
    }

    public void a(String str) {
        this.language = str;
    }

    public void a(int i) {
        this.showPosition = i;
    }

    public int a() {
        return this.showPosition;
    }

    /* renamed from: com.huawei.openalliance.ad.beans.metadata.TextState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus;

        static {
            int[] iArr = new int[AppStatus.values().length];
            $SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus = iArr;
            try {
                iArr[AppStatus.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus[AppStatus.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus[AppStatus.DOWNLOADING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus[AppStatus.INSTALLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus[AppStatus.INSTALL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus[AppStatus.INSTALLING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static int a(AppStatus appStatus) {
        switch (AnonymousClass1.$SwitchMap$com$huawei$openalliance$ad$download$app$AppStatus[appStatus.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 2;
            case 4:
                return 6;
            case 5:
                return 4;
            case 6:
                return 5;
            default:
                return -1;
        }
    }
}
