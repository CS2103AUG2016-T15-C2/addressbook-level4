# A0152962Bunused
###### \java\seedu\scheduler\logic\parser\Parser.java
``` java
    //Unused: update feature removed, no longer require the regular expression
    /*private static final Pattern ENTRY_UPDATE_ARGS_FORMAT = 
            Pattern.compile("(?<targetIndex>\\d+) "
                    + "(?<isStartTimePrivate>p?)(?:(from/|f/|st/)(?<startTime>[^/]+))?"
                    + "(?<isEndTimePrivate>p?)(?:(to/|et/)(?<endTime>[^/]+))?"
                    + "(?<isDatePrivate>p?)(?:(on/|date/|sd/|by/)(?<date>[^/]+))?"
                    + "(?<isEndDatePrivate>p?)(?:(edate/|ed/)(?<endDate>[^/]+))?"
                    + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of tags
    */
```
###### \java\seedu\scheduler\logic\parser\Parser.java
``` java
        //Unused: update feature removed, removed to prevent registering an unimplemented feature
        /*case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(arguments);
            
        case UpdateCommand.COMMAND_WORD2:
            return prepareUpdate(arguments);
        */
```
###### \java\seedu\scheduler\logic\parser\Parser.java
``` java
    /**
     * Parses arguments into the context of the update entry command.
     * 
     * @param args full command args string
     * @return the newly prepared command
     */
    //Unused: update feature removed, no longer needed
    /*private Command prepareUpdate(String args) {
        final Matcher matcher = ENTRY_UPDATE_ARGS_FORMAT.matcher(args.trim());
        if(!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }
        
        try {
            return new UpdateCommand(Integer.parseInt(matcher.group("targetIndex")),
                    matcher.group("startTime"), matcher.group("endTime"), matcher.group("date"), matcher.group("endDate"),
                    getTagsFromArgs(matcher.group("tagArguments")));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }
    */
    
```
###### \java\seedu\scheduler\model\entry\Entry.java
``` java
    /**
     * Replaces this entry's name with name in the argument.
     * @param name to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setName(Name name) {
        this.name = name;
    }
    */
    
    /**
     * Replaces this entry's start time with startTime in the argument.
     * @param startTime to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }
    */
    
    /**
     * Replaces this entry's end time with endTime in the argument.
     * @param endTime to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setEndTime(EndTime endTime) {
        this.endTime = endTime;
    }
    */
    
    /**
     * Replaces this entry's date/start date with date in the argument.
     * @param date to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setDate(Date date) {
        this.date = date;
    }
    */
    
    /**
     * Replaces this entry's end date with endDate in the argument.
     * @param endDate to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }
    */
    
    /**
     * Adds tags to this entry's tags with the tags in the argument tag list that are do not already exist.
     * @param updates to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void updateTags(UniqueTagList updates) {
        this.tags.mergeFrom(updates);
    }
    */
```
###### \java\seedu\scheduler\model\entry\UniqueEntryList.java
``` java
    /**
     * Updates the specified entry with given fields if not empty.
     * 
     * @param startTime  New start time to update to.
     * @param endTime  New end time to update to.
     * @param date  New start date to update to.
     * @param endDate  New end date to update to.
     * @param tagList  New tags to add on to.
     * @param toUpdate  The entry that is required to update.
     * @throws EntryNotFoundException if no such entry could be found in the list.
     */
    /*public void update(StartTime startTime, EndTime endTime, Date date, EndDate endDate, UniqueTagList tagList, ReadOnlyEntry toUpdate) 
            throws EntryNotFoundException {
        assert toUpdate != null;
        final boolean entryFound = internalList.contains(toUpdate);
        if(!entryFound) {
            throw new EntryNotFoundException();
        }
        int index = internalList.indexOf(toUpdate);
        //if(!update.getName().fullName.equals(null) 
        //        && !update.getName().fullName.equals(toUpdate.getName().fullName)) {
        //    internalList.get(index).setName(update.getName());
        //}
        
        if(!startTime.value.equals("empty")
                && !startTime.equals(toUpdate.getStartTime())) {
            internalList.get(index).setStartTime(startTime);
        }
        if(!endTime.value.equals("empty")
                && !endTime.equals(toUpdate.getEndTime())) {
            internalList.get(index).setEndTime(endTime);
        }
        if(!date.value.equals("empty")
                && !date.equals(toUpdate.getDate())) {
            internalList.get(index).setDate(date);
        }
        if(!endDate.value.equals("empty")
                && !endDate.equals(toUpdate.getEndDate())) {
            internalList.get(index).setEndDate(endDate);
        }
        if(!tagList.equals(null)
                && !tagList.equals(toUpdate.getTags())) {
            internalList.get(index).updateTags(tagList);
        }
    }
    */
```
###### \java\seedu\scheduler\model\Model.java
``` java
    /** Updates the given entry */
    //Unused: update feature removed, no longer required
    /*void updateEntry(StartTime startTime, EndTime endTime, Date date, EndDate endDate, UniqueTagList tagList, ReadOnlyEntry entryToUpdate)
            throws UniqueEntryList.EntryNotFoundException;
    */
```
###### \java\seedu\scheduler\model\ModelManager.java
``` java
    /*@Override
    public synchronized void updateEntry(StartTime startTime, EndTime endTime, Date date, EndDate endDate, UniqueTagList tagList, ReadOnlyEntry toUpdate)
            throws UniqueEntryList.EntryNotFoundException{
        scheduler.updateEntry(startTime, endTime, date, endDate, tagList, toUpdate);
        indicateSchedulerChanged();
    }
    */
```
###### \java\seedu\scheduler\model\Scheduler.java
``` java
    /**
     * Updates the fields of a specified entry in the list.
     * 
     * @throws UniqueEntryList.EntryNotFoundException if no such entry could be found in the list.
     */
    /*public void updateEntry(StartTime st, EndTime et, Date d, EndDate ed, UniqueTagList utl, ReadOnlyEntry toUpdate) throws UniqueEntryList.EntryNotFoundException{
        entrys.update(st, et, d, ed, utl, toUpdate);
    }
    */

```
