import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Color;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.sound.sampled.*;


@SuppressWarnings("serial")
public class GameEngine extends JFrame
{
	
	static Integer[] matchPoints = new Integer[45];
	static int firecoord;
    private JLabel positionLabel;
	public static int count = 0;
	public static int random = 0;
	public static JTextField textField; 
	public String content;
	static boolean set = false;
	public boolean fire;
	public static  JTextArea   chatBox;

  //Initializing the buttons
  
    public static JButton resetButton,aCarrier,bShip,cSub,dCruiser,eDestroyer,
    FireButton,EndGameButton,RandomizeShips,FinalizeShips,Output,Refresh;
	
	public static JLabel ACPic;
	
	public static JButton buttonF2[] = new JButton[100];
	public static JButton buttonF[] = new JButton[100];
	public static JButton button[] = new JButton[100];
    static int iterator = 0; 
    
    public static JLabel Boom = new JLabel();
    
	private static int gridSize = 10;
    static String Action;
	
// Creating Integer Objects to store the set coordinates for the ships
    static Integer AircraftCoordinates[] = new Integer[4];
    static Integer BattleShipCoordinates[] = new Integer[4];
    static Integer SubmarineCoordinates[] = new Integer[4];
    static Integer CruiserCoordinates[] = new Integer[4];
    static Integer DestroyerCoordinates[] = new Integer[4];
    static Integer ACpoints[] = new Integer[5];
    static Integer BSpoints[] = new Integer[5];
    static Integer SUBpoints[] = new Integer[5];
    static Integer Cpoints[] = new Integer [5];
    static Integer Dpoints[] = new Integer [5];
    
    int ShipLocation[] = new int[20];
    
 // Creating booleans for the ActionListener  
    static boolean AircraftCarrier = false;
	static boolean BattleShip = false;
	static boolean Submarine = false;
	static boolean Cruiser = false;
	static boolean Destroyer = false;
	
	//length of ships are correct
	static boolean 
	FullSizeAC = false, FullSizeBS = false, FullSizeSUB = false, FullSizeC = false, FullSizeD = false; 
	
	static boolean HideShipButtons = true;
	static boolean SettingUpShips = true;
	static boolean JumpShip = false;
	
	static boolean Vertical = false;
	
	
	public int ShipX;
	public int ShipY; 
	public int Sx,Sy,z1,z2;
	public static int PicX;
	public static int PicY = 0;
	
	static int[] Sendship(int[] ShipLocation) {
		return ShipLocation;
	}
	
	
//--------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------

public GameEngine()
    {
        super("Fleet Destroyer Engine - " + Player.getCurrentUser());
        super.setResizable(false);
        
    }

public void createAndDisplayGUI()
    {       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPane = new JPanel()
        {
            @Override
            public boolean isOptimizedDrawingEnabled()
            {
                return false;
            }
        };
        //Music();
       
        ImageIcon Explosion = new ImageIcon("Explosion.gif");
        Explosion.setImage(Explosion.getImage().getScaledInstance(750,750, Image.SCALE_DEFAULT));
        Boom = new JLabel(Explosion);
        Boom.setBounds(700,200,750,750);
        contentPane.add(Boom);
        Boom.setVisible(false);
        
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension (2400,1200));
		final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    
        JPanel buttonLeftPanel = new JPanel();
        
        
       ImageIcon ACImage = new ImageIcon("New Aircraft.jpg");
       final JLabel ACPic = new JLabel(ACImage);
       ACPic.setBackground(Color.BLACK);
       ACImage.setImage(ACImage.getImage().getScaledInstance(340, 40, Image.SCALE_DEFAULT));
       //ACPic.setBounds(1165,115,180,20);
       contentPane.add(ACPic);
       
       ImageIcon ACImageV = new ImageIcon("New AircraftV.jpg");
       final JLabel ACPicV = new JLabel(ACImageV);
       ACImageV.setImage(ACImageV.getImage().getScaledInstance(40, 340, Image.SCALE_DEFAULT));
       //ACPic.setBounds(1165,115,180,20);
       contentPane.add(ACPicV);
   
        
        ImageIcon BSImage = new ImageIcon("New battleship.jpg");
        final JLabel BSPic = new JLabel(BSImage);
        BSImage.setImage(BSImage.getImage().getScaledInstance(280, 40, Image.SCALE_DEFAULT));
        //BSPic.setBounds(1165,115,180,20);
        contentPane.add(BSPic);
        
        ImageIcon BSImageV = new ImageIcon("New battleship.jpg");
        final JLabel BSPicV = new JLabel(BSImageV);
        BSImageV.setImage(BSImageV.getImage().getScaledInstance(40, 280, Image.SCALE_DEFAULT));
        //BSPic.setBounds(1165,115,180,20);
        contentPane.add(BSPicV);
     
        
        ImageIcon SUBImage = new ImageIcon("New Submarine.jpg");
        final JLabel SUBPic = new JLabel(SUBImage);
        SUBImage.setImage(SUBImage.getImage().getScaledInstance(200, 40, Image.SCALE_DEFAULT));
        //SUBPic.setBounds(1160,165,130,20);
        contentPane.add(SUBPic);
        
        ImageIcon SUBImageV = new ImageIcon("New SubmarineV.jpg");
        final JLabel SUBPicV = new JLabel(SUBImageV);
        SUBImageV.setImage(SUBImageV.getImage().getScaledInstance(40, 200, Image.SCALE_DEFAULT));
        //SUBPic.setBounds(1160,165,130,20);
        contentPane.add(SUBPicV);
        
        
        ImageIcon CImage = new ImageIcon("New Cruiser.jpg");
        final JLabel CPic = new JLabel(CImage);
        CImage.setImage(CImage.getImage().getScaledInstance(200, 40, Image.SCALE_DEFAULT));
        //CPic.setBounds(1165,215,130,20);
        contentPane.add(CPic);
        
        ImageIcon CImageV = new ImageIcon("New CruiserV.jpg");
        final JLabel CPicV = new JLabel(CImageV);
        CImageV.setImage(CImageV.getImage().getScaledInstance(40, 200, Image.SCALE_DEFAULT));
        //CPic.setBounds(1165,215,130,20);
        contentPane.add(CPicV);
        
        ImageIcon DImage = new ImageIcon("New Destroyer.jpg");
        final JLabel DPic = new JLabel(DImage);
        DImage.setImage(DImage.getImage().getScaledInstance(120, 40, Image.SCALE_DEFAULT));
        //DPic.setBounds(1165,270,75,20);
        contentPane.add(DPic);
        
        ImageIcon DImageV = new ImageIcon("New DestroyerV.jpg");
        final JLabel DPicV = new JLabel(DImageV);
        DImageV.setImage(DImageV.getImage().getScaledInstance(40, 120, Image.SCALE_DEFAULT));
        //DPic.setBounds(1165,270,75,20);
        contentPane.add(DPicV);
        
       
        
        
        
      //-------------------------------------------------------------------
      //-------------------------------------------------------------------	      
        
        //Background 

