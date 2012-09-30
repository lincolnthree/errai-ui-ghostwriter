package org.jboss.errai.ui.ghostwriter.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

@Portable
public class FragmentDelete extends FragmentEvent {
  public FragmentDelete(@MapsTo("fragment") Fragment fragment) {
    super(fragment);
  }
}
