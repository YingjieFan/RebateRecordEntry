/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataentrymain;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author YingjieFan
 */
public class RecordTableModel extends AbstractTableModel{
    private final static int NAME_COL = 0;
    private final static int PHONE_COL = 1;
    private String[] columnNames = {"Name","Phone"};
    private List<Record> recordList;

    public RecordTableModel(List<Record> recordList) {
        this.recordList = new ArrayList<>();
        this.recordList = recordList;
    }
    
    @Override
    public int getRowCount() {
        
        return recordList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
        }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record tempRecord = recordList.get(rowIndex);
        switch(columnIndex){
            case NAME_COL:
                String temp = "";
                temp=temp+tempRecord.getFirstName()+" "+tempRecord.getLastName();
                return temp;
            case PHONE_COL:
                return tempRecord.getPhone();
            default:
                return "Null";
        }
    }

    @Override
    public String getColumnName(int column) {
        
        return columnNames[column];
    }
    
}