        contentPane.setBackground(Color.DARK_GRAY);
        //ImageIcon BackgroundWater = new ImageIcon("Background water.jpg");
        ImageIcon BackgroundWater = new ImageIcon("Alt Background.jpg");
        BackgroundWater.setImage(BackgroundWater.getImage().getScaledInstance(1750, 1200, Image.SCALE_DEFAULT));
        JLabel BGridWater = new JLabel(BackgroundWater);
		BGridWater.setBounds(650,0,1750,1200);
		
        ImageIcon ButtonBackground = new ImageIcon("Walpaper.jpg");
        ButtonBackground.setImage(ButtonBackground.getImage().getScaledInstance(650, 1200, Image.SCALE_DEFAULT));
        JLabel ButtonBack = new JLabel(ButtonBackground);
        ButtonBack.setBounds(0, 0, 650, 1200);
        
        
        
        
        buttonLeftPanel.setOpaque(false);
        leftPanel.setOpaque(false);
        
	      //-------------------------------------------------------------------
	      //-------------------------------------------------------------------		
		
		ImageIcon GridWater = new ImageIcon("New Grid Water.gif");
        GridWater.setImage(GridWater.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
    
        
        //Home ships grid
		
        final JPanel ShipsPanel = new JPanel();
		ShipsPanel.setBounds(1600,200,750,750);
		
		
        //For adding GridWater to grids
       
		JLabel SGridWater = new JLabel(GridWater);
		ShipsPanel.add(SGridWater);
		SGridWater.setVisible(false);
		contentPane.add(ShipsPanel);	

		for (int i = 0; i < gridSize; i++)
		{
			for (int j = 0; j < gridSize; j++)
			{
				int y = j *10;
				int z = y+i;
				
			    button[z] = new JButton();
				
			    button[z].setPreferredSize(new Dimension(70,70));
			    button[z].setOpaque(true);
			    button[z].setBorderPainted(false);
			    button[z].setBackground(Color.BLUE);
				
				button[z].setActionCommand( j + "," + i );
				
				ShipsPanel.add(button[z]);
				
			}
		}
			
		
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------		
							//Firing Grid
    
	JPanel FirePanel = new JPanel();
	FirePanel.setBackground(Color.WHITE);	
	
	// adding GridWater
			
			JLabel FGridWater = new JLabel(GridWater);
			FirePanel.add(FGridWater);
			FGridWater.setVisible(false);
			contentPane.add(FirePanel);

			FirePanel.setBounds(700,200,750,750);	

					for (int a = 0; a < gridSize; a++)
					{
						for (int b = 0; b < gridSize; b++)
						{
							int y = b*10;
							int z = y+a;
							
						    buttonF[z] = new JButton();

						    buttonF[z].setPreferredSize(new Dimension(70,70));
						    buttonF[z].setOpaque(true);
						    buttonF[z].setBorderPainted(false);
						    buttonF[z].setBackground(Color.BLUE);
						    buttonF[z].setActionCommand( b + "," + a );
						    FirePanel.add(buttonF[z]);
								}
						}

		//-------------------------------------------------------------------
		//-------------------------------------------------------------------		
		
		
//Setting the Background and creating JLabels
        JLabel x1 = new JLabel("0    1    2    3    4    5    6    7    8   9");
			x1.setFont(new Font("Serif", Font.BOLD, 52));
			contentPane.add(x1);
			x1.setBounds(710,120,750,50);
			x1.setForeground(Color.WHITE);
		
		JLabel x2 = new JLabel("0    1    2    3    4    5    6    7    8   9");
			x2.setFont(new Font("Serif", Font.BOLD, 52));
			contentPane.add(x2);
			x2.setBounds(1610,120,750,50);
			x2.setForeground(Color.WHITE);
		
		JLabel y1 = new JLabel("<html>0<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9</html>");
			y1.setFont(new Font("Serif", Font.BOLD, 59));
			contentPane.add(y1);
			y1.setBounds(1510,80,530,1000);
			y1.setForeground(Color.WHITE);
		
		JLabel Opp = new JLabel("Target Range");
			Opp.setBounds(900,1000,400,75);
			Opp.setFont(new Font("Times New Roman", Font.BOLD, 50));
			contentPane.add(Opp);
			Opp.setForeground(Color.RED);
				
		JLabel Home = new JLabel("Your Ships");
			Home.setBounds(1875,1000,300,75);
			Home.setFont(new Font("Times New Roman", Font.BOLD, 50));
			Home.setForeground(Color.green);
			contentPane.add(Home);
			
        //Tells your coordinate pressed on your ships grid
        JPanel labelPanel = new JPanel();
        positionLabel = new JLabel();
        positionLabel.setFont(new Font("Algerian", Font.BOLD, 22));
        labelPanel.setBounds(0, 100, 100, 100);


        
        //Chat
		final JPanel ChatBox = displayChat();
		ChatBox.setBounds(120,1000,400,100);
		ChatBox.setVisible(false);
		ChatBox.setOpaque(false);
		
		
			
//--------------------------------------------------------------------------------------------		
//--------------------------------------------------------------------------------------------	

        	
//left side panel buttons
        resetButton = new JButton("Reset Board");
		aCarrier = new JButton("Set Aircraft Carrier");
		bShip = new JButton("Set BattleShip");
		cSub = new JButton("Set Submarine");
		dCruiser = new JButton("Set Cruiser");
		eDestroyer = new JButton("Set Destroyer");
		RandomizeShips = new JButton("Randomize Carrier");
		FinalizeShips = new JButton("Finalize Ships");
		FireButton = new JButton("Fire on Location");
		EndGameButton = new JButton("Exit Game");
		Output = new JButton("Output");
		Refresh = new JButton("Refresh Screen");
		
		resetButton.setForeground(Color.red);
		resetButton.setBackground(Color.DARK_GRAY);
		resetButton.setFont(new Font("Times New Roman", Font.BOLD, 35));
		aCarrier.setForeground(Color.red);
		aCarrier.setBackground(Color.DARK_GRAY);
		aCarrier.setFont(new Font("Times New Roman", Font.BOLD, 35));
		bShip.setForeground(Color.red);
		bShip.setBackground(Color.DARK_GRAY);
		bShip.setFont(new Font("Times New Roman", Font.BOLD, 35));
		cSub.setForeground(Color.red);
		cSub.setBackground(Color.DARK_GRAY);
		cSub.setFont(new Font("Times New Roman", Font.BOLD, 35));
		dCruiser.setForeground(Color.red);
		dCruiser.setBackground(Color.DARK_GRAY);
		dCruiser.setFont(new Font("Times New Roman", Font.BOLD, 35));
		eDestroyer.setForeground(Color.red);
		eDestroyer.setBackground(Color.DARK_GRAY);
		eDestroyer.setFont(new Font("Times New Roman", Font.BOLD, 35));
		RandomizeShips.setForeground(Color.red);
		RandomizeShips.setBackground(Color.DARK_GRAY);
		RandomizeShips.setFont(new Font("Times New Roman", Font.BOLD, 35));
		FinalizeShips.setForeground(Color.red);
		FinalizeShips.setBackground(Color.DARK_GRAY);
		FinalizeShips.setFont(new Font("Times New Roman", Font.BOLD, 35));
		FireButton.setBackground(Color.red);
		FireButton.setFont(new Font("Times New Roman", Font.BOLD, 35));
		EndGameButton.setBackground(Color.red);
		EndGameButton.setFont(new Font("Times New Roman", Font.BOLD, 35));
		

//left panel graphics
		labelPanel.add(positionLabel);
		//leftPanel.add(labelPanel);

		//buttonLeftPanel.setLayout(new BoxLayout(buttonLeftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBounds(150,1000,350,350); 
		buttonLeftPanel.setBounds(120,50,400,650);
		//leftPanel.setBorder(new LineBorder(Color.black, 5));

		contentPane.add(buttonLeftPanel);
		contentPane.add(leftPanel);
		contentPane.add(ChatBox);

		 /*Dimension D = new Dimension();
		 D = aCarrier.getPreferredSize();
		 resetButton.setPreferredSize(new Dimension (130,26));
		 System.out.print(D);*/
		RandomizeShips.setAlignmentX(Component.CENTER_ALIGNMENT);
		FinalizeShips.setAlignmentX(Component.CENTER_ALIGNMENT);
		FinalizeShips.setBounds(0,0,500,100);
		FireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		EndGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		leftPanel.add(RandomizeShips);
		leftPanel.add(FinalizeShips);
		buttonLeftPanel.add(aCarrier);
		buttonLeftPanel.add(bShip);
		buttonLeftPanel.add(cSub);
		buttonLeftPanel.add(dCruiser);
		buttonLeftPanel.add(eDestroyer);
		buttonLeftPanel.add(resetButton);
		leftPanel.add(FireButton);
		leftPanel.add(EndGameButton);
		//buttonLeftPanel.add(Output);
		//buttonLeftPanel.add(Refresh);
		
		contentPane.add(ButtonBack);
		contentPane.add(BGridWater);
		
		 
//--------------------------------------------------------------------------------------------		
//--------------------------------------------------------------------------------------------			
			//Test button for debugging
		Output.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ae){ 
			/*
			 * System.out.println("  ");
			 
			System.out.println("  ");
			System.out.println("Acpoints"+Arrays.toString(ACpoints));
			System.out.println("ACcoordinates"+Arrays.toString(AircraftCoordinates));
			System.out.println("BSpoints"+Arrays.toString(BSpoints));
			System.out.println("BScoordinates"+Arrays.toString(BattleShipCoordinates));
			System.out.println("SUBpoints"+Arrays.toString(SUBpoints));
			System.out.println("SUBcoordinates"+Arrays.toString(SubmarineCoordinates));
			System.out.println("Cpoints"+Arrays.toString(Cpoints));
			System.out.println("Ccoordinates"+Arrays.toString(CruiserCoordinates));
			System.out.println("Dpoints"+Arrays.toString(Dpoints));
			System.out.println("Dcoordinates"+Arrays.toString(DestroyerCoordinates));
			System.out.println("  ");
			System.out.println("  ");
			*/
		}});
		
		//protocol for placing the Aircraft Carrier
	aCarrier.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = true;
					BattleShip = false;
					Submarine = false;
					Cruiser = false;
					Destroyer = false;
					
					ACPic.setVisible(false);
					
					if(AircraftCoordinates[2] != null){
						Reset(AircraftCoordinates,5);
						FullSizeAC = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your AircraftCarrier (5 spaces long)");

					}});
	
		//protocol for placing the Battleship
	bShip.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = true;
					Submarine = false;
					Cruiser = false;
					Destroyer = false;
					
					BSPic.setVisible(false);
					
					if(BattleShipCoordinates[2] != null){
						Reset(BattleShipCoordinates,4);
						FullSizeBS = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your BattleShip (4 spaces long)");
				}});
		
		//protocol for placing the Submarine
	cSub.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = false;
					Submarine = true;
					Cruiser = false;
					Destroyer = false;
					
					SUBPic.setVisible(false);
					
					if(SubmarineCoordinates[2] != null){
						Reset(SubmarineCoordinates,3);
						FullSizeSUB = false;
					}	
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Submarine (3 spaces long)");
				}});
		
		//protocol for placing the Cruiser
	dCruiser.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = false;
					Submarine = false;
					Cruiser = true;
					Destroyer = false;
					
					CPic.setVisible(false);
					
					if(CruiserCoordinates[2] != null){
						Reset(CruiserCoordinates,3);
						FullSizeC = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Cruiser (3 spaces long)");
				}});

		//protocol for placing the Destroyer
	eDestroyer.addActionListener(new ActionListener() 

				{
				public void actionPerformed(ActionEvent ae){ 
					AircraftCarrier = false;
					BattleShip = false;
					Submarine = false;
					Cruiser = false;
					Destroyer = true;
					
					DPic.setVisible(false);
					
					if(DestroyerCoordinates[2] != null){
						Reset(DestroyerCoordinates,2);
						FullSizeD = false;
					}
					
					JOptionPane.showMessageDialog(null, "Pick first and last coordinate for"
							+ " your Destroyer (2 spaces long)");
					}});

