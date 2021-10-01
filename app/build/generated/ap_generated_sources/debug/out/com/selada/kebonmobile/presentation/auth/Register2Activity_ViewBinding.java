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

public class Register2Activity_ViewBinding implements Unbinder {
  private Register2Activity target;

  private View view7f0a0087;

  @UiThread
  public Register2Activity_ViewBinding(Register2Activity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Register2Activity_ViewBinding(final Register2Activity target, View source) {
    this.target = target;

    View view;
    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.editTextEmail, "field 'editTextEmail'", TextView.class);
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
    Register2Activity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextEmail = null;

    view7f0a0087.setOnClickListener(null);
    view7f0a0087 = null;
  }
}
