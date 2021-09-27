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

  private View view7f0a013b;

  private View view7f0a013d;

  private View view7f0a013a;

  private View view7f0a0138;

  private View view7f0a0139;

  private View view7f0a0069;

  @UiThread
  public DetailAkunActivity_ViewBinding(DetailAkunActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailAkunActivity_ViewBinding(final DetailAkunActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.layout_name, "method 'onClickEditName'");
    view7f0a013b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditName();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_phone, "method 'onClickEditPhone'");
    view7f0a013d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditPhone();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_email, "method 'onClickEditEmail'");
    view7f0a013a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditEmail();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_address, "method 'onClickEditAddress'");
    view7f0a0138 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditAddress();
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_change_pass, "method 'onClickEditPass'");
    view7f0a0139 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickEditPass();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'onClickBack'");
    view7f0a0069 = view;
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


    view7f0a013b.setOnClickListener(null);
    view7f0a013b = null;
    view7f0a013d.setOnClickListener(null);
    view7f0a013d = null;
    view7f0a013a.setOnClickListener(null);
    view7f0a013a = null;
    view7f0a0138.setOnClickListener(null);
    view7f0a0138 = null;
    view7f0a0139.setOnClickListener(null);
    view7f0a0139 = null;
    view7f0a0069.setOnClickListener(null);
    view7f0a0069 = null;
  }
}
