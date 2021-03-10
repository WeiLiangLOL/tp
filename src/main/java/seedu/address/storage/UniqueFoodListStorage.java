package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFoodList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for a list of {@link seedu.address.model.food.Food}.
 */
public interface UniqueFoodListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodListFilePath();

    /**
     * Returns FoodList data as a {@link ReadOnlyFoodList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodList> readFoodList() throws DataConversionException, IOException;

    /**
     * @see #getFoodListFilePath()
     */
    Optional<ReadOnlyFoodList> readFoodList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFoodList} to the storage.
     * @param foodList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodList(ReadOnlyFoodList foodList) throws IOException;

    /**
     * @see #saveFoodList(ReadOnlyFoodList)
     */
    void saveFoodList(ReadOnlyFoodList foodList, Path filePath) throws IOException;

}
