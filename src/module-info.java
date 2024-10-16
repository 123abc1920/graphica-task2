module Graphica2 {
	requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

	opens cs.vsu.ru.k2.g42.myshkevich_a_n.task2 to javafx.graphics, javafx.fxml;
}