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

public class VerificationEmailActivity_ViewBinding implements Unbinder {
  private VerificationEmailActivity target;

  private View view7f0a028c;

  @UiThread
  public VerificationEmailActivity_ViewBinding(VerificationEmailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VerificationEmailActivity_ViewBinding(final VerificationEmailActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_skip, "method 'onClickSkip'");
    view7f0a028c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSkip();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a028c.setOnClickListener(null);
    view7f0a028c = null;
  }
}
