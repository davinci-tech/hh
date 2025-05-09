package com.huawei.phoneservice.feedback.entity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class d implements Serializable {
    private static final long serialVersionUID = -3865037260659453289L;

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8257a;
    private ImageView b;
    private TextView c;
    private String d;
    private View e;
    private String h;
    private int j;

    public View cdS_() {
        return this.e;
    }

    public TextView cdR_() {
        return this.c;
    }

    public String c() {
        return this.h;
    }

    public String e() {
        return this.d;
    }

    public int d() {
        return this.j;
    }

    public void d(String str) {
        this.h = str;
    }

    public void cdQ_(ImageView imageView) {
        this.b = imageView;
    }

    public ImageView b() {
        return this.b;
    }

    public void c(String str) {
        this.d = str;
    }

    public void cdP_(TextView textView) {
        this.c = textView;
    }

    public void a(ImageView imageView) {
        this.f8257a = imageView;
    }

    public void cdO_(View view) {
        this.e = view;
    }

    public void d(int i) {
        this.j = i;
    }

    public ImageView a() {
        return this.f8257a;
    }
}
