import tkinter as tk
from tkinter import messagebox, ttk

class ToDoListApp:
    def __init__(self, root):
        self.root = root
        self.root.title("To-Do List")  # Set the window title
        self.root.geometry("450x450")  # Set the window size
        self.root.configure(bg="#F5F5F5")  # Set background color

        # Create a list to store tasks
        self.tasks = []

        # Create GUI components
        self.create_widgets()

    def create_widgets(self):
        # Heading Label
        heading_label = tk.Label(
            self.root,
            text="My To-Do List",
            font=("Helvetica", 20, "bold"),
            bg="#F5F5F5",
            fg="#333333"
        )
        heading_label.pack(pady=15)

        # Entry Field for Adding Tasks
        self.task_entry = tk.Entry(
            self.root,
            font=("Helvetica", 14),
            bg="#FFFFFF",
            fg="#333333",
            relief=tk.FLAT,
            width=30
        )
        self.task_entry.pack(pady=10, padx=20, fill=tk.X)

        # Add Task Button
        add_button = tk.Button(
            self.root,
            text="Add Task",
            font=("Helvetica", 12),
            bg="#4CAF50",
            fg="#FFFFFF",
            relief=tk.FLAT,
            command=self.add_task
        )
        add_button.pack(pady=5, padx=20, fill=tk.X)

        # Task List Display (Treeview for better styling)
        self.task_listbox = ttk.Treeview(
            self.root,
            columns=("Tasks"),
            show="headings",
            selectmode="browse",
            height=10
        )
        self.task_listbox.heading("Tasks", text="Tasks", anchor=tk.W)
        self.task_listbox.column("Tasks", stretch=tk.YES, width=400)
        self.task_listbox.pack(pady=10, padx=20, fill=tk.BOTH, expand=True)

        # Style for Treeview
        style = ttk.Style()
        style.theme_use("default")
        style.configure("Treeview",
                        background="#FFFFFF",
                        foreground="#333333",
                        fieldbackground="#FFFFFF",
                        font=("Helvetica", 12),
                        rowheight=25)
        style.map("Treeview", background=[("selected", "#E0E0E0")])

        # Delete Task Button
        delete_button = tk.Button(
            self.root,
            text="Delete Selected Task",
            font=("Helvetica", 12),
            bg="#FF5252",
            fg="#FFFFFF",
            relief=tk.FLAT,
            command=self.delete_task
        )
        delete_button.pack(pady=10, padx=20, fill=tk.X)

    def add_task(self):
        # Get the task from the entry field
        task = self.task_entry.get().strip()

        if task:  # Check if the task is not empty
            # Capitalize the first letter of the task
            task = task[0].upper() + task[1:]

            # Add the task to the list and Treeview
            self.tasks.append(task)
            self.task_listbox.insert("", tk.END, values=(f"• {task}",))

            # Clear the entry field
            self.task_entry.delete(0, tk.END)
        else:
            # Show a warning if the task is empty
            messagebox.showwarning("Empty Task", "Please enter a task!")

    def delete_task(self):
        # Get the selected task
        selected_item = self.task_listbox.selection()

        if selected_item:  # Check if a task is selected
            # Remove the task from the list and Treeview
            self.task_listbox.delete(selected_item)
            self.tasks.pop(self.task_listbox.index(selected_item))
        else:
            # Show a warning if no task is selected
            messagebox.showwarning("No Task Selected", "Please select a task to delete!")


# Main function to run the application
if __name__ == "__main__":
    root = tk.Tk()  # Create the main window
    app = ToDoListApp(root)  # Initialize the application
    root.mainloop()  # Run the application