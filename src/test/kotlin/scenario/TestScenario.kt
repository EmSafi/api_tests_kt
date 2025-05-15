package scenario

import actions.Action

class TestScenario {
    private val actions = mutableListOf<Action>()
    private val cleanupActions = mutableListOf<() -> Unit>()

    fun addAction(action: Action): TestScenario {
        actions.add(action)
        return this
    }

    fun addCleanup(action: () -> Unit): TestScenario {
        cleanupActions.add(action)
        return this
    }

    fun execute() {
        try {
            actions.forEach { it.run() }
        } finally {
            cleanupActions.reversed().forEach { it.invoke() }
        }
    }
}