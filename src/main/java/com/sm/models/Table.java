package com.sm.models;

import javafx.beans.property.*;

public class Table {
  private String rMatricula;
  private String rFirstName;
  private String rLastName;

  public Table(String sMatricula, String sFirstName, String sLastName, Boolean sPresente, Boolean sFalta, Boolean sAtestado) {
    rMatricula = sMatricula;
    rFirstName = sFirstName;
    rLastName = sLastName;
    presenteChecked.set(sPresente);
    faltaChecked.set(sFalta);
    atestadoChecked.set(sAtestado);
}

  private SimpleBooleanProperty presenteChecked = new SimpleBooleanProperty(false);
  private SimpleBooleanProperty faltaChecked = new SimpleBooleanProperty(false);
  private SimpleBooleanProperty atestadoChecked = new SimpleBooleanProperty(false);

  public SimpleBooleanProperty presenteCheckedProperty() {
    if (presenteChecked.get()) {
      faltaChecked.set(false);
      atestadoChecked.set(false);
    }
    return presenteChecked;
  }
  
  public SimpleBooleanProperty faltaCheckedProperty() {
    if (faltaChecked.get()) {
      presenteChecked.set(false);
      atestadoChecked.set(false);
    }
    return faltaChecked;
  }
  
  public SimpleBooleanProperty atestadoCheckedProperty() {
    if (atestadoChecked.get()) {
      presenteChecked.set(false);
      faltaChecked.set(false);
    }
    return atestadoChecked;
  }

  public String getRFirstName() {
    return rFirstName;
  }

  public String getRLastName() {
    return rLastName;
  }

  public String getRMatricula() {
    return rMatricula;
  }
}