import java.util.Scanner

data class Expense(val id: Int, val description: String, val amount: Double)

class ExpenseTracker {
    private val expenses = mutableListOf<Expense>()
    private var nextId = 1

    // Function to add an expense
    fun addExpense(description: String, amount: Double) {
        if (amount <= 0) {
            println("Amount should be greater than 0!")
            return
        }
        val expense = Expense(nextId, description, amount)
        expenses.add(expense)
        nextId++
        println("Expense added successfully: $expense")
    }

    // Function to remove an expense by ID
    fun removeExpense(id: Int) {
        val expense = expenses.find { it.id == id }
        if (expense != null) {
            expenses.remove(expense)
            println("Expense removed successfully: $expense")
        } else {
            println("Expense with ID $id not found.")
        }
    }

    // Function to view all expenses
    fun viewExpenses() {
        if (expenses.isEmpty()) {
            println("No expenses recorded.")
        } else {
            println("\n--- Expense List ---")
            expenses.forEach { println("ID: ${it.id}, Description: ${it.description}, Amount: ${it.amount}") }
        }
    }

    // Function to calculate the total expenses
    fun calculateTotal(): Double {
        return expenses.sumOf { it.amount }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val expenseTracker = ExpenseTracker()

    while (true) {
        println(
            """
            |--- Expense Tracker ---
            |1. Add Expense
            |2. Remove Expense
            |3. View Expenses
            |4. View Total Expenses
            |5. Exit
            |Enter your choice:
            """.trimMargin()
        )

        when (scanner.nextInt()) {
            1 -> {
                println("Enter expense description:")
                scanner.nextLine() // consume newline
                val description = scanner.nextLine()
                println("Enter expense amount:")
                val amount = scanner.nextDouble()
                expenseTracker.addExpense(description, amount)
            }

            2 -> {
                println("Enter expense ID to remove:")
                val id = scanner.nextInt()
                expenseTracker.removeExpense(id)
            }

            3 -> expenseTracker.viewExpenses()
            4 -> println("Total Expenses: ${expenseTracker.calculateTotal()}")
            5 -> {
                println("Exiting Expense Tracker. Goodbye!")
                break
            }

            else -> println("Invalid choice. Please try again.")
        }
    }
}
