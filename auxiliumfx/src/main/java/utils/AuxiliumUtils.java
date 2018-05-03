/*
 * Copyright [2018] [Sparky]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Defines the class GuiUtils. Any method shall be executed on FX UI thread.
 * 
 * @author : Sparky
 * @version : $
 * @since : May 3, 2018
 */
public final class AuxiliumUtils {

	/**
	 * Logger-Field LOGGER
	 */
	public static final Logger LOGGER = Logger.getLogger(AuxiliumUtils.class.getName());

	/**
	 * Defines the class GuiActionType.
	 * 
	 * @author : Sparky
	 * @version : $
	 * @since : May 3, 2018
	 */
	public enum GuiActionType {
		/**
		 * GuiAction-Field LOAD
		 */
		LOAD,
		/**
		 * GuiAction-Field SAVE
		 */
		SAVE
	}

	/**
	 * This loadView method defines dynamically loading views during runtime. Should
	 * run on FX thread.
	 * 
	 * @param clazz
	 *            uses path
	 * @param anchorPane
	 *            uses pane to load
	 * @param fxmlPath
	 *            uses string path
	 * @param msg
	 *            for logging purpose
	 * @return controller
	 */
	public static Object loadView(final Class<?> clazz, final AnchorPane anchorPane, final String fxmlPath,
			final String msg) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(clazz.getResource(fxmlPath));
			Parent root = fxmlLoader.load();
			if (anchorPane != null)
				anchorPane.getChildren().setAll(root);
			LOGGER.log(Level.INFO, msg + " < LOADED >");

			return fxmlLoader.getController();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, msg + " < FAILED >", e);
		}
		return null;
	}

	/**
	 * This styleButton method defines .
	 * 
	 * @param button
	 * @param fileActual
	 * @param fileSelect
	 */
	public static void styleButton(final Button button, final String fileActual, final String fileSelect,
			final int width, final int height) {
		button.styleProperty()
				.bind(Bindings.when(button.focusedProperty())
						.then(new SimpleStringProperty(styleButton(fileSelect, width, height)))
						.otherwise(new SimpleStringProperty(styleButton(fileActual, width, height))));

	}

	/**
	 * This setStackContent method defines . Should run on FX thread.
	 * 
	 * @param stackContent
	 * @param nodes
	 */
	public static void setStackContent(final Pane stackContent, final AnchorPane node) {
		final int size = stackContent.getChildren().size();
		if (size > 0) {
			stackContent.getChildren().setAll(node);
			return;
		}
		stackContent.getChildren().addAll(node);
	}

	/**
	 * This getScreenBounds method defines .
	 * 
	 * @return
	 */
	public static Rectangle2D getScreenBounds() {
		return Screen.getPrimary().getVisualBounds();
	}

	/**
	 * This openFileChooser method defines .
	 * 
	 * @param type
	 * @param window
	 * @param extensionFilters
	 * @return
	 */
	public static File openFileChooser(final GuiActionType type, final Window window,
			final ExtensionFilter... extensionFilters) {
		return openFileChooser(type, window, null, extensionFilters);
	}

	/**
	 * This openFileChooser method defines .
	 * 
	 * @param type
	 * @param window
	 * @param pathDir
	 * @param extensionFilters
	 * @return
	 */
	public static File openFileChooser(final GuiActionType type, final Window window, final File pathDir,
			final ExtensionFilter... extensionFilters) {
		FileChooser fileChooser = new FileChooser();
		if (pathDir != null && pathDir.exists())
			fileChooser.setInitialDirectory(pathDir);
		for (int i = 0; i < extensionFilters.length; i++)
			fileChooser.getExtensionFilters().add(extensionFilters[i]);
		switch (type) {
		case LOAD:
			fileChooser.setTitle("Open Resource File");
			return fileChooser.showOpenDialog(window);

		case SAVE:
			fileChooser.setTitle("Save configuration File");
			return fileChooser.showSaveDialog(window);

		default:
			break;
		}
		return null;
	}

	/**
	 * This openDialog method defines dialog popup taking fxml input stream. Should
	 * run on FX thread.
	 * 
	 * @param inputstream
	 * @return
	 */
	public static <T> T openDialog(final InputStream inputstream) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			fxmlLoader.load(inputstream);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error while opening Dialog", e);
		}
		Parent root = (Parent) fxmlLoader.getRoot();
		final Stage stage = openDialog(root);
		stage.setResizable(false);
		stage.showAndWait();
		return fxmlLoader.getController();
	}

	/**
	 * This openDialog method defines new stage for a given parent element. Should
	 * run on FX thread.
	 * 
	 * @param root
	 * @return
	 */
	public static Stage openDialog(final Parent root) {
		final Stage stage = new Stage();
		stage.initModality(Modality.NONE);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		return stage;
	}

	/**
	 * This loadDirectoryChooser method defines choosing directory.
	 * 
	 * @param window
	 * @return
	 */
	public static String loadDirectoryChooser(final Window window) {
		String folderPath = null;
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select workspace Directory");
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File dir = directoryChooser.showDialog(window);
		if (dir != null) {
			folderPath = dir.getAbsolutePath();
		}
		return folderPath;
	}

	/**
	 * This openAlertDialog method defines popup dialogs windows accordingly with
	 * messages. Thread Safe method.
	 * 
	 * @param alertType
	 * @param titleBar
	 * @param headerMessage
	 * @param infoMessage
	 * @return
	 */
	public static Alert openAlertDialog(AlertType alertType, String titleBar, String headerMessage) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		return alert;

	}

	/**
	 * This styleButton method defines .
	 * 
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	public static String styleButton(final String file, final int width, final int height) {
		return "-fx-background-image: url(\"" + file + "\"); -fx-background-color: transparent; -fx-background-size: "
				+ width + " " + height
				+ "; -fx-background-repeat: no-repeat; -fx-background-position: center; -fx-text-fill: midnightblue; -fx-font-size: 18;";
	}

	/**
	 * This toWebHexCode method defines .
	 * 
	 * @param color
	 * @return
	 */
	public static String toWebHexCode(Color color) {
		return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));
	}

}
