package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.food.Food;

/**
 * Unmodifiable view of a food list
 */
public interface ReadOnlyFoodList {

    /**
     * Returns an unmodifiable view of the food list.
     * This list will not contain any duplicate food.
     */
    ObservableList<Food> getFoodList();

}
