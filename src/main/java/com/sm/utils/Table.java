package com.sm.utils;

import javafx.beans.property.*;

public class Table {
  private String rFirstName;
  private String rLastName;

  public Table(String sFirstName, String sLastName, Boolean sPresente, Boolean sFalta, Boolean sAtestado) {
    rFirstName = sFirstName;
    rLastName = sLastName;
    presenteCheckedProperty(false).set(sPresente);
    faltaCheckedProperty(false).set(sFalta);
    atestadoCheckedProperty(false).set(sAtestado);
  }

  private SimpleBooleanProperty presenteChecked = new SimpleBooleanProperty(false);
  private SimpleBooleanProperty faltaChecked = new SimpleBooleanProperty(false);
  private SimpleBooleanProperty atestadoChecked = new SimpleBooleanProperty(false);

  public SimpleBooleanProperty presenteCheckedProperty(boolean recursion) {
    if (recursion) {
      faltaCheckedProperty(false).set(false);
      atestadoCheckedProperty(false).set(false);
    }
    return presenteChecked;
  }

  public SimpleBooleanProperty faltaCheckedProperty(boolean recursion) {
    if (recursion) {
      presenteCheckedProperty(false).set(false);
      atestadoCheckedProperty(false).set(false);
    }
    return faltaChecked;
  }

  public SimpleBooleanProperty atestadoCheckedProperty(boolean recursion) {
    if (recursion) {
      presenteCheckedProperty(false).set(false);
      faltaCheckedProperty(false).set(false);
    }
    return atestadoChecked;
  }

  public String getRFirstName() {
    return rFirstName;
  }

  public String getRLastName() {
    return rLastName;
  }
}