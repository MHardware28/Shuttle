
import javax.swing.*;
import java.awt.*;
import java.util.List;

/** Shuttle GUI
 * AUTHORED BY RICH-ANN
* The ShuttleGUI is the main user interface for the shuttle tracking system.

Display screens such as:
Start panel
Shuttle type panel (Day / Night / Weekend)
 Stop selector panel
 Live tracking panel (simulation of real-time updates)
 Handle all user interactions:
 Clicking buttons
 Choosing shuttle type
 Choosing a stop
 Returning to previous screens
 Receive shuttle status updates from the Facade and print them to
  the live tracking window.
 pop ups

**/

public class ShuttleGUI extends JFrame implements Observer {

    private final ShuttleFacade facade = new ShuttleFacade();//new instance to pull on backend knowledge
    private final CardLayout layout = new CardLayout();//multiple screens
    private final JPanel mainPanel = new JPanel(layout);//main panel

    //define UI components for each stage of the flow
    private JPanel startPanel;
    private JPanel typePanel;
    private JPanel stopPanel;
    private JPanel livePanel;

    //defining live panel components
    private JTextArea liveArea;
    private JButton unsubscribeBtn;
    private JButton backBtnLive;

    //selected stop filter
    private String selectedStop = null;

    public ShuttleGUI() {
        setTitle("NCF Shuttle Tracker");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

        //build each screen now so navigation is ready
        buildStartPanel();
        buildTypePanel();
        buildStopPanel();
        buildLivePanel();

        add(mainPanel);  //add the CardLayout panel to the frame
        setVisible(true); //required for user to see and interact
    }



    private void buildStartPanel() {
        startPanel = new JPanel(new BorderLayout());
        startPanel.setBackground(new Color(210, 245, 255));

        //title
        JLabel title = new JLabel("Where is the Shuttle?", SwingConstants.CENTER);
        title.setFont(new Font("Script MT Bold", Font.BOLD, 35));
        title.setForeground(new Color(90, 0, 140)); //rgb color blue

        //load shuttle image
        ImageIcon icon = new ImageIcon("C:/Users/r.campbell27/Downloads/shuttle.png");
        Image scaled = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        //makes image smaller to fit
        JLabel imageLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);

        //find shuttle button
        JButton goBtn = new JButton("Find Shuttle");
        goBtn.setFont(new Font("Arial", Font.BOLD, 30));
        goBtn.setPreferredSize(new Dimension(200, 80));//sizing
        goBtn.addActionListener(e -> layout.show(mainPanel, "TYPE"));

        //ADD panels
        add(imageLabel, BorderLayout.NORTH);
        startPanel.add(title, BorderLayout.NORTH);
        startPanel.add(imageLabel, BorderLayout.CENTER);
        startPanel.add(goBtn, BorderLayout.SOUTH);

