// *H=========================================================================
// *M MERCEDES BENZ RESEARCH AND DEVELOPMENT INDIA (MBRDI) RESTRICTED
// *H=========================================================================
// *H
// *C COPYRIGHT
// *C
// *C Licensed to the Apache Software Foundation (ASF) under one
// *C or more contributor license agreements.  See the NOTICE file
// *C distributed with this work for additional information
// *C regarding copyright ownership.  The ASF licenses this file
// *C to you under the Apache License, Version 2.0 (the
// *C "License"); you may not use this file except in compliance
// *C with the License.  You may obtain a copy of the License at
// *C
// *C  http://www.apache.org/licenses/LICENSE-2.0
// *C
// *C Unless required by applicable law or agreed to in writing,
// *C software distributed under the License is distributed on an
// *C "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// *C KIND, either express or implied.  See the License for the
// *C specific language governing permissions and limitations
// *C under the License.
// *C
// *H===========================================================================

package floatbox;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

/**
 * Defines the class CusHBox.
 * @author kariyas
 * @version 0.7.0
 * @since May 3, 2017
 */
public class FloatBox extends HBox {

  /**
   * TextField-Field title
   */
  @FXML
  TextField field;

  /**
   * Button-Field close
   */
  @FXML
  Button close;

  /**
   * boolean-Field isSel
   */
  boolean isSel;

  /**
   * String-Field currText
   */
  String currText;

  /**
   * String-Field filePath
   */
  String filePath;

  /**
   * Constructor for FloatBox.
   */
  public FloatBox() {
    FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "FloatBox.fxml" ) );
    fxmlLoader.setRoot( this );
    fxmlLoader.setController( this );
    try {
      fxmlLoader.load();
    } catch( IOException exception ) {
      throw new RuntimeException( exception );
    }
  }

  /**
   * This addCustomNode method defines .
   * @param node
   */
  public void addCustomNode( final Node node ) {
    getChildren().add( node );
  }

  /**
   * This setField method defines .
   * @param title
   */
  public void setField( final String title ) {
    this.field.setText( title );
    select( true );
    setCurrText( title );
  }

  /**
   * This setFilePath method defines .
   * @param path
   */
  public void setFilePath( final String path ) {
    this.filePath = path;
    final Tooltip tooltip = new Tooltip( filePath );
    this.field.setTooltip( tooltip );
    this.close.setTooltip( tooltip );
  }

  /**
   * Getter:
   * Returns the title of this TextField.
   * @return the title of this TextField.
   */
  public TextField getField() {
    return field;
  }

  /**
   * Getter:
   * Returns the currText of this FloatBox.
   * @return the currText of this FloatBox.
   */
  public String getCurrText() {
    return currText;
  }

  /**
   * Setter:
   * Sets the currText of this FloatBox.
   * @param currText
   *          the currText to set.
   */
  public void setCurrText( String currText ) {
    this.currText = currText;
  }

  /**
   * Getter:
   * Returns the close of this FloatBox.
   * @return the close of this FloatBox.
   */
  public Button getClose() {
    return close;
  }

  /**
   * Getter:
   * Returns the isSel of this FloatBox.
   * @return the isSel of this FloatBox.
   */
  public boolean isSel() {
    return isSel;
  }

  /**
   * This select method defines .
   * @param bool
   */
  public void select( final boolean bool ) {
    this.isSel = bool;
    if( !bool )
      field.setStyle( "-fx-background-color: white;" );
    else
      field.setStyle( "-fx-background-color: transparent;" );
  }

  /**
   * This updateView method defines .
   */
  public void updateView( final boolean bool ) {
    field.setEditable( !bool );
    select( bool );
    close.setDisable( !bool );
  }

}
