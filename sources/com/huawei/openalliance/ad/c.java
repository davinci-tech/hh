package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import java.util.List;

/* loaded from: classes9.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    LinearLayout f6670a;
    private com.huawei.hms.ads.a b;
    private Context c;

    public void a(List<d> list, boolean z) {
        int color;
        int i;
        this.f6670a.removeAllViews();
        Resources resources = this.c.getResources();
        for (int i2 = 0; i2 < list.size(); i2++) {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.c).inflate(R.layout.hiad_choices_item, (ViewGroup) null);
            TextView textView = (TextView) relativeLayout.findViewById(R.id.scroll_view_text_view);
            if (!z) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                textView.setLayoutParams(layoutParams);
            }
            textView.setText(list.get(i2).a());
            textView.setTag(list.get(i2).b());
            if (list.get(i2).b() == b.HIDE_AD) {
                textView.setBackgroundResource(R.drawable._2131428515_res_0x7f0b04a3);
                i = R.color._2131298012_res_0x7f0906dc;
            } else if (list.get(i2).b() == b.WHY_THIS_AD) {
                textView.setBackgroundResource(R.drawable._2131428516_res_0x7f0b04a4);
                i = R.color._2131298013_res_0x7f0906dd;
            } else {
                if (list.get(i2).b() == b.CLOSE_AD) {
                    textView.setBackgroundResource(R.drawable._2131428516_res_0x7f0b04a4);
                } else if (list.get(i2).b() == b.NOT_INTEREST) {
                    textView.setBackgroundResource(R.drawable._2131428517_res_0x7f0b04a5);
                } else {
                    textView.setTextColor(resources.getColor(R.color._2131298011_res_0x7f0906db));
                    ho.a("ScrollAdapter", "sdk do-nothing settings.");
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.c.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (view.getTag() == b.HIDE_AD) {
                                ho.a("ScrollAdapter", "sdk tag is HIDE_AD");
                                c.this.b.e();
                            } else if (view.getTag() == b.WHY_THIS_AD) {
                                c.this.b.c();
                            } else if (view.getTag() == b.CLOSE_AD) {
                                c.this.b.a(((TextView) view).getText().toString());
                            } else if (view.getTag() == b.NOT_INTEREST) {
                                c.this.b.a("");
                            } else {
                                ho.a("ScrollAdapter", "sdk onclick do-nothing");
                            }
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    this.f6670a.addView(relativeLayout);
                }
                color = resources.getColor(R.color._2131298011_res_0x7f0906db);
                textView.setTextColor(color);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.c.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (view.getTag() == b.HIDE_AD) {
                            ho.a("ScrollAdapter", "sdk tag is HIDE_AD");
                            c.this.b.e();
                        } else if (view.getTag() == b.WHY_THIS_AD) {
                            c.this.b.c();
                        } else if (view.getTag() == b.CLOSE_AD) {
                            c.this.b.a(((TextView) view).getText().toString());
                        } else if (view.getTag() == b.NOT_INTEREST) {
                            c.this.b.a("");
                        } else {
                            ho.a("ScrollAdapter", "sdk onclick do-nothing");
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                this.f6670a.addView(relativeLayout);
            }
            color = resources.getColor(i);
            textView.setTextColor(color);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.c.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (view.getTag() == b.HIDE_AD) {
                        ho.a("ScrollAdapter", "sdk tag is HIDE_AD");
                        c.this.b.e();
                    } else if (view.getTag() == b.WHY_THIS_AD) {
                        c.this.b.c();
                    } else if (view.getTag() == b.CLOSE_AD) {
                        c.this.b.a(((TextView) view).getText().toString());
                    } else if (view.getTag() == b.NOT_INTEREST) {
                        c.this.b.a("");
                    } else {
                        ho.a("ScrollAdapter", "sdk onclick do-nothing");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.f6670a.addView(relativeLayout);
        }
    }

    public c(Context context, com.huawei.hms.ads.a aVar, LinearLayout linearLayout) {
        this.c = context;
        this.b = aVar;
        this.f6670a = linearLayout;
    }
}
