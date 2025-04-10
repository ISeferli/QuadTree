# QuadTree for Search Optimization

This project implements a spatial data structure, **QuadTree**, designed to efficiently store and search points in a 2D space. A quadtree is a tree data structure in which each internal node has exactly four children. 

--- 

## 🎓 Project Info

- **Course**: Data Structure
- **Institution**: Technical University of Crete (TUC)
- **Department**: School of Electrical and Computer Engineering
- **Semester**: Fall 2018
- **Project Type**: Individual assignment
- **Language**: Java
- **Platform**: Eclipse

---

## ✨ Key Features:
- Insertion of points with random values.
- Search operations (both successful and unsuccessful) are tracked for performance evaluation.
- Average number of accesses for successful and unsuccessful search operations.
- Randomly generated points for stress testing the performance of the QuadTree and KD-Tree with various sizes.

---

## 🧱 File Structure

| File Name           | Description                                              |
|---------------------|----------------------------------------------------------|
| **main.java**  |  Entry point for the application. |
| **QuadTree.java**  | Implementation of the QuadTree |
| **QuadNode.java**           | Abstract base class for QuadTree nodes. |
| **QuadInternal.java**         |  Internal node for QuadTree.  |
| **QuadLeaf.java**       | Leaf node for QuadTree     |

---

## 🛠 Build and Run

### Eclipse

During creation, I was using **Eclipse** for development. Follow these steps to build and run the project with this IDE:

1. **Import the Project into Eclipse:**
   - Open Eclipse IDE.
   - Go to `File` -> `Import`.
   - Select `General` -> `Existing Projects into Workspace`, then click `Next`.
   - Browse to the folder containing the project and select it.
   - Click `Finish` to import the project into Eclipse.

2. **Build the Project:**
   - Eclipse will automatically compile the project when you save any file or make changes. There is no need for an explicit compile command.

3. **Run the Program:**
   - To run the program, right-click on the `Main.java` file in the **Project Explorer**.
   - Select `Run As` -> `Java Application`.

### Run in Any Java Environment (including terminal)

You can also run the project outside of an IDE, including directly in the terminal, as long as **Java** is installed.
