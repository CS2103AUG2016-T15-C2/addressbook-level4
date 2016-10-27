package seedu.scheduler.testutil;

import com.google.common.io.Files;
import guitests.guihandles.EntryCardHandle;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import junit.framework.AssertionFailedError;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import seedu.scheduler.TestApp;
import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.commons.util.FileUtil;
import seedu.scheduler.commons.util.XmlUtil;
import seedu.scheduler.model.Scheduler;
import seedu.scheduler.model.entry.*;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;
import seedu.scheduler.storage.XmlSerializableScheduler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    public static String LS = System.lineSeparator();

    public static void assertThrows(Class<? extends Throwable> expected, Runnable executable) {
        try {
            executable.run();
        }
        catch (Throwable actualException) {
            if (!actualException.getClass().isAssignableFrom(expected)) {
                String message = String.format("Expected thrown: %s, actual: %s", expected.getName(),
                        actualException.getClass().getName());
                throw new AssertionFailedError(message);
            } else return;
        }
        throw new AssertionFailedError(
                String.format("Expected %s to be thrown, but nothing was thrown.", expected.getName()));
    }

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    public static String SANDBOX_FOLDER = FileUtil.getPath("./src/test/data/sandbox/");

    public static final Entry[] sampleEntryData = getSampleEntryData();

    private static Entry[] getSampleEntryData() {
        try {
            return new Entry[]{
                    new Entry(new Name("Lunch"), new StartTime("12:00"), new EndTime("13:30"), new Date("10-10-2016"), new EndDate("10-10-2017"), new UniqueTagList()),
                    new Entry(new Name("Study Group"), new StartTime("13:00"), new EndTime("15:00"), new Date("11-10-2016"), new EndDate("11-10-2017"), new UniqueTagList()),
                    new Entry(new Name("Football"), new StartTime("14:00"), new EndTime("16:00"), new Date("12-10-2016"),new EndDate("12-10-2017"),  new UniqueTagList()),
                    new Entry(new Name("Test"), new StartTime("15:00"), new EndTime("16:30"), new Date("13-10-2016"),new EndDate("13-10-2017"),  new UniqueTagList()),
                    new Entry(new Name("Basketball"), new StartTime("16:00"), new EndTime("18:00"), new Date("14-10-2016"),new EndDate("14-10-2017"),  new UniqueTagList()),
                    new Entry(new Name("Meeting"), new StartTime("17:00"), new EndTime("18:00"), new Date("15-10-2016"),new EndDate("15-10-2017"),  new UniqueTagList()),
                    new Entry(new Name("Dinner"), new StartTime("18:00"), new EndTime("19:00"), new Date("16-10-2016"),new EndDate("16-10-2017"),  new UniqueTagList()),
                    new Entry(new Name("Buy Cake"), new StartTime("19:00"), new EndTime("20:00"), new Date("17-10-2016"),new EndDate("17-10-2017"),  new UniqueTagList()),
                    new Entry(new Name("Watch Football"), new StartTime("20:00"), new EndTime("22:00"), new Date("18-10-2016"), new EndDate("18-10-2017"), new UniqueTagList())
            };
        } catch (IllegalValueException e) {
            assert false;
            //not possible
            return null;
        }
    }

    public static final Tag[] sampleTagData = getSampleTagData();

    private static Tag[] getSampleTagData() {
        try {
            return new Tag[]{
                    new Tag("relatives"),
                    new Tag("friends")
            };
        } catch (IllegalValueException e) {
            assert false;
            return null;
            //not possible
        }
    }

    public static List<Entry> generateSampleEntryData() {
        return Arrays.asList(sampleEntryData);
    }

    /**
     * Appends the file name to the sandbox folder path.
     * Creates the sandbox folder if it doesn't exist.
     * @param fileName
     * @return
     */
    public static String getFilePathInSandboxFolder(String fileName) {
        try {
            FileUtil.createDirs(new File(SANDBOX_FOLDER));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER + fileName;
    }

    public static void createDataFileWithSampleData(String filePath) {
        createDataFileWithData(generateSampleStorageScheduler(), filePath);
    }

    public static <T> void createDataFileWithData(T data, String filePath) {
        try {
            File saveFileForTesting = new File(filePath);
            FileUtil.createIfMissing(saveFileForTesting);
            XmlUtil.saveDataToFile(saveFileForTesting, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String... s) {
        createDataFileWithSampleData(TestApp.SAVE_LOCATION_FOR_TESTING);
    }

    public static Scheduler generateEmptyScheduler() {
        return new Scheduler(new UniqueEntryList(), new UniqueTagList());
    }

    public static XmlSerializableScheduler generateSampleStorageScheduler() {
        return new XmlSerializableScheduler(generateEmptyScheduler());
    }

    /**
     * Tweaks the {@code keyCodeCombination} to resolve the {@code KeyCode.SHORTCUT} to their
     * respective platform-specific keycodes
     */
    public static KeyCode[] scrub(KeyCodeCombination keyCodeCombination) {
        List<KeyCode> keys = new ArrayList<>();
        if (keyCodeCombination.getAlt() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.ALT);
        }
        if (keyCodeCombination.getShift() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.SHIFT);
        }
        if (keyCodeCombination.getMeta() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.META);
        }
        if (keyCodeCombination.getControl() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.CONTROL);
        }
        keys.add(keyCodeCombination.getCode());
        return keys.toArray(new KeyCode[]{});
    }

    public static boolean isHeadlessEnvironment() {
        String headlessProperty = System.getProperty("testfx.headless");
        return headlessProperty != null && headlessProperty.equals("true");
    }

    public static void captureScreenShot(String fileName) {
        File file = GuiTest.captureScreenshot();
        try {
            Files.copy(file, new File(fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String descOnFail(Object... comparedObjects) {
        return "Comparison failed \n"
                + Arrays.asList(comparedObjects).stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public static void setFinalStatic(Field field, Object newValue) throws NoSuchFieldException, IllegalAccessException{
        field.setAccessible(true);
        // remove final modifier from field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        // ~Modifier.FINAL is used to remove the final modifier from field so that its value is no longer
        // final and can be changed
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    public static void initRuntime() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.hideStage();
    }

    public static void tearDownRuntime() throws Exception {
        FxToolkit.cleanupStages();
    }

    /**
     * Gets private method of a class
     * Invoke the method using method.invoke(objectInstance, params...)
     *
     * Caveat: only find method declared in the current Class, not inherited from supertypes
     */
    public static Method getPrivateMethod(Class objectClass, String methodName) throws NoSuchMethodException {
        Method method = objectClass.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method;
    }

    public static void renameFile(File file, String newFileName) {
        try {
            Files.copy(file, new File(newFileName));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Gets mid point of a node relative to the screen.
     * @param node
     * @return
     */
    public static Point2D getScreenMidPoint(Node node) {
        double x = getScreenPos(node).getMinX() + node.getLayoutBounds().getWidth() / 2;
        double y = getScreenPos(node).getMinY() + node.getLayoutBounds().getHeight() / 2;
        return new Point2D(x,y);
    }

    /**
     * Gets mid point of a node relative to its scene.
     * @param node
     * @return
     */
    public static Point2D getSceneMidPoint(Node node) {
        double x = getScenePos(node).getMinX() + node.getLayoutBounds().getWidth() / 2;
        double y = getScenePos(node).getMinY() + node.getLayoutBounds().getHeight() / 2;
        return new Point2D(x,y);
    }

    /**
     * Gets the bound of the node relative to the parent scene.
     * @param node
     * @return
     */
    public static Bounds getScenePos(Node node) {
        return node.localToScene(node.getBoundsInLocal());
    }

    public static Bounds getScreenPos(Node node) {
        return node.localToScreen(node.getBoundsInLocal());
    }

    public static double getSceneMaxX(Scene scene) {
        return scene.getX() + scene.getWidth();
    }

    public static double getSceneMaxY(Scene scene) {
        return scene.getX() + scene.getHeight();
    }

    public static Object getLastElement(List<?> list) {
        return list.get(list.size() - 1);
    }

    /**
     * Removes a subset from the list of entrys.
     * @param entrys The list of entrys
     * @param entrysToRemove The subset of entrys.
     * @return The modified entrys after removal of the subset from entrys.
     */
    public static TestEntry[] removeEntrysFromList(final TestEntry[] entrys, TestEntry... entrysToRemove) {
        List<TestEntry> listOfEntrys = asList(entrys);
        listOfEntrys.removeAll(asList(entrysToRemove));
        return listOfEntrys.toArray(new TestEntry[listOfEntrys.size()]);
    }


    /**
     * Returns a copy of the list with the entry at specified index removed.
     * @param list original list to copy from
     * @param targetIndexInOneIndexedFormat e.g. if the first element to be removed, 1 should be given as index.
     */
    public static TestEntry[] removeEntryFromList(final TestEntry[] list, int targetIndexInOneIndexedFormat) {
        return removeEntrysFromList(list, list[targetIndexInOneIndexedFormat-1]);
    }

    /**
     * Replaces entrys[i] with a entry.
     * @param entrys The array of entrys.
     * @param entry The replacement entry
     * @param index The index of the entry to be replaced.
     * @return
     */
    public static TestEntry[] replaceEntryFromList(TestEntry[] entrys, TestEntry entry, int index) {
        entrys[index] = entry;
        return entrys;
    }

    /**
     * Appends entrys to the array of entrys.
     * @param entrys A array of entrys.
     * @param entrysToAdd The entrys that are to be appended behind the original array.
     * @return The modified array of entrys.
     */
    public static TestEntry[] addEntrysToList(final TestEntry[] entrys, TestEntry... entrysToAdd) {
        List<TestEntry> listOfEntrys = asList(entrys);
        listOfEntrys.addAll(asList(entrysToAdd));
        return listOfEntrys.toArray(new TestEntry[listOfEntrys.size()]);
    }

    private static <T> List<T> asList(T[] objs) {
        List<T> list = new ArrayList<>();
        for(T obj : objs) {
            list.add(obj);
        }
        return list;
    }

    public static boolean compareCardAndEntry(EntryCardHandle card, ReadOnlyEntry entry) {
        return card.isSameEntry(entry);
    }

    public static Tag[] getTagList(String tags) {

        if (tags.equals("")) {
            return new Tag[]{};
        }

        final String[] split = tags.split(", ");

        final List<Tag> collect = Arrays.asList(split).stream().map(e -> {
            try {
                return new Tag(e.replaceFirst("Tag: ", ""));
            } catch (IllegalValueException e1) {
                //not possible
                assert false;
                return null;
            }
        }).collect(Collectors.toList());

        return collect.toArray(new Tag[split.length]);
    }

}
