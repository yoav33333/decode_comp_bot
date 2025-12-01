package org.firstinspires.ftc.teamcode.Util.UtilCommands

import dev.nextftc.core.commands.Command

class LoopingCommand(val command: Command) : Command() {

    override val isDone: Boolean = false

    init {
        setRequirements(command.requirements)
    }

    override fun start() = command.start()

    override fun update() = command.update()

    override fun stop(interrupted: Boolean)
    {
        command.stop(interrupted)
        if(!interrupted) {
            schedule()
        }
    }
}