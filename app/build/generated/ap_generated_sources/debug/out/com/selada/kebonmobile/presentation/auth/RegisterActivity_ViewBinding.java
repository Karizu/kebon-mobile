// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.auth;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view7f0a0087;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.editTextName = Utils.findRequiredViewAsType(source, R.id.editTextName, "field 'editTextName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_lanjutkan, "method 'onClickLnajutkan'");
    view7f0a0087 = view;
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
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextName = null;

    view7f0a0087.setOnClickListener(null);
    view7f0a0087 = null;
  }
}
