// Generated code from Butter Knife. Do not modify!
package ru.nitrobubbles.motoplaces.adapters;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SearchTextAdapter$ViewHolder$$ViewBinder<T extends ru.nitrobubbles.motoplaces.adapters.SearchTextAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624149, "field 'distance'");
    target.distance = finder.castView(view, 2131624149, "field 'distance'");
    view = finder.findRequiredView(source, 2131624045, "field 'title'");
    target.title = finder.castView(view, 2131624045, "field 'title'");
    view = finder.findRequiredView(source, 2131624133, "field 'subtext'");
    target.subtext = finder.castView(view, 2131624133, "field 'subtext'");
  }

  @Override public void unbind(T target) {
    target.distance = null;
    target.title = null;
    target.subtext = null;
  }
}
