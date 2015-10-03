/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataentrymain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Kent
 * This class is the Jframe class that host the interface. It maintains some interface related data and methods.
 */
public class Interface extends javax.swing.JFrame{
    private List<Record> records = new ArrayList<>();
    private RecordTableModel tableModel;
    private int selectedRowIndex=-1;
    Date date;
    FileOperation fileOp;
    String modifiedDate;
    /**
     * Creates new form Interface
     */
    public Interface() {
        fileOp = new FileOperation();
        this.records = fileOp.getAllRecords();

        tableModel = new RecordTableModel(records);
        initComponents();
        
        //Add a window listener that warns user about unsaved entry when exit
    	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	this.addWindowListener(
    			new WindowAdapter( )
    	        {
    	            public void windowClosing (WindowEvent e)
    	            {
    	            	
    	            	//Save the changes to file 
    	            	fileOp.saveFile(records);
    	                String message = "Quit? (Unsaved entries will be lost)";
    	                String title = "Warning";
    	                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    	                if (reply == JOptionPane.YES_OPTION)
    	                {
    	                    System.exit(0);
    	                }

    	            }
    	        }
    			);
        tableRecords.setModel(tableModel);
        tableRecords.setCellSelectionEnabled(false);
        tableRecords.setRowSelectionAllowed(true);
        /*tableRecords.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				selectedRowIndex=tableRecords.getSelectedRow();
				if(selectedRowIndex==-1){
					clearAllFields();
				}else{
					Font font = new Font("Verdana", Font.BOLD, 12);
			        textAreaInstruction.setFont(font);
			        textAreaInstruction.setForeground(Color.BLACK);
					textAreaInstruction.setText("Click 'Edit' button to save modified record");
					populateFields(selectedRowIndex);
				}
				
			}
        	
        });*/
        //Change the action when hitting enter to focus on First Name field so user can start editing
        tableRecords.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        tableRecords.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            	fieldFN.requestFocus();
            }
        });
        
        //Change the behavior of TAB in jTable so user can switch focus to fields
        tableRecords.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Tab");
        tableRecords.getActionMap().put("Tab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            	fieldFN.requestFocus();
            }
        });
        //Enable to toggle the check box using Enter
        jCheckBoxProof.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        jCheckBoxProof.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            	if(jCheckBoxProof.isSelected()){
            		jCheckBoxProof.setSelected(false);
            	}else{
            		jCheckBoxProof.setSelected(true);
            	}
            }
        });
        
        //Enable the pressing of each button using Enter
        buttonAdd.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        buttonAdd.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            	buttonAdd.doClick();
            }
        });
        
        buttonEdit.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        buttonEdit.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            	buttonEdit.doClick();
            }
        });
        
        buttonDelete.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        buttonDelete.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            	buttonDelete.doClick();
            }
        });
        
        buttonSave.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        buttonSave.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //do something on JTable enter pressed
            	buttonSave.doClick();
            }
        });
        
        tableRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	selectedRowIndex=tableRecords.getSelectedRow();
				if(selectedRowIndex==-1){
					clearAllFields();
				}else{
					//Font font = new Font("Verdana", Font.BOLD, 12);
			        /*textAreaInstruction.setFont(font);
			        textAreaInstruction.setForeground(Color.BLACK);
					*/
					textAreaInstruction.setText("Click 'Edit' button to save modified record");
					populateFields(selectedRowIndex);
				}
            	
            	/*System.out.println("mouseClicked");
                int row = tableRecords.rowAtPoint(evt.getPoint());
                if (row<0) {
                	selectedRowIndex=-1;
                   clearAllFields();
                }*/
            }
        });
        
        
        //Get system time and fill in the textfield as default
        date = new Date();
		modifiedDate= new SimpleDateFormat("MM/dd/yyyy").format(date);
        fieldDate.setText(modifiedDate);
        textAreaInstruction.setEditable(false);
        Font font = new Font("Verdana", Font.BOLD, 12);
        textAreaInstruction.setFont(font);
        textAreaInstruction.setForeground(Color.RED);
    }
    private void populateFields(int index){
    	Record tmp = records.get(index);
    	fieldFN.setText(tmp.getFirstName());
    	fieldLN.setText(tmp.getLastName());
    	fieldMI.setText(tmp.getMI());
    	fieldAdd1.setText(tmp.getAddressLine1());
    	fieldAdd2.setText(tmp.getAddressLine2());
    	fieldCity.setText(tmp.getCity());
    	fieldState.setText(tmp.getState());
    	fieldZip.setText(tmp.getZipcode());
    	fieldPhone.setText(tmp.getPhone());
    	fieldEml.setText(tmp.getEmail());
    	jCheckBoxProof.setSelected(tmp.isProofAttached());
    	fieldDate.setText(tmp.getDate());
    	
    }
    private void clearAllFields(){
    	fieldFN.setText("");
    	fieldLN.setText("");
    	fieldMI.setText("");
    	fieldAdd1.setText("");
    	fieldAdd2.setText("");
    	fieldCity.setText("");
    	fieldState.setText("");
    	fieldZip.setText("");
    	fieldPhone.setText("");
    	fieldEml.setText("");
    	jCheckBoxProof.setSelected(true);
    	fieldDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(date));
    	
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelFN = new javax.swing.JLabel();
        fieldFN = new javax.swing.JTextField();
        labelLN = new javax.swing.JLabel();
        fieldLN = new javax.swing.JTextField();
        labelMI = new javax.swing.JLabel();
        fieldMI = new javax.swing.JTextField();
        labelAdd1 = new javax.swing.JLabel();
        fieldAdd1 = new javax.swing.JTextField();
        labelAdd2 = new javax.swing.JLabel();
        fieldAdd2 = new javax.swing.JTextField();
        labelCity = new javax.swing.JLabel();
        fieldCity = new javax.swing.JTextField();
        labelState = new javax.swing.JLabel();
        fieldState = new javax.swing.JTextField();
        labelZip = new javax.swing.JLabel();
        fieldZip = new javax.swing.JTextField();
        buttonAdd = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaInstruction = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        labelPhone = new javax.swing.JLabel();
        fieldPhone = new javax.swing.JTextField();
        labelEml = new javax.swing.JLabel();
        fieldEml = new javax.swing.JTextField();
        labelProof = new javax.swing.JLabel();
        jCheckBoxProof = new javax.swing.JCheckBox();
        labelDate = new javax.swing.JLabel();
        fieldDate = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRecords = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        labelFN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelFN.setText("First Name*");

        fieldFN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldFN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFNActionPerformed(evt);
            }
        });

        labelLN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelLN.setText("Last Name*");

        fieldLN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldLN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldLNActionPerformed(evt);
            }
        });

        labelMI.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMI.setText("M.I.");

        fieldMI.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldMIActionPerformed(evt);
            }
        });

        labelAdd1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelAdd1.setText("Address Line 1*");

        fieldAdd1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAdd1ActionPerformed(evt);
            }
        });

        labelAdd2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelAdd2.setText("Address Line 2");

        fieldAdd2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAdd2ActionPerformed(evt);
            }
        });

        labelCity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelCity.setText("City*");

        fieldCity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCityActionPerformed(evt);
            }
        });

        labelState.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelState.setText("State*");

        fieldState.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldStateActionPerformed(evt);
            }
        });

        labelZip.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelZip.setText("Zip Code*");

        fieldZip.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldZipActionPerformed(evt);
            }
        });

        buttonAdd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonEdit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buttonSave.setText("Save & Exit");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        textAreaInstruction.setColumns(20);
        textAreaInstruction.setRows(5);
        jScrollPane2.setViewportView(textAreaInstruction);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Fields with asterisk (*) must be filled");

        labelPhone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelPhone.setText("Phone*");

        fieldPhone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPhoneActionPerformed(evt);
            }
        });

        labelEml.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelEml.setText("Email*");

        fieldEml.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldEml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEmlActionPerformed(evt);
            }
        });

        labelProof.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelProof.setText("Proof Attached*");

        jCheckBoxProof.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBoxProof.setSelected(true);
        jCheckBoxProof.setText("Yes");
        jCheckBoxProof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProofActionPerformed(evt);
            }
        });

        labelDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelDate.setText("Date Received*");

        fieldDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fieldDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDateActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("(mm/dd/yyyy)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelProof)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxProof))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelPhone)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelEml)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldEml))
                        .addComponent(jScrollPane2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelFN)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldFN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelLN)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldLN, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelMI)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldMI, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelCity)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelState)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldState, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelZip)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(fieldZip))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelAdd1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldAdd1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelAdd2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(fieldAdd2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFN)
                    .addComponent(fieldFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLN)
                    .addComponent(fieldLN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMI)
                    .addComponent(fieldMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAdd1)
                    .addComponent(fieldAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAdd2)
                    .addComponent(fieldAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCity)
                    .addComponent(fieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelState)
                    .addComponent(fieldState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelZip)
                    .addComponent(fieldZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPhone)
                    .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEml)
                    .addComponent(fieldEml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProof)
                    .addComponent(jCheckBoxProof))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDate)
                    .addComponent(fieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd)
                    .addComponent(buttonEdit)
                    .addComponent(buttonDelete)
                    .addComponent(buttonSave))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)

                //.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tableRecords.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Phone"
            }
        ));
        tableRecords.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(tableRecords);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldFNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFNActionPerformed
        // TODO add your handling code here:
        System.out.println("FN field Changed");
    }//GEN-LAST:event_fieldFNActionPerformed

    private void fieldAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAdd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAdd2ActionPerformed

    private void fieldLNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldLNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldLNActionPerformed

    private void fieldAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAdd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAdd1ActionPerformed

    private void fieldPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPhoneActionPerformed

    private void fieldEmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEmlActionPerformed

    private void fieldMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldMIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldMIActionPerformed

    private void fieldCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCityActionPerformed

    private void fieldStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldStateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldStateActionPerformed

    private void fieldZipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldZipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldZipActionPerformed

    private void jCheckBoxProofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxProofActionPerformed

    private void fieldDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDateActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        // TODO add your handling code here:
        System.out.println("Add button pressed");
        
        Record tmpRecord = new Record();
        tmpRecord.setFirstName(fieldFN.getText());
        tmpRecord.setLastName(fieldLN.getText());
        tmpRecord.setMI(fieldMI.getText());
        tmpRecord.setAddressLine1(fieldAdd1.getText());
        tmpRecord.setAddressLine2(fieldAdd2.getText());
        tmpRecord.setCity(fieldCity.getText());
        tmpRecord.setState(fieldState.getText());
        tmpRecord.setZipcode(fieldZip.getText());
        tmpRecord.setPhone(fieldPhone.getText());
        tmpRecord.setEmail(fieldEml.getText());
        tmpRecord.setProofAttached(jCheckBoxProof.isSelected());
        tmpRecord.setDate(fieldDate.getText());
        Utility utils = new Utility(tmpRecord);
        int[] fieldInformation = utils.getFieldInformation();
        Boolean canAdd = true;
        for(int i:fieldInformation){
        	if(i==0){
        		canAdd = false;
        	}
        }
        if(canAdd&&!utils.checkDupRecord(records, tmpRecord)){
            System.out.println("Record can be add");
            tmpRecord = utils.getFormattedRec();
        	records.add(tmpRecord);
            tableModel.fireTableDataChanged();
            textAreaInstruction.setText("Record saved successfully");
            clearAllFields();
            fieldFN.requestFocus();
            System.out.println("Record added");
        }else if(!canAdd&&!utils.checkDupRecord(records, tmpRecord)){
        	
        	System.out.println("Cannot be add");
        	displayErr(fieldInformation);
        }else if(canAdd&&utils.checkDupRecord(records, tmpRecord)){
        	textAreaInstruction.setText("Cannot add record with same First Name & Last Name & MI");
        }
        
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
    	int rowIndex = tableRecords.getSelectedRow();
    	Record tmpRecord = new Record();
    	tmpRecord.setFirstName(fieldFN.getText());
        tmpRecord.setLastName(fieldLN.getText());
        tmpRecord.setMI(fieldMI.getText());
        tmpRecord.setAddressLine1(fieldAdd1.getText());
        tmpRecord.setAddressLine2(fieldAdd2.getText());
        tmpRecord.setCity(fieldCity.getText());
        tmpRecord.setState(fieldState.getText());
        tmpRecord.setZipcode(fieldZip.getText());
        tmpRecord.setPhone(fieldPhone.getText());
        tmpRecord.setEmail(fieldEml.getText());
        tmpRecord.setProofAttached(jCheckBoxProof.isSelected());
        tmpRecord.setDate(fieldDate.getText());
   	Utility utils = new Utility(tmpRecord);
    	int[] fieldInformation = utils.getFieldInformation();
    	for(int i:fieldInformation){
        	if(i==0){
            	displayErr(fieldInformation);
            	return;
        	}
        }
    	Record modifiedRec = utils.getFormattedRec();
    	records.set(rowIndex, modifiedRec);
    	textAreaInstruction.setText("Modified record saved");;
    	clearAllFields();
    	fieldFN.requestFocus();
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
    	int rowIndex = tableRecords.getSelectedRow();
    	if(rowIndex==-1){
    		textAreaInstruction.setText("Please select a record first");
    	}else{
    		records.remove(rowIndex);
            tableModel.fireTableDataChanged();
            fieldFN.requestFocus();
    	}
    	

        // TODO add your handling code here:
    	
    	
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        // TODO add your handling code here:
    	fileOp.saveFile(records);
    	//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	System.exit(0);
    }//GEN-LAST:event_buttonSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.out.println("Yay");
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });

    }
    
    private void displayErr(int[] fieldInformation){
    	textAreaInstruction.setText("");
    	for(int i=0;i<fieldInformation.length;i++){
    		if(fieldInformation[i]==0){
   
    			textAreaInstruction.append(Utility.errorMessageList[i]+"\n");
    		}
    	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonSave;
    private javax.swing.JTextField fieldAdd1;
    private javax.swing.JTextField fieldAdd2;
    private javax.swing.JTextField fieldCity;
    private javax.swing.JTextField fieldDate;
    private javax.swing.JTextField fieldEml;
    private javax.swing.JTextField fieldFN;
    private javax.swing.JTextField fieldLN;
    private javax.swing.JTextField fieldMI;
    private javax.swing.JTextField fieldPhone;
    private javax.swing.JTextField fieldState;
    private javax.swing.JTextField fieldZip;
    private javax.swing.JCheckBox jCheckBoxProof;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAdd1;
    private javax.swing.JLabel labelAdd2;
    private javax.swing.JLabel labelCity;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelEml;
    private javax.swing.JLabel labelFN;
    private javax.swing.JLabel labelLN;
    private javax.swing.JLabel labelMI;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelProof;
    private javax.swing.JLabel labelState;
    private javax.swing.JLabel labelZip;
    private javax.swing.JTable tableRecords;
    private javax.swing.JTextArea textAreaInstruction;
    // End of variables declaration//GEN-END:variables
	
}
