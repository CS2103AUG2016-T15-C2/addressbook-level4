package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Scheduler;
import seedu.address.model.entry.*;

/**
 *
 */
public class TypicalTestEntrys {

    public static TestEntry alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestEntrys() {
        try {
            alice =  new EntryBuilder().withName("Alice Pauline").withDate("123, Jurong West Ave 6, #08-111")
                    .withEndTime("alice@gmail.com").withStartTime("85355255")
                    .withTags("friends").build();
            benson = new EntryBuilder().withName("Benson Meier").withDate("311, Clementi Ave 2, #02-25")
                    .withEndTime("johnd@gmail.com").withStartTime("98765432")
                    .withTags("owesMoney", "friends").build();
            carl = new EntryBuilder().withName("Carl Kurz").withStartTime("95352563").withEndTime("heinz@yahoo.com").withDate("wall street").build();
            daniel = new EntryBuilder().withName("Daniel Meier").withStartTime("87652533").withEndTime("cornelia@google.com").withDate("10th street").build();
            elle = new EntryBuilder().withName("Elle Meyer").withStartTime("9482224").withEndTime("werner@gmail.com").withDate("michegan ave").build();
            fiona = new EntryBuilder().withName("Fiona Kunz").withStartTime("9482427").withEndTime("lydia@gmail.com").withDate("little tokyo").build();
            george = new EntryBuilder().withName("George Best").withStartTime("9482442").withEndTime("anna@google.com").withDate("4th street").build();

            //Manually added
            hoon = new EntryBuilder().withName("Hoon Meier").withStartTime("8482424").withEndTime("stefan@mail.com").withDate("little india").build();
            ida = new EntryBuilder().withName("Ida Mueller").withStartTime("8482131").withEndTime("hans@google.com").withDate("chicago ave").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadSchedulerWithSampleData(Scheduler ab) {

        try {
            ab.addEntry(new Entry(alice));
            ab.addEntry(new Entry(benson));
            ab.addEntry(new Entry(carl));
            ab.addEntry(new Entry(daniel));
            ab.addEntry(new Entry(elle));
            ab.addEntry(new Entry(fiona));
            ab.addEntry(new Entry(george));
        } catch (UniqueEntryList.DuplicateEntryException e) {
            assert false : "not possible";
        }
    }

    public TestEntry[] getTypicalEntrys() {
        return new TestEntry[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public Scheduler getTypicalScheduler(){
        Scheduler ab = new Scheduler();
        loadSchedulerWithSampleData(ab);
        return ab;
    }
}
