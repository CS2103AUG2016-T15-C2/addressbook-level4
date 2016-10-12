package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Scheduler;
import seedu.address.model.entry.*;

/**
 *
 */
public class TypicalTestEntrys {

    public static TestEntry chem, bio, dinner, date, groceries, concert, hike, meeting, pickup;

    public TypicalTestEntrys() {
        try {
            chem =  new EntryBuilder().withName("Chemistry Test").withDate("01-10-2016")
                    .withEndTime("12:00").withStartTime("10:30")
                    .withTags("chem","test").build();
            bio = new EntryBuilder().withName("Biology Test").withDate("01-10-2016")
                    .withEndTime("16:00").withStartTime("14:00")
                    .withTags("bio", "test").build();
            dinner = new EntryBuilder().withName("Dinner with friends").withStartTime("18:00").withEndTime("20:00").withDate("01-10-2016").build();
            date = new EntryBuilder().withName("Date").withStartTime("13:00").withEndTime("21:00").withDate("02-10-2016").build();
            groceries = new EntryBuilder().withName("Weekly groceries").withStartTime("14:00").withEndTime("16:00").withDate("03-10-2016").build();
            concert = new EntryBuilder().withName("Concert").withStartTime("19:00").withEndTime("22:00").withDate("04-10-2016").build();
            hike = new EntryBuilder().withName("Morning Hike").withStartTime("07:00").withEndTime("10:00").withDate("04-10-2016").build();

            //Manually added
            meeting = new EntryBuilder().withName("Project meeting").withStartTime("14:00").withEndTime("16:00").withDate("10-10-2016").build();
            pickup = new EntryBuilder().withName("Pickup parents from airport").withStartTime("15:00").withEndTime("16:00").withDate("11-10-2016").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadSchedulerWithSampleData(Scheduler ab) {

        try {
            ab.addEntry(new Entry(chem));
            ab.addEntry(new Entry(bio));
            ab.addEntry(new Entry(dinner));
            ab.addEntry(new Entry(date));
            ab.addEntry(new Entry(groceries));
            ab.addEntry(new Entry(concert));
            ab.addEntry(new Entry(hike));
        } catch (UniqueEntryList.DuplicateEntryException e) {
            assert false : "not possible";
        }
    }

    public TestEntry[] getTypicalEntrys() {
        return new TestEntry[]{chem, bio, dinner, date, groceries, concert, hike};
    }

    public Scheduler getTypicalScheduler(){
        Scheduler ab = new Scheduler();
        loadSchedulerWithSampleData(ab);
        return ab;
    }
}
