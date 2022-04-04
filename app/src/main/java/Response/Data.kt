package Response

data class Data(
    val has_more: Boolean,
    val users: List<User>
)