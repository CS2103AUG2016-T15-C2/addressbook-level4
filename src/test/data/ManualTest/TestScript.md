# Manual Testing

## Loading the SampleData.xml
1. Create a `data` folder at the file location where `Scheduler.jar` is located.
2. Copy `SampleData.xml` file to `data` foler.
3. Launch `Scheduler.jar`.
4. Type `path data/SampleData` into the input box, press Enter.
5. Close the application.
6. Relaunch `Scheduler.jar`, the data should now be loaded.<br>

## Command Tests

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

* Input: `delete 41`<br>
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

* Input: `d 62`<br>
Expected Output:<br>
Prompt: `The entry index provided is invalid`<br>
No change to scheduler.<br>

#### Edit
* Input: `edit` or `e`<br>
Expected Output:<br>
Prompt: `Invalid command format! `<br>
`edit: Edits an entry in the scheduler. Parameters: INDEX NAME st/START_TIME et/END_TIME d/DATE [t/TAG]...`<br>
`Example: edit 2 John Wedding st/15:00 et/21:00 d/12-10-2016 t/undone`<br>
No change to scheduler.<br>

* Input: `edit 61 Gathering 1`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering 1 Start Time: empty End Time: empty Date: empty End Date: empty Tags: `<br>
Entry at index `61` is now changed to just `Gathering` without another parameters shown. <br>

* Input: `e 62 Gather`<br>
Expected Output:<br>
Prompt: `The entry index provided is invalid`<br>
No change to scheduler.<br>

* Input: `e 60 Gathering 1`<br>
Expected Output:<br>
Prompt: `This entry already exists in the scheduler`<br>
No change to scheduler.<br>

* Input: `e 60 Gathering 1 t/1`<br>
Expected Output:<br>
Prompt: `This entry already exists in the scheduler`<br>
No change to scheduler.<br>

* Input: `e 61 st/13:00`<br>
Expected Output:<br>
Prompt: `Entry names should be spaces or alphanumeric characters`<br>
No change to scheduler.<br>

* Input: `e 61 Gathering st/13:00`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: 13:00 End Time: empty Date: empty End Date: empty Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Date` displaying as `empty` and `Start Time` being displayed.<br>

* Input: `e 61 Gathering et/13:00`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: empty End Time: 13:00 Date: empty End Date: empty Tags: `<br>
Entry at `61` is now changed to `Gathering` with `End Date` displaying as `empty` and `End Time` being displayed.<br>

* Input: `e 61 Gathering sd/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: empty End Time: empty Date: 11-11-2016 End Date: empty Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Date` displayed.<br>

* Input: `e 61 Gathering ed/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: empty End Time: empty Date: empty End Date: 11-11-2016 Tags: `<br>
Entry at `61` is now changed to `Gathering` with `End Date` displayed.<br>

* Input: `e 61 Gathering st/11:00 et/13:00`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: 11:00 End Time: 13:00 Date: empty End Date: empty Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Date` and `End Date` displaying `empty`, while `Start Time` and `End Time` displays their time.<br>

* Input: `e 61 Gathering sd/11-11-2016 ed/12-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: empty End Time: empty Date: 11-11-2016 End Date: 12-11-2016 Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Date` and `End Date` being displayed. <br>

* Input: `e 61 Gathering st/13:00 sd/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: 13:00 End Time: empty Date: 11-11-2016 End Date: empty Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Time` and `Start Date` being displayed. <br>

* Input: `e 61 Gathering et/13:00 ed/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: empty End Time: 13:00 Date: empty End Date: 11-11-2016 Tags: `<br>
Entry at `61` is now changed to `Gathering` with `End Time` and `End Date` being displayed. <br>

* Input: `e 61 Gathering st/11:00 ed/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: 13:00 End Time: empty Date: empty End Date: 11-11-2016 Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Date` and `End Time` displaying `empty`, while `Start Time` and `End Date` displays.<br>

* Input: `e 61 Gathering et/11:00 sd/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: empty End Time: 11:00 Date: 11-11-2016 End Date: empty Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Time` and `End Date` displaying `empty`, while `Start Date` and `End Time` displays.<br>

* Input: `e 61 Gathering st/11:00 et/13:00 sd/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: 11:00 End Time: 13:00 Date: 11-11-2016 End Date: empty Tags: `<br>
Entry at `61` is now changed to `Gathering` with `End Date` displaying `empty`, while `Start Time`, `Start Date` and `End Time` displays.<br>

* Input: `e 61 Gathering st/11:00 et/13:00 ed/11-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: 11:00 End Time: 13:00 Date: empty End Date: 11-11-2016 Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Date` displaying `empty`, while `Start Time`, `End Date` and `End Time` displays.<br>

* Input: `e 61 Gathering st/11:00 sd/05-11-2016 ed/06-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: 11:00 End Time: empty Date: 05-11-2016 End Date: 06-11-2016 Tags: `<br>
Entry at `61` is now changed to `Gathering` with `End Time` displaying `empty`, while `Start Time`, `Start Date` and `End Date` displays. Entry is coloured `Red` for attention.<br>