//-------------------------------------------------------------------
//-------------------------------------------------------------------		
					//Randomly places ships on board (not finished)

//Can't put any ships on the board b4 using hard wire it wont move the black boxes
	
RandomizeShips.addActionListener(new ActionListener() 
{
	public void actionPerformed(ActionEvent ae){
		/*if (random == -1){
			FullSizeAC = false;
			FullSizeBS = false;
			FullSizeSUB = false;
			FullSizeC = false;
			FullSizeD = false;
			random = 0;
		}*/

		boolean next = false;
		
		
		//while( random <5){
			
			Random rand = new Random();
			int Size = 0;
			//random = 0;
			
			if(random == 0){
				
				Size = 4;
				while (next == false){
					FullSizeAC = false;
					ACPic.setVisible(false);
					ACPicV.setVisible(false);
					Reset(AircraftCoordinates,5);
					int RxCoor1 = rand.nextInt(9);
					int RxCoor2 = 0;
					int RyCoor1 = rand.nextInt(9);
					int RyCoor2 = 0;
					int Direction = rand.nextInt(4);
					
				if (Direction ==1){
					RyCoor2 = RyCoor1;
					RxCoor2 = Math.abs(RxCoor1+Size)%10;
					//System.out.println(RxCoor2);
				}
				if (Direction ==2){
					RyCoor2 = RyCoor1;
					RxCoor2 = Math.abs(RxCoor1-Size)%10;
					//System.out.println(RxCoor2);
				}
				if (Direction ==3){
					RyCoor2 = Math.abs(RyCoor1+Size)%10;
					RxCoor2 = RxCoor1;
					//System.out.println(RyCoor2);
				}
				if (Direction ==4){
					RyCoor2 = Math.abs(RyCoor1-Size)%10;
					RxCoor2 = RxCoor1;
					//System.out.println(RyCoor2);
				}
					
					AircraftCarrier = true;
					BattleShip = false;
					Submarine = false;
					Cruiser = false;
					Destroyer = false;
					
					takeCoordinates(RxCoor1, RyCoor1, AircraftCoordinates, FullSizeAC,4);
					takeCoordinates(RxCoor2, RyCoor2, AircraftCoordinates, FullSizeAC,4);
					ACPic.setVisible(false);
					//takeCoordinates(0, 0, AircraftCoordinates, FullSizeAC,4);
					//takeCoordinates(4, 0, AircraftCoordinates, FullSizeAC,4);
					
					if(FullSizeAC == true){
						FillInShip(AircraftCoordinates,5, ACpoints);
						
						if (JumpShip == false){
							next =true;
							aCarrier.setVisible(false);
							RandomizeShips.setText("Randomize BattleShip");
							RefreshScreen();
							JOptionPane.showMessageDialog(null, "Your AircraftCarrier is in position");
							if(Vertical == true){
								ACPicV.setVisible(true);
								ACPic.setVisible(false);
								ACPicV.setBounds(PicX, PicY, 50, 350);
							}else{
								ACPic.setVisible(true);
								ACPicV.setVisible(false);
								ACPic.setBounds(PicX,PicY,350,50);
							}
							random = random+1;
						}
						else{Reset(AircraftCoordinates,5);}
					}		
				}
			}
			
			if(random == 1){
							
					Size = 3;
				while (next == false){
					FullSizeBS = false;
					BSPic.setVisible(false);
					BSPicV.setVisible(false);
					Reset(BattleShipCoordinates,4);
					int RxCoor1 = rand.nextInt(9);
					int RxCoor2 = 0;
					int RyCoor1 = rand.nextInt(9);
					int RyCoor2 = 0;
					int Direction = rand.nextInt(4);
					
					if (Direction ==1){
						RyCoor2 = RyCoor1;
						RxCoor2 = Math.abs(RxCoor1+Size)%10;
						//System.out.println(RxCoor2);
					}
					if (Direction ==2){
						RyCoor2 = RyCoor1;
						RxCoor2 = Math.abs(RxCoor1-Size)%10;
						//System.out.println(RxCoor2);
					}
					if (Direction ==3){
						RyCoor2 = Math.abs(RyCoor1+Size)%10;
						RxCoor2 = RxCoor1;
						//System.out.println(RyCoor2);
					}
					if (Direction ==4){
						RyCoor2 = Math.abs(RyCoor1-Size)%10;
						RxCoor2 = RxCoor1;
						//System.out.println(RyCoor2);
					}
					AircraftCarrier = false;
					BattleShip = true;
					Submarine = false;
					Cruiser = false;
					Destroyer = false;
					takeCoordinates(RxCoor1, RyCoor1, BattleShipCoordinates, FullSizeBS,3 );
					takeCoordinates(RxCoor2, RyCoor2, BattleShipCoordinates, FullSizeBS,3 );
					BSPic.setVisible(false);
					//takeCoordinates(0, 1, BattleShipCoordinates, FullSizeBS,3 );
					//takeCoordinates(3, 1, BattleShipCoordinates, FullSizeBS,3 );
					
					if(FullSizeBS == true){
						FillInShip(BattleShipCoordinates,4, BSpoints);
						if (JumpShip == false){
							next =true;
							bShip.setVisible(false);
							RandomizeShips.setText("Randomize Submarine");
							RefreshScreen();
							JOptionPane.showMessageDialog(null, "Your BattleShip is in position");
							if(Vertical == true){
								BSPicV.setVisible(true);
								BSPic.setVisible(false);
								BSPicV.setBounds(PicX, PicY, 50, 280);
							}else{
								BSPic.setVisible(true);
								BSPicV.setVisible(false);
								BSPic.setBounds(PicX,PicY,280,50);
							}
							random = random+1;
						}
						else{Reset(BattleShipCoordinates,4);}
					}		
				}
			}
			
			
			if(random == 2){
				
				Size = 2;
				while (next == false){
					FullSizeSUB = false;
					SUBPic.setVisible(false);
					SUBPicV.setVisible(false);
					Reset(SubmarineCoordinates,3);
					int RxCoor1 = rand.nextInt(9);
					int RxCoor2 = 0;
					int RyCoor1 = rand.nextInt(9);
					int RyCoor2 = 0;
					int Direction = rand.nextInt(4);
					
					if (Direction ==1){
						RyCoor2 = RyCoor1;
						RxCoor2 = Math.abs(RxCoor1+Size)%10;
						//System.out.println(RxCoor2);
					}
					if (Direction ==2){
						RyCoor2 = RyCoor1;
						RxCoor2 = Math.abs(RxCoor1-Size)%10;
						//System.out.println(RxCoor2);
					}
					if (Direction ==3){
						RyCoor2 = Math.abs(RyCoor1+Size)%10;
						RxCoor2 = RxCoor1;
						//System.out.println(RyCoor2);
					}
					if (Direction ==4){
						RyCoor2 = Math.abs(RyCoor1-Size)%10;
						RxCoor2 = RxCoor1;
						//System.out.println(RyCoor2);
					}
					AircraftCarrier = false;
					BattleShip = false;
					Submarine = true;
					Cruiser = false;
					Destroyer = false;
					takeCoordinates(RxCoor1, RyCoor1, SubmarineCoordinates, FullSizeSUB,2 );
					takeCoordinates(RxCoor2, RyCoor2, SubmarineCoordinates, FullSizeSUB,2 );
					SUBPic.setVisible(false);
					//takeCoordinates(0, 2, SubmarineCoordinates, FullSizeSUB,2 );
					//takeCoordinates(2, 2, SubmarineCoordinates, FullSizeSUB,2 );
					
					if(FullSizeSUB == true){
						FillInShip(SubmarineCoordinates,3, SUBpoints);
						if (JumpShip == false){
							next =true;
							cSub.setVisible(false);
							RandomizeShips.setText("Randomize Cruiser");
							RefreshScreen();
							JOptionPane.showMessageDialog(null, "Your Submarine is in position");
							if(Vertical == true){
								SUBPicV.setVisible(true);
								SUBPic.setVisible(false);
								SUBPicV.setBounds(PicX, PicY, 50, 200);
							}else{
								SUBPic.setVisible(true);
								SUBPicV.setVisible(false);
								SUBPic.setBounds(PicX,PicY,200,50);
							}
							random = random+1;
						}
						else{Reset(SubmarineCoordinates,3);}
					}		
				}
			}
		
			if(random == 3){
				
					Size = 2;
					while (next == false){
						FullSizeC = false;
						CPic.setVisible(false);
						CPicV.setVisible(false);
						Reset(CruiserCoordinates,3);
						int RxCoor1 = rand.nextInt(9);
						int RxCoor2 = 0;
						int RyCoor1 = rand.nextInt(9);
						int RyCoor2 = 0;
						int Direction = rand.nextInt(4);
						
						if (Direction ==1){
							RyCoor2 = RyCoor1;
							RxCoor2 = Math.abs(RxCoor1+Size)%10;
							//System.out.println(RxCoor2);
						}
						if (Direction ==2){
							RyCoor2 = RyCoor1;
							RxCoor2 = Math.abs(RxCoor1-Size)%10;
							//System.out.println(RxCoor2);
						}
						if (Direction ==3){
							RyCoor2 = Math.abs(RyCoor1+Size)%10;
							RxCoor2 = RxCoor1;
							//System.out.println(RyCoor2);
						}
						if (Direction ==4){
							RyCoor2 = Math.abs(RyCoor1-Size)%10;
							RxCoor2 = RxCoor1;
							//System.out.println(RyCoor2);
						}
						AircraftCarrier = false;
						BattleShip = false;
						Submarine = false;
						Cruiser = true;
						Destroyer = false;
						
						takeCoordinates(RxCoor1, RyCoor1, CruiserCoordinates, FullSizeC,2 );
						takeCoordinates(RxCoor2, RyCoor2, CruiserCoordinates, FullSizeC,2 );
						CPic.setVisible(false);
						//takeCoordinates(0, 3, CruiserCoordinates, FullSizeC,2 );
						//takeCoordinates(2, 3, CruiserCoordinates, FullSizeC,2 );
							
						if(FullSizeC == true){
							FillInShip(CruiserCoordinates,3, Cpoints);
							if (JumpShip == false){
								next =true;
								dCruiser.setVisible(false);
								RandomizeShips.setText("Randomize Destroyer");
								RefreshScreen();
								JOptionPane.showMessageDialog(null, "Your Cruiser is in position");
								if(Vertical == true){
									CPicV.setVisible(true);
									CPic.setVisible(false);
									CPicV.setBounds(PicX, PicY, 50, 200);
								}else{
									CPic.setVisible(true);
									CPicV.setVisible(false);
									CPic.setBounds(PicX,PicY,200,50);
								}
								random = random+1;
							}
							else{Reset(CruiserCoordinates,3);}	
					}
					}
			}
			
			if(random == 4){
				
					Size = 1;
					while (next == false){
						FullSizeD = false;
						DPic.setVisible(false);
						DPicV.setVisible(false);
						Reset(DestroyerCoordinates,2);
						int RxCoor1 = rand.nextInt(9);
						int RxCoor2 = 0;
						int RyCoor1 = rand.nextInt(9);
						int RyCoor2 = 0;
						int Direction = rand.nextInt(4);
						
						if (Direction ==1){
							RyCoor2 = RyCoor1;
							RxCoor2 = Math.abs(RxCoor1+Size)%10;
							//System.out.println(RxCoor2);
						}
						if (Direction ==2){
							RyCoor2 = RyCoor1;
							RxCoor2 = Math.abs(RxCoor1-Size)%10;
							//System.out.println(RxCoor2);
						}
						if (Direction ==3){
							RyCoor2 = Math.abs(RyCoor1+Size)%10;
							RxCoor2 = RxCoor1;
							//System.out.println(RyCoor2);
						}
						if (Direction ==4){
							RyCoor2 = Math.abs(RyCoor1-Size)%10;
							RxCoor2 = RxCoor1;
							//System.out.println(RyCoor2);
						}
						AircraftCarrier = false;
						BattleShip = false;
						Submarine = false;
						Cruiser = false;
						Destroyer = true;
						takeCoordinates(RxCoor1, RyCoor1, DestroyerCoordinates, FullSizeD,1 );
						takeCoordinates(RxCoor2, RyCoor2, DestroyerCoordinates, FullSizeD,1 );
						DPic.setVisible(false);
						//takeCoordinates(0, 4, DestroyerCoordinates, FullSizeD,1 );
						//takeCoordinates(1, 4, DestroyerCoordinates, FullSizeD,1 );
						
							
						if(FullSizeD == true){
							FillInShip(DestroyerCoordinates,2, Dpoints);
							if (JumpShip == false){
								next =true;
								eDestroyer.setVisible(false);
								RandomizeShips.setText("Randomize Carrier");
								RefreshScreen();
								JOptionPane.showMessageDialog(null, "Your Destroyer is in position");
								if(Vertical == true){
									DPicV.setVisible(true);
									DPic.setVisible(false);
									DPicV.setBounds(PicX, PicY, 50, 120);
								}else{
									DPic.setVisible(true);
									DPicV.setVisible(false);
									DPic.setBounds(PicX,PicY,120,50);
								}
								random = 0;
							}
							else{Reset(DestroyerCoordinates,2);}
						}
					}
			}
			//if (next == true){
			//random = random+1;
			//}
		//}
	}});

