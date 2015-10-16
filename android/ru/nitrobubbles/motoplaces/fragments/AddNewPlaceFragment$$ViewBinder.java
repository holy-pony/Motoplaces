// Generated code from Butter Knife. Do not modify!
package ru.nitrobubbles.motoplaces.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddNewPlaceFragment$$ViewBinder<T extends ru.nitrobubbles.motoplaces.fragments.AddNewPlaceFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624084, "field 'nextButton' and method 'showSubInfoLayout'");
    target.nextButton = finder.castView(view, 2131624084, "field 'nextButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.showSubInfoLayout();
        }
      });
  }

  @Override public void unbind(T target) {
    target.nextButton = null;
  }
}
