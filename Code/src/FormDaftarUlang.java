import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormDaftarUlang extends JFrame {

    JTextField txtNama = new JTextField();
    JTextField txtLahir = new JTextField();
    JTextField txtNomor = new JTextField();
    JTextField txtTelp = new JTextField();
    JTextArea txtAlamat = new JTextArea(3, 20);
    JTextField txtEmail = new JTextField();

    public FormDaftarUlang() {
        setTitle("Form Mahasiswa");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // styling
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);

        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        );

        // Apply style
        JTextField[] fields = {txtNama, txtLahir, txtNomor, txtTelp, txtEmail};
        for (JTextField tf : fields) {
            tf.setFont(fontInput);
            tf.setBorder(border);
        }

        txtAlamat.setFont(fontInput);
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        txtAlamat.setBorder(border);

        // title
        JLabel title = new JLabel("Form Pendaftaran Mahasiswa", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        // panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addBaris(mainPanel, "Nama Lengkap", txtNama, gbc, 0, fontLabel);
        addBaris(mainPanel, "Tanggal Lahir", txtLahir, gbc, 1, fontLabel);
        addBaris(mainPanel, "Nomor Pendaftaran", txtNomor, gbc, 2, fontLabel);
        addBaris(mainPanel, "No. Telp", txtTelp, gbc, 3, fontLabel);

        // alamat(beda sendiri dia)
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setFont(fontLabel);
        mainPanel.add(lblAlamat, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        mainPanel.add(txtAlamat, gbc);

        addBaris(mainPanel, "E-mail", txtEmail, gbc, 5, fontLabel);

        // button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(new Color(66, 133, 244));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 13));

        gbc.gridx = 1; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(btnSubmit, gbc);

        add(title, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // action
        btnSubmit.addActionListener(e -> {
            if (isKosong()) {
                JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!");
                return;
            }

            int yakin = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah data sudah benar?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );

            if (yakin == JOptionPane.YES_OPTION) {
                tampilkanJendelaBaru();
                dispose();
            }
        });
    }

    private boolean isKosong() {
        return txtNama.getText().trim().isEmpty() ||
                txtLahir.getText().trim().isEmpty() ||
                txtNomor.getText().trim().isEmpty() ||
                txtTelp.getText().trim().isEmpty() ||
                txtAlamat.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty();
    }

    private void addBaris(JPanel p, String label, JTextField tf, GridBagConstraints gbc, int y, Font fontLabel) {
        gbc.gridx = 0; gbc.gridy = y;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        JLabel lbl = new JLabel(label);
        lbl.setFont(fontLabel);
        p.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(tf, gbc);
    }

    private void tampilkanJendelaBaru() {
        JFrame frameHasil = new JFrame("Hasil Data");
        frameHasil.setSize(400, 350);
        frameHasil.setLocationRelativeTo(null);
        frameHasil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Data Mahasiswa", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pnl.add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        String isi =
                "Nama              : " + txtNama.getText() + "\n" +
                        "Tanggal Lahir     : " + txtLahir.getText() + "\n" +
                        "No. Pendaftaran   : " + txtNomor.getText() + "\n" +
                        "No. Telp          : " + txtTelp.getText() + "\n" +
                        "Alamat            : " + txtAlamat.getText() + "\n" +
                        "E-mail            : " + txtEmail.getText();

        area.setText(isi);
        pnl.add(area, BorderLayout.CENTER);

        frameHasil.add(pnl);
        frameHasil.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new FormDaftarUlang().setVisible(true);
    }
}