//-------------------------------------------------------------------	
//-------------------------------------------------------------------			
	
	//Firing Functionality
		
// JTEXTFIELD FOCUS,WHERE WE ENTER COORDINATES	
		textField = new JTextField("Enter Coordiantes to Fire!",5);
		textField.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 14));
		leftPanel.add(textField);
		textField.requestFocusInWindow();
		textField.setColumns(10);

		fire = true;
		textField.requestFocusInWindow();
		
		textField.setVisible(false);
		FireButton.setVisible(false);
		EndGameButton.setVisible(false);
		
		//clear text field and re populates when focus lost
		textField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					textField.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE,36));
					textField.setText("");
		      }
		      public void focusLost(FocusEvent e) {
		    	  //textField.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 14));
		    	  //textField.setText("");
		      }});
		
		
		//grabs coordinates for fire box
		textField.addActionListener(new ActionListener()
		{
		
		public void actionPerformed(ActionEvent event)
			{
			content = textField.getText();
			//System.out.print(content);
			}
		});
		
		//button for firing on coordinates
		FireButton.addActionListener(new ActionListener()
		{
		
		public void actionPerformed(ActionEvent event) {
			String FireCoordinates = textField.getText();
			FireCoordinates = FireCoordinates.replaceAll("[^0-9.]", "");
			if(FireCoordinates.length() == 2){
				Client.setFire(textField.getText());
			}
			
			textField.setText("");
			content = textField.getText();
			//System.out.print(content);
			}
			

		
		});
		//button for firing on coordinates
		EndGameButton.addActionListener(new ActionListener()
		{
		
		public void actionPerformed(ActionEvent event) {
			dispose();
			}
			

		
		});
		
