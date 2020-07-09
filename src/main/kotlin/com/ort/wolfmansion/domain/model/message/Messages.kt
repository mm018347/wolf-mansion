package com.ort.wolfmansion.domain.model.message


data class Messages(
    val list: List<Message>,
    val allRecordCount: Int? = null,
    val allPageCount: Int? = null,
    val isExistPrePage: Boolean? = null,
    val isExistNextPage: Boolean? = null,
    val currentPageNum: Int? = null
) {
    fun add(message: Message): Messages = this.copy(list = list + message)

    fun existsDifference(messages: Messages): Boolean {
        return list.size != messages.list.size
    }

    fun pageNumList(): List<Int>? {
        val allPageCount = allPageCount ?: return null
        val currentPageNumber = currentPageNum ?: return null
        var startPage = currentPageNumber - 2
        var endPage = currentPageNumber + 2
        if (startPage < 1) {
            startPage = 1
            endPage = 5
        }
        if (endPage > allPageCount) {
            endPage = allPageCount
            startPage = allPageCount - 4
            if (startPage < 1) {
                startPage = 1
            }
        }
        return (startPage..endPage).toList()
    }
}