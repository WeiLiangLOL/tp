package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyFoodList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * A class to access FoodList data stored as a json file on the hard disk.
 */
public class JsonUniqueFoodListStorage implements UniqueFoodListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniqueFoodListStorage.class);

    private Path filePath;

    public JsonUniqueFoodListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getFoodListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFoodList> readFoodList() throws DataConversionException, IOException {
        return readFoodList(filePath);
    }

    /**
     * Similar to {@link #readFoodList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFoodList> readFoodList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUniqueFoodList> jsonFoodList = JsonUtil.readJsonFile(
                filePath, JsonSerializableUniqueFoodList.class);
        if (!jsonFoodList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFoodList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFoodList(ReadOnlyFoodList foodList) throws IOException {
        saveFoodList(foodList, filePath);
    }

    /**
     * Similar to {@link #saveFoodList(ReadOnlyFoodList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFoodList(ReadOnlyFoodList foodList, Path filePath) throws IOException {
        requireNonNull(foodList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUniqueFoodList(foodList), filePath);
    }

}
