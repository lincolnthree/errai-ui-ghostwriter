package org.jboss.errai.ui.ghostwriter.client.shared;

public abstract class FragmentEvent {

  private Fragment fragment;

  public FragmentEvent(Fragment fragment) {
    this.fragment = fragment;
  }

  public Fragment getFragment() {
    return fragment;
  }
}