        mainPanel.add(startPanel, "START");
    }

    private void selectWeekend(){
        facade.selectShuttleType(ShuttleType.WEEKEND);
        facade.stopSimulation();
        JOptionPane.showMessageDialog(this, "Shuttle requested from your location");
        loadStops();
        layout.show(mainPanel, "STOP");
    }

    private void buildTypePanel() {
        //grid with 5row  1col, 20px space
        typePanel = new JPanel(new GridLayout(5, 1, 10, 20));

        JLabel lbl = new JLabel("Select Shuttle Type", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));

        //all buttons
        JButton day = new JButton("Day Shuttle (7AM–3PM)");
        JButton night = new JButton("Night Shuttle (3PM–11PM)");
        JButton weekend = new JButton("Weekend Shuttle (On Demand)");
        JButton back = new JButton("Back");

        day.addActionListener(e -> selectType(ShuttleType.DAY));
        night.addActionListener(e -> selectType(ShuttleType.NIGHT));
        /*weekend.addActionListener(e -> {
            selectType(ShuttleType.WEEKEND);
            facade.selectShuttleType(ShuttleType.WEEKEND);
            JOptionPane.showMessageDialog(this, "Weekend shuttle requested from your location.");
            showLiveScreen("Weekend Shuttle Requested");*/
        weekend.addActionListener(e -> selectWeekend());



        back.addActionListener(e -> layout.show(mainPanel, "START"));

        //panel setup/order
        typePanel.add(lbl);
        typePanel.add(day);
        typePanel.add(night);
        typePanel.add(weekend);
        typePanel.add(back);

        mainPanel.add(typePanel, "TYPE");
    }

    private void selectType(ShuttleType type) {
        facade.selectShuttleType(type);//tells the facade the selected type
        facade.stopSimulation(); //clean start/stops old simulations
        //pop up
        JOptionPane.showMessageDialog(this, "Shuttle type selected.");
        loadStops();
        layout.show(mainPanel, "STOP");//card lbl stop

    }


    private JButton backBtnStop;

    private void buildStopPanel() {

        //stopPanel = new JPanel(new GridLayout(5, 1, 30, 50));
        stopPanel = new JPanel();
        stopPanel.setLayout(new BoxLayout(stopPanel, BoxLayout.Y_AXIS));//box layout for scrolling/list
        JLabel lbl;


        if (facade.getSelectedType() == ShuttleType.WEEKEND) {
            lbl = new JLabel("Where do you want to go?", SwingConstants.CENTER);
        } else {
            lbl = new JLabel("Choose a Stop", SwingConstants.CENTER);

        }
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopPanel.add(lbl);


        //back button and back listener
        backBtnStop = new JButton("Back");
        backBtnStop.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtnStop.addActionListener(e -> layout.show(mainPanel, "TYPE"));

        mainPanel.add(stopPanel, "STOP");
    }

    private void loadStops() {
        stopPanel.removeAll();
        buildStopPanel(); //reset header & back button

        List<Stop> stops = facade.getStops();//lizt of facade stops
        if (stops == null)
            return;

        for (Stop s : stops) {//loop to create a button for each stop
            JButton btn = new JButton(s.getName());
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);

            btn.addActionListener(e -> {
                selectedStop = s.getName();//save selected stop
                subscribeToStop(s.getName());//let the facade tell the observer to subscribe
            });

            stopPanel.add(btn);//add stop button
        }

        stopPanel.add(Box.createVerticalStrut(20));
        stopPanel.add(backBtnStop);


    }

    private void subscribeToStop(String stopName) {
        JOptionPane.showMessageDialog(this, "You've subscribed to: " + stopName);

        facade.unsubscribeAll();//unsubscribe to old stops
        facade.subscribeStop(stopName, this);
        facade.startSimulation();//strt eta simulation

        showLiveScreen("You've subscribed to: " + stopName);
    }


    private void buildLivePanel() {
        livePanel = new JPanel(new BorderLayout());

        //new simulation text area uneditable by subscriber
        liveArea = new JTextArea();
        liveArea.setEditable(false);

        //one unsubscribe and one back button
        unsubscribeBtn = new JButton("Unsubscribe");
        unsubscribeBtn.addActionListener(e -> {
            facade.unsubscribeAll();
            JOptionPane.showMessageDialog(this, "Unsubscribed.");
        });

        backBtnLive = new JButton("Back");
        backBtnLive.addActionListener(e -> {
            facade.unsubscribeAll();
            layout.show(mainPanel, "STOP");
        });

        JPanel bottomBtns = new JPanel();//jpanel to place buttons together
        bottomBtns.add(unsubscribeBtn);
        bottomBtns.add(backBtnLive);

        livePanel.add(new JScrollPane(liveArea), BorderLayout.CENTER);
        livePanel.add(bottomBtns, BorderLayout.SOUTH);//place them on bottom

        mainPanel.add(livePanel, "LIVE");//cardlayout live lbl
    }

    private void showLiveScreen(String initialMsg) {
        liveArea.setText(initialMsg + "\n");//show subscribed msg
        layout.show(mainPanel, "LIVE");
    }

    @Override

    public void update(String description, double eta) {
        SwingUtilities.invokeLater(() -> {//prevent freezing


            String msg = description;
            if (eta > 0) {//if the eta is more than zero we keep sending updates
                int minutes = (int) eta;
                msg += " | ETA: " + minutes + " min";
            }
            liveArea.append(msg + "\n");

            //send update at 2 mins away
            if (eta == 2.0 && selectedStop != null && description.contains(selectedStop)) {
                JOptionPane.showMessageDialog(
                        this,
                        "The shuttle is 2 minutes away from " + selectedStop + "!",
                        "Shuttle Approaching",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            //popup when the shuttle arrives at the selected stop
            if (description.contains("has arrived") && description.contains(selectedStop)) {
                JOptionPane.showMessageDialog(
                        this,
                        "The shuttle has arrived at " + selectedStop + "!",
                        "Shuttle Arrived",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShuttleGUI::new);
    }
}