//----------------------------------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------------------------------		
				// Brings back a button after the ship has been set on the board (more to come)

		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				ACPic.setVisible(false);
				ACPicV.setVisible(false);
				BSPic.setVisible(false);
				BSPicV.setVisible(false);
				SUBPic.setVisible(false);
				SUBPicV.setVisible(false);
				CPic.setVisible(false);
				CPicV.setVisible(false);
				DPic.setVisible(false);
				DPicV.setVisible(false);
				
				if(AircraftCoordinates[2] != null){
					Reset(AircraftCoordinates,5);
					FullSizeAC = false;
					aCarrier.setVisible(true);
					for(int i = 0; i<5;i++){
						ACpoints[i] = null;
					}
				}
				if(BattleShipCoordinates[2] != null){
					Reset(BattleShipCoordinates,4);
					FullSizeBS = false;
					bShip.setVisible(true);
					for(int i = 0; i<5;i++){
						BSpoints[i] = null;
					}
				}
				
				if(SubmarineCoordinates[2] != null){
					Reset(SubmarineCoordinates,3);
					FullSizeSUB = false;
					cSub.setVisible(true);
					for(int i = 0; i<5;i++){
						SUBpoints[i] = null;
					}
				}
				if(CruiserCoordinates[2] != null){
					Reset(CruiserCoordinates,3);
					FullSizeC = false;
					dCruiser.setVisible(true);
					for(int i = 0; i<5;i++){
						Cpoints[i] = null;
					}
				}
				if(DestroyerCoordinates[2] != null){
					Reset(DestroyerCoordinates,2);
					FullSizeD = false;
					eDestroyer.setVisible(true);
					for(int i = 0; i<5;i++){
						Dpoints[i] = null;
					}
				}
				random = 0;
			}
		}
				);
		
		Refresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				RefreshScreen();
			}});
