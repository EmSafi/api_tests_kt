package actions

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractAction: Action {
    /**
     * Экземпляр класса логгер.
     */
    protected val logger =  LoggerFactory.getLogger(this::class.java) as Logger
}