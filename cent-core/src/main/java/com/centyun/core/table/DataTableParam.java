package com.centyun.core.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centyun.core.constant.AppConstant;

public class DataTableParam {
    private Integer draw;

    private Integer start;

    private Integer length;

    private Map<Search, String> search = new HashMap<Search, String>();

    private List<Map<Order, String>> order = new ArrayList<Map<Order, String>>();

    private List<Map<Column, String>> columns = new ArrayList<Map<Column, String>>();

    public DataTableParam() {
    }

    public enum Search {
        value, regex
    }

    public enum Order {
        column, dir
    }

    public enum Column {
        data, name, searchable, orderable, searchValue, searchRegex
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        if (start == null || start < 1) {
            start = AppConstant.DEFAULT_PAGE_START;
        }
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        if (length == null || length < 1) {
            length = AppConstant.DEFAULT_PAGE_SIZE;
        }
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Map<Search, String> getSearch() {
        return search;
    }

    public void setSearch(Map<Search, String> search) {
        this.search = search;
    }

    public List<Map<Order, String>> getOrder() {
        return order;
    }

    public void setOrder(List<Map<Order, String>> order) {
        this.order = order;
    }

    public List<Map<Column, String>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<Column, String>> columns) {
        this.columns = columns;
    }

    public List<KeyValuePair> getOrders() {
        if (columns == null || columns.size() == 0)
            return null;

        Map<Integer, String> cols = new HashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            Map<Column, String> map = columns.get(i);
            cols.put(i, map.get(Column.data));
        }

        List<KeyValuePair> result = new ArrayList<>();
        for (Map<Order, String> orders : order) {
            Object col = cols.get(Integer.parseInt(orders.get(Order.column).toString()));
            Object dir = orders.get(Order.dir);
            result.add(new KeyValuePair(toDbField((String) col), dir));
        }
        return result;
    }

    private String toDbField(String value) {
        if (value == null || value.trim().length() == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char a = value.charAt(i);
            sb.append(Character.isUpperCase(a) ? "_" + Character.toLowerCase(a) : a);
        }
        String field = sb.toString();
        if (field.equals("tenant_name")) {
            return "t.name";
        } else if (field.equals("product_name")) {
            return "p.name";
        } else if (field.equals("charge_manager_name")) {
            return "m1.display_name";
        } else if (field.equals("ip_str")) {
            return "inet_ntoa(a.ip)";
        }/* else if (field.equals("type")) {
            return "a.type";
        } else if (field.equals("mobile")) {
            return "a.mobile";
        } else if (field.equals("note")) {
            return "a.note";
        } else if (field.equals("status")) {
            return "a.status";
        }*/
        return "a." + sb.toString();
    }

    public String getSearchValue() {
        return getSearch().get(Search.value);
    }
}
