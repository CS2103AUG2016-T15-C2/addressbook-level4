# A0152962Bunused
###### \UserGuide.md
``` md

<!--#### Updating an item : `update`
Update the item selected by the user. User can also type `u` instead of `update`<br>
Format: `update INDEX [st/START_TIME] [et/END_TIME] [sd/START_DATE] [ed/END_DATE] [t/TAG]`
Format: `u INDEX [st/START_TIME] [et/END_TIME] [sd/START_DATE] [ed/END_DATE] [t/TAG]`<br>
Examples:
* `update 1 st/13:00 et/15:00 sd/11-11-2016`
* `u 5 ed/25-12-2016 t/christmas`<br><br>

> * Update functionality does not allow updating the name of the event.
> * Inputing tags will replace existing tags.
-->

#### Undo recent actions (up to 10): `undo`
Reverts scheduler back to the state before the recent committed actions (up to 10 undoable commands).<br>
Undoable commands include: `add` `delete` `edit` `mark` `clear`<br>
Format: `undo`

> * Undo stack holds up to 10 recent undoable comments.
> * Does not display a text feedback to user.

#### Redo recent undone actions: `redo`
Reverts scheduler back to the state before the recent undone actions.<br>
Format: `redo`

> * Redo stacks clears upon a new valid commmand.
> * Does not display a	text feedback to user.
```
