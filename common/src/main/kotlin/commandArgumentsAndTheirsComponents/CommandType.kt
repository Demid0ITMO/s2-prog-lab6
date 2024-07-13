package commandArgumentsAndTheirsComponents

import kotlinx.serialization.Serializable

@Serializable
enum class CommandType {
    NO_ARG,
    SINGLE_ARG,
    OBJECT_ARG,
    MIXED_ARG
}