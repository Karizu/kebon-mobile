// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.auth;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f0a007e;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.editTextPass = Utils.findRequiredViewAsType(source, R.id.editTextPass, "field 'editTextPass'", EditText.class);
    target.editTextKonfirmPass = Utils.findRequiredViewAsType(source, R.id.editTextKonfirmPass, "field 'editTextKonfirmPass'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_lanjutkan, "method 'onClickBtnLanjutkan'");
    view7f0a007e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickBtnLanjutkan();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextPass = null;
    target.editTextKonfirmPass = null;

    view7f0a007e.setOnClickListener(null);
    view7f0a007e = null;
  }
}