//-------------------------------------------------------------------
//-------------------------------------------------------------------	
		//protocol for locking in ships

	FinalizeShips.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent ae){
				
				if (FullSizeAC == true && FullSizeBS == true && FullSizeSUB == true 
						&& FullSizeC == true && FullSizeD == true){
					
						aCarrier.setVisible(false);
						bShip.setVisible(false);
						cSub.setVisible(false);
						dCruiser.setVisible(false);
						eDestroyer.setVisible(false);
						
						AircraftCarrier = false;
						BattleShip = false;
						Submarine = false;
						Cruiser = false;
						Destroyer = false;
						
						
						leftPanel.setBounds(120,55,400,200);
						FinalizeShips.setVisible(false);
						textField.setVisible(true);
						ChatBox.setVisible(true);
						FireButton.setVisible(true);
						resetButton.setVisible(false);
						RandomizeShips.setVisible(false);
						ShipsPanel.setOpaque(false);
						
						//FGridWater.setVisible(true);
						//SGridWater.setVisible(true);
						
						
	
					
				//puts all ship coordinates into 1 array 
					
					JOptionPane.showMessageDialog(null, "Your ships are set load the cannons!!");
						int x = 0;
						for(int z = 0; z < 20; z++){
						
							if(x == 4)
							{x = 0;}
							
							if (z<4){
								ShipLocation[z] = AircraftCoordinates[x];
								x++;
							}
							else if(z>=4 && z<8){
								ShipLocation[z] = BattleShipCoordinates[x];
								x++;
							}
							else if(z>=8 && z<12){
								ShipLocation[z] = SubmarineCoordinates[x];
								x++;
							}
							else if(z>=12 && z<16){
								ShipLocation[z] = CruiserCoordinates[x];
								x++;
							}
							else if(z>=16 && z<20){
								ShipLocation[z] = DestroyerCoordinates[x];
								x++;
							}	
						}
						String shipfinalcoordinates = "";
						for(int i = 0 ; i <20; i++){
							if(i==0){
								shipfinalcoordinates += "AircraftCarrier ";
							}
							if(i==4){
								shipfinalcoordinates += " BattleShip ";
							}
							if(i==8){
								shipfinalcoordinates += " Submarine ";
							}
							if(i==12){
								shipfinalcoordinates += " Cruiser ";
							}
							if(i==16){
								shipfinalcoordinates += " Destroyer ";
							}

							shipfinalcoordinates += "" + ShipLocation[i];
							
						}
						setFinalCoordinates(shipfinalcoordinates);
		}
			
			else{
				JOptionPane.showMessageDialog(null, "You haven't set up all of your ships yet");
			}
			
			set=true;
		}
	}
);
//-------------------------------------------------------------------
//-------------------------------------------------------------------
		
		
					
//Action Listener for Home Ships Grid	
	
	for (int i = 0; i < gridSize; i++){
		for (int j = 0; j < gridSize; j++){	
			int z = j*10+i;	
			button[z].addActionListener(new ActionListener(){
				
	public void actionPerformed(ActionEvent ae){
		
		JButton but = (JButton) ae.getSource();
			Action = but.getActionCommand();
			String [] strArray = Action.split(",");
			int [] intArray = new int [strArray.length];
		
			for (int i = 0; i < strArray.length; i++){
				intArray[i] = Integer.parseInt(strArray[i]);
							Sx = intArray[0];
							Sy = intArray[1];
				}
			
if(AircraftCarrier == true){
	
			count = count +1;
			takeCoordinates(Sx, Sy, AircraftCoordinates, FullSizeAC,4);
			z1 = (Sx*10)+Sy;
			button[z1].setBackground(Color.black);
			
			if(count == 2 && FullSizeAC == true){
					count = 0;
					FillInShip(AircraftCoordinates,5, ACpoints);
					AircraftCarrier = false;
				
				if(JumpShip == false){
						JOptionPane.showMessageDialog
						(null,"Your AircraftCarrier is in position");
				
					aCarrier.setVisible(false);
					
					if(Vertical == true) 
						{ACPicV.setVisible(true);ACPicV.setBounds(PicX, PicY, 50, 350);}
					else 
						{ACPic.setVisible(true);ACPic.setBounds(PicX,PicY,350,50);}
				}
				
					else{
						FullSizeAC =false;
						JOptionPane.showMessageDialog(null, "Abondon Ship!");
						}
			}
			
			else if(count == 2 && FullSizeAC != true){
					JOptionPane.showMessageDialog(null, "Repick your second coordinate or press Set"
					+ " Aircraft Carrier to start again");
				count = 1;
				button[z1].setBackground(Color.BLUE);
			}
}

else if(BattleShip == true){
				
				count = count + 1;
				takeCoordinates(Sx, Sy, BattleShipCoordinates, FullSizeBS,3 );
				z1 = ((Sx*10)+Sy);
				button[z1].setBackground(Color.black);
				
				if(count == 2 && FullSizeBS == true){
						count = 0;
						FillInShip(BattleShipCoordinates,4, BSpoints);
						BattleShip = false;	
					
					if(JumpShip == false){
						
						JOptionPane.showMessageDialog
						(null, "Your BattleShip is in Position");
						bShip.setVisible(false);
							
						if(Vertical == true)
							{BSPicV.setVisible(true);BSPicV.setBounds(PicX, PicY, 50, 280);}
						else
							{BSPic.setVisible(true);BSPic.setBounds(PicX,PicY,280,50);}
					}
					else
					{FullSizeBS =false; JOptionPane.showMessageDialog(null, "Abondon Ship!"); }
				}
			
				else if(count == 2 && FullSizeBS != true){
				JOptionPane.showMessageDialog
				(null, "Repick your second coordinate or press Set BattleShip to start again");
				count = 1;
				button[z1].setBackground(Color.BLUE);
			}
	}

else if (Submarine == true){
				count = count + 1;
				takeCoordinates(Sx, Sy, SubmarineCoordinates, FullSizeSUB,2 );
				z1 = (Sx*10)+Sy;
				button[z1].setBackground(Color.black);
					
					if(count == 2 && FullSizeSUB == true){	
							count = 0;
							FillInShip(SubmarineCoordinates,3,SUBpoints);
							Submarine = false;
						
						if(JumpShip == false){
							
							JOptionPane.showMessageDialog(null, "Your Submarine is in Position");
							cSub.setVisible(false);
							
							if(Vertical == true)
								{SUBPicV.setVisible(true);SUBPicV.setBounds(PicX, PicY, 50, 200);}
							else
							{SUBPic.setVisible(true);SUBPic.setBounds(PicX,PicY,200,50);}
						}
						else
							{FullSizeSUB =false;JOptionPane.showMessageDialog(null, "Abondon Ship!");}
					}
					else if(count == 2 && FullSizeSUB != true){
						
						JOptionPane.showMessageDialog
						(null, "Repick your second coordinate or press Set Submarine to start again");
						count = 1;
						button[z1].setBackground(Color.BLUE);
					}
	}

else if (Cruiser == true){
		
			count = count+1;
			takeCoordinates(Sx, Sy, CruiserCoordinates, FullSizeC,2 );
			z1 = (Sx*10)+Sy;
			button[z1].setBackground(Color.black);
					
				if(count == 2 && FullSizeC == true){										
						count = 0;
						FillInShip(CruiserCoordinates,3,Cpoints);
						Cruiser = false;
					
					if(JumpShip == false){
							JOptionPane.showMessageDialog(null, "Your Cruiser is in position");
							dCruiser.setVisible(false);
						
						if(Vertical == true)
							{CPicV.setVisible(true); CPicV.setBounds(PicX, PicY, 50, 200);}
						else
							{CPic.setVisible(true); CPic.setBounds(PicX,PicY,200,50);}
					}
					
					else
						{FullSizeC =false; JOptionPane.showMessageDialog(null, "Abondon Ship!");}
				}
				
				else if(count == 2 && FullSizeC != true){
					
					JOptionPane.showMessageDialog
					(null, "Repick your second coordinate or press Set Cruiser to start again");
					count = 1;
					button[z1].setBackground(Color.BLUE);
				}
	}

else if (Destroyer == true){
				count = count +1;
				takeCoordinates(Sx, Sy, DestroyerCoordinates, FullSizeD,1 );
				z1 = (Sx*10)+Sy;
				button[z1].setBackground(Color.black);
					
					if(count == 2 && FullSizeD == true){										
							count = 0;
							FillInShip(DestroyerCoordinates,2,Dpoints);
							Destroyer = false;
							
						if(JumpShip == false){
								JOptionPane.showMessageDialog(null, "Your Destoyer is in position");
								eDestroyer.setVisible(false);
							if(Vertical == true)
								{DPicV.setVisible(true);DPicV.setBounds(PicX, PicY, 50, 120);}
							else
								{DPic.setVisible(true);DPic.setBounds(PicX,PicY,120,50);}
						}
						
						else
							{FullSizeD =false;JOptionPane.showMessageDialog(null, "Abondon Ship!");}
					}
					
					else if(count == 2 && FullSizeD != true){
						JOptionPane.showMessageDialog
						(null, "Repick your second coordinate or press Set Destroyer to start again");
						count = 1;
						button[z1].setBackground(Color.BLUE);
					}
	}
}});		
		}
	}
						setContentPane(contentPane);
						pack();
						setLocationRelativeTo(null); 
						setLocationByPlatform(true);
						setVisible(true);
						//setExtendedState(JFrame.MAXIMIZED_BOTH);    
    }
