import javax.swing.JButton;

public class Btn extends JButton{
    private int row,col,count;
    private boolean mine, flag;

    public Btn(int row, int col){
        this.row=row;
        this.col=col;
        this.count=count;
        this.mine=mine;
        this.flag=false;

    }
    public boolean isMine(){
        return mine;
    }
    public boolean isFlag(){
        return flag;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row=row;
    }
    public int getCol(){
        return col;
    }
    public void setCol(int col){
        this.col=col;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }
    public void setMine(boolean mine){
        this.mine=mine;
    }
    public void setFlag(boolean flag){
        this.flag=flag;
    }

}