* Input: `e 61 Gathering et/11:00 sd/05-11-2016 ed/06-11-2016`<br>
Expected Output:<br>
Prompt: `Entry edited: Gathering Start Time: empty End Time: 11:00 Date: 05-11-2016 End Date: 06-11-2016 Tags: `<br>
Entry at `61` is now changed to `Gathering` with `Start Time` displaying `empty`, while `End Time`, `Start Date` and `End Date` displays. Entry is coloured `Red` for attention.<br>

* Input: `e 23 test`<br>
Expected Output:<br>
Prompt: `Entry edited: test Start Time: empty End Time: empty Date: empty End Date: empty Tags: `<br>
Entry at `23` is now changed to `test` from `Check out of hostel`.<br>

* Input: `undo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverts previous action, entry at `23` is now back to `Check out of hostel`.<br>

* Input: `redo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverts previous undo action, entry at `23` is now back to `test`.<br>

#### Mark
* Input: `mark` or `m`<br>
Expected Output:<br>
Prompt: `Invalid command format! `<br>
`mark: Marks the entry identified by the index number used in the last entry listing as completed.`<br>
`Parameters: INDEX (must be a positive integer)`<br>
`Example: mark 1`<br>
No change to scheduler.<br>

* Input: `mark 5`<br>
Expected Output:<br>
Prompt: `Marked Entry: CS2103 Project Submission Start Time: empty End Time: 23:59 Date: empty End Date: 07-11-2016 Tags: [`<br>
Entry at index `5` is marked as `completed` with a `tag`, the entry is pushed to the bottom of the list, now at index `61`. It is also coloured `Green`.<br>

* Input: `undo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverts previous action, entry `61` now reverts back to coloured `Red` and back at index `5`, without the `completed` tag.<br>

* Input: `redo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverts previous undo action, entry `5` now reverts back to coloured `Green` and back at index `61`, with the `completed` tag.<br>

* Input: `m 67`<br>
Expected Output:<br>
Prompt: `The entry index provided is invalid`<br>
No change to scheduler.<br>

#### Undo/Redo
* Inputs: `e 59 Gather Undo`<br>
`d 60`<br>
`a undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
Expected Output:<br>
Entry at index `59` changed to `Gather Undo`. Entry at `60` deleted. Added new entry at the bottom. Undo all mentioned 3 commands. Reverts back to original state.<br>

* Inputs: `a 1`<br>
`a 2`<br>
`a 3`<br>
`a 4`<br>
`a 5`<br>
`a 6`<br>
`a 7`<br>
`a 8`<br>
`a 9`<br>
`a 10`<br>
`a 11`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
`undo`<br>
Expected Output:<br>
After adding 11 entries and typing 11 `undo`s, entry `1` should be left in the scheduler as the undo stack holds up to 10 recent actions.<br>

* Inputs: `a 2`<br>
`undo`<br>
`a 3`<br>
`redo`<br>
Expected Output:<br>
Executing a `new valid command` clears the redo stack. No change should happen to the scheduler when inputting `redo` after adding a new entry.<br>

* Inputs: `a st/`<br>
`undo`<br>
Expected Output:<br>
Executing an `invalid command` will not be registered in the undo stack. Inputting `undo` will undo the last valid command. Entry at `63` should be deleted.<br>

* Inputs: `f homework`<br>
`d 2`<br>
`list`<br>
`f homework`<br>
`undo`<br>
`redo` or `d 2`<br>
`list`<br>
`undo`<br>
Expected Output:<br>
Note that `CS3230 Homework 2` is at index `36` when all entries are being listed. `f homework` displays it at index `2`. `d 2` removes it from the list. Going back to all entries, the said entry is no longer at index `36`. Returning to the `filtered list`, `undo` returns it back to index `2`. Delete it again or redo. Inputting `undo` after `list`ing all will put the entry back at index `36`.<br>

#### Path
* Input: `path` or `p`<br>
Expected Output:<br>
Prompt: `Invalid command format! `<br>
`path or p: Change a specific file path to save. Parameters: FILE_PATH`<br>
`Example: path or p dropbox`<br>
No change to scheduler.<br>

* Input: `path data/new_path` or `p data/new_path`<br>
Expected Output:<br>
Prompt: `Invalid command format! `<br>
`path or p: Change a specific file path to save. Parameters: FILE_PATH`<br>
`Example: path or p dropbox`<br>
No change to scheduler.<br>

* Input: `path data/newpath` or `p data/newpath`<br>
Expected Output:<br>
Prompt: `File Path to save changed to: data/newpath.xml`<br>
Saving file path changed. Does not autosave after execution. Requires a scheduler-modifying command to save to new location. Check `data` folder, there is no `newpath.xml`.<br>

* Input: `a 2`<br>
Expected Output:<br>
`newpath.xml` file can not be found in `data` folder.<br>

#### Clear
* Input: `clear` or `c`<br>
Click `Cancel`<br>
Expected Output:<br>
Prompt: *Clears, nothing*<br>
No change to scheduler.<br>

* Input: `clear` or `c`<br>
Hit `Enter` or Click `OK`<br>
Expected Output:<br>
Prompt: `Scheduler has been cleared!`<br>
Scheduler is cleared. No entries.<br>

* Input: `undo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler is reverted back to previous state, with all the entries inside.<br>

* Input: `redo`<br>
Expected Output:<br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverts last undo. Scheduler is cleared of entries.<br>

#### Exit
* Input: `exit` or `ex`<br>
Expected Output:<br>
Application closes without prompt or messages.<br>
