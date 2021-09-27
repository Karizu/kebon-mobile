// Generated code from Butter Knife. Do not modify!
package com.selada.kebonmobile.presentation.akun;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.selada.kebonmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AkunFragment_ViewBinding implements Unbinder {
  private AkunFragment target;

  private View view7f0a0086;

  private View view7f0a0076;

  private View view7f0a0072;

  private View view7f0a0071;

  private View view7f0a0065;

  private View view7f0a007b;

  private View view7f0a00f2;

  @UiThread
  public AkunFragment_ViewBinding(final AkunFragment target, View source) {
    this.target = target;

    View view;
    target.btnBack = Utils.findRequiredViewAsType(source, R.id.btn_back, "field 'btnBack'", ImageView.class);
    target.btnChat = Utils.findRequiredViewAsType(source, R.id.btn_chat, "field 'btnChat'", ImageView.class);
    target.tvTitleBar = Utils.findRequiredViewAsType(source, R.id.tv_title_bar, "field 'tvTitleBar'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_tambah_lahan, "method 'onClickTambahLahan'");
    view7f0a0086 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickTambahLahan();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_history, "method 'onClickHistory'");
    view7f0a0076 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickHistory();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_faq, "method 'onClickFaq'");
    view7f0a0072 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickFaq();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_cs, "method 'onClickCs'");
    view7f0a0071 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickCs();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_about, "method 'onClickAbout'");
    view7f0a0065 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickAbout();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_keluar, "method 'onClickKeluar'");
    view7f0a007b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickKeluar();
      }
    });
    view = Utils.findRequiredView(source, R.id.frame_profile, "method 'onClickProfile'");
    view7f0a00f2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickProfile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AkunFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBack = null;
    target.btnChat = null;
    target.tvTitleBar = null;

    view7f0a0086.setOnClickListener(null);
    view7f0a0086 = null;
    view7f0a0076.setOnClickListener(null);
    view7f0a0076 = null;
    view7f0a0072.setOnClickListener(null);
    view7f0a0072 = null;
    view7f0a0071.setOnClickListener(null);
    view7f0a0071 = null;
    view7f0a0065.setOnClickListener(null);
    view7f0a0065 = null;
    view7f0a007b.setOnClickListener(null);
    view7f0a007b = null;
    view7f0a00f2.setOnClickListener(null);
    view7f0a00f2 = null;
  }
}
