package com.midea.common.util;

import java.io.Serializable;
import java.util.List;

public class QueryResult<E> implements Serializable {
    private static final long serialVersionUID = 1655794134066769677L;

    private Long total;//总条数
    private Long totalpage;//总条数
    private List<E> resultList;
    public QueryResult(List<E> resultList, Long totalCount, Long totalpage) {
        this.total = totalCount;
        this.totalpage = totalpage;
        this.resultList = resultList;
    }

    public QueryResult() {

    }

    public List<E> getResultList() {
        return resultList;
    }

    public void setResultList(List<E> resultList) {
        this.resultList = resultList;
    }

    public Long getTotalCount() {
        return total;
    }

    public void setTotalCount(Long totalCount) {
        this.total = totalCount;
    }

    public Long getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Long totalpage) {
        this.totalpage = totalpage;
    }
}
