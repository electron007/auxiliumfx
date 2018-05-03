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
package main;

import floatbox.FloatBox;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Defines the class MasterTest.java
 *
 * @author : Sparky
 * @version : $
 * @since : May 3, 2018
 */
public class MasterTest extends Application {

	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group group = new Group(getFloatBox());

		final Scene scene = new Scene(group, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static FloatBox getFloatBox() {
		final FloatBox floatBox = new FloatBox();

		return floatBox;
	}

}
