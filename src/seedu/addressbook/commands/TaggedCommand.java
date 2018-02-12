package seedu.addressbook.commands;


import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.Iterator;
import java.util.List;

/**
 * Lists all persons in the address book based on the tag given to the user.
 */
public class TaggedCommand extends Command {
    public static final String COMMAND_WORD = "tagged";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers based on the tag given.\n"
            + "Example: " + COMMAND_WORD +
            " owesmoney";
    public static final String MESSAGE_FAILURE = "Only one tag can be applied";

    Tag tag;
    String[] numberOfTagsCalculator;
    public TaggedCommand(String tag) throws IllegalValueException {
        numberOfTagsCalculator = tag.trim().split(" ");
        this.tag = new Tag(tag.trim());
    }

    @Override
    public CommandResult execute() {
        if(numberOfTagsCalculator.length > 1){
            return new CommandResult(String.format(MESSAGE_FAILURE));
        }else{
            UniquePersonList allPersons = addressBook.getAllPersons();
            List<ReadOnlyPerson> taggedPersons = findTaggedPeople(allPersons).immutableListView();
            return new CommandResult(getMessageForPersonListShownSummary(taggedPersons), taggedPersons);
        }

    }

    public UniquePersonList findTaggedPeople(UniquePersonList allPersons){
        UniquePersonList taggedPersons = new UniquePersonList();
        Iterator<Person> itr = allPersons.iterator();
        while(itr.hasNext()){
            Person p = itr.next();
            if(p.getTags().contains(tag)){
                try {
                    taggedPersons.add(p);
                } catch (UniquePersonList.DuplicatePersonException e) {
                    e.printStackTrace();
                }
            }
        }
        return taggedPersons;
    }
}
