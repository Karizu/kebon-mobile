// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.akun;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailAkunActivity_ViewBinding implements Unbinder {
  private DetailAkunActivity target;

  private View view7f0a0151;

  private View view7f0a0153;

  private View view7f0a0150;

  private View view7f0a014e;

  private View view7f0a014f;

  private View view7f0a006b;

  @UiThread
  public DetailAkunActivity_ViewBinding(DetailAkunActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailAkunActivity_ViewBinding(final DetailAkunActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.layout_name, "method 'onClickEditName'");
    view7f0a0151 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditName();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_phone, "method 'onClickEditPhone'");
    view7f0a0153 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditPhone();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_email, "method 'onClickEditEmail'");
    view7f0a0150 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditEmail();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_address, "method 'onClickEditAddress'");
    view7f0a014e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditAddress();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_change_pass, "method 'onClickEditPass'");
    view7f0a014f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditPass();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'onClickBack'");
    view7f0a006b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBack();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a0151.setOnClickListener(null);
    view7f0a0151 = null;
    view7f0a0153.setOnClickListener(null);
    view7f0a0153 = null;
    view7f0a0150.setOnClickListener(null);
    view7f0a0150 = null;
    view7f0a014e.setOnClickListener(null);
    view7f0a014e = null;
    view7f0a014f.setOnClickListener(null);
    view7f0a014f = null;
    view7f0a006b.setOnClickListener(null);
    view7f0a006b = null;
  }
}