//-------------------------------------------------------------------
//-------------------------------------------------------------------
//-------------------------------------------------------------------


					//Places ship coordinates in their arrays
// Almost there just can't get this method to change the FullSize bo0leans ie. FullsizeAC wont change when Full changes
public void takeCoordinates(int X, int Y, Integer Coordinates[], boolean Full, int Size ){
	
	if(Coordinates[0] == null){
		Coordinates[0] = X;
		Coordinates[1] = Y;
	}else{
			Coordinates[2] = X;
			Coordinates[3] = Y;
	}
	
	if (Coordinates[2] != null){
		
		int x1 = Coordinates[0];
		int y1 = Coordinates[1];
		int x2 = Coordinates[2];
		int y2 = Coordinates[3];
		int DeltaY = y2-y1;
		int DeltaX = x2-x1;
			
			if((x1 == x2) && (Math.abs(DeltaY)==Size)){ 
				Full = true;
				//Temporary until we get Full to manipulate the actual booleans		
				if (AircraftCarrier == true){FullSizeAC = true;}
				if(BattleShip == true){FullSizeBS = true;}
				if(Submarine == true){FullSizeSUB = true;}
				if(Cruiser == true){FullSizeC = true;}
				if(Destroyer == true){FullSizeD = true;}
			}
			else if((y1==y2) && (Math.abs(DeltaX)==Size)){
				Full = true;
				//Temporary until we get Full to manipulate the actual booleans		
				if (AircraftCarrier == true){FullSizeAC = true;}
				if(BattleShip == true){FullSizeBS = true;}
				if(Submarine == true){FullSizeSUB = true;}
				if(Cruiser == true){FullSizeC = true;}
				if(Destroyer == true){FullSizeD = true;}
			}			
	}
}

public void RefreshScreen(){
	for(int i = 0; i<5; i++){
		if(ACpoints[i] != null){
			button[ACpoints[i]].setBackground(Color.black);
		}
		if(BSpoints[i] != null){
			button[BSpoints[i]].setBackground(Color.black);
		}
		if(SUBpoints[i] != null){
			button[SUBpoints[i]].setBackground(Color.black);
		}
		if(Cpoints[i] != null){
			button[Cpoints[i]].setBackground(Color.black);
		}
		if(Dpoints[i] != null){
			button[Dpoints[i]].setBackground(Color.black);
		}
	}
}

/// HADIIIIII
static String shiplocations = "";
public void setFinalCoordinates(String fcoord){
	shiplocations = fcoord;
}
public static String getFinalCoordinates(){
	return shiplocations;
}
//////Hadi
		//Places ship on board and checks for overlap (might want to split to two methods)
