# Developer Guide 

* [Setting Up](#setting-up)
* [Design](#design)
* [Implementation](#implementation)
* [Testing](#testing)
* [Dev Ops](#dev-ops)
* [Appendix A: User Stories](#appendix-a--user-stories)
* [Appendix B: Use Cases](#appendix-b--use-cases)
* [Appendix C: Non Functional Requirements](#appendix-c--non-functional-requirements)
* [Appendix D: Glossary](#appendix-d--glossary)
* [Appendix E : Product Survey](#appendix-e-product-survey)


## Setting up

#### Prerequisites

1. **JDK `1.8.0_60`**  or later<br>

    > Having any Java 8 version is not enough. <br>
    This app will not work with earlier versions of Java 8.
    
2. **Eclipse** IDE
3. **e(fx)clipse** plugin for Eclipse (Do the steps 2 onwards given in
   [this page](http://www.eclipse.org/efxclipse/install.html#for-the-ambitious))
4. **Buildship Gradle Integration** plugin from the Eclipse Marketplace


#### Importing the project into Eclipse

0. Fork this repo, and clone the fork to your computer
1. Open Eclipse (Note: Ensure you have installed the **e(fx)clipse** and **buildship** plugins as given 
   in the prerequisites above)
2. Click `File` > `Import`
3. Click `Gradle` > `Gradle Project` > `Next` > `Next`
4. Click `Browse`, then locate the project's directory
5. Click `Finish`

  > * If you are asked whether to 'keep' or 'overwrite' config files, choose to 'keep'.
  > * Depending on your connection speed and server load, it can even take up to 30 minutes for the set up to finish
      (This is because Gradle downloads library files from servers during the project set up process)
  > * If Eclipse auto-changed any settings files during the import process, you can discard those changes.

## Design

### Architecture

<img src="images/Architecture.png" width="600"><br>
The **_Architecture Diagram_** given above explains the high-level design of the App.
Given below is a quick overview of each component.

`Main` has only one class called [`MainApp`](../src/main/java/seedu/scheduler/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connect them up with each other.
* At shut down: Shuts down the components and invoke cleanup method where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.
Two of those classes play important roles at the architecture level.
* `EventsCentre` : This class (written using [Google's Event Bus library](https://github.com/google/guava/wiki/EventBusExplained))
  is used by components to communicate with other components using events (i.e. a form of _Event Driven_ design)
* `LogsCenter` : Used by many classes to write log messages to the App's log file.

The rest of the App consists four components.
* [**`UI`**](#ui-component) : The UI of the App.
* [**`Logic`**](#logic-component) : The command executor.
* [**`Model`**](#model-component) : Holds the data of the App in-memory.
* [**`Storage`**](#storage-component) : Reads data from, and writes data to, the hard disk.

Each of the four components
* Defines its _API_ in an `interface` with the same name as the Component.
* Exposes its functionality using a `{Component Name}Manager` class.

For example, the `Logic` component (see the class diagram given below) defines it's API in the `Logic.java`
interface and exposes its functionality using the `LogicManager.java` class.<br>
<img src="images/LogicClassDiagram.png" width="800"><br>

The _Sequence Diagram_ below shows how the components interact for the scenario where the user issues the
command `delete 3`.
<!-- @@author A0139956L -->
<img src="images\SDforDeleteEntry.JPG" width="800">

>Note how the `Model` simply raises a `SchedulerChangedEvent` when the Scheduler data are changed,
 instead of asking the `Storage` to save the updates to the hard disk.

The diagram below shows how the `EventsCenter` reacts to that event, which eventually results in the updates
being saved to the hard disk and the status bar of the UI being updated to reflect the 'Last Updated' time. <br>
<img src="images\SDforDeleteEntryEventHandling.JPG" width="800">
<!-- @@author -->
> Note how the event is propagated through the `EventsCenter` to the `Storage` and `UI` without `Model` having
  to be coupled to either of them. This is an example of how this Event Driven approach helps us reduce direct 
  coupling between components.

The sections below give more details of each component.

### UI component
<!-- @@author A0139956L -->
<img src="images/UiClassDiagram.JPG" width="800"><br>

**API** : [`Ui.java`](../src/main/java/seedu/scheduler/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `EntryListPanel`,
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class
and they can be loaded using the `UiPartLoader`.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files
 that are in the `src/main/resources/view` folder.<br>
 For example, the layout of the [`MainWindow`](../src/main/java/seedu/scheduler/ui/MainWindow.java) is specified in
 [`MainWindow.fxml`](../src/main/resources/view/MainWindow.fxml)
<!-- @@author -->
The `UI` component,
* Executes user commands using the `Logic` component.
* Binds itself to some data in the `Model` so that the UI can auto-update when data in the `Model` change.
* Responds to events raised from various parts of the App and updates the UI accordingly.

### Logic component

<img src="images/LogicClassDiagram.png" width="800"><br>
<!-- @@author A0139956L -->
**API** : [`Logic.java`](../src/main/java/seedu/scheduler/logic/Logic.java)

1. `Logic` uses the `Parser` class to parse the user command.
2. This results in a `Command` object goes through the `LogicManager`, while `UndoableCommand` object goes through `UndoManager` after being executing a valid command.
3. The command execution can affect the `Model` (e.g. adding an entry) and/or raise events.
4. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")`
 API call.<br>
<img src="images/DeletePersonSdForLogic.JPG" width="800"><br>
<!-- @@author -->
### Model component
<!-- @@author A0139956L -->
<img src="images/ModelClassDiagram.JPG" width="800"><br>

**API** : [`Model.java`](../src/main/java/seedu/scheduler/model/Model.java)

The `Model`,
* stores a `UserPref` object that represents the user's preferences.
* stores the Scheduler data.
* exposes a `UnmodifiableObservableList<ReadOnlyEntry>` that can be 'observed' e.g. the UI can be bound to this list
  so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.
<!-- @@author -->
### Storage component
<!-- @@author A0139956L -->
<img src="images/StorageClassDiagram.JPG" width="800"><br>

**API** : [`Storage.java`](../src/main/java/seedu/scheduler/storage/Storage.java)
<!-- @@author -->
The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the Scheduler data in xml format and read it back.

### Common classes
<!-- @@author A0139956L -->
Classes used by multiple components are in the `seedu.scheduler.commons` package.
<!-- @@author -->
## Implementation

### Logging

We are using `java.util.logging` package for logging. The `LogsCenter` class is used to manage the logging levels
and logging destinations.

* The logging level can be controlled using the `logLevel` setting in the configuration file
  (See [Configuration](#configuration))
* The `Logger` for a class can be obtained using `LogsCenter.getLogger(Class)` which will log messages according to
  the specified logging level
* Currently log messages are output through: `Console` and to a `.log` file.

**Logging Levels**

* `SEVERE` : Critical problem detected which may possibly cause the termination of the application
* `WARNING` : Can continue, but with caution
* `INFO` : Information showing the noteworthy actions by the App
* `FINE` : Details that is not usually noteworthy but may be useful in debugging
  e.g. print the actual list instead of just its size

### Configuration

Certain properties of the application can be controlled (e.g App name, logging level) through the configuration file 
(default: `config.json`):


## Testing

Tests can be found in the `./src/test/java` folder.

**In Eclipse**:
> If you are not using a recent Eclipse version (i.e. _Neon_ or later), enable assertions in JUnit tests
  as described [here](http://stackoverflow.com/questions/2522897/eclipse-junit-ea-vm-option).

* To run all tests, right-click on the `src/test/java` folder and choose
  `Run as` > `JUnit Test`
* To run a subset of tests, you can right-click on a test package, test class, or a test and choose
  to run as a JUnit test.

**Using Gradle**:
* See [UsingGradle.md](UsingGradle.md) for how to run tests using Gradle.

We have two types of tests:

1. **GUI Tests** - These are _System Tests_ that test the entire App by simulating user actions on the GUI. 
   These are in the `guitests` package.
<!-- @@author A0139956L -->  
2. **Non-GUI Tests** - These are tests not involving the GUI. They include,
   1. _Unit tests_ targeting the lowest level methods/classes. <br>
      e.g. `seedu.scheduler.commons.UrlUtilTest`
   2. _Integration tests_ that are checking the integration of multiple code units 
     (those code units are assumed to be working).<br>
      e.g. `seedu.scheduler.storage.StorageManagerTest`
   3. Hybrids of unit and integration tests. These test are checking multiple code units as well as 
      how the are connected together.<br>
      e.g. `seedu.scheduler.logic.LogicManagerTest`
<!-- @@author -->  
**Headless GUI Testing** :
Thanks to the [TestFX](https://github.com/TestFX/TestFX) library we use,
 our GUI tests can be run in the _headless_ mode. 
 In the headless mode, GUI tests do not show up on the screen.
 That means the developer can do other things on the Computer while the tests are running.<br>
 See [UsingGradle.md](UsingGradle.md#running-tests) to learn how to run tests in headless mode.
  
## Dev Ops

### Build Automation

See [UsingGradle.md](UsingGradle.md) to learn how to use Gradle for build automation.

### Continuous Integration

We use [Travis CI](https://travis-ci.org/) to perform _Continuous Integration_ on our projects.
See [UsingTravis.md](UsingTravis.md) for more details.

### Making a Release

Here are the steps to create a new release.
 
 1. Generate a JAR file [using Gradle](UsingGradle.md#creating-the-jar-file).
 2. Tag the repo with the version number. e.g. `v0.1`
 2. [Crete a new release using GitHub](https://help.github.com/articles/creating-releases/) 
    and upload the JAR file your created.
   
### Managing Dependencies
<!-- @@author A0139956L -->
A project often depends on third-party libraries. For example, Scheduler depends on the
[Jackson library](http://wiki.fasterxml.com/JacksonHome) for XML parsing. Managing these _dependencies_
can be automated using Gradle. For example, Gradle can download the dependencies automatically, which
is better than these alternatives.<br>
a. Include those libraries in the repo (this bloats the repo size)<br>
b. Require developers to download those libraries manually (this creates extra work for developers)<br>
<!-- @@author -->
## Appendix A : User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have)  - `* *`,  Low (unlikely to have) - `*`

<!-- @@author A0139956L -->
Priority | As a ... | I want to ... | So that I can...
-------- | :-------- | :--------- | :-----------
`* * *` | new user | see usage instructions | refer to instructions when I forget how to use the App
`* * *` | user | add an event, a task and a floating task | so that I can record tasks that need to be done some day
`* * *` | user | list all items | view all entries and dates that are due sorted by date and time and know what needs to be done next
`* * *` | user | edit an item | update entries that are outdated
`* * *` | user | delete an item | remove entries that I no longer need
`* * *` | user | mark an item | distinguish completed from uncompleted items
`* * *` | user | undo an operation | have read-consistency and data changes should not be visible to queries that started running before committing them
`* * *` | user | redo an operation | commit to data changes
`* * *` | advanced user | use flexible commands | type a command faster
`* * *` | user | find an item by name | locate details of item without having to go through the entire list
`* * *` | user | path folder | specify a specific folder as the data storage location 
`* *` | user | clear all items | clear all my list in the scheduler 
`*` | user | exit the program | exit the program conveniently
<!-- @@author -->
## Appendix B : Use Cases
<!-- @@author A0139956L -->
(For all use cases below, the **System** is the `Scheduler` and the **Actor** is the `user`, unless specified otherwise)
<!-- @@author -->
<!-- @@author A0126090N -->
#### Use case: Open Help documentation

**MSS**

1. User requests for help documentation
2. Scheduler opens window displaying all program commands and its functionality <br>
Use case ends.

**Extensions**

#### Use case: Add item

**MSS**

1. User calls the add function
2. Scheduler displays added item <br>
Use case ends.

**Extensions**

2a. The user adds an events

> 2a1. Scheduler displays item in a list with start time, end time and date <br>

2b. The user adds a task with a deadline

> 2b1. Scheduler displays item in a list with a date <br>

2c. The user adds a task

> 2c1. Scheduler displays item in a list <br>

> Use case ends

#### Use case: List items

**MSS**

1. User calls the list function
2. Scheduler displays all items as a list <br>
Use case ends.

**Extensions**

2a. The list is empty

> 2a1. Scheduler displays empty list <br>

### Use case: Mark items

**MSS**

1. User selects the targeted entry
2. Scheduler tags the selected entry as completed <br>
Use case ends.

**Extensions**

1a. The list is empty

> 1a1. Scheduler displays empty list <br>

#### Use case: Edit items

**MSS**

1. User requests to list items or find item
2. Scheduler shows a list of items or the item
3. User calls the edit function
4. User types in index of item and inputs new values for the item
5. Scheduler displays updated list of items <br>
Use case ends.

**Extensions**

4a. The item is an event 

> 4a1. The function requires an input “'NAME OF EVENT' 'START TIME' 'END TIME' 'DATE'" <br>

4b. The item is a task with a deadline 

> 4b1. The function requires an input “'NAME OF TASK' 'DATE'” <br>

4c. The item is a floating task

> 4c1. The function requires an input “'NAME OF TASK'” <br>

#### Use case: Delete item

**MSS**

1. User requests to list items or find item
2. Scheduler shows a list of items or the item
3. User requests to delete a specific item in the list based on the index
4. Scheduler deletes the item 
5. Scheduler displays updated list of items <br>
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

5a. The list is empty

> Use case ends

#### Use case: Undo action

**MSS**

1. User requests to undo most recently executed action
2. System reverts back to previous state in history
3. Scheduler displays updated list <br>
Use case ends.

**Extensions**

1a. The scheduler is new and no previous actions were executed

> Use case ends

#### Use case: Redo action

**MSS**

1. User requests to redo most recently executed action
2. System reverts back to previous state in history
3. Scheduler displays updated list <br>
Use case ends.

**Extensions**

1a. The scheduler is new and no previous actions were executed

> Use case ends

#### Use case: Find item

**MSS**

1. User calls the find function
2. Scheduler displays the items that are word related with no case sensitive
 <br>
Use case ends.

**Extensions**

2a. The list is empty

> 2a1. Scheduler displays empty list <br>

<!-- @@author A0139956L -->
#### Use case: Path folder

**MSS**

1. User calls the path function
2. Scheduler creates a folder in specified path <br>
Use case ends.

**Extensions**

2a. The folder does not exist

> 2a1. Scheduler will create a folder <br>
<!-- @@author -->
#### Use case: Clear items

**MSS**

1. User requests to clear items
2. Scheduler deletes all items
3. Scheduler displays empty list <br>
Use case ends.

**Extensions**

1a. The list is empty

> Use case ends

#### Use case: Exit

**MSS**

1. User requests to exit program
2. Scheduler exits program <br>
Use case ends.

## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java `1.8.0_60` or higher installed.
2. Should be able to hold up to 1000 entries.
3. Should come with automated unit tests and open source code.
4. Should favor DOS style commands over Unix-style commands.
5. Synchronization capability to other task management sites (ex: Google calendar).
6. Back-up data that can be used to recover if original copy gets deleted.
7. Able to migrate all items into .txt file.
8. Able to update collaborators of list as changes are made.
9. Able to receover to last working point in case of sudden termination.
10. Able to reproduce history of activities.

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

## Appendix E : Product Survey
<!-- @@author A0152962B -->
#### Google Calendar
* Syncs tasks immediately to your calendar (tasks are in calendar view)
* Syncs with your email (email with date/time will have the option to be added to the calendar
* Option to create multiple calendars (similar to tagging the task)
* Able to add attachments
* Able to display calendar to view tasks after filtering entries (e.g. calendar 4-day view - displays only tasks with deadlines in 4 days)
* Keyboard shortcuts (e.g. c = create event)

#### Evernote
* Option of presentation mode (full screen view of tasks)
* Tracks task details (e.g. location, task creation date, author, etc.)
* Sync on multiple devices (e.g. laptops, mo
* Export and convert lists to other file formats (e.g. PDF)

#### Wunderlist
* Able to create shared lists
* Assign to-dos 
* Subtasks (break down larger tasks)
* Personalisation (background)
* Upload attachments
* Keyboard shortcuts to mark task as completed
* Browser extension (save/add links to task notes)

#### Trello
* Activity feed (i.e. shows history of events)
* Personalisation option
* Able to group and assign tasks
* Option of priority marking and alarm
* Shared board (i.e. many collaborators on a list)
<!-- @@author -->
