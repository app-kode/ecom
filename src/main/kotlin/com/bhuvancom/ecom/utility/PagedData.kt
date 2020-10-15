package com.bhuvancom.ecom.utility

import org.springframework.data.domain.Page

class PagedData {
    companion object {
        fun getPagedData(data: Page<Any>): HashMap<String, Any> {
            val dataMap = HashMap<String, Any>()
            dataMap["data"] = data.content
            dataMap["totalItem"] = data.totalElements
            dataMap["pageNumber"] = data.number + 1
            dataMap["itemPerPage"] = data.size
            dataMap["isFirstPage"] = data.isFirst
            dataMap["isLastPage"] = data.isLast
            return dataMap
        }
    }
}