public static void FillInShip(Integer[] Coor,int x, Integer[] blocks){
	Integer[] points = blocks;
	//int x = points.length;
	
	JumpShip = false;
	
	for (int i = 0;i<45;i++){
			matchPoints[i] = null;
		}
	
	// right
		if( Coor[1] == Coor[3] && Coor[0]<Coor[2]){
		       
			Vertical = false;
							for(int a = 0; a<x;a++){
								int t = (Coor[0]*10) + Coor[1];
								t = t + a*10;
								button[t].setBackground(Color.black);								
								points[a] = t;
								
							}
							//Bound = button[].getAlignmentX();
					
			}
		
		// left
		if( Coor[1] == Coor[3] && Coor[2]<Coor[0]){
					
			Vertical = false;
							for(int a = 0; a<x;a++){
								int t = (Coor[2]*10) + Coor[1];
								t = t + a*10;
								button[t].setBackground(Color.black);
								points[a] = t;
								
							}
			}

		// down
		if( Coor[0] == Coor[2] && Coor[1]<Coor[3]){
			
			Vertical = true;
					
							for(int a = 0; a<x;a++){
								int t = (Coor[0]*10) + Coor[1];
								t = t + a;
								button[t].setBackground(Color.black);
								points[a] = t;
								
							}
				}
		// up
		if( Coor[0] == Coor[2] && Coor[1]>Coor[3]){
			
			Vertical = true;
					
							for(int a = 0; a<x;a++){
								int t = (Coor[0]*10) + Coor[3];
								t = t + a;
								button[t].setBackground(Color.black);
								points[a] = t;
								
							}
		}
		
		PicX = 1610+(button[points[0]].getX());
		PicY = 210+(button[points[0]].getY());
		
		int match = 0;
		int matchAC = 0;
		int matchBS = 0;
		int matchSUB = 0;
		int matchC = 0;
		int matchD = 0;
		
		//static Integer[] matchPoints = new Integer[45];
		int spot = 0;
		
		//checks new ship point a with every point of each ship b
		//the puts them in an array matchPoints at AC(0-4)+BS(10-14)+SUB(20-24)+C(30-34)+D(40-44)
		
			for (int a = 0; a<5;a++){
				for (int b = 0;b<5;b++){
					if((points[a] == ACpoints[b]) && (points[a] != null) && points != ACpoints ){
						matchAC = matchAC + 1;
							spot = a;
							matchPoints[spot] = points[a];
							//System.out.println("Match at AC point"+ACpoints[b]);
					}
					if((points[a] == BSpoints[b]) && (points[a] != null) && points != BSpoints){
						matchBS = matchBS + 1;
							spot = 10+a;
							matchPoints[spot] = points[a];
							//System.out.println("Match at BS point"+BSpoints[b]);
					}	
					if((points[a] == SUBpoints[b]) && (points[a] != null) && points != SUBpoints ){
						matchSUB = matchSUB + 1;	
							spot = 20+a;
							matchPoints[spot] = points[a];
						//	System.out.println("Match at SUB point"+SUBpoints[b]);
					}	
					if((points[a] == Cpoints[b]) && (points[a] != null) && points != Cpoints ){
						matchC = matchC + 1;	
							spot = 30+a;
							matchPoints[spot] = points[a];
						//	System.out.println("Match at C point"+Cpoints[b]);
					}
					if((points[a] == Dpoints[b]) && (points[a] != null) && points != Dpoints ){
						matchD = matchD + 1;
							spot = 40+a;
							matchPoints[spot] = points[a];
							//System.out.println("Match at D point"+Dpoints[b]);
					}
					
				}
			}
			match = matchAC+matchBS+matchSUB+matchC+matchD;
			
			if(match != 0){
				JumpShip = true;
				Reset(Coor,x);
				for(int i = 0; i<5;i++){
					points[i] = null;
				}
				
			for (int i = 0;i<45;i++){
				if(matchPoints[i]!=null){
					//System.out.println("Make point"+matchPoints[i]+ "Black");
					button[matchPoints[i]].setBackground(Color.black);
					//System.out.println("MatchArray" + Arrays.toString(matchPoints));
					matchPoints[i] = null;
				}
			}
			
			}
			
			
			//Thread.flush();
}
		//Takes ship off the board and replaces the 'overlap block' for the other ship
		//Use points not coordinates and say if != match points
public static void Reset(Integer[] Coor, int x){
		
	if((Coor[1]!=null) && (Coor[2]!=null)){
	
		// right
	if( Coor[1] == Coor[3] && Coor[0]<Coor[2]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[0]*10) + Coor[1];
							t = t + a*10;
							button[t].setBackground(Color.BLUE);
						}
		}
	
	// left
	if( Coor[1] == Coor[3] && Coor[2]<Coor[0]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[2]*10) + Coor[1];
							t = t + a*10;
							button[t].setBackground(Color.BLUE);
						}
		}

	// down
	if( Coor[0] == Coor[2] && Coor[1]<Coor[3]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[0]*10) + Coor[1];
							t = t + a;
							button[t].setBackground(Color.BLUE);
						}
			}
	// up
	if( Coor[0] == Coor[2] && Coor[1]>Coor[3]){
				
						for(int a = 0; a<x;a++){
							int t = (Coor[0]*10) + Coor[3];
							t = t + a;
							button[t].setBackground(Color.BLUE);
						}
			}
	

		for(int c = 0;c<4;c++){
			Coor[c] = null;
		} 
		count = 0;
	}


}


public JPanel displayChat() {
	    final JTextField  messageField;
	
	
    JPanel ChatBox = new JPanel();
    ChatBox.setLayout(new BorderLayout());

    JPanel chatPanel = new JPanel();
    chatPanel.setOpaque(false);
    chatPanel.setLayout(new GridBagLayout());
    
   
    
    messageField = new JTextField(20);
    messageField.setText("Send Message");
	messageField.setFont(new Font("Serif", Font.ITALIC,20));
    messageField.setBorder(new LineBorder(Color.BLACK, 3));
    messageField.requestFocusInWindow();
   
    chatBox = new JTextArea();
    chatBox.setEditable(false);
    chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
    chatBox.setForeground(Color.RED);
    chatBox.setLineWrap(true);
    chatBox.setBackground(Color.BLACK);
    

    
   messageField.addActionListener(new ActionListener(){//hit enter to send text
	    

	        public void actionPerformed(ActionEvent event) {
	            if (messageField.getText().length() < 1) {
	            	
	            } else if (messageField.getText().equals(".clear")) {
	                chatBox.setText("CHAT CLEARED\n");
	                messageField.setText("");
	            } else {
	            	String message = "<" + Player.getCurrentUser() + ">:  " + messageField.getText()+ "\n";
	                try {
						ChatClient.sendMessage(message);
					} catch (IOException e) {}
	            	messageField.setText("");
	            }}
	            
		});
    
	messageField.addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent e) {
			messageField.setText("");
			messageField.setFont(new Font("Serif", Font.PLAIN, 20));
			//System.out.print("gained");
      }
      public void focusLost(FocusEvent e) {
    	  messageField.setText("Send Message");
    	  messageField.setFont(new Font("Serif", Font.ITALIC,20));
    	 // System.out.print("lost");
    	  
      }});
    
    messageField.setBackground(Color.DARK_GRAY);
    messageField.setForeground(Color.red);
    ChatBox.add(new JScrollPane(chatBox), BorderLayout.CENTER);
    chatPanel.add(messageField);

    ChatBox.add(BorderLayout.SOUTH, chatPanel);
	return ChatBox;

    
}
public static void Music(){
	try {
	File Music = new File("Background.wav");
	 AudioInputStream audioIn = AudioSystem.getAudioInputStream(Music);
	 Clip clip = AudioSystem.getClip();
	 clip.open(audioIn);
	 clip.loop(5);
	 
	 }
	 
	 catch (UnsupportedAudioFileException e) {
		 e.printStackTrace();
	  } catch (IOException e) {
		 e.printStackTrace();
	  } catch (LineUnavailableException e) {
		 e.printStackTrace();
	  }
	
}

//-----------------------MAINNNNNNNNNN-------------------------------

	
public static void main(String[] args){
         SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	
  
              new GameEngine().createAndDisplayGUI();
              
              
            }

        });
   

    }
}
