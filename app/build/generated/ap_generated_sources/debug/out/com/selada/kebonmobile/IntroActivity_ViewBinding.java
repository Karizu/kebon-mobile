// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IntroActivity_ViewBinding implements Unbinder {
  private IntroActivity target;

  private View view7f0a008d;

  private View view7f0a006b;

  private View view7f0a007a;

  private View view7f0a0232;

  @UiThread
  public IntroActivity_ViewBinding(IntroActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public IntroActivity_ViewBinding(final IntroActivity target, View source) {
    this.target = target;

    View view;
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPager.class);
    target.dotsLayout = Utils.findRequiredViewAsType(source, R.id.layoutDots, "field 'dotsLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_next, "field 'btn_next' and method 'onClickBtnNext'");
    target.btn_next = Utils.castView(view, R.id.btn_next, "field 'btn_next'", ImageView.class);
    view7f0a008d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnNext();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_back, "field 'btn_back' and method 'onClickBtnBack'");
    target.btn_back = Utils.castView(view, R.id.btn_back, "field 'btn_back'", ImageView.class);
    view7f0a006b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnBack();
      }
    });
    target.img_dots_1 = Utils.findRequiredViewAsType(source, R.id.img_dots_1, "field 'img_dots_1'", ImageView.class);
    target.img_dots_2 = Utils.findRequiredViewAsType(source, R.id.img_dots_2, "field 'img_dots_2'", ImageView.class);
    target.img_dots_3 = Utils.findRequiredViewAsType(source, R.id.img_dots_3, "field 'img_dots_3'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_gabung, "method 'onClickGabung'");
    view7f0a007a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickGabung();
      }
    });
    view = Utils.findRequiredView(source, R.id.textView, "method 'onClickLogin'");
    view7f0a0232 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickLogin();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    IntroActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
    target.dotsLayout = null;
    target.btn_next = null;
    target.btn_back = null;
    target.img_dots_1 = null;
    target.img_dots_2 = null;
    target.img_dots_3 = null;

    view7f0a008d.setOnClickListener(null);
    view7f0a008d = null;
    view7f0a006b.setOnClickListener(null);
    view7f0a006b = null;
    view7f0a007a.setOnClickListener(null);
    view7f0a007a = null;
    view7f0a0232.setOnClickListener(null);
    view7f0a0232 = null;
  }
}
