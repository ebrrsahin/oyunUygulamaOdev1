import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class MayinTarlasi implements MouseListener {
    JFrame frame;
    Btn[][] board= new Btn[10][10];
    int openButton;

    public MayinTarlasi() {
        openButton = 0;
        frame = new JFrame("Mayın Tarlası");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 10));

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Btn b = new Btn(row, col);
                frame.add(b);
                b.addMouseListener(this);
                board[row][col] = b;
            }
        }
        generateMine();
        updateCount();
        frame.setVisible(true);
    }
        public void generateMine () {
            int i = 0;
            while (i < 10) {
                int randRow = (int) (Math.random() * board.length);
                int randCol = (int) (Math.random() * board[0].length);

                while (board[randRow][randCol].isMine()) {
                    randRow = (int) (Math.random() * board.length);
                    randCol = (int) (Math.random() * board[0].length);
                }
                board[randRow][randCol].setMine(true);
                i++;
            }
        }
        public void print(){
            for(int row=0; row<board.length; row++){
                for(int col=0;col<board[0].length;col++){
                    if(board[row][col].isMine()){
                        board[row][col].setIcon(new ImageIcon("mine.png"));
                    }
                    else{
                        board[row][col].setText(board[row][col].getCount()+"");
                        board[row][col].setEnabled(false);
                    }
                }
            }
        }
        public void printMine(){
            for(int row=0; row<board.length; row++){
                for(int col=0; col<board[0].length; col++){
                    if(board[row][col].isMine()){
                        board[row][col].setIcon(new ImageIcon("mine.png"));
                    }
                }
            }
        }
        public void updateCount(){
            for(int row=0; row<board.length; row++ ){
                for(int col=0; col<board[0].length; col++){
                    if(board[row][col].isMine()){
                        counting(row,col);
                    }
                }
            }
        }
        public void counting(int row, int col){
            for(int i=row-1; i<=row+1; i++){
                for(int j=col-1;j<=col+1; j++){
                    try{
                        int value= board[i][j].getCount();
                        board[i][j].setCount(++value);
                    }
                    catch(Exception exception){

                    }
                }
            }
        }
        public void open(int row, int col){
            if(row<0 || row>=board.length || col<0
            || col>=board[0].length || board[row][col].getText().length() > 0
            || board[row][col].isEnabled()==false){
                return;
            }
            else if(board[row][col].getCount()!=0){
                board[row][col].setText(board[row][col].getCount()+"");
                board[row][col].setEnabled(false);
                openButton++;
            }
            else{
                openButton++;
                board[row][col].setEnabled(false);
                open(row-1, col);
                open(row+1, col);
                open(row,col-1);
                open(row, col+1);
            }
        }
        public void mouseClicked(MouseEvent e){
        Btn b =(Btn) e.getComponent();
        if(e.getButton()==1){
            System.out.println("sol");
            if(b.isMine()){
                JOptionPane.showMessageDialog(frame, "GAME OVER!!!");
                print();
            }
            else{
                open(b.getRow(), b.getCol());
                if(openButton==(board.length* board[0].length)-10){
                    JOptionPane.showMessageDialog(frame, "YOU WIN!!!");
                    print();
                }
            }
        }
        else if(e.getButton()==3){
            System.out.println("sağ");
            if(!b.isFlag()){
                b.setIcon((new ImageIcon("flag.png")));
                b.setFlag(true);
            }else{
                b.setIcon(null);
                b.setFlag(false);
            }
        }
        }
    @Override
    public void mouseEntered(MouseEvent e) {
        Btn b = (Btn) e.getComponent();
        if (!b.isFlag() && b.isEnabled()) {
            JOptionPane.showMessageDialog(frame, "1");
            if (b.isMine()) {
                b.setIcon(new ImageIcon("Image/mine.png"));
            } else {
                b.setText(b.getCount() + "");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Btn b = (Btn) e.getComponent();
        if (!b.isFlag() && b.isEnabled()) {

            b.setText("");
            b.setIcon(null);
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }}