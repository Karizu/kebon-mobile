// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.auth;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Register3Activity_ViewBinding implements Unbinder {
  private Register3Activity target;

  private View view7f0a007e;

  @UiThread
  public Register3Activity_ViewBinding(Register3Activity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Register3Activity_ViewBinding(final Register3Activity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_lanjutkan, "method 'onClickLnajutkan'");
    view7f0a007e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickLnajutkan();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a007e.setOnClickListener(null);
    view7f0a007e = null;
  }
}
