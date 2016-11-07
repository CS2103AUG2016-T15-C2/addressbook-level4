# Manual Testing

## Loading the SampleData.xml
1. Create a `data` folder at the file location where `Scheduler.jar` is located.
2. Copy `SampleData.xml` file to `data` foler.
3. Launch `Scheduler.jar`.
4. Type `path data/SampleData` into the input box, press Enter.
5. Close the application.
6. Relaunch `Scheduler.jar`, the data should now be loaded.<br>

## Manual Testing

#### Help
* Input: `help`<br>
Expected Output: <br>
Prompt: `Opened help window.`<br>
Scheduler App opens the help window which links to the UserGuide.md on GitHub<br>

* Step 1: Click `Help` located in the upper left corner.<br>
Step 2: Click `Help` again from the drop down list. <br>
Expected Output: <br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler App opens the help window which links to the UserGuide.md on GitHub<br>

#### Add
* Input: `add` or `a` <br>
Expected Output: <br>
Prompt: *No new prompt, displaying previous*<br>
No change to scheduler.<br>

* Input: `add Meeting st/13:00 et/15:00 sd/11-11-2016 ed/11-11-2016 t/meeting`<br>
Expected Output: <br>
Prompt: `New entry added: Meeting Start Time: 13:00 End Time: 15:00 Date: 11-11-2016 End Date: 11-11-2016 Tags: [meeting]` <br>
New entry added to the bottom of the list with index `53`.<br>

* Input: `undo`<br>
Expected Output: <br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverted to previous state, Entry `53` is no longer in the scheduler.<br>

* Input: `redo`<br>
Expected Output: <br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverted to previous state, Entry `53` is back in the scheduler.<br>

* Input: `add Meeting st/13:00 et/15:00 sd/11-11-2016 ed/11-11-2016 t/meeting`<br>
Expected Output: <br>
Prompt: `This entry already exists in the scheduler`<br>
No change in Scheduler.<br>

* Input: `add Meeting sd/11-11-2016 ed/11-11-2016`<br>
Expected Output:<br>
Prompt: `New entry added: Meeting Start Time: empty End Time: empty Date: 11-11-2016 End Date: 11-11-2016 Tags: `<br>
New entry added to the bottom of the list with index `54`. New entry does not display `Start Time` and `End Time`.<br>

* Input: `a Meeting from/13:00 to/15:00`<br>
Expected Output:<br>
Prompt: `New entry added: Meeting Start Time: 13:00 End Time: 15:00 Date: *Today's Date on Computer* End Date:  *Today's Date on Computer* Tags: `<br>
New Entry added to the bottom of the list with index `55`. New Entry retrieves local time and uses it for `Start Date` and `End Date`. The Entry is coloured `Red` for attention.<br>

* Input: `a Gathering st/1300`<br>
Expected Output:<br>
Prompt: `Item's start time should be in the 24-Hr format hh-mm in numbers separated by a ':'`<br>
No change to Scheduler.<br>

* Input: `a Gathering et/1300`<br>
Expected Output:<br>
Prompt: `Item's end time should be in the 24-Hr format hh-mm in numbers separated by a ':'`<br>
No change to Scheduler.<br>

* Input: `a Gathering sd/24-05-16`<br>
Expected Output:<br>
Prompt: `Item's start date should be in the format dd-mm-yyyy in numbers separated by a '-'`<br>
No change in Scheduler. <br>

* Input: `a Gathering ed/24-05-16`<br>
Expected Output:<br>
Prompt: `Item's end date should be in the format dd-mm-yyyy in numbers separated by a '-'`<br>
No change in Scheduler. <br>

* Input: `a Gathering sd/24-05-2016`<br>
Expected Output:<br>
Prompt: `New entry added: Gathering Start Time: empty End Time: empty Date: 24-05-2016 End Date: empty Tags: `<br>
New Entry added to the bottom of the list with index `56`. Only `Start Date` displays in the scheduler. The Entry is coloured `Red` for attention.<br>

* Input: `a Gathering sd/24-05-2016 t/1`<br>
Expected Output:<br>
Prompt: `This entry already exists in the scheduler`<br>
No change in Scheduler. <br>

* Input: `a Gathering et/13:00 sd/24-05-2016`<br>
Expected Output:<br>
Prompt: `New entry added: Gathering Start Time: empty End Time: 13:00 Date: 24-05-2016 End Date: *Today's Date on Computer* Tags: `<br>
New Entry added to the bottom of the list with index `57`. New Entry retrieves local time and uses it for `End Date`. `Start Time` is also displayed with `empty` in the field. The Entry is coloured `Red` for attention.<br>

