package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Scheduler;
import seedu.address.model.person.*;

/**
 *
 */
public class TypicalTestPersons {

    public static TestPerson alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestPersons() {
        try {
            alice =  new PersonBuilder().withName("Alice Pauline").withDate("123, Jurong West Ave 6, #08-111")
                    .withEndTime("alice@gmail.com").withStartTime("85355255")
                    .withTags("friends").build();
            benson = new PersonBuilder().withName("Benson Meier").withDate("311, Clementi Ave 2, #02-25")
                    .withEndTime("johnd@gmail.com").withStartTime("98765432")
                    .withTags("owesMoney", "friends").build();
            carl = new PersonBuilder().withName("Carl Kurz").withStartTime("95352563").withEndTime("heinz@yahoo.com").withDate("wall street").build();
            daniel = new PersonBuilder().withName("Daniel Meier").withStartTime("87652533").withEndTime("cornelia@google.com").withDate("10th street").build();
            elle = new PersonBuilder().withName("Elle Meyer").withStartTime("9482224").withEndTime("werner@gmail.com").withDate("michegan ave").build();
            fiona = new PersonBuilder().withName("Fiona Kunz").withStartTime("9482427").withEndTime("lydia@gmail.com").withDate("little tokyo").build();
            george = new PersonBuilder().withName("George Best").withStartTime("9482442").withEndTime("anna@google.com").withDate("4th street").build();

            //Manually added
            hoon = new PersonBuilder().withName("Hoon Meier").withStartTime("8482424").withEndTime("stefan@mail.com").withDate("little india").build();
            ida = new PersonBuilder().withName("Ida Mueller").withStartTime("8482131").withEndTime("hans@google.com").withDate("chicago ave").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadSchedulerWithSampleData(Scheduler ab) {

        try {
            ab.addPerson(new Person(alice));
            ab.addPerson(new Person(benson));
            ab.addPerson(new Person(carl));
            ab.addPerson(new Person(daniel));
            ab.addPerson(new Person(elle));
            ab.addPerson(new Person(fiona));
            ab.addPerson(new Person(george));
        } catch (UniquePersonList.DuplicatePersonException e) {
            assert false : "not possible";
        }
    }

    public TestPerson[] getTypicalPersons() {
        return new TestPerson[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public Scheduler getTypicalScheduler(){
        Scheduler ab = new Scheduler();
        loadSchedulerWithSampleData(ab);
        return ab;
    }
}
