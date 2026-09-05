# Mini Hospital Emergency Management System

A console-based Java application for CIT300 (Data Structures and Algorithms) that
simulates patient registration, emergency treatment, and visit history tracking
using four core data structures, each implemented manually (no `java.util` collections
used for the core logic).

## Data Structures Used

|Requirement|Data Structure|Files|
|-|-|-|
|Patient Records|Binary Search Tree (keyed by Patient ID)|`Patient.java`, `PatientNode.java`, `PatientBST.java`|
|Emergency Patient Queue|Queue (FIFO, linked-list based)|`EmergencyPatient.java`, `QueueNode.java`, `EmergencyQueue.java`|
|Treatment History|Stack (LIFO, linked-list based)|`TreatmentRecord.java`, `StackNode.java`, `TreatmentStack.java`|
|Patient Visit History|Singly Linked List (one per patient)|`Visit.java`, `VisitNode.java`, `VisitHistory.java`|
|Application entry point|Console menu tying everything together|`HospitalManagementSystem.java`|

## How the pieces fit together

1. **Register** a patient -> stored in the `PatientBST`, keyed by Patient ID.
2. **Add to emergency queue** -> creates an `EmergencyPatient` and enqueues it (FIFO).
3. **Call next patient** -> dequeues the front of the line, records the treatment given,
and pushes a `TreatmentRecord` onto the `TreatmentStack` (LIFO — most recent on top).
4. **Add a visit** -> appends a `Visit` to that specific patient's own `VisitHistory`
linked list (each `Patient` object owns one).

## Project Structure

```
HospitalEMS/
├── README.md
└── src/
    └── hospital/
        ├── Patient.java
        ├── PatientNode.java
        ├── PatientBST.java
        ├── EmergencyPatient.java
        ├── QueueNode.java
        ├── EmergencyQueue.java
        ├── TreatmentRecord.java
        ├── StackNode.java
        ├── TreatmentStack.java
        ├── Visit.java
        ├── VisitNode.java
        ├── VisitHistory.java
        └── HospitalManagementSystem.java
```

## How to Compile and Run

From the `HospitalEMS` directory:

```bash
javac -d out src/hospital/\*.java
java -cp out hospital.HospitalManagementSystem
```

## Sample Usage Flow

1. Choose `1` to register a patient (e.g. ID 101, "Nimal Perera", age 34).
2. Choose `5` to add patient 101 to the emergency queue with a reason.
3. Choose `6` to call the next patient — this dequeues them and lets you record the
treatment given, pushing it onto the treatment history stack.
4. Choose `8` to see the treatment history (most recent on top).
5. Choose `10` to add a past/completed visit to patient 101's visit history.
6. Choose `13` to display that patient's full visit history.

## Complexity Notes

* **BST**: insert/search/delete average `O(log n)`, worst case `O(n)` for a skewed tree.
* **Queue**: enqueue/dequeue are `O(1)` thanks to head + tail pointers.
* **Stack**: push/pop are `O(1)` since the head of the linked list is the top.
* **Linked List**: append is `O(1)` (tail pointer kept); search/remove are `O(n)`.

## Notes for Development / Git Workflow

This project is intentionally split into small, independent classes so it can be
committed progressively, matching the suggested commit history in the assignment brief,
for example:

1. `Created project structure`
2. `Implemented Patient class and PatientBST (insert, search)`
3. `Added BST delete and in-order traversal`
4. `Implemented EmergencyQueue (enqueue/dequeue/display)`
5. `Implemented TreatmentStack (push/pop/display)`
6. `Implemented Visit and VisitHistory linked list`
7. `Wired everything together in HospitalManagementSystem menu`
8. `Added testing / sample data`
9. `Updated README`

Before submitting, compile and run the program yourself, walk through each menu option,
and make sure you can explain — in your own words, for the demo video — how each data
structure's insert/remove/search/traverse operations actually work under the hood.



\## Student submission



Student ID: 23DA2-0507



The program was tested using patient records, emergency queue operations, treatment stack operations, and patient visit history operations. Screenshots of the tested output are included in Screenshots.pdf.



