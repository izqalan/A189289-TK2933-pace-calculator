import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Pace calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(720, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("resources/runner_banner_scale.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel banner = new JLabel(new ImageIcon(myPicture));

        JPanel form_panel = new JPanel();
        form_panel.setBorder(new EmptyBorder(new Insets(50, 50, 5, 50)));
        LayoutManager layout = new BoxLayout(form_panel, BoxLayout.PAGE_AXIS);
        form_panel.setLayout(new GridLayout(5, 4));

        JPanel banner_panel = new JPanel();
//        banner_panel.setSize(,);
        banner_panel.add(banner);

        form_panel.add(new JLabel("Set Goal Time: "));
        JTextField hour_field = new JTextField();
        JTextField minute_field = new JTextField();
        minute_field.setMaximumSize(new Dimension(360, 20));
        hour_field.setMaximumSize(new Dimension(360, 20));
        JButton calculate_btn = new JButton("Calculate");
        JPanel hPanel = new JPanel();
        hPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        hPanel.setLayout(new BoxLayout(hPanel, BoxLayout.LINE_AXIS));
        hPanel.add(hour_field);
        hPanel.add(new JLabel(" hour"));
        form_panel.add(hPanel);
        JPanel mPanel = new JPanel();
        mPanel.setBorder(new EmptyBorder(new Insets(-20, 0, 0, 0)));
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.LINE_AXIS));
        mPanel.add(minute_field);
        mPanel.add(new JLabel(" minutes"));
        form_panel.add(mPanel);
        form_panel.add(calculate_btn);

        JPanel distance_panel = new JPanel();
        distance_panel.setBorder(new EmptyBorder(new Insets(10, 50, 5, 50)));

        ButtonGroup distanceButtonGroup = new ButtonGroup();
        JLabel distance_label = new JLabel("Distance: ");
        JRadioButton fiveKiloMeters = new JRadioButton("5KM");
        fiveKiloMeters.setSelected(true);
        JRadioButton tenKiloMeters = new JRadioButton("10KM");
        JRadioButton halfMarathon = new JRadioButton("Half-marathon");
        JRadioButton fullMarathon = new JRadioButton("Full-marathon");

        distanceButtonGroup.add(fiveKiloMeters);
        distanceButtonGroup.add(tenKiloMeters);
        distanceButtonGroup.add(halfMarathon);
        distanceButtonGroup.add(fullMarathon);

        distance_panel.add(distance_label);
        distance_panel.add(fiveKiloMeters);
        distance_panel.add(tenKiloMeters);
        distance_panel.add(halfMarathon);
        distance_panel.add(fullMarathon);

        LayoutManager distance_layout = new BoxLayout(distance_panel, BoxLayout.Y_AXIS);
        distance_panel.setLayout(distance_layout);

        JLabel result_label = new JLabel();

        calculate_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hour_field.getText().equals("")){
                    hour_field.setText("0");
                }
                if (minute_field.getText().equals("")){
                    minute_field.setText("0");
                }


                if (fiveKiloMeters.isSelected()){
                    double time = calculatePace(Double.parseDouble(hour_field.getText()), Integer.parseInt(minute_field.getText()), 5.0);
                    result_label.setText("You have to run " + Double.toString(time) + "min/km to finish at targeted time");
                } else if (tenKiloMeters.isSelected()) {
                    double time = calculatePace(Integer.parseInt(hour_field.getText()), Integer.parseInt(minute_field.getText()), 10.0);
                    result_label.setText("You have to run " + Double.toString(time) + " min/km to finish at targeted time");
                } else if (halfMarathon.isSelected()) {
                    double time = calculatePace(Integer.parseInt(hour_field.getText()), Integer.parseInt(minute_field.getText()), 21.1);
                    result_label.setText("You have to run " + Double.toString(time) + " min/km to finish at targeted time");
                } else if (fullMarathon.isSelected()) {
                    double time = calculatePace(Integer.parseInt(hour_field.getText()), Integer.parseInt(minute_field.getText()), 42.2);
                    result_label.setText("You have to run " + Double.toString(time) + " min/km to finish at targeted time");
                } else {
                    JOptionPane.showMessageDialog(frame, "No distance selected");
                }
            }
        });


        JPanel result_panel = new JPanel();
        result_panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
        result_panel.add(result_label);

        form_panel.add(result_label);

        frame.getContentPane().add(banner_panel, BorderLayout.NORTH);
        frame.getContentPane().add(form_panel, BorderLayout.CENTER);
        frame.getContentPane().add(distance_panel, BorderLayout.SOUTH);

    }

    private static double calculatePace(double hour, double min, double distance) {
        // convert hour into minutes\
        double hourMin = (hour * 60) + min;
        DecimalFormat df = new DecimalFormat("0.00");
        double value = hourMin /distance;
        return Double.parseDouble(df.format(value));
    }



}
