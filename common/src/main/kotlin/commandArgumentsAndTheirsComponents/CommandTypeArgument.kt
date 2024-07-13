package commandArgumentsAndTheirsComponents

import kotlinx.serialization.Serializable

@Serializable
class CommandTypeArgument(val commandType: CommandType): CommandArgument()