* Input: `a Gathering by/13-11-2017`<br>
Expected Output:<br>
Prompt: `New entry added: Gathering Start Time: empty End Time: empty Date: empty End Date: 13-11-2017 Tags: `<br>
New Entry added to the bottom of the list with index `58`. Only `End Date` displays in the scheduler. <br>

* Input: `a Gathering st/13:00 by/13-11-2017`<br>
Expected Output:<br>
Prompt: `New entry added: Gathering Start Time: 13:00 End Time: empty Date: 07-11-2016 End Date: 13-11-2017 Tags: `<br>
New Entry added to the bottom of the list with index `59`. New Entry retrieves local time and uses it for `Start Date`. `End Time` is also displayed with `empty` in the field. <br>

* Input: `a Gathering st/13:00`<br>
Expected Output:<br>
Prompt: `New entry added: Gathering Start Time: 13:00 End Time: empty Date: 07-11-2016 End Date: empty Tags: `<br>
New Entry added to the bottom of the list with index `60`. New Entry retrieves local time and uses it for `Start Date`. `End Date` and `End Time` are hidden.<br>

* Input: `a Gathering et/13:00`<br>
Expected Output:<br>
Prompt: `New entry added: Gathering Start Time: empty End Time: 13:00 Date: empty End Date: 07-11-2016 Tags: `<br>
New Entry added to the bottom of the list with index `61`. New Entry retrieves local time and uses it for `End Date`. `Start Date` and `Start Time` are hidden. The Entry is coloured `Red` for attention.<br>

#### List & Find
* Input: `find` or `f`<br>
Expected Output:<br>
Prompt: `Invalid command format! `<br>
`find: Finds all entrys whose names contain any of the specified keywords (case-sensitive) and displays them as a list with index numbers.`<br>
`Can also be used to find completed and incompleted task.`<br>
`Parameters: KEYWORD [MORE_KEYWORDS]...`<br>
No change to scheduler.<br>

* Input: `find homework`<br>
Expected Output:<br>
Prompt: `7 entrys listed!`<br>
Scheduler lists out `7 entries`, `6` of which are `completed` and coloured in `Green`. The `incompleted` entry should be on the top of the list.<br>

* Input: `f exam`<br>
Expected Output:<br>
Prompt: `6 entrys listed!`<br>
Scheduler lists out `6 entries`<br>

* Input: `f cs`<br>
Expected Output:<br>
Prompt: `0 entrys listed!`<br>
Scheduler should be empty.

* Input: `f CS2106`<br>
Expected Output:<br>
Prompt: `5 entrys listed!`<br>
Scheduler lists out `5 entries`, `3` of which are `completed` and coloured in `Green`. The `incompleted` entries should be on the top of the list.<br>

* Input: `f cs2106 lab`<br>
Expected Output:<br>
Prompt: `8 entrys listed!`<br>
Scheduler lists out `8 entries`, `5` of which are `completed` and coloured in `Green`. The `incompleted` entries should be on the top of the list. The scheduler displays all entries that have either of the keywords. 

* Input: `f c` or `f completed`<br>
Expected Output:<br>
Prompt: `22 entrys listed!`<br>
Scheduler lists out all `22 entries` which are `completed` and coloured in `Green`. <br>

* Input: `f i` or `f incompleted`<br>
Expected Output:<br>
Prompt: `39 entrys listed!`<br>
Scheduler lists out all `39 entries` which are `incompleted`. 

* Input: `list` or `l`<br>
Expected Output:<br>
Prompt: `Listed all entrys`<br>
Scheduler lists out all the entries. Last entry should have index `61`.<br>

#### Delete
* Input: `delete` or `d`<br>
Expected Output:<br>
Prompt: `Invalid command format! `<br>
`delete: Deletes the entry identified by the index number used in the last entry listing.`<br>
`Parameters: INDEX (must be a positive integer)`<br>
`Example: delete 1`<br>
No change to scheduler.<br>

* Input: `d 41`<br>
Expected Output:<br>
Prompt: `Deleted Entry: Beer with friends Start Time: empty End Time: empty Date: empty End Date: 12-10-2016 Tags: [Completed]`<br>
Entry at index `41` is removed from the scheduler, all entries below are shifted up.<br>

* Input: `undo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Entry that is deleted is added back in at index `41`. All entries below are shifted down.<br>

* Input: `redo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Entry at index `41` is removed from the scheduler, all entries below are shifted up.<br>

#### Edit
* Input: `edit` or `e`<br>
Expected Output:<br>
Prompt: `Invalid command format! `<br>
`edit: Edits an entry in the scheduler. Parameters: INDEX NAME st/START_TIME et/END_TIME d/DATE [t/TAG]...`<br>
`Example: edit 2 John Wedding st/15:00 et/21:00 d/12-10-2016 t/undone`<br>
No change to scheduler.<br>

* Input: `
