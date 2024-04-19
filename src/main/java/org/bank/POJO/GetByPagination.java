package org.bank.POJO;

/**
 * @author Muzamil-M
 */
public class GetByPagination {
    int currentPage, itemsperPage;
    String sortBy, Direction;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsperPage() {
        return itemsperPage;
    }

    public void setItemsperPage(int itemsperPage) {
        this.itemsperPage = itemsperPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    @Override
    public String toString() {
        return "GetByPagination{" +
                "currentPage=" + currentPage +
                ", itemsperPage=" + itemsperPage +
                ", sortBy='" + sortBy + '\'' +
                ", Direction='" + Direction + '\'' +
                "\n"+'}';
    }
}

