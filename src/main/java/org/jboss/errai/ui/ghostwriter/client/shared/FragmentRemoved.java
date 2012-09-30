package org.jboss.errai.ui.ghostwriter.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.marshalling.client.api.annotations.MapsTo;

@Portable
public class FragmentRemoved extends FragmentEvent {
  public FragmentRemoved(@MapsTo("fragment") Fragment fragment) {
    super(fragment);
  }
}
