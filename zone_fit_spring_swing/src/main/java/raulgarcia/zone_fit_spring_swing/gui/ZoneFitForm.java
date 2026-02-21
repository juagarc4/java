package raulgarcia.zone_fit_spring_swing.gui;

import org.springframework.stereotype.Component;
import raulgarcia.zone_fit_spring_swing.model.Client;
import raulgarcia.zone_fit_spring_swing.service.IClientService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static java.lang.System.exit;

@Component
public class ZoneFitForm extends JFrame {
    private JPanel mainPanel;
    private JTable clientsTable;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtMembership;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnExit;
    private final IClientService clientService;
    private DefaultTableModel clientsTableModel;
    private Integer clientId;

    public ZoneFitForm(IClientService clientService) {
        this.clientService = clientService;
        setupForm();
        initListeners();

    }

    private void initListeners() {
        btnSave.addActionListener(e -> saveClient());
        btnExit.addActionListener(e -> exit(0));
        btnClear.addActionListener(e -> clearForm());
        btnDelete.addActionListener(e -> deleteClient());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                txtFirstName.requestFocusInWindow();
            }
        });
        clientsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadSelectedClient();
            }
        });
    }

    private void setupForm() {
        setContentPane(mainPanel);
        setTitle("ZoneFit - Access Control");
        try {
            java.net.URL iconURL = getClass().getResource("/logo.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
            }
        } catch (Exception e) {
            System.err.println("Icon could not be loaded: " + e.getMessage());
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1024, 800));
        pack();
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        String[] headers = {"Id", "First Name", "Last Name", "Membership"};
        this.clientsTableModel = new DefaultTableModel(0, 4) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.clientsTableModel.setColumnIdentifiers(headers);
        this.clientsTable = new JTable(clientsTableModel);
        configureTableAlignment();
        loadClientsData();
    }

    private void loadClientsData() {
        this.clientsTableModel.setRowCount(0);
        List<Client> clients = this.clientService.listClients();
        clients.forEach(client -> {
            Object[] row = {
                    client.getId(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getMembership()
            };
            this.clientsTableModel.addRow(row);
        });
    }

    private void saveClient() {
        if (txtFirstName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First Name is required.", "First Name required", JOptionPane.WARNING_MESSAGE);
            txtMembership.requestFocusInWindow();
            return;
        }

        if (txtMembership.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Membership is required.", "Membership required", JOptionPane.WARNING_MESSAGE);
            txtMembership.requestFocusInWindow();
        }

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        Integer membership = Integer.parseInt(txtMembership.getText());
        Client client = new Client();
        client.setId(this.clientId);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setMembership(membership);
        this.clientService.saveClient(client);
        JOptionPane.showMessageDialog(this, "Client updated successfully.");
        loadClientsData();
        clearForm();
    }

    private void deleteClient() {

        if (this.clientId == null) {
            JOptionPane.showMessageDialog(this, "Please select a client from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "This action cannot be undone. Do you want to continue?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (confirmation == JOptionPane.YES_OPTION) {
            Client client = new Client();
            client.setId(this.clientId);

            this.clientService.deleteClient(client);

            JOptionPane.showMessageDialog(this, "Client deleted successfully.");

            loadClientsData();
            clearForm();
        }

    }

    private void loadSelectedClient() {
        int rowIndex = clientsTable.getSelectedRow();

        if (rowIndex != -1) {
            this.clientId = Integer.parseInt(clientsTable.getModel().getValueAt(rowIndex, 0).toString());
            this.txtFirstName.setText(clientsTable.getModel().getValueAt(rowIndex, 1).toString());
            this.txtLastName.setText(clientsTable.getModel().getValueAt(rowIndex, 2).toString());
            this.txtMembership.setText(clientsTable.getModel().getValueAt(rowIndex, 3).toString());

        }
    }

    private void configureTableAlignment() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        int[] columnsToCenter = {0, 3};

        for (int index : columnsToCenter) {
            this.clientsTable.getColumnModel().getColumn(index).setCellRenderer(centerRenderer);
        }
    }

    private void clearForm() {
        this.clientId = null;
        txtFirstName.setText("");
        txtLastName.setText("");
        txtMembership.setText("");
        this.clientsTable.getSelectionModel().clearSelection();
    }
}
