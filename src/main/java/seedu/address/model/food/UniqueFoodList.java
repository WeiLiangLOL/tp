package seedu.address.model.food;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyFoodList;
import seedu.address.model.food.exceptions.FoodItemNotFoundException;
import seedu.address.model.person.Person;

public class UniqueFoodList implements ReadOnlyFoodList, Iterable<Food>  {

    private final ObservableList<Food> foodList;

    public UniqueFoodList(ReadOnlyFoodList list) {
        foodList = FXCollections.observableArrayList(list.getFoodList());
    }

    public UniqueFoodList() {
        foodList = FXCollections.observableArrayList();
    }

    /**
     * Views food list.
     *
     * @return food list
     */
    public ObservableList<Food> getFoodList() {
        return foodList;
    }

    /**
     * Adds a food item into the food list.
     *
     * @param foodItem food item
     * @return success message
     */
    public String addFoodItem(Food foodItem) {
        this.foodList.add(foodItem);
        String result = "Success adding " + foodItem.getName() + " to food list.";
        return result;
    }

    /**
     * Updates the relevant info of the food into the food list.
     *
     * @param foodItem updated food item
     */
    public void updateFoodItem(Food foodItem) {
        for (Food food : this.foodList) {
            if (food.getName().equals(foodItem.getName())) {
                food.updateCarbos(foodItem.getCarbos());
                food.updateFats(foodItem.getFats());
                food.updateProteins(foodItem.getProteins());
                return;
            }
        }
        throw new FoodItemNotFoundException();
    }

    /**
     * Checks if the food list contains a particular food item.
     * @param foodItem food item
     * @return true or false on whether a match is found
     */
    public boolean hasFoodItem(Food foodItem) {
        for (Food food : this.foodList) {
            if (food.getName().equals(foodItem.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a food item from the food list based on the food name.
     *
     * @param index food item index
     */
    public void deleteFoodItem(int index) {
        this.foodList.remove(index);
    }

    /**
     * Lists all food items in the food list.
     *
     * @return string output of all food items
     */
    public String listAllFoodItem() {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 1; //Used for for-loop counter indicator.
        for (Food food : this.foodList) {
            stringBuilder.append(counter + ". " + food.toString() + "\n");
            counter++;
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Food> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(foodList));
    }

    @Override
    public Iterator<Food> iterator() {
        return foodList.iterator();
    }

}
