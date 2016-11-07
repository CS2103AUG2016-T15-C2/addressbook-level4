# Manual Testing

## Loading the SampleData.xml
1. Create a `data` folder at the file location where `Scheduler.jar` is located.
2. Copy `SampleData.xml` file to `data` foler.
3. Launch `Scheduler.jar`.
4. Type `path data/SampleData` into the input box, press Enter.
5. Close the application.
6. Relaunch `Scheduler.jar`, the data should now be loaded.<br>

## Manual Testing
#### Add
* Input: `add Meeting st/13:00 et/15:00 sd/11-11-2016 ed/11-11-2016 t/meeting`<br>
Expected Output: <br>
Prompt: `New entry added: Meeting Start Time: 13:00 End Time: 15:00 Date: 11-11-2016 End Date: 11-11-2016 Tags: [meeting]` <br>
New entry added to the bottom of the list with index 53<br>

* Input: `undo`<br>
Expected Output: <br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverted to previous state, Entry 53 is no longer in the scheduler.<br>

* Input: `redo`<br>
Expected Output: <br>
Prompt: *No new prompt, displaying previous*<br>
Scheduler reverted to previous state, Entry 53 is back in the scheduler.<br>

* Input: `add Meeting st/13:00 et/15:00 sd/11-11-2016 ed/11-11-2016 t/meeting`<br>
Expected Output: <br>
Prompt: `This entry already exists in the scheduler`<br>
No change in Scheduler.

* Input: `add Meeting sd/11-11-2016 ed/11-11-2016`<br>
Expected Output:<br>
Prompt: `New entry added: Meeting Start Time: empty End Time: empty Date: 11-11-2016 End Date: 11-11-2016 Tags: `<br>
New entry added to the bottom of the list with index 54. New entry does not display Start Time and End Time.<br>

* Input